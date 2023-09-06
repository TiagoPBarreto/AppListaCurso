package com.barreto.applistacurso.controller;

import com.barreto.applistacurso.model.Curso;

import java.util.ArrayList;
import java.util.List;

public class CursoController {
    private List listCursos;
    public List getListaDeCursos(){
        listCursos = new ArrayList<Curso>();
        listCursos.add(new Curso("Java"));
        listCursos.add(new Curso("HTML"));
        listCursos.add(new Curso("Flutter"));
        listCursos.add(new Curso("Dart"));
        return listCursos;
    }
    public ArrayList<String>dadosParaSpinner(){
        ArrayList<String>dados = new ArrayList<>();

        for (int i = 0; i <getListaDeCursos().size() ; i++) {
            Curso curso = (Curso) getListaDeCursos().get(i);
            curso.getNomeDoCursoDesejado();
            dados.add(curso.getNomeDoCursoDesejado());
        }
        return dados;
    }
}
