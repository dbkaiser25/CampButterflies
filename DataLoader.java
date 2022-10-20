import java.io.FileReader; 
import java.util.ArrayList;
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
                String sex = (String)camperJSON.get(GENDER); 
                JSONArray allergiesJson = (JSONArray)camperJSON.get(ALLERGIES);
                /*
                 * test this to see if it's correct; might be wrong
                 */
                ArrayList<String> allergies = new ArrayList<String>();
                for(int j=0;i<allergiesJson.size();j++) {
                    String allergy = (String)allergiesJson.get(j);
                    allergies.add(allergy);
                } 
                //allergies = (ArrayList<String>)camperJSON.get(ALLERGIES);

                ArrayList<Medication> medications = new ArrayList<Medication>(); 
                for(int j=0;i<medications.size();j++) {
                    String dose = (String)camperJSON.get(MED_DOSAGE);
                    String type = (String)camperJSON.get(MED_TYPE);
                    String time = (String)camperJSON.get(MED_TIME);
                    Medication medication = new Medication (dose, type, time); 
                    medications.add(medication);
                }
                //medications = (ArrayList<Medication>)camperJSON.get(MEDICALINFO);  
                ArrayList<Contact> contacts = new ArrayList<Contact>(); 
                for(int j=0;j>contacts.size();j++) {
                    String contFirstName = (String)camperJSON.get(CONT_FIRST_NAME);
                    String contLastName = (String)camperJSON.get(CONT_LAST_NAME); 
                    String contPhoneNum = (String)camperJSON.get(CONT_PHONE_NUM);
                    String contEmail = (String)camperJSON.get(CONT_EMAIL); 
                    String contRelationToPerson = (String)camperJSON.get(CONT_RELATION_TO_PERSON);
                    Contact contact = new Contact(contFirstName, contLastName, contPhoneNum, contEmail, contRelationToPerson); 
                    contacts.add(contact); 
                }
                //contacts = (ArrayList<Contact>)camperJSON.get(CONTACTS); 
                Contact pediatrician = new Contact(); 
                
                campers.add(new Camper(id, firstName, lastName, dateOfBirth, homeAddress, sex, medications, allergies, contacts, pediatrician)); 
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
                ArrayList<Camper> camperslist = new ArrayList<Camper>(); 
                camperslist = (ArrayList<Camper>)userJSON.get(CAMPERS); 

                JSONArray campers = new JSONArray(); 


                /*
                 * Loop through json array of camper uuids
                 * Camper camper = CamperList.getInstance().getCamperByUUID(id);
                 */
                for(int j=0;j<campers.size();j++)
                {
                    Camper camper = CamperList.getInstance().getCamperByUUID(ID); 
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
            FileReader reader = new FileReader(USER_FILE); 
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
                
                counselors.add(new Counselor(id, firstName, lastName, dateOfBirth, homeAddress, phoneNumber, emailAddress, contacts, pediatrician, counselorLogin));

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
            FileReader reader = new FileReader(USER_FILE); 
            JSONParser parser = new JSONParser(); 
            JSONArray usersJSON = (JSONArray)new JSONParser().parse(reader);

            for(int i=0;i<usersJSON.size();i++) {
                
            }
        }
        catch (Exception e) {
            e.printStackTrace(); 
        }
    }

    public static ArrayList<Calendar> loadCamps() {
        ArrayList<User> users = new ArrayList<User>(); 

        try {
            FileReader reader = new FileReader(USER_FILE); 
            JSONParser parser = new JSONParser(); 
            JSONArray usersJSON = (JSONArray)new JSONParser().parse(reader);

            for(int i=0;i<usersJSON.size();i++) {
                
            }
        }
        catch (Exception e) {
            e.printStackTrace(); 
        }
    }
}
