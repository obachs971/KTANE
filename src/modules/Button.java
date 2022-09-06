package modules;

import javax.swing.JOptionPane;

import misc.PlayType;
import start.BombEdgework;

public class Button 
{
	private final BombEdgework ew;
	private final boolean isSouv;
	private String color;
	private final PlayType PT;
	public Button(BombEdgework e, boolean s, PlayType pt)
	{
		ew = e;
		isSouv = s;
		PT = pt;
	}
	public String run()
	{
		color = "";
		String button = JOptionPane.showInputDialog("BA - Blue Abort\nRP - Red Press\nYH - Yellow Hold\nWD - White Detonate\nEnter the color and label:").toUpperCase();
		boolean v = valid(button);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			button = JOptionPane.showInputDialog("BA - Blue Abort\nRP - Red Press\nYH - Yellow Hold\nWD - White Detonate\nEnter the color and label:").toUpperCase();
			v = valid(button);
		}
		if(button.endsWith("BA"))
			hold();
		else if(ew.BAT() > 1 && button.charAt(1) == 'D')
			JOptionPane.showMessageDialog(null, "Tap the button");
		else if(ew.findLit("CAR") && button.charAt(0) == 'W')
			hold();
		else if(ew.BAT() > 2 && ew.findLit("FRK"))
			JOptionPane.showMessageDialog(null, "Tap the button");
		else if(button.charAt(0) == 'Y')
			hold();
		else if(button.equals("RH"))
			JOptionPane.showMessageDialog(null, "Tap the button");
		else
			hold();
		return color;
	}
	private void hold()
	{
		if(PT == PlayType.EFM)
		{
			if(isSouv)
			{
				String input = JOptionPane.showInputDialog("Hold the button\nRelease when the\ntimer contains a:\nBlue - 4\nYellow - 5\nRed/White - 1\nEnter the color of the strip:").toUpperCase();
				getColor(input);
				while(color.length() == 0)
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog("Hold the button\nRelease when the\ntimer contains a:\nBlue - 4\nYellow - 5\nRed/White - 1\nEnter the color of the strip:").toUpperCase();
					getColor(input);
				}
			}
			else
				JOptionPane.showMessageDialog(null, "Hold the button\nRelease when the\ntimer contains a:\nBlue - 4\nYellow - 5\nRed/White - 1");
		}
		else
		{
			String input = JOptionPane.showInputDialog("Hold the button\nEnter the color of the strip:").toUpperCase();
			getColor(input);
			while(color.length() == 0)
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Hold the button\nEnter the color of the strip:").toUpperCase();
				getColor(input);
			}
			switch(input)
			{
				case "BLUE":
				case "B":
					JOptionPane.showMessageDialog(null, "Release when the\ntimer contains a 4");
					break;
				case "YELLOW":
				case "Y":
					JOptionPane.showMessageDialog(null, "Release when the\ntimer contains a 5");
					break;
				default:
					JOptionPane.showMessageDialog(null, "Release when the\ntimer contains a 1");
					break;
			}
		}
	}
	private boolean valid(String i)
	{
		if(i.length() == 2)
		{
			if("BRYW".indexOf(i.charAt(0)) >= 0 && "AHPD".indexOf(i.charAt(1)) >= 0)
				return true;
		}
		return false;
	}
	private void getColor(String i)
	{
		switch(i)
		{
			case "BLUE":
			case "B":
				color = "STRIP COLOR: BLUE";
				break;
			case "YELLOW":
			case "Y":
				color = "STRIP COLOR: YELLOW";
				break;
			case "RED":
			case "R":
				color = "STRIP COLOR: RED";
				break;
			case "WHITE":
			case "W":
				color = "STRIP COLOR: WHITE";
				break;
		}
	}
}
