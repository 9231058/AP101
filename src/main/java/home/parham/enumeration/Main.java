/*
 * In The Name Of God
 * ======================================
 * [] Project Name : AP101 
 * 
 * [] Package Name : home.parham.enumeration
 *
 * [] Creation Date : 11-03-2015
 *
 * [] Created By : Parham Alvani (parham.alvani@gmail.com)
 * =======================================
*/

package home.parham.enumeration;

import java.util.EnumSet;

public class Main {
	public static void main(String args[]){
		System.out.println(PEnum.A.getValue());
		System.out.println(PEnum.A.name());
		System.out.println(PEnum.B.getValue());

		/* print all values in enum PNum */
		for (PEnum element : PEnum.values())
			System.out.println(element.getValue());

		for (PEnum element : EnumSet.range(PEnum.A, PEnum.B))
			System.out.println(element.getValue());
	}
}
