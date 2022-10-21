import java.util.ArrayList;
import java.util.UUID;

public class DirectorList 
{
    private ArrayList<Director> directors;   
    private static DirectorList directorList;

    private DirectorList()
    {
        directors = new ArrayList<Director>();
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

    public Director getDirector()
    {
        return null;
    }
    
    public void editDirector()
    {

    }

    public void saveDirector()
    {
    
    }
}
