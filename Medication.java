public class Medication 
{
    private String dose;
    private String type;
    private String time;

    public Medication(String dose, String type, String time)
    {
        this.dose = dose;
        this.type = type;
        this.time = time;
    }

    public String toString()
    {
        return "A string representation of the medication";
    }
}
