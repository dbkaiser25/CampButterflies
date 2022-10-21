
import java.util.UUID;
import java.util.ArrayList;
import java.util.Scanner;

public class CampFacade 
{
    private CampList campList;
    private CamperList camperList;
    private UserList userList;
    private CounselorList counselorList;
    private DirectorList directorList;
    private Scanner scan = new Scanner(System.in);

    public CampFacade(CampList campList, CamperList camperList, UserList userList, 
    CounselorList counselorList, DirectorList directorList)
    {
        this.campList = campList;
        this.camperList = camperList;
        this.userList = userList;
        this.counselorList = counselorList;
        this.directorList = directorList;
    }

    public void Login(LoginInfo userLogin)
    {
        
    }

    public void addUser(){
        String username = get("Username");
        String password = get("Password");
        LoginInfo loginInfo = new LoginInfo(username, password);
        String firstName = get("First Name");
        String lastName = get("Last Name");
        String homeAddress = get("Home Address");
        String dateOfBirth = get("Date of Birth(MM/DD/YYYY)");

        ArrayList<Camper> campers = new ArrayList<>();
        boolean more = true;
        while(more){
            System.out.println("Camper Information");
            Camper camper = addCamper();
            campers.add(camper);
            String answer = get("Would you like to add more campers?(yes/no)");
            if(answer.equalsIgnoreCase("no"))
                    more = false;
        }

        User user = new User(firstName, lastName, dateOfBirth, homeAddress, loginInfo, campers);
        userList.addUser(user);
    }

    public Camper addCamper()
    {
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

        Camper camper = new Camper(firstName, lastName, homeAddress, dateOfBirth, sex, medications, allergies, emergencyContacts, pediatrician);
        camperList.addCamper(camper);
        return camper;
    }

    private String get(String prompt){
        System.out.print(prompt + ": ");
        return scan.nextLine();
    }

    public void editCamperProfile()
    {

    }

    public void editUserProfile()
    {

    }

    public void addCounselor()
    {
        String username = get("Username");
        String password = get("Password");
        LoginInfo loginInfo = new LoginInfo(username, password);
        String firstName = get("First Name");
        String lastName = get("Last Name");
        String phoneNumber = get("Phone Number");
        String emailAddress = get("Email Address");
        String homeAddress = get("Home Address");
        String dateOfBirth = get("Date of Birth(MM/DD/YYYY)");
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

        Counselor counselor = new Counselor(firstName, lastName, phoneNumber, emailAddress, homeAddress, dateOfBirth, emergencyContacts, pediatrician, loginInfo);
        counselorList.addCounselor(counselor);
    }

    public boolean qualifiesForDiscount()
    {
        return true;
    }

    public void selectWeek()
    {

    }

    public void viewUserProfile()
    {

    }

    public void viewCamperProfile()
    {

    }

    public void editCalendar()
    {

    }

    public String viewCalendar()
    {
        return null;
    }

    public String getActivities()
    {
        return null;
    }
    //this needs an instance but of what?????
    //TODO
    public void getInstance()
    {

    }


    public User getCurrentUser(LoginInfo userLogin)
    {
        return null;
    }   

}

