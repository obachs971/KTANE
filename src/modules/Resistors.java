package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class Resistors 
{
	private final BombEdgework ew;
	public Resistors(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		int target = (ew.getSNDIG(0) * 10) + ew.getSNDIG(1);
		for(int aa = 0; aa < ew.BAT() && aa < 6; aa++)
			target *= 10;
		String pri = "A", sec = "C";
		if(ew.getSNDIG(0) % 2 == 1)
			pri = "B";
		if(ew.getSNDIG(ew.numSNDIGS() - 1) % 2 == 1)
			sec = "D";
		ArrayList<String> connections = new ArrayList<String>();
		if(target == 0)
		{
			connections.add(pri + " -> " + sec);
			if(ew.findLit("FRK"))
				connections.add(pri + " -> " + "CD".replace(sec, ""));
			else if(ew.BD() >= 1)
				connections.add("AB".replace(pri, "") + " -> " + "CD".replace(sec, ""));
		}
		else
		{
			int[] resistors = new int[2];
			String[] pos = {"TOP", "BOTTOM"};
			for(int aa = 0; aa < 2; aa++)
			{
				String input = JOptionPane.showInputDialog("Red, Orange, Yellow,\nGreen, Blue, Violet,\nBrown, Black, Gray,\nWhite, Gold, Silver\nEnter the 3 colors on the\n" + pos[aa] + " resistor (spaces):").toUpperCase();
				boolean v = valid(input);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog("Red, Orange, Yellow,\nGreen, Blue, Violet,\nBrown, Black, Gray,\nWhite, Gold, Silver\nEnter the 3 colors on the\n" + pos[aa] + " resistor (spaces):").toUpperCase();
					v = valid(input);
				}
				String[] r = input.split(" ");
				resistors[aa] = getMulti(r[2], (getFirstNums(r[0]) * 10) + getFirstNums(r[1]));
			}
			if(resistors[0] == target)
			{
				connections.add(pri + " -> TOP");
				connections.add("TOP -> " + sec);
			}
			else if(resistors[1] == target)
			{
				connections.add(pri + " -> BOTTOM");
				connections.add("BOTTOM -> " + sec);
			}
			else if(resistors[0] < target && resistors[1] < target)
			{
				connections.add(pri + " -> TOP");
				connections.add("TOP -> BOTTOM");
				connections.add("BOTTOM -> " + sec);
			}
			else
			{
				connections.add(pri + " -> TOP");
				connections.add(pri + " -> BOTTOM");
				connections.add("TOP -> " + sec);
				connections.add("BOTTOM -> " + sec);
			}
			if(ew.findLit("FRK"))
			{
				if(connections.size() == 4)
				{
					connections.add("TOP -> " + "CD".replace(sec, ""));
					connections.add("BOTTOM -> " + "CD".replace(sec, ""));
				}
				else
					connections.add(connections.get(connections.size() - 1).substring(0, connections.get(connections.size() - 1).length() - 1) + "CD".replace(sec, ""));	
			}
			else if(ew.BD() >= 1)
				connections.add("AB".replace(pri, "") + " -> " + "CD".replace(sec, ""));		
		}
		String out = connections.get(0);
		for(int aa = 1; aa < connections.size(); aa++)
			out = out + "\n" + connections.get(aa);
		JOptionPane.showMessageDialog(null, out);
	}
			
	private int getFirstNums(String c)
	{
		switch(c)
		{
			case "BLACK":
				return 0;
			case "BROWN":
				return 1;
			case "RED":
				return 2;
			case "ORANGE":
				return 3;
			case "YELLOW":
				return 4;
			case "GREEN":
				return 5;
			case "BLUE":
				return 6;
			case "VIOLET":
				return 7;
			case "GRAY":
				return 8;
			case "WHITE":
				return 9;
		}
		return -1;
	}
	private int getMulti(String c, int res)
	{
		switch(c)
		{
			case "BLACK":
				return (res * 1);
			case "BROWN":
				return (res * 10);
			case "RED":
				return (res * 100);
			case "ORANGE":
				return (res * 1000);
			case "YELLOW":
				return (res * 10000);
			case "GREEN":
				return (res * 100000);
			case "BLUE":
				return (res * 1000000);
			case "VIOLET":
				return (res * 10000000);
			case "GOLD":
				return (res / 10);
			case "SILVER":
				return (res / 100);
		}
		return -1;
	}
	private boolean valid(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 3)
		{
			if(getFirstNums(conv[0]) != -1 && getFirstNums(conv[1]) != -1 && getMulti(conv[2], 1) != -1)
				return true;
		}
		return false;
	}
}

