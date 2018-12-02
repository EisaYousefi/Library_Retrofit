package com.broa.broasjapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHlper extends SQLiteOpenHelper {
    public static final String DB_NAME = "books";
    public static final int VERSION = 1;
    public static final String TAG = "test";

    public static final String TABLE_BOOK = "tablenewBook";
    public static final String TABLE_AZO = "azo_table";
    public static final String TABLE_AMANAT = "table_amanat";


    Books books = new Books();
    Azo azo = new Azo();
    Amanat amanat = new Amanat();
    Context context;

    public DatabaseHlper(Context context)
    {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Books.BOOKTABLE);
        db.execSQL(Amanat.BTABLEAMANAT);
        db.execSQL(Azo.AZOTABLE);
        Log.i(TAG, "Create the table was successful ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK);
        onCreate(db);

    }

    //-----insert to table books-----
    public void insertBook(Books books) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Books.KEY_ID, books.getId());
        values.put(Books.KEY_PUBLISHERS, books.getPublishers());
        values.put(Books.KEY_PRICE, books.getPrice());
        values.put(Books.KEY_BOOKNAME, books.getBookName());
        values.put(Books.KEY_AUTHORNAME, books.getAuthorName());

        database.insert(TABLE_BOOK, null, values);

        Cursor cursor = database.rawQuery("select * from  " + TABLE_BOOK, null);
        if (cursor.moveToFirst()) {
            do {
                Log.i(TAG, "writer=" + cursor.getString(cursor.getColumnIndex(Books.KEY_AUTHORNAME)));
                Log.i(TAG, "namebook=" + cursor.getString(cursor.getColumnIndex(Books.KEY_BOOKNAME)));
                Log.i(TAG, "id=" + cursor.getString(cursor.getColumnIndex(Books.KEY_ID)));
            } while (cursor.moveToNext());
            if (database.isOpen()) database.close();
        }

    }

    //=---------------insert azo--
    //-----insert to table books-----
    public void insertAzo(Azo azo) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Azo.KEY_ID_AZO, azo.getId_azo());
        values.put(Azo.KEY_NAMEFAMIL, azo.getNameFamil());
        values.put(Azo.KEY_TEL, azo.getTel());

        database.insert(TABLE_AZO, null, values);

        Cursor cursor = database.rawQuery("select * from  " + TABLE_AZO, null);
        if (cursor.moveToFirst()) {
            do {
                Log.i(TAG, "writer=" + cursor.getString(cursor.getColumnIndex(Azo.KEY_ID_AZO)));
                Log.i(TAG, "namebook=" + cursor.getString(cursor.getColumnIndex(Azo.KEY_NAMEFAMIL)));
                Log.i(TAG, "id=" + cursor.getString(cursor.getColumnIndex(Azo.KEY_TEL)));
            } while (cursor.moveToNext());
            if (database.isOpen()) database.close();
        }

    }

    //insert to table Amanat
    public void insertAmanat(Amanat amanat) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Amanat.KEY_BOOK_AMANAT, amanat.getName_book_amanat());
        values.put(Amanat.KEY_ID_BOOK_AMANAT, amanat.getId_book_amanat());
        values.put(Amanat.KEY_ID_NAME_AMANAT, amanat.getId_name_amanat());
        values.put(Amanat.KEY_NAME_AMANAT, amanat.getName_amanat());
        values.put(Amanat.KEY_TEL_AMANAT, amanat.getTel_amanat());
        values.put(Amanat.KEY_WRITER_BOOK_AMANAT, amanat.getWritername_book_amanat());

        database.insert(TABLE_AMANAT, null, values);

        Cursor cursor = database.rawQuery("select * from  " + TABLE_AMANAT, null);
        if (cursor.moveToFirst()) {
            do {
                Log.i(TAG, "nambook=" + cursor.getString(cursor.getColumnIndex(Amanat.KEY_BOOK_AMANAT)));
                Log.i(TAG, "name=" + cursor.getString(cursor.getColumnIndex(Amanat.KEY_NAME_AMANAT)));
                Log.i(TAG, "id=" + cursor.getString(cursor.getColumnIndex(Amanat.KEY_ID_BOOK_AMANAT)));
            } while (cursor.moveToNext());
            if (database.isOpen()) database.close();
        }

    }


    //--------------search  ID------------
    public Cursor getItemBookID(String idBook) {
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor;
        cursor = database.rawQuery("SELECT * FROM " + TABLE_BOOK
                + " WHERE id='" + idBook + "'", null);
        if (cursor.moveToFirst()) {
            do {
                books.setAuthorName(cursor.getString(cursor.getColumnIndex(Books.KEY_AUTHORNAME)));
                books.setBookName(cursor.getString(cursor.getColumnIndex(Books.KEY_BOOKNAME)));
                books.setPrice(cursor.getInt(cursor.getColumnIndex(Books.KEY_PRICE)));
                books.setPublishers(cursor.getString(cursor.getColumnIndex(Books.KEY_PUBLISHERS)));
            } while (cursor.moveToNext());
        }
        return cursor;
    }

    //-----search for id to table Amanat
    public Cursor getItemAmanatbookID(String idBook) {
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor;
        cursor = database.rawQuery("SELECT * FROM " + TABLE_AMANAT
                + " WHERE id_book_amanat='" + idBook + "'", null);

        return cursor;
    }

    //----------search for azo-----
    public Cursor getItemBookIDAzo(String idAzo) {
        SQLiteDatabase database = this.getWritableDatabase();
        Azo azo1 = new Azo();

        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_AZO
                + " WHERE id_azo='" + idAzo + "'", null);
        if (cursor.moveToFirst()) {
            do {
                azo1.setNameFamil(cursor.getString(cursor.getColumnIndex(Azo.KEY_NAMEFAMIL)));
                azo1.setTel(cursor.getString(cursor.getColumnIndex(Azo.KEY_TEL)));
            } while (cursor.moveToNext());
        }
        return cursor;


    }


    //----------------------search name book------------
    //--------------search  ID------------
    public Cursor getItemNameBook(String nameBook) {
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor;
        cursor = database.rawQuery("SELECT * FROM " + TABLE_BOOK + " WHERE bookname='" + nameBook + "'", null);
        if (cursor.moveToFirst()) {
            do {
                books.setAuthorName(cursor.getString(cursor.getColumnIndex(Books.KEY_AUTHORNAME)));
                books.setId(cursor.getString(cursor.getColumnIndex(Books.KEY_ID)));
                books.setPrice(cursor.getInt(cursor.getColumnIndex(Books.KEY_PRICE)));
                books.setPublishers(cursor.getString(cursor.getColumnIndex(Books.KEY_PUBLISHERS)));
            } while (cursor.moveToNext());
        }
        return cursor;
    }
    //-------------end search namebook--

    //----------All search--------------
    public List <String> getAllNameBook() {
        SQLiteDatabase database = this.getWritableDatabase();
        List <String> booksList = new ArrayList <>();
        Cursor cursor;
        cursor = database.rawQuery("SELECT * FROM " + TABLE_BOOK + "", null);
        if (cursor.moveToFirst()) {
            do {
                books.setId(cursor.getString(cursor.getColumnIndex(Books.KEY_ID)));
                books.setAuthorName(cursor.getString(cursor.getColumnIndex(Books.KEY_AUTHORNAME)));
                books.setBookName(cursor.getString(cursor.getColumnIndex(Books.KEY_BOOKNAME)));
                books.setPrice(cursor.getInt(cursor.getColumnIndex(Books.KEY_PRICE)));
                books.setPublishers(cursor.getString(cursor.getColumnIndex(Books.KEY_PUBLISHERS)));
                booksList.add("کد کتاب: " + books.getId() + "    " + books.getBookName());
            } while (cursor.moveToNext());
        }
        return booksList;
    }

    //---update--
    public void updateBook(Books books) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Books.KEY_PUBLISHERS, books.getPublishers());
        values.put(Books.KEY_PRICE, books.getPrice());
        values.put(Books.KEY_BOOKNAME, books.getBookName());
        values.put(Books.KEY_AUTHORNAME, books.getAuthorName());
        database.update(TABLE_BOOK, values, Books.KEY_ID + "=?", new String[]{books.getId()});

    }

    //-----updat azo---
    public void updateazo(Azo azo) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Azo.KEY_NAMEFAMIL, azo.getNameFamil());
        values.put(Azo.KEY_TEL, azo.getTel());
        database.update(TABLE_AZO, values, Azo.KEY_ID_AZO + "=?", new String[]{azo.getId_azo()});

    }

    //---Delete--
    public void deletebook(String idbook) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + TABLE_BOOK
                + " WHERE id='" + idbook + "'");


    }

    //--delete amanat book
    public void deleteAmanatbook(String idbookamanat) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + TABLE_AMANAT + " WHERE id_book_amanat='" + idbookamanat + "'");
    }

    //---------getallbooks--
    public List <Books> getallbook() {
        SQLiteDatabase database = this.getWritableDatabase();
        List <Books> booksList = new ArrayList <>();
        Cursor cursor;
        cursor = database.rawQuery("SELECT * FROM " + TABLE_BOOK + "", null);
        if (cursor.moveToFirst()) {
            do {
                Books books1 = new Books();
                books1.setId(cursor.getString(cursor.getColumnIndex(Books.KEY_ID)));
                books1.setAuthorName(cursor.getString(cursor.getColumnIndex(Books.KEY_AUTHORNAME)));
                books1.setBookName(cursor.getString(cursor.getColumnIndex(Books.KEY_BOOKNAME)));
                books1.setPrice(cursor.getInt(cursor.getColumnIndex(Books.KEY_PRICE)));
                books1.setPublishers(cursor.getString(cursor.getColumnIndex(Books.KEY_PUBLISHERS)));
                booksList.add(books1);
            } while (cursor.moveToNext());

        }
        return booksList;

    }

    //----لیست همه اعضا--------
    public List <Azo> getallAzo() {
        SQLiteDatabase database = this.getWritableDatabase();
        List <Azo> listazos = new ArrayList <>();
        Cursor cursor;
        cursor = database.rawQuery("SELECT * FROM " + TABLE_AZO + "", null);
        if (cursor.moveToFirst()) {
            do {
                Azo azo = new Azo();
                azo.setId_azo(cursor.getString(cursor.getColumnIndex(Azo.KEY_ID_AZO)));
                azo.setNameFamil(cursor.getString(cursor.getColumnIndex(Azo.KEY_NAMEFAMIL)));
                azo.setTel(cursor.getString(cursor.getColumnIndex(Azo.KEY_TEL)));

                listazos.add(azo);
            } while (cursor.moveToNext());

        }
        return listazos;

    }

    //------همه کتاب های که در امانتند-------
    public List <Amanat> getallAmanat() {
        SQLiteDatabase database = this.getWritableDatabase();
        List <Amanat> booksList = new ArrayList <>();
        Cursor cursor;
        cursor = database.rawQuery("SELECT * FROM " + TABLE_AMANAT + "", null);
        if (cursor.moveToFirst()) {
            do {
                Amanat amanat = new Amanat();
                amanat.setTel_amanat(cursor.getString(cursor.getColumnIndex(Amanat.KEY_TEL_AMANAT)));
                amanat.setName_amanat(cursor.getString(cursor.getColumnIndex(Amanat.KEY_NAME_AMANAT)));
                amanat.setId_book_amanat(cursor.getString(cursor.getColumnIndex(Amanat.KEY_ID_BOOK_AMANAT)));
                amanat.setWritername_book_amanat(cursor.getString(cursor.getColumnIndex(Amanat.KEY_WRITER_BOOK_AMANAT)));
                amanat.setName_book_amanat(cursor.getString(cursor.getColumnIndex(Amanat.KEY_BOOK_AMANAT)));
                booksList.add(amanat);
            } while (cursor.moveToNext());

        }
        return booksList;

    }

}
