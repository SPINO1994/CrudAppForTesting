package com.restapidemo.cruddemo.dao;

import com.restapidemo.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee employee);
    void deletebyID(int id);
}
