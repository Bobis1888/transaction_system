package model;

import java.io.Serializable;

public class BankAccount implements Serializable {
    private int accountNumber;
    private int accountBalance;
    private String nameOwner;
    private boolean active;
    public String toString(){
        return  "Name owner: " + nameOwner + "\n" +
                "Account number : " + accountNumber + "\n" +
                "Account balance : " + accountBalance + "\n" +
                "Account status : " + active + "\n";
    }
    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void addAccountBalance(int amount){
        accountBalance+=amount;
    }
    public void deductAccountBalance(int amount){
        accountBalance-=amount;
    }

    public String getNameOwner() {
        return nameOwner;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}