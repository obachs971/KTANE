package modules;

import javax.swing.JOptionPane;

public class ColoredSquares 
{
	private final int[][] table =
		{
				{},
				{1, 6, 0, 3, 5, 2, 4},
				{5, 2, 1, 4, 0, 6, 3},
				{3, 4, 2, 5, 1, 0, 6},
				{1, 2, 3, 6, 0, 5, 4},
				{3, 5, 1, 4, 6, 0, 2},
				{4, 0, 3, 2, 6, 1, 5},
				{2, 5, 6, 1, 4, 3, 0},
				{4, 0, 2, 1, 3, 6, 5},
				{6, 3, 0, 2, 5, 4, 1},
				{2, 6, 5, 0, 4, 1, 3},
				{0, 3, 5, 6, 2, 4, 1},
				{6, 1, 4, 0, 3, 5, 2},
				{5, 4, 6, 3, 1, 2, 0},
				{0, 1, 4, 5, 2, 3, 6},
				{6, 5, 6, 5, 6, 5, 6}
		};
	public String run()
	{
		String[] groups = {"Red", "Blue", "Green", "Yellow", "Magenta", "the topmost Row", "the leftmost Column"};
		String input = JOptionPane.showInputDialog("Red, Blue, Yellow,\nGreen, Magenta\nEnter the color that\noccurs the fewest:").toUpperCase();
		int row = 0,  col = colorToNum(input);
		while(col == -1)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Red, Blue, Yellow,\nGreen, Magenta\nEnter the color\nthat occurs the fewest:").toUpperCase();
			col = colorToNum(input);
		}
		String souv = "FIRST COLOR GROUP: " + groups[col].toUpperCase();
		do
		{
			input = JOptionPane.showInputDialog("Press " + groups[col] + "\nEnter the total number\nof white squares:");
			boolean v = valid(input, row);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Press " + groups[col] + "\nEnter the total number\nof white squares:");
				v = valid(input, row);
			}
			if(input.length() == 0)
				break;
			row = Integer.parseInt(input);
			col = table[row][col];
		}while(row < 14);
		if(row == 14 || row == 15)
			JOptionPane.showMessageDialog(null, "Press " + groups[col]);
		return souv;
	}
	private int colorToNum(String i)
	{
		switch(i)
		{
			case "RED":
			case "R":
				return 0;
			case "BLUE":
			case "B":
				return 1;
			case "GREEN":
			case "G":
				return 2;
			case "YELLOW":
			case "Y":
				return 3;
			case "MAGENTA":
			case "M":
				return 4;
		}
		return -1;
	}
	private boolean valid(String i, int row)
	{
		if(i.length() == 0)
			return true;
		switch(i)
		{
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
			case "8":
			case "9":
			case "10":
			case "11":
			case "12":
			case "13":
			case "14":
			case "15":
				if(Integer.parseInt(i) > row)
					return true;
				break;
		}
		return false;
	}
}
