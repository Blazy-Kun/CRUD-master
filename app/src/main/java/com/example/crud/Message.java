package com.example.crud;

public class Message {
    String student_id;
    String message_id;
    String message;

    public Message(String student_id, String message_id, String message) {
        this.student_id = student_id;
        this.message_id = message_id;
        this.message = message;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
