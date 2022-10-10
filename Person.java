/**
 * An abstract class that defines a person to be inherited 
 * @author dbkaiser
 */
public abstract class Person 
{
    protected String firstName;
    protected String lastName;
    protected String dateOfBirth;
    protected String homeAddress;

    /**
     * A method to create an instance of a person
     * @param firstName A string for their first name
     * @param lastName A string for their last name
     * @param dateOfBirth A string for their date of birth
     * @param homeAddress A string for their home address
     */
    public Person(String firstName, String lastName, String dateOfBirth, String homeAddress)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.homeAddress = homeAddress;
    }
}
