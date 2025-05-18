package com.landlord;

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
                    break; // Exit login loop
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
    
                // Generate LNID automatically
                String newLnid = generateLandlordId(name);
    
                while (landlords.containsKey(newLnid)) {
                    // In rare case of conflict, generate again
                    newLnid = generateLandlordId(name);
                }
    
                Landlord newLandlord = new Landlord(newLnid, name, email, phone);
                landlords.put(newLnid, newLandlord);
                currentLandlord = newLandlord;
                System.out.println("Account created successfully! Your LNID is: " + newLnid);
                break; // Exit login loop
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
        int randomNum = 1000 + new Random().nextInt(9000); // random 4-digit number
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
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = readIntInput();

            switch (choice) {
                case 1 -> viewProperties();
                case 2 -> viewTenants();
                case 3 -> createTenant();
                case 4 -> assignTenantToProperty();
                case 5 -> recordPayment();
                case 6 -> createProperty();
                case 7 -> {
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
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
        if (properties.isEmpty()) {
            System.out.println("No properties available.");
        } else {
            for (Property property : properties) {
                System.out.println(property);
            }
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
        if (properties.isEmpty()) {
            System.out.println("No properties available.");
            return;
        }

        System.out.println("Available Properties:");
        for (int i = 0; i < properties.size(); i++) {
            System.out.println((i + 1) + ". " + properties.get(i));
        }

        System.out.print("Select a property by number: ");
        int propertyIndex = readIntInput() - 1;

        if (propertyIndex < 0 || propertyIndex >= properties.size()) {
            System.out.println("Invalid property selection.");
            return;
        }

        Property selectedProperty = properties.get(propertyIndex);

        if (tenants.isEmpty()) {
            System.out.println("No tenants available.");
            return;
        }

        System.out.println("Available Tenants:");
        for (int i = 0; i < tenants.size(); i++) {
            System.out.println((i + 1) + ". " + tenants.get(i));
        }

        System.out.print("Select a tenant by number: ");
        int tenantIndex = readIntInput() - 1;

        if (tenantIndex < 0 || tenantIndex >= tenants.size()) {
            System.out.println("Invalid tenant selection.");
            return;
        }

        Tenant selectedTenant = tenants.get(tenantIndex);
        selectedProperty.setTenant(selectedTenant);
        System.out.println("Tenant assigned to property successfully.");
    }

    public static void recordPayment() {
        if (tenants.isEmpty()) {
            System.out.println("No tenants available.");
            return;
        }

        System.out.println("Available Tenants:");
        for (int i = 0; i < tenants.size(); i++) {
            System.out.println((i + 1) + ". " + tenants.get(i));
        }

        System.out.print("Select tenant by number: ");
        int index = readIntInput();

        if (index < 1 || index > tenants.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Tenant selectedTenant = tenants.get(index - 1);

        System.out.print("Enter payment amount: ");
        double amount = readDoubleInput();

        // Placeholder logic for payment recording
        System.out.println("Recording payment of $" + amount + " for tenant " + selectedTenant.getName());
        System.out.println("Payment recorded successfully.");
    }

    public static void createProperty() {
        System.out.print("Enter property ID: ");
        String propertyId = scanner.nextLine().trim();
        System.out.print("Enter address: ");
        String address = scanner.nextLine().trim();
        System.out.print("Enter rent amount: ");
        double rent = readDoubleInput();

        Property property = new Property(propertyId, address, rent);
        properties.add(property);
        System.out.println("Property created successfully.");
    }
}
