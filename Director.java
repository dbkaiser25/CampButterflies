
import java.util.ArrayList;
import java.util.UUID;

public class Director extends Person
{
   //private Calendar calendar; Consider this deleated, i don't think it is needed 
   private LoginInfo userLogin;

   public Director(String firstName, String lastName, String dateOfBirth, 
   String homeAddress, LoginInfo userLogin)
   {
      super(firstName,lastName,dateOfBirth,homeAddress);
      
      //the calendar will probably need to call the get instance of the calendar object but yea that aint written yet
   }

   public Director(UUID uuid, String firstName, String lastName, String dateOfBirth, 
   String homeAddress, LoginInfo userLogin)
   {
      super(uuid,firstName,lastName,dateOfBirth,homeAddress);
      
   }
   
   public LoginInfo getUserLogin() {
      return userLogin;
   }

   //public void setUserLogin(LoginInfo userLogin) {
      //this.userLogin = userLogin;
   //}

   public void removeCamper(String firstName, String lastName)
   {

      //not forard to write this method
   }

   public void viewActivities()
   {

   }

   //this assumes we can pass what 
   public void addActivity(Camp camp, Activity activity)
   {
      camp.addActivity(activity);
      //add dat activity
   }

   public void removeActivity(Activity activity)
   {

   }

   public String viewCounselorInfo(String firstName, String lastName)
   {
      //view dat info
      return null;
   }

   public String toString()
   {
      return "a string";
   }
}
