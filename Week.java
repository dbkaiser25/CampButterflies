import java.util.HashMap;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Calendar;
import java.util.Random;

public class Week {
    public static void main(String[] args) 
    {
        Counselor c1 = new Counselor("George","Washington",null,null);
        Counselor c2 = new Counselor("John","Adams",null,null);
        Counselor c3 = new Counselor("Thomas","Jefferson",null,null);
        Counselor c4 = new Counselor("James","Madison",null,null);
        Counselor c5 = new Counselor("Barak","Obama",null,null);
        Counselor c6 = new Counselor("Donald","Trump",null,null);

        Group g1 = new Group();
        Group g2 = new Group();
        Group g3 = new Group();
        Group g4 = new Group();
        Group g5 = new Group();
        Group g6 = new Group();

        Week wk = new Week();

        wk.getCounselors().add(c1);
        wk.getCounselors().add(c2);
        wk.getCounselors().add(c3);
        wk.getCounselors().add(c4);
        wk.getCounselors().add(c5);
        wk.getCounselors().add(c6);

        wk.getGroups().add(g1);
        wk.getGroups().add(g2);
        wk.getGroups().add(g3);
        wk.getGroups().add(g4);
        wk.getGroups().add(g5);
        wk.getGroups().add(g6);

        Activity a1 = new Activity("Swimming","Lake","Go Swimming");
        Activity a2 = new Activity("Kayaking","Lake","Go kayaking");
        Activity a3 = new Activity("Archery","Range","Do Archery");
        Activity a4 = new Activity("Rifle Shooting", "Range","Do shooting");
        Activity a5 = new Activity("Dodgeball", "Field", "Play Dodgeball");
        Activity a6 = new Activity("Soccer", "Field", "Play Soccer");

        ArrayList<Activity> mainActivities = new ArrayList<Activity>();
        mainActivities.add(a1);
        mainActivities.add(a2);
        mainActivities.add(a3);
        mainActivities.add(a4);
        mainActivities.add(a5);
        mainActivities.add(a6);

        wk.assignCounselors();
        wk.generateSchedules(mainActivities);

        System.out.println("\t\tGroup: " + 0 + "\n" + wk.getGroups().get(0).printSchedule());

        //for(int i = 0; i < wk.getGroups().size(); i++)
        //{
            //System.out.println("\t\tGroup: " + i + "\n" + wk.getGroups().get(i).printSchedule());
        //}

        System.out.println("It didn't crash!!!");

        /* 
        System.out.println("Before Assign Counselors");
        for(int i = 0; i < 6; i++)
        {
            
            System.out.println(wk.getGroups().get(i).getCounselor().getFirstName() + " " + wk.getGroups().get(i).getCounselor().getLastName());
        }

        */

        /* 
        wk.getGroups().get(2).setCounselor(c6);
        wk.getGroups().get(1).setCounselor(c5);
        wk.getGroups().get(5).setCounselor(c1);

        wk.assignCounselors();

        System.out.println("After Assign Counselor");
        for(int i = 0; i < 6; i++)
        {
            System.out.println(wk.getGroups().get(i).getCounselor().getFirstName() + " " + wk.getGroups().get(i).getCounselor().getLastName());
        }

        */
    }








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

    public Group getGroupByNumber(int num) {
        Group group = new Group();
        System.out.println("in method");
        for (int i = 0; i < groups.size(); i++) {
            if (i == num) {
                group = groups.get(i);
                System.out.println("group: " + group);
            }
            System.out.println("in loop");
        }
        return group;
        // return groups.get(num-1);
    }

    // Maybe we want an empty constructor, for when new weeks need to be registered
    public Week() {

        // Constructor goes here

    }

    // and a full one so that when we read from JSON we can create weeks that have
    // already been created/registered
    // hmm this might be altered because it may need to read a schedule
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

    public boolean containsCamper(ArrayList<Camper> userCampers) {
        for (Camper c : campers) {
            for (int i = 0; i < userCampers.size(); i++) {
                if (c.getFirstName().equals(userCampers.get(i).getFirstName())
                        && c.getLastName().equals(userCampers.get(i).getLastName()))
                    return true;
            }
        }
        return false;
    }

    public boolean containsCamper(String firstName, String lastName) {
        for (Camper c : campers) {
            if (c.getFirstName().equals(firstName)
                    && c.getLastName().equals(lastName)) {
                return true;
            }
        }
        return false;
    }

    // The camp needs to tell the week what list of activities are available
    public boolean generateSchedules(ArrayList<Activity> activities) {
        // we need to figure out what the current date is for camper age
        Date currentDate = new Date();
        // automation of the schedules will be done in here, and they will be assigned
        // to groups
        // reason for this is a week has the list of groups, counselors available for
        // the week,
        // and campers that want to attend the camp for this week

        // to create a group, they need a counselor
        // then they need maximum of 10 kids preferably by age
        // if we do it by age: how will we determine their age

        // then each group needs a schedule

        // sort campers by age

        /*
         * How to select a month/day/year from the date
         * Date dob = convertToDate(dateOfBirth);
         * Calendar calendar = Calendar.getInstance();
         * calendar.setTime(dob);
         * System.out.println("calendar month " + calendar.get(Calendar.MONTH));
         */

        // there probably is a better way to initialize the groups
        
        /* 

        Group g1 = new Group();
        groups.add(g1);
        Group g2 = new Group();
        groups.add(g2);
        Group g3 = new Group();
        groups.add(g3);
        Group g4 = new Group();
        groups.add(g4);
        Group g5 = new Group();
        groups.add(g5);
        Group g6 = new Group();
        groups.add(g6);

        for (Camper c : campers) {
            int[] groupTotals = new int[6];
            int temp = calculateAge(c.getDateOfBirth(), currentDate);

            // TECHNICALLY there are no requirements or specifications on how the list of
            // campers
            // will be for generating groups. I'm never one to assume the best outcome but
            // I'll be doing that,
            // It is a lower priority to insert code to better split groups with worse/less
            // nice data

            if (temp == 7 || temp == 8) {
                // groupTotals[0]++;
                groups.get(0).addCamper(c);
            } else if (temp == 9 || temp == 10) {
                // groupTotals[1]++;
                groups.get(1).addCamper(c);
            } else if (temp == 11 || temp == 12) {
                // groupTotals[2]++;
                groups.get(2).addCamper(c);
            } else if (temp == 13 || temp == 14) {
                // groupTotals[3]++;
                groups.get(3).addCamper(c);
            } else if (temp == 15 || temp == 16) {
                // groupTotals[4]++;
                groups.get(4).addCamper(c);
            } else if (temp == 17 || temp == 18) {
                // groupTotals[5]++;
                groups.get(5).addCamper(c);
            }
        }

        */

        // From here we assume that groups has been properly populated
        // give each group a counselor
        /* 
        if (counselors.size() > 6 || counselors.size() <= 0) {
            // Either too many or not enough counselors assigned to the week
            return false;
        } else {
            for (int i = 0; i < counselors.size(); i++) {
                groups.get(i).setCounselor(counselors.get(i));
            }
        }
        */

        // generate a schedule for each (g)roup
        // Random rand = new Random();
        ArrayList<Activity> meals = getMeals();
        Random rand = new Random();
        ArrayList<Activity> tempActivities = new ArrayList<Activity>(activities.size());
        

        for (int g = 0; g < groups.size(); g++) {

            HashMap<DayOfWeek, ArrayList<Activity>> schedule = new HashMap<DayOfWeek, ArrayList<Activity>>();

            // need a schedule for every (d)ay
            // groups.get(i).setSchedule(generateGroupSchedule(activities));
            for (int d = 0; d < 7; d++) 
            {
                ArrayList<Activity> groupActivities = new ArrayList<Activity>(8);
                int randomIndex = 0;
                //create clone of activities array list
                for(int i = 0; i < activities.size(); i++)
                {
                    tempActivities.add(activities.get(i));
                }

                for (int i = 0; i < 8; i++) 
                {
                    if (i % 3 == 0) 
                    {
                        groupActivities.add(meals.get(i / 3)); // wether or not to add a meal
                    } 
                    else 
                    {
                        boolean hasConflict = true;
                        //boolean invalidIndex = true;
                        //int randomIndex = 0;
                        //ArrayList<Integer> invalidIndexs = new ArrayList<Integer>();
                        while(hasConflict)
                        {
                            hasConflict = false;

                            





                            //while(invalidIndex)
                            //{
                                //invalidIndex = false;
                                randomIndex = rand.nextInt(tempActivities.size()); // generate random index
                                //for(int k = 0; k < invalidIndexs.size(); k++)
                                //{
                                    //if((Integer) randomIndex == invalidIndexs.get(k))
                                    //{
                                        //it is an invalid index
                                        //invalidIndex = true;
                                        //k = invalidIndexs.size();
                                    //}
                                //}
                            //}
                            
                            //need to make sure nobody else has this activity at this time
                            for(int j = 0; j < g; j++)
                            {
                                DayOfWeek[] dOW = DayOfWeek.values();
                                if(testGroup(groups.get(j),dOW[d],i,tempActivities.get(randomIndex)))
                                {
                                    hasConflict = true;
                                    j = g;
                                    //invalidIndexs.add(randomIndex);
                                
                                }
                            }

                        }
                        groupActivities.add(tempActivities.get(randomIndex));
                        tempActivities.remove(randomIndex);

                    }
                }
                // schedule for this day is completed
                DayOfWeek[] dOW = DayOfWeek.values();
                //System.out.print("\nDay: " + dOW[d] + "\n");
                schedule.put(dOW[d], groupActivities);
                
                //for(int i = 0; i < groupActivities.size(); i++)
                //{
                    //System.out.println(groupActivities.get(i).toString());
                //}
            }

            // The group's schedule for the week is completed
            groups.get(g).setSchedule(schedule);
        }

        return true;
    }

    private boolean testGroup(Group group, DayOfWeek dOW, int timeSlot, Activity newActivity) 
    {
        Week week = new Week();
        for (HashMap.Entry<DayOfWeek, ArrayList<Activity>> entry : group.getSchedule().entrySet()) 
        {
            DayOfWeek day = entry.getKey();
            ArrayList<Activity> tempList = entry.getValue();
            if(day.equals(dOW) && hasConflict(tempList, timeSlot,newActivity))
            {
                return true;
            }
        }
        return false;
    }

    private boolean hasConflict(ArrayList<Activity> activities, int timeSlot, Activity newActivity)
    {
        if(activities.get(timeSlot).equals(newActivity))
        {
            return true;
        }
        return false;
    }   

    public void assignCounselors()
    {
        //Array list of potential counselors to be assigned to the group
        ArrayList<Counselor> availableCounselors = new ArrayList<Counselor>(counselors.size());

        //populate available counselors arraylist
        for(int c = 0; c < counselors.size(); c++)
        {
            availableCounselors.add(counselors.get(c));
        }


        for(int i = 0; i < groups.size(); i++)
        {
            if(groups.get(i).getCounselor() != null)
            {
                for(int c = 0; c < counselors.size(); c++)
                {
                    if(groups.get(i).getCounselor().equals(counselors.get(c)))
                    {
                        availableCounselors.remove(counselors.get(c));
                        c = counselors.size();
                    }
                }
            }
        }

        //int x = 0; 
        for(int i = 0; i < groups.size(); i++)
        {
            if(groups.get(i).getCounselor() == null)
            {
                groups.get(i).setCounselor(availableCounselors.get(0));
                availableCounselors.remove(0);
                //groups.get(i).setCounselor(availableCounselors.get(x));
                //x++;
            }
        }
    }
    
    //this can probably be deleted
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
        return temp;
    }

    public void editSchedule() {
        // edit da schedule here
    }

    public String toString() {
        // Calendar start = Calendar.getInstance();
        // start.setTime(this.getStartDate());
        // Calendar end = Calendar.getInstance();
        // end.setTime(this.getEndDate());
        // return String.valueOf(start.get(start.MONTH)) + "/" +
        // String.valueOf(start.get(start.DAY_OF_MONTH)) + " - "
        // + String.valueOf(end.get(end.MONTH)) + "/" +
        // String.valueOf(end.get(end.DAY_OF_MONTH)) + " " + theme;
        return "";
    }

    public ArrayList<Activity> getMeals() {
        Activity breakfast = new Activity("Breakfast", "Dining Hall", "Eat Breakfast");
        Activity lunch = new Activity("Lunch", "Dining Hall", "Eat Lunch");
        Activity dinner = new Activity("Dinner", "Dining Hall", "Eat Dinner");

        ArrayList<Activity> meals = new ArrayList<Activity>(3);
        meals.add(breakfast);
        meals.add(lunch);
        meals.add(dinner);

        return meals;
    }

    

}
