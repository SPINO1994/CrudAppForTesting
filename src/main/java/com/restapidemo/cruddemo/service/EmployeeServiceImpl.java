package com.restapidemo.cruddemo.service;

import com.restapidemo.cruddemo.dao.EmployeeRepository;
import com.restapidemo.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepo) {
        this.employeeRepository = theEmployeeRepo;
    }
    @Override
    public Employee findById(int id) {
        Optional<Employee> result = employeeRepository.findById(id);
        Employee ReturnedEmployee= null;
        if (result.isPresent()){
            ReturnedEmployee=result.get();
        }else{
            throw new RuntimeException("system didnt find the employee");
        }
        return ReturnedEmployee;
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }


    @Override
    public List<Employee> findAll() {
        return  employeeRepository.findAll();
    }
}
