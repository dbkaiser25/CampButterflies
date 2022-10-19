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
        homepageOptions[4] = "Register";
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
                    backToHomepage();
                    break;
                case 2:
                    //facade.getWeeks();
                    backToHomepage();
                    break;
                case 3:
                    price();
                    backToHomepage();
                    break;
                case 4:
                    contact();
                    backToHomepage();
                case 5:
                    //register
                case 6:
                    //login

            }                       
            

        }
    }

    /**
     * makes sure that the choice entered by the user is valid
     * @param range the valid range in options
     * @return the choice or -1 if not valid
     */
    public int getChoice(int range){
        int choice;

		try {
			choice = Integer.parseInt(scan.nextLine()) - 1;
		} catch (Exception e) {
			System.out.println("You need to enter a valid number\n");
			clear();
			return -1;
		}

		clear();

		if (choice < 0 || choice > range) {
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
    public void homepage(){
        System.out.println("\tWelcome to Camp Butterflies");
        System.out.println("-----------------------------------------------");
        for (int i = 0; i < homepageOptions.length; i++) {
			System.out.println((i + 1) + ". " + homepageOptions[i]);
		}
    }

    /**
     * Returns the user back to the mainpage of the camp
     */
    public void backToHomepage(){
         System.out.println("Press ENTER to go back");
         scan.next();
    }

    /**
     * Prints out camp price and available discounts
     */
    public void price(){
        System.out.println("The general price of our camp is $675\n" +
                            "Discounts Available:\n" +
                            "\tReturning Camper - 10% off\n" +
                            "\t2+ Campers Registered - 10% off\n" +
                            "\tRegsistered for Multiple Weeks - 10% off");
    }

    public void contact(){
        System.out.println("Contact us with any questions or concerns\n" +
                            "Phone Number: 555-123-CAMP\n" +
                            "Email: help@campbutterflies.org");
    }
}
