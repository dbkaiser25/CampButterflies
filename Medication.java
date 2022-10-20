public class Medication 
{
    private String dose;
    private String type;
    private String time;

    public Medication(String type, String dose,  String time)
    {
        this.type = type;
        this.dose = dose;
        this.time = time;
    }

    public String toString()
    {
        return "Medication Name: " + type +
                "Dosage Amount: " + dose +
                "Time Taken: " + time;
    }
}
