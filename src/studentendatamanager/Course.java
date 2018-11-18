/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package studentendatamanager;

public class Course 
{
    private String name, description;
    private int ECT;

    public Course(String name, String description, int ECT)
    {
        this.name = name;
        this.description = description;
        this.ECT = ECT;
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
