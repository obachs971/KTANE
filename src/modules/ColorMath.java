package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class ColorMath 
{
	private final int[][] table1 =
		{
				{6000, 1000, 2000, 4000, 9000, 0, 8000, 5000, 3000, 7000},
				{800, 100, 900, 400, 300, 600, 0, 500, 700, 200},
				{40, 10, 90, 70, 0, 20, 50, 30, 80, 60},
				{6, 8, 7, 5, 4, 9, 1, 3, 0, 2}
		};
	private final int[][] table2 =
		{
				{0, 6000, 5000, 4000, 3000, 7000, 9000, 8000, 1000, 2000},
				{200, 900, 800, 0, 500, 300, 400, 700, 100, 600},
				{50, 0, 60, 40, 20, 70, 90, 30, 80, 10},
				{5, 4, 2, 9, 8, 6, 7, 1, 3, 0}
		};
	private final String[] table4 =
		{
				"AGOWPBMKYR",
				"BGKPMRAYOW",
				"MYBARKGPOW",
				"ABPRYMKOGW"
		};
	private final BombEdgework ew;
	public ColorMath(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		String input = JOptionPane.showInputDialog("Red, Orange, Yellow,\nGreen, Blue, Purple,\nMagenta, White, Gray, Black\nEnter the 4 colors on the\nleft, top to bottom (spaces)").toUpperCase();
		boolean v = v1(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Red, Orange, Yellow,\nGreen, Blue, Purple,\nMagenta, White, Gray, Black\nEnter the 4 colors on the\nleft, top to bottom (spaces)").toUpperCase();
			v = v1(input);
		}
		String[] colors = input.split(" ");
		int[] numbers = {0, 0};
		for(int aa = 0; aa < colors.length; aa++)
			numbers[0] += table1[aa][colorToColumn(colors[aa])];
		String letter = JOptionPane.showInputDialog("G - Green\nR - Red\nA, S, M, D\nEnter the color/letter:").toUpperCase();
		v = v2(letter);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			letter = JOptionPane.showInputDialog("G - Green\nR - Red\nA, S, M, D\nEnter the color/letter:").toUpperCase();
			v = v2(letter);
		}
		if(letter.charAt(0) == 'G')
		{
			input = JOptionPane.showInputDialog("Red, Orange, Yellow,\nGreen, Blue, Purple,\nMagenta, White, Gray, Black\nEnter the 4 colors on the\nright, top to bottom (spaces)").toUpperCase();
			v = v1(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Red, Orange, Yellow,\nGreen, Blue, Purple,\nMagenta, White, Gray, Black\nEnter the 4 colors on the\nright, top to bottom (spaces)").toUpperCase();
				v = v1(input);
			}
			colors = input.split(" ");
			for(int aa = 0; aa < colors.length; aa++)
				numbers[1] += table2[aa][colorToColumn(colors[aa])];
		}
		else
		{
			if(ew.BAT() >= 6)
				numbers[1] = (ew.findPort("DVI-D") * 1000) + 500 + (ew.numCharsInSN("BCDFGHJKLMNPQRSTVWXYZ") * 10) + ew.numLit();
			else if(ew.BAT() >= 4)
				numbers[1] = (ew.numCharsInSN("AEIOU") * 1000) + (ew.BH() * 100) + (ew.findPort("SERIAL") * 10) + 4;
			else if(ew.BAT() >= 2)
				numbers[1] = (ew.findPort("PS/2") * 100) + (ew.numSNLETS() * 10) + ew.getSNDIG(ew.numSNDIGS() - 1);
			else
				numbers[1] = (ew.getSNDIG(0) * 1000) + (ew.numUnlit() * 100) + 90 + ew.findPort("RJ-45");
		}
		//System.out.println(numbers[0]);
		//System.out.println(numbers[1]);
		switch(letter.charAt(1))
		{
			case 'A':
				numbers[0] += numbers[1];
				break;
			case 'S':
				numbers[0] -= numbers[1];
				break;
			case 'M':
				numbers[0] *= numbers[1];
				break;
			case 'D':
				numbers[0] /= numbers[1];
				break;
		}
		if(numbers[0] < 0)
			numbers[0] = numbers[0] * -1;
		numbers[0] = numbers[0] % 10000;
		//System.out.println(numbers[0]);
		String out = table4[0].charAt(numbers[0] / 1000) + " " + table4[1].charAt((numbers[0] / 100) % 10) + " " + table4[2].charAt((numbers[0] / 10) % 10) + " " + table4[3].charAt(numbers[0] % 10);
		colors = new String[] {"RED", "ORANGE", "YELLOW", "GREEN", "BLUE", "PURPLE", "MAGENTA", "WHITE", "GRAY", "BLACK"};
		for(int aa = 0; aa < 7; aa += 2)
			out = out + "\n" + colors["ROYGBPMWAK".indexOf(out.charAt(aa))];
		JOptionPane.showMessageDialog(null, "Submit these colors: " + out);
	}
	private int colorToColumn(String i)
	{
		String[] colors = {"BLUE", "GREEN", "PURPLE", "YELLOW", "WHITE", "MAGENTA", "RED", "ORANGE", "GRAY", "BLACK"};
		for(int aa = 0; aa < colors.length; aa++)
		{
			if(colors[aa].equals(i))
				return aa;
		}
		return -1;
	}
	private boolean v1(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 4)
		{
			for(int aa = 0; aa < conv.length; aa++)
			{
				if(colorToColumn(conv[aa]) == -1)
					return false;
			}
			return true;
		}
		return false;
	}
	private boolean v2(String i)
	{
		if(i.length() == 2)
		{
			if("GR".indexOf(i.charAt(0)) < 0 || "ASMD".indexOf(i.charAt(1)) < 0)
				return false;
			return true;
		}
		return false;
	}
}
