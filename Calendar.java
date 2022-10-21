import java.util.HashMap;
import java.util.UUID;
import java.util.ArrayList;

public class Calendar {
     private HashMap<Integer, Week> masterSchedule;
     private ArrayList<Activity> activities;
     private UUID uuid;

     public Calendar(HashMap<Integer, Week> MasterSchedule, ArrayList<Activity> Activities) {
          // TODO figure out Calendar constructor
          this.uuid = UUID.randomUUID();
          this.masterSchedule = MasterSchedule;
          this.activities = Activities;
     }

     public Calendar() {
          this.masterSchedule = null;
          this.activities = null;
     }

     public void editCalendar() {
          // do some editing of the calendar
     }

     public String viewCalendar() {
          // TODO write method
          // TODO figure out if this is essentially just a toString method and should
          // be changed to that and create view schedule in Facade
          return null;
     }
}
