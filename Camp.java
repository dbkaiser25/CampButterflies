
import java.util.HashMap;
import java.util.ArrayList;

public class Camp {

    private String name;
    private String description;
    private int year;
    private HashMap<Integer, Week> masterSchedule = new HashMap<Integer, Week>();
    private ArrayList<Activity> activities = new ArrayList<Activity>();

    // Empty constructor if we want to make a new camp? idk if we need this
    public Camp() {

    }

    // full constructor for reading from JSON
    public Camp(String name, String description, HashMap<Integer, Week> masterSchedule,
            ArrayList<Activity> activities, int year) {
        // TODO figure out Calendar constructor
        this.name = name;
        this.description = description;
        this.masterSchedule = masterSchedule;
        this.activities = activities;
        this.year = year;
    }

    // TODO figure out which constructors are needed --> Ask Zak
    public Camp(String name, String description) {
        this.name = name;
        this.description = description;
        masterSchedule = new HashMap<Integer, Week>();
    }

    // constructor to create a new camp for director when he wants
    // to make a new camp for a new year or something
    public Camp(String name, String description, int weekNumber) {
        this.name = name;
        this.description = description;
        masterSchedule = new HashMap<Integer, Week>(weekNumber);
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
            if (num - 1 == weekInt) {
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
            // does this need to by typecasted?
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

    // I don't like this method name but I have to think it was written like this
    // for a reason
    // If it's useful for the facade i won't change it but
    public void printMasterSchedule() {
        for (int i = 0; i < masterSchedule.size(); i++) {
            System.out.println(masterSchedule.get(i).getTheme());
        }
    }

    public void editCalendar() {
        // do some editing of the calendar
    }

    // You could make an argument that printing higher levels of schedules
    // (masterSchedule)
    // should be formatted differently but that is of lower priority (prolly 3)
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
