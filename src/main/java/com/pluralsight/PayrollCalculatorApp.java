package com.pluralsight;

import java.io.BufferedReader;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PayrollCalculatorApp {
    public static void main(String[] args) throws IOException {
        Scanner input=new Scanner(System.in);
        Employee[] employees = new Employee[100];
        boolean firstLine = true;
       // double grossPay = (employees.getHoursWorked()) * (employees.getPayRate());
        FileReader filename;
        System.out.println("What is the name of the file you want to read?");
        String doc=input.nextLine();
        filename = new FileReader(doc);
        BufferedReader bufferReader= new BufferedReader(filename);

        String content;
        int employeeCount=0;
        while((content=bufferReader.readLine())!=null) {
            if(firstLine) {
                firstLine = false;
                continue;
            }
          //  System.out.println(content);
            String[]objects = content.split("\\|");
            int employeeId = Integer.parseInt(objects[0]);
            String name = objects[1];
            double hoursWorked = Double.parseDouble(objects[2]);
            double payRate = Double.parseDouble(objects[3]);

            employees[employeeCount] = new Employee(employeeId, name, hoursWorked, payRate);

            employeeCount++;
        }
        display(employees);
        bufferReader.close();
        String docWrite = "input.nextLine()";
        writeIntoanotherFile(employees,input);
        FileWriter writer=new FileWriter(docWrite);
        //input.nextLine();
        writer.close();

    }
    public static void display(Employee[]employees){
        for(Employee employee:employees){
            if(employee != null) {
                System.out.println(employee);
            }
        }
    }
    public static void writeIntoanotherFile(Employee[]employees,Scanner input){

            System.out.println("What is the filename you want to create and for me to write to?");
            String docWrite=input.nextLine();
            try {
                try (FileWriter writer = new FileWriter(docWrite)) {
                    writer.write("id | name | grossPay\n");
                    for (Employee employee : employees) {
                        if (employee != null) {
                            writer.write(employee.toprintString() + "\n");
                        }
                    }
                    writer.write("111 | Cameron Tay | 3277.65\n222 | James Tee | 2150.00:\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
