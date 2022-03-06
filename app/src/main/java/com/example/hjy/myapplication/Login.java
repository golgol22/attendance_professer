package com.example.hjy.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hjy.myapplication.DB.DBHelper;

/**
 * Created by hjy on 2018-04-04.
 */

public class Login extends AppCompatActivity {  //로그인

    public DrawerLayout mDrawerLayout;
    Button  btnLog;


    EditText editId, editPw;
    CheckBox ch;
    String id, pw;

    SharedPreferences prof, key;
    SharedPreferences.Editor editor, editorkey;

    DBHelper myHelper;
    SQLiteDatabase sqlDB;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        editId =(EditText)findViewById(R.id.editId);
        editPw =(EditText)findViewById(R.id.editPw);
        btnLog = (Button)findViewById(R.id.btnLog);
        ch=(CheckBox)findViewById(R.id.ch);

        myHelper = new DBHelper(this);


        key=getSharedPreferences("loginkey", 0);
        editorkey= key.edit();

        prof =  getSharedPreferences("login", 0);
        editor = prof.edit();

        if(prof.getBoolean("auto_save", false)==true) {  //불러올때
            editId.setText(prof.getString("id", ""));
            editPw.setText(prof.getString("pw", ""));
            ch.setChecked(true);
        }

        ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(ch.isChecked()==true){
                    String id= editId.getText().toString();
                    String pw =editPw.getText().toString();
                    editor.putString("id", id);
                    editor.putString("pw", pw);
                    editor.putBoolean("auto_save", true);
                    editor.commit();  //저장(안해주면 변경값 저장 안됨)
                }else{
                    editor.clear(); //모두 없애줄때
                    //editor.remove("id"); id만 없애주고 싶을떄
                    editor.commit();
                }
            }
        });

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id = editId.getText().toString();
                pw = editPw.getText().toString();

                if (id.trim().equals("")) {
                    Toast.makeText(getApplication(), "ID를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    editId.requestFocus();
                } else if (pw.trim().equals("")) {
                    Toast.makeText(getApplication(), "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    editPw.requestFocus();
                } else {

                    try {
                       sqlDB = myHelper.getWritableDatabase();

                        Cursor cursor;
                        String sql="select P_name, P_passwd from Professor where P_no = '"+id+"';";

                        cursor = sqlDB.rawQuery(sql, null);
                        if (cursor.getCount() > 0) {
                            while ((cursor.moveToNext())) {
                                 String name = cursor.getString(0);
                                 String passwd = cursor.getString(1);

                                if (passwd.equals(pw)) {
                                    Toast.makeText(getApplication(), name + "님 환영합니다.", Toast.LENGTH_SHORT).show();
                                    editorkey.putString("name", name);
                                    editorkey.putBoolean("loginkey", true);
                                    editorkey.commit();
                                    Intent i = new Intent(getApplicationContext(), Activity2.class);
                                    i.putExtra("name",name);
                                    startActivityForResult(i,0);
                                } else {
                                    Toast.makeText(getApplication(), "비밀번호가 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                                }

                            }
                            cursor.close();
                        } else {
                            Toast.makeText(getApplication(), "ID가 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                        sqlDB.close();

                    }catch(Exception e){
                       Toast.makeText(getApplication(), "DB가 없어", Toast.LENGTH_SHORT).show();
                    }
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
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("로그인");

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
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
                break;

            case R.id.menubar:
                mDrawerLayout.openDrawer(GravityCompat.END);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


         }





