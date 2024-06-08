package calc;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;

public abstract class Tela extends JFrame {

    public void configuraFrame() {
        this.setSize(1000, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.black);
        this.setResizable(false);
        this.setLocationRelativeTo(this);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        this.setVisible(true);

    }
}