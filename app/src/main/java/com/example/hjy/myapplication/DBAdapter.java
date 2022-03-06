package com.example.hjy.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class DBAdapter extends CursorAdapter { //superclass : CursorAdapter

    public DBAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) { //만들어준 xml을 객체화 시켜주기 위한 레이아웃 인플레이터 역할을 함

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.listlayout, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {  //실제 데이터를 넣어줌

        TextView txtName =(TextView) view.findViewById(R.id.txtName);
        TextView txtClass =(TextView) view.findViewById(R.id.txtClass);

        txtName.setText(cursor.getString(cursor.getColumnIndex("name")));
        txtClass.setText(cursor.getString(cursor.getColumnIndex("time")));
    }
}

