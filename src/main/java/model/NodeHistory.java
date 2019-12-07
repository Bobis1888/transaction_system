package model;

import java.io.Serializable;

public class NodeHistory implements Serializable {
    private int id;
    private int numberSender;
    private int numberRecipient;
    private int amount;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberSender() {
        return numberSender;
    }

    public void setNumberSender(int numberSender) {
        this.numberSender = numberSender;
    }

    public int getNumberRecipient() {
        return numberRecipient;
    }

    public void setNumberRecipient(int numberRecipient) {
        this.numberRecipient = numberRecipient;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String toString(){
        return  "Id : " + id + "\n" +
                "Sender bank account number : " + numberSender + "\n" +
                "Recipient bank account number : " + numberRecipient + "\n" +
                "Transfer amount : " + amount + "\n" +
                "Date of transfer : " + date + "\n";
    }
}
