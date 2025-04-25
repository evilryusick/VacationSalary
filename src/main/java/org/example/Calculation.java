package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Calculation
{
    public Calculation(double averageSalary, int vacationDays, Date date)
    {
        this.averageSalary = averageSalary;
        this.vacationDays = vacationDays;
        if(date != null)
        {
            startDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
    }
    // Variables for calculations
    private final double averageSalary;
    private final int vacationDays;
    private LocalDate startDate;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
    private double vacationPay;

    public double getVacationPay()
    {
        return vacationPay;
    }

    public void Calculations()
    {
        // When the date is not provided, then calculations are made using a simple formula
        if (startDate == null)
        {
            final double averageMonthsDay = 29.3;
            final int months = 12;
            vacationPay = averageSalary / months / averageMonthsDay * vacationDays;
            BigDecimal result = new BigDecimal(vacationPay);
            vacationPay = (result.setScale(2, RoundingMode.DOWN)).doubleValue();
        }
        else
        {
            final String[] publicHolidays = {"01.01.2024", "02.01.2024", "03.01.2024", "04.01.2024", "05.01.2024",
                                             "06.01.2024", "07.01.2024", "08.01.2024", "23.02.2024", "08.03.2024",
                                             "01.05.2024", "09.05.2024", "12.06.2024", "04.11.2024"};
            int excessiveDaysCount = 0;
            final double averageYearDays = 365.25;

            // Not counting the weekend days and public holidays
            for (int count = 0; count < vacationDays; count++)
            {
                if((startDate.getDayOfWeek() == DayOfWeek.SATURDAY)||(startDate.getDayOfWeek() == DayOfWeek.SUNDAY))
                {
                    excessiveDaysCount++;
                }
                else
                {
                    for (String startDateString : publicHolidays)
                    {
                        if (startDate.isEqual(LocalDate.parse(startDateString, formatter)))
                        {
                            excessiveDaysCount++;
                        }
                    }
                }
                startDate = startDate.plusDays(1);
            }

            vacationPay = averageSalary / averageYearDays * (vacationDays - excessiveDaysCount);
            BigDecimal result = new BigDecimal(vacationPay);
            vacationPay = (result.setScale(2, RoundingMode.DOWN)).doubleValue();
        }
    }
}
