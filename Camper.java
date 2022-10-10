import java.util.ArrayList;

/**
 * A class that defines a camper for the camp
 * @author dbkaiser
 */
public class Camper extends Person
{
    private String sex;
    private String medicalInfo;
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
    public Camper(String firstName, String lastName, String PhoneNumber, String emailAddress, String homeAddress, String dateOfBirth, String sex, String medicalInfo, ArrayList<Contact> emergencyContacts, Contact pediatrician)
    {
        super(firstName,lastName,dateOfBirth,homeAddress);
        this.sex = sex;
        this.medicalInfo = medicalInfo;
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
