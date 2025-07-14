package com.javaproject.app;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.*;
import java.io.*;

public class StudentManagement {
	static {
	    try {
	        System.setProperty("java.util.logging.config.file",
	        		StudentManagement.class.getClassLoader().getResource("logging.properties").getPath());
	    } catch (Exception e) {
	        System.out.println("Logging config failed: " + e);
	    }
	}


    private static final Scanner SCANNER = new Scanner(System.in);

    static class TipThread extends Thread {
        public void run() {
            String[] tips = {
                "Tip: Backup your database regularly!",
                "Tip: Press 4 to export student records to file.",
                "Tip: You can import data using option 5.",
                "Tip: Use correct ID to update student records.",
                "Tip: Keep your CSV files in project root for easy access."
            };
            int index = 0;
            while (true) {
                try {
                    Thread.sleep(600000); // 10 minutes
                    System.out.println("\n[Tip] " + tips[index % tips.length]);
                    index++;
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    private static void insertStudent(String name, int age, String course, double marks) {
        String sql = "INSERT INTO studentdetails(name, age, course, marks) VALUES (:name, :age, :course, :marks)";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            var query = session.createNativeQuery(sql);
            query.setParameter("name", name);
            query.setParameter("age", age);
            query.setParameter("course", course);
            query.setParameter("marks", marks);
            query.executeUpdate();
            tx.commit();
            System.out.println("Student inserted ✔");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateStudent(int id, String name, int age, String course, double marks) {
        String sql = "UPDATE studentdetails SET name = :name, age = :age, course = :course, marks = :marks WHERE id = :id";

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            var query = session.createNativeQuery(sql);
            query.setParameter("name", name);
            query.setParameter("age", age);
            query.setParameter("course", course);
            query.setParameter("marks", marks);
            query.setParameter("id", id);
            int result = query.executeUpdate();
            tx.commit();
            System.out.println(result > 0 ? "Updated ✔" : "ID not found ✖");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void listStudents() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Object[]> results = session.createNativeQuery("SELECT * FROM studentdetails ORDER BY id").list();
            System.out.printf("%n%-4s | %-20s | %-3s | %-12s | %-5s%n", "ID", "NAME", "AGE", "COURSE", "MARKS");
            System.out.println("--------------------------------------------------------------");
            for (Object[] row : results) {
                System.out.printf("%-4d | %-20s | %-3d | %-12s | %-5.2f%n",
                        row[0], row[1], row[2], row[3], row[4]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void exportToCSV(String filePath) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();
             PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {

            List<Object[]> results = session.createNativeQuery("SELECT * FROM studentdetails").list();
            pw.println("id,name,age,course,marks");
            for (Object[] row : results) {
                pw.printf("%d,%s,%d,%s,%.2f%n", row[0], row[1], row[2], row[3], row[4]);
            }
            System.out.println("Data exported to " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void importFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
             Session session = HibernateUtil.getSessionFactory().openSession()) {

            Transaction tx = session.beginTransaction();
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                String sql = "INSERT INTO studentdetails(name, age, course, marks) VALUES (:name, :age, :course, :marks)";
                var query = session.createNativeQuery(sql);
                query.setParameter("name", p[1]);
                query.setParameter("age", Integer.parseInt(p[2]));
                query.setParameter("course", p[3]);
                query.setParameter("marks", Double.parseDouble(p[4]));
                query.executeUpdate();
            }
            tx.commit();
            System.out.println("Data imported from " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TipThread().start();

        while (true) {
            System.out.println("\n==== STUDENT MANAGEMENT MENU ====");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Show All Students");
            System.out.println("4. Export to File");
            System.out.println("5. Import from File");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            String choice = SCANNER.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.print("Name   : "); String name = SCANNER.nextLine();
                    System.out.print("Age    : "); int age = Integer.parseInt(SCANNER.nextLine());
                    System.out.print("Course : "); String course = SCANNER.nextLine();
                    System.out.print("Marks  : "); double marks = Double.parseDouble(SCANNER.nextLine());
                    insertStudent(name, age, course, marks);
                    break;

                case "2":
                    System.out.print("Student ID to update: "); int id = Integer.parseInt(SCANNER.nextLine());
                    System.out.print("New Name   : "); name = SCANNER.nextLine();
                    System.out.print("New Age    : "); age = Integer.parseInt(SCANNER.nextLine());
                    System.out.print("New Course : "); course = SCANNER.nextLine();
                    System.out.print("New Marks  : "); marks = Double.parseDouble(SCANNER.nextLine());
                    updateStudent(id, name, age, course, marks);
                    break;

                case "3":
                    listStudents();
                    break;

                case "4":
                    System.out.print("Enter export file name (e.g., students.csv): ");
                    String file = SCANNER.nextLine();
                    exportToCSV(file);
                    break;

                case "5":
                    System.out.print("Enter import file name (e.g., students.csv): ");
                    file = SCANNER.nextLine();
                    importFromCSV(file);
                    break;

                case "0":
                    System.out.println("Goodbye!");
                    HibernateUtil.shutdown();
                    return;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}
