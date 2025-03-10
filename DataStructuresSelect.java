package LearningCategory;
import java.util.Scanner;
public class DataStructuresSelect 
{
    String option;
    String[] dataStructures = new String[4];
    Scanner sc = new Scanner(System.in);
    public String selectDataStructures() 
    {
        System.out.println("Available Data Structures Options:");
        dataStructures[0] = "Arrays";
        dataStructures[1] = "Linked Lists";
        dataStructures[2] = "Stacks";
        dataStructures[3] = "Queues";
        for (int i = 0; i < 4; i++) {
            System.out.print((i + 1) + "." + dataStructures[i]+"\t");
        }

        System.out.println("Select Data Structures Option:");
        int opt = sc.nextInt();
        switch (opt) 
        {
            case 1:
                option = dataStructures[0];
                break;
            case 2:
                option = dataStructures[1];
                break;
            case 3:
                option = dataStructures[2];
                break;
            case 4:
                option = dataStructures[3];
                break;
            default:
                System.out.println("Wrong option");
        }
        return option;
    }
}
