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
                JSONArray medicationsJSON =(JSONArray)camperJSON.get(MEDICATIONS);
                ArrayList<Medication> medications = new ArrayList<Medication>(); 
                for(int j=0;i<medicationsJSON.size();j++) {
                    JSONObject medJSON = (JSONObject)medicationsJSON.get(j);
                    String dosage = (String)medJSON.get(DOSAGE); 
                    String type = (String)medJSON.get(TYPE);
                    String time = (String)medJSON.get(TIME); 
                    medications.add(new Medication(type, dosage, time));
                }
                JSONArray contactsJSON = (JSONArray)camperJSON.get(CONTACTS); 
                ArrayList<Contact> contacts = new ArrayList<Contact>(); 
                for(int j=0;j>contacts.size();j++) {
                    JSONObject contactJSON = (JSONObject)contactsJSON.get(j); 
                    String contactFirstName = (String)contactJSON.get(FIRSTNAME); 
                    String contactLastName = (String)contactJSON.get(LASTNAME); 
                    String phoneNumber = (String)contactJSON.get(PHONE_NUM); 
                    String emailAddress = (String)contactJSON.get(EMAIL); 
                    String relationToPerson = (String)contactJSON.get(CONT_RELATION_TO_PERSON);
                    contacts.add(new Contact(contactFirstName, contactLastName, phoneNumber, emailAddress, relationToPerson)); 
                }
                JSONArray pediatricianJSON = (JSONArray)camperJSON.get(PEDIATRICIAN); 
                Contact pediatrician = new Contact(); //see if this works 
                for(int j=0;j<pediatricianJSON.size();j++) {
                    JSONObject pedJSON = (JSONObject)pediatricianJSON.get(j); 
                    String pedFirstName = (String)pedJSON.get(FIRSTNAME); 
                    String pedLastName = (String)pedJSON.get(LASTNAME); 
                    String phoneNumber = (String)pedJSON.get(PHONE_NUM); 
                    String email = (String)pedJSON.get(EMAIL); 
                    String relationToPerson = (String)pedJSON.get(CONT_RELATION_TO_PERSON); 
                    pediatrician = new Contact(pedFirstName, pedLastName, phoneNumber, email, relationToPerson); 
                }
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
                 * camperList: getCamperByUUID(UUID)
                 * arrayLists: make a JSON array
                 * make a getCurrentWeek method in facade 
                 */
                JSONArray contactsJSON = (JSONArray)counselorJSON.get(CONTACTS); 
                ArrayList<Contact> contacts = new ArrayList<Contact>(); 
                for(int j=0;j>contacts.size();j++) {
                    JSONObject contactJSON = (JSONObject)contactsJSON.get(j); 
                    String contactFirstName = (String)contactJSON.get(FIRSTNAME); 
                    String contactLastName = (String)contactJSON.get(LASTNAME); 
                    String contactPhoneNumber = (String)contactJSON.get(PHONE_NUM); 
                    String contactEmailAddress = (String)contactJSON.get(EMAIL); 
                    String relationToPerson = (String)contactJSON.get(CONT_RELATION_TO_PERSON);
                    contacts.add(new Contact(contactFirstName, contactLastName, contactPhoneNumber, contactEmailAddress, relationToPerson)); 
                }
                JSONArray pediatricianJSON = (JSONArray)counselorJSON.get(PEDIATRICIAN); 
                Contact pediatrician = new Contact(); //see if this works 
                for(int j=0;j<pediatricianJSON.size();j++) {
                    JSONObject pedJSON = (JSONObject)pediatricianJSON.get(j); 
                    String pedFirstName = (String)pedJSON.get(FIRSTNAME); 
                    String pedLastName = (String)pedJSON.get(LASTNAME); 
                    String pedPhoneNumber = (String)pedJSON.get(PHONE_NUM); 
                    String email = (String)pedJSON.get(EMAIL); 
                    String relationToPerson = (String)pedJSON.get(CONT_RELATION_TO_PERSON); 
                    pediatrician = new Contact(pedFirstName, pedLastName, pedPhoneNumber, email, relationToPerson); 
                }
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
                //how the fuck do I store a hashmap in JSON 
                HashMap<Integer, Week> masterSchedule = (HashMap<Integer, Week>)campJSON.get(WEEK);


                JSONArray activitiesJSON = (JSONArray)campJSON.get(DAILY_ACTIVITIES); 
                ArrayList<Activity> activities = new ArrayList<Activity>(); 
                for(int j=0;j<activities.size();j++) {
                    JSONObject activityJSON = (JSONObject)activitiesJSON.get(j); 
                    String activityName = (String)activityJSON.get(NAME); 
                    String location = (String)activityJSON.get(LOCATION); 
                    String activityDescription = (String)activityJSON.get(DESCRIPTION); 
                    activities.add(new Activity(activityName, location, activityDescription)); 
                }

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
