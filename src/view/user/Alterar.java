package user;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import menu.TelaInicial;

public class Alterar extends Login {

    public void voltar(ActionEvent event) {
        if (event.getSource() == this.bntVolta) { // bnt de volta
            this.dispose();
            new TelaInicial().init(new Login());

        }
    }

    public void init(){
        super.init();
        this.bntClick.setText("EDIT");
    }

    public void prosseguir(ActionEvent event) {

        String senha = new String(this.txtpSenha.getPassword()); // converte o Password para s
        String user = new String(this.txtUsuario.getText()); // converte o Password para s

        if (event.getSource() == this.bntClick) {
            // validade = algo
            if (!(senha.equals("")) || !(user.equals(""))) {
                this.dispose();
                new TelaInicial().init(this);
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
