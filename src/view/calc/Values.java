package calc;

import java.awt.Dimension;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import menu.Style;

public class Values implements Style{
    private JTextField a, b, c;
    private List<JTextField> field = Arrays.asList(a = new JTextField(), b = new JTextField(), c = new JTextField());
    private JLabel x = new JLabel("x + "), x2 = new JLabel("x² + "), zero = new JLabel("= 0");
    private JLabel xLinha1 = new JLabel("X' = ");
    private JLabel xLinha2 = new JLabel("X'' = ");
    private JLabel aLabel = new JLabel("2"), bLabel = new JLabel("2"), cLabel = new JLabel("2");

    public JLabel getcLabel() {
        return cLabel;
    }

    public void setcLabel(Double valor) {
        this.cLabel.setText("" + valor);
    }

    public JLabel getbLabel() {
        return bLabel;
    }

    public void setbLabel(Double valor) {
        this.bLabel.setText("" + valor);
    }

    public JLabel getaLabel() {
        return aLabel;
    }

    public void setaLabel(Double valor) {
        this.aLabel.setText("" + valor);
    }

    private JLabel deltaLabel = new JLabel("<html>&#916;</hmtl>" + " = ");
    private JLabel rootLabel = new JLabel("Numero de Raízes = ");
    private List<JLabel> labels = Arrays.asList(xLinha1, xLinha2, deltaLabel, rootLabel, x, x2, zero, aLabel, bLabel,
            cLabel);

    public void Value() {

    }

    public void valuesExpression() {
        this.field.forEach(e -> {
            e.setFont(getMathFont());
            e.setForeground(Style.lightBlueColor);
            e.setBackground(Style.darkBlueColor);
            e.setPreferredSize(new Dimension(100, 40));
        });
    }

    public void configurarValues() {
        this.labels.forEach(e -> {
            e.setFont(getMathFont());
            e.setForeground(Style.lightBlueColor);
        });
    }

    public List<JTextField> getField() {
        return field;
    }

    public JTextField getA() {
        return a;
    }

    public JTextField getB() {
        return b;
    }

    public JTextField getC() {
        return c;
    }

    public JLabel getX() {
        return x;
    }

    public JLabel getXQuadrado() {
        return x2;
    }

    public JLabel getZero() {
        return zero;
    }

    public JLabel getRootLabel() {
        return rootLabel;
    }

    public JLabel getDeltaLabel() {
        return deltaLabel;
    }

    public JLabel getXLinha2() {
        return xLinha2;
    }

    public JLabel getXLinha1() {
        return xLinha1;
    }

    public List<JComponent> getExpression() {
        List<JComponent> expressao = Arrays.asList(a, x2, b, x, c, zero);
        return expressao;
    }

    public List<JComponent> getExpressionStatic() {
        List<JComponent> expressao = Arrays.asList(x2, x, zero);
        return expressao;
    }

    public void setRootLabel(Integer value) {
        this.rootLabel.setText("Numero de Raízes = " + value);
    }

    public void setxLabelValue(String valor) {
        this.xLinha1.setText("X' = " + valor);
    }

    public void setx2LabelValue(String valor) {
        this.xLinha2.setText("X'' = " + valor);
    }

    public void setDeltaLabelValue(String valor) {
        this.deltaLabel.setText("<html>&#916;</hmtl>" + " = " + valor);
    }

}
