/**
 * A clas for a user's login info
 * @author dbkaiser
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
}
