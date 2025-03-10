package coupons;
public class CouponCheck
{
	public static double applyCoupon(String platform, String couponCode, double price) 
	{
                switch (couponCode.toLowerCase()) 
		{
            		case "leet10":
                	if (platform.equalsIgnoreCase("Leetcode") && price > 2000) 
			{
                    		return price * 0.90;
                	}
                	break;
            		case "leet20":
                	if (platform.equalsIgnoreCase("Leetcode") && price > 5000) 
			{
                    		return price * 0.80; 
                	}
                	break;
            		case "hack10":
                	if (platform.equalsIgnoreCase("HackerRank") && price > 1500) 
			{
                    		return price * 0.90;  
                	}
                	break;
            		case "hack20":
                	if (platform.equalsIgnoreCase("HackerRank") && price > 4000) 
			{
                    		return price * 0.80; 
	                }
                	break;
            		case "chef10":
                	if (platform.equalsIgnoreCase("CodeChef") && price > 1000) 
			{
                    		return price * 0.90; 
	                }
                	break;
            		case "chef15":
                	if (platform.equalsIgnoreCase("CodeChef") && price > 3000) 
			{
                    		return price * 0.85;  
                	}
                	break;
            		default:
                	System.out.println("Invalid coupon or conditions not met.");
                	return price; 
        	}
		System.out.println("Invalid coupon");
        	return price; 
    	}
}
