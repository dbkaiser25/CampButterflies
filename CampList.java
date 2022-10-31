import java.util.ArrayList;
import java.util.UUID;

public class CampList {

    private ArrayList<Camp> camps = new ArrayList<Camp>();
    private static CampList campList;
    private static ArrayList<Group> groups = new ArrayList<Group>();
    // private static GroupList groupList;

    private CampList() {
        camps = DataLoader.loadCamps();
    }

    public static CampList getInstance() {
        if (campList == null) {
            campList = new CampList();
        }
        return campList;
    }

    public boolean addGroup(Group group) {
        if (group == null) {
            return false;
        } else {
            groups.add(group);
            return true;
        }
    }

    public boolean addCamp(Camp camp) {
        if (camps == null) {
            return false;
        } else {
            camps.add(camp);
            return true;
        }
    }

    public Camp getCamp(String name) {
        for (Camp c : camps) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Group> getGroupsFromCamp(CampList c) {
        for (int i = 0; i < c.getCamps().size(); i++) {
            for (int j = 0; j < c.getCamps().get(i).getWeeks().size(); j++) {
                for (int k = 0; k < c.getCamps().get(i).getWeeks().get(j).getGroups().size(); k++) {
                    groups.add(new Group(c.getCamps().get(i).getWeeks().get(j).getGroups().get(k)));
                    System.out.println(groups.get(k));
                }
            }
        }
        return groups;
    }

    public Group getGroupByUUID(UUID id) {

        for (Group g : groups) {
            if (g.getUuid().equals(id)) {
                return g;
            }
        }
        return null;
    }

    public ArrayList<Camp> getCamps() {
        return camps;
    }

    public void saveCamps() {
        DataWriter.saveCamps();
    }

    /*
     * Zak do you want these and if so what should they do!!!!!
     * public Group getGroup()
     * {
     * return null;
     * }
     * 
     * public void editGroup()
     * {
     * 
     * }
     * 
     * public void saveGroup()
     * {
     * 
     * }
     * 
     */

    public void editGroup() {

    }

    public String toString() {
        String allCamps = "";
        for (Camp camp : camps) {
            allCamps += camp + "\n";
        }
        return allCamps;
    }
}
