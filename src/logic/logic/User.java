package logic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User implements ErrorCodeNumbers{
    private String name;
    private String password;
    private String historyFileName;
    private boolean isLogged;
    private Path historyPath;
    public static Path usersFileName;

    public User() {
        this.isLogged = false;
        usersFileName = Paths.get("files", "users.csv");
    }

    public static boolean searchUser(String userName) throws IOException{
        File fp = new File(usersFileName.toString());
        if(!fp.exists()) fp.createNewFile();

        for(String line : Files.readAllLines(usersFileName)){
            String candidate = line.split(",")[0].toLowerCase();
            if(candidate.equals(userName.toLowerCase())) return true;
        }
        return false;
    }

    public int login(String name, String password) throws IOException{
        List<String> lines = Files.readAllLines(usersFileName);

        this.name = name;
        this.password = password;
        boolean userExists = false;
        String userCredentials = "";

        for(String line : lines){
            if(line.contains(name)){
                userExists = true;
                userCredentials = line;
                break;
            }
        }

        if(!userExists){
            this.isLogged = false;
            return USER_NOT_FOUND;
        } // usuário não encontrado

        if(!password.equals(userCredentials.split(",")[1])){
            this.isLogged = false;
            return WRONG_PASSWORD;
        } // senha incorreta

        this.isLogged = true;
        this.historyFileName = this.name + "_history.csv";

        this.historyPath = Paths.get("files", historyFileName);
        return SUCCESS; // sucesso
    }

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

    public List<List<String>> getHistoryEntries() throws IOException{
        List<String> rawHistory = Files.readAllLines(this.historyPath);
        List<List<String>> splitHistory = new ArrayList<List<String>>();

        for(String line : rawHistory){
            splitHistory.add(Arrays.asList(line.split(",")));
        }
        
        if(splitHistory.size()==0) return null;
        return splitHistory;
    }

    public void clearHistory() throws IOException{
        BufferedWriter writer = new BufferedWriter(
            new FileWriter(this.historyPath.toString(), false));
        writer.write("");
        writer.close();
    }

    public int updateName(String newName) throws IOException{
        if(newName.contains(",")) return COMMA_IN_STRING;
        if(searchUser(newName)){
            if(!newName.toLowerCase().equals(this.name.toLowerCase())){
                return USER_ALREADY_EXISTS;
            }
        }
        
        List<String> newLines = new ArrayList<String>();

        for(String line : Files.readAllLines(usersFileName)){
            String userName = line.split(",")[0];

            if(userName.equals(this.getName())){
                newLines.add(line.replace(this.getName(), newName));
                continue;
            }
            newLines.add(line);
        }

        this.setName(newName);
        Files.write(usersFileName, newLines);

        File fp = new File(this.historyPath.toString());
        this.historyFileName = this.name + "_history.csv";
        this.historyPath = Paths.get("files", historyFileName);
        fp.renameTo(new File(this.historyPath.toString()));

        MenuView.input(this.historyFileName + " - " + this.historyPath.toString());

        return SUCCESS;
    }

    public int updatePassword(String newPassword) throws IOException{
        if(newPassword.contains(",")) return COMMA_IN_STRING;
        
        List<String> newLines = new ArrayList<String>();

        for(String line : Files.readAllLines(usersFileName)){
            String userName = line.split(",")[0];
            String userPassword = line.split(",")[1];

            if(userPassword.equals(this.getPassword())
               && userName.equals(this.getName())){
                newLines.add(line.replace(this.getPassword(), newPassword));
                continue;
            }
            newLines.add(line);
        }

        this.setPassword(newPassword);
        Files.write(usersFileName, newLines);
        return SUCCESS;
    }

    public String toString(){
        return "\033[33m" + this.name + "\033[m: " +
                (this.isLogged ? "\033[32mactive" : "\033[31minactive") + "\033[m";
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
