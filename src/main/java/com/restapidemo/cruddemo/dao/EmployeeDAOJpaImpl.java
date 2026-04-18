package com.restapidemo.cruddemo.dao;

import com.restapidemo.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {
private EntityManager em;

    @Override
    public Employee findById(int id) {
        Employee foundemeplyoee= em.find(Employee.class, id);
        return foundemeplyoee;
    }

    @Override
    public Employee save(Employee employee) {
        Employee dbEmployee = em.merge(employee);
        return dbEmployee;
    }

    @Override
    public void deletebyID(int ID) {
     Employee employee = em.find(Employee.class, ID);
     em.remove(employee);
    }

    @Autowired
public EmployeeDAOJpaImpl(EntityManager em) {
    this.em = em;
}
    @Override
    public List<Employee> findAll() {
    TypedQuery<Employee> query = em.createQuery("from Employee ", Employee.class);
    List<Employee> employees = query.getResultList();

        return employees;
    }
}
