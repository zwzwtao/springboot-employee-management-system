package com.odas.controller;

import com.odas.dao.DepartmentDao;
import com.odas.dao.EmployeeDao;
import com.odas.pojo.Department;
import com.odas.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class EmployeeController {

    // 这里本来是调用Service层，但是我们没有些service所以
    // 先调用DAO层
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps", employees);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model) {
        // 查询所有部门的信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);

        return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        // 这里有一个很神奇的机制，如果我们传了department的id而非department对象的话
        // 竟然会直接根据id解析出department对象
        // 添加员工的操作
        System.out.println("save => " + employee);
        employeeDao.save(employee);     // 调用底层业务方法保存员工信息

        return "redirect:/emps";
    }
}
