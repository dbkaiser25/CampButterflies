import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {

    public static void main(String[] args) {
        Date dob = new Date(06, 24, 2003);
        convertDateToString(dob);

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

    public static JSONObject getCamperJSON(Camper camper) { // finished not tested
        JSONObject camperDetails = new JSONObject();
        camperDetails.put(ID, camper.getUUID().toString());
        camperDetails.put(FIRSTNAME, camper.getFirstName());
        camperDetails.put(LASTNAME, camper.getLastName());
        camperDetails.put(HOMEADDRESS, camper.getHomeAddress());
        camperDetails.put(DOB, camper.getDateOfBirth().toString()); // TODO fix DOB
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
            JSONObject contactObj = (JSONObject) contactsJSON.get(i);
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
            contactsJSON.add(new Contact(firstName, lastName, phoneNumber, email, relationtoPerson));
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
        userDetails.put(ID, user.getUuid().toString());
        userDetails.put(FIRSTNAME, user.getFirstName());
        userDetails.put(LASTNAME, user.getLastName());
        userDetails.put(DOB, user.getDateOfBirth());
        userDetails.put(HOMEADDRESS, user.getHomeAddress());
        userDetails.put(USERNAME, user.getUserLogin().getUserName());
        userDetails.put(PASSWORD, user.getUserLogin().getPassword());

        /*
         * adding arrayLists
         */
        JSONArray campersJSON = new JSONArray();
        for (int i = 0; i < user.getCampers().size(); i++) {
            UUID camperID = user.getCampers().get(i).getUUID();
            campersJSON.add(camperID.toString());
        }
        userDetails.put(CAMPERS, campersJSON);
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
        String dob = counselor.getDateOfBirth().toString();
        counselorDetails.put(DOB, dob);

        counselorDetails.put(USERNAME, counselor.getUserLogin().getUserName());
        counselorDetails.put(PASSWORD, counselor.getUserLogin().getPassword());

        JSONArray contactsJSON = new JSONArray();
        for (int i = 0; i < counselor.getEmergencyContacts().size(); i++) {
            JSONObject contactObj = (JSONObject) contactsJSON.get(i);
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

        // see if this works
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
        directorDetails.put(DOB, director.getDateOfBirth());
        directorDetails.put(HOMEADDRESS, director.getHomeAddress());
        directorDetails.put(USERNAME, director.getUserLogin().getUserName());
        directorDetails.put(PASSWORD, director.getUserLogin().getPassword());

        return directorDetails;
    }

    /*
     * writing Camp and getCampJSON methods
     * create campList
     */
    public static void saveCamp() {

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

        JSONArray masterScheduleJSON = new JSONArray();
        for (int i = 0; i < camp.getMasterSchedule().size(); i++) {
            // include week number, week, week counselors, week campers, start date, end
            // date, and is full
            JSONObject msJSON = (JSONObject) masterScheduleJSON.get(i);
            // ask about this
            msJSON.put(WEEK_NUM, camp.getMasterSchedule().get(WEEK_NUM));

            JSONObject weekObj = new JSONObject();
            weekObj.put(THEME, camp.getWeek(i).getTheme());
            JSONArray groupArray = new JSONArray();
            for (int j = 0; j < camp.getWeek(i).getGroups().size(); j++) {
                JSONObject groupObj = (JSONObject) groupArray.get(j);
                // see if these work for ID
                groupObj.put(GROUP_ID, camp.getWeek(i).getGroups().get(j).getUuid().toString());
                groupObj.put(COUNSELOR_ID, camp.getWeek(i).getGroups().get(j).getCounselor().getUUID().toString());

                // groupObj.put(COUNSELOR_ID,
                // camp.getWeek(i).getCounselors().get(j).getUUID().toString());

                JSONArray campersArray = new JSONArray();
                for (int k = 0; k < camp.getWeek(i).getGroups().get(j).getCampers().size(); k++) {
                    JSONObject camperObj = (JSONObject) campersArray.get(k);
                    camperObj.put(ID, camp.getWeek(i).getGroups().get(j).getCampers().get(k).getUUID().toString()); // see
                                                                                                                    // if
                                                                                                                    // this
                                                                                                                    // is
                                                                                                                    // right
                    campersArray.add(camperObj);
                }
                groupObj.put(GROUP_CAMPERS, campersArray);
                JSONArray groupSchedule = new JSONArray();
                for (int k = 0; k < camp.getWeek(i).getGroups().get(j).getSchedule().size(); k++) {
                    JSONObject scheduleObj = (JSONObject) groupSchedule.get(k);
                    scheduleObj.put(DAY_OF_WEEK, camp.getWeek(i).getGroups().get(j).getSchedule().get(k));
                    JSONArray dailyActivitiesArray = new JSONArray();
                    for (int l = 0; l < camp.getWeek(i).getGroups().get(j).getSchedule().get(k).size(); l++) {
                        JSONObject activityObj = (JSONObject) dailyActivitiesArray.get(l);
                        activityObj.put(NAME, camp.getActivities()); // see if this will work- don't know how to get it
                                                                     // to return one single activity
                    }
                }
                groupArray.add(groupObj);
            }
            weekObj.put(GROUPS, groupArray);
            msJSON.put(WEEK, weekObj);

        }

        // campDetails.put(CALENDAR_HASH, camp.getMasterSchedule());

        // campDetails.put(ALL_ACTIVITIES, );
        return campDetails;
    }

    public static String convertDateToString(Date date) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String newDate = df.format(date);
        return newDate;
    }
}
