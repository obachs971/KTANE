package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import misc.PlayType;

public class Password 
{
	private final String[] words =
		{
			"ABOUT", "AFTER", "AGAIN", "BELOW", "COULD",
			"EVERY", "FIRST", "FOUND", "GREAT", "HOUSE",
			"LARGE", "LEARN", "NEVER", "OTHER", "PLACE",
			"PLANT", "POINT", "RIGHT", "SMALL", "SOUND",
			"SPELL", "STILL", "STUDY", "THEIR", "THERE",
			"THESE", "THING", "THINK", "THREE", "WATER",
			"WHERE", "WHICH", "WORLD", "WOULD", "WRITE"
		};
	private final PlayType playType;
	public Password(PlayType pt)
	{
		playType = pt;
	}
	public void run()
	{
		
		if(playType == PlayType.Team)
		{
			String type = "the";
			while(true)
			{
				String input = JOptionPane.showInputDialog("Enter " + type + " 1st letter\nfor a list of words:").toUpperCase();
				boolean v = valid(input, 1);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog("Enter " + type + " 1st letter\nfor a list of words:").toUpperCase();
					v = valid(input, 1);
				}
				if(input.length() == 0)
					break;
				int counter = 1;
				String out = "";
				for(int aa = 0; aa < words.length; aa++)
				{
					if(words[aa].charAt(0) == input.charAt(0))
					{
						out = out + "" + words[aa] + " ";
						if(counter % 3 == 0)
							out = out + "\n";
						counter++;
					}
				}
				JOptionPane.showMessageDialog(null, out);
				type = "another";
			}
		}
		else
		{
			ArrayList<String> possWords = new ArrayList<String>();
			ArrayList<String> letters = new ArrayList<String>();
			ArrayList<Integer> positions = new ArrayList<Integer>();
			for(int aa = 0; aa < 5; aa++)
			{
				letters.add("");
				positions.add(aa + 0);
			}
			do
			{
				if(letters.get(0).length() == 6)
					break;
				String message = "Toggle the letters then\nenter the new letters:";
				if(letters.get(0).length() == 0)
					message = "Enter all the\ncurrent letters:";
				String input = JOptionPane.showInputDialog(message).toUpperCase();
				boolean v = valid(input, 5);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog(message).toUpperCase();
					v = valid(input, 5);
				}
				for(int aa = 0; aa < 5; aa++)
					letters.set(aa, letters.get(aa) + "" + input.charAt(aa));
				possWords = getPossWords(letters, positions);
			}while(possWords.size() != 1);
			if(possWords.size() == 1)
				JOptionPane.showMessageDialog(null, "Submit this word: " + possWords.get(0));
			else
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				run();
			}
		}
	}
	private ArrayList<String> getPossWords(ArrayList<String> l, ArrayList<Integer> pos)
	{
		ArrayList<String> w = new ArrayList<String>();
		for(int aa = 0; aa < words.length; aa++)
			w.add(words[aa].toUpperCase());
		for(int aa = 0; aa < l.size(); aa++)
		{
			w = getPossWords(w, l.get(aa), pos.get(aa));
			if(w.size() == 0)
				return w;
		}
		return w;
	}
	private ArrayList<String> getPossWords(ArrayList<String> w, String l, int pos)
	{
		ArrayList<String> possWords = new ArrayList<String>();
		for(int aa = 0; aa < w.size(); aa++)
		{
			if(l.contains(w.get(aa).charAt(pos) + ""))
				possWords.add(w.get(aa).toUpperCase());
		}
		return possWords;
	}
	private boolean valid(String i, int l)
	{
		if(playType == PlayType.Team && i.length() == 0)
			return true;
		if(i.length() == l)
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
