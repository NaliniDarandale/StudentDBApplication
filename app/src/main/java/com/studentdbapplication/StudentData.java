package com.studentdbapplication;

public class StudentData {
     int id;
    String name, surname, address, contact_no,  dob,  gender,  m1,  m2,  m3, strTotal, percent, remark, profile_image;

    public StudentData(){}

    public StudentData(String name, String surname, String address, String contact_no, String dob, String gender, String m1, String m2, String m3, String strTotal, String percent, String remark, String profile_image) {

        this.name = name;
        this.surname = surname;
        this.address = address;
        this.contact_no = contact_no;
        this.dob = dob;
        this.gender = gender;
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
        this.strTotal = strTotal;
        this.percent = percent;
        this.remark = remark;
        this.profile_image = profile_image;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getM1() {
        return m1;
    }

    public void setM1(String m1) {
        this.m1 = m1;
    }

    public String getM2() {
        return m2;
    }

    public void setM2(String m2) {
        this.m2 = m2;
    }

    public String getM3() {
        return m3;
    }

    public void setM3(String m3) {
        this.m3 = m3;
    }

    public String getStrTotal() {
        return strTotal;
    }

    public void setStrTotal(String strTotal) {
        this.strTotal = strTotal;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }
}
