package edu.gatech.oad.antlab.person;

/**
 *  A simple class for person 2
 *  returns their name and a
 *  modified string 
 *
 * @author Bob Zhai
 * @version 1.1
 */
public class Person2 {
    /** Holds the persons real name */
    private String name;
	 	/**
	 * The constructor, takes in the persons
	 * name
	 * @param pname the person's real name
	 */
	 public Person2(String pname) {
	   name = pname;
	 }
	/**
	 * This method should take the string
	 * input and return its characters in
	 * random order.
	 * given "gtg123b" it should return
	 * something like "g3tb1g2".
	 *
	 * @param input the string to be modified
	 * @return the modified string
	 */
	private String calc(String input) {
	  //Person 2 put your implementation here
		char inputArray[] = new char[input.length()];
		for (int i = 0; i < input.length(); i++) {
			inputArray[i] = input.charAt(i);
		}
		String output = "";
		int len = input.length();
		while (output.length() < input.length()) {
			int random = (int) (Math.random() * len);
			output += inputArray[random];
			char temp = inputArray[random];
			inputArray[random] = inputArray[len - 1];
			inputArray[len - 1] = temp;
			len--;
		}
		return output;
	}
	/**
	 * Return a string rep of this object
	 * that varies with an input string
	 *
	 * @param input the varying string
	 * @return the string representing the 
	 *         object
	 */
	public String toString(String input) {
	  return name + calc(input);
	}
}
