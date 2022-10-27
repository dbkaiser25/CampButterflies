
import java.util.Date;
import java.util.ArrayList;
import java.util.UUID;

public class User extends Person {
    private LoginInfo userLogin;
    private ArrayList<Camper> campers = new ArrayList<Camper>();

    // Constructor without UUID
    public User(String firstName, String lastName, Date dateOfBirth,
            String homeAddress, LoginInfo userLogin, ArrayList<Camper> campers) {
        super(firstName, lastName, dateOfBirth, homeAddress);
        this.userLogin = userLogin;
        this.campers = campers;
        uuid = UUID.randomUUID();
    }

    // Constructor with UUID
    public User(UUID uuid, String firstName, String lastName, Date dateOfBirth,
            String homeAddress, LoginInfo userLogin, ArrayList<Camper> campers) {
        super(uuid, firstName, lastName, dateOfBirth, homeAddress);
        this.userLogin = userLogin;
        this.campers = campers;
    }

    public LoginInfo getUserLogin() {
        return userLogin;
    }

    // consider deleating
    public void setUserLogin(LoginInfo userLogin) {
        this.userLogin = userLogin;
    }

    public ArrayList<Camper> getCampers() {
        return campers;
    }

    public void addCamper(Camper camper) {
        if (camper != null)
            campers.add(camper);
    }

    public boolean qualifiesForDiscount() {
        // TODO figure out/finalize what discounts there are
        return false;
    }

    // TODO ill look at this later
    public void selectWeek(Camp camp, int weekNumber, int camperNumber) 
    {
        //In the UI, the user will register their campers
        //then, they select the weeks option to figure out what weeks they want to sign up for
        //and what camper gets signed up for whatever week
        //paramters: we need to know what camp and year were talking about, 
        //           we need to know which camper
        //           and then which week
        //im thinking this method will get called once to sign a camper up for a week
        //then again for a different week
        //then again for a different camper

        //so for this method to work
        //the camper needs to be added to the campers array list in week
        //we can get to this by accessing the master schedule in camp
        camp.getMasterSchedule().get(weekNumber).getCampers().add(campers.get(camperNumber));



    }

    // TODO
    // Essentially this is going to become the toString method, I don't think an
    // actual toString method is necessary because we have this instead
    public String viewUserProfile() {
        String temp = new String();
        // do we want to show their password too?
        temp = "User: " + firstName + " " + lastName + "\nUsername: " + userLogin.getUserName() + "\nDate of Birth: "
                + dateOfBirth.toString() + "\nAddress: " + homeAddress;
        return temp;
    }

    // TODO how will this method work, can something like editCamper work?
    // this method will likely be deleated in favor of just calling the setters
    public void editUserProfile() {

    }

    /**
     * This method will display a camper's personal information so the user can view
     * it
     * 
     * @param firstName The first name of the camper the user wants to view
     * @return A string description of the camper's information
     */
    public String viewCamperProfile(String firstName) {
        for (int i = 0; i < campers.size(); i++) {
            if (campers.get(i).getFirstName().equals(firstName)) {
                return campers.get(i).toString();
            }
        }
        return "There are no campers with that name";
    }

    // This method may be deleated in favor of just calling the necessary setters to
    // change the information
    /**
     * This method will update one of the camper's information that the user has
     * registered
     * It works by having a camper with the updated information passed to the method
     * 
     * @param newCamper The name of the camper object with the updated information
     *                  to replace the old camper
     */
    public void editCamperProfile(Camper newCamper) {
        if (newCamper != null) {
            for (int i = 0; i < campers.size(); i++) {
                if (newCamper.getFirstName().equals(campers.get(i).getFirstName()))
                    ;
                {
                    // this will change the order of campers in the list, I don't think that
                    // matters?
                    campers.remove(i);
                    campers.add(newCamper);
                    i = campers.size();
                }
            }
        }
        // this method is great and all but does it need to add the camper or something
        // to a camperList?
    }

    // TODO consider deleating
    public String toString() {
        return firstName + ", " + lastName + ", amt of campers:" + campers.size();
    }
}
