package user;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import logic.ErrorCodeNumbers;
import menu.Initial;
import menu.Menu;
import menu.Style;

public class Login extends JFrame implements ErrorCodeNumbers, Style {
    protected int tentativas;
    protected JPanel jpnLogin;
    protected JLabel lblSenha, lblUsuario;
    protected JTextField txtUsuario;
    protected JPasswordField txtpSenha;
    protected JButton bntInside, btnAction;
    protected JPanel fundo = new JPanel();

    public void init() {

        configurarJanela();
        login();
        this.bntInside.setText("LOGAR");
        this.setVisible(true);
    }

    public void configurarJanela() {
        this.add(this.fundo);
        this.fundo.setBackground(Style.darkGreenColor);
        this.setSize(1000, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(this);
    }

    public void components() {
        jpnLogin = new JPanel();
        lblUsuario = new JLabel("USUARIO");
        lblSenha = new JLabel("SENHA");
        txtUsuario = new JTextField();
        txtpSenha = new JPasswordField();
        bntInside = new JButton("LOGAR");
        btnAction = new JButton("VOLTAR");
    }

    public void login() {
        this.components();
        List<JComponent> components = Arrays.asList(lblUsuario, txtUsuario, lblSenha, txtpSenha, btnAction,
                bntInside);
        List<JButton> btns = Arrays.asList(btnAction, bntInside);
        this.jpnLogin.setPreferredSize(new Dimension(400, 600));
        this.jpnLogin.setBackground(Style.darkGreenColor);
        this.jpnLogin.setOpaque(false);

        this.jpnLogin.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 90));

        this.fundo.add(this.jpnLogin);

        this.lblUsuario.setForeground(Style.lightGreenColor);
        this.lblUsuario.setFont(new Font("Arial Black", Font.PLAIN, 16));
        this.lblUsuario.setPreferredSize(new Dimension(150, 20));

        this.txtUsuario.setPreferredSize(new Dimension(207, 25));
        this.txtUsuario.setFont(getPixelFont());
        // this.txtUsuario.setFont(new Font("Arial black", Font.BOLD, 12));
        this.txtUsuario.setBackground(Style.darkGreenColor);
        this.txtUsuario.setForeground(Style.lightGreenColor);

        this.lblSenha.setForeground(Style.lightGreenColor);
        this.lblSenha.setFont(new Font("Arial Black", Font.PLAIN, 16));
        this.lblSenha.setPreferredSize(new Dimension(150, 20));

        this.txtpSenha.setFont(getPixelFont());
        // this.txtpSenha.setFont(new Font("Arial Black", Font.BOLD, 12));
        this.txtpSenha.setPreferredSize(new Dimension(207, 25));
        this.txtpSenha.setBackground(Style.darkGreenColor);
        this.txtpSenha.setForeground(Style.lightGreenColor);

        btns.forEach((e) -> {
            e.setPreferredSize(new Dimension(120, 30));
            e.setBackground(Style.lightGreenColor);
            e.setForeground(Style.darkGreenColor);
        });

        components.forEach(e -> jpnLogin.add(e));

        this.btnAction.addActionListener(this::voltar);
        this.bntInside.addActionListener(e -> {
            prosseguir(e);
        });
    }

    public void prosseguir(ActionEvent event) {
        String nome = new String(this.txtUsuario.getText()); // converte o Password para s
        String senha = new String(this.txtpSenha.getPassword()); // converte o Password para s

        int errorCode = Initial.currentUser.login(nome, senha);

        if (event.getSource() == this.bntInside) {
            // validade = algo
            if (Initial.currentUser.isLogged()) { // 3 é a soma da validade da senha(1) e do usuario(2);
                this.dispose();
                Menu telaInicial = new Menu();
                telaInicial.init();
                return;
            }
            if (errorCode == EMPTY_STRING) {
                JOptionPane.showMessageDialog(rootPane, "Não deixe campos vazios!", "Tente Novamente",
                        JOptionPane.WARNING_MESSAGE);
            } else if (errorCode == COMMA_IN_STRING) {
                JOptionPane.showMessageDialog(rootPane, "Não deixe campos vazios!", "Tente Novamente",
                        JOptionPane.WARNING_MESSAGE);
            } else if (errorCode == WRONG_PASSWORD) {
                JOptionPane.showMessageDialog(rootPane, "Senha incorreta!", "Tente Novamente",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                this.tentativas++; 
                if (this.tentativas == 3) {
                    JOptionPane.showMessageDialog(rootPane, "Usuario ou senha incorretos", "Tente Novamente",
                            JOptionPane.WARNING_MESSAGE);
                    this.tentativas = 0;
                }
            }
        }
    }

    public void voltar(ActionEvent event) {
        if (event.getSource() == this.btnAction) { // bnt de volta
            this.dispose();
            new Initial().init();
        }
    }
}