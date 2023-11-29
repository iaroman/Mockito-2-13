package pro.sky.Mockito23;

import net.bytebuddy.asm.MemberSubstitution;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseEmployeesTest {
    Employee employee1;
    Employee employee2;
    Employee employee3;
    Employee employee4;
    Employee employee5;
    Employee employee6;
    Employee employee7;
    @BeforeEach
    void setUp() {
        employee1 = new Employee("Людовиг", "Четырнадцатый", 30_000, Employee.Department.ONE);
        employee2 = new Employee("Ричард", "Третий", 50_000, Employee.Department.ONE);
        employee3 = new Employee("Петр", "Первый", 10_000, Employee.Department.ONE);
        employee4 = new Employee("Иван", "Грозной", 300_000, Employee.Department.TWO);
        employee5 = new Employee("Гарри", "Принц", 350_000, Employee.Department.TWO);
        employee6 = new Employee("Александр", "Невский", 500_000, Employee.Department.THREE);
        employee7 = new Employee("Джек", "Воробей", 3_000_000, Employee.Department.FOUR);
    }
    public static Stream<Arguments> provideParamsForTestDeleteEmployee() {
        return Stream.of(
                Arguments.of("Людовиг", "Четырнадцатый", 30_000, Employee.Department.ONE),
                Arguments.of("Ричард", "Третий", 50_000, Employee.Department.ONE),
                Arguments.of("Петр", "Первый", 10_000, Employee.Department.ONE),
                Arguments.of("Александр", "Невский", 500_000, Employee.Department.THREE)
        );
    }
    @ParameterizedTest
    @MethodSource("provideParamsForTestDeleteEmployee")
    void addEmployee(String name, String soname, int salary, Employee.Department department) {
        DatabaseEmployees expectedDatabaseEmployees = new DatabaseEmployees();
        expectedDatabaseEmployees.addEmployee(name, soname, salary, department);
        String key = name + soname;
        assertTrue(expectedDatabaseEmployees.getAll().containsKey(key));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTestDeleteEmployee")
    public void deleteEmployee(String name, String soname, int salary, Employee.Department department) {
        DatabaseEmployees expectedDatabaseEmployees = new DatabaseEmployees();
        expectedDatabaseEmployees.addEmployee(name, soname, salary, department);
        String key = name + soname;
        assertTrue(expectedDatabaseEmployees.getAll().containsKey(key));
        expectedDatabaseEmployees.deleteEmployee(name, soname);
        assertFalse(expectedDatabaseEmployees.getAll().containsKey(key));
    }

    @Test
    void getAll() {
        DatabaseEmployees expectedDatabaseEmployees = new DatabaseEmployees();
        expectedDatabaseEmployees.addEmployee("Людовиг", "Четырнадцатый", 30_000, Employee.Department.ONE);
        expectedDatabaseEmployees.addEmployee("Ричард", "Третий", 50_000, Employee.Department.ONE);
        expectedDatabaseEmployees.addEmployee("Петр", "Первый", 10_000, Employee.Department.ONE);
        expectedDatabaseEmployees.addEmployee("Иван", "Грозной", 300_000, Employee.Department.TWO);
        expectedDatabaseEmployees.addEmployee("Гарри", "Принц", 350_000, Employee.Department.TWO);
        expectedDatabaseEmployees.addEmployee("Александр", "Невский", 500_000, Employee.Department.THREE);
        expectedDatabaseEmployees.addEmployee("Джек", "Воробей", 3_000_000, Employee.Department.FOUR);

        Map<String, Employee> expected = expectedDatabaseEmployees.getAll();

        Map<String, Employee> actualDatabaseEmployees = new HashMap<>();
        actualDatabaseEmployees.put(employee1.getName()+employee1.getSoname(), employee1);
        actualDatabaseEmployees.put(employee2.getName()+employee2.getSoname(), employee2);
        actualDatabaseEmployees.put(employee3.getName()+employee3.getSoname(), employee3);
        actualDatabaseEmployees.put(employee4.getName()+employee4.getSoname(), employee4);
        actualDatabaseEmployees.put(employee5.getName()+employee5.getSoname(), employee5);
        actualDatabaseEmployees.put(employee6.getName()+employee6.getSoname(), employee6);
        actualDatabaseEmployees.put(employee7.getName()+employee7.getSoname(), employee7);


        assertEquals(expected, actualDatabaseEmployees);
    }
}