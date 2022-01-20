package modules;

import javax.swing.JOptionPane;

public class Alphabet 
{
	private final String[] wordBank =
		{
			"ARGF", "IRNM", "JQXZ", "OKBV", "PQJS", "QYDX", 
			"DFW", "HDU", "LXE", "PKD", "QEW", "TJL", "VCN", "VSI", "YKQ", "ZNY", 
			"AC", "GS", "JR", "OP" 
		};
	public void run()
	{
		String letters = JOptionPane.showInputDialog("Enter the 4 letters:").toUpperCase();
		boolean v = valid(letters);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			letters = JOptionPane.showInputDialog("Enter the 4 letters:").toUpperCase();
			v = valid(letters);
		}
		String word = getWord(letters);
		for(int bb = 0; bb < word.length(); bb++)
			letters = letters.replace(word.charAt(bb) + "", "");
		if(word.length() == 2)
		{
			String temp = getWord(letters);
			if(word.compareTo(temp) < 0)
				word = word + "" + temp.toUpperCase();
			else
				word = temp.toUpperCase() + "" + word;
			for(int bb = 0; bb < temp.length(); bb++)
				letters = letters.replace(temp.charAt(bb) + "", "");
		}
		word = word + "" + order(letters, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		JOptionPane.showMessageDialog(null, "Press these letters: " + word.charAt(0) + " " + word.charAt(1) + " " + word.charAt(2) + " " + word.charAt(3));
	}
	private String order(String i, String alpha)
	{
		String conv = "";
		for(int aa = 0; aa < alpha.length(); aa++)
		{
			if(i.indexOf(alpha.charAt(aa)) >= 0)
				conv = conv + "" + alpha.charAt(aa);
		}
		return conv;
	}
	private String getWord(String letters)
	{
		for(int aa = 0; aa < wordBank.length; aa++)
		{
			boolean flag = true;
			for(int bb = 0; bb < wordBank[aa].length(); bb++)
			{
				if(letters.indexOf(wordBank[aa].charAt(bb)) < 0)
				{
					flag = false;
					break;
				}
			}
			if(flag)
				return wordBank[aa].toUpperCase();
		}
		return "";
	}
	private boolean valid(String i)
	{
		if(i.length() == 4)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
}
