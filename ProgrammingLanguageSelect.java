package LearningCategory;
import java.util.Scanner;

public class ProgrammingLanguageSelect 
{
    String option;
    String[] languages = new String[4];
    Scanner sc = new Scanner(System.in);
    public String selectProgrammingLanguage() 
   {
        System.out.println("Available Programming Languages:");
        languages[0] = "C";
        languages[1] = "Java";
        languages[2] = "C++";
        languages[3] = "Python";
        for (int i = 0; i < 4; i++) 
        {
            System.out.print((i + 1) + "." + languages[i]+"\t");
        }
        System.out.println("Select Programming Language:");
        int opt = sc.nextInt();
        switch (opt) {
            case 1: option = languages[0];
                break;
            case 2: option = languages[1];
                break;
            case 3: option = languages[2];
                break;
            case 4: option = languages[3];
                break;
            default: System.out.println("Wrong option");
        }
        return option;
    }
}
