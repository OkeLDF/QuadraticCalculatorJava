import java.util.Arrays;
import java.util.List;

public class Equation {
    private List<Double> coeficients;
    private double delta;
    private double firstResult;  // x1 ou parte real
    private double secondResult; // x2 ou parte imaginária
    private int rootsQuantity;
    private boolean isImaginaryNumber;

    public Equation(double a, double b, double c){
        this.setCoeficients(a,b,c);
    }

    public Equation(){
        this.setCoeficients(0,0,0);
    }

    public void calculate(){
        double
            a = this.coeficients.get(0),
            b = this.coeficients.get(1),
            c = this.coeficients.get(2);
        
        this.delta = b*b - 4*a*c;

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        if(a == 0 && b == 0 && c == 0){
            this.setResults(0,0);
        }

        if(this.delta == 0){
            this.firstResult = -b/(2*a);
            this.secondResult = firstResult;
            this.rootsQuantity = 1;
            this.isImaginaryNumber = false;
            return;
        }

        if(this.delta < 0){
            this.setResults(-b/(2*a), Math.sqrt(-this.delta)/(2*a));
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

    @Override
    public String toString(){
        String prefix = this.coeficients.get(0) + "x² + " +
            this.coeficients.get(1) + "x + " +
            this.coeficients.get(2) + " = 0\n\n" +
            "discriminante = " + this.delta + "\n\n";
        
        if(this.isImaginaryNumber){
            double res2 = Math.abs(this.secondResult);
            return prefix +
            " x1 = " + this.firstResult + " + " + res2 + " * i\n" +
            " x2 = " + this.firstResult + " - " + res2 + " * i";
        }
        return prefix +
            " x1 = " + this.firstResult + "\n" +
            " x2 = " + this.secondResult + "\n";
    }

    private void setResults(double firstResult, double secondResult){
        this.firstResult = firstResult;
        this.secondResult = secondResult;
    }

    public void setCoeficients(double a, double b, double c) {
        List<Double> coef = Arrays.asList(a,b,c);
        this.coeficients = coef;
    }

    public void setA(double a){
        this.setCoeficients(
            a,
            this.coeficients.get(1),
            this.coeficients.get(2));
    }

    public void setB(double b){
        this.setCoeficients(
            this.coeficients.get(0),
            b,
            this.coeficients.get(2));
    }

    public void setC(double c){
        this.setCoeficients(
            this.coeficients.get(0),
            this.coeficients.get(1),
            c);
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

    public int getRootsQuantity() {
        return rootsQuantity;
    }

    public boolean isImaginaryNumber() {
        return isImaginaryNumber;
    }

    public List<Double> getCoeficients() {
        return coeficients;
    }

    public double getA(){
        return coeficients.get(0);
    }

    public double getB(){
        return coeficients.get(1);
    }

    public double getC(){
        return coeficients.get(2);
    }
}
