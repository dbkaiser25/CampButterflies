
import java.util.ArrayList;
import java.util.UUID;


public class User extends Person
{
    private UUID uuid;  
    private LoginInfo userLogin;
    private ArrayList<Camper> campers;
    
    //Constructor without UUID
    public User(String firstName, String lastName, String dateOfBirth, 
    String homeAddress, LoginInfo userLogin, ArrayList<Camper> campers)
    {
        super(firstName,lastName,dateOfBirth,homeAddress);
        this.userLogin = userLogin;
        this.campers = campers;
    }

    //Constructor with UUID
    public User(UUID uuid, String firstName, String lastName, String dateOfBirth, 
    String homeAddress, LoginInfo userLogin, ArrayList<Camper> campers)
    {
        super(uuid,firstName,lastName,dateOfBirth,homeAddress);
        this.userLogin = userLogin;
        this.campers = campers;
    }

    public void addCamper(Camper camper)
    {

    }

    public boolean qualifiesForDiscount()
    {
        return false;
    }

    public void selectWeek()
    {

    }

    public void viewUserProfile()
    {
        
    }

    public void editUserProfile()
    {

    }

    //TODO do we like these parameters?
    public void viewCamperProfile(String firstName, String lastName)
    {

    }

    public void editCamperProfile(String firstName, String lastName)
    {

    }

    public String toString()
    {
        return "     ";
    }
}
