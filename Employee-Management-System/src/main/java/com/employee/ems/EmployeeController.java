package com.employee.ems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Employee> employees = repository.findAll();
        model.addAttribute("employees", employees);
        return "index";
    }

    @PostMapping("/save")
    public String saveEmployee(Employee employee) {
        repository.save(employee);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable int id) {
        repository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable int id, Model model) {
        Employee employee = repository.findById(id).orElse(null);
        model.addAttribute("employee", employee);
        return "edit";
    }

    @PostMapping("/update")
    public String updateEmployee(Employee employee) {
        repository.save(employee);
        return "redirect:/";
    }


@GetMapping("/search")
public String searchEmployee(@RequestParam String keyword, Model model) {

    List<Employee> employees = repository.findByNameContaining(keyword);

    model.addAttribute("employees", employees);
    model.addAttribute("totalEmployees", employees.size());

    return "index";
}
}