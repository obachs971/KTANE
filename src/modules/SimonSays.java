package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class SimonSays 
{
	private final String[] chart =
		{
			"BRYG",
			"YGBR",
			"GRYB",
			"BYGR",
			"RBYG",
			"YGBR"
		};
	private final BombEdgework ew;
	public SimonSays(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{
		int row = 3;
		if(ew.numCharsInSN("AEIOU") > 0)
			row = 0;
		String flashes = "";
		String souv = "FLASHED COLORS: ";
		for(int aa = 0; aa < 5; aa++)
		{
			String input = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nEnter flash #" + (aa + 1) + ":").toUpperCase();
			boolean v = v1(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nEnter flash #" + (aa + 1) + ":").toUpperCase();
				v = v1(input);
			}
			if(input.length() == 0)
				break;
			flashes = flashes + "" + input.toUpperCase();
			souv = souv + "" + input.toUpperCase() + " ";
			input = JOptionPane.showInputDialog("Enter the number\nof strikes:");
			v = v2(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the number\nof strikes:");
				v = v2(input);
			}
			int strikes = Integer.parseInt(input);
			if(strikes > 2)
				strikes = 2;
			String press = "";
			for(int bb = 0; bb < flashes.length(); bb++)
				press = press + "" + chart[row + strikes].charAt("RBGY".indexOf(flashes.charAt(bb))) + " ";
			JOptionPane.showMessageDialog(null, "Press these colors:\n" + press);
		}
		return souv;
	}
	private boolean v1(String i)
	{
		switch(i)
		{
			case "":
			case "R":
			case "B":
			case "Y":
			case "G":
				return true;
			default:
				return false;
		}
	}
	private boolean v2(String i)
	{
		for(int aa = 0; aa < i.length(); aa++)
		{
			if("0123456789".indexOf(i.charAt(aa)) < 0)
				return false;
		}
		return true;
	}
}
