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
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/*
 * For more information please see queue interface description
 * in the following link:
 * http://ceit.aut.ac.ir/~9231058/java/api/index.html
 */

public class StandardQueue<E> implements Queue<E> {

	private ArrayList<E> queue;

	StandardQueue(){
		queue = new ArrayList<E>();
	}

	@Override
	public int size(){
		return queue.size();
	}

	@Override
	public boolean isEmpty(){
		return queue.isEmpty();
	}

	@Override
	public boolean contains(Object o){
		return queue.contains(o);
	}

	@Override
	public Iterator iterator(){
		return queue.iterator();
	}

	@Override
	public Object[] toArray(){
		return queue.toArray();
	}

	@Override
	public <T> T[] toArray(T[] ts){
		return queue.toArray(ts);
	}

	@Override
	public boolean add(E o){
		return queue.add(o);
	}

	@Override
	public boolean remove(Object o){
		return queue.remove(o);
	}

	@Override
	public boolean addAll(Collection collection){
		return queue.addAll(collection);
	}

	@Override
	public void clear(){
		queue.clear();
	}

	@Override
	public boolean retainAll(Collection collection){
		return queue.retainAll(collection);
	}

	@Override
	public boolean removeAll(Collection collection){
		return queue.removeAll(collection);
	}

	@Override
	public boolean containsAll(Collection collection){
		return queue.containsAll(collection);
	}

	@Override
	public boolean offer(E o){
		return queue.add(o);
	}

	@Override
	public E remove(){
		E x = queue.get(0);
		queue.remove(0);
		return x;
	}

	@Override
	public E poll(){
		E x = queue.get(0);
		queue.remove(0);
		return x;
	}

	@Override
	public E element(){
		return queue.get(0);
	}

	@Override
	public E peek(){
		return queue.get(0);
	}
}
