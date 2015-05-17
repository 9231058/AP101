/*
 * In The Name Of God
 * ======================================
 * [] Project Name : AP101 
 * 
 * [] Package Name : home.parham.multithreading
 *
 * [] Creation Date : 17-05-2015
 *
 * [] Created By : Parham Alvani (parham.alvani@gmail.com)
 * =======================================
*/

package home.parham.multithreading;

public class Main {
	public static void main(String args[]) {

		(new Thread(new MyThread1())).start();
		(new Thread(new MyThread2())).start();
	}
}
