package br.com.ps.appmybooks.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBGateway {

    private static DBGateway dbGateway;
    private DBHelper dbHelper;

    private SQLiteDatabase dbRead;
    private SQLiteDatabase dbWrite;

    public DBGateway(Context context) {
        this.dbHelper = new DBHelper(context);
        this.dbRead =  this.dbHelper.getReadableDatabase();
        this.dbWrite = this.dbHelper.getWritableDatabase();
    }

    public static DBGateway getInstance(Context context) {
        if (dbGateway == null) {
            dbGateway = new DBGateway(context);
        }
        return dbGateway;
    }

    public SQLiteDatabase openDbWrite() {
        return this.dbWrite;
    }
    public SQLiteDatabase openDbRead() {
        return this.dbRead;
    }
}
