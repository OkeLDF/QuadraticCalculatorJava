package user;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import menu.Initial;
import menu.Menu;

public class Alterar extends Login {

    public void voltar(ActionEvent event) {
        if (event.getSource() == this.btnAction) { // bnt de volta
            this.dispose();
            new Menu().init();

        }
    }

    public void init() {
        super.init();
        this.bntInside.setText("EDIT");
    }

    public void prosseguir(ActionEvent event) {
        String senha = new String(this.txtpSenha.getPassword()); // converte o Password para s
        String nome = new String(this.txtUsuario.getText()); // converte o Nome para s
        int errorCode = -1;

        if (event.getSource() == this.bntInside) {
            if(!senha.equals("")){
                errorCode = Initial.currentUser.updatePassword(senha);

                if(errorCode == INVALID_CHARACTER){
                    JOptionPane.showMessageDialog(rootPane, "Não use barra ou contrabarra no nome ou senha!", "Tente Novamente",
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
                    this.dispose();
                    new Menu().init();
                }

            }

            else if(!nome.equals("")){
                errorCode = Initial.currentUser.updateName(nome);

                if(errorCode == COMMA_IN_STRING){
                    JOptionPane.showMessageDialog(rootPane, "Não use vírgula no nome ou senha!", "Tente Novamente",
                        JOptionPane.WARNING_MESSAGE);
                }
        
                else if(errorCode == USER_ALREADY_EXISTS){
                    JOptionPane.showMessageDialog(rootPane, "Esse nome de usuário já está sendo utilizado!", "Tente Novamente",
                        JOptionPane.WARNING_MESSAGE);
                }
        
                else if(errorCode == SUCCESS){
                    this.dispose();
                    new Menu().init();
                }
            }

            else {
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
