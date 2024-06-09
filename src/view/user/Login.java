package user;

import java.awt.Color;
import java.awt.Dimension;
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

import menu.TelaInicial;

public class Login extends JFrame {
    private int tentativas;
    JPanel jpnLogin;
    JLabel lblSenha, lblUsuario;
    JTextField txtUsuario;
    JPasswordField txtpSenha;
    JButton bntClick, bntVolta;
    JPanel fundo = new JPanel();

    public Login() {
    }

    public void init() {
        configurarJanela();
        login();
        this.setVisible(true);
    }

    public void configurarJanela() {
        this.add(this.fundo);
        this.fundo.setBackground(Color.black);
        this.setSize(1000, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(this);
    }

    public void components() {
        jpnLogin = new JPanel();
        lblUsuario = new JLabel("USUARIO:");
        lblSenha = new JLabel("SENHA");
        txtUsuario = new JTextField();
        txtpSenha = new JPasswordField();
        bntClick = new JButton("OK");
        bntVolta = new JButton("VOLTAR");
    }

    public void login() {
        this.components();
        List<JComponent> components = Arrays.asList(lblUsuario, txtUsuario, lblSenha, txtpSenha, bntVolta, bntClick);
        List<JButton> btns = Arrays.asList(bntVolta, bntClick);
        this.jpnLogin.setPreferredSize(new Dimension(400, 600));
        this.jpnLogin.setBackground(Color.black);
        this.jpnLogin.setOpaque(false);
        this.fundo.add(this.jpnLogin);

        this.lblUsuario.setForeground(Color.green);
        this.lblUsuario.setFont(new Font("Arial Black", Font.PLAIN, 16));
        this.lblUsuario.setPreferredSize(new Dimension(150, 200));
        this.txtUsuario.setPreferredSize(new Dimension(207, 25));
        this.txtUsuario.setFont(new Font("Arial black", Font.BOLD, 12));
        this.txtUsuario.setBackground(Color.black);
        this.txtUsuario.setForeground(Color.green);

        this.lblSenha.setForeground(Color.green);
        this.lblSenha.setFont(new Font("Arial Black", Font.PLAIN, 16));
        this.lblSenha.setPreferredSize(new Dimension(150, 170));
        this.txtpSenha.setFont(new Font("Arial Black", Font.BOLD, 12));
        this.txtpSenha.setPreferredSize(new Dimension(207, 25));
        this.txtpSenha.setBackground(Color.black);
        this.txtpSenha.setForeground(Color.green);

        btns.forEach((e) -> {
            e.setPreferredSize(new Dimension(120, 20));
            e.setBackground(Color.green);
        });

        components.forEach(e -> jpnLogin.add(e));

        this.bntVolta.addActionListener(this::voltar);
        this.bntClick.addActionListener(this::prosseguir);
    }

    public void prosseguir(ActionEvent event) {

        String senha = new String(this.txtpSenha.getPassword()); // converte o Password para s
        Integer validade = 0;
        if (event.getSource() == this.bntClick) {
            // validade = algo
            if (senha.equals("123")) { // 3 é a soma da validade da senha(1) e do usuario(2);
                this.dispose();
                TelaInicial telaInicial = new TelaInicial();
                telaInicial.init(this);
            } else {
                this.tentativas++; // 3 tentativas até Aparece a mensagem //
                if (this.tentativas == 3) {
                    JOptionPane.showMessageDialog(rootPane, "Usuario ou senha incorretos", "Tente Novamente",
                            JOptionPane.WARNING_MESSAGE);
                    this.tentativas = 0;
                }
            }

        }
    }

    public void voltar(ActionEvent event) {
        if (event.getSource() == this.bntVolta) { // bnt de volta
            this.dispose();
            new TelaInicial();

        }
    }
}