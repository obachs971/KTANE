package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import misc.PlayType;

public class DoubleOh 
{
	private final int[][][][] chart =
		{
			{
				{
					{60, 0, 15},
					{88, 46, 31},
					{74, 27, 53}
				},
				{
					{57, 36, 83},
					{70, 22, 64},
					{0, 41, 18}
				},
				{
					{48, 71, 24},
					{0, 55, 13},
					{86, 30, 62}
				}
			},
			{
				{
					{52, 10, 0},
					{33, 65, 78},
					{47, 81, 26}
				},
				{
					{43, 85, 37},
					{21, 0, 56},
					{68, 14, 72}
				},
				{
					{61, 28, 76},
					{12, 44, 87},
					{50, 0, 35}
				}
			},
			{
				{
					{0, 38, 42},
					{25, 73, 67},
					{11, 54, 80}
				},
				{
					{84, 63, 20},
					{16, 58, 0},
					{32, 77, 45}
				},
				{
					{75, 17, 51},
					{34, 82, 40},
					{23, 66, 0}
				}
			}
		};
	private final PlayType pt;
	private final boolean isSouv;
	public DoubleOh(PlayType p, boolean s)
	{
		pt = p;
		isSouv = s;
	}
	public String run()
	{
		String souv = "SUBMIT BUTTON: ", input = JOptionPane.showInputDialog("Enter the starting number:");
		boolean v = v1(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the starting number:");
			v = v1(input);
		}
		int[] current = getPos(Integer.parseInt(input));
		if(pt == PlayType.TP)
		{
			ArrayList<String> moveTypes = new ArrayList<String>();
			String[] buttons = {"VERT1", "HORIZ1", "HORIZ2", "VERT2", "SUBMIT"};
			String out = "";
			//Getting the moves of each button as it cycles in TP
			for(int aa = 0; aa < 5; aa++)
			{
				input = JOptionPane.showInputDialog("Press " + buttons[aa] + "\nEnter the 2 other numbers:").toUpperCase();
				v = v2(input, moveTypes, current);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog("Press " + buttons[aa] + "\nEnter the 2 other numbers:").toUpperCase();
					v = v2(input, moveTypes, current);
				}
				if(input.equals("SUBMIT"))
					moveTypes.add("SUBMIT");
				else
				{
					String[] temp = input.split(" ");
					int[] nums = new int[2];
					for(int bb = 0; bb < 2; bb++)
						nums[bb] = Integer.parseInt(temp[bb]);
					moveTypes.add(getMove(current, nums));
					//Depending on the moveType and position, store the solution to be later output
					switch(moveTypes.get(aa))
					{
						case "BU":
							if(current[0] == 0)
								out = out + "" + buttons[aa] + " " + buttons[aa] + "\n";
							else if(current[0] == 2)
								out = out + "" + buttons[aa] + "\n";
							break;
						case "BD":
							if(current[0] == 2)
								out = out + "" + buttons[aa] + " " + buttons[aa] + "\n";
							else if(current[0] == 0)
								out = out + "" + buttons[aa] + "\n";
							break;
						case "BL":
							if(current[1] == 0)
								out = out + "" + buttons[aa] + " " + buttons[aa] + "\n";
							else if(current[1] == 2)
								out = out + "" + buttons[aa] + "\n";
							break;
						case "BR":
							if(current[1] == 2)
								out = out + "" + buttons[aa] + " " + buttons[aa] + "\n";
							else if(current[1] == 0)
								out = out + "" + buttons[aa] + "\n";
							break;
						case "LU":
							if(current[2] == 0)
								out = out + "" + buttons[aa] + " " + buttons[aa] + "\n";
							else if(current[2] == 2)
								out = out + "" + buttons[aa] + "\n";
							break;
						case "LD":
							if(current[2] == 2)
								out = out + "" + buttons[aa] + " " + buttons[aa] + "\n";
							else if(current[2] == 0)
								out = out + "" + buttons[aa] + "\n";
							break;
						case "LL":
							if(current[3] == 0)
								out = out + "" + buttons[aa] + " " + buttons[aa] + "\n";
							else if(current[3] == 2)
								out = out + "" + buttons[aa] + "\n";
							break;
						case "LR":
							if(current[3] == 2)
								out = out + "" + buttons[aa] + " " + buttons[aa] + "\n";
							else if(current[3] == 0)
								out = out + "" + buttons[aa] + "\n";
							break;
					}
				}
				if(aa == 3 && !(moveTypes.contains("SUBMIT")))
				{
					moveTypes.add("SUBMIT");
					break;
				}
			}
			out = out + "" + buttons[moveTypes.indexOf("SUBMIT")];
			JOptionPane.showMessageDialog(null, "Press these buttons:\n" + out);
			souv = souv + "" + buttons[moveTypes.indexOf("SUBMIT")].toUpperCase();
		}
		else
		{
			String out = "";
			boolean flag = false;
			for(int aa = 0; aa < 3; aa++)
			{
				input = JOptionPane.showInputDialog(out + "Toggle button #" + (aa + 1) + " and enter\na non-glitched number:");
				v = v1(input);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog(out + "Toggle button #" + (aa + 1) + "and enter\na non-glitched number:");
					v = v1(input);
				}
				int[] next = getPos(Integer.parseInt(input));
				for(int bb = 0; bb < 4; bb++)
				{
					if(next[bb] != current[bb])
					{
						current[bb] = 1;
						if(current[0] == 1 && current[1] == 1 && current[2] == 1 && current[3] == 1)
						{
							out = "Go to 0\n";
							flag = true;
							break;
						}
						out = "Go to " + chart[current[0]][current[1]][current[2]][current[3]] + "\n";
					}
				}
				if(flag)
					break;
			}
			if(current[0] != 1 || current[1] != 1 || current[2] != 1 || current[3] != 1)
				out = out + "Go to 0\n";
			if(isSouv)
			{
				input = JOptionPane.showInputDialog(out + "Submit\nEnter the submit button:").toUpperCase();
				v = v3(input);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog(out + "Submit\nEnter the submit button:").toUpperCase();
					v = v3(input);
				}
				souv = souv + "" + input.toUpperCase();
			}
			else
				JOptionPane.showMessageDialog(null, out + "Submit");
		}
		return souv;
	}
	private int[] getPos(int n)
	{
		for(int aa = 0; aa < chart.length; aa++)
		{
			for(int bb = 0; bb < chart[aa].length; bb++)
			{
				for(int cc = 0; cc < chart[aa][bb].length; cc++)
				{
					for(int dd = 0; dd < chart[aa][bb][cc].length; dd++)
					{
						if(chart[aa][bb][cc][dd] == n)
							return new int[] {aa, bb, cc, dd};
					}
				}
			}
		}
		return null;
	}
	private String getMove(int[] s, int[] n)
	{
		int[] temp;
		String[][] moves;
		if(n[0] != 0)
		{
			temp = getPos(n[0]);
			moves = new String[][] 
			{
					{"BU", "BD"},
					{"BL", "BR"},
					{"LU", "LD"},
					{"LL", "LR"}
			};
		}
		else if(n[1] != 0)
		{
			temp = getPos(n[1]);
			moves = new String[][] 
			{
					{"BD", "BU"},
					{"BR", "BL"},
					{"LD", "LU"},
					{"LR", "LL"}
			};
		}
		else
			return "";
		for(int aa = 0; aa < 4; aa++)
		{
			if(temp[aa] != s[aa])
			{
				if((temp[aa] - s[aa]) == 2 || (temp[aa] - s[aa]) == -1)
					return moves[aa][0];
				else
					return moves[aa][1];
			}
		}
		return "";
	}
	private boolean v1(String i)
	{
		if(i.length() == 2 && isNum(i))
		{
			int number = Integer.parseInt(i);
			if(number == 0)
				return false;
			int[] conv = getPos(number);
			if(conv == null)
				return false;
			return true;
		}
		return false;
	}
	private boolean v2(String i, ArrayList<String> m, int[] s)
	{
		if(i.equals("SUBMIT"))
		{
			if(m.contains(i))
				return false;
			return true;
		}
		String[] conv = i.split(" ");
		if(conv.length == 2)
		{
			int[][] temp = new int[2][];
			int[] temp2 = new int[2];
			for(int aa = 0; aa < 2; aa++)
			{
				if(isNum(conv[aa]))
				{
					temp2[aa] = Integer.parseInt(conv[aa]);
					temp[aa] = getPos(temp2[aa]);
					if(temp[aa] == null)
						return false;
				}
				else
					return false;
			}
			String move = getMove(s, temp2);
			if(move.equals("") || m.contains(move.replace("R", "L").replace("D", "U")) || m.contains(move.replace("L", "R").replace("U", "D")))
				return false;
			return true;
		}
		return false;
	}
	private boolean v3(String i)
	{
		switch(i)
		{
			case "VERT1":
			case "VERT2":
			case "HORIZ1":
			case "HORIZ2":
			case "SUBMIT":
				return true;
			default:
				return false;
		}
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
}
