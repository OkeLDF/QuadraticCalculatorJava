package menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import calc.Calc;
import historic.Historico;
import user.Alterar;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author User
 */
public class Menu extends JFrame {

    protected JPanel jpConfirma, jpCalc, jpVolta;
    protected JPanel panel;
    protected JButton btnHistorico, btnCalcular, bntLogout, bntEdit;
    private Calc calcular;
    private Historico historico;
    private Alterar alterar;

    public Menu() {

    }

    public void init() {
        configurarJanela();
        configurarPanel();
        this.setVisible(true);
        calcular = new Calc();
        historico = new Historico();
        alterar = new Alterar();
    }

    protected void configurarJanela() {
        panel = new JPanel();
        this.panel.setBackground(Color.black);
        this.panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 130));
        this.add(panel);
        this.setTitle("Tela Inicial");
        this.setSize(1000, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(this);

    }

    protected void configurarPanel() {
        jpCalc = new JPanel();
        jpConfirma = new JPanel(); // será utilizado por suas subclasses
        jpVolta = new JPanel(); // será utilizado por suas subclasses

        this.jpCalc.setLayout(new FlowLayout(FlowLayout.LEFT, 90, 40));
        this.jpCalc.setPreferredSize(new Dimension(490, 500));
        this.jpCalc.setBackground(Color.darkGray);
        this.jpCalc.setOpaque(false);

        this.panel.add(this.jpCalc);

        configurarDados();
        this.jpCalc.add(this.btnCalcular);
        this.jpCalc.add(this.bntEdit);
        this.jpCalc.add(this.btnHistorico);
        this.jpCalc.add(this.bntLogout);

    }

    private void configurarDados() {
        bntEdit = new JButton("ALTERAR");
        btnHistorico = new JButton("HISTÓRICO");
        btnCalcular = new JButton("CALCULAR");
        bntLogout = new JButton("LOGOUT ");
        List<JButton> bntList = Arrays.asList(bntEdit, btnHistorico, btnCalcular, bntLogout);

        bntList.forEach((btn) -> {
            btn.setFont(new Font("Arial Black", Font.BOLD, 14));
            btn.setForeground(Color.black);
            btn.setBackground(Color.green);
            btn.setFocusPainted(false);
            btn.setPreferredSize(new Dimension(120, 30));

        });

        bntList.forEach(e -> e.addActionListener(this::escolha));

    }

    private void escolha(ActionEvent event) {
        System.out.println("opa");
        if (this.btnCalcular.equals(event.getSource())) {
            new Calc().init();
            this.dispose();
        } else if (this.bntLogout.equals(event.getSource())) {
            this.dispose();
            new Initial().init();
        } else if (this.btnHistorico.equals(event.getSource())) {
            this.dispose();
            new Historico().init();
        } else if (this.bntEdit.equals(event.getSource())) {
            new Alterar().init();
            this.dispose();
        }
    }

}