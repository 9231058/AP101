package com.github.autceit.ap101.s02.e01;

public class Lab {
    private Student[] students;
    private double avg;
    private String day;
    private int capacity;
    private int currentSize;

    public Lab(int capacity, String d) {
        this.day = d;
        this.capacity = capacity;
        this.students = new Student[capacity];
    }

    public void enrollStudent(Student std) {
        if (currentSize < capacity) {
            students[currentSize] = std;
            currentSize++;
        } else {
            System.out.println("Lab is Full");
        }
    }

    public void print() {
        System.out.println(this.getDay());
        for (Student student : this.students) {
            if (student != null)
                student.print();
        }
    }

    public Student[] getStudents() {
        return this.students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
        this.capacity = students.length;
        this.currentSize = students.length;
    }

    public double getAvg() {
        this.calculateAvg();
        return this.avg;
    }

    public void calculateAvg() {
        int sum = 0;
        int num = 0;
        for (int i = 0; i < currentSize; i++) {
            sum += this.students[i].getGrade();
            num++;
        }
        this.avg = (double) sum / num;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
