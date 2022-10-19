import java.util.HashMap;
import java.util.ArrayList;
import java.util.Date;

public class Week 
{
    //TODO figure out the dimension labels of schedule/masterschedule hashmap
    //private HashMap<Group, ArrayList<Activity>> schedule; TODO this is the old HashMap Configuration see if new is good then fully delete
    //New configuration does not have any kind of schedule in week rather shifts them to group and keeps one in camp/calendar



    private String theme;
    private ArrayList<Group> groups;
    private ArrayList<Counselor> counselors;
    private ArrayList<Camper> campers;
    private Date startDate;
    private Date endDate;
    private boolean isFull;

    //TODO write constructor
    public Week()
    {
        //Constructor goes here
    }
                                                                                        
    public void generateSchedule()
    {
        //do schedule things here
    }

    public String viewSchedule()
    {
        //do more schedule things here (related to viewing)
        return null;
    }

    public void editSchedule()
    {
        //edit da schedule here
    }
}
