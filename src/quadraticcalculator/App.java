import java.util.Scanner;

public class App {

    @SuppressWarnings("resource")
    public static String input(String message){
        Scanner scan = new Scanner(System.in);
        System.out.print(message);
        String buf = scan.nextLine();
        return buf.strip();
    }

    public static void main(String[] args) {
        Equation equation = new Equation(1, 2, -6);
        
        String name = input("Insira um nome: ");
        String password = input("Insira a senha: ");

        User user = new User();
        user.login(name, password, "users.csv");

        System.out.println("\n" + equation);
        System.out.println(user);
    }
}
