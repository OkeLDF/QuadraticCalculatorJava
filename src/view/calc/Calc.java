package calc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

import logic.Equation;
import menu.Menu;

public class Calc extends Tela {
    private Equation equation = new Equation();
    private Double a = 0d, b = 0d, c = 0d;
    private Panel jpExpression, jpTotal, jpResult, jpX, jpX2, jpDelta, jpRoot, jpBtn;
    private JButton btnCalcular;
    private Values values = new Values();
    private Menu telaInicial;

    {
        this.jpExpression = new Panel();
        this.jpTotal = new Panel();
        this.jpResult = new Panel();
        this.jpX = new Panel();
        this.jpX2 = new Panel();
        this.jpDelta = new Panel();
        this.jpRoot = new Panel();
        this.jpBtn = new Panel();
        this.btnCalcular = new JButton("CALCULAR: ");
    }

    public void init(Menu anterior) {
        this.telaInicial = anterior;
        this.configuraFrame();
        this.values.configurarValues();
        this.configuarPanel();
    }

    public void definirBtns() {
        super.configurarBtn();
        this.btnCalcular.setBackground(Color.green);
        this.btnCalcular.addActionListener(this::calcular);
        this.jpBtn.definiTamanho(800, 50);
        this.jpBtn.setLayout(new FlowLayout(FlowLayout.LEFT, 175, 20));
        this.jpBtn.add(btnVolta);
        this.jpBtn.add(btnCalcular);
        this.jpBtn.setOpaque(false);
    }

    public void configuarValuesPanel() {
        List<Panel> configurarResult = Arrays.asList(jpX, jpX2, jpDelta, jpRoot);
        configurarResult.forEach((e) -> {
            e.setOpaque(false);
            e.setBackground(Color.black);
            e.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 0));
            e.definiTamanho(600, 40);
            jpResult.add(e);
        });
        jpX.add(values.getxLinha1());
        jpX2.add(values.getx2Label());
        jpDelta.add(values.getDeltaLabel());
        jpRoot.add(values.getRootLabel());
    }

    public void configuarResultPane() {
        this.configuarValuesPanel();
        this.jpResult.definiTamanho(800, 220);
        this.jpResult.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 10));
        this.jpResult.setOpaque(false);
        this.jpTotal.add(jpResult);
        this.jpTotal.add(jpBtn);
    }

    public void setResult(Double x, Double x2, Double delta, Integer root) {
        if (delta < 0) {
            this.values.setxLabelValue(x + " + " + x2 + "*i");
            this.values.setx2LabelValue(x + " - " + x2 + "*i");
            this.values.setDeltaLabelValue(delta + "");
            this.values.setRootLabel(root);
            return;
        }
        this.values.setxLabelValue(x + "");
        this.values.setx2LabelValue(x2.toString() + "");
        this.values.setDeltaLabelValue(delta.toString() + "");
        this.values.setRootLabel(root);

    }

    public void fatherPanel() {
        this.jpTotal.setPreferredSize(new Dimension(800, 500));
        this.jpTotal.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 20));
        this.add(jpTotal);
    }

    public void configuarPanel() {
        this.fatherPanel();
        this.values.valuesExpression(); // configuar os valores a,b,c da
        this.jpExpression.configurarPanel();
        this.jpExpression.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 20));
        this.jpExpression.setOpaque(false);

        this.values.getExpression().forEach(jpExpression::add);
        this.jpTotal.add(jpExpression);
        this.jpTotal.setOpaque(false);
        this.definirBtns();
        this.configuarResultPane();
    }

    public JPanel getExpressionPane() {
        return this.jpExpression;
    }

    public JPanel getPanelBtn() {
        return this.jpBtn;
    }

    public JButton getBtnCalc() {
        return this.btnCalcular;
    }

    public Values getValues() {
        return this.values;
    }

    public boolean catchValue() {
        try {
            this.a = Double.parseDouble(this.values.getA().getText());
            this.b = Double.parseDouble(this.values.getB().getText());
            this.c = Double.parseDouble(this.values.getC().getText());
            return true;
        } catch (NumberFormatException err) {
            System.out.println(err.getMessage());
            return false;
        }
    }

    public void calcular(ActionEvent e) {
        if (!this.catchValue())
            return;
        this.equation.setCoeficients(this.a, this.b, this.c);

        this.setResult(this.equation.getFirstResult(), this.equation.getSecondResult(), this.equation.getDelta(),
                this.equation.getRootsQuantity());
    }

    @Override
    public void volta(ActionEvent e) {
        this.dispose();
        this.telaInicial.setVisible(true);

    }
}
