package com.hfad.restaurantapp.database;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "ccdata")
public class CreditCardEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String firstName;
    private String lastName;
    private String cvv;
    private String num_card;
    private String exp_date;
    private String flag;


    @Ignore
    public CreditCardEntry(String firstName, String lastName, String cvv, String num_card, String exp_date, String flag) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cvv = cvv;
        this.num_card = num_card;
        this.exp_date = exp_date;
        this.flag = flag;
    }

    public CreditCardEntry(int id, String firstName, String lastName, String cvv, String num_card, String exp_date, String flag) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cvv = cvv;
        this.num_card = num_card;
        this.exp_date = exp_date;
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCvv() {
        return cvv;
    }

    public String getNum_card() {
        return num_card;
    }

    public String getExp_date() {
        return exp_date;
    }

    public String getFlag() {
        return flag;
    }




}
