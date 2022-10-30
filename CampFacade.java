import java.util.ArrayList;
import java.util.Date;

public class CampFacade 
{
    private CampList campList;
    private CamperList camperList;
    private UserList userList;
    private CounselorList counselorList;
    private DirectorList directorList;
    private User currentUser;
    private Counselor currentCounselor;
    private Director currentDirector;

    /**
     * Creates the Camp facade with all of the instances of the lists
     */
    public CampFacade()
    {
        campList = CampList.getInstance();
        camperList = CamperList.getInstance();
        userList = UserList.getInstance();
        counselorList = CounselorList.getInstance();
        directorList = DirectorList.getInstance();
    }

    /**
     * Returns the current user that is logged in
     * @return
     */
    public User getCurrentUser(){
        return currentUser;
    }

    /**
     * Returns the current counselor that is logged in
     * @return
     */
    public Counselor getCurrentCounselor(){
        return currentCounselor;
    }

    /**
     * Returns the current director that is logged in
     * @return
     */
    public Director getCurrentDirector(){
        return currentDirector;
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
            currentDirector = directorList.getDirectorByUserName(userLogin.getUserName());
            return 3;
        }
        else if(userList.haveUser(userLogin)){
            currentUser = userList.getUserByUserName(userLogin.getUserName());
            return 1;
        }
        else if(counselorList.haveCounselor(userLogin)){
            currentCounselor = counselorList.getCounselorByUserName(userLogin.getUserName());
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

    /**
     * changes campers first name
     * @param camper
     * @param firstName
     */
    public void editCamperFirstName(String camper, String firstName)
    {
        Camper currentCamper = currentUser.getCamper(camper);
        if(currentCamper==null)
            System.out.println("There is no camper with that name");
        else
            currentCamper.setFirstName(firstName);
    }

    /**
     * changes campers last name
     * @param camper
     * @param lastName
     */
    public void editCamperLastName(String camper, String lastName)
    {
        Camper currentCamper = currentUser.getCamper(camper);
        if(currentCamper==null)
            System.out.println("There is no camper with that name");
        else
            currentCamper.setLastName(lastName);
    }

    /**
     * changes campers home address
     * @param camper
     * @param homeAddress
     */
    public void editCamperHomeAddress(String camper, String homeAddress)
    {
        Camper currentCamper = currentUser.getCamper(camper);
        if(currentCamper==null)
            System.out.println("There is no camper with that name");
        else
            currentCamper.setHomeAddress(homeAddress);
    }

    /**
     * changes campers date of birth
     * @param camper
     * @param dateOfBirth
     */
    public void editCamperDateOfBirth(String camper, Date dateOfBirth)
    {
        Camper currentCamper = currentUser.getCamper(camper);
        if(currentCamper==null)
            System.out.println("There is no camper with that name");
        else
            currentCamper.setDateOfBirth(dateOfBirth);
    }

    /**
     * changes campers sex
     * @param camper
     * @param sex
     */
    public void editCamperSex(String camper, Sex sex)
    {
        Camper currentCamper = currentUser.getCamper(camper);
        if(currentCamper==null)
            System.out.println("There is no camper with that name");
        else
            currentCamper.setSex(sex);
    }

    /**
     * edits campers allergy list
     * @param camper
     * @param allergies
     */
    public void editCamperAllergies(String camper, ArrayList<String> allergies)
    {
        Camper currentCamper = currentUser.getCamper(camper);
        if(currentCamper==null)
            System.out.println("There is no camper with that name");
        else
            currentCamper.setAllergies(allergies);
    }

    /**
     * edits campers emergency contacts list
     * @param camper
     * @param contacts
     */
    public void editCamperEmergencyContacts(String camper, ArrayList<Contact> contacts)
    {
        Camper currentCamper = currentUser.getCamper(camper);
        if(currentCamper==null)
            System.out.println("There is no camper with that name");
        else
            currentCamper.setEmergencyContacts(contacts);
    }

    /**
     * edits campers doctor
     * @param camper
     * @param doctor
     */
    public void editCamperPediatrician(String camper, Contact doctor)
    {
        Camper currentCamper = currentUser.getCamper(camper);
        if(currentCamper==null)
            System.out.println("There is no camper with that name");
        else
            currentCamper.setPediatrician(doctor);
    }

    /**
     * changes users first name
     * @param firstName
     */
    public void editUserFirstName(String firstName)
    {
        currentUser.setFirstName(firstName);
    }

    /**
     * changes users last name
     * @param lastName
     */
    public void editUserLastName(String lastName)
    {
        currentUser.setLastName(lastName);
    }

    /**
     * chnages users home address
     * @param homeAddress
     */
    public void editUserHomeAddress(String homeAddress)
    {
        currentUser.setHomeAddress(homeAddress);
    }

    /**
     * changes users date of birth
     * @param dateOfBirth
     */
    public void editUserDateOfBirth(Date dateOfBirth)
    {

        currentUser.setDateOfBirth(dateOfBirth);
    }

    /**
     * changes counselors first name
     * @param firstName
     */
    public void editCounselorFirstName(String firstName)
    {
        currentCounselor.setFirstName(firstName);
    }

    /**
     * changes counselors last name
     * @param lastName
     */
    public void editCounselorLastName(String lastName)
    {
        currentCounselor.setLastName(lastName);
    }

    /**
     * changes chounselors home address
     * @param homeAddress
     */
    public void editCounselorHomeAddress(String homeAddress)
    {
        currentCounselor.setHomeAddress(homeAddress);
    }

    /**
     * changes counselors phone number
     * @param phoneNumber
     */
    public void editCounselorPhoneNumber(String phoneNumber)
    {
        currentCounselor.setPhoneNumber(phoneNumber);
    }

    /**
     * changes counselors date of birth
     * @param dob
     */
    public void editCounselorDateOfBirth(Date dob)
    {
        currentCounselor.setDateOfBirth(dob);
    }

    /**
     * edits counselors emergency contacts list
     * @param contacts
     */
    public void editCounselorEmergencyContacts(ArrayList<Contact> contacts)
    {
        currentCounselor.setEmergencyContacts(contacts);
    }

    /**
     * edits counselors doctor
     * @param doctor
     */
    public void editCounselorDoctor(Contact doctor)
    {
        currentCounselor.setPediatrician(doctor);
    }

    /**
     * returns true if they qualify for a discount
     * @return
     */
    public boolean qualifiesForDiscount()
    {
        return currentUser.qualifiesForDiscount();
    }

    /**
     * returns user profile
     * @return
     */
    public String viewUserProfile()
    {
        return currentUser.viewUserProfile();
    }

    /**
     * returns all of the campers a user has
     * @return
     */
    public String viewCampers()
    {
        String campers = "";
        for(Camper camper: currentUser.getCampers()){
            campers += camper.toStringBrief() + "\n" + camper.getWeeks() +"\n";
        }
        return campers;
    }

    /**
     * returns a specific camper profile
     * @param firstName
     * @return
     */
    public String viewCamperProfile(String firstName)
    {
        return currentUser.viewCamperProfile(firstName);
    }

    /**
     * returns counselors profile
     * @return
     */
    public String viewCounselorProfile(){
        return currentCounselor.toString();
    }

    public void editCalendar()
    {

    }

    /**
     * returns the calendar
     * @return
     */
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

}

