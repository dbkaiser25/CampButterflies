
import java.util.ArrayList;
import java.util.UUID;


public class User extends Person
{
    private UUID uuid;  
    private LoginInfo userLogin;
    private ArrayList<Camper> campers = new ArrayList<Camper>();
    
    //Constructor without UUID
    public User(String firstName, String lastName, String dateOfBirth, 
    String homeAddress, LoginInfo userLogin, ArrayList<Camper> campers)
    {
        super(firstName,lastName,dateOfBirth,homeAddress);
        this.userLogin = userLogin;
        this.campers = campers;
        uuid = UUID.randomUUID();
    }

    //Constructor with UUID
    public User(UUID uuid, String firstName, String lastName, String dateOfBirth, 
    String homeAddress, LoginInfo userLogin, ArrayList<Camper> campers)
    {
        super(uuid,firstName,lastName,dateOfBirth,homeAddress);
        this.userLogin = userLogin;
        this.campers = campers;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LoginInfo getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(LoginInfo userLogin) {
        this.userLogin = userLogin;
    }

    public ArrayList<Camper> getCampers() {
        return campers;
    }

    public void setCampers(ArrayList<Camper> campers) {
        this.campers = campers;
    }

    public void addCamper(Camper camper)
    {
        if(camper != null)
            campers.add(camper);
    }

    public boolean qualifiesForDiscount()
    {
        //TODO figure out/finalize what discounts there are 
        return false;
    }

    public void selectWeek()
    {

    }

    //TODO
    //Essentially this is going to become the toString method, I don't think an 
    //actual toString method is necessary because we have this instead
    public String viewUserProfile()
    {
        return " ";
    }

    //TODO how will this method work, can something like editCamper work?
    public void editUserProfile()
    {

    }

    /**
     * This method will display a camper's personal information so the user can view it
     * @param firstName The first name of the camper the user wants to view
     * @return A string description of the camper's information 
     */
    public String viewCamperProfile(String firstName)
    {
        for(int i = 0; i < campers.size(); i++)
        {
            if(campers.get(i).getFirstName().equals(firstName))    
            {
                return campers.get(i).toString();
            }
        }
        return "There are no campers with that name";
    }

    /**
     * This method will update one of the camper's information that the user has registered
     * It works by having a camper with the updated information passed to the method
     * @param newCamper The name of the camper object with the updated information to replace the old camper
     */
    public void editCamperProfile(Camper newCamper)
    {
        if(newCamper != null)
        {
            for(int i = 0; i < campers.size(); i++)
            {
                if(newCamper.getFirstName().equals(campers.get(i).getFirstName()));
                {
                    //this will change the order of campers in the list, I don't think that matters?
                    campers.remove(i);
                    campers.add(newCamper);
                    i = campers.size();
                }
            }
        }
    }

    //TODO consider deleating
    public String toString()
    {
        return "     ";
    }
}
