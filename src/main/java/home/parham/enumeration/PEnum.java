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

public enum PEnum {
	A(10), B(20), C(30);

	private int value;

	private PEnum(int value){
		this.value = value;
	}

	public int getValue(){
		return value;
	}
}
