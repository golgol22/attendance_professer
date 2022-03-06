package com.example.hjy.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hjy.myapplication.DB.DBHelper;

import org.apache.http.conn.ConnectTimeoutException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.Inflater;


/**
 * Created by hjy on 2018-05-24.
 */

public class AttendList extends AppCompatActivity {  //호명방식 출결 진행창

    ListView lv;
    DBAdapter adapter;

    Button btnstore;
    String classname=" ", date= " ", code="", date1="", dayofweek2="";
    TextView subject;
    Cursor  cr, cr1, cr3, cr4;


    String [] Ctime;
    String [] sname;

    SharedPreferences key;
    SharedPreferences.Editor editorkey;

    DBHelper myHelper;
    SQLiteDatabase sqlDB;

    private DrawerLayout mDrawerLayout;


    @Override
    protected void onStop() {
        super.onStop();
        cr.close(); cr1.close();  cr3.close();cr4.close();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendlist);


        lv = (ListView) findViewById(R.id.listview);
        subject = (TextView) findViewById(R.id.subject);
        btnstore = (Button) findViewById(R.id.btnStore);

        key = getSharedPreferences("loginkey", 0);
        editorkey = key.edit();

        myHelper = new DBHelper(this);

        Intent in = getIntent(); // 출결확인
        date = in.getStringExtra("date"); //출결을 확인하고 싶은 날짜
        classname = in.getStringExtra("classname"); //과목이름
        subject.setText(classname + " " + date);


        sqlDB = myHelper.getWritableDatabase(); //DB열기

        String sql = "select S_code from Subject where S_name='"+classname+"';";
        cr = sqlDB.rawQuery(sql, null);
        while(cr.moveToNext()){
            code = cr.getString(0);
        }

        String sql1="select Name from '"+code+"';";
        cr1 = sqlDB.rawQuery(sql1, null);

        final int count = cr1.getCount();
        sname= new String[count];

        for(int i=0; i<count; i++){
            cr1.moveToNext();
            sname[i] = cr1.getString(0);
        }

      try {
          date1 = "2018/" + date;

          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
          Date nDate = simpleDateFormat.parse(date1);
          Calendar cal = Calendar.getInstance();
          cal.setTime(nDate);

          int dayofweek1 = cal.get(Calendar.DAY_OF_WEEK);

          switch (dayofweek1) {

              case 1:
                  dayofweek2 = "일";
                  break;
              case 2:
                  dayofweek2 = "월";
                  break;
              case 3:
                  dayofweek2 = "화";
                  break;
              case 4:
                  dayofweek2 = "수";
                  break;
              case 5:
                  dayofweek2 = "목";
                  break;
              case 6:
                  dayofweek2 = "금";
                  break;
              case 7:
                  dayofweek2 = "토";
                  break;
          }
      }catch (ParseException e){
          e.printStackTrace();
      }
        String sql3 = "select T_no from C_info where S_code ='"+code+"' and C_day='"+dayofweek2+"';";
        cr3 = sqlDB.rawQuery(sql3, null);
        //교시명
        final int count1 = cr3.getCount();
        Ctime= new String[count1];

        for(int i=0; i<count1; i++){
            cr3.moveToNext();
            Ctime[i] = cr3.getString(0);
        }

        String sql8 = "drop table if exists contact;";
        sqlDB.execSQL(sql8);

        String sql5 = "create table contact (_id integer primary key autoincrement, " +
                "name char(20), time char(20));";
        sqlDB.execSQL(sql5);

        String sql6 = "insert into contact values(null, ?, ?)";
        for(int i=0; i<count; i++) {
            for(int j=0; j<count1;j++) {
                Object[] args = {sname[i], Ctime[j]};
                sqlDB.execSQL(sql6, args);
            }
        }

        String sql7 = "select * from contact;";
        cr4 = sqlDB.rawQuery(sql7, null);


        if(cr4.getCount()>0){
            adapter = new DBAdapter(AttendList.this, cr4, true);
            lv.setAdapter(adapter);
        }
        sqlDB.close();






        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (key.getBoolean("loginkey", true)) {  //로그인함
                    Intent intent = new Intent(getApplicationContext(), Activity2.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }
            }
        });
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("출결확인");

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.login:
                        if(key.getBoolean("loginkey", true)) {  //로그인함
                            editorkey.putBoolean("loginkey", false);
                            editorkey.clear();
                            editorkey.commit();
                            finish();
                            Toast.makeText(getApplicationContext(), "로그아웃되었습니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }else{
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);
                            finish();
                        }
                        break;


                    case R.id.list:
                        if (key.getBoolean("loginkey", true)) {  //로그인함
                            Intent intent2 = new Intent(getApplicationContext(), ClassList.class);
                            startActivityForResult(intent2, 7);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "먼저 로그인을 해주세요.", Toast.LENGTH_SHORT).show();
                            Intent intent2 = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent2);
                        }
                        break;

                    case R.id.howtouse:
                        finish();

                }
                return true;
            }
        });

        btnstore.setOnClickListener(new View.OnClickListener() {  //저장
            @Override
            public void onClick(View view) {

                //DB에 저장하는 소스


                Intent intent = new Intent(getApplicationContext(), Activity2.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finish();
                return true;

            case R.id.menubar:
                mDrawerLayout.openDrawer(GravityCompat.END);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



}