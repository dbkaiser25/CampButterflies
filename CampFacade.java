import java.util.ArrayList;
import java.util.Date;

public class CampFacade 
{
    private CampList campList;
    private CamperList camperList;
    private UserList userList;
    private CounselorList counselorList;
    private DirectorList directorList;
    private Person currentUser;

    public CampFacade()
    {
        campList = CampList.getInstance();
        camperList = CamperList.getInstance();
        userList = UserList.getInstance();
        counselorList = CounselorList.getInstance();
        directorList = DirectorList.getInstance();
    }

    public Person getCurrentUser(){
        return currentUser;
    }

    /**
     * returns all camps
     * @return all camps
     */
    public CampList getCampList(){
        return campList;
    }

    /**
     * Checks to see if login information exists
     * @param userLogin 
     * @return true if it exists, false if it doesnt
     */
    public int Login(LoginInfo userLogin)
    {
        if(directorList.haveDirector(userLogin)){
            currentUser = directorList.getDirectorByUserName(userLogin.getUserName());
            return 3;
        }
        else if(userList.haveUser(userLogin)){
            currentUser = userList.getUserByUserName(userLogin.getUserName());
            return 1;
        }
        else if(counselorList.haveCounselor(userLogin)){
            currentUser = counselorList.getCounselorByUserName(userLogin.getUserName());
            return 2;
        }
        return 0;
    }

    /**
     * Adds a user
     * @param firstName
     * @param lastName
     * @param doB
     * @param homeAddress
     * @param loginInfo
     * @param campers
     */
    public void addUser(String firstName, String lastName, Date doB, String homeAddress, LoginInfo loginInfo, ArrayList<Camper> campers){

        User user = new User(firstName, lastName, doB, homeAddress, loginInfo, campers);
        userList.addUser(user);
    }

    /**
     * Adds a camper
     * @param firstName
     * @param lastName
     * @param homeAddress
     * @param doB
     * @param sex
     * @param medications
     * @param allergies
     * @param emergencyContacts
     * @param pediatrician
     * @return
     */
    public Camper addCamper(String firstName, String lastName, String homeAddress, Date doB, Sex sex, ArrayList<Medication> medications,ArrayList<String> allergies, 
                            ArrayList<Contact> emergencyContacts, Contact pediatrician){
        Camper camper = new Camper(firstName, lastName, homeAddress, doB, sex, medications, allergies, emergencyContacts, pediatrician);
        camperList.addCamper(camper);
        return camper;
    }

    /**
     * Adds a counselor
     * @param firstName
     * @param lastName
     * @param phoneNumber
     * @param emailAddress
     * @param homeAddress
     * @param doB
     * @param emergencyContacts
     * @param pediatrician
     * @param loginInfo
     */
    public void addCounselor(String firstName, String lastName, String phoneNumber, String emailAddress, String homeAddress, Date doB, ArrayList<Contact> emergencyContacts, Contact pediatrician, LoginInfo loginInfo)
    {
        Counselor counselor = new Counselor(firstName, lastName, phoneNumber, emailAddress, homeAddress, doB, emergencyContacts, pediatrician, loginInfo);
        counselorList.addCounselor(counselor);
    }

    public void editCamperProfile()
    {

    }

    public void editUserProfile()
    {

    }

    public boolean qualifiesForDiscount()
    {
        return true;
    }

    public void selectWeek()
    {

    }

    public void viewUserProfile()
    {

    }

    public void viewCamperProfile()
    {

    }

    public void editCalendar()
    {

    }

    public String viewCalendar()
    {
        return null;
    }

    /**
     * returns all the activities the camp offers
     * @param campName
     * @return
     */
    public String getActivities(String campName)
    {
        Camp camp = campList.getCamp(campName);
        return camp.getActivities();
    }
   
    /**
     * returns all the weeks the camp offers
     * @param campName
     * @return
     */
    public ArrayList<Week> getWeeks(String campName)
    {
        Camp camp = campList.getCamp(campName);
        ArrayList<Week> weeks = camp.getWeeks();
        return weeks;
    }


    public User getCurrentUser(LoginInfo userLogin)
    {
        return null;
    }   

}

