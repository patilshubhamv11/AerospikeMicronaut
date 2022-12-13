package com.restapi.Repository;

import com.restapi.Model.Employee;

import java.util.List;


public abstract class EmployeeRepository {
 public abstract String addEmployee(Employee employee);


 public abstract List<Employee> getAllEmployee();


 public abstract Employee findById(int id);

 //public Employee UpdateEmployee(Employee employee, int id);

 public abstract String updateEmployee(Employee employee, int id);

 public abstract String deleteById(int id);

}


