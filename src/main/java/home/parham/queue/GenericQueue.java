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

public class GenericQueue<E> {


	ArrayList<E> queue;

	public GenericQueue(){
		queue = new ArrayList<E>();


	}

	public E pop(){
		return queue.remove(0);
//        Person x = queue.get(0);
//        return x;

	}

	public void push(E e){
		queue.add(e);

	}

	public int getSize(){

		return queue.size();
	}

}

