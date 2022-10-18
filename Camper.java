import java.util.ArrayList;
import java.util.UUID;

/**
 * A class that defines a camper for the camp
 * @author dbkaiser
 */
public class Camper extends Person
{
    private String sex;
    private ArrayList<Medication> medications = new ArrayList();
    private ArrayList<String> allergies = new ArrayList();
    private ArrayList<Contact> emergencyContacts = new ArrayList();
    private Contact pediatrician;
  
    /**
     * The following attributes are needed to create an individual camper
     * @param sex A string for their sex 
     * @param medicalInfo A string for their medical info
     * @param emergencyContacts A list of the camper's emergency contacts 
     * @param pediatrician A contact for the camper's pediatrician
     * TODO Figure out the constructors for emergency contacts and maybe pediatrician
     */
    public Camper(String firstName, String lastName, String homeAddress, String dateOfBirth,
     String sex, ArrayList<Medication> medications, ArrayList<String> allergies, ArrayList<Contact> emergencyContacts, Contact pediatrician)
    {
        super(firstName,lastName,dateOfBirth,homeAddress);
        this.sex = sex;
        this.medications = medications;
        this.allergies = allergies;
        this.emergencyContacts = emergencyContacts;
        this.pediatrician = pediatrician;
    }

    //constructor with UUID
    public Camper(UUID uuid, String firstName, String lastName, String homeAddress, String dateOfBirth,
     String sex, ArrayList<Medication> medications, ArrayList<String> allergies, ArrayList<Contact> emergencyContacts, Contact pediatrician)
    {
        super(uuid,firstName,lastName,dateOfBirth,homeAddress);
        this.sex = sex;
        this.medications = medications;
        this.allergies = allergies;
        this.emergencyContacts = emergencyContacts;
        this.pediatrician = pediatrician;
    }

    /**
     * A description of the camper in string form
     * @return A string description of the camper
     */
    public String toString()
    {
        return "this is harder than i thought";
    }
}
