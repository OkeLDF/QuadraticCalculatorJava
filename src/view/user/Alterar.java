package user;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import menu.Menu;

public class Alterar extends Login {

    public void voltar(ActionEvent event) {
        if (event.getSource() == this.btnAction) { // bnt de volta
            this.dispose();
            new Menu().init(new Login());

        }
    }

    public void init() {
        super.init();
        this.bntInside.setText("EDIT");
    }

    public void prosseguir(ActionEvent event) {

        String senha = new String(this.txtpSenha.getPassword()); // converte o Password para s
        String user = new String(this.txtUsuario.getText()); // converte o Password para s

        if (event.getSource() == this.bntInside) {
            // validade = algo
            if (!(senha.equals("")) || !(user.equals(""))) {
                this.dispose();
                new Menu().init(this);
            } else {
                this.tentativas++; // 3 tentativas até Aparece a mensagem //
                if (this.tentativas == 3) {
                    JOptionPane.showMessageDialog(rootPane, "Usuario ou senha vazios", "Tente Novamente",
                            JOptionPane.WARNING_MESSAGE);
                    this.tentativas = 0;
                }
            }

        }
    }
}
