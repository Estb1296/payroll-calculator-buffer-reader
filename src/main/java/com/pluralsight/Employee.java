package com.pluralsight;

public class Employee {
    private final int employeeId;
    private final String name;
    private final double hoursWorked;
    private final double payRate;

    Employee(int employeeId, String name, double hoursWorked, double payRate) {
        this.employeeId = employeeId;
        this.name = name;
        this.hoursWorked = hoursWorked;
        this.payRate = payRate;
    }

    public String toString() {
        return employeeId + " | " + name + " | " + hoursWorked + " | " + payRate + " | " + getGrossPay();
    }

    public String toprintString() {
        return employeeId + " | " + name + " | " + getGrossPay();
    }

    public String toJsonString() {
        return "{ \"id\": " + employeeId + ", \"name\": \"" + name + "\", \"grossPay\": " + getGrossPay() + " }";
    }
    //Formatting for the other filetypes will be done in the money but unchangeable file structure will be built here.

    public double getGrossPay() {
        return payRate * hoursWorked;
    }

}
