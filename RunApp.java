import java.util.Scanner;

public class RunApp {
	static Scanner sc = new Scanner(System.in);
	private static String pass="admin123";
	static String getPass(){
		return pass;
	}
	static void setPass(String pa){
		pass=pa;
	}
	static boolean auth(){
		System.out.print("\t\t\tEnter your password: ");
		String pa=sc.next();
		if(pa.equals(getPass())){
			System.out.println("\t\t\tSuccesful login.........");
			return true;
		}
		return false;
	}
	static void chnagepass(){
		System.out.print("\t\t\tEnter old password: ");
		String old=sc.next();
		if(!old.equals(getPass())){
			System.out.print("\t\t\tWrong old password..try again");
		}else{
			System.out.print("\t\t\tEnter new password: ");
			String newpass=sc.next();
			setPass(newpass);
			System.out.println("\t\t\tpasswprd updated...login with new password");
			System.out.println();
			
		}
	}
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PGOperations pgOps = new PGOperations();
        RentalOperations rentalOps = new RentalOperations(); // Define this separately

        while (true) {
			System.out.println();
			System.out.println("\t\t\t=======Hey..Find your Stay!=======");
            System.out.println("\n\t\t\t1. User Login\n\t\t\t2. Admin Login\n\t\t\t3. Exit");
			System.out.print("\t\t\tEnter your choice: ");
			System.out.println();
			
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
					while(true){
                    System.out.println("\t\t\t1. View PG Status\n\t\t\t2. View Rental Houses\n\t\t\t3. Exit");
					System.out.print("\t\t\tEnter your choice: ");
                    int uChoice = Integer.parseInt(sc.nextLine());
                    if (uChoice == 1) 
						pgOps.showPGStatus(sc);
                    else if(uChoice == 2)
						rentalOps.displayHouses();
                    else
						break;
					}
					break;

                case 2:
					if(!auth()){
						System.out.println("\t\t\tWrong password!");
						System.out.println("\t\t\t");
						break;
					}
					int st=0;
					while(st==0){
                    System.out.println("\t\t\t1. Add PG\n\t\t\t2. Add Person\n\t\t\t3. Remove Person\n\t\t\t4. View PG Status\n\t\t\t5. Add Rental House\n\t\t\t6. View Rentals \n\t\t\t7. Change password \n\t\t\t8. Exit");
                    System.out.print("\t\t\tEnter your choice: ");
					int aChoice = Integer.parseInt(sc.nextLine());

                    switch (aChoice) {
                        case 1: pgOps.addNewPG(sc); break;
                        case 2: pgOps.addPerson(sc); break;
                        case 3: pgOps.removePerson(sc); break;
                        case 4: pgOps.showPGStatus(sc); break;
                        case 5: rentalOps.addHouse(sc); break;
                        case 6: rentalOps.displayHouses(); break;
						case 7: chnagepass();st=1;break;
						case 8:	st=1; break;
                        default: System.out.println("\t\t\tInvalid choice.");
                    }
					}
					break;
                case 3:
                    System.out.println("\t\t\tThank you!");
                    sc.close();
                    return;
                default:
                    System.out.println("\t\t\tInvalid input.");
            }
        }
    }
}
