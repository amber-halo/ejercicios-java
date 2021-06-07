package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Exercise1 {

	public static void main(String[] args) {
		// Set the word
		System.out.println("Ingrese una palabra:");
		
		Scanner in = new Scanner(System.in);
		
		String word = in.nextLine();
		System.out.println(word);
		
		in.close();
		
		word = word.toLowerCase();
		
		char[] chars = word.toCharArray();
		
		List<String> tokens = new ArrayList<>();
		
		// Add chars to the array
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			int index = getIndexOf(tokens, c);
			
			// If the array already contains the char, add it in the same index
			// If not, append it to the array
			if (index != -1) {
				tokens.set(index, tokens.get(index) + c);
			} else {
				tokens.add(c + "");
			}
		}
		
		// The array contains a group of chars in each element
		// The group length is the count
		for (int i = 0; i < tokens.size(); i++) {
			String token = tokens.get(i);
			
			if (!token.isEmpty()) {
				int count = token.length();
				
				// Modify the array to set the output format
				tokens.set(i, tokens.get(i).charAt(0) + " -> " + count);
			}
		}
		
		// Sort the char array of the word
		Arrays.sort(chars);
		
		System.out.println(tokens);
		System.out.println(chars);
		
		
		/*
		 * There is also another way to achieve the same thing, 
		 * by sorting the array first, and then,
		 * count every character until there's a new one.
		 * The example is below:
		 * 
		String word = "amarillo";
		word = word.toLowerCase();
		
		char[] arr = word.toCharArray();
		
		Arrays.sort(arr);
		
		char curr = ' ';
		int count = 0;
		String result = "";
		
		for (char c : arr) {
			if (c != curr) {
				if (curr != ' ') result += curr + " -> " + count + "\n";
				curr = c;
				count = 0;
			}
			
			count++;
		}
		
		result += curr + " -> " + count;
		
		System.out.println(result);
		
		System.out.println(arr);
		 */
		
	}
	
	public static int getIndexOf(List<String> tokens, char c) {
		for (int i = 0; i < tokens.size(); i++) {
			String token = tokens.get(i);
			
			if (!token.isEmpty() && token.charAt(0) == c) return i;
		}
		
		return -1;
	}

}
