import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class HospitalManagementSystem {
    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler();
        Scanner scanner = new Scanner(System.in);

        System.out.println("1.Patient reception");
        System.out.println("2.Nurse");

        System.out.println("3. Exit");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                PatientSection patientSection = new PatientSection();
                PatientSection.managePatient();
                break;
            case 2:
                NurseSection.manageNurse();
                break;
            case 3 :
                System.out.println("Goodbye");
                System.exit(0);
            default:
                System.out.println("The number entered is not valid. Please try again.");
        }
    }

}
