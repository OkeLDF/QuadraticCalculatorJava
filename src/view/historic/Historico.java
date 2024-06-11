package historic;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;

import calc.Calc;
import menu.Initial;
import menu.Style;

public class Historico implements Style {
    private Calc calc = new Calc();
    private JButton btnProx = new JButton(">");
    private JButton btnAnt = new JButton("<");
    private List<JButton> btns = Arrays.asList(btnAnt, btnProx);
    private int iterator = 0;
    JLabel teste = new JLabel("1");

    public void init() {
        this.calc.init();
        this.configurar();
        this.setarValores();
    }

    public void configurar() {
        teste.setForeground(Style.lightBlueColor);
        teste.setBackground(Style.darkBlueColor);
        teste.setFont(getMathFont());
        this.calc.getPanelBtn().remove(this.calc.getBtnCalc());
        this.calc.getPanelBtn().remove(this.calc.getBtnVolta());

        this.calc.getPanelBtn().remove(this.calc.getBtnCalc());
        this.calc.getValues().getExpression().forEach(calc.getExpressionPane()::remove);
        this.calc.getValues().getExpressionStatic().forEach(calc.getExpressionPane()::add);
        this.calc.getExpressionPane().setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.btns.forEach((e) -> {
            e.setBackground(Style.lightBlueColor);
            e.setForeground(Style.darkBlueColor);
        });

        calc.getPanelBtn().setLayout(new FlowLayout(FlowLayout.LEFT, 105, 20));
        calc.getPanelBtn().add(calc.getBtnVolta());
        calc.getPanelBtn().add(this.btnAnt);
        calc.getPanelBtn().add(this.teste);
        calc.getPanelBtn().add(this.btnProx);
        this.btns.forEach(e -> e.addActionListener(this::navegar));
    }

    public void navegar(ActionEvent e) {
        List<List<String>> historyEntries = Initial.currentUser.getHistoryEntries();

        if (this.btnProx.equals(e.getSource())) {
            if (this.iterator >= historyEntries.size() - 1)
                return;
            this.iterator++;
            this.teste.setText("" + (this.iterator + 1));
            this.setarValores();
        }
        if (this.btnAnt.equals(e.getSource())) {
            if (this.iterator == 0)
                return;
            this.iterator--;
            this.teste.setText("" + (this.iterator + 1));
            this.setarValores();
        }
    }

    public void setarValores() {
        List<List<String>> historyEntries = Initial.currentUser.getHistoryEntries();

        String valorA = "";
        String valorB = "";
        String valorC = "";
        String valorDelta = "";
        String valorXLinha1 = "";
        String valorXLinha2 = "";
        String valorRootValue = "";

        if (historyEntries != null) {
            // if (this.iterator < 0)
            // return;

            // if (this.iterator >= historyEntries.size()-1)
            // return;

            List<String> test = historyEntries.get(this.iterator);
            valorA = test.get(0);
            valorB = test.get(1);
            valorC = test.get(2);
            valorDelta = test.get(3);
            valorXLinha1 = test.get(4);
            valorXLinha2 = test.get(5);
            valorRootValue = test.get(6);
        }

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
