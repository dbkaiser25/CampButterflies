
import java.util.ArrayList;
//import java.util.UUID;

public class Director extends Person
{
   private int id;
   private Calendar calendar;
   private LoginInfo userLogin;

   public Director(String firstName, String lastName, String dateOfBirth, 
   String homeAddress, LoginInfo userLogin)
   {
      super(firstName,lastName,dateOfBirth,homeAddress);
      this.id = 0; //idk what this is supposed to do
      //the calendar will probably need to call the get instance of the calendar object but yea that aint written yet
   }

   public void removeCamper(String firstName, String lastName)
   {

      //not forard to write this method
   }

   public void viewActivities()
   {

   }

   public void addActivity(Activity activity)
   {
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
