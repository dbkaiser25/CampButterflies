
import java.util.HashMap;
import java.util.ArrayList;

public class Camp 
{
    private String name;
    private String description;
    private  HashMap<Integer, Week> masterSchedule;
    private ArrayList<Activity> activities;

    public Camp(String name, String description, HashMap<Integer, Week> masterSchedule, ArrayList<Activity> activities)
    {
        //TODO figure out Calendar constructor
    }

    //TODO figure out which constructors are needed --> Ask Zak
    public Camp(String name, String description)
    {
        this.name = name;
        this.description = description;
        masterSchedule = new HashMap<Integer, Week>();
    }

    public Camp(String name, String description, ArrayList<Activity> activities)
    {
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

    //public HashMap<Integer, Week> getMasterSchedule() {
        //return masterSchedule;
    //}

    //public void setMasterSchedule(HashMap<Integer, Week> masterSchedule) {
        //this.masterSchedule = masterSchedule;
    //}

    public String getActivities() 
    {
        String temp = new String();
        for(Activity a: activities)
        {
            temp = temp + a.toString() + "\n";
        }

        return temp;
    }

    public boolean addActivity(Activity activity)
    {
        if(activity == null)
        {
            return false;
        }
        else
        {
            activities.add(activity);
            return true;
        }
        
    }

    //public void setActivities(ArrayList<Activity> activities) {
        //this.activities = activities;
    //}

    public void editCalendar()
    {
        //do some editing of the calendar
    }

    public String viewCalendar()
    {
        //TODO write method
        //TODO figure out if this is essentially just a toString method and should 
        //be changed to that and create view schedule in Facade
        return null;
    }
}
