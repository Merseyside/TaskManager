package com.merseyside.admin.taskmanager.data.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.merseyside.admin.taskmanager.presentation.di.ApplicationContext;
import com.merseyside.admin.taskmanager.presentation.di.DatabaseInfo;

import javax.inject.Inject;

/**
 * Created by ivan_ on 24.08.2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    static final String TABLE_NOTES_NAME = "notes";

    static final String NAME_COLUMN = "name";
    static final String TEXT_COLUMN = "text";
    static final String IMG_URL_COLUMN = "image";
    static final String CREATION_DATE_COLUMN = "date_of_creation";
    static final String CHANGE_DATE_COLUMN = "date_of_change";


    private static final String SCRIPT = "create table " + TABLE_NOTES_NAME +
            " (_id integer primary key autoincrement, "
            + NAME_COLUMN + ", "
            + TEXT_COLUMN + ", "
            + IMG_URL_COLUMN + ", "
            + CREATION_DATE_COLUMN + ", "
            + CHANGE_DATE_COLUMN + ");";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { }

    @Inject
    DBHelper(@ApplicationContext Context context, @DatabaseInfo String dbName, @DatabaseInfo Integer version) {
        super(context, dbName, null, version);
    }

}
