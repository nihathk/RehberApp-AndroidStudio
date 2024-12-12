package com.example.rehber_uygulamasi_final_odevi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.StringBuilderPrinter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Veritabani extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "rehber";
    private static final String KISILER_TABLE = "kisiler";
    private static final int DATABASE_VERSION = 1;

    public static final String ROW_ID = "id";
    public static final String ROW_NAME = "ad";
    public static final String ROW_PHONE = "telefon";
    public static final String ROW_EMAIL = "email";
    public static final String ROW_ADRESS = "adres";
    public static final String ROW_WEBSITE = "website";

    public Veritabani(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + KISILER_TABLE + "("+ROW_ID+" INTEGER PRIMARY KEY, "+ROW_NAME+" TEXT NOT NULL, "+ROW_PHONE+" TEXT NOT NULL, "+ROW_EMAIL+" TEXT, "+ROW_ADRESS+" TEXT, "+ROW_WEBSITE+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + KISILER_TABLE);
        onCreate(db);
    }

    public List<String> KisileriListele()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> kisiler = new ArrayList<String>();
        Cursor cursor = db.query(KISILER_TABLE, new String[]{ROW_ID, ROW_NAME, ROW_PHONE, ROW_EMAIL, ROW_ADRESS, ROW_WEBSITE}, null, null, null, null, null);
        while (cursor.moveToNext())
        {
            kisiler.add(cursor.getInt(0) + " - " + cursor.getString(1) + " - " + cursor.getString(2) + " - " + cursor.getString(3) + " - " + cursor.getString(4) + " - " + cursor.getString(5));
        }
        return kisiler;
    }

    public void KisiSil(long id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(KISILER_TABLE, ROW_ID + "=" + id, null);
        db.close();
    }

    public void KisiDuzenle(long id, String ad, String telefon, String email, String adres, String website)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ROW_NAME, ad);
        cv.put(ROW_PHONE, telefon);
        cv.put(ROW_EMAIL, email);
        cv.put(ROW_ADRESS, adres);
        cv.put(ROW_WEBSITE, website);
        db.update(KISILER_TABLE, cv, ROW_ID + "=" + id, null);
        db.close();
    }

    public void KisiEkle(String adi, String telefon, String email, String adres, String website)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ROW_NAME, adi);
        cv.put(ROW_PHONE, telefon);
        cv.put(ROW_EMAIL, email);
        cv.put(ROW_ADRESS, adres);
        cv.put(ROW_WEBSITE, website);
        db.insert(KISILER_TABLE, null, cv);
        db.close();
    }
}
