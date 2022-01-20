package modules;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class MysticSquare 
{
	private final char[][] chart =
		{
				{'1', '3', '5', '4', '6', '7', '2', '8'},
				{'2', '5', '7', '3', '8', '1', '4', '6'},
				{'6', '4', '8', '1', '7', '3', '5', '2'},
				{'8', '1', '2', '5', '3', '4', '6', '7'},
				{'3', '2', '6', '8', '4', '5', '7', '1'},
				{'7', '6', '1', '2', '5', '8', '3', '4'},
				{'4', '7', '3', '6', '1', '2', '8', '5'},
				{'5', '8', '4', '7', '2', '6', '1', '3'}
		};
	private final String[] constellations =
		{
			"1?2???4?3", "1?2???3?4", "1?3???7?5", "1?3???5?7", 
			"?1?4?2?3?", "?1?3?2?4?", "?2?8?4?6?", "?2?6?4?8?", 
			"1???2???3", "??3?2?1??", "3???2???1", "??1?2?3??", 
			"123?4????", "1??24?3??", "????4?123", "??1?42??3"
		};
	private final BombEdgework ew;
	private final int pt;
	private final boolean isSouv;
	private String moveAllSpace;
	private String constellation;
	private boolean first;
	
	public MysticSquare(BombEdgework e, int playType, boolean s)
	{
		ew = e;
		pt = playType;
		isSouv = s;
	}
	public String run() throws IOException
	{
		String souv = "SKULL POSITION: ";
		if(pt == 1)
		{
			String input = JOptionPane.showInputDialog("- - Blank\nEnter the middle number:");
			boolean v = v2(input, "-12345678");
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("- - Blank\nEnter the middle number:");
				v = v2(input, "-12345678");
			}
			String out = "";
			if(input.equals("-"))
				out = "Skull is under 7";
			else
			{
				out = "Start at the empty space.\nThen for each number that's adjacent,\nmove to that number.\nThe last space is where the skull is\n";
				int rowcol = Integer.parseInt(input) - 1;
				if(JOptionPane.showConfirmDialog(null, "Is the number " + ew.getSNDIG(ew.numSNDIGS() - 1) + " on the\nTL, TR, MM, BL, or BR pos?") == 0)
				{
					for(int aa = 0; aa < 8; aa++)
					{
						out = out + "" + chart[rowcol][aa] + " ";
						if((aa + 1) % 4 == 0)
							out = out + "\n";
					}
				}
				else
				{
					for(int aa = 0; aa < 8; aa++)
					{
						out = out + "" + chart[aa][rowcol] + " ";
						if((aa + 1) % 4 == 0)
							out = out + "\n";
					}
				}
			}
			if(isSouv)
			{
				input = JOptionPane.showInputDialog(out + "\nEnter the skull's position:");
				v = v3(input);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog(out + "\nEnter the skull's position:");
					v = v3(input);
				}
				souv = souv + "" + input.toUpperCase();
			}
			else
				JOptionPane.showMessageDialog(null, out);
		}
		else
		{
			String[] posNames = {"TL", "TM", "TR", "ML", "MM", "MR", "BL", "BM", "BR"};
			String out = "";
			constellation = "";
			moveAllSpace = "";
			first = true;
			//Inputting the 3x3 grid.
			String grid = JOptionPane.showInputDialog("- - Blank\nEnter the numbers\nin reading order:").replace(" ", "").replace(",", "").replace("/", "");
			boolean v = valid(grid);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				grid = JOptionPane.showInputDialog("- - Blank\nEnter the numbers\nin reading order:").replace(" ", "").replace(",", "").replace("/", "");
				v = valid(grid);
			}
			boolean serialNumberCross = (grid.charAt(0) == ew.getSNCHAR(5) || grid.charAt(2) == ew.getSNCHAR(5) || grid.charAt(4) == ew.getSNCHAR(5) || grid.charAt(6) == ew.getSNCHAR(5) || grid.charAt(8) == ew.getSNCHAR(5));
			File moveList = new File("files/MysticSquareMoveList.txt");
			if(!(moveList.createNewFile()))
			{
				Scanner reader = new Scanner(moveList);
				while(reader.hasNextLine())
				{
					String config = reader.nextLine();
					boolean cross = reader.nextBoolean();
					reader.nextLine();
					if(grid.equals(config) && serialNumberCross == cross)
					{
						String[] temp = reader.nextLine().split(" ");
						first = false;
						String allNums = "12345678";
						for(int aa = 0; aa < temp[0].length(); aa++)
						{
							moveAllSpace = moveAllSpace + "" + temp[0].charAt(aa) + " ";
							allNums = allNums.replace(temp[0].charAt(aa) + "", "");
							if((aa + 1) % 4 == 0)
								moveAllSpace = moveAllSpace + "\n";
						}
						for(int aa = 0; aa < temp[1].length(); aa++)
						{
							constellation = constellation + "" + temp[1].charAt(aa) + " ";
							if((aa + 1) % 3 == 0)
								constellation = constellation + "\n";
						}
						for(int aa = 0; aa < temp[2].length(); aa++)
						{
							out = out + "" + temp[2].charAt(aa);
							if((aa + 1) % 12 == 0)
								out = out + "\n";
							else if((aa + 1) % 3 == 0)
								out = out + " ";
						}
						souv = souv + "" + posNames[grid.indexOf(allNums)];
						JOptionPane.showMessageDialog(null, "Move these spaces first:\n" + moveAllSpace + "\nConstellation Solution:\n" + constellation + "\nFull Solution:\n" + out);
						break;
					}
					else
							reader.nextLine();
				}
				reader.close();
			}
			if(first)
			{
				String[] tempGrid = new String[5];
				tempGrid[0] = "WWWWW";
				tempGrid[4] = "WWWWW";
				for(int aa = 1; aa < 4; aa++)
					tempGrid[aa] = "W" + grid.substring((aa - 1) * 3, aa * 3) + "W";

				//Finding the Skull
				int cursor = grid.indexOf('7');
				if(grid.charAt(4) != '-')
				{
					cursor = grid.indexOf('-');
					int rowcol = "12345678".indexOf(grid.charAt(4));
					if(serialNumberCross)
					{
						for(int aa = 0; aa < 8; aa++)
						{
							if(tempGrid[(cursor / 3)].charAt((cursor % 3) + 1) == chart[rowcol][aa])
								cursor -= 3;
							else if(tempGrid[(cursor / 3) + 1].charAt((cursor % 3) + 2) == chart[rowcol][aa])
								cursor += 1;
							else if(tempGrid[(cursor / 3) + 2].charAt((cursor % 3) + 1) == chart[rowcol][aa])
								cursor += 3;
							else if(tempGrid[(cursor / 3) + 1].charAt((cursor % 3)) == chart[rowcol][aa])
								cursor -= 1;
						}
					}
					else
					{
						for(int aa = 0; aa < 8; aa++)
						{
							if(tempGrid[(cursor / 3)].charAt((cursor % 3) + 1) == chart[aa][rowcol])
								cursor -= 3;
							else if(tempGrid[(cursor / 3) + 1].charAt((cursor % 3) + 2) == chart[aa][rowcol])
								cursor += 1;
							else if(tempGrid[(cursor / 3) + 2].charAt((cursor % 3) + 1) == chart[aa][rowcol])
								cursor += 3;
							else if(tempGrid[(cursor / 3) + 1].charAt((cursor % 3)) == chart[aa][rowcol])
								cursor -= 1;
						}
					}
				}
				souv = souv + "" + posNames[cursor];
				tempGrid[(cursor / 3) + 1] = tempGrid[(cursor / 3) + 1].replace(grid.charAt(cursor), 'W');
				
				
				//Setting up maze
				String[][] maze = new String[8][];
				int counter = 0;
				for(int aa = 1; aa < 4; aa++)
				{
					for(int bb = 1; bb < 4; bb++)
					{
						if(tempGrid[aa].charAt(bb) != 'W')
						{
							String temp = tempGrid[aa].charAt(bb) + "";
							if(tempGrid[aa - 1].charAt(bb) != 'W')
								temp = temp + " " + tempGrid[aa - 1].charAt(bb);
							if(tempGrid[aa].charAt(bb + 1) != 'W')
								temp = temp + " " + tempGrid[aa].charAt(bb + 1);
							if(tempGrid[aa + 1].charAt(bb) != 'W')
								temp = temp + " " + tempGrid[aa + 1].charAt(bb);
							if(tempGrid[aa].charAt(bb - 1) != 'W')
								temp = temp + " " + tempGrid[aa].charAt(bb - 1);
							maze[counter] = temp.split(" ");
							counter++;
						}
					}
				}
				ArrayList<String> longestPaths = getLongestPath("-", maze);
				
				ArrayList<String> solutionPaths = new ArrayList<String>();
				for(int aa = 0; aa < longestPaths.size(); aa++)
				{
					//System.out.println(longestPaths.get(aa));
					solutionPaths.add(finalStep(grid.toUpperCase(), longestPaths.get(aa).toUpperCase(), tempGrid));
					if(solutionPaths.get(solutionPaths.size() - 1).length() == 0)
						solutionPaths.remove(solutionPaths.size() - 1);
				}
				int best = solutionPaths.get(0).length();
				int cur = 0;
				for(int aa = 1; aa < solutionPaths.size(); aa++)
				{
					if(solutionPaths.get(aa).length() < best)
					{
						best = solutionPaths.get(aa).length();
						cur = aa;
					}
				}
				if(solutionPaths.get(cur).length() - moveAllSpace.length() >= 13)
				{
					FileWriter writer = new FileWriter(moveList, true);
					writer.write(grid + "\n" + serialNumberCross + "\n" + moveAllSpace + " " + constellation + " " + solutionPaths.get(cur) + "\n");
					writer.close();
				}
				String outConstellation = "";
				String outMove = "";
				for(int aa = 0; aa < moveAllSpace.length(); aa++)
				{
					outMove = outMove + "" + moveAllSpace.charAt(aa) + " ";
					if((aa + 1) % 4 == 0)
						outMove = outMove + "\n";
				}
				for(int aa = 0; aa < constellation.length(); aa++)
				{
					outConstellation = outConstellation + "" + constellation.charAt(aa) + " ";
					if((aa + 1) % 3 == 0)
						outConstellation = outConstellation + "\n";
				}
				for(int aa = 0; aa < solutionPaths.get(cur).length(); aa++)
				{
					out = out + "" + solutionPaths.get(cur).charAt(aa);
					if((aa + 1) % 12 == 0)
						out = out + "\n";
					else if((aa + 1) % 3 == 0)
						out = out + " ";
				}
				JOptionPane.showMessageDialog(null, "Move these spaces first:\n" + outMove + "\nConstellation Solution:\n" + outConstellation + "\nFull Solution:\n" + out);
			}
		}
		return souv;
	}
	private String finalStep(String grid, String longestPath, String[] tempGrid)
	{
		int cursor;
		if(longestPath.length() < 8)
		{
			String allNumbers = "12345678";
			for(int aa = 0; aa < longestPath.length(); aa++)
				allNumbers = allNumbers.replace(longestPath.charAt(aa) + "", "");
			cursor = grid.indexOf('-');
			int pathCur = 0;
			int index = 0;
			String tempPath = longestPath.toUpperCase().substring(1);
			do
			{
				if(allNumbers.contains(tempGrid[(cursor / 3)].charAt((cursor % 3) + 1) + ""))
				{
					tempPath = tempPath.substring(0, index) + "" + tempGrid[(cursor / 3)].charAt((cursor % 3) + 1) + "" + tempGrid[(cursor / 3)].charAt((cursor % 3) + 1) + "" + tempPath.substring(index);
					allNumbers = allNumbers.replace(tempPath.charAt(index + 1) + "", "");
					pathCur--;
					index++;
				}
				else if(allNumbers.contains(tempGrid[(cursor / 3) + 2].charAt((cursor % 3) + 1) + ""))
				{
					tempPath = tempPath.substring(0, index) + "" + tempGrid[(cursor / 3) + 2].charAt((cursor % 3) + 1) + "" + tempGrid[(cursor / 3) + 2].charAt((cursor % 3) + 1) + "" + tempPath.substring(index);
					allNumbers = allNumbers.replace(tempPath.charAt(index + 1) + "", "");
					pathCur--;
					index++;
				}
				else if(allNumbers.contains(tempGrid[(cursor / 3) + 1].charAt((cursor % 3)) + ""))
				{
					tempPath = tempPath.substring(0, index) + "" + tempGrid[(cursor / 3) + 1].charAt((cursor % 3)) + "" + tempGrid[(cursor / 3 + 1)].charAt((cursor % 3)) + "" + tempPath.substring(index);
					allNumbers = allNumbers.replace(tempPath.charAt(index + 1) + "", "");
					pathCur--;
					index++;
				}
				else if(allNumbers.contains(tempGrid[(cursor / 3) + 1].charAt((cursor % 3) + 2) + ""))
				{
					tempPath = tempPath.substring(0, index) + "" + tempGrid[(cursor / 3) + 1].charAt((cursor % 3) + 2) + "" + tempGrid[(cursor / 3) + 1].charAt((cursor % 3) + 2) + "" + tempPath.substring(index);
					allNumbers = allNumbers.replace(tempPath.charAt(index + 1) + "", "");
					pathCur--;
					index++;
				}
				index++;
				pathCur++;
				//System.out.println(allNumbers + " " + tempPath + " " + pathCur);
				cursor = grid.indexOf(longestPath.charAt(pathCur));
			}while(allNumbers.length() > 1);
			longestPath = "-" + tempPath.toUpperCase();
		}
		
		
		
		//Start finding out the solutions
		ArrayList<String> solutions = new ArrayList<String>();
		solutions.add("12345678-");
		int[][] ColRowSum = new int[2][3];
		for(int aa = 0; aa < 3; aa++)
		{
			ColRowSum[0][aa] = "-12345678".indexOf(grid.charAt(aa)) + "-12345678".indexOf(grid.charAt(aa + 3)) + "-12345678".indexOf(grid.charAt(aa + 6));
			ColRowSum[1][aa] = "-12345678".indexOf(grid.charAt(0 + (aa * 3))) + "-12345678".indexOf(grid.charAt(1 + (aa * 3))) + "-12345678".indexOf(grid.charAt(2 + (aa * 3)));
		}
		if(ColRowSum[0][0] > ColRowSum[0][1] && ColRowSum[0][0] > ColRowSum[0][2])
			cursor = 0;
		else if(ColRowSum[0][1] > ColRowSum[0][0] && ColRowSum[0][1] > ColRowSum[0][2])
			cursor = 1;
		else if(ColRowSum[0][2] > ColRowSum[0][0] && ColRowSum[0][2] > ColRowSum[0][1])
			cursor = 2;
		else
			cursor = 3;
		if(ColRowSum[1][0] > ColRowSum[1][1] && ColRowSum[1][0] > ColRowSum[1][2])
			solutions.add(constellations[cursor].toUpperCase());
		else if(ColRowSum[1][1] > ColRowSum[1][0] && ColRowSum[1][1] > ColRowSum[1][2])
			solutions.add(constellations[cursor + 4].toUpperCase());
		else if(ColRowSum[1][2] > ColRowSum[1][0] && ColRowSum[1][2] > ColRowSum[1][1])
			solutions.add(constellations[cursor + 8].toUpperCase());
		else
			solutions.add(constellations[cursor + 12].toUpperCase());
		//System.out.println(solutions.toString());
		if(first)
		{
			moveAllSpace = longestPath.substring(1);
			constellation = solutions.get(1).toUpperCase();
			first = false;
		}
		//Prepare grid for solving
		boolean skip = false;
		for(int aa = 1; aa < longestPath.length(); aa++)
		{
			grid = getNumberMove(grid, longestPath.charAt(aa));
			if(isSolved(grid, solutions))
			{
				
				longestPath = longestPath.substring(0, aa + 1);
				skip = true;
				break;
			}
		}
		if(!(skip))
		{
			String temp = solving(grid, solutions);
			if(temp.length() == 0)
				return "";
			else
				longestPath = longestPath + "" + temp;
		}
		return longestPath.substring(1);
	}
	private ArrayList<String> getLongestPath(String cursor, String[][] maze)
	{
		ArrayList<String> prev = new ArrayList<String>();
		prev.add(cursor.toUpperCase());
		ArrayList<String> cursors = new ArrayList<String>();
		cursors.add(cursor.toUpperCase());
		ArrayList<String> allPaths = new ArrayList<String>();
		for(int aa = 0; aa < cursors.size(); aa++)
		{
			for(int bb = 0; bb < maze.length; bb++)
			{
				if(maze[bb][0].equals(cursors.get(aa)))
				{
					ArrayList<String> possSpace = new ArrayList<String>();
					for(int cc = 1; cc < maze[bb].length; cc++)
					{
						if(!(prev.get(aa).contains((maze[bb][cc]))))
							possSpace.add(maze[bb][cc].toUpperCase());
					}
					if(possSpace.size() > 0)
					{
						String tempPrev = prev.get(aa).toUpperCase();
						cursors.set(aa, possSpace.get(0).toUpperCase());
						prev.set(aa, tempPrev + "" + cursors.get(aa));
						for(int cc = 1; cc < possSpace.size(); cc++)
						{
							aa++;
							cursors.add(aa, possSpace.get(cc).toUpperCase());;
							prev.add(aa, tempPrev + "" + cursors.get(aa));
						}
					}
					else
					{
						allPaths.add(prev.get(aa).toUpperCase());
						cursors.remove(aa);						
						prev.remove(aa);
						aa--;
					}
					break;
				}
			}
			if(aa >= cursors.size() - 1)
				aa = -1;
		}
		ArrayList<String> longestPaths = new ArrayList<String>();
		longestPaths.add(allPaths.get(0));
		for(int aa = 1; aa < allPaths.size(); aa++)
		{
			if(allPaths.get(aa).length() > longestPaths.get(0).length())
			{
				longestPaths.clear();
				longestPaths.add(allPaths.get(aa));
			}
			else if(allPaths.get(aa).length() == longestPaths.get(0).length())
				longestPaths.add(allPaths.get(aa));
		}
		return longestPaths;
	}
	private String getNumberMove(String grid, char number)
	{
		switch(grid.indexOf('-'))
		{
			case 0:
				if(grid.indexOf(number) == 1)
					return (grid.charAt(1) + "-" + grid.substring(2));
				else
					return (grid.charAt(3) + "" + grid.substring(1, 3) + "-" + grid.substring(4));
			case 1:
				if(grid.indexOf(number) == 0)
					return ("-" + grid.charAt(0) + "" + grid.substring(2));
				else if(grid.indexOf(number) == 2)
					return (grid.charAt(0) + "" + grid.charAt(2) + "-" + grid.substring(3));
				else
					return (grid.charAt(0) + "" + grid.charAt(4) + "" + grid.substring(2, 4) + "-" + grid.substring(5));
			case 2:
				if(grid.indexOf(number) == 1)
					return (grid.charAt(0) + "-" + grid.charAt(1) + "" + grid.substring(3));
				else
					return (grid.substring(0, 2) + "" + grid.charAt(5) + "" + grid.substring(3, 5) + "-" + grid.substring(6));
			case 3:
				if(grid.indexOf(number) == 0)
					return ("-" + grid.substring(1, 3) + "" + grid.charAt(0)) + "" + grid.substring(4);
				else if(grid.indexOf(number) == 4)
					return (grid.substring(0, 3) + "" + grid.charAt(4) + "-" + grid.substring(5));
				else
					return (grid.substring(0, 3) + "" + grid.charAt(6) + "" + grid.substring(4, 6) + "-" + grid.substring(7));
			case 4:
				if(grid.indexOf(number) == 1)
					return (grid.charAt(0) + "-" + grid.substring(2, 4) + "" + grid.charAt(1) + "" + grid.substring(5));
				else if(grid.indexOf(number) == 3)
					return (grid.substring(0, 3) + "-" + grid.charAt(3) + "" + grid.substring(5));
				else if(grid.indexOf(number) == 5)
					return (grid.substring(0, 4) + "" + grid.charAt(5) + "-" + grid.substring(6));
				else
					return (grid.substring(0, 4) + "" + grid.charAt(7) + "" + grid.substring(5, 7) + "-" + grid.substring(8));
			case 5:
				if(grid.indexOf(number) == 2)
					return (grid.substring(0, 2) + "-" + grid.substring(3, 5)) + "" + grid.charAt(2) + "" + grid.substring(6);
				else if(grid.indexOf(number) == 4)
					return (grid.substring(0, 4) + "-" + grid.charAt(4) + "" + grid.substring(6));
				else
					return (grid.substring(0, 5) + "" + grid.charAt(8) + "" + grid.substring(6, 8) + "-");
			case 6:
				if(grid.indexOf(number) == 3)
					return (grid.substring(0, 3) + "-" + grid.substring(4, 6) + "" + grid.charAt(3) + "" + grid.substring(7));
				else
					return (grid.substring(0, 6) + "" + grid.charAt(7) + "-" + grid.substring(8));
			case 7:
				if(grid.indexOf(number) == 4)
					return (grid.substring(0, 4) + "-" + grid.substring(5, 7) + "" + grid.charAt(4) + "" + grid.substring(8));
				else if(grid.indexOf(number) == 6)
					return (grid.substring(0, 6) + "-" + grid.charAt(6) + "" + grid.substring(8));
				else
					return (grid.substring(0, 7) + "" + grid.charAt(8) + "-");
			default:
				if(grid.indexOf(number) == 5)
					return (grid.substring(0, 5) + "-" + grid.substring(6, 8) + "" + grid.charAt(5));
				else
					return (grid.substring(0, 7) + "-" + grid.charAt(7));
		}
	}
	private boolean isSolved(String grid, ArrayList<String> solutions)
	{
		if(grid.equals(solutions.get(0)))
			return true;
		for(int aa = 0; aa < 9; aa++)
		{
			if(solutions.get(1).charAt(aa) != '?' && solutions.get(1).charAt(aa) != grid.charAt(aa))
				return false;
		}
		return true;
	}
	private String solving(String grid, ArrayList<String> solutions)
	{
		ArrayList<String> prev = new ArrayList<String>();
		prev.add(grid.toUpperCase());
		ArrayList<String> presses = new ArrayList<String>();
		presses.add("");
		ArrayList<String> gridStates = new ArrayList<String>();
		gridStates.add(grid.toUpperCase());
		for(int aa = 0; aa < presses.size(); aa++)
		{
			
			//System.out.println(presses.size());
			String allPresses = getPossibleNumberMoves(gridStates.get(aa));
			ArrayList<String> possGridStates = new ArrayList<String>();
			String possPresses = "";
			for(int bb = 0; bb < allPresses.length(); bb++)
			{
				String temp = getNumberMove(gridStates.get(aa), allPresses.charAt(bb));
				if(!(prev.contains(temp)))
				{
					possGridStates.add(temp.toUpperCase());
					possPresses = possPresses + "" + allPresses.charAt(bb);
				}
			}
			if(possGridStates.size() > 0)
			{
				gridStates.set(aa,  possGridStates.get(0).toUpperCase());
				String tempPresses = presses.get(aa).toUpperCase();
				//System.out.println(tempPresses);
				presses.set(aa, tempPresses.toUpperCase() + "" + possPresses.charAt(0));
				prev.add(possGridStates.get(0).toUpperCase());
				for(int bb = 1; bb < possGridStates.size(); bb++)
				{
					aa++;
					gridStates.add(aa, possGridStates.get(bb).toUpperCase());
					prev.add(possGridStates.get(bb).toUpperCase());
					presses.add(aa, tempPresses + "" + possPresses.charAt(bb));
				}
				for(int cc = 0; cc < gridStates.size(); cc++)
				{
					if(isSolved(gridStates.get(cc), solutions))
						return presses.get(cc);
				}
			}
			else
			{
				gridStates.remove(aa);
				presses.remove(aa);
				aa--;
			}
			if(aa >= gridStates.size() - 1)
				aa = -1;
		}
		return "";
	}
	private String getPossibleNumberMoves(String grid)
	{
		String[] arr = {
					grid.charAt(1) + "" + grid.charAt(3),
					grid.charAt(0) + "" + grid.charAt(2) + "" + grid.charAt(4),
					grid.charAt(1) + "" + grid.charAt(5),
					grid.charAt(0) + "" + grid.charAt(4) + "" + grid.charAt(6),
					grid.charAt(1) + "" + grid.charAt(3) + "" + grid.charAt(5) + "" + grid.charAt(7),
					grid.charAt(2) + "" + grid.charAt(4) + "" + grid.charAt(8),
					grid.charAt(3) + "" + grid.charAt(7),
					grid.charAt(4) + "" + grid.charAt(6) + "" + grid.charAt(8),
					grid.charAt(5) + "" + grid.charAt(7)
			};
		return arr[grid.indexOf('-')];
	}
	private boolean valid(String i)
	{
		if(i.length() == 9)
		{
			String must = "-12345678";
			for(int aa = 0; aa < i.length(); aa++)
				must = must.replace(i.charAt(aa) + "", "");
			if(must.length() == 0)
				return true;
		}
		return false;
	}
	private boolean v2(String i, String s)
	{
		if(i.length() == 1 && s.contains(i))
			return true;
		else
			return false;
	}
	private boolean v3(String i)
	{
		switch(i)
		{
			case "TL":case "TM":case "TR":
			case "ML":case "MM":case "MR":
			case "BL":case "BM":case "BR":
				return true;
		}
		return false;
	}
}
