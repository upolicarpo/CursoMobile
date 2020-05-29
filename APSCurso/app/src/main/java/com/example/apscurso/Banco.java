package com.example.apscurso;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {
    private static final String NOME = "APSCurso";
    private static final int VERSAO = 1;

    public Banco(Context contexto){
        super(contexto, NOME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS turmas ( " +
                "  id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , " +
                "  nome TEXT , " +
                "  sala TEXT  ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
