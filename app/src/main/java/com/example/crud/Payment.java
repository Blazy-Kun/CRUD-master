package com.example.crud;

public class Payment {


    String amount;
    String subject;
    String  id;

    public Payment(String amount, String subject, String id) {
        this.amount = amount;
        this.subject = subject;
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
