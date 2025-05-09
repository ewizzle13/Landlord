package com.landlord;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Tenant> tenants = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            choice = menu();
            System.out.println("You selected option: " + choice);

            switch (choice) {
                case 1:
                    viewProperties();
                    break;
                case 2:
                    viewTenants();
                    break;
                case 3:
                    createNewProperty();
                    break;
                case 4:
                    createNewTenants(); // you can rename to updateTenants if needed
                    break;
                case 5:
                    System.out.println("Program has ended!");
                    break;
            }
        } while (choice != 5);
    }

    private static int menu() {
        System.out.println("\nWelcome to the Landlord Portal!");
        System.out.println("1. View Properties");
        System.out.println("2. View Tenants");
        System.out.println("3.  Property");
        System.out.println("4. Create Tenants");
        System.out.println("5. Exit");
        System.out.print("Please select an option: ");
        return scanner.nextInt();
    }

    private static void viewProperties() {
        // Stubbed out for now
        System.out.println("Property viewing not implemented yet.");
    }

    public static void createNewProperty() {
        if (tenants.isEmpty()) {
            System.out.println("No tenants available to assign to a property.");
            return;
        } else{
        System.out.println("Enter the property street name:");
        String streetName = scanner.nextLine();
        System.out.println("Enter the property city:");
        String city = scanner.nextLine();
        System.out.println("Enter the property state:");
        String state = scanner.nextLine();
        System.out.println("Enter the property zip code:");
        String zipCode = scanner.nextLine();
        System.out.println("Enter the property type (e.g., Apartment, House):");
        String propertyType = scanner.nextLine();
        System.out.println("Enter the number of bedrooms:");
        int bedrooms = scanner.nextInt();
        System.out.println("Enter the number of bathrooms:");
        int bathrooms = scanner.nextInt();
        System.out.println("Enter the monthly rent:");
        double monthlyRent = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter the property ID:");
        String propertyId = scanner.nextLine();
    }
}

    private static void viewTenants() {
        if (tenants.isEmpty()) {
            System.out.println("No tenants have been added yet.");
        } else {
            System.out.println("Current Tenants:");
            for (Tenant tenant : tenants) {
                System.out.println(tenant);
            }
        }
    }

    private static void createNewTenants() {
        System.out.println("Enter the number of tenants to create:");
        int tenantCount = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < tenantCount; i++) {
            System.out.println("Enter the full name of tenant " + (i + 1) + ":");
            String tenantName = scanner.nextLine();
            System.out.println("Enter the ID of tenant " + (i + 1) + ":");
            String tenantId = scanner.nextLine();

            System.out.println("Enter the monthly rent for tenant " + (i + 1) + ":");
            double monthlyRent = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            Tenant tenant = new Tenant(tenantName, tenantId, monthlyRent);
            tenants.add(tenant);

            System.out.println("Tenant " + (i + 1) + " created: " + tenant);
        }
    }
}
