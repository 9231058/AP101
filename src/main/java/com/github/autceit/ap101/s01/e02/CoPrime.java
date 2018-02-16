/*
 * In The Name Of God
 * ========================================
 * [] File Name : HelloWorld.java
 *
 * [] Creation Date : 16-02-2018
 *
 * [] Created By : Parham Alvani <parham.alvani@gmail.com>
 * =======================================
 */

package com.github.autceit.ap101.s01.e02;

import java.util.Scanner;

public class CoPrime {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int a = input.nextInt();
        int b = input.nextInt();

        int A = a;
        int B = b;

        while (a != 0) {
            int t = a;
            a = b % a;
            b = t;
        }
        if (b == 1) {
            System.out.printf("%d and %d are co-prime integers", A, B);
        } else {
            System.out.printf("%d and %d aren't co-prime integers and have gcd = %d", A, B, b);
        }
    }
}
