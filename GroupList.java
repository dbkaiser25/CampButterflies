import java.util.ArrayList;

public class GroupList 
{
    private ArrayList<Group> groups;
    private static GroupList groupList;

    private GroupList()
    {
        //constructor goes here 
    }

    public static GroupList getInstance()
    {
        if(groupList == null)
        {
            groupList = new GroupList();
        }
        return groupList;
    }

    public void addGroup(Group group)
    {
        //add group
    }

    public Group getGroup()
    {
        return null;
    }

    public void editGroup()
    {

    }

    public void saveGroup()
    {
        
    }
}
