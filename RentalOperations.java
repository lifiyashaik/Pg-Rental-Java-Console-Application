import java.util.*;

class RentalHouse {
    private String houseName;
    private String area;
    private double rent;
    private boolean isOccupied;

    public RentalHouse(String houseName, String area, double rent) {
        this.houseName = houseName;
        this.area = area;
        this.rent = rent;
        this.isOccupied = false;
    }

    public String getHouseName() {
        return houseName;
    }

    public String getArea() {
        return area;
    }

    public double getRent() {
        return rent;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }

    public String getStatus() {
        return isOccupied ? "Occupied" : "Available";
    }

    public void display() {
        System.out.println("\t\t\tHouse: " + houseName + " | Area: " + area + " | Rent: ₹" + rent + " | Status: " + getStatus());
    }
}

public class RentalOperations {
    private List<RentalHouse> rentalHouses;
    public RentalOperations() {
        rentalHouses = new ArrayList<>();
        addDefaultRentals();
    }

    private void addDefaultRentals() {
        rentalHouses.add(new RentalHouse("Rose Villa", "Madhapur", 15000));
        rentalHouses.add(new RentalHouse("Hill View", "Gachibowli", 13000));
        rentalHouses.add(new RentalHouse("Green Nest", "Kondapur", 14000));
    }

    public void addHouse(Scanner sc) {
        System.out.print("\t\t\tEnter House Name: ");
        String name = sc.nextLine();
        System.out.print("\t\t\tEnter Area: ");
        String area = sc.nextLine();
        System.out.print("\t\t\tEnter Rent: ");
        double rent = Double.parseDouble(sc.nextLine());

        RentalHouse newHouse = new RentalHouse(name, area, rent);
        rentalHouses.add(newHouse);
        System.out.println("\t\t\tRental house added successfully!");
		System.out.println("\t\t\t");
    }

    public void displayHouses() {
        if (rentalHouses.isEmpty()) {
            System.out.println("\t\t\tNo rental houses available.");
			System.out.println("\t\t\t");
            return;
        }
		System.out.println();
        System.out.println("\n\t\t\tAvailable Rental Houses:");
		System.out.println();
        for (RentalHouse house : rentalHouses) {
            house.display();
        }
		System.out.println();
    }
}
