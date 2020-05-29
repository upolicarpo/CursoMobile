package com.example.apscurso;

import androidx.annotation.NonNull;

public class Turma {
    public int id;
    private String nome, sala;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    @NonNull
    @Override
    public String toString() {
        return nome + " - " + sala;
    }
}
