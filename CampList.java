import java.util.ArrayList;
import java.util.UUID;

public class CampList {

    private ArrayList<Camp> camps = new ArrayList<Camp>();
    private static CampList campList;

    private CampList() {
        camps = DataLoader.loadCamps();
    }

    public static CampList getInstance() {
        if (campList == null) {
            campList = new CampList();
        }
        return campList;
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

    public Group getGroupByUUID(UUID id) {
        for (Camp camp : camps) {
            for (Week week : camp.getWeeks()) {
                for (Group group : week.getGroups()) {
                    if (group.getUuid().equals(id)) {
                        return group;
                    }
                }
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
