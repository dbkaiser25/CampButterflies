import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {
    public static void main(String[] args) {
        ArrayList<Camper> campers = DataLoader.loadCampers();
        for (int i = 0; i < campers.size(); i++) {
            System.out.println(campers.get(i));
        }

    }

    public static ArrayList<Camper> loadCampers() {
        ArrayList<Camper> campers = new ArrayList<Camper>();

        try {
            FileReader reader = new FileReader(CAMPER_FILE);
            JSONArray campersJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < campersJSON.size(); i++) {
                JSONObject camperJSON = (JSONObject) campersJSON.get(i);
                UUID id = UUID.fromString((String) camperJSON.get(ID));
                String firstName = (String) camperJSON.get(FIRSTNAME);
                String lastName = (String) camperJSON.get(LASTNAME);
                String dateOfBirth = (String) camperJSON.get(DOB);
                String homeAddress = (String) camperJSON.get(HOMEADDRESS);
                // Sex sex = (Sex) camperJSON.get(GENDER); //have to cast string to enum
                Sex sex = Sex.FEMALE;
                JSONArray allergiesJson = (JSONArray) camperJSON.get(ALLERGIES);
                ArrayList<String> allergies = new ArrayList<String>();
                for (int j = 0; j < allergiesJson.size(); j++) {
                    String allergy = (String) allergiesJson.get(j);
                    allergies.add(allergy);
                }
                JSONArray medicationsJSON = (JSONArray) camperJSON.get(MEDICATIONS);
                ArrayList<Medication> medications = new ArrayList<Medication>();
                for (int j = 0; j < medicationsJSON.size(); j++) {
                    JSONObject medJSON = (JSONObject) medicationsJSON.get(j);
                    String dosage = (String) medJSON.get(DOSAGE);
                    String type = (String) medJSON.get(TYPE);
                    String time = (String) medJSON.get(TIME);
                    medications.add(new Medication(type, dosage, time));
                }
                JSONArray contactsJSON = (JSONArray) camperJSON.get(CONTACTS);
                ArrayList<Contact> contacts = new ArrayList<Contact>();
                for (int j = 0; j > contacts.size(); j++) {
                    JSONObject contactJSON = (JSONObject) contactsJSON.get(j);
                    String contactFirstName = (String) contactJSON.get(FIRSTNAME);
                    String contactLastName = (String) contactJSON.get(LASTNAME);
                    String phoneNumber = (String) contactJSON.get(PHONE_NUM);
                    String emailAddress = (String) contactJSON.get(EMAIL);
                    String relationToPerson = (String) contactJSON.get(CONT_RELATION_TO_PERSON);
                    contacts.add(new Contact(contactFirstName, contactLastName, phoneNumber, emailAddress,
                            relationToPerson));
                }
                JSONObject pediatricianJSON = (JSONObject) camperJSON.get(PEDIATRICIAN);
                String pedFirstName = (String) pediatricianJSON.get(FIRSTNAME);
                String pedLastName = (String) pediatricianJSON.get(LASTNAME);
                String phoneNumber = (String) pediatricianJSON.get(PHONE_NUM);
                String email = (String) pediatricianJSON.get(EMAIL);
                String relationToPerson = (String) pediatricianJSON.get(CONT_RELATION_TO_PERSON);
                Contact pediatrician = new Contact(pedFirstName, pedLastName, phoneNumber, email, relationToPerson);
                campers.add(new Camper(id, firstName, lastName, homeAddress, dateOfBirth, sex, medications, allergies,
                        contacts, pediatrician));
            }
            return campers;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<User>();

        try {
            FileReader reader = new FileReader(USER_FILE);
            JSONArray usersJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < usersJSON.size(); i++) {
                JSONObject userJSON = (JSONObject) usersJSON.get(i);
                UUID id = UUID.fromString((String) userJSON.get(ID));
                String firstName = (String) userJSON.get(FIRSTNAME);
                String lastName = (String) userJSON.get(LASTNAME);
                String dateOfBirth = (String) userJSON.get(DOB);
                String homeAddress = (String) userJSON.get(HOMEADDRESS);
                String userName = (String) userJSON.get(USERNAME);
                String password = (String) userJSON.get(PASSWORD);
                LoginInfo userLogin = new LoginInfo(userName, password);

                ArrayList<Camper> campersList = new ArrayList<Camper>();
                JSONArray campers = (JSONArray) userJSON.get(CAMPERS);
                /*
                 * Loop through json array of camper uuids
                 * Camper camper = CamperList.getInstance().getCamperByUUID(id);
                 */
                for (int j = 0; j < campers.size(); j++) {
                    UUID camperID = UUID.fromString((String) campers.get(j));
                    Camper camper = CamperList.getInstance().getCamperByUUID(camperID);
                    campersList.add(camper);
                }

                users.add(new User(id, firstName, lastName, dateOfBirth, homeAddress, userLogin, campers));
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Counselor> loadCounselors() {
        ArrayList<Counselor> counselors = new ArrayList<Counselor>();

        try {
            FileReader reader = new FileReader(COUNSELOR_FILE);
            JSONArray counselorsJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < counselorsJSON.size(); i++) {
                JSONObject counselorJSON = (JSONObject) counselorsJSON.get(i);
                UUID id = UUID.fromString((String) counselorJSON.get(ID));
                String firstName = (String) counselorJSON.get(FIRSTNAME);
                String lastName = (String) counselorJSON.get(LASTNAME);
                String dateOfBirth = (String) counselorJSON.get(DOB);
                String homeAddress = (String) counselorJSON.get(HOMEADDRESS);
                String phoneNumber = (String) counselorJSON.get(PHONE_NUM);
                String emailAddress = (String) counselorJSON.get(EMAIL);
                String userName = (String) counselorJSON.get(USERNAME);
                String password = (String) counselorJSON.get(PASSWORD);
                LoginInfo counselorLogin = new LoginInfo(userName, password);
                /*
                 * camperList: getCamperByUUID(UUID)
                 * arrayLists: make a JSON array
                 * make a getCurrentWeek method in facade
                 */
                JSONArray contactsJSON = (JSONArray) counselorJSON.get(CONTACTS);
                ArrayList<Contact> contacts = new ArrayList<Contact>();
                for (int j = 0; j > contacts.size(); j++) {
                    JSONObject contactJSON = (JSONObject) contactsJSON.get(j);
                    String contactFirstName = (String) contactJSON.get(FIRSTNAME);
                    String contactLastName = (String) contactJSON.get(LASTNAME);
                    String contactPhoneNumber = (String) contactJSON.get(PHONE_NUM);
                    String contactEmailAddress = (String) contactJSON.get(EMAIL);
                    String relationToPerson = (String) contactJSON.get(CONT_RELATION_TO_PERSON);
                    contacts.add(new Contact(contactFirstName, contactLastName, contactPhoneNumber, contactEmailAddress,
                            relationToPerson));
                }
                JSONArray pediatricianJSON = (JSONArray) counselorJSON.get(PEDIATRICIAN);
                Contact pediatrician = new Contact(); // see if this works
                for (int j = 0; j < pediatricianJSON.size(); j++) {
                    JSONObject pedJSON = (JSONObject) pediatricianJSON.get(j);
                    String pedFirstName = (String) pedJSON.get(FIRSTNAME);
                    String pedLastName = (String) pedJSON.get(LASTNAME);
                    String pedPhoneNumber = (String) pedJSON.get(PHONE_NUM);
                    String email = (String) pedJSON.get(EMAIL);
                    String relationToPerson = (String) pedJSON.get(CONT_RELATION_TO_PERSON);
                    pediatrician = new Contact(pedFirstName, pedLastName, pedPhoneNumber, email, relationToPerson);
                }
                counselors.add(new Counselor(id, firstName, lastName, phoneNumber, emailAddress, homeAddress,
                        dateOfBirth, contacts, pediatrician, counselorLogin));

            }
            return counselors;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Director> loadDirectors() {
        ArrayList<Director> directors = new ArrayList<Director>();

        try {
            FileReader reader = new FileReader(DIRECTOR_FILE);
            JSONArray directorsJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < directorsJSON.size(); i++) {
                JSONObject directorJSON = (JSONObject) directorsJSON.get(i);
                UUID id = (UUID) directorJSON.get(ID);
                String firstName = (String) directorJSON.get(FIRSTNAME);
                String lastName = (String) directorJSON.get(LASTNAME);
                String dateOfBirth = (String) directorJSON.get(DOB);
                String homeAddress = (String) directorJSON.get(HOMEADDRESS);
                String userName = (String) directorJSON.get(USERNAME);
                String password = (String) directorJSON.get(PASSWORD);
                LoginInfo directorLogin = new LoginInfo(userName, password);

                JSONArray jsonCalendar = (JSONArray) directorJSON.get(CALENDAR);
                Calendar calendar = new Calendar();
                for (int j = 0; j < jsonCalendar.size(); j++) {
                    JSONObject calendarJSON = (JSONObject) jsonCalendar.get(j);
                    // HashMap<Integer,Week> calendarHash =
                    // (HashMap<Integer,Week>)calendarJSON.get(CALENDAR_HASH);
                    JSONArray jsonHash = (JSONArray) calendarJSON.get(CALENDAR_HASH);
                    HashMap<Integer, Week> hashMap = new HashMap<Integer, Week>();
                    Integer week_num = ((Long) calendarJSON.get(WEEK_NUM)).intValue();
                    /*
                     * potential
                     */
                    Week week = (Week) calendarJSON.get(WEEK);
                    for (int k = 0; k < jsonHash.size(); k++) {
                        JSONObject weekJSON = (JSONObject) jsonHash.get(k);
                        String theme = (String) weekJSON.get(THEME);
                        ArrayList<Group> groups = new ArrayList<Group>();
                        JSONArray groupsJSON = (JSONArray) weekJSON.get(GROUPS);
                        for (int l = 0; l < groupsJSON.size(); l++) {
                            JSONObject jsonGroup = (JSONObject) groupsJSON.get(l);
                            int number = (int) jsonGroup.get(GROUP_NUM); // see if this has to be a UUID
                            // make group.json with just UUID
                            UUID counselorUUID = UUID.fromString((String) jsonGroup.get(COUNSELOR_ID));
                            Counselor counselor = CounselorList.getInstance().getCounselorByUUID(counselorUUID);
                            ArrayList<Camper> campersList = new ArrayList<Camper>();
                            JSONArray campers = (JSONArray) jsonGroup.get(GROUP_CAMPERS);
                            /*
                             * Loop through json array of camper uuids
                             * Camper camper = CamperList.getInstance().getCamperByUUID(id);
                             */
                            for (int m = 0; m < campers.size(); m++) {
                                UUID camperID = UUID.fromString((String) campers.get(j));
                                Camper camper = CamperList.getInstance().getCamperByUUID(camperID);
                                campersList.add(camper);
                            }
                            groups.add(new Group(counselor, campersList));// see if we need group number in constructor
                                                                          // as well
                        }
                        ArrayList<Counselor> week_counselors = new ArrayList<Counselor>();
                        JSONArray counselorsJSON = (JSONArray) weekJSON.get(WEEK_COUNSELORS);
                        for (int l = 0; l < counselorsJSON.size(); l++) {

                        }
                    }
                    /*
                     * figure out how to add to hashMap
                     */
                    ArrayList<Activity> activities = new ArrayList<Activity>();
                    calendar = new Calendar(hashMap, activities);
                }

                directors.add(new Director(id, firstName, lastName, dateOfBirth, homeAddress, directorLogin));
            }
            return directors;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * this is the hardest part. See if you can figure this out Friday
     */
    public static ArrayList<Camp> loadCamps() {
        ArrayList<Camp> camps = new ArrayList<Camp>();

        try {
            FileReader reader = new FileReader(CAMP_FILE);
            JSONArray campsJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < campsJSON.size(); i++) {
                JSONObject campJSON = (JSONObject) campsJSON.get(i);
                String name = (String) campJSON.get(NAME);
                String description = (String) campJSON.get(DESCRIPTION);
                HashMap<Integer, Week> masterSchedule = (HashMap<Integer, Week>) campJSON.get(WEEK);

                JSONArray activitiesJSON = (JSONArray) campJSON.get(DAILY_ACTIVITIES);
                ArrayList<Activity> activities = new ArrayList<Activity>();
                for (int j = 0; j < activities.size(); j++) {
                    JSONObject activityJSON = (JSONObject) activitiesJSON.get(j);
                    String activityName = (String) activityJSON.get(NAME);
                    String location = (String) activityJSON.get(LOCATION);
                    String activityDescription = (String) activityJSON.get(DESCRIPTION);
                    activities.add(new Activity(activityName, location, activityDescription));
                }

                camps.add(new Camp(name, description, masterSchedule, activities));
            }
            return camps;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
