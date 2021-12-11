package com.noiunina.model;

public class Studente {

    public String nome,cognome, corso,email;

    public Studente(){


    }

    public Studente(String nome, String cognome, String corso, String email) {
        this.nome = nome;
        this.cognome = cognome;
        this.corso = corso;
        this.email = email;
    }

    public String getNome() {

        return nome;
    }

    public String getCognome() {

        return cognome;
    }

    public String getCorso() {

        return corso;
    }

    public String getEmail() {

        return email;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setCorso(String corso) {
        this.corso = corso;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
