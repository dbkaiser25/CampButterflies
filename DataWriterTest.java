
//zak elguindi 
import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataWriterTest {
    private ArrayList<Camper> campers = DataLoader.loadCampers();

    private ArrayList<Counselor> counselors = DataLoader.loadCounselors();

    private ArrayList<User> users = DataLoader.loadUsers();
    private ArrayList<Director> directors = DataLoader.loadDirectors();
    private ArrayList<Camp> camps = DataLoader.loadCamps();

    @BeforeEach
    public void setup() {

        campers.clear();
        DataWriter.saveCampers();

        counselors.clear();
        DataWriter.saveCounselors();

        users.clear();
        DataWriter.saveUsers();
    }

    @Test
    void testWritingZeroCampers() {
        assertEquals(0, campers.size());
    }

    @Test
    void testWritingOneCamper() {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        contacts.add(new Contact("mary", "green", "145-295-6639", "marygreen@gmail.com", "mother"));
        ArrayList<Medication> medications = new ArrayList<Medication>();
        medications.add(new Medication("epipen", "one shot", "when reaction occurs"));
        ArrayList<String> allergies = new ArrayList<String>();
        allergies.add(new String("peanut allergy"));
        campers.add(new Camper("james", "green", "44 thorndale drive", convertToDate("8/11/2014"), Sex.MALE,
                medications, allergies, contacts,
                new Contact("Dr. ", "Smith", "202-433-5935", "drsmith1@gmail.com", "doctor")));
        assertEquals("james", campers.get(0).getFirstName());
    }

    @Test
    void testWritingThreeCampers() {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        contacts.add(new Contact("mary", "green", "145-295-6639", "marygreen@gmail.com", "mother"));
        ArrayList<Medication> medications = new ArrayList<Medication>();
        medications.add(new Medication("epipen", "one shot", "when reaction occurs"));
        ArrayList<String> allergies = new ArrayList<String>();
        allergies.add(new String("peanut allergy"));
        Contact doctor = new Contact("Dr. ", "Smith", "202-433-5935", "drsmith1@gmail.com", "doctor");
        campers.add(new Camper("james", "green", "44 thorndale drive", convertToDate("8/11/2014"), Sex.MALE,
                medications, allergies, contacts,
                doctor));

        contacts.remove(0);
        medications.remove(0);
        allergies.remove(0);
        contacts.add(new Contact("Aaron", "Rodgers", "640-629-6406", "aaronrodgers@gmail.com", "father"));
        allergies.add("none");
        campers.add(new Camper("Don", "Rodgers", "1024 Packers Drive", convertToDate("04/24/2014"), Sex.MALE,
                medications, allergies, contacts, doctor));

        contacts.remove(0);
        allergies.remove(0);
        contacts.add(new Contact("Tom", "Brady", "104-777-2053", "tombrady@gmail.com", "father"));
        allergies.add("pineapples");
        medications.add(new Medication("pineapple medicine", "100 mg", "when allergy occurs"));
        campers.add(new Camper("Tom ", "Brady Jr.", "12 Blue Drive", convertToDate("7/8/2014"), Sex.MALE, medications,
                allergies, contacts, doctor));
        assertEquals("12 Blue Drive", campers.get(2).getHomeAddress());
    }

    @Test
    void testWritingCamperNull() {
        campers.add(new Camper(null, null, null, null, null, null, null, null, null));
        assertEquals(null, campers.get(0).getAllergies());
    }

    @Test
    void testWritingCamperEmpty() {
        campers.add(new Camper("", "", "", null, null, null, null, null, null));
        assertEquals("", campers.get(0).getFirstName());
    }

    // counselors
    @Test
    void writeZeroCounselor() {
        counselors.clear();
        assertEquals(0, counselors.size());
    }

    @Test
    void writeOneCounselor() {
        Contact doctor = new Contact("Dr. ", "Smith", "202-433-5935", "drsmith1@gmail.com", "doctor");
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        contacts.add(new Contact("kathryn", "france", "400-295-6639", "kathrynfrance@gmail.com", "mother"));
        counselors.add(new Counselor("timothy", "france", "103-400-5380", "timfrance@gmail.com", "30 benson lane",
                convertToDate("12/30/2005"), contacts, doctor,
                new LoginInfo("tim", "france")));
        assertEquals("tim", counselors.get(0).getUserLogin().getUserName());
    }

    @Test
    void writeThreeCounselors() {
        Contact doctor = new Contact("Dr. ", "Smith", "202-433-5935", "drsmith1@gmail.com", "doctor");
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        contacts.add(new Contact("kathryn", "france", "400-295-6639", "kathrynfrance@gmail.com", "mother"));
        counselors.add(new Counselor("timothy", "france", "103-400-5380", "timfrance@gmail.com", "30 benson lane",
                convertToDate("12/30/2005"), contacts, doctor,
                new LoginInfo("tim", "france")));

        contacts.remove(0);
        contacts.add(new Contact("Josh", "Hughes", "125-255-5830", "joshhughes@gmail.com", "father"));
        counselors.add(new Counselor("Jason", "Hughes", "140-266-3590", "jasonhughes@gmail.com", "130 prescott drive",
                convertToDate("4/13/2005"), contacts, doctor,
                new LoginInfo("jason", "hughes")));

        contacts.remove(0);
        contacts.add(new Contact("Bryce", "Young", "610-253-2005", "bryceyoung@gmail.com", "father"));
        counselors.add(new Counselor("Ashely", "Young", "610-255-3694", "ashleyyoung@gmail.com", "180 prescott drive",
                convertToDate("05/16/2006"), contacts, doctor,
                new LoginInfo("ashley", "young")));
        assertEquals("130 prescott drive", counselors.get(1).getHomeAddress());
    }

    @Test
    void writeNullCounselor() {
        counselors.add(new Counselor(null, null, null, null, null, null, null, null, null));
        assertEquals(null, counselors.get(0).getDateOfBirth());
    }

    @Test
    void writeEmptyCounselor() {
        counselors.add(new Counselor("", "", "", "", "", null, null, null, null));
        assertEquals("", counselors.get(0).getPhoneNumber());
    }

    @Test
    void testWritingZeroUsers() {
        users = DataLoader.loadUsers();
        assertEquals(0, users.size());
    }

    @Test
    void writeOneUser() {
        users.add(new User("LeBron", "James", convertToDate("08/31/1980"), "16 Calcium Circle",
                new LoginInfo("lebron", "james"), campers));
        assertEquals("lebron", users.get(0).getUserLogin().getUserName());
    }

    @Test
    void writeThreeUsers() {
        users.add(new User("LeBron", "James", convertToDate("08/31/1980"), "16 Calcium Circle",
                new LoginInfo("lebron", "james"), campers));
        users.add(new User("Michael", "Jordan", convertToDate("12/14/1970"), "23 Bulls Drive",
                new LoginInfo("michael", "jordan"), campers));
        users.add(new User("Kobe", "Bryant", convertToDate("08/23/1975"), "88 Lake Court",
                new LoginInfo("kobe", "Bryant"), campers));
        assertEquals("Bryant", users.get(2).getLastName());
    }

    @Test
    void writeNullUser() {
        users.add(new User(null, null, null, null, null, null));
        assertEquals(null, users.get(0).getCampers());
    }

    @Test
    void writeEmptyUser() {
        users.add(new User("", "", null, "", null, null));
        assertEquals("", users.get(0).getLastName());
    }

    // directors
    @Test
    void writeZeroDirector() {
        directors.clear();
        assertEquals(0, directors.size());
    }

    @Test
    void editOneDirector() {
        directors.get(0).setEmail("director@gmail.com");
        assertEquals("director@gmail.com", directors.get(0).getEmail());
    }

    @Test
    void writeNullDirector() {
        directors.add(new Director(null, null, null, null, null, null, null));
        assertEquals(null, directors.get(1).getCamps());
    }

    @Test
    void testEmptyDirector() {
        directors.add(new Director("", "", convertToDate(""), "", "", null, null));
        assertEquals("", directors.get(1).getDateOfBirth());
    }

    // camos
    @Test
    void writeZeroCamps() {
        camps.clear();
        assertEquals(0, camps.size());
    }

    @Test
    void editOneCamp() {
        camps.get(0).getActivitiesArrayList().get(0).setLocation("blank location");
        assertEquals("blank location", camps.get(0).getActivitiesArrayList().get(0).getLocation());
    }

    @Test
    void writeNullCamp() {
        camps.add(new Camp(null, null, 0, 0, null));
        assertEquals(null, camps.get(1).getActivitiesArrayList());
    }

    @Test
    void writeEmptyCamp() {
        camps.add(new Camp("", "", 0, 0, null));
        assertEquals("", camps.get(1).getName());
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
