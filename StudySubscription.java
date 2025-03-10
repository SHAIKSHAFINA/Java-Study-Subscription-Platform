import LearningCategory.PlatformSelect;
import LearningCategory.ProgrammingLanguageSelect;
import LearningCategory.WebDevelopmentSelect;
import LearningCategory.AptitudeSelect;
import LearningCategory.DataStructuresSelect;
import pricing.PricingCalculator;
import coupons.CouponCheck;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudySubscription {
    private static Scanner sc = new Scanner(System.in);
    private static final int MAX_LIVE_CLASS_SEATS = 2;
    private static final int MAX_VIDEO_LECTURE_SEATS = 10;
    private static final int MAX_MOCK_TEST_SEATS = 10;

    private static Map<String, Integer> seatAvailability = new HashMap<>();

    static {
        seatAvailability.put("Live Classes", MAX_LIVE_CLASS_SEATS);
        seatAvailability.put("Video Lectures", MAX_VIDEO_LECTURE_SEATS);
        seatAvailability.put("Mock Tests", MAX_MOCK_TEST_SEATS);
    }

    public static void main(String[] args) {
        loadExistingEnrollments(); 
        String name = getUserName();
        PricingCalculator pricingCalculator = new PricingCalculator();
        StringBuilder enrollmentRecord = new StringBuilder(createHeader(name));

        boolean selectMoreCourses = true;
        while (selectMoreCourses) {
            String selectedPlatform = new PlatformSelect().Platform();
            System.out.println("Selected Platform: " + selectedPlatform);

            String selectedMainCourse = selectMainCourse();
            String selectedSubCourse = selectSubCourse(selectedMainCourse);
            if (selectedSubCourse == null) continue;

            String selectedMode = selectLearningMode(selectedSubCourse, selectedPlatform);
            if (selectedMode == null) continue;

            String selectedPeriod = selectTimePeriod();
            double price = pricingCalculator.calculateTotalPrice(selectedPlatform, selectedSubCourse, selectedMode, selectedPeriod);
            double finalPrice = applyCoupon(price, selectedPlatform);

            recordEnrollment(enrollmentRecord, selectedPlatform, selectedMainCourse, selectedSubCourse, selectedMode, selectedPeriod, price, finalPrice);

            selectMoreCourses = askForMoreCourses();
        }

        writeEnrollmentToFile(enrollmentRecord.toString());
        System.out.println("Thank you for using the platform, " + name + "!");
    }

    private static void loadExistingEnrollments() {
        try (BufferedReader reader = new BufferedReader(new FileReader("enrollments.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Platform:")) {
                    String platform = line.split(": ")[1].trim();
                    String courseLine = reader.readLine(); // Read course line
                    String course = courseLine.split(": ")[1].trim();
                    String subCourseLine = reader.readLine(); // Read sub-course line
                    String subCourse = subCourseLine.split(": ")[1].trim();
                    String modeLine = reader.readLine(); // Read mode line
                    String mode = modeLine.split(": ")[1].trim();

                    if (seatAvailability.containsKey(mode)) {
                        seatAvailability.put(mode, seatAvailability.get(mode) - 1);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading enrollments: " + e.getMessage());
        }
    }

    private static String getUserName() {
        System.out.print("Enter your name: ");
        return sc.nextLine();
    }

    private static String createHeader(String name) {
        return String.format("------------------------------------------\nUsername: %s\n", name);
    }

    private static String selectMainCourse() {
	 System.out.println("------------------------------------------");
        System.out.println("Select a course:\n1. Programming Languages\n2. Web Development\n3. Aptitude\n4. Data Structures");
        int courseChoice = getValidatedChoice(4);
        switch (courseChoice) {
            case 1: return "Programming Languages";
            case 2: return "Web Development";
            case 3: return "Aptitude";
            case 4: return "Data Structures";
            default: return null;
        }
    }

    private static String selectSubCourse(String mainCourse) {
	 System.out.println("------------------------------------------");
        switch (mainCourse) {
            case "Programming Languages": return new ProgrammingLanguageSelect().selectProgrammingLanguage();
            case "Web Development": return new WebDevelopmentSelect().selectWebDevelopmentTechnology();
            case "Aptitude": return new AptitudeSelect().selectAptitude();
            case "Data Structures": return new DataStructuresSelect().selectDataStructures();
            default: return null;
        }
    }

    private static String selectLearningMode(String subCourse, String platform) {
	 System.out.println("------------------------------------------");
        System.out.println("Select learning mode:\n1. Live Classes\n2. Video Lectures\n3. Mock Tests");
        int modeChoice = getValidatedChoice(3);
        switch (modeChoice) {
            case 1: return checkAndEnroll(subCourse, "Live Classes", platform);
            case 2: return checkAndEnroll(subCourse, "Video Lectures", platform);
            case 3: return checkAndEnroll(subCourse, "Mock Tests", platform);
            default: return null;
        }
    }

    private static int getValidatedChoice(int maxChoice) {
        while (true) {
            try {
                int choice = sc.nextInt();
                if (choice >= 1 && choice <= maxChoice) {
                    return choice;
                } else {
                    System.out.println("Please select a valid option between 1 and " + maxChoice + ".");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); 
            }
        }
    }

    private static String checkAndEnroll(String course, String mode, String platform) {
        if (seatAvailability.get(mode) > 0) {
            seatAvailability.put(mode, seatAvailability.get(mode) - 1); // Decrement available seats
            return mode;
        } else {
            System.out.println("Seat unavailable for " + mode + ". Please select another mode.");
            return null;
        }
    }

    private static String selectTimePeriod() {
	 System.out.println("------------------------------------------");
        System.out.println("Select time period:\n1. 6 Months\n2. 1 Year");
        int periodChoice = getValidatedChoice(2);
        return periodChoice == 1 ? "6 Months" : "1 Year";
    }

    private static double applyCoupon(double price, String platform) {
	 System.out.println("------------------------------------------");
        System.out.println("Total price is " + price);
        System.out.print("Do you want to apply a coupon code? (yes/no): ");
        String hasCoupon = sc.next();
        if (hasCoupon.equalsIgnoreCase("yes")) {
            System.out.println("Available Coupons: leet10, leet20, hack10, hack20, chef10, chef15");
            System.out.print("Enter coupon code: ");
            String couponCode = sc.next();
            double discountedPrice = CouponCheck.applyCoupon(platform, couponCode, price);
            if (discountedPrice != price) {
                System.out.println("Coupon applied! Your discounted price is: $" + discountedPrice);
                return discountedPrice;
            } else {
                System.out.println("Invalid coupon for the selected platform or conditions.");
            }
        }
        return price;
    }

    private static void recordEnrollment(StringBuilder record, String platform, String mainCourse, String subCourse, String mode, String period, double price, double finalPrice) {
        record.append(String.format("\nPlatform: %s\nCourse: %s\nSub-Course: %s\nMode: %s\nTime Period: %s\nPrice: $%.2f\nDiscounted Price: $%.2f\n",
                platform, mainCourse, subCourse, mode, period, price, finalPrice));
    }

    private static boolean askForMoreCourses() {
	 System.out.println("------------------------------------------");
        System.out.print("Do you want to select another course? (yes/no): ");
        String moreCourses = sc.next();
        sc.nextLine(); // Clear buffer
        return moreCourses.equalsIgnoreCase("yes");
    }

    private static void writeEnrollmentToFile(String enrollmentRecord) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("enrollments.txt", true))) {
            writer.write(enrollmentRecord);
            System.out.println("Enrollment details recorded successfully.");
            
            // Display the current user's enrollment details
            System.out.println("\n--- Your Enrollment Details ---");
            System.out.println(enrollmentRecord);
            System.out.println("------------------------------------------");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
