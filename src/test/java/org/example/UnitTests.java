package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTests
{
    @Test
    public void calculationTest()
    {
        Calendar cal = Calendar.getInstance();
        cal.set(2024, 0, 1);
        Calculation newVacation = new Calculation(360000, 14, cal.getTime());
        newVacation.Calculations();
        double result = newVacation.getVacationPay();
        assertEquals(3942.5, result, 0.001);
    }

    @Test
    public void calculateTest()
    {
        Controller cont = new Controller();
        Date date = new Date();
        date.setDate(01);
        date.setMonth(01);
        date.setYear(1996);
        try
        {
            cont.calculate(-360000, -12, date);
        }
        catch (Controller.NegativeParamsException neg)
        {
            System.out.println(neg);
        }
    }
}
