import java.util.ArrayList;
public class CamperList 
{
   private ArrayList<Camper> campers;
   private static CamperList camperList;
   
   
   private CamperList()
   {
          campers = new ArrayList<Camper>();
   }


public static CamperList getInstance()
   {
          if(camperList == null)
          {
               camperList = new CamperList();
          }
          return camperList;
   }

   public void addCamper(Camper camper)
   {

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
