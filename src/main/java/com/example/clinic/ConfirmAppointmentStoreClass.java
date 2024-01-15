package com.example.clinic;

public class ConfirmAppointmentStoreClass {
    String patientname,mob,em,date,t1,t2,appointmentStatus1;

    public ConfirmAppointmentStoreClass(String patientname, String mob, String em, String date, String t1, String t2, String appointmentStatus1) {
        this.patientname = patientname;
        this.mob = mob;
        this.em = em;
        this.date = date;
        this.t1 = t1;
        this.t2 = t2;
        this.appointmentStatus1 = appointmentStatus1;
    }

    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname;
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

    public String getAppointmentStatus1() {
        return appointmentStatus1;
    }

    public void setAppointmentStatus1(String appointmentStatus1) {
        this.appointmentStatus1 = appointmentStatus1;
    }
}
