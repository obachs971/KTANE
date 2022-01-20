package modules;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import start.BombEdgework;

public class Nonogram 
{
	private final String[][] table =
		{
				{"BO", "RB", "YO", "RG", "GY", "OP", "GO", "GP", "YP", "BP", "RO", "RP"},
				{"YO", "GP", "BO", "BY", "RG", "RO", "BG", "BP", "YP", "GY", "RB", "RY"},
				{"1", "2", "3", "4", "5", "11", "12", "13", "21", "22", "31", "111"},
		};
	private final BombEdgework ew;
	private final double r;
	public Nonogram(double resizer, BombEdgework e)
	{
		r = resizer;
		ew = e;
	}
	public void run()
	{
		String[] clues = getClues();
		String[] grid = solveGrid(clues);
		while(grid == null)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			clues = getClues();
			grid = solveGrid(clues);
		}
		displaySolution(grid);
	}
	private void printGrid(String[] grid)
	{
		for(int aa = 0; aa < 5; aa++)
			System.out.println(grid[aa]);
		System.out.println("----------");
	}
	private String[] solveGrid(String[] clues)
	{
		String[] grid = new String[]{"NNNNN", "NNNNN", "NNNNN", "NNNNN", "NNNNN"};
		boolean flag = true;
		int repeats = 0;
		while(flag)
		{
			for(int aa = 0; aa < 5; aa++)
			{
				grid[aa] = fill(grid[aa], clues[aa + 5]);
				printGrid(grid);
				if(grid[aa].equals("ERROR"))
					return null;
			}
			for(int aa = 0; aa < 5; aa++)
			{
				String temp = grid[0].charAt(aa) + "" + grid[1].charAt(aa) + "" + grid[2].charAt(aa) + "" + grid[3].charAt(aa) + "" + grid[4].charAt(aa);
				temp = fill(temp, clues[aa]);
				for(int bb = 0; bb < 5; bb++)
					grid[bb] = grid[bb].substring(0, aa) + "" + temp.charAt(bb) + "" + grid[bb].substring(aa + 1);
				System.out.println(clues[aa]);
				printGrid(grid);
				if(temp.equals("ERROR"))
					return null;
			}
			flag = false;
			for(int aa = 0; aa < 5; aa++)
			{
				if(grid[aa].contains("N"))
				{
					flag = true;
					break;
				}
			}
			repeats++;
			if(repeats == 100)
				return null;
		}
		if(validateGrid(grid, clues))
			return grid;
		else
			return null;
	}
	private String[] getClues()
	{
		String[] colors = getColors();
		boolean flag = false;
		String[] clues = {"", "", "", "", "", "", "", "", "", ""};
		for(int aa = 0; aa < clues.length; aa++)
		{
			for(int bb = 0; bb < 12; bb++)
			{
				if(table[ew.getSNDIG(ew.numSNDIGS() - 1) % 2][bb].indexOf(colors[0].charAt(aa)) >= 0 && table[ew.getSNDIG(ew.numSNDIGS() - 1) % 2][bb].indexOf(colors[1].charAt(aa)) >= 0)
				{
					clues[aa] = table[2][bb].toUpperCase();
					break;
				}
			}
			if(clues[aa].length() == 0)
			{
				flag = true;
				break;
			}
		}
		if(flag)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			return getClues();
		}
		else
			return clues;
	}
	private String[] getColors()
	{
		String[] colors = new String[2];
		for(int aa = 0; aa < 2; aa++)
		{
			colors[aa] = JOptionPane.showInputDialog("R - Red\nO - Orange\nY - Yellow\nG - Green\nB - Blue\nP - Purple\nEnter the colors on\nthe top and left:").toUpperCase().replace(" ", "");
			boolean v = v1(colors[aa]);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				colors[aa] = JOptionPane.showInputDialog("R - Red\nO - Orange\nY - Yellow\nG - Green\nB - Blue\nP - Purple\nEnter the colors on\nthe top and left:").toUpperCase().replace(" ", "");
				v = v1(colors[aa]);
			}
			if(aa == 0)
				JOptionPane.showMessageDialog(null, "Toggle the colors");
		}
		return colors;
	}
	private String fill(String line, String clue)
	{
		switch(clue)
		{
			case "5":
				return "BBBBB";
			case "13":
				return "BWBBB";
			case "22":
				return "BBWBB";
			case "31":
				return "BBBWB";
			case "111":
				return "BWBWB";
			default:
				int numW = 0, numB = 0, sumB = 0;
				for(int aa = 0; aa < line.length(); aa++)
				{
					if(line.charAt(aa) == 'B')
						numB++;
					else if(line.charAt(aa) == 'W')
						numW++;
				}
				for(int aa = 0; aa < clue.length(); aa++)
					sumB += ("012345".indexOf(clue.charAt(aa)));
				if(sumB == numB) //Rest of the spaces have to be white
					return line.replace("N", "W");
				else if(numW == (5 - sumB)) //Rest of the spaces have to be black
					return line.replace("N", "B");
				else if(numB > sumB || numW > (5 - sumB))
					return "ERROR";
				else
				{
					switch(clue)
					{
						case "2":
							if(line.charAt(0) == 'B')
								return "ERROR";
							else if(line.charAt(1) == 'B')
							{
								if(line.charAt(0) == 'W')
									return "WBBWW";
								else if(line.charAt(2) == 'W')
										return "BBWWW";
								return line.substring(0, 3) + "WW";
							}
							else if(line.charAt(2) == 'B')
							{
								if(line.charAt(1) == 'W')
									return "WWBBW";
								else if(line.charAt(3) == 'W')
									return "WBBWW";
								return "W" + line.substring(1, 4) + "W";
							}
							else if(line.charAt(3) == 'B')
							{
								if(line.charAt(2) == 'W')
									return "WWWBB";
								else if(line.charAt(4) == 'W')
										return "WWBBW";
								return "WW" + line.substring(2);
							}
							else if(line.charAt(4) == 'B')
								return "WWWBB";
							if(numW == 1)
							{
								switch(line.indexOf("W"))
								{
									case 1:
										return "WW" + line.charAt(2) + "B" + line.charAt(4);
									case 3:
										return line.charAt(0) + "B" + line.charAt(2) + "WW";
									default:
										return line.toUpperCase();
								}
							}
							else if(numW == 2)
							{
								switch(line.indexOf("W"))
								{
									case 0:
										if(line.charAt(1) == 'W')
											return line.substring(0, 3) + "B" + line.charAt(4);
										else if(line.charAt(2) == 'W')
											return "WWWBB";
										else if(line.charAt(3) == 'W')
											return "WBBWW";
										return line.substring(0, 2) + "B" + line.substring(3);
									case 1:
										if(line.charAt(2) == 'W')
											return "WWWBB";
										else if(line.charAt(3) == 'W')
											return "ERROR";
										return "WWBBW";
									default:
										return "BBWWW";
								}
							}
							return line.toUpperCase();
						case "3":
							if(line.charAt(0) == 'W')
								return line.substring(0, 2) + "BB" + line.charAt(4);
							else if(line.charAt(1) == 'W')
								return "WWBBB";
							else if(line.charAt(2) == 'W')
								return "ERROR";
							else if(line.charAt(3) == 'W')
								return "BBBWW";
							else if(line.charAt(4) == 'W')
								return line.charAt(0) + "BB" + line.substring(3);
							else if(numB > 0)
							{
								switch(line.indexOf("B"))
								{
									case 0:
										return "BBBWW";
									case 1:
										if(line.charAt(3) == 'B')
											return "WBBBW";
										return line.charAt(0) + "BB" + line.charAt(3) + "W";
									case 2:
										if(line.charAt(4) == 'B')
											return "WWBBB";
										return line.toUpperCase();
									case 3:
										if(line.charAt(4) == 'B')
											return "WWBBB";
										return "W" + line.charAt(1) + "BB" + line.charAt(4);
									case 4:
										return "WWBBB";
								}
							}
							return line.substring(0, 2) + "B" + line.substring(3);
						case "4":
							return line.charAt(0) + "BBB" + line.charAt(4);
						case "11":
							if(line.charAt(0) == 'B')
								return "BW" + line.substring(2);
							else if(line.charAt(1) == 'B')
							{
								if(line.charAt(3) == 'W')
									return "WBWWB";
								else if(line.charAt(4) == 'W')
									return "WBWBW";
								return "WBW" + line.substring(3);
							}	
							else if(line.charAt(2) == 'B')
							{
								if(line.charAt(0) == 'W')
									return "WWBWB";
								else if(line.charAt(4) == 'W')
									return "BWBWW";
								return line.charAt(0) + "WBW" + line.charAt(4);
							}
							else if(line.charAt(3) == 'B')
							{
								if(line.charAt(0) == 'W')
									return "WBWBW";
								else if(line.charAt(1) == 'W')
									return "BWWBW";
								return line.substring(0, 2) + "WBW";
							}	
							else if(line.charAt(4) == 'B')
								return line.substring(0, 3) + "WB";
							else if(numW > 0)
							{
								switch(line.indexOf("W"))
								{
									case 0:
										if(line.charAt(1) == 'W')
											return "WWBWB";
										else if(line.charAt(2) == 'W')
											return "WBW" + line.substring(3);
										else if(line.charAt(3) == 'W')
											return line.substring(0, 4) + "B";
										else if(line.charAt(4) == 'W')
											return "WBWBW";
										return line.toUpperCase();
									case 1:
										if(line.charAt(2) == 'W')
											return "BWW" + line.substring(3);
										else if(line.charAt(4) == 'W')
											return "B" + line.substring(1);
										return line.toUpperCase();
									case 2:
										if(line.charAt(3) == 'W')
											return line.substring(0, 4) + "B";
										else if(line.charAt(4) == 'W')
											return line.substring(0, 3) + "BW";
										return line.toUpperCase();
									case 3:
										if(line.charAt(4) == 'W')
											return "BWBWW";
								}
							}
							return line.toUpperCase();
						case "12":
							if(line.charAt(0) == 'W')
								return "WBWBB";
							else if(line.charAt(1) == 'W')
							{
								if(line.charAt(2) == 'B')
									return "BWBBW";
								else if(line.charAt(4) == 'B')
									return "BWWBB";
								return "BW" + line.charAt(2) + "B" + line.charAt(4);
							}
							else if(line.charAt(2) == 'W')
							{
								if(line.charAt(0) == 'B')
									return "BWWBB";
								else if(line.charAt(1) == 'B')
									return "WBWBB";
								return line.substring(0, 3) + "BB";
							}
							else if(line.charAt(3) == 'W')
								return "ERROR";
							else if(line.charAt(4) == 'W')
								return "BWBBW";
							else if(numB > 0)
							{
								switch(line.indexOf("B"))
								{
									case 0:
										if(line.charAt(2) == 'B')
											return "BWBBW";
										else if(line.charAt(4) == 'B')
											return "BWWBB";
										return "BW" + line.charAt(2) + "B" + line.charAt(4);
									case 1:
										return "WBWBB";
									case 2:
										return "BWBBW";
									case 3:
										if(line.charAt(4) == 'B')
											return line.substring(0, 2) + "WBB";
										return line.toUpperCase();
									case 4:
										return line.substring(0, 2) + "WBB";
								}
							}
							return line.substring(0, 3) + "B" + line.charAt(4);
						case "21":
							if(line.charAt(0) == 'W')
								return "WBBWB";
							else if(line.charAt(1) == 'W')
								return "ERROR";
							else if(line.charAt(2) == 'W')
							{
								if(line.charAt(3) == 'B')
									return "BBWBW";
								else if(line.charAt(4) == 'B')
									return "BBWWB";
								return "BBW" + line.substring(3);
							}
							else if(line.charAt(3) == 'W')
							{
								if(line.charAt(0) == 'B')
									return "BBWWB";
								else if(line.charAt(2) == 'B')
									return "WBBWB";
								return line.charAt(0) + "B" + line.charAt(2) + "WB";
							}
							else if(line.charAt(4) == 'W')
								return "BBWBW";
							else if(numB > 0)
							{
								switch(line.indexOf("B"))
								{
									case 0:
										if(line.charAt(3) == 'B')
											return "BBWBW";
										else if(line.charAt(4) == 'B')
											return "BBWWB";
										return "BBW" + line.substring(3);
									case 1:
										if(line.charAt(2) == 'B')
											return "WBBWB";
										else if(line.charAt(3) == 'B')
											return "BBWBW";
										else if(line.charAt(4) == 'B')
											return line.substring(3) + "WB";
										return line.toUpperCase();
									case 2:
										return "WBBWB";
									case 3:
										return "BBWBW";
									case 4:
										return line.charAt(0) + "B" + line.charAt(3) + "WB";
								}
							}
							return line.charAt(0) + "B" + line.substring(2);
					}
				}
				return line.toUpperCase();
		}
	}
	private boolean v1(String i)
	{
		if(i.length() == 10)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("ROYGBP".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
	private boolean validateGrid(String[] grid, String[] clues)
	{
		for(int aa = 0; aa < 5; aa++)
		{
			if(!(validateLine(grid[aa], clues[aa + 5])))
				return false;
		}
		for(int aa = 0; aa < 5; aa++)
		{
			String temp = grid[0].charAt(aa) + "" + grid[1].charAt(aa) + "" + grid[2].charAt(aa) + "" + grid[3].charAt(aa) + "" + grid[4].charAt(aa);
			if(!(validateLine(temp, clues[aa])))
				return false;
		}
		return true;
	}	
	
	private boolean validateLine(String line, String clue)
	{
		switch(clue)
		{
			case "1":
				switch(line)
				{
					case "BWWWW":
					case "WBWWW":
					case "WWBWW":
					case "WWWBW":
					case "WWWWB":
						return true;
				}
				return false;
			case "2":
				switch(line)
				{
					case "BBWWW":
					case "WBBWW":
					case "WWBBW":
					case "WWWBB":
						return true;
				}
				return false;
			case "3":
				switch(line)
				{
					case "BBBWW":
					case "WBBBW":
					case "WWBBB":
						return true;
				}
				return false;
			case "4":
				switch(line)
				{
					case "BBBBW":
					case "WBBBB":
						return true;
				}
				return false;
			case "5":
				if(line.equals("BBBBB"))
					return true;
				return false;
			case "11":
				switch(line)
				{
					case "BWBWW":
					case "BWWBW":
					case "BWWWB":
					case "WBWBW":
					case "WBWWB":
					case "WWBWB":
						return true;
				}
				return false;
			case "12":
				switch(line)
				{
					case "BWBBW":
					case "BWWBB":
					case "WBWBB":
						return true;
				}
				return false;
			case "13":
				if(line.equals("BWBBB"))
					return true;
				return false;
			case "21":
				switch(line)
				{
					case "BBWBW":
					case "BBWWB":
					case "WBBWB":
						return true;
				}
				return false;
			case "22":
				if(line.equals("BBWBB"))
					return true;
				return false;
			case "31":
				if(line.equals("BBBWB"))
					return true;
				return false;
			default:
				if(line.equals("BWBWB"))
					return true;
				return false;
		}
	}
	private void displaySolution(String[] grid)
	{
		ImageIcon[][] solution = new ImageIcon[grid.length][];
		JLabel[][] solutionLabel = new JLabel[grid.length][];
		JFrame solutionFrame = new JFrame();
		solutionFrame.setLayout(new GridLayout(grid.length, grid[0].length()));
		String out = "";
		for(int aa = 0; aa < grid.length; aa++)
		{
			out = out + "ROW #" + (aa + 1) + ": ";
			solution[aa] = new ImageIcon[grid[aa].length()];
			solutionLabel[aa] = new JLabel[grid[aa].length()];
			for(int bb = 0; bb < grid[aa].length(); bb++)
			{
				solution[aa][bb] = new ImageIcon("img/Nonogram" + grid[aa].charAt(bb) + ".jpg");
				Image image = solution[aa][bb].getImage();
				image = image.getScaledInstance((int)(solution[aa][bb].getIconWidth() / r), (int)(solution[aa][bb].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
				solution[aa][bb] = new ImageIcon(image);
				solutionLabel[aa][bb] = new JLabel();
				solutionLabel[aa][bb].setIcon(solution[aa][bb]);
				solutionFrame.add(solutionLabel[aa][bb]);
				if(grid[aa].charAt(bb) == 'B')
					out = out + "" + (bb + 1) + " ";
			}
			out = out + "\n";
		}
		solutionFrame.pack();
		solutionFrame.setVisible(true);
		JOptionPane.showMessageDialog(null, out);
		solutionFrame.setVisible(false);
	}
}
