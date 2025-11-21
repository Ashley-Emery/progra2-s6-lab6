/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progra2.s6.lab6;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author ferna
 */
public class FrameCMD extends JFrame {

    private final MiCmd cmd;
    private final JTextArea areaConsola;
    private final JTextField campoEntrada;
    private final JScrollPane scroll;

    public FrameCMD() {
        super("CMD");

        cmd = new MiCmd();
        areaConsola = crearAreaConsola();
        campoEntrada = crearCampoEntrada();
        scroll = new JScrollPane(areaConsola);

        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);
        add(campoEntrada, BorderLayout.SOUTH);

        setVisible(true);

        imprimir("Microsoft Windows [Version 10.0.22621.521]\n");
        imprimir("(c) Microsoft Corporation. All rights reserved.\n\n");
        mostrarPrompt();
    }

    private JTextArea crearAreaConsola() {
        JTextArea txt = new JTextArea();
        txt.setEditable(false);
        txt.setFont(new Font("Arial", Font.PLAIN, 14));
        txt.setBackground(Color.BLACK);
        txt.setForeground(Color.WHITE);
        txt.setMargin(new Insets(5, 5, 5, 5));
        return txt;
    }

    private JTextField crearCampoEntrada() {
        JTextField txt = new JTextField();
        txt.setFont(new Font("Arial", Font.PLAIN, 14));
        txt.addActionListener(e -> {
            String linea = txt.getText();
            txt.setText("");
            entrada(linea);
        });
        return txt;
    }

    private void imprimir(String texto) {
        areaConsola.append(texto);
        areaConsola.setCaretPosition(areaConsola.getDocument().getLength());
    }

    private void mostrarPrompt() {
        imprimir(cmd.getPrompt());
    }

    private void entrada(String linea) {
        String comando = linea == null ? "" : linea.trim();

        if (comando.isBlank()) {
            imprimir("\n");
            mostrarPrompt();
            return;
        }

        imprimir(comando + "\n");

        String respuesta = cmd.procesarComando(comando, this);

        if (respuesta != null && !respuesta.isBlank()) {
            imprimir(respuesta);
        }

        mostrarPrompt();
    }

}
