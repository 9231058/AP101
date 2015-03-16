/*
 * In The Name Of God
 * ======================================
 * [] Project Name : AP101 
 * 
 * [] Package Name : home.parham.queue
 *
 * [] Creation Date : 16-03-2015
 *
 * [] Created By : Parham Alvani (parham.alvani@gmail.com)
 * =======================================
*/

package home.parham.queue;

import java.util.Random;

public class Person {
	private int time = 0;
	private String name = "";

	public Person(String name){
		this.name = name;
		Random rn = new Random();
		time = Math.abs(rn.nextInt(10));
	}

	public int getTime(){
		return time;
	}


	public String getName(){
		return name;
	}
}
