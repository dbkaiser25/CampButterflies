import java.util.ArrayList;
import java.util.UUID;

public class UserList 
{
    private ArrayList<User> users;
    private static UserList userList;

    private UserList()
    {
        users = new ArrayList<User>();
    }

    public static UserList getInstance()
    {
        if(userList == null)
        {
            userList = new UserList();
        }
        return userList;
    }

    public boolean addUser(User user)
    {
        if(user == null)
        {
            return false;
        }
        else
        {
            users.add(user);
        }
        return false;
    }

    public User getUserByUUID(UUID uuid)
    {
        for(User u: users)
        {
            if(u.getUUID().equals(uuid))
                return u;
        }
        return null;
    }

    public User getUserByUserName(String userName)
    {
        for(User u: users)
        {
            if(u.getUserLogin().getUserName().equals(userName))
                return u;
        }
        return null;
    }

    public User getUser()
    {
        return null;
    }
    public ArrayList<User> getUsers() {
        return users; 
    }
    public void editUser()
    {

    }   
    public void saveUsers()
    {
    
    }
}