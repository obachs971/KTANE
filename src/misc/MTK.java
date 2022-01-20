package misc;

import java.util.ArrayList;

public class MTK 
{
	/*
	 * Solves an logic expression using 2 booleans and a logic gate
	 * List of gates: AND, OR, XOR, NAND, NOR, XNOR, >, <
	 * @return Result of the logic gate performed on 2 booleans
	 */
	public boolean solveLogicExpression(boolean b1, boolean b2, String gate)
	{
		switch(gate)
		{
			case "AND":
				return (b1 && b2);
			case "OR":
				return (b1 || b2);
			case "XOR":
				return (b1 != b2);
			case "NAND":
				return !(b1 && b2);
			case "NOR":
				return !(b1 || b2);
			case "XNOR":
				return (b1 == b2);
			case ">":
				return (!(b1) || b2);
			case "<":
				return (b1 || !(b2));
		}
		return false;
	}
	public String morseToMessage(String[] code)
	{
		String message = "";
		for(int aa = 0; aa < code.length; aa++)
		{
			switch(code[aa])
			{
				case ".-":
				case "A":
					message = message + "A";
					break;
				case "-...":
				case "B":
					message = message + "B";
					break;
				case "-.-.":
				case "C":
					message = message + "C";
					break;
				case "-..":
				case "D":
					message = message + "D";
					break;
				case ".":
				case "E":
					message = message + "E";
					break;
				case "..-.":
				case "F":
					message = message + "F";
					break;
				case "--.":
				case "G":
					message = message + "G";
					break;
				case "....":
				case "H":
					message = message + "H";
					break;
				case "..":
				case "I":
					message = message + "I";
					break;
				case ".---":
				case "J":
					message = message + "J";
					break;
				case "-.-":
				case "K":
					message = message + "K";
					break;
				case ".-..":
				case "L":
					message = message + "L";
					break;
				case "--":
				case "M":
					message = message + "M";
					break;
				case "-.":
				case "N":
					message = message + "N";
					break;
				case "---":
				case "O":
					message = message + "O";
					break;
				case ".--.":
				case "P":
					message = message + "P";
					break;
				case "--.-":
				case "Q":
					message = message + "Q";
					break;
				case ".-.":
				case "R":
					message = message + "R";
					break;
				case "...":
				case "S":
					message = message + "S";
					break;
				case "-":
				case "T":
					message = message + "T";
					break;
				case "..-":
				case "U":
					message = message + "U";
					break;
				case "...-":
				case "V":
					message = message + "V";
					break;
				case ".--":
				case "W":
					message = message + "W";
					break;
				case "-..-":
				case "X":
					message = message + "X";
					break;
				case "-.--":
				case "Y":
					message = message + "Y";
					break;
				case "--..":
				case "Z":
					message = message + "Z";
					break;
				case ".----":
				case "1":
					message = message + "1";
					break;
				case "..---":
				case "2":
					message = message + "2";
					break;
				case "...--":
				case "3":
					message = message + "3";
					break;
				case "....-":
				case "4":
					message = message + "4";
					break;
				case ".....":
				case "5":
					message = message + "5";
					break;
				case "-....":
				case "6":
					message = message + "6";
					break;
				case "--...":
				case "7":
					message = message + "7";
					break;
				case "---..":
				case "8":
					message = message + "8";
					break;
				case "----.":
				case "9":
					message = message + "9";
					break;
				case "-----":
				case "0":
					message = message + "0";
					break;
				default:
					return "";
			}
		}
		return message;
	}
	public String[] messageToMorse(String message)
	{
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890-";
		String[] morseList =
			{
				".-", "-...", "-.-.", "-..", ".", "..-.",
				"--.", "....", "..", ".---", "-.-", ".-..",
				"--", "-.", "---", ".--.", "--.-", ".-.",
				"...", "-", "..-", "...-", ".--", "-..-",
				"-.--", "--..", ".----", "..---", "...--", "....-", 
				".....", "-....", "--...", "---..", "----.", "-----", 
				"-....-"
			};
		String[] morse = new String[message.length()];
		for(int aa = 0; aa < message.length(); aa++)
		{
			if(alpha.indexOf(message.charAt(aa)) < 0)
				return null;
			else
				morse[aa] = morseList[alpha.indexOf((message.charAt(aa)))];
		}
		return morse;
	}
	public String brailleToMessage(String braille)
	{
		String message = "";
		for(int aa = 0; aa < braille.length() / 6; aa++)
		{
			switch(braille.substring(aa * 6, (aa * 6) + 6))
			{
				case ".     ":
					message = message + "A";
					break;
				case "..    ":
					message = message + "B";
					break;
				case ".  .  ":
					message = message + "C";
					break;
				case ".  .. ":
					message = message + "D";
					break;
				case ".   . ":
					message = message + "E";
					break;
				case ".. .  ":
					message = message + "F";
					break;
				case ".. .. ":
					message = message + "G";
					break;
				case "..  . ":
					message = message + "H";
					break;
				case " . .  ":
					message = message + "I";
					break;
				case " . .. ":
					message = message + "J";
					break;
				case ". .   ":
					message = message + "K";
					break;
				case "...   ":
					message = message + "L";
					break;
				case ". ..  ":
					message = message + "M";
					break;
				case ". ... ":
					message = message + "N";
					break;
				case ". . . ":
					message = message + "O";
					break;
				case "....  ":
					message = message + "P";
					break;
				case "..... ":
					message = message + "Q";
					break;
				case "... . ":
					message = message + "R";
					break;
				case " ...  ":
					message = message + "S";
					break;
				case " .... ":
					message = message + "T";
					break;
				case ". .  .":
					message = message + "U";
					break;
				case "...  .":
					message = message + "V";
					break;
				case " . ...":
					message = message + "W";
					break;
				case ". .. .":
					message = message + "X";
					break;
				case ". ....":
					message = message + "Y";
					break;
				case ". . ..":
					message = message + "Z";
					break;
				case ".... .":
					message = message + "AND";
					break;
				case "......":
					message = message + "FOR";
					break;
				case " ... .":
					message = message + "THE";
					break;
				case " .....":
					message = message + "WITH";
					break;
				case "  ... ":
					message = message + "AR";
					break;
				case " ..   ":
					message = message + "BB";
					break;
				case " .  . ":
					message = message + "CC";
					break;
				case ".    .":
					message = message + "CH";
					break;
				case " .    ":
					message = message + "EA";
					break;
				case ".. . .":
					message = message + "ED";
					break;
				case " .   .":
					message = message + "EN";
					break;
				case ".. ...":
					message = message + "ER";
					break;
				case " .. . ":
					message = message + "FF";
					break;
				case " .. ..":
					message = message + "GG";
					break;
				case "..   .":
					message = message + "GH";
					break;
				case "  . . ":
					message = message + "IN";
					break;
				case "  .. .":
					message = message + "ING";
					break;
				case "... ..":
					message = message + "OF";
					break;
				case "..  ..":
					message = message + "OU";
					break;
				case " . . .":
					message = message + "OW";
					break;
				case ".  . .":
					message = message + "SH";
					break;
				case "  ..  ":
					message = message + "ST";
					break;
				case ".  ...":
					message = message + "TH";
					break;
				case ".   ..":
					message = message + "WH";
					break;
				default:
					return "";
			}
		}
		return message;
	}
	public String getMazeSolution(String[][] maze, int numFront, String curr, String goal)
	{
		ArrayList<String> cursors = new ArrayList<String>();
		ArrayList<String> togglePaths = new ArrayList<String>();
		ArrayList<String> prev = new ArrayList<String>();
		cursors.add(curr.toUpperCase());
		togglePaths.add("");
		prev.add(curr.toUpperCase());
		for(int aa = 0; aa < cursors.size(); aa++)
		{
			for(int bb = 0; bb < maze.length; bb++)
			{
				if(maze[bb][0].equals(cursors.get(aa)))
				{
					ArrayList<String> poss = new ArrayList<String>();
					ArrayList<String> possToggle = new ArrayList<String>();
					for(int cc = 1; cc < maze[bb].length; cc++)
					{
						if(!(prev.contains(maze[bb][cc].substring(numFront))))
						{
							if(numFront == 0)
							{
								poss.add(maze[bb][cc].substring(0));
								possToggle.add(maze[bb][cc].substring(0));
							}
							else
							{
								poss.add(maze[bb][cc].substring(numFront));
								possToggle.add(maze[bb][cc].substring(0, numFront));
							}
						}
					}
					if(poss.size() > 0)
					{
						prev.addAll(poss);
						String tempToggle = togglePaths.get(aa).toUpperCase();
						cursors.set(aa, poss.get(0).toUpperCase());
						togglePaths.set(aa, tempToggle + "" + possToggle.get(0).toUpperCase());
						for(int cc = 1; cc < poss.size(); cc++)
						{
							aa++;
							cursors.add(aa, poss.get(cc).toUpperCase());;
							togglePaths.add(aa, tempToggle + "" + possToggle.get(cc).toUpperCase());
						}
						if(cursors.contains(goal))
							return togglePaths.get(cursors.indexOf(goal));
					}
					else
					{
						cursors.remove(aa);
						togglePaths.remove(aa);
						aa--;
					}
					break;
				}
			}
			if(aa >= cursors.size() - 1)
				aa = -1;
		}
		return "";
	}
	
}
