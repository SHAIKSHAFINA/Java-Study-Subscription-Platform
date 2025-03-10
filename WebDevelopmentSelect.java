package LearningCategory;
import java.util.Scanner;
public class WebDevelopmentSelect 
{
    String option;
    String[] webTech = new String[4];
    Scanner sc = new Scanner(System.in);
    public String selectWebDevelopmentTechnology() 
   {
        System.out.println("Available Web Development Technologies:");
        webTech[0] = "HTML";
        webTech[1] = "CSS";
        webTech[2] = "JavaScript";
        webTech[3] = "SQL";
        for (int i = 0; i < 4; i++) 
	{
            System.out.print((i + 1) + "." + webTech[i]+"\t");
        }
        System.out.println("Select Web Development Technology:");
        int opt = sc.nextInt();
        switch (opt) {
            case 1: option = webTech[0];
                break;
            case 2: option = webTech[1];
                break;
            case 3: option = webTech[2];
                break;
            case 4: option = webTech[3];
                break;
            default: System.out.println("Wrong option");
        }
        return option;
    }
}
