import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {
    /**
     * main method used for testing loader methods
     * 
     * @param args
     */
    public static void main(String[] args) {

    }

    /**
     * reads campers from camper.json
     * 
     * @return returns an array list of campers from JSON file
     */
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
                Date dob = convertToDate(dateOfBirth);
                String homeAddress = (String) camperJSON.get(HOMEADDRESS);
                String gender = (String) camperJSON.get(GENDER);
                Sex sex = Sex.valueOf(gender.toUpperCase());
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
                for (int j = 0; j < contactsJSON.size(); j++) {
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
                Contact pediatrician = new Contact(pedFirstName, pedLastName, phoneNumber,
                        email, relationToPerson);
                campers.add(new Camper(id, firstName, lastName, homeAddress, dob, sex, medications, allergies,
                        contacts, pediatrician));
            }
            return campers;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * reads users from user.json
     * 
     * @return return an arraylist of users from JSON
     */
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
                Date dob = convertToDate(dateOfBirth);
                String homeAddress = (String) userJSON.get(HOMEADDRESS);
                String userName = (String) userJSON.get(USERNAME);
                String password = (String) userJSON.get(PASSWORD);
                LoginInfo userLogin = new LoginInfo(userName, password);

                ArrayList<Camper> campersList = new ArrayList<Camper>();
                JSONArray campers = (JSONArray) userJSON.get(USER_CAMPERS);
                for (int j = 0; j < campers.size(); j++) {
                    UUID camperID = UUID.fromString((String) campers.get(j));
                    Camper camper = CamperList.getInstance().getCamperByUUID(camperID);
                    campersList.add(camper);
                }

                users.add(new User(id, firstName, lastName, dob, homeAddress, userLogin, campersList));
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * reads counselors from counselor.json
     * 
     * @return arraylist of counselors from JSON
     */
    public static ArrayList<Counselor> loadCounselors() { // tested: works
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
                Date dob = convertToDate(dateOfBirth);
                String homeAddress = (String) counselorJSON.get(HOMEADDRESS);
                String phoneNumber = (String) counselorJSON.get(PHONE_NUM);
                String emailAddress = (String) counselorJSON.get(EMAIL);
                String userName = (String) counselorJSON.get(USERNAME);
                String password = (String) counselorJSON.get(PASSWORD);
                LoginInfo counselorLogin = new LoginInfo(userName, password);
                JSONArray contactsJSON = (JSONArray) counselorJSON.get(CONTACTS);
                ArrayList<Contact> contacts = new ArrayList<Contact>();
                for (int j = 0; j < contactsJSON.size(); j++) {
                    JSONObject contactJSON = (JSONObject) contactsJSON.get(j);
                    String contactFirstName = (String) contactJSON.get(FIRSTNAME);
                    String contactLastName = (String) contactJSON.get(LASTNAME);
                    String contactPhoneNumber = (String) contactJSON.get(PHONE_NUM);
                    String contactEmailAddress = (String) contactJSON.get(EMAIL);
                    String relationToPerson = (String) contactJSON.get(CONT_RELATION_TO_PERSON);
                    contacts.add(new Contact(contactFirstName, contactLastName, contactPhoneNumber, contactEmailAddress,
                            relationToPerson));
                }
                JSONObject pediatricianJSON = (JSONObject) counselorJSON.get(PEDIATRICIAN);
                String pedFirstName = (String) pediatricianJSON.get(FIRSTNAME);
                String pedLastName = (String) pediatricianJSON.get(LASTNAME);
                String pedPhoneNumber = (String) pediatricianJSON.get(PHONE_NUM);
                String email = (String) pediatricianJSON.get(EMAIL);
                String relationToPerson = (String) pediatricianJSON.get(CONT_RELATION_TO_PERSON);
                Contact pediatrician = new Contact(pedFirstName, pedLastName, pedPhoneNumber,
                        email, relationToPerson);

                counselors.add(new Counselor(id, firstName, lastName, phoneNumber, emailAddress, homeAddress,
                        dob, contacts, pediatrician, counselorLogin));

            }
            return counselors;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * reads directors from director.json
     * 
     * @return an arraylist of directors from JSON
     */
    public static ArrayList<Director> loadDirectors() {
        ArrayList<Director> directors = new ArrayList<Director>();

        try {
            FileReader reader = new FileReader(DIRECTOR_FILE);
            JSONArray directorsJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < directorsJSON.size(); i++) {
                JSONObject directorJSON = (JSONObject) directorsJSON.get(i);
                UUID id = UUID.fromString((String) directorJSON.get(ID));
                String firstName = (String) directorJSON.get(FIRSTNAME);
                String lastName = (String) directorJSON.get(LASTNAME);
                String dateOfBirth = (String) directorJSON.get(DOB);
                Date dob = convertToDate(dateOfBirth);
                String homeAddress = (String) directorJSON.get(HOMEADDRESS);
                String userName = (String) directorJSON.get(USERNAME);
                String password = (String) directorJSON.get(PASSWORD);
                String email = (String) directorJSON.get(EMAIL);
                LoginInfo directorLogin = new LoginInfo(userName, password);

                JSONArray jsonCalendar = (JSONArray) directorJSON.get(CALENDAR);
                ArrayList<Camp> camps = new ArrayList<Camp>();
                Camp calendar = new Camp();
                for (int j = 0; j < jsonCalendar.size(); j++) {
                    JSONObject calendarJSON = (JSONObject) jsonCalendar.get(j);
                    String campName = (String) calendarJSON.get(NAME);
                    String campDescription = (String) calendarJSON.get(DESCRIPTION);
                    Integer year = ((Long) calendarJSON.get(YEAR)).intValue();
                    JSONArray jsonHash = (JSONArray) calendarJSON.get(CALENDAR_HASH);
                    HashMap<Integer, Week> masterScheduleHash = new HashMap<Integer, Week>();

                    for (int k = 0; k < jsonHash.size(); k++) {
                        JSONObject JSONWeek = (JSONObject) jsonHash.get(k);
                        Integer week_num = ((Long) JSONWeek.get(WEEK_NUM)).intValue();
                        JSONObject weekJSON = (JSONObject) JSONWeek.get(WEEK);
                        String theme = (String) weekJSON.get(THEME);
                        ArrayList<Group> groups = new ArrayList<Group>();
                        JSONArray groupsJSON = (JSONArray) weekJSON.get(GROUPS);
                        for (int l = 0; l < groupsJSON.size(); l++) {
                            JSONObject jsonGroup = (JSONObject) groupsJSON.get(l);
                            UUID groupNum = UUID.fromString((String) jsonGroup.get(GROUP_ID));
                            UUID counselorUUID = UUID.fromString((String) jsonGroup.get(COUNSELOR_ID));
                            Counselor counselor = CounselorList.getInstance().getCounselorByUUID(counselorUUID);
                            ArrayList<Camper> campersList = new ArrayList<Camper>();
                            JSONArray campers = (JSONArray) jsonGroup.get(GROUP_CAMPERS);
                            for (int m = 0; m < campers.size(); m++) {
                                JSONObject camperUUID = (JSONObject) campers.get(m);
                                UUID camperID = UUID.fromString((String) camperUUID.get(ID));
                                Camper camper = CamperList.getInstance().getCamperByUUID(camperID);
                                campersList.add(camper);
                            }
                            Counselor c = CounselorList.getInstance().getCounselorByUUID(counselorUUID);
                            JSONArray group_schedule = (JSONArray) jsonGroup.get(GROUP_SCHEDULE);
                            HashMap<DayOfWeek, ArrayList<Activity>> groupHashMap = new HashMap<DayOfWeek, ArrayList<Activity>>();
                            for (int m = 0; m < group_schedule.size(); m++) {
                                JSONObject scheduleJSON = (JSONObject) group_schedule.get(m);
                                String dayOfWeek = (String) scheduleJSON.get(DAY_OF_WEEK);
                                DayOfWeek day = DayOfWeek.valueOf(dayOfWeek.toUpperCase());
                                JSONArray activitiesJSON = (JSONArray) scheduleJSON.get(DAILY_ACTIVITIES);
                                ArrayList<Activity> dailyActivities = new ArrayList<Activity>();
                                for (int n = 0; n < activitiesJSON.size(); n++) {
                                    JSONObject thisActivity = (JSONObject) activitiesJSON.get(n);
                                    String activityName = (String) thisActivity.get(NAME);
                                    String activityLocaiton = (String) thisActivity.get(LOCATION);
                                    String activityDescription = (String) thisActivity.get(DESCRIPTION);
                                    dailyActivities
                                            .add(new Activity(activityName, activityLocaiton, activityDescription));
                                }
                                groupHashMap.put(day, dailyActivities);
                            }
                            Group g = new Group(groupNum, counselor, campersList, groupHashMap);
                            groups.add(g);

                        }
                        ArrayList<Counselor> week_counselors = new ArrayList<Counselor>();
                        JSONArray counselorsJSON = (JSONArray) weekJSON.get(WEEK_COUNSELORS);
                        for (int l = 0; l < counselorsJSON.size(); l++) {
                            JSONObject counselorJSONID = (JSONObject) counselorsJSON.get(l);
                            UUID counselorID = UUID.fromString((String) counselorJSONID.get(ID));
                            Counselor counselor = CounselorList.getInstance().getCounselorByUUID(counselorID);
                            week_counselors.add(counselor);
                        }
                        ArrayList<Camper> week_campers = new ArrayList<Camper>();
                        JSONArray campersJSON = (JSONArray) weekJSON.get(WEEK_CAMPERS);
                        for (int l = 0; l < campersJSON.size(); l++) {
                            JSONObject camperJSONID = (JSONObject) campersJSON.get(l);
                            UUID camperUUID = UUID.fromString((String) camperJSONID.get(ID));
                            Camper camper = CamperList.getInstance().getCamperByUUID(camperUUID);
                            week_campers.add(camper);
                        }
                        String startDateString = (String) weekJSON.get(START_DATE);
                        Date startDate = convertToDate(startDateString);
                        String endDateString = (String) weekJSON.get(END_DATE);
                        Date endDate = convertToDate(endDateString);
                        String isFullString = (String) weekJSON.get(ISFULL);
                        Boolean isFull = Boolean.valueOf(isFullString);
                        Week week = new Week(theme, groups, week_counselors, week_campers, startDate, endDate, isFull);

                        masterScheduleHash.put(week_num, week);
                    }
                    ArrayList<Activity> activities = new ArrayList<Activity>();
                    JSONArray activitiesJSON = (JSONArray) calendarJSON.get(ALL_ACTIVITIES);
                    for (int l = 0; l < activitiesJSON.size(); l++) {
                        JSONObject activity = (JSONObject) activitiesJSON.get(l);
                        String name = (String) activity.get(NAME);
                        String location = (String) activity.get(LOCATION);
                        String description = (String) activity.get(DESCRIPTION);
                        activities.add(new Activity(name, location, description));
                    }
                    calendar = new Camp(campName, campDescription, masterScheduleHash, activities, year);
                    camps.add(calendar);
                }
                directors.add(new Director(id, firstName, lastName, dob, homeAddress, email, directorLogin, camps));
            }
            return directors;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * reads camps from camp.json
     * 
     * @return arraylist of camps from JSON
     */
    public static ArrayList<Camp> loadCamps() {
        ArrayList<Camp> camps = new ArrayList<Camp>();

        try {
            FileReader reader = new FileReader(CAMP_FILE);
            JSONArray campsJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i = 0; i < campsJSON.size(); i++) {
                JSONObject campJSON = (JSONObject) campsJSON.get(i);
                String campName = (String) campJSON.get(NAME);
                String campDescription = (String) campJSON.get(DESCRIPTION);
                Integer year = ((Long) campJSON.get(YEAR)).intValue();
                JSONArray jsonHash = (JSONArray) campJSON.get(CALENDAR_HASH);
                HashMap<Integer, Week> masterScheduleHash = new HashMap<Integer, Week>();
                for (int j = 0; j < jsonHash.size(); j++) {
                    JSONObject JSONWeek = (JSONObject) jsonHash.get(j);
                    Integer week_num = ((Long) JSONWeek.get(WEEK_NUM)).intValue();
                    JSONObject weekJSON = (JSONObject) JSONWeek.get(WEEK);
                    String theme = (String) weekJSON.get(THEME);
                    ArrayList<Group> groups = new ArrayList<Group>();
                    JSONArray groupsJSON = (JSONArray) weekJSON.get(GROUPS);
                    for (int l = 0; l < groupsJSON.size(); l++) {
                        JSONObject jsonGroup = (JSONObject) groupsJSON.get(l);
                        UUID groupNum = UUID.fromString((String) jsonGroup.get(GROUP_ID));
                        UUID counselorUUID;
                        Counselor counselor = new Counselor();
                        if (jsonGroup.get(COUNSELOR_ID) == null) {
                            counselorUUID = null;
                            counselor = CounselorList.getInstance().getCounselorByUUID(counselorUUID);
                        } else {
                            counselorUUID = UUID.fromString((String) jsonGroup.get(COUNSELOR_ID));
                            counselor = CounselorList.getInstance().getCounselorByUUID(counselorUUID);
                        }
                        counselor = CounselorList.getInstance().getCounselorByUUID(counselorUUID);
                        ArrayList<Camper> campersList = new ArrayList<Camper>();
                        if (jsonGroup.get(GROUP_CAMPERS) == null) {
                            Camper camper = null;
                            campersList.add(camper);
                        } else {
                            JSONArray campers = (JSONArray) jsonGroup.get(GROUP_CAMPERS);
                            for (int m = 0; m < campers.size(); m++) {
                                JSONObject camperUUID = (JSONObject) campers.get(m);
                                UUID camperID = UUID.fromString((String) camperUUID.get(ID));
                                Camper camper = CamperList.getInstance().getCamperByUUID(camperID);
                                campersList.add(camper);
                            }
                        }
                        JSONArray group_schedule = (JSONArray) jsonGroup.get(GROUP_SCHEDULE);
                        HashMap<DayOfWeek, ArrayList<Activity>> groupHashMap = new HashMap<DayOfWeek, ArrayList<Activity>>();
                        for (int m = 0; m < group_schedule.size(); m++) {
                            JSONObject scheduleJSON = (JSONObject) group_schedule.get(m);
                            String dayOfWeek = (String) scheduleJSON.get(DAY_OF_WEEK);
                            DayOfWeek day = DayOfWeek.valueOf(dayOfWeek.toUpperCase());
                            JSONArray activitiesJSON = (JSONArray) scheduleJSON.get(DAILY_ACTIVITIES);
                            ArrayList<Activity> dailyActivities = new ArrayList<Activity>();
                            for (int n = 0; n < activitiesJSON.size(); n++) {
                                JSONObject thisActivity = (JSONObject) activitiesJSON.get(n);
                                String activityName = (String) thisActivity.get(NAME);
                                String activityLocation = (String) thisActivity.get(LOCATION);
                                String activityDescription = (String) thisActivity.get(DESCRIPTION);
                                dailyActivities.add(new Activity(activityName, activityLocation, activityDescription));
                            }
                            groupHashMap.put(day, dailyActivities);
                        }
                        groups.add(new Group(groupNum, counselor, campersList, groupHashMap));
                    }
                    ArrayList<Counselor> week_counselors = new ArrayList<Counselor>();
                    JSONArray counselorsJSON = (JSONArray) weekJSON.get(WEEK_COUNSELORS);
                    for (int l = 0; l < counselorsJSON.size(); l++) {
                        JSONObject counselorJSONID = (JSONObject) counselorsJSON.get(l);
                        UUID counselorID = UUID.fromString((String) counselorJSONID.get(ID));
                        Counselor counselor = CounselorList.getInstance().getCounselorByUUID(counselorID);
                        week_counselors.add(counselor);
                    }
                    ArrayList<Camper> week_campers = new ArrayList<Camper>();
                    JSONArray campersJSON = (JSONArray) weekJSON.get(WEEK_CAMPERS);
                    for (int l = 0; l < campersJSON.size(); l++) {
                        JSONObject camperJSONID = (JSONObject) campersJSON.get(l);
                        UUID camperUUID = UUID.fromString((String) camperJSONID.get(ID));
                        Camper camper = CamperList.getInstance().getCamperByUUID(camperUUID);
                        week_campers.add(camper);
                    }
                    String startDateString = (String) weekJSON.get(START_DATE);
                    Date startDate = convertToDate(startDateString);
                    String endDateString = (String) weekJSON.get(END_DATE);
                    Date endDate = convertToDate(endDateString);
                    String isFullString = (String) weekJSON.get(ISFULL);
                    Boolean isFull = Boolean.valueOf(isFullString);
                    Week week = new Week(theme, groups, week_counselors, week_campers, startDate, endDate, isFull);
                    masterScheduleHash.put(week_num, week);
                }
                ArrayList<Activity> activities = new ArrayList<Activity>();
                JSONArray activitiesJSON = (JSONArray) campJSON.get(ALL_ACTIVITIES);
                for (int l = 0; l < activitiesJSON.size(); l++) {
                    JSONObject activity = (JSONObject) activitiesJSON.get(l);
                    String name = (String) activity.get(NAME);
                    String location = (String) activity.get(LOCATION);
                    String description = (String) activity.get(DESCRIPTION);
                    activities.add(new Activity(name, location, description));
                }
                Camp camp = new Camp(campName, campDescription, masterScheduleHash, activities, year);
                camps.add(camp);
            }
            return camps;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * converts Strings from JSON to dates to be used in backend methods
     * 
     * @param date string that will be converted to date
     * @return return the converted string as a date
     */
    public static Date convertToDate(String date) {
        try {
            return new SimpleDateFormat("MM/dd/yyyy").parse(date);
        } catch (java.text.ParseException e) {
            System.out.println("Sorry " + date + " is not parsable");
            return null;
        }
    }

}
