package com.spis.rett.model.search;

import java.util.ArrayList;

public class Parent
{
    private String name;
    private String text1;


     
    // ArrayList to store child objects
    private ArrayList<Child> children;
     
    public String getName()
    {
        return name;
    }
     
    public void setName(String name)
    {
        this.name = name;
    }
    public String getText1()
    {
        return text1;
    }
     
    public void setText1(String text1)
    {
        this.text1 = text1;
    }
     
  
    // ArrayList to store child objects
    public ArrayList<Child> getChildren()
    {
        return children;
    }
     
    public void setChildren(ArrayList<Child> children)
    {
        this.children = children;
    }
}
 