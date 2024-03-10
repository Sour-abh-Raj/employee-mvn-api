package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

 @Autowired
 private EmployeeService employeeService;

 @PostMapping
 public Employee addEmployee(@RequestBody Employee employee) {
     return employeeService.addEmployee(employee);
 }

 @GetMapping
 public List<Employee> getAllEmployees() {
     return employeeService.getAllEmployees();
 }

 @DeleteMapping("/{id}")
 public void deleteEmployee(@PathVariable String id) {
     employeeService.deleteEmployee(id);
 }

 @PutMapping("/{id}")
 public Employee updateEmployee(@PathVariable String id, @RequestBody Employee employee) {
     return employeeService.updateEmployee(id, employee);
 }

 @GetMapping("/managers/{employeeId}")
 public Employee getNthLevelManager(@PathVariable String employeeId, @RequestParam int level) {
     return employeeService.getNthLevelManager(employeeId, level);
 }

 @GetMapping("/paginate")
 public List<Employee> getEmployeesWithPaginationAndSorting(@RequestParam int page,
                                                             @RequestParam int size,
                                                             @RequestParam String sortBy) {
     return employeeService.getEmployeesWithPaginationAndSorting(page, size, sortBy);
 }
}
