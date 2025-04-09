package org.example;

import org.junit.jupiter.api.Test;
import java.util.Calendar;
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
}
