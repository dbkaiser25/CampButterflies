import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * notes on automation
 * makeSchedule should have other methods to help it
 * should have themes, schedules, year, number of cabins, - in facade
 */

public class DataWriter extends DataConstants {

    public static void main(String[] args) {

        // CamperList campers = CamperList.getInstance();
        // campers.saveCampers();
        // ArrayList<Camper> newCampers = DataLoader.loadCampers();
        // for (int i = 0; i < newCampers.size(); i++) {
        // System.out.println(newCampers.get(i));
        // }

        // CounselorList counselors = CounselorList.getInstance();
        // counselors.saveCounselor();
        // ArrayList<Counselor> newCounselors = DataLoader.loadCounselors();
        // for (int i = 0; i < newCounselors.size(); i++) {
        // System.out.println(newCounselors.get(i));
        // }

        // UserList users = UserList.getInstance();
        // users.saveUsers();
        // ArrayList<User> newUsers = DataLoader.loadUsers();
        // for (int i = 0; i < newUsers.size(); i++) {
        // System.out.println(newUsers.get(i));
        // }

        // DirectorList directors = DirectorList.getInstance();
        // directors.saveDirector();
        // ArrayList<Director> newDirectors = DataLoader.loadDirectors();
        // for (int i = 0; i < newDirectors.size(); i++) {
        // System.out.println(newDirectors.get(i).getEmail());
        // }

        CampList camps = CampList.getInstance();
        camps.saveCamps();
        ArrayList<Camp> newCamps = DataLoader.loadCamps();
        for (int i = 0; i < newCamps.size(); i++) {
            System.out.println("test 2 " + newCamps.get(i));
        }
        UUID id = UUID.fromString("b6a12faa-3eaa-44f5-8a24-62d6e839d41d");
        writeGroupFiles(id);
    }

    public static void saveCampers() { // finished not tested
        CamperList campers = CamperList.getInstance();
        ArrayList<Camper> camperList = campers.getCampers();
        JSONArray jsonCampers = new JSONArray();
        /*
         * creates JSON Objects for campers
         */
        for (int i = 0; i < camperList.size(); i++) {
            jsonCampers.add(getCamperJSON(camperList.get(i)));
        }
        /*
         * writes JSON file
         */
        try (FileWriter file = new FileWriter(CAMPER_FILE)) {
            file.write(jsonCampers.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getCamperJSON(Camper camper) { // finished and tested
        JSONObject camperDetails = new JSONObject();
        camperDetails.put(ID, camper.getUUID().toString());
        camperDetails.put(FIRSTNAME, camper.getFirstName());
        camperDetails.put(LASTNAME, camper.getLastName());
        camperDetails.put(HOMEADDRESS, camper.getHomeAddress());
        camperDetails.put(DOB, convertDateToString(camper.getDateOfBirth()));
        Sex sex = camper.getSex();
        String gender = sex.toString();
        camperDetails.put(GENDER, gender);
        JSONArray allergiesJSON = new JSONArray();
        for (int i = 0; i < camper.getAllergies().size(); i++) {
            String allergy = camper.getAllergies().get(i);
            allergiesJSON.add(allergy);
        }
        camperDetails.put(ALLERGIES, allergiesJSON);
        JSONArray medicationsJSON = new JSONArray();
        for (int i = 0; i < camper.getMedications().size(); i++) {
            // make medication json object
            JSONObject medJSON = new JSONObject();
            String dosage = camper.getMedications().get(i).getDose();
            medJSON.put(DOSAGE, dosage);
            String type = camper.getMedications().get(i).getType();
            medJSON.put(TYPE, type);
            String time = camper.getMedications().get(i).getTime();
            medJSON.put(TIME, time);
            medicationsJSON.add(medJSON);
        }
        camperDetails.put(MEDICATIONS, medicationsJSON);
        JSONArray contactsJSON = new JSONArray();
        for (int i = 0; i < camper.getEmergencyContacts().size(); i++) {
            JSONObject contactObj = new JSONObject();
            String firstName = camper.getEmergencyContacts().get(i).getFirstName();
            contactObj.put(FIRSTNAME, firstName);
            String lastName = camper.getEmergencyContacts().get(i).getLastName();
            contactObj.put(LASTNAME, lastName);
            String phoneNumber = camper.getEmergencyContacts().get(i).getPhoneNumber();
            contactObj.put(PHONE_NUM, phoneNumber);
            String email = camper.getEmergencyContacts().get(i).getEmailAddress();
            contactObj.put(EMAIL, email);
            String relationtoPerson = camper.getEmergencyContacts().get(i).getRelationToPerson();
            contactObj.put(CONT_RELATION_TO_PERSON, relationtoPerson);
            contactsJSON.add(contactObj);
        }
        camperDetails.put(CONTACTS, contactsJSON);
        // see if this works
        JSONObject pediatricianJSON = new JSONObject();
        String firstName = camper.getPediatrician().getFirstName();
        String lastName = camper.getPediatrician().getLastName();
        String phoneNumber = camper.getPediatrician().getPhoneNumber();
        String email = camper.getPediatrician().getEmailAddress();
        String relationToPerson = camper.getPediatrician().getRelationToPerson();
        pediatricianJSON.put(FIRSTNAME, firstName);
        pediatricianJSON.put(LASTNAME, lastName);
        pediatricianJSON.put(PHONE_NUM, phoneNumber);
        pediatricianJSON.put(EMAIL, email);
        pediatricianJSON.put(CONT_RELATION_TO_PERSON, relationToPerson);
        camperDetails.put(PEDIATRICIAN, pediatricianJSON);

        return camperDetails;
    }

    /*
     * creating save-users and helper getUSERJSON methods
     */
    public static void saveUsers() { // finished not tested
        UserList users = UserList.getInstance();
        ArrayList<User> userList = users.getUsers();
        JSONArray jsonUsers = new JSONArray();

        // creating JSON user objects
        for (int i = 0; i < userList.size(); i++) {
            jsonUsers.add(getUserJSON(userList.get(i)));
        }

        try (FileWriter file = new FileWriter(USER_FILE)) {
            file.write(jsonUsers.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject getUserJSON(User user) { // finished not testeed
        JSONObject userDetails = new JSONObject();
        userDetails.put(ID, user.getUUID().toString());
        userDetails.put(FIRSTNAME, user.getFirstName());
        userDetails.put(LASTNAME, user.getLastName());
        userDetails.put(DOB, convertDateToString(user.getDateOfBirth()));
        userDetails.put(HOMEADDRESS, user.getHomeAddress());
        userDetails.put(USERNAME, user.getUserLogin().getUserName());
        userDetails.put(PASSWORD, user.getUserLogin().getPassword());

        /*
         * adding arrayLists
         */
        JSONArray campersJSON = new JSONArray();
        System.out.println("Size: " + user.getCampers().size());
        for (int i = 0; i < user.getCampers().size(); i++) {
            String camperID = user.getCampers().get(i).getUUID().toString();
            campersJSON.add(camperID);
        }
        userDetails.put(USER_CAMPERS, campersJSON);
        System.out.println(campersJSON);
        return userDetails;
    }

    /*
     * create saveCounselors and getCounselorsJSON methods
     */
    public static void saveCounselors() { // finished not tested
        CounselorList counselors = CounselorList.getInstance();
        ArrayList<Counselor> counselorList = counselors.getCounselors();
        JSONArray jsonCounselors = new JSONArray();

        // creating JSON Objects
        for (int i = 0; i < counselorList.size(); i++) {
            jsonCounselors.add(getCounselorJSON(counselorList.get(i)));
        }
        try (FileWriter file = new FileWriter(COUNSELOR_FILE)) {
            file.write(jsonCounselors.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getCounselorJSON(Counselor counselor) { // finished not tested- still need to do date's
        JSONObject counselorDetails = new JSONObject();
        counselorDetails.put(ID, counselor.getUUID().toString());
        counselorDetails.put(FIRSTNAME, counselor.getFirstName());
        counselorDetails.put(LASTNAME, counselor.getLastName());
        counselorDetails.put(PHONE_NUM, counselor.getPhoneNumber());
        counselorDetails.put(EMAIL, counselor.getEmailAddress());
        counselorDetails.put(HOMEADDRESS, counselor.getHomeAddress());
        // see if this works- think it should
        java.util.Date dateOfBirth = counselor.getDateOfBirth();
        String dob = convertDateToString(dateOfBirth); // think this should work but keep an eye out
        counselorDetails.put(DOB, dob);

        counselorDetails.put(USERNAME, counselor.getUserLogin().getUserName());
        counselorDetails.put(PASSWORD, counselor.getUserLogin().getPassword());

        JSONArray contactsJSON = new JSONArray();
        for (int i = 0; i < counselor.getEmergencyContacts().size(); i++) {
            JSONObject contactObj = new JSONObject();
            String firstName = counselor.getEmergencyContacts().get(i).getFirstName();
            contactObj.put(FIRSTNAME, firstName);
            String lastName = counselor.getEmergencyContacts().get(i).getLastName();
            contactObj.put(LASTNAME, lastName);
            String phoneNumber = counselor.getEmergencyContacts().get(i).getPhoneNumber();
            contactObj.put(PHONE_NUM, phoneNumber);
            String email = counselor.getEmergencyContacts().get(i).getEmailAddress();
            contactObj.put(EMAIL, email);
            String relationtoPerson = counselor.getEmergencyContacts().get(i).getRelationToPerson();
            contactObj.put(CONT_RELATION_TO_PERSON, relationtoPerson);
            contactsJSON.add(contactObj);
        }
        counselorDetails.put(CONTACTS, contactsJSON);

        JSONObject pediatricianJSON = new JSONObject();
        String firstName = counselor.getPediatrician().getFirstName();
        String lastName = counselor.getPediatrician().getLastName();
        String phoneNumber = counselor.getPediatrician().getPhoneNumber();
        String email = counselor.getPediatrician().getEmailAddress();
        String relationToPerson = counselor.getPediatrician().getRelationToPerson();
        pediatricianJSON.put(FIRSTNAME, firstName);
        pediatricianJSON.put(LASTNAME, lastName);
        pediatricianJSON.put(PHONE_NUM, phoneNumber);
        pediatricianJSON.put(EMAIL, email);
        pediatricianJSON.put(CONT_RELATION_TO_PERSON, relationToPerson);
        counselorDetails.put(PEDIATRICIAN, pediatricianJSON);

        return counselorDetails;
    }

    /*
     * writing directors and getDirectorJSON methods
     */
    public static void saveDirectors() { // finished not tested

        DirectorList directors = DirectorList.getInstance();
        ArrayList<Director> directorList = directors.getDirectors();
        JSONArray jsonDirectors = new JSONArray();

        for (int i = 0; i < directorList.size(); i++) {
            jsonDirectors.add(getDirectorsJSON(directorList.get(i)));
        }

        try (FileWriter file = new FileWriter(DIRECTOR_FILE)) {
            file.write(jsonDirectors.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject getDirectorsJSON(Director director) {
        JSONObject directorDetails = new JSONObject();
        directorDetails.put(ID, director.getUUID().toString());
        directorDetails.put(FIRSTNAME, director.getFirstName());
        directorDetails.put(LASTNAME, director.getLastName());
        directorDetails.put(DOB, convertDateToString(director.getDateOfBirth()));
        directorDetails.put(HOMEADDRESS, director.getHomeAddress());
        directorDetails.put(USERNAME, director.getUserLogin().getUserName());
        directorDetails.put(PASSWORD, director.getUserLogin().getPassword());
        directorDetails.put(EMAIL, director.getEmail());

        JSONArray calendarArr = new JSONArray();
        for (int a = 0; a < director.getCamps().size(); a++) {
            JSONObject campDetails = new JSONObject();
            campDetails.put(NAME, director.getCamps().get(a).getName());
            campDetails.put(DESCRIPTION, director.getCamps().get(a).getDescription());
            campDetails.put(YEAR, director.getCamps().get(a).getYear());

            JSONArray masterScheduleJSON = new JSONArray();

            for (HashMap.Entry<Integer, Week> entry : director.getCamps().get(a).getMasterSchedule().entrySet()) {
                JSONObject calHashObj = new JSONObject();
                Integer num = entry.getKey();
                calHashObj.put(WEEK_NUM, num);
                // integer added

                JSONObject weekObj = new JSONObject();
                Week week = entry.getValue();
                weekObj.put(THEME, week.getTheme());
                JSONArray groupsArray = new JSONArray();
                for (int i = 0; i < week.getGroups().size(); i++) {
                    JSONObject groupObj = new JSONObject();
                    groupObj.put(GROUP_ID, week.getGroups().get(i).getUuid().toString()); // adding group id
                    groupObj.put(COUNSELOR_ID, week.getGroups().get(i).getCounselor().getUUID().toString());

                    // groupObj.put(COUNSELOR_ID,
                    // week.getGroups().get(i).getCounselor().getUUID().toString());
                    JSONArray campersArr = new JSONArray();
                    for (int j = 0; j < week.getGroups().get(i).getCampers().size(); j++) {
                        JSONObject camperObj = new JSONObject();
                        String id = week.getGroups().get(i).getCampers().get(j).getUUID().toString();
                        camperObj.put(ID, id);
                        campersArr.add(camperObj);
                    }
                    groupObj.put(GROUP_CAMPERS, campersArr);

                    JSONArray groupSchedule = new JSONArray();
                    for (HashMap.Entry<DayOfWeek, ArrayList<Activity>> groupEntry : week.getGroups().get(i)
                            .getSchedule().entrySet()) {
                        JSONObject scheduleObj = new JSONObject();
                        String day = groupEntry.getKey().toString();
                        scheduleObj.put(DAY_OF_WEEK, day);

                        JSONArray activitiesJSON = new JSONArray();
                        ArrayList<Activity> dailyActivities = groupEntry.getValue(); // if errors, assign at the end,
                                                                                     // might be null
                        for (int k = 0; k < dailyActivities.size(); k++) {
                            JSONObject dailyActivitiesObj = new JSONObject();
                            dailyActivitiesObj.put(NAME, dailyActivities.get(k).getName());
                            dailyActivitiesObj.put(LOCATION, dailyActivities.get(k).getLocation());
                            dailyActivitiesObj.put(DESCRIPTION, dailyActivities.get(k).getDescription());
                            activitiesJSON.add(dailyActivitiesObj);
                        }
                        scheduleObj.put(DAILY_ACTIVITIES, activitiesJSON);
                        groupSchedule.add(scheduleObj);

                    }
                    groupObj.put(GROUP_SCHEDULE, groupSchedule);
                    groupsArray.add(groupObj);
                }
                weekObj.put(GROUPS, groupsArray);
                // groups added
                JSONArray weekCounselorArr = new JSONArray();
                for (int i = 0; i < week.getCounselors().size(); i++) {
                    JSONObject counselorObj = new JSONObject();
                    counselorObj.put(ID, week.getCounselors().get(i).getUUID().toString());
                    weekCounselorArr.add(counselorObj);
                }
                weekObj.put(WEEK_COUNSELORS, weekCounselorArr);
                // week counselors added

                JSONArray weekCampersArr = new JSONArray();
                for (int i = 0; i < week.getCampers().size(); i++) {
                    JSONObject camperObj = new JSONObject();
                    camperObj.put(ID, week.getCampers().get(i).getUUID().toString());
                    weekCampersArr.add(camperObj);
                }
                weekObj.put(WEEK_CAMPERS, weekCampersArr);
                // week campers added

                weekObj.put(START_DATE, convertDateToString(week.getStartDate()));
                weekObj.put(END_DATE, convertDateToString(week.getEndDate()));
                Boolean isFullBool = week.isFull();
                String isFull = Boolean.toString(isFullBool);
                weekObj.put(ISFULL, isFull);
                // added start, end, is full

                calHashObj.put(WEEK, weekObj);
                // added weeks
                masterScheduleJSON.add(calHashObj);
                campDetails.put(CALENDAR_HASH, masterScheduleJSON);

                JSONArray allActivitiesArr = new JSONArray();
                for (int i = 0; i < director.getCamps().get(a).getActivitiesArrayList().size(); i++) {
                    JSONObject activityObj = (JSONObject) new JSONObject();
                    activityObj.put(NAME, director.getCamps().get(a).getActivitiesArrayList().get(i).getName());
                    activityObj.put(LOCATION, director.getCamps().get(a).getActivitiesArrayList().get(i).getLocation());
                    activityObj.put(DESCRIPTION,
                            director.getCamps().get(a).getActivitiesArrayList().get(i).getDescription());
                    allActivitiesArr.add(activityObj);
                }
                campDetails.put(ALL_ACTIVITIES, allActivitiesArr);
                // added activities list

            }
            calendarArr.add(campDetails);
        }
        directorDetails.put(CALENDAR, calendarArr);

        return directorDetails;
    }

    /*
     * writing Camp and getCampJSON methods
     * create campList
     */
    public static void saveCamps() {

        CampList camps = CampList.getInstance();
        ArrayList<Camp> campList = camps.getCamps();
        JSONArray jsonCamps = new JSONArray();

        for (int i = 0; i < campList.size(); i++) {
            jsonCamps.add(getCampJSON(campList.get(i)));
        }
        try (FileWriter file = new FileWriter(CAMP_FILE)) {
            file.write(jsonCamps.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Object getCampJSON(Camp camp) {
        JSONObject campDetails = new JSONObject();
        campDetails.put(NAME, camp.getName());
        campDetails.put(DESCRIPTION, camp.getDescription());
        campDetails.put(YEAR, camp.getYear());

        JSONArray masterScheduleJSON = new JSONArray();
        for (HashMap.Entry<Integer, Week> entry : camp.getMasterSchedule().entrySet()) {
            JSONObject calHashObj = new JSONObject();
            Integer num = entry.getKey();
            calHashObj.put(WEEK_NUM, num);
            // integer added

            JSONObject weekObj = new JSONObject();
            Week week = entry.getValue();
            weekObj.put(THEME, week.getTheme());
            JSONArray groupsArray = new JSONArray();
            for (int i = 0; i < week.getGroups().size(); i++) {
                JSONObject groupObj = new JSONObject();
                groupObj.put(GROUP_ID, week.getGroups().get(i).getUuid().toString()); // adding group id
                groupObj.put(COUNSELOR_ID, week.getGroups().get(i).getCounselor().getUUID().toString());

                JSONArray campersArr = new JSONArray();
                for (int j = 0; j < week.getGroups().get(i).getCampers().size(); j++) {
                    JSONObject camperObj = new JSONObject();
                    String id = week.getGroups().get(i).getCampers().get(j).getUUID().toString();
                    camperObj.put(ID, id);
                    campersArr.add(camperObj);
                }
                groupObj.put(GROUP_CAMPERS, campersArr);

                JSONArray groupSchedule = new JSONArray();
                for (HashMap.Entry<DayOfWeek, ArrayList<Activity>> groupEntry : week.getGroups().get(i).getSchedule()
                        .entrySet()) {
                    JSONObject scheduleObj = new JSONObject();
                    String day = groupEntry.getKey().toString();
                    scheduleObj.put(DAY_OF_WEEK, day);

                    JSONArray activitiesJSON = new JSONArray();
                    ArrayList<Activity> dailyActivities = groupEntry.getValue();
                    for (int k = 0; k < dailyActivities.size(); k++) {
                        JSONObject dailyActivitiesObj = new JSONObject();
                        dailyActivitiesObj.put(NAME, dailyActivities.get(k).getName());
                        dailyActivitiesObj.put(LOCATION, dailyActivities.get(k).getLocation());
                        dailyActivitiesObj.put(DESCRIPTION, dailyActivities.get(k).getDescription());
                        activitiesJSON.add(dailyActivitiesObj);
                    }
                    scheduleObj.put(DAILY_ACTIVITIES, activitiesJSON);
                    groupSchedule.add(scheduleObj);

                }
                groupObj.put(GROUP_SCHEDULE, groupSchedule);
                groupsArray.add(groupObj);
            }
            weekObj.put(GROUPS, groupsArray);
            // groups added
            JSONArray weekCounselorArr = new JSONArray();
            for (int i = 0; i < week.getCounselors().size(); i++) {
                JSONObject counselorObj = new JSONObject();
                counselorObj.put(ID, week.getCounselors().get(i).getUUID().toString());
                weekCounselorArr.add(counselorObj);
            }
            weekObj.put(WEEK_COUNSELORS, weekCounselorArr);
            // week counselors added

            JSONArray weekCampersArr = new JSONArray();
            for (int i = 0; i < week.getCampers().size(); i++) {
                JSONObject camperObj = new JSONObject();
                camperObj.put(ID, week.getCampers().get(i).getUUID().toString());
                weekCampersArr.add(camperObj);
            }
            weekObj.put(WEEK_CAMPERS, weekCampersArr);
            // week campers added

            weekObj.put(START_DATE, convertDateToString(week.getStartDate()));
            weekObj.put(END_DATE, convertDateToString(week.getEndDate()));
            Boolean isFullBool = week.isFull();
            String isFull = Boolean.toString(isFullBool);
            weekObj.put(ISFULL, isFull);
            // added start, end, is full

            calHashObj.put(WEEK, weekObj);
            // added weeks
            masterScheduleJSON.add(calHashObj);
            campDetails.put(CALENDAR_HASH, masterScheduleJSON);

            JSONArray allActivitiesArr = new JSONArray();
            for (int i = 0; i < camp.getActivitiesArrayList().size(); i++) {
                JSONObject activityObj = (JSONObject) new JSONObject();
                activityObj.put(NAME, camp.getActivitiesArrayList().get(i).getName());
                activityObj.put(LOCATION, camp.getActivitiesArrayList().get(i).getLocation());
                activityObj.put(DESCRIPTION, camp.getActivitiesArrayList().get(i).getDescription());
                allActivitiesArr.add(activityObj);
            }
            campDetails.put(ALL_ACTIVITIES, allActivitiesArr);
            // added activities list

        }
        return campDetails;
    }

    // TODO complete this part by Saturday night

    public static void writeGroupFiles(UUID id) {
        // get the group, write the schedule, add it to a txt file
        // should print schedule, allergy information of all campers

        // CounselorList c = CounselorList.getInstance();
        // Counselor coun = c.getCounselorByUUID(id);
        // System.out.println("Counselor name "+coun.getFirstName());

        CampList campList = CampList.getInstance(); // getting group
        Group group = campList.getGroupByUUID(id);
        System.out.println("ID: " + id);
        System.out.println(group);
        // for (HashMap.Entry<DayOfWeek, ArrayList<Activity>> entry :
        // group.getSchedule().entrySet()) {
        // ArrayList<Activity> activities = entry.getValue();
        // for (int i = 0; i < activities.size(); i++) {
        // System.out.println("activity: " + activities.get(i));
        // }
        // }
        // above test works- prints all activities in JSON

        try (FileWriter file = new FileWriter("schedules/" + group.getCounselor().getFirstName() + "'s Group")) {
            // for (HashMap.Entry<DayOfWeek, ArrayList<Activity>> entry :
            // group.getSchedule().entrySet()) {
            // ArrayList<Activity> activities = entry.getValue();
            // for (int i = 0; i < activities.size(); i++) {
            // // System.out.println("activity: " + activities.get(i));\
            // String temp = activities.get(i).toString();
            // file.write(temp);
            // }
            // }
            // new txt file
            file.write(group.printSchedule());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String convertDateToString(Date dateOfBirth) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String newDate = df.format(dateOfBirth);
        return newDate;
    }
}
