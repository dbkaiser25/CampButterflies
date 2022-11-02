
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

    // TODO adding new HashMap Configuration figure out if its good
    private HashMap<DayOfWeek, ArrayList<Activity>> schedule = new HashMap<DayOfWeek, ArrayList<Activity>>();

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

    // TODO figure out if JSON or something else needs this
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

    // TODO do we need this
    public Camper getCamper(UUID id) {
        return CamperList.getInstance().getCamperByUUID(id);
        // idk who needs this but ^^^^^^ this code can prolly just be called from
        // wherever
        // its needed, no reason for a messenger method just get rid of the middle man
    }

    // Do they want the actual list or just a pretty string of the list???? TODO
    public ArrayList<Camper> getCamperList() {
        return campers;
    }

    // TODO figure out if we keep this method or the one above ^^^^^ or both
    public String viewCamperList() {
        String temp = new String();
        for (Camper c : campers) {
            temp = temp + "\n" + c.toStringBrief();
        }
        return temp;
    }

    // same thing with this one, do they want the actual schedule or to just view
    // the schedule
    public HashMap<DayOfWeek, ArrayList<Activity>> getSchedule() {
        return schedule;
    }

    // printing the schedule assuming its not empty
    public String printSchedule() {

        String temp = "";

        // temp = temp + "Counselor " + this.getCounselor().getFirstName() + "'s
        // Group's";
        // Schedule:\n";

        /*
         * for (HashMap.Entry<DayOfWeek, ArrayList<Activity>> entry :
         * getSchedule().entrySet()) {
         * System.out.println("test");
         * ArrayList<Activity> activities = entry.getValue();
         * DayOfWeek day = entry.getKey();
         * for (int i = 0; i < activities.size(); i++) {
         * temp = temp + "Day: " + day + "\n\t" + activities.get(i).toString() + "\n";
         * }
         * }
         */
        System.out.println("Here 1");
        DayOfWeek[] dOW = DayOfWeek.values();
        for (int d = 0; d < dOW.length; d++) {
            System.out.println("size " + schedule.get(dOW[d]).size());
            System.out.println("Here 2");
            temp = temp + "\nDay: " + dOW[d] + "\n";
            for (int a = 0; a < schedule.get(dOW[d]).size(); a++) {
                System.out.println("Here 3");
                temp = temp + schedule.get(dOW[d]).get((Integer) a) + "\n";
            }
        }

        // for(HashMap.Entry<DayOfWeek, ArrayList<Activity>> entry:
        // getSchedule().entrySet())
        // {
        // DayOfWeek day = entry.getKey();
        // ArrayList<Activity> activities = entry.getValue();
        // //DayOfWeek[] dOW = DayOfWeek.values();
        // temp = temp + "Day: " + day + "\n";

        // }

        return temp;
    }

    // when the schedule is automatically generated, it can be assigned as this
    // group's schedule
    public void setScedule(HashMap<DayOfWeek, ArrayList<Activity>> schedule) {
        this.schedule = schedule;
    }
}
