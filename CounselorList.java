import java.util.ArrayList;

public class CounselorList 
{
    private ArrayList<Counselor> conselors;
    private static CounselorList counselorList;

    private CounselorList()
    {
        // add constructor
    }

    public static CounselorList getInstance()
    {
        return null;
    }

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

    public void addCounselor(Counselor counselor)
    {

    }

    public Counselor getCounselor()
    {
        return null;
    }

    public void editCounselor()
    {

    }    
    public void saveCounselor()
    {

    }


}
