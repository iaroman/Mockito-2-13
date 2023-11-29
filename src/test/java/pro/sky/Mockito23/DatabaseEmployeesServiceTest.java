package pro.sky.Mockito23;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseEmployeesServiceTest {
    private DatabaseEmployees testData;
    Map<String, Employee> testMap = new HashMap<>();
    DatabaseEmployeesService testService;
    @BeforeEach
    void setUp() {
        testService = new DatabaseEmployeesService();
        testData = new DatabaseEmployees();
        testData.addEmployee("Людовиг", "Четырнадцатый", 30_000, Employee.Department.ONE);
        testData.addEmployee("Ричард", "Третий", 50_000, Employee.Department.ONE);
        testData.addEmployee("Петр", "Первый", 10_000, Employee.Department.ONE);
        testData.addEmployee("Иван", "Грозной", 300_000, Employee.Department.TWO);
        testData.addEmployee("Гарри", "Принц", 350_000, Employee.Department.TWO);
        testData.addEmployee("Александр", "Невский", 500_000, Employee.Department.THREE);
        testData.addEmployee("Джек", "Воробей", 3_000_000, Employee.Department.FOUR);
        testData.addEmployee("Иван", "Грозный", 3_000, Employee.Department.THREE);
        testData.addEmployee("Джорж", "Буш", 3_000, Employee.Department.FOUR);
    }
    @AfterEach
    void tearDown() {

    }
    public static Stream<Arguments> provideParamsForTestFind() {
        return Stream.of(
                Arguments.of("Людовиг", "Четырнадцатый", 30_000, Employee.Department.ONE),
                Arguments.of("Ричард", "Третий", 50_000, Employee.Department.ONE),
                Arguments.of("Петр", "Первый", 10_000, Employee.Department.ONE),
                Arguments.of("Иван", "Грозной", 300_000, Employee.Department.TWO),
                Arguments.of("Гарри", "Принц", 350_000, Employee.Department.TWO),
                Arguments.of("Александр", "Невский", 500_000, Employee.Department.THREE),
                Arguments.of("Джек", "Воробей", 3_000_000, Employee.Department.FOUR),
                Arguments.of("ДЖЕКИ", "ЧАН", 3_000_000, Employee.Department.FOUR),
                Arguments.of("СИНЯЯ", "БОРОДА", 3_000_000, Employee.Department.FOUR)
        );
    }
    @ParameterizedTest
    @MethodSource("provideParamsForTestFind")
    void findEmployee(String name, String soname, int salary, Employee.Department department) {
        String key = name + soname;
        if (testData.getAll().containsKey(key)) {
            Employee expected = testService.findEmployee(testData.getAll(), name, soname);
            Employee actual = new Employee(name, soname, salary, department);
            assertEquals(expected, actual);
        } else {
            assertNull(testService.findEmployee(testData.getAll(), name, soname));
        }
    }
    public static Stream<Arguments> provideParamsForTestMin() {
        return Stream.of(
                Arguments.of(Employee.Department.ONE, 10_000),
                Arguments.of(Employee.Department.TWO, 300_000),
                Arguments.of(Employee.Department.THREE, 3_000),
                Arguments.of(Employee.Department.FOUR, 3_000)
        );
    }
    @ParameterizedTest
    @MethodSource("provideParamsForTestMin")
    void withMinSalary(Employee.Department department, int actualMin) {
        int expected = testService.withMinSalary(testData.getAll(), department).getSalary();
        assertEquals(expected, actualMin);
    }
    public static Stream<Arguments> provideParamsForTestMax() {
        return Stream.of(
                Arguments.of(Employee.Department.ONE, 50_000),
                Arguments.of(Employee.Department.TWO, 350_000),
                Arguments.of(Employee.Department.THREE, 500_000),
                Arguments.of(Employee.Department.FOUR, 3_000_000)
        );
    }
    @ParameterizedTest
    @MethodSource("provideParamsForTestMax")
    void withMaxSalary(Employee.Department department, int actualMax) {
        int expected = testService.withMaxSalary(testData.getAll(), department).getSalary();
        assertEquals(expected, actualMax);
    }
    public static Stream<Arguments> provideParamsForTestTotal() {
        return Stream.of(
                Arguments.of(Employee.Department.ONE, 90_000),
                Arguments.of(Employee.Department.TWO, 650_000),
                Arguments.of(Employee.Department.THREE, 503_000),
                Arguments.of(Employee.Department.FOUR, 3_003_000)
        );
    }
    @ParameterizedTest
    @MethodSource("provideParamsForTestTotal")
    void totalSalaryByDepartment(Employee.Department department, int actualTotal) {
        int expected = testService.totalSalaryByDepartment(testData.getAll(), department);
        assertEquals(expected, actualTotal);
    }
    public static Stream<Arguments> provideParamsForTestDepartment() {
        return Stream.of(
                Arguments.of(Employee.Department.ONE),
                Arguments.of(Employee.Department.TWO),
                Arguments.of(Employee.Department.THREE),
                Arguments.of(Employee.Department.FOUR)
        );
    }
    @ParameterizedTest
    @MethodSource("provideParamsForTestDepartment")
    void listByDepartment(Employee.Department department) {
        List<Employee> testList = testService.listByDepartment(testData.getAll(), department);
        for (Employee employee : testList) {
            assertEquals(employee.getDepartment(), department);
        }
    }

    @Test
    void groupingByDepartment() {
        Map<Employee.Department,List<Employee>> testMap = testService.groupingByDepartment(testData.getAll());
        for (Map.Entry<Employee.Department, List<Employee>> map : testMap.entrySet()) {
            for (Employee employee : map.getValue()) {
                assertEquals(map.getKey(), employee.getDepartment());
            }
        }
    }
}