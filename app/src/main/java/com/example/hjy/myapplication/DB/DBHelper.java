package com.example.hjy.myapplication.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import static android.content.ContentValues.TAG;

/**
 * Created by hjy on 2018-05-12.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG="DBHelper";
    //생성자, 추상메소드 생성

    int month, day, year, dayofweek1;
    String dayofweek2=" ";


    public DBHelper(Context context) {
        super(context, "attend.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //테이블 생성 (1번만 실행)

        try {
            String sql = "create table if not exists Professor ( P_no text not null primary key,";
            sql += "P_name text,";
            sql += "P_passwd text);";
            db.execSQL(sql);

            String p1 ="insert into Professor(P_no, P_name, P_passwd) values( '201', '최영수', 'ysu201c');";
            String p2= "insert into Professor(P_no, P_name, P_passwd) values( '202', '김인태', 'ysu202k');";
            String p3= "insert into Professor(P_no, P_name, P_passwd) values( '203', '류재춘', 'ysu203r');";
            String p4= "insert into Professor(P_no, P_name, P_passwd) values( '204', '정선미', 'ysu204s');";
            String p5= "insert into Professor(P_no, P_name, P_passwd) values( '205', '이용관', 'ysu205b');";
            String p6= "insert into Professor(P_no, P_name, P_passwd) values( '206', '조정란', 'ysu206x');";
            db.execSQL(p1); db.execSQL(p2); db.execSQL(p3); db.execSQL(p4);  db.execSQL(p5); db.execSQL(p6);


            String sql5 = "CREATE TABLE if not exists Subject (" +
                    "S_code   TEXT NOT NULL," +
                    "S_name   TEXT," +
                    "P_no   TEXT," +
                    "FOREIGN KEY(P_no) REFERENCES Professor(P_no)," +
                    "PRIMARY KEY(S_code)" +
                    ");";
            db.execSQL(sql5);

            String s1 = "insert into Subject(S_code, S_name, P_no) values( 'P', '프로젝트', '201');";
            String s2 = "insert into Subject(S_code, S_name, P_no) values( 'C', '취업과 창업', '201');";
            String s3 = "insert into Subject(S_code, S_name, P_no) values( 'A', '안드로이드', '204');";
            String s4 = "insert into Subject(S_code, S_name, P_no) values( 'S', '서버관리', '203');";
            String s5 = "insert into Subject(S_code, S_name, P_no) values( 'E', '임베디드 응용', '203');";
            String s6 = "insert into Subject(S_code, S_name, P_no) values( 'F', '프레임워크', '202');";
            String s7 = "insert into Subject(S_code, S_name, P_no) values( 'B', '시스템 분석 및 설계', '205');";
            String s8 = "insert into Subject(S_code, S_name, P_no) values( 'X', '기업가정신과 창업', '206');";
            db.execSQL(s1); db.execSQL(s2); db.execSQL(s3); db.execSQL(s4);
            db.execSQL(s5); db.execSQL(s6); db.execSQL(s7); db.execSQL(s8);


            String sql10="CREATE TABLE if not exists Time (" +
                    "T_no   INTEGER NOT NULL," +
                    "T_time   TEXT," +
                    "PRIMARY KEY(T_no)" +
                    ");";
            db.execSQL(sql10);

            String sql11="insert into Time(T_no, T_time) values(1, '09:30 ~ 10:20');";
            String sql12="insert into Time(T_no, T_time) values(2, '10:30 ~ 11:20');";
            String sql13="insert into Time(T_no, T_time) values(3, '11:30 ~ 12:20');";
            String sql14="insert into Time(T_no, T_time) values(4, '12:30 ~ 13:20');";
            String sql15="insert into Time(T_no, T_time) values(5, '13:30 ~ 14:20');";
            String sql16="insert into Time(T_no, T_time) values(6, '14:30 ~ 15:20');";
            String sql17="insert into Time(T_no, T_time) values(7, '15:30 ~ 16:20');";
            String sql18="insert into Time(T_no, T_time) values(8, '16:30 ~ 17:20');";

            db.execSQL(sql11); db.execSQL(sql12); db.execSQL(sql13); db.execSQL(sql14);
            db.execSQL(sql15); db.execSQL(sql16); db.execSQL(sql17); db.execSQL(sql18);

            String sql19="CREATE TABLE if not exists C_info (" +
                    "C_no   INTEGER NOT NULL primary key autoincrement," +
                    "S_code   TEXT," +
                    "C_day   TEXT," +
                    "T_no   INTEGER," +
                    "FOREIGN KEY(T_no) REFERENCES Time(T_no)" +
                    ");";
            db.execSQL(sql19);

            String c1 = "insert into C_info( S_code, C_day, T_no) values('F', '월', 1);";
            String c2 = "insert into C_info( S_code, C_day, T_no) values('F', '월', 2);";
            String c3 = "insert into C_info( S_code, C_day, T_no) values('F', '월', 3);";
            String c4 = "insert into C_info( S_code, C_day, T_no) values('P', '월', 5);";
            String c5 = "insert into C_info( S_code, C_day, T_no) values('P', '월', 6);";
            String c6 = "insert into C_info( S_code, C_day, T_no) values('P', '월', 7);";
            String c7 = "insert into C_info( S_code, C_day, T_no) values('E', '화', 1);";
            String c8 = "insert into C_info( S_code, C_day, T_no) values('E', '화', 2);";
            String c9 = "insert into C_info( S_code, C_day, T_no) values('E', '화', 3);";
            String c10 = "insert into C_info( S_code, C_day, T_no) values('A', '화', 5);";
            String c11 = "insert into C_info( S_code, C_day, T_no) values('A', '화', 6);";
            String c12 = "insert into C_info( S_code, C_day, T_no) values('A', '수', 1);";
            String c13 = "insert into C_info( S_code, C_day, T_no) values('A', '수', 2);";
            String c14 = "insert into C_info( S_code, C_day, T_no) values('S', '수', 3);";
            String c15 = "insert into C_info( S_code, C_day, T_no) values('S', '수', 4);";
            String c16 = "insert into C_info( S_code, C_day, T_no) values('B', '목', 1);";
            String c17 = "insert into C_info( S_code, C_day, T_no) values('X', '목', 2);";
            String c18 = "insert into C_info( S_code, C_day, T_no) values('X', '목', 3);";
            String c19 = "insert into C_info( S_code, C_day, T_no) values('C', '목', 4);";
            String c20 = "insert into C_info( S_code, C_day, T_no) values('B', '금', 2);";
            String c21 = "insert into C_info( S_code, C_day, T_no) values('B', '금', 3);";
            String c22 = "insert into C_info( S_code, C_day, T_no) values('S', '금', 5);";
            String c23 = "insert into C_info( S_code, C_day, T_no) values('S', '금', 6);";
            db.execSQL(c1); db.execSQL(c2); db.execSQL(c3); db.execSQL(c4); db.execSQL(c5);
            db.execSQL(c6); db.execSQL(c7); db.execSQL(c8); db.execSQL(c9); db.execSQL(c10);
            db.execSQL(c11); db.execSQL(c12); db.execSQL(c13); db.execSQL(c14); db.execSQL(c15);
            db.execSQL(c16); db.execSQL(c17); db.execSQL(c18); db.execSQL(c19); db.execSQL(c20);
            db.execSQL(c21); db.execSQL(c22); db.execSQL(c23);


            String sql28="CREATE TABLE if not exists Student (" +
                    "SNo   TEXT NOT NULL," +
                    "Name   TEXT," +
                    "Tel   TEXT," +
                    "PRIMARY KEY(SNo)" +
                    ");";
            db.execSQL(sql28);

            String sql29= "insert into Student(SNo, Name, Tel) values( '2016100274', '김태호', '010-4633-7506');";
            String sql30 = "insert into Student(SNo, Name, Tel) values( '2016100304', '황수빈', '010-8920-5618');";
            String sql31 = "insert into Student(SNo, Name, Tel) values( '2016100306', '이진주', '010-7163-2861');";
            String sql32= "insert into Student(SNo, Name, Tel) values( '2016100318', '허정연', '010-5294-9601');";

            db.execSQL(sql29);  db.execSQL(sql30);  db.execSQL(sql31);  db.execSQL(sql32);


            String sql33= "CREATE TABLE if not exists Attend (" +
                    "A_no   INTEGER NOT NULL primary key autoincrement," +
                    "C_no   INTEGER NOT NULL," +
                    "Date   TEXT," +
                    "SNo   TEXT NOT NULL," +
                    "State   TEXT NOT NULL DEFAULT '미처리' CHECK(State in('미처리','출석','결석','지각'))," +
                    "FOREIGN KEY(C_no) REFERENCES C_info(C_no)," +
                    "FOREIGN KEY(SNo) REFERENCES Student(SNo)" +
                    ");";
            db.execSQL(sql33);

            //1주
            String a1="insert into Attend(C_no, Date, SNo, State) values(20, '03/02', '2016100304', '출석');";
            String a2="insert into Attend(C_no, Date, SNo, State) values(21, '03/02', '2016100304', '출석');";
            String a3="insert into Attend(C_no, Date, SNo, State) values(20, '03/02', '2016100306', '출석');";
            String a4="insert into Attend(C_no, Date, SNo, State) values(21, '03/02', '2016100306', '출석');";
            String a5="insert into Attend(C_no, Date, SNo, State) values(20, '03/02', '2016100318', '출석');";
            String a6="insert into Attend(C_no, Date, SNo, State) values(21, '03/02', '2016100318', '출석');"; //금 시스템분석및 설계

            String a7="insert into Attend(C_no, Date, SNo, State) values(22, '03/02', '2016100274', '출석');";
            String a8="insert into Attend(C_no, Date, SNo, State) values(23, '03/02', '2016100274', '출석');";
            String a9="insert into Attend(C_no, Date, SNo, State) values(22, '03/02', '2016100304', '출석');";
            String a10="insert into Attend(C_no, Date, SNo, State) values(23, '03/02', '2016100304', '출석');";
            String a11="insert into Attend(C_no, Date, SNo, State) values(22, '03/02', '2016100306', '출석');";
            String a12="insert into Attend(C_no, Date, SNo, State) values(23, '03/02', '2016100306', '출석');";
            String a13="insert into Attend(C_no, Date, SNo, State) values(22, '03/02', '2016100318', '출석');";
            String a14="insert into Attend(C_no, Date, SNo, State) values(23, '03/02', '2016100318', '출석');"; //금 서버관리

            String a15="insert into Attend(C_no, Date, SNo, State) values(4, '03/05', '2016100274', '출석');";
            String a16="insert into Attend(C_no, Date, SNo, State) values(5, '03/05', '2016100274', '출석');";
            String a17="insert into Attend(C_no, Date, SNo, State) values(6, '03/05', '2016100274', '출석');";
            String a18="insert into Attend(C_no, Date, SNo, State) values(4, '03/05', '2016100304', '출석');";
            String a19="insert into Attend(C_no, Date, SNo, State) values(5, '03/05', '2016100304', '출석');";
            String a20="insert into Attend(C_no, Date, SNo, State) values(6, '03/05', '2016100304', '출석');";
            String a21="insert into Attend(C_no, Date, SNo, State) values(4, '03/05', '2016100306', '출석');";
            String a22="insert into Attend(C_no, Date, SNo, State) values(5, '03/05', '2016100306', '출석');";
            String a23="insert into Attend(C_no, Date, SNo, State) values(6, '03/05', '2016100306', '출석');";
            String a24="insert into Attend(C_no, Date, SNo, State) values(4, '03/05', '2016100318', '출석');";
            String a25="insert into Attend(C_no, Date, SNo, State) values(5, '03/05', '2016100318', '출석');";
            String a26="insert into Attend(C_no, Date, SNo, State) values(6, '03/05', '2016100318', '출석');"; //월 프로젝트

            String a27="insert into Attend(C_no, Date, SNo, State) values(1, '03/05', '2016100274', '결석');";
            String a28="insert into Attend(C_no, Date, SNo, State) values(2, '03/05', '2016100274', '지각');";
            String a29="insert into Attend(C_no, Date, SNo, State) values(3, '03/05', '2016100274', '출석');";
            String a30="insert into Attend(C_no, Date, SNo, State) values(1, '03/05', '2016100304', '결석');";
            String a31="insert into Attend(C_no, Date, SNo, State) values(2, '03/05', '2016100304', '결석');";
            String a32="insert into Attend(C_no, Date, SNo, State) values(3, '03/05', '2016100304', '결석');";
            String a33="insert into Attend(C_no, Date, SNo, State) values(1, '03/05', '2016100306', '출석');";
            String a34="insert into Attend(C_no, Date, SNo, State) values(2, '03/05', '2016100306', '출석');";
            String a35="insert into Attend(C_no, Date, SNo, State) values(3, '03/05', '2016100306', '결석');";
            String a36="insert into Attend(C_no, Date, SNo, State) values(1, '03/05', '2016100318', '출석');";
            String a37="insert into Attend(C_no, Date, SNo, State) values(2, '03/05', '2016100318', '출석');";
            String a38="insert into Attend(C_no, Date, SNo, State) values(3, '03/05', '2016100318', '출석');"; //월 프레임워크

            String a39="insert into Attend(C_no, Date, SNo, State) values(7, '03/06', '2016100304', '출석');";
            String a40="insert into Attend(C_no, Date, SNo, State) values(8, '03/06', '2016100304', '출석');";
            String a41="insert into Attend(C_no, Date, SNo, State) values(9, '03/06', '2016100304', '출석');";
            String a42="insert into Attend(C_no, Date, SNo, State) values(7, '03/06', '2016100306', '출석');";
            String a43="insert into Attend(C_no, Date, SNo, State) values(8, '03/06', '2016100306', '출석');";
            String a44="insert into Attend(C_no, Date, SNo, State) values(9, '03/06', '2016100306', '출석');";
            String a45="insert into Attend(C_no, Date, SNo, State) values(7, '03/06', '2016100318', '출석');";
            String a46="insert into Attend(C_no, Date, SNo, State) values(8, '03/06', '2016100318', '출석');";
            String a47="insert into Attend(C_no, Date, SNo, State) values(9, '03/06', '2016100318', '출석');"; //화 임베디드

            String a48="insert into Attend(C_no, Date, SNo, State) values(10, '03/06', '2016100274', '출석');";
            String a49="insert into Attend(C_no, Date, SNo, State) values(11, '03/06', '2016100274', '출석');";
            String a50="insert into Attend(C_no, Date, SNo, State) values(10, '03/06', '2016100304', '출석');";
            String a51="insert into Attend(C_no, Date, SNo, State) values(11, '03/06', '2016100304', '출석');";
            String a52="insert into Attend(C_no, Date, SNo, State) values(10, '03/06', '2016100306', '출석');";
            String a53="insert into Attend(C_no, Date, SNo, State) values(11, '03/06', '2016100306', '출석');";
            String a54="insert into Attend(C_no, Date, SNo, State) values(10, '03/06', '2016100318', '출석');";
            String a55="insert into Attend(C_no, Date, SNo, State) values(11, '03/06', '2016100318', '출석');"; //화 안드로이드

            String a56="insert into Attend(C_no, Date, SNo, State) values(12, '03/07', '2016100274', '출석');";
            String a57="insert into Attend(C_no, Date, SNo, State) values(13, '03/07', '2016100274', '출석');";
            String a58="insert into Attend(C_no, Date, SNo, State) values(12, '03/07', '2016100304', '출석');";
            String a59="insert into Attend(C_no, Date, SNo, State) values(13, '03/07', '2016100304', '출석');";
            String a60="insert into Attend(C_no, Date, SNo, State) values(12, '03/07', '2016100306', '출석');";
            String a61="insert into Attend(C_no, Date, SNo, State) values(13, '03/07', '2016100306', '출석');";
            String a62="insert into Attend(C_no, Date, SNo, State) values(12, '03/07', '2016100318', '출석');";
            String a63="insert into Attend(C_no, Date, SNo, State) values(13, '03/07', '2016100318', '출석');"; //수 안드로이드

            String a64="insert into Attend(C_no, Date, SNo, State) values(14, '03/07', '2016100274', '출석');";
            String a65="insert into Attend(C_no, Date, SNo, State) values(15, '03/07', '2016100274', '출석');";
            String a66="insert into Attend(C_no, Date, SNo, State) values(14, '03/07', '2016100304', '출석');";
            String a67="insert into Attend(C_no, Date, SNo, State) values(15, '03/07', '2016100304', '출석');";
            String a68="insert into Attend(C_no, Date, SNo, State) values(14, '03/07', '2016100306', '출석');";
            String a69="insert into Attend(C_no, Date, SNo, State) values(15, '03/07', '2016100306', '출석');";
            String a70="insert into Attend(C_no, Date, SNo, State) values(14, '03/07', '2016100318', '출석');";
            String a71="insert into Attend(C_no, Date, SNo, State) values(15, '03/07', '2016100318', '출석');"; //수 서버관리

            String a72="insert into Attend(C_no, Date, SNo, State) values(16, '03/08', '2016100304', '출석');";
            String a73="insert into Attend(C_no, Date, SNo, State) values(16, '03/08', '2016100306', '출석');";
            String a74="insert into Attend(C_no, Date, SNo, State) values(16, '03/08', '2016100318', '출석');"; //목 시스템 분석 및 설계

            String a75="insert into Attend(C_no, Date, SNo, State) values(17, '03/08', '2016100274', '출석');";
            String a76="insert into Attend(C_no, Date, SNo, State) values(18, '03/08', '2016100274', '출석');"; //목 기업가정신과 창업

            String a77="insert into Attend(C_no, Date, SNo, State) values(19, '03/08', '2016100274', '출석');";
            String a78="insert into Attend(C_no, Date, SNo, State) values(19, '03/08', '2016100304', '출석');";
            String a79="insert into Attend(C_no, Date, SNo, State) values(19, '03/08', '2016100306', '출석');";
            String a80="insert into Attend(C_no, Date, SNo, State) values(19, '03/08', '2016100318', '출석');"; //목 취업과 창업

            db.execSQL(a1); db.execSQL(a2); db.execSQL(a3); db.execSQL(a4); db.execSQL(a5);
            db.execSQL(a6); db.execSQL(a7); db.execSQL(a8); db.execSQL(a9); db.execSQL(a10);
            db.execSQL(a11); db.execSQL(a12); db.execSQL(a13); db.execSQL(a14); db.execSQL(a15);
            db.execSQL(a16); db.execSQL(a17); db.execSQL(a18); db.execSQL(a19); db.execSQL(a20);
            db.execSQL(a21); db.execSQL(a22); db.execSQL(a23); db.execSQL(a24); db.execSQL(a25);

            db.execSQL(a26); db.execSQL(a27); db.execSQL(a28); db.execSQL(a29); db.execSQL(a30);
            db.execSQL(a31); db.execSQL(a32); db.execSQL(a33); db.execSQL(a34); db.execSQL(a35);
            db.execSQL(a36); db.execSQL(a37); db.execSQL(a38); db.execSQL(a39); db.execSQL(a40);
            db.execSQL(a41); db.execSQL(a42); db.execSQL(a43); db.execSQL(a44); db.execSQL(a45);
            db.execSQL(a46); db.execSQL(a47); db.execSQL(a48); db.execSQL(a49); db.execSQL(a50);

            db.execSQL(a51); db.execSQL(a52); db.execSQL(a53); db.execSQL(a54); db.execSQL(a55);
            db.execSQL(a56); db.execSQL(a57); db.execSQL(a58); db.execSQL(a59); db.execSQL(a60);
            db.execSQL(a61); db.execSQL(a62); db.execSQL(a63); db.execSQL(a64); db.execSQL(a65);
            db.execSQL(a66); db.execSQL(a67); db.execSQL(a68); db.execSQL(a69); db.execSQL(a70);
            db.execSQL(a71); db.execSQL(a72); db.execSQL(a73); db.execSQL(a74); db.execSQL(a75);
            db.execSQL(a76); db.execSQL(a77); db.execSQL(a78); db.execSQL(a79); db.execSQL(a80);


            String [] z = new String[80];  //2주

            z[0]="insert into Attend(C_no, Date, SNo, State) values(20, '03/09', '2016100304', '출석');";
            z[1]="insert into Attend(C_no, Date, SNo, State) values(21, '03/09', '2016100304', '출석');";
            z[2]="insert into Attend(C_no, Date, SNo, State) values(20, '03/09', '2016100306', '출석');";
            z[3]="insert into Attend(C_no, Date, SNo, State) values(21, '03/09', '2016100306', '출석');";
            z[4]="insert into Attend(C_no, Date, SNo, State) values(20, '03/09', '2016100318', '출석');";
            z[5]="insert into Attend(C_no, Date, SNo, State) values(21, '03/09', '2016100318', '출석');"; //금 시스템분석및 설계

            z[6]="insert into Attend(C_no, Date, SNo, State) values(22, '03/09', '2016100274', '출석');";
            z[7]="insert into Attend(C_no, Date, SNo, State) values(23, '03/09', '2016100274', '출석');";
            z[8]="insert into Attend(C_no, Date, SNo, State) values(22, '03/09', '2016100304', '출석');";
            z[9]="insert into Attend(C_no, Date, SNo, State) values(23, '03/09', '2016100304', '출석');";
            z[10]="insert into Attend(C_no, Date, SNo, State) values(22, '03/09', '2016100306', '출석');";
            z[11]="insert into Attend(C_no, Date, SNo, State) values(23, '03/09', '2016100306', '출석');";
            z[12]="insert into Attend(C_no, Date, SNo, State) values(22, '03/09', '2016100318', '출석');";
            z[13]="insert into Attend(C_no, Date, SNo, State) values(23, '03/09', '2016100318', '출석');"; //금 서버관리

            z[14]="insert into Attend(C_no, Date, SNo, State) values(4, '03/12', '2016100274', '출석');";
            z[15]="insert into Attend(C_no, Date, SNo, State) values(5, '03/12', '2016100274', '출석');";
            z[16]="insert into Attend(C_no, Date, SNo, State) values(6, '03/12', '2016100274', '출석');";
            z[17]="insert into Attend(C_no, Date, SNo, State) values(4, '03/12', '2016100304', '출석');";
            z[18]="insert into Attend(C_no, Date, SNo, State) values(5, '03/12', '2016100304', '출석');";
            z[19]="insert into Attend(C_no, Date, SNo, State) values(6, '03/12', '2016100304', '출석');";
            z[20]="insert into Attend(C_no, Date, SNo, State) values(4, '03/12', '2016100306', '출석');";
            z[21]="insert into Attend(C_no, Date, SNo, State) values(5, '03/12', '2016100306', '출석');";
            z[22]="insert into Attend(C_no, Date, SNo, State) values(6, '03/12', '2016100306', '출석');";
            z[23]="insert into Attend(C_no, Date, SNo, State) values(4, '03/12', '2016100318', '출석');";
            z[24]="insert into Attend(C_no, Date, SNo, State) values(5, '03/12', '2016100318', '출석');";
            z[25]="insert into Attend(C_no, Date, SNo, State) values(6, '03/12', '2016100318', '출석');"; //월 프로젝트

            z[26]="insert into Attend(C_no, Date, SNo, State) values(1, '03/12', '2016100274', '출석');";
            z[27]="insert into Attend(C_no, Date, SNo, State) values(2, '03/12', '2016100274', '출석');";
            z[28]="insert into Attend(C_no, Date, SNo, State) values(3, '03/12', '2016100274', '출석');";
            z[29]="insert into Attend(C_no, Date, SNo, State) values(1, '03/12', '2016100304', '출석');";
            z[30]="insert into Attend(C_no, Date, SNo, State) values(2, '03/12', '2016100304', '출석');";
            z[31]="insert into Attend(C_no, Date, SNo, State) values(3, '03/12', '2016100304', '출석');";
            z[32]="insert into Attend(C_no, Date, SNo, State) values(1, '03/12', '2016100306', '출석');";
            z[33]="insert into Attend(C_no, Date, SNo, State) values(2, '03/12', '2016100306', '출석');";
            z[34]="insert into Attend(C_no, Date, SNo, State) values(3, '03/12', '2016100306', '출석');";
            z[35]="insert into Attend(C_no, Date, SNo, State) values(1, '03/12', '2016100318', '출석');";
            z[36]="insert into Attend(C_no, Date, SNo, State) values(2, '03/12', '2016100318', '출석');";
            z[37]="insert into Attend(C_no, Date, SNo, State) values(3, '03/12', '2016100318', '출석');"; //월 프레임워크

            z[38]="insert into Attend(C_no, Date, SNo, State) values(7, '03/13', '2016100304', '출석');";
            z[39]="insert into Attend(C_no, Date, SNo, State) values(8, '03/13', '2016100304', '출석');";
            z[40]="insert into Attend(C_no, Date, SNo, State) values(9, '03/13', '2016100304', '출석');";
            z[41]="insert into Attend(C_no, Date, SNo, State) values(7, '03/13', '2016100306', '출석');";
            z[42]="insert into Attend(C_no, Date, SNo, State) values(8, '03/13', '2016100306', '출석');";
            z[43]="insert into Attend(C_no, Date, SNo, State) values(9, '03/13', '2016100306', '출석');";
            z[44]="insert into Attend(C_no, Date, SNo, State) values(7, '03/13', '2016100318', '출석');";
            z[45]="insert into Attend(C_no, Date, SNo, State) values(8, '03/13', '2016100318', '출석');";
            z[46]="insert into Attend(C_no, Date, SNo, State) values(9, '03/13', '2016100318', '출석');"; //화 임베디드

            z[47]="insert into Attend(C_no, Date, SNo, State) values(10, '03/13', '2016100274', '출석');";
            z[48]="insert into Attend(C_no, Date, SNo, State) values(11, '03/13', '2016100274', '출석');";
            z[49]="insert into Attend(C_no, Date, SNo, State) values(10, '03/13', '2016100304', '출석');";
            z[50]="insert into Attend(C_no, Date, SNo, State) values(11, '03/13', '2016100304', '출석');";
            z[51]="insert into Attend(C_no, Date, SNo, State) values(10, '03/13', '2016100306', '출석');";
            z[52]="insert into Attend(C_no, Date, SNo, State) values(11, '03/13', '2016100306', '출석');";
            z[53]="insert into Attend(C_no, Date, SNo, State) values(10, '03/13', '2016100318', '출석');";
            z[54]="insert into Attend(C_no, Date, SNo, State) values(11, '03/13', '2016100318', '출석');"; //화 안드로이드

            z[55]="insert into Attend(C_no, Date, SNo, State) values(12, '03/14', '2016100274', '출석');";
            z[56]="insert into Attend(C_no, Date, SNo, State) values(13, '03/14', '2016100274', '출석');";
            z[57]="insert into Attend(C_no, Date, SNo, State) values(12, '03/14', '2016100304', '출석');";
            z[58]="insert into Attend(C_no, Date, SNo, State) values(13, '03/14', '2016100304', '출석');";
            z[59]="insert into Attend(C_no, Date, SNo, State) values(12, '03/14', '2016100306', '출석');";
            z[60]="insert into Attend(C_no, Date, SNo, State) values(13, '03/14', '2016100306', '출석');";
            z[61]="insert into Attend(C_no, Date, SNo, State) values(12, '03/14', '2016100318', '출석');";
            z[62]="insert into Attend(C_no, Date, SNo, State) values(13, '03/14', '2016100318', '출석');"; //수 안드로이드

            z[63]="insert into Attend(C_no, Date, SNo, State) values(14, '03/14', '2016100274', '출석');";
            z[64]="insert into Attend(C_no, Date, SNo, State) values(15, '03/14', '2016100274', '출석');";
            z[65]="insert into Attend(C_no, Date, SNo, State) values(14, '03/14', '2016100304', '출석');";
            z[66]="insert into Attend(C_no, Date, SNo, State) values(15, '03/14', '2016100304', '출석');";
            z[67]="insert into Attend(C_no, Date, SNo, State) values(14, '03/14', '2016100306', '출석');";
            z[68]="insert into Attend(C_no, Date, SNo, State) values(15, '03/14', '2016100306', '출석');";
            z[69]="insert into Attend(C_no, Date, SNo, State) values(14, '03/14', '2016100318', '출석');";
            z[70]="insert into Attend(C_no, Date, SNo, State) values(15, '03/14', '2016100318', '출석');"; //수 서버관리

            z[71]="insert into Attend(C_no, Date, SNo, State) values(16, '03/15', '2016100304', '출석');";
            z[72]="insert into Attend(C_no, Date, SNo, State) values(16, '03/15', '2016100306', '출석');";
            z[73]="insert into Attend(C_no, Date, SNo, State) values(16, '03/15', '2016100318', '출석');"; //목 시스템 분석 및 설계

            z[74]="insert into Attend(C_no, Date, SNo, State) values(17, '03/15', '2016100274', '출석');";
            z[75]="insert into Attend(C_no, Date, SNo, State) values(18, '03/15', '2016100274', '출석');"; //목 기업가정신과 창업

            z[76]="insert into Attend(C_no, Date, SNo, State) values(19, '03/15', '2016100274', '출석');";
            z[77]="insert into Attend(C_no, Date, SNo, State) values(19, '03/15', '2016100304', '출석');";
            z[78]="insert into Attend(C_no, Date, SNo, State) values(19, '03/15', '2016100306', '출석');";
            z[79]="insert into Attend(C_no, Date, SNo, State) values(19, '03/15', '2016100318', '출석');"; //목 취업과 창업
            for(int i=0; i<80; i++){ db.execSQL(z[i]); }


            String [] y = new String[80];  //3주
            y[0]="insert into Attend(C_no, Date, SNo, State) values(20, '03/16', '2016100304', '출석');";
            y[1]="insert into Attend(C_no, Date, SNo, State) values(21, '03/16', '2016100304', '출석');";
            y[2]="insert into Attend(C_no, Date, SNo, State) values(20, '03/16', '2016100306', '출석');";
            y[3]="insert into Attend(C_no, Date, SNo, State) values(21, '03/16', '2016100306', '출석');";
            y[4]="insert into Attend(C_no, Date, SNo, State) values(20, '03/16', '2016100318', '출석');";
            y[5]="insert into Attend(C_no, Date, SNo, State) values(21, '03/16', '2016100318', '출석');"; //금 시스템분석및 설계

            y[6]="insert into Attend(C_no, Date, SNo, State) values(22, '03/16', '2016100274', '출석');";
            y[7]="insert into Attend(C_no, Date, SNo, State) values(23, '03/16', '2016100274', '출석');";
            y[8]="insert into Attend(C_no, Date, SNo, State) values(22, '03/16', '2016100304', '출석');";
            y[9]="insert into Attend(C_no, Date, SNo, State) values(23, '03/16', '2016100304', '출석');";
            y[10]="insert into Attend(C_no, Date, SNo, State) values(22, '03/16', '2016100306', '출석');";
            y[11]="insert into Attend(C_no, Date, SNo, State) values(23, '03/16', '2016100306', '출석');";
            y[12]="insert into Attend(C_no, Date, SNo, State) values(22, '03/16', '2016100318', '출석');";
            y[13]="insert into Attend(C_no, Date, SNo, State) values(23, '03/16', '2016100318', '출석');"; //금 서버관리

            y[14]="insert into Attend(C_no, Date, SNo, State) values(4, '03/19', '2016100274', '출석');";
            y[15]="insert into Attend(C_no, Date, SNo, State) values(5, '03/19', '2016100274', '출석');";
            y[16]="insert into Attend(C_no, Date, SNo, State) values(6, '03/19', '2016100274', '출석');";
            y[17]="insert into Attend(C_no, Date, SNo, State) values(4, '03/19', '2016100304', '출석');";
            y[18]="insert into Attend(C_no, Date, SNo, State) values(5, '03/19', '2016100304', '출석');";
            y[19]="insert into Attend(C_no, Date, SNo, State) values(6, '03/19', '2016100304', '출석');";
            y[20]="insert into Attend(C_no, Date, SNo, State) values(4, '03/19', '2016100306', '출석');";
            y[21]="insert into Attend(C_no, Date, SNo, State) values(5, '03/19', '2016100306', '출석');";
            y[22]="insert into Attend(C_no, Date, SNo, State) values(6, '03/19', '2016100306', '출석');";
            y[23]="insert into Attend(C_no, Date, SNo, State) values(4, '03/19', '2016100318', '출석');";
            y[24]="insert into Attend(C_no, Date, SNo, State) values(5, '03/19', '2016100318', '출석');";
            y[25]="insert into Attend(C_no, Date, SNo, State) values(6, '03/19', '2016100318', '출석');"; //월 프로젝트

            y[26]="insert into Attend(C_no, Date, SNo, State) values(1, '03/19', '2016100274', '출석');";
            y[27]="insert into Attend(C_no, Date, SNo, State) values(2, '03/19', '2016100274', '출석');";
            y[28]="insert into Attend(C_no, Date, SNo, State) values(3, '03/19', '2016100274', '출석');";
            y[29]="insert into Attend(C_no, Date, SNo, State) values(1, '03/19', '2016100304', '출석');";
            y[30]="insert into Attend(C_no, Date, SNo, State) values(2, '03/19', '2016100304', '출석');";
            y[31]="insert into Attend(C_no, Date, SNo, State) values(3, '03/19', '2016100304', '출석');";
            y[32]="insert into Attend(C_no, Date, SNo, State) values(1, '03/19', '2016100306', '출석');";
            y[33]="insert into Attend(C_no, Date, SNo, State) values(2, '03/19', '2016100306', '출석');";
            y[34]="insert into Attend(C_no, Date, SNo, State) values(3, '03/19', '2016100306', '출석');";
            y[35]="insert into Attend(C_no, Date, SNo, State) values(1, '03/19', '2016100318', '출석');";
            y[36]="insert into Attend(C_no, Date, SNo, State) values(2, '03/19', '2016100318', '출석');";
            y[37]="insert into Attend(C_no, Date, SNo, State) values(3, '03/19', '2016100318', '출석');"; //월 프레임워크

            y[38]="insert into Attend(C_no, Date, SNo, State) values(7, '03/20', '2016100304', '출석');";
            y[39]="insert into Attend(C_no, Date, SNo, State) values(8, '03/20', '2016100304', '출석');";
            y[40]="insert into Attend(C_no, Date, SNo, State) values(9, '03/20', '2016100304', '출석');";
            y[41]="insert into Attend(C_no, Date, SNo, State) values(7, '03/20', '2016100306', '출석');";
            y[42]="insert into Attend(C_no, Date, SNo, State) values(8, '03/20', '2016100306', '출석');";
            y[43]="insert into Attend(C_no, Date, SNo, State) values(9, '03/20', '2016100306', '출석');";
            y[44]="insert into Attend(C_no, Date, SNo, State) values(7, '03/20', '2016100318', '출석');";
            y[45]="insert into Attend(C_no, Date, SNo, State) values(8, '03/20', '2016100318', '출석');";
            y[46]="insert into Attend(C_no, Date, SNo, State) values(9, '03/20', '2016100318', '출석');"; //화 임베디드

            y[47]="insert into Attend(C_no, Date, SNo, State) values(10, '03/20', '2016100274', '출석');";
            y[48]="insert into Attend(C_no, Date, SNo, State) values(11, '03/20', '2016100274', '출석');";
            y[49]="insert into Attend(C_no, Date, SNo, State) values(10, '03/20', '2016100304', '출석');";
            y[50]="insert into Attend(C_no, Date, SNo, State) values(11, '03/20', '2016100304', '출석');";
            y[51]="insert into Attend(C_no, Date, SNo, State) values(10, '03/20', '2016100306', '출석');";
            y[52]="insert into Attend(C_no, Date, SNo, State) values(11, '03/20', '2016100306', '출석');";
            y[53]="insert into Attend(C_no, Date, SNo, State) values(10, '03/20', '2016100318', '출석');";
            y[54]="insert into Attend(C_no, Date, SNo, State) values(11, '03/20', '2016100318', '출석');"; //화 안드로이드

            y[55]="insert into Attend(C_no, Date, SNo, State) values(12, '03/21', '2016100274', '출석');";
            y[56]="insert into Attend(C_no, Date, SNo, State) values(13, '03/21', '2016100274', '출석');";
            y[57]="insert into Attend(C_no, Date, SNo, State) values(12, '03/21', '2016100304', '출석');";
            y[58]="insert into Attend(C_no, Date, SNo, State) values(13, '03/21', '2016100304', '출석');";
            y[59]="insert into Attend(C_no, Date, SNo, State) values(12, '03/21', '2016100306', '출석');";
            y[60]="insert into Attend(C_no, Date, SNo, State) values(13, '03/21', '2016100306', '출석');";
            y[61]="insert into Attend(C_no, Date, SNo, State) values(12, '03/21', '2016100318', '출석');";
            y[62]="insert into Attend(C_no, Date, SNo, State) values(13, '03/21', '2016100318', '출석');"; //수 안드로이드

            y[63]="insert into Attend(C_no, Date, SNo, State) values(14, '03/21', '2016100274', '출석');";
            y[64]="insert into Attend(C_no, Date, SNo, State) values(15, '03/21', '2016100274', '출석');";
            y[65]="insert into Attend(C_no, Date, SNo, State) values(14, '03/21', '2016100304', '출석');";
            y[66]="insert into Attend(C_no, Date, SNo, State) values(15, '03/21', '2016100304', '출석');";
            y[67]="insert into Attend(C_no, Date, SNo, State) values(14, '03/21', '2016100306', '출석');";
            y[68]="insert into Attend(C_no, Date, SNo, State) values(15, '03/21', '2016100306', '출석');";
            y[69]="insert into Attend(C_no, Date, SNo, State) values(14, '03/21', '2016100318', '출석');";
            y[70]="insert into Attend(C_no, Date, SNo, State) values(15, '03/21', '2016100318', '출석');"; //수 서버관리

            y[71]="insert into Attend(C_no, Date, SNo, State) values(16, '03/22', '2016100304', '출석');";
            y[72]="insert into Attend(C_no, Date, SNo, State) values(16, '03/22', '2016100306', '출석');";
            y[73]="insert into Attend(C_no, Date, SNo, State) values(16, '03/22', '2016100318', '출석');"; //목 시스템 분석 및 설계

            y[74]="insert into Attend(C_no, Date, SNo, State) values(17, '03/22', '2016100274', '출석');";
            y[75]="insert into Attend(C_no, Date, SNo, State) values(18, '03/22', '2016100274', '출석');"; //목 기업가정신과 창업

            y[76]="insert into Attend(C_no, Date, SNo, State) values(19, '03/22', '2016100274', '출석');";
            y[77]="insert into Attend(C_no, Date, SNo, State) values(19, '03/22', '2016100304', '출석');";
            y[78]="insert into Attend(C_no, Date, SNo, State) values(19, '03/22', '2016100306', '출석');";
            y[79]="insert into Attend(C_no, Date, SNo, State) values(19, '03/22', '2016100318', '출석');"; //목 취업과 창업

            for(int i=0; i<80; i++){ db.execSQL(y[i]); }




            String [] x = new String[80];  //4주
            x[0]="insert into Attend(C_no, Date, SNo, State) values(20, '03/23', '2016100304', '출석');";
            x[1]="insert into Attend(C_no, Date, SNo, State) values(21, '03/23', '2016100304', '출석');";
            x[2]="insert into Attend(C_no, Date, SNo, State) values(20, '03/23', '2016100306', '출석');";
            x[3]="insert into Attend(C_no, Date, SNo, State) values(21, '03/23', '2016100306', '출석');";
            x[4]="insert into Attend(C_no, Date, SNo, State) values(20, '03/23', '2016100318', '출석');";
            x[5]="insert into Attend(C_no, Date, SNo, State) values(21, '03/23', '2016100318', '출석');"; //금 시스템분석및 설계

            x[6]="insert into Attend(C_no, Date, SNo, State) values(22, '03/23', '2016100274', '출석');";
            x[7]="insert into Attend(C_no, Date, SNo, State) values(23, '03/23', '2016100274', '출석');";
            x[8]="insert into Attend(C_no, Date, SNo, State) values(22, '03/23', '2016100304', '출석');";
            x[9]="insert into Attend(C_no, Date, SNo, State) values(23, '03/23', '2016100304', '출석');";
            x[10]="insert into Attend(C_no, Date, SNo, State) values(22, '03/23', '2016100306', '출석');";
            x[11]="insert into Attend(C_no, Date, SNo, State) values(23, '03/23', '2016100306', '출석');";
            x[12]="insert into Attend(C_no, Date, SNo, State) values(22, '03/23', '2016100318', '출석');";
            x[13]="insert into Attend(C_no, Date, SNo, State) values(23, '03/23', '2016100318', '출석');"; //금 서버관리

            x[14]="insert into Attend(C_no, Date, SNo, State) values(4, '03/26', '2016100274', '출석');";
            x[15]="insert into Attend(C_no, Date, SNo, State) values(5, '03/26', '2016100274', '출석');";
            x[16]="insert into Attend(C_no, Date, SNo, State) values(6, '03/26', '2016100274', '출석');";
            x[17]="insert into Attend(C_no, Date, SNo, State) values(4, '03/26', '2016100304', '출석');";
            x[18]="insert into Attend(C_no, Date, SNo, State) values(5, '03/26', '2016100304', '출석');";
            x[19]="insert into Attend(C_no, Date, SNo, State) values(6, '03/26', '2016100304', '출석');";
            x[20]="insert into Attend(C_no, Date, SNo, State) values(4, '03/26', '2016100306', '출석');";
            x[21]="insert into Attend(C_no, Date, SNo, State) values(5, '03/26', '2016100306', '출석');";
            x[22]="insert into Attend(C_no, Date, SNo, State) values(6, '03/26', '2016100306', '출석');";
            x[23]="insert into Attend(C_no, Date, SNo, State) values(4, '03/26', '2016100318', '출석');";
            x[24]="insert into Attend(C_no, Date, SNo, State) values(5, '03/26', '2016100318', '출석');";
            x[25]="insert into Attend(C_no, Date, SNo, State) values(6, '03/26', '2016100318', '출석');"; //월 프로젝트

            x[26]="insert into Attend(C_no, Date, SNo, State) values(1, '03/26', '2016100274', '출석');";
            x[27]="insert into Attend(C_no, Date, SNo, State) values(2, '03/26', '2016100274', '출석');";
            x[28]="insert into Attend(C_no, Date, SNo, State) values(3, '03/26', '2016100274', '출석');";
            x[29]="insert into Attend(C_no, Date, SNo, State) values(1, '03/26', '2016100304', '출석');";
            x[30]="insert into Attend(C_no, Date, SNo, State) values(2, '03/26', '2016100304', '출석');";
            x[31]="insert into Attend(C_no, Date, SNo, State) values(3, '03/26', '2016100304', '출석');";
            x[32]="insert into Attend(C_no, Date, SNo, State) values(1, '03/26', '2016100306', '출석');";
            x[33]="insert into Attend(C_no, Date, SNo, State) values(2, '03/26', '2016100306', '출석');";
            x[34]="insert into Attend(C_no, Date, SNo, State) values(3, '03/26', '2016100306', '출석');";
            x[35]="insert into Attend(C_no, Date, SNo, State) values(1, '03/26', '2016100318', '출석');";
            x[36]="insert into Attend(C_no, Date, SNo, State) values(2, '03/26', '2016100318', '출석');";
            x[37]="insert into Attend(C_no, Date, SNo, State) values(3, '03/26', '2016100318', '출석');"; //월 프레임워크

            x[38]="insert into Attend(C_no, Date, SNo, State) values(7, '03/27', '2016100304', '출석');";
            x[39]="insert into Attend(C_no, Date, SNo, State) values(8, '03/27', '2016100304', '출석');";
            x[40]="insert into Attend(C_no, Date, SNo, State) values(9, '03/27', '2016100304', '출석');";
            x[41]="insert into Attend(C_no, Date, SNo, State) values(7, '03/27', '2016100306', '출석');";
            x[42]="insert into Attend(C_no, Date, SNo, State) values(8, '03/27', '2016100306', '출석');";
            x[43]="insert into Attend(C_no, Date, SNo, State) values(9, '03/27', '2016100306', '출석');";
            x[44]="insert into Attend(C_no, Date, SNo, State) values(7, '03/27', '2016100318', '출석');";
            x[45]="insert into Attend(C_no, Date, SNo, State) values(8, '03/27', '2016100318', '출석');";
            x[46]="insert into Attend(C_no, Date, SNo, State) values(9, '03/27', '2016100318', '출석');"; //화 임베디드

            x[47]="insert into Attend(C_no, Date, SNo, State) values(10, '03/27', '2016100274', '출석');";
            x[48]="insert into Attend(C_no, Date, SNo, State) values(11, '03/27', '2016100274', '출석');";
            x[49]="insert into Attend(C_no, Date, SNo, State) values(10, '03/27', '2016100304', '출석');";
            x[50]="insert into Attend(C_no, Date, SNo, State) values(11, '03/27', '2016100304', '출석');";
            x[51]="insert into Attend(C_no, Date, SNo, State) values(10, '03/27', '2016100306', '출석');";
            x[52]="insert into Attend(C_no, Date, SNo, State) values(11, '03/27', '2016100306', '출석');";
            x[53]="insert into Attend(C_no, Date, SNo, State) values(10, '03/27', '2016100318', '출석');";
            x[54]="insert into Attend(C_no, Date, SNo, State) values(11, '03/27', '2016100318', '출석');"; //화 안드로이드

            x[55]="insert into Attend(C_no, Date, SNo, State) values(12, '03/28', '2016100274', '출석');";
            x[56]="insert into Attend(C_no, Date, SNo, State) values(13, '03/28', '2016100274', '출석');";
            x[57]="insert into Attend(C_no, Date, SNo, State) values(12, '03/28', '2016100304', '출석');";
            x[58]="insert into Attend(C_no, Date, SNo, State) values(13, '03/28', '2016100304', '출석');";
            x[59]="insert into Attend(C_no, Date, SNo, State) values(12, '03/28', '2016100306', '출석');";
            x[60]="insert into Attend(C_no, Date, SNo, State) values(13, '03/28', '2016100306', '출석');";
            x[61]="insert into Attend(C_no, Date, SNo, State) values(12, '03/28', '2016100318', '출석');";
            x[62]="insert into Attend(C_no, Date, SNo, State) values(13, '03/28', '2016100318', '출석');"; //수 안드로이드

            x[63]="insert into Attend(C_no, Date, SNo, State) values(14, '03/28', '2016100274', '출석');";
            x[64]="insert into Attend(C_no, Date, SNo, State) values(15, '03/28', '2016100274', '출석');";
            x[65]="insert into Attend(C_no, Date, SNo, State) values(14, '03/28', '2016100304', '출석');";
            x[66]="insert into Attend(C_no, Date, SNo, State) values(15, '03/28', '2016100304', '출석');";
            x[67]="insert into Attend(C_no, Date, SNo, State) values(14, '03/28', '2016100306', '출석');";
            x[68]="insert into Attend(C_no, Date, SNo, State) values(15, '03/28', '2016100306', '출석');";
            x[69]="insert into Attend(C_no, Date, SNo, State) values(14, '03/28', '2016100318', '출석');";
            x[70]="insert into Attend(C_no, Date, SNo, State) values(15, '03/28', '2016100318', '출석');"; //수 서버관리

            x[71]="insert into Attend(C_no, Date, SNo, State) values(16, '03/29', '2016100304', '출석');";
            x[72]="insert into Attend(C_no, Date, SNo, State) values(16, '03/29', '2016100306', '출석');";
            x[73]="insert into Attend(C_no, Date, SNo, State) values(16, '03/29', '2016100318', '출석');"; //목 시스템 분석 및 설계

            x[74]="insert into Attend(C_no, Date, SNo, State) values(17, '03/29', '2016100274', '출석');";
            x[75]="insert into Attend(C_no, Date, SNo, State) values(18, '03/29', '2016100274', '출석');"; //목 기업가정신과 창업

            x[76]="insert into Attend(C_no, Date, SNo, State) values(19, '03/29', '2016100274', '출석');";
            x[77]="insert into Attend(C_no, Date, SNo, State) values(19, '03/29', '2016100304', '출석');";
            x[78]="insert into Attend(C_no, Date, SNo, State) values(19, '03/29', '2016100306', '출석');";
            x[79]="insert into Attend(C_no, Date, SNo, State) values(19, '03/29', '2016100318', '출석');"; //목 취업과 창업

            for(int i=0; i<80; i++){ db.execSQL(x[i]); }




            String [] w = new String[80];  //5주
            w[0]="insert into Attend(C_no, Date, SNo, State) values(20, '03/30', '2016100304', '출석');";
            w[1]="insert into Attend(C_no, Date, SNo, State) values(21, '03/30', '2016100304', '출석');";
            w[2]="insert into Attend(C_no, Date, SNo, State) values(20, '03/30', '2016100306', '출석');";
            w[3]="insert into Attend(C_no, Date, SNo, State) values(21, '03/30', '2016100306', '출석');";
            w[4]="insert into Attend(C_no, Date, SNo, State) values(20, '03/30', '2016100318', '출석');";
            w[5]="insert into Attend(C_no, Date, SNo, State) values(21, '03/30', '2016100318', '출석');"; //금 시스템분석및 설계

            w[6]="insert into Attend(C_no, Date, SNo, State) values(22, '03/30', '2016100274', '출석');";
            w[7]="insert into Attend(C_no, Date, SNo, State) values(23, '03/30', '2016100274', '출석');";
            w[8]="insert into Attend(C_no, Date, SNo, State) values(22, '03/30', '2016100304', '출석');";
            w[9]="insert into Attend(C_no, Date, SNo, State) values(23, '03/30', '2016100304', '출석');";
            w[10]="insert into Attend(C_no, Date, SNo, State) values(22, '03/30', '2016100306', '출석');";
            w[11]="insert into Attend(C_no, Date, SNo, State) values(23, '03/30', '2016100306', '출석');";
            w[12]="insert into Attend(C_no, Date, SNo, State) values(22, '03/30', '2016100318', '출석');";
            w[13]="insert into Attend(C_no, Date, SNo, State) values(23, '03/30', '2016100318', '출석');"; //금 서버관리

            w[14]="insert into Attend(C_no, Date, SNo, State) values(4, '04/02', '2016100274', '출석');";
            w[15]="insert into Attend(C_no, Date, SNo, State) values(5, '04/02', '2016100274', '출석');";
            w[16]="insert into Attend(C_no, Date, SNo, State) values(6, '04/02', '2016100274', '출석');";
            w[17]="insert into Attend(C_no, Date, SNo, State) values(4, '04/02', '2016100304', '출석');";
            w[18]="insert into Attend(C_no, Date, SNo, State) values(5, '04/02', '2016100304', '출석');";
            w[19]="insert into Attend(C_no, Date, SNo, State) values(6, '04/02', '2016100304', '출석');";
            w[20]="insert into Attend(C_no, Date, SNo, State) values(4, '04/02', '2016100306', '출석');";
            w[21]="insert into Attend(C_no, Date, SNo, State) values(5, '04/02', '2016100306', '출석');";
            w[22]="insert into Attend(C_no, Date, SNo, State) values(6, '04/02', '2016100306', '출석');";
            w[23]="insert into Attend(C_no, Date, SNo, State) values(4, '04/02', '2016100318', '출석');";
            w[24]="insert into Attend(C_no, Date, SNo, State) values(5, '04/02', '2016100318', '출석');";
            w[25]="insert into Attend(C_no, Date, SNo, State) values(6, '04/02', '2016100318', '출석');"; //월 프로젝트

            w[26]="insert into Attend(C_no, Date, SNo, State) values(1, '04/02', '2016100274', '출석');";
            w[27]="insert into Attend(C_no, Date, SNo, State) values(2, '04/02', '2016100274', '출석');";
            w[28]="insert into Attend(C_no, Date, SNo, State) values(3, '04/02', '2016100274', '출석');";
            w[29]="insert into Attend(C_no, Date, SNo, State) values(1, '04/02', '2016100304', '출석');";
            w[30]="insert into Attend(C_no, Date, SNo, State) values(2, '04/02', '2016100304', '출석');";
            w[31]="insert into Attend(C_no, Date, SNo, State) values(3, '04/02', '2016100304', '출석');";
            w[32]="insert into Attend(C_no, Date, SNo, State) values(1, '04/02', '2016100306', '출석');";
            w[33]="insert into Attend(C_no, Date, SNo, State) values(2, '04/02', '2016100306', '출석');";
            w[34]="insert into Attend(C_no, Date, SNo, State) values(3, '04/02', '2016100306', '출석');";
            w[35]="insert into Attend(C_no, Date, SNo, State) values(1, '04/02', '2016100318', '출석');";
            w[36]="insert into Attend(C_no, Date, SNo, State) values(2, '04/02', '2016100318', '출석');";
            w[37]="insert into Attend(C_no, Date, SNo, State) values(3, '04/02', '2016100318', '출석');"; //월 프레임워크

            w[38]="insert into Attend(C_no, Date, SNo, State) values(7, '04/03', '2016100304', '출석');";
            w[39]="insert into Attend(C_no, Date, SNo, State) values(8, '04/03', '2016100304', '출석');";
            w[40]="insert into Attend(C_no, Date, SNo, State) values(9, '04/03', '2016100304', '출석');";
            w[41]="insert into Attend(C_no, Date, SNo, State) values(7, '04/03', '2016100306', '출석');";
            w[42]="insert into Attend(C_no, Date, SNo, State) values(8, '04/03', '2016100306', '출석');";
            w[43]="insert into Attend(C_no, Date, SNo, State) values(9, '04/03', '2016100306', '출석');";
            w[44]="insert into Attend(C_no, Date, SNo, State) values(7, '04/03', '2016100318', '출석');";
            w[45]="insert into Attend(C_no, Date, SNo, State) values(8, '04/03', '2016100318', '출석');";
            w[46]="insert into Attend(C_no, Date, SNo, State) values(9, '04/03', '2016100318', '출석');"; //화 임베디드

            w[47]="insert into Attend(C_no, Date, SNo, State) values(10, '04/03', '2016100274', '출석');";
            w[48]="insert into Attend(C_no, Date, SNo, State) values(11, '04/03', '2016100274', '출석');";
            w[49]="insert into Attend(C_no, Date, SNo, State) values(10, '04/03', '2016100304', '출석');";
            w[50]="insert into Attend(C_no, Date, SNo, State) values(11, '04/03', '2016100304', '출석');";
            w[51]="insert into Attend(C_no, Date, SNo, State) values(10, '04/03', '2016100306', '출석');";
            w[52]="insert into Attend(C_no, Date, SNo, State) values(11, '04/03', '2016100306', '출석');";
            w[53]="insert into Attend(C_no, Date, SNo, State) values(10, '04/03', '2016100318', '출석');";
            w[54]="insert into Attend(C_no, Date, SNo, State) values(11, '04/03', '2016100318', '출석');"; //화 안드로이드

            w[55]="insert into Attend(C_no, Date, SNo, State) values(12, '04/04', '2016100274', '출석');";
            w[56]="insert into Attend(C_no, Date, SNo, State) values(13, '04/04', '2016100274', '출석');";
            w[57]="insert into Attend(C_no, Date, SNo, State) values(12, '04/04', '2016100304', '출석');";
            w[58]="insert into Attend(C_no, Date, SNo, State) values(13, '04/04', '2016100304', '출석');";
            w[59]="insert into Attend(C_no, Date, SNo, State) values(12, '04/04', '2016100306', '출석');";
            w[60]="insert into Attend(C_no, Date, SNo, State) values(13, '04/04', '2016100306', '출석');";
            w[61]="insert into Attend(C_no, Date, SNo, State) values(12, '04/04', '2016100318', '출석');";
            w[62]="insert into Attend(C_no, Date, SNo, State) values(13, '04/04', '2016100318', '출석');"; //수 안드로이드

            w[63]="insert into Attend(C_no, Date, SNo, State) values(14, '04/04', '2016100274', '출석');";
            w[64]="insert into Attend(C_no, Date, SNo, State) values(15, '04/04', '2016100274', '출석');";
            w[65]="insert into Attend(C_no, Date, SNo, State) values(14, '04/04', '2016100304', '출석');";
            w[66]="insert into Attend(C_no, Date, SNo, State) values(15, '04/04', '2016100304', '출석');";
            w[67]="insert into Attend(C_no, Date, SNo, State) values(14, '04/04', '2016100306', '출석');";
            w[68]="insert into Attend(C_no, Date, SNo, State) values(15, '04/04', '2016100306', '출석');";
            w[69]="insert into Attend(C_no, Date, SNo, State) values(14, '04/04', '2016100318', '출석');";
            w[70]="insert into Attend(C_no, Date, SNo, State) values(15, '04/04', '2016100318', '출석');"; //수 서버관리

            w[71]="insert into Attend(C_no, Date, SNo, State) values(16, '04/05', '2016100304', '출석');";
            w[72]="insert into Attend(C_no, Date, SNo, State) values(16, '04/05', '2016100306', '출석');";
            w[73]="insert into Attend(C_no, Date, SNo, State) values(16, '04/05', '2016100318', '출석');"; //목 시스템 분석 및 설계

            w[74]="insert into Attend(C_no, Date, SNo, State) values(17, '04/05', '2016100274', '출석');";
            w[75]="insert into Attend(C_no, Date, SNo, State) values(18, '04/05', '2016100274', '출석');"; //목 기업가정신과 창업

            w[76]="insert into Attend(C_no, Date, SNo, State) values(19, '04/05', '2016100274', '출석');";
            w[77]="insert into Attend(C_no, Date, SNo, State) values(19, '04/05', '2016100304', '출석');";
            w[78]="insert into Attend(C_no, Date, SNo, State) values(19, '04/05', '2016100306', '출석');";
            w[79]="insert into Attend(C_no, Date, SNo, State) values(19, '04/05', '2016100318', '출석');"; //목 취업과 창업

            for(int i=0; i<80; i++){ db.execSQL(w[i]); }



            String [] v = new String[80];  //6주
            v[0]="insert into Attend(C_no, Date, SNo, State) values(20, '04/06', '2016100304', '출석');";
            v[1]="insert into Attend(C_no, Date, SNo, State) values(21, '04/06', '2016100304', '출석');";
            v[2]="insert into Attend(C_no, Date, SNo, State) values(20, '04/06', '2016100306', '출석');";
            v[3]="insert into Attend(C_no, Date, SNo, State) values(21, '04/06', '2016100306', '출석');";
            v[4]="insert into Attend(C_no, Date, SNo, State) values(20, '04/06', '2016100318', '출석');";
            v[5]="insert into Attend(C_no, Date, SNo, State) values(21, '04/06', '2016100318', '출석');"; //금 시스템분석및 설계

            v[6]="insert into Attend(C_no, Date, SNo, State) values(22, '04/06', '2016100274', '출석');";
            v[7]="insert into Attend(C_no, Date, SNo, State) values(23, '04/06', '2016100274', '출석');";
            v[8]="insert into Attend(C_no, Date, SNo, State) values(22, '04/06', '2016100304', '출석');";
            v[9]="insert into Attend(C_no, Date, SNo, State) values(23, '04/06', '2016100304', '출석');";
            v[10]="insert into Attend(C_no, Date, SNo, State) values(22, '04/06', '2016100306', '출석');";
            v[11]="insert into Attend(C_no, Date, SNo, State) values(23, '04/06', '2016100306', '출석');";
            v[12]="insert into Attend(C_no, Date, SNo, State) values(22, '04/06', '2016100318', '출석');";
            v[13]="insert into Attend(C_no, Date, SNo, State) values(23, '04/06', '2016100318', '출석');"; //금 서버관리

            v[14]="insert into Attend(C_no, Date, SNo, State) values(4, '04/09', '2016100274', '출석');";
            v[15]="insert into Attend(C_no, Date, SNo, State) values(5, '04/09', '2016100274', '출석');";
            v[16]="insert into Attend(C_no, Date, SNo, State) values(6, '04/09', '2016100274', '출석');";
            v[17]="insert into Attend(C_no, Date, SNo, State) values(4, '04/09', '2016100304', '출석');";
            v[18]="insert into Attend(C_no, Date, SNo, State) values(5, '04/09', '2016100304', '출석');";
            v[19]="insert into Attend(C_no, Date, SNo, State) values(6, '04/09', '2016100304', '출석');";
            v[20]="insert into Attend(C_no, Date, SNo, State) values(4, '04/09', '2016100306', '출석');";
            v[21]="insert into Attend(C_no, Date, SNo, State) values(5, '04/09', '2016100306', '출석');";
            v[22]="insert into Attend(C_no, Date, SNo, State) values(6, '04/09', '2016100306', '출석');";
            v[23]="insert into Attend(C_no, Date, SNo, State) values(4, '04/09', '2016100318', '출석');";
            v[24]="insert into Attend(C_no, Date, SNo, State) values(5, '04/09', '2016100318', '출석');";
            v[25]="insert into Attend(C_no, Date, SNo, State) values(6, '04/09', '2016100318', '출석');"; //월 프로젝트

            v[26]="insert into Attend(C_no, Date, SNo, State) values(1, '04/09', '2016100274', '출석');";
            v[27]="insert into Attend(C_no, Date, SNo, State) values(2, '04/09', '2016100274', '출석');";
            v[28]="insert into Attend(C_no, Date, SNo, State) values(3, '04/09', '2016100274', '출석');";
            v[29]="insert into Attend(C_no, Date, SNo, State) values(1, '04/09', '2016100304', '출석');";
            v[30]="insert into Attend(C_no, Date, SNo, State) values(2, '04/09', '2016100304', '출석');";
            v[31]="insert into Attend(C_no, Date, SNo, State) values(3, '04/09', '2016100304', '출석');";
            v[32]="insert into Attend(C_no, Date, SNo, State) values(1, '04/09', '2016100306', '출석');";
            v[33]="insert into Attend(C_no, Date, SNo, State) values(2, '04/09', '2016100306', '출석');";
            v[34]="insert into Attend(C_no, Date, SNo, State) values(3, '04/09', '2016100306', '출석');";
            v[35]="insert into Attend(C_no, Date, SNo, State) values(1, '04/09', '2016100318', '출석');";
            v[36]="insert into Attend(C_no, Date, SNo, State) values(2, '04/09', '2016100318', '출석');";
            v[37]="insert into Attend(C_no, Date, SNo, State) values(3, '04/09', '2016100318', '출석');"; //월 프레임워크

            v[38]="insert into Attend(C_no, Date, SNo, State) values(7, '04/10', '2016100304', '출석');";
            v[39]="insert into Attend(C_no, Date, SNo, State) values(8, '04/10', '2016100304', '출석');";
            v[40]="insert into Attend(C_no, Date, SNo, State) values(9, '04/10', '2016100304', '출석');";
            v[41]="insert into Attend(C_no, Date, SNo, State) values(7, '04/10', '2016100306', '출석');";
            v[42]="insert into Attend(C_no, Date, SNo, State) values(8, '04/10', '2016100306', '출석');";
            v[43]="insert into Attend(C_no, Date, SNo, State) values(9, '04/10', '2016100306', '출석');";
            v[44]="insert into Attend(C_no, Date, SNo, State) values(7, '04/10', '2016100318', '출석');";
            v[45]="insert into Attend(C_no, Date, SNo, State) values(8, '04/10', '2016100318', '출석');";
            v[46]="insert into Attend(C_no, Date, SNo, State) values(9, '04/10', '2016100318', '출석');"; //화 임베디드

            v[47]="insert into Attend(C_no, Date, SNo, State) values(10, '04/10', '2016100274', '출석');";
            v[48]="insert into Attend(C_no, Date, SNo, State) values(11, '04/10', '2016100274', '출석');";
            v[49]="insert into Attend(C_no, Date, SNo, State) values(10, '04/10', '2016100304', '출석');";
            v[50]="insert into Attend(C_no, Date, SNo, State) values(11, '04/10', '2016100304', '출석');";
            v[51]="insert into Attend(C_no, Date, SNo, State) values(10, '04/10', '2016100306', '출석');";
            v[52]="insert into Attend(C_no, Date, SNo, State) values(11, '04/10', '2016100306', '출석');";
            v[53]="insert into Attend(C_no, Date, SNo, State) values(10, '04/10', '2016100318', '출석');";
            v[54]="insert into Attend(C_no, Date, SNo, State) values(11, '04/10', '2016100318', '출석');"; //화 안드로이드

            v[55]="insert into Attend(C_no, Date, SNo, State) values(12, '04/11', '2016100274', '출석');";
            v[56]="insert into Attend(C_no, Date, SNo, State) values(13, '04/11', '2016100274', '출석');";
            v[57]="insert into Attend(C_no, Date, SNo, State) values(12, '04/11', '2016100304', '출석');";
            v[58]="insert into Attend(C_no, Date, SNo, State) values(13, '04/11', '2016100304', '출석');";
            v[59]="insert into Attend(C_no, Date, SNo, State) values(12, '04/11', '2016100306', '출석');";
            v[60]="insert into Attend(C_no, Date, SNo, State) values(13, '04/11', '2016100306', '출석');";
            v[61]="insert into Attend(C_no, Date, SNo, State) values(12, '04/11', '2016100318', '출석');";
            v[62]="insert into Attend(C_no, Date, SNo, State) values(13, '04/11', '2016100318', '출석');"; //수 안드로이드

            v[63]="insert into Attend(C_no, Date, SNo, State) values(14, '04/11', '2016100274', '출석');";
            v[64]="insert into Attend(C_no, Date, SNo, State) values(15, '04/11', '2016100274', '출석');";
            v[65]="insert into Attend(C_no, Date, SNo, State) values(14, '04/11', '2016100304', '출석');";
            v[66]="insert into Attend(C_no, Date, SNo, State) values(15, '04/11', '2016100304', '출석');";
            v[67]="insert into Attend(C_no, Date, SNo, State) values(14, '04/11', '2016100306', '출석');";
            v[68]="insert into Attend(C_no, Date, SNo, State) values(15, '04/11', '2016100306', '출석');";
            v[69]="insert into Attend(C_no, Date, SNo, State) values(14, '04/11', '2016100318', '출석');";
            v[70]="insert into Attend(C_no, Date, SNo, State) values(15, '04/11', '2016100318', '출석');"; //수 서버관리

            v[71]="insert into Attend(C_no, Date, SNo, State) values(16, '04/12', '2016100304', '출석');";
            v[72]="insert into Attend(C_no, Date, SNo, State) values(16, '04/12', '2016100306', '출석');";
            v[73]="insert into Attend(C_no, Date, SNo, State) values(16, '04/12', '2016100318', '출석');"; //목 시스템 분석 및 설계

            v[74]="insert into Attend(C_no, Date, SNo, State) values(17, '04/12', '2016100274', '출석');";
            v[75]="insert into Attend(C_no, Date, SNo, State) values(18, '04/12', '2016100274', '출석');"; //목 기업가정신과 창업

            v[76]="insert into Attend(C_no, Date, SNo, State) values(19, '04/12', '2016100274', '출석');";
            v[77]="insert into Attend(C_no, Date, SNo, State) values(19, '04/12', '2016100304', '출석');";
            v[78]="insert into Attend(C_no, Date, SNo, State) values(19, '04/12', '2016100306', '출석');";
            v[79]="insert into Attend(C_no, Date, SNo, State) values(19, '04/12', '2016100318', '출석');"; //목 취업과 창업

            for(int i=0; i<80; i++){ db.execSQL(v[i]); }



            String [] u = new String[80];  //7주
            u[0]="insert into Attend(C_no, Date, SNo, State) values(20, '04/13', '2016100304', '출석');";
            u[1]="insert into Attend(C_no, Date, SNo, State) values(21, '04/13', '2016100304', '출석');";
            u[2]="insert into Attend(C_no, Date, SNo, State) values(20, '04/13', '2016100306', '출석');";
            u[3]="insert into Attend(C_no, Date, SNo, State) values(21, '04/13', '2016100306', '출석');";
            u[4]="insert into Attend(C_no, Date, SNo, State) values(20, '04/13', '2016100318', '출석');";
            u[5]="insert into Attend(C_no, Date, SNo, State) values(21, '04/13', '2016100318', '출석');"; //금 시스템분석및 설계

            u[6]="insert into Attend(C_no, Date, SNo, State) values(22, '04/13', '2016100274', '출석');";
            u[7]="insert into Attend(C_no, Date, SNo, State) values(23, '04/13', '2016100274', '출석');";
            u[8]="insert into Attend(C_no, Date, SNo, State) values(22, '04/13', '2016100304', '출석');";
            u[9]="insert into Attend(C_no, Date, SNo, State) values(23, '04/13', '2016100304', '출석');";
            u[10]="insert into Attend(C_no, Date, SNo, State) values(22, '04/13', '2016100306', '출석');";
            u[11]="insert into Attend(C_no, Date, SNo, State) values(23, '04/13', '2016100306', '출석');";
            u[12]="insert into Attend(C_no, Date, SNo, State) values(22, '04/13', '2016100318', '출석');";
            u[13]="insert into Attend(C_no, Date, SNo, State) values(23, '04/13', '2016100318', '출석');"; //금 서버관리

            u[14]="insert into Attend(C_no, Date, SNo, State) values(4, '04/16', '2016100274', '출석');";
            u[15]="insert into Attend(C_no, Date, SNo, State) values(5, '04/16', '2016100274', '출석');";
            u[16]="insert into Attend(C_no, Date, SNo, State) values(6, '04/16', '2016100274', '출석');";
            u[17]="insert into Attend(C_no, Date, SNo, State) values(4, '04/16', '2016100304', '출석');";
            u[18]="insert into Attend(C_no, Date, SNo, State) values(5, '04/16', '2016100304', '출석');";
            u[19]="insert into Attend(C_no, Date, SNo, State) values(6, '04/16', '2016100304', '출석');";
            u[20]="insert into Attend(C_no, Date, SNo, State) values(4, '04/16', '2016100306', '출석');";
            u[21]="insert into Attend(C_no, Date, SNo, State) values(5, '04/16', '2016100306', '출석');";
            u[22]="insert into Attend(C_no, Date, SNo, State) values(6, '04/16', '2016100306', '출석');";
            u[23]="insert into Attend(C_no, Date, SNo, State) values(4, '04/16', '2016100318', '출석');";
            u[24]="insert into Attend(C_no, Date, SNo, State) values(5, '04/16', '2016100318', '출석');";
            u[25]="insert into Attend(C_no, Date, SNo, State) values(6, '04/16', '2016100318', '출석');"; //월 프로젝트

            u[26]="insert into Attend(C_no, Date, SNo, State) values(1, '04/16', '2016100274', '출석');";
            u[27]="insert into Attend(C_no, Date, SNo, State) values(2, '04/16', '2016100274', '출석');";
            u[28]="insert into Attend(C_no, Date, SNo, State) values(3, '04/16', '2016100274', '출석');";
            u[29]="insert into Attend(C_no, Date, SNo, State) values(1, '04/16', '2016100304', '출석');";
            u[30]="insert into Attend(C_no, Date, SNo, State) values(2, '04/16', '2016100304', '출석');";
            u[31]="insert into Attend(C_no, Date, SNo, State) values(3, '04/16', '2016100304', '출석');";
            u[32]="insert into Attend(C_no, Date, SNo, State) values(1, '04/16', '2016100306', '출석');";
            u[33]="insert into Attend(C_no, Date, SNo, State) values(2, '04/16', '2016100306', '출석');";
            u[34]="insert into Attend(C_no, Date, SNo, State) values(3, '04/16', '2016100306', '출석');";
            u[35]="insert into Attend(C_no, Date, SNo, State) values(1, '04/16', '2016100318', '출석');";
            u[36]="insert into Attend(C_no, Date, SNo, State) values(2, '04/16', '2016100318', '출석');";
            u[37]="insert into Attend(C_no, Date, SNo, State) values(3, '04/16', '2016100318', '출석');"; //월 프레임워크

            u[38]="insert into Attend(C_no, Date, SNo, State) values(7, '04/17', '2016100304', '출석');";
            u[39]="insert into Attend(C_no, Date, SNo, State) values(8, '04/17', '2016100304', '출석');";
            u[40]="insert into Attend(C_no, Date, SNo, State) values(9, '04/17', '2016100304', '출석');";
            u[41]="insert into Attend(C_no, Date, SNo, State) values(7, '04/17', '2016100306', '출석');";
            u[42]="insert into Attend(C_no, Date, SNo, State) values(8, '04/17', '2016100306', '출석');";
            u[43]="insert into Attend(C_no, Date, SNo, State) values(9, '04/17', '2016100306', '출석');";
            u[44]="insert into Attend(C_no, Date, SNo, State) values(7, '04/17', '2016100318', '출석');";
            u[45]="insert into Attend(C_no, Date, SNo, State) values(8, '04/17', '2016100318', '출석');";
            u[46]="insert into Attend(C_no, Date, SNo, State) values(9, '04/17', '2016100318', '출석');"; //화 임베디드

            u[47]="insert into Attend(C_no, Date, SNo, State) values(10, '04/17', '2016100274', '출석');";
            u[48]="insert into Attend(C_no, Date, SNo, State) values(11, '04/17', '2016100274', '출석');";
            u[49]="insert into Attend(C_no, Date, SNo, State) values(10, '04/17', '2016100304', '출석');";
            u[50]="insert into Attend(C_no, Date, SNo, State) values(11, '04/17', '2016100304', '출석');";
            u[51]="insert into Attend(C_no, Date, SNo, State) values(10, '04/17', '2016100306', '출석');";
            u[52]="insert into Attend(C_no, Date, SNo, State) values(11, '04/17', '2016100306', '출석');";
            u[53]="insert into Attend(C_no, Date, SNo, State) values(10, '04/17', '2016100318', '출석');";
            u[54]="insert into Attend(C_no, Date, SNo, State) values(11, '04/17', '2016100318', '출석');"; //화 안드로이드

            u[55]="insert into Attend(C_no, Date, SNo, State) values(12, '04/18', '2016100274', '출석');";
            u[56]="insert into Attend(C_no, Date, SNo, State) values(13, '04/18', '2016100274', '출석');";
            u[57]="insert into Attend(C_no, Date, SNo, State) values(12, '04/18', '2016100304', '출석');";
            u[58]="insert into Attend(C_no, Date, SNo, State) values(13, '04/18', '2016100304', '출석');";
            u[59]="insert into Attend(C_no, Date, SNo, State) values(12, '04/18', '2016100306', '출석');";
            u[60]="insert into Attend(C_no, Date, SNo, State) values(13, '04/18', '2016100306', '출석');";
            u[61]="insert into Attend(C_no, Date, SNo, State) values(12, '04/18', '2016100318', '출석');";
            u[62]="insert into Attend(C_no, Date, SNo, State) values(13, '04/18', '2016100318', '출석');"; //수 안드로이드

            u[63]="insert into Attend(C_no, Date, SNo, State) values(14, '04/18', '2016100274', '출석');";
            u[64]="insert into Attend(C_no, Date, SNo, State) values(15, '04/18', '2016100274', '출석');";
            u[65]="insert into Attend(C_no, Date, SNo, State) values(14, '04/18', '2016100304', '출석');";
            u[66]="insert into Attend(C_no, Date, SNo, State) values(15, '04/18', '2016100304', '출석');";
            u[67]="insert into Attend(C_no, Date, SNo, State) values(14, '04/18', '2016100306', '출석');";
            u[68]="insert into Attend(C_no, Date, SNo, State) values(15, '04/18', '2016100306', '출석');";
            u[69]="insert into Attend(C_no, Date, SNo, State) values(14, '04/18', '2016100318', '출석');";
            u[70]="insert into Attend(C_no, Date, SNo, State) values(15, '04/18', '2016100318', '출석');"; //수 서버관리

            u[71]="insert into Attend(C_no, Date, SNo, State) values(16, '04/19', '2016100304', '출석');";
            u[72]="insert into Attend(C_no, Date, SNo, State) values(16, '04/19', '2016100306', '출석');";
            u[73]="insert into Attend(C_no, Date, SNo, State) values(16, '04/19', '2016100318', '출석');"; //목 시스템 분석 및 설계
            u[74]="insert into Attend(C_no, Date, SNo, State) values(17, '04/19', '2016100274', '출석');";
            u[75]="insert into Attend(C_no, Date, SNo, State) values(18, '04/19', '2016100274', '출석');"; //목 기업가정신과 창업

            u[76]="insert into Attend(C_no, Date, SNo, State) values(19, '04/19', '2016100274', '출석');";
            u[77]="insert into Attend(C_no, Date, SNo, State) values(19, '04/19', '2016100304', '출석');";
            u[78]="insert into Attend(C_no, Date, SNo, State) values(19, '04/19', '2016100306', '출석');";
            u[79]="insert into Attend(C_no, Date, SNo, State) values(19, '04/19', '2016100318', '출석');"; //목 취업과 창업

            for(int i=0; i<80; i++){ db.execSQL(u[i]); }


            String [] t= new String[80];  //8주
            t[0]="insert into Attend(C_no, Date, SNo, State) values(20, '04/20', '2016100304', '출석');";
            t[1]="insert into Attend(C_no, Date, SNo, State) values(21, '04/20', '2016100304', '출석');";
            t[2]="insert into Attend(C_no, Date, SNo, State) values(20, '04/20', '2016100306', '출석');";
            t[3]="insert into Attend(C_no, Date, SNo, State) values(21, '04/20', '2016100306', '출석');";
            t[4]="insert into Attend(C_no, Date, SNo, State) values(20, '04/20', '2016100318', '출석');";
            t[5]="insert into Attend(C_no, Date, SNo, State) values(21, '04/20', '2016100318', '출석');"; //금 시스템분석및 설계

            t[6]="insert into Attend(C_no, Date, SNo, State) values(22, '04/20', '2016100274', '출석');";
            t[7]="insert into Attend(C_no, Date, SNo, State) values(23, '04/20', '2016100274', '출석');";
            t[8]="insert into Attend(C_no, Date, SNo, State) values(22, '04/20', '2016100304', '출석');";
            t[9]="insert into Attend(C_no, Date, SNo, State) values(23, '04/20', '2016100304', '출석');";
            t[10]="insert into Attend(C_no, Date, SNo, State) values(22, '04/20', '2016100306', '출석');";
            t[11]="insert into Attend(C_no, Date, SNo, State) values(23, '04/20', '2016100306', '출석');";
            t[12]="insert into Attend(C_no, Date, SNo, State) values(22, '04/20', '2016100318', '출석');";
            t[13]="insert into Attend(C_no, Date, SNo, State) values(23, '04/20', '2016100318', '출석');"; //금 서버관리

            t[14]="insert into Attend(C_no, Date, SNo, State) values(4, '04/23', '2016100274', '출석');";
            t[15]="insert into Attend(C_no, Date, SNo, State) values(5, '04/23', '2016100274', '출석');";
            t[16]="insert into Attend(C_no, Date, SNo, State) values(6, '04/23', '2016100274', '출석');";
            t[17]="insert into Attend(C_no, Date, SNo, State) values(4, '04/23', '2016100304', '출석');";
            t[18]="insert into Attend(C_no, Date, SNo, State) values(5, '04/23', '2016100304', '출석');";
            t[19]="insert into Attend(C_no, Date, SNo, State) values(6, '04/23', '2016100304', '출석');";
            t[20]="insert into Attend(C_no, Date, SNo, State) values(4, '04/23', '2016100306', '출석');";
            t[21]="insert into Attend(C_no, Date, SNo, State) values(5, '04/23', '2016100306', '출석');";
            t[22]="insert into Attend(C_no, Date, SNo, State) values(6, '04/23', '2016100306', '출석');";
            t[23]="insert into Attend(C_no, Date, SNo, State) values(4, '04/23', '2016100318', '출석');";
            t[24]="insert into Attend(C_no, Date, SNo, State) values(5, '04/23', '2016100318', '출석');";
            t[25]="insert into Attend(C_no, Date, SNo, State) values(6, '04/23', '2016100318', '출석');"; //월 프로젝트

            t[26]="insert into Attend(C_no, Date, SNo, State) values(1, '04/23', '2016100274', '출석');";
            t[27]="insert into Attend(C_no, Date, SNo, State) values(2, '04/23', '2016100274', '출석');";
            t[28]="insert into Attend(C_no, Date, SNo, State) values(3, '04/23', '2016100274', '출석');";
            t[29]="insert into Attend(C_no, Date, SNo, State) values(1, '04/23', '2016100304', '출석');";
            t[30]="insert into Attend(C_no, Date, SNo, State) values(2, '04/23', '2016100304', '출석');";
            t[31]="insert into Attend(C_no, Date, SNo, State) values(3, '04/23', '2016100304', '출석');";
            t[32]="insert into Attend(C_no, Date, SNo, State) values(1, '04/23', '2016100306', '출석');";
            t[33]="insert into Attend(C_no, Date, SNo, State) values(2, '04/23', '2016100306', '출석');";
            t[34]="insert into Attend(C_no, Date, SNo, State) values(3, '04/23', '2016100306', '출석');";
            t[35]="insert into Attend(C_no, Date, SNo, State) values(1, '04/23', '2016100318', '출석');";
            t[36]="insert into Attend(C_no, Date, SNo, State) values(2, '04/23', '2016100318', '출석');";
            t[37]="insert into Attend(C_no, Date, SNo, State) values(3, '04/23', '2016100318', '출석');"; //월 프레임워크

            t[38]="insert into Attend(C_no, Date, SNo, State) values(7, '04/24', '2016100304', '출석');";
            t[39]="insert into Attend(C_no, Date, SNo, State) values(8, '04/24', '2016100304', '출석');";
            t[40]="insert into Attend(C_no, Date, SNo, State) values(9, '04/24', '2016100304', '출석');";
            t[41]="insert into Attend(C_no, Date, SNo, State) values(7, '04/24', '2016100306', '출석');";
            t[42]="insert into Attend(C_no, Date, SNo, State) values(8, '04/24', '2016100306', '출석');";
            t[43]="insert into Attend(C_no, Date, SNo, State) values(9, '04/24', '2016100306', '출석');";
            t[44]="insert into Attend(C_no, Date, SNo, State) values(7, '04/24', '2016100318', '출석');";
            t[45]="insert into Attend(C_no, Date, SNo, State) values(8, '04/24', '2016100318', '출석');";
            t[46]="insert into Attend(C_no, Date, SNo, State) values(9, '04/24', '2016100318', '출석');"; //화 임베디드

            t[47]="insert into Attend(C_no, Date, SNo, State) values(10, '04/24', '2016100274', '출석');";
            t[48]="insert into Attend(C_no, Date, SNo, State) values(11, '04/24', '2016100274', '출석');";
            t[49]="insert into Attend(C_no, Date, SNo, State) values(10, '04/24', '2016100304', '출석');";
            t[50]="insert into Attend(C_no, Date, SNo, State) values(11, '04/24', '2016100304', '출석');";
            t[51]="insert into Attend(C_no, Date, SNo, State) values(10, '04/24', '2016100306', '출석');";
            t[52]="insert into Attend(C_no, Date, SNo, State) values(11, '04/24', '2016100306', '출석');";
            t[53]="insert into Attend(C_no, Date, SNo, State) values(10, '04/24', '2016100318', '출석');";
            t[54]="insert into Attend(C_no, Date, SNo, State) values(11, '04/24', '2016100318', '출석');"; //화 안드로이드

            t[55]="insert into Attend(C_no, Date, SNo, State) values(12, '04/25', '2016100274', '출석');";
            t[56]="insert into Attend(C_no, Date, SNo, State) values(13, '04/25', '2016100274', '출석');";
            t[57]="insert into Attend(C_no, Date, SNo, State) values(12, '04/25', '2016100304', '출석');";
            t[58]="insert into Attend(C_no, Date, SNo, State) values(13, '04/25', '2016100304', '출석');";
            t[59]="insert into Attend(C_no, Date, SNo, State) values(12, '04/25', '2016100306', '출석');";
            t[60]="insert into Attend(C_no, Date, SNo, State) values(13, '04/25', '2016100306', '출석');";
            t[61]="insert into Attend(C_no, Date, SNo, State) values(12, '04/25', '2016100318', '출석');";
            t[62]="insert into Attend(C_no, Date, SNo, State) values(13, '04/25', '2016100318', '출석');"; //수 안드로이드

            t[63]="insert into Attend(C_no, Date, SNo, State) values(14, '04/25', '2016100274', '출석');";
            t[64]="insert into Attend(C_no, Date, SNo, State) values(15, '04/25', '2016100274', '출석');";
            t[65]="insert into Attend(C_no, Date, SNo, State) values(14, '04/25', '2016100304', '출석');";
            t[66]="insert into Attend(C_no, Date, SNo, State) values(15, '04/25', '2016100304', '출석');";
            t[67]="insert into Attend(C_no, Date, SNo, State) values(14, '04/25', '2016100306', '출석');";
            t[68]="insert into Attend(C_no, Date, SNo, State) values(15, '04/25', '2016100306', '출석');";
            t[69]="insert into Attend(C_no, Date, SNo, State) values(14, '04/25', '2016100318', '출석');";
            t[70]="insert into Attend(C_no, Date, SNo, State) values(15, '04/25', '2016100318', '출석');"; //수 서버관리

            t[71]="insert into Attend(C_no, Date, SNo, State) values(16, '04/26', '2016100304', '출석');";
            t[72]="insert into Attend(C_no, Date, SNo, State) values(16, '04/26', '2016100306', '출석');";
            t[73]="insert into Attend(C_no, Date, SNo, State) values(16, '04/26', '2016100318', '출석');"; //목 시스템 분석 및 설계
            t[74]="insert into Attend(C_no, Date, SNo, State) values(17, '04/26', '2016100274', '출석');";
            t[75]="insert into Attend(C_no, Date, SNo, State) values(18, '04/26', '2016100274', '출석');"; //목 기업가정신과 창업

            t[76]="insert into Attend(C_no, Date, SNo, State) values(19, '04/26', '2016100274', '출석');";
            t[77]="insert into Attend(C_no, Date, SNo, State) values(19, '04/26', '2016100304', '출석');";
            t[78]="insert into Attend(C_no, Date, SNo, State) values(19, '04/26', '2016100306', '출석');";
            t[79]="insert into Attend(C_no, Date, SNo, State) values(19, '04/26', '2016100318', '출석');"; //목 취업과 창업

            for(int i=0; i<80; i++){ db.execSQL(t[i]); }



            String [] s= new String[80];  //9주
            s[0]="insert into Attend(C_no, Date, SNo, State) values(20, '04/27', '2016100304', '출석');";
            s[1]="insert into Attend(C_no, Date, SNo, State) values(21, '04/27', '2016100304', '출석');";
            s[2]="insert into Attend(C_no, Date, SNo, State) values(20, '04/27', '2016100306', '출석');";
            s[3]="insert into Attend(C_no, Date, SNo, State) values(21, '04/27', '2016100306', '출석');";
            s[4]="insert into Attend(C_no, Date, SNo, State) values(20, '04/27', '2016100318', '출석');";
            s[5]="insert into Attend(C_no, Date, SNo, State) values(21, '04/27', '2016100318', '출석');"; //금 시스템분석및 설계

            s[6]="insert into Attend(C_no, Date, SNo, State) values(22, '04/27', '2016100274', '출석');";
            s[7]="insert into Attend(C_no, Date, SNo, State) values(23, '04/27', '2016100274', '출석');";
            s[8]="insert into Attend(C_no, Date, SNo, State) values(22, '04/27', '2016100304', '출석');";
            s[9]="insert into Attend(C_no, Date, SNo, State) values(23, '04/27', '2016100304', '출석');";
            s[10]="insert into Attend(C_no, Date, SNo, State) values(22, '04/27', '2016100306', '출석');";
            s[11]="insert into Attend(C_no, Date, SNo, State) values(23, '04/27', '2016100306', '출석');";
            s[12]="insert into Attend(C_no, Date, SNo, State) values(22, '04/27', '2016100318', '출석');";
            s[13]="insert into Attend(C_no, Date, SNo, State) values(23, '04/27', '2016100318', '출석');"; //금 서버관리

            s[14]="insert into Attend(C_no, Date, SNo, State) values(4, '04/30', '2016100274', '출석');";
            s[15]="insert into Attend(C_no, Date, SNo, State) values(5, '04/30', '2016100274', '출석');";
            s[16]="insert into Attend(C_no, Date, SNo, State) values(6, '04/30', '2016100274', '출석');";
            s[17]="insert into Attend(C_no, Date, SNo, State) values(4, '04/30', '2016100304', '출석');";
            s[18]="insert into Attend(C_no, Date, SNo, State) values(5, '04/30', '2016100304', '출석');";
            s[19]="insert into Attend(C_no, Date, SNo, State) values(6, '04/30', '2016100304', '출석');";
            s[20]="insert into Attend(C_no, Date, SNo, State) values(4, '04/30', '2016100306', '출석');";
            s[21]="insert into Attend(C_no, Date, SNo, State) values(5, '04/30', '2016100306', '출석');";
            s[22]="insert into Attend(C_no, Date, SNo, State) values(6, '04/30', '2016100306', '출석');";
            s[23]="insert into Attend(C_no, Date, SNo, State) values(4, '04/30', '2016100318', '출석');";
            s[24]="insert into Attend(C_no, Date, SNo, State) values(5, '04/30', '2016100318', '출석');";
            s[25]="insert into Attend(C_no, Date, SNo, State) values(6, '04/30', '2016100318', '출석');"; //월 프로젝트

            s[26]="insert into Attend(C_no, Date, SNo, State) values(1, '04/30', '2016100274', '출석');";
            s[27]="insert into Attend(C_no, Date, SNo, State) values(2, '04/30', '2016100274', '출석');";
            s[28]="insert into Attend(C_no, Date, SNo, State) values(3, '04/30', '2016100274', '출석');";
            s[29]="insert into Attend(C_no, Date, SNo, State) values(1, '04/30', '2016100304', '출석');";
            s[30]="insert into Attend(C_no, Date, SNo, State) values(2, '04/30', '2016100304', '출석');";
            s[31]="insert into Attend(C_no, Date, SNo, State) values(3, '04/30', '2016100304', '출석');";
            s[32]="insert into Attend(C_no, Date, SNo, State) values(1, '04/30', '2016100306', '출석');";
            s[33]="insert into Attend(C_no, Date, SNo, State) values(2, '04/30', '2016100306', '출석');";
            s[34]="insert into Attend(C_no, Date, SNo, State) values(3, '04/30', '2016100306', '출석');";
            s[35]="insert into Attend(C_no, Date, SNo, State) values(1, '04/30', '2016100318', '출석');";
            s[36]="insert into Attend(C_no, Date, SNo, State) values(2, '04/30', '2016100318', '출석');";
            s[37]="insert into Attend(C_no, Date, SNo, State) values(3, '04/30', '2016100318', '출석');"; //월 프레임워크

            s[38]="insert into Attend(C_no, Date, SNo, State) values(7, '05/01', '2016100304', '출석');";
            s[39]="insert into Attend(C_no, Date, SNo, State) values(8, '05/01', '2016100304', '출석');";
            s[40]="insert into Attend(C_no, Date, SNo, State) values(9, '05/01', '2016100304', '출석');";
            s[41]="insert into Attend(C_no, Date, SNo, State) values(7, '05/01', '2016100306', '출석');";
            s[42]="insert into Attend(C_no, Date, SNo, State) values(8, '05/01', '2016100306', '출석');";
            s[43]="insert into Attend(C_no, Date, SNo, State) values(9, '05/01', '2016100306', '출석');";
            s[44]="insert into Attend(C_no, Date, SNo, State) values(7, '05/01', '2016100318', '출석');";
            s[45]="insert into Attend(C_no, Date, SNo, State) values(8, '05/01', '2016100318', '출석');";
            s[46]="insert into Attend(C_no, Date, SNo, State) values(9, '05/01', '2016100318', '출석');"; //화 임베디드

            s[47]="insert into Attend(C_no, Date, SNo, State) values(10, '05/01', '2016100274', '출석');";
            s[48]="insert into Attend(C_no, Date, SNo, State) values(11, '05/01', '2016100274', '출석');";
            s[49]="insert into Attend(C_no, Date, SNo, State) values(10, '05/01', '2016100304', '출석');";
            s[50]="insert into Attend(C_no, Date, SNo, State) values(11, '05/01', '2016100304', '출석');";
            s[51]="insert into Attend(C_no, Date, SNo, State) values(10, '05/01', '2016100306', '출석');";
            s[52]="insert into Attend(C_no, Date, SNo, State) values(11, '05/01', '2016100306', '출석');";
            s[53]="insert into Attend(C_no, Date, SNo, State) values(10, '05/01', '2016100318', '출석');";
            s[54]="insert into Attend(C_no, Date, SNo, State) values(11, '05/01', '2016100318', '출석');"; //화 안드로이드

            s[55]="insert into Attend(C_no, Date, SNo, State) values(12, '05/02', '2016100274', '출석');";
            s[56]="insert into Attend(C_no, Date, SNo, State) values(13, '05/02', '2016100274', '출석');";
            s[57]="insert into Attend(C_no, Date, SNo, State) values(12, '05/02', '2016100304', '출석');";
            s[58]="insert into Attend(C_no, Date, SNo, State) values(13, '05/02', '2016100304', '출석');";
            s[59]="insert into Attend(C_no, Date, SNo, State) values(12, '05/02', '2016100306', '출석');";
            s[60]="insert into Attend(C_no, Date, SNo, State) values(13, '05/02', '2016100306', '출석');";
            s[61]="insert into Attend(C_no, Date, SNo, State) values(12, '05/02', '2016100318', '출석');";
            s[62]="insert into Attend(C_no, Date, SNo, State) values(13, '05/02', '2016100318', '출석');"; //수 안드로이드

            s[63]="insert into Attend(C_no, Date, SNo, State) values(14, '05/02', '2016100274', '출석');";
            s[64]="insert into Attend(C_no, Date, SNo, State) values(15, '05/02', '2016100274', '출석');";
            s[65]="insert into Attend(C_no, Date, SNo, State) values(14, '05/02', '2016100304', '출석');";
            s[66]="insert into Attend(C_no, Date, SNo, State) values(15, '05/02', '2016100304', '출석');";
            s[67]="insert into Attend(C_no, Date, SNo, State) values(14, '05/02', '2016100306', '출석');";
            s[68]="insert into Attend(C_no, Date, SNo, State) values(15, '05/02', '2016100306', '출석');";
            s[69]="insert into Attend(C_no, Date, SNo, State) values(14, '05/02', '2016100318', '출석');";
            s[70]="insert into Attend(C_no, Date, SNo, State) values(15, '05/02', '2016100318', '출석');"; //수 서버관리

            s[71]="insert into Attend(C_no, Date, SNo, State) values(16, '05/03', '2016100304', '출석');";
            s[72]="insert into Attend(C_no, Date, SNo, State) values(16, '05/03', '2016100306', '출석');";
            s[73]="insert into Attend(C_no, Date, SNo, State) values(16, '05/03', '2016100318', '출석');"; //목 시스템 분석 및 설계
            s[74]="insert into Attend(C_no, Date, SNo, State) values(17, '05/03', '2016100274', '출석');";
            s[75]="insert into Attend(C_no, Date, SNo, State) values(18, '05/03', '2016100274', '출석');"; //목 기업가정신과 창업

            s[76]="insert into Attend(C_no, Date, SNo, State) values(19, '05/03', '2016100274', '출석');";
            s[77]="insert into Attend(C_no, Date, SNo, State) values(19, '05/03', '2016100304', '출석');";
            s[78]="insert into Attend(C_no, Date, SNo, State) values(19, '05/03', '2016100306', '출석');";
            s[79]="insert into Attend(C_no, Date, SNo, State) values(19, '05/03', '2016100318', '출석');"; //목 취업과 창업

            for(int i=0; i<80; i++){ db.execSQL(s[i]); }



            String [] r= new String[80];  //10주
            r[0]="insert into Attend(C_no, Date, SNo, State) values(20, '05/04', '2016100304', '출석');";
            r[1]="insert into Attend(C_no, Date, SNo, State) values(21, '05/04', '2016100304', '출석');";
            r[2]="insert into Attend(C_no, Date, SNo, State) values(20, '05/04', '2016100306', '출석');";
            r[3]="insert into Attend(C_no, Date, SNo, State) values(21, '05/04', '2016100306', '출석');";
            r[4]="insert into Attend(C_no, Date, SNo, State) values(20, '05/04', '2016100318', '출석');";
            r[5]="insert into Attend(C_no, Date, SNo, State) values(21, '05/04', '2016100318', '출석');"; //금 시스템분석및 설계

            r[6]="insert into Attend(C_no, Date, SNo, State) values(22, '05/04', '2016100274', '출석');";
            r[7]="insert into Attend(C_no, Date, SNo, State) values(23, '05/04', '2016100274', '출석');";
            r[8]="insert into Attend(C_no, Date, SNo, State) values(22, '05/04', '2016100304', '출석');";
            r[9]="insert into Attend(C_no, Date, SNo, State) values(23, '05/04', '2016100304', '출석');";
            r[10]="insert into Attend(C_no, Date, SNo, State) values(22, '05/04', '2016100306', '출석');";
            r[11]="insert into Attend(C_no, Date, SNo, State) values(23, '05/04', '2016100306', '출석');";
            r[12]="insert into Attend(C_no, Date, SNo, State) values(22, '05/04', '2016100318', '출석');";
            r[13]="insert into Attend(C_no, Date, SNo, State) values(23, '05/04', '2016100318', '출석');"; //금 서버관리

            r[14]="insert into Attend(C_no, Date, SNo, State) values(4, '05/07', '2016100274', '출석');";
            r[15]="insert into Attend(C_no, Date, SNo, State) values(5, '05/07', '2016100274', '출석');";
            r[16]="insert into Attend(C_no, Date, SNo, State) values(6, '05/07', '2016100274', '출석');";
            r[17]="insert into Attend(C_no, Date, SNo, State) values(4, '05/07', '2016100304', '출석');";
            r[18]="insert into Attend(C_no, Date, SNo, State) values(5, '05/07', '2016100304', '출석');";
            r[19]="insert into Attend(C_no, Date, SNo, State) values(6, '05/07', '2016100304', '출석');";
            r[20]="insert into Attend(C_no, Date, SNo, State) values(4, '05/07', '2016100306', '출석');";
            r[21]="insert into Attend(C_no, Date, SNo, State) values(5, '05/07', '2016100306', '출석');";
            r[22]="insert into Attend(C_no, Date, SNo, State) values(6, '05/07', '2016100306', '출석');";
            r[23]="insert into Attend(C_no, Date, SNo, State) values(4, '05/07', '2016100318', '출석');";
            r[24]="insert into Attend(C_no, Date, SNo, State) values(5, '05/07', '2016100318', '출석');";
            r[25]="insert into Attend(C_no, Date, SNo, State) values(6, '05/07', '2016100318', '출석');"; //월 프로젝트

            r[26]="insert into Attend(C_no, Date, SNo, State) values(1, '05/07', '2016100274', '출석');";
            r[27]="insert into Attend(C_no, Date, SNo, State) values(2, '05/07', '2016100274', '출석');";
            r[28]="insert into Attend(C_no, Date, SNo, State) values(3, '05/07', '2016100274', '출석');";
            r[29]="insert into Attend(C_no, Date, SNo, State) values(1, '05/07', '2016100304', '출석');";
            r[30]="insert into Attend(C_no, Date, SNo, State) values(2, '05/07', '2016100304', '출석');";
            r[31]="insert into Attend(C_no, Date, SNo, State) values(3, '05/07', '2016100304', '출석');";
            r[32]="insert into Attend(C_no, Date, SNo, State) values(1, '05/07', '2016100306', '출석');";
            r[33]="insert into Attend(C_no, Date, SNo, State) values(2, '05/07', '2016100306', '출석');";
            r[34]="insert into Attend(C_no, Date, SNo, State) values(3, '05/07', '2016100306', '출석');";
            r[35]="insert into Attend(C_no, Date, SNo, State) values(1, '05/07', '2016100318', '출석');";
            r[36]="insert into Attend(C_no, Date, SNo, State) values(2, '05/07', '2016100318', '출석');";
            r[37]="insert into Attend(C_no, Date, SNo, State) values(3, '05/07', '2016100318', '출석');"; //월 프레임워크

            r[38]="insert into Attend(C_no, Date, SNo, State) values(7, '05/08', '2016100304', '출석');";
            r[39]="insert into Attend(C_no, Date, SNo, State) values(8, '05/08', '2016100304', '출석');";
            r[40]="insert into Attend(C_no, Date, SNo, State) values(9, '05/08', '2016100304', '출석');";
            r[41]="insert into Attend(C_no, Date, SNo, State) values(7, '05/08', '2016100306', '출석');";
            r[42]="insert into Attend(C_no, Date, SNo, State) values(8, '05/08', '2016100306', '출석');";
            r[43]="insert into Attend(C_no, Date, SNo, State) values(9, '05/08', '2016100306', '출석');";
            r[44]="insert into Attend(C_no, Date, SNo, State) values(7, '05/08', '2016100318', '출석');";
            r[45]="insert into Attend(C_no, Date, SNo, State) values(8, '05/08', '2016100318', '출석');";
            r[46]="insert into Attend(C_no, Date, SNo, State) values(9, '05/08', '2016100318', '출석');"; //화 임베디드

            r[47]="insert into Attend(C_no, Date, SNo, State) values(10, '05/08', '2016100274', '출석');";
            r[48]="insert into Attend(C_no, Date, SNo, State) values(11, '05/08', '2016100274', '출석');";
            r[49]="insert into Attend(C_no, Date, SNo, State) values(10, '05/08', '2016100304', '출석');";
            r[50]="insert into Attend(C_no, Date, SNo, State) values(11, '05/08', '2016100304', '출석');";
            r[51]="insert into Attend(C_no, Date, SNo, State) values(10, '05/08', '2016100306', '출석');";
            r[52]="insert into Attend(C_no, Date, SNo, State) values(11, '05/08', '2016100306', '출석');";
            r[53]="insert into Attend(C_no, Date, SNo, State) values(10, '05/08', '2016100318', '출석');";
            r[54]="insert into Attend(C_no, Date, SNo, State) values(11, '05/08', '2016100318', '출석');"; //화 안드로이드

            r[55]="insert into Attend(C_no, Date, SNo, State) values(12, '05/09', '2016100274', '출석');";
            r[56]="insert into Attend(C_no, Date, SNo, State) values(13, '05/09', '2016100274', '출석');";
            r[57]="insert into Attend(C_no, Date, SNo, State) values(12, '05/09', '2016100304', '출석');";
            r[58]="insert into Attend(C_no, Date, SNo, State) values(13, '05/09', '2016100304', '출석');";
            r[59]="insert into Attend(C_no, Date, SNo, State) values(12, '05/09', '2016100306', '출석');";
            r[60]="insert into Attend(C_no, Date, SNo, State) values(13, '05/09', '2016100306', '출석');";
            r[61]="insert into Attend(C_no, Date, SNo, State) values(12, '05/09', '2016100318', '출석');";
            r[62]="insert into Attend(C_no, Date, SNo, State) values(13, '05/09', '2016100318', '출석');"; //수 안드로이드

            r[63]="insert into Attend(C_no, Date, SNo, State) values(14, '05/09', '2016100274', '출석');";
            r[64]="insert into Attend(C_no, Date, SNo, State) values(15, '05/09', '2016100274', '출석');";
            r[65]="insert into Attend(C_no, Date, SNo, State) values(14, '05/09', '2016100304', '출석');";
            r[66]="insert into Attend(C_no, Date, SNo, State) values(15, '05/09', '2016100304', '출석');";
            r[67]="insert into Attend(C_no, Date, SNo, State) values(14, '05/09', '2016100306', '출석');";
            r[68]="insert into Attend(C_no, Date, SNo, State) values(15, '05/09', '2016100306', '출석');";
            r[69]="insert into Attend(C_no, Date, SNo, State) values(14, '05/09', '2016100318', '출석');";
            r[70]="insert into Attend(C_no, Date, SNo, State) values(15, '05/09', '2016100318', '출석');"; //수 서버관리

            r[71]="insert into Attend(C_no, Date, SNo, State) values(16, '05/10', '2016100304', '출석');";
            r[72]="insert into Attend(C_no, Date, SNo, State) values(16, '05/10', '2016100306', '출석');";
            r[73]="insert into Attend(C_no, Date, SNo, State) values(16, '05/10', '2016100318', '출석');"; //목 시스템 분석 및 설계
            r[74]="insert into Attend(C_no, Date, SNo, State) values(17, '05/10', '2016100274', '출석');";
            r[75]="insert into Attend(C_no, Date, SNo, State) values(18, '05/10', '2016100274', '출석');"; //목 기업가정신과 창업

            r[76]="insert into Attend(C_no, Date, SNo, State) values(19, '05/10', '2016100274', '출석');";
            r[77]="insert into Attend(C_no, Date, SNo, State) values(19, '05/10', '2016100304', '출석');";
            r[78]="insert into Attend(C_no, Date, SNo, State) values(19, '05/10', '2016100306', '출석');";
            r[79]="insert into Attend(C_no, Date, SNo, State) values(19, '05/10', '2016100318', '출석');"; //목 취업과 창업

            for(int i=0; i<80; i++){ db.execSQL(r[i]); }



            String [] q= new String[80];  //10주
            q[0]="insert into Attend(C_no, Date, SNo, State) values(20, '05/11', '2016100304', '출석');";
            q[1]="insert into Attend(C_no, Date, SNo, State) values(21, '05/11', '2016100304', '출석');";
            q[2]="insert into Attend(C_no, Date, SNo, State) values(20, '05/11', '2016100306', '출석');";
            q[3]="insert into Attend(C_no, Date, SNo, State) values(21, '05/11', '2016100306', '출석');";
            q[4]="insert into Attend(C_no, Date, SNo, State) values(20, '05/11', '2016100318', '출석');";
            q[5]="insert into Attend(C_no, Date, SNo, State) values(21, '05/11', '2016100318', '출석');"; //금 시스템분석및 설계

            q[6]="insert into Attend(C_no, Date, SNo, State) values(22, '05/11', '2016100274', '출석');";
            q[7]="insert into Attend(C_no, Date, SNo, State) values(23, '05/11', '2016100274', '출석');";
            q[8]="insert into Attend(C_no, Date, SNo, State) values(22, '05/11', '2016100304', '출석');";
            q[9]="insert into Attend(C_no, Date, SNo, State) values(23, '05/11', '2016100304', '출석');";
            q[10]="insert into Attend(C_no, Date, SNo, State) values(22, '05/11', '2016100306', '출석');";
            q[11]="insert into Attend(C_no, Date, SNo, State) values(23, '05/11', '2016100306', '출석');";
            q[12]="insert into Attend(C_no, Date, SNo, State) values(22, '05/11', '2016100318', '출석');";
            q[13]="insert into Attend(C_no, Date, SNo, State) values(23, '05/11', '2016100318', '출석');"; //금 서버관리

            q[14]="insert into Attend(C_no, Date, SNo, State) values(4, '05/14', '2016100274', '출석');";
            q[15]="insert into Attend(C_no, Date, SNo, State) values(5, '05/14', '2016100274', '출석');";
            q[16]="insert into Attend(C_no, Date, SNo, State) values(6, '05/14', '2016100274', '출석');";
            q[17]="insert into Attend(C_no, Date, SNo, State) values(4, '05/14', '2016100304', '출석');";
            q[18]="insert into Attend(C_no, Date, SNo, State) values(5, '05/14', '2016100304', '출석');";
            q[19]="insert into Attend(C_no, Date, SNo, State) values(6, '05/14', '2016100304', '출석');";
            q[20]="insert into Attend(C_no, Date, SNo, State) values(4, '05/14', '2016100306', '출석');";
            q[21]="insert into Attend(C_no, Date, SNo, State) values(5, '05/14', '2016100306', '출석');";
            q[22]="insert into Attend(C_no, Date, SNo, State) values(6, '05/14', '2016100306', '출석');";
            q[23]="insert into Attend(C_no, Date, SNo, State) values(4, '05/14', '2016100318', '출석');";
            q[24]="insert into Attend(C_no, Date, SNo, State) values(5, '05/14', '2016100318', '출석');";
            q[25]="insert into Attend(C_no, Date, SNo, State) values(6, '05/14', '2016100318', '출석');"; //월 프로젝트

            q[26]="insert into Attend(C_no, Date, SNo, State) values(1, '05/14', '2016100274', '출석');";
            q[27]="insert into Attend(C_no, Date, SNo, State) values(2, '05/14', '2016100274', '출석');";
            q[28]="insert into Attend(C_no, Date, SNo, State) values(3, '05/14', '2016100274', '출석');";
            q[29]="insert into Attend(C_no, Date, SNo, State) values(1, '05/14', '2016100304', '출석');";
            q[30]="insert into Attend(C_no, Date, SNo, State) values(2, '05/14', '2016100304', '출석');";
            q[31]="insert into Attend(C_no, Date, SNo, State) values(3, '05/14', '2016100304', '출석');";
            q[32]="insert into Attend(C_no, Date, SNo, State) values(1, '05/14', '2016100306', '출석');";
            q[33]="insert into Attend(C_no, Date, SNo, State) values(2, '05/14', '2016100306', '출석');";
            q[34]="insert into Attend(C_no, Date, SNo, State) values(3, '05/14', '2016100306', '출석');";
            q[35]="insert into Attend(C_no, Date, SNo, State) values(1, '05/14', '2016100318', '출석');";
            q[36]="insert into Attend(C_no, Date, SNo, State) values(2, '05/14', '2016100318', '출석');";
            q[37]="insert into Attend(C_no, Date, SNo, State) values(3, '05/14', '2016100318', '출석');"; //월 프레임워크

            q[38]="insert into Attend(C_no, Date, SNo, State) values(7, '05/15', '2016100304', '출석');";
            q[39]="insert into Attend(C_no, Date, SNo, State) values(8, '05/15', '2016100304', '출석');";
            q[40]="insert into Attend(C_no, Date, SNo, State) values(9, '05/15', '2016100304', '출석');";
            q[41]="insert into Attend(C_no, Date, SNo, State) values(7, '05/15', '2016100306', '출석');";
            q[42]="insert into Attend(C_no, Date, SNo, State) values(8, '05/15', '2016100306', '출석');";
            q[43]="insert into Attend(C_no, Date, SNo, State) values(9, '05/15', '2016100306', '출석');";
            q[44]="insert into Attend(C_no, Date, SNo, State) values(7, '05/15', '2016100318', '출석');";
            q[45]="insert into Attend(C_no, Date, SNo, State) values(8, '05/15', '2016100318', '출석');";
            q[46]="insert into Attend(C_no, Date, SNo, State) values(9, '05/15', '2016100318', '출석');"; //화 임베디드

            q[47]="insert into Attend(C_no, Date, SNo, State) values(10, '05/15', '2016100274', '출석');";
            q[48]="insert into Attend(C_no, Date, SNo, State) values(11, '05/15', '2016100274', '출석');";
            q[49]="insert into Attend(C_no, Date, SNo, State) values(10, '05/15', '2016100304', '출석');";
            q[50]="insert into Attend(C_no, Date, SNo, State) values(11, '05/15', '2016100304', '출석');";
            q[51]="insert into Attend(C_no, Date, SNo, State) values(10, '05/15', '2016100306', '출석');";
            q[52]="insert into Attend(C_no, Date, SNo, State) values(11, '05/15', '2016100306', '출석');";
            q[53]="insert into Attend(C_no, Date, SNo, State) values(10, '05/15', '2016100318', '출석');";
            q[54]="insert into Attend(C_no, Date, SNo, State) values(11, '05/15', '2016100318', '출석');"; //화 안드로이드

            q[55]="insert into Attend(C_no, Date, SNo, State) values(12, '05/16', '2016100274', '출석');";
            q[56]="insert into Attend(C_no, Date, SNo, State) values(13, '05/16', '2016100274', '출석');";
            q[57]="insert into Attend(C_no, Date, SNo, State) values(12, '05/16', '2016100304', '출석');";
            q[58]="insert into Attend(C_no, Date, SNo, State) values(13, '05/16', '2016100304', '출석');";
            q[59]="insert into Attend(C_no, Date, SNo, State) values(12, '05/16', '2016100306', '출석');";
            q[60]="insert into Attend(C_no, Date, SNo, State) values(13, '05/16', '2016100306', '출석');";
            q[61]="insert into Attend(C_no, Date, SNo, State) values(12, '05/16', '2016100318', '출석');";
            q[62]="insert into Attend(C_no, Date, SNo, State) values(13, '05/16', '2016100318', '출석');"; //수 안드로이드

            q[63]="insert into Attend(C_no, Date, SNo, State) values(14, '05/16', '2016100274', '출석');";
            q[64]="insert into Attend(C_no, Date, SNo, State) values(15, '05/16', '2016100274', '출석');";
            q[65]="insert into Attend(C_no, Date, SNo, State) values(14, '05/16', '2016100304', '출석');";
            q[66]="insert into Attend(C_no, Date, SNo, State) values(15, '05/16', '2016100304', '출석');";
            q[67]="insert into Attend(C_no, Date, SNo, State) values(14, '05/16', '2016100306', '출석');";
            q[68]="insert into Attend(C_no, Date, SNo, State) values(15, '05/16', '2016100306', '출석');";
            q[69]="insert into Attend(C_no, Date, SNo, State) values(14, '05/16', '2016100318', '출석');";
            q[70]="insert into Attend(C_no, Date, SNo, State) values(15, '05/16', '2016100318', '출석');"; //수 서버관리

            q[71]="insert into Attend(C_no, Date, SNo, State) values(16, '05/17', '2016100304', '출석');";
            q[72]="insert into Attend(C_no, Date, SNo, State) values(16, '05/17', '2016100306', '출석');";
            q[73]="insert into Attend(C_no, Date, SNo, State) values(16, '05/17', '2016100318', '출석');"; //목 시스템 분석 및 설계
            q[74]="insert into Attend(C_no, Date, SNo, State) values(17, '05/17', '2016100274', '출석');";
            q[75]="insert into Attend(C_no, Date, SNo, State) values(18, '05/17', '2016100274', '출석');"; //목 기업가정신과 창업

            q[76]="insert into Attend(C_no, Date, SNo, State) values(19, '05/17', '2016100274', '출석');";
            q[77]="insert into Attend(C_no, Date, SNo, State) values(19, '05/17', '2016100304', '출석');";
            q[78]="insert into Attend(C_no, Date, SNo, State) values(19, '05/17', '2016100306', '출석');";
            q[79]="insert into Attend(C_no, Date, SNo, State) values(19, '05/17', '2016100318', '출석');"; //목 취업과 창업

            for(int i=0; i<80; i++){ db.execSQL(q[i]); }


            String [] p= new String[80];  //10주
            p[0]="insert into Attend(C_no, Date, SNo, State) values(20, '05/18', '2016100304', '출석');";
            p[1]="insert into Attend(C_no, Date, SNo, State) values(21, '05/18', '2016100304', '출석');";
            p[2]="insert into Attend(C_no, Date, SNo, State) values(20, '05/18', '2016100306', '출석');";
            p[3]="insert into Attend(C_no, Date, SNo, State) values(21, '05/18', '2016100306', '출석');";
            p[4]="insert into Attend(C_no, Date, SNo, State) values(20, '05/18', '2016100318', '출석');";
            p[5]="insert into Attend(C_no, Date, SNo, State) values(21, '05/18', '2016100318', '출석');"; //금 시스템분석및 설계

            p[6]="insert into Attend(C_no, Date, SNo, State) values(22, '05/18', '2016100274', '출석');";
            p[7]="insert into Attend(C_no, Date, SNo, State) values(23, '05/18', '2016100274', '출석');";
            p[8]="insert into Attend(C_no, Date, SNo, State) values(22, '05/18', '2016100304', '출석');";
            p[9]="insert into Attend(C_no, Date, SNo, State) values(23, '05/18', '2016100304', '출석');";
            p[10]="insert into Attend(C_no, Date, SNo, State) values(22, '05/18', '2016100306', '출석');";
            p[11]="insert into Attend(C_no, Date, SNo, State) values(23, '05/18', '2016100306', '출석');";
            p[12]="insert into Attend(C_no, Date, SNo, State) values(22, '05/18', '2016100318', '출석');";
            p[13]="insert into Attend(C_no, Date, SNo, State) values(23, '05/18', '2016100318', '출석');"; //금 서버관리

            p[14]="insert into Attend(C_no, Date, SNo, State) values(4, '05/21', '2016100274', '출석');";
            p[15]="insert into Attend(C_no, Date, SNo, State) values(5, '05/21', '2016100274', '출석');";
            p[16]="insert into Attend(C_no, Date, SNo, State) values(6, '05/21', '2016100274', '출석');";
            p[17]="insert into Attend(C_no, Date, SNo, State) values(4, '05/21', '2016100304', '출석');";
            p[18]="insert into Attend(C_no, Date, SNo, State) values(5, '05/21', '2016100304', '출석');";
            p[19]="insert into Attend(C_no, Date, SNo, State) values(6, '05/21', '2016100304', '출석');";
            p[20]="insert into Attend(C_no, Date, SNo, State) values(4, '05/21', '2016100306', '출석');";
            p[21]="insert into Attend(C_no, Date, SNo, State) values(5, '05/21', '2016100306', '출석');";
            p[22]="insert into Attend(C_no, Date, SNo, State) values(6, '05/21', '2016100306', '출석');";
            p[23]="insert into Attend(C_no, Date, SNo, State) values(4, '05/21', '2016100318', '출석');";
            p[24]="insert into Attend(C_no, Date, SNo, State) values(5, '05/21', '2016100318', '출석');";
            p[25]="insert into Attend(C_no, Date, SNo, State) values(6, '05/21', '2016100318', '출석');"; //월 프로젝트

            p[26]="insert into Attend(C_no, Date, SNo, State) values(1, '05/21', '2016100274', '출석');";
            p[27]="insert into Attend(C_no, Date, SNo, State) values(2, '05/21', '2016100274', '출석');";
            p[28]="insert into Attend(C_no, Date, SNo, State) values(3, '05/21', '2016100274', '출석');";
            p[29]="insert into Attend(C_no, Date, SNo, State) values(1, '05/21', '2016100304', '출석');";
            p[30]="insert into Attend(C_no, Date, SNo, State) values(2, '05/21', '2016100304', '출석');";
            p[31]="insert into Attend(C_no, Date, SNo, State) values(3, '05/21', '2016100304', '출석');";
            p[32]="insert into Attend(C_no, Date, SNo, State) values(1, '05/21', '2016100306', '출석');";
            p[33]="insert into Attend(C_no, Date, SNo, State) values(2, '05/21', '2016100306', '출석');";
            p[34]="insert into Attend(C_no, Date, SNo, State) values(3, '05/21', '2016100306', '출석');";
            p[35]="insert into Attend(C_no, Date, SNo, State) values(1, '05/21', '2016100318', '출석');";
            p[36]="insert into Attend(C_no, Date, SNo, State) values(2, '05/21', '2016100318', '출석');";
            p[37]="insert into Attend(C_no, Date, SNo, State) values(3, '05/21', '2016100318', '출석');"; //월 프레임워크

            p[38]="insert into Attend(C_no, Date, SNo, State) values(7, '05/22', '2016100304', '출석');";
            p[39]="insert into Attend(C_no, Date, SNo, State) values(8, '05/22', '2016100304', '출석');";
            p[40]="insert into Attend(C_no, Date, SNo, State) values(9, '05/22', '2016100304', '출석');";
            p[41]="insert into Attend(C_no, Date, SNo, State) values(7, '05/22', '2016100306', '출석');";
            p[42]="insert into Attend(C_no, Date, SNo, State) values(8, '05/22', '2016100306', '출석');";
            p[43]="insert into Attend(C_no, Date, SNo, State) values(9, '05/22', '2016100306', '출석');";
            p[44]="insert into Attend(C_no, Date, SNo, State) values(7, '05/22', '2016100318', '출석');";
            p[45]="insert into Attend(C_no, Date, SNo, State) values(8, '05/22', '2016100318', '출석');";
            p[46]="insert into Attend(C_no, Date, SNo, State) values(9, '05/22', '2016100318', '출석');"; //화 임베디드

            p[47]="insert into Attend(C_no, Date, SNo, State) values(10, '05/22', '2016100274', '출석');";
            p[48]="insert into Attend(C_no, Date, SNo, State) values(11, '05/22', '2016100274', '출석');";
            p[49]="insert into Attend(C_no, Date, SNo, State) values(10, '05/22', '2016100304', '출석');";
            p[50]="insert into Attend(C_no, Date, SNo, State) values(11, '05/22', '2016100304', '출석');";
            p[51]="insert into Attend(C_no, Date, SNo, State) values(10, '05/22', '2016100306', '출석');";
            p[52]="insert into Attend(C_no, Date, SNo, State) values(11, '05/22', '2016100306', '출석');";
            p[53]="insert into Attend(C_no, Date, SNo, State) values(10, '05/22', '2016100318', '출석');";
            p[54]="insert into Attend(C_no, Date, SNo, State) values(11, '05/22', '2016100318', '출석');"; //화 안드로이드

            p[55]="insert into Attend(C_no, Date, SNo, State) values(12, '05/23', '2016100274', '출석');";
            p[56]="insert into Attend(C_no, Date, SNo, State) values(13, '05/23', '2016100274', '출석');";
            p[57]="insert into Attend(C_no, Date, SNo, State) values(12, '05/23', '2016100304', '출석');";
            p[58]="insert into Attend(C_no, Date, SNo, State) values(13, '05/23', '2016100304', '출석');";
            p[59]="insert into Attend(C_no, Date, SNo, State) values(12, '05/23', '2016100306', '출석');";
            p[60]="insert into Attend(C_no, Date, SNo, State) values(13, '05/23', '2016100306', '출석');";
            p[61]="insert into Attend(C_no, Date, SNo, State) values(12, '05/23', '2016100318', '출석');";
            p[62]="insert into Attend(C_no, Date, SNo, State) values(13, '05/23', '2016100318', '출석');"; //수 안드로이드

            p[63]="insert into Attend(C_no, Date, SNo, State) values(14, '05/23', '2016100274', '출석');";
            p[64]="insert into Attend(C_no, Date, SNo, State) values(15, '05/23', '2016100274', '출석');";
            p[65]="insert into Attend(C_no, Date, SNo, State) values(14, '05/23', '2016100304', '출석');";
            p[66]="insert into Attend(C_no, Date, SNo, State) values(15, '05/23', '2016100304', '출석');";
            p[67]="insert into Attend(C_no, Date, SNo, State) values(14, '05/23', '2016100306', '출석');";
            p[68]="insert into Attend(C_no, Date, SNo, State) values(15, '05/23', '2016100306', '출석');";
            p[69]="insert into Attend(C_no, Date, SNo, State) values(14, '05/23', '2016100318', '출석');";
            p[70]="insert into Attend(C_no, Date, SNo, State) values(15, '05/23', '2016100318', '출석');"; //수 서버관리

            p[71]="insert into Attend(C_no, Date, SNo, State) values(16, '05/24', '2016100304', '출석');";
            p[72]="insert into Attend(C_no, Date, SNo, State) values(16, '05/24', '2016100306', '출석');";
            p[73]="insert into Attend(C_no, Date, SNo, State) values(16, '05/24', '2016100318', '출석');"; //목 시스템 분석 및 설계
            p[74]="insert into Attend(C_no, Date, SNo, State) values(17, '05/24', '2016100274', '출석');";
            p[75]="insert into Attend(C_no, Date, SNo, State) values(18, '05/24', '2016100274', '출석');"; //목 기업가정신과 창업

            p[76]="insert into Attend(C_no, Date, SNo, State) values(19, '05/24', '2016100274', '출석');";
            p[77]="insert into Attend(C_no, Date, SNo, State) values(19, '05/24', '2016100304', '출석');";
            p[78]="insert into Attend(C_no, Date, SNo, State) values(19, '05/24', '2016100306', '출석');";
            p[79]="insert into Attend(C_no, Date, SNo, State) values(19, '05/24', '2016100318', '출석');"; //목 취업과 창업

            for(int i=0; i<80; i++){ db.execSQL(p[i]); }


            String [] o= new String[80];  //10주
            o[0]="insert into Attend(C_no, Date, SNo, State) values(20, '05/25', '2016100304', '출석');";
            o[1]="insert into Attend(C_no, Date, SNo, State) values(21, '05/25', '2016100304', '출석');";
            o[2]="insert into Attend(C_no, Date, SNo, State) values(20, '05/25', '2016100306', '출석');";
            o[3]="insert into Attend(C_no, Date, SNo, State) values(21, '05/25', '2016100306', '출석');";
            o[4]="insert into Attend(C_no, Date, SNo, State) values(20, '05/25', '2016100318', '출석');";
            o[5]="insert into Attend(C_no, Date, SNo, State) values(21, '05/25', '2016100318', '출석');"; //금 시스템분석및 설계

            o[6]="insert into Attend(C_no, Date, SNo, State) values(22, '05/25', '2016100274', '출석');";
            o[7]="insert into Attend(C_no, Date, SNo, State) values(23, '05/25', '2016100274', '출석');";
            o[8]="insert into Attend(C_no, Date, SNo, State) values(22, '05/25', '2016100304', '출석');";
            o[9]="insert into Attend(C_no, Date, SNo, State) values(23, '05/25', '2016100304', '출석');";
            o[10]="insert into Attend(C_no, Date, SNo, State) values(22, '05/25', '2016100306', '출석');";
            o[11]="insert into Attend(C_no, Date, SNo, State) values(23, '05/25', '2016100306', '출석');";
            o[12]="insert into Attend(C_no, Date, SNo, State) values(22, '05/25', '2016100318', '출석');";
            o[13]="insert into Attend(C_no, Date, SNo, State) values(23, '05/25', '2016100318', '출석');"; //금 서버관리

            o[14]="insert into Attend(C_no, Date, SNo, State) values(4, '05/28', '2016100274', '출석');";
            o[15]="insert into Attend(C_no, Date, SNo, State) values(5, '05/28', '2016100274', '출석');";
            o[16]="insert into Attend(C_no, Date, SNo, State) values(6, '05/28', '2016100274', '출석');";
            o[17]="insert into Attend(C_no, Date, SNo, State) values(4, '05/28', '2016100304', '출석');";
            o[18]="insert into Attend(C_no, Date, SNo, State) values(5, '05/28', '2016100304', '출석');";
            o[19]="insert into Attend(C_no, Date, SNo, State) values(6, '05/28', '2016100304', '출석');";
            o[20]="insert into Attend(C_no, Date, SNo, State) values(4, '05/28', '2016100306', '출석');";
            o[21]="insert into Attend(C_no, Date, SNo, State) values(5, '05/28', '2016100306', '출석');";
            o[22]="insert into Attend(C_no, Date, SNo, State) values(6, '05/28', '2016100306', '출석');";
            o[23]="insert into Attend(C_no, Date, SNo, State) values(4, '05/28', '2016100318', '출석');";
            o[24]="insert into Attend(C_no, Date, SNo, State) values(5, '05/28', '2016100318', '출석');";
            o[25]="insert into Attend(C_no, Date, SNo, State) values(6, '05/28', '2016100318', '출석');"; //월 프로젝트

            o[26]="insert into Attend(C_no, Date, SNo, State) values(1, '05/28', '2016100274', '출석');";
            o[27]="insert into Attend(C_no, Date, SNo, State) values(2, '05/28', '2016100274', '출석');";
            o[28]="insert into Attend(C_no, Date, SNo, State) values(3, '05/28', '2016100274', '출석');";
            o[29]="insert into Attend(C_no, Date, SNo, State) values(1, '05/28', '2016100304', '출석');";
            o[30]="insert into Attend(C_no, Date, SNo, State) values(2, '05/28', '2016100304', '출석');";
            o[31]="insert into Attend(C_no, Date, SNo, State) values(3, '05/28', '2016100304', '출석');";
            o[32]="insert into Attend(C_no, Date, SNo, State) values(1, '05/28', '2016100306', '출석');";
            o[33]="insert into Attend(C_no, Date, SNo, State) values(2, '05/28', '2016100306', '출석');";
            o[34]="insert into Attend(C_no, Date, SNo, State) values(3, '05/28', '2016100306', '출석');";
            o[35]="insert into Attend(C_no, Date, SNo, State) values(1, '05/28', '2016100318', '출석');";
            o[36]="insert into Attend(C_no, Date, SNo, State) values(2, '05/28', '2016100318', '출석');";
            o[37]="insert into Attend(C_no, Date, SNo, State) values(3, '05/28', '2016100318', '출석');"; //월 프레임워크

            o[38]="insert into Attend(C_no, Date, SNo, State) values(7, '05/29', '2016100304', '출석');";
            o[39]="insert into Attend(C_no, Date, SNo, State) values(8, '05/29', '2016100304', '출석');";
            o[40]="insert into Attend(C_no, Date, SNo, State) values(9, '05/29', '2016100304', '출석');";
            o[41]="insert into Attend(C_no, Date, SNo, State) values(7, '05/29', '2016100306', '출석');";
            o[42]="insert into Attend(C_no, Date, SNo, State) values(8, '05/29', '2016100306', '출석');";
            o[43]="insert into Attend(C_no, Date, SNo, State) values(9, '05/29', '2016100306', '출석');";
            o[44]="insert into Attend(C_no, Date, SNo, State) values(7, '05/29', '2016100318', '출석');";
            o[45]="insert into Attend(C_no, Date, SNo, State) values(8, '05/29', '2016100318', '출석');";
            o[46]="insert into Attend(C_no, Date, SNo, State) values(9, '05/29', '2016100318', '출석');"; //화 임베디드

            o[47]="insert into Attend(C_no, Date, SNo, State) values(10, '05/29', '2016100274', '출석');";
            o[48]="insert into Attend(C_no, Date, SNo, State) values(11, '05/29', '2016100274', '출석');";
            o[49]="insert into Attend(C_no, Date, SNo, State) values(10, '05/29', '2016100304', '출석');";
            o[50]="insert into Attend(C_no, Date, SNo, State) values(11, '05/29', '2016100304', '출석');";
            o[51]="insert into Attend(C_no, Date, SNo, State) values(10, '05/29', '2016100306', '출석');";
            o[52]="insert into Attend(C_no, Date, SNo, State) values(11, '05/29', '2016100306', '출석');";
            o[53]="insert into Attend(C_no, Date, SNo, State) values(10, '05/29', '2016100318', '출석');";
            o[54]="insert into Attend(C_no, Date, SNo, State) values(11, '05/29', '2016100318', '출석');"; //화 안드로이드

            o[55]="insert into Attend(C_no, Date, SNo, State) values(12, '05/30', '2016100274', '출석');";
            o[56]="insert into Attend(C_no, Date, SNo, State) values(13, '05/30', '2016100274', '출석');";
            o[57]="insert into Attend(C_no, Date, SNo, State) values(12, '05/30', '2016100304', '출석');";
            o[58]="insert into Attend(C_no, Date, SNo, State) values(13, '05/30', '2016100304', '출석');";
            o[59]="insert into Attend(C_no, Date, SNo, State) values(12, '05/30', '2016100306', '출석');";
            o[60]="insert into Attend(C_no, Date, SNo, State) values(13, '05/30', '2016100306', '출석');";
            o[61]="insert into Attend(C_no, Date, SNo, State) values(12, '05/30', '2016100318', '출석');";
            o[62]="insert into Attend(C_no, Date, SNo, State) values(13, '05/30', '2016100318', '출석');"; //수 안드로이드

            o[63]="insert into Attend(C_no, Date, SNo, State) values(14, '05/30', '2016100274', '출석');";
            o[64]="insert into Attend(C_no, Date, SNo, State) values(15, '05/30', '2016100274', '출석');";
            o[65]="insert into Attend(C_no, Date, SNo, State) values(14, '05/30', '2016100304', '출석');";
            o[66]="insert into Attend(C_no, Date, SNo, State) values(15, '05/30', '2016100304', '출석');";
            o[67]="insert into Attend(C_no, Date, SNo, State) values(14, '05/30', '2016100306', '출석');";
            o[68]="insert into Attend(C_no, Date, SNo, State) values(15, '05/30', '2016100306', '출석');";
            o[69]="insert into Attend(C_no, Date, SNo, State) values(14, '05/30', '2016100318', '출석');";
            o[70]="insert into Attend(C_no, Date, SNo, State) values(15, '05/30', '2016100318', '출석');"; //수 서버관리

            o[71]="insert into Attend(C_no, Date, SNo, State) values(16, '05/31', '2016100304', '출석');";
            o[72]="insert into Attend(C_no, Date, SNo, State) values(16, '05/31', '2016100306', '출석');";
            o[73]="insert into Attend(C_no, Date, SNo, State) values(16, '05/31', '2016100318', '출석');"; //목 시스템 분석 및 설계
            o[74]="insert into Attend(C_no, Date, SNo, State) values(17, '05/31', '2016100274', '출석');";
            o[75]="insert into Attend(C_no, Date, SNo, State) values(18, '05/31', '2016100274', '출석');"; //목 기업가정신과 창업
            o[76]="insert into Attend(C_no, Date, SNo, State) values(19, '05/31', '2016100274', '출석');";
            o[77]="insert into Attend(C_no, Date, SNo, State) values(19, '05/31', '2016100304', '출석');";
            o[78]="insert into Attend(C_no, Date, SNo, State) values(19, '05/31', '2016100306', '출석');";
            o[79]="insert into Attend(C_no, Date, SNo, State) values(19, '05/31', '2016100318', '출석');"; //목 취업과 창업

            for(int i=0; i<80; i++){ db.execSQL(o[i]); }

            String [] n= new String[80];  //10주
            n[0]="insert into Attend(C_no, Date, SNo, State) values(20, '06/01', '2016100304', '출석');";
            n[1]="insert into Attend(C_no, Date, SNo, State) values(21, '06/01', '2016100304', '출석');";
            n[2]="insert into Attend(C_no, Date, SNo, State) values(20, '06/01', '2016100306', '출석');";
            n[3]="insert into Attend(C_no, Date, SNo, State) values(21, '06/01', '2016100306', '출석');";
            n[4]="insert into Attend(C_no, Date, SNo, State) values(20, '06/01', '2016100318', '출석');";
            n[5]="insert into Attend(C_no, Date, SNo, State) values(21, '06/01', '2016100318', '출석');"; //금 시스템분석및 설계

            n[6]="insert into Attend(C_no, Date, SNo, State) values(22, '06/01', '2016100274', '출석');";
            n[7]="insert into Attend(C_no, Date, SNo, State) values(23, '06/01', '2016100274', '출석');";
            n[8]="insert into Attend(C_no, Date, SNo, State) values(22, '06/01', '2016100304', '출석');";
            n[9]="insert into Attend(C_no, Date, SNo, State) values(23, '06/01', '2016100304', '출석');";
            n[10]="insert into Attend(C_no, Date, SNo, State) values(22, '06/01', '2016100306', '출석');";
            n[11]="insert into Attend(C_no, Date, SNo, State) values(23, '06/01', '2016100306', '출석');";
            n[12]="insert into Attend(C_no, Date, SNo, State) values(22, '06/01', '2016100318', '출석');";
            n[13]="insert into Attend(C_no, Date, SNo, State) values(23, '06/01', '2016100318', '출석');"; //금 서버관리

            n[14]="insert into Attend(C_no, Date, SNo, State) values(4, '06/04', '2016100274', '출석');";
            n[15]="insert into Attend(C_no, Date, SNo, State) values(5, '06/04', '2016100274', '출석');";
            n[16]="insert into Attend(C_no, Date, SNo, State) values(6, '06/04', '2016100274', '출석');";
            n[17]="insert into Attend(C_no, Date, SNo, State) values(4, '06/04', '2016100304', '출석');";
            n[18]="insert into Attend(C_no, Date, SNo, State) values(5, '06/04', '2016100304', '출석');";
            n[19]="insert into Attend(C_no, Date, SNo, State) values(6, '06/04', '2016100304', '출석');";
            n[20]="insert into Attend(C_no, Date, SNo, State) values(4, '06/04', '2016100306', '출석');";
            n[21]="insert into Attend(C_no, Date, SNo, State) values(5, '06/04', '2016100306', '출석');";
            n[22]="insert into Attend(C_no, Date, SNo, State) values(6, '06/04', '2016100306', '출석');";
            n[23]="insert into Attend(C_no, Date, SNo, State) values(4, '06/04', '2016100318', '출석');";
            n[24]="insert into Attend(C_no, Date, SNo, State) values(5, '06/04', '2016100318', '출석');";
            n[25]="insert into Attend(C_no, Date, SNo, State) values(6, '06/04', '2016100318', '출석');"; //월 프로젝트

            n[26]="insert into Attend(C_no, Date, SNo, State) values(1, '06/04', '2016100274', '출석');";
            n[27]="insert into Attend(C_no, Date, SNo, State) values(2, '06/04', '2016100274', '출석');";
            n[28]="insert into Attend(C_no, Date, SNo, State) values(3, '06/04', '2016100274', '출석');";
            n[29]="insert into Attend(C_no, Date, SNo, State) values(1, '06/04', '2016100304', '출석');";
            n[30]="insert into Attend(C_no, Date, SNo, State) values(2, '06/04', '2016100304', '출석');";
            n[31]="insert into Attend(C_no, Date, SNo, State) values(3, '06/04', '2016100304', '출석');";
            n[32]="insert into Attend(C_no, Date, SNo, State) values(1, '06/04', '2016100306', '출석');";
            n[33]="insert into Attend(C_no, Date, SNo, State) values(2, '06/04', '2016100306', '출석');";
            n[34]="insert into Attend(C_no, Date, SNo, State) values(3, '06/04', '2016100306', '출석');";
            n[35]="insert into Attend(C_no, Date, SNo, State) values(1, '06/04', '2016100318', '출석');";
            n[36]="insert into Attend(C_no, Date, SNo, State) values(2, '06/04', '2016100318', '출석');";
            n[37]="insert into Attend(C_no, Date, SNo, State) values(3, '06/04', '2016100318', '출석');"; //월 프레임워크

            n[38]="insert into Attend(C_no, Date, SNo, State) values(7, '06/05', '2016100304', '미처리');";
            n[39]="insert into Attend(C_no, Date, SNo, State) values(8, '06/05', '2016100304', '미처리');";
            n[40]="insert into Attend(C_no, Date, SNo, State) values(9, '06/05', '2016100304', '미처리');";
            n[41]="insert into Attend(C_no, Date, SNo, State) values(7, '06/05', '2016100306', '미처리');";
            n[42]="insert into Attend(C_no, Date, SNo, State) values(8, '06/05', '2016100306', '미처리');";
            n[43]="insert into Attend(C_no, Date, SNo, State) values(9, '06/05', '2016100306', '미처리');";
            n[44]="insert into Attend(C_no, Date, SNo, State) values(7, '06/05', '2016100318', '미처리');";
            n[45]="insert into Attend(C_no, Date, SNo, State) values(8, '06/05', '2016100318', '미처리');";
            n[46]="insert into Attend(C_no, Date, SNo, State) values(9, '06/05', '2016100318', '미처리');"; //화 임베디드

            n[47]="insert into Attend(C_no, Date, SNo, State) values(10, '06/05', '2016100274', '미처리');";
            n[48]="insert into Attend(C_no, Date, SNo, State) values(11, '06/05', '2016100274', '미처리');";
            n[49]="insert into Attend(C_no, Date, SNo, State) values(10, '06/05', '2016100304', '미처리');";
            n[50]="insert into Attend(C_no, Date, SNo, State) values(11, '06/05', '2016100304', '미처리');";
            n[51]="insert into Attend(C_no, Date, SNo, State) values(10, '06/05', '2016100306', '미처리');";
            n[52]="insert into Attend(C_no, Date, SNo, State) values(11, '06/05', '2016100306', '미처리');";
            n[53]="insert into Attend(C_no, Date, SNo, State) values(10, '06/05', '2016100318', '미처리');";
            n[54]="insert into Attend(C_no, Date, SNo, State) values(11, '06/05', '2016100318', '미처리');"; //화 안드로이드

            n[55]="insert into Attend(C_no, Date, SNo, State) values(12, '06/06', '2016100274', '미처리');";
            n[56]="insert into Attend(C_no, Date, SNo, State) values(13, '06/06', '2016100274', '미처리');";
            n[57]="insert into Attend(C_no, Date, SNo, State) values(12, '06/06', '2016100304', '미처리');";
            n[58]="insert into Attend(C_no, Date, SNo, State) values(13, '06/06', '2016100304', '미처리');";
            n[59]="insert into Attend(C_no, Date, SNo, State) values(12, '06/06', '2016100306', '미처리');";
            n[60]="insert into Attend(C_no, Date, SNo, State) values(13, '06/06', '2016100306', '미처리');";
            n[61]="insert into Attend(C_no, Date, SNo, State) values(12, '06/06', '2016100318', '미처리');";
            n[62]="insert into Attend(C_no, Date, SNo, State) values(13, '06/06', '2016100318', '미처리');"; //수 안드로이드

            n[63]="insert into Attend(C_no, Date, SNo, State) values(14, '06/06', '2016100274', '미처리');";
            n[64]="insert into Attend(C_no, Date, SNo, State) values(15, '06/06', '2016100274', '미처리');";
            n[65]="insert into Attend(C_no, Date, SNo, State) values(14, '06/06', '2016100304', '미처리');";
            n[66]="insert into Attend(C_no, Date, SNo, State) values(15, '06/06', '2016100304', '미처리');";
            n[67]="insert into Attend(C_no, Date, SNo, State) values(14, '06/06', '2016100306', '미처리');";
            n[68]="insert into Attend(C_no, Date, SNo, State) values(15, '06/06', '2016100306', '미처리');";
            n[69]="insert into Attend(C_no, Date, SNo, State) values(14, '06/06', '2016100318', '미처리');";
            n[70]="insert into Attend(C_no, Date, SNo, State) values(15, '06/06', '2016100318', '미처리');"; //수 서버관리

            n[71]="insert into Attend(C_no, Date, SNo, State) values(16, '06/07', '2016100304', '미처리');";
            n[72]="insert into Attend(C_no, Date, SNo, State) values(16, '06/07', '2016100306', '미처리');";
            n[73]="insert into Attend(C_no, Date, SNo, State) values(16, '06/07', '2016100318', '미처리');"; //목 시스템 분석 및 설계
            n[74]="insert into Attend(C_no, Date, SNo, State) values(17, '06/07', '2016100274', '미처리');";
            n[75]="insert into Attend(C_no, Date, SNo, State) values(18, '06/07', '2016100274', '미처리');"; //목 기업가정신과 창업
            n[76]="insert into Attend(C_no, Date, SNo, State) values(19, '06/07', '2016100274', '미처리');";
            n[77]="insert into Attend(C_no, Date, SNo, State) values(19, '06/07', '2016100304', '미처리');";
            n[78]="insert into Attend(C_no, Date, SNo, State) values(19, '06/07', '2016100306', '미처리');";
            n[79]="insert into Attend(C_no, Date, SNo, State) values(19, '06/07', '2016100318', '미처리');"; //목 취업과 창업

            for(int i=0; i<80; i++){ db.execSQL(n[i]); }


            String [] m= new String[80];  //10주
            m[0]="insert into Attend(C_no, Date, SNo, State) values(20, '06/08', '2016100304', '미처리');";
            m[1]="insert into Attend(C_no, Date, SNo, State) values(21, '06/08', '2016100304', '미처리');";
            m[2]="insert into Attend(C_no, Date, SNo, State) values(20, '06/08', '2016100306', '미처리');";
            m[3]="insert into Attend(C_no, Date, SNo, State) values(21, '06/08', '2016100306', '미처리');";
            m[4]="insert into Attend(C_no, Date, SNo, State) values(20, '06/08', '2016100318', '미처리');";
            m[5]="insert into Attend(C_no, Date, SNo, State) values(21, '06/08', '2016100318', '미처리');"; //금 시스템분석및 설계

            m[6]="insert into Attend(C_no, Date, SNo, State) values(22, '06/08', '2016100274', '미처리');";
            m[7]="insert into Attend(C_no, Date, SNo, State) values(23, '06/08', '2016100274', '미처리');";
            m[8]="insert into Attend(C_no, Date, SNo, State) values(22, '06/08', '2016100304', '미처리');";
            m[9]="insert into Attend(C_no, Date, SNo, State) values(23, '06/08', '2016100304', '미처리');";
            m[10]="insert into Attend(C_no, Date, SNo, State) values(22, '06/08', '2016100306', '미처리');";
            m[11]="insert into Attend(C_no, Date, SNo, State) values(23, '06/08', '2016100306', '미처리');";
            m[12]="insert into Attend(C_no, Date, SNo, State) values(22, '06/08', '2016100318', '미처리');";
            m[13]="insert into Attend(C_no, Date, SNo, State) values(23, '06/08', '2016100318', '미처리');"; //금 서버관리

            m[14]="insert into Attend(C_no, Date, SNo, State) values(4, '06/11', '2016100274', '미처리');";
            m[15]="insert into Attend(C_no, Date, SNo, State) values(5, '06/11', '2016100274', '미처리');";
            m[16]="insert into Attend(C_no, Date, SNo, State) values(6, '06/11', '2016100274', '미처리');";
            m[17]="insert into Attend(C_no, Date, SNo, State) values(4, '06/11', '2016100304', '미처리');";
            m[18]="insert into Attend(C_no, Date, SNo, State) values(5, '06/11', '2016100304', '미처리');";
            m[19]="insert into Attend(C_no, Date, SNo, State) values(6, '06/11', '2016100304', '미처리');";
            m[20]="insert into Attend(C_no, Date, SNo, State) values(4, '06/11', '2016100306', '미처리');";
            m[21]="insert into Attend(C_no, Date, SNo, State) values(5, '06/11', '2016100306', '미처리');";
            m[22]="insert into Attend(C_no, Date, SNo, State) values(6, '06/11', '2016100306', '미처리');";
            m[23]="insert into Attend(C_no, Date, SNo, State) values(4, '06/11', '2016100318', '미처리');";
            m[24]="insert into Attend(C_no, Date, SNo, State) values(5, '06/11', '2016100318', '미처리');";
            m[25]="insert into Attend(C_no, Date, SNo, State) values(6, '06/11', '2016100318', '미처리');"; //월 프로젝트

            m[26]="insert into Attend(C_no, Date, SNo, State) values(1, '06/11', '2016100274', '미처리');";
            m[27]="insert into Attend(C_no, Date, SNo, State) values(2, '06/11', '2016100274', '미처리');";
            m[28]="insert into Attend(C_no, Date, SNo, State) values(3, '06/11', '2016100274', '미처리');";
            m[29]="insert into Attend(C_no, Date, SNo, State) values(1, '06/11', '2016100304', '미처리');";
            m[30]="insert into Attend(C_no, Date, SNo, State) values(2, '06/11', '2016100304', '미처리');";
            m[31]="insert into Attend(C_no, Date, SNo, State) values(3, '06/11', '2016100304', '미처리');";
            m[32]="insert into Attend(C_no, Date, SNo, State) values(1, '06/11', '2016100306', '미처리');";
            m[33]="insert into Attend(C_no, Date, SNo, State) values(2, '06/11', '2016100306', '미처리');";
            m[34]="insert into Attend(C_no, Date, SNo, State) values(3, '06/11', '2016100306', '미처리');";
            m[35]="insert into Attend(C_no, Date, SNo, State) values(1, '06/11', '2016100318', '미처리');";
            m[36]="insert into Attend(C_no, Date, SNo, State) values(2, '06/11', '2016100318', '미처리');";
            m[37]="insert into Attend(C_no, Date, SNo, State) values(3, '06/11', '2016100318', '미처리');"; //월 프레임워크

            m[38]="insert into Attend(C_no, Date, SNo, State) values(7, '06/12', '2016100304', '미처리');";
            m[39]="insert into Attend(C_no, Date, SNo, State) values(8, '06/12', '2016100304', '미처리');";
            m[40]="insert into Attend(C_no, Date, SNo, State) values(9, '06/12', '2016100304', '미처리');";
            m[41]="insert into Attend(C_no, Date, SNo, State) values(7, '06/12', '2016100306', '미처리');";
            m[42]="insert into Attend(C_no, Date, SNo, State) values(8, '06/12', '2016100306', '미처리');";
            m[43]="insert into Attend(C_no, Date, SNo, State) values(9, '06/12', '2016100306', '미처리');";
            m[44]="insert into Attend(C_no, Date, SNo, State) values(7, '06/12', '2016100318', '미처리');";
            m[45]="insert into Attend(C_no, Date, SNo, State) values(8, '06/12', '2016100318', '미처리');";
            m[46]="insert into Attend(C_no, Date, SNo, State) values(9, '06/12', '2016100318', '미처리');"; //화 임베디드

            m[47]="insert into Attend(C_no, Date, SNo, State) values(10, '06/12', '2016100274', '미처리');";
            m[48]="insert into Attend(C_no, Date, SNo, State) values(11, '06/12', '2016100274', '미처리');";
            m[49]="insert into Attend(C_no, Date, SNo, State) values(10, '06/12', '2016100304', '미처리');";
            m[50]="insert into Attend(C_no, Date, SNo, State) values(11, '06/12', '2016100304', '미처리');";
            m[51]="insert into Attend(C_no, Date, SNo, State) values(10, '06/12', '2016100306', '미처리');";
            m[52]="insert into Attend(C_no, Date, SNo, State) values(11, '06/12', '2016100306', '미처리');";
            m[53]="insert into Attend(C_no, Date, SNo, State) values(10, '06/12', '2016100318', '미처리');";
            m[54]="insert into Attend(C_no, Date, SNo, State) values(11, '06/12', '2016100318', '미처리');"; //화 안드로이드

            m[55]="insert into Attend(C_no, Date, SNo, State) values(12, '06/13', '2016100274', '미처리');";
            m[56]="insert into Attend(C_no, Date, SNo, State) values(13, '06/13', '2016100274', '미처리');";
            m[57]="insert into Attend(C_no, Date, SNo, State) values(12, '06/13', '2016100304', '미처리');";
            m[58]="insert into Attend(C_no, Date, SNo, State) values(13, '06/13', '2016100304', '미처리');";
            m[59]="insert into Attend(C_no, Date, SNo, State) values(12, '06/13', '2016100306', '미처리');";
            m[60]="insert into Attend(C_no, Date, SNo, State) values(13, '06/13', '2016100306', '미처리');";
            m[61]="insert into Attend(C_no, Date, SNo, State) values(12, '06/13', '2016100318', '미처리');";
            m[62]="insert into Attend(C_no, Date, SNo, State) values(13, '06/13', '2016100318', '미처리');"; //수 안드로이드

            m[63]="insert into Attend(C_no, Date, SNo, State) values(14, '06/13', '2016100274', '미처리');";
            m[64]="insert into Attend(C_no, Date, SNo, State) values(15, '06/13', '2016100274', '미처리');";
            m[65]="insert into Attend(C_no, Date, SNo, State) values(14, '06/13', '2016100304', '미처리');";
            m[66]="insert into Attend(C_no, Date, SNo, State) values(15, '06/13', '2016100304', '미처리');";
            m[67]="insert into Attend(C_no, Date, SNo, State) values(14, '06/13', '2016100306', '미처리');";
            m[68]="insert into Attend(C_no, Date, SNo, State) values(15, '06/13', '2016100306', '미처리');";
            m[69]="insert into Attend(C_no, Date, SNo, State) values(14, '06/13', '2016100318', '미처리');";
            m[70]="insert into Attend(C_no, Date, SNo, State) values(15, '06/13', '2016100318', '미처리');"; //수 서버관리

            m[71]="insert into Attend(C_no, Date, SNo, State) values(16, '06/14', '2016100304', '미처리');";
            m[72]="insert into Attend(C_no, Date, SNo, State) values(16, '06/14', '2016100306', '미처리');";
            m[73]="insert into Attend(C_no, Date, SNo, State) values(16, '06/14', '2016100318', '미처리');"; //목 시스템 분석 및 설계
            m[74]="insert into Attend(C_no, Date, SNo, State) values(17, '06/14', '2016100274', '미처리');";
            m[75]="insert into Attend(C_no, Date, SNo, State) values(18, '06/14', '2016100274', '미처리');"; //목 기업가정신과 창업
            m[76]="insert into Attend(C_no, Date, SNo, State) values(19, '06/14', '2016100274', '미처리');";
            m[77]="insert into Attend(C_no, Date, SNo, State) values(19, '06/14', '2016100304', '미처리');";
            m[78]="insert into Attend(C_no, Date, SNo, State) values(19, '06/14', '2016100306', '미처리');";
            m[79]="insert into Attend(C_no, Date, SNo, State) values(19, '06/14', '2016100318', '미처리');"; //목 취업과 창업

            for(int i=0; i<80; i++){ db.execSQL(m[i]); }


            String [] l= new String[80];  //10주
            l[0]="insert into Attend(C_no, Date, SNo, State) values(20, '06/15', '2016100304', '미처리');";
            l[1]="insert into Attend(C_no, Date, SNo, State) values(21, '06/15', '2016100304', '미처리');";
            l[2]="insert into Attend(C_no, Date, SNo, State) values(20, '06/15', '2016100306', '미처리');";
            l[3]="insert into Attend(C_no, Date, SNo, State) values(21, '06/15', '2016100306', '미처리');";
            l[4]="insert into Attend(C_no, Date, SNo, State) values(20, '06/15', '2016100318', '미처리');";
            l[5]="insert into Attend(C_no, Date, SNo, State) values(21, '06/15', '2016100318', '미처리');"; //금 시스템분석및 설계

            l[6]="insert into Attend(C_no, Date, SNo, State) values(22, '06/15', '2016100274', '미처리');";
            l[7]="insert into Attend(C_no, Date, SNo, State) values(23, '06/15', '2016100274', '미처리');";
            l[8]="insert into Attend(C_no, Date, SNo, State) values(22, '06/15', '2016100304', '미처리');";
            l[9]="insert into Attend(C_no, Date, SNo, State) values(23, '06/15', '2016100304', '미처리');";
            l[10]="insert into Attend(C_no, Date, SNo, State) values(22, '06/15', '2016100306', '미처리');";
            l[11]="insert into Attend(C_no, Date, SNo, State) values(23, '06/15', '2016100306', '미처리');";
            l[12]="insert into Attend(C_no, Date, SNo, State) values(22, '06/15', '2016100318', '미처리');";
            l[13]="insert into Attend(C_no, Date, SNo, State) values(23, '06/15', '2016100318', '미처리');"; //금 서버관리

            l[14]="insert into Attend(C_no, Date, SNo, State) values(4, '06/18', '2016100274', '미처리');";
            l[15]="insert into Attend(C_no, Date, SNo, State) values(5, '06/18', '2016100274', '미처리');";
            l[16]="insert into Attend(C_no, Date, SNo, State) values(6, '06/18', '2016100274', '미처리');";
            l[17]="insert into Attend(C_no, Date, SNo, State) values(4, '06/18', '2016100304', '미처리');";
            l[18]="insert into Attend(C_no, Date, SNo, State) values(5, '06/18', '2016100304', '미처리');";
            l[19]="insert into Attend(C_no, Date, SNo, State) values(6, '06/18', '2016100304', '미처리');";
            l[20]="insert into Attend(C_no, Date, SNo, State) values(4, '06/18', '2016100306', '미처리');";
            l[21]="insert into Attend(C_no, Date, SNo, State) values(5, '06/18', '2016100306', '미처리');";
            l[22]="insert into Attend(C_no, Date, SNo, State) values(6, '06/18', '2016100306', '미처리');";
            l[23]="insert into Attend(C_no, Date, SNo, State) values(4, '06/18', '2016100318', '미처리');";
            l[24]="insert into Attend(C_no, Date, SNo, State) values(5, '06/18', '2016100318', '미처리');";
            l[25]="insert into Attend(C_no, Date, SNo, State) values(6, '06/18', '2016100318', '미처리');"; //월 프로젝트

            l[26]="insert into Attend(C_no, Date, SNo, State) values(1, '06/18', '2016100274', '미처리');";
            l[27]="insert into Attend(C_no, Date, SNo, State) values(2, '06/18', '2016100274', '미처리');";
            l[28]="insert into Attend(C_no, Date, SNo, State) values(3, '06/18', '2016100274', '미처리');";
            l[29]="insert into Attend(C_no, Date, SNo, State) values(1, '06/18', '2016100304', '미처리');";
            l[30]="insert into Attend(C_no, Date, SNo, State) values(2, '06/18', '2016100304', '미처리');";
            l[31]="insert into Attend(C_no, Date, SNo, State) values(3, '06/18', '2016100304', '미처리');";
            l[32]="insert into Attend(C_no, Date, SNo, State) values(1, '06/18', '2016100306', '미처리');";
            l[33]="insert into Attend(C_no, Date, SNo, State) values(2, '06/18', '2016100306', '미처리');";
            l[34]="insert into Attend(C_no, Date, SNo, State) values(3, '06/18', '2016100306', '미처리');";
            l[35]="insert into Attend(C_no, Date, SNo, State) values(1, '06/18', '2016100318', '미처리');";
            l[36]="insert into Attend(C_no, Date, SNo, State) values(2, '06/18', '2016100318', '미처리');";
            l[37]="insert into Attend(C_no, Date, SNo, State) values(3, '06/18', '2016100318', '미처리');"; //월 프레임워크

            l[38]="insert into Attend(C_no, Date, SNo, State) values(7, '06/19', '2016100304', '미처리');";
            l[39]="insert into Attend(C_no, Date, SNo, State) values(8, '06/19', '2016100304', '미처리');";
            l[40]="insert into Attend(C_no, Date, SNo, State) values(9, '06/19', '2016100304', '미처리');";
            l[41]="insert into Attend(C_no, Date, SNo, State) values(7, '06/19', '2016100306', '미처리');";
            l[42]="insert into Attend(C_no, Date, SNo, State) values(8, '06/19', '2016100306', '미처리');";
            l[43]="insert into Attend(C_no, Date, SNo, State) values(9, '06/19', '2016100306', '미처리');";
            l[44]="insert into Attend(C_no, Date, SNo, State) values(7, '06/19', '2016100318', '미처리');";
            l[45]="insert into Attend(C_no, Date, SNo, State) values(8, '06/19', '2016100318', '미처리');";
            l[46]="insert into Attend(C_no, Date, SNo, State) values(9, '06/19', '2016100318', '미처리');"; //화 임베디드

            l[47]="insert into Attend(C_no, Date, SNo, State) values(10, '06/19', '2016100274', '미처리');";
            l[48]="insert into Attend(C_no, Date, SNo, State) values(11, '06/19', '2016100274', '미처리');";
            l[49]="insert into Attend(C_no, Date, SNo, State) values(10, '06/19', '2016100304', '미처리');";
            l[50]="insert into Attend(C_no, Date, SNo, State) values(11, '06/19', '2016100304', '미처리');";
            l[51]="insert into Attend(C_no, Date, SNo, State) values(10, '06/19', '2016100306', '미처리');";
            l[52]="insert into Attend(C_no, Date, SNo, State) values(11, '06/19', '2016100306', '미처리');";
            l[53]="insert into Attend(C_no, Date, SNo, State) values(10, '06/19', '2016100318', '미처리');";
            l[54]="insert into Attend(C_no, Date, SNo, State) values(11, '06/19', '2016100318', '미처리');"; //화 안드로이드

            l[55]="insert into Attend(C_no, Date, SNo, State) values(12, '06/20', '2016100274', '미처리');";
            l[56]="insert into Attend(C_no, Date, SNo, State) values(13, '06/20', '2016100274', '미처리');";
            l[57]="insert into Attend(C_no, Date, SNo, State) values(12, '06/20', '2016100304', '미처리');";
            l[58]="insert into Attend(C_no, Date, SNo, State) values(13, '06/20', '2016100304', '미처리');";
            l[59]="insert into Attend(C_no, Date, SNo, State) values(12, '06/20', '2016100306', '미처리');";
            l[60]="insert into Attend(C_no, Date, SNo, State) values(13, '06/20', '2016100306', '미처리');";
            l[61]="insert into Attend(C_no, Date, SNo, State) values(12, '06/20', '2016100318', '미처리');";
            l[62]="insert into Attend(C_no, Date, SNo, State) values(13, '06/20', '2016100318', '미처리');"; //수 안드로이드

            l[63]="insert into Attend(C_no, Date, SNo, State) values(14, '06/20', '2016100274', '미처리');";
            l[64]="insert into Attend(C_no, Date, SNo, State) values(15, '06/20', '2016100274', '미처리');";
            l[65]="insert into Attend(C_no, Date, SNo, State) values(14, '06/20', '2016100304', '미처리');";
            l[66]="insert into Attend(C_no, Date, SNo, State) values(15, '06/20', '2016100304', '미처리');";
            l[67]="insert into Attend(C_no, Date, SNo, State) values(14, '06/20', '2016100306', '미처리');";
            l[68]="insert into Attend(C_no, Date, SNo, State) values(15, '06/20', '2016100306', '미처리');";
            l[69]="insert into Attend(C_no, Date, SNo, State) values(14, '06/20', '2016100318', '미처리');";
            l[70]="insert into Attend(C_no, Date, SNo, State) values(15, '06/20', '2016100318', '미처리');"; //수 서버관리

            l[71]="insert into Attend(C_no, Date, SNo, State) values(16, '06/21', '2016100304', '미처리');";
            l[72]="insert into Attend(C_no, Date, SNo, State) values(16, '06/21', '2016100306', '미처리');";
            l[73]="insert into Attend(C_no, Date, SNo, State) values(16, '06/21', '2016100318', '미처리');"; //목 시스템 분석 및 설계
            l[74]="insert into Attend(C_no, Date, SNo, State) values(17, '06/21', '2016100274', '미처리');";
            l[75]="insert into Attend(C_no, Date, SNo, State) values(18, '06/21', '2016100274', '미처리');"; //목 기업가정신과 창업
            l[76]="insert into Attend(C_no, Date, SNo, State) values(19, '06/21', '2016100274', '미처리');";
            l[77]="insert into Attend(C_no, Date, SNo, State) values(19, '06/21', '2016100304', '미처리');";
            l[78]="insert into Attend(C_no, Date, SNo, State) values(19, '06/21', '2016100306', '미처리');";
            l[79]="insert into Attend(C_no, Date, SNo, State) values(19, '06/21', '2016100318', '미처리');"; //목 취업과 창업

            for(int i=0; i<80; i++){ db.execSQL(l[i]); }


            String [] k= new String[80];  //10주
            k[0]="insert into Attend(C_no, Date, SNo, State) values(20, '06/22', '2016100304', '미처리');";
            k[1]="insert into Attend(C_no, Date, SNo, State) values(21, '06/22', '2016100304', '미처리');";
            k[2]="insert into Attend(C_no, Date, SNo, State) values(20, '06/22', '2016100306', '미처리');";
            k[3]="insert into Attend(C_no, Date, SNo, State) values(21, '06/22', '2016100306', '미처리');";
            k[4]="insert into Attend(C_no, Date, SNo, State) values(20, '06/22', '2016100318', '미처리');";
            k[5]="insert into Attend(C_no, Date, SNo, State) values(21, '06/22', '2016100318', '미처리');"; //금 시스템분석및 설계

            k[6]="insert into Attend(C_no, Date, SNo, State) values(22, '06/22', '2016100274', '미처리');";
            k[7]="insert into Attend(C_no, Date, SNo, State) values(23, '06/22', '2016100274', '미처리');";
            k[8]="insert into Attend(C_no, Date, SNo, State) values(22, '06/22', '2016100304', '미처리');";
            k[9]="insert into Attend(C_no, Date, SNo, State) values(23, '06/22', '2016100304', '미처리');";
            k[10]="insert into Attend(C_no, Date, SNo, State) values(22, '06/22', '2016100306', '미처리');";
            k[11]="insert into Attend(C_no, Date, SNo, State) values(23, '06/22', '2016100306', '미처리');";
            k[12]="insert into Attend(C_no, Date, SNo, State) values(22, '06/22', '2016100318', '미처리');";
            k[13]="insert into Attend(C_no, Date, SNo, State) values(23, '06/22', '2016100318', '미처리');"; //금 서버관리

            k[14]="insert into Attend(C_no, Date, SNo, State) values(4, '06/25', '2016100274', '미처리');";
            k[15]="insert into Attend(C_no, Date, SNo, State) values(5, '06/25', '2016100274', '미처리');";
            k[16]="insert into Attend(C_no, Date, SNo, State) values(6, '06/25', '2016100274', '미처리');";
            k[17]="insert into Attend(C_no, Date, SNo, State) values(4, '06/25', '2016100304', '미처리');";
            k[18]="insert into Attend(C_no, Date, SNo, State) values(5, '06/25', '2016100304', '미처리');";
            k[19]="insert into Attend(C_no, Date, SNo, State) values(6, '06/25', '2016100304', '미처리');";
            k[20]="insert into Attend(C_no, Date, SNo, State) values(4, '06/25', '2016100306', '미처리');";
            k[21]="insert into Attend(C_no, Date, SNo, State) values(5, '06/25', '2016100306', '미처리');";
            k[22]="insert into Attend(C_no, Date, SNo, State) values(6, '06/25', '2016100306', '미처리');";
            k[23]="insert into Attend(C_no, Date, SNo, State) values(4, '06/25', '2016100318', '미처리');";
            k[24]="insert into Attend(C_no, Date, SNo, State) values(5, '06/25', '2016100318', '미처리');";
            k[25]="insert into Attend(C_no, Date, SNo, State) values(6, '06/25', '2016100318', '미처리');"; //월 프로젝트

            k[26]="insert into Attend(C_no, Date, SNo, State) values(1, '06/25', '2016100274', '미처리');";
            k[27]="insert into Attend(C_no, Date, SNo, State) values(2, '06/25', '2016100274', '미처리');";
            k[28]="insert into Attend(C_no, Date, SNo, State) values(3, '06/25', '2016100274', '미처리');";
            k[29]="insert into Attend(C_no, Date, SNo, State) values(1, '06/25', '2016100304', '미처리');";
            k[30]="insert into Attend(C_no, Date, SNo, State) values(2, '06/25', '2016100304', '미처리');";
            k[31]="insert into Attend(C_no, Date, SNo, State) values(3, '06/25', '2016100304', '미처리');";
            k[32]="insert into Attend(C_no, Date, SNo, State) values(1, '06/25', '2016100306', '미처리');";
            k[33]="insert into Attend(C_no, Date, SNo, State) values(2, '06/25', '2016100306', '미처리');";
            k[34]="insert into Attend(C_no, Date, SNo, State) values(3, '06/25', '2016100306', '미처리');";
            k[35]="insert into Attend(C_no, Date, SNo, State) values(1, '06/25', '2016100318', '미처리');";
            k[36]="insert into Attend(C_no, Date, SNo, State) values(2, '06/25', '2016100318', '미처리');";
            k[37]="insert into Attend(C_no, Date, SNo, State) values(3, '06/25', '2016100318', '미처리');"; //월 프레임워크

            k[38]="insert into Attend(C_no, Date, SNo, State) values(7, '06/26', '2016100304', '미처리');";
            k[39]="insert into Attend(C_no, Date, SNo, State) values(8, '06/26', '2016100304', '미처리');";
            k[40]="insert into Attend(C_no, Date, SNo, State) values(9, '06/26', '2016100304', '미처리');";
            k[41]="insert into Attend(C_no, Date, SNo, State) values(7, '06/26', '2016100306', '미처리');";
            k[42]="insert into Attend(C_no, Date, SNo, State) values(8, '06/26', '2016100306', '미처리');";
            k[43]="insert into Attend(C_no, Date, SNo, State) values(9, '06/26', '2016100306', '미처리');";
            k[44]="insert into Attend(C_no, Date, SNo, State) values(7, '06/26', '2016100318', '미처리');";
            k[45]="insert into Attend(C_no, Date, SNo, State) values(8, '06/26', '2016100318', '미처리');";
            k[46]="insert into Attend(C_no, Date, SNo, State) values(9, '06/26', '2016100318', '미처리');"; //화 임베디드

            k[47]="insert into Attend(C_no, Date, SNo, State) values(10, '06/26', '2016100274', '미처리');";
            k[48]="insert into Attend(C_no, Date, SNo, State) values(11, '06/26', '2016100274', '미처리');";
            k[49]="insert into Attend(C_no, Date, SNo, State) values(10, '06/26', '2016100304', '미처리');";
            k[50]="insert into Attend(C_no, Date, SNo, State) values(11, '06/26', '2016100304', '미처리');";
            k[51]="insert into Attend(C_no, Date, SNo, State) values(10, '06/26', '2016100306', '미처리');";
            k[52]="insert into Attend(C_no, Date, SNo, State) values(11, '06/26', '2016100306', '미처리');";
            k[53]="insert into Attend(C_no, Date, SNo, State) values(10, '06/26', '2016100318', '미처리');";
            k[54]="insert into Attend(C_no, Date, SNo, State) values(11, '06/26', '2016100318', '미처리');"; //화 안드로이드

            for(int i=0; i<55; i++){ db.execSQL(k[i]); }



            String [] view = new String[8] ;  //수강 신청
            view[0]= "create view P as select * from Student;";  //4
            view[1]= "create view C as select * from Student;";  //4
            view[2]= "create view A as select * from Student;";  //4
            view[3]= "create view S as select * from Student;";  //4
            view[4]= "create view E as select * from Student where SNo !='2016100274';";  //3
            view[5]= "create view F as select * from Student;";   //4
            view[6]= "create view B as select * from Student where SNo !='2016100274';";   //3
            view[7]= "create view X as select * from Student where SNo='2016100274';";  //1
            for(int i=0; i<8; i++){ db.execSQL(view[i]); }


            Log.d(TAG, "create table");
        }catch (Exception e){
            Log.e(TAG, "Exception in CREATE_SQL", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Professor;");
        onCreate(db);
    }
}



