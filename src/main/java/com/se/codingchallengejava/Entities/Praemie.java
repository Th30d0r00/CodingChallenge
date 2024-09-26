package com.se.codingchallengejava.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Praemie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int geschaetzteKilometer;
    private int postleitzahlZulassungsstelle;
    private String fahrzeugtyp;
    private double praemie;

    public Praemie(int geschaetzteKilometer, int postleitzahlZulassungsstelle, String fahrzeugtyp, double praemie) {
        this.geschaetzteKilometer = geschaetzteKilometer;
        this.postleitzahlZulassungsstelle = postleitzahlZulassungsstelle;
        this.fahrzeugtyp = fahrzeugtyp;
        this.praemie = praemie;
    }

    public Praemie() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getGeschaetzteKilometer() {
        return geschaetzteKilometer;
    }

    public void setGeschaetzteKilometer(int geschaetzteKilometer) {
        this.geschaetzteKilometer = geschaetzteKilometer;
    }

    public int getPostleitzahlZulassungsstelle() {
        return postleitzahlZulassungsstelle;
    }

    public void setPostleitzahlZulassungsstelle(int postleitzahlZulassungsstelle) {
        this.postleitzahlZulassungsstelle = postleitzahlZulassungsstelle;
    }

    public String getFahrzeugtyp() {
        return fahrzeugtyp;
    }

    public void setFahrzeugtyp(String fahrzeugtyp) {
        this.fahrzeugtyp = fahrzeugtyp;
    }

    public double getPraemie() {
        return praemie;
    }

    public void setPraemie(double praemie) {
        this.praemie = praemie;
    }
}
