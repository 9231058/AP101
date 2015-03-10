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
	}
}
