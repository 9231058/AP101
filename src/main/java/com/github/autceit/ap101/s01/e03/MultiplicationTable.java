package com.github.autceit.ap101.s01.e03;

public class MultiplicationTable {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                System.out.printf("%d x %d = %d\n", i, j , i * j);
            }
        }
    }
}
