package com.leumi.coupon_project.data;

import java.util.ArrayList;
import java.util.Iterator;

public class Company {
    public int ID;
    public String name;

    public Company() {
        super();
    }

    public String email;
    public String password;
    public ArrayList<Coupon> coupons;

    public Company(int ID, String name, String email, String password) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.coupons = new ArrayList<Coupon>();
    }

    @Override
    public String toString() {
        return "Company{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", coupons=" + coupons +
                '}';
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Iterator<Coupon> getCoupons() {
        return coupons.iterator();
    }
}
