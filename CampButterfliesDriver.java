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
    public CampButterfliesDriver(CampFacade facade){
        this.facade = facade;
        scan = new Scanner(System.in);

        homepageOptions[0] = "View Activities";
        homepageOptions[1] = "View Available Weeks";
        homepageOptions[2] = "View Pricing and Discounts";
        homepageOptions[3] = "Contact the Camp";
        homepageOptions[4] = "Create an Account";
        homepageOptions[5] = "Login";
        homepageOptions[6] = "Quit";
    }

    /**
     * starts running the system
     */
    public void run(){
        boolean running = true;
        while(running){
            homepage();
            int choice = getChoice(homepageOptions.length-1);
            
            if (choice == -1){
                continue;
            }

            switch(choice){
                case 1:
                    System.out.println(facade.getActivities("Camp Blue Butterflies"));
                    //backToHomepage();
                    break;
                case 2:
                    System.out.println(facade.getWeeks("Camp Blue Butterflies"));
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
                    createAccount();
                    break;
                case 6:
                    //login
                    break;
                case 7:
                    System.out.println("Have a good day!");
                    running = false;
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
           //clear();
			System.out.println("You need to enter a valid number\n");
			return -1;
		}

		//clear();

		if (choice < 1 || choice > range) {
			///clear();
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
        System.out.println("\n\tWelcome to Camp Blue Butterflies");
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

    /**
     * Prints out the campscontact information
     */
    private void contact(){
        System.out.println("Contact us with any questions or concerns\n" +
                            "Phone Number: 555-123-CAMP\n" +
                            "Email: help@campbutterflies.org");
    }

    /**
     * Lets the user create a new account
     */
    private void createAccount(){
        System.out.println("Would you like to create a \n1. Parent Account \n2. Counselor Account");
        int choice = scan.nextInt();
        switch(choice){
            case 1:
                facade.addUser();
                break;
            case 2:
                facade.addCounselor();
                break;
        }
    }


    private String get(String prompt){
        System.out.print(prompt + ": ");
        return scan.nextLine();
    }

    public static void main(String[] args){
        CampList campList = CampList.getInstance();
        CamperList camperList = CamperList.getInstance();
        UserList userList = UserList.getInstance();
        CounselorList counselorList = CounselorList.getInstance();
        DirectorList directorList = DirectorList.getInstance();

        CampFacade facade = new CampFacade(campList, camperList, userList, counselorList, directorList);
        CampButterfliesDriver driver = new CampButterfliesDriver(facade);
        driver.run();
    }
}
