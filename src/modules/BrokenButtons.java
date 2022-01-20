package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class BrokenButtons 
{
	public String run()
	{
		String[] words = new String[12];
		for(int aa = 0; aa < words.length; aa++)
		{
			words[aa] = JOptionPane.showInputDialog("Enter word #" + (aa + 1)).toUpperCase();
			boolean v = valid(words[aa]);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				words[aa] = JOptionPane.showInputDialog("Enter word #" + (aa + 1)).toUpperCase();
				v = valid(words[aa]);
			}
		}
		ArrayList<String> pressed = new ArrayList<String>();
		String submit = "LEFT";
		int cur = -1;
		String col = "ABC";
		for(int aa = 0; aa < 5; aa++)
		{
			cur = -1;
			if(findWord(words, "SEA") >= 0)
				cur = findWord(words, "SEA");
			else if(Ton1st3rd(words) >= 0)
				cur = Ton1st3rd(words);
			else if(findWord(words, "ONE") >= 0 && findWord(words, "SUBMIT") >= 0)
			{
				submit = "LEFT";
				cur = findWord(words, "ONE");
			}
			else if(findWord(words, "") >= 0)
				cur = findWord(words, "");
			else if(findWord(words, "OTHER") >= 0)
			{
				if(submit.equals("LEFT"))
					submit = "RIGHT";
				else
					submit = "LEFT";
				cur = findWord(words, "OTHER");
			}
			else if(findDup(words) >= 0)
				cur = findDup(words);
			else if((findWord(words, "PORT") >= 0 || findWord(words, "MODULE") >= 0) && findWord(words, "RJ-45") >= 0)
				cur = findWord(words, "RJ-45");
			else if((findWord(words, "PORT") >= 0 || findWord(words, "MODULE") >= 0) && findWord(words, "DVI-D") >= 0)
				cur = findWord(words, "DVI-D");
			else if((findWord(words, "PORT") >= 0 || findWord(words, "MODULE") >= 0) && findWord(words, "RCA") >= 0)
				cur = findWord(words, "RCA");
			else if((findWord(words, "PORT") >= 0 || findWord(words, "MODULE") >= 0) && findWord(words, "PS/2") >= 0)
				cur = findWord(words, "PS/2");
			else if((findWord(words, "PORT") >= 0 || findWord(words, "MODULE") >= 0) && findWord(words, "SERIAL") >= 0)
				cur = findWord(words, "SERIAL");
			else if(findLess3(words) >= 0)
				cur = findLess3(words);
			else if(findWord(words, "BOMB") >= 0 && findWord(words, "BOOM") >= 0)
				cur = findWord(words, "BOOM");
			else if(findWord(words, "SUBMIT") >= 0 && findWord(words, "BUTTON") >= 0)
				cur = -1;
			else if(findWord(words, "COLUMN") >= 0 && (findWord(words, "SEVEN") >= 0 || findWord(words, "TWO") >= 0))
				cur = findWord(words, "COLUMN");
			else if(aa == 0)
				cur = 5;
			else if(pressed.get(0).contains("E"))
				submit = "RIGHT";
			if(cur == -1)
			{
				JOptionPane.showMessageDialog(null, "Submit " + submit);
				break;
			}
			else if(aa == 4)
			{
				pressed.add(words[cur].toUpperCase());
				JOptionPane.showMessageDialog(null, "Press the word at " + col.charAt(cur % 3) + "" + ((cur / 3) + 1) + "\nSubmit " + submit);
			}
			else
			{
				pressed.add(words[cur].toUpperCase());
				words[cur] = JOptionPane.showInputDialog("Press the word at " + col.charAt(cur % 3) + "" + ((cur / 3) + 1) + "\nEnter the new word:").toUpperCase();
				boolean v = valid(words[cur]);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					words[cur] = JOptionPane.showInputDialog("Press the word at " + col.charAt(cur % 3) + "" + ((cur / 3) + 1) + "\nEnter the new word:").toUpperCase();
					v = valid(words[cur]);
				}
			}
		}
		String souv = "";
		for(int aa = 0; aa < pressed.size(); aa++)
			souv = souv + "BUTTON #" + (aa + 1) + ": " + pressed.get(aa).toUpperCase() + "\n";
		souv = souv + "SUBMIT: " + submit.toUpperCase();
		return souv;
	}
	private int findWord(String[] words, String word)
	{
		for(int aa = 0; aa < words.length; aa++)
		{
			if(words[aa].equals(word))
				return aa;
		}
		return -1;
	}
	private int Ton1st3rd(String[] words)
	{
		for(int aa = 0; aa < 3; aa++)
		{
			if(words[aa].startsWith("T"))
				return aa;
		}
		for(int aa = 6; aa < 9; aa++)
		{
			if(words[aa].startsWith("T"))
				return aa;
		}
		return -1;
	}
	private int findDup(String[] words)
	{
		for(int aa = 0; aa < words.length; aa++)
		{
			for(int bb = aa + 1; bb < words.length; bb++)
			{
				if(words[aa].equals(words[bb]))
					return aa;
			}
		}
		return -1;
	}
	private int findLess3(String[] words)
	{
		for(int aa = 0; aa < words.length; aa++)
		{
			if(words[aa].length() < 3)
				return aa;
		}
		return -1;
	}
	private boolean valid(String i)
	{
		String[] poss = {"BOMB", "BLAST", "BOOM", "BURST", "WIRE", "BUTTON", "MODULE", "LIGHT", "LED", "SWITCH", "RJ-45", "DVI-D", "RCA", "PS/2", "SERIAL", "PORT", "ROW", "COLUMN", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "SIZE", "THIS", "THAT", "OTHER", "SUBMIT", "ABORT", "DROP", "THING", "BLANK", "", "BROKEN", "TOO", "TO", "YES", "SEE", "SEA", "C", "WAIT", "WORD", "BOB", "NO", "NOT", "FIRST", "HOLD", "LATE", "FAIL"};
		for(int aa = 0; aa < poss.length; aa++)
		{
			if(poss[aa].equals(i))
				return true;
		}
		return false;
	}
}
