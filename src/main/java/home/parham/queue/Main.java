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

public class Main {
	public static void main(String[] args){
		/*************NonGENERIC******************/
		NonGenericQueue nonGenericQueue = new NonGenericQueue();


		Person p1 = new Person("S");
		Person p2 = new Person("D");
		Person p3 = new Person("A");

		nonGenericQueue.push(p1);
		nonGenericQueue.push(p2);
		nonGenericQueue.push(p3);

		int total = 0;

		for (int i = 0; i < nonGenericQueue.getSize(); i++) {
			int alpha = nonGenericQueue.pop().getTime();
			total += alpha;
			System.out.println(alpha);
		}

		System.out.println("Total time is " + total);

		/*************GENERIC**********************/

		GenericQueue<Person> genericQueue = new GenericQueue<Person>();

		for (int i = 0; i < 10; i++) {
			genericQueue.push(new Person("name " + i));
		}

		for (int i = 0; i < genericQueue.getSize(); i++) {
			int alpha = genericQueue.pop().getTime();
			total += alpha;
			System.out.println(alpha);
			// OR eliminate <Person> and cast for getTime()
		}

		System.out.println("Total time is " + total);

		/*************Standard**********************/

		StandardQueue<Person> standardQueue = new StandardQueue<Person>();

		for (int i = 0; i < 10; i++) {
			standardQueue.add(new Person("name " + i));
		}

		for (int i = 0; i < standardQueue.size(); i++) {
			int alpha = standardQueue.remove().getTime();
			total += alpha;
			System.out.println(alpha);
			// OR eliminate <Person> and cast for getTime()
		}

		System.out.println("Total time is " + total);


	}
}
