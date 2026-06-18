import java.io.*;
import java.util.*;
class StudentManager {

    private ArrayList<Student> students = new ArrayList<>();

    private final String SERIAL_FILE = "data/students.ser";
    private final String TEXT_FILE = "data/students.txt";
    private final String BINARY_FILE = "data/students.dat";

    public StudentManager() {
        createFiles();
        loadStudents();
    }

    private void createFiles() {
        try {
            File dataDir = new File("data");

            if (!dataDir.exists())
                dataDir.mkdir();

            new File(TEXT_FILE).createNewFile();
            new File(BINARY_FILE).createNewFile();
            new File(SERIAL_FILE).createNewFile();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Add Student
    public void addStudent(Student s) {
        students.add(s);
        saveStudents();
    }

    // Search Student
    public Student searchStudent(int id) {
        for (Student s : students) {
            if (s.getStudentId() == id)
                return s;
        }
        return null;
    }

    // Update Student
    public boolean updateStudent(int id, String name,
                                 String department, double gpa) {

        Student s = searchStudent(id);

        if (s != null) {
            s.setName(name);
            s.setDepartment(department);
            s.setGpa(gpa);

            saveStudents();
            return true;
        }

        return false;
    }

    // Delete Student
    public boolean deleteStudent(int id) {

        Student s = searchStudent(id);

        if (s != null) {
            students.remove(s);
            saveStudents();
            return true;
        }

        return false;
    }

    // Display All
    public void displayStudents() {

        if (students.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        for (Student s : students) {
            System.out.println(s);
        }
    }

    // Save using Object Serialization
    private void saveStudents() {

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(
                             new FileOutputStream(SERIAL_FILE))) {

            oos.writeObject(students);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Load Students
    private void loadStudents() {

        File file = new File(SERIAL_FILE);

        if (file.length() == 0)
            return;

        try (ObjectInputStream ois =
                     new ObjectInputStream(
                             new FileInputStream(SERIAL_FILE))) {

            students = (ArrayList<Student>) ois.readObject();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Text File
    public void saveToTextFile() {

        try (PrintWriter pw =
                     new PrintWriter(TEXT_FILE)) {

            for (Student s : students) {
                pw.println(s.getStudentId() + "," +
                        s.getName() + "," +
                        s.getDepartment() + "," +
                        s.getGpa());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveToBinaryFile() {

        try (DataOutputStream dos =
                     new DataOutputStream(
                             new FileOutputStream(BINARY_FILE))) {

            for (Student s : students) {

                dos.writeInt(s.getStudentId());
                dos.writeUTF(s.getName());
                dos.writeUTF(s.getDepartment());
                dos.writeDouble(s.getGpa());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Report
    public void generateReport() {

        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        double highest = students.get(0).getGpa();
        double lowest = students.get(0).getGpa();

        double sum = 0;

        for (Student s : students) {

            if (s.getGpa() > highest)
                highest = s.getGpa();

            if (s.getGpa() < lowest)
                lowest = s.getGpa();

            sum += s.getGpa();
        }

        double average = sum / students.size();

        System.out.println("\n----- REPORT -----");
        System.out.println("Total Students : " + students.size());
        System.out.println("Highest GPA    : " + highest);
        System.out.println("Lowest GPA     : " + lowest);
        System.out.println("Average GPA    : " + average);
    }

    // File Properties
    public void showFileProperties() {

        File file = new File(SERIAL_FILE);

        System.out.println("\nFile Name: " + file.getName());
        System.out.println("Path: " + file.getAbsolutePath());
        System.out.println("Size: " + file.length() + " bytes");
        System.out.println("Last Modified: "
                + new Date(file.lastModified()));
    }

    // Backup Using Buffered Streams
    public void createBackup() {

        try {

            File backupDir = new File("backup");

            if (!backupDir.exists())
                backupDir.mkdir();

            BufferedInputStream bis =
                    new BufferedInputStream(
                            new FileInputStream(SERIAL_FILE));

            BufferedOutputStream bos =
                    new BufferedOutputStream(
                            new FileOutputStream(
                                    "backup/students_backup.ser"));

            int data;

            while ((data = bis.read()) != -1) {
                bos.write(data);
            }

            bis.close();
            bos.close();

            System.out.println("Backup created successfully.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
