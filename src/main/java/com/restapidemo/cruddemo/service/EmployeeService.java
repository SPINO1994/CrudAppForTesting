package com.restapidemo.cruddemo.service;

import com.restapidemo.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    void deleteById(int id);

    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee employee);

}