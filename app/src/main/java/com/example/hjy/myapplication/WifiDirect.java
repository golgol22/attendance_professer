package com.example.hjy.myapplication;

import android.app.Activity;
import android.content.ClipData;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.hjy.myapplication.DB.DBHelper;

/**
 * Created by hjy on 2018-04-04.
 */

public class WifiDirect extends AppCompatActivity { //wifi-direct출결 진행창

    public DrawerLayout mDrawerLayout;

    SharedPreferences key;
    SharedPreferences.Editor editorkey;

    ActionBarDrawerToggle drawerToggle = null;
    boolean toolbarNavigationListenerRegistered = false;
    String subjectname="", todayDate="", code="", stunum="0";
    String s;

    DBHelper myHelper;
    SQLiteDatabase sqlDB;

    Cursor cr, cr1;
    Button btnDir;
    TextView state;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wifidirect);

        key=getSharedPreferences("loginkey", 0);
        editorkey= key.edit();
        myHelper = new DBHelper(this);
        state = (TextView)findViewById(R.id.state);
        btnDir =(Button)findViewById(R.id.btnDir);

        Intent in = getIntent(); // 출결확인
        subjectname = in.getStringExtra("subjectname"); //출결을 확인하고 싶은 날짜
        todayDate = in.getStringExtra("todayDate"); //과목이름

        sqlDB = myHelper.getWritableDatabase();
        String sql = "select S_code from Subject where S_name='"+subjectname+"';";
        cr = sqlDB.rawQuery(sql, null);
        while(cr.moveToNext()){
            code = cr.getString(0);
        }
        String sql1="select count(*) from'"+code+"';";
        cr1 = sqlDB.rawQuery(sql1, null);
        while(cr1.moveToNext()){
            stunum= cr1.getString(0);
        }

        state.setText("0/"+stunum);

        btnDir = (Button) findViewById(R.id.btnDir);
        state = (TextView) findViewById(R.id.state);

        btnDir.setText("출석현황");
        btnDir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnDir.getText().toString().equals("끝내기")) {
                    Intent intent = new Intent(getApplicationContext(), Activity2.class);
                    startActivity(intent);
                }else {
                     s = state.getText().toString();
                    Intent intent = new Intent(getApplicationContext(), Current_state.class);
                    intent.putExtra("subjectname", subjectname);
                    intent.putExtra("todayDate", todayDate);
                    intent.putExtra("state1", s);
                    startActivityForResult(intent, 2);
                }
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
        actionBar.setTitle("출석 확인");
       // actionBar.setSubtitle("출석확인");
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

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
                //menuItem.CheckClear():
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