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

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Printer {

	private static Lock lock = new ReentrantLock();

	public static void print(String name) {
		lock.lock();
		for (int i = 0; i < 100; i++) {
			System.out.println(name + " " + i);
		}
		lock.unlock();
	}
}
