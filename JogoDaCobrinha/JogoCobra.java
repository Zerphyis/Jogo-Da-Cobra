package JogoDaCobrinha;

import javax.swing.*;


public class JogoCobra  extends JFrame {
    public static void main(String[] args) {
        new JogoCobra();


    }
    JogoCobra() {
        add(new TelaCobra());
        setTitle("Jogo da Cobrinha - Snake game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

}
