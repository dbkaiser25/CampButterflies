import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;

/**
 * A class that defines a camper for the camp
 * 
 * @author dbkaiser
 */
public class Camper extends Person {
    private Sex sex;
    private ArrayList<Medication> medications = new ArrayList<Medication>();
    private ArrayList<String> allergies = new ArrayList<String>();
    private ArrayList<Contact> emergencyContacts = new ArrayList<Contact>();
    private Contact pediatrician;

    /**
     * The following attributes are needed to create an individual camper
     * 
     * @param sex               A string for their sex
     * @param medicalInfo       A string for their medical info
     * @param emergencyContacts A list of the camper's emergency contacts
     * @param pediatrician      A contact for the camper's pediatrician
     *                          TODO Figure out the constructors for emergency
     *                          contacts and maybe pediatrician
     */
    public Camper(String firstName, String lastName, String homeAddress, Date dateOfBirth,
            Sex sex, ArrayList<Medication> medications, ArrayList<String> allergies,
            ArrayList<Contact> emergencyContacts, Contact pediatrician) {
        super(firstName, lastName, dateOfBirth, homeAddress);
        this.sex = sex;
        this.medications = medications;
        this.allergies = allergies;
        this.emergencyContacts = emergencyContacts;
        this.pediatrician = pediatrician;
    }

    // constructor with UUID
    public Camper(UUID uuid, String firstName, String lastName, String homeAddress, Date dateOfBirth,
            Sex sex, ArrayList<Medication> medications, ArrayList<String> allergies,
            ArrayList<Contact> emergencyContacts, Contact pediatrician) {
        super(uuid, firstName, lastName, dateOfBirth, homeAddress);
        this.sex = sex;
        this.medications = medications;
        this.allergies = allergies;
        this.emergencyContacts = emergencyContacts;
        this.pediatrician = pediatrician;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public ArrayList<Medication> getMedications() {
        return medications;
    }

    public void setMedications(ArrayList<Medication> medications) {
        this.medications = medications;
    }

    public ArrayList<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(ArrayList<String> allergies) {
        this.allergies = allergies;
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

    /**
     * A description of the camper in string form
     * 
     * @return A string description of the camper
     */
    public String toStringBrief() {
        return firstName + " " + lastName + " " + homeAddress;
    }

    //different groups need to see different amounts of camper information
    public String toStringFull()
    {
        String temp = new String();

        /*
         * temp = "Counselor:  " + firstName + " " + lastName + "\nDate of Birth: " + dateOfBirth.getMonth() + 
        "/" + dateOfBirth.getDate() + "/" + dateOfBirth.getYear() + "\nAddress: " + homeAddress + 
        "\nEmail: " + emailAddress + "\n " + pediatrician + "Medical Info: \n" + medicalInfo + "\nPhone Number: " + phoneNumber;
         */

        temp = "Camper: " + firstName + " " + lastName + "\nDate of Birth: " + 
        return "";
    }
}
