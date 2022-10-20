import java.util.ArrayList;
import java.util.UUID;

public class CounselorList 
{
    private ArrayList<Counselor> counselors;
    //private ArrayList<Counselor> conselors = new ArrayList();
    private static CounselorList counselorList;

    private CounselorList()
    {
        counselors = new ArrayList<Counselor>();
        //counselors = new ArrayList<Counselor>();
        // add constructor
    }

    public static CounselorList getInstance()
    {
        if(counselorList == null)
        {
            counselorList = new CounselorList();
        }
        return counselorList;
    }

    public boolean addCounselor(Counselor counselor)
    {
        if(counselor == null)
        {
            return false;
        }
        else
        {
            counselors.add(counselor);
            return true;
        }
    }

    //returns a counselor in the list with the given userName
    public Counselor getCounselorByUserName(String userName)
    {
        for(Counselor c: counselors)
        {
            if(c.getUserLogin().getUserName().equals(userName))
                return c;
        }
        return null;
    }

    public Counselor getCounselorByUUID(UUID uuid)
    {
        for(Counselor c: counselors)
        {
            if(c.getUUID().equals(uuid))
                return c;
        }
        return null;
    }

    public void editCounselor()
    {

    }    
    public void saveCounselor()
    {

    }


}
