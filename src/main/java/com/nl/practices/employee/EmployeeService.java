package com.nl.practices.employee;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeService {
    List<Employee> employeeList = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void removeAnEmployee(String id) {
        employeeList.removeIf(e -> e.getId().equals(id));
    }

    public List<Employee> findAllEmployeeByDepartmentId(String departmentId) {
        return employeeList.stream()
                .filter(e -> e.getDepartment().equals(departmentId))
                .toList();
    }

    public Optional<Employee> getEmployeeWithHighestSalary() {
        return employeeList.stream().max(Comparator.comparing(Employee::getSalary));
    }

    public Map<String, BigDecimal> getAverageSalaryByDepartment() {
        return employeeList.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                employeesInDepartment -> {
                                    BigDecimal totalSalary = employeesInDepartment.stream()
                                            .map(Employee::getSalary)
                                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                                    int count = employeesInDepartment.size();
                                    return count > 0
                                            ? totalSalary.divide(BigDecimal.valueOf(count), RoundingMode.HALF_UP)
                                            : BigDecimal.ZERO;
                                }
                        )
                ));
    }

    public Map<String, Long> getDepartmentEmployeeCount(){
        return employeeList.stream().collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.counting()
        ));
    }

}
