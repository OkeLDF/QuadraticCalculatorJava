import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;

public class Calc extends Tela {
    private Double a = 0d, b = 0d, c = 0d;
    private Panel jpExpression, jpTotal, jpResult, jpX, jpX2, jpDelta, jpRoot, jpBtn;
    private JButton btnCalcular = new JButton("CALCULAR: ");
    private Values values = new Values();

    {
        this.jpExpression = new Panel();
        this.jpTotal = new Panel();
        this.jpResult = new Panel();
        this.jpX = new Panel();
        this.jpX2 = new Panel();
        this.jpDelta = new Panel();
        this.jpRoot = new Panel();
        this.jpBtn = new Panel();
    }

    public Calc() {
        this.configuraFrame();
        this.values.configurarValues();
        this.configuarPanel();
    }

    public void definirBtns() {
        this.btnCalcular.setBackground(Color.green);
        this.btnCalcular.addActionListener(this::calcular);
        this.jpBtn.definiTamanho(800, 50);
        this.jpBtn.setLayout(new FlowLayout(FlowLayout.LEFT, 325, 20));
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
        jpX.add(values.getxLabel());
        jpX2.add(values.getx2Label());
        jpDelta.add(values.getDeltaLabel());
        jpRoot.add(values.getRootLabel());
    }

    public void configuarResultPane() {
        this.definirBtns();
        this.configuarValuesPanel();
        this.jpResult.definiTamanho(800, 220);
        this.jpResult.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 10));
        this.jpResult.setOpaque(false);
        this.jpTotal.add(jpResult);
        this.jpTotal.add(jpBtn);
    }

    public void setResult(Double x, Double x2, Double delta) {
        this.values.setxLabelValue(x.toString());
        this.values.setx2LabelValue(x2.toString());
        this.values.setDeltaLabelValue(delta.toString());
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
        this.jpExpression.setOpaque(true);
        this.values.getX().setForeground(Color.black);
        this.values.getXQuadrado().setForeground(Color.black);
        this.values.getZero().setForeground(Color.black);

        this.values.getExpression().forEach(jpExpression::add);
        this.jpTotal.add(jpExpression);
        this.jpTotal.setOpaque(false);
        this.configuarResultPane();
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
        if (!this.catchValue()) return;
        this.setResult(this.a,this.b,this.c);
    }
}
