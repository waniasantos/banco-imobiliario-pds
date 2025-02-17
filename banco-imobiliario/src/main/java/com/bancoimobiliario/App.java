package com.bancoimobiliario;

import com.bancoimobiliario.gui.BancoImobiliarioGUI;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BancoImobiliarioGUI().setVisible(true);
        });
    }
}
