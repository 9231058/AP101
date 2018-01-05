/*
 * In The Name Of God
 * ======================================
 * [] Project Name : AP101 
 * 
 * [] Package Name : home.parham.recursion
 *
 * [] Creation Date : 10-03-2015
 *
 * [] Created By : Parham Alvani (parham.alvani@gmail.com)
 * =======================================
*/

package home.parham.recursion;

public class Fibonacci {
	public int getFibonacci(int n){
		if (n == 0 || n == 1)
			return 1;
		return getFibonacci(n - 1) + getFibonacci(n - 2);
	}

}
