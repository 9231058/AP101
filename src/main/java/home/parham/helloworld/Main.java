/*
 * In The Name Of God
 * ======================================
 * [] Project Name : AP101 
 * 
 * [] Package Name : home.parham.helloworld
 *
 * [] Creation Date : 10-03-2015
 *
 * [] Created By : Parham Alvani (parham.alvani@gmail.com)
 * =======================================
*/

package home.parham.helloworld;

import java.util.Scanner;

public class Main {
	public static void main(String args[]){
		String hello1 = "Hello world of Java";
		String hello2 = "HELLO WORLD of JAVA";

		if (hello1.equalsIgnoreCase(hello2)) {
			System.out.println("Hellos are equal without case");
		} else {
			System.out.println("Hellos are not equal without case");
		}
		if (hello1.equals(hello2)) {
			System.out.println("Hellos are equal with case");
		} else {
			System.out.println("Hellos are not equal with case");
		}

		/* Input reading ... */
		Scanner cin = new Scanner(System.in);
		System.out.println(cin.next());
		System.out.println(cin.nextInt());
	}
}
