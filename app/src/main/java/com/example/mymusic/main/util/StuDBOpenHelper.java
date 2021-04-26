package com.example.mymusic.main.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.mymusic.main.bean.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/11/28.
 */

public class StuDBOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="stu.db3";
    private static final String TABLE_NAME="student";
    private static final int VERSION=1;
    private static final String KEY_ID="_id";
    private static final String KEY_NAME="name";
    private static final String KEY_GRADE="grade";
    private static final String CREATE_TABLE="create table "+TABLE_NAME+"("+KEY_ID+
            " integer primary key autoincrement,"+KEY_NAME+" text not null,"+
            KEY_GRADE+" text not null);";
    SQLiteDatabase db;
    public StuDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addStu(Student stu){
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,stu.getName());
         values.put(KEY_GRADE,stu.getGrade());
         db.insert(TABLE_NAME,null,values);

    }
    public void updateStu(Student stu){
       // SQLiteDatabase db=this.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,stu.getName());
        values.put(KEY_GRADE,stu.getGrade());
        db.update(TABLE_NAME,values,KEY_ID+"=?",new String[]{String.valueOf(stu.getId())});
    }
    public void deleteStu(int id){
        db.delete(TABLE_NAME,KEY_ID+"=?",new String[]{String.valueOf(id)});
    }
    public Student findByName(String name){
        Cursor cursor=db.query(TABLE_NAME,new String[]{KEY_ID,KEY_NAME,KEY_GRADE},KEY_NAME+"=?",new String[]{name},null,null,null);
        Student s=null;
        if(cursor.moveToFirst()){
            s=new Student(cursor.getInt(0),cursor.getString(1), cursor.getString(2));
        }
        return  s;
    }
    public List<Student> getAllStudent(){
        List<Student>stus=new ArrayList<>();
        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME,null);
        while(cursor.moveToNext()){
            Student s=new Student();
            s.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            s.setName(cursor.getString(cursor.getColumnIndex("name")));
            s.setGrade(cursor.getString(cursor.getColumnIndex("grade")));
            stus.add(s);
        }
        return  stus;
    }

}
