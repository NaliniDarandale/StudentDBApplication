package com.studentdbapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.PeriodicSync;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "Student_Info.db";
    public static final String TABLE_NAME = "Student_info_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "ADDRESS";
    public static final String COL_5 = "CONTACT_NO";
    public static final String COL_6 = "DOB";
    public static final String COL_7 = "GENDER";
    public static final String COL_8 = "M1";
    public static final String COL_9 = "M2";
    public static final String COL_10 = "M3";
    public static final String COL_11 = "TOTAL";
    public static final String COL_12 = "PERCENTAGE";
    public static final String COL_13 = "REMARK";
    public static final String COL_14 = "PROFILE_IMAGE";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT NOT NULL, SURNAME TEXT NOT NULL, ADDRESS TEXT NOT NULL, CONTACT_NO INTEGER NOT NULL, DOB TEXT NOT NULL, GENDER TEXT NOT NULL, M1 INTEGER NOT NULL, M2 INTEGER NOT NULL, M3 INTEGER NOT NULL, TOTAL INTEGER NOT NULL, PERCENTAGE DOUBLE NOT NULL, REMARK TEXT NOT NULL, PROFILE_IMAGE TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }


    //String name, String surname, String address, String contact_no, String dob, String gender, String m1, String m2, String m3, String total, String percentage, String remark, String profile_image
    public boolean insertData(StudentData sd){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, sd.getName());
        contentValues.put(COL_3, sd.getSurname());
        contentValues.put(COL_4, sd.getAddress());
        contentValues.put(COL_5, sd.getContact_no());
        contentValues.put(COL_6, sd.getDob());
        contentValues.put(COL_7, sd.getGender());
        contentValues.put(COL_8, sd.getM1());
        contentValues.put(COL_9, sd.getM2());
        contentValues.put(COL_10, sd.getM3());
        contentValues.put(COL_11, sd.getStrTotal());
        contentValues.put(COL_12, sd.getPercent());
        contentValues.put(COL_13, sd.getRemark());
        contentValues.put(COL_14, sd.getProfile_image());

        db.insert(TABLE_NAME,null,contentValues);
        return true;
    }

    public List<StudentData> studentList(String filter){
        String query;
        if (filter.equals(""))
            query = "SELECT * FROM "+TABLE_NAME;
        else
            query = "SELECT * FROM "+TABLE_NAME+" ORDER BY "+filter;

        List<StudentData> studentLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        StudentData studentData;

        if (cursor.moveToFirst()){
            do {
                studentData = new StudentData();
                studentData.setId(cursor.getInt(cursor.getColumnIndex(COL_1)));
                studentData.setName(cursor.getString(cursor.getColumnIndex(COL_2)));
                studentData.setSurname(cursor.getString(cursor.getColumnIndex(COL_3)));
                studentData.setAddress(cursor.getString(cursor.getColumnIndex(COL_4)));
                studentData.setContact_no(cursor.getString(cursor.getColumnIndex(COL_5)));
                studentData.setDob(cursor.getString(cursor.getColumnIndex(COL_6)));
                studentData.setGender(cursor.getString(cursor.getColumnIndex(COL_7)));
                studentData.setM1(cursor.getString(cursor.getColumnIndex(COL_8)));
                studentData.setM2(cursor.getString(cursor.getColumnIndex(COL_9)));
                studentData.setM3(cursor.getString(cursor.getColumnIndex(COL_10)));
                studentData.setStrTotal(cursor.getString(cursor.getColumnIndex(COL_11)));
                studentData.setPercent(cursor.getString(cursor.getColumnIndex(COL_12)));
                studentData.setRemark(cursor.getString(cursor.getColumnIndex(COL_13)));
                studentData.setProfile_image(cursor.getString(cursor.getColumnIndex(COL_14)));
                studentLinkedList.add(studentData);
            }while (cursor.moveToNext());
        }
        return studentLinkedList;
    }

    //get only one record
    public StudentData getStudent(int id){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+" WHERE ID="+id;
        Cursor cursor = db.rawQuery(query, null);

        StudentData receivedStudent = new StudentData();
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            //receivedStudent.setId(cursor.getInt(cursor.getColumnIndex(COL_1)));
            receivedStudent.setName(cursor.getString(cursor.getColumnIndex(COL_2)));
            receivedStudent.setSurname(cursor.getString(cursor.getColumnIndex(COL_3)));
            receivedStudent.setAddress(cursor.getString(cursor.getColumnIndex(COL_4)));
            receivedStudent.setContact_no(cursor.getString(cursor.getColumnIndex(COL_5)));
            receivedStudent.setDob(cursor.getString(cursor.getColumnIndex(COL_6)));
            receivedStudent.setGender(cursor.getString(cursor.getColumnIndex(COL_7)));
            receivedStudent.setM1(cursor.getString(cursor.getColumnIndex(COL_8)));
            receivedStudent.setM2(cursor.getString(cursor.getColumnIndex(COL_9)));
            receivedStudent.setM3(cursor.getString(cursor.getColumnIndex(COL_10)));
            receivedStudent.setStrTotal(cursor.getString(cursor.getColumnIndex(COL_11)));
            receivedStudent.setPercent(cursor.getString(cursor.getColumnIndex(COL_12)));
            receivedStudent.setRemark(cursor.getString(cursor.getColumnIndex(COL_13)));
            receivedStudent.setProfile_image(cursor.getString(cursor.getColumnIndex(COL_14)));
        }
        return receivedStudent;
    }

    //String id, String name,String surname, String address, String contact_no, String dob, String gender, String m1, String m2, String m3, String total, String percentage, String remark, String profile_image
    public void updateData(int id, Context context, StudentData sd){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, sd.getName());
        contentValues.put(COL_3, sd.getSurname());
        contentValues.put(COL_4, sd.getAddress());
        contentValues.put(COL_5, sd.getContact_no());
        contentValues.put(COL_6, sd.getDob());
        contentValues.put(COL_7, sd.getGender());
        contentValues.put(COL_8, sd.getM1());
        contentValues.put(COL_9, sd.getM2());
        contentValues.put(COL_10, sd.getM3());
        contentValues.put(COL_11, sd.getStrTotal());
        contentValues.put(COL_12, sd.getPercent());
        contentValues.put(COL_13, sd.getRemark());
        contentValues.put(COL_14, sd.getProfile_image());

        db.update(TABLE_NAME,contentValues,"ID = ?", new String[]{String.valueOf(id)});
        Toast.makeText(context,"Updated Successfully...", Toast.LENGTH_LONG).show();
    }

    public void deleteData(int id, Context context){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "ID = ?",new String[] {String.valueOf(id)});
        Toast.makeText(context,"Deleted Successfully...", Toast.LENGTH_LONG).show();
    }
}
