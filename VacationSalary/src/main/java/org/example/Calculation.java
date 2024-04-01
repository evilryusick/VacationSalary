package org.example;

public class Calculation
{
    public static double Calculations(double averageSalary,
                                      int vacationDays,
                                      int months,
                                      double averageMonthsDay)
    {
        return averageSalary / months / averageMonthsDay * vacationDays;
    }
}
