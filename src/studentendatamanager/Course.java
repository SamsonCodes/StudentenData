/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package studentendatamanager;

import java.util.ArrayList;

public class Course 
{
    private String name, description;
    private int ECT;
    public static ArrayList<Course> courses;

    public Course(String name, String description, int ECT)
    {
        this.name = name;
        this.description = description;
        this.ECT = ECT;
    }
    
    public static void load(String path)
    {
        courses = new ArrayList();
        ArrayList<String> courseStrings = DataHandler.loadDataList(path);
        for(String courseString: courseStrings)
        {
            String[] courseData = courseString.split(",");
            courses.add(new Course(courseData[0], courseData[1], Integer.parseInt(courseData[2])));
        }
    }
            

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public int getECT()
    {
        return ECT;
    }
    
    
}
