import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PatientSection {
    public class Patient {
        private String code;
        private String name;
        private String doctorName;
        private String admissionDate;
        private String medications;

        public Patient(String code, String name, String doctorName, String admissionDate, String medications) {
            this.code = code;
            this.name = name;
            this.doctorName = doctorName;
            this.admissionDate = admissionDate;
            this.medications = medications;
        }
        public String toFileString() {
            return code + " - " + name + " - " + doctorName + " - " + admissionDate + " - " + medications;
        }
        public String getCode() {
            return code;
        }

        @Override
        public String toString() {
            return code + " - " + name + " - " + doctorName + " - " + admissionDate + " - " + medications;
        }
    }

    private static final String PATIENTS_FILE_PATH = "patients.txt";

    public static void managePatient() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Reception department management");

        while (true) {
            System.out.println("Reception menu:");
            System.out.println("1.Add new patient");
            System.out.println("2. Remove the patient");
            System.out.println("3.Patient search");
            System.out.println("4.Display all patients' records");
            System.out.println("0.Exit");

            System.out.print("Please select an option:");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addNewPatient();
                    break;
                case 2:
                    deletePatient();
                    break;
                case 3:
                    searchPatient();
                    break;
                case 4:
                    displayAllPatients();
                    break;
                case 0:
                    System.out.println("Thanks for using our system");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void addNewPatient() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Patient Code: ");
        String patientCode = scanner.nextLine();


        if (isDuplicatePatientCode(patientCode)) {
            System.out.println("Patient with this code already exists!");
            return;
        }

        System.out.print("Enter Patient Name: ");
        String patientName = scanner.nextLine();


        saveNewPatient(patientCode, patientName);

        System.out.println("Patient added successfully!");
    }

    private static boolean isDuplicatePatientCode(String patientCode) {

        try {
            Scanner fileScanner = new Scanner(new File(PATIENTS_FILE_PATH));
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

    private static void saveNewPatient(String patientCode, String patientName) {

        try {
            FileWriter fileWriter = new FileWriter(new File(PATIENTS_FILE_PATH), true);
            fileWriter.write(patientCode + "," + patientName + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deletePatient() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Patient Code to Delete: ");
        String patientCodeToDelete = scanner.nextLine();


        if (!isValidPatient(patientCodeToDelete)) {
            System.out.println("Patient with this code does not exist!");
            return;
        }

        deletePatientFromFile(patientCodeToDelete);

        System.out.println("Patient deleted successfully!");
    }

    private static void deletePatientFromFile(String patientCodeToDelete) {

        try {
            File tempFile = new File("tempPatients.txt");
            File patientsFile = new File(PATIENTS_FILE_PATH);

            Scanner fileScanner = new Scanner(patientsFile);
            FileWriter fileWriter = new FileWriter(tempFile);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                String code = parts[0].trim();
                if (!code.equals(patientCodeToDelete)) {
                    fileWriter.write(line + "\n");
                }
            }

            fileScanner.close();
            fileWriter.close();


            tempFile.renameTo(patientsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void searchPatient() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Patient Code to Search: ");
        String patientCodeToSearch = scanner.nextLine();

        if (!isValidPatient(patientCodeToSearch)) {
            System.out.println("Patient with this code does not exist!");
            return;
        }

        displayPatientInfo(patientCodeToSearch);
    }

    private static void displayPatientInfo(String patientCode) {
        try {
            Scanner fileScanner = new Scanner(new File(PATIENTS_FILE_PATH));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                String code = parts[0].trim();
                if (code.equals(patientCode)) {
                    String name = parts[1].trim();
                    System.out.println("Patient Code: " + code);
                    System.out.println("Patient Name: " + name);
                    return;
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displayAllPatients() {
        try {
            Scanner fileScanner = new Scanner(new File(PATIENTS_FILE_PATH));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                String code = parts[0].trim();
                String name = parts[1].trim();
                System.out.println("Patient Code: " + code + ", Patient Name: " + name);
            }
            fileScanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isValidPatient(String patientCode) {
        try {
            Scanner fileScanner = new Scanner(new File(PATIENTS_FILE_PATH));
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

}