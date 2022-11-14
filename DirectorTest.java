import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

//Derek Kaiser

import java.util.ArrayList;
import java.util.Date;
public class DirectorTest 
{
    //this is just getters and setters so yea not getting tested
    
    //
    CampFacade facade = new CampFacade();
    CampButterfliesDriver driver = new CampButterfliesDriver(facade);
    LoginInfo directorLoginInfo = new LoginInfo("samuels","sam");
    //driver.login
    int num = facade.Login(directorLoginInfo);
    //facade.Login(directorLoginInfo);


    
    
    

    //1 View the director profile
    @Test
    public void testViewDirectorProfile()
    {
        
        String temp = new String ("\nDirector: Samantha Samuels\nUsername: samuels\nDate of Birth: 11/04/1980\nAddress: 1101 Prizer Court\n");
        assertEquals(temp, facade.viewDirectorProfile());
    }

     

    //2 Eidt Profile
    @Test
    void testEditDirectorName_IdealInput()
    {
        
        facade.getCurrentDirector().setFirstName("Sam");
        assertEquals("Sam",facade.getCurrentDirector().getFirstName());
    }
    
    
    //5 Edit activities
    //6 view all counselors
    //7 view a counselors information
    //8 remove counselor
    //9 View All Campers
    //10 View a campers information
    //11 remove camper
    //12 view schedules
    //13 logout

    @Test
    void testEditDirectorName_NullInput()
    {
        facade.getCurrentDirector().setFirstName(null);
        assertEquals(null,facade.getCurrentDirector().getFirstName());
    }

    @Test
    void testEditDirectorName_EmptyInput()
    {
        
        facade.getCurrentDirector().setFirstName("");
        assertEquals("",facade.getCurrentDirector().getFirstName());
    }

    //4 view activities
    @Test
    void testViewActivities_IdealInput()
    {
        String userInput = new String("2023 Summer Camp");
        //String output = facade.getActivities(userInput);
        assertEquals("Activity: swimming, Location: Lake Butterfly, Description: campers will go swimming in Lake Butterfly\nActivity: Archery, Location: Butterfly range, Description: campers will practice archery at the Butterfly range\n",facade.getActivities(userInput));
    }

    @Test
    void testViewActivities_MispelledInput()
    {
        String userInput = new String("2023 sumer Camp");
        //String output = facade.getActivities(userInput);
        assertEquals("This camp does not exist",facade.getActivities(userInput));
    }

    @Test
    void testViewActivities_EmptyInput()
    {
        String userInput = new String("");
        //String output = facade.getActivities(userInput);
        assertEquals("This camp does not exist",facade.getActivities(userInput));
    }

    @Test
    void testViewActivities_NullInput()
    {
        String userInput = null;
        //String output = facade.getActivities(userInput);
        assertEquals("This camp does not exist",facade.getActivities(userInput));
    }

    /*
    //Option 5 Edit Current activities
    //The previous tests ensure that the system can find the correct camp based off user input
    //so if those tests fail, they won't work here either and therefore I'll skip them
    @Test
    void testEditActivities_Edit_IdealInput()
    {
        driver.editActivities("2023 Summer Camp");
        
    }
    */
    


    
}
