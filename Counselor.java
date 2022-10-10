import java.util.ArrayList;

/** 
 * A class that defines a counselor 
 * @author dbkaiser
*/
public class Counselor extends Person
{
    //TODO figure out which classes/arraylists need to be initialized here
    private String emailAddress;
    private ArrayList<Contact> emergencyContacts;
    private Contact pediatricion;
    private String medicalInfo;
    private String phoneNumber;
    private LoginInfo userLogin;

    /**
     * A counselor is instantiated with the following attributes
     * @param emailAddress A string for their email address
     * @param dateOfBirth A string for their date of birth
     * @param medicalInfo A string for their medical info
     * @param emergencyContacts An arraylist for their emergency contacts
     * @param pediatrician A contact for their pediatrician
     * @param userLogin Logininfo for the individual counselor
     */
    public Counselor(String firstName, String lastName, String phoneNumber, String emailAddress, String homeAddress, String dateOfBirth, String medicalInfo, ArrayList<Contact> emergencyContacts, Contact pediatrician, LoginInfo userLogin)
    {
        super(firstName,lastName,dateOfBirth,homeAddress);
        this.emailAddress = emailAddress;
        this.emergencyContacts = emergencyContacts;
        this.pediatricion = pediatricion;
        this.medicalInfo = medicalInfo;
        this.phoneNumber = phoneNumber;
        this.userLogin = userLogin;
    }

    public String viewCampers()
    {
        return "something idk what";
    }

    public String viewSchedule()
    {
        return "a string";
    }

    //TODO write the calendar class
    //public Calendar selectWeeks()
    //{
        //return null;
    //}

    public String toString()
    {
        return "something";
    }

}
