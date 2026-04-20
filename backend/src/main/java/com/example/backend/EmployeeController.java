package com.example.backend;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController{

    @GetMapping("/api/health")
    public String health(){
        return "ok";
    }

    @GetMapping("/api/employees")
    public List<Employee> employees() {
        return List.of(
                new Employee(1L, "Fayyadh", "IT"),
                new Employee(2L, "Fayyadh HR", "HR"),
                new Employee(3L, "Fayyadh Something", "Something")
        );
    }
}