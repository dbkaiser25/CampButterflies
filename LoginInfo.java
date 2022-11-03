/**
 * A clas for a user's login info
 */
public class LoginInfo 
{
    public String userName;
    public String password;

    /**
     * To create an instance of a user's login info
     * @param userName A string for their username
     * @param password A string for their password
     */
    public LoginInfo(String userName, String password)
    {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
