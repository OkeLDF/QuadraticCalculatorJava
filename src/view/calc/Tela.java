package calc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public abstract class Tela extends JFrame {
    protected JButton btnVolta = new JButton("VOLTAR");

    public void configurarBtn() {
        this.btnVolta.setBackground(Color.green);
        this.btnVolta.addActionListener(this::volta);

    }

    public abstract void volta(ActionEvent e);

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