import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;

public abstract class AppMenu implements ErrorCodeNumbers{
    public static User currentUser = new User();
    private static boolean isRunning = true;

    public static int mainLoop() throws IOException{
        clearScreen();
        do{
            while(!currentUser.isLogged()){
                callLoginMenu();
                if(!isRunning) return SUCCESS;
                }
                
            callMainMenu();

        } while(isRunning);

        return SUCCESS;
    }

    public static void callLoginMenu() throws IOException{
        String[] options = {"(1) Login", "(2) Cadastrar", "(0) Sair"};
        int opt = MenuView.requestIntByOptions(options);
        
        switch (opt) {
            case 0:
                isRunning=false;
                return;

            case 1:
                requestLogin();
                return;

            case 2:
                requestSignIn();
                clearScreen();
                return;
        
            default:
                break;
        }
    }

    public static void callMainMenu() throws IOException{
        String[] options = {
            "(1) Calculadora",
            "(2) Histórico",
            "(3) Editar usuário",
            "(4) Logout",
            "(0) Sair"
        };
        int opt = MenuView.requestIntByOptions(options);

        switch (opt) {
            case 0:
                isRunning=false;
                return;

            case 1:
                Equation eq = requestCoeficients();
                currentUser.saveOnHistory(eq);
                System.out.println(eq + "\n");
                return;

            case 4:
                currentUser.setLogged(false);
                clearScreen();
                return;
        
            default:
                break;
        }
    }

    public static boolean requestLogin() throws IOException{
        String name="", password=""; 

        do {
            name = MenuView.input("Insira um nome: ");
        } while(name.strip().equals(""));
        do {
            password = MenuView.input("Insira a senha: ");
        } while(password.strip().equals(""));

        clearScreen();

        int returnCode = currentUser.login(name, password, "users.csv");
        
        if(returnCode==USER_NOT_FOUND){
            System.out.println("Usuário não encontrado!\n");
            return false;
        }

        if(returnCode==WRONG_PASSWORD){
            System.out.println("Senha incorreta!\n");
            return false;
        }

        return true;
    }

    public static void requestSignIn() throws IOException{
        String name="", password=""; 
        
        do {
            name = MenuView.input("Insira um nome: ");
        } while(name.strip().equals(""));
        do {
            password = MenuView.input("Insira a senha: ");
        } while(password.strip().equals(""));
        
        BufferedWriter writer = new BufferedWriter(
            new FileWriter(User.usersFileName.toString(), true));
        writer.write(name + "," + password + "\n");
        writer.close();
    }

    public static Equation requestCoeficients(){
        clearScreen();
        System.out.println("ax² + bx + c = 0");

        double a=0, b=0, c=0;
        try{
            a = MenuView.inputDouble("Insira o valor do coeficiente \033[1ma\033[m: ");
            b = MenuView.inputDouble("Insira o valor do coeficiente \033[1mb\033[m: ");
            c = MenuView.inputDouble("Insira o valor do coeficiente \033[1mc\033[m: ");
        }
        catch(InputMismatchException e){
            clearScreen();
            System.out.println("Use somente números!\n");
            return null;
        }
        clearScreen();
        return new Equation(a,b,c);
    }

    public static void clearScreen(){
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\033[2J");
    }
}