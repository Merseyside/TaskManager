package com.merseyside.admin.taskmanager.data.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.merseyside.admin.taskmanager.TaskApplication;
import com.merseyside.admin.taskmanager.data.entity.Note;
import com.merseyside.admin.taskmanager.presentation.di.ApplicationContext;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by ivan_ on 24.08.2017.
 */

public class DataManager {

    Context context;
    DBHelper dbHelper;

    @Inject
    public DataManager(@ApplicationContext Context context, DBHelper dbHelper) {
        this.context = context;
        this.dbHelper = dbHelper;
    }

    public ArrayList<Note> getNotesFromDB() {
        ArrayList<Note> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.query(DBHelper.TABLE_NOTES_NAME, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                int idColIndex = c.getColumnIndex("_id");
                int nameColIndex = c.getColumnIndex(DBHelper.NAME_COLUMN);
                int textColIndex = c.getColumnIndex(DBHelper.TEXT_COLUMN);
                int picColIndex = c.getColumnIndex(DBHelper.IMG_URL_COLUMN);
                int creationColIndex = c.getColumnIndex(DBHelper.CREATION_DATE_COLUMN);
                int changeColIndex = c.getColumnIndex(DBHelper.CHANGE_DATE_COLUMN);
                list.add(new Note(c.getString(idColIndex), c.getString(nameColIndex), c.getString(textColIndex), c.getString(picColIndex), c.getString(creationColIndex), c.getString(changeColIndex)));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return list;
    }

    public boolean saveToDB(Note note) {
        ContentValues cv = new ContentValues();
        SQLiteDatabase db =  dbHelper.getWritableDatabase();
        cv.put(DBHelper.NAME_COLUMN, note.getName());
        cv.put(DBHelper.TEXT_COLUMN, note.getText());
        cv.put(DBHelper.IMG_URL_COLUMN, note.getImg());
        cv.put(DBHelper.CREATION_DATE_COLUMN, note.getCreation_date());
        cv.put(DBHelper.CHANGE_DATE_COLUMN, note.getChange_date());
        try {
            if (note.getId().equals("")) {
                db.insert(DBHelper.TABLE_NOTES_NAME, null, cv);
            }
            else db.update(DBHelper.TABLE_NOTES_NAME, cv, "_id = ?", new String[]{String.valueOf(note.getId())});
        }
        catch(NullPointerException e) {
            db.close();
            return false;
        }
        db.close();
        return true;
    }

    public boolean deleteFromDB(Note note) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            return db.delete(DBHelper.TABLE_NOTES_NAME, "_id = " + String.valueOf(note.getId()), null) >= 0;
        } finally {
            db.close();
        }
    }

    public void dropDB(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DBHelper.TABLE_NOTES_NAME,null, null);
        db.close();
    }
}
