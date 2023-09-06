package com.barreto.applistacurso.controller;

import android.content.SharedPreferences;

import com.barreto.applistacurso.model.Pessoa;
import com.barreto.applistacurso.view.MainActivity;

public class PessoaController {
    SharedPreferences preferences;
    SharedPreferences.Editor listaVip;
    public static final String NOME_PREFERENCES = "pref_listaVip";
    public PessoaController(MainActivity mainActivity){
        preferences = mainActivity.getSharedPreferences(NOME_PREFERENCES,0);
        listaVip = preferences.edit();
    }
    public void salvar(Pessoa pessoas){
        listaVip.putString("primeiroNome", pessoas.getPrimeiroNome());
        listaVip.putString("sobreNome", pessoas.getSobreNome());
        listaVip.putString("cursoDesejado", pessoas.getCursoDesejado());
        listaVip.putString("telefoneContato", pessoas.getTelefoneContato());
        listaVip.apply();
    }
    public Pessoa buscar(Pessoa pessoas){
        pessoas.setPrimeiroNome(preferences.getString("primeiroNome",""));
        pessoas.setSobreNome(preferences.getString("sobreNome",""));
        pessoas.setCursoDesejado(preferences.getString("cursoDesejado",""));
        pessoas.setTelefoneContato(preferences.getString("telefoneContato",""));
        return pessoas;
    }
    public void limpar(){
         listaVip.clear();
         listaVip.apply();
    }
}
