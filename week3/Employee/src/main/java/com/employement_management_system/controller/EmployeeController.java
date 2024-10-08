package com.employement_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public class EmployeeController {
      @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
        Employee employee=employeeService.getEmployeeById(id);
        if(employee!=null){
            employee.setName(employeeDetails.getName());
            employee.setEmail(employeeDetails.getEmail());
            employee.setDepartment(employeeDetails.getDepartment());
            return employeeService.saveEmployee(employee);
        }
        return null;
    }
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id){
        Employee employee=employeeService.getEmployeeById(id);
        if(employee!=null){
            employeeService.deleteEmployee(id);
        }
    }
    @GetMapping("/department/{departmentId}")
    public Page<Employee> getEmployeesByDepartmentId(
        @PathVariable Long deptId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy){
            Pageable pageable=PageRequest.of(page, size, Sort.by(sortBy));
            return employeeService.getEmployeesByDepartmentId(deptId,pageable);
    }
}
    
}
