
import java.util.Date;
import java.util.ArrayList;
import java.util.UUID;

public class User extends Person {
    private LoginInfo userLogin;
    private ArrayList<Camper> campers = new ArrayList<Camper>();

    public User(String firstName, String lastName, Date dateOfBirth,
            String homeAddress, LoginInfo userLogin, ArrayList<Camper> campers) {
        super(firstName, lastName, dateOfBirth, homeAddress);
        this.userLogin = userLogin;
        this.campers = campers;
        uuid = UUID.randomUUID();
    }

    public User(UUID uuid, String firstName, String lastName, Date dateOfBirth,
            String homeAddress, LoginInfo userLogin, ArrayList<Camper> campers) {
        super(uuid, firstName, lastName, dateOfBirth, homeAddress);
        this.userLogin = userLogin;
        this.campers = campers;
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

    public void addCamper(Camper camper) {
        if (camper != null)
            campers.add(camper);
    }

    /**
     * Determines if the user qualifies for a discount
     * 
     * @param camps      The list of all the different years for the given camp.
     *                   Weirdly this list only exists in Director so call director
     *                   to get the list
     * @param campNumber The number for the camp of this year. Presumably previous
     *                   years will be checked to de
     * @return
     */
    public boolean qualifiesForDiscount(ArrayList<Camp> camps, int campNumber) {

        for (int i = campNumber - 1; i >= 0; i--) {
            if (camps.get(campNumber).qualifiesForDiscount(campers)) {
                return true;
            }
        }

        if (campers.size() >= 2) {
            int campersRegistered = 0;

            for (int i = 0; i < campers.size(); i++) {
                if (inCamp(camps.get(campNumber), campers.get(i).getFirstName(), campers.get(i).getLastName())) {
                    if (campersRegistered >= 2) {
                        return true;
                    }
                    campersRegistered++;
                }
            }
        }

        int weeksRegistered = 0;
        for (int i = 0; i < campers.size(); i++) {
            for (int j = 0; j < camps.get(campNumber).getMasterSchedule().size(); j++) {
                if (camps.get(campNumber).getMasterSchedule().get(j)
                        .containsCamper(campers.get(i).getFirstName(), campers.get(i).getLastName())) {
                    if (weeksRegistered >= 2) {
                        return true;
                    }
                    weeksRegistered++;
                }
            }
            weeksRegistered = 0;
        }
        return false;
    }

    private boolean inCamp(Camp camp, String firstName, String lastName) {
        for (int i = 0; i < camp.getMasterSchedule().size(); i++) {
            if (camp.getMasterSchedule().get(i).containsCamper(firstName, lastName)) {
                return true;
            }
        }
        return false;
    }

    public String viewUserProfile() {
        String temp = new String();
        temp = "User: " + firstName + " " + lastName + "\nUsername: " + userLogin.getUserName() + "\nDate of Birth: "
                + dateOfBirth.toString() + "\nAddress: " + homeAddress;
        return temp;
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
                return campers.get(i).toStringFull();
            }
        }
        return "There are no campers with that name";
    }

    public Camper getCamper(String firstname) {
        for (int i = 0; i < campers.size(); i++) {
            if (campers.get(i).getFirstName().equals(firstName)) {
                return campers.get(i);
            }
        }
        return null;
    }

    public String toString() {
        return firstName + ", " + lastName + ", amount of campers:" + campers.size();
    }

    public boolean qualifiesForDiscount(CampList campList, int campNumber) {
        return false;
    }
}
