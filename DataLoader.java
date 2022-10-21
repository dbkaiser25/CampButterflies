import java.io.FileReader; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser; 

public class DataLoader extends DataConstants{
    
    public static ArrayList<Camper> loadCampers() { 
        ArrayList<Camper> campers = new ArrayList<Camper>();
        
        try {
            FileReader reader = new FileReader(CAMPER_FILE); 
            JSONParser parser = new JSONParser(); 
            JSONArray campersJSON = (JSONArray)new JSONParser().parse(reader); 

            for(int i=0;i<campersJSON.size();i++) {
                JSONObject camperJSON = (JSONObject)campersJSON.get(i); 
                UUID id = UUID.fromString((String)camperJSON.get(ID)); 
                String firstName = (String)camperJSON.get(FIRSTNAME);
                String lastName = (String)camperJSON.get(LASTNAME); 
                String dateOfBirth = (String)camperJSON.get(DOB); 
                String homeAddress = (String)camperJSON.get(HOMEADDRESS); 
                Sex sex = (Sex)camperJSON.get(GENDER); 
                JSONArray allergiesJson = (JSONArray)camperJSON.get(ALLERGIES);
                ArrayList<String> allergies = new ArrayList<String>();
                for(int j=0;i<allergiesJson.size();j++) {
                    String allergy = (String)allergiesJson.get(j);
                    allergies.add(allergy);
                } 
                //allergies = (ArrayList<String>)camperJSON.get(ALLERGIES);
                //keep an eye on this part, might be wrong 
                JSONArray medicationsJSON =(JSONArray)camperJSON.get(MEDICATIONS);
                ArrayList<Medication> medications = new ArrayList<Medication>(); 
                for(int j=0;i<medicationsJSON.size();j++) {
                    Medication medication = (Medication)medicationsJSON.get(j);
                    medications.add(medication);
                     // ArrayList<String> medInfo = new ArrayList<String>(); 
                    // String dose = (String)camperJSON.get(DOSAGE);
                    // String type = (String)camperJSON.get(TYPE);
                    // String time = (String)camperJSON.get(TIME);
                    // medInfo.add(dose); 
                    // medInfo.add(type); 
                    // medInfo.add(time); 
                }
                JSONArray contactsJSON = (JSONArray)camperJSON.get(CONTACTS); 
                ArrayList<Contact> contacts = new ArrayList<Contact>(); 
                for(int j=0;j>contacts.size();j++) {
                    // String contFirstName = (String)camperJSON.get(FIRSTNAME);
                    // String contLastName = (String)camperJSON.get(LASTNAME); 
                    // String contPhoneNum = (String)camperJSON.get(PHONE_NUM);
                    // String contEmail = (String)camperJSON.get(EMAIL); 
                    // String contRelationToPerson = (String)camperJSON.get(CONT_RELATION_TO_PERSON);
                    // Contact contact = new Contact(contFirstName, contLastName, contPhoneNum, contEmail, contRelationToPerson); 
                    // contacts.add(contact); 
                    Contact contact = (Contact)contactsJSON.get(j);
                    contacts.add(contact); 
                }
                //contacts = (ArrayList<Contact>)camperJSON.get(CONTACTS); 
                Contact pediatrician = (Contact)camperJSON.get(PEDIATRICIAN);

                campers.add(new Camper(id, firstName, lastName, homeAddress, dateOfBirth, sex, medications, allergies, contacts, pediatrician)); 
            }
            return campers; 
        }
        catch (Exception e) {
            e.printStackTrace(); 
        }
        return null; 
    }


    public static ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<User>(); 

        try {
            FileReader reader = new FileReader(USER_FILE); 
            JSONParser parser = new JSONParser(); 
            JSONArray usersJSON = (JSONArray)new JSONParser().parse(reader);

            for(int i=0;i<usersJSON.size();i++) {
                JSONObject userJSON = (JSONObject)usersJSON.get(i); 
                UUID id = UUID.fromString((String)userJSON.get(ID)); 
                String firstName = (String)userJSON.get(FIRSTNAME); 
                String lastName = (String)userJSON.get(LASTNAME); 
                String dateOfBirth = (String)userJSON.get(DOB); 
                String homeAddress = (String)userJSON.get(HOMEADDRESS); 
                String userName = (String)userJSON.get(USERNAME); 
                String password = (String)userJSON.get(PASSWORD); 
                LoginInfo userLogin = new LoginInfo(userName, password); 
                /*
                 * arrayList issue
                 */
                ArrayList<Camper> campersList = new ArrayList<Camper>();
                JSONArray campers = (JSONArray)userJSON.get(CAMPERS);
                /*
                 * Loop through json array of camper uuids
                 * Camper camper = CamperList.getInstance().getCamperByUUID(id);
                 */
                for(int j=0;j<campers.size();j++)
                {
                    UUID camperID = UUID.fromString((String)campers.get(j));
                    Camper camper = CamperList.getInstance().getCamperByUUID(camperID); 
                    campersList.add(camper);
                }

                users.add(new User(id, firstName, lastName, dateOfBirth, homeAddress, userLogin, campers));
            }
            return users; 
        }
        catch (Exception e) {
            e.printStackTrace(); 
        }
        return null; 
    }

    public static ArrayList<Counselor> loadCounselors() {
        ArrayList<Counselor> counselors = new ArrayList<Counselor>(); 

        try {
            FileReader reader = new FileReader(COUNSELOR_FILE); 
            JSONParser parser = new JSONParser(); 
            JSONArray counselorsJSON = (JSONArray)new JSONParser().parse(reader);

            for(int i=0;i<counselorsJSON.size();i++) {
                JSONObject counselorJSON = (JSONObject)counselorsJSON.get(i); 
                UUID id = UUID.fromString((String)counselorJSON.get(ID));
                String firstName = (String)counselorJSON.get(FIRSTNAME); 
                String lastName = (String)counselorJSON.get(LASTNAME); 
                String dateOfBirth = (String)counselorJSON.get(DOB); 
                String homeAddress = (String)counselorJSON.get(HOMEADDRESS); 
                String phoneNumber = (String)counselorJSON.get(PHONE_NUM); 
                String emailAddress = (String)counselorJSON.get(EMAIL); 
                String userName = (String)counselorJSON.get(USERNAME); 
                String password = (String)counselorJSON.get(PASSWORD);
                LoginInfo counselorLogin = new LoginInfo(userName, password); 
                /*
                 * arraylists
                 */
                ArrayList<Contact> contacts = new ArrayList<Contact>(); 
                contacts = (ArrayList<Contact>)counselorJSON.get(CONTACTS); 
                Contact pediatrician = (Contact)counselorJSON.get(PEDIATRICIAN); 
                /*
                 * camperList: getCamperByUUID(UUID)
                 * arrayLists: make a JSON array
                 * make a getCurrentWeek method in facade 
                 */
                
                counselors.add(new Counselor(id, firstName, lastName, phoneNumber, emailAddress, homeAddress, dateOfBirth, contacts, pediatrician, counselorLogin));

            }
            return counselors; 
        }
        catch (Exception e) {
            e.printStackTrace(); 
        }
        return null; 
    }

    public static ArrayList<Director> loadDirectors() {
        ArrayList<Director> directors = new ArrayList<Director>(); 

        try {
            FileReader reader = new FileReader(DIRECTOR_FILE); 
            JSONParser parser = new JSONParser(); 
            JSONArray directorsJSON = (JSONArray)new JSONParser().parse(reader);

            for(int i=0;i<directorsJSON.size();i++) {
                JSONObject directorJSON = (JSONObject)directorsJSON.get(i); 
                UUID id = (UUID)directorJSON.get(ID); 
                String firstName = (String)directorJSON.get(FIRSTNAME); 
                String lastName = (String)directorJSON.get(LASTNAME); 
                String dateOfBirth = (String)directorJSON.get(DOB); 
                String homeAddress = (String)directorJSON.get(HOMEADDRESS); 
                String userName = (String)directorJSON.get(USERNAME); 
                String password = (String)directorJSON.get(PASSWORD);
                LoginInfo directorLogin = new LoginInfo(userName, password);  

                directors.add(new Director(id, firstName, lastName, dateOfBirth, homeAddress, directorLogin));
            }
            return directors; 
        }
        catch (Exception e) {
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
            JSONParser parser = new JSONParser(); 
            JSONArray campsJSON = (JSONArray)new JSONParser().parse(reader);

            for(int i=0;i<campsJSON.size();i++) {
                JSONObject campJSON = (JSONObject)campsJSON.get(i); 
                String name = (String)campJSON.get(NAME); 
                String description = (String)campJSON.get(DESCRIPTION); 
                HashMap<Integer, Week> masterSchedule = (HashMap<Integer, Week>)campJSON.get(WEEK);
                ArrayList<Activity> activities = (ArrayList<Activity>)campJSON.get(DAILY_ACTIVITIES); 

                camps.add(new Camp(name, description, masterSchedule, activities));
            }
            return camps; 
        }
        catch (Exception e) {
            e.printStackTrace(); 
        }
        return null; 
    }
}
