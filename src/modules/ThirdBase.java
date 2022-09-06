package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import misc.PlayType;

public class ThirdBase 
{
	private final String[][] table =
		{
				{"H6SI", "6O8I", "NHXS", "XI8Z", "66I8"},
				{"H6SI", "6NZH"},
				{"Z8IX", "XI8Z", "I8O9", "XOHZ", "IH6X", "66I8", "SXHN", "NXO8", "6NZH", "6O8I"},
				{"ZHOX", "IS9H", "X6IS", "SNZX", "SI9X", "X9HI", "ZSN8", "XZNS", "9NZS", "S89H", "HZN9", "8NSZ", "SZN6", "8I99"},
				{"8I99", "X9HI", "X6IS", "HZN9", "9NZS", "XZNS", "SNZX", "SZN6", "8NSZ"},
				{"XI8Z", "IH6X", "6NZH", "XOHZ", "I8O9", "NHXS", "H6SI", "SXHN", "66I8", "Z8IX", "8OXN"},
				{"8NSZ", "8I99", "ZHOX", "HZN9", "IS9H", "SNZX", "SZN6", "XZNS", "SI9X", "9NZS"},
				{"6NZH", "I8O9", "NHXS", "6O8I", "SXHN", "H6SI", "IH6X", "8OXN", "NXO8", "XI8Z", "Z8IX", "XOHZ", "66I8", "H68S"},
				{"NHXS", "IH6X", "XI8Z", "66I8", "SXHN", "NXO8", "XOHZ", "H6SI"},
				{"9NZS", "HZN9"},
				{"6O8I", "SXHN", "H68S", "NHXS", "8OXN", "IH6X", "NXO8", "I8O9"},
				{"8OXN", "H6SI", "I8O9", "6O8I", "NHXS", "Z8IX", "SXHN", "66I8", "6NZH", "XOHZ", "NXO8", "H68S", "IH6X"},
				{"SI9X", "SNZX", "ZSN8", "ZHOX", "XZNS", "8NSZ", "IS9H"},
				{"I8O9", "H6SI", "8OXN", "6O8I", "H68S", "XOHZ", "66I8", "XI8Z", "IH6X", "NHXS"},
				{"8OXN", "SXHN", "Z8IX", "I8O9", "NHXS", "6NZH", "H68S", "66I8", "XOHZ", "NXO8"},
				{"SNZX", "8NSZ", "IS9H", "SI9X", "HZN9", "SZN6", "ZSN8", "X9HI", "S89H"},
				{"9NZS", "XZNS", "HZN9", "ZHOX", "S89H", "X9HI", "ZSN8", "X6IS", "8I99", "SNZX", "SZN6", "IS9H", "SI9X"},
				{"SNZX"},
				{"Z8IX", "8OXN", "NXO8", "H68S", "XOHZ", "XI8Z", "H6SI", "NHXS", "IH6X", "6NZH", "66I8", "I8O9", "SXHN"},
				{"X9HI", "S89H", "SZN6"},
				{"HZN9", "IS9H", "S89H", "SZN6", "XZNS", "X9HI", "ZSN8", "SI9X", "SNZX", "9NZS", "X6IS"},
				{"8NSZ", "SNZX", "IS9H", "SI9X", "ZHOX", "SZN6", "HZN9", "XZNS", "X6IS", "9NZS", "S89H", "8I99", "ZSN8", "X9HI"},
				{"NHXS", "I8O9", "XOHZ", "6O8I", "6NZH", "66I8", "H6SI", "Z8IX", "XI8Z"},
				{"8OXN", "XOHZ"},
				{"8I99", "S89H", "X9HI", "ZSN8", "9NZS", "SZN6", "8NSZ", "SI9X", "HZN9", "IS9H", "XZNS"},
				{"NXO8", "H6SI", "I8O9", "6O8I", "Z8IX"},
				{"ZSN8", "8I99", "SNZX", "ZHOX"},
				{"SZN6", "S89H", "8I99", "HZN9", "IS9H", "ZSN8"}
		};
	private final PlayType pt;
	public ThirdBase(PlayType p)
	{
		pt = p;
	}
	public String run()
	{
		ArrayList<String> displayed = new ArrayList<String>();
		int flip = JOptionPane.showConfirmDialog(null, "Are you flipping the module?");
		if(pt == PlayType.Team)
		{
			for(int zz = 0; zz < 3; zz++)
			{
				String display = JOptionPane.showInputDialog("Enter the word\nin the display:").toUpperCase();
				if(flip == 1)
					display = flip(display);
				int pos = getPosition(display);
				while(pos == -1)
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					display = JOptionPane.showInputDialog("Enter the word\nin the display:").toUpperCase();
					if(flip == 1)
						display = flip(display);
					pos = getPosition(display);
				}
				if(flip == 1)
					pos = 5 - pos;
				displayed.add(display.toUpperCase());
				String[] order = {"TL", "TR", "ML", "MR", "BL", "BR"};
				String word = JOptionPane.showInputDialog("Enter the word\non the " + order[pos] + " button:").toUpperCase();
				if(flip == 1)
					word = flip(word);
				int row = getRow(word);
				while(row == -1)
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					word = JOptionPane.showInputDialog("Enter the word\non the " + order[pos] + " button:").toUpperCase();
					if(flip == 1)
						word = flip(word);
					row = getRow(word);
				}
				String out = "";
				for(int aa = 0; aa < table[row].length; aa++)
				{
					if(flip == 1)
						out = out + "" + flip(table[row][aa]) + ", ";
					else
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
				if(flip == 1)
					display = flip(display);
				int pos = getPosition(display);
				while(pos == -1)
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					display = JOptionPane.showInputDialog("Enter the word\nin the display:").toUpperCase();
					if(flip == 1)
						display = flip(display);
					pos = getPosition(display);
				}
				if(flip == 1)
					pos = 5 - pos;
				displayed.add(display.toUpperCase());
				ArrayList<String> buttons = new ArrayList<String>();
				String[] order = {"TL", "TR", "ML", "MR", "BL", "BR"};
				for(int aa = 0; aa < 6; aa++)
				{
					String input = JOptionPane.showInputDialog("Enter the\n" + order[aa] + " button:").toUpperCase();
					if(flip == 1)
						input = flip(input);
					boolean v = (getRow(input) > -1 && !(buttons.contains(input)));
					while(!(v))
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						input = JOptionPane.showInputDialog("Enter the\n" + order[aa] + " button:").toUpperCase();
						if(flip == 1)
							input = flip(input);
						v = (getRow(input) > -1 && !(buttons.contains(input)));
					}
					buttons.add(input.toUpperCase());
				}
				int row = getRow(buttons.get(pos));
				for(int aa = 0; aa < table[row].length; aa++)
				{
					if(buttons.contains(table[row][aa]))
					{
						if(flip == 1)
							JOptionPane.showMessageDialog(null, "Press this button: " + flip(table[row][aa]));
						else
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
			case "SZN6":
				return 0;
			case "IH6X":
			case "I8O9":
			case "X6IS":
				return 1;
			case "NHXS":
			case "H68S":
			case "6NZH":
			case "IS9H":
				return 2;
			case "Z8IX":
			case "6O8I":
			case "NXO8":
			case "9NZS":
			case "ZHOX":
			case "SI9X":
			case "X9HI":
				return 3;
			case "8OXN":
			case "66I8":
			case "S89H":
			case "HZN9":
				return 4;
			case "XI8Z":
			case "XOHZ":
			case "SXHN":
			case "H6SI":
			case "SNZX":
			case "8I99":
			case "ZSN8":
			case "XZNS":
			case "8NSZ":
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
	private String flip(String word)
	{
		word = word.replace("6", "*");
		word = word.replace("9", "6");
		word = word.replace("*", "9");
		String flip = "";
		for(char c : word.toCharArray())
			flip = c + "" + flip;
		return flip;
	}
}
