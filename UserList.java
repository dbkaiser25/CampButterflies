import java.util.ArrayList;

public class UserList 
{
    private ArrayList<User> users;
    private static UserList userList;

    private UserList()
    {
    // constructor foes here
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public static UserList getUserList() {
        return userList;
    }

    public static void setUserList(UserList userList) {
        UserList.userList = userList;
    }

    public static UserList getInstance()
    {
        return null;
    }

    public void addUser(User user)
    {
        // add user
    }
    public User getUser()
    {
        return null;
    }

    public void editUser()
    {

    }   
    public void saveUsers()
    {
    
    }
}