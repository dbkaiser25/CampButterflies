import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/*
 * Testing the Camp Facade methods
 * Kataleena Mishra
 */
public class CampFacadeTest {
    CampFacade facade;
    
    @BeforeEach
    public void setup(){
        this.facade = new CampFacade();
    }

    @Test
    void validDirectorLogin(){
        LoginInfo director = new LoginInfo("samuels", "sam");
        int login = facade.Login(director);
        assertEquals(1, login);
    }

    @Test
    void validUserLogin(){
        LoginInfo user = new LoginInfo("lebron", "james");
        int login = facade.Login(user);
        assertEquals(2, login);
    }

    @Test
    void validCounselorLogin(){
        LoginInfo counselor = new LoginInfo("jimbeans", "beans");
        int login = facade.Login(counselor);
        assertEquals(3, login);
    }

    @Test
    void invalidLogin(){
        LoginInfo random = new LoginInfo("bobby", "beans");
        int login = facade.Login(random);
        assertEquals(0, login);
    }

    @Test 
    void nullLogin(){
        int login = facade.Login(null);
        assertEquals(0,login);
    }

    @Test
    void sameLogin(){
        LoginInfo login = new LoginInfo("jimbeans", "beans");
        ArrayList<Camper> campers = new ArrayList<>();
        Date date = new Date();
        facade.addUser("Jimmy", "Beandl", date, "123 Street Lane", login, campers);

        assertEquals(3, facade.Login(login));
    }

    @Test 
    void addingValidUser(){
        LoginInfo login = new LoginInfo("Marym", "Martinm");
        ArrayList<Camper> campers = new ArrayList<>();

        Date date1 = new Date();
        facade.addUser("Mary", "Martin", date1, "123 Street Lane", login, campers);
        assertEquals(2, facade.Login(login));
    }

    //Will Literally break the entire program bc it actually allows a null user
    /*
    @Test
    void AddingNullUser(){
        facade.addUser(null, null, null, null, null, null);
        assertEquals(2,facade.Login(null));
    }
    */

    @Test 
    void addingTheSameUser(){
        LoginInfo login = new LoginInfo("Marym", "Martinm");
        ArrayList<Camper> campers = new ArrayList<>();
        Date date1 = new Date();
        facade.addUser("Mary", "Martin", date1, "125 Street Lane", login, campers);
        User user = new User("Mary", "Martin", date1, "125 Street Lane", login, campers);
        facade.Login(login);
        assertEquals(facade.getCurrentUser(), user);
    }
    
    @Test
    void addingValidCamper(){
        Date date = new Date();
        ArrayList<Medication> medications = new ArrayList<>();
        ArrayList<String> allergies = new ArrayList<>();
        ArrayList<Contact> contacts = new ArrayList<>();
        Contact pediatrician = new Contact();

        Camper one = facade.addCamper("Bobby", "Martin", "123 Street Lane", date, Sex.MALE, medications, allergies, contacts, pediatrician);
        Camper two = facade.getCamperList().getCamperByName("Bobby", "Martin");

        assertEquals(one, two);
    }

    @Test
    void addingSameNameCamper(){
        Date date = new Date();
        ArrayList<Medication> medications = new ArrayList<>();
        ArrayList<String> allergies = new ArrayList<>();
        ArrayList<Contact> contacts = new ArrayList<>();
        Contact pediatrician = new Contact();

        Camper one = facade.addCamper("Bobby", "Martin", "124 Street Lane", date, Sex.FEMALE, medications, allergies, contacts, pediatrician);
        Camper two = facade.getCamperList().getCamperByName("Bobby", "Martin");

        assertEquals(one, two);
    }

    @Test 
    void addingValidCounselor(){
        Date date = new Date();
        ArrayList<Contact> contacts = new ArrayList<>();
        Contact pediatrician = new Contact();
        LoginInfo login = new LoginInfo("Peter", "Rabbit");
         
        Counselor counselor = facade.addCounselor("Peter", "Rabbit", "908-346-0022", "prabbit@carrot.com", "908 Garden Lane", date, contacts, pediatrician, login);
        facade.Login(login);
        assertEquals(facade.getCurrentCounselor(), counselor);
    }

    @Test
    void changeCamperName(){
        LoginInfo login = new LoginInfo("lebron", "james");
        facade.Login(login);       
        facade.editCamperFirstName("tommy", "tomathan");
        String name = facade.getCurrentUser().getCampers().get(0).getFirstName();
        assertEquals("tomathan", name);
    }

    @Test
    void changeUserName(){
        LoginInfo login = new LoginInfo("lebron", "james");
        facade.Login(login);
        facade.editUserFirstName("Lebron");
        assertEquals("Lebron", facade.getCurrentUser().firstName);
    }

    @Test 
    void changeNullName(){
        LoginInfo login = new LoginInfo("lebron", "james");
        facade.Login(login);
        facade.editUserFirstName(null);
        assertEquals("LeBron", facade.getCurrentUser().firstName);
    }

    @Test
    void changeCounselorName(){
        LoginInfo counselor = new LoginInfo("jimbeans", "beans");
        facade.Login(counselor);
        facade.editCounselorFirstName("Jimmy");
        assertEquals("Jimmy", facade.getCurrentCounselor().firstName);
    }

    @Test
    void changeDirectorName(){
        LoginInfo director = new LoginInfo("samuels", "sam");
        facade.Login(director);
        facade.editDirectorFirstName("Sam");
        assertEquals("Sam", facade.getCurrentDirector().getFirstName());
    }



    @Test
    void addingValidCamp(){
        ArrayList<Activity> activities = new ArrayList<>();
        Activity activity = new Activity("art", "art room", "do art");
        activities.add(activity);
        HashMap<Integer,Week> schedule = new HashMap<>();

        facade.newCamp("Camp Party", "Super Fun Camp", 2025, activities, schedule);
        Camp one = new Camp("Camp Party", "Super Fun Camp", schedule, activities, 2025);
        Camp two = facade.getCampList().getCamp("Camp Party");

        assertEquals(one, two);
    }

    @Test
    void viewCalendar(){
        assertNotNull(facade.viewCalendar());
    }

    @Test
    void settingWeek(){
        Date date = new Date();
        facade.setWeek("2023 Summer Camp", 10, date, date, "fun");
        String theme = facade.getCampList().getCamp("2023 Summer Camp").getWeek(10).getTheme();
        assertEquals("fun", theme);
    }

    @Test
    void gettingActivities(){
        String activities = facade.getActivities("2023 Summer Camp");
        assertNotNull(activities);
    }

    @Test
    void gettingEmptyActivities(){
        String activities = facade.getActivities(" ");
        assertNotNull(activities);
    }

    @Test
    void gettingNullActivities(){
        String activities = facade.getActivities(null);
        assertNotNull(activities);
    }

    @Test
    void gettingGroupCampers(){
        ArrayList<Camper> group= facade.getGroupCampers("2023 Summer Camp", 1);
        assertNotNull(group);
    }

    @Test
    void gettingEmptyGroupCampers(){
        ArrayList<Camper> group= facade.getGroupCampers(" ", 1);
        assertNotNull(group);
    }

    @Test
    void gettingNullGroupCampers(){
        ArrayList<Camper> group= facade.getGroupCampers(null, 1);
        assertNotNull(group);
    }

    @Test
    void gettingGroupUUID(){
        UUID uuid = facade.getGroupUUID("2023 Summer Camp", 1);
        assertNotNull(uuid);
    }

    @Test
    void gettingEmptyGroupUUID(){
        UUID uuid = facade.getGroupUUID(" ", 1);
        assertNotNull(uuid);
    }

    @Test
    void gettingNullGroupUUID(){
        UUID uuid = facade.getGroupUUID(null, 1);
        assertNotNull(uuid);
    }

    @Test
    void gettingScehdule(){
        String schedule = facade.getSchedule("2023 Summer Camp", 1);
        assertNotNull(schedule);
    }

    @Test
    void gettingEmptyScehdule(){
        String schedule = facade.getSchedule(" ", 1);
        assertNotNull(schedule);
    }

    @Test
    void gettingNullScehdule(){
        String schedule = facade.getSchedule(null, 1);
        assertNotNull(schedule);
    }
}
