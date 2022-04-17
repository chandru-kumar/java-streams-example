import org.w3c.dom.ls.LSOutput;
import pojo.Employee;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class JavaStreams {

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>(List.of(
                new Employee(1, "Adam", "Software Engineer", 20000),
                new Employee(2, "Ben", "Software Engineer", 20000),
                new Employee(3, "Chris", "Senior Software Engineer", 40000),
                new Employee(4, "Benny", "Team Lead", 50000),
                new Employee(5, "Jack", "Manager", 70000),
                new Employee(6, "Jim", "Architect", 75000),
                new Employee(7, "Jimmy", "Senior Architect", 90000),
                new Employee(8, "Joe", "Chief Architect", 120000),
                new Employee(8, "Dave", "Chief Architect", 130000)
        ));

        //Employees by Designation
        Map<String, List<Employee>> groupByDesignation = employees.stream()
                        .collect(Collectors.groupingBy(Employee::getDesignation));

        groupByDesignation.forEach((key, value) -> System.out.println(key + "," + value));

        //Employees count by Designation
        Map<String, Long> employeesCountByDesignation = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDesignation, Collectors.counting()));

        employeesCountByDesignation.forEach((key, value) -> System.out.println(key + "," + value));

        //Sum up salary by Designation
        Map<String, Integer> salaryByDesignation = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDesignation,Collectors.summingInt(Employee::getSalary)));

        salaryByDesignation.forEach((key, value) -> System.out.println(key + "," + value));

        //Max salaried Employee
        Optional<Employee> maxSalariedEmployee = employees.stream().max(Comparator.comparing(Employee::getSalary));
        System.out.println(maxSalariedEmployee);

        //Min salaried Employee
        Optional<Employee> minSalariedEmployee = employees.stream().min(Comparator.comparing(Employee::getSalary));
        System.out.println(minSalariedEmployee);

        //Max salary by Designation
        Map<String, Optional<Employee>> maxSalaryByDesignation= employees.stream()
                .collect(
                        Collectors.groupingBy(
                        Employee::getDesignation,
                        Collectors.reducing(BinaryOperator.maxBy(Comparator.comparing(Employee::getSalary))))
                        );
        System.out.println(maxSalaryByDesignation);

        //Total Salary
        int totalSalary = employees.stream().collect(Collectors.summingInt(Employee::getSalary));
        System.out.println(totalSalary);

        //Filter by salary
        List<Employee> filterBySalary = employees.stream().filter(e -> e.getSalary() >= 50000).collect(Collectors.toList());
        System.out.println(filterBySalary);

        //No of Employees getting >= 50000 by salary
        Long noOfEmployees = employees.stream().filter(e -> e.getSalary() >= 50000).count();
        System.out.println(noOfEmployees);




    }
}
