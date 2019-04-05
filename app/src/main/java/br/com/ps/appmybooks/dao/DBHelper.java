package br.com.ps.appmybooks.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "my_books.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABELA_USUARIO= "usuario";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createTableUsuario(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists usuario");
    }

    private void createTableUsuario(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sql = new StringBuilder();
        try {
            sql.append("CREATE TABLE ").append(TABELA_USUARIO);
            sql.append("( _id ").append("integer primary key autoincrement, ");
            sql.append("id_servidor ").append("integer, ");
            sql.append("login ").append("text, ");
            sql.append("senha ").append("text, ");
            sql.append("is_ativo ").append("integer default 0)");
            sqLiteDatabase.execSQL(sql.toString());
        }finally {
            sql = null;
        }
    }
}
