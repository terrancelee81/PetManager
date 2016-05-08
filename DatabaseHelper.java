package com.example.tlee1.petmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tlee1 on 4/20/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    //private static final String LOG = "DatabaseHelper";
    private static final String DATABASE_NAME = "Owner.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_Owner = "Owner";
    private static final String TABLE_PetInfo = "PetInfo";
    private static final String TABLE_PetFitness = "PetFitness";

    private static final String COLUMN_NAME= "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_USERNAME = "userName";
    private static final String COLUMN_PASS1 = "pass1";

    private static final String COLUMN_PETNAME= "petName";
    private static final String COLUMN_BREED= "breed";
    private static final String COLUMN_BIRTHYEAR= "birthYear";
    private static final String COLUMN_VETNAME= "vetName";
    private static final String COLUMN_VETNUMBER= "vetNumber";


    private static final String COLUMN_STEPS= "steps";
    private static final String COLUMN_DATE= "date";

    private static final String CREATE_TABLE_OWNER = "create table " + TABLE_Owner + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, name text, email text, userName text, pass1 text)";
    private static final String CREATE_TABLE_PETINFO = "create table " + TABLE_PetInfo + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, petName text, breed text, birthYear text," +
            "vetName text, vetNumber text)";
   private static final String CREATE_TABLE_PETFITNESS = "create table " + TABLE_PetFitness + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, steps text, created_at date DEFAULT CURRENT_DATE)";




    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_OWNER);
        db.execSQL(CREATE_TABLE_PETINFO);
        db.execSQL(CREATE_TABLE_PETFITNESS);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_Owner);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_PetInfo);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_PetFitness);
        onCreate(db);

    }

    public boolean insertData(String name, String email, String userName, String pass1){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_USERNAME, userName);
        contentValues.put(COLUMN_PASS1, pass1);


        long result = db.insertOrThrow(TABLE_Owner, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insertInfo(String petName, String breed, String birthYear,
                              String vetName, String vetNumber){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PETNAME, petName);
        contentValues.put(COLUMN_BREED, breed);
        contentValues.put(COLUMN_BIRTHYEAR, birthYear);
        contentValues.put(COLUMN_VETNAME, vetName);
        contentValues.put(COLUMN_VETNUMBER, vetNumber);

        long result = db.insertOrThrow(TABLE_PetInfo, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_PetInfo, null);
        return result;
    }

    public boolean updateData(String petName, String breed, String birthYear,
                              String vetName, String vetNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PETNAME, petName);
        contentValues.put(COLUMN_BREED, breed);
        contentValues.put(COLUMN_BIRTHYEAR, birthYear);
        contentValues.put(COLUMN_VETNAME, vetName);
        contentValues.put(COLUMN_VETNUMBER, vetNumber);
        db.update(TABLE_PetInfo, contentValues, "petName = ?", new String[]{ petName });
        return true;
    }

    public boolean insertSteps(String textViewSteps){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_STEPS, textViewSteps);
        //contentValues.put(COLUMN_DATE, textDate);

        long result = db.insertOrThrow(TABLE_PetFitness, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }
    public Cursor getSteps() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_PetFitness, null);
        return result;

     }
}
