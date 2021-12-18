package com.noiunina.model;

import java.util.ArrayList;

public class Studente {

    public String uuid, nome,cognome, corso,email;
    public ArrayList<CredenzialiChat> credenzialiChats;

    public Studente(){


    }

    public Studente(String uuid, String nome, String cognome, String corso, String email, ArrayList<CredenzialiChat> credenzialiChats) {
        this.uuid=uuid;
        this.nome = nome;
        this.cognome = cognome;
        this.corso = corso;
        this.email = email;
        this.credenzialiChats = credenzialiChats;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public ArrayList<CredenzialiChat> getCredenzialiChats() {
        return credenzialiChats;
    }

    public void setCredenzialiChats(ArrayList<CredenzialiChat> credenzialiChats) {
        this.credenzialiChats = credenzialiChats;
    }


}
