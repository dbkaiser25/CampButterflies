
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * A class that defines a group or collection of campers and counselors for the
 * camp
 */
public class Group {
    private UUID uuid;
    private Counselor counselor;
    private ArrayList<Camper> campers = new ArrayList<Camper>();
    private HashMap<DayOfWeek, ArrayList<Activity>> schedule = new HashMap<DayOfWeek, ArrayList<Activity>>();

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Counselor getCounselor() {
        return counselor;
    }

    public void setCounselor(Counselor counselor) {
        this.counselor = counselor;
    }

    public ArrayList<Camper> getCampers() {
        return campers;
    }

    public void setCampers(ArrayList<Camper> campers) {
        this.campers = campers;
    }

    public void setSchedule(HashMap<DayOfWeek, ArrayList<Activity>> schedule) {
        this.schedule = schedule;
    }

    /**
     * Creates an instance of a class with the following attributes
     * 
     * @param counselor A counselor to be in charge of the group
     * @param campers   A list of campers who are apart of the group
     */
    public Group(Counselor counselor, ArrayList<Camper> campers, HashMap<DayOfWeek, ArrayList<Activity>> schedule) {
        this.uuid = UUID.randomUUID();
        this.counselor = counselor;
        this.campers = campers;
        this.schedule = schedule;

    }

    public Group(UUID uuid, Counselor counselor, ArrayList<Camper> campers,
            HashMap<DayOfWeek, ArrayList<Activity>> schedule) {
        this.uuid = uuid;
        this.counselor = counselor;
        this.campers = campers;
        this.schedule = schedule;
    }

    public Group(UUID uuid) {
        this.uuid = uuid;
        this.counselor = null;
        this.campers = null;
    }

    public Group() {
    }

    /**
     * Adds a camper to the list of campers assigned to this group
     * 
     * @param camper A camper to be added to the group
     */
    public void addCamper(Camper camper) {
        campers.add(camper);
    }

    /**
     * A pretty description of the group in string form
     * 
     * @return A string description of the group
     */
    public String toString() {
        return "a string";
    }

    public Camper getCamper(UUID id) {
        return CamperList.getInstance().getCamperByUUID(id);
    }

    public ArrayList<Camper> getCamperList() {
        return campers;
    }

    public String viewCamperList() {
        String temp = new String();
        for (Camper c : campers) {
            temp = temp + "\n" + c.toStringBrief();
        }
        return temp;
    }

    public HashMap<DayOfWeek, ArrayList<Activity>> getSchedule() {
        return schedule;
    }

    public String printSchedule() {

        String temp = "";
        temp = temp + "Counselor " + this.getCounselor().getFirstName() + "'s Group's Schedule:\n";

        for (HashMap.Entry<DayOfWeek, ArrayList<Activity>> entry : getSchedule().entrySet()) {
            ArrayList<Activity> activities = entry.getValue();
            DayOfWeek day = entry.getKey();
            temp = temp + "Day: " + day + "\n";
            for (int i = 0; i < activities.size(); i++) {
                temp = temp + "\t" + activities.get(i).toString() + "\n";
            }
        }
        return temp;
    }

    public void setScedule(HashMap<DayOfWeek, ArrayList<Activity>> schedule) {
        this.schedule = schedule;
    }
}
