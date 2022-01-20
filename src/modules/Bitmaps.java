package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class Bitmaps 
{
	private final BombEdgework ew;
	public Bitmaps(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{
		String input = JOptionPane.showInputDialog("Enter the number of black\npixels in each quardrant\nin reading order (spaces):");
		boolean v = v1(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the number of black\npixels in each quardrant\nin reading order (spaces):");
			v = v1(input);
		}
		String[] temp = input.split(" ");
		int[][] BW = new int[4][2];
		int[] BWMost = {0, 0};
		for(int aa = 0; aa < temp.length; aa++)
		{
			BW[aa][0] = Integer.parseInt(temp[aa]);
			BW[aa][1] = 16 - BW[aa][0];
			if(BW[aa][0] > BW[aa][1])
				BWMost[0]++;
			else if(BW[aa][0] < BW[aa][1])
				BWMost[1]++;
		}
		int num = -1;
		int rule = ew.getSNDIG(ew.numSNDIGS() - 1);
		while(num < 0)
		{
			switch(rule)
			{
				case 0:
					if(BW[0][1] <= 5 && BW[1][1] > 5 && BW[2][1] > 5 && BW[3][1] > 5)
						num = BW[1][1] + BW[2][1] + BW[3][1];
					else if(BW[0][1] > 5 && BW[1][1] <= 5 && BW[2][1] > 5 && BW[3][1] > 5)
						num = BW[0][1] + BW[2][1] + BW[3][1];
					else if(BW[0][1] > 5 && BW[1][1] > 5 && BW[2][1] <= 5 && BW[3][1] > 5)
						num = BW[0][1] + BW[1][1] + BW[3][1];
					else if(BW[0][1] > 5 && BW[1][1] > 5 && BW[2][1] > 5 && BW[3][1] <= 5)
						num = BW[0][1] + BW[1][1] + BW[2][1];
					break;
				case 1:
					if(BWMost[1] == ew.numLit())
						num = ew.BAT();
					break;
				case 2:
					if(JOptionPane.showConfirmDialog(null, "Is there exactly 1 row/column\nthat is completely white/black?") == 0)
					{
						input = JOptionPane.showInputDialog("1 - Top/Left\nEnter the coordinate of the row/col:");
						v = v2(input);
						while(!(v))
						{
							JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
							input = JOptionPane.showInputDialog("1 - Top/Left\nEnter the coordinate of the row/col:");
							v = v2(input);
						}
						num = Integer.parseInt(input);
					}
					break;
				case 3:
					if(BWMost[1] < BWMost[0])
						num = BWMost[0];
					break;
				case 4:
					if((BW[0][1] + BW[1][1] + BW[2][1] + BW[3][1]) >= 36)
						num = BW[0][1] + BW[1][1] + BW[2][1] + BW[3][1];
					break;
				case 5:
					if(BWMost[1] > BWMost[0])
					{
						num = BW[0][0];
						for(int aa = 1; aa < 4; aa++)
						{
							if(BW[aa][0] < num)
								num = BW[aa][0];
						}
					}
					break;
				case 6:
					if(BW[0][0] <= 5 && BW[1][0] > 5 && BW[2][0] > 5 && BW[3][0] > 5)
						num = BW[1][0] + BW[2][0] + BW[3][0];
					else if(BW[0][0] > 5 && BW[1][0] <= 5 && BW[2][0] > 5 && BW[3][0] > 5)
						num = BW[0][0] + BW[2][0] + BW[3][0];
					else if(BW[0][0] > 5 && BW[1][0] > 5 && BW[2][0] <= 5 && BW[3][0] > 5)
						num = BW[0][0] + BW[1][0] + BW[3][0];
					else if(BW[0][0] > 5 && BW[1][0] > 5 && BW[2][0] > 5 && BW[3][0] <= 5)
						num = BW[0][0] + BW[1][0] + BW[2][0];
					break;
				case 7:
					if(BWMost[0] == ew.numUnlit())
						num = ew.numPorts();
					break;
				case 8:
					if(JOptionPane.showConfirmDialog(null, "Is there a 3x3 square that\nis completely white/black?") == 0)
					{
						input = JOptionPane.showInputDialog("1 - Left\nEnter the x-coordinate of the\ncenter square in reading order:");
						v = v3(input);
						while(!(v))
						{
							JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
							input = JOptionPane.showInputDialog("1 - Left\nEnter the x-coordinate of the\ncenter square in reading order:");
							v = v3(input);
						}
						num = Integer.parseInt(input);
					}
					break;
				case 9:
					if(BWMost[1] == BWMost[0])
						num = ew.getSNDIG(0);
					break;
			}
			rule = (rule + 1) % 10;
		}
		num = num % 4;
		if(num == 0)
			num = 4;
		JOptionPane.showMessageDialog(null, "Press " + num);
		String souv = "BLACK:\nTL: " + BW[0][0] + "\nTR: " + BW[1][0] + "\nBL: " + BW[2][0] + "\nBR: " + BW[3][0] + "\n----------\nWHITE:\nTL: " + BW[0][1] + "\nTR: " + BW[1][1] + "\nBL: " + BW[2][1] + "\nBR: " + BW[3][1];
		return souv;
	}
	private boolean v1(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 4)
		{
			for(int aa = 0; aa < conv.length; aa++)
			{
				switch(conv[aa])
				{
					case "0":
					case "1":
					case "2":
					case "3":
					case "4":
					case "5":
					case "6":
					case "7":
					case "8":
					case "9":
					case "10":
					case "11":
					case "12":
					case "13":
					case "14":
					case "15":
					case "16":
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
		switch(i)
		{
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
			case "8":
				return true;
			default:
				return false;
		}
	}
	private boolean v3(String i)
	{
		switch(i)
		{
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
				return true;
			default:
				return false;
		}
	}
}
