package pro.sky.Mockito23;

import java.util.Objects;

public class Employee {
    private String soname, name;
    private int salary;
    public enum Department {
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE
    }
    private Department department;
    static private int counter;
    private int id;
    public Employee(String name, String soname, int salary, Department department) {
        this.name = name;
        this.soname = soname;
        this.salary = salary;
        this.department = department;
        id = ++counter;
    }
    public String getSoname() {
        return soname;
    }
    public void setSoname(String soname) {
        this.soname = soname;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        return soname + " " + name + ", зарплата: " + salary + ", отдел: " + department;
    }
    public String getEmployee() {
        return soname + " " + name + ", зарплата: " + salary;
    }
    public static int compare (Employee e1, Employee e2) {
        if (e1.getSalary() > e2.getSalary())
            return 1;
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return salary == employee.salary && Objects.equals(soname, employee.soname) && Objects.equals(name, employee.name) && department == employee.department;
    }

    @Override
    public int hashCode() {
        return Objects.hash(soname, name, salary, department);
    }
}