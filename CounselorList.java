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

    /* IDK if we need this or not
    public Counselor getCounselorUUID(UUID uuid)
    {

        return null;
    }
    */

    public ArrayList<Counselor> getConselors() {
        return conselors;
    }

    public void setConselors(ArrayList<Counselor> conselors) {
        this.conselors = conselors;
    }

    public static CounselorList getCounselorList() {
        return counselorList;
    }

    public static void setCounselorList(CounselorList counselorList) {
        CounselorList.counselorList = counselorList;
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

    
    public Counselor getCounselorByUsername(String userName)
    {
        for(Counselor c: counselors)
        {
            //if
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
