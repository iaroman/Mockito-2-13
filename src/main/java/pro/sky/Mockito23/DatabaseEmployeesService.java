package pro.sky.Mockito23;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DatabaseEmployeesService {
 /*   public static void main(String[] args) {
        DatabaseEmployees testData = new DatabaseEmployees();
        testData.addEmployee("Людовиг", "Четырнадцатый", 30_000, Employee.Department.ONE);
        testData.addEmployee("Ричард", "Третий", 50_000, Employee.Department.ONE);
        testData.addEmployee("Петр", "Первый", 10_000, Employee.Department.ONE);
        testData.addEmployee("Иван", "Грозной", 300_000, Employee.Department.TWO);
        testData.addEmployee("Гарри", "Принц", 350_000, Employee.Department.TWO);
        testData.addEmployee("Александр", "Невский", 500_000, Employee.Department.THREE);
        testData.addEmployee("Джек", "Воробей", 3_000_000, Employee.Department.FOUR);
        System.out.println(testDatabaseEmployees);

        DatabaseEmployeesService databaseEmployeesService = new DatabaseEmployeesService();
        System.out.println(databaseEmployeesService.listByDepartment(
                testDatabaseEmployees.getAll(),
                Employee.Department.FOUR
        ));

        System.out.println(databaseEmployeesService.withMaxSalary(
                testData.getAll(),
                Employee.Department.ONE
        ));
        System.out.println(databaseEmployeesService.totalSalaryByDepartment(testData.getAll(), Employee.Department.TWO));

        System.out.println(databaseEmployeesService.groupingByDepartment(testData.getAll()));
    }*/
    public Employee findEmployee(Map<String, Employee> databaseEmployees, String name, String soname) {
        String key = name + soname;
        return databaseEmployees.get(key);
    }
    public Employee withMinSalary(Map<String, Employee> databaseEmployees, Employee.Department department) {
        Employee min = databaseEmployees.values()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .min(Employee::compare)
                .get();
        return min;
    }
    public Employee withMaxSalary(Map<String, Employee> databaseEmployees, Employee.Department department) {
        Employee max = databaseEmployees.values()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .max(Employee::compare)
                .get();
        return max;
    }
    public int totalSalaryByDepartment(Map<String, Employee> databaseEmployees, Employee.Department department) {
        List<Employee> employeeList = databaseEmployees.values()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
        int total = 0;
        for (Employee employee : employeeList) {
            total += employee.getSalary();
        }
        return total;
    }
    public List<Employee> listByDepartment(Map<String, Employee> databaseEmployees, Employee.Department department) {
        List<Employee> employeeList = databaseEmployees.values()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
        return employeeList;

    }
    public Map<Employee.Department,List<Employee>> groupingByDepartment(Map<String, Employee> databaseEmployees) {
        Map<Employee.Department, List<Employee>> employeeList = databaseEmployees.values()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        return employeeList;
    }
}
