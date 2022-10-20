import java.util.ArrayList;

public class DirectorList 
{
    private ArrayList<Director> directors;   
    private static DirectorList directorList;

    private DirectorList()
    {
    //add constructor
    }
    
    public ArrayList<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(ArrayList<Director> directors) {
        this.directors = directors;
    }

    public static DirectorList getDirectorList() {
        return directorList;
    }

    public static void setDirectorList(DirectorList directorList) {
        DirectorList.directorList = directorList;
    }

    public static DirectorList getInstance()
    {
        if(directorList == null)
        {
            directorList = new DirectorList();
        }
        return directorList;
    }

    public void addDirector(Director director)
    {

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
