package com.example.apscurso;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FormularioActivity extends AppCompatActivity {
    private EditText etNome, etSala;
    private Button btnSalvar;
    private String acao;
    private Turma turma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNome = (EditText) findViewById(R.id.etNome);
        etSala = findViewById(R.id.etSala);
        btnSalvar = findViewById(R.id.btnSalvar);

        acao = getIntent().getStringExtra("acao");

        if( acao.equals("editar")){
            carregarFormulario();
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });

    }

    private void salvar(){
        if( acao.equals("inserir")){
            turma = new Turma();
        }
        String nome = etNome.getText().toString();
        if( nome.isEmpty() ){
            Toast.makeText(this, "Você deve informar o nome da turma!", Toast.LENGTH_LONG ).show();
        }else {
            turma.setNome( nome );
            turma.setSala( etSala.getText().toString() );

            if( acao.equals("inserir")){
                TurmaDAO.inserir( this, turma );
                limpar();
            }else {
                TurmaDAO.editar( this, turma);
                finish();
            }
        }
    }

    private void carregarFormulario(){
        int idTurma =  getIntent().getIntExtra("idTurma", 0) ;
        turma = TurmaDAO.getTurmaById( this, idTurma);
        etNome.setText( turma.getNome() );
        etSala.setText( turma.getSala() );

    }

    private void limpar(){
        turma = null;
        etNome.setText("");
        etSala.setText("");
    }
}
