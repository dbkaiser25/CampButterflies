import java.util.HashMap;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Week {

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
        System.out.println("Size " + groups.size());
        System.out.println(id);
        Group g = new Group();
        for (int i = 0; i < groups.size(); i++) {
            System.out.println(groups.get(i).getUuid());
            System.out.println(groups.get(i).getCounselor().getUUID());
            if (groups.get(i).getUuid().equals(id)) {
                g = groups.get(i);
                return g;
            }
        }
        return null;
    }

    public Group getGroupByNumber(int num) {
        Group group = new Group();
        System.out.println("in method");
        System.out.println(groups.size());
        for (int i = 0; i < groups.size(); i++) {
            if (i == num) {
                group = groups.get(i);
            }
        }
        return group;
    }

    public Week() {
        for (int i = 0; i < 6; i++) {
            groups.add(new Group());
        }

    }

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

    public Week(String theme, Date startDate, Date endDate, boolean isFull) {
        this.theme = theme;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isFull = isFull;
        this.groups = new ArrayList<>(6);
        this.counselors = new ArrayList<>();
        this.campers = new ArrayList<>();
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

    public boolean generateSchedules(ArrayList<Activity> activities) {
        for (int i = 0; i < 6; i++) {
            // UUID uuid = UUID.randomUUID();
            // groups.add(new Group(uuid));
            groups.add(new Group(UUID.randomUUID()));
        }

        ArrayList<Activity> meals = getMeals();
        Random rand = new Random();
        ArrayList<Activity> tempActivities = new ArrayList<Activity>(activities.size());

        for (int g = 0; g < groups.size(); g++) {

            HashMap<DayOfWeek, ArrayList<Activity>> schedule = new HashMap<DayOfWeek, ArrayList<Activity>>();

            for (int d = 0; d < 7; d++) {

                ArrayList<Activity> groupActivities = new ArrayList<Activity>(8);
                
                for (int i = 0; i < activities.size(); i++) {
                    tempActivities.add(activities.get(i));
                }
                for (int i = 0; i < 8; i++) {
                    if (i % 3 == 0) {
                        groupActivities.add(meals.get(i / 3)); 
                    } else {
                        int randomIndex = rand.nextInt(tempActivities.size()); 
                        groupActivities.add(tempActivities.get(randomIndex));
                        tempActivities.remove(randomIndex);
                    }
                }
                DayOfWeek[] dOW = DayOfWeek.values();
                schedule.put(dOW[d], groupActivities);
            }
            groups.get(g).setSchedule(schedule);
        }

        return true;
    }

    public String viewSchedule() {
        String temp = new String();
        int x = 1;
        for (Group g : groups) {
            temp = temp + "\n\n\t\tGroup: " + x + "\n" + g.printSchedule();
            x++;
        }
        return temp;
    }

    public String toString() {
        Calendar start = Calendar.getInstance();
        start.setTime(this.getStartDate());
        Calendar end = Calendar.getInstance();
        end.setTime(this.getEndDate());
        return String.valueOf(start.get(start.MONTH)) + "/" +
                String.valueOf(start.get(start.DAY_OF_MONTH)) + " - "
                + String.valueOf(end.get(end.MONTH)) + "/" +
                String.valueOf(end.get(end.DAY_OF_MONTH)) + " " + theme;
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
