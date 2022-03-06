package com.example.hjy.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hjy.myapplication.DB.DBHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by hjy on 2018-04-04.
 */

public class Speak extends AppCompatActivity {  //호명방식 출결 진행창

    Button btnstore;
    String  subjectname=" ", dayofweek2,  pname="";
    String date= " ", code="", date1="";
    String sql8;

    DBAdapter adapter;
    TextView subject;
    ListView listView;

    String id;
    String [] Ctime;
    String [] sname;
    Integer num;

    View view;

    Cursor cr, cr1, cr2, cr3, cr4;

    RadioGroup rg;
    RadioButton rdo1, rdo2, rdo3;

    String s3;


    SharedPreferences key;
    SharedPreferences.Editor editorkey;

    DBHelper myHelper;
    SQLiteDatabase sqlDB;

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onStop() {
        super.onStop();
        cr.close(); cr1.close(); cr2.close(); cr3.close();cr4.close();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speak);
        listView = (ListView)findViewById(R.id.listview);

        subject = (TextView) findViewById(R.id.subject);
        btnstore = (Button) findViewById(R.id.btnStore);

        key = getSharedPreferences("loginkey", 0);
        editorkey = key.edit();

        myHelper = new DBHelper(this);

        if (key.getBoolean("loginkey", true)) {  //불러올때
            pname = key.getString("name", "");  //교수
        }


        GregorianCalendar toDay = new GregorianCalendar();
        int dayofweek1= toDay.get(toDay.DAY_OF_WEEK);

        switch (dayofweek1){

            case 1: dayofweek2="일";
                break;
            case 2: dayofweek2="월";
                break;
            case 3: dayofweek2="화";
                break;
            case 4: dayofweek2="수";
                break;
            case 5: dayofweek2="목";
                break;
            case 6: dayofweek2="금";
                break;
            case 7: dayofweek2="토";
                break;
        }

        sqlDB = myHelper.getWritableDatabase();
        String sql = "select S_name from Subject where P_no =(select P_no from Professor where " +
                "P_name ='" + pname + "') and S_code in (select S_code from C_info where C_day ='" + dayofweek2 + "');";
        cr = sqlDB.rawQuery(sql, null);
        if (cr.getCount() > 0) {
            while (cr.moveToNext()) {
                subjectname = cr.getString(0);
                subject.setText(subjectname);  //과목
            }

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd", Locale.KOREA);
            Date currentTime = new Date();
            final String todayDate = simpleDateFormat.format(currentTime); //오늘 날짜

            subject.setText(subjectname + "  " + todayDate);

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
            actionBar.setTitle("호명 방식");

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
                                startActivity(intent2);
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


            String sql2 = "select S_code from Subject where S_name='"+subjectname+"';";
            cr2 = sqlDB.rawQuery(sql2, null);
            while(cr2.moveToNext()){
                code = cr2.getString(0);
            }

            String sql1="select Name from '"+code+"';";  //
            cr1 = sqlDB.rawQuery(sql1, null);
            //학생 명단
            //select Name from Student where No in (

            final int count = cr1.getCount();
            sname= new String[count];

            for(int i=0; i<count; i++){
                cr1.moveToNext();
                sname[i] = cr1.getString(0);
            }

            try {
                date1 = "2018/" + date;

                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
                Date nDate = simpleDateFormat.parse(date1);
                Calendar cal = Calendar.getInstance();
                cal.setTime(nDate);

                int dayofweek11 = cal.get(Calendar.DAY_OF_WEEK);

                switch (dayofweek11) {

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
            sql8 = "drop table if exists contact;";
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
                adapter = new DBAdapter(Speak.this, cr4, true);
                listView.setAdapter(adapter);
            }



            btnstore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int a= count*count1;
                    String s="";

                    try {
                       Cursor c1, c2;
                       String s1="select name from contact where _id='"+id+"'";

                        String s2 = "select min(A_no) from Attend where C_no in(select min(C_no) from C_info where S_code = " +
                                "(select S_code from Subject where S_name='" + subjectname + "' and C_day='" + dayofweek2 + "')) and Date='" + todayDate + "'";
                        c2 = sqlDB.rawQuery(s2, null);
                        while (c2.moveToNext()) {
                            num = c2.getInt(0);
                        }
                        for (int i = 0; i < a; i++) {
                            if(rdo1.isChecked())
                                s="출석";
                            else if(rdo2.isChecked())
                                s="지각";
                            else
                                s="결석";
                             s3 = "update Attend set State='"+s+"' where A_no='" + (num++) + "';";
                            sqlDB.execSQL(s3);
                        }

                        c2.close();
                        Intent intent = new Intent(getApplicationContext(), Activity2.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(), "저장하지 못하였습니다.", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    sqlDB.close();
                }
            });




        }

    }
        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            getMenuInflater().inflate(R.menu.menu, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
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



    public class DBAdapter extends CursorAdapter { //superclass : CursorAdapter

        public DBAdapter(Context context, Cursor c, boolean autoRequery) {
            super(context, c, autoRequery);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) { //만들어준 xml을 객체화 시켜주기 위한 레이아웃 인플레이터 역할을 함

            LayoutInflater inflater = LayoutInflater.from(context);
             view = inflater.inflate(R.layout.listlayout, parent, false);
             rg = (RadioGroup)view.findViewById(R.id.rg);
             rdo1 = (RadioButton)view.findViewById(R.id.rdo1);
             rdo2= (RadioButton)view.findViewById(R.id.rdo1);
             rdo3 = (RadioButton)view.findViewById(R.id.rdo1);
            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {  //실제 데이터를 넣어줌

             id=cursor.getString(cursor.getColumnIndex("_id"));

            TextView txtName =(TextView) view.findViewById(R.id.txtName);
            TextView txtClass =(TextView) view.findViewById(R.id.txtClass);

            txtName.setText(cursor.getString(cursor.getColumnIndex("name")));
            txtClass.setText(cursor.getString(cursor.getColumnIndex("time")));


            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if(rdo1.isChecked()){

                    }
                    if(rdo2.isChecked()){
                        sqlDB.execSQL("UPDATE contact SET State='지각' Where _id="+id+";");
                    }
                    if(rdo3.isChecked()){
                       sqlDB.execSQL("UPDATE contact SET State='결석' Where _id="+id+";");


                    }

                }
            });




        }
    }




}
