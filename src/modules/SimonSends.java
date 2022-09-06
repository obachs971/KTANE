package modules;

import javax.swing.JOptionPane;

import misc.MTK;

public class SimonSends 
{
	private final String[][] manual =
		{
				{"THIS", "IS", "THE", "FIRST", "WORD", "FOR", "PURPOSES", "OF", "COUNTING", "WORDS", "AND", "PARAGRAPHS", "IN", "THIS", "TEXT", "THE", "FLAVOR", "TEXT", "AND", "APPENDIX", "ARE", "EXCLUDED"},
				{"HYPHENATED", "WORDS", "EQUATE", "TO", "JUST", "ONE", "WORD", "PUNCTUATION", "MARKS", "DO", "NOT", "COUNT", "AS", "LETTERS"},
				{"A", "SIMON", "SENDS", "PUZZLE", "IS", "EQUIPPED", "WITH", "COLORIZED", "LIGHTS", "WHICH", "FLASH", "UNIQUE", "LETTERS", "IN", "MORSE", "CODE", "SIMULTANEOUSLY", "AND", "A", "DIAL", "FOR", "ADJUSTING", "THE", "FREQUENCY", "OF", "FLASHING"},
				{"OWING", "TO", "THEIR", "PROXIMITY", "THE", "LIGHTS", "RED", "GREEN", "AND", "BLUE", "MIX", "BY", "WAY", "OF", "ADDITIVE", "COLOR", "MIXING", "WORK", "OUT", "THE", "INDIVIDUAL", "COLORS"},
				{"CONVERT", "EACH", "RECOGNIZED", "LETTER", "INTO", "A", "NUMBER", "USING", "ITS", "ALPHABETIC", "POSITION", "CALL", "YOUR", "THUSLY", "ACQUIRED", "NUMBERS", "R", "G", "AND", "B", "DERIVE", "NEW", "LETTERS", "AS", "FOLLOWS"},
				{"COUNT", "R", "LETTERS", "FROM", "THE", "START", "OF", "THE", "GTH", "WORD", "FROM", "THE", "START", "OF", "THE", "BTH", "PARAGRAPH", "IN", "THIS", "MANUAL", "AND", "MAKE", "IT", "YOUR", "NEW", "RED", "LETTER"},
				{"COUNT", "G", "LETTERS", "FROM", "THE", "START", "OF", "THE", "BTH", "WORD", "FROM", "THE", "START", "OF", "THE", "RTH", "PARAGRAPH", "IN", "THIS", "MANUAL", "AND", "MAKE", "IT", "YOUR", "NEW", "GREEN", "LETTER"},
				{"COUNT", "B", "LETTERS", "FROM", "THE", "START", "OF", "THE", "RTH", "WORD", "FROM", "THE", "START", "OF", "THE", "GTH", "PARAGRAPH", "IN", "THIS", "MANUAL", "AND", "MAKE", "IT", "YOUR", "NEW", "BLUE", "LETTER"},
				{"REALIZE", "A", "NEW", "COLOR", "SEQUENCE", "BY", "JUXTAPOSING", "AGAIN", "USING", "KNOWN", "ADDITIVE", "COLOR", "MIXING", "ONE", "COPY", "OF", "EACH", "NEW", "LETTERS", "MORSE", "CODE"},
				{"ACKNOWLEDGE", "A", "DOT", "AND", "A", "DASH", "IN", "MORSE", "CODE", "HAVE", "SIZES", "OF", "ONE", "AND", "THREE", "UNITS", "RESPECTIVELY", "GAPS", "BETWEEN", "THEM", "ALSO", "HAVE", "A", "SIZE", "OF", "JUST", "ONE", "UNIT"},
				{"INPUT", "YOUR", "ACQUIRED", "COLOR", "SEQUENCE", "USING", "EACH", "QUALIFYING", "COLOR", "BUTTON"},
				{"A", "MISTAKE", "IS", "REJECTED", "WITH", "A", "STRIKE", "ON", "SUCH", "AN", "OCCASION", "ADJUST", "AND", "FINISH", "YOUR", "ANSWER", "LOOK", "AT", "THE", "DISPLAY", "TO", "JUDGE", "YOUR", "INPUT", "THUS", "FAR"},
				{"JUMP", "BACK", "TO", "THE", "FIRST", "WORD", "IF", "WHILE", "COUNTING", "YOU", "ADVANCE", "BEYOND", "THE", "LAST", "WORD", "WHICH", "IS", "THIS"}
		};
	public String run()
	{
		String input = JOptionPane.showInputDialog("Enter the morse code for the Red, \nGreen, and Blue codes in that order:").toUpperCase();
		int[] vals = getValues(input);
		while(vals == null)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the morse code for the Red, \nGreen, and Blue codes in that order:").toUpperCase();
			vals = getValues(input);
		}
		char[] letters = new char[3];
		for(int i = 0; i < 3; i++)
		{
			int paragraph = (vals[(i + 2) % 3] % manual.length);
			//System.out.println(paragraph);
			int word = vals[(i + 1) % 3];
			while(word >= manual[paragraph].length)
			{
				word -= manual[paragraph].length;
				paragraph = (paragraph + 1) % manual.length;
			}
			//System.out.println(paragraph + " " + word);
			int letter = vals[i];
			while(letter >= manual[paragraph][word].length())
			{
				letter -= manual[paragraph][word].length();
				word++;
				if(word >= manual[paragraph].length)
				{
					word = 0;
					paragraph = (paragraph + 1) % manual.length;
				}
			}
			//System.out.println(paragraph + " " + word + " " + letter);
			letters[i] = manual[paragraph][word].charAt(letter);
		}
		//System.out.println(new String(letters));
		String[] morse = new MTK().messageToMorse(new String(letters));
		for(int i = 0; i < 3; i++)
		{
			morse[i] = morse[i].replace("-", "1110").replace(".", "10");
			morse[i] = morse[i].substring(0, morse[i].length() - 1);
		}
		int best = 0;
		for(String m : morse)
		{
			if(m.length() > best)
				best = m.length();
		}
		for(int i = 0; i < morse.length; i++)
		{
			while(morse[i].length() < best)
				morse[i] += "0";
		}
		String order = "KRGYBMCW";
		String out = "";
		for(int i = 0; i < best; i++)
		{
			int color = 0;
			if(morse[0].charAt(i) == '1')
				color += 1;
			if(morse[1].charAt(i) == '1')
				color += 2;
			if(morse[2].charAt(i) == '1')
				color += 4;
			out = out + "" + order.charAt(color);
			if((i + 1) % 3 == 0)
				out += " ";
		}
		JOptionPane.showMessageDialog(null, "Press these colors:\n" + out);
		for(int i = 0; i < 3; i++)
			letters[i] = (char) (vals[i] + 'A');
		return ("RED LETTER: " + letters[0] + "\nGREEN LETTER: " + letters[1] + "\nBLUE LETTER" + letters[2]);
	}
	private int[] getValues(String input)
	{
		String letters = new MTK().morseToMessage(input.split(" "));
		if(letters.length() == 3)
		{
			int[] vals = new int[3];
			for(int i = 0; i < vals.length; i++)
			{
				vals[i] = (letters.charAt(i) - 'A');
				if(vals[i] < 0 || vals[i] > 25)
					return null;
			}
			return vals;
		}
		return null;
	}
}
