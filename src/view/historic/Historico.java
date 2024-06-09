package historic;

import calc.Calc;
import menu.TelaInicial;

public class Historico {
    private Calc calc = new Calc();

    public void init(TelaInicial telaInicial) {
        calc.init(telaInicial);
        configurar();
    }

    public void configurar() {
        this.calc.getPanelBtn().remove(this.calc.getBtnCalc());
        this.calc.getValues().getExpression().forEach(calc.getExpressionPane()::remove);
        this.calc.getValues().getExpressionStatic().forEach(calc.getExpressionPane()::add);
    }

    public void show() {

    }

}
