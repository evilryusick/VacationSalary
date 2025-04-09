package org.example;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@RestController
@RequestMapping("/calculate")
public class Controller
{
    @GetMapping
    public double calculate(@RequestParam double averageSalary, int vacationDays, @DateTimeFormat(pattern = "dd.MM.yyyy") Date startDate)
    {
            Calculation newVacation = new Calculation(averageSalary, vacationDays, startDate);
            newVacation.Calculations();
            return newVacation.getVacationPay();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public String handleFormatException()
    {
        return "Wrong parameters.";
    }
}
