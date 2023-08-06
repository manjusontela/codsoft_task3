package codsoft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Student {
     
	private String name;
    private int age;
    private String course;
    private int rollno;

    public Student(String name, int age, String course,int rollno) {
        this.name = name;
        this.age = age;
        this.course = course;
        this.rollno=rollno;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCourse() {
        return course;
    }
    public int getrollno() {
    	return rollno;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCourse(String course) {
        this.course = course;
    }
     public void setrollno(int rollno) {
    	 this.rollno=rollno;
     }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Course: " + course +",Rollno: "+ rollno;
    }
}


