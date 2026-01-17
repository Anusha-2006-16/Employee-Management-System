package com.employee.Employee_Management.controller;

import com.employee.Employee_Management.entity.Employee;
import com.employee.Employee_Management.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String employeesPage(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employeeList";
    }
    @GetMapping("/add")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "AddEmployee";
    }
    @PostMapping("/add")
    public String saveEmployee(@ModelAttribute Employee employee){
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }
    @GetMapping("/view/{id}")
    public String viewEmployee(Model model, @PathVariable Long id){
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "ViewEmployee";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(Model model, @PathVariable Long id){
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return  "EditEmployee";
    }
    @PostMapping("/edit/{id}")
    public String editEmployee(@ModelAttribute Employee employee, @PathVariable Long id){
        employeeService.updateEmployee(employee,id);
        return "redirect:/employees";
    }
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
}
