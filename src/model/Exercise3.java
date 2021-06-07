package model;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exercise3 {
	/*
	 * 	2 letras mayusculas

		3 números no repetidos

		1 carácter de esta lista (* _ - ¿ ¡ ? # $)

		no espacios en blanco
		
		tamanio entre 8-15 caracteres
	 * */

	public static void main(String[] args) {
		// Set the word
		System.out.println("Ingrese una palabra:");
				
		Scanner in = new Scanner(System.in);
				
		String password = in.nextLine();
		System.out.println(password);
				
		in.close();
		
		String result = matchWithCharSearch(password);
		System.out.println(result);
		
		/*
		 * 
		 * There is also another way to do this, by using regex
		 * 
		 * */
		
//		String result = matchWithRegex(password);
//		System.out.println(result);

	}
	
	public static String matchWithCharSearch(String password) {
		char[] chars = password.toCharArray();

		int capitalLetters = 0;
		int digits = 0;
		boolean hasRepeated = false;
		int specialChars = 0;
		int whitespaces = 0;
		
		String buffer = "";
		
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			
			// Find capital letters
			if (Character.isUpperCase(c)) capitalLetters++;
			
			// Find numbers
			if (Character.isDigit(c)) {
				if (buffer.contains(c + "")) hasRepeated = true;
				else buffer += c;
				
				digits++;
			}
				
			// Find special chars
			if (c == '*' || c == '_' || c == '-' || c == '¿' ||
					c == '¡' || c == '?' || c == '#' || c == '$') 
				specialChars++;
			
			// Find whitespaces
			if (Character.isWhitespace(c)) whitespaces++;
			
		}
		
		String output = "";
		
		// Check if there is at least 2 capital letters
		if (capitalLetters < 2)
			output += "Error, la contraseña debe tener al menos dos letras mayúsculas\n";
		
		if (digits < 3)
			output += "Error la contraseña debe tener al menos 3 números\n";
		
		// Check if there is at least 3 non-repeated numbers
		if (hasRepeated)
			output += "Error, la contraseña no debe tener números repetidos\n";
		
		// Check if there is at least one character of the list (* _ - ¿ ¡ ? # $)
		if (specialChars == 0)
			output += "Error, la contraseña debe tener al menos un carácter especial (* _ - ¿ ¡ ? # $)\n";
		
		// Check if there are whitespaces
		if (whitespaces > 0)
			output += "Error, la contraseña no debe tener espacios en blanco\n";
		
		// Password length has to be 8-15
		if (password.length() < 8 || password.length() > 15)
			output += "Error la contraseña debe tener entre 8 y 15 caracteres\n";
		
		if (output.isEmpty())
			output += "Contraseña válida";
		
		return output;
	}
	
	public static String matchWithRegex(String password) {
		// To check if the password is valid, we can use the following regex:
		// ---> ^(?=.*[A-Z].*[A-Z])(?=.*[*_\\-¿¡?#$])(?!.*([0-9]).*\\1)(?=.*[0-9].*[0-9].*[0-9])(?!.*[ ])[A-Za-z0-9*_\\-¿¡?#$ñ]{8,15}$
		
		// We can also match every by parts
		String output = "";
		
		// Find at least 2 capital letters
		if (!patternMatch("^(?=.*[A-Z].*[A-Z]).+$", password))
			output += "Error, la contraseña debe tener al menos dos letras mayúsculas\n";
		
		// Find at least 3 numbers
		if (!patternMatch("^(?=.*[0-9].*[0-9].*[0-9]).+$", password))
			output += "Error la contraseña debe tener al menos 3 números\n";
		
		// Find non-repeated numbers
		if (!patternMatch("^(?!.*([0-9]).*\\1).+$", password))
			output += "Error, la contraseña no debe tener números repetidos\n";
		
		// Find at least one character of the list (* _ - ¿ ¡ ? # $)
		if (!patternMatch("^(?=.*[*_\\-¿¡?#$]).+$", password))
			output += "Error, la contraseña debe tener al menos un carácter especial (* _ - ¿ ¡ ? # $)\n";
		
		// It must not have whitespaces
		if (!patternMatch("^(?!.*[ ]).+$", password))
			output += "Error, la contraseña no debe tener espacios en blanco\n";
		
		// Password length has to be 8-15
		if (!patternMatch("^.{8,15}$", password))
			output += "Error la contraseña debe tener entre 8 y 15 caracteres\n";
		
		if (output.isEmpty())
			output += "Contraseña válida";
		
		return output;
	}
	
	public static boolean patternMatch(String regex, String match) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(match);
		
		return matcher.matches();
	}

}
