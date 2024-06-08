package logic;
import java.util.InputMismatchException;


public abstract class AppMenu implements ErrorCodeNumbers{
    public static User currentUser = new User();
    private static boolean isRunning = true;

    public static int mainLoop(){
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

    public static void callLoginMenu(){
        String[] options = {"(1) Login", "(2) Cadastrar", "(0) Sair"};
        int opt = MenuView.requestIntByOptions(options);
        
        switch (opt) {
            case 0:
                isRunning=false;
                return;

            case 1:
                requestLogin();
                return;
        
            default:
                break;
        }
    }

    public static void callMainMenu(){
        String[] options = {"(1) Calculadora", "(2) Histórico", "(3) Editar usuário", "(0) Sair"};
        int opt = MenuView.requestIntByOptions(options);
        
        switch (opt) {
            case 0:
                isRunning=false;
                return;

            case 1:
                Equation eq = requestCoeficients();
                System.out.println(eq + "\n");
                return;
        
            default:
                break;
        }
    }

    public static boolean requestLogin(){
        String name = MenuView.input("Insira um nome: ");
        String password = MenuView.input("Insira a senha: ");

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
        System.out.print("\n\n\033[2J");
    }
}
