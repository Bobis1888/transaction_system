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
