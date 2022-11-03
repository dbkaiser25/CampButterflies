import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * A class that defines a counselor
 * 
 */
public class Counselor extends Person {

    private String emailAddress;
    private ArrayList<Contact> emergencyContacts;
    private Contact pediatrician;
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

    public Counselor() {
        super(null, null, null, null, null);
        this.emailAddress = null;
        this.emergencyContacts = null;
        this.pediatrician = null;
        this.phoneNumber = null;
        this.userLogin = null;
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

    public UUID getUUID() {
        return uuid;
    }

    public String viewCampers(Group group) {
        String temp = new String();
        for (int i = 0; i < group.getCamperList().size(); i++) {
            temp = temp + "\n" + group.getCamperList().get(i).getFirstName() + " "
                    + group.getCamperList().get(i).getLastName();
        }
        return temp;
    }

    public String viewSchedule(Camp camp, int weekNumber) {
        for (Group g : camp.getMasterSchedule().get(weekNumber).getGroups()) {
            if (g.getCounselor().getFirstName().equals(firstName) && g.getCounselor().getLastName().equals(lastName)) {
                return g.printSchedule();
            }
        }
        return "You are not apart of a group";
    }

    public String toString() {
        String temp = new String();
        temp = "\nCounselor:  " + firstName + " " + lastName + "\nUsername: " + userLogin.getUserName()
                + "\nDate of Birth: " + formatDate(dateOfBirth)
                + "\nAddress: " + homeAddress + "\nEmail: " + emailAddress
                + "\nPhone Number: " + phoneNumber + "\nEmergency Contacts: \n" + printEmergencyContacts()
                + "\nPediatrician: \n" + pediatrician + "\n";
        return temp;
    }

    private String formatDate(Date d) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return formatter.format(d);
    }

    // to be deleated
    private String printEmergencyContacts() {
        String temp = new String();
        for (Contact c : emergencyContacts) {
            temp = temp + c.toString() + "\n";
        }
        return temp;
    }

    public void selectWeek(Camp camp, int weekNumber) {
        getWeek(camp, weekNumber).getCounselors().add(this);
    }

    private Week getWeek(Camp camp, Integer weekNumber) {
        Week week = new Week();
        for (HashMap.Entry<Integer, Week> entry : camp.getMasterSchedule().entrySet()) {
            Integer weekInt = entry.getKey();
            Week thisWeek = entry.getValue();
            if (weekNumber - 1 == weekInt) {
                week = thisWeek;
            }
        }
        return week;
    }
}
