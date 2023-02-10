package com.example.wtank3.model;

public class login_response {
    String message;

    public login_response(String message) {
        this.message = message;
    }

    public login_response() {
    }

    public String getMassage() {
        return message;
    }

    public void setMassage(String message) {
        this.message = message;
    }
}
