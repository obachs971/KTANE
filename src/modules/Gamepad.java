package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class Gamepad 
{
	private final BombEdgework ew;
	public Gamepad(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{
		//Entering 4 digit number
		String command, input = JOptionPane.showInputDialog("Enter the 4 digit number:");
		boolean v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the 4 digit number:");
			v = valid(input);
		}
		String souv = "NUMBERS: " + input;
		int x = Integer.parseInt(input.substring(0, 2)), y = Integer.parseInt(input.substring(2)), a = x / 10, b = x % 10, c = y / 10, d = y % 10;
		//Getting the first subcommand
		if(prime(x))
			command = "▲▲▼▼";
		else if(x % 12 == 0)
			command = "▲A◀◀";
		else if((a + b) == 10 && ew.getSNDIG(ew.numSNDIGS() - 1) % 2 == 1)
			command = "AB◀▶";
		else if((x - 3) % 6 == 0 || (x - 5) % 10 == 0)
			command = "▼◀A▶";
		else if(x % 7 == 0 && y % 7 != 0)
			command = "◀◀▲B";
		else if(x == (c * d))
			command = "A▲◀◀";
		else if(square(x))
			command = "▶▶A▼";
		else if((x + 1) % 3 == 0 || ew.findUnlit("SND"))
			command = "▶AB▲";
		else if(60 <= x && x < 90 && ew.BAT() == 0)
			command = "BB▶◀";
		else if(x % 6 == 0)
			command = "ABA▶";
		else if(x % 4 == 0)
			command = "▼▼◀▲";
		else
			command = "A◀B▶";
		//Getting the second subcommand
		if(prime(y))
			command = command + "◀▶◀▶";
		else if(y % 8 == 0)
			command = command + "▼▶B▲";
		else if((c - d) == 4 && ew.findPort("RCA") > 0)
			command = command + "▶A▼▼";
		else if((y - 2) % 4 == 0 || ew.findLit("FRQ"))
			command = command + "B▲▶A";
		else if(y % 7 == 0 && x % 7 != 0)
			command = command + "◀◀▼A";
		else if(square(y))
			command = command + "▲▼B▶";
		else if((a * b) == y)
			command = command + "A▲◀▼";
		else if((y + 1) % 4 == 0 || ew.findPort("PS/2") > 0)
			command = command + "▲BBB";
		else if(c > d && ew.BAT() >= 2)
			command = command + "AA▲▼";
		else if(y % 5 == 0)
			command = command + "BAB◀";
		else if(y % 3 == 0)
			command = command + "▶▲▲◀";
		else
			command = command + "B▲A▼";
		//Global Overrides
		if(x % 11 == 0)
			command = command.charAt(1) + "" + command.charAt(0) + "" + command.substring(2, 4) + "" + command.charAt(6) + "" + command.charAt(5) + "" + command.charAt(4) + "" + command.charAt(7);
		if(a == (1 + d))
			command = command.substring(0, 2) + "" + command.charAt(3) + "" + command.charAt(2) + "" + command.charAt(4) + "" + command.charAt(7) + "" + command.charAt(6) + "" + command.charAt(5);
		if(highlyComposite(x) || highlyComposite(y))
			command = command.substring(4) + "" + command.substring(0, 4);
		if(square(x) && square(y))
		{
			String temp = "";
			for(int aa = 0; aa < command.length(); aa++)
				temp = command.charAt(aa) + "" + temp;
			command = temp.toUpperCase();
		}
		JOptionPane.showMessageDialog(null, "Enter this command:\n" + command.charAt(0) + " " + command.charAt(1) + " " + command.charAt(2) + " " + command.charAt(3) + "\n" + command.charAt(4) + " " + command.charAt(5) + " " + command.charAt(6) + " " + command.charAt(7));
		return souv;
	}
	//Returns true if the number is prime
	private boolean prime(int n)
	{
		if(n < 2)
			return false;
		for(int aa = 2; aa < n; aa++)
		{
			if(n % aa == 0)
				return false;
		}
		return true;
	}
	//Returns true if the number is a perfect square
	private boolean square(int n)
	{
		for(int aa = 1; (aa * aa) <= n; aa++)
		{
			if(n == (aa * aa))
				return true;
		}
		return false;
	}
	//Returns true if the number is Highly Composite
	private boolean highlyComposite(int n)
	{
		if(n == 0)
			return false;
		int best = 0;
		for(int aa = 1; aa < n; aa++)
		{
			ArrayList<Integer> temp = getDiv(aa);
			if(temp.size() > best)
				best = temp.size();
		}
		if(getDiv(n).size() > best)
			return true;
		return false;
	}
	//Returns an ArrayList containing of numbers that N is divisible by.
	private ArrayList<Integer> getDiv(int n)
	{
		ArrayList<Integer> divs = new ArrayList<Integer>();
		for(int aa = 1; aa <= n; aa++)
		{
			if(n % aa == 0)
				divs.add(aa);
		}
		return divs;
	}
	//Validation of input
	private boolean valid(String i)
	{
		if(i.length() == 4)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("0123456789".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
}
