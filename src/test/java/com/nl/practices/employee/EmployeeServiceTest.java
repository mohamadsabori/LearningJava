package com.nl.practices.employee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeServiceTest {

    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        employeeService = new EmployeeService();
    }

    @Test
    public void testEmployeeCountForDepartment() {
        employeeService.addEmployee(new Employee("1", "Alice", "IT", new BigDecimal("5000"), 5));
        employeeService.addEmployee(new Employee("2", "Bob", "HR", new BigDecimal("3000"), 3));
        employeeService.addEmployee(new Employee("3", "Charlie", "IT", new BigDecimal("7000"), 7));
        employeeService.addEmployee(new Employee("4", "David", "HR", new BigDecimal("5000"), 4));
        employeeService.addEmployee(new Employee("5", "Alex", "HR", new BigDecimal("4500"), 4));
        employeeService.addEmployee(new Employee("6", "Martin", "HR", new BigDecimal("4500"), 4));

        Map<String, Long> employeeCount = employeeService.getDepartmentEmployeeCount();

        assertEquals(2, employeeCount.get("IT"));
        assertEquals(4, employeeCount.get("HR"));
    }

    @Test
    public void testAverageSalaryByDepartmentWithMultipleDepartments() {
        employeeService.addEmployee(new Employee("1", "Alice", "IT", new BigDecimal("5000"), 5));
        employeeService.addEmployee(new Employee("2", "Bob", "HR", new BigDecimal("3000"), 3));
        employeeService.addEmployee(new Employee("3", "Charlie", "IT", new BigDecimal("7000"), 7));
        employeeService.addEmployee(new Employee("4", "David", "HR", new BigDecimal("5000"), 4));

        Map<String, BigDecimal> averageSalaries = employeeService.getAverageSalaryByDepartment();

        assertEquals(new BigDecimal("6000"), averageSalaries.get("IT"));
        assertEquals(new BigDecimal("4000"), averageSalaries.get("HR"));
    }

    @Test
    public void testAverageSalaryByDepartmentWithSingleDepartment() {
        employeeService.addEmployee(new Employee("1", "Alice", "Sales", new BigDecimal("4500"), 6));
        employeeService.addEmployee(new Employee("2", "Bob", "Sales", new BigDecimal("5500"), 8));

        Map<String, BigDecimal> averageSalaries = employeeService.getAverageSalaryByDepartment();

        assertEquals(new BigDecimal("5000"), averageSalaries.get("Sales"));
    }

    @Test
    public void testAverageSalaryByDepartmentWithNoEmployees() {
        Map<String, BigDecimal> averageSalaries = employeeService.getAverageSalaryByDepartment();

        assertEquals(0, averageSalaries.size());
    }

    @Test
    public void testAverageSalaryByDepartmentWithDifferentSalaryValues() {
        employeeService.addEmployee(new Employee("1", "Alice", "Marketing", new BigDecimal("4000"), 4));
        employeeService.addEmployee(new Employee("2", "Bob", "Marketing", new BigDecimal("6000"), 6));
        employeeService.addEmployee(new Employee("3", "Charlie", "Marketing", new BigDecimal("5000"), 5));

        Map<String, BigDecimal> averageSalaries = employeeService.getAverageSalaryByDepartment();

        assertEquals(new BigDecimal("5000"), averageSalaries.get("Marketing"));
    }

    @Test
    public void testAverageSalaryByDepartmentWithRounding() {
        employeeService.addEmployee(new Employee("1", "Alice", "Finance", new BigDecimal("3333.33"), 5));
        employeeService.addEmployee(new Employee("2", "Bob", "Finance", new BigDecimal("3333.33"), 3));
        employeeService.addEmployee(new Employee("3", "Charlie", "Finance", new BigDecimal("3333.34"), 2));

        Map<String, BigDecimal> averageSalaries = employeeService.getAverageSalaryByDepartment();

        assertEquals(new BigDecimal("3333.33"), averageSalaries.get("Finance"));
    }
}
