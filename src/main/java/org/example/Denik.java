package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.util.LinkedList;

public class Denik {
    private LinkedList<Zaznam> seznamZaznamu;
    private int aktualniIndex = 0;
    private SimpleDateFormat dateFormat;
    private JTextArea textArea;
    private String tempDatum;
    private String tempText;

    public Denik(JTextArea textArea) {
        seznamZaznamu = new LinkedList<>();
        aktualniIndex = -1;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.textArea = textArea;
    }

    public void pridejZaznam(String datum, String text) {
        tempDatum = datum;
        tempText = text;
        if (!seznamZaznamu.isEmpty()) {
            Zaznam posledniZaznam = seznamZaznamu.getLast();
            vypisPosledniZaznam(posledniZaznam);
        }
    }

    private void vypisPosledniZaznam(Zaznam zaznam) {
        textArea.setText(""); // Vymaže obsah JTextArea před vypsáním nového záznamu
        textArea.append("Poslední záznam:\n");
        textArea.append("Datum: " + zaznam.getDatum() + "\n");
        textArea.append("Text: " + zaznam.getText() + "\n");
    }

    public void ulozZaznam() {
        boolean validFormat = false;
        while (!validFormat) {
            try {
                Date parsedDate = dateFormat.parse(tempDatum);
                Zaznam novyZaznam = new Zaznam(dateFormat.format(parsedDate), tempText);
                seznamZaznamu.add(novyZaznam);
                aktualniIndex = seznamZaznamu.size() - 1;
                vypisAktualniZaznam();
                vypisPocetZaznamu();
                validFormat = true;
            } catch (ParseException e) {
                tempDatum = JOptionPane.showInputDialog("Nesprávný formát data. Zadejte datum ve formátu RRRR-MM-DD:");
            }
        }
    }

    public void dalsi() {
        if (aktualniIndex < seznamZaznamu.size() - 1) {
            aktualniIndex++;
            vypisAktualniZaznam();
            vypisPocetZaznamu();
        } else {
            updateTextArea("\nJste na posledním záznamu.");
        }
    }

    public void predchozi() {
        if (aktualniIndex > 0) {
            aktualniIndex--;
            vypisAktualniZaznam();
            vypisPocetZaznamu();
        } else {
            updateTextArea("\nJste na prvním záznamu.");
        }
    }


    public void nastavIndexZobrazovanehoZaznamu(int index) {
        aktualniIndex = index;
    }

    public void smazZobrazovanyZaznam() {
        if (seznamZaznamu.isEmpty()) {
            textArea.setText("Deník je prázdný.");
        } else if (aktualniIndex >= 0 && aktualniIndex < seznamZaznamu.size()) {
            Zaznam odstranovanyZaznam = seznamZaznamu.get(aktualniIndex);
            int potvrzeni = JOptionPane.showConfirmDialog(null, "Odstraňovaný záznam:\n" + "Datum: " + odstranovanyZaznam.getDatum() + "\nText: " + odstranovanyZaznam.getText() + "\nOpravdu chcete tento záznam odstranit?", "Potvrďte odstranění záznamu", JOptionPane.YES_NO_OPTION);

            if (potvrzeni == JOptionPane.YES_OPTION) {
                seznamZaznamu.remove(aktualniIndex);
                if (seznamZaznamu.isEmpty()) {
                    textArea.setText("Deník je prázdný.");
                } else {
                    textArea.setText("První vložený záznam:\n" + "Datum: " + seznamZaznamu.get(0).getDatum() + "\nText: " + seznamZaznamu.get(0).getText());
                    aktualniIndex = 0;
                }
            }
            vypisPocetZaznamu();
        } else {
            textArea.setText("Žádný záznam není zobrazen.");
        }
    }

    public void zavri() {
        updateTextArea("Program ukončen.");
    }

    private void updateTextArea(String message) {
        textArea.append(message + "\n");
    }

    private void vypisAktualniZaznam() {
        textArea.setText(""); // Vymaže obsah JTextArea před vypsáním nového záznamu
        if (aktualniIndex >= 0 && aktualniIndex < seznamZaznamu.size()) {
            Zaznam zaznam = seznamZaznamu.get(aktualniIndex);
            updateTextArea("Aktuální záznam:");
            updateTextArea("Datum: " + zaznam.getDatum());
            updateTextArea("Text: " + zaznam.getText());
        }
    }

    private void vypisPocetZaznamu() {
        textArea.append("\nPočet záznamů v deníku: " + seznamZaznamu.size());
    }
}
