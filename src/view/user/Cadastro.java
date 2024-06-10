package user;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import menu.Initial;
import menu.Menu;

public class Cadastro extends Login {

    public void init() {
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
        if (event.getSource() == this.bntInside) {
            this.dispose();
            Menu telaInicial = new Menu();
            telaInicial.init(this);

        }
    }

}
