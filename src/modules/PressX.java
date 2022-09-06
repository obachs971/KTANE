package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class PressX 
{
	private final String[] table =
		{
				"ABY",
				"XYA",
				"BAX",
				"YXB"
		};
	private final BombEdgework ew;
	public PressX(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		int col = ew.numUnlit() > ew.numLit() ? 0 : ew.numLit() > ew.numUnlit() ? 1 : 2;
		String input = JOptionPane.showInputDialog("4n Solves\nEnter the number of solves:");
		boolean v = v1(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("4n Solves\nEnter the number of solves:");
			v = v1(input);
		}
		int row = Integer.parseInt(input) % 4;
		String time = getTime(table[row].charAt(col));
		while(true)
		{
			
			input = JOptionPane.showInputDialog("4n + " + row + " Solves\n" + "Press " + table[row].charAt(col) + time + "\nEnter new solves or blank to stop:");
			v = v2(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("4n + " + row + " Solves\n" + "Press " + table[row].charAt(col) + time + "\nEnter new solves or blank to stop:");
				v = v2(input);
			}
			if(input.length() == 0)
				break;
			row = Integer.parseInt(input) % 4;
			time = getTime(table[row].charAt(col));
		}
	}
	private String getTime(char press)
	{
		if(press == 'X' && ew.findLit("CAR") && ew.BAT() < 2)
			return " at any time";
		else if(ew.BAT() >= 3)
			return " when the last digit\nin the bomb timer is " + ew.getSNDIG(0);
		else if(press == 'A' && ew.numCharsInSN("25") > 0)
			return " when the seconds in\nthe bomb timer is 05 or 30";
		else if(press != 'Y' && ew.findLit("NSA"))
			return " when the seconds in the\nbomb timer are equal to each other";
		else
			return " when the seconds in\nthe bomb timer add up to 9";
	}
	private boolean v1(String i)
	{
		if(i.length() > 0)
		{
			for(char c : i.toCharArray())
			{
				if("0123456789".indexOf(c) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
	private boolean v2(String i)
	{
		if(i.length() == 0)
			return true;
		return v1(i);
	}
}
