package com.restapi.Service;

import com.restapi.Model.Employee;

import java.util.List;

public interface EmployeeService {
    public  String addEmployee(Employee employee);


    public  List<Employee> getAllEmployee();

    public  Employee findById(int id);
    //public Employee UpdateEmployee(Employee employee, int id);

    public  String updateEmployee(Employee employee, int id);
    public  String deleteById(int id);
}
