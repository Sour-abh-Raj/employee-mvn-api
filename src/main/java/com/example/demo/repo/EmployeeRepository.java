package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

	Optional<Employee> findByEmail(String email);
}
