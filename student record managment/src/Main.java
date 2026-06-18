import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentManager manager =
                new StudentManager();

        int choice;

        do {

            System.out.println("\n===== STUDENT RECORD SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Search Student");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Generate Report");
            System.out.println("7. File Properties");
            System.out.println("8. Create Backup");
            System.out.println("9. Exit");

            System.out.print("Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("ID: ");
                    int id = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Department: ");
                    String dep = sc.nextLine();

                    System.out.print("GPA: ");
                    double gpa = sc.nextDouble();

                    manager.addStudent(
                            new Student(id, name, dep, gpa));

                    break;

                case 2:

                    System.out.print("Enter ID: ");
                    id = sc.nextInt();

                    Student s =
                            manager.searchStudent(id);

                    if (s != null)
                        System.out.println(s);
                    else
                        System.out.println("Not Found");

                    break;

                case 3:

                    System.out.print("Enter ID: ");
                    id = sc.nextInt();

                    sc.nextLine();

                    System.out.print("New Name: ");
                    name = sc.nextLine();

                    System.out.print("New Department: ");
                    dep = sc.nextLine();

                    System.out.print("New GPA: ");
                    gpa = sc.nextDouble();

                    if (manager.updateStudent(
                            id, name, dep, gpa))
                        System.out.println("Updated");
                    else
                        System.out.println("Student Not Found");

                    break;

                case 4:

                    System.out.print("Enter ID: ");
                    id = sc.nextInt();

                    if (manager.deleteStudent(id))
                        System.out.println("Deleted");
                    else
                        System.out.println("Student Not Found");

                    break;

                case 5:
                    manager.displayStudents();
                    break;

                case 6:
                    manager.generateReport();
                    break;

                case 7:
                    manager.showFileProperties();
                    break;

                case 8:
                    manager.createBackup();
                    break;

                case 9:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 9);

        sc.close();
    }
}