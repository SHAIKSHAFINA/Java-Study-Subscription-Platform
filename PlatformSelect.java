package LearningCategory;
import java.util.*;
public class PlatformSelect
{  
	String option;
	int opt;
	String[] platform = new String[3];
	Scanner sc = new Scanner(System.in);
	public String Platform()
	{
		System.out.println("Available Platform");
		platform[0]="Leetcode";
		platform[1]="HackerRank";
		platform[2]="CodeChef";
		for(int i=0;i<3;i++)
		{
			System.out.println((i+1)+" "+platform[i]);
		}
		System.out.println("Select Platform");
		int opt=sc.nextInt();
		switch(opt)
		{
			case 1:option=platform[0];
				break;
			case 2:option=platform[1];
				break;
			case 3:option=platform[2];
				break;
			default: System.out.println("Wrong option");
		}
		return option;
	}
}
	
