package security;


import com.sun.istack.internal.NotNull;
import model.Client;

import java.util.Date;

//написать нормальный шифровщик или использовать стандартные либы
public class Encrypt {
    private final static long key = 31;
    private final static Date date = new Date();
    private Encrypt(){}
    @NotNull
    public static String encrypt(int pass){
        long result = pass*key + date.hashCode();
        return String.valueOf(result);
    }
    @NotNull
    public static long decrypt(String s){
        long result = Long.parseLong(s);
        return (result-date.hashCode())/key;
    }
    public static boolean validateAuth(Client client){
        if (client==null)
            return false;
        int id = client.getId();
        long resultDecrypt = decrypt(client.getKeyAuth());
        return id == resultDecrypt;
    }
}
