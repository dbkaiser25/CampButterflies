public class Activity {
    /**
     * A class to describe a camp activity
     * 
     * @author dbkaiser
     */
    private String name;
    private String location;
    private String description;

    /**
     * Creates an instance of an activity with a name, location and description
     * 
     * @param name        a string for the name
     * @param location    a string for the location
     * @param description a string for the description
     */
    public Activity(String name, String location, String description) {
        this.name = name;
        this.location = location;
        this.description = description;
    }

    /**
     * A description of the activity in string form
     * 
     * @return a string description of the activity
     */
    public String toString() {
        return name + ": " + description + "\n" + location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
