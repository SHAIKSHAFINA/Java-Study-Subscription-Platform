package pricing;
public class PricingCalculator 
{
	private double totalPrice = 0.0;  
        public double calculateTotalPrice(String platform, String course, String mode,String period) 
        {
        	double price = 0.0;
        	switch (platform) 
		{
            		case "Leetcode":price = 1000.0;
                			break;
            		case "HackerRank":
                			price = 900.0;
                			break;
            		case "CodeChef":
                			price = 800.0;
                			break;
            		default:
                	System.out.println("Unknown platform, no pricing available.");
                	return 0.0;
        	}
		switch (course) 
		{
            		case "C": price +=1000;
				  break;
            		case "Java":price +=1200;
				    break;
            		case "C++":price +=1200;
				    break;
            		case "Python":price += 1000.0;
                		      break;
            		case "HTML":price += 1000.0;
				    break;
            		case "CSS":price += 1200.0;
				   break;
            		case "JavaScript":price += 1500.0;
					break;
            		case "SQL":price += 1500.0;
                		   break;
            		case "Logical Reasoning":price += 1000.0;
						break;
            		case "Verbal Reasoning":price += 1000.0;
						break;
            		case "Quantitative Analysis":price += 1000.0;
                				      break;
            		case "Arrays":price += 1000.0;
					break;
            		case "Linked Lists":price += 1500.0;
					    break;
            		case "Stacks":price += 1200.0;
					break;
            		case "Queues":
                		price += 1000.0;
                		break;
            		default:
                	System.out.println("Unknown course, no pricing available.");
                	return 0.0;
        	}
        	switch (mode) 
		{
            		case "Live Classes": price += 500.0;
                			     break;
            		case "Video Lectures": price += 300.0;
                				break;
            		case "Mock Tests": price += 200.0;
                			   break;
            		default:System.out.println(" no pricing available.");
                		return 0.0;
        	}
		if (period.equals("1 Year")) 
		{
            		price *= 1.5; 
	        } 
		else if (period.equals("6 Months")) 
		{
            		price *= 1;
	        }
		totalPrice += price; 
        	return price;
   	 }
    	 public double getTotalPrice() 
	{
        	return totalPrice;
    	}
}
