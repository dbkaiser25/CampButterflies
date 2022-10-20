import java.util.ArrayList;
import java.util.UUID;
public class CamperList 
{
   private ArrayList<Camper> campers;
   private static CamperList camperList;
   
   
   private CamperList()
   {

   }

   public ArrayList<Camper> getCampers() {
     return campers;
}

public void setCampers(ArrayList<Camper> campers) {
     this.campers = campers;
}

public static CamperList getCamperList() {
     return camperList;
}

public static void setCamperList(CamperList camperList) {
     CamperList.camperList = camperList;
}

public CamperList getInstance()
   {
        return null;
   }

   public void addCamper(Camper camper)
   {

   }
   public Camper getCamperByUUID(UUID uuid)
    {
        for(Camper c: campers)
        {
            if(c.getUUID().equals(uuid))
                return c;
        }
        return null;
    }
   public Camper getCamper()
   {
        return null;
   }

   public void editCamper()
   {

   }

   public void saveCamper()
   {
    
   }
}
