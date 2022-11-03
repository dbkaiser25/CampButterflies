
import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Director extends Person {

   private LoginInfo userLogin;
   private ArrayList<Camp> camps = new ArrayList<Camp>();
   private String email;

   public Director(String firstName, String lastName, Date dateOfBirth,
         String homeAddress, String email, LoginInfo userLogin, ArrayList<Camp> camps) {
      super(firstName, lastName, dateOfBirth, homeAddress);
      this.camps = camps;
      this.userLogin = userLogin;
      this.email = email;
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

   public void removeCamper(String firstName, String lastName, Camp camp)
   {
      for(int w = 0; w < camp.getMasterSchedule().size(); w++)
      {
         for(int c = 0; c < camp.getMasterSchedule().get(w).getCampers().size(); c++)
         {
            if(camp.getMasterSchedule().get(w).getCampers().get(c).getFirstName().equals(firstName)
            && camp.getMasterSchedule().get(w).getCampers().get(c).getLastName().equals(lastName))
            {
               removeCamperFromGroups(camp.getMasterSchedule().get(w), camp.getMasterSchedule().get(w).getCampers().get(c));
               camp.getMasterSchedule().get(w).getCampers().remove(c);
               c = camp.getMasterSchedule().get(w).getCampers().size();
            }
         }
      }

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
      return false;
   }

   public void removeCounselor(String firstName, String lastName, Camp camp)
   {
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
                     camp.getMasterSchedule().get(w).getGroups().get(g).setCounselor(null);
                     g = camp.getMasterSchedule().get(w).getGroups().size();
                  }
               }
               camp.getMasterSchedule().get(w).getCounselors().remove(c);
               c = camp.getMasterSchedule().get(w).getCounselors().size();
            }
         }
      }

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

   public String getGroupSchedule(int campNumber, Integer weekNumber, int groupNumber) {
      return camps.get(campNumber).getMasterSchedule().get(weekNumber).getGroups().get(groupNumber).printSchedule();
   }

   public String getWeekSchedule(int campNumber, Integer weekNumber) {
      return camps.get(campNumber).getMasterSchedule().get(weekNumber).viewSchedule();
   }

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
