package org.example;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
public class Calculation {
    public Calculation(double averageSalary, int vacationDays, String date){
        this.averageSalary = averageSalary;
        this.vacationDays = vacationDays;
        if(date != null)
        {
            startDate = LocalDate.parse(date, formatter);
        }
    }//Инициализация экземпляра с датой начала
    private final double averageSalary;//Средняя зарплата
    private final int vacationDays;//Количество дней отпуска
    private LocalDate startDate;//Дата начала отпуска

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);;//Объявление и инициализация формата парсируемых дат
    private double vacationPay;//Итоговое количество отпускных
    public double getVacationPay() {
        return vacationPay;
    }//Вывод отпускных
    public void Calculations(){
        if (startDate == null)
        {
            final double averageMonthsDay = 29.3;//Среднее количество дней в месяце
            final int months = 12;//Количество месяцев в году
            vacationPay = averageSalary / months / averageMonthsDay * vacationDays;
            BigDecimal result = new BigDecimal(vacationPay);
            vacationPay = (result.setScale(2, RoundingMode.DOWN)).doubleValue();
        }//Рассчет отпускных без даты начала, по простой схеме
        else
        {
            final String[] publicHolidays = {"01.01.2024", "02.01.2024", "03.01.2024", "04.01.2024", "05.01.2024",//Массив содержащий даты праздничных дней
                                             "06.01.2024", "07.01.2024", "08.01.2024", "23.02.2024", "08.03.2024",
                                             "01.05.2024", "09.05.2024", "12.06.2024", "04.11.2024"};
            int vacationDaysCount = 0;
            for (int count = 0; count < vacationDays; count++) {
                vacationPay+=100;
                if((startDate.getDayOfWeek() == DayOfWeek.SATURDAY)||(startDate.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                    vacationDaysCount++;
                }//Выявление выходных дней
                else {
                    for (String startDateString : publicHolidays)
                    {
                        if (startDate.isEqual(LocalDate.parse(startDateString, formatter)))
                        {
                            vacationDaysCount++;
                        }
                    }
                }//Выявление праздничных дней
                startDate = startDate.plusDays(1);//Итерация на следующий день
            }//Цикл проверки отпускных дат на выходные и праздничные
            final double averageYearDays = 365.25;
            vacationPay = averageSalary / averageYearDays * (vacationDays - vacationDaysCount);
            BigDecimal result = new BigDecimal(vacationPay);
            vacationPay = (result.setScale(2, RoundingMode.DOWN)).doubleValue();
        }//Рассчет отпускных с датой начала, вычитая все праздничные и выходные дни
    }//Метод вычисления отпускных
}
