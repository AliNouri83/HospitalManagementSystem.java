import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler extends NurseSection {
    public static final String PATIENTS_FILE_PATH = "patients.txt";
    public static final String NURSES_FILE_PATH = "nurses.txt";




    public ArrayList<Nurse> readNursesFromFile() {
        ArrayList<Nurse> nursesList = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(NURSES_FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" - ");
                Nurse nurse = new Nurse(parts[0], parts[1]);
                nursesList.add(nurse);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return nursesList;
    }

    public void writeNursesToFile(ArrayList<Nurse> list) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(NURSES_FILE_PATH))) {
            for (Nurse nurse : list) {
                writer.println(nurse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<PatientSection.Patient> readPatientsFromFile() {
        return null;
    }


    public static void writePatientsToFile(ArrayList<PatientSection.Patient> patientsList) {

        }
}
