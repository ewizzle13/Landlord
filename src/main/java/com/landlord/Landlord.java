package com.landlord;
public class Landlord {
    private String lnid;
    private String name;
    private String email;
    private String phone;

    public Landlord(String lnid, String name, String email, String phone) {
        this.lnid = lnid;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getLnid() {
        return lnid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Landlord{" +
                "lnid='" + lnid + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
