import java.util.*;

class Room {
    private int roomNumber;
    private int sharingType;
    private double rent;
    private boolean isOccupied;
    private List<String> occupants;

    public Room(int roomNumber, int sharingType, double rent) {
        this.roomNumber = roomNumber;
        this.sharingType = sharingType;
        this.rent = rent;
        this.occupants = new ArrayList<>();
        this.isOccupied = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getSharingType() {
        return sharingType;
    }

    public double getRent() {
        return rent;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public List<String> getOccupants() {
        return occupants;
    }

    public String getStatus() {
        return isOccupied ? "Occupied" : "Vacant";
    }

    public void occupy(String person) {
        occupants.add(person);
        if (occupants.size() == sharingType) {
            isOccupied = true;
        }
    }

    public void vacate(String person) {
        occupants.remove(person);
        isOccupied = false;
    }
}

class PG {
    private String name;
    private String area;
    private List<Room> rooms;

    public PG(String name, String area) {
        this.name = name;
        this.area = area;
        this.rooms = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getArea() {
        return area;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void displayStatus() {
        System.out.println("\t\t\tRoom\tSharing\tRent\tStatus\tOccupants");
        for (Room room : rooms) {
            System.out.println("\t\t\t"+room.getRoomNumber() + "\t" + room.getSharingType() + "\t" +
                    room.getRent() + "\t" + room.getStatus() + "\t" + room.getOccupants());
        }
    }
}

public class PGOperations {
    private List<PG> pgList;
    private List<String> areas;

    public PGOperations() {
        pgList = new ArrayList<>();
        areas = new ArrayList<>();
        addDefaultPGs();
    }

    private void addDefaultPGs() {
        PG pg1 = new PG("Sunrise PG", "Madhapur");
        PG pg2 = new PG("Green Villa", "Gachibowli");
        PG pg3 = new PG("Blue Sky PG", "Kondapur");

        addRoomsToPG(pg1, 5, 5, 5, 9000, 8000, 7000);
        addRoomsToPG(pg2, 4, 4, 4, 8500, 7500, 6500);
        addRoomsToPG(pg3, 6, 6, 6, 9500, 8500, 7500);

        pgList.add(pg1);
        pgList.add(pg2);
        pgList.add(pg3);

        areas.add("Madhapur");
        areas.add("Gachibowli");
        areas.add("Kondapur");
    }

    public void addNewPG(Scanner sc) {
        System.out.print("\t\t\tEnter PG Name: ");
        String name = sc.nextLine();
        System.out.print("\t\t\tEnter Area: ");
        String area = sc.nextLine();

        System.out.print("\t\t\tNumber of 2-sharing rooms: ");
        int r2 = Integer.parseInt(sc.nextLine());
        System.out.print("\t\t\tRent for 2-sharing rooms: ");
        double rent2 = Double.parseDouble(sc.nextLine());

        System.out.print("\t\t\tNumber of 3-sharing rooms: ");
        int r3 = Integer.parseInt(sc.nextLine());
        System.out.print("\t\t\tRent for 3-sharing rooms: ");
        double rent3 = Double.parseDouble(sc.nextLine());

        System.out.print("\t\t\tNumber of 4-sharing rooms: ");
        int r4 = Integer.parseInt(sc.nextLine());
        System.out.print("\t\t\tRent for 4-sharing rooms: ");
        double rent4 = Double.parseDouble(sc.nextLine());

        PG newPG = new PG(name, area);
        addRoomsToPG(newPG, r2, r3, r4, rent2, rent3, rent4);
        pgList.add(newPG);
        if (!areas.contains(area)) areas.add(area);
        System.out.println("\t\t\tPG added successfully!");
		System.out.println("\t\t\t");
    }

    private void addRoomsToPG(PG pg, int two, int three, int four, double r2, double r3, double r4) {
        int roomNo = 1;
        for (int i = 0; i < two; i++) pg.addRoom(new Room(roomNo++, 2, r2));
        for (int i = 0; i < three; i++) pg.addRoom(new Room(roomNo++, 3, r3));
        for (int i = 0; i < four; i++) pg.addRoom(new Room(roomNo++, 4, r4));
    }

    public void showAreas() {
        for (int i = 0; i < areas.size(); i++) {
            System.out.println("\t\t\t"+(i + 1) + ". " + areas.get(i));
        }
    }

    public List<PG> getPGsInArea(String area) {
        List<PG> result = new ArrayList<>();
        for (PG pg : pgList) {
            if (pg.getArea().equalsIgnoreCase(area)) {
                result.add(pg);
            }
        }
        return result;
    }

    public void showPGStatus(Scanner sc) {
        showAreas();
        System.out.print("\t\t\tSelect area by number: ");
        int areaChoice = Integer.parseInt(sc.nextLine());
        String selectedArea = areas.get(areaChoice - 1);

        List<PG> matches = getPGsInArea(selectedArea);
        for (int i = 0; i < matches.size(); i++) {
            System.out.println("\t\t\t"+(i + 1) + ". " + matches.get(i).getName());
        }

        System.out.print("\t\t\tSelect PG by number: ");
        int pgChoice = Integer.parseInt(sc.nextLine());
        PG pg = matches.get(pgChoice - 1);

        pg.displayStatus();
    }

    public void addPerson(Scanner sc) {
		System.out.print("\t\t\tEnter person name: ");
		String name = sc.nextLine();
	
		// Step 1: Choose Area
		showAreas();
		System.out.print("\t\t\tSelect area by number: ");
		int areaChoice = Integer.parseInt(sc.nextLine());
		String area = areas.get(areaChoice - 1);

		// Step 2: Choose PG
		List<PG> matches = getPGsInArea(area);
		for (int i = 0; i < matches.size(); i++) {
			System.out.println("\t\t\t" + (i + 1) + ". " + matches.get(i).getName());
		}
		System.out.print("\t\t\tSelect PG by number: ");
		int pgChoice = Integer.parseInt(sc.nextLine());
		PG selectedPG = matches.get(pgChoice - 1);

		// Step 3: Choose Sharing Type
		System.out.println("\t\t\tSelect sharing type: ");
		System.out.println("\t\t\t1. 2 Sharing");
		System.out.println("\t\t\t2. 3 Sharing");
		System.out.println("\t\t\t3. 4 Sharing");
		System.out.print("\t\t\tEnter type of room: ");
		int shareChoice = Integer.parseInt(sc.nextLine());
		int selectedSharing = shareChoice + 1; // 2 for choice 1, 3 for 2, 4 for 3

		// Step 4: Show available rooms
		List<Room> availableRooms = new ArrayList<>();
		for (Room room : selectedPG.getRooms()) {
			if (room.getSharingType() == selectedSharing &&
					(!room.isOccupied() || room.getOccupants().size() < room.getSharingType())) {
				availableRooms.add(room);
			}
		}

		if (availableRooms.isEmpty()) {
			System.out.println("\t\t\tNo available rooms in " + selectedSharing + "-sharing type.");
			System.out.println("\t\t\t");
			return;
		}

		System.out.println("\t\t\tAvailable Rooms:");
		for (Room room : availableRooms) {
			System.out.println("\t\t\tRoom No: " + room.getRoomNumber() +
					" | Occupants: " + room.getOccupants().size() +
					"/" + room.getSharingType());
		}
		System.out.println("\t\t\t");

		// Step 5: Choose room
		System.out.print("\t\t\tEnter Room Number to assign: ");
		int roomNo = Integer.parseInt(sc.nextLine());

		for (Room room : availableRooms) {
			if (room.getRoomNumber() == roomNo) {
				room.occupy(name);
				System.out.println("\t\t\t" + name + " assigned to Room " + room.getRoomNumber());
				System.out.println("\t\t\t");
				return;
			}
		}

		System.out.println("\t\t\tInvalid room number selected.");
		System.out.println("\t\t\t");
	}

	public void removePerson(Scanner sc) {
		System.out.print("\t\t\tEnter person name to remove: ");
		String name = sc.nextLine();

		// Step 1: Select Area
		showAreas();
		System.out.print("\t\t\tSelect area by number: ");
		int areaChoice = Integer.parseInt(sc.nextLine());
		String area = areas.get(areaChoice - 1);

		// Step 2: Select PG
		List<PG> matches = getPGsInArea(area);
		for (int i = 0; i < matches.size(); i++) {
			System.out.println("\t\t\t" + (i + 1) + ". " + matches.get(i).getName());
		}
		System.out.print("\t\t\tSelect PG by number: ");
		int pgChoice = Integer.parseInt(sc.nextLine());
		PG selectedPG = matches.get(pgChoice - 1);

		// Step 3: Search for the person in that PG
		boolean found = false;
		for (Room room : selectedPG.getRooms()) {
			if (room.getOccupants().contains(name)) {
				room.vacate(name);
				System.out.println("\t\t\t" + name + " removed from Room " + room.getRoomNumber());
				System.out.println("\t\t\t");
				found = true;
				break;
			}
		}

		if (!found) {
			System.out.println("\t\t\tPerson not found in the selected PG.");
			System.out.println("\t\t\t");
		}
	}

}
