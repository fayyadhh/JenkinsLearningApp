package com.example.backend;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class EmployeeController{

    @GetMapping("/api/health")
    public String health(){
        return "ok from jenkins";
    }

    @GetMapping("/api/employees")
    public List<Employee> employees() {
        return List.of(
                new Employee(1L, "Fayyadh", "IT"),
                new Employee(2L, "Fayyadh HR", "HR"),
                new Employee(3L, "Fayyadh Something", "Something"),
                new Employee(4L, "Fayyadh AWS Test", "AWS")
        );
    }
}