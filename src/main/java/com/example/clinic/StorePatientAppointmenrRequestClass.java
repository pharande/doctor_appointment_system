package com.example.clinic;

public class StorePatientAppointmenrRequestClass {
    String pname,mob,em,date,t1,t2,appointmentStatus;
    public StorePatientAppointmenrRequestClass(){}
    public StorePatientAppointmenrRequestClass(String pname, String mob, String em, String date, String t1, String t2,String appointmentStatus) {
        this.pname = pname;
        this.mob = mob;
        this.em = em;
        this.date = date;
        this.t1 = t1;
        this.t2 = t2;
        this.appointmentStatus=appointmentStatus;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getEm() {
        return em;
    }

    public void setEm(String em) {
        this.em = em;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }
}
