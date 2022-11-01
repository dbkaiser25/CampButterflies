
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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
    public CampButterfliesDriver(CampFacade facade) {
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
    public void run() {
        boolean running = true;
        while (running) {
            homepage();
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:
                    activites();
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
                default:
                    System.out.println("Please enter a valid number");
                    break;
            }
            pause();
        }
    }

    /**
     * Prints out activities for a certain camp
     */
    private void activites() {
        System.out.println("Which camp's activities would you like to see?");
        System.out.println(facade.getCamps() + "\n");
        System.out.println(facade.getActivities(scan.nextLine()));
    }

    /**
     * Prints out the available weeks for a certain camp
     */
    private void weeks() {
        System.out.println("Which camps sessions would you like to see?");
        System.out.println(facade.getCamps() + "\n");
        String camp = scan.nextLine();
        int i = 1;
        for (Week week : facade.getWeeks(camp)) {
            System.out.println("Week " + i + ": " + week);
            i++;
        }
    }

    /*
     * Clears the console
     * 
     * private void clear() {
     * System.out.print("\033[H\033[2J");
     * }
     */

    /**
     * prints the hompage UI
     */
    private void homepage() {
        System.out.println("\n\tWelcome to Camp Blue Butterflies");
        System.out.println("-----------------------------------------------");
        for (int i = 0; i < homepageOptions.length; i++) {
            System.out.println((i + 1) + ". " + homepageOptions[i]);
        }
    }

    /**
     * Pauses the code to give users time to look at the new information
     */
    private void pause() {
        try {
            TimeUnit.MILLISECONDS.sleep(600);
        } catch (Exception e) {
            System.out.println("Timer error");
        }
    }

    /**
     * Prints out camp price and available discounts
     */
    private void price() {
        System.out.println("The general price of our camp is $675\n" +
                "Discounts Available:\n" +
                "\tReturning Camper - 10% off\n" +
                "\t2+ Campers Registered - 10% off\n" +
                "\tRegsistered for Multiple Weeks - 10% off");
    }

    /**
     * Prints out the campscontact information
     */
    private void contact() {
        System.out.println("Contact us with any questions or concerns\n" +
                "Phone Number: 555-123-CAMP\n" +
                "Email: help@campbutterflies.org");
    }

    /**
     * Lets the user create a new account
     */
    private void createAccount() {
        System.out.println("Would you like to create a \n1. Parent Account \n2. Counselor Account");
        int choice = scan.nextInt();
        scan.nextLine();
        switch (choice) {
            case 1:
                createUser();
                break;
            case 2:
                createCounselor();
                ;
                break;
        }
    }

    /**
     * Creates a new user with campers
     */
    private void createUser() {
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
        while (more) {
            System.out.println("\nCamper Information");
            Camper camper = createCamper();
            campers.add(camper);
            System.out.println("Which camp would you like to sign up for? (Enter a number)");
            System.out.println(facade.getCamps());
            String camp = scan.nextLine();
            System.out.println("Pick a week");
            boolean moreWeeks = true;
            while (moreWeeks) {
                int i = 1;
                for (Week week : facade.getWeeks(camp)) {
                    System.out.println("Week " + i + ": " + week);
                    i++;
                }
                System.out.println("Week Number:");
                int week = scan.nextInt();
                scan.nextLine();
                facade.getCampList().getCamp(camp).getWeek(week);
                camper.addWeek(week);
                String answer = get("Would you like to add another week?(yes/no)");
                if (answer.equalsIgnoreCase("no"))
                    moreWeeks = false;
            }
            String answer = get("Would you like to add more campers?(yes/no)");
            if (answer.equalsIgnoreCase("no"))
                more = false;

        }
        facade.addUser(firstName, lastName, doB, homeAddress, loginInfo, campers);
    }

    /**
     * Creates a new camper
     * 
     * @return camper that was made
     */
    private Camper createCamper() {
        String firstName = get("First Name");
        String lastName = get("Last Name");
        String homeAddress = get("Home Address");
        String dateOfBirth = get("Date of Birth(MM/DD/YYYY)");
        Date doB = formatDate(dateOfBirth);
        Sex sex = Enum.valueOf(Sex.class, get("Sex(MALE/FEMALE)"));
        String med = get("Would you like to add any medications(yes/no)");

        ArrayList<Medication> medications = new ArrayList<>();
        if (med.equalsIgnoreCase("yes")) {
            boolean more = true;
            while (more) {
                String type = get("Medication Name");
                String dose = get("Dose Amount");
                String time = get("Time Taken");
                Medication newMeds = new Medication(type, dose, time);
                medications.add(newMeds);
                String answer = get("Would you like to add more(yes/no)");
                if (answer.equalsIgnoreCase("no"))
                    more = false;
            }
        }

        ArrayList<String> allergies = new ArrayList<>();
        boolean moreAllergies = true;
        while (moreAllergies) {
            String allergy = get("Allergies");
            allergies.add(allergy);
            String answer = get("Would you like to add more allergies?(yes/no)");
            if (answer.equalsIgnoreCase("no"))
                moreAllergies = false;
        }

        System.out.println("\nDoctor Information");
        String relationToPerson = "Pediatrician";
        String doctorFirstName = get("First Name");
        String doctorLastName = get("Last Name");
        String phoneNumber = get("Phone Number");
        String doctorAddress = get("Email Address");
        Contact pediatrician = new Contact(doctorFirstName, doctorLastName, phoneNumber, doctorAddress,
                relationToPerson);

        System.out.println("\nEmergency Contacts");
        ArrayList<Contact> emergencyContacts = new ArrayList<>();
        boolean moreContacts = true;
        while (moreContacts) {
            String contactFirstName = get("First Name");
            String contactLastName = get("Last Name");
            String conatctphoneNumber = get("Phone Number");
            String contactAddress = get("Email Address");
            String conatctrelationToPerson = get("Relation To Person");
            Contact contact = new Contact(contactFirstName, contactLastName, conatctphoneNumber, contactAddress,
                    conatctrelationToPerson);
            emergencyContacts.add(contact);
            String answer = get("Would you like to add more emergency contacts?(yes/no)");
            if (answer.equalsIgnoreCase("no"))
                moreContacts = false;
        }

        return facade.addCamper(firstName, lastName, homeAddress, doB, sex, medications, allergies, emergencyContacts,
                pediatrician);
    }

    /**
     * Creates a new counselor
     */
    private void createCounselor() {
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
        Contact pediatrician = new Contact(doctorFirstName, doctorLastName, doctorphoneNumber, doctorAddress,
                relationToPerson);

        System.out.println("\nEmergency Contacts");
        ArrayList<Contact> emergencyContacts = new ArrayList<>();
        boolean moreContacts = true;
        while (moreContacts) {
            String contactFirstName = get("First Name");
            String contactLastName = get("Last Name");
            String conatctphoneNumber = get("Phone Number");
            String contactAddress = get("Email Address");
            String conatctrelationToPerson = get("Relation To Person");
            Contact contact = new Contact(contactFirstName, contactLastName, conatctphoneNumber, contactAddress,
                    conatctrelationToPerson);
            emergencyContacts.add(contact);
            String answer = get("Would you like to add more emergency contacts?(yes/no)");
            if (answer.equalsIgnoreCase("no"))
                moreContacts = false;
        }

        System.out.println("Which camp would you like to sign up for?");
        System.out.println(facade.getCamps());
        String camp = scan.nextLine();
        System.out.println("Pick a week");
        boolean moreWeeks = true;
        while (moreWeeks) {
            int i = 1;
            for (Week week : facade.getWeeks(camp)) {
                System.out.println("Week " + i + ": " + week);
                i++;
            }
            System.out.println("Week Number:");
            facade.getCampList().getCamp(camp).getWeek(scan.nextInt());
            scan.nextLine();
            String answer = get("Would you like to add another week?(yes/no)");
            if (answer.equalsIgnoreCase("no"))
                moreWeeks = false;
        }

        facade.addCounselor(firstName, lastName, phoneNumber, emailAddress, homeAddress, doB, emergencyContacts,
                pediatrician, loginInfo);
    }

    /**
     * Logs someone in if their credentials match the database
     */
    private void login() {
        String userName = get("Username");
        String password = get("Password");
        LoginInfo loginInfo = new LoginInfo(userName, password);
        int loginnum = facade.Login(loginInfo);
        if (loginnum == 1) {
            directorScreen();
        } else if (loginnum == 2) {
            userScreen();
        } else if (loginnum == 3) {
            counselorScreen();
        } else
            System.out.println("Username and Password are not valid");
    }

    /**
     * Formats questions and returns teh users answer
     * 
     * @param prompt
     * @return users input
     */
    private String get(String prompt) {
        System.out.print(prompt + ": ");
        return scan.nextLine();
    }

    /**
     * formats a string into a date class
     * 
     * @param date
     * @return date
     */
    private Date formatDate(String date) {
        try {
            return new SimpleDateFormat("MM/dd/yyyy").parse(date);
        } catch (ParseException e) {
            System.out.println("Sorry " + date + " is not valid");
            return null;
        }
    }

    /**
     * Welcomes the user once they sign in with their first and last name
     */
    private void welcomeUserScreen() {
        System.out.println("\nWelcome Back, " + facade.getCurrentUser().getFirstName() + " "
                + facade.getCurrentUser().getLastName() + "!");
    }

    /**
     * Welcomes the user once they sign in with their first and last name
     */
    private void welcomeDirectorScreen() {
        System.out.println("\nWelcome Back, " + facade.getCurrentDirector().getFirstName() + " "
                + facade.getCurrentDirector().getLastName() + "!");
    }

    /**
     * Welcomes the user once they sign in with their first and last name
     */
    private void welcomeCounselorScreen() {
        System.out.println("\nWelcome Back, " + facade.getCurrentCounselor().getFirstName() + " "
                + facade.getCurrentCounselor().getLastName() + "!");
    }

    /**
     * The screen users see when they login
     */
    private void userScreen() {
        welcomeUserScreen();
        boolean run = true;
        while (run) {
            userOptions();
            int option = scan.nextInt();
            scan.nextLine();
            switch (option) {
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
                    run = false;
                    break;
                default:
                    System.out.println("Please enter a valid number");
                    break;
            }
            pause();
        }

    }

    /**
     * Prints the options the user has
     */
    private void userOptions() {
        System.out.println("1. View My Profile\n2. Edit My Profile\n3. View My Existing Campers\n" +
                "4. Edit My Existing Campers\n5. Register New Camper\n6. View discounts\n7. Logout");
    }

    /**
     * Displays all the campers that the user has and lets them choose one
     */
    private String chooseCamper(String action) {
        System.out.println("Your Current Campers: \n" + facade.viewCampers());
        System.out.println("Please enter the first name of the one you want to " + action + ": ");
        return scan.nextLine();
    }

    /**
     * Prints out the users total in the camp
     */
    private void discount() {
        if (facade.qualifiesForDiscount())
            System.out.println("You qualify for a 10% discount!");
        else
            System.out.println("You do not qualify for a discount");
    }

    /**
     * Gives the user options to edit their profile, then edits the profile
     */
    private void editUser() {
        boolean run = true;
        while (run) {
            System.out.println("What would you like to edit:\n1. First Name \n2. Last Name" +
                    "\n3. Home Address\n4. Date of Birth\n5. Quit");
            int choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
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
                    break;
                default:
                    System.out.println("Please enter a valid number");
                    break;
            }
        }
    }

    /**
     * edits the campers profile
     * 
     * @param firstname name of camper being edited
     */
    private void editCamper(String firstname) {
        boolean run = true;
        while (run) {
            System.out.println("What would you like to edit:\n1. First Name \n2. Last Name" +
                    "\n3. Home Address\n4. Date of Birth\n5. Sex\n6. Allergies" +
                    "\n7. Emergency Contacts\n8. Doctor Information\n9. Quit");
            int choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
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
                    Sex newdate = Enum.valueOf(Sex.class, get("Sex(MALE/FEMALE)"));
                    facade.editCamperSex(firstname, newdate);
                    break;
                case 6:
                    editAllergies(firstname);
                    break;
                case 7:
                    facade.editCamperEmergencyContacts(firstname,
                            editEC(facade.getCurrentUser().getCamper(firstname).getEmergencyContacts()));
                    break;
                case 8:
                    facade.editCamperPediatrician(firstname,
                            editDoctor(facade.getCurrentUser().getCamper(firstname).getPediatrician()));
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
     * 
     * @param camper
     */
    private void editAllergies(String camper) {
        ArrayList<String> newAllergies = (ArrayList<String>) facade.getCurrentUser().getCamper(camper).getAllergies()
                .clone();
        boolean run = true;
        while (run) {
            System.out.println("1. Delete An Existing Allergy\n2.Add A New Allergy\n3. Finish Editing Allergies");
            int choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Choose an allergy to delete");
                    for (int i = 0; i < newAllergies.size(); i++) {
                        System.out.println((i + 1) + ". " + newAllergies.get(i));
                    }
                    newAllergies.remove(scan.nextInt() - 1);
                    scan.nextLine();
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
     * 
     * @param emergencyContacts
     * @return the edited arrayList
     */
    private ArrayList<Contact> editEC(ArrayList<Contact> emergencyContacts) {
        System.out.println("1. Add New Emergency Contact\n2. Remove An Existing Emergency Contact");
        int choice = scan.nextInt();
        scan.nextLine();
        if (choice == 1) {
            System.out.println("New Emergency Contact");
            String contactFirstName = get("First Name");
            String contactLastName = get("Last Name");
            String conatctphoneNumber = get("Phone Number");
            String contactAddress = get("Email Address");
            String conatctrelationToPerson = get("Relation To Person");
            Contact contact = new Contact(contactFirstName, contactLastName, conatctphoneNumber, contactAddress,
                    conatctrelationToPerson);
            emergencyContacts.add(contact);
        } else if (choice == 2) {
            System.out.println("Remove: ");
            for (int i = 0; i < emergencyContacts.size(); i++) {
                System.out.println((i + 1) + ". " + emergencyContacts.get(i).getFirstName() + " "
                        + emergencyContacts.get(i).getLastName());
            }
            emergencyContacts.remove(scan.nextInt());
            scan.nextLine();
        } else
            System.out.println("Please enter a valid number");
        return emergencyContacts;
    }

    /**
     * edits doctor information
     * 
     * @param doctor
     * @return
     */
    private Contact editDoctor(Contact doctor) {
        boolean run = true;
        while (run) {
            System.out.println("Change: \n1. First Name\n2. Last Name\n3. Phone Number\n4. Email Address\n5. Quit");
            int choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
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
    private void counselorScreen() {
        welcomeCounselorScreen();
        boolean run = true;
        while (run) {
            counselorOptions();
            int option = scan.nextInt();
            scan.nextLine();
            switch (option) {
                case 1:
                    System.out.println(facade.viewCounselorProfile());
                    break;
                case 2:
                    editCounselor();
                    break;
                case 3:
                    viewGroup();
                    break;
                case 4:
                    viewGroupInfo();
                    break;
                case 5:
                    String camp = get("Camp");
                    int week = Integer.parseInt(get("Week Number"));
                    System.out.println(facade.getSchedule(camp, week));
                    break;
                case 6:
                    run = false;
                    break;
                default:
                    System.out.println("Please enter a valid number");
                    break;
            }
            pause();
        }
    }

    /**
     * Displays all campers in the counselors group
     */
    private void viewGroup() {
        String camp = get("Camp");
        int week = Integer.parseInt(get("Week Number"));
        ArrayList<Camper> campers = facade.getGroup(camp, week);
        for (Camper camper : campers) {
            System.out.println(camper.toStringBrief() + "\n");
        }
    }

    /**
     * Displays teh information of all campers in the group
     */
    private void viewGroupInfo() {
        String camp = get("Camp");
        int week = Integer.parseInt(get("Week Number"));
        ArrayList<Camper> campers = facade.getGroup(camp, week);
        for (Camper camper : campers) {
            System.out.println(camper.toStringFull() + "\n");
        }
    }

    /**
     * Prints out the counselors options
     */
    private void counselorOptions() {
        System.out.println("1. View My Profile\n2. Edit My Profile\n3. View Campers\n4. View Camper Information" +
                "\n5. View My Schedule\n6. Logout");
    }

    private void editCounselor() {
        boolean run = true;
        while (run) {
            System.out.println("What would you like to edit:\n1. First Name \n2. Last Name" +
                    "\n3. Home Address\n4. Date of Birth\n5. Phone Number" +
                    "\n6. Emergency Contacts\n7. Doctor Information\n8. Quit");
            int choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
                case 1:
                    String newfirstname = get("First Name");
                    facade.editCounselorFirstName(newfirstname);
                    break;
                case 2:
                    String newlastname = get("Last Name");
                    facade.editCounselorLastName(newlastname);
                    break;
                case 3:
                    String newhomeaddress = get("Home Address");
                    facade.editCounselorHomeAddress(newhomeaddress);
                    break;
                case 4:
                    String newdob = get("Date of Birth(MM/DD/YYYY");
                    facade.editCounselorDateOfBirth(formatDate(newdob));
                    break;
                case 5:
                    String newphonenumber = get("Phone Number");
                    facade.editCounselorPhoneNumber(newphonenumber);
                    break;
                case 6:
                    facade.editCounselorEmergencyContacts(editEC(facade.getCurrentCounselor().getEmergencyContacts()));
                    break;
                case 7:
                    facade.editCounselorDoctor(editDoctor(facade.getCurrentCounselor().getPediatrician()));
                case 8:
                    run = false;
                    break;
                default:
                    System.out.println("Please enter a valid number");
                    break;
            }
            pause();
        }
    }

    /**
     * The screen directors see when the login
     */
    private void directorScreen() {
        welcomeDirectorScreen();
        boolean run = true;
        while (run) {
            directorOptions();
            int option = scan.nextInt();
            scan.nextLine();
            switch (option) {
                case 1: //View My Profile
                    System.out.println(facade.viewDirectorProfile());
                    break;
                case 2: //Edit My (director) Profile
                    editDirector();
                    break;
                case 3: //Create new camp
                    createCamp();
                    break;
                case 4: //View Activities
                    System.out.println("What camp would you like to see?");
                    System.out.println(facade.getActivities(scan.nextLine()));
                    break;
                case 5: //Edit Activities
                    changeActivites();
                    break;
                case 6: // View All counselors
                    //System.out.println(facade.getCounselorList());
                    System.out.println(facade.getCounselors());

                    break;
                case 7: //View a Counselors Information
                    String firstName = get("Counselor's First Name");
                    String lastName = get("Counselor's Last Name");
                    System.out.println(facade.getCounselorList().getCounselorByName(firstName, lastName));
                    break;
                case 8: //Remove Counselor
                    // remove counselor
                    // Derek wrote this not sure if its right
                    // System.out.println("Doesnt work");
                    String campName = get("Camp");
                    String counselorname = get("Counselor First Name"); // I assume this is the proper call to prompt
                    String counselorlname = get("Counselor Last Name");
                    facade.removeCounselor(counselorname, counselorlname, facade.getCampList().getCamp(campName));
                    break;
                case 9: //View All Campers
                    System.out.println(facade.getCampers());
                    break;
                case 10: //View A Camper
                    String camperName = get("Camper's First Name");
                    String camperlName = get("Camper's Last Name");
                    System.out.println(facade.getCamperList().getCamperByName(camperName,camperlName));
                    
                case 11: //Remove Camper
                    String campname = get("Camp");
                    // int weeknum = Integer.parseInt(get("Week"));
                    String campername = get("Camper First Name");
                    String camperlname = get("Camper Last Name");
                    facade.removeCamper(campername, camperlname,
                        facade.getCampList().getCamp(campname));
                    break;
                case 12: //View Schedule
                    String camp = get("Camp");
                    int week = Integer.parseInt(get("Week"));
                    int group = Integer.parseInt(get("Group"));
                    System.out.println(facade.getSchedule(camp, week, group));
                    break;
                case 13: //Logout
                    run = false;
                    break;
                default:
                    System.out.println("Please enter a valid number");
                    break;
            }
            pause();
        }
    }

    /**
     * options on the directors homepage
     */
    private void directorOptions() {
        System.out.println(
                "1. View My Profile\n2. Edit My Profile\n3. Create New Camp\n4. View Activities\n5. Edit Activites\n6. View All Counselors"
                        +
                        "\n7. View a Counselors Information\n8. Remove Counselor\n9. View All Campers\n10. View a Campers Information\n11. Remove Camper\n12. View Schedules"
                        +
                        "\n13. Logout");
    }

    /**
     * edits the directors information
     */
    private void editDirector() {
        boolean run = true;
        while (run) {
            System.out.println("What would you like to edit:\n1. First Name \n2. Last Name" +
                    "\n3. Home Address\n4. Date of Birth\n5. Quit");
            int choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
                case 1:
                    String newfirstname = get("First Name");
                    facade.editDirectorFirstName(newfirstname);
                    break;
                case 2:
                    String newlastname = get("Last Name");
                    facade.editDirectorLastName(newlastname);
                    break;
                case 3:
                    String newhomeaddress = get("Home Address");
                    break;
                case 4:
                    String newdob = get("Date of Birth(MM/DD/YYYY");
                    facade.editDirectorDateOfBirth(formatDate(newdob));
                    break;
                case 5:
                    run = false;
                    break;
                default:
                    System.out.println("Please enter a valid number");
                    break;
            }
        }
    }

    /**
     * prompts dircetor for new camp information
     */
    private void createCamp() {
        int year = Integer.parseInt(get("Year of Camp"));
        String name = get("Name of Camp");
        String description = get("Short Description of Camp");
        String weekString = get("Number of Weeks");
        int weeks = Integer.parseInt(weekString);
        facade.newCamp(name, description, weeks, year);
        for (int i = 1; i <= weeks; i++) {
            System.out.println("Week " + i + ": ");
            Date startDate = formatDate(get("Start Date(MM/DD/YYYY)"));
            Date endDate = formatDate(get("End Date(MM/DD/YYYY)"));
            String theme = get("Theme");
            facade.setWeek(name, i - 1, startDate, endDate, theme);
        }
        int numActivities = Integer.parseInt(get("How many activities would you like to add"));
        ArrayList<Activity> activities = new ArrayList<>();
        for (int i = 1; i <= numActivities; i++) {
            System.out.println("Activity " + i);
            String activityName = get("Name");
            String activityDesc = get("Description");
            String location = get("Location");
            Activity activity = new Activity(activityName, location, activityDesc);
            activities.add(activity);
        }
        facade.getCampList().getCamp(name).setActivities(activities);
        for (int i = 0; i < weeks; i++) {
            facade.getCampList().getCamp(name).getWeek(i).generateSchedules(activities);
        }
    }

    /**
     * Changes activities for a given camp
     */
    private void changeActivites() {
        String camp = get("Which Camp would you like to change the activities for");
        boolean run = true;
        while (run) {
            System.out.println("Would you like to: \n1. Edit Current Activites \n2. Delete Current Activites" +
                    "\n3. Add New Activities \n4. Quit");
            int choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
                case 1:
                    editActivities(camp);
                    break;
                case 2:
                    deleteActivity(camp);
                    break;
                case 3:
                    addActivity(camp);
                    break;
                case 4:
                    run = false;
                    break;
                default:
                    System.out.println("Please enter a valid number");
            }
        }
    }

    /**
     * edits particular activities
     * 
     * @param camp
     */
    private void editActivities(String camp) {
        System.out.println("Which Activity would you like to edit?");
        int i = 1;
        for (Activity activity : facade.getCampList().getCamp(camp).getActivitiesArrayList()) {
            System.out.println(i + ". " + activity);
            i++;
        }
        int num = scan.nextInt() - 1;
        //int num = scan.nextInt();
        scan.nextLine();
        System.out.println("New Activity");
        String activityName = get("Name");
        String activityDesc = get("Description");
        String location = get("Location");
        Activity activity = new Activity(activityName, location, activityDesc);
        facade.getCampList().getCamp(camp).getActivitiesArrayList().set(num, activity);
    }

    /**
     * deletes a particular activity
     * 
     * @param camp
     */
    private void deleteActivity(String camp) {
        System.out.println("Which Activity would you like to delete?");
        int i = 1;
        for (Activity activity : facade.getCampList().getCamp(camp).getActivitiesArrayList()) {
            System.out.println(i + ". " + activity);
            i++;
        }
        facade.getCampList().getCamp(camp).getActivitiesArrayList().remove(scan.nextInt() - 1);
        scan.nextLine();
    }

    /**
     * adds an activity to a camp
     * 
     * @param camp
     */
    private void addActivity(String camp) {
        System.out.println("New Activity");
        String activityName = get("Name");
        String activityDesc = get("Description");
        String location = get("Location");
        Activity activity = new Activity(activityName, location, activityDesc);
        facade.getCampList().getCamp(camp).getActivitiesArrayList().add(activity);
    }

    public static void main(String[] args) {
        CampFacade facade = new CampFacade();
        CampButterfliesDriver driver = new CampButterfliesDriver(facade);
        driver.run();
    }
}
