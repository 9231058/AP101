package com.github.autceit.ap101.s02.e01;

/**
 * @author lab2
 * @version 1.0.0
 */
public class Student {

    private String firstName;
    private String lastName;
    private String id;
    private int grade;

    public Student(String firstName, String lastName, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.grade = 0;
    }

    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName set the first name of the student
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getGrade() {
        return this.grade;
    }

    public void print() {
        System.out.printf("%s, Student ID: %s, Grade: %d\n", this.lastName, this.id, this.grade);
    }
}
