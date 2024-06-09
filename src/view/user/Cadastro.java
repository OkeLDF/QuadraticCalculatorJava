package user;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import menu.TelaInicial;

public class Cadastro extends Login {

    public void init() {
        super.init();
        this.configuar();
    }

    public void configuar() {
        this.btnAction.setText("LOGIN");
    }

    public void action(ActionEvent event) {
        this.dispose();
        new Login().init();
    }
        public void prosseguir(ActionEvent event) {
        if (event.getSource() == this.bntInside) {
                this.dispose();
                TelaInicial telaInicial = new TelaInicial();
                telaInicial.init(this);

        }
    }


}
