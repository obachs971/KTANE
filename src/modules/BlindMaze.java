package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class BlindMaze 
{
	private final String[][] mazes =
		{
				{//0
					"WWWWWEWWWWW",
					"WWWWWOWWWWW",
					"WSOSOSWSOSW",
					"WOWOWWWOWOW",
					"WSWSOSOSWSW",
					"WOWWWOWWWOW",
					"WSOSWSOSWSW",
					"WWWOWWWOWWW",
					"WSOSWSWSOSW",
					"WOWOWOWWWOW",
					"WSWSOSWSOSW",
					"WWWWWWWWWWW"
				},
				{//1
					"WWWWWEWWWWW",
					"WWWWWOWWWWW",
					"WSOSWSWSOSW",
					"WWWOWOWOWOW",
					"WSOSOSWSWSW",
					"WOWWWWWOWWW",
					"WSOSOSOSOSW",
					"WOWOWWWWWOW",
					"WSWSWSWSOSW",
					"WOWWWOWWWOW",
					"WSOSOSWSOSW",
					"WWWWWWWWWWW"
				},
				{//2
					"WWWWWEWWWWW",
					"WWWWWOWWWWW",
					"WSOSWSOSWSW",
					"WOWWWOWWWOW",
					"WSOSOSOSOSW",
					"WWWWWWWWWOW",
					"WSOSOSOSOSW",
					"WWWOWOWOWWW",
					"WSWSWSWSOSW",
					"WOWOWOWWWOW",
					"WSOSWSOSWSW",
					"WWWWWWWWWWW"
				},
				{//3
					"WWWWWEWWWWW",
					"WWWWWOWWWWW",
					"WSOSWSOSOSW",
					"WWWOWWWOWOW",
					"WSOSOSOSWSW",
					"WOWWWWWWWOW",
					"WSOSWSOSOSW",
					"WOWOWOWOWWW",
					"WSWSWSWSOSW",
					"WWWWWOWOWOW",
					"WSOSOSWSWSW",
					"WWWWWWWWWWW"
				},
				{//4
					"WWWWWEWWWWW",
					"WWWWWOWWWWW",
					"WSOSOSOSWSW",
					"WOWOWWWOWOW",
					"WSWSWSWSOSW",
					"WWWOWOWWWOW",
					"WSOSOSWSOSW",
					"WOWWWWWOWWW",
					"WSWSOSWSOSW",
					"WOWWWOWWWOW",
					"WSOSWSOSOSW",
					"WWWWWWWWWWW"
				},
				{//5
					"WWWWWEWWWWW",
					"WWWWWOWWWWW",
					"WSOSWSOSOSW",
					"WOWOWWWWWOW",
					"WSWSOSWSOSW",
					"WOWOWWWOWWW",
					"WSWSOSOSOSW",
					"WOWOWWWOWOW",
					"WSWSWSOSWSW",
					"WWWOWOWWWOW",
					"WSOSWSOSWSW",
					"WWWWWWWWWWW"
				},
				{//6
					"WWWWWEWWWWW",
					"WWWWWOWWWWW",
					"WSOSOSOSOSW",
					"WOWOWWWWWOW",
					"WSWSWSOSWSW",
					"WOWWWOWOWOW",
					"WSWSOSWSOSW",
					"WOWWWWWOWOW",
					"WSOSWSOSWSW",
					"WWWOWWWWWOW",
					"WSOSOSWSOSW",
					"WWWWWWWWWWW"
				},
				{//7
					"WWWWWEWWWWW",
					"WWWWWOWWWWW",
					"WSOSWSWSWSW",
					"WWWOWOWOWOW",
					"WSOSWSWSOSW",
					"WOWOWOWOWWW",
					"WSWSOSWSOSW",
					"WOWWWOWWWOW",
					"WSWSWSOSWSW",
					"WOWOWWWOWOW",
					"WSWSOSOSOSW",
					"WWWWWWWWWWW"
				},
				{//8
					"WWWWWEWWWWW",
					"WWWWWOWWWWW",
					"WSOSOSWSOSW",
					"WOWWWOWOWWW",
					"WSWSOSOSOSW",
					"WOWOWWWWWOW",
					"WSWSOSWSOSW",
					"WOWWWWWWWOW",
					"WSOSWSOSWSW",
					"WWWWWOWOWOW",
					"WSOSOSWSOSW",
					"WWWWWWWWWWW"
				},
				{//9
					"WWWWWEWWWWW",
					"WWWWWOWWWWW",
					"WSWSOSWSOSW",
					"WOWWWOWOWOW",
					"WSOSWSWSWSW",
					"WWWOWOWOWWW",
					"WSWSOSOSOSW",
					"WOWWWWWOWWW",
					"WSOSWSOSOSW",
					"WOWOWOWOWWW",
					"WSWSOSWSOSW",
					"WWWWWWWWWWW"
				}
		};
	private final int[][] table = 
		{
				{1, 5, 2, 2, 3},
				{3, 1, 5, 5, 2},
				{2, 5, 3, 1, 4},
				{3, 2, 4, 3, 2}
		};
	private final BombEdgework ew;
	public BlindMaze(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{
		String colors = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nA - Gray\nEnter the colors in NEWS order:").toUpperCase().replace(" ", "");
		boolean v = valid(colors);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			colors = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nA - Gray\nEnter the colors in NEWS order:").toUpperCase().replace(" ", "");
			v = valid(colors);
		}
		int x = table[0]["RGBAY".indexOf(colors.charAt(0))] + table[3]["RGBAY".indexOf(colors.charAt(3))];
		while(x > 5)
			x -= 5;
		int y = table[1]["RGBAY".indexOf(colors.charAt(1))] + table[2]["RGBAY".indexOf(colors.charAt(2))];
		while(y > 5)
			y -= 5;
		int cw = 0;
		//After = Pos, Before = Neg
		if(colors.indexOf("R") != colors.lastIndexOf("R"))
			cw = 1;
		else if(ew.BAT() >= 5)
			cw = -1;
		else if(ew.findInd("IND"))
			cw = 2;
		else if(colors.indexOf("Y") < 0 && colors.indexOf("R") >= 0)
			cw = 3;
		else if(JOptionPane.showConfirmDialog(null, "Is any of the following modules present?\n3D Maze, A-maze-ing Buttons, Boolean Maze,\nColored Maze, The Crystal Maze, Factory Maze,\nFaulty RGB Maze, Hexamaze, Maze,\nMazematics, Maze Scrambler, Maze^3\nModule Maze, Morse-A-Maze, Mouse In The Maze,\nPolyhedral Maze, RGB Maze, USA Maze") == 0)
			cw = -2;
		else if(ew.numPortTypes() <= 1)
			cw = -3;
		for(int aa = 0; aa < cw; aa++)
		{
			int temp = x;
			x = y + 0;
			y = 6 - temp;
		}
		x = 1 + (2 * (x - 1));
		y = y * 2;
		if(cw < 0)
			cw = cw * -1;
		String input = JOptionPane.showInputDialog("10n Solves\nEnter the number of solves:");
		v = isNum(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("10n Solves\nEnter the number of solves:");
			v = isNum(input);
		}
		String souv = "N: " + new String[]{"RED", "BLUE", "YELLOW", "GREEN", "GRAY"}["RBYGA".indexOf(colors.charAt(0))] + "\n";
		souv = souv + "E: " + new String[]{"RED", "BLUE", "YELLOW", "GREEN", "GRAY"}["RBYGA".indexOf(colors.charAt(1))] + "\n";
		souv = souv + "W: " + new String[]{"RED", "BLUE", "YELLOW", "GREEN", "GRAY"}["RBYGA".indexOf(colors.charAt(2))] + "\n";
		souv = souv + "S: " + new String[]{"RED", "BLUE", "YELLOW", "GREEN", "GRAY"}["RBYGA".indexOf(colors.charAt(3))] + "\n";
		int index;
		do
		{
			int solves = Integer.parseInt(input);
			index = (ew.getSNDIG(ew.numSNDIGS() - 1) + solves) % 10;
			String[] maze = getMaze(index);
			String solution = solveMaze(maze, x, y);
			for(int aa = 0; aa < cw; aa++)
			{
				solution = solution.replace("N", "0");
				solution = solution.replace("W", "N");
				solution = solution.replace("S", "W");
				solution = solution.replace("E", "S");
				solution = solution.replace("0", "E");
			}
			String out = "";
			for(int aa = 0; aa < solution.length(); aa++)
			{
				out = out + "" + solution.charAt(aa);
				if((aa + 1) % 12 == 0)
					out = out + "\n";
				else if((aa + 1) % 3 == 0)
					out = out + " ";
			}
			if(solves % 10 == 0)
				out = "10n Solves\nFollow these dir:\n" + out;
			else
				out = "10n + " + (solves % 10) + " Solves\nFollow these dir:\n" + out;
			input = JOptionPane.showInputDialog(out + "\nEnter solves if needed:");
			v = isNum(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog(out + "\nEnter solves if needed:");
				v = isNum(input);
			}
		}while(input.length() > 0);
		souv = souv + "MAZE #: " + index;
		return souv;
	}
	private String[] getMaze(int index)
	{
		String[] temp = new String[mazes[index].length];
		for(int aa = 0; aa < temp.length; aa++)
			temp[aa] = mazes[index][aa].toUpperCase();
		return temp;
	}
	private String solveMaze(String[] maze, int x, int y)
	{
		String dir = "";
		while(maze[y].charAt(x) != 'E')
		{
			if(maze[y - 1].charAt(x) != 'W')//UP
			{
				dir = dir + "N";
				maze[y - 1] = maze[y - 1].substring(0, x) + "W" + maze[y - 1].substring(x + 1);
				y -= 2;
			}
			else if(maze[y].charAt(x + 1) != 'W')//RIGHT
			{
				dir = dir + "E";
				maze[y] = maze[y].substring(0, x + 1) + "W" + maze[y].substring(x + 2);
				x += 2;
			}
			else if(maze[y + 1].charAt(x) != 'W')//DOWN
			{
				dir = dir + "S";
				maze[y + 1] = maze[y + 1].substring(0, x) + "W" + maze[y + 1].substring(x + 1);
				y += 2;
			}
			else if(maze[y].charAt(x - 1) != 'W')//LEFT
			{
				dir = dir + "W";
				maze[y] = maze[y].substring(0, x - 1) + "W" + maze[y].substring(x);
				x -= 2;
			}
			else//DEADEND
			{
				switch(dir.charAt(dir.length() - 1))
				{
					case 'N':
						y += 2;
						break;
					case 'E':
						x -= 2;
						break;
					case 'S':
						y -= 2;
						break;
					case 'W':
						x += 2;
						break;
				}
				dir = dir.substring(0, dir.length() - 1);
			}
			//System.out.println(x + ", " + y);
			//System.out.println(maze[y].charAt(x));
		}
		return dir;
	}
	private boolean valid(String i)
	{
		if(i.length() == 4)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("RGBAY".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
	
	private boolean isNum(String i)
	{
		for(int aa = 0; aa < i.length(); aa++)
		{
			if("0123456789".indexOf(i.charAt(aa)) < 0)
				return false;
		}
		return true;
	}
	
}
