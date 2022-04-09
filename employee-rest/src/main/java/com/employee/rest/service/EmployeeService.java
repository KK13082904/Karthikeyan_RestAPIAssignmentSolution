package com.employee.rest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.employee.rest.entity.Employee;
import com.employee.rest.entity.Role;
import com.employee.rest.entity.User;

@Service
public interface EmployeeService{
	public void save(Employee theEmployee);
	
	public Employee findById(int empId);
	
	public void deleteById(int empId);
	
	public List<Employee> findAll();
	
	public List<Employee> searchByFirstName(String firstName);
	
	public List<Employee> sortByFirstNameAsc();
	
	public User saveUser(User user);
	public Role saveRole(Role role);
	
}
