import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {

    public static void saveCampers() {
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

    public static JSONObject getCamperJSON(Camper camper) {
        JSONObject camperDetails = new JSONObject();
        camperDetails.put(ID, camper.getUUID().toString());
        camperDetails.put(FIRSTNAME, camper.getFirstName());
        camperDetails.put(LASTNAME, camper.getLastName());
        camperDetails.put(HOMEADDRESS, camper.getHomeAddress());
        camperDetails.put(DOB, camper.getDateOfBirth()); // TODO fix DOB
        camperDetails.put(GENDER, camper.getSex()); // TODO convert String to enum

        camperDetails.put(MEDICATIONS, camper.getMedications());
        camperDetails.put(ALLERGIES, camper.getAllergies());
        camperDetails.put(CONTACTS, camper.getEmergencyContacts());
        camperDetails.put(PEDIATRICIAN, camper.getPediatrician());

        return camperDetails;
    }

    /*
     * creating save-users and helper getUSERJSON methods
     */
    public static void saveUsers() {
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

    private static JSONObject getUserJSON(User user) {
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
    public static void saveCounselors() {
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

    public static JSONObject getCounselorJSON(Counselor counselor) {
        JSONObject counselorDetails = new JSONObject();
        counselorDetails.put(ID, counselor.getUUID().toString());
        counselorDetails.put(FIRSTNAME, counselor.getFirstName());
        counselorDetails.put(LASTNAME, counselor.getLastName());
        counselorDetails.put(PHONE_NUM, counselor.getPhoneNumber());
        counselorDetails.put(EMAIL, counselor.getEmailAddress());
        counselorDetails.put(HOMEADDRESS, counselor.getHomeAddress());
        counselorDetails.put(DOB, counselor.getDateOfBirth()); // store as date in object, store as String in JSON
        // look up how to convert String to a date

        counselorDetails.put(USERNAME, counselor.getUserLogin().getUserName());
        counselorDetails.put(PASSWORD, counselor.getUserLogin().getPassword());

        JSONArray contactsJSON = new JSONArray();
        for (int i = 0; i < counselor.getEmergencyContacts().size(); i++) {
            String firstName = counselor.getEmergencyContacts().get(i).getFirstName();
            counselorDetails.put(FIRSTNAME, firstName);
            String lastName = counselor.getEmergencyContacts().get(i).getLastName();
            counselorDetails.put(LASTNAME, lastName);
            String phoneNumber = counselor.getEmergencyContacts().get(i).getPhoneNumber();
            counselorDetails.put(PHONE_NUM, phoneNumber);
            String email = counselor.getEmergencyContacts().get(i).getEmailAddress();
            counselorDetails.put(EMAIL, email);
            String relationtoPerson = counselor.getEmergencyContacts().get(i).getRelationToPerson();
            counselorDetails.put(CONT_RELATION_TO_PERSON, relationtoPerson);
            contactsJSON.add(new Contact(firstName, lastName, phoneNumber, email, relationtoPerson));
        }
        counselorDetails.put(CONTACTS, contactsJSON);

        // see if this works

        counselorDetails.put(PEDIATRICIAN, counselor.getPediatricion());
        String firstName = counselor.getPediatricion().getFirstName();
        String lastName = counselor.getPediatricion().getLastName();
        String phoneNumber = counselor.getPediatricion().getPhoneNumber();
        String email = counselor.getPediatricion().getEmailAddress();
        String relationToPerson = counselor.getPediatricion().getRelationToPerson();
        counselorDetails.put(FIRSTNAME, firstName);
        counselorDetails.put(LASTNAME, lastName);
        counselorDetails.put(PHONE_NUM, phoneNumber);
        counselorDetails.put(EMAIL, email);
        counselorDetails.put(CONT_RELATION_TO_PERSON, relationToPerson);

        return counselorDetails;
    }

    /*
     * writing directors and getDirectorJSON methods
     */
    public static void saveDirectors() {

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
        Array

        try (FileWriter file = new FileWriter(CAMP_FILE)) {
            file.write(jsonCamp.toJSONString()); 
            file.flush(); 
        }
        catch (IOException e){ 
            e.printStackTrace();
        }
    }
}
