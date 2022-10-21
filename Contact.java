/**
 * A class for a human contact
 * @author dbkaiser
 */
public class Contact 
{
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private String relationToPerson;

    /**
     * To instantiate a contact with the following attributes
     * @param firstName A string for the contact's first name
     * @param lastName A string for the contact's last name
     * @param phoneNumber A string fot the contact's phone number
     * @param emailAddress A string for their email address
     * @param relationToPerson A strig for the contact's relation to the subject
     */
    public Contact(String firstName, String lastName, String phoneNumber, String emailAddress, String relationToPerson)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.relationToPerson = relationToPerson;
    }

    /**
     * TODO figure out if this is needed or not
     * This info might need to be filled in later check camper for explanation
     */
    public Contact()
    {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getRelationToPerson() {
        return relationToPerson;
    }

    public void setRelationToPerson(String relationToPerson) {
        this.relationToPerson = relationToPerson;
    }

    /**
     * A pretty description of the Contact in string form
     * @return A string for the description of the contact
     */
    public String toString()
    {
        return "A string of the contact";
    }
}
