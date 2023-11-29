package pro.sky.Mockito23;

import java.util.*;

public class DatabaseEmployees {
    private Map<String, Employee> databaseEmployees = new HashMap<>();
    public void addEmployee(String name, String soname, int salary, Employee.Department department) {
        String key = name + soname;
        if (databaseEmployees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже работает!");
        }
        Employee employee = new Employee(name, soname, salary, department);
        databaseEmployees.put(key, employee);
    }
    public void deleteEmployee(String name, String soname) {
        String key = name + soname;
        databaseEmployees.remove(key);
    }
    public Map<String, Employee> getAll() {
        return databaseEmployees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatabaseEmployees that = (DatabaseEmployees) o;
        return Objects.equals(databaseEmployees, that.databaseEmployees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(databaseEmployees);
    }

    @Override
    public String toString() {
        return "DatabaseEmployees{" +
                "databaseEmployees=" + databaseEmployees +
                '}';
    }
}
