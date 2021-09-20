package com.odas.dao;

import com.odas.pojo.Department;
import com.odas.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

// 员工dao
@Repository
public class EmployeeDao {

    // 模拟数据库中的数据
    private static Map<Integer, Employee> employees = null;
    // 员工所属的部门
    @Autowired
    private DepartmentDao departmentDao;

    // 静态模块先加载所以这里其实先一步于@Autowired加载，所以不能在里面调用departmentDao对象
    static {
        // 创建一个员工表
        employees = new HashMap<Integer, Employee>();

        employees.put(101, new Employee(1001, "AA", "123@gmail.com", 1, new Department(101, "科学部")));
        employees.put(102, new Employee(1002, "AA", "456@gmail.com", 0, new Department(101, "科学部")));
        employees.put(103, new Employee(1003, "AA", "789@gmail.com", 1, new Department(101, "科学部")));
        employees.put(104, new Employee(1004, "AA", "135@gmail.com", 0, new Department(101, "科学部")));
        employees.put(105, new Employee(1005, "AA", "267@gmail.com", 1, new Department(101, "科学部")));
    }

    // 主键自增!
    private static Integer initId = 1006;

    // 增加一个员工
    public void save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId ++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));

        employees.put(employee.getId(), employee);
    }

    // 查询全部员工信息
    public Collection<Employee> getAll() {
        return employees.values();
    }

    // 通过id查询员工
    public Employee getEmployeeById(Integer id) {
        return employees.get(id);
    }

    // 通过id删除员工
    public void delete(Integer id) {
        employees.remove(id);
    }

}
