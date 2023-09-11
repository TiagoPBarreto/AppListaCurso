package com.barreto.applistacurso.controller;

import android.content.ContentValues;
import android.content.SharedPreferences;

import com.barreto.applistacurso.database.ListVipDB;
import com.barreto.applistacurso.model.Pessoa;
import com.barreto.applistacurso.view.MainActivity;

public class PessoaController extends ListVipDB {
    SharedPreferences preferences;
    SharedPreferences.Editor listaVip;
    public static final String NOME_PREFERENCES = "pref_listaVip";
    public PessoaController(MainActivity mainActivity){
        super(mainActivity);
        preferences = mainActivity.getSharedPreferences(NOME_PREFERENCES,0);
        listaVip = preferences.edit();
    }
    public void salvar(Pessoa pessoas){
        ContentValues dados = new ContentValues();
        listaVip.putString("primeiroNome", pessoas.getPrimeiroNome());
        listaVip.putString("sobreNome", pessoas.getSobreNome());
        listaVip.putString("cursoDesejado", pessoas.getCursoDesejado());
        listaVip.putString("telefoneContato", pessoas.getTelefoneContato());
        listaVip.apply();
        dados.put("primeiroNome",pessoas.getPrimeiroNome());
        dados.put("sobreNome",pessoas.getSobreNome());
        dados.put("cursoDesejado",pessoas.getCursoDesejado());
        dados.put("telefoneContato",pessoas.getTelefoneContato());
        salvarObjeto("Pessoa",dados);

    }
    public Pessoa buscarDadosSharedPreferences(Pessoa pessoas){
        pessoas.setPrimeiroNome(preferences.getString("primeiroNome",""));
        pessoas.setSobreNome(preferences.getString("sobreNome",""));
        pessoas.setCursoDesejado(preferences.getString("cursoDesejado",""));
        pessoas.setTelefoneContato(preferences.getString("telefoneContato",""));
        return pessoas;
    }

    public void alterar(Pessoa pessoas){
        ContentValues dados = new ContentValues();
        dados.put("id",pessoas.getId());
        dados.put("primeiroNome",pessoas.getPrimeiroNome());
        dados.put("sobreNome",pessoas.getSobreNome());
        dados.put("cursoDesejado",pessoas.getCursoDesejado());
        dados.put("telefoneContato",pessoas.getTelefoneContato());
        alterarObjeto("Combustivel",dados);
    }
    public void deletar(int id){
        deletar("combustivel",id);
    }


    public void limpar(){
         listaVip.clear();
         listaVip.apply();
    }
}
