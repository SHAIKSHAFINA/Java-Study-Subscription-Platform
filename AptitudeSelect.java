package LearningCategory;
import java.util.Scanner;
public class AptitudeSelect 
{
    String option;
    String[] aptitude = new String[3];
    Scanner sc = new Scanner(System.in);
    public String selectAptitude() 
    {
        System.out.println("Available Aptitude Options:");
        aptitude[0] = "Logical Reasoning";
        aptitude[1] = "Verbal Reasoning";
        aptitude[2] = "Quantitative Analysis";
        for (int i = 0; i < 3; i++) 
        {
            System.out.print((i + 1) + "." + aptitude[i]+"\t");
        }
        System.out.println("Select Aptitude Option:");
        int opt = sc.nextInt();
        switch (opt) {
            case 1:
                option = aptitude[0];
                break;
            case 2:
                option = aptitude[1];
                break;
            case 3:
                option = aptitude[2];
                break;
            default:
                System.out.println("Wrong option");
        }
        return option;
    }
}
