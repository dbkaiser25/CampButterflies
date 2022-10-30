import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
                    break;
                case 2:
                    weeks();
                    break;
                case 3:
                    price();
                    break;
                case 4:
                    contact();
                    break;
                case 5:
                    createAccount();
                    break;
                case 6:
                    login();
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
			System.out.println("Please enter a valid number\n");
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


    private void weeks(){
        int i = 1;
        for(Week week: facade.getWeeks("Camp Blue Butterflies")){
            System.out.println("Week " + i + ": " + week);
            i++;
        }
    }
    
    /*
     * Clears the console
     
    private void clear() {
		System.out.print("\033[H\033[2J");
	}*/

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

    /*
     * Returns the user back to the mainpage of the camp
     
    private void backToHomepage(){
         System.out.println("Enter any key to return to the homepage");
         scan.next();
    }*/

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
                createUser();
                break;
            case 2:
                createCounselor();;
                break;
        }
    }

    /**
     * Creates a new user with campers
     */
    private void createUser(){
        String username = get("Username");
        String password = get("Password");
        LoginInfo loginInfo = new LoginInfo(username, password);
        String firstName = get("First Name");
        String lastName = get("Last Name");
        String homeAddress = get("Home Address");
        String dateOfBirth = get("Date of Birth(MM/DD/YYYY)");
        Date doB = formatDate(dateOfBirth);

        ArrayList<Camper> campers = new ArrayList<>();
        boolean more = true;
        while(more){
            System.out.println("Camper Information");
            Camper camper = createCamper();
            campers.add(camper);
            String answer = get("Would you like to add more campers?(yes/no)");
            if(answer.equalsIgnoreCase("no"))
                    more = false;
        }

        facade.addUser(firstName, lastName, doB, homeAddress, loginInfo, campers);
    }

    /**
     * Creates a new camper
     * @return camper that was made
     */
    private Camper createCamper(){
        String firstName = get("First Name");
        String lastName = get("Last Name");
        String homeAddress = get("Home Address");
        String dateOfBirth = get("Date of Birth(MM/DD/YYYY)");
        Date doB = formatDate(dateOfBirth);
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
                String answer = get("Would you like to add more(yes/no)");
                if(answer.equalsIgnoreCase("no"))
                    more = false;
            }
        }

        ArrayList<String> allergies = new ArrayList<>();
        boolean moreAllergies = true;
        while(moreAllergies){
            String allergy = get("Allergies:");
            allergies.add(allergy);
            String answer = get("Would you like to add more allergies?(yes/no)");
            if(answer.equalsIgnoreCase("no"))
                    moreAllergies = false;
        }

        System.out.println("\nDoctor Information");
        String relationToPerson = "Pediatrician";
        String doctorFirstName = get("First Name");
        String doctorLastName = get("Last Name");
        String phoneNumber = get("Phone Number");
        String doctorAddress = get("Email Address");
        Contact pediatrician = new Contact(doctorFirstName,doctorLastName,phoneNumber,doctorAddress,relationToPerson);

        System.out.println("\nEmergency Contacts");
        ArrayList<Contact> emergencyContacts = new ArrayList<>();
        boolean moreContacts = true;
        while(moreContacts){
            String contactFirstName = get("First Name");
            String contactLastName = get("Last Name");
            String conatctphoneNumber = get("Phone Number");
            String contactAddress = get("Email Address");
            String conatctrelationToPerson = get("Relation To Person");
            Contact contact = new Contact(contactFirstName, contactLastName, conatctphoneNumber, contactAddress, conatctrelationToPerson);
            emergencyContacts.add(contact);
            String answer = get("Would you like to add more emergency contacts?(yes/no)");
            if(answer.equalsIgnoreCase("no"))
                    moreContacts = false;
        }
        return facade.addCamper(firstName, lastName, homeAddress, doB, sex, medications, allergies, emergencyContacts, pediatrician);
    }

    /**
     * Creates a new counselor
     */
    private void createCounselor(){
        String username = get("Username");
        String password = get("Password");
        LoginInfo loginInfo = new LoginInfo(username, password);
        String firstName = get("First Name");
        String lastName = get("Last Name");
        String phoneNumber = get("Phone Number");
        String emailAddress = get("Email Address");
        String homeAddress = get("Home Address");
        String dateOfBirth = get("Date of Birth(MM/DD/YYYY)");
        Date doB = formatDate(dateOfBirth);

        System.out.println("\nDoctor Information");
        String relationToPerson = "Pediatrician";
        String doctorFirstName = get("First Name");
        String doctorLastName = get("Last Name");
        String doctorphoneNumber = get("Phone Number");
        String doctorAddress = get("Email Address");
        Contact pediatrician = new Contact(doctorFirstName,doctorLastName,doctorphoneNumber,doctorAddress,relationToPerson);

        System.out.println("\nEmergency Contacts");
        ArrayList<Contact> emergencyContacts = new ArrayList<>();
        boolean moreContacts = true;
        while(moreContacts){
            String contactFirstName = get("First Name");
            String contactLastName = get("Last Name");
            String conatctphoneNumber = get("Phone Number");
            String contactAddress = get("Email Address");
            String conatctrelationToPerson = get("Relation To Person");
            Contact contact = new Contact(contactFirstName, contactLastName, conatctphoneNumber, contactAddress, conatctrelationToPerson);
            emergencyContacts.add(contact);
            String answer = get("Would you like to add more emergency contacts?(yes/no)");
            if(answer.equalsIgnoreCase("no"))
                    moreContacts = false;
        }

        facade.addCounselor(firstName, lastName, phoneNumber, emailAddress, homeAddress, doB, emergencyContacts, pediatrician, loginInfo);
    }

    /**
     * Logs someone in if their credentials match the database
     */
    private void login(){
        String userName = get("Username");
        String password = get("Password");
        LoginInfo loginInfo = new LoginInfo(userName, password);
        if(facade.Login(loginInfo)==1)
            userScreen();
        else if(facade.Login(loginInfo)==2)
            counselorScreen();
        else if (facade.Login(loginInfo)==3)
            directorScreen();
        else
            System.out.println("Username and Password are not valid");
    }

    /**
     * Formats questions and returns teh users answer
     * @param prompt
     * @return users input
     */
    private String get(String prompt){
        System.out.print(prompt + ": ");
        return scan.nextLine();
    }

    /**
     * formats a string into a date class
     * @param date
     * @return date
     */
    private Date formatDate(String date){
        try{
            return new SimpleDateFormat("MM/dd/yyyy").parse(date);
        } catch (ParseException e) {
            System.out.println("Sorry " + date + " is not valid");
            return null;
        }
    }

    /**
     * The screen users see when they login
     */
    private void userScreen(){
        welcomeScreen();
        boolean run = true;
        while(run){
            userOptions();
            int option = scan.nextInt();
            switch(option){
                case 1: 
                    System.out.println(facade.viewUserProfile());
                    break;
                case 2:
                    editUser();
                    break;
                case 3:
                    String view = chooseCamper("view");
                    System.out.println(facade.viewCamperProfile(view));
                    break;
                case 4:
                    String edit = chooseCamper("edit");
                    editCamper(edit);
                    break;
                case 5:
                    createCamper();
                    break;
                case 6:
                    discount();
                    break;
                case 7:
                    run=false;
                    break;
                default:
                    System.out.println("Please enter a valid number");
                    break;
            }
        }


    }

    /**
     * Prints the options the user has
     */
    private void userOptions(){
        System.out.println("1. View My Profile\n2. Edit My Profile\n3. View My Existing Campers\n" +
                            "4. Edit My Existing Campers\n5. Register New Camper\n6. View discounts\n7. Logout");
    }

    /**
     * Displays all the campers that the user has and lets them choose one 
     */
    private String chooseCamper(String action){
        System.out.println("Your Current Campers: \n" + facade.viewCampers());
        System.out.println("Please enter the first name of the one you want to " + action + ": ");
        return scan.nextLine();
    }

    /**
     * Prints out the users total in the camp
     */
    private void discount(){
        if(facade.qualifiesForDiscount())
            System.out.println("You qualify for a 10% discount!");
        else
            System.out.println("You do not qualify for a discount");
    }

    /**
     * Gives the user options to edit their profile, then edits the profile
     */
    private void editUser(){
        boolean run=true;
        while(run){
            System.out.println("What would you like to edit:\n1. First Name \n2. Last Name" +
                            "\n3. Home Address\n4. Date of Birth\n5. Quit");
            switch(scan.nextInt()){
                case 1:
                    String newfirstname = get("First Name");
                    facade.editUserFirstName(newfirstname);
                    break;
                case 2:
                    String newlastname = get("Last Name");
                    facade.editUserLastName(newlastname);
                    break;
                case 3:
                    String newhomeaddress = get("Home Address");
                    facade.editUserHomeAddress(newhomeaddress);
                    break;
                case 4:
                    String newdob = get("Date of Birth(MM/DD/YYYY");
                    facade.editUserDateOfBirth(formatDate(newdob));
                    break;
                case 5:
                    run = false;
                default:
                    System.out.println("Please enter a valid number");
                    break;
            }
        }
    }

    /**
     * edits the campers profile
     * @param firstname name of camper being edited
     */
    private void editCamper(String firstname){
        boolean run=true;
        while(run){
            System.out.println("What would you like to edit:\n1. First Name \n2. Last Name" +
                                "\n3. Home Address\n4. Date of Birth\n5. Sex\n6. Allergies" +
                                "\n7. Emergency Contacts\n8. Doctor Information\n9. Quit");
            switch(scan.nextInt()){
                case 1:
                    String newfirstname = get("First Name");
                    facade.editCamperFirstName(firstname, newfirstname);
                    break;
                case 2:
                    String newlastname = get("Last Name");
                    facade.editCamperLastName(firstname, newlastname);
                    break;
                case 3:
                    String newhomeaddress = get("Home Address");
                    facade.editCamperHomeAddress(firstname, newhomeaddress);
                    break;
                case 4:
                    String newdob = get("Date of Birth(MM/DD/YYYY");
                    facade.editCamperDateOfBirth(firstname, formatDate(newdob));
                    break;
                case 5:
                    Sex newdate = Enum.valueOf(Sex.class,get("Sex(MALE/FEMALE)"));
                    facade.editCamperSex(firstname, newdate);
                    break;
                case 6:
                    editAllergies(firstname);
                    break;
                case 7:
                    facade.editCamperEmergencyContacts(firstname, editEC(facade.getCurrentUser().getCamper(firstname).getEmergencyContacts()));
                    break;
                case 8:
                    facade.editCamperPediatrician(firstname, editDoctor(facade.getCurrentUser().getCamper(firstname).getPediatrician()));
                case 9:
                    run = false;
                    break;
                default:
                    System.out.println("Please enter a valid number");
                    break;
            }
        }
    }

    /**
     * edits the campers allergy information
     * @param camper
     */
    private void editAllergies(String camper){
        ArrayList<String> newAllergies = (ArrayList<String>)facade.getCurrentUser().getCamper(camper).getAllergies().clone();
        boolean run=true;
        while(run){
            System.out.println("1. Delete An Existing Allergy\n2.Add A New Allergy\n3. Finish Editing Allergies");
            switch(scan.nextInt()){
                case 1:
                    System.out.println("Choose an allergy to delete");
                    for(int i = 0; i < newAllergies.size(); i++){
                        System.out.println((i+1)+". " + newAllergies.get(i));
                    }
                    newAllergies.remove(scan.nextInt()-1);
                    break;
                case 2:
                    newAllergies.add(get("Allergy"));
                    break;
                case 3:
                    run = false;
                    break;
                default:
                    System.out.println("Please enter a valid number");
                    break;
            }
        }
        facade.editCamperAllergies(camper, newAllergies);
    }

    /**
     * edits emergency contacts
     * @param emrgencyContacts 
     * @return the edited arrayList
     */
    private ArrayList<Contact> editEC(ArrayList<Contact> emergencyContacts){
        System.out.println("1. Add New Emergency Contact\n2. Remove An Existing Emergency Contact");
        int choice = scan.nextInt();
        if(choice == 1){
            System.out.println("New Emergency Contact");
            String contactFirstName = get("First Name");
            String contactLastName = get("Last Name");
            String conatctphoneNumber = get("Phone Number");
            String contactAddress = get("Email Address");
            String conatctrelationToPerson = get("Relation To Person");
            Contact contact = new Contact(contactFirstName, contactLastName, conatctphoneNumber, contactAddress, conatctrelationToPerson);
            emergencyContacts.add(contact);
        }
        else if(choice == 2){
            System.out.println("Remove: ");
            for(int i = 0; i < emergencyContacts.size(); i++){
                System.out.println((i+1) + ". " + emergencyContacts.get(i).getFirstName() + " " + emergencyContacts.get(i).getLastName());
            }
            emergencyContacts.remove(scan.nextInt());
        }
        else 
            System.out.println("Please enter a valid number");
        return emergencyContacts;
    }

    /**
     * edits doctor information
     * @param doctor
     * @return
     */
    private Contact editDoctor(Contact doctor){
        boolean run = true;
        while(run){
            System.out.println("Change: \n1. First Name\n2. Last Name\n3. Phone Number\n4. Email Address\n5. Quit");
            switch(scan.nextInt()){
                case 1:
                    doctor.setFirstName(get("First Name"));
                    break;
                case 2:
                    doctor.setLastName(get("Last Name"));
                    break;
                case 3:
                    doctor.setPhoneNumber(get("Phone Number"));
                    break;
                case 4:
                    doctor.setEmailAddress(get("Email Address"));
                    break;
                case 5:
                    run = false;
                    break;
                default:
                    System.out.println("Please enter a valid number");
                    break;
            }
        }
        return doctor;
    }

    /**
     * The screen counselors see when they login
     */
    private void counselorScreen(){
        welcomeScreen();
        boolean run = true;
        while(run){
            counselorOptions();
            int option = scan.nextInt();
            switch(option){
                case 1:
                    System.out.println(facade.viewCounselorProfile());
                    break;
                case 2:
                    //edit
                    break;
                case 3:
                    //view campers
                    break;
                case 4:
                    //view campers full
                    break;
                case 5:
                    //view schedule
                    break;
                case 6:
                    run = false;
                    break;
            }
        }
    }

    /**
     * Prints out the counselors options
     */
    private void counselorOptions(){
         System.out.println("1. View My Profile\n2. Edit My Profile\n3. View Campers\n4. View Camper Information"+
                            "\n5. View My Schedule\n6. Logout");
    }

    /**
     * The screen directors see when the login
     */
    private void directorScreen(){
        welcomeScreen();
        boolean run = true;
        while(run){
            directorOptions();
            int option = scan.nextInt();
            switch(option){
                case 1:
                    //view profile
                    break;
                case 2:
                    //edit
                    break;
                case 3:
                    //
                    break;
            }
        }
    }

    /**
     * options on the directors homepage
     */
    private void directorOptions(){
        System.out.println("1. View My Profile\n2. Edit My Profile\n3. Create new ");
    }

    /**
     * Welcomes the user once they sign in with their first and last name
     */
    private void welcomeScreen(){
        System.out.println("Welcome Back, " + facade.getCurrentUser().getFirstName() +" " + facade.getCurrentUser().getLastName() + "!");
    }



    public static void main(String[] args){
        CampFacade facade = new CampFacade();
        CampButterfliesDriver driver = new CampButterfliesDriver(facade);
        driver.run();
    }
}
