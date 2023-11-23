package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.example.Denik;

public class DenikGUI {
    private Denik denik;
    private JFrame frame;
    private JTextArea textArea;
    private JTextField outputField;

    public DenikGUI() {
        frame = new JFrame("Deník");
        textArea = new JTextArea();
        textArea.setEditable(false);

        outputField = new JTextField(30);

        denik = new Denik(textArea);

        JButton pridejButton = new JButton("Přidat záznam");
        JButton smazButton = new JButton("Smazat záznam");
        JButton ulozButton = new JButton("Uložit");
        JButton dalsiButton = new JButton("Další");
        JButton predchoziButton = new JButton("Předchozí");
        JButton zavriButton = new JButton("Ukončit");

        pridejButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String datum = JOptionPane.showInputDialog("Zadej datum (RRRR-MM-DD):");
                String text = JOptionPane.showInputDialog("Zadej text:");
                denik.pridejZaznam(datum, text);
            }
        });

        smazButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                denik.smazZobrazovanyZaznam();
            }
        });

        ulozButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                denik.ulozZaznam();
            }
        });

        dalsiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                denik.dalsi();
            }
        });

        predchoziButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                denik.predchozi();
            }
        });

        zavriButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                denik.zavri();
                frame.dispose(); // Uzavření okna
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(3, 2));
        buttonPanel.add(pridejButton);
        buttonPanel.add(smazButton);
        buttonPanel.add(ulozButton);
        buttonPanel.add(dalsiButton);
        buttonPanel.add(predchoziButton);
        buttonPanel.add(zavriButton);

        frame.setLayout(new BorderLayout());
        frame.add(buttonPanel, BorderLayout.WEST);
        frame.add(textArea, BorderLayout.CENTER);
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DenikGUI();
        });
    }
}
