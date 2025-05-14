package com.landlord;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Tenant> tenants = new ArrayList<>();
    private static final List<Property> properties = new ArrayList<>(); // NEW: Property list

    public static void main(String[] args) {
        int choice;

        System.out.println("To begin, please create your landlord profile.");
        createLandlordprofile();
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
                    createNewTenants();
                    break;
                case 5:
                    System.out.println("Program has ended!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 5);
    }

    public static void createLandlordprofile() {
        System.out.println("Creating your profile as a landlord...");
        System.out.println("Please enter your full name:");
        String fullName = scanner.nextLine();
        System.out.println("Please enter your email address:");
        String email = scanner.nextLine();
        System.out.println("Please enter your phone number:");
        String phoneNumber = scanner.nextLine();
        System.out.println("Please enter your address:");
        String address = scanner.nextLine();
        System.out.println("Below is your landlord profile:");
        System.out.println("Full Name: " + fullName + "\nEmail: " + email +
                "\nPhone Number: " + phoneNumber + "\nAddress: " + address);
        randomizeLandlordId();
        System.out.println("Your landlord ID is: " + randomizeLandlordId());
        System.out.println("Please keep this ID safe for future reference.");
        System.out.println("Thank you for creating your landlord profile!");
        System.out.println("You can now proceed to create properties and tenants.");
        System.out.println("Press Enter to continue...");
        scanner.nextLine(); // Wait for user to press Enter
        // Implement landlord creation logic here
    }

    private static String randomizeLandlordId() {
        return "LND" + (int) (Math.random() * 10000);
    }

    private static int menu() {
        System.out.println("\nWelcome to the Landlord Portal!");
        System.out.println("1. View Properties");
        System.out.println("2. View Tenants");
        System.out.println("3. Create Property");
        System.out.println("4. Create Tenants");
        System.out.println("5. Exit");
        System.out.print("Please select an option: ");
        return scanner.nextInt();
    }

    private static void viewProperties() {
        if (properties.isEmpty()) {
            System.out.println("No properties have been added yet.");
        } else {
            System.out.println("Current Properties:");
            for (Property property : properties) {
                System.out.println(property);
            }
        }
    }

    public static void updateProperty(Property property) {
        System.out.println("Enter the new street name:");
        String streetName = scanner.nextLine();
        property.setStreetName(streetName);

        System.out.println("Enter the new city:");
        String city = scanner.nextLine();
        property.setCity(city);

        System.out.println("Enter the new state:");
        String state = scanner.nextLine();
        property.setState(state);

        System.out.println("Enter the new zip code:");
        String zipCode = scanner.nextLine();
        property.setZipCode(zipCode);

        System.out.println("Enter the new property type:");
        String propertyType = scanner.nextLine();
        property.setPropertyType(propertyType);

        System.out.println("Enter the new number of bedrooms:");
        int bedrooms = scanner.nextInt();
        property.setBedrooms(bedrooms);

        System.out.println("Enter the new number of bathrooms:");
        int bathrooms = scanner.nextInt();
        property.setBathrooms(bathrooms);

        System.out.println("Enter the new monthly rent:");
        double monthlyRent = scanner.nextDouble();
        property.setMonthlyRent(monthlyRent);

        System.out.println("Property updated successfully!");

    }

    public static void createNewProperty() {
        scanner.nextLine(); // Consume newline

        System.out.println("Enter the property street name:");
        String streetName = scanner.nextLine();

        System.out.println("Enter the property city:");
        String city = scanner.nextLine();
        // Use ignoreCase() to ensure case-insensitive input

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

        Property property = new Property(streetName, city, state, zipCode, propertyType,
                bedrooms, bathrooms, monthlyRent, propertyId);

        properties.add(property);
        System.out.println("New property created: " + property);
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
        if (properties.isEmpty()) {
            System.out.println("No properties available to assign to a tenant.");
            return;
        }

        // Show available properties
        System.out.println("Available Properties:");
        for (int i = 0; i < properties.size(); i++) {
            System.out.println((i + 1) + ". " + properties.get(i).getPropertyId() + " - "
                    + properties.get(i).getStreetName() + ", " + properties.get(i).getCity());
        }

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

            // Prompt the user to select a property from the list of available properties
            System.out.println("Select a property by its number (1-" + properties.size() + "):");
            int propertySelection = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Check if the selection is valid
            if (propertySelection < 1 || propertySelection > properties.size()) {
                System.out.println("Invalid property selection.");
                return; // Exit method if the selection is invalid
            }

            // Get the selected property by its ID
            Property selectedProperty = properties.get(propertySelection - 1); // Adjusting index (0-based)

            // Check if tenantId matches propertyId
            if (!tenantId.equals(selectedProperty.getPropertyId())) {
                System.out.println("Error: Tenant ID does not match selected Property ID.");
                return; // Stop if there's a mismatch
            }

            // Create a tenant and assign the property ID
            Tenant tenant = new Tenant(tenantName, tenantId, monthlyRent, selectedProperty.getPropertyId());
            tenants.add(tenant);

            System.out.println("Tenant " + (i + 1) + " created and assigned to property "
                    + selectedProperty.getPropertyId() + ": " + tenant);
        }
    }
}
