
import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;

//Director doesn't have an email?!?!?! i think thats an issue
public class Director extends Person {
   // private Calendar calendar; Consider this deleated, i don't think it is needed
   private LoginInfo userLogin;
   private Camp camp;

   public Director(String firstName, String lastName, Date dateOfBirth,
         String homeAddress, LoginInfo userLogin, Camp camp) {
      super(firstName, lastName, dateOfBirth, homeAddress);
      this.camp = camp;
      // the calendar will probably need to call the get instance of the calendar
      // object but yea that aint written yet
   }

   public Director(UUID uuid, String firstName, String lastName, Date dateOfBirth,
         String homeAddress, LoginInfo userLogin, Camp camp) {
      super(uuid, firstName, lastName, dateOfBirth, homeAddress);
      this.camp = camp;
   }

   public LoginInfo getUserLogin() {
      return userLogin;
   }

   public Camp getCamp()
   {
      return camp;
   }

   public void setCamp(Camp camp)
   {
      this.camp = camp;
   }

   public void removeCamper(String firstName, String lastName, Week week) 
   {
      // 1. they need to be removed from the list of campers for that given week
      for(int i = 0; i < week.getCampers().size(); i++)
      {
         if(week.getCampers().get(i).getFirstName().equals(firstName) 
         && week.getCampers().get(i).getLastName().equals(lastName))
         {
            removeCamperFromGroup(week,week.getCampers().get(i));
            week.getCampers().remove(i);
            //exit the loop
            i = week.getCampers().size();
         }
      }
   }

   // 2. they need to be removed from whatever group they are in 
   //helper method
   private boolean removeCamperFromGroup(Week week, Camper camperToBeRemoved)
   {
      for(Group g: week.getGroups())
      {
         for(int i = 0; i < g.getCampers().size(); i++)
         {
            if(g.getCampers().get(i).equals(camperToBeRemoved))
            {
               g.getCampers().remove(i);
               return true;
            }
         }
      }
      //camper not found
      return false;
   }

   public void viewActivities() {

   }

   public void addActivity(Activity activity) {
      camp.addActivity(activity);
   }

   public void removeActivity(Activity activity) 
   {
      
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
      return "works";
      // String temp = new String();
      // temp = "Director: " + firstName + " " + lastName + "\nUsername: "
      // + userLogin.getUserName() + "\nDate of Birth: " + dateOfBirth.toString()
      // + "\nAddress: " + homeAddress;
      // return temp;
   }
}
