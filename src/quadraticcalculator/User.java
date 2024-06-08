import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class User implements ErrorCodeNumbers{
    private String name;
    private String password;
    private String historyFileName;
    private boolean isLogged;
    Path historyPath;

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

        this.historyPath = Paths.get("files", historyFileName);
        return SUCCESS; // sucesso
    }

    // public boolean createHistoryFile() throws IOException{
    //     Files.createFile(this.historyPath);
    //     return this.isHistoryCreated();
    // }

    public boolean saveOnHistory(Equation equation) throws IOException{
        if(equation==null) return false;
        File fp = new File(this.historyPath.toString());
        if(!fp.exists()) fp.createNewFile();
        
        BufferedWriter writer = new BufferedWriter(
            new FileWriter(this.historyPath.toString(), true));
        
        writer.write(
            equation.getA() + "," +
            equation.getB() + "," +
            equation.getC() + "," +
            equation.getDelta() + "," +
            equation.getFirstResult() + "," +
            equation.getSecondResult() + "," +
            equation.getRootsQuantity() + "\n"
        );
        writer.close();

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

    // public boolean isHistoryCreated(){
    //     Path path = Paths.get(historyPath.toString());
    //     if(path == null) return false;
    //     return true;
    // }

    public void setLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }
    
}
