package com.landlord;

public class Property {
    private String propertyId;
    private String address;
    private double rent;
    private Tenant tenant;

    public Property(String propertyId, String address, double rent) {
        this.propertyId = propertyId;
        this.address = address;
        this.rent = rent;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public String getAddress() {
        return address;
    }

    public double getRent() {
        return rent;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    @Override
    public String toString() {
        return "Property{" +
                "PropertyID='" + propertyId + '\'' +
                ", Address='" + address + '\'' +
                ", Rent=$" + rent +
                (tenant != null ? ", Tenant=" + tenant.getName() : ", Tenant=None") +
                '}';
    }
}
