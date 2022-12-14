
/**
 * An abstract class that defines a person to be inherited 
 */

import java.util.UUID;
import java.util.Date;

public abstract class Person {
    protected UUID uuid;
    protected String firstName;
    protected String lastName;
    protected Date dateOfBirth;
    protected String homeAddress;

    /**
     * A method to create an instance of a person
     * 
     * @param firstName    A string for their first name
     * @param lastName     A string for their last name
     * @param dateOfBirth2 A string for their date of birth
     * @param homeAddress  A string for their home address
     */
    // Constructor without UUID
    public Person(String firstName, String lastName, Date dateOfBirth, String homeAddress) {
        this.uuid = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.homeAddress = homeAddress;
    }

    // constructor with UUID
    public Person(UUID uuid, String firstName, String lastName, Date dateOfBirth, String homeAddress) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.homeAddress = homeAddress;
    }

    public UUID getUUID() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
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
}
