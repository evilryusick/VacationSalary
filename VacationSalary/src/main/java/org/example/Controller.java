package org.example;

import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.example.Calculation.Calculations;

@RestController
@RequestMapping("/calculate")
public class Controller
{
    final int months = 12;
    final double averageMonthsDay = 29.3;

    @GetMapping
    public double shows(@RequestBody Data data)
    {
        double result = Calculations(data.averageSalary, data.vacationDays, months, averageMonthsDay);
        BigDecimal round = new BigDecimal(result);
        round = round.setScale(3, RoundingMode.DOWN);
        return round.doubleValue();
    }

    public static class Data
    {
        public double averageSalary;
        public int vacationDays;
    }
}
