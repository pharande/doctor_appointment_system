package com.example.clinic;

public class FeedbackFormStore {
  String mob1,name1,feedback1,helpful,design,userfrend;

    public FeedbackFormStore(String mob1, String name1, String feedback1, String helpful, String design, String userfrend) {
        this.mob1 = mob1;
        this.name1 = name1;
        this.feedback1 = feedback1;
        this.helpful = helpful;
        this.design = design;
        this.userfrend = userfrend;
    }
    public FeedbackFormStore(){}
    public String getMob1() {
        return mob1;
    }

    public void setMob1(String mob1) {
        this.mob1 = mob1;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getFeedback1() {
        return feedback1;
    }

    public void setFeedback1(String feedback1) {
        this.feedback1 = feedback1;
    }

    public String getHelpful() {
        return helpful;
    }

    public void setHelpful(String helpful) {
        this.helpful = helpful;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public String getUserfrend() {
        return userfrend;
    }

    public void setUserfrend(String userfrend) {
        this.userfrend = userfrend;
    }
}
