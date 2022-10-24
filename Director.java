
import java.util.ArrayList;
import java.util.UUID;

//Director doesn't have an email?!?!?! i think thats an issue
public class Director extends Person {
   // private Calendar calendar; Consider this deleated, i don't think it is needed
   private LoginInfo userLogin;

   public Director(String firstName, String lastName, Date dateOfBirth,
         String homeAddress, LoginInfo userLogin) {
      super(firstName, lastName, dateOfBirth, homeAddress);

      // the calendar will probably need to call the get instance of the calendar
      // object but yea that aint written yet
   }

   public Director(UUID uuid, String firstName, String lastName, Date dateOfBirth,
         String homeAddress, LoginInfo userLogin) {
      super(uuid, firstName, lastName, dateOfBirth, homeAddress);

   }

   public LoginInfo getUserLogin() {
      return userLogin;
   }

   public void removeCamper(String firstName, String lastName) {

      // not forard to write this method
   }

   public void viewActivities() 
   {

   }

   // this assumes we can pass Camp
   public void addActivity(Camp camp, Activity activity) {
      camp.addActivity(activity);
   }

   public void removeActivity(Activity activity) 
   {

   }

   public String viewCounselorInfo(String firstName, String lastName) 
   {
      Counselor temp;
      temp = CounselorList.getInstance().getCounselorByName(firstName, lastName);
      if(temp == null)
         return null;
      else
         return temp.toString();
   }

   public String toString() 
   {
      String temp = new String();
      temp = "Director: " + firstName + " " + lastName + "\nUsername: " 
      + userLogin.getUserName() + "\nDate of Birth: " + dateOfBirth.toString() 
      + "\nAddress: " + homeAddress; 
      return temp;
   }
}
