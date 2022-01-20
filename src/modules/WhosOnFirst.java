package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;


public class WhosOnFirst 
{
	private final String[][] table =
		{
				{"YES", "OKAY", "WHAT", "MIDDLE", "LEFT", "PRESS", "RIGHT", "BLANK", "READY"},
				{"LEFT", "OKAY", "YES", "MIDDLE", "NO", "RIGHT", "NOTHING", "UHHH", "WAIT", "READY", "BLANK", "WHAT", "PRESS", "FIRST"},
				{"BLANK", "UHHH", "WAIT", "FIRST", "WHAT", "READY", "RIGHT", "YES", "NOTHING", "LEFT", "PRESS", "OKAY", "NO"},
				{"WAIT", "RIGHT", "OKAY", "MIDDLE", "BLANK"},
				{"UHHH", "RIGHT", "OKAY", "MIDDLE", "YES", "BLANK", "NO", "PRESS", "LEFT", "WHAT", "WAIT", "FIRST", "NOTHING"},
				{"OKAY", "RIGHT", "UHHH", "MIDDLE", "FIRST", "WHAT", "PRESS", "READY", "NOTHING", "YES"},
				{"UHHH", "WHAT"},
				{"READY", "NOTHING", "LEFT", "WHAT", "OKAY", "YES", "RIGHT", "NO", "PRESS", "BLANK", "UHHH"},
				{"RIGHT", "LEFT"},
				{"YES", "NOTHING", "READY", "PRESS", "NO", "WAIT", "WHAT", "RIGHT"},
				{"BLANK", "READY", "OKAY", "WHAT", "NOTHING", "PRESS", "NO", "WAIT", "LEFT", "MIDDLE"},
				{"MIDDLE", "NO", "FIRST", "YES", "UHHH", "NOTHING", "WAIT", "OKAY"},
				{"UHHH", "NO", "BLANK", "OKAY", "YES", "LEFT", "FIRST", "PRESS", "WHAT", "WAIT"},
				{"RIGHT", "MIDDLE", "YES", "READY", "PRESS"},
				{"SURE", "YOU ARE", "YOUR", "YOU'RE", "NEXT", "UH HUH", "UR", "HOLD", "WHAT?", "YOU"},
				{"YOUR", "NEXT", "LIKE", "UH HUH", "WHAT?", "DONE", "UH UH", "HOLD", "YOU", "U", "YOU'RE", "SURE", "UR", "YOU ARE"},
				{"UH UH", "YOU ARE", "UH HUH", "YOUR"},
				{"YOU", "YOU'RE"},
				{"DONE", "U", "UR"},
				{"UH HUH", "SURE", "NEXT", "WHAT?", "YOU'RE", "UR", "UH UH", "DONE", "U"},
				{"UH HUH"},
				{"UR", "U", "YOU ARE", "YOU'RE", "NEXT", "UH UH"},
				{"YOU", "HOLD", "YOU'RE", "YOUR", "U", "DONE", "UH UH", "LIKE", "YOU ARE", "UH HUH", "UR", "NEXT", "WHAT?"},
				{"SURE", "UH HUH", "NEXT", "WHAT?", "YOUR", "UR", "YOU'RE", "HOLD", "LIKE", "YOU", "U", "YOU ARE", "UH UH", "DONE"},
				{"WHAT?", "UH HUH", "UH UH", "YOUR", "HOLD", "SURE", "NEXT"},
				{"YOU ARE", "U", "DONE", "UH UH", "YOU", "UR", "SURE", "WHAT?", "YOU'RE", "NEXT", "HOLD"},
				{"YOU ARE", "DONE", "LIKE", "YOU'RE", "YOU", "HOLD", "UH HUH", "UR", "SURE"},
				{"YOU'RE", "NEXT", "U", "UR", "HOLD", "DONE", "UH UH", "WHAT?", "UH HUH", "YOU", "LIKE"}
		};
	private final int PT;
	public WhosOnFirst(int pt)
	{
		PT = pt;
	}
	public String run()
	{
		ArrayList<String> displayed = new ArrayList<String>();
		if(PT == 1)
		{
			for(int zz = 0; zz < 3; zz++)
			{
				String display = JOptionPane.showInputDialog("Enter the word\nin the display:").toUpperCase();
				int pos = getPosition(display);
				while(pos == -1)
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					display = JOptionPane.showInputDialog("Enter the word\nin the display:").toUpperCase();
					pos = getPosition(display);
				}
				displayed.add(display.toUpperCase());
				String[] order = {"TL", "TR", "ML", "MR", "BL", "BR"};
				String word = JOptionPane.showInputDialog("Enter the word\non the " + order[pos] + " button:").toUpperCase();
				int row = getRow(word);
				while(row == -1)
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					word = JOptionPane.showInputDialog("Enter the word\non the " + order[pos] + " button:").toUpperCase();
					row = getRow(word);
				}
				String out = "";
				for(int aa = 0; aa < table[row].length; aa++)
				{
					out = out + "" + table[row][aa] + ", ";
					if((aa + 1) % 4 == 0)
						out = out + "\n";
				}
				JOptionPane.showMessageDialog(null, out);
			}
		}
		else
		{
			for(int zz = 0; zz < 3; zz++)
			{
				String display = JOptionPane.showInputDialog("Enter the word\nin the display:").toUpperCase();
				int pos = getPosition(display);
				while(pos == -1)
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					display = JOptionPane.showInputDialog("Enter the word\nin the display:").toUpperCase();
					pos = getPosition(display);
				}
				displayed.add(display.toUpperCase());
				ArrayList<String> buttons = new ArrayList<String>();
				String[] order = {"TL", "TR", "ML", "MR", "BL", "BR"};
				for(int aa = 0; aa < 6; aa++)
				{
					String input = JOptionPane.showInputDialog("Enter the\n" + order[aa] + " button:").toUpperCase();
					boolean v = (getRow(input) > -1 && !(buttons.contains(input)));
					while(!(v))
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						input = JOptionPane.showInputDialog("Enter the\n" + order[aa] + " button:").toUpperCase();
						v = (getRow(input) > -1 && !(buttons.contains(input)));
					}
					buttons.add(input.toUpperCase());
				}
				int row = getRow(buttons.get(pos));
				for(int aa = 0; aa < table[row].length; aa++)
				{
					if(buttons.contains(table[row][aa]))
					{
						JOptionPane.showMessageDialog(null, "Press this button: " + table[row][aa]);
						break;
					}
				}
			}
		}
		return "DISPLAY WORD #1: " + displayed.get(0) + "\nDISPLAY WORD #2: " + displayed.get(1) + "\nDISPLAY WORD #3: " + displayed.get(2);
	}
	private int getPosition(String d)
	{
		switch(d)
		{
			case "UR":
				return 0;
			case "FIRST":
			case "OKAY":
			case "C":
				return 1;
			case "YES":
			case "NOTHING":
			case "LED":
			case "THEY ARE":
				return 2;
			case "BLANK":
			case "READ":
			case "RED":
			case "YOU":
			case "YOUR":
			case "YOU'RE":
			case "THEIR":
				return 3;
			case "":
			case "REED":
			case "LEED":
			case "THEY'RE":
				return 4;
			case "DISPLAY":
			case "SAYS":
			case "NO":
			case "LEAD":
			case "HOLD ON":
			case "YOU ARE":
			case "THERE":
			case "SEE":
			case "CEE":
				return 5;
			default:
				return -1;
		}
	}
	private int getRow(String i)
	{
		for(int aa = 0; aa < table.length; aa++)
		{
			if(table[aa][table[aa].length - 1].equals(i))
				return aa;
		}
		return -1;
	}
}
