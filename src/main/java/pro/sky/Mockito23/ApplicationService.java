package pro.sky.Mockito23;

import java.util.List;
import java.util.Map;

public interface ApplicationService {
    void addEmployee(String name, String soname, int salary, Employee.Department department);
    void deleteEmployee(String name, String soname);
    Employee findEmployee(String name, String soname);
    List<Employee> employees(Employee.Department id);
    int sum(Employee.Department id);
    int max(Employee.Department id);
    int min(Employee.Department id);
    Map<Employee.Department, List<Employee>> employeesByDepartment();
}
