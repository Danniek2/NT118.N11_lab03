package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;

import java.util.ArrayList;
import java.util.List;

public class DbAdapter {
    public static final String KEY_ID = "_id";
    public static final String KEY_MSSV = "mssv";
    public static final String KEY_NAME = "name";
    public static final String KEY_PHONE = "phone";
    private static final String KEY_GIOITINH="gioitinh";
    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private static final String DATABASE_NAME = "Database_Demo";
    private static final String DATABASE_TABLE = "users";
    private static final int DATABASE_VERSION = 5;
    private final Context context;
    public DbAdapter(Context ctx) {
        this.context = ctx;
    }
    public DbAdapter open() {
        dbHelper = new DatabaseHelper(context, DATABASE_NAME, null,
                DATABASE_VERSION);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        dbHelper.close();
    }
    public long createUser(String name,String phone,String mssv,int gioitinh) {
        ContentValues inititalValues = new ContentValues();
        inititalValues.put(KEY_NAME, name);
        inititalValues.put(KEY_PHONE,phone);
        inititalValues.put(KEY_MSSV,mssv);
        inititalValues.put(KEY_GIOITINH,gioitinh);
        return sqLiteDatabase.insert(DATABASE_TABLE, null, inititalValues);
    }
    public boolean deleteUser(CharSequence rowId) {
        return sqLiteDatabase.delete(DATABASE_TABLE, KEY_MSSV + "=" + rowId,
                null) > 0;
    }
    public boolean deleteAllUsers() {
        return sqLiteDatabase.delete(DATABASE_TABLE, null, null) > 0;
    }
    public Cursor getAllUsers() {
        return sqLiteDatabase.query(DATABASE_TABLE, new String[]{KEY_ID,
                KEY_NAME,KEY_PHONE,KEY_MSSV}, null, null, null, null, null);
    }
    public Sinhvien getStudent(int studentId) {
        String sql="SELECT * FROM users where " + KEY_MSSV + "=" + studentId;
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Sinhvien sinhvien;
        //Cursor cursor = sqLiteDatabase.query(DATABASE_NAME, null, KEY_MSSV + "=" + studentId ,null,null, null, null);
        //cursor=sqLiteDatabase
        Cursor cursor=sqLiteDatabase.rawQuery(sql,null);
        cursor.moveToFirst();
        Sinhvien student = new Sinhvien(cursor.getString(4), cursor.getString(2), cursor.getString(1),cursor.getInt(3));
        return student;
    }
    public List<Sinhvien> all()
    {
        String sql="SELECT * FROM users";
        List<Sinhvien> sinhvienList=new ArrayList<Sinhvien>();
        sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(sql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            int masv= cursor.getInt(0);
            String s=String.valueOf(masv);
            String phone= cursor.getString(2);
            String name=cursor.getString(4);
            String mssv=cursor.getString(1);
            int gioitinh=cursor.getInt(3);
            Sinhvien sv=new Sinhvien(name,phone,mssv,gioitinh);
            sinhvienList.add(sv);
            cursor.moveToNext();
        }
        return sinhvienList;
    }
    public void updateStudent(String name, String phone, String mssv, int gioitinh) {
        sqLiteDatabase= dbHelper.getWritableDatabase();
        ContentValues inititalValues = new ContentValues();
        inititalValues.put(KEY_NAME, name);
        inititalValues.put(KEY_PHONE,phone);
        inititalValues.put(KEY_MSSV, mssv);
        inititalValues.put(KEY_GIOITINH,gioitinh);
        //inititalValues.put(KEY_ID);
        String t="mssv=";
        String[] s= {"1234"};
        sqLiteDatabase.update(DATABASE_TABLE, inititalValues, KEY_MSSV+"=?",new String[] {String.valueOf(mssv)});
        sqLiteDatabase.close();
    }
}
