import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;

/**
 * A class that defines a counselor
 * 
 * @author dbkaiser
 */
public class Counselor extends Person {
    // TODO figure out which classes/arraylists need to be initialized here
    private String emailAddress;
    private ArrayList<Contact> emergencyContacts;
    private Contact pediatrician;
    // private ArrayList<String> allergies = new ArrayList<String>();
    // private ArrayList<Medication> medications = new ArrayList<Medication>();
    private String phoneNumber;
    private LoginInfo userLogin;

    /**
     * A counselor is instantiated with the following attributes
     * 
     * @param emailAddress      A string for their email address
     * @param dateOfBirth       A string for their date of birth
     * @param emergencyContacts An arraylist for their emergency contacts
     * @param pediatrician      A contact for their pediatrician
     * @param userLogin         Logininfo for the individual counselor
     */
    public Counselor(String firstName, String lastName, String phoneNumber, String emailAddress, String homeAddress,
            Date dateOfBirth,
            ArrayList<Contact> emergencyContacts, Contact pediatrician, LoginInfo userLogin) {
        super(firstName, lastName, dateOfBirth, homeAddress);
        // this.medications = medications;
        // this.allergies = allergies;
        this.emailAddress = emailAddress;
        this.emergencyContacts = emergencyContacts;
        this.pediatrician = pediatrician;
        this.phoneNumber = phoneNumber;
        this.userLogin = userLogin;
        uuid = UUID.randomUUID();
    }

    // Constructor with UUID
    public Counselor(UUID uuid, String firstName, String lastName, String phoneNumber, String emailAddress,
            String homeAddress,
            Date dateOfBirth, ArrayList<Contact> emergencyContacts, Contact pediatrician, LoginInfo userLogin) {
        super(uuid, firstName, lastName, dateOfBirth, homeAddress);
        this.emailAddress = emailAddress;
        this.emergencyContacts = emergencyContacts;
        this.pediatrician = pediatrician;
        this.phoneNumber = phoneNumber;
        this.userLogin = userLogin;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public ArrayList<Contact> getEmergencyContacts() {
        return emergencyContacts;
    }

    public void setEmergencyContacts(ArrayList<Contact> emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
    }

    public Contact getPediatrician() {
        return pediatrician;
    }

    public void setPediatrician(Contact pediatrician) {
        this.pediatrician = pediatrician;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LoginInfo getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(LoginInfo userLogin) {
        this.userLogin = userLogin;
    }

    public UUID getUUID(){
        return uuid;
    }

    // counselors need to be able to view the campers in their group
    // Can we just pass them their group somehow? I am going to assume we can but to
    // be determined later TODO
    public String viewCampers(Group group) {
        String temp = new String();
        for (int i = 0; i < group.getCamperList().size(); i++) {
            // TODO what camper info does the counselor need to see? Right now they get
            // their first and last name thats it.
            temp = temp + "\n" + group.getCamperList().get(i).getFirstName() + " "
                    + group.getCamperList().get(i).getLastName();
        }
        return temp;
    }

    // TODO clearly there's a bigger problem of how are counselors able to see their
    // whatever group.
    // This needs to be determined while I assume we can pass the correct group
    // This can be modified to give a prettier output but file that under UI problem
    /*
     * The string output should look something like this assuming i didn't make any
     * mistakes
     * Sunday
     * Activity1
     * Activity2
     * Activity3
     * ...
     *
     * Monday
     * Activity1
     * ...
     * 
     * Tuesday
     * ...
     */

    public String viewSchedule(Camp camp, int weekNumber) 
    {
        for(Group g: camp.getMasterSchedule().get(weekNumber).getGroups())
        {
            if(g.getCounselor().getFirstName().equals(firstName) && g.getCounselor().getLastName().equals(lastName))
            {
                return g.printSchedule();
            }
        }
        return "You are not apart of a group";
    }

    // helper method for viewSchedule
    // Same thing can be modified for prettier output
    /*
     * private String getActivities(Group group, DayOfWeek day)
     * {
     * this code used to be here but now i believe is in a better place, in groups
     * it will be deleated eventually
     * String temp = new String();
     * temp = day.toString() + "\n";
     * for (int i = 0; i < group.getSchedule().get(day).size(); i++) {
     * temp = temp + group.getSchedule().get(day).get(i).getName() + "\n";
     * }
     * return temp;
     * 
     * 
     * 
     * }
     */

    public String toString() {
        String temp = new String();
        temp = "Counselor:  " + firstName + " " + lastName + "\nUsername: " + userLogin.getUserName()
                + "\nDate of Birth: "
                + dateOfBirth.toString() + "\nAddress: " + homeAddress + "\nEmail: " + emailAddress
                + "\nPhone Number: " + phoneNumber + "\nEmergency Contacts: \n" + printEmergencyContacts()
                + "\nPediatrician: \n" + pediatrician;
        return temp;
    }



    // to be deleated
    private String printEmergencyContacts() {
        String temp = new String();
        for (Contact c : emergencyContacts) {
            temp = temp + c.toString() + "\n";
        }
        return temp;
    }

}
