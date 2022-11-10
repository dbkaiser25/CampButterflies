//Zak Elguindi 

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.json.simple.parser.ParseException;

import java.beans.Transient;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataLoaderTest {
    private CamperList camperlist = CamperList.getInstance();
    private ArrayList<Camper> campers = camperlist.getCampers();

    private CounselorList counselorlist = CounselorList.getInstance();
    private ArrayList<Counselor> counselors = counselorlist.getCounselors();

    private UserList userlist = UserList.getInstance();
    private ArrayList<User> users = userlist.getUsers();

    private DirectorList directorlist = DirectorList.getInstance();
    private ArrayList<Director> directors = directorlist.getDirectors();

    private CampList camplist = CampList.getInstance();
    private ArrayList<Camp> camps = camplist.getCamps();

    @BeforeEach
    public void setup() {

        // campers
        campers.clear();
        ArrayList<String> allergies = new ArrayList<String>();
        allergies.add("peanut");
        String date = "06/20/2013";
        ArrayList<Medication> medications = new ArrayList<Medication>();
        medications.add(new Medication("epipen", "one shot", "when reaction occurs"));
        Date dob = convertToDate(date);
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        contacts.add(new Contact("stacy", "jones", "844-204-5335", "stacyjones@gmail.com", "mother"));
        campers.add(new Camper("tommy", "jones", "77 this lane", dob, Sex.MALE, medications, allergies, contacts,
                new Contact("Dr. ", "smith", "202-433-5996", "drsmith@gmail.com", "doctor")));
        DataWriter.saveCampers();

        // counselors
        counselors.clear();
        contacts.remove(0);
        contacts.add(new Contact("martha", "beans", "333-402-5039",
                "marthabeans@gmail.com", "mother"));
        dob = convertToDate("1/6/1978");
        counselors.add(new Counselor("tim", "beans", "404-205-5406",
                "timbeans@gmail.com", "44 beans court", dob,
                contacts, new Contact("Dr.", "beans", "494-305-1024", "drbeans@gmail.com",
                        "doctor"),
                new LoginInfo("jimbeans", "beans")));
        DataWriter.saveCounselors();

        // users
        users.clear();
        dob = convertToDate("12/23/1986");
        users.add(new User("LeBron", "James", dob, "44 Lakers Drive", new LoginInfo("lebron", "james"), campers));
        DataWriter.saveUsers();

        /*
         * data for camps and directors were pulled straight from JSON, was having
         * issues with below lines
         */

        // // camps
        // camps.clear();
        // HashMap<Integer, Week> ms = new HashMap<Integer, Week>();
        // HashMap<DayOfWeek, ArrayList<Activity>> schedule = new HashMap<DayOfWeek,
        // ArrayList<Activity>>();
        // ArrayList<Activity> activities = new ArrayList<Activity>();
        // activities.add(new Activity("basektball", "basketball courts", "campers will
        // play basketball"));
        // schedule.put(DayOfWeek.FRIDAY, activities);
        // Group group = new Group(counselors.get(0), campers, schedule);
        // ArrayList<Group> groups = new ArrayList<Group>();
        // groups.add(group);
        // Week week = new Week("basketball", groups, counselors, campers,
        // convertToDate("06/01/2025"),
        // convertToDate("06/08/2025"), false);
        // ms.put(1, week);
        // camps.add(new Camp("Basketball Camp", "fun summer basketball camp", ms,
        // activities, 2025));
        // DataWriter.saveCamps();

        // directors
        // directors.clear();
        // dob = convertToDate("5/8/1980");
        // directors.add(new Director("billy", "director", dob, "44 this lane",
        // "directorbilly@gmail.com",
        // new LoginInfo("billy", "ilovecamp"), camps));
        // DataWriter.saveDirectors();
    }

    // camper first name
    @Test
    void testGetCampersFirstName() {
        campers = DataLoader.loadCampers();
        assertEquals("tommy", campers.get(0).getFirstName());
    }

    @Test
    void testGetCampersFirstNameEmpty() {
        campers = DataLoader.loadCampers();
        campers.get(0).setFirstName("");
        assertEquals("", campers.get(0).getFirstName());
    }

    @Test
    void testGetCampersFirstNameNull() {
        campers = DataLoader.loadCampers();
        campers.get(0).setFirstName(null);
        assertEquals(null, campers.get(0).getFirstName());
    }

    // size
    @Test
    void testGetCampersSize() {
        campers = DataLoader.loadCampers();
        assertEquals(1, campers.size());
    }

    // allergies
    @Test
    void testGetAllergiesNull() {
        campers = DataLoader.loadCampers();
        campers.get(0).setAllergies(null);
        assertEquals(null, campers.get(0).getAllergies());
    }

    @Test
    void testGetAllergiesEmpty() {
        campers = DataLoader.loadCampers();
        ArrayList<String> allergies = new ArrayList<String>();
        allergies.add("");
        campers.get(0).setAllergies(allergies);
        DataWriter.saveCampers();
        assertEquals("", campers.get(0).getAllergies());
    }

    // counselors
    // getpassword
    @Test
    void testCounselorPassword() {
        counselors = DataLoader.loadCounselors();
        assertEquals("beans", counselors.get(0).getUserLogin().getPassword());
    }

    @Test
    void testCounselorPasswordNull() {
        counselors = DataLoader.loadCounselors();
        counselors.get(0).getUserLogin().setPassword(null);
        assertEquals(null, counselors.get(0).getUserLogin().getPassword());
    }

    @Test
    void testCounselorPasswordEmpty() {
        counselors = DataLoader.loadCounselors();
        counselors.get(0).getUserLogin().setPassword("");
        assertEquals("", counselors.get(0).getUserLogin().getPassword());
    }

    // getPhoneNumber for counselor
    @Test
    void testCounselorPhone() {
        counselors = DataLoader.loadCounselors();
        assertEquals("404-205-5406", counselors.get(0).getPhoneNumber());
    }

    @Test
    void testCounselorPhoneWrong() {
        // should fail
        counselors = DataLoader.loadCounselors();
        counselors.get(0).setPhoneNumber("34-155-35696");
        assertNotEquals("34-155-35696", counselors.get(0).getPhoneNumber());
    }

    @Test
    void testCounselorPhoneEmpty() {
        counselors = DataLoader.loadCounselors();
        counselors.get(0).setPhoneNumber("");
        assertEquals("", counselors.get(0).getPhoneNumber());
    }

    @Test
    void getEmail() {
        counselors = DataLoader.loadCounselors();
        assertEquals("timbeans@gmail.com", counselors.get(0).getEmailAddress());
    }

    @Test
    void getEmailInvalid() {
        // should fail
        counselors = DataLoader.loadCounselors();
        counselors.get(0).setEmailAddress("timbeans@@@gmail.com");
        assertNotEquals("timbeans@@@gmail.com", counselors.get(0).getEmailAddress());
    }

    @Test
    void getEmailNull() {
        counselors = DataLoader.loadCounselors();
        counselors.get(0).setEmailAddress(null);
        assertEquals(null, counselors.get(0).getEmailAddress());
    }

    @Test
    void getEmailEmpty() {
        counselors = DataLoader.loadCounselors();
        counselors.get(0).setEmailAddress("");
        assertEquals("", counselors.get(0).getEmailAddress());
    }

    // users
    // get campers
    @Test
    void getCampers() {
        users = DataLoader.loadUsers();
        campers = DataLoader.loadCampers();
        assertEquals(campers.toString(), users.get(0).getCampers().toString());
    }

    @Test
    void getCampersNull() {
        users = DataLoader.loadUsers();
        for (int i = 0; i < users.get(0).getCampers().size(); i++) {
            users.get(0).getCampers().get(i).setFirstName(null);
        }
        assertEquals(null, users.get(0).getCampers().get(0).getFirstName());
    }

    // user address
    @Test
    void getUserAddress() {
        users = DataLoader.loadUsers();
        assertEquals("44 Lakers Drive", users.get(0).getHomeAddress());
    }

    @Test
    void getUserAddressNull() {
        users = DataLoader.loadUsers();
        users.get(0).setHomeAddress(null);
        assertEquals(null, users.get(0).getHomeAddress());
    }

    @Test
    void getUserAddressEmpty() {
        users = DataLoader.loadUsers();
        users.get(0).setHomeAddress("");
        assertEquals("", users.get(0).getHomeAddress());
    }

    @Test
    void getUserAddressInvalid() {
        // should fail
        users = DataLoader.loadUsers();
        users.get(0).setHomeAddress("invalid address");
        assertNotEquals("invalid address", users.get(0).getHomeAddress());
    }

    // user password
    @Test
    void getUserPassword() {
        users = DataLoader.loadUsers();
        assertEquals("james", users.get(0).getUserLogin().getPassword());
    }

    @Test
    void getUserPasswordNull() {
        users = DataLoader.loadUsers();
        users.get(0).getUserLogin().setPassword(null);
        assertEquals(null, users.get(0).getUserLogin().getPassword());
    }

    @Test
    void getUserPasswordEmpty() {
        users = DataLoader.loadUsers();
        users.get(0).getUserLogin().setPassword("");
        assertEquals("", users.get(0).getUserLogin().getPassword());
    }

    @Test
    void getUserPasswordInvalid() {
        users = DataLoader.loadUsers();
        users.get(0).getUserLogin().setPassword("lebron  james");
        assertNotEquals("lebron  james", users.get(0).getUserLogin().getPassword());
    }

    // directors
    // getsize
    @Test
    void getDirectorSize() {
        directors = DataLoader.loadDirectors();
        assertEquals(1, directors.size());
    }

    // getfirstname
    @Test
    void getDirectorFirstName() {
        directors = DataLoader.loadDirectors();
        assertEquals("Samantha", directors.get(0).getFirstName());
    }

    @Test
    void getDirectorFirstNameNull() {
        directors = DataLoader.loadDirectors();
        directors.get(0).setFirstName(null);
        assertEquals(null, directors.get(0).getFirstName());
    }

    @Test
    void getDirectorFirstNameEmpty() {
        directors = DataLoader.loadDirectors();
        directors.get(0).setFirstName("");
        assertEquals("", directors.get(0).getFirstName());
    }

    @Test
    void getDirectorFirstNameInvalid() {
        directors = DataLoader.loadDirectors();
        directors.get(0).setFirstName("123");
        assertNotEquals("123", directors.get(0).getFirstName());
    }

    // getpassword
    @Test
    void getDirectorPassword() {
        directors = DataLoader.loadDirectors();
        assertEquals("sam", directors.get(0).getUserLogin().getPassword());
    }

    @Test
    void getDirectorPasswordNull() {
        directors = DataLoader.loadDirectors();
        directors.get(0).getUserLogin().setPassword(null);
        assertEquals(null, directors.get(0).getUserLogin().getPassword());
    }

    @Test
    void getDirectorPasswordEmpty() {
        directors = DataLoader.loadDirectors();
        directors.get(0).getUserLogin().setPassword("");
        assertEquals("", directors.get(0).getUserLogin().getPassword());
    }

    @Test
    void getDirectorPasswordInvalid() {
        directors = DataLoader.loadDirectors();
        directors.get(0).getUserLogin().setPassword("invalid password");
        assertNotEquals("invalid password", directors.get(0).getUserLogin().getPassword());
    }

    // getemail
    @Test
    void getDirectorEmail() {
        directors = DataLoader.loadDirectors();
        assertEquals("samanthasamuels@gmail.com", directors.get(0).getEmail());
    }

    @Test
    void getDirectorEmailNull() {
        directors = DataLoader.loadDirectors();
        directors.get(0).setEmail(null);
        assertEquals(null, directors.get(0).getEmail());
    }

    @Test
    void getDirectorEmailEmpty() {
        directors = DataLoader.loadDirectors();
        directors.get(0).setEmail("");
        assertEquals("", directors.get(0).getEmail());
    }

    @Test
    void getDirectorEmailInvalid() {
        directors = DataLoader.loadDirectors();
        directors.get(0).setEmail("sam @@ com.gmail");
        assertNotEquals("sam @@ com.gmail", directors.get(0).getEmail());
    }

    // camps
    // get year
    @Test
    void getCampYear() {
        camps = DataLoader.loadCamps();
        assertEquals(2022, camps.get(0).getYear());
    }

    @Test
    void getCampYearTooLow() {
        camps = DataLoader.loadCamps();
        camps.get(0).setYear(45);
        assertNotEquals(45, camps.get(0).getYear());
    }

    @Test
    void getCampYearTooHigh() {
        camps = DataLoader.loadCamps();
        camps.get(0).setYear(20450);
        assertNotEquals(20450, camps.get(0).getYear());
    }

    // getname
    @Test
    void getCampName() {
        camps = DataLoader.loadCamps();
        assertEquals("2023 Summer Camp", camps.get(0).getName());
    }

    @Test
    void getCampNameNull() {
        camps = DataLoader.loadCamps();
        camps.get(0).setName(null);
        assertEquals(null, camps.get(0).getName());
    }

    @Test
    void getCampNameEmpty() {
        camps = DataLoader.loadCamps();
        camps.get(0).setName("");
        assertEquals("", camps.get(0).getName());
    }

    @Test
    void getCampNameInvalid() {
        camps = DataLoader.loadCamps();
        camps.get(0).setName("10593");
        assertNotEquals("10593", camps.get(0).getName());
    }

    // getIsfull
    @Test
    void testCampIsFull() {
        camps = DataLoader.loadCamps();
        assertEquals(false, camps.get(0).getWeek(0).isFull());
    }

    @Test
    void testCampIsFullNull() {
        camps = DataLoader.loadCamps();
        assertEquals(null, camps.get(0).getWeek(0).isFull());
    }

    @Test
    void testCampIsFullEmpty() {
        camps = DataLoader.loadCamps();
        assertEquals("", camps.get(0).getWeek(0).isFull());
    }

    @Test
    void testCampIsFullWrong() {
        camps = DataLoader.loadCamps();
        assertNotEquals(true, camps.get(0).getWeek(0).isFull());
    }

    public static Date convertToDate(String date) {
        try {
            return new SimpleDateFormat("MM/dd/yyyy").parse(date);
        } catch (java.text.ParseException e) {
            System.out.println("Sorry " + date + " is not parsable");
            return null;
        }
    }

}
