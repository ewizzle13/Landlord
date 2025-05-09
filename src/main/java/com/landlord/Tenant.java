package com.landlord;

public class Tenant {
    private String name;
    private String tenantId;
    private double monthlyRent;

    // Constructor
    public Tenant(String name, String tenantId, double monthlyRent) {
        this.name = name;
        this.tenantId = tenantId;
        this.monthlyRent = monthlyRent;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public double getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(double monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    // Override toString() to return a formatted string
    @Override
    public String toString() {
        return "Tenant{name='" + name + "', tenantId='" + tenantId + "', monthlyRent=" + monthlyRent + '}';
    }
}

