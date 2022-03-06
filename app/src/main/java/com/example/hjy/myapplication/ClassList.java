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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hjy.myapplication.DB.DBHelper;

import java.util.ArrayList;

/**
 * Created by hjy on 2018-04-05.
 */

public class ClassList extends AppCompatActivity{  //교수가 가르치는 과목목록
    public DrawerLayout mDrawerLayout;
    ListView lv;
    ArrayList<String> item;

    SharedPreferences key;
    SharedPreferences.Editor editorkey;

    DBHelper myHelper;
    SQLiteDatabase sqlDB;
    String pname, classname;


    TextView pro;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classlist);

        pro = (TextView)findViewById(R.id.pro);
        lv = (ListView) findViewById(R.id.listview2);

        item = new ArrayList<String>();
        myHelper = new DBHelper(this);

        key=getSharedPreferences("loginkey", 0);
        editorkey= key.edit();

        if(key.getBoolean("loginkey", true)) {  //불러올때
            pname = key.getString("name", "");
            pro.setText(pname);
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
                //menuItem.CheckClear():
            }
        });



        ArrayAdapter adapter = (ArrayAdapter) new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, item);
        lv.setAdapter(adapter);

        sqlDB = myHelper.getWritableDatabase(); //DB열기
        Cursor cr;
        item.clear(); //조회를 하면 새로 arraylist에 들어가서 중복되기때문에 지워주고 조회
        String sql = "select S_name from Subject where P_no = (select P_no from Professor where P_name='"+pname+"');";
        cr = sqlDB.rawQuery(sql, null);

        while(cr.moveToNext()){
            classname=cr.getString(0);
            item.add(classname);
        }

        adapter.notifyDataSetChanged();

        cr.close();
        sqlDB.close();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){  //눌렀을때 화면이동
                    case 0:
                     Intent intent = new Intent(getApplicationContext(), Pro_subject.class);
                     intent.putExtra("classname", item.get(0));
                     startActivityForResult(intent, 0);
                     break;
                    case 1:
                        Intent intent1 = new Intent(getApplicationContext(), Pro_subject.class);
                        intent1.putExtra("classname", item.get(1));
                        startActivityForResult(intent1, 0);
                        break;
                }
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


