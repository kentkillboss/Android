package com.example.product1;

import java.io.Serializable;

public class History implements Serializable {
    private int id;
    private String nameCus;
    private String nameSP;
    private String datetime;
    private int tongtien;




    public History(String nameCus, String nameSP, String datetime, int tongtien) {
        this.nameCus = nameCus;
        this.nameSP = nameSP;
        this.datetime = datetime;
        this.tongtien = tongtien;
    }

    public History(int id, String nameCus, String nameSP, String datetime, int tongtien) {
        this.id = id;
        this.nameCus = nameCus;
        this.nameSP = nameSP;
        this.datetime = datetime;
        this.tongtien = tongtien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCus() {
        return nameCus;
    }

    public void setNameCus(String nameCus) {
        this.nameCus = nameCus;
    }

    public String getNameSP() {
        return nameSP;
    }

    public void setNameSP(String nameSP) {
        this.nameSP = nameSP;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", nameCus='" + nameCus + '\'' +
                ", nameSP='" + nameSP + '\'' +
                ", datetime='" + datetime + '\'' +
                ", tongtien=" + tongtien +
                '}';
    }
}
