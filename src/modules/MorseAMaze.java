package modules;

import javax.swing.JOptionPane;

import misc.MTK;
import start.BombConfig;
import start.BombEdgework;

public class MorseAMaze 
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
				},
				{
					"WWWWWWWWWWWWW",
					"WSOSOSOSOSOSW",
					"WOWWWWWOWWWOW",
					"WSWSOSWSWSOSW",
					"WOWOWOWOWOWWW",
					"WSOSWSWSWSWSW",
					"WWWWWWWOWOWOW",
					"WSOSWSOSWSOSW",
					"WOWOWOWWWWWOW",
					"WSWSOSWSOSWSW",
					"WOWWWWWWWOWOW",
					"WSOSOSOSOSWSW",
					"WWWWWWWWWWWWW"
				},
				{
					"WWWWWWWWWWWWW",
					"WSOSOSOSOSOSW",
					"WWWWWOWOWWWOW",
					"WSOSWSWSWSOSW",
					"WOWOWOWOWOWWW",
					"WSWSWSWSWSOSW",
					"WOWOWWWOWWWWW",
					"WSWSOSWSOSOSW",
					"WOWWWOWWWWWOW",
					"WSWSOSOSOSOSW",
					"WOWWWWWWWWWOW",
					"WSOSOSOSWSOSW",
					"WWWWWWWWWWWWW"
				},
				{
					"WWWWWWWWWWWWW",
					"WSOSOSOSOSOSW",
					"WOWWWWWWWWWOW",
					"WSOSWSOSOSWSW",
					"WWWOWOWWWWWOW",
					"WSOSWSOSWSOSW",
					"WOWWWWWOWOWOW",
					"WSOSOSWSWSWSW",
					"WWWWWOWOWOWWW",
					"WSOSWSOSWSOSW",
					"WOWWWWWOWWWOW",
					"WSOSOSOSOSWSW",
					"WWWWWWWWWWWWW"
				},
				{
					"WWWWWWWWWWWWW",
					"WSOSWSOSOSOSW",
					"WOWOWOWOWWWWW",
					"WSWSOSWSOSOSW",
					"WWWWWOWWWWWOW",
					"WSOSWSWSOSOSW",
					"WOWOWWWOWWWOW",
					"WSWSOSOSWSWSW",
					"WOWWWOWWWOWOW",
					"WSOSWSWSOSWSW",
					"WOWOWWWOWWWOW",
					"WSWSOSOSWSOSW",
					"WWWWWWWWWWWWW"
				},
				{
					"WWWWWWWWWWWWW",
					"WSOSOSWSOSOSW",
					"WOWWWOWWWOWOW",
					"WSWSWSOSWSWSW",
					"WOWOWWWOWOWWW",
					"WSWSOSOSWSOSW",
					"WOWWWOWWWWWOW",
					"WSOSWSOSOSWSW",
					"WWWOWWWWWOWOW",
					"WSWSWSWSOSWSW",
					"WOWOWOWOWWWOW",
					"WSOSWSOSOSOSW",
					"WWWWWWWWWWWWW"
				},
				{
					"WWWWWWWWWWWWW",
					"WSOSOSOSOSOSW",
					"WOWWWWWWWWWOW",
					"WSWSOSOSOSWSW",
					"WOWWWWWOWOWOW",
					"WSWSOSOSWSWSW",
					"WOWOWWWOWWWOW",
					"WSOSWSOSWSWSW",
					"WWWWWOWWWOWOW",
					"WSOSWSOSWSOSW",
					"WOWWWWWOWWWWW",
					"WSOSOSOSOSOSW",
					"WWWWWWWWWWWWW"
				},
				{
					"WWWWWWWWWWWWW",
					"WSOSOSOSOSOSW",
					"WOWWWWWWWOWOW",
					"WSWSOSOSWSWSW",
					"WWWOWWWOWOWWW",
					"WSOSWSWSWSOSW",
					"WOWWWOWOWOWOW",
					"WSWSOSWSWSWSW",
					"WOWOWOWOWWWOW",
					"WSWSWSWSOSWSW",
					"WOWWWOWWWOWOW",
					"WSOSWSOSOSOSW",
					"WWWWWWWWWWWWW"
				},
				{
					"WWWWWWWWWWWWW",
					"WSOSOSOSWSOSW",
					"WOWWWOWOWOWOW",
					"WSOSWSWSOSWSW",
					"WWWWWOWWWWWOW",
					"WSOSOSWSOSOSW",
					"WOWWWWWOWWWWW",
					"WSWSOSWSWSOSW",
					"WOWOWOWOWOWWW",
					"WSWSWSWSWSWSW",
					"WOWOWOWWWOWOW",
					"WSOSWSOSOSOSW",
					"WWWWWWWWWWWWW"
				},
				{
					"WWWWWWWWWWWWW",
					"WSOSWSOSWSOSW",
					"WOWOWWWOWOWOW",
					"WSWSOSWSOSWSW",
					"WWWWWOWWWWWOW",
					"WSOSWSOSOSOSW",
					"WOWOWWWWWWWOW",
					"WSWSOSWSOSWSW",
					"WOWWWWWOWOWOW",
					"WSOSWSOSWSWSW",
					"WOWOWOWWWOWOW",
					"WSWSOSOSWSOSW",
					"WWWWWWWWWWWWW"
				}
		};
	private final BombConfig cf;
	private final BombEdgework ew;
	public MorseAMaze(BombConfig c, BombEdgework e)
	{
		cf = c;
		ew = e;
	}
	public String run()
	{
		MTK mtk = new MTK();
		String input = JOptionPane.showInputDialog("Enter the light's col/row:").toUpperCase();
		boolean v = isCoord(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the light's col/row:").toUpperCase();
			v = isCoord(input);
		}
		String souv = "START: " + input.toUpperCase();
		int[] cur = {"-1-2-3-4-5-6".indexOf(input.charAt(1)), "-A-B-C-D-E-F".indexOf(input.charAt(0))};
		input = JOptionPane.showInputDialog("Enter the morse code:").toUpperCase();
		input = mtk.morseToMessage(input.split(" "));
		v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the morse code:").toUpperCase();
			input = mtk.morseToMessage(input.split(" "));
			v = valid(input);
		}
		String[] info = new String[2];
		if(isCoord(input))
		{
			info[0] = input.toUpperCase();
			input = JOptionPane.showInputDialog("Enter the morse word:").toUpperCase();
			input = mtk.morseToMessage(input.split(" "));
			v = isWord(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the morse word:").toUpperCase();
				input = mtk.morseToMessage(input.split(" "));
				v = isWord(input);
			}
			info[1] = input.toUpperCase();
		}
		else
		{
			info[1] = input.toUpperCase();
			input = JOptionPane.showInputDialog("Enter the morse coord:").toUpperCase();
			input = mtk.morseToMessage(input.split(" "));
			v = isCoord(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the morse coord:").toUpperCase();
				input = mtk.morseToMessage(input.split(" "));
				v = isCoord(input);
			}
			info[0] = input.toUpperCase();
		}
		souv = souv + "\nEND: " + info[0] + "\nWORD: " + info[1];
		String[] maze = mazes[getMaze(info[1]) % 18];
		int[] goal = {"-1-2-3-4-5-6".indexOf(info[0].charAt(1)), "-A-B-C-D-E-F".indexOf(info[0].charAt(0))};
		String dir = "";
		while(cur[0] != goal[0] || cur[1] != goal[1])
		{
			if(maze[cur[0] - 1].charAt(cur[1]) != 'W')//UP
			{
				dir = dir + "U";
				maze[cur[0] - 1] = maze[cur[0] - 1].substring(0, cur[1]) + "W" + maze[cur[0] - 1].substring(cur[1] + 1);
				cur[0] -= 2;
			}
			else if(maze[cur[0]].charAt(cur[1] + 1) != 'W')//RIGHT
			{
				dir = dir + "R";
				maze[cur[0]] = maze[cur[0]].substring(0, cur[1] + 1) + "W" + maze[cur[0]].substring(cur[1] + 2);
				cur[1] += 2;
			}
			else if(maze[cur[0] + 1].charAt(cur[1]) != 'W')//DOWN
			{
				dir = dir + "D";
				maze[cur[0] + 1] = maze[cur[0] + 1].substring(0, cur[1]) + "W" + maze[cur[0] + 1].substring(cur[1] + 1);
				cur[0] += 2;
			}
			else if(maze[cur[0]].charAt(cur[1] - 1) != 'W')//LEFT
			{
				dir = dir + "L";
				maze[cur[0]] = maze[cur[0]].substring(0, cur[1] - 1) + "W" + maze[cur[0]].substring(cur[1]);
				cur[1] -= 2;
			}
			else//DEADEND
			{
				if(dir.length() == 0)
					break;
				switch(dir.charAt(dir.length() - 1))
				{
					case 'U':
						cur[0] += 2;
						break;
					case 'R':
						cur[1] -= 2;
						break;
					case 'D':
						cur[0] -= 2;
						break;
					case 'L':
						cur[1] += 2;
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
	private int getMaze(String message)
	{
		switch(message)
		{
			case "COUNT":
				if(ew.numTF() > 0)
				{
					String in1 = JOptionPane.showInputDialog("Enter the 2nd least sig\nfig of each two factor:");
					boolean v1 = (in1.length() == ew.numTF() && isNum(in1));
					while(!(v1))
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						in1 = JOptionPane.showInputDialog("Enter the 2nd least sig\nfig of each two factor:");
						v1 = (in1.length() == ew.numTF() && isNum(in1));
					}
					int num = 0;
					for(int aa = 0; aa < in1.length(); aa++)
						num += ("0123456789".indexOf(in1.charAt(aa)));
					return num;
				}
				else
				{
					String in2 = JOptionPane.showInputDialog("Enter the number of solves:");
					boolean v2 = (isNum(in2) && (cf.getNumberModules() >= (cf.getNumberNeedies() + Integer.parseInt(in2))));
					while(!(v2))
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						in2 = JOptionPane.showInputDialog("Enter the number of solves:");
						v2 = (isNum(in2) && (cf.getNumberModules() >= (cf.getNumberNeedies() + Integer.parseInt(in2))));
					}
					return (cf.getNumberModules() - (cf.getNumberNeedies() + Integer.parseInt(in2)));
				}
			case "ASSAY":
				String in3 = JOptionPane.showInputDialog("Enter the number of solves:");
				boolean v3 = isNum(in3);
				while(!(v3))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					in3 = JOptionPane.showInputDialog("Enter the number of solves:");
					v3 = isNum(in3);
				}
				return Integer.parseInt(in3);
			case "BOUGHT":
				String in4 = JOptionPane.showInputDialog("Enter the number of strikes:");
				boolean v4 = isNum(in4);
				while(!(v4))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					in4 = JOptionPane.showInputDialog("Enter the number of strikes:");
					v4 = isNum(in4);
				}
				return Integer.parseInt(in4);
			case "RABBIT":
				return ew.BH();
			case "STENCH":
				return ew.numPortTypes();
			case "SUBMIT":
				return ew.numPorts();
			case "SALADS":
				return ew.numLit();
			case "TRIBES":
				return ew.numUnlit();
			case "AWARDS":
				return ew.numInd();
			case "SWORD":
				return ew.numPlates();
			case "APRON":
				return ew.getSNDIG(ew.numSNDIGS() - 1);
			case "COUNTY":
				return ew.getSNSUM();
			case "MOSAIC":
				return ew.BAT();
			case "SUMMIT":
				return ew.getSNDIG(0);
			case "THINGS":
				return (int)cf.getStartingBombMinutes();
			case "MUSIC":
				switch(cf.getStartingDayName())
				{
					case "SUNDAY":
						return 0;
					case "MONDAY":
						return 1;
					case "TUESDAY":
						return 2;
					case "WEDNESDAY":
						return 3;
					case "THURSDAY":
						return 4;
					case "FRIDAY":
						return 5;
					default:
						return 6;
				}
			case "TACIT":
				return ew.numEmpty();
			case "THINKS":
				return ("ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(ew.getSNLET(0)));
			case "PULSES":
				return 0;
			case "PULSE":
				return 1;
			case "COUSIN":
				return 2;
			case "BRASS":
				return 3;
			case "SPURS":
				return 4;
			case "PROVE":
				return 5;
			case "GUARDS":
				return 6;
			case "ESSAYS":
				return 7;
			case "STROBE":
				return 8;
			case "STROKE":
				return 9;
			case "TACTIC":
				return 10;
			case "COUNTS":
				return 11;
			case "ARTIST":
				return 12;
			case "OPENER":
				return 13;
			case "AWARD":
				return 14;
			case "TOAST":
				return 15;
			case "STAYED":
				return 16;
			case "PRONE":
				return 17;	
		}
		return -1;
	}
	private boolean isNum(String i)
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
	private boolean valid(String i)
	{
		if(i == null)
			return false;
		return (isCoord(i) || isWord(i));
	}
	private boolean isCoord(String i)
	{
		if(i.length() == 2)
			return ("ABCDEF".indexOf(i.charAt(0)) >= 0 && "123456".indexOf(i.charAt(1)) >= 0);
		return false;
	}
	private boolean isWord(String i)
	{
		switch(i)
		{
			case "COUNT":
			case "ASSAY":	case "BOUGHT":
			case "RABBIT"	:case "STENCH":
			case "SUBMIT"	:case "SALADS":
			case "TRIBES"	:case "AWARDS":
			case "SWORD":	case "APRON":
			case "COUNTY"	:case "MOSAIC":
			case "SUMMIT"	:case "THINGS":
			case "MUSIC":	case "TACIT":
			case "THINKS":
			
			case "PULSES":	case "PULSE":	case "COUSIN":
			case "BRASS":	case "SPURS":	case "PROVE":
			case "GUARDS":	case "ESSAYS":	case "STROBE":
			case "STROKE":	case "TACTIC":	case "COUNTS":
			case "ARTIST":	case "OPENER":	case "AWARD":
			case "TOAST":	case "STAYED":	case "PRONE":
				return true;
			default:
				return false;
		}
	}
}
