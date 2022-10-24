import java.util.HashMap;
import java.util.ArrayList;
import java.util.Date;

public class Week {
    // TODO figure out the dimension labels of schedule/masterschedule hashmap
    // private HashMap<Group, ArrayList<Activity>> schedule; TODO this is the old
    // HashMap Configuration see if new is good then fully delete
    // New configuration does not have any kind of schedule in week rather shifts
    // them to group and keeps one in camp/calendar

    private String theme;

    private ArrayList<Group> groups = new ArrayList<Group>();
    private ArrayList<Counselor> counselors = new ArrayList<Counselor>();
    private ArrayList<Camper> campers = new ArrayList<Camper>();
    private Date startDate;
    private Date endDate;
    private boolean isFull;

    // Maybe we want an empty constructor, for when new weeks need to be registered
    public Week() {
        // Constructor goes here
    }

    //and a full one so that when we read from JSON we can create weeks that have already been created/registered
    public Week(String theme, ArrayList<Group> groups, ArrayList<Counselor> counselors, ArrayList<Camper> campers,
            Date startDate, Date endDate, boolean isFull) {
        this.theme = theme;
        this.groups = groups;
        this.counselors = counselors;
        this.campers = campers;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isFull = isFull;
    }

    public void generateSchedules() 
    {

        // do schedule things here
    }

    public String viewSchedule() 
    {
        String temp = new String();
        for(Group g: groups)
        {
            temp = temp + "\n\n\t\t" + g.printSchedule();
        }
        return null;
    }

    public void editSchedule() {
        // edit da schedule here
    }
}
