package com.example.madbuildllc;

import java.util.*;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Property> properties = new ArrayList<>();
    private static final List<Tenant> tenants = new ArrayList<>();
    private static final Map<String, Landlord> landlords = new HashMap<>();
    private static Landlord currentLandlord = null;

    public static void main(String[] args) {
        login();
        menu();
    }

    public static void login() {
        while (true) {
            int choice = promptLoginOrSignup();

            if (choice == 1) {
                System.out.print("Please enter your Landlord ID (LNID) to login: ");
                String lnid = scanner.nextLine().trim();

                if (landlords.containsKey(lnid)) {
                    currentLandlord = landlords.get(lnid);
                    System.out.println("Welcome back, " + currentLandlord.getName() + "!");
                    break;
                } else {
                    System.out.println("LNID not found. Please try again or create a new account.");
                }

            } else if (choice == 2) {
                System.out.print("Enter your full name: ");
                String name = scanner.nextLine().trim();
                System.out.print("Enter your email: ");
                String email = scanner.nextLine().trim();
                System.out.print("Enter your phone number: ");
                String phone = scanner.nextLine().trim();

                String newLnid = generateLandlordId(name);

                while (landlords.containsKey(newLnid)) {
                    newLnid = generateLandlordId(name);
                }

                Landlord newLandlord = new Landlord(newLnid, name, email, phone);
                landlords.put(newLnid, newLandlord);
                currentLandlord = newLandlord;
                System.out.println("Account created successfully! Your LNID is: " + newLnid);
                break;
            } else {
                System.out.println("Invalid selection. Please enter 1 or 2.");
            }
        }
    }

    private static int promptLoginOrSignup() {
        System.out.println("Welcome! Please select an option:");
        System.out.println("1. Login with your existing Landlord ID (LNID)");
        System.out.println("2. Create a new Landlord account (Sign up)");
        System.out.print("Enter your choice (1 or 2): ");

        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equals("1") || input.equals("2")) {
                return Integer.parseInt(input);
            }
            System.out.print("Invalid input. Please enter 1 or 2: ");
        }
    }

    private static String generateLandlordId(String name) {
        String prefix = name.length() >= 3 ? name.substring(0, 3).toUpperCase() : name.toUpperCase();
        int randomNum = 1000 + new Random().nextInt(9000);
        return prefix + randomNum;
    }

    public static void menu() {
        while (true) {
            System.out.println("\n--- Property Management System ---");
            System.out.println("1. View Properties");
            System.out.println("2. View Tenants");
            System.out.println("3. Create Tenant");
            System.out.println("4. Assign Tenant to Property");
            System.out.println("5. Record Payment");
            System.out.println("6. Create Property");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");

            int choice = readIntInput();

            switch (choice) {
                case 1:
                    viewProperties();
                    break;
                case 2:
                    viewTenants();
                    break;
                case 3:
                    createTenant();
                    break;
                case 4:
                    assignTenantToProperty();
                    break;
                case 5:
                    recordPayment();
                    break;
                case 6:
                    createProperty();
                    break;
                case 7:
                    System.out.println("Logging out. Goodbye!");
                    currentLandlord = null;
                    login();  // Allow login again after logout
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static int readIntInput() {
        while (true) {
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    private static double readDoubleInput() {
        while (true) {
            String input = scanner.nextLine();
            try {
                return Double.parseDouble(input.trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid amount: ");
            }
        }
    }

    public static void viewProperties() {
        boolean found = false;
        for (Property property : properties) {
            if (property.getLandlordId().equals(currentLandlord.getId())) {
                System.out.println(property);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No properties found for your account.");
        }
    }

    public static void viewTenants() {
        if (tenants.isEmpty()) {
            System.out.println("No tenants available.");
        } else {
            for (Tenant tenant : tenants) {
                System.out.println(tenant);
            }
        }
    }

    public static void createTenant() {
        System.out.print("Enter tenant ID: ");
        String tenantId = scanner.nextLine().trim();
        System.out.print("Enter tenant name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter tenant email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter tenant phone number: ");
        String phoneNumber = scanner.nextLine().trim();

        Tenant tenant = new Tenant(tenantId, name, email, phoneNumber);
        tenants.add(tenant);
        System.out.println("Tenant created successfully.");
    }

    public static void assignTenantToProperty() {
        System.out.print("Enter the Property ID to assign tenant: ");
        String propertyId = scanner.nextLine().trim();

        Property foundProperty = null;
        for (Property property : properties) {
            if (property.getPropertyId().equals(propertyId) && property.getLandlordId().equals(currentLandlord.getId())) {
                foundProperty = property;
                break;
            }
        }

        if (foundProperty == null) {
            System.out.println("Property not found or does not belong to you.");
            return;
        }

        System.out.print("Enter Tenant ID to assign: ");
        String tenantId = scanner.nextLine().trim();

        Tenant foundTenant = null;
        for (Tenant tenant : tenants) {
            if (tenant.getTenantId().equals(tenantId)) {
                foundTenant = tenant;
                break;
            }
        }

        if (foundTenant == null) {
            System.out.println("Tenant not found.");
            return;
        }

        foundProperty.setTenant(foundTenant);
        System.out.println("Tenant assigned to property successfully.");
    }

    public static void recordPayment() {
        System.out.print("Enter Property ID for payment: ");
        String propertyId = scanner.nextLine().trim();

        Property foundProperty = null;
        for (Property property : properties) {
            if (property.getPropertyId().equals(propertyId) && property.getLandlordId().equals(currentLandlord.getId())) {
                foundProperty = property;
                break;
            }
        }

        if (foundProperty == null) {
            System.out.println("Property not found or does not belong to you.");
            return;
        }

        if (foundProperty.getTenant() == null) {
            System.out.println("No tenant assigned to this property.");
            return;
        }

        System.out.print("Enter payment amount: ");
        double amount = readDoubleInput();

        System.out.println("Payment of $" + amount + " recorded for tenant " 
            + foundProperty.getTenant().getName() + " at property " + foundProperty.getAddress());
    }

    public static void createProperty() {
        System.out.print("Enter property ID: ");
        String propertyId = scanner.nextLine().trim();
        System.out.print("Enter property address: ");
        String address = scanner.nextLine().trim();
        System.out.print("Enter monthly rent: ");
        double rent = readDoubleInput();

        Property property = new Property(propertyId, address, rent, currentLandlord.getId());
        properties.add(property);

        System.out.println("Property created successfully.");
    }
}
