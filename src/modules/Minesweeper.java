package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class Minesweeper 
{
	private BombEdgework ew;
	public Minesweeper(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{
		String colors = JOptionPane.showInputDialog("R - Red\nO - Orange\nY - Yellow\nG - Green\nB - Blue\nP - Purple\nK - Black\nEnter the colors in reading order:").toUpperCase();
		boolean v = valid(colors);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			colors = JOptionPane.showInputDialog("R - Red\nO - Orange\nY - Yellow\nG - Green\nB - Blue\nP - Purple\nK - Black\nEnter the colors in reading order:").toUpperCase();
			v = valid(colors);
		}
		colors = getStart(colors);
		JOptionPane.showMessageDialog(null, "Dig at " + colors);
		return ("STARTING CELL: " + colors.toUpperCase());
	}
	private String getStart(String colors)
	{
		String[] colorName = {"Red", "Orange", "Yellow", "Green", "Blue", "Purple", "Black"};
		int num = ew.getSNDIG(1);
		if(num == 0)
			num = 10;
		num--;
		num = "KGOYPRB".indexOf(colors.charAt(num % colors.length()));
		if(num == 0)
			return "";
		num += "-ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(ew.getSNLET(0));
		num--;
		return colorName["ROYGBPK".indexOf(colors.charAt(colors.length() - (num % colors.length()) - 1))];
	}
	private boolean valid(String i)
	{
		if(i.length() >= 5 && i.length() <= 7)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("ROYGBPK".indexOf(i.charAt(aa)) < 0)
					return false;
				for(int bb = aa + 1; bb < i.length(); bb++)
				{
					if(i.charAt(aa) == i.charAt(bb))
						return false;
				}
			}
			if(getStart(i).length() == 0)
				return false;
			return true;
		}
		return false;
	}
}
