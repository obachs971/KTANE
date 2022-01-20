package modules;

import javax.swing.JOptionPane;

public class Maze 
{
	private final String[][] mazes =
		{
				{
					"WWWWWWWWWWWWW",
					"WSOSOSWSOSOSW",
					"WOWWWOWOWWWWW",
					"WSWSOSWSOSOSW",
					"WOWOWWWWWWWOW",
					"WSWSOSWSOSOSW",
					"WOWWWOWOWWWOW",
					"WSWSOSOSWSOSW",
					"WOWWWWWWWWWOW",
					"WSOSOSWSOSWSW",
					"WOWWWOWOWWWOW",
					"WSOSWSOSWSOSW",
					"WWWWWWWWWWWWW"
				},
				{
					"WWWWWWWWWWWWW",
					"WSOSOSWSOSOSW",
					"WWWOWWWOWOWWW",
					"WSOSWSOSWSOSW",
					"WOWWWOWWWWWOW",
					"WSWSOSWSOSOSW",
					"WOWOWWWOWWWOW",
					"WSOSWSOSWSWSW",
					"WOWWWOWWWOWOW",
					"WSWSWSWSOSWSW",
					"WOWOWOWOWWWOW",
					"WSWSOSWSOSOSW",
					"WWWWWWWWWWWWW"
				},
				{
					"WWWWWWWWWWWWW",
					"WSOSOSWSWSOSW",
					"WOWWWOWOWOWOW",
					"WSWSWSWSOSWSW",
					"WWWOWOWWWWWOW",
					"WSOSWSWSOSWSW",
					"WOWOWOWOWOWOW",
					"WSWSWSWSWSWSW",
					"WOWOWOWOWOWOW",
					"WSWSOSWSWSWSW",
					"WOWWWWWOWOWOW",
					"WSOSOSOSWSOSW",
					"WWWWWWWWWWWWW"
				},
				{
					"WWWWWWWWWWWWW",
					"WSOSWSOSOSOSW",
					"WOWOWWWWWWWOW",
					"WSWSWSOSOSOSW",
					"WOWOWOWWWWWOW",
					"WSWSOSWSOSWSW",
					"WOWWWWWOWWWOW",
					"WSWSOSOSOSOSW",
					"WOWWWWWWWWWOW",
					"WSOSOSOSOSWSW",
					"WOWWWWWWWOWOW",
					"WSOSOSWSOSWSW",
					"WWWWWWWWWWWWW"
				},
				{
					"WWWWWWWWWWWWW",
					"WSOSOSOSOSOSW",
					"WWWWWWWWWOWOW",
					"WSOSOSOSOSWSW",
					"WOWWWWWOWWWWW",
					"WSOSWSOSWSOSW",
					"WOWOWWWWWOWOW",
					"WSWSOSOSWSWSW",
					"WOWWWWWOWWWOW",
					"WSWSOSOSOSWSW",
					"WOWOWWWWWWWOW",
					"WSWSOSOSOSOSW",
					"WWWWWWWWWWWWW"
				},
				{
					"WWWWWWWWWWWWW",
					"WSWSOSWSOSOSW",
					"WOWOWOWWWOWOW",
					"WSWSWSWSOSWSW",
					"WOWOWOWOWWWOW",
					"WSOSWSWSWSOSW",
					"WOWWWWWOWOWWW",
					"WSOSWSOSWSWSW",
					"WWWOWOWOWOWOW",
					"WSOSWSWSWSOSW",
					"WOWWWWWOWWWOW",
					"WSOSOSOSWSOSW",
					"WWWWWWWWWWWWW"
				},
				{
					"WWWWWWWWWWWWW",
					"WSOSOSOSWSOSW",
					"WOWWWWWOWOWOW",
					"WSWSOSWSOSWSW",
					"WOWOWWWWWWWOW",
					"WSOSWSOSWSOSW",
					"WWWWWOWWWOWWW",
					"WSOSWSOSOSWSW",
					"WOWOWOWWWWWOW",
					"WSWSWSOSOSWSW",
					"WOWWWWWWWOWOW",
					"WSOSOSOSOSOSW",
					"WWWWWWWWWWWWW"
				},
				{
					"WWWWWWWWWWWWW",
					"WSWSOSOSWSOSW",
					"WOWOWWWOWOWOW",
					"WSOSOSWSOSWSW",
					"WOWWWWWWWWWOW",
					"WSWSOSOSOSWSW",
					"WOWOWWWWWOWOW",
					"WSWSOSWSOSOSW",
					"WOWWWOWWWWWWW",
					"WSWSWSOSOSOSW",
					"WOWOWWWWWWWWW",
					"WSOSOSOSOSOSW",
					"WWWWWWWWWWWWW"
				},
				{
					"WWWWWWWWWWWWW",
					"WSWSOSOSOSOSW",
					"WOWOWWWWWOWOW",
					"WSWSWSOSWSWSW",
					"WOWOWOWWWOWOW",
					"WSOSOSWSOSWSW",
					"WOWWWWWOWWWOW",
					"WSWSWSOSWSOSW",
					"WOWOWOWWWWWOW",
					"WSWSWSWSOSWSW",
					"WOWOWOWOWOWWW",
					"WSOSWSOSWSOSW",
					"WWWWWWWWWWWWW"
				}
		};
	public String run()
	{
		String input = JOptionPane.showInputDialog("Enter the col/row of the\ngreen circle, white dot,\nand the red triangle (spaces):").toUpperCase();
		boolean v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the col/row of the\n green circle, white dot,\nand the red triangle (spaces):").toUpperCase();
			v = valid(input);
		}
		String[] temp = input.split(" ");
		String souv = "STARTING POSITION: " + temp[1].toUpperCase();
		int[] GC = getPosition(temp[0]);
		int[] WD = getPosition(temp[1]);
		int[] RT = getPosition(temp[2]);
		String[] maze = getMaze(GC);
		maze[RT[1]] = maze[RT[1]].substring(0, RT[0]) + "*" + maze[RT[1]].substring(RT[0] + 1);
		/*for(int aa = 0; aa < maze.length; aa++)
			System.out.println(maze[aa]);*/
		String dir = "";
		while(maze[WD[1]].charAt(WD[0]) != '*')
		{
			if(maze[WD[1] - 1].charAt(WD[0]) != 'W')//UP
			{
				dir = dir + "U";
				maze[WD[1] - 1] = maze[WD[1] - 1].substring(0, WD[0]) + "W" + maze[WD[1] - 1].substring(WD[0] + 1);
				WD[1] -= 2;
			}
			else if(maze[WD[1]].charAt(WD[0] + 1) != 'W')//RIGHT
			{
				dir = dir + "R";
				maze[WD[1]] = maze[WD[1]].substring(0, WD[0] + 1) + "W" + maze[WD[1]].substring(WD[0] + 2);
				WD[0] += 2;
			}
			else if(maze[WD[1] + 1].charAt(WD[0]) != 'W')//DOWN
			{
				dir = dir + "D";
				maze[WD[1] + 1] = maze[WD[1] + 1].substring(0, WD[0]) + "W" + maze[WD[1] + 1].substring(WD[0] + 1);
				WD[1] += 2;
			}
			else if(maze[WD[1]].charAt(WD[0] - 1) != 'W')//LEFT
			{
				dir = dir + "L";
				maze[WD[1]] = maze[WD[1]].substring(0, WD[0] - 1) + "W" + maze[WD[1]].substring(WD[0]);
				WD[0] -= 2;
			}
			else//DEADEND
			{
				switch(dir.charAt(dir.length() - 1))
				{
					case 'U':
						WD[1] += 2;
						break;
					case 'R':
						WD[0] -= 2;
						break;
					case 'D':
						WD[1] -= 2;
						break;
					case 'L':
						WD[0] += 2;
						break;
				}
				dir = dir.substring(0, dir.length() - 1);
			}
		}
		String out = "";
		for(int aa = 0; aa < dir.length(); aa++)
		{
			out = out + "" + dir.charAt(aa);
			if((aa + 1) % 12 == 0)
				out = out + "\n";
			else if((aa + 1) % 3 == 0)
				out = out + " ";
		}
		JOptionPane.showMessageDialog(null, out);
		return souv;
	}
	private int[] getPosition(String c)
	{
		if(c.length() != 2)
			return new int[] {0, 0};
		return (new int[] {coordToNum(c.charAt(0)), coordToNum(c.charAt(1))});
	}
	private int coordToNum(char c)
	{
		switch(c)
		{
			case 'A':
			case '1':
				return 1;
			case 'B':
			case '2':
				return 3;
			case 'C':
			case '3':
				return 5;
			case 'D':
			case '4':
				return 7;
			case 'E':
			case '5':
				return 9;
			case 'F':
			case '6':
				return 11;
			default:
				return 0;
		}
	}
	private String[] getMaze(int[] GC)
	{
		switch(GC[0] + "" + GC[1])
		{
			case "13":
			case "115":
				return mazes[0];
			case "37":
			case "93":
				return mazes[1];
			case "77":
			case "117":
				return mazes[2];
			case "11":
			case "17":
				return mazes[3];
			case "711":
			case "95":
				return mazes[4];
			case "59":
			case "91":
				return mazes[5];
			case "31":
			case "311":
				return mazes[6];
			case "57":
			case "71":
				return mazes[7];
			case "19":
			case "53":
				return mazes[8];
			default:
				return null;
		}
	}
	private boolean valid(String i)
	{
		String[] temp = i.split(" ");
		if(temp.length == 3)
		{
			for(int aa = 0; aa < 3; aa++)
			{
				int[] coord = getPosition(temp[aa]);
				if(coord[0] < 1 || coord[1] < 1)
					return false;
			}
			if(getMaze(getPosition(temp[0])) != null)
				return true;
		}
		return false;
	}
}
