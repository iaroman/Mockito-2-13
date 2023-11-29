package pro.sky.Mockito23;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    private DatabaseEmployees databaseEmployees = new DatabaseEmployees();
    private DatabaseEmployeesService databaseEmployeesService = new DatabaseEmployeesService();
    public void addEmployee(String name, String soname, int salary, Employee.Department department) {
        databaseEmployees.addEmployee(name, soname, salary, department);
    }
    public void deleteEmployee(String name, String soname) {
        databaseEmployees.deleteEmployee(name, soname);
    }
    public List<Employee> employees(Employee.Department id) {
        return databaseEmployeesService.listByDepartment(databaseEmployees.getAll(), id);
    }
    public Employee findEmployee(String name, String soname) {
        return databaseEmployeesService.findEmployee(databaseEmployees.getAll(), name, soname);
    }
    public int sum(Employee.Department id) {
        return databaseEmployeesService.totalSalaryByDepartment(databaseEmployees.getAll(), id);
    }
    public int max(Employee.Department id) {
        return databaseEmployeesService.withMaxSalary(databaseEmployees.getAll(), id).getSalary();
    }
    public int min(Employee.Department id) {
        return databaseEmployeesService.withMinSalary(databaseEmployees.getAll(), id).getSalary();
    }
    public Map<Employee.Department, List<Employee>> employeesByDepartment() {
        return databaseEmployeesService.groupingByDepartment(databaseEmployees.getAll());
    }
}
