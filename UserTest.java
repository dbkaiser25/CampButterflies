//Vershon Lee
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
class UserTest {

    CampFacade facade = new CampFacade();
    CampButterfliesDriver driver = new CampButterfliesDriver(facade);
    
    @Test 
    public void testTest(){
        assertEquals(1,1);
    }

    @Test
    public void testHaveValidUser()
    {
        LoginInfo loginInfo = new LoginInfo("lebron","james");
        boolean result = UserList.getInstance().haveUser(loginInfo);
        assertEquals(true, result);
    }

    @Test
    public void testHaveInvalidUser()
    {
        LoginInfo loginInfo = new LoginInfo("lebron","apple");
        boolean result = UserList.getInstance().haveUser(loginInfo);
        assertEquals(false, result);
    }

    @Test
    public void testAddDuplicateUser()
    {
        LoginInfo loginInfo = new LoginInfo("lebron","james");
        User user = new User("Lebron", "James", new Date(), "44 Lakers Drive", loginInfo, new ArrayList<Camper>());
        boolean result = UserList.getInstance().addUser(user);
        assertEquals(false, result);
    }
    @Test
    public void testAddDuplicateDifferentUser()
    {
        LoginInfo loginInfo = new LoginInfo("lebron","apple");
        User user = new User("Lebron", "apple", new Date(), "4 Lakers Drive", loginInfo, new ArrayList<Camper>());
        boolean result = UserList.getInstance().addUser(user);
        assertEquals(false, result);
    }
    @Test
    public void testAddnullUser()
    {
        LoginInfo loginInfo = new LoginInfo("","apple");
        User user = new User("shon", "bryan", new Date(), "56 Lakers Drive", loginInfo, new ArrayList<Camper>());
        boolean result = UserList.getInstance().addUser(user);
        assertEquals(false, result);
    }
    @Test
    public void testAddValidUser()
    {
        LoginInfo loginInfo = new LoginInfo("crazy","snapple");
        User user = new User("Leon", "Lion", new Date(), "56 Lion Lane", loginInfo, new ArrayList<Camper>());
        boolean result = UserList.getInstance().addUser(user);
        assertEquals(true, result);
    }
    @Test
    public void testAddInvalidAddressUser()
    {
        LoginInfo loginInfo = new LoginInfo("Batch","Quest");
        User user = new User("Karen", "King", new Date(), "44 Lakers Drive", loginInfo, new ArrayList<Camper>());
        boolean result = UserList.getInstance().addUser(user);
        assertEquals(false, result);
    }
    @Test
    public void testAddInvalidLoginUser()
    {
        LoginInfo loginInfo = new LoginInfo("","Quest");
        User user = new User("Karen", "King", new Date(), "44 Lakers Drive", loginInfo, new ArrayList<Camper>());
        boolean result = UserList.getInstance().addUser(user);
        assertEquals(false, result);
    }
    @Test
    public void testAddInvalidPasswordUser()
    {
        LoginInfo loginInfo = new LoginInfo("Batch","");
        User user = new User("Karen", "King", new Date(), "44 Lakers Drive", loginInfo, new ArrayList<Camper>());
        boolean result = UserList.getInstance().addUser(user);
        assertEquals(false, result);
    }
    @Test
    public void testGetUsers()
    {
        int num =UserList.getInstance().getUsers().size();
        assertEquals(1,num);
    }

    @Test
    public void testNotQualifiesForDiscount()
    {
        ArrayList<Camp> camps = facade.getCampList().getCamps();
        boolean qualifies = UserList.getInstance().getUserByUserName("lebron").qualifiesForDiscount(camps, 0);
        assertFalse(qualifies);
    }
    

    @Test
    public void testQualifiesForDiscount()
    {
        ArrayList<Camp> camps = facade.getCampList().getCamps();
        boolean qualifies = UserList.getInstance().getUserByUserName("lebron").qualifiesForDiscount(camps, 2);
        assertTrue(qualifies);
    }


    @Test
    public void testQualifiesForDiscountOne()
    {
        ArrayList<Camp> camps = facade.getCampList().getCamps();
        boolean qualifies = UserList.getInstance().getUserByUserName("lebron").qualifiesForDiscount(camps, 1);
        assertTrue(qualifies);
    }

    @Test
    public void testValidGetCamper()
    {
        Camper contains = UserList.getInstance().getUserByUserName("lebron").getCamper("tommy");
        assertEquals("tommy", contains.getFirstName());
    }
    @Test
    public void testInvalidGetCamper()
    {
        Camper contains = UserList.getInstance().getUserByUserName("lebron").getCamper("");
        assertEquals(null, contains);
    }
}
