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
    @Override
    public String toString(){
        return  "Id : " + id + "\n" +
                "Sender bank account number : " + numberSender + "\n" +
                "Recipient bank account number : " + numberRecipient + "\n" +
                "Transfer amount : " + amount + "\n" +
                "Date of transfer : " + date + "\n";
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
        NodeHistory node = (NodeHistory)o;
        return  this.id == node.id
                && this.amount == node.amount
                && this.numberSender == node.numberSender
                && this.numberRecipient == node.numberRecipient;
    }

    @Override
    public int hashCode() {
        int p = 31;
        int result = 1;
        result = result * p + this.id;
        result = result * p + this.amount;
        result = result * p + this.numberSender;
        result = result * p + this.numberRecipient;
        return result;
    }
}
