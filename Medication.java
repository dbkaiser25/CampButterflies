
public class Medication 
{
    private String dose;
    private String type;
    private String time;

    public Medication(String type, String dose, String time) {
        this.type = type;
        this.dose = dose;
        this.time = time;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String toString() {
        return "Medication Name: " + type +
                "Dosage Amount: " + dose +
                "Time Taken: " + time;
    }

    
}
