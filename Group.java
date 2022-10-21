
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
    private ArrayList<Camper> campers;

    // TODO adding new HashMap Configuration figure out if its good
    private HashMap<DayOfWeek, ArrayList<Activity>> schedule;

    /**
     * Creates an instance of a class with the following attributes
     * 
     * @param counselor A counselor to be in charge of the group
     * @param campers   A list of campers who are apart of the group
     */
    public Group(Counselor counselor, ArrayList<Camper> campers) {
        this.uuid = UUID.randomUUID();
        this.counselor = counselor;
        this.campers = campers;
    }

    // TODO figure out if JSON or something else needs this
    public Group(UUID uuid, Counselor counselor, ArrayList<Camper> campers) {
        this.uuid = uuid;
        this.counselor = counselor;
        this.campers = campers;
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
    public Camper getCamper(int num) {
        return campers.get(num);
    }

    // or this instead
    public ArrayList<Camper> getCamperList() {
        return campers;
    }

    public HashMap<DayOfWeek, ArrayList<Activity>> getSchedule() {
        return schedule;
    }
}
