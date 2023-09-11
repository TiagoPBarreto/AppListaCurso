package com.barreto.applistacurso.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.barreto.applistacurso.R;
import com.barreto.applistacurso.controller.CursoController;
import com.barreto.applistacurso.controller.PessoaController;
import com.barreto.applistacurso.model.Pessoa;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Pessoa pessoas;
    List<String> nomesDosCursos;
    PessoaController controller;
    CursoController cursoController;
    EditText editPrimeiroNome, editSobreNome, editCursoDesejado, editTelContato;
    Button btnLimpar, btnSalvar, btnFinalizar;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);


        controller = new PessoaController(MainActivity.this);
        cursoController = new CursoController();
        nomesDosCursos = cursoController.dadosParaSpinner();

        pessoas = new Pessoa();


        controller.buscarDadosSharedPreferences(pessoas);
        editPrimeiroNome = findViewById(R.id.editPrimeiroNome);
        editSobreNome = findViewById(R.id.editSobreNome);
        editCursoDesejado = findViewById(R.id.editCursoDesejado);
        editTelContato = findViewById(R.id.editTelContato);

        editPrimeiroNome.setText(pessoas.getPrimeiroNome());
        editSobreNome.setText(pessoas.getSobreNome());
        editCursoDesejado.setText(pessoas.getCursoDesejado());
        editTelContato.setText(pessoas.getTelefoneContato());

        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        spinner = findViewById(R.id.spinner);

        //adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                cursoController.dadosParaSpinner());
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editPrimeiroNome.setText("");
                editSobreNome.setText("");
                editCursoDesejado.setText("");
                editTelContato.setText("");
                controller.limpar();

            }
        });
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Volte sempre", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pessoas.setPrimeiroNome(editPrimeiroNome.getText().toString());
                pessoas.setSobreNome(editSobreNome.getText().toString());
                pessoas.setCursoDesejado(editCursoDesejado.getText().toString());
                pessoas.setTelefoneContato(editTelContato.getText().toString());
                Toast.makeText(MainActivity.this, "Salvo "+pessoas.toString(), Toast.LENGTH_SHORT).show();

                controller.salvar(pessoas);
            }
        });
    }
}