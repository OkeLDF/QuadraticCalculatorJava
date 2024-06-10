package user;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JOptionPane;

import logic.ErrorCodeNumbers;
import logic.User;
import menu.Initial;

public class Cadastro extends Login implements ErrorCodeNumbers {

    public void init() throws IOException {
        super.init();
        this.configuar();
    }

    public void configuar() {
        this.bntInside.setText("CADASTRAR");
    }

    public void voltar(ActionEvent event) {
        this.dispose();
        new Initial().init();
    }

    public void prosseguir(ActionEvent event) {
        String nome = new String(this.txtUsuario.getText()); // converte o Password para s
        String senha = new String(this.txtpSenha.getPassword()); // converte o Password para s
        int errorCode = -1;

        try {
            errorCode = User.signUp(nome, senha);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(errorCode == EMPTY_STRING){
            JOptionPane.showMessageDialog(rootPane, "Não deixe campos vazios!", "Tente Novamente",
                JOptionPane.WARNING_MESSAGE);
        }

        else if(errorCode == COMMA_IN_STRING){
            JOptionPane.showMessageDialog(rootPane, "Não use vírgula no nome ou senha!", "Tente Novamente",
                JOptionPane.WARNING_MESSAGE);
        }

        else if(errorCode == USER_ALREADY_EXISTS){
            JOptionPane.showMessageDialog(rootPane, "Esse nome de usuário já está sendo utilizado!", "Tente Novamente",
                JOptionPane.WARNING_MESSAGE);
        }

        else if(errorCode == SUCCESS){
            if (event.getSource() == this.bntInside) {
                this.dispose();
                new Initial().init();
            }
        }
    }

}
