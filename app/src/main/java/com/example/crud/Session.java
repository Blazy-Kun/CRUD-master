package com.example.crud;

public class Session {
    String userid;
    String Subject;
    String amount;
    String level;
    String tutor;
    String date;

    public Session(String userid, String subject, String amount, String level, String tutor, String date) {
        this.userid = userid;
        Subject = subject;
        this.amount = amount;
        this.level = level;
        this.tutor = tutor;
        this.date = date;
    }


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
