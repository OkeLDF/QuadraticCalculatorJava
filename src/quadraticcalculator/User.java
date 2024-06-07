import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class User implements ErrorCodeNumbers{
    private String name;
    private String password;
    private String historyFileName;
    private boolean isLogged;

    public User(){
        this.isLogged = false;
    }

    public int login(String name, String password, String fileToSearch){
        /* Pendente:
         * buscar nomes e senhas disponíveis em arquivo csv
        */

        this.name = name;
        this.password = password;
        
        if(!name.equals("okeldf")){
            this.isLogged = false;
            return USER_NOT_FOUND;
        } // usuário não encontrado

        if(!password.equals("123")){
            this.isLogged = false;
            return WRONG_PASSWORD;
        }// senha incorreta
        
        this.isLogged = true;
        this.historyFileName = this.name + "_history.csv";
        return SUCCESS; // sucesso
    }

    public boolean createHistoryFile(){
        return false;
    }

    public boolean saveOnHistory(Equation equation){
        if(equation==null) return false;

        Path path = Paths.get("..", "..", "files", historyFileName);

        return true;
    }

    public String toString(){
        return "\033[33m" + this.name + "\033[m: " +
         (this.isLogged?"\033[32mactive":"\033[31minactive") + "\033[m";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHistoryFileName() {
        return historyFileName;
    }

    public void setHistoryFileName(String historyFileName) {
        this.historyFileName = historyFileName;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }
    
}
