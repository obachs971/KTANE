package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class FizzBuzz 
{
	private final int[][] table =
		{
				{7, 3, 2, 4, 5},
				{3, 4, 9, 2, 8},
				{4, 5, 8, 8, 2},
				{2, 3, 7, 9, 1},
				{6, 6, 1, 2, 8},
				{1, 2, 2, 5, 3},
				{3, 1, 8, 3, 4}
		};
	private final BombEdgework ew;
	public FizzBuzz(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		int[] offsets = {0, 0, 0, 0, 0};
		String colors = JOptionPane.showInputDialog("R - Red\nB- Blue\nY - Yellow\nG - Green\nW - White\nEnter the colors:").toUpperCase().replace(" ", "");
		boolean v = v1(colors);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			colors = JOptionPane.showInputDialog("R - Red\nB- Blue\nY - Yellow\nG - Green\nW - White\nEnter the colors:").toUpperCase().replace(" ", "");
			v = v1(colors);
		}
		String[] pos = {"top", "middle", "bottom"};
		int[][] numbers = new int[3][7];
		for(int aa = 0; aa < 3; aa++)
		{
			String input = JOptionPane.showInputDialog("Enter the " + pos[aa] + " number:");
			v = v2(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the " + pos[aa] + " number:");
				v = v2(input);
			}
			for(int bb = 0; bb < 7; bb++)
				numbers[aa][bb] = "0123456789".indexOf(input.charAt(bb));
		}
		if(ew.BH() >= 3)
		{
			for(int aa = 0; aa < 5; aa++)
				offsets[aa] += table[0][aa];
		}
		if(ew.findPort("SERIAL") > 0 && ew.findPort("PARALLEL") > 0)
		{
			for(int aa = 0; aa < 5; aa++)
				offsets[aa] += table[1][aa];
		}
		if(ew.numSNDIGS() == 3)
		{
			for(int aa = 0; aa < 5; aa++)
				offsets[aa] += table[2][aa];
		}
		if(ew.findPort("DVI-D") > 0 && ew.findPort("RCA") > 0)
		{
			for(int aa = 0; aa < 5; aa++)
				offsets[aa] += table[3][aa];
		}
		if(JOptionPane.showConfirmDialog(null, "Is there 2 or more strikes present?") == 0)
		{
			for(int aa = 0; aa < 5; aa++)
				offsets[aa] += table[4][aa];
		}
		if(ew.BAT() >= 5)
		{
			for(int aa = 0; aa < 5; aa++)
				offsets[aa] += table[5][aa];
		}
		if(offsets[0] == 0)
		{
			for(int aa = 0; aa < 5; aa++)
				offsets[aa] += table[6][aa];
		}
		String[] outs = new String[3];
		for(int aa = 0; aa < 3; aa++)
		{
			int sum = 0;
			for(int bb = 0; bb < 7; bb++)
			{
				numbers[aa][bb] = (numbers[aa][bb] + offsets["RGBYW".indexOf(colors.charAt(aa))]) % 10;
				sum += numbers[aa][bb];
			}
			if(sum % 3 == 0)
			{
				if(numbers[aa][6] % 5 == 0)
					outs[aa] = "FIZZBUZZ";
				else
					outs[aa] = "FIZZ";
			}
			else if(numbers[aa][6] % 5 == 0)
				outs[aa] = "BUZZ";
			else
				outs[aa] = "NUMBER";
		}
		JOptionPane.showMessageDialog(null, "Submit these values:\n" + outs[0] + "\n" + outs[1] + "\n" + outs[2]);	
	}
	private boolean v1(String i)
	{
		if(i.length() == 3)
		{
			for(int aa = 0; aa < 3; aa++)
			{
				if("RBYGW".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
	private boolean v2(String i)
	{
		if(i.length() == 7)
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
