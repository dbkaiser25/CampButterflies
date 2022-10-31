import java.util.ArrayList;
import java.util.UUID;

public class DirectorList 
{
    private ArrayList<Director> directors = new ArrayList<Director>(); 
    private static DirectorList directorList;

    private DirectorList()
    {
        //directors = new ArrayList<Director>();
        directors = DataLoader.loadDirectors(); 
    }
    
    public static DirectorList getInstance()
    {
        if(directorList == null)
        {
            directorList = new DirectorList();
        }
        return directorList;
    }
    public boolean addDirector(Director director)
    {
        if(director == null)
            return false;
        else
        {
            directors.add(director);
            return true;
        }
    }

    public Director getDirectorByUserName(String userName)
    {
        for(Director d: directors)
        {
            if(d.getUserLogin().getUserName().equals(userName))
                return d;
        }
        return null;
    }

    public Director getDirectorByUUID(UUID uuid)
    {
        for(Director d: directors)
        {
            if(d.getUUID().equals(uuid))
                return d;
        }
        return null;
    }
    public ArrayList<Director> getDirectors() {
        return directors; 
    }

    //what is this method and why does it just return null???????
    //TODO figure out what this is supposed to be
    public Director getDirector()
    {
        return null;
    }

    public boolean haveDirector(LoginInfo info){
        for(Director director: directors){
            if(director.getUserLogin().equals(info));
                return true;
        }
        return false;
    }
    
    public void editDirector()
    {

    }

    public void saveDirector()
    {
        DataWriter.saveDirectors(); 
    }
}
