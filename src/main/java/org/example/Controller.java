package org.example;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/calculate")
public class Controller
{
    @PostMapping
    public double shows(@RequestBody Data data)
    {
        Calculation newVacation = new Calculation(data.averageSalary, data.vacationDays, data.date);
        newVacation.Calculations();
        return newVacation.getVacationPay();
    }
    // For storing the query parameters
    public static class Data
    {
        public double averageSalary;
        public int vacationDays;
        public String date = null;
    }
}
