package logic;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;

public abstract class AppMenu implements ErrorCodeNumbers {
    public static User currentUser = new User();
    private static boolean isRunning = true;

    public static int mainLoop() throws IOException{
        MenuView.clearScreen();
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
                return;
        
            default:
                break;
        }
    }

    public static void callHistory() throws IOException{
        List<List<String>> historyEntries = currentUser.getHistoryEntries();
        
        if(historyEntries==null){
            MenuView.clearScreen();
            System.out.println("Histórico vazio!\n");
            return;
        }

        int historySize = historyEntries.size();
        Equation eq = new Equation();
        
        for(int i=0;;){
            MenuView.clearScreen();
            eq.setCoeficients(
                Double.parseDouble(historyEntries.get(i).get(0)),
                Double.parseDouble(historyEntries.get(i).get(1)),
                Double.parseDouble(historyEntries.get(i).get(2)));
            
            System.out.println("[" + (i+1) + "]\n\n" + eq + "\n");

            String[] options = {"(1) Próximo", "(2) Anterior", "(3) Apagar histórico", "(0) Voltar"};
            int r = MenuView.requestIntByOptions(options);

            switch (r) {
                case 1:
                    if(++i >= historySize) i=0;
                    break;

                case 2:
                    if(--i < 0) i=historySize-1;
                    break;

                case 3:
                    String x = MenuView.input("Apagar histórico? Digite 'apagar': ");
                    if(!x.strip().equals("apagar")) break;
                    currentUser.clearHistory();
                    MenuView.clearScreen();
                    return;
            
                case 0:
                    MenuView.clearScreen();
                    return;

                default:
                    break;
            }
        }
    }

    public static void callEditMenu() throws IOException{
        MenuView.clearScreen();
        System.out.println("EDITAR INFORMAÇÕES DO USUÁRIO:\n");
        System.out.println("Nome  : " + currentUser.getName());
        System.out.println("Senha : " + currentUser.getPassword() + "\n");

        String[] options = {"(1) Editar nome", "(2) Editar senha", "(0) Voltar"};
        int r = MenuView.requestIntByOptions(options);

        switch (r) {
            case 1:
                String newName = MenuView.input("Insira o novo nome: ");
                int errorCodeNum = currentUser.updateName(newName);
                if(errorCodeNum==COMMA_IN_STRING){
                    MenuView.clearScreen();
                    System.out.println("Não use vírgulas!\n");
                    break;
                }
                if(errorCodeNum==USER_ALREADY_EXISTS){
                    MenuView.clearScreen();
                    System.out.println("O usuário já existe!\n");
                    break;
                }
                MenuView.clearScreen();
                break;
                
            case 2:
                String newPassword = MenuView.input("Insira a nova senha: ");
                if(currentUser.updatePassword(newPassword)==COMMA_IN_STRING){
                    MenuView.clearScreen();
                    System.out.println("Não use vírgulas!\n");
                    break;
                }
                MenuView.clearScreen();
                break;

            case 0:
                MenuView.clearScreen();
                break;
        
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

            case 2:
                callHistory();
                return;

            case 3:
                callEditMenu();
                return;

            case 4:
                currentUser.setLogged(false);
                MenuView.clearScreen();
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

        MenuView.clearScreen();

        int returnCode = currentUser.login(name, password);
        
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

        if(User.searchUser(name)){
            MenuView.clearScreen();
            System.out.println("Esse nome já está sendo utilizado!\n");
            return;
        }
        if(name.contains(",")){
            MenuView.clearScreen();
            System.out.println("Não use vírgulas!\n");
            return;
        }

        do {
            password = MenuView.input("Insira a senha: ");
        } while(password.strip().equals(""));
        
        if(password.contains(",")){
            MenuView.clearScreen();
            System.out.println("Não use vírgulas!\n");
            return;
        }

        BufferedWriter writer = new BufferedWriter(
            new FileWriter(User.usersFileName.toString(), true));
        writer.write(name + "," + password + "\n");
        writer.close();
        MenuView.clearScreen();
    }

    public static Equation requestCoeficients(){
        MenuView.clearScreen();
        System.out.println("ax² + bx + c = 0");

        double a=0, b=0, c=0;
        try{
            a = MenuView.inputDouble("Insira o valor do coeficiente 'a': ");
            b = MenuView.inputDouble("Insira o valor do coeficiente 'b': ");
            c = MenuView.inputDouble("Insira o valor do coeficiente 'c': ");
        }
        catch(InputMismatchException e){
            MenuView.clearScreen();
            System.out.println("Use somente números!\n");
            return null;
        }
        MenuView.clearScreen();
        return new Equation(a,b,c);
    }
}