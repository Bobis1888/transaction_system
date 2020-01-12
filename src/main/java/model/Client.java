package model;

import java.io.Serializable;
import java.util.ArrayList;


public class Client implements Serializable {
    private int id;
    private String clientName;
    private String gender;
    private int age;
    private String password;
    private ArrayList<BankAccount> bankAccounts;
    private boolean active;

    public Client(){
        bankAccounts = new ArrayList<>();
    }
    Client(String clientName,String gender,int age){
        this();
        this.gender = gender;
        this.age = age;
        this.clientName = clientName;
    }
    @Override
    public String toString(){
        return "Name : " + clientName + " Age : " + age + " Gender : " + gender.toUpperCase();
    }

    @Override
    public int hashCode() {
        int p = 31;
        int result = 1;
        result = result * p + this.id;
        result = result * p + this.age;
        result = result * p + this.clientName.length();
        result = result * p + this.password.length();
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
                && this.password.equals(client.password)
                && this.clientName.equals(client.clientName)
                && this.active == client.active;
    }

    public ArrayList<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void addBankAccount(BankAccount bankAccount){
        bankAccounts.add(bankAccount);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
