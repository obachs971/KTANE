package modules;

import javax.swing.JOptionPane;

public class Gridlock 
{
	private final int[][] shapeChart =
		{
				{6, 2, 5, 3},
				{6, 1, 7, 8},
				{1, 8, 5, 7},
				{2, 4, 4, 3}
		};
	private final String[] blankChart =
		{
				"7618",	"1587",	"4324",	"3526",
				"3526",	"7618",	"1587",	"4324",
				"7158",	"4432",	"6352",	"8761",
				"1876",	"8715",	"2443",	"2635"
		};
	private final int[][] dirChart =
		{
				{},{-1, 0},{-1, 1},{0, 1},{1, 1},
				{1, 0},{1, -1},{0, -1},{-1, -1}
		};
	public String run()
	{
		char[][] grid =
			{
					{'O', 'O', 'O', 'O'},
					{'O', 'O', 'O', 'O'},
					{'O', 'O', 'O', 'O'},
					{'O', 'O', 'O', 'O'}
			};
		String input = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nA4, B3, C1, D2\nEnter the color and\ncol/row of the Star:").toUpperCase().replace(" ", "");
		boolean v = v1(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nA4, B3, C1, D2\nEnter the color and\ncol/row of the Star:").toUpperCase().replace(" ", "");
			v = v1(input);
		}
		String[] colors = {"RED", "BLUE", "YELLOW", "GREEN"};
		char color = input.charAt(0);
		String souv = "COLOR: " + colors["RBYG".indexOf(color)] + "\nSTART: " + input.toUpperCase();
		int[] cur = {"1234".indexOf(input.charAt(2)), "ABCD".indexOf(input.charAt(1))};
		grid[cur[0]][cur[1]] = 'W';
		String command = color + "S";
		String gridlock = "";
		while(true)
		{
			boolean shape = false;
			int[] dir;
			if(command.length() == 0)
				dir = dirChart["-12345678".indexOf(blankChart[(4 * "BGYR".indexOf(color)) + cur[0]].charAt(cur[1]))];
			else if(getDir(command) != 0)
				dir = dirChart[getDir(command)];
			else
			{
				color = command.charAt(0);
				dir = dirChart[shapeChart["TDHS".indexOf(command.charAt(1))]["BGYR".indexOf(command.charAt(0))]];
				shape = true;
			}
			int count = 0;
			while(grid[cur[0]][cur[1]] == 'W')
			{
				cur[0] = mod(cur[0] + dir[0], 4);
				cur[1] = mod(cur[1] + dir[1], 4);
				count++;
				if(count == 4)
				{
					gridlock = "ABCD".charAt(cur[1]) + "" + "1234".charAt(cur[0]);
					break;
				}
			}
			if(gridlock.length() > 0)
			{
				souv = souv + "\nEND: " + gridlock.toUpperCase();
				break;
			}
			grid[cur[0]][cur[1]] = 'W';
			if(shape)
				JOptionPane.showMessageDialog(null, "Press NEXT");
			command = JOptionPane.showInputDialog(null, "RH - Red Hexagon\nBD - Blue Diamond\nGT - Green Triangle\nYS - Yellow Star\nN/U, NE/TR, E/R, SE/BR,\nS/D, SW/BL, W/L, NW/TL\nEnter the info at " + "ABCD".charAt(cur[1]) + "" + "1234".charAt(cur[0])).toUpperCase();
			v = v2(command);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				command = JOptionPane.showInputDialog(null, "RH - Red Hexagon\nBD - Blue Diamond\nGT - Green Triangle\nYS - Yellow Star\nN/U, NE/TR, E/R, SE/BR,\nS/D, SW/BL, W/L, NW/TL\nEnter the info at " + "ABCD".charAt(cur[1]) + "" + "1234".charAt(cur[0])).toUpperCase();
				v = v2(command);
			}
		}
		JOptionPane.showMessageDialog(null, "Press " + gridlock);
		return souv;
	}
	private int getDir(String c)
	{
		switch(c)
		{
			case "N":case "U":case "T":
				return 1;
			case "NE":case "TR":
				return 2;
			case "E":case "R":
				return 3;
			case "SE":case "BR":
				return 4;
			case "S":case "D":case "B":
				return 5;
			case "SW":case "BL":
				return 6;
			case "W":case "L":
				return 7;
			case "NW":case "TL":
				return 8;
		}
		return 0;
	}
	private int mod(int n, int m)
	{
		while(n < 0)
			n += m;
		return (n % m);
	}
	private boolean v1(String i)
	{
		if(i.length() == 3)
		{
			if("RBYG".indexOf(i.charAt(0)) < 0)
				return false;
			if("ABCD".indexOf(i.charAt(1)) < 0)
				return false;
			if("1234".indexOf(i.charAt(2)) < 0)
				return false;
			return true;
		}
		return false;
	}
	private boolean v2(String i)
	{
		if(i.length() == 0)
			return true;
		if(getDir(i) != 0)
			return true;
		if(i.length() == 2)
		{
			if("BGYR".indexOf(i.charAt(0)) >= 0 && "TDHS".indexOf(i.charAt(1)) >= 0)
				return true;
		}
		return false;
	}
}
