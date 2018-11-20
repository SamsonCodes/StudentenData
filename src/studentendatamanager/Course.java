/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package studentendatamanager;

import java.util.ArrayList;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Course 
{
    private SimpleStringProperty name, description;
    private SimpleIntegerProperty ECT;
    public static ArrayList<Course> courses;

    public Course(String name, String description, int ECT)
    {
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.ECT = new SimpleIntegerProperty(ECT);
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
        return name.get();
    }

    public String getDescription()
    {
        return description.get();
    }

    public int getECT()
    {
        return ECT.get();
    }
    
    
}
