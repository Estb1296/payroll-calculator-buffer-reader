package com.pluralsight;

import java.io.*;
//import java.io.FileNotFoundException;
import java.util.Scanner;

public class PayrollCalculatorApp {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        Employee[] employees = new Employee[100];
        boolean firstLine = true;
        // double grossPay = (employees.getHoursWorked()) * (employees.getPayRate());
        FileReader filename;
        System.out.println("What is the name of the file you want to read?");
        String doc = input.nextLine();
        filename = new FileReader(doc);
        BufferedReader bufferReader = new BufferedReader(filename);
        int employeeCount = 0;
        fileReader(bufferReader, firstLine, employees, employeeCount);
        displayOnTheConsole(employees);
        bufferReader.close();

        writeIntoanotherFile(employees, input);

        //code so far can do .json format and a simple csv format or even a txt format but none of the other formats as they will be added later on.

    }

    private static void fileReader(BufferedReader bufferReader, boolean firstLine, Employee[] employees, int employeeCount) throws IOException {
        String content;
        while ((content = bufferReader.readLine()) != null) {
            if (firstLine) {
                firstLine = false;
                continue;
            }
            //  System.out.println(content);
            String[] objects = content.split("\\|");
            int employeeId = Integer.parseInt(objects[0]);
            String name = objects[1];
            double hoursWorked = Double.parseDouble(objects[2]);
            double payRate = Double.parseDouble(objects[3]);

            employees[employeeCount] = new Employee(employeeId, name, hoursWorked, payRate);

            employeeCount++;
        }
    }

    public static void displayOnTheConsole(Employee[] employees) {
        for (Employee employee : employees) {
            if (employee != null) {
                System.out.println(employee);
            }
        }
    }

    public static void writeIntoanotherFile(Employee[] employees, Scanner input) {

        System.out.println("Do you want to create and write the data from the previous file entered into another file?(Yes/N0)");
        String choice = input.nextLine();
        if (choice.equals("yes") || choice.equals("Yes")) {
            System.out.println("What is the filename you want to create?");
            String doc = input.nextLine().trim();
            try {
                // Check if filename has proper extension
                if (!doc.endsWith(".txt") && !doc.endsWith(".csv") && !doc.endsWith(".xls") && !doc.endsWith(".pdf") && !doc.endsWith(".json") && !doc.endsWith(".xml")) {
                    throw new IOException("Invalid file extension. Please use .txt or .csv or .xls or .pdf or .json or .xml");
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter(doc));
                // writer.write("id | name | grossPay\n");
                boolean first = true;
                if (doc.endsWith(".json")) {
                    writer.write("[\n");
                    for (Employee employee : employees) {
                        if (employee != null) {
                            if (!first) {
                                writer.write(",\n");

                            }
                            first = false;
                            writer.write(employee.toJsonString() + "\n");
                        }
                    }
                    writer.write("]\n");
                } else {
                    for (Employee employee : employees) {
                        if (employee != null) {
                            writer.write(employee.toprintString() + "\n");
                        }
                    }
                }
                writer.close();
                System.out.println("File '" + doc + "' created successfully!");

                // RECURSION - call itself again
                writeIntoanotherFile(employees, input);

            } catch (IOException e) {
                System.out.println("ERROR: " + e.getMessage());
                // Recursively try again
                writeIntoanotherFile(employees, input);
            }

        } else if (choice.equals("no") || choice.equals("No")) {
            System.out.println("Thank you for using the app!");
            // END - no recursion call
        } else {
            System.out.println("Please enter 'yes' or 'no'.");
            // RECURSION - ask again for valid input
            writeIntoanotherFile(employees, input);
        }
    }
}
