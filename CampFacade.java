import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CampFacade {
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
    public CampFacade() {
        campList = CampList.getInstance();
        camperList = CamperList.getInstance();
        userList = UserList.getInstance();
        counselorList = CounselorList.getInstance();
        directorList = DirectorList.getInstance();
    }

    /**
     * Returns the current user that is logged in
     * 
     * @return
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Returns the current counselor that is logged in
     * 
     * @return
     */
    public Counselor getCurrentCounselor() {
        return currentCounselor;
    }

    /**
     * Returns the current director that is logged in
     * 
     * @return
     */
    public Director getCurrentDirector() {
        return currentDirector;
    }

    /**
     * returns all camps
     * 
     * @return all camps
     */
    public CampList getCampList() {
        return campList;
    }

    public String getCamps() {
        String camps = "";
        for (Camp camp : campList.getCamps()) {
            camps += camp.getName() + "\n";
        }
        return camps;
    }

    public String getCampers() {
        String campers = "";
        for (Camper camper : camperList.getCampers()) {
            campers += camper.getFirstName() + " " + camper.getLastName() + "\n";
        }
        return campers;
    }

    /**
     * Checks to see if login information exists
     * 
     * @param userLogin
     * @return
     */
    public int Login(LoginInfo userLogin) {
        if (directorList.haveDirector(userLogin)) {
            currentDirector = directorList.getDirectorByUserName(userLogin.getUserName());
            return 1;
        }
        if (userList.haveUser(userLogin)) {
            currentUser = userList.getUserByUserName(userLogin.getUserName());
            // num = 2;
            return 2;
        }
        if (counselorList.haveCounselor(userLogin)) {
            currentCounselor = counselorList.getCounselorByUserName(userLogin.getUserName());
            return 3;
        }
        return 0;
    }

    /**
     * Adds a user
     * 
     * @param firstName
     * @param lastName
     * @param doB
     * @param homeAddress
     * @param loginInfo
     * @param campers
     */
    public void addUser(String firstName, String lastName, Date doB, String homeAddress, LoginInfo loginInfo,
            ArrayList<Camper> campers) {

        User user = new User(firstName, lastName, doB, homeAddress, loginInfo, campers);
        userList.addUser(user);
    }

    /**
     * Adds a camper
     * 
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
    public Camper addCamper(String firstName, String lastName, String homeAddress, Date doB, Sex sex,
            ArrayList<Medication> medications, ArrayList<String> allergies,
            ArrayList<Contact> emergencyContacts, Contact pediatrician) {
        Camper camper = new Camper(firstName, lastName, homeAddress, doB, sex, medications, allergies,
                emergencyContacts, pediatrician);
        camperList.addCamper(camper);
        return camper;
    }

    /**
     * Adds a counselor
     * 
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
    public Counselor addCounselor(String firstName, String lastName, String phoneNumber, String emailAddress,
            String homeAddress, Date doB, ArrayList<Contact> emergencyContacts, Contact pediatrician,
            LoginInfo loginInfo) {
        Counselor counselor = new Counselor(firstName, lastName, phoneNumber, emailAddress, homeAddress, doB,
                emergencyContacts, pediatrician, loginInfo);
        counselorList.addCounselor(counselor);
        // call that one thing
        return counselor;
    }

    /**
     * changes campers first name
     * 
     * @param camper
     * @param firstName
     */
    public void editCamperFirstName(String camper, String firstName) {
        Camper currentCamper = currentUser.getCamper(camper);
        if (currentCamper == null)
            System.out.println("There is no camper with that name");
        else
            currentCamper.setFirstName(firstName);
        camperList.saveCampers();
    }

    /**
     * changes campers last name
     * 
     * @param camper
     * @param lastName
     */
    public void editCamperLastName(String camper, String lastName) {
        Camper currentCamper = currentUser.getCamper(camper);
        if (currentCamper == null)
            System.out.println("There is no camper with that name");
        else
            currentCamper.setLastName(lastName);
        camperList.saveCampers();
    }

    /**
     * changes campers home address
     * 
     * @param camper
     * @param homeAddress
     */
    public void editCamperHomeAddress(String camper, String homeAddress) {
        Camper currentCamper = currentUser.getCamper(camper);
        if (currentCamper == null)
            System.out.println("There is no camper with that name");
        else
            currentCamper.setHomeAddress(homeAddress);
        camperList.saveCampers();
    }

    /**
     * changes campers date of birth
     * 
     * @param camper
     * @param dateOfBirth
     */
    public void editCamperDateOfBirth(String camper, Date dateOfBirth) {
        Camper currentCamper = currentUser.getCamper(camper);
        if (currentCamper == null)
            System.out.println("There is no camper with that name");
        else
            currentCamper.setDateOfBirth(dateOfBirth);
        camperList.saveCampers();
    }

    /**
     * changes campers sex
     * 
     * @param camper
     * @param sex
     */
    public void editCamperSex(String camper, Sex sex) {
        Camper currentCamper = currentUser.getCamper(camper);
        if (currentCamper == null)
            System.out.println("There is no camper with that name");
        else
            currentCamper.setSex(sex);
        camperList.saveCampers();
    }

    /**
     * edits campers allergy list
     * 
     * @param camper
     * @param allergies
     */
    public void editCamperAllergies(String camper, ArrayList<String> allergies) {
        Camper currentCamper = currentUser.getCamper(camper);
        if (currentCamper == null)
            System.out.println("There is no camper with that name");
        else
            currentCamper.setAllergies(allergies);
        camperList.saveCampers();
    }

    /**
     * edits campers emergency contacts list
     * 
     * @param camper
     * @param contacts
     */
    public void editCamperEmergencyContacts(String camper, ArrayList<Contact> contacts) {
        Camper currentCamper = currentUser.getCamper(camper);
        if (currentCamper == null)
            System.out.println("There is no camper with that name");
        else
            currentCamper.setEmergencyContacts(contacts);
        camperList.saveCampers();
    }

    /**
     * edits campers doctor
     * 
     * @param camper
     * @param doctor
     */
    public void editCamperPediatrician(String camper, Contact doctor) {
        Camper currentCamper = currentUser.getCamper(camper);
        if (currentCamper == null)
            System.out.println("There is no camper with that name");
        else
            currentCamper.setPediatrician(doctor);
        camperList.saveCampers();
    }

    /**
     * changes users first name
     * 
     * @param firstName
     */
    public void editUserFirstName(String firstName) {
        currentUser.setFirstName(firstName);
        userList.saveUsers();
    }

    /**
     * changes users last name
     * 
     * @param lastName
     */
    public void editUserLastName(String lastName) {
        currentUser.setLastName(lastName);
        userList.saveUsers();
    }

    /**
     * chnages users home address
     * 
     * @param homeAddress
     */
    public void editUserHomeAddress(String homeAddress) {
        currentUser.setHomeAddress(homeAddress);
        userList.saveUsers();
    }

    /**
     * changes users date of birth
     * 
     * @param dateOfBirth
     */
    public void editUserDateOfBirth(Date dateOfBirth) {

        currentUser.setDateOfBirth(dateOfBirth);
        userList.saveUsers();
    }

    /**
     * changes counselors first name
     * 
     * @param firstName
     */
    public void editCounselorFirstName(String firstName) {
        currentCounselor.setFirstName(firstName);
        counselorList.saveCounselor();
    }

    /**
     * changes counselors last name
     * 
     * @param lastName
     */
    public void editCounselorLastName(String lastName) {
        currentCounselor.setLastName(lastName);
        counselorList.saveCounselor();
    }

    /**
     * changes chounselors home address
     * 
     * @param homeAddress
     */
    public void editCounselorHomeAddress(String homeAddress) {
        currentCounselor.setHomeAddress(homeAddress);
        counselorList.saveCounselor();
    }

    /**
     * changes counselors phone number
     * 
     * @param phoneNumber
     */
    public void editCounselorPhoneNumber(String phoneNumber) {
        currentCounselor.setPhoneNumber(phoneNumber);
        counselorList.saveCounselor();
    }

    /**
     * changes counselors date of birth
     * 
     * @param dob
     */
    public void editCounselorDateOfBirth(Date dob) {
        currentCounselor.setDateOfBirth(dob);
        counselorList.saveCounselor();
    }

    /**
     * edits counselors emergency contacts list
     * 
     * @param contacts
     */
    public void editCounselorEmergencyContacts(ArrayList<Contact> contacts) {
        currentCounselor.setEmergencyContacts(contacts);
        counselorList.saveCounselor();
    }

    /**
     * edits counselors doctor
     * 
     * @param doctor
     */
    public void editCounselorDoctor(Contact doctor) {
        currentCounselor.setPediatrician(doctor);
        counselorList.saveCounselor();
    }

    /**
     * returns true if they qualify for a discount
     * 
     * @return
     */
    public boolean qualifiesForDiscount() {
        return currentUser.qualifiesForDiscount(campList, 1);
    }

    /**
     * returns user profile
     * 
     * @return
     */
    public String viewUserProfile() {
        return currentUser.viewUserProfile();
    }

    /**
     * returns all of the campers a user has
     * 
     * @return
     */
    public String viewCampers() {
        String campers = "";
        for (Camper camper : currentUser.getCampers()) {
            campers += camper.toStringBrief() + "\n" + camper.getWeeks() + "\n";
        }
        return campers;
    }

    /**
     * returns a specific camper profile
     * 
     * @param firstName
     * @return
     */
    public String viewCamperProfile(String firstName) {
        return currentUser.viewCamperProfile(firstName);
    }

    /**
     * returns counselors profile
     * 
     * @return
     */
    public String viewCounselorProfile() {
        return currentCounselor.toString();
    }

    /**
     * returns the directors profile
     * 
     * @return
     */
    public String viewDirectorProfile() {
        return currentDirector.toString();
    }

    /**
     * changes the directors first name
     * 
     * @param firstName
     */
    public void editDirectorFirstName(String firstName) {
        currentDirector.setFirstName(firstName);
        directorList.saveDirector();
    }

    /**
     * changes the directors last name
     * 
     * @param lastName
     */
    public void editDirectorLastName(String lastName) {
        currentDirector.setLastName(lastName);
        directorList.saveDirector();
    }

    /**
     * changes the directors home address
     * 
     * @param homeAddress
     */
    public void editDirectorHomeAddress(String homeAddress) {
        currentDirector.setHomeAddress(homeAddress);
        directorList.saveDirector();
    }

    /**
     * changes tha directors date of birth
     * 
     * @param dob
     */
    public void editDirectorDateOfBirth(Date dob) {
        currentDirector.setDateOfBirth(dob);
        directorList.saveDirector();
    }

    /**
     * Adds a new Camp to campList
     * 
     * @param name
     * @param desc
     * @param weeks
     * @param year
     */
    public void newCamp(String name, String desc, int year, ArrayList<Activity> activites, HashMap<Integer, Week> masterSchedule) {
        Camp camp = new Camp(name, desc, masterSchedule, activites, year);
        campList.addCamp(camp);
        campList.saveCamps();
    }

    /**
     * Initializes each week of a camp
     * 
     * @param camp
     * @param week
     * @param startDate
     * @param endDate
     * @param theme
     */
    // setWeek doesn't work. theme and dates are null
    public void setWeek(String name, int week, Date startDate, Date endDate, String theme) {
        campList.getCamp(name).getWeek(week).setStartDate(startDate);
        campList.getCamp(name).getWeek(week).setEndDate(endDate);
        campList.getCamp(name).getWeek(week).setTheme(theme);
        System.out.println("date " + campList.getCamp(name).getWeek(week).getStartDate());
        System.out.println("theme " + campList.getCamp(name).getWeek(week).getTheme());
        campList.saveCamps();
    }

    /**
     * returns the calendar
     * 
     * @return
     */
    public String viewCalendar() {
        return null;
    }

    /**
     * returns all the activities the camp offers
     * 
     * @param campName
     * @return
     */
    public String getActivities(String campName) {
        for (Camp camp : campList.getCamps()) {
            if (camp.getName().equals(campName))
                return camp.getActivities();
        }
        return "This camp does not exist";
    }

    /**
     * sets activities for a camp
     * 
     * @param camp
     * @param activities
     */
    public void setActivities(String camp, ArrayList<Activity> activities) {
        campList.getCamp(camp).setActivities(activities);
        campList.saveCamps();
    }

    /**
     * returns all the weeks the camp offers
     * 
     * @param campName
     * @return
     */
    public ArrayList<Week> getWeeks(String campName) {
        for (Camp camp : campList.getCamps()) {
            if (camp.getName().equals(campName))
                return camp.getWeeks();
        }
        return null;
    }

    /**
     * returns a counselors campers
     * 
     * @param camp
     * @param week
     * @return
     */
    public ArrayList<Camper> getGroup(String camp, int week) {
        return campList.getCamp(camp).getWeek(week - 1).getGroupByUUID(currentCounselor.getUUID()).getCamperList();
    }

    /**
     * returns a counselors schedule
     * 
     * @param camp
     * @param week
     * @return
     */
    public String getSchedule(String camp, int week) {
        return campList.getCamp(camp).getWeek(week - 1).getGroupByUUID(currentCounselor.getUUID()).printSchedule();
    }

    public String getSchedule(String camp, int week, int group) { // error here
        return campList.getCamp(camp).getWeek(week - 1).getGroupByNumber(group).printSchedule();
    }

    /**
     * returns the camperlist
     * 
     * @return
     */
    public CamperList getCamperList() {
        return camperList;
    }

    /**
     * returns the counselorlist
     * 
     * @return
     */
    public CounselorList getCounselorList() {
        return counselorList;
    }

    public String getCounselors() {
        String counselors = new String();
        counselors = "\nCounselors: \n";
        for (Counselor c : counselorList.getCounselors()) {
            counselors = counselors + c.getFirstName() + " " + c.getLastName() + "\n";
        }
        return counselors;
    }

    /*
     * public String getCampers(){
     * String campers = "";
     * for(Camper camper: camperList.getCampers()){
     * campers += camper.getFirstName() +" " + camper.getLastName() + "\n";
     * }
     * return campers;
     * }
     */

    /**
     * removes a camper from a week
     * 
     * @param firstName
     * @param lastName
     * @param week
     */
    public void removeCamper(String firstName, String lastName, Camp camp) {
        currentDirector.removeCamper(firstName, lastName, camp);
        camperList.saveCampers();
    }

    public void removeCounselor(String firstName, String lastName, Camp camp) {
        currentDirector.removeCounselor(firstName, lastName, camp);
        counselorList.saveCounselor();
    }

}
