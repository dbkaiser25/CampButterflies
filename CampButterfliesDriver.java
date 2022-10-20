import java.util.ArrayList;
import java.util.Scanner;

/**
 * the UI code for the Camp Butterfly program 
 */
public class CampButterfliesDriver {
    private Scanner scan;
    private String[] homepageOptions = new String[7];
    private CampFacade facade; 

    /**
     * Creates the driver and initializes the scanner
     */
    public CampButterfliesDriver(){
        //facade = new CampFacade(null, null, null, null, null);
        scan = new Scanner(System.in);




        homepageOptions[0] = "View Activities";
        homepageOptions[1] = "View Available Weeks";
        homepageOptions[2] = "View Pricing and Discounts";
        homepageOptions[3] = "Contact the Camp";
        homepageOptions[4] = "Create an Account";
        homepageOptions[5] = "Login";
        homepageOptions[6] = "View other camps";
    }

    /**
     * starts running the system
     */
    public void run(){
        

        while(true){
            homepage();
            int choice = getChoice(homepageOptions.length-1);
            
            if (choice == -1){
                continue;
            }

            switch(choice){
                case 1:
                    //facade.getActivities();
                    //backToHomepage();
                    break;
                case 2:
                    //facade.getWeeks();
                    //backToHomepage();
                    break;
                case 3:
                    price();
                    //backToHomepage();
                    break;
                case 4:
                    contact();
                    //backToHomepage();
                    break;
                case 5:
                    //register
                    break;
                case 6:
                    //login
                    break;

            }                       
            

        }
    }

    /**
     * makes sure that the choice entered by the user is valid
     * @param range the valid range in options
     * @return the choice or -1 if not valid
     */
    private int getChoice(int range){
        int choice;

		try {
			choice = Integer.parseInt(scan.nextLine());
		} catch (Exception e) {
            clear();
			System.out.println("You need to enter a valid number\n");
			return -1;
		}

		clear();

		if (choice < 1 || choice > range) {
			clear();
			System.out.println("Sorry, your option is not in the valid range.\n");
			return -1;
		}

		return choice;
	}
    
    /**
     * Clears the console
     */
    private void clear() {
		System.out.print("\033[H\033[2J");
	}

    /**
     * prints the hompage UI
     */
    private void homepage(){
        System.out.println("\n\tWelcome to Camp Butterflies");
        System.out.println("-----------------------------------------------");
        for (int i = 0; i < homepageOptions.length; i++) {
			System.out.println((i + 1) + ". " + homepageOptions[i]);
		}
    }

    /**
     * Returns the user back to the mainpage of the camp
     */
    private void backToHomepage(){
         System.out.println("Enter any key to return to the homepage");
         scan.next();
    }

    /**
     * Prints out camp price and available discounts
     */
    private void price(){
        System.out.println("The general price of our camp is $675\n" +
                            "Discounts Available:\n" +
                            "\tReturning Camper - 10% off\n" +
                            "\t2+ Campers Registered - 10% off\n" +
                            "\tRegsistered for Multiple Weeks - 10% off");
    }

    private void contact(){
        System.out.println("Contact us with any questions or concerns\n" +
                            "Phone Number: 555-123-CAMP\n" +
                            "Email: help@campbutterflies.org");
    }

    private void creatAccount(){
        String username = get("Username");
        String password = get("Password");
        String firstName = get("First Name");
        String lastName = get("Last Name");
        String homeAddress = get("Home Address");
        String dateOfBirth = get("Date of Birth(MM/DD/YYYY)");
        Sex sex = Enum.valueOf(Sex.class,get("Sex(MALE/FEMALE)"));
        String med = get("Would you like to add any medications(yes/no)");
        ArrayList<Medication> medications = new ArrayList<>();
        if(med.equalsIgnoreCase("yes")){
            boolean more = true;
            while(more){
                String type = get("Medication Name");
                String dose = get("Dose Amount");
                String time = get("Time Taken");
                Medication newMeds = new Medication(type, dose, time);
                medications.add(newMeds);
                String answer = get("Would you like to add more(yes/no");
                if(answer.equalsIgnoreCase("no"))
                    more = false;
            }
        }
    }

    private String get(String prompt){
        System.out.print(prompt + ": ");
        return scan.nextLine();
    }

    public static void main(String[] args){
        CampButterfliesDriver driver = new CampButterfliesDriver();
        driver.run();
    }
}
