package com.employee.rest.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.employee.rest.entity.Employee;
import com.employee.rest.entity.Role;
import com.employee.rest.entity.User;
import com.employee.rest.repository.*;
import com.employee.rest.repository.RoleRepository;
import com.employee.rest.repository.UserRepository;


@Repository
public class EmployeeServiceImpl implements EmployeeService{
	
	private SessionFactory sessionFactory;
	
	private Session session;
	
	private EmployeeRepository employeeRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository){
		employeeRepository = theEmployeeRepository;
	}
	
	@Override
	public void save(Employee theEmployee) {
		employeeRepository.save(theEmployee);
		
	}

	@Override
	public Employee findById(int empId) {
		Optional<Employee> result = employeeRepository.findById(empId);

		Employee theEmployee = null;

		if (result.isPresent()) {
			theEmployee = result.get();
		} else {
			// we didn't find the employee
			throw new RuntimeException("Did not find employee id - " + empId);
		}

		return theEmployee;
	}

	@Override
	public void deleteById(int empId) {
		employeeRepository.deleteById(empId);
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public List<Employee> searchByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return employeeRepository.findByFirstNameContainsAllIgnoreCase(firstName);
	}

	@Override
	public List<Employee> sortByFirstNameAsc() {
		// TODO Auto-generated method stub
		return employeeRepository.findAllByOrderByFirstNameAsc();
	}

	

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		user.setPassword(user.getPassword());
		return userRepository.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		// TODO Auto-generated method stub
		return roleRepository.save(role);
	}
}
