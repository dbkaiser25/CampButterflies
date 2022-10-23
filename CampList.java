import java.util.ArrayList;

public class CampList {

    private ArrayList<Camp> camps;
    private static CampList campList;
    private ArrayList<Group> groups;
    // private static GroupList groupList;

    private CampList() {
        camps = new ArrayList<Camp>();
        groups = new ArrayList<Group>();
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

    public ArrayList<Camp> getCamps() {
        return camps;
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
}
