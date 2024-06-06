import java.util.Arrays;
import java.util.List;

public class Equation {
    private List<Double> coeficients;
    private double delta;
    private double firstResult;
    private double secondResult;
    private double rootsQuantity;
    private boolean isImaginaryNumber;


    public Equation(double a, double b, double c){
        List<Double> coeficients = Arrays.asList(a,b,c);
        this.coeficients = coeficients;
        this.calculate();
    }

    public void calculate(){
        double
            a = this.coeficients.get(0),
            b = this.coeficients.get(1),
            c = this.coeficients.get(2);
        
        this.delta = b*b - 4*a*c;

        if(this.delta == 0){
            this.firstResult = -b/(2*a);
            this.secondResult = firstResult;
            this.rootsQuantity = 1;
            this.isImaginaryNumber = false;
            return;
        }

        if(this.delta < 0){
            this.setResults(-b/a, Math.sqrt(-this.delta));
            this.rootsQuantity = 2;
            this.isImaginaryNumber = true;
            return;
        }

        if(a == 0){
            this.firstResult = -c/a;
            this.secondResult = firstResult;
            this.rootsQuantity = 1;
            this.isImaginaryNumber = false;
            return;
        }
        
        this.setResults(
            (Math.sqrt(delta)-b)/(2*a),
            -(Math.sqrt(delta)+b)/(2*a));
        this.rootsQuantity = 2;
        this.isImaginaryNumber = false;
    }

    public String toString(){
        return this.coeficients.get(0) + "x² + " + this.coeficients.get(1) + "x + " + this.coeficients.get(2) + " = 0";
    }

    private void setResults(double firstResult, double secondResult){
        this.firstResult = firstResult;
        this.secondResult = secondResult;
    }

    public List<Double> getCoeficients() {
        return coeficients;
    }

    public void setCoeficients(List<Double> coeficients) {
        this.coeficients = coeficients;
    }

    public double getDelta() {
        return delta;
    }

    public double getFirstResult() {
        return firstResult;
    }

    public double getSecondResult() {
        return secondResult;
    }

    public double getRootsQuantity() {
        return rootsQuantity;
    }

    public boolean isImaginaryNumber() {
        return isImaginaryNumber;
    }
}
