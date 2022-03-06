package com.example.hjy.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hjy.myapplication.DB.DBHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by hjy on 2018-04-05.
 */

public class Pro_subject extends AppCompatActivity {  //과목별로 진행된 출결창

    public DrawerLayout mDrawerLayout;
    SharedPreferences key;
    SharedPreferences.Editor editorkey;
    ListView lv;
    ArrayList<String> item;

    DBHelper myHelper;
    SQLiteDatabase sqlDB;
    int count;
    String  date1="", dayofweek2="";
    TextView pro;
    String [] date;
    String st1, st2;
    String classname;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pro_subject);

        myHelper = new DBHelper(this);
        pro = (TextView)findViewById(R.id.pro);
        lv = (ListView) findViewById(R.id.listview2);

        item = new ArrayList<String>();

        key=getSharedPreferences("loginkey", 0);
        editorkey= key.edit();

        Intent in = getIntent();
        classname = in.getStringExtra("classname");
        pro.setText(classname);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(key.getBoolean("loginkey", true)) {  //로그인함
                    Intent intent = new Intent(getApplicationContext(), Activity2.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }
            }
        });
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("출석 목록");

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //menuItem.setChecked(true);
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
                        if(key.getBoolean("loginkey", true)) {  //로그인함
                            Intent intent2 = new Intent(getApplicationContext(),ClassList.class);
                            startActivity(intent2);
                            finish();

                        }else{
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




        sqlDB = myHelper.getWritableDatabase(); //DB열기
        Cursor cr, cr1, cr2;
        item.clear();
        String sql = "select distinct Date from Attend where C_no in (select C_no from C_info where S_code = " +
                "(select S_code from Subject where S_name='"+classname+"')) order by substr(Date, 1, 2) desc, substr(Date, 4, 2) desc ;";
        cr = sqlDB.rawQuery(sql, null);  //날짜

         count = cr.getCount();
         date= new String[count];
         final String [] result= new String [count];

        for(int i=0; i<count; i++){
               cr.moveToNext();
               date[i] = cr.getString(0);

            try {
                date1 = "2018/" + date[i];

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


            //오늘 날짜와 같거나 전에 있는 날짜만 출력
           // if() {
                String sql1 =
            "select count(*) from Attend where C_no = (select max(C_no) from C_info where S_code = " +
                    "(select S_code from Subject where S_name='" + classname + "' and C_day='"+dayofweek2+"')) and Date='" + date[i] + "' and State='출석';";
                cr1 = sqlDB.rawQuery(sql1, null);
                cr1.moveToNext();
                st1 = cr1.getString(0); //출석 학생수

                String sql2 = "select count(*) from Attend where C_no = (select max(C_no) from C_info where S_code = " +
                        "(select S_code from Subject where S_name='" + classname + "' and C_day='"+dayofweek2+"')) and Date='" + date[i] + "'";
                cr2 = sqlDB.rawQuery(sql2, null);
                cr2.moveToNext();
                st2 = cr2.getString(0);  //전체 학생수
                result[i] = "   " + date[i] + "               " + st1 + "  /  " + st2 + "                 " + Double.parseDouble(st1) / Double.parseDouble(st2) * 100 + "%";
            //   }
            }
        ArrayAdapter adapter = (ArrayAdapter) new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, result);
        lv.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        cr.close();
        sqlDB.close();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent = new Intent(getApplicationContext(), AttendList.class);  //출결리스트를 만든다면 수정
                                intent.putExtra("date", date[i]);
                                intent.putExtra("classname", classname);
                                startActivityForResult(intent, 0);

                editorkey.putString("date", date[i]);
                //editorkey.putString("classname", classname);
                editorkey.commit();  //저장(안해주면 변경값 저장

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
