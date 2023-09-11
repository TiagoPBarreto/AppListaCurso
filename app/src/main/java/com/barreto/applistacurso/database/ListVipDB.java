package com.barreto.applistacurso.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.barreto.applistacurso.model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class ListVipDB extends SQLiteOpenHelper {
    //Criar métodos para implementar crud
    //para criar o banco de dados
    // 1 - Nome do Banco de dados
    // 2 - Versão do banco de dados

    private static final String DB_NAME = "listavip.db";
    private static final int DB_VERSION = 1;

    Cursor cursor;
    SQLiteDatabase db;

    public ListVipDB(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTabelaPessoa = "CREATE TABLE Pessoa (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "primeiroNome TEXT, " +
                "sobreNome TEXT, " +
                "cursoDesejado TEXT, "+
                "telefoneContato TEXT)";
        db.execSQL(sqlTabelaPessoa);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void salvarObjeto(String tabela, ContentValues dados){
        db.insert(tabela,null,dados);

    }
    public List<Pessoa>listarDados(){
        List<Pessoa>lista = new ArrayList<>();
        Pessoa registro;
        String querySQL = "SELECT * FROM Pessoa";
        cursor = db.rawQuery(querySQL,null);
        if (cursor.moveToFirst()){

            do {
                registro = new Pessoa();
                registro.setId(cursor.getInt(0));
                registro.setPrimeiroNome(cursor.getString(1));
                registro.setSobreNome(cursor.getString(2));
                registro.setCursoDesejado(cursor.getString(3));
                registro.setTelefoneContato(cursor.getString(4));
                lista.add(registro);

            }while (cursor.moveToNext());
        }
        return lista;
    }
    public void alterarObjeto(String tabela, ContentValues dados){
        //ID para ser alterado
        int id = dados.getAsInteger("id");
        db.update(tabela,dados,"id=?",new String[]{Integer.toString(id)});

    }

    public void deletar(String tabela, int id){
        //ID para ser alterado
        db.delete(tabela,"id=?",new String[]{Integer.toString(id)});

    }

}
