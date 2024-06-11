package menu;

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

public class Menu extends JFrame {

    protected JPanel jpConfirma, jpCalc, jpVolta;
    protected JPanel panel;
    protected JButton btnHistorico, btnCalcular, bntLogout, bntEdit;

    public Menu() {

    }

    public void init() {
        configurarJanela();
        configurarPanel();
        this.setVisible(true);
    }

    public void init(Initial inicio) {
        configurarJanela();
        configurarPanel();
        this.setVisible(true);
    }

    protected void configurarJanela() {
        panel = new JPanel();
        this.panel.setBackground(Style.darkGreenColor);
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

        this.jpCalc.setLayout(new FlowLayout(FlowLayout.LEFT, 70, 40));
        this.jpCalc.setPreferredSize(new Dimension(490, 500));
        this.jpCalc.setBackground(Style.darkGreenColor);
        this.jpCalc.setOpaque(true);

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
            btn.setFont(new Font("Arial Black", Font.PLAIN, 14));
            btn.setForeground(Style.darkGreenColor);
            btn.setBackground(Style.lightGreenColor);
            btn.setFocusPainted(false);
            btn.setPreferredSize(new Dimension(140, 30));
            btn.addActionListener(this::escolha);
        });

    }

    private void escolha(ActionEvent event) {
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