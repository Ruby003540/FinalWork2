package com.ruby.finalwork;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class NoteManager {
    private DBHelper dbHelper;
    private String TBNAME;

    public NoteManager(Context context) {
        dbHelper = new DBHelper(context);
        TBNAME = DBHelper.TB_NAME;
    }

    public void add(NoteItem item){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("curname", item.getCurName());
        values.put("currate", item.getCurRate());
        db.insert(TBNAME, null, values);
        db.close();
    }
    public List<NoteItem> listAll(){
        List<NoteItem> noteList = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TBNAME, null, null, null, null, null, null);
        if(cursor!=null){
            noteList = new ArrayList<NoteItem>();
            while(cursor.moveToNext()){
                NoteItem item = new NoteItem();
                item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
                item.setCurName(cursor.getString(cursor.getColumnIndex("CURNAME")));
                item.setCurRate(cursor.getString(cursor.getColumnIndex("CURRATE")));

                noteList.add(item);
            }
            cursor.close();
        }
        db.close();
        return noteList;

    }


}
