package org.example;

public class Zaznam {
    private String datum;
    private String text;

    public Zaznam(String datum, String text) {
        this.datum = datum;
        this.text = text;
    }

    public String getDatum() {
        return datum;
    }

    public String getText() {
        return text;
    }
}

