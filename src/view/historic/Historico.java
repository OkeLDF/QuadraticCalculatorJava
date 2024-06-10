package historic;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Arrays;

import javax.swing.JButton;

import calc.Calc;
import menu.Initial;
import menu.Menu;

public class Historico {
    private Calc calc = new Calc();
    private JButton btnProx = new JButton(">");
    private JButton btnAnt = new JButton("<");
    private List<JButton> btns = Arrays.asList(btnAnt, btnProx);
    private int iterator = 0;

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
        this.btns.forEach(e -> e.addActionListener(this::navegar));

    }

    public void navegar(ActionEvent e) {
        if (this.btnProx.equals(e.getSource())) {
            this.iterator--;
            this.setarValores();
        }
        if(this.btnAnt.equals(e.getSource())){
            this.iterator++;
            this.setarValores();
        }
    }

    public void setarValores() {
        List<List<String>> historyEntries = Initial.currentUser.getHistoryEntries();

        if(this.iterator<0) this.iterator = historyEntries.size()-1;
        if(this.iterator>=historyEntries.size()) this.iterator = 0;
        
        List<String> test = historyEntries.get(this.iterator);

        String valorA = test.get(0);
        String valorB = test.get(1);
        String valorC = test.get(2);
        String valorDelta = test.get(3);
        String valorXLinha1 = test.get(4);
        String valorXLinha2 = test.get(5);
        String valorRootValue = test.get(6);

        this.calc.getValues().getXQuadrado().setText(valorA + "x²");
        this.calc.getValues().getX().setText(" + " + valorB + "x");
        this.calc.getValues().getZero().setText(" + " + valorC + " = 0");
        this.calc.getValues().getDeltaLabel().setText("<html>&#916;</hmtl>" + " = " + valorDelta);
        this.calc.getValues().getXLinha1().setText("X' = " + valorXLinha1);
        this.calc.getValues().getXLinha2().setText("X'' = " + valorXLinha2);
        this.calc.getValues().getRootLabel().setText("Número de raízes = " + valorRootValue);
    }

    public void show() {

    }

}
