import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Values {
    private JTextField a, b, c;
    private List<JTextField> field = Arrays.asList(a = new JTextField(), b = new JTextField(), c = new JTextField());
    private JLabel x = new JLabel("x+"), x2 = new JLabel("x²+"), zero = new JLabel("=0");
    private JLabel xLabel = new JLabel("X' = ");
    private JLabel yLabel = new JLabel("X'' '= ");
    private JLabel deltaLabel = new JLabel("<html>&#916;</hmtl>" + "= ");
    private JLabel rootLabel = new JLabel("Numero de Raízes= ");
    private List<JLabel> labels = Arrays.asList(xLabel, yLabel, deltaLabel, rootLabel, x, x2, zero);

    public void Value() {

    }

    public void valuesExpression() {

        this.field.forEach(e -> {
            e.setFont(new Font("Arial Black", Font.ITALIC, 36));
            e.setForeground(Color.white);
            e.setBackground(Color.black);
            e.setPreferredSize(new Dimension(100, 40));
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

    public JLabel getx2Label() {
        return yLabel;
    }

    public JLabel getxLabel() {
        return xLabel;
    }

    public List<JComponent> getExpression(){
         List<JComponent> expressao = Arrays.asList(a,x2,b,x,c,zero);
         return expressao;
    }

    public void setRootLabel(Integer value) {
        this.rootLabel.setText("Numero de Raízes= " + value);
    }

    // public void setExpressao(Double a, Double b, Double c) {
    //     // "ax²+bx+c=0"
    //     this.expressao.setText(a + "x²" + "+" + b + "x" + c + "=0");
    // }

    public void setxLabelValue(String valor) {
        this.xLabel.setText("X= " + valor);
    }

    public void setx2LabelValue(String valor) {
        this.yLabel.setText("X''= " + valor);
    }

    public void setDeltaLabelValue(String valor) {
        this.deltaLabel.setText("<html>&#916;</hmtl>" + "= " + valor);
    }

    public void configurarValues() {
        this.labels.forEach(e -> {
            e.setFont(new Font("Arial Black", Font.ITALIC, 36));
            e.setForeground(Color.white);
        });
    }
}
