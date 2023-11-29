package pro.sky.Mockito23;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
/* Тесты
http://localhost:8080/department/add?name=Петр&soname=Первый&salary=1000&department=ONE

http://localhost:8080/department/ONE/employees

http://localhost:8080/department/TWO/employees

http://localhost:8080/department/employees
 */
@RestController
@RequestMapping("/department")
public class ApplicationController {
    private final ApplicationService applicationService;
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }
    @GetMapping("/add")
    public void addEmployee(@RequestParam String name, @RequestParam String soname, @RequestParam int salary, @RequestParam Employee.Department department) {
        applicationService.addEmployee(name, soname, salary, department);
    }
    @GetMapping("/del")
    public void deleteEmployee(@RequestParam String name, @RequestParam String soname) {
        applicationService.deleteEmployee(name, soname);
    }
    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String name, @RequestParam String soname) {
        return applicationService.findEmployee(name, soname);
    }
    @GetMapping("/{id}/employees")
    public List<Employee> employees(@PathVariable Employee.Department id) {
        return applicationService.employees(id);
    }
    @GetMapping("/{id}/salary/sum")
    public int sum(@PathVariable Employee.Department id) {
        return applicationService.sum(id);
    }
    @GetMapping("/{id}/salary/max")
    public int max(@PathVariable Employee.Department id) {
        return applicationService.max(id);
    }
    @GetMapping("/{id}/salary/min")
    public int min(@PathVariable Employee.Department id) {
        return applicationService.min(id);
    }
    @GetMapping("/employees")
    public Map<Employee.Department, List<Employee>> employeesByDepartment() {
        return applicationService.employeesByDepartment();
    }
    @GetMapping("/testADD")
    public void testAdd() {
        applicationService.addEmployee("Людовиг", "Четырнадцатый", 30_000, Employee.Department.ONE);
        applicationService.addEmployee("Ричард", "Третий", 50_000, Employee.Department.ONE);
        applicationService.addEmployee("Петр", "Первый", 10_000, Employee.Department.ONE);
        applicationService.addEmployee("Иван", "Грозной", 300_000, Employee.Department.TWO);
        applicationService.addEmployee("Гарри", "Принц", 350_000, Employee.Department.TWO);
        applicationService.addEmployee("Александр", "Невский", 500_000, Employee.Department.THREE);
        applicationService.addEmployee("Джек", "Воробей", 3_000_000, Employee.Department.FOUR);
    }
    @GetMapping("/testDEL")
    public void testDel() {
        applicationService.deleteEmployee("Ричард", "Третий");
        applicationService.deleteEmployee("Петр", "Первый");
        applicationService.deleteEmployee("Иван", "Грозной");
        applicationService.deleteEmployee("Гарри", "Принц");
        applicationService.deleteEmployee("Александр", "Невский");
    }
}
