
import java.util.HashMap;
import java.util.ArrayList;

/**
 * A class that defines a camp object that can be interacted with by users
 */
public class Camp {

    private String name;
    private String description;
    private int year;
    private ArrayList<Activity> activities = new ArrayList<Activity>();

    /**
     * A hashmap that holds the weeks this camp is available for. A director can choose 
     * how many weeks to create the camp for
     */
    private HashMap<Integer, Week> masterSchedule = new HashMap<Integer, Week>();
    
    /**
     * An empty constructor, useful for creating a new instance of camp where attributes are unknown
     */
    public Camp() {
    }

    /**
     * A constructor with all attributes of camp. Useful for reading camp objects in JSON
     * @param name A string for the name of the camp
     * @param description A string for the description of the camp
     * @param year An int for the year of the camp
     * @param weeks An integer for the number of weeks for the camp
     * @param activities An array list storing the activites available at the camp
     */
    public Camp(String name, String description, int year, int weeks, ArrayList<Activity> activities)
    {
        this.name = name;
        this.description = description;
        this.year = year;
        masterSchedule = new HashMap<Integer, Week>(weeks);
        this.activities = activities;
    }

    public Camp(String name, String description, HashMap<Integer, Week> masterSchedule,
            ArrayList<Activity> activities, int year) {
        // TODO figure out Calendar constructor
        this.name = name;
        this.description = description;
        this.masterSchedule = masterSchedule;
        this.activities = activities;
        this.year = year;
    }

    public Camp(String name, String description) {
        this.name = name;
        this.description = description;
        masterSchedule = new HashMap<Integer, Week>();
    }

    public Camp(String name, String description, int weekNumber) {
        this.name = name;
        this.description = description;
        masterSchedule = new HashMap<Integer, Week>(weekNumber);
    }

    public void addCamp(String name, String desc, int year, HashMap<Integer, Week> masterSchedule, ArrayList<Activity> activities)
    {
        this.name = name;
        this.description = desc;
        this.year = year;
        this.masterSchedule = masterSchedule;
        this.activities = activities;
        
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public HashMap<Integer, Week> getMasterSchedule() {
        return masterSchedule;
    }

    public void setMasterSchedule(HashMap<Integer, Week> masterSchedule) {
        this.masterSchedule = masterSchedule;
    }

    public void setActivities(ArrayList<Activity> activities) {
        this.activities = activities;
    }

    public Camp(String name, String description, ArrayList<Activity> activities) {
        this.name = name;
        this.description = description;
        this.activities = activities;
        masterSchedule = new HashMap<Integer, Week>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Week getWeek(Integer num) {
        Week week = new Week();
        for (HashMap.Entry<Integer, Week> entry : masterSchedule.entrySet()) {
            Integer weekInt = entry.getKey();
            Week thisWeek = entry.getValue();
            if (num == weekInt) {
                week = thisWeek;
            }
        }
        return week;
    }

    public ArrayList<Week> getWeeks() {
        ArrayList<Week> weeks = new ArrayList<Week>();

        for (HashMap.Entry<Integer, Week> entry : masterSchedule.entrySet()) {
            Week week = entry.getValue();
            weeks.add(week);
        }
        return weeks;
    }

    public boolean qualifiesForDiscount(ArrayList<Camper> campers) {
        for (int i = 0; i < masterSchedule.size(); i++) {
            if (masterSchedule.get((Integer) i).containsCamper(campers)) {
                return true;
            }
        }
        return false;
    }

    public String getActivities() {
        String displayActivities = new String();

        for (Activity activity : activities) {
            displayActivities += activity.toString() + "\n";
        }
        return displayActivities;
    }

    public ArrayList<Activity> getActivitiesArrayList() {
        return activities;
    }

    public boolean addActivity(Activity activity) {
        if (activity == null) {
            return false;
        } else {
            activities.add(activity);
            return true;
        }

    }

    public void printMasterSchedule() {
        for (int i = 0; i < masterSchedule.size(); i++) {
            System.out.println(masterSchedule.get(i).getTheme());
        }
    }

    public String viewCalendar() {
        String temp = new String();
        for (int i = 0; i < masterSchedule.size(); i++) {
            temp = temp + "\t\tWeek: " + i + "\n" + masterSchedule.get(i).viewSchedule() + "\n\n";
        }
        return temp;
    }

    public String toString() {
        return "CAMP: name: " + name + " description: " + description + "masterSchedule " + masterSchedule
                + " activities " + activities;
    }
}
