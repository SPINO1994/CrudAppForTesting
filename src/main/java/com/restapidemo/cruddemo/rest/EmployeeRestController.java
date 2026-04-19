package com.restapidemo.cruddemo.rest;

import com.restapidemo.cruddemo.dao.EmployeeDAO;
import com.restapidemo.cruddemo.entity.Employee;
import com.restapidemo.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private final EmployeeService employeeService;
    private JsonMapper jsonMapper;
    @Autowired
    public EmployeeRestController( EmployeeService employeeService, JsonMapper jsonMapper) {
       this.jsonMapper = jsonMapper;
        this.employeeService = employeeService;
    }
@GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return  employeeService.findAll();
}
@GetMapping("/employees/{EmployeeID}")
    public Employee getEmployeeById(@PathVariable int EmployeeID) throws Exception {
        if (employeeService.findById(EmployeeID)== null) {
            throw new RuntimeException("Employee Not Found - "+EmployeeID);
        }
        return  employeeService.findById(EmployeeID);
}
@PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        employee.setId(0);

Employee dbEmployee= employeeService.save(employee);
return dbEmployee;

    }
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {

        Employee dbEmployee= employeeService.save(employee);
        return dbEmployee;
    }
@PatchMapping("/employees/{employeeId}")

public Employee patchEmployee(@PathVariable int employeeId,@RequestBody Map<String,Object> patchPayLoad){
 Employee  TempEmployee= employeeService.findById(employeeId);
//THROW EXCEPTION IF ITS NULL
    if(TempEmployee==null){
        throw new RuntimeException("Employee is null " + employeeId);
    }
//Throw exception if body contains id key "asd"
    if(patchPayLoad.containsKey("id")){
        throw new RuntimeException("Employee Id not allowed in request body" + employeeId);
    }
    Employee patchedemployee = jsonMapper.updateValue(TempEmployee,patchPayLoad);
    Employee DbEmployee = employeeService.save(patchedemployee);
    return DbEmployee;
    }

}
