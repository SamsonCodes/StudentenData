/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentendatamanager;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Student
{

    private String firstName, lastName;
    private int idNumber;
    private Date birthDate, enrollDate;
    private ArrayList<Course> courses;
    private int studyScore;
    private int year;
    
    public Student(String saveData)
    {
        this.firstName = XMLReader.getAttribute("firstName", saveData);
        this.lastName = XMLReader.getAttribute("lastName", saveData);
        this.idNumber = Integer.parseInt(XMLReader.getAttribute("idNumber", saveData));
        try
        {
            this.birthDate = StudentDataManager.sdf.parse(XMLReader.getAttribute("birthDate", saveData));
            this.enrollDate = StudentDataManager.sdf.parse(XMLReader.getAttribute("enrollDate", saveData));
        }
        catch (ParseException ex)
        {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.studyScore = Integer.parseInt(XMLReader.getAttribute("studyScore", saveData));
        this.year = Integer.parseInt(XMLReader.getAttribute("year", saveData));
        courses = new ArrayList();
        String courseData = XMLReader.stripTags("courses",XMLReader.getElementPlus("courses", saveData));
        //System.out.println(firstName + ": courseData=" + courseData);
        for(String course: courseData.split(","))
        {
            for(Course c: Course.courses)
            {
                if(course.equals(c.getName()))
                {
                    courses.add(c);
                    //System.out.println(firstName + ": added " + c.getName());
                }
            }
        }
        
    }

    public Student(String firstName, String lastName, int idNumber, Date birthDate, Date enrollDate)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.birthDate = birthDate;
        this.enrollDate = enrollDate;
        courses = new ArrayList<>();
        year = 1;
        System.out.println(toString() + " created");
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

    @Override
    public String toString()
    {
        return "Student{" + "firstName=" + firstName + ", lastName=" + lastName + ", idNumber=" + idNumber + ", birthDate=" + birthDate + ", enrollDate=" + enrollDate + ", courses=" + courses + ", studyScore=" + studyScore + ", year=" + year + '}';
    }
    
    public String getSaveData()
    {
        String data = "<student ";
        data += "firstName=\"" + firstName + "\" lastName=\"" + lastName + "\" idNumber=\"" 
                + idNumber + "\" birthDate=\"" + StudentDataManager.sdf.format(birthDate) 
                + "\" enrollDate=\"" + StudentDataManager.sdf.format(enrollDate) 
                + "\" studyScore=\"" + studyScore + "\" year=\"" + year + "\">";
        data += "<courses>";
        for(Course c: courses)
        {
            data+= c.getName() + ",";
        }
        data += "</courses>";
        data += "</student>";
        return data;
    }
    
    
}
