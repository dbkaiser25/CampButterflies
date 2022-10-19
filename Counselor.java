import java.util.ArrayList;
import java.util.UUID;

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
    public Counselor(String firstName, String lastName, String phoneNumber, String emailAddress, String homeAddress, 
    String dateOfBirth, String medicalInfo, ArrayList<Contact> emergencyContacts, Contact pediatrician, LoginInfo userLogin)
    {
        super(firstName,lastName,dateOfBirth,homeAddress);
        this.emailAddress = emailAddress;
        this.emergencyContacts = emergencyContacts;
        this.pediatricion = pediatricion;
        this.medicalInfo = medicalInfo;
        this.phoneNumber = phoneNumber;
        this.userLogin = userLogin;
    }

    //Constructor with UUID
    public Counselor(UUID uuid, String firstName, String lastName, String phoneNumber, String emailAddress, String homeAddress, 
    String dateOfBirth, String medicalInfo, ArrayList<Contact> emergencyContacts, Contact pediatrician, LoginInfo userLogin)
    {
        super(uuid,firstName,lastName,dateOfBirth,homeAddress);
        this.emailAddress = emailAddress;
        this.emergencyContacts = emergencyContacts;
        this.pediatricion = pediatricion;
        this.medicalInfo = medicalInfo;
        this.phoneNumber = phoneNumber;
        this.userLogin = userLogin;
    }

    //counselors need to be able to view the campers in their group
    //Can we just pass them their group somehow? I am going to assume we can but to be determined later TODO
    public String viewCampers(Group group)
    {
        String temp = new String();
        for(int i = 0; i < group.getCamperList().size(); i++)
        {
            //TODO what camper info does the counselor need to see? Right now they get their first and last name thats it. 
            temp = temp + "\n" + group.getCamperList().get(i).getFirstName() + " " + group.getCamperList().get(i).getLastName();
        }
        return temp;
    }

    //TODO clearly there's a bigger problem of how are counselors able to see their whatever group. 
    //This needs to be determined while I assume we can pass the correct group
    //This can be modified to give a prettier output but file that under UI problem
    /*
     * The string output should look something like this assuming i didn't make any mistakes 
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
    public String viewSchedule(Group group)
    {
        String temp = new String();

        //This really looks like it should be a loop but i can't figure out a way to increment the days of the week in a way thats faster than this
        temp = getActivities(group, DayOfWeek.SUNDAY);
        temp = temp + "\n" + getActivities(group, DayOfWeek.MONDAY);
        temp = temp + "\n" + getActivities(group, DayOfWeek.TUESDAY);
        temp = temp + "\n" + getActivities(group, DayOfWeek.WEDNESDAY);
        temp = temp + "\n" + getActivities(group, DayOfWeek.THURSDAY);
        temp = temp + "\n" + getActivities(group, DayOfWeek.FRIDAY);
        temp = temp + "\n" + getActivities(group, DayOfWeek.SATURDAY);
        return temp;
    }

    //helper method for viewSchedule
    //Same thing can be modified for prettier output
    private String getActivities(Group group, DayOfWeek day)
    {
        String temp = new String();
        temp = day.toString() + "\n";
        for(int i = 0; i < group.getSchedule().get(day).size(); i++)
        {
            temp = temp + group.getSchedule().get(day).get(i).getName() + "\n";
        }
        return temp;
    }

    public String toString()
    {
        return "something";
    }

}