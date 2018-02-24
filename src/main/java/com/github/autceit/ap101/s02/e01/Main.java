package com.github.autceit.ap101.s02.e01;

public class Main {
    public static void main(String[] args) {
        Student std1 = new Student("Ehsan",
                "Edalat","9031066");
        Student std2 = new Student("Parham",
                "Alvani", "9231058");
        Student std3 = new Student("Iman",
                "Tabrizian", "9331032");

        std1.print();
        std1.setGrade(12);
        std1.print();

        std2.print();
        std2.setGrade(11);
        std2.print();

        std3.print();
        std3.setFirstName("Hamid Reza");
        std3.print();

        Lab l1 = new Lab(2, "Saturday");
        l1.enrollStudent(std1);
        l1.enrollStudent(std2);
        l1.enrollStudent(std3);
        l1.print();
        System.out.printf("%g\n", l1.getAvg());
    }
}
