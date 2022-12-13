package com.restapi.Controller;

import com.restapi.Model.CustomResponse;
import com.restapi.Model.Employee;
import com.restapi.Service.EmployeeService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import java.util.List;

@Controller("/employee")
@Tag(name = "Employee Details")
@SecurityRequirement(name="Authorization")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class EmployeeController {

    @Inject
    EmployeeService employeeService;


    @Post(value = "/add")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<CustomResponse> addEmployee(@Body Employee employee){
        return HttpResponse.ok(new CustomResponse(employeeService.addEmployee(employee)));
    }

    @Get(value = "/show")
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }
    @Get(value = "/show/{id}")
    public Employee getEmployeeById(@PathVariable("id") int id){
        return employeeService.findById(id);
    }

    @Put(value = "/update/{id}")
    public HttpResponse<CustomResponse> updateEmployee(@Body Employee employee ,@PathVariable("id") int id ){
        return HttpResponse.ok(new CustomResponse(employeeService.updateEmployee(employee, id)));

    }

    @Delete(value = "/delete/{id}")
    public HttpResponse<CustomResponse> deleteById(@PathVariable int id){
        return HttpResponse.ok(new CustomResponse(employeeService.deleteById(id))) ;
    }
}

