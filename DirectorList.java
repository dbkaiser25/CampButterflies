import java.util.ArrayList;
import java.util.UUID;

public class DirectorList {
    private ArrayList<Director> directors = new ArrayList<Director>();
    private static DirectorList directorList;

    private DirectorList() {
        directors = DataLoader.loadDirectors();
    }

    public static DirectorList getInstance() {
        if (directorList == null) {
            directorList = new DirectorList();
        }
        return directorList;
    }

    public boolean addDirector(Director director) {
        if (director == null)
            return false;
        else {
            directors.add(director);
            saveDirector();
            return true;
        }
    }

    public Director getDirectorByUserName(String userName) {
        for (Director d : directors) {
            if (d.getUserLogin().getUserName().equals(userName))
                return d;
        }
        return null;
    }

    public Director getDirectorByUUID(UUID uuid) {
        for (Director d : directors) {
            if (d.getUUID().equals(uuid))
                return d;
        }
        return null;
    }

    public ArrayList<Director> getDirectors() {
        return directors;
    }

    public boolean haveDirector(LoginInfo info) {
        for (Director director : directors) {
            if (director.getUserLogin().getUserName().equals(info.getUserName())
                    && director.getUserLogin().getPassword().equals(info.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public void saveDirector() {
        DataWriter.saveDirectors();
    }
}
