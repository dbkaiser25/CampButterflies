
import java.util.UUID;
import java.util.ArrayList;
import java.util.Scanner;

public class CampFacade 
{
    private GroupList groupList;
    private CamperList camperList;
    private UserList userList;
    private CounselorList counselorList;
    private DirectorList directorList;
    private Scanner scan = new Scanner(System.in);

    public CampFacade(GroupList groupList, CamperList camperList, UserList userList, 
    CounselorList counselorList, DirectorList directorList)
    {
        this.groupList = groupList;
        this.camperList = camperList;
        this.userList = userList;
        this.counselorList = counselorList;
        this.directorList = directorList;
    }

    public void Login(LoginInfo userLogin)
    {

    }

    public void addCamper()
    {
        String username = get("Username");
        String password = get("Password");
        String firstName = get("First Name");
        String lastName = get("Last Name");
        String homeAddress = get("Home Address");
        String dateOfBirth = get("Date of Birth(MM/DD/YYYY)");
        Sex sex = Enum.valueOf(Sex.class,get("Sex(MALE/FEMALE)"));
        String med = get("Would you like to add any medications(yes/no)");

        ArrayList<Medication> medications = new ArrayList<>();
        if(med.equalsIgnoreCase("yes")){
            boolean more = true;
            while(more){
                String type = get("Medication Name");
                String dose = get("Dose Amount");
                String time = get("Time Taken");
                Medication newMeds = new Medication(type, dose, time);
                medications.add(newMeds);
                String answer = get("Would you like to add more(yes/no)");
                if(answer.equalsIgnoreCase("no"))
                    more = false;
            }
        }

        ArrayList<String> allergies = new ArrayList<>();
        String allergy = get("Any Allergies");
    }

    private String get(String prompt){
        System.out.print(prompt + ": ");
        return scan.nextLine();
    }

    public void editCamperProfile()
    {

    }

    public void editUserProfile()
    {

    }

    public void addCounselor()
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

    public String getActivities()
    {
        return null;
    }
    //this needs an instance but of what?????
    //TODO
    public void getInstance()
    {

    }


    public User getCurrentUser(LoginInfo userLogin)
    {
        return null;
    }   

}

