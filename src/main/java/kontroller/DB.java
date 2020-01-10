package kontroller;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class DB {
    private String login;
    private String pass;
    private String url;
    private String forName;
    DB(){
        try {
            //переписать путь по нормальному
            File file = new File("/home/bobis1888/Git/transaction_system/src/main/resources/login.properties");
            FileReader fileReader = new FileReader(file);
            Properties properties = new Properties();
            properties.load(fileReader);
            login = properties.getProperty("login");
            pass = properties.getProperty("pass");
            url = properties.getProperty("URL");
            forName = properties.getProperty("forName");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DB db = new DB();
        System.out.println(db.pass);
    }
    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getUrl() {
        return url;
    }

    public String getForName() {
        return forName;
    }
}
