package com.noiunina.model;

public class Studente {

    public String uuid, nome,cognome, corso,email;
    private String pwd;


    public Studente(){


    }

    public Studente(String nome, String cognome, String corso, String email, String pwd) {
        this.nome = nome;
        this.cognome = cognome;
        this.corso = corso;
        this.email = email;
        this.pwd = pwd;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

}
