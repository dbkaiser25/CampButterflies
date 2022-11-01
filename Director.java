
import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;
import java.text.SimpleDateFormat;

//Director doesn't have an email?!?!?! i think thats an issue
public class Director extends Person {
   // private Calendar calendar; Consider this deleated, i don't think it is needed
   private LoginInfo userLogin;
   private ArrayList<Camp> camps = new ArrayList<Camp>();
   private String email;

   public Director(String firstName, String lastName, Date dateOfBirth,
         String homeAddress, String email, LoginInfo userLogin, ArrayList<Camp> camps) {
      super(firstName, lastName, dateOfBirth, homeAddress);
      this.camps = camps;
      this.userLogin = userLogin;
      this.email = email;
      // the calendar will probably need to call the get instance of the calendar
      // object but yea that aint written yet
   }

   public Director(UUID uuid, String firstName, String lastName, Date dateOfBirth,
         String homeAddress, String email, LoginInfo userLogin, ArrayList<Camp> camps) {
      super(uuid, firstName, lastName, dateOfBirth, homeAddress);
      this.camps = camps;
      this.userLogin = userLogin;
      this.email = email;
   }

   public LoginInfo getUserLogin() {
      return userLogin;
   }

   public ArrayList<Camp> getCamps() {
      return camps;
   }

   public String getEmail() {
      return email;
   }

   public void setUserLogin(LoginInfo userLogin) {
      this.userLogin = userLogin;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public void setCamps(ArrayList<Camp> camps) {
      this.camps = camps;
   }

   public ArrayList<Camp> getCamp() {
      return camps;
   }

   public void addCamp(Camp camp) {
      camps.add(camp);
   }

   /* 
   //to be deleted old implementation
   public void removeCamper(String firstName, String lastName, Week week) {
      // 1. they need to be removed from the list of campers for that given week
      for (int i = 0; i < week.getCampers().size(); i++) 
      {
         if (week.getCampers().get(i).getFirstName().equals(firstName)
         &&  week.getCampers().get(i).getLastName().equals(lastName)) 
         {
            removeCamperFromGroups(week, week.getCampers().get(i));
            week.getCampers().remove(i);
            // exit the loop
            i = week.getCampers().size();
         }
      }
   }
   */

   public void removeCamper(String firstName, String lastName, Camp camp)
   {
      for(int w = 0; w < camp.getMasterSchedule().size(); w++)
      {
         for(int c = 0; c < camp.getMasterSchedule().get(w).getCampers().size(); c++)
         {
            if(camp.getMasterSchedule().get(w).getCampers().get(c).getFirstName().equals(firstName)
            && camp.getMasterSchedule().get(w).getCampers().get(c).getLastName().equals(lastName))
            {
               //remove them from any groups
               removeCamperFromGroups(camp.getMasterSchedule().get(w), camp.getMasterSchedule().get(w).getCampers().get(c));

               //remove them from the master list so they can't be put into any more groups
               camp.getMasterSchedule().get(w).getCampers().remove(c);

               //exit this week and check more weeks
               c = camp.getMasterSchedule().get(w).getCampers().size();
            }
         }
      }

      //they also need to be removed from Camper List
      for(int c = 0; c < CamperList.getInstance().getCampers().size(); c++)
      {
         if(CamperList.getInstance().getCampers().get(c).getFirstName().equals(firstName)
         && CamperList.getInstance().getCampers().get(c).getLastName().equals(lastName))
         {
            CamperList.getInstance().getCampers().remove(c);
            c = CamperList.getInstance().getCampers().size();
         }
      }
   }

   // 2. they need to be removed from whatever group they are in
   // helper method
   private boolean removeCamperFromGroups(Week week, Camper camperToBeRemoved) 
   {
      for (Group g : week.getGroups()) 
      {
         for (int i = 0; i < g.getCampers().size(); i++) 
         {
            if (g.getCampers().get(i).equals(camperToBeRemoved)) 
            {
               g.getCampers().remove(i);
               return true;
            }
         }
      }
      // camper not found
      return false;
   }

   public void removeCounselor(String firstName, String lastName, Camp camp)
   {
      //they need to be removed from the list of counselors for any week
      for(int w = 0; w < camp.getMasterSchedule().size(); w++)
      {
         for(int c = 0; c < camp.getMasterSchedule().get(w).getCounselors().size(); c++)
         {
            if(camp.getMasterSchedule().get(w).getCounselors().get(c).getFirstName().equals(firstName) 
            && camp.getMasterSchedule().get(w).getCounselors().get(c).getLastName().equals(lastName))
            {
               for(int g = 0; g < camp.getMasterSchedule().get(w).getGroups().size(); g++)
               {
                  if(camp.getMasterSchedule().get(w).getGroups().get(g).getCounselor().equals
                  (camp.getMasterSchedule().get(w).getCounselors().get(c)))
                  {
                     //I don't like setting anything to null but he has to get removed somehow
                     camp.getMasterSchedule().get(w).getGroups().get(g).setCounselor(null);
                     g = camp.getMasterSchedule().get(w).getGroups().size();
                  }
               }
               camp.getMasterSchedule().get(w).getCounselors().remove(c);
               c = camp.getMasterSchedule().get(w).getCounselors().size();
            }
         }
      }

      //They also need to be removed from Counselor list
      for(int c = 0; c < CounselorList.getInstance().getCounselors().size(); c++)
      {
         if(CounselorList.getInstance().getCounselors().get(c).getFirstName().equals(firstName)
         && CounselorList.getInstance().getCounselors().get(c).getLastName().equals(lastName))
         {
            CounselorList.getInstance().getCounselors().remove(c);
            c = CounselorList.getInstance().getCounselors().size();
         }
      }
   }

   

   // to view an individual schedule, I need to know what camp were talking about
   // what week and what group were talking about
   public String getGroupSchedule(int campNumber, Integer weekNumber, int groupNumber) {
      return camps.get(campNumber).getMasterSchedule().get(weekNumber).getGroups().get(groupNumber).printSchedule();
   }

   // to view schedule for an entire weeek (all of the groups )
   public String getWeekSchedule(int campNumber, Integer weekNumber) {
      return camps.get(campNumber).getMasterSchedule().get(weekNumber).viewSchedule();
   }

   // view all of the activities
   public String viewActivities(int year) {
      String temp = new String();
      for (Camp c : camps) {
         if (c.getYear() == year) {
            for (Activity a : c.getActivitiesArrayList()) {
               temp = temp + a.toString() + "\n";
            }
            return temp;
         }
      }
      // no camp with the given year
      return null;
   }

   public void addActivity(int year, Activity activity) {
      for (Camp c : camps) {
         if (c.getYear() == year) {
            c.getActivitiesArrayList().add(activity);
            break;
         }
      }
   }

   public void removeActivity(int year, Activity activity) {

      for (Camp c : camps) {
         if (c.getYear() == year) {
            for (int i = 0; i < c.getActivitiesArrayList().size(); i++) {
               if (c.getActivitiesArrayList().get(i).equals(activity)) {
                  c.getActivitiesArrayList().remove(i);
                  i = c.getActivitiesArrayList().size();
               }
            }
            break;
         }
      }
   }

   public String viewCounselorInfo(String firstName, String lastName) {
      Counselor temp;
      temp = CounselorList.getInstance().getCounselorByName(firstName, lastName);
      if (temp == null)
         return null;
      else
         return temp.toString();
   }

   public String toString() {
      String temp = new String();
      temp = "\nDirector: " + firstName + " " + lastName + "\nUsername: "
      + userLogin.getUserName() + "\nDate of Birth: " 
      + formatDate(dateOfBirth) + "\nAddress: " + homeAddress + "\n";
      return temp;
   }

   private String formatDate(Date d)
   {
      SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
      return formatter.format(d);
   }

}
