package com.example.apscurso;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvLista = findViewById(R.id.lvLista);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //           Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //                   .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra("acao", "inserir");
                startActivity(intent);
            }
        });

        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Turma turma = (Turma) lvLista.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra("acao", "editar");
                intent.putExtra("idTurma", turma.getId());
                startActivity(intent);

            }
        });

        lvLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Turma turma = (Turma) lvLista.getItemAtPosition(position);

                excluir(turma);

                return true;
            }
        });

    }

    private void excluir(final Turma turmaSelecionado) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Atenção!");
        alerta.setIcon(android.R.drawable.ic_dialog_alert);
        alerta.setMessage("Você confirma a exclusão da turma: " + turmaSelecionado.getNome() + "?");
        alerta.setNeutralButton("Cancelar", null);

        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TurmaDAO.excluir(MainActivity.this, turmaSelecionado.getId());
                carregarTurmas();
            }
        });
        alerta.show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        carregarTurmas();
    }


    private void carregarTurmas() {
        List<Turma> listaTurmas = TurmaDAO.listar(this);
        AdapterTurma adapter = new AdapterTurma(this, listaTurmas);
        lvLista.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}