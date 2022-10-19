
import java.util.HashMap;
import java.util.ArrayList;

public class Camp 
{
    private String name;
    private String description;
    private  HashMap<Integer, Week> masterSchedule;
    private ArrayList<Activity> activities;

    public Camp()
    {
        //TODO figure out Calendar constructor
    }

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
