package modules;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import start.BombConfig;
import start.BombEdgework;

public class Wire 
{
	private final BombEdgework ew;
	private final BombConfig con;
	private int INITIATE = -1;
	public Wire(BombConfig cf, BombEdgework e)
	{
		con = cf;
		ew = e;
	}
	public void run()
	{
		String input = JOptionPane.showInputDialog("Red, Orange, Green,\nBlue, Purple, Grey\nEnter the dial colors\nin reading order (spaces):").toUpperCase().replace("GRAY", "GREY");
		boolean v = v1(input, 3);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Red, Orange, Green,\nBlue, Purple, Grey\nEnter the dial colors\nin reading order (spaces):").toUpperCase().replace("GRAY", "GREY");
			v = v1(input, 3);
		}
		ArrayList<String> colors = new ArrayList<String>(Arrays.asList(input.split(" ")));
		input = JOptionPane.showInputDialog("Red, Orange, Green,\nBlue, Purple, Grey\nEnter the wire color\nand the digit (spaces):").toUpperCase().replace("GRAY", "GREY");
		v = v2(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Red, Orange, Green,\nBlue, Purple, Grey\nEnter the wire color\nand the digit (spaces):").toUpperCase().replace("GRAY", "GREY");
			v = v2(input);
		}
		String[] spl = input.split(" ");
		int digit;
		if("0123456789".indexOf(spl[0]) >= 0)
		{
			digit = Integer.parseInt(spl[0]);
			colors.add(spl[1]);
		}
		else
		{
			digit = Integer.parseInt(spl[1]);
			colors.add(spl[0]);
		}
		String dials = getDialPos(colors, digit);
		int num = 0;
		if(colors.get(0).equals("BLUE") || colors.get(0).equals("GREEN") || colors.get(0).equals("RED"))
			num += 1;
		if(colors.get(1).equals("ORANGE") || colors.get(1).equals("GREY") || colors.get(1).equals("BLUE"))
			num += 2;
		if(colors.get(2).equals("PURPLE") || colors.get(2).equals("RED") || colors.get(2).equals("ORANGE"))
			num += 4;
		if(colors.get(3).equals("GREEN") || colors.get(3).equals("GREY") || colors.get(3).equals("PURPLE"))
			num += 8;
		int time = getTime(num, digit);
		JOptionPane.showMessageDialog(null, "Dial 1: " + dials.charAt(0) + "\nDial 2: " + dials.charAt(1) + "\nDial 3: " + dials.charAt(2) + "\nCut the wire when the last\nsecond of the timer is a " + time);
	}
	private String getDialPos(ArrayList<String> colors, int digit)
	{
		if(ew.numCharsInSN("-ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(digit + ew.getSNSUM()) + "") > 0)
			return FC1(colors, digit, "Q23");
		else
			return FC2(colors, digit, "123");
	}
	private String FC1(ArrayList<String> colors, int digit, String dials) 
	{
		if((digit + ew.BAT()) > (colors.get(1).length() + colors.get(3).length()))
			return FC3(colors, digit, dials);
		else
			return FC4(colors, digit, dials.replace("2", "E"));
	}
	private String FC2(ArrayList<String> colors, int digit, String dials) 
	{
		if(colors.indexOf(colors.get(3)) < 3)
			return FC1(colors, digit, dials);
		else
			return FC5(colors, digit, dials);
	}
	private String FC3(ArrayList<String> colors, int digit, String dials) 
	{
		if(colors.contains("GREEN") && colors.contains("PURPLE"))
			return FC6(colors, digit, dials.replace("3", "Y"));
		else
			return FC5(colors, digit, dials.replace("2", "M"));
	}
	private String FC4(ArrayList<String> colors, int digit, String dials) 
	{
		if(prime(digit + 1))
			return FC7(colors, digit, dials);
		else
			return FC6(colors, digit, dials.replace("3", "T"));
	}
	private String FC5(ArrayList<String> colors, int digit, String dials) 
	{
		if(ew.numPorts() > digit)
			return FC4(colors, digit, dials);
		else
			return FC6(colors, digit, dials);
	}
	private String FC6(ArrayList<String> colors, int digit, String dials) 
	{
		if(digit == 7 || digit == 3 || colors.contains("RED"))
			return dials.replace("1", "Z").replace("2", "A").replace("3", "O");
		else
			return dials.replace("1", "U").replace("2", "S").replace("3", "B");
	}
	private String FC7(ArrayList<String> colors, int digit, String dials) 
	{
		if(colors.indexOf(colors.get(2)) < 2 || colors.indexOf(colors.get(1)) < 1)
			return dials.replace("1", "Q").replace("2", "E").replace("3", "Y");
		else
			return dials.replace("1", "I").replace("2", "M").replace("3", "T");
	}
	private int getTime(int n, int digit)
	{
		int a = digit;
		int c = ew.numInd() * 2;
		int d = ew.numPlates() * 4;
		int e = digit % 3;
		int f = ew.numUnlit();
		int g = ew.numPorts() - (ew.findPort("RCA") + ew.findPort("PS/2"));
		int h = con.getNumberModules();
		int i = digit * 6;
		int j = ew.numLit();
		switch(n)
		{
			case 0:
				return ((b() * (e + a)) % 8);
			case 1:
				return ((j * a + c) % 9);
			case 2:
				return ((a * c * f) % 8);
			case 3:
				return ((b() + c) % 6);
			case 4:
				return ((c * g) % 10);
			case 5:
				return ((g * b() + b()) % (e + 4));
			case 6:
				return ((4 * j) % 5);
			case 7:
				return ((g + a) % 10);
			case 8:
				return (((i + d + h) % 7) + 2);
			case 9:
				return ((j * (f + h)) % 9);
			case 10:
				return "0123456789".indexOf(((f * i + h) + "").charAt(0));
			case 11:
				return (((d % 7) + (i % 4)) % 10);
			case 12:
				return ((i + d - e) % 10);
			case 13:
				return (d % 10);
			case 14:
				return ((j + e + f) % 10);
			default:
				return "0123456789".indexOf(((3 * h + g) + "").charAt(0));
		}
	}
	private int b()
	{
		if(INITIATE < 0)
		{
			String input = JOptionPane.showInputDialog("Enter the number of times the\ninitiate button was pressed:");
			boolean v = v3(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the number of times the\ninitiate button was pressed:");
				v = v3(input);
			}
			INITIATE = Integer.parseInt(input);
		}
		return INITIATE;
	}
	private boolean prime(int n)
	{
		if(n < 2)
			return false;
		for(int i = 2; i < n; i++)
		{
			if(n % i == 0)
				return false;
		}
		return true;
	}
	private boolean v1(String i, int l)
	{
		String[] conv = i.split(" ");
		if(conv.length == l)
		{
			for(int aa = 0; aa < conv.length; aa++)
			{
				switch(conv[aa])
				{
					case "RED":case "ORANGE":case "GREEN":
					case "BLUE":case "PURPLE":case "GREY":
						break;
					default:
						return false;
				}
			}
			return true;
		}
		return false;
	}
	private boolean v2(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 2)
		{
			if("0123456789".indexOf(conv[0]) >= 0)
				return v1(conv[1], 1);
			else if("0123456789".indexOf(conv[1]) >= 0)
				return v1(conv[0], 1);
		}
		return false;
	}
	private boolean v3(String i)
	{
		if(i.length() > 0)
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
