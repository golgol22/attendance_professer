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
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hjy.myapplication.DB.DBHelper;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hjy on 2018-04-04.
 */

public class Current_state extends AppCompatActivity { //wifi-direct출석현황

    TextView state_sub, state;
    Button btnModify;

    public DrawerLayout mDrawerLayout;
    String subjectname, todayDate, state1="00 ", dayofweek2;
    SharedPreferences key;
    SharedPreferences.Editor editorkey;

    DBHelper myHelper;
    SQLiteDatabase sqlDB;
    Cursor cr;
    String [] sname;

    ActionBarDrawerToggle drawerToggle = null;
    //boolean toolbarNavigationListenerRegistered = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_state);

        key=getSharedPreferences("loginkey", 0);
        editorkey= key.edit();

        myHelper = new DBHelper(this);

        Intent in = getIntent(); // (WifiDirect에서)
        subjectname = in.getStringExtra("subjectname"); //출결을 확인하고 싶은 날짜
        todayDate = in.getStringExtra("todayDate"); //과목이름
        state1= in.getStringExtra("state1");


        state_sub = (TextView)findViewById(R.id.state_sub);
        state = (TextView)findViewById(R.id.State);
        btnModify = (Button) findViewById(R.id.btnModify);

        state_sub.setText(subjectname+"  "+todayDate);
        state.setText(state1);  // 0/4

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

        sqlDB = myHelper.getWritableDatabase(); //DB열기

        String sql1="select Name from Student where SNo in (select SNo from Attend where C_no = (select max(C_no) from C_info where S_code = " +
                "(select S_code from Subject where S_name='" + subjectname + "' and C_day='"+dayofweek2+"'))" +
                " and Date='" + todayDate + "' and State='미처리');";
        //결석자 명단
        cr = sqlDB.rawQuery(sql1, null);

        final int count = cr.getCount();
        sname= new String[count];

        for(int i=0; i<count; i++){
            cr.moveToNext();
            sname[i] = cr.getString(0);
        }

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
        actionBar.setTitle("출석 현황");

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawerToggle.syncState();


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


        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AttendList.class);
                intent.putExtra("classname", subjectname);
                intent.putExtra("date", todayDate);
                startActivity(intent);
            }
        });

        ExpandableListView listview = (ExpandableListView) findViewById(R.id.parent);

        List<Map<String, Object>> parentsList = new ArrayList<Map<String, Object>>();

        List<List<Map<String, Object>>> childrenList = new ArrayList<List<Map<String, Object>>>();


        Map<String, Object> parentData;
        parentData = new HashMap<String, Object>();
        parentData.put("parent", "결석자 명단");
        parentsList.add(parentData);

        Map<String, Object> childData;
        List<Map<String, Object>> childList;

        childList = new ArrayList<Map<String, Object>>();


        for(int i=0; i<count;i++) {
            childData = new HashMap<String, Object>();
            childData.put("child", sname[i]);
            childList.add(childData);
        }


        //childData = new HashMap<String, Object>();
       // childData.put("child", "황수빈");
        //childList.add(childData);

        childrenList.add(childList);

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(this, parentsList,
                android.R.layout.simple_expandable_list_item_1,
                new String[]{"parent"},
                new int[]{android.R.id.text1},
                childrenList,
                R.layout.child,
                new String[]{"child"},
                new int[]{R.id.child}
        );

        listview.setAdapter(adapter);
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




