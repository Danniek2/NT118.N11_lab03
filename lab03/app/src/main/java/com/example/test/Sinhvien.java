package com.example.test;

public class Sinhvien {
    private String name;
    private String phone;
    private String ID;
    private int gioitinh;

    public Sinhvien(String name, String phone, String ID,int gioitinh) {
        this.name = name;
        this.phone = phone;
        this.ID = ID;
        this.gioitinh=gioitinh;
    }

    public int getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(int gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
