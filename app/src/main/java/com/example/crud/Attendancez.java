package com.example.crud;

public class Attendancez {

String id;
String lec;

    String location;
    String Date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLec() {
        return lec;
    }

    public void setLec(String lec) {
        this.lec = lec;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Attendancez(String id, String lec, String location, String date) {
        this.id = id;
        this.lec = lec;
        this.location = location;
        Date = date;
    }
}
