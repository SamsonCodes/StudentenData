/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentendatamanager;

public class Date
{

    private int day, month, year;

    public Date(int day, int month, int year)
    {
        this.day = cap(day, 1, 31);
        this.month = cap(month, 1, 12);
        this.year = cap(year, 1850, 2020);
    }

    public int getDay()
    {
        return day;
    }

    public int getMonth()
    {
        return month;
    }

    public int getYear()
    {
        return year;
    }
    
    public static Date getDefaultDate()
    {
        return new Date(1, 1, 1950);
    }
    
    private int cap(int value, int min, int max)
    {
        if(value < min)
            return min;
        else if(value > max)
            return max;
        else
            return value;
    }
}
