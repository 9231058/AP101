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

import java.util.ArrayList;

public class NonGenericQueue {

	ArrayList<Person> queue;

	public NonGenericQueue(){
		queue = new ArrayList<Person>();


	}

	public Person pop(){
		Person x = queue.get(0);
		queue.remove(0);

		return x;

	}

	public void push(Person p){
		queue.add(p);

	}

	public int getSize(){
		return queue.size();
	}

}
