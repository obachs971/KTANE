package modules;

import javax.swing.JOptionPane;

public class WordScramble 
{
	private final String[] wordList =
		{
			"BANANA", "ATTACK", "DAMAGE", "NAPALM", "OTTAWA", "KABOOM", 
			"BLASTS", "CHARGE", "ARCHER", "CASING", "CANNON", "KEYPAD", 
			"DISARM", "FLAMES", "KEVLAR", "WEAPON", "SAPPER", "MORTAR", 
			"BUTTON", "ROBOTS", "BURSTS", "DEVICE", "ROCKET", "DEFUSE", 
			"WIDGET", "MODULE", "LETTER", "SEMTEX", "PERSON", "WIRING"
		};
	public void run()
	{
		String word = JOptionPane.showInputDialog("Enter the displayed text:").toUpperCase();
		word = getWord(word);
		while(word.length() == 0)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			word = JOptionPane.showInputDialog("Enter the displayed text:").toUpperCase();
			word = getWord(word);
		}
		JOptionPane.showMessageDialog(null, "Submit this word: " + word);
	}
	private String getWord(String i)
	{
		for(int aa = 0; aa < wordList.length; aa++)
		{
			String temp = i.toUpperCase();
			for(int bb = 0; bb < 6; bb++)
				temp = temp.replaceFirst(wordList[aa].charAt(bb) + "", "");
			if(temp.length() == 0)
				return wordList[aa].toUpperCase();
		}
		return "";
	}
}
