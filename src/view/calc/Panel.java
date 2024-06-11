package calc;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import menu.Style;

public class Panel extends JPanel {

    public void configurarPanel() {
        this.setPreferredSize(new Dimension(800, 70));
        this.setBackground(Style.lightBlueColor);
        this.setForeground(Style.darkBlueColor);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 20));
        this.setVisible(true);
        this.setOpaque(false);
    }

    public void definiTamanho(Integer x, Integer y) {
        this.setPreferredSize(new Dimension(x, y));
    }

}
