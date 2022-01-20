package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class FastMath 
{
	private final int[][] table =
		{
				{25, 11, 53, 97, 2, 42, 51, 97, 12, 86, 55, 73, 33},
				{54, 7, 32, 19, 84, 33, 27, 78, 26, 46, 9, 13, 58},
				{86, 37, 44, 1, 5, 26, 93, 49, 18, 69, 23, 40, 22},
				{54, 28, 77, 93, 11, 0, 35, 61, 27, 48, 13, 72, 80},
				{99, 36, 23, 95, 67, 5, 26, 17, 44, 60, 26, 41, 67},
				{74, 95, 3, 4, 56, 23, 54, 29, 52, 38, 10, 76, 98},
				{88, 46, 37, 96, 2, 52, 81, 37, 12, 70, 14, 36, 78},
				{54, 43, 12, 65, 94, 3, 47, 23, 16, 62, 73, 46, 21},
				{7, 33, 26, 1, 67, 26, 27, 77, 83, 14, 27, 93, 9},
				{63, 64, 94, 27, 48, 84, 33, 10, 16, 74, 43, 99, 4},
				{35, 39, 3, 25, 47, 62, 38, 45, 88, 48, 34, 31, 27},
				{67, 30, 27, 71, 9, 11, 44, 37, 18, 40, 32, 15, 78},
				{13, 23, 26, 85, 92, 12, 73, 56, 81, 7, 75, 47, 99}
		};
	private final BombEdgework ew;
	public FastMath(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{
		int offset = 0, result;
		if(ew.findLit("MSA"))
			offset += 20;
		if(ew.findPort("SERIAL") > 0)
			offset += 14;
		if(ew.numCharsInSN("FAST") > 0)
			offset -= 5;
		if(ew.findPort("RJ-45") > 0)
			offset += 27;
		if(ew.BAT() > 3)
			offset -= 15;
		String alpha = "ABCDEGKNPSTXZ", letters = JOptionPane.showInputDialog("Enter the displayed letters:").toUpperCase().replace(" ", "");
		boolean v = v1(letters);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			letters = JOptionPane.showInputDialog("Enter the displayed letters:").toUpperCase().replace(" ", "");
			v = v1(letters);
		}
		String souv = "";
		for(int aa = 0; aa < 4 && letters.length() == 2; aa++)
		{
			souv = "LETTERS: " + letters.toUpperCase();
			result = table[alpha.indexOf(letters.charAt(0))][alpha.indexOf(letters.charAt(1))] + offset;
			while(result < 0)
				result += 50;
			result = result % 100;
			String out = result + "";
			if(result < 10)
				out = "0" + out;
			letters = JOptionPane.showInputDialog("Submit " + out + "\nEnter the new displayed letters:").toUpperCase().replace(" ", "");
			v = v2(letters);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				letters = JOptionPane.showInputDialog("Submit " + out + "\nEnter the new displayed letters:").toUpperCase().replace(" ", "");
				v = v2(letters);
			}
		}
		if(letters.length() == 2)
		{
			souv = "LETTERS: " + letters.toUpperCase();
			result = table[alpha.indexOf(letters.charAt(0))][alpha.indexOf(letters.charAt(1))] + offset;
			while(result < 0)
				result += 50;
			result = result % 100;
			String out = result + "";
			if(result < 10)
				out = out + "0";
			JOptionPane.showMessageDialog(null, "Submit " + out);
		}
		return souv;
	}
	private boolean v1(String i)
	{
		if(i.length() == 2)
		{
			for(int aa = 0; aa < 2; aa++)
			{
				if("ABCDEGKNPSTXZ".indexOf(i.charAt(aa)) < 0)
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
		else
			return v1(i);
	}
}
