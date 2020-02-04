package com.galvanize.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

import static javax.swing.text.html.HTML.Tag.DD;


public class Person {
    private String name;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date birthDate;
    private String email;
    private String address;

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Person(String name, Date birthDate, String email, String address){
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.address = address;
    }

    public Person(){

    }

        public int getAge() {
            Calendar c = Calendar.getInstance();
            c.setTime(this.birthDate);
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH) + 1;
            int day = c.get(Calendar.DATE);
            LocalDate ll = LocalDate.of(year, month, day);
            LocalDate now = LocalDate.now();
            Period diff = Period.between(ll, now);
            return diff.getYears();
        }



}
