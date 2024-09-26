package com.se.codingchallengejava.Entities;

public class PostCode {
    private String Bundesland;
    private String Land;
    private String Ort;
    private int Postleitzahl;
    private String Bezirk;

    public PostCode(String bundesland, String land, String ort, int postleitzahl, String bezirk) {
        Bundesland = bundesland;
        Land = land;
        Ort = ort;
        Postleitzahl = postleitzahl;
        Bezirk = bezirk;
    }

    public String getBundesland() {
        return Bundesland;
    }

    public void setBundesland(String bundesland) {
        Bundesland = bundesland;
    }

    public String getLand() {
        return Land;
    }

    public void setLand(String land) {
        Land = land;
    }

    public String getOrt() {
        return Ort;
    }

    public void setOrt(String ort) {
        Ort = ort;
    }

    public int getPostleitzahl() {
        return Postleitzahl;
    }

    public void setPostleitzahl(int postleitzahl) {
        Postleitzahl = postleitzahl;
    }

    public String getBezirk() {
        return Bezirk;
    }

    public void setBezirk(String bezirk) {
        Bezirk = bezirk;
    }
}
