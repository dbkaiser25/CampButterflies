import java.util.HashMap;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Calendar;

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

    public Group getGroupByUUID(UUID id) {
        for (Group g : groups) {
            if (g.getUuid().equals(id)) {
                return g;
            }
        }
        return null;
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

    public void addCamper(Camper camper) {
        if (camper != null) {
            campers.add(camper);
        }
    }

    public void addCounselor(Counselor counselor) {
        if (counselor != null) {
            counselors.add(counselor);
        }
    }

<<<<<<< HEAD
    public boolean containsCamper(ArrayList<Camper> userCampers)
    {
        for(Camper c: campers)
        {
            for(int i = 0; i < userCampers.size(); i++)
            {
                if(c.getFirstName().equals(userCampers.get(i).getFirstName()) 
                && c.getLastName().equals(userCampers.get(i).getLastName()))
                    return true;
            }
        }
        return false;
    }

    public boolean containsCamper(String firstName, String lastName)
    {
        for(Camper c: campers)
        {
            if(c.getFirstName().equals(firstName)
            && c.getLastName().equals(lastName))
            {
                return true;
            }
        }
        return false;
    }

    public void generateSchedules() 
    {
        //we need to figure out what the current date is for camper age
=======
    public void generateSchedules() {
        // we need to figure out what the current date is for camper age
>>>>>>> 90e5edc37a79ade17fb874d2812b4668c0e9f429
        Date currentDate = new Date();
        // automation of the schedules will be done in here, and they will be assigned
        // to groups
        // reason for this is a week has the list of groups, counselors available for
        // the week,
        // and campers that want to attend the camp for this week

        // to create a group, they need a counselor
        // then they need maximum of 10 kids preferably by age
        // if we do it by age: how will we determine their age

        // sort campers by age

        /*
         * How to select a month/day/year from the date
         * Date dob = convertToDate(dateOfBirth);
         * Calendar calendar = Calendar.getInstance();
         * calendar.setTime(dob);
         * System.out.println("calendar month " + calendar.get(Calendar.MONTH));
         */

        for (Camper c : campers) {
            int temp = calculateAge(c.getDateOfBirth(), currentDate);

        }

    }

    private void sortCampersByAge(Date birthDate, Date currentDate) {

    }

    private int calculateAge(Date birthDate, Date currentDate) {
        int month[] = { 31, 28, 31, 30, 31, 30, 31,
                31, 30, 31, 30, 31 };

        Calendar calBirthDate = Calendar.getInstance();
        Calendar calCurrentDate = Calendar.getInstance();
        calBirthDate.setTime(birthDate);
        calCurrentDate.setTime(currentDate);

        // int[] cd = {calCurrentDate.get(Calendar.DATE),
        // calCurrentDate.get(Calendar.MONTH), calCurrentDate.get(Calendar.YEAR)};
        // int[] bd = {calBirthDate.get(Calendar.DATE),
        // calBirthDate.get(Calendar.MONTH), calBirthDate.get(Calendar.YEAR)};

        int currentMonth, birthMonth;
        int currentYear, birthYear;

        currentMonth = calCurrentDate.get(Calendar.MONTH);
        birthMonth = calBirthDate.get(Calendar.MONTH);

        currentYear = calCurrentDate.get(Calendar.YEAR);
        birthYear = calBirthDate.get(Calendar.YEAR);

        if (birthMonth > currentMonth) {
            currentYear = currentYear - 1;
            // cd[2] = cd[2] - 1;
        }

        return currentYear - birthYear;
        // return cd[2] - bd[2];
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

    public String toString() {
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        return String.valueOf(start.get(start.MONTH)) + "/" + String.valueOf(start.get(start.DAY_OF_MONTH)) + " - "
                + String.valueOf(end.get(end.MONTH)) + "/" + String.valueOf(end.get(end.DAY_OF_MONTH)) + " " + theme;
    }
}
