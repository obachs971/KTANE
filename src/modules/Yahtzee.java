package modules;

import javax.swing.*;

import start.BombEdgework;
public class Yahtzee 
{
	private final BombEdgework ew;
	private String souv;
	private boolean flag;
	private boolean yahtzee;
	public Yahtzee(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{
		souv = "ROLL: ";
		flag = true;
		boolean flag2 = false;
		roll5();
		if(!(yahtzee))
		{
			String input = JOptionPane.showInputDialog("Enter number of rolled dice:");
			while(!(input.equals("")))
			{
				switch(input)
				{
					case "4":
						roll4();
						break;
					case "3":
						roll3();
						break;
					case "2":
						roll2();
						break;
					case "1":
						flag2 = true;
						roll1();
						break;
				}
				if(flag2)
					break;
				input = JOptionPane.showInputDialog("Enter number of rolled dice:");
			}
		}
		return souv;
	}
	private void roll5()
	{
		yahtzee = false;
		String nums = "";
		for(int aa = 0; aa < ew.numSNDIGS(); aa++)
		{
			if(ew.getSNDIG(aa) < 7 && ew.getSNDIG(aa) > 0)
			{
				nums = nums + "" + ew.getSNDIG(aa) + " ";
			}
		}
		String input = JOptionPane.showInputDialog("Y - Yahtzee\nLS - Large Straight\nSS - Small Straight\n3 - 3 of a kind\nFH - Full House\n4 - 4 of a kind\n2P - 2 pairs\nP - Pair\nRoll and enter the roll you made:").toUpperCase();
		boolean v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Y - Yahtzee\nLS - Large Straight\nSS - Small Straight\n3 - 3 of a kind\nFH - Full House\n4 - 4 of a kind\n2P - 2 pairs\nP - Pair\nRoll and enter the roll you made:").toUpperCase();
			v = valid(input);
		}
		String out = "";
		if(input.equals("LS"))
			out = "Keep a die with the highest value equal to one of these value(s): " + nums + "\nOr keep Purple";
		else if(input.equals("SS"))
			out = "Keep the outlier";
		else if(input.equals("3") || input.equals("FH"))
		{
			if(ew.numLit() >= 2)
				out = "Keep White";
			else if(ew.numUnlit() >= 2)
				out = "Keep Black";
			else
				out = "Keep the highest value not in the 3 of a kind";
		}
		else if(input.equals("4") || input.equals("2P"))
		{
			if(ew.BAT() <= 6)
				out = out + "Keep a die with a value of " + ew.BAT() + "\n";
			if(ew.BH() <= 6 && ew.BH() != ew.BAT())
				out = out + "Keep a die with a value of " + ew.BH() + "\n";
			out = out + "Keep Yellow";
		}
		else if(input.equals("P"))
		{
			if(ew.findPort("PARALLEL") > 0)
				out = "Keep Purple";
			else if(ew.findPort("PS/2") > 0)
				out = "Keep Blue";
			else if(ew.findPort("RCA") > 0)
				out = "Keep White";
			else if(ew.findPort("RJ-45") > 0)
				out = "Keep Black";
			else
				out = "Keep Yellow";
		}
		else if(input.equals("Y"))
		{
			out = "Y";
			yahtzee = true;
		}
		else
			out = "";
		
		if(out.equals(""))
		{
			JOptionPane.showMessageDialog(null, "Roll Again");
			flag = false;
			souv = souv + "NOTHING";
			roll5();
		}
		else
		{
			if(out.equals("Y"))
				JOptionPane.showMessageDialog(null, "CONGRATS!");
			else
				JOptionPane.showMessageDialog(null, out + "\n\nYou can keep dice of the same value");
			if(flag)
			{
				switch(input)
				{
					case "Y":
						souv = souv + "YAHTZEE";
						break;
					case "LS":
						souv = souv + "LARGE STRAIGHT";
						break;
					case "SS":
						souv = souv + "SMALL STRAIGHT";
						break;
					case "3":
						souv = souv + "THREE OF A KIND";
						break;
					case "FH":
						souv = souv + "FULL HOUSE";
						break;
					case "4":
						souv = souv + "FOUR OF A KIND";
						break;
					case "2P":
						souv = souv + "TWO PAIRS";
						break;
					case "P":
						souv = souv + "PAIR";
						break;
				}
			}
			
		}
	}
	private void roll4()
	{
		String out = "LS or SS: Keep a die/dice of a different value\n";
		out = out + "Keep 1: If it's not BLACK\n";
		out = out + "Keep 2: If neither are BLUE\n";
		if(ew.numPlates() != 3)
		{
			String nums = "";
			for(int aa = 0; aa < ew.numSNDIGS(); aa++)
			{
				if(ew.getSNDIG(aa) < 7 && ew.getSNDIG(aa) > 0)
					nums = nums + "" + ew.getSNDIG(aa + 1) + " ";
			}
			out = out + "Keep 3: If the other 2 dice are not any of these value(s): " + nums + "\n";
		}
		if(ew.numPlates() != 4)
			out = out + "Keep 4: If the fifth die has a larger value";
		JOptionPane.showMessageDialog(null, out);
	}
	private void roll3()
	{
		String out = "FH: ";
		if(ew.numDupPorts() > 0)
			out = out + "Reroll the pair\n";
		else
			out = out + "Reroll the three of a kind\n";
		
		out = out + "Keep 3: If PURPLE or WHITE was kept previously\n";
		out = out + "Keep 4: If the fifth die has a smaller value\n";
		String nums = "";
		for(int aa = 0; aa < ew.numSNDIGS(); aa++)
		{
			if(ew.getSNDIG(aa) < 7 && ew.getSNDIG(aa) > 0)
				nums = nums + "" + ew.getSNDIG(aa) + " ";
		}
		out = out + "Keeping any number of dice if the value(s) is " + nums;
		JOptionPane.showMessageDialog(null, out);
	}
	private void roll2()
	{
		JOptionPane.showMessageDialog(null, "Keep 4: If YELLOW or BLUE was kept previously");
	}
	private void roll1()
	{
		JOptionPane.showMessageDialog(null, "Keep rolling til YAHTZEE");
	}
	private boolean valid(String i)
	{
		switch(i)
		{
			case "LS":
			case "SS":
			case "FH":
			case "3":
			case "4":
			case "2P":
			case "P":
			case "Y":
			case "":
				return true;
			default:
				return false;
		}
	}
}
