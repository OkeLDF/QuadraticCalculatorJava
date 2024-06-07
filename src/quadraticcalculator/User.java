public class User {
    private String name;
    private String password;
    private String historyFileName;
    private boolean isLogged;

    public static final int SUCCESS = 0;
    public static final int USER_NOT_FOUND = 1;
    public static final int WRONG_PASSWORD = 2;

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
