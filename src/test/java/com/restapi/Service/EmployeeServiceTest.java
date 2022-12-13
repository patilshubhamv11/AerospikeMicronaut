//package com.restapi.Service;
//
//import com.restapi.Model.Department;
//import com.restapi.Model.Employee;
//import com.restapi.Repository.EmployeeRepository;
//import io.micronaut.test.annotation.MockBean;
//import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
//import jakarta.inject.Inject;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@MicronautTest
//class EmployeeServiceTest {
//
//    @Inject
//    EmployeeService employeeService;
//    @Inject
//    EmployeeRepository employeeRepository;
//    @Inject
//    EmployeeServiceImpl employeeServiceImpl;
//
//    @MockBean
//            (EmployeeService.class)
//    EmployeeService employeeService(){
//        return mock(EmployeeService.class);
//
//    };
//
//    @MockBean
//            (EmployeeServiceImpl.class)
//    EmployeeServiceImpl employeeServiceImpl(){
//        return mock(EmployeeServiceImpl.class);
//
//    };
//
//    @Test
//    void addEmployee() {
//        Employee employee =new Employee(1,"shubham","patilshubham063@gmail.com",25000,25,"2022-05-05",new Department(1,"Mechanical"));
//        when(employeeServiceImpl.addEmployee(employee)).thenReturn("Employee saved successfully..!="+employee.getId());
//        String result = employeeService.addEmployee(employee);
//        assertEquals(result,"Employee saved successfully..!="+employee.getId());
//    }
//
//    @Test
//    void getAllEmployee() {
//
//    }
//
//    @Test
//    void findById() {
//
//    }
//
//    @Test
//    void updateEmployee() {
//        Employee employee = new Employee(1,"shubham","patilshubham063@gmail.com",25000,25,"2022-10-10",)
//
//    }
//
//    @Test
//    void deleteById() {
//
//    }
//}