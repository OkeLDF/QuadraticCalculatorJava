package historic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Arrays;

import javax.swing.JButton;

import calc.Calc;
import menu.Menu;

public class Historico {
    private Calc calc = new Calc();
    private JButton btnProx = new JButton(">");
    private JButton btnAnt = new JButton("<");
    private List<JButton> btns = Arrays.asList(btnAnt, btnProx);

    public void init(Menu telaInicial) {
        calc.init(telaInicial);
        configurar();
        this.setarValores();
    }

    public void configurar() {
        this.calc.getPanelBtn().remove(this.calc.getBtnCalc());
        this.calc.getValues().getExpression().forEach(calc.getExpressionPane()::remove);
        this.calc.getValues().getExpressionStatic().forEach(calc.getExpressionPane()::add);
        this.calc.getExpressionPane().setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.btns.forEach((e) -> {
            e.setBackground(Color.green);
        });
        this.btns.forEach(calc.getPanelBtn()::add);
        this.btns.forEach(e -> e.addActionListener(this::acompanhar));

    }

    public void acompanhar(ActionEvent e) {
        if (this.btnProx.equals(e.getSource())) {

        } else {

        }
    }

    public void setarValores() {
        Double valorA = 4d;
        Double valorB = 6d;
        Double valorc = 4d;

        this.calc.getValues().getXQuadrado().setText(valorA + "x²");
        this.calc.getValues().getX().setText("+" + valorB + "x");
        this.calc.getValues().getZero().setText("+" + valorc + "=0");

    }

    public void show() {

    }

}
