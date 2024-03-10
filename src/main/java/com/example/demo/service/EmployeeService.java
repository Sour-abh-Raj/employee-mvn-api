package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.demo.model.Employee;
import com.example.demo.repo.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private EmailService emailService;

    public Employee addEmployee(Employee employee) {
        Optional<Employee> existingEmployee = employeeRepository.findByEmail(employee.getEmail());
        if (existingEmployee.isPresent()) {
            return null;
        }
        String id = UUID.randomUUID().toString().split("-")[0];
        employee.setId(id);
        Employee savedEmployee = employeeRepository.save(employee);
        
        String managerEmail = null;
        if (savedEmployee.getReportsTo() != null) {
            Optional<Employee> managerOptional = employeeRepository.findById(savedEmployee.getReportsTo());
            if (managerOptional.isPresent()) {
                managerEmail = managerOptional.get().getEmail();
            }
        }
        
        if (managerEmail != null) {
            String employeeName = savedEmployee.getEmployeeName();
            String phoneNumber = savedEmployee.getPhoneNumber();
            String emailId = savedEmployee.getEmail();
            emailService.sendEmailToManager(managerEmail, employeeName, phoneNumber, emailId);
        }

        return savedEmployee;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteEmployee(String id) {
        employeeRepository.deleteById(id);
    }

    public Employee updateEmployee(String id, Employee employee) {
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    public Employee getNthLevelManager(String employeeId, int level) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) {
            return null;
        }
        return findNthLevelManager(employee, level);
    }

    private Employee findNthLevelManager(Employee employee, int level) {
        if (level <= 0) {
            return employee;
        }
        if (employee.getReportsTo() == null) {
            return null;
        }
        return findNthLevelManager(employeeRepository.findById(employee.getReportsTo()).orElse(null), level - 1);
    }

    public List<Employee> getEmployeesWithPaginationAndSorting(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return employeeRepository.findAll(pageable).getContent();
    }
}