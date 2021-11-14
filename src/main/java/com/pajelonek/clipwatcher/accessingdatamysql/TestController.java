package com.pajelonek.clipwatcher.accessingdatamysql;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/demo")
public class TestController {

    private final EmployeeMapper repository;

    public TestController(EmployeeMapper repository) {
        this.repository = repository;
    }

    @GetMapping ("/test")
    public String test() {
        return "hello world";
    }

    @PostMapping("/employee")
    public int addEmployee(@RequestBody Employee employee) {
        return repository.insert(employee);
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return repository.findAll();
    }

}