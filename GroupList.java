import java.util.ArrayList;

public class GroupList 
{
    private ArrayList<Group> groups;
    private static GroupList groupList;

    private GroupList()
    {
        groups = new ArrayList<Group>();
    }

    public static GroupList getInstance()
    {
        if(groupList == null)
        {
            groupList = new GroupList();
        }
        return groupList;
    }

    public boolean addGroup(Group group)
    {
        if(group == null)
        {
            return false;
        }
        else
        {
            groups.add(group);
            return true;
        }
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
