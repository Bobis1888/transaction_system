package model;

import java.io.Serializable;
import java.util.ArrayList;


public class Client implements Serializable {
    private String nameClient;
    private String gender;
    private int age;
    private ArrayList<BankAccount> bankAccounts;

    Client(){
        bankAccounts = new ArrayList<>();
    }
    Client(String nameClient,String gender,int age,BankAccount bankAccount){
        this();
        this.gender = gender;
        this.age = age;
        this.nameClient = nameClient;
        addBankAccount(bankAccount);
    }

    public ArrayList<BankAccount> getBankAccounts() {
        return bankAccounts;
    }
    public void addBankAccount(BankAccount bankAccount){
        bankAccounts.add(bankAccount);
    }
    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getNameClient() {
        return nameClient;
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
}
