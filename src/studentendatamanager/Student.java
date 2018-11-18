/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentendatamanager;

import java.util.ArrayList;

public class Student
{

    private String firstName, lastName;
    private int idNumber;
    private Date birthDate, enrollDate;
    private ArrayList<Course> courses;
    private int studyScore;
    private int year;

    public Student(String firstName, String lastName, int idNumber, Date birthDate, Date enrollDate)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.birthDate = birthDate;
        this.enrollDate = enrollDate;
        courses = new ArrayList<>();
    }

    public String getFirstName()
    {
        return firstName;
    }

    public int getIdNumber()
    {
        return idNumber;
    }

    public Date getBirthDate()
    {
        return birthDate;
    }

    public Date getEnrollDate()
    {
        return enrollDate;
    }

    public ArrayList<Course> getCourses()
    {
        return courses;
    }

    public void addCourse(Course c)
    {
        courses.add(c);
    }

    public int getStudyScore()
    {
        return studyScore;
    }

    public void setStudyScore(int studyScore)
    {
        this.studyScore = studyScore;
    }

    public void addStudyScore(int amount)
    {
        studyScore += amount;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }
}
