package pro.sky.Mockito23;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// непроверяемое исключение
//@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Такой сотрудник уже работает!")
public class EmployeeAlreadyAddedException extends RuntimeException{
    public EmployeeAlreadyAddedException(String massage) {
        super(massage);
    }
}
