
import java.util.HashMap;
import java.util.ArrayList;

public class Camp {
    /**
     * Derek: I need to be able to access a specific week within the masterSchedule
     * so if you could make getters and setters that could get a specific week and
     * integer
     * within the hashmap that'd be amazing
     * essentially in the writer I want to be able to call:
     * "campDetails.put(THEME, camp.getMasterSchedule().getWeek().getTheme());"
     * which should theoretically write the camp's theme to the json file
     * text me if you need help, not crazy urgent but I can't write the writer file
     * without this
     * -Zak
     */
    private String name;
    private String description;
    private HashMap<Integer, Week> masterSchedule;
    private ArrayList<Activity> activities;

    //Empty constructor if we want to make a new camp? idk if we need this
    public Camp()
    {
        name = null;
        description = null;
        masterSchedule = null;
        activities = null;
    }

    //full constructor for reading from JSON
    public Camp(String name, String description, HashMap<Integer, Week> masterSchedule,
            ArrayList<Activity> activities) {
        // TODO figure out Calendar constructor
    }

    // TODO figure out which constructors are needed --> Ask Zak
    public Camp(String name, String description) {
        this.name = name;
        this.description = description;
        masterSchedule = new HashMap<Integer, Week>();
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

    /**
     * Derek: I need to be able to access a specific week within the masterSchedule
     * so if you could make getters and setters that could get a specific week and
     * integer
     * within the hashmap that'd be amazing
     * essentially in the writer I want to be able to call:
     * "campDetails.put(THEME, camp.getMasterSchedule().getWeek().getTheme());"
     * which should theoretically write the camp's theme to the json file
     * text me if you need help, not crazy urgent but I can't write the writer file
     * without this
     * -Zak
     * Zak: this hashmap essentially functions as an array but i didn't want to change it 
     * let me know if it works how you want
     */
    public Week getWeek(int num)
    {
        return masterSchedule.get(num);
    }



    public String getActivities() {
        String displayActivities = new String();
        for (Activity activity : activities) {
            displayActivities += activity.toString() + "\n";
        }

        return displayActivities;
    }

    public boolean addActivity(Activity activity) {
        if (activity == null) {
            return false;
        } else {
            activities.add(activity);
            return true;
        }

    }

    public void editCalendar() {
        // do some editing of the calendar
    }

    public String viewCalendar() {
        // TODO write method
        // TODO figure out if this is essentially just a toString method and should
        // be changed to that and create view schedule in Facade
        return null;
    }
}
