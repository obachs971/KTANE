package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import misc.MTK;

public class MorseCode 
{
	public void run()
	{
		String[] words = {
				"SHELL", "HALLS", "SLICK", "TRICK", 
				"BOXES", "LEAKS", "STROBE", "BISTRO", 
				"FLICK", "BOMBS", "BREAK", "BRICK", 
				"STEAK", "STING", "VECTOR", "BEATS"
		};
		String[] freq = {
			"505", "515", "522", "532", 
			"535", "542", "545", "552", 
			"555", "565", "572", "575", 
			"582", "592", "595", "600"
		};
		MTK mtk = new MTK();
		ArrayList<String> possWords = new ArrayList<String>();
		ArrayList<String> possFreq = new ArrayList<String>();
		for(int aa = 0; aa < words.length; aa++)
		{
			possWords.add(words[aa].toUpperCase());
			possFreq.add(freq[aa].toUpperCase());
		}
		String word = "";
		while(possWords.size() > 1)
		{
			String input = JOptionPane.showInputDialog("Enter the morse code\n(" + (word.length() + 1) + " - 6) (spaces):").toUpperCase();
			boolean v = valid(input, word, possWords, mtk);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the morse code\n(" + (word.length() + 1) + " - 6) (spaces):").toUpperCase();
				v = valid(input, word, possWords, mtk);
			}
			word = word + "" + mtk.morseToMessage(input.split(" "));
			for(int aa = 0; aa < possWords.size(); aa++)
			{
				if(!(possWords.get(aa).startsWith(word)))
				{
					possWords.remove(aa);
					possFreq.remove(aa);
					aa--;
				}
			}
		}
		JOptionPane.showMessageDialog(null, "Set it to this\nfrequency: 3." + possFreq.get(0));
	}
	private boolean valid(String i, String w, ArrayList<String> possWords, MTK mtk)
	{
		String temp = w.toUpperCase() + "" + mtk.morseToMessage(i.split(" "));
		if(temp.length() == w.length())
			return false;
		for(int aa = 0; aa < possWords.size(); aa++)
		{
			if(possWords.get(aa).startsWith(temp))
				return true;
		}
		return false;
	}
}

