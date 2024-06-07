public class App {
    public static void main(String[] args) {
        Equation eq = new Equation(1, 2, -6);
        
        System.out.println(eq);

        eq.setCoeficients(2, 2, 2);
        eq.calculate();

        System.out.println(eq);
    }
}
