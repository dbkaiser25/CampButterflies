
import java.util.UUID;
import java.util.ArrayList;

public class CampFacade 
{
    private GroupList groupList;
    private CamperList camperList;
    //private UserList userList;
    //private CounselorList counselorList;
    //private DirectorList directorList;

    public CampFacade(GroupList groupList, CamperList camperList, UserList userList, 
    CounselorList counselorList, DirectorList directorList)
    {
        this.groupList = groupList;
        this.camperList = camperList;
        //this.userList = userList;
        //this.counselorList = counselorList;
        //this.directorList = directorList;
    }

    public void Login(LoginInfo userLogin)
    {

    }

    public void addCamper()
    {
        
    }

    public void editCamperProfile()
    {

    }

    public void editUserProfile()
    {

    }

    public void addCounselor()
    {

    }

    public boolean qualifiesForDiscount()
    {
        return true;
    }

    public void selectWeek()
    {

    }

    public void viewUserProfile()
    {

    }

    public void viewCamperProfile()
    {

    }

    public void editCalendar()
    {

    }

    public String viewCalendar()
    {
        return null;
    }

    public String getActivities()
    {
        return null;
    }
    //this needs an instance but of what?????
    //TODO
    public void getInstance()
    {

    }


    public User getCurrentUser(LoginInfo userLogin)
    {
        return null;
    }   

}

