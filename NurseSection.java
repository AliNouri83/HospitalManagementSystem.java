import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class NurseSection {
    private static List<String> nurseCodes;
    private static List<String> nursePasswords;
    private Scanner scanner;

    public NurseSection() {
        nurseCodes = new ArrayList<>();
        nursePasswords = new ArrayList<>();

        nurseCodes.add("100");
        nurseCodes.add("200");
        nurseCodes.add("300");
        nurseCodes.add("400");
        nurseCodes.add("500");

        nursePasswords.add("111");
        nursePasswords.add("222");
        nursePasswords.add("333");
        nursePasswords.add("444");
        nursePasswords.add("555");

        this.scanner = new Scanner(System.in);

    }


    private static boolean isValidNurse(String nurseCode, String nursePassword) {
        for (int i = 0; i < nurseCodes.size(); i++) {
            if (nurseCodes.get(i).equals(nurseCode) && nursePasswords.get(i).equals(nursePassword)) {
                return true;
            }
        }
        return false;
    }

    public class Nurse {
        private String code;
        private String password;

        public String getCode() {
            return code;
        }

        public Nurse(String code, String password) {
            this.code = code;
            this.password = password;
        }


        @Override
        public String toString() {
            return code + " - " + password;
        }
    }

    private static final String NURSE_FILE_PATH = "nurses.txt";
    private static final int MAX_LOGIN_ATTEMPTS = 3;

    public static void manageNurse() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nursing department management");

        if (authenticateNurse()) {
            System.out.println("You entered the nurse's department");
        } else {
            System.out.println("You do not have permission to access the application.");
        }
    }

    public static boolean authenticateNurse() {
        Scanner scanner = new Scanner(System.in);
        int loginAttempts = 0;

        while (loginAttempts < MAX_LOGIN_ATTEMPTS) {
            System.out.print("Enter Nurse Code: ");
            String nurseCode = scanner.nextLine();

            System.out.print("Enter Nurse Password: ");
            String nursePassword = scanner.nextLine();
            if (isValidNurse(nurseCode, nursePassword)) {
                displayNurseMenu();
            } else {
                System.out.println("You do not have permission to access the application");
                loginAttempts++;
            }
        }

        return false;
    }


    private static void displayNurseMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("nurse menu:");
            System.out.println("1.Update the patient record");
            System.out.println("2.change Password");
            System.out.println("0.Exit");

            System.out.print("Please select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    updatePatientRecord();
                    break;
                case 2:
                    changePassword();
                    break;
                case 0:
                    System.out.println("Thanks for using our system");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void updatePatientRecord() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Patient Code: ");
        String patientCode = scanner.nextLine();

        if (isValidPatient(patientCode)) {

            System.out.println("Update the patient's file.");

        } else {
            System.out.println("Invalid patient code!");
        }
    }

    private static boolean isValidPatient(String patientCode) {

        try {
            Scanner fileScanner = new Scanner(new File(FileHandler.NURSES_FILE_PATH));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                String code = parts[0].trim();
                if (code.equals(patientCode)) {
                    return true;
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    private static void changePassword() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Current Password: ");
        String currentPassword = scanner.nextLine();

        if (!isValidNursePassword(currentPassword)) {
            System.out.println("Invalid Current Password!");
            return;
        }

        System.out.print("Enter New Password: ");
        String newPassword = scanner.nextLine();

        if (!isValidPassword(newPassword)) {
            System.out.println("Invalid New Password!");
            return;
        }

        saveNewPassword(newPassword);

        System.out.println("Password changed successfully!");
    }

    private static boolean isValidNursePassword(String currentPassword) {
        try {
            Scanner fileScanner = new Scanner(new java.io.File(NURSE_FILE_PATH));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                String password = parts[1].trim();
                if (password.equals(currentPassword)) {
                    return true;
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean isValidPassword(String newPassword) {
        return newPassword.length() >= 8;
    }

    private static void saveNewPassword(String newPassword) {
        try {
            FileWriter fileWriter = new FileWriter(new java.io.File(NURSE_FILE_PATH));
            fileWriter.write("123," + newPassword);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



