package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Client implements Serializable {
    private int id;
    private String clientName;
    private String gender;
    private int age;
    //ключ для проверки аунтетификации
    private String keyAuth;
    private boolean active;
    private List<BankAccount> bankAccounts;


    public Client(){
        bankAccounts = new ArrayList<>();
    }
    @Override
    public String toString(){
        return "Name : " + clientName + "<br> Age : " + age + "<br> Gender : " + gender.toUpperCase();
    }

    @Override
    public int hashCode() {
        int p = 31;
        int result = 1;
        result = result * p + this.id;
        result = result * p + this.age;
        result = result * p + this.clientName.length();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null){
            return false;
        }
        if (this == o){
            return true;
        }
        if (getClass() != o.getClass()){
            return false;
        }
        Client client = (Client)o;
        return  this.id == client.id
                && this.age == client.age
                && this.gender.equals(client.gender)
                && this.clientName.equals(client.clientName);
    }

    public List<BankAccount> getBankAccountBalance(){
        return bankAccounts;
    }
    public void setBankAccountBalance(BankAccount bankAccount){
        bankAccounts.add(bankAccount);
    }
    public void setKeyAuth(String keyAuth){
        this.keyAuth = keyAuth;
    }

    public String getKeyAuth(){
        return this.keyAuth;
    }

    public void setNameClient(String clientName) {
        this.clientName = clientName;
    }

    public String getNameClient() {
        return clientName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
