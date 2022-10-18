import java.util.Scanner;
/**
 * the UI code for the Camp Butterfly program 
 */
public class CampButterfliesDriver {
    private Scanner scan;
    private String[] homepageOptions = new String[7];

    /**
     * Creates the driver and initializes the scanner
     */
    public CampButterfliesDriver(){
        scan = new Scanner(System.in);
        homepageOptions[0] = "View Activities";
        homepageOptions[1] = "View Available Weeks";
        homepageOptions[2] = "View Pricing and Discounts";
        homepageOptions[3] = "Contact the Camp";
        homepageOptions[4] = "Register";
        homepageOptions[5] = "Login";
        homepageOptions[6] = "Quit";
    }

    public void run(){
        
        while(true){

        }
    }

    public void homepage(){
        System.out.println("\tWelcome to Camp Butterflies");
        System.out.println("-----------------------------------------------");
        System.out.println("");
    }
}
