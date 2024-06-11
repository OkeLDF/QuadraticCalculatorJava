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

import user.Cadastro;
import user.Login;

import logic.User;

public class Initial extends JFrame {
    protected JPanel jpConfirma, jpCalc, jpVolta;
    protected JPanel panel;
    protected JButton btnHistorico, btnCadastro, bntLogin, bntEdit;

    public static User currentUser = new User();

    public void init() {
        this.configurarJanela();
        this.configurarPanel();
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

        this.jpCalc.setLayout(new FlowLayout(FlowLayout.LEFT, 70, 90));
        this.jpCalc.setPreferredSize(new Dimension(490, 500));
        this.jpCalc.setBackground(Style.darkGreenColor);
        this.jpCalc.setOpaque(false);

        this.panel.add(this.jpCalc);

        configurarDados();
        this.jpCalc.add(this.btnCadastro);
        this.jpCalc.add(this.bntLogin);

    }

    private void configurarDados() {
        btnCadastro = new JButton("CADASTRO");
        bntLogin = new JButton("LOGIN ");

        List<JButton> bntList = Arrays.asList(btnCadastro, bntLogin);

        bntList.forEach((btn) -> {
            btn.setFont(new Font("Arial Black", Font.BOLD, 14));
            btn.setForeground(Style.darkGreenColor);
            btn.setBackground(Style.lightGreenColor);
            btn.setFocusPainted(false);
            btn.setPreferredSize(new Dimension(140, 30));

        });

        bntList.forEach(e -> e.addActionListener(e1 -> {
            escolha(e1);
        }));

    }

    private void escolha(ActionEvent event) {
        this.dispose();
        if (this.bntLogin.equals(event.getSource())) {
            this.dispose();
            Login login = new Login();
            login.init();
        } else {
            this.dispose();
            Cadastro cadastro = new Cadastro();
            cadastro.init();

        }
    }
}
