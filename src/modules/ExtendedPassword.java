package modules;

import javax.swing.JOptionPane;

import misc.PlayType;

public class ExtendedPassword 
{
	private final String[] wordList =
		{
			"ADJUST", "ANCHOR", "BOWTIE", "BUTTON", "CIPHER", "CORNER", 
			"DAMPEN", "DEMOTE", "ENLIST", "EVOLVE", "FORGET", "FINISH", 
			"GEYSER", "GLOBAL", "HAMMER", "HELIUM", "IGNITE", "INDIGO", 
			"JIGSAW", "JULIET", "KARATE", "KEYPAD", "LAMBDA", "LISTEN", 
			"MATTER", "MEMORY", "NEBULA", "NICKEL", "OVERDO", "OXYGEN", 
			"PEANUT", "PHOTON", "QUARTZ", "QUEBEC", "RESIST", "RIDDLE", 
			"SIERRA", "STRIKE", "TEAPOT", "TWENTY", "UNTOLD", "ULTIMA", 
			"VICTOR", "VIOLET", "WITHER", "WRENCH", "XENONS", "XYLOSE", 
			"YELLOW", "YOGURT", "ZENITH", "ZODIAC"
		};
	private final PlayType playType;
	public ExtendedPassword(PlayType pt)
	{
		playType = pt;
	}
	public void run()
	{
		if(playType == PlayType.Team)
		{
			String input = JOptionPane.showInputDialog("Enter the 1st letter:").toUpperCase();
			boolean v = v1(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the 1st letter:").toUpperCase();
				v = v1(input);
			}
			while(input.length() > 0)
			{
				int num = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(input);
				String out = wordList[num * 2] + "\n" + wordList[(num * 2) + 1] + "\n";
				input = JOptionPane.showInputDialog("Possible Words:\n" + out + "Enter another 1st letter:").toUpperCase();
				v = v1(input);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog("Enter another 1st letter:").toUpperCase();
					v = v1(input);
				}
			}
		}
		else
		{
			String[] letters = {"", "", "", "", "", ""};
			String message = "Enter the letters:";
			boolean flag = true;
			do
			{
				String input = JOptionPane.showInputDialog(message).toUpperCase();
				boolean v = v2(input);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog(message).toUpperCase();
					v = v2(input);
				}
				for(int aa = 0; aa < letters.length; aa++)
					letters[aa] = letters[aa] + "" + input.charAt(aa);
				for(int aa = 0; aa < wordList.length; aa++)
				{
					if(letters[0].indexOf(wordList[aa].charAt(0)) >= 0 && letters[1].indexOf(wordList[aa].charAt(1)) >= 0 && letters[2].indexOf(wordList[aa].charAt(2)) >= 0 && letters[3].indexOf(wordList[aa].charAt(3)) >= 0 && letters[4].indexOf(wordList[aa].charAt(4)) >= 0 && letters[5].indexOf(wordList[aa].charAt(5)) >= 0)
					{
						JOptionPane.showMessageDialog(null, "Submit " + wordList[aa]);
						flag = false;
						break;
					}
				}
				message = "Toggle all the letters\nEnter the new letters:";
			}while(flag);
		}
	}
	private boolean v1(String i)
	{
		if(i.length() == 0)
			return true;
		if(i.length() == 1)
			return ("ABCDEFGHIJKLMNOPQRSTUVWXYZ".contains(i));
		return false;
	}
	private boolean v2(String i)
	{
		if(i.length() == 6)
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
