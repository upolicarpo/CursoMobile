package com.example.apscurso;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TurmaDAO {

    public static void inserir(Context contex, Turma turma){
        Banco banco = new Banco(contex);

        ContentValues valores = new ContentValues();
        valores.put( "nome", turma.getNome() );
        valores.put( "sala", turma.getSala() );

        SQLiteDatabase db = banco.getWritableDatabase();

        db.insert("turmas", null, valores);
    }

    public static void editar(Context contex, Turma turma){
        Banco banco = new Banco(contex);

        ContentValues valores = new ContentValues();
        valores.put( "nome", turma.getNome() );
        valores.put( "sala", turma.getSala() );

        SQLiteDatabase db = banco.getWritableDatabase();
        db.update("turmas", valores, " id = "+ turma.getId(), null);
    }

    public static void excluir(Context context, int idTurma) {
        Banco banco = new Banco(context);

        SQLiteDatabase db = banco.getWritableDatabase();
        db.execSQL("DELETE FROM turmas WHERE id = " + idTurma);

    }

    public static List<Turma> listar(Context context){
        List<Turma> lista = new ArrayList<Turma>();

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM turmas ORDER BY nome", null);
        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                Turma turma = new Turma();
                turma.setId( cursor.getInt(0) );
                turma.setNome( cursor.getString(1) );
                turma.setSala( cursor.getString(2) );
                lista.add( turma );
            }while ( cursor.moveToNext() );
        }
        return lista;
    }

    public static Turma getTurmaById(Context context, int idTurma){

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM turmas WHERE id = " + idTurma, null);
        // id, nome, grupo
        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            //     do{
            Turma turma = new Turma();
            turma.setId( cursor.getInt(0) );
            turma.setNome( cursor.getString(1) );
            turma.setSala( cursor.getString(2) );
            return turma;
        }else {
            return null;
        }
    }
}
