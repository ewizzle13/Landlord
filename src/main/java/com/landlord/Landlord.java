package com.example.madbuildllc;

public class Landlord {
    private String id;
    private String name;
    private String email;
    private String phone;

    public Landlord(String id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
