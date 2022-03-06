package com.example.hjy.myapplication;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hjy.myapplication.DB.DBHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


/**
 * Created by hjy on 2018-05-19.
 */

public class Activity2 extends AppCompatActivity{  //로그인한 메인화면
    private DrawerLayout mDrawerLayout;

    TextView name, date, subject, time;
    Button speak, wifidirect;

    SharedPreferences key;
    SharedPreferences.Editor editorkey;

    DBHelper myHelper;
    SQLiteDatabase sqlDB;

    String subjectname, classtime="", dayofweek2, pname="";

    int month, day;

    long now = System.currentTimeMillis(); //현재시간을 구함
    Date date2= new Date(now); //현재시간을(now) 변수에 저장
    SimpleDateFormat formatTime = new SimpleDateFormat("HH"); //시간을 나타내는 형식을 결정
    SimpleDateFormat formatTime2 = new SimpleDateFormat("mm");
    String hour = formatTime.format(date2); //현재 시간을 형식에 맞게 만들고 변수에 저장
    String min = formatTime2.format(date2);

    String st1, st2, et1, et2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        key=getSharedPreferences("loginkey", 0);
        editorkey= key.edit();

        myHelper = new DBHelper(this);

        name= (TextView)findViewById(R.id.name);
        date= (TextView)findViewById(R.id.date);
        subject= (TextView)findViewById(R.id.subject);
        time= (TextView)findViewById(R.id.time);

        speak =(Button)findViewById(R.id.speak);
        wifidirect =(Button)findViewById(R.id.wifi_direct);


        if(key.getBoolean("loginkey", true)) {  //불러올때
            pname=key.getString("name", "");
            name.setText("교수: "+pname);
        }


        GregorianCalendar toDay = new GregorianCalendar();

        month = toDay.get(toDay.MONTH)+1;
        day = toDay.get(toDay.DAY_OF_MONTH);
        int dayofweek1= toDay.get(toDay.DAY_OF_WEEK);
        date.setText("▼"+month+"월 "+day+"일 강의 목록");

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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd", Locale.KOREA);
        Date currentTime = new Date();
        final String todayDate = simpleDateFormat.format(currentTime);

        sqlDB = myHelper.getWritableDatabase();
        Cursor cr, cr1;
        String sql = "select S_name from Subject where P_no =(select P_no from Professor where P_name ='"+pname+"') and S_code in (select S_code from C_info where C_day ='"+dayofweek2+"');";
        cr = sqlDB.rawQuery(sql, null);
        if(cr.getCount()>0){
        while(cr.moveToNext()){
            subjectname=cr.getString(0);
            subject.setText(subjectname);  //과목
        }
            String sql1 = "select T_time from Time where T_no in (select T_no from C_info where S_code  in (select S_code from Subject where S_name='"+subjectname+"')" +
                    "and C_Day='"+dayofweek2+"');";
            cr1 = sqlDB.rawQuery(sql1, null);
            while(cr1.moveToNext()){
                classtime+=cr1.getString(0)+"\n";
            }
            time.setText(classtime); //시간
        }else{
            subject.setText("오늘은 강의가 없습니다.");  //과목
            subject.setTextSize(20);
            subject.setTextColor(Color.RED);
            speak.setVisibility(View.INVISIBLE); wifidirect.setVisibility(View.INVISIBLE);
        }

      //  cr.close();
      //  sqlDB.close();




        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //출결시간 확인
                Cursor cr;
                String sql = "select substr(T_time,1,2), substr(T_time,4,2), substr(T_time,-5,2), substr(T_time,-1,2) from Time where T_no = (select min(T_no) from C_info where S_code  in (select S_code from Subject where S_name='"+subjectname+"'));";
                cr=sqlDB.rawQuery(sql,null);

                while(cr.moveToNext()) {
                    st1 =cr.getString(0);
                    st2 =cr.getString(1);
                    et1 =cr.getString(2);
                    et2 =cr.getString(3);
                }

                if((hour.equals(st1) && min.compareTo(st2) >0) || (hour.equals(et1) && min.compareTo(st2) <0)  ) {

                    Intent intent2 = new Intent(getApplicationContext(), Speak.class);
                    startActivity(intent2);
                    //현재의 시간과 맞을경우(수업시간내에 현재시간)

                }else {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(Activity2.this, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                    dlg.setTitle("※ 강의 시간이 아닙니다.");
                    dlg.setMessage("출결을 진행하시겠습니까?");
                    dlg.setNegativeButton("취소", null);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(), Speak.class);
                            intent.putExtra("subjectname", subjectname);
                            intent.putExtra("todayDate", todayDate);
                            startActivityForResult(intent, 1);
                        }
                    });
                    dlg.show();
                }//현재의 시간과 강의시간이 다를경우

            }
        });

        wifidirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //출결시간 확인
                Cursor cr;
                String sql = "select substr(T_time,1,2), substr(T_time,4,2), substr(T_time,-5,2), substr(T_time,-1,2) from Time where T_no = (select min(T_no) from C_info where S_code  in (select S_code from Subject where S_name='" + subjectname + "'));";
                cr = sqlDB.rawQuery(sql, null);

                while (cr.moveToNext()) {
                    st1 = cr.getString(0);
                    st2 = cr.getString(1);
                    et1 = cr.getString(2);
                    et2 = cr.getString(3);
                }

                if ((hour.equals(st1) && min.compareTo(st2) > 0) || (hour.equals(et1) && min.compareTo(st2) < 0)) {

                    Intent intent2 = new Intent(getApplicationContext(), WifiDirect.class);
                    startActivity(intent2);
                    //현재의 시간과 맞을경우(수업시간내에 현재시간)

                } else {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(Activity2.this, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                    dlg.setTitle("※ 강의 시간이 아닙니다.");
                    dlg.setMessage("출결을 진행하시겠습니까?");
                    dlg.setNegativeButton("취소", null);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(), WifiDirect.class);
                            intent.putExtra("subjectname", subjectname);
                            intent.putExtra("todayDate", todayDate);
                            startActivityForResult(intent, 1);
                        }
                    });
                    dlg.show();
                }


                WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                wifi.setWifiEnabled(true);
            }
        });



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
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("출결앱 (교수용)");
        actionBar.setSubtitle("wifi-direct/호명방식 출결");

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                menuItem.setChecked(false);

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

                }
                return true;

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

/*
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
       // getMenuInflater().inflate(R.menu.drawer, menu);

        if(key.getBoolean("loginkey", true)) {  //로그인함
            menu.findItem(R.id.login).setTitle("로그아웃");
        }else {
            menu.findItem(R.id.login).setTitle("로그인");
        }
        return super.onPrepareOptionsMenu(menu);
    }
*/
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
