package com.example.product1;

import android.widget.TextView;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private int price;
    private String nameCus;
    private String address;
    private int phone;

    public Product(int id, String name, int price, String nameCus, String address, int phone) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.nameCus = nameCus;
        this.address = address;
        this.phone = phone;
    }
    public Product( String name, int price, String nameCus, String address, int phone) {
        this.name = name;
        this.price = price;
        this.nameCus = nameCus;
        this.address = address;
        this.phone = phone;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getNameCus() {
        return nameCus;
    }

    public void setNameCus(String nameCus) {
        this.nameCus = nameCus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }


    public Product(TextView name, TextView quantity) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
