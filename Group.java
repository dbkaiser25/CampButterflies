
import java.util.ArrayList;
/**
 * A class that defines a group or collection of campers and counselors for the camp
 */
public class Group 
{
    private int number;
    private Counselor counselor;
    private ArrayList<Camper> campers;

    /**
     * Creates an instance of a class with the following attributes
     * @param counselor A counselor to be in charge of the group
     * @param campers A list of campers who are apart of the group
     */
    public Group(int num, Counselor counselor, ArrayList<Camper> campers)
    {
        num = number;
        this.counselor = counselor;
        this.campers = campers;
    }

    public Group(Counselor counselor, ArrayList<Camper> campers)
    {
        this.counselor = counselor;
        this.campers = campers;
    }

    /**
     * Adds a camper to the list of campers assigned to this group
     * @param camper A camper to be added to the group
     */
    public void addCamper(Camper camper)
    {
        campers.add(camper);
    }

    /**
     * A pretty description of the group in string form
     * @return A string description of the group
     */
    public String toString()
    {
        return "a string";
    }
}
