import java.util.HashMap;
import java.util.ArrayList;
import java.util.Calendar;
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

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public ArrayList<Counselor> getCounselors() {
        return counselors;
    }

    public void setCounselors(ArrayList<Counselor> counselors) {
        this.counselors = counselors;
    }

    public ArrayList<Camper> getCampers() {
        return campers;
    }

    public void setCampers(ArrayList<Camper> campers) {
        this.campers = campers;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean isFull) {
        this.isFull = isFull;
    }

    // Maybe we want an empty constructor, for when new weeks need to be registered
    public Week() {
        // Constructor goes here
    }

    // and a full one so that when we read from JSON we can create weeks that have
    // already been created/registered
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
        // automation of the schedules will be done in here, and they will be assigned to groups
        //reason for this is a week has the list of groups, counselors available for the week, 
        //and campers that want to attend the camp for this week 

        //to create a group, they need a counselor
        // then they need maximum of 10 kids preferably by age
        //          if we do it by age: how will we determine their age

        //sort campers by age
        

    }

    private void sortCampersByAge()
    {

    }

    private boolean calculateAge(Date birthDate, Date currentDate)
    {
        
        return true;
    }

    public String viewSchedule() {
        String temp = new String();
        for (Group g : groups) {
            temp = temp + "\n\n\t\t" + g.printSchedule();
        }
        return null;
    }

    public void editSchedule() {
        // edit da schedule here
    }

    public String toString(){
        Calendar start = Calendar.getInstance();
        start.setTime(startDate); 
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        return String.valueOf(start.get(start.MONTH))+"/" + String.valueOf(start.get(start.DAY_OF_MONTH)) + " - " 
                + String.valueOf(end.get(end.MONTH)) +"/"+ String.valueOf(end.get(end.DAY_OF_MONTH)) + " " + theme;
    }
}
