package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class NumberPad 
{
	private final int[] level1 = {2, 5, 7, 8};
	private final int[][] level2 =
		{
				{2, 3, 9, 0},
				{2, 6, 0, 9},
				{4, 7, 2, 0},
				{9, 6, 3, 1}
		};
	private final int[][][] level3 =
		{
				{{4, 3}, {9, 4}, {0, 9}, {7, 9}},
				{{6, 4}, {6, 2}, {2, 8}, {7, 4}},
				{{9, 8}, {1, 5}, {8, 0}, {0, 9}},
				{{6, 0}, {9, 2}, {3, 6}, {2, 3}}
		};
	private final int[][][][] level4 =
		{
				{{{6, 8}, {1, 3}},{{5, 1}, {3, 6}},{{9, 7}, {8, 9}},{{8, 9}, {4, 0}}},
				{{{0, 3}, {1, 7}},{{3, 5}, {8, 5}},{{6, 0}, {6, 0}},{{9, 8}, {2, 9}}},
				{{{1, 4}, {0, 8}},{{8, 5}, {8, 3}},{{6, 0}, {8, 2}},{{3, 4}, {0, 3}}},
				{{{7, 5}, {6, 1}},{{2, 9}, {0, 7}},{{6, 9}, {0, 6}},{{3, 8}, {3, 5}}}
		};
	private final BombEdgework ew;
	public NumberPad(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		String numpad = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nW - White\nEnter the colors of each\nnumber in reading order:").toUpperCase().replace(" ", "");
		boolean v = valid(numpad);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			numpad = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nW - White\nEnter the colors of each\nnumber in reading order:").toUpperCase().replace(" ", "");
			v = valid(numpad);
		}
		int[] path = new int[3];
		int[] nums = new int[4];
		if(getColorCount(numpad, 'Y') >= 3)
			path[0] = 0;
		else if("WBR".contains(numpad.charAt(3) + "") && "WBR".contains(numpad.charAt(4) + "") && "WBR".contains(numpad.charAt(5) + ""))
			path[0] = 1;
		else if(ew.numCharsInSN("AEIOU") > 0)
			path[0] = 2;
		else
			path[0] = 3;
		nums[0] = level1[path[0]];
		if(getColorCount(numpad, 'B') >= 2 && getColorCount(numpad, 'G') >= 3)
			path[1] = 0;
		else if("BW".indexOf(numpad.charAt(4)) < 0)
			path[1] = 1;
		else if(ew.numPorts() < 2)
			path[1] = 2;
		else
		{
			path[1] = 3;
			if(numpad.charAt(0) == 'G' || numpad.charAt(1) == 'G' || numpad.charAt(2) == 'G')
				nums[0] = (nums[0] + 9) % 10;
		}
		nums[1] = level2[path[0]][path[1]];
		if(getColorCount(numpad, 'W') > 2 && getColorCount(numpad, 'Y') > 2)
		{
			path[2] = 0;
			nums[2] = level3[path[0]][path[1]][path[2]];
		}
		else
		{
			path[2] = 1;
			nums[2] = nums[0] + 0;
			nums[0] = level3[path[0]][path[1]][path[2]];
		}
		if(getColorCount(numpad, 'Y') <= 2)
		{
			nums[3] = level4[path[0]][path[1]][path[2]][0];
			for(int aa = 0; aa < 4; aa++)
				nums[aa] = (nums[aa] + 1) % 10;
		}
		else
			nums[3] = level4[path[0]][path[1]][path[2]][1];
		
		//System.out.println(nums[0] + "" + nums[1] + "" + nums[2] + "" + nums[3]);
		
		if(ew.getSNDIG(ew.numSNDIGS() - 1) % 2 == 0)
		{
			int temp = nums[0] + 0;
			nums[0] = nums[2] + 0;
			nums[2] = temp + 0;
		}
		if(ew.BAT() % 2 == 1)
		{
			int temp = nums[1] + 0;
			nums[1] = nums[2] + 0;
			nums[2] = temp + 0;
		}
		if(ew.getSNDIG(ew.numSNDIGS() - 1) % 2 == 1 && ew.BAT() % 2 == 0)
		{
			int temp = nums[0] + 0;
			nums[0] = nums[3] + 0;
			nums[3] = temp + 0;
		}
		if((nums[0] + nums[1] + nums[2] + nums[3]) % 2 == 0)
		{
			int[] temp = {nums[3], nums[2], nums[1], nums[0]};
			nums = temp;
		}
		JOptionPane.showMessageDialog(null, "Submit this number: " + nums[0] + "" + nums[1] + "" + nums[2] + "" + nums[3]);
	}
	private int getColorCount(String np, char c)
	{
		int sum = 0;
		for(int aa = 0; aa < np.length(); aa++)
		{
			if(np.charAt(aa) == c)
				sum++;
		}
		return sum;
	}
	private boolean valid(String i)
	{
		if(i.length() == 10)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("RBYGW".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
	
}
