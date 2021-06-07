package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercise2 {

	public static void main(String[] args) {
		// Set the word
		System.out.println("Ingrese una palabra:");
		
		Scanner in = new Scanner(System.in);
		
		String word = in.nextLine();
		System.out.println(word);
		
		in.close();
		
		char[] chars = word.toCharArray();
		
		String buffer = "";
		int vocalsCount = 0;
		
		List<String> tokens = new ArrayList<>();
		
		// Add chars to buffer until we find 2 vocals
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			buffer += c;
			
			// If is vocal, and the count is 2, we cut the buffer to separate it
			if (isVocal(c)) {
				vocalsCount++;
				
				if (vocalsCount == 2) {
					// Analyze and separate buffer into syllables
					int consonantCount = getConsonantCountBetweenVocals(buffer);
					String bufferSyllables = analyze(buffer, consonantCount);
					tokens.add(bufferSyllables);
					
					// Restart values and add the last vocal to buffer
					vocalsCount--;
					buffer = c + "";
				}
			}
		}
		
		// If there are values left in buffer, add it to the tokens list
		if (buffer.length() > 1 && !isVocal(buffer.charAt(1))) tokens.add(buffer.charAt(1) + "");
		
		// If the word only has 1 vocal output just the whole word
		if (getVocalsCount(word) == 1) {
			tokens = new ArrayList<>();
			tokens.add(word);
		}
		
		// Format the output
		String result = "";
		
		for (int i = 0; i < tokens.size(); i++) {
			String token = tokens.get(i);
			
			if (i != 0) {
				if (token.length() == 1) result += token;
				else result += token.substring(1);
			} else {
				result += token;
			}
		}

		System.out.println(result);
	}
	
	public static boolean isVocal(char c) {
		String vocals = "aeiouAEIOUáéíóúü";
		return vocals.indexOf(c) >= 0;
	}
	
	public static int getVocalsCount(String word) {
		int count = 0;
		
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			
			if (isVocal(c)) count++;
		}
		
		return count;
	}
	
	public static int getConsonantCountBetweenVocals(String sub) {
		char[] arr = sub.toCharArray();
		int consonantCount = 0;
		int countTmp = 0;
		
		for (int i = 0; i < arr.length; i++) {
			if (isVocal(arr[i])) {
				consonantCount = countTmp;
				countTmp = 0;
			} else {
				countTmp++;
			}
		}
		
		// pr, pl, br, bl, fr, fl, tr, tl, dr, dl, cr, cl, gr
		if (sub.contains("pr") || sub.contains("pl") || sub.contains("br") || sub.contains("bl") || 
				sub.contains("fr") || sub.contains("fl") || sub.contains("tr") || sub.contains("tl") || 
				sub.contains("dr") || sub.contains("dl") || sub.contains("cr") || sub.contains("cl") || sub.contains("gr") ||
				sub.contains("ch") || sub.contains("ll") || sub.contains("rr")) {
			consonantCount--;
		}
		
		return consonantCount;
	}
	
	public static int getFirstVocalIndex(String buffer) {
		for (int i = 0; i < buffer.length(); i++) {
			char c = buffer.charAt(i);
			
			if (isVocal(c)) {
				return i;
			}
		}
		
		return -1;
	}
	
	public static String analyze(String buffer, int consonantCount) {
		// Depending on the number of consonants between vocals, separate
		
		String output = "";
		
		int index = 0;
		boolean flag = false;
		
		if (consonantCount == 1) index = 1;
		else if (consonantCount == 2) index = 2;
		else if (consonantCount == 3 || consonantCount == 4) index = 3;
		else if (consonantCount == 0 && buffer.length() > 1) {
			index = 1;
			flag = true;
		}
		
		// The algorithm is always the same
		// The only thing that can change is the start index of the substring
		int firstVocalIndex = getFirstVocalIndex(buffer);
		
		if (firstVocalIndex != -1 && (firstVocalIndex + index) < buffer.length()) {
			String first = buffer.substring(0, firstVocalIndex + index);
			String second = buffer.substring(firstVocalIndex + index);
			
			// If the buffer does not contain 'io', separate syllables
			// Else, add vocals 'io'
			if (!flag) output += first + "-" + second;
			else output += first + second;
		}
		
		return output;
	}

}
