import java.util.ArrayList;

public class CampList {
    
    private ArrayList<Camp> camps;
    private static CampList campList;

    private CampList(){
        camps = new ArrayList<Camp>();
    }
    
    public static CampList getInstance()
    {
        if(campList == null)
        {
            campList = new CampList();
        }
        return campList;
    }

    public boolean addCamp(Camp camp){
        if(camps== null){
            return false;
        }
        else{
             camps.add(camp);
             return true;
        }
    }

    public Camp getCamp(){
        return null;
    }

    public void editGroup(){
        
    }
}
