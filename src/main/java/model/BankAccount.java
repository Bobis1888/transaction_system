package model;

import java.io.Serializable;

public class BankAccount implements Serializable {
    private int accountNumber;
    private int accountBalance;
    private String nameOwner;
    private boolean active;
    @Override
    public String toString(){
        return  "*--Account number :  " + accountNumber + "<br>" +
                "*--Account balance : " + accountBalance + "<br>" +
                "*----Account status :  " + active + "<br>" + "<br>" +
                "*------Name Owner : " + nameOwner + "<br>";
    }

    @Override
    public int hashCode() {
        int p = 31;
        int result = 1;
        result = result * p + accountNumber;
        result = result * p + accountBalance;
        result = result * p + nameOwner.length();
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
        BankAccount account = (BankAccount)o;
        return  this.active == account.active
                && this.nameOwner.equals(account.nameOwner)
                && this.accountNumber == account.accountNumber
                && this.accountBalance == account.accountBalance;
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