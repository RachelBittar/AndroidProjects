package com.hfad.restaurantapp;

public class CreditCard {

    private String firstName;
    private String lastName;
    private String cvv;
    private String num_card;
    private String exp_date;
    private String flag;


    public CreditCard(String firstName, String lastName, String cvv, String num_card, String exp_date,String flag) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cvv = cvv;
        this.num_card = num_card;
        this.exp_date = exp_date;
        this.flag = flag;
    }


    public String getFlag() {
        return flag;
    }

    public void setFlat(String flat) {
        this.flag = flag;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getNum_card() {
        return num_card;
    }

    public void setNum_card(String num_card) {

        num_card.replaceAll("\\d{4}", "$0 ");
        num_card.replaceFirst("\\d{4}", "$0 ").replaceFirst("\\d{6}", "$0 ");
        this.num_card = num_card;
    }

    public String getExp_date() {
        return exp_date;
    }

    public void setExp_date(String exp_date) {
        this.exp_date = exp_date;
    }






}
