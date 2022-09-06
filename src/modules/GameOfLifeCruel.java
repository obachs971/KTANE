package modules;

import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import misc.PlayType;
import start.BombConfig;
import start.BombEdgework;

public class GameOfLifeCruel 
{
	private final BombConfig con;
	private final BombEdgework ew;
	private PlayType playType;
	private final double r;
	public GameOfLifeCruel(BombConfig cf, BombEdgework e, PlayType pt, double resizer)
	{
		con = cf;
		ew = e;
		playType = pt;
		r = resizer;
	}
	public String run()
	{
		String souv = "";
		String[] colorList = {"RED", "ORANGE", "YELLOW", "GREEN", "BLUE", "PURPLE", "BROWN"};
		char[][] grid = 
			{
					{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
					{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
					{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
					{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
					{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
					{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
					{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
					{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
					{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
					{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
			};
		int strike = 2, time = 2, solves = 2, sec = con.getStartingBombSeconds() / 2;
		long min = con.getStartingBombMinutes();
		if(min % 2 == 1)
			sec = sec + 30;
		min = min / 2;
		String halfTime;
		if(sec < 10)
			halfTime = min + ":0" + sec;
		else
			halfTime = min + ":" + sec;
		if(playType == PlayType.Team)
		{
			int count = 1;
			while(true)
			{
				String input = JOptionPane.showInputDialog("R - Red\nO - Orange\nY - Yellow\nG - Green\nB - Blue\nP - Purple\nN - Brown\nK - Black\nEnter color # " + count + ":").toUpperCase();
				boolean v = v3(input);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog("R - Red\nO - Orange\nY - Yellow\nG - Green\nB - Blue\nP - Purple\nN - Brown\nK - Black\nEnter color # " + count + ":").toUpperCase();
					v = v3(input);
				}
				if(input.length() == 0)
					break;
				boolean white = false;
				if(input.length() == 1)
					souv = souv + "COMBO #" + count + ": SOLID " + colorList["ROYGBPN".indexOf(input)] + "\n";
				else if(input.contains("K"))
					souv = souv + "COMBO #" + count + ": FLASHING " + colorList["ROYGBPN".indexOf(input.replace("K", ""))] + "\n";
				else
					souv = souv + "COMBO #" + count + ": FLASHING " + colorList["ROYGBPN".indexOf(input.charAt(0))] + "/" + colorList["ROYGBPN".indexOf(input.charAt(1))] + "\n";
				String color = getColor(input);
				switch(color.charAt(0))
				{
					case 'R':
						if(ew.BAT() > 0)
						{
							if(strike == 2)
								strike = JOptionPane.showConfirmDialog(null, "Is there a strike?");
							if(strike == 0)
								white = true;
						}
						break;
					case 'O':
						if(!(ew.findInd("CAR")))
						{
							if(time == 2)
								time = JOptionPane.showConfirmDialog(null, "Is the displayed time under " + halfTime + "?");
							if(time == 0)
								white = true;
						}
						break;
					case 'Y':
						if(ew.findPort("RJ-45") == 0 && ew.numLit() > ew.numUnlit())
							white = true;
						break;
					case 'G':
						if(!(ew.findInd("CLR")))
						{
							if(solves == 2)
								solves = JOptionPane.showConfirmDialog(null, "Is there an even number of solves?");
							if(solves == 0)
								white = true;
						}
						break;
					case 'B':
						if(!(ew.findInd("SND")) && ew.numCharsInSN("SEAKY") > 0)
							white = true;
						break;
					case 'P':
						if(ew.BAT() < 4 && ew.numUnlit() > ew.numLit())
							white = true;
						break;
					case 'N':
						if(ew.numInd() > 0 && ew.numPortTypes() >= 3)
							white = true;
						break;
				}
				if(color.contains("-"))
					white = !(white);
				if(white)
					JOptionPane.showMessageDialog(null, "Set that square to white");
				else
					JOptionPane.showMessageDialog(null, "Set that square to black");
				count++;
			}
			for(int aa = 1; aa < 9; aa++)
			{
				String input = JOptionPane.showInputDialog("Enter the positions of the\nwhite spaces on row #" + aa + ":").toUpperCase();
				boolean v = v2(input);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog("Enter the positions of the\nwhite spaces on row #" + aa + ":").toUpperCase();
					v = v2(input);
				}
				input = input.replace("A", "1").replace("B", "2").replace("C", "3").replace("D", "4").replace("E", "5").replace("F", "6");
				for(int bb = 0; bb < input.length(); bb++)
					grid[aa]["-123456".indexOf(input.charAt(bb))] = 'W';
				//System.out.println(grid[aa][1] + "" + grid[aa][2] + "" + grid[aa][3] + "" + grid[aa][4] + "" + grid[aa][5] + "" + grid[aa][6]);
			}
			grid = solveGrid(grid);
			displaySolution(grid, "");
		}
		else
		{
			ArrayList<String[]> colors = new ArrayList<String[]>();
			while(true)
			{
				String input = JOptionPane.showInputDialog("R - Red\nO - Orange\nY - Yellow\nG - Green\nB - Blue\nP - Purple\nN - Brown\nK - Black\nEnter color # " + (colors.size() + 1) + " and col/row:").toUpperCase().replace(" ", "");
				boolean v = v1(input);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog("R - Red\nO - Orange\nY - Yellow\nG - Green\nB - Blue\nP - Purple\nN - Brown\nK - Black\nEnter color # " + (colors.size() + 1) + " and col/row:").toUpperCase().replace(" ", "");
					v = v1(input);
				}
				if(input.length() == 0)
					break;
				colors.add(new String[] {input.substring(0, input.length() / 2), input.substring(input.length() / 2)});
			}
			for(int aa = 1; aa < 9; aa++)
			{
				String input = JOptionPane.showInputDialog("Enter the positions of the\nwhite spaces on row #" + aa + ":").toUpperCase();
				boolean v = v2(input);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog("Enter the positions of the\nwhite spaces on row #" + aa + ":").toUpperCase();
					v = v2(input);
				}
				input = input.replace("A", "1").replace("B", "2").replace("C", "3").replace("D", "4").replace("E", "5").replace("F", "6");
				for(int bb = 0; bb < input.length(); bb++)
					grid[aa]["-123456".indexOf(input.charAt(bb))] = 'W';
				//System.out.println(grid[aa][1] + "" + grid[aa][2] + "" + grid[aa][3] + "" + grid[aa][4] + "" + grid[aa][5] + "" + grid[aa][6]);
			}
			ArrayList<int[]> G = new ArrayList<int[]>();
			ArrayList<int[]> g = new ArrayList<int[]>();
			for(int aa = 0; aa < colors.size(); aa++)
			{
				boolean white = false;
				if(colors.get(aa)[0].length() == 1)
					souv = souv + "COMBO #" + (aa + 1) + ": SOLID " + colorList["ROYGBPN".indexOf(colors.get(aa)[0])] + "\n";
				else if(colors.get(aa)[0].contains("K"))
					souv = souv + "COMBO #" + (aa + 1) + ": FLASHING " + colorList["ROYGBPN".indexOf(colors.get(aa)[0].replace("K", ""))] + "\n";
				else
					souv = souv + "COMBO #" + (aa + 1) + ": FLASHING " + colorList["ROYGBPN".indexOf(colors.get(aa)[0].charAt(0))] + "/" + colorList["ROYGBPN".indexOf(colors.get(aa)[0].charAt(1))] + "\n";
				String color = getColor(colors.get(aa)[0]);
				switch(color.charAt(0))
				{
					case 'R':
						if(ew.BAT() > 0)
						{
							if(strike == 2)
								strike = JOptionPane.showConfirmDialog(null, "Is there a strike?");
							if(strike == 0)
								white = true;
						}
						break;
					case 'O':
						if(!(ew.findInd("CAR")))
						{
							if(time == 2)
								time = JOptionPane.showConfirmDialog(null, "Is the displayed time under " + halfTime + "?");
							if(time == 0)
								white = true;
						}
						break;
					case 'Y':
						if(ew.findPort("RJ-45") == 0 && ew.numLit() > ew.numUnlit())
							white = true;
						break;
					case 'G':
						if(!(ew.findInd("CLR")))
						{
							white = true;
							if(color.contains("-"))
								g.add(getCoord(colors.get(aa)[1]));
							else
								G.add(getCoord(colors.get(aa)[1]));
						}
						break;
					case 'B':
						if(!(ew.findInd("SND")) && ew.numCharsInSN("SEAKY") > 0)
							white = true;
						break;
					case 'P':
						if(ew.BAT() < 4 && ew.numUnlit() > ew.numLit())
							white = true;
						break;
					case 'N':
						if(ew.numInd() > 0 && ew.numPortTypes() >= 3)
							white = true;
						break;
				}
				if(color.contains("-"))
					white = !(white);
				if(white)
				{
					int[] c = getCoord(colors.get(aa)[1]);
					grid[c[1]][c[0]] = 'W';
				}
			}
			if(G.size() > 0 || g.size() > 0)
			{
				char[][] even = solveGrid(grid);
				for(int aa = 0; aa < G.size(); aa++)
					grid[G.get(aa)[1]][G.get(aa)[0]] = 'B';
				for(int aa = 0; aa < g.size(); aa++)
					grid[g.get(aa)[1]][g.get(aa)[0]] = 'W';
				char[][] odd = solveGrid(grid);
				boolean flag = true;
				for(int aa = 0; aa < even.length; aa++)
				{
					for(int bb = 0; bb < even[aa].length; bb++)
					{
						if(even[aa][bb] != odd[aa][bb])
						{
							flag = false;
							break;
						}
					}
					if(!(flag))
						break;
				}
				if(flag)
					displaySolution(even, "");
				else
				{
					displaySolution(even, "Even Solves\n");
					displaySolution(odd, "Odd Solves\n");
				}
			}
			else
			{
				grid = solveGrid(grid);
				displaySolution(grid, "");
			}
		}
		return souv;
	}
	private String getColor(String i)
	{
		if(i.length() == 1)
			return i.toUpperCase();
		else if(i.contains("K"))
			return (i.replace("K", "") + "-");
		else if("RBY".indexOf(i.charAt(0)) >= 0 && "RBY".indexOf(i.charAt(1)) >= 0)
			return mix(i);
		else if("GOP".indexOf(i.charAt(0)) >= 0 && "GOP".indexOf(i.charAt(1)) >= 0)
			return mix(i);
		else if(i.contains("N"))
		{
			if(ew.getSNDIG(ew.numSNDIGS() - 1) % 2 == 0)
				return "N";
			else
				return i.replace("N", "");
		}
		else 
			return getCommon(i);
	}
	private String mix(String i)
	{
		switch(i)
		{
			case "RB":
			case "BR":
				return "P";
			case "BY":
			case "YB":
				return "G";
			case "RY":
			case "YR":
				return "O";
			case "GO":
			case "OG":
				return "Y";
			case "GP":
			case "PG":
				return "B";
			case "PO":
			case "OP":
				return "R";
		}
		return "";
	}
	private String getCommon(String i)
	{
		if(i.contains("G"))
		{
			if(i.contains("R"))
				return "G";
			else
				return i.replace("G", "");
		}
		else if(i.contains("O"))
		{
			if(i.contains("B"))
				return "O";
			else
				return i.replace("O", "");
		}
		else
		{
			if(i.contains("Y"))
				return "P";
			else
				return i.replace("P", "");
		}
	}
	private int[] getCoord(String c)
	{
		int[] coord = {"ABCDEF".indexOf(c.charAt(0)) + 1, "12345678".indexOf(c.charAt(1)) + 1};
		return coord;
	}
	private char[][] solveGrid(char[][] grid)
	{
		char[][] solution = new char[8][6];
		for(int aa = 1; aa < 9; aa++)
		{
			for(int bb = 1; bb < 7; bb++)
			{
				String temp = grid[aa - 1][bb - 1] + "" + grid[aa - 1][bb] + "" + grid[aa - 1][bb + 1] + "" + grid[aa][bb - 1] + "" + grid[aa][bb + 1] + "" + grid[aa + 1][bb - 1] + "" + grid[aa + 1][bb] + "" + grid[aa + 1][bb + 1];
				int numW = 0;
				for(int cc = 0; cc < temp.length(); cc++)
				{
					if(temp.charAt(cc) == 'W')
						numW++;
				}
				if(grid[aa][bb] == 'B')
				{
					if(numW == 3)
						solution[aa - 1][bb - 1] = 'W';
					else
						solution[aa - 1][bb - 1] = 'B';
				}	
				else
				{
					if(numW == 2 || numW == 3)
						solution[aa - 1][bb - 1] = 'W';
					else
						solution[aa - 1][bb - 1] = 'B';
				}
			}
		}
		return solution;
	}
	private void displaySolution(char[][] grid, String out)
	{
		ImageIcon[][] solution = new ImageIcon[8][];
		JLabel[][] solutionLabel = new JLabel[8][];
		JFrame solutionFrame = new JFrame();
		solutionFrame.setLayout(new GridLayout(8,6));
		for(int aa = 0; aa < 8; aa++)
		{
			out = out + "ROW #" + (aa + 1) + ": ";
			solution[aa] = new ImageIcon[6];
			solutionLabel[aa] = new JLabel[6];
			for(int bb = 0; bb < 6; bb++)
			{
				solution[aa][bb] = new ImageIcon("img/GameOfLife" + grid[aa][bb] + ".jpg");
				Image image = solution[aa][bb].getImage();
				image = image.getScaledInstance((int)(solution[aa][bb].getIconWidth() / r), (int)(solution[aa][bb].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
				solution[aa][bb] = new ImageIcon(image);
				solutionLabel[aa][bb] = new JLabel();
				solutionLabel[aa][bb].setIcon(solution[aa][bb]);
				solutionFrame.add(solutionLabel[aa][bb]);
				if(grid[aa][bb] == 'W')
					out = out + "" + (bb + 1) + " ";
			}
			out = out + "\n";
		}
		solutionFrame.pack();
		solutionFrame.setVisible(true);
		JOptionPane.showMessageDialog(null, out);
		solutionFrame.setVisible(false);
	}
	private boolean v1(String i)
	{
		if(i.length() == 0)
			return true;
		if(i.length() == 4 || i.length() == 3)
		{
			String[] conv = {i.substring(0, i.length() / 2), i.substring(i.length() / 2)};
			if(conv[0].length() == 1)
			{
				if("ROYGBPN".indexOf(conv[0]) < 0)
					return false;
			}
			else
			{
				if("ROYGBPNK".indexOf(conv[0].charAt(0)) < 0 || "ROYGBPNK".indexOf(conv[0].charAt(1)) < 0)
					return false;
				if(conv[0].charAt(0) == conv[0].charAt(1))
					return false;
			}
			int[] temp = getCoord(conv[1]);
			return (temp[0] > 0 && temp[1] > 0);
		}
		return false;
	}
	private boolean v2(String i)
	{
		if(i.length() < 7)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("ABCDEF".indexOf(i.charAt(aa)) < 0 && "123456".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;	
		}
		return false;
	}
	private boolean v3(String i)
	{
		if(i.length() == 0)
			return true;
		else if(i.length() == 1)
			return ("ROYGBPN".indexOf(i.charAt(0)) >= 0);
		else if(i.length() == 2)
		{
			if(i.charAt(0) == i.charAt(1))
				return false;
			return ("ROYGBPNK".indexOf(i.charAt(0)) >= 0 && "ROYGBPNK".indexOf(i.charAt(1)) >= 0);
		}
		return false;
	}
}
