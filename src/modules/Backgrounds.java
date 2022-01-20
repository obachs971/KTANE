package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class Backgrounds 
{
	private final String[] ruleTable =
		{
			"ADCDBFEBCE",
			"CBEDFEBCDA"
		};
	private final int[][] numberTable =
		{
				{3,2,9,1,7,4},
				{7,9,8,8,2,3},
				{5,1,7,4,4,6},
				{6,4,2,6,8,5},
				{5,1,5,3,9,9},
				{1,2,3,6,7,8}
		};
	private final BombEdgework ew;
	public Backgrounds(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		String input = JOptionPane.showInputDialog("Red, Orange, Yellow,\nGreen, Blue, Purple,\nWhite, Gray, Black\nEnter the colors of the\nbutton and the background (spaces):").toUpperCase();
		boolean v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Red, Orange, Yellow,\nGreen, Blue, Purple,\nWhite, Gray, Black\nEnter the colors of the\nbackground and the button (spaces):").toUpperCase();
			v = valid(input);
		}
		String[] colors = input.split(" ");
		ArrayList<String> pri = new ArrayList<String>();
		pri.add("RED");
		pri.add("BLUE");
		pri.add("YELLOW");
		ArrayList<String> sec = new ArrayList<String>();
		sec.add("ORANGE");
		sec.add("GREEN");
		sec.add("PURPLE");
		int cur = 0;
		String letters = "";
		if(colors[0].equals(colors[1]))
			letters = letters + "" + ruleTable[cur++ % 2].charAt(0);
		if((colors[0].equals("WHITE") || colors[0].equals("BLACK")) != (colors[1].equals("WHITE")))
			letters = letters + "" + ruleTable[cur++ % 2].charAt(1);
		if(ew.BD() == 0)
			letters = letters + "" + ruleTable[cur++ % 2].charAt(2);
		if(ew.BA() == 0)
			letters = letters + "" + ruleTable[cur++ % 2].charAt(3);
		if(pri.contains(colors[0]) && pri.contains(colors[1]))
			letters = letters + "" + ruleTable[cur++ % 2].charAt(4);
		if(sec.contains(colors[0]))
			letters = letters + "" + ruleTable[cur++ % 2].charAt(5);	
		if(ew.findUnlit("SND"))
			letters = letters + "" + ruleTable[cur++ % 2].charAt(6);
		if(ew.findPort("SERIAL") > 0)
			letters = letters + "" + ruleTable[cur++ % 2].charAt(7);
		if(colors[0].equals(mixBlue(colors[1])))
			letters = letters + "" + ruleTable[cur++ % 2].charAt(8);
		while(letters.length() < 2)
			letters = letters + "" + ruleTable[cur++ % 2].charAt(9);
		//System.out.println(letters);
		JOptionPane.showMessageDialog(null, "Submit this number: " + numberTable["ABCDEF".indexOf(letters.charAt(0))]["ABCDEF".indexOf(letters.charAt(1))]);
	}
	private String mixBlue(String c)
	{
		switch(c)
		{
			case "BLUE":
				return "BLUE";
			case "RED":
				return "PURPLE";
			case "YELLOW":
				return "GREEN";
			default:
				return "";
		}
	}
	private boolean valid(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 2)
		{
			switch(conv[0])
			{
				case "RED":
				case "ORANGE":
				case "YELLOW":
				case "GREEN":
				case "BLUE":
				case "PURPLE":
				case "WHITE":
				case "GRAY":
				case "BLACK":
					break;
				default:
					return false;
			}
			switch(conv[1])
			{
				case "RED":
				case "ORANGE":
				case "YELLOW":
				case "GREEN":
				case "BLUE":
				case "PURPLE":
				case "WHITE":
				case "GRAY":
					break;
				default:
					return false;
			}
			return true;
		}
		return false;
	}
}
