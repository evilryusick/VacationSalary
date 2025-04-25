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
    public double calculate(@RequestParam double averageSalary, @RequestParam int vacationDays, @DateTimeFormat(pattern = "dd.MM.yyyy") Date startDate) throws NegativeParamsException
    {
        if ((averageSalary < 0) || (vacationDays < 0))
            throw new NegativeParamsException("Negative parameters.");
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

    public static class NegativeParamsException extends Exception
    {
        public NegativeParamsException (String message)
        {
            super(message);
        }
    }
}
