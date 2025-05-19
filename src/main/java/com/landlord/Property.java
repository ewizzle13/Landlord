package com.example.madbuildllc;

public class Property {
    private String propertyId;
    private String address;
    private double rent;
    private String landlordId;
    private Tenant tenant;

    public Property(String propertyId, String address, double rent, String landlordId) {
        this.propertyId = propertyId;
        this.address = address;
        this.rent = rent;
        this.landlordId = landlordId;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public String getAddress() {
        return address;
    }

    public String getLandlordId() {
        return landlordId;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public String toString() {
        return "Property ID: " + propertyId + ", Address: " + address + ", Rent: " + rent;
    }
}
