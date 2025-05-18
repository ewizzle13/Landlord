package com.landlord;

public class Tenant {
    private String tenantId;
    private String name;
    private String email;
    private String phoneNumber;

    public Tenant(String tenantId, String name, String email, String phoneNumber) {
        this.tenantId = tenantId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getTenantId() {
        return tenantId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "TenantID='" + tenantId + '\'' +
                ", Name='" + name + '\'' +
                ", Email='" + email + '\'' +
                ", Phone='" + phoneNumber + '\'' +
                '}';
    }
}
