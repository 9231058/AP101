/*
 * In The Name Of God
 * ======================================
 * [] Project Name : AP101 
 * 
 * [] Package Name : home.parham.collections
 *
 * [] Creation Date : 10-03-2015
 *
 * [] Created By : Parham Alvani (parham.alvani@gmail.com)
 * =======================================
*/

package home.parham.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {
	public static void main(String args[]){
		List<Integer> pList = new ArrayList<Integer>();
		/* Add number 10 in 10'th index of array */
		pList.add(10, 10);
		/* Without toString */
		System.out.println(pList);
		/* Add number 10 in last index of array */
		pList.add(120);
		/* With toString */
		System.out.println(pList.toString());
		/* Set index 0, 1 in array */
		pList.set(0, -10);
		pList.set(1, -20);
		System.out.println(pList);

		/* Print all the array elements using foreach */
		for (int element : pList) {
			System.out.println(element);
		}

		ArrayList<String> list = new ArrayList<String>();

		/* Adding items to arrayList */
		list.add("Item1");
		list.add("Item2");
		/*  it will add Item3 to the third position of array list */
		list.add(2, "Item3");
		list.add("Item4");

		/* Display the contents of the array list */
		System.out.println("The arraylist contains the following elements: " + list);

		/* Checking index of an item */
		int pos = list.indexOf("Item2");
		System.out.println("The index of Item2 is: " + pos);

		/* Checking if array list is empty */
		boolean check = list.isEmpty();
		System.out.println("Checking if the arraylist is empty: " + check);

		/* Getting the size of the list */
		int size = list.size();
		System.out.println("The size of the list is: " + size);

		/* Checking if an element is included to the list */
		boolean element = list.contains("Item5");
		System.out.println("Checking if the arraylist contains the object Item5: " + element);

		/* Getting the element in a specific position */
		String item = list.get(0);
		System.out.println("The item is the index 0 is: " + item);

		/* Retrieve elements from the arraylist */

		/* 1st way: loop using index and size list */
		System.out.println("Retrieving items with loop using index and size list");
		for (int i = 0; i < list.size(); i++) {
			System.out.println("Index: " + i + " - Item: " + list.get(i));
		}

		/* 2nd way:using foreach loop */
		System.out.println("Retrieving items using foreach loop");
		for (String str : list) {
			System.out.println("Item is: " + str);
		}

		/* 3rd way:using iterator */
		/* hasNext(): returns true if there are more elements */
		/* next(): returns the next element */
		System.out.println("Retrieving items using iterator");
		for (Iterator<String> it = list.iterator(); it.hasNext(); ) {
			System.out.println("Item is: " + it.next());
		}

		/* Replacing an element */
		list.set(1, "NewItem");
		System.out.println("The arraylist after the replacement is: " + list);

		/* Removing items */
		/* removing the item in index 0 */
		list.remove(0);

		/* removing the first occurrence of item "Item3" */
		list.remove("Item3");

		System.out.println("The final contents of the arraylist are: " + list);

		/* Converting ArrayList to Array */
		String[] simpleArray = list.toArray(new String[list.size()]);
		System.out.println("The array created after the conversion of our arraylist is: " + Arrays.toString(simpleArray));

	}
}
