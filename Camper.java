import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * A class that defines a camper for the camp
 * 
 * @author dbkaiser
 */
public class Camper extends Person {
    private Sex sex;
    private ArrayList<Medication> medications = new ArrayList<Medication>();
    private ArrayList<String> allergies = new ArrayList<String>();
    private ArrayList<Contact> emergencyContacts = new ArrayList<Contact>();
    private Contact pediatrician;
    private Date dateOfBirth;
    private ArrayList<Integer> weeks;

    /**
     * The following attributes are needed to create an individual camper
     * 
     * @param sex               A string for their sex
     * @param medicalInfo       A string for their medical info
     * @param emergencyContacts A list of the camper's emergency contacts
     * @param pediatrician      A contact for the camper's pediatrician
     *                          TODO Figure out the constructors for emergency
     *                          contacts and maybe pediatrician
     */
    public Camper(String firstName, String lastName, String homeAddress, Date dateOfBirth,
            Sex sex, ArrayList<Medication> medications, ArrayList<String> allergies,
            ArrayList<Contact> emergencyContacts, Contact pediatrician) {
        super(firstName, lastName, dateOfBirth, homeAddress);
        this.sex = sex;
        this.medications = medications;
        this.allergies = allergies;
        this.emergencyContacts = emergencyContacts;
        this.pediatrician = pediatrician;
        this.dateOfBirth = dateOfBirth;
        weeks = new ArrayList<>();
    }

    // constructor with UUID
    public Camper(UUID uuid, String firstName, String lastName, String homeAddress, Date dateOfBirth,
            Sex sex, ArrayList<Medication> medications, ArrayList<String> allergies,
            ArrayList<Contact> emergencyContacts, Contact pediatrician) {
        super(uuid, firstName, lastName, dateOfBirth, homeAddress);
        this.sex = sex;
        this.medications = medications;
        this.allergies = allergies;
        this.emergencyContacts = emergencyContacts;
        this.pediatrician = pediatrician;
        this.dateOfBirth = dateOfBirth;
        weeks = new ArrayList<>();
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public ArrayList<Medication> getMedications() {
        return medications;
    }

    public void setMedications(ArrayList<Medication> medications) {
        this.medications = medications;
    }

    public ArrayList<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(ArrayList<String> allergies) {
        this.allergies = allergies;
    }

    public ArrayList<Contact> getEmergencyContacts() {
        return emergencyContacts;
    }

    public void setEmergencyContacts(ArrayList<Contact> emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
    }

    public Contact getPediatrician() {
        return pediatrician;
    }

    public void setPediatrician(Contact pediatrician) {
        this.pediatrician = pediatrician;
    }

    public void addWeek(int week) {
        weeks.add(week);
    }

    public String getWeeks() {
        String temp = "";
        for (int i = 0; i < weeks.size(); i++) {
            temp += "Week " + weeks.get(i) + "\n";
        }
        return temp;
    }

    /**
     * A description of the camper in string form
     * A to string for when a counselor wants to see info about the campers in their
     * group
     * 
     * @return A string description of the camper
     */
    public String toStringBrief() {
        return firstName + " " + lastName;
        // + "\n" + dateOfBirth.toString()

    }

    // different groups need to see different amounts of camper information
    // when director or user wants to see camper information
    public String toStringFull() {
        String temp = "";

        /*
         * temp = "Counselor:  " + firstName + " " + lastName + "\nDate of Birth: " +
         * dateOfBirth.getMonth() +
         * "/" + dateOfBirth.getDate() + "/" + dateOfBirth.getYear() + "\nAddress: " +
         * homeAddress +
         * "\nEmail: " + emailAddress + "\n " + pediatrician + "Medical Info: \n" +
         * medicalInfo + "\nPhone Number: " + phoneNumber;
         */

        temp = "\nCamper: " + firstName + " " + lastName + "\nDate of Birth: " + formatDate(dateOfBirth)
                + "\nAddress: " + homeAddress + "\nSex: " + sex + "\nMedications: \n" + printMedication()
                + "\nAllergies: \n"
                + printAllergies() + "\nEmergency Contacts: \n" + printEmergencyContacts() + "\nPediatrician: \n"
                + pediatrician.toString() + "\n";
        return temp;
    }

    private String formatDate(Date d) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return formatter.format(d);
    }

    // helper methods
    // it seems weird these methods can't go into their own classes because I'm sure
    // we'll need em again
    private String printMedication() {
        String temp = new String();
        for (Medication m : medications) {
            temp = temp + m.toString() + "\n";
        }
        return temp;
    }

    private String printEmergencyContacts() {
        String temp = new String();
        for (Contact c : emergencyContacts) {
            temp = temp + c.toString() + "\n";
        }
        return temp;
    }

    private String printAllergies() {
        String temp = new String();
        for (String a : allergies) {
            temp = temp + a.toString() + "\n";
        }
        return temp;
    }

    public String toString() {
        return firstName + " " + lastName;
    }

    // TODO ill look at this later
    public boolean selectWeek(Camp camp, Integer weekNumber) {
        // In the UI, the user will register their campers
        // then, they select the weeks option to figure out what weeks they want to sign
        // up for
        // and what camper gets signed up for whatever week
        // paramters: we need to know what camp and year were talking about,
        // we need to know which camper
        // and then which week
        // im thinking this method will get called once to sign a camper up for a week
        // then again for a different week
        // then again for a different camper

        // so for this method to work
        // the camper needs to be added to the campers array list in week
        // we can get to this by accessing the master schedule in camp

        // if(!)

        /*
         * if(!camp.getMasterSchedule().get(weekNumber).isFull())
         * {
         * camp.getMasterSchedule().get(weekNumber).getCampers().add(campers.get(
         * camperNumber));
         * return true;
         * }
         * return false;
         */
        return true;
    }

    public Week getWeek(Camp camp, Integer weekNumber) {
        Week week = new Week();
        for (HashMap.Entry<Integer, Week> entry : camp.getMasterSchedule().entrySet()) {
            Integer weekInt = entry.getKey();
            Week thisWeek = entry.getValue();
            if (weekNumber - 1 == weekInt) {
                week = thisWeek;
            }
        }
        return week;
    }
}
