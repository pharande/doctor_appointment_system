package com.example.clinic;

public class NewPatient {
String mobile,pass;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }

    public NewPatient(String mobile, String pass) {
        this.mobile = mobile;
        this.pass = pass;
    }
}
