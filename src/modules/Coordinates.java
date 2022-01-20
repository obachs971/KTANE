package modules;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Coordinates 
{
	private final double r;
	public Coordinates(double resizer)
	{
		r = resizer;
	}
	public String run()
	{
		//Getting input
		String souv = "";
		ArrayList<String> coords = new ArrayList<String>();
		int[][] grid = new int[0][0];
		boolean size = true;
		ImageIcon i = new ImageIcon("img/CoordinatesChinese.jpg");
		Image image = i.getImage();
		image = image.getScaledInstance((int)(i.getIconWidth() / r), (int)(i.getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
		i = new ImageIcon(image);
		JLabel l = new JLabel();
		l.setIcon(i);
		JFrame f = new JFrame();
		f.setLayout(new BorderLayout());
		f.add(l, BorderLayout.CENTER);
		f.pack();
		f.setVisible(true);
		for(int aa = 0; aa < 9; aa++)
		{
			String input = JOptionPane.showInputDialog("Enter coordinate #" + (aa + 1) + ":").toUpperCase();
			if(aa == 8 && size)
			{
				input = isSize(input);
				while(input.length() == 0)
				{
					f.setVisible(false);
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					f.setVisible(true);
					input = JOptionPane.showInputDialog("Enter coordinate #" + (aa + 1) + ":").toUpperCase();
					input = isSize(input);
				}
				grid = getGridSize(input);
				souv = "GRID SIZE: " + input.toUpperCase();
			}
			else if(size)
			{
				String[] temp = {isSize(input), isCoord(input)};
				while(temp[0].length() == 0 && temp[1].length() == 0)
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog("Enter coordinate #" + (aa + 1) + ":").toUpperCase();
					temp[0] = isSize(input);
					temp[1] = isCoord(input);
				}
				if(temp[0].length() > 0)
				{
					size = false;
					input = temp[0].toUpperCase();
					grid = getGridSize(input);
					souv = "GRID SIZE: " + input.toUpperCase();
				}
				else
				{
					input = temp[1].toUpperCase();
					coords.add(input.toUpperCase());
				}
			}
			else
			{
				input = isCoord(input);
				while(input.length() == 0)
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog("Enter coordinate #" + (aa + 1) + ":").toUpperCase();
					input = isCoord(input);
				}
				coords.add(input.toUpperCase());
			}
		}
		//End of getting input
		/*System.out.println("GRID SIZE: " + grid.length + " " + grid[0].length);
		for(int aa = 0; aa < coords.size(); aa++)
			System.out.println(coords.get(aa));*/
		for(int aa = 0; aa < grid.length; aa++)
		{
			for(int bb = 0; bb < grid[aa].length; bb++)
				grid[aa][bb] = -1;
		}
		String out = "";
		for(int aa = 0; aa < coords.size(); aa++)
		{
			int row, col;
			if(coords.get(aa).contains(" FROM "))
			{
				row = 0; 
				col = 0;
				String[] temp = coords.get(aa).split(" ");
				for(int bb = 0; bb < temp.length; bb++)
				{
					if(temp[bb].equals("FROM"))
					{
						String stuff = temp[bb + 1].toUpperCase();
						for(int cc = bb + 2; cc < temp.length; cc++)
							stuff = stuff + " " + temp[cc].toUpperCase();
						int[] temp2 = getPosition(stuff, grid);
						row = temp2[0];
						col = temp2[1];
						for(int cc = 0; cc < bb; cc += 2)
						{
							switch(temp[cc + 1])
							{
								case "UP":
								case "NORTH":
									row = row - Integer.parseInt(temp[cc]);
									break;
								case "RIGHT":
								case "EAST":
									col = col + Integer.parseInt(temp[cc]);
									break;
								case "DOWN":
								case "SOUTH":
									row = row + Integer.parseInt(temp[cc]);
									break;
								case "LEFT":
								case "WEST":
									col = col - Integer.parseInt(temp[cc]);
									break;
							}
						}
						break;
					}
				}
			}
			else if(isPosition(coords.get(aa)).length() > 0)
			{
				int[] temp = getPosition(coords.get(aa), grid);
				row = temp[0];
				col = temp[1];
			}
			else if(coords.get(aa).startsWith("CHINESE "))
			{
				int num = Integer.parseInt(coords.get(aa).split(" ")[1]) - 1;
				row = num % grid.length;
				col = grid[0].length - (num / grid.length) - 1;
			}
			else if(coords.get(aa).charAt(0) == '[')
			{
				if(isNum(coords.get(aa).substring(1, coords.get(aa).length() - 1)))
				{
					int num = Integer.parseInt(coords.get(aa).substring(1, coords.get(aa).length() - 1));
					row = num / grid[0].length;
					col = num % grid[0].length;
				}
				else
				{
					String[] temp = coords.get(aa).substring(1, coords.get(aa).length() - 1).split(" ");
					col = Integer.parseInt(temp[0]);
					row = Integer.parseInt(temp[1]);
				}
			}
			else if(coords.get(aa).charAt(0) == '<')
			{
				String[] temp = coords.get(aa).substring(1, coords.get(aa).length() - 1).split(" ");
				row = Integer.parseInt(temp[0]);
				col = Integer.parseInt(temp[1]);
			}
			else if(coords.get(aa).charAt(0) == '(')
			{
				String[] temp = coords.get(aa).substring(1, coords.get(aa).length() - 1).split(" ");
				col = Integer.parseInt(temp[0]);
				row = grid.length - Integer.parseInt(temp[1]) - 1;
			}
			else if(coords.get(aa).charAt(0) == '"')
			{
				String[] temp = coords.get(aa).substring(1, coords.get(aa).length() - 1).split(" ");
				row = grid.length - Integer.parseInt(temp[0]) - 1;
				col = Integer.parseInt(temp[1]);
			}
			else if(coords.get(aa).charAt(0) == '#')
			{
				int num = Integer.parseInt(coords.get(aa).substring(1)) - 1;
				row = grid.length - (num / grid[0].length) - 1;
				col = num % grid[0].length;
			}
			else if(coords.get(aa).charAt(1) == ' ')
			{
				row = Integer.parseInt(coords.get(aa).substring(0, 1)) - 1;
				col = Integer.parseInt(coords.get(aa).substring(2)) - 1;
			}
			else if(coords.get(aa).charAt(1) == '-')
			{
				col = "ABCDEFGHI".indexOf(coords.get(aa).charAt(0));
				row = grid.length - Integer.parseInt(coords.get(aa).substring(2));
			}
			else if(coords.get(aa).charAt(1) == '/')
			{
				row = grid.length - Integer.parseInt(coords.get(aa).substring(0, 1));
				col = Integer.parseInt(coords.get(aa).substring(2)) - 1;
			}
			else if(coords.get(aa).length() == 2)
			{
				col = "ABCDEFGHI".indexOf(coords.get(aa).charAt(0));
				row = Integer.parseInt(coords.get(aa).substring(1)) - 1;
			}
			else
			{
				int n = Integer.parseInt(coords.get(aa).substring(0, coords.get(aa).length() - 2)) - 1;
				row = n / grid[0].length;
				col = n % grid[0].length;
			}
			row = mod(row, grid.length);
			col = mod(col, grid[0].length);
			if(grid[row][col] != -1)
			{
				souv = souv + "\nCOORD #1: " + coords.get(grid[row][col]) + "\nCOORD #2: " + coords.get(aa);
				out = coords.get(grid[row][col]) + "\n" + coords.get(aa);
				break;
			}
			grid[row][col] = aa;
		}
		if(out.length() == 0)
		{
			f.setVisible(false);
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			return run();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Submit these coordinates:\n" + out);
			f.setVisible(false);
			return souv;
		}
	}
	//Returns the grid size
	private int[][] getGridSize(String input)
	{
		if(isNum(input))
		{
			int[] primes = getPrimes(Integer.parseInt(input));
			return new int[primes[1]][primes[0]];
		}
		else if(input.charAt(0) == '(')
		{
			int[] primes = getPrimes(Integer.parseInt(input.substring(1, input.length() - 1)));
			return new int[primes[0]][primes[1]];
		}
		else if(input.charAt(1) == 'X')
		{
			return new int["0123456789".indexOf(input.charAt(2))]["0123456789".indexOf(input.charAt(0))];
		}
		else if(input.contains(" BY "))
		{
			return new int["0123456789".indexOf(input.charAt(0))]["0123456789".indexOf(input.charAt(5))];
		}
		else if(input.contains("*"))
		{
			int h = "0123456789".indexOf(input.charAt(input.length() - 1));
			int w = Integer.parseInt(input.substring(0, input.indexOf("*"))) / h;
			return new int[h][w];
		}
		else
		{
			int w = "0123456789".indexOf(input.charAt(input.length() - 1));
			int h = Integer.parseInt(input.substring(0, input.indexOf(":"))) / w;
			return new int[h][w];
		}
	}
	//Valid primes from this module
	private int[] getPrimes(int n)
	{
		switch(n)
		{
			case 9:
				return new int[] {3, 3};
			case 15:
				return new int[] {5, 3};
			case 21:
				return new int[] {7, 3};
			case 25:
				return new int[] {5, 5};
			case 35:
				return new int[] {7, 5};
			case 49:
				return new int[] {7, 7};
		}
		return null;
	}
	//Returns if the string is a valid size
	private String isSize(String i)
	{
		String s = i.replace(",", " ");
		if(s.length() > 0)
		{
			if(s.contains("X") && s.length() == 3)
			{
				if("3456789".indexOf(s.charAt(0)) >= 0 && "3456789".indexOf(s.charAt(2)) >= 0)
						return s.toUpperCase();
			}
			else if(s.contains(" BY ") && s.length() == 6)
			{
				if("3456789".indexOf(s.charAt(0)) >= 0 && "3456789".indexOf(s.charAt(5)) >= 0)
					return s.toUpperCase();
			}
			else if(s.contains("*") && s.length() >= 3)
			{
				String s1 = s.substring(0, s.indexOf("*"));
				if(isNum(s1 + "") && isNum(s.charAt(s.length() - 1) + ""))
				{
					int n1 = Integer.parseInt(s1);
					int n2 = "0123456789".indexOf(s.charAt(s.length() - 1));
					if(n2 > 2 && n1 % n2 == 0)
						return s.toUpperCase();
				}
			}
			else if(s.contains(":") && s.length() >= 3)
			{
				String s1 = s.substring(0, s.indexOf(":"));
				if(isNum(s1 + "") && isNum(s.charAt(s.length() - 1) + ""))
				{
					int n1 = Integer.parseInt(s1);
					int n2 = "0123456789".indexOf(s.charAt(s.length() - 1));
					if(n2 > 2 && n1 % n2 == 0)
						return s.toUpperCase();
				}
			}
			else if(s.charAt(0) == '(' && s.length() >= 2 && s.length() <= 4)
			{
				s = s.substring(1);
				if(s.charAt(s.length() - 1) == ')')
					s = s.substring(0, s.length() - 1);
				if(isNum(s) && getPrimes(Integer.parseInt(s)) != null)
					return ("(" + s.toUpperCase() + ")");
			}
			else if(isNum(s))
			{
				if(getPrimes(Integer.parseInt(s)) != null)
					return s.toUpperCase();
			}
		}
		return "";
	}
	//Returns if the string is a valid coordinate
	private String isCoord(String i)
	{
		String s = i.replace(",", " ");
		if(s.length() > 0)
		{
			if(s.charAt(0) == '[' && s.split(" ").length > 1)
			{
				s = s.substring(1);
				if(s.charAt(s.length() - 1) == ']')
					s = s.substring(0, s.length() - 1);
				String[] temp = s.split(" ");
				if(temp.length == 2)
				{
					if(isNum(temp[0]) && temp[0].length() == 1 && isNum(temp[1]) && temp[1].length() == 1)
						return "[" + temp[0] + " " + temp[1] + "]";
				}
			}
			else if(s.charAt(0) == '<' && s.split(" ").length > 1)
			{
				s = s.substring(1);
				if(s.charAt(s.length() - 1) == '>')
					s = s.substring(0, s.length() - 1);
				String[] temp = s.split(" ");
				if(temp.length == 2)
				{
					if(isNum(temp[0]) && temp[0].length() == 1 && isNum(temp[1]) && temp[1].length() == 1)
						return "<" + temp[0] + " " + temp[1] + ">";
				}
			}
			else if(s.charAt(0) == '(' && s.split(" ").length > 1)
			{
				s = s.substring(1);
				if(s.charAt(s.length() - 1) == ')')
					s = s.substring(0, s.length() - 1);
				String[] temp = s.split(" ");
				if(temp.length == 2)
				{
					if(isNum(temp[0]) && temp[0].length() == 1 && isNum(temp[1]) && temp[1].length() == 1)
						return "(" + temp[0] + " " + temp[1] + ")";
				}
			}
			else if(s.charAt(0) == '"' && s.split(" ").length > 1)
			{
				s = s.substring(1);
				if(s.charAt(s.length() - 1) == '"')
					s = s.substring(0, s.length() - 1);
				String[] temp = s.split(" ");
				if(temp.length == 2)
				{
					if(isNum(temp[0]) && temp[0].length() == 1 && isNum(temp[1]) && temp[1].length() == 1)
						return "\"" + temp[0] + " " + temp[1] + "\"";
				}
			}
			else if(isPosition(s).length() > 0)
			{
				return isPosition(s);
			}
			else if(s.startsWith("CHINESE "))
			{
				String[] temp = s.split(" ");
				if(temp.length == 2 && (isNum(temp[1])))
					return "CHINESE " + temp[1];
			}
			else if(s.split(" ").length == 2)
			{
				String[] temp = s.split(" ");
				if(isNum(temp[0]) && temp[0].length() == 1 && isNum(temp[1]) && temp[1].length() == 1)
					return temp[0] + " " + temp[1];
			}
			else if(s.split(" ").length == 1)
			{
				if(s.charAt(0) == '[' && s.length() > 1)
				{
					s = s.substring(1);
					if(s.charAt(s.length() - 1) == ']')
						s = s.substring(0, s.length() - 1);
					if(s.length() < 3 && isNum(s))
						return "[" + s.toUpperCase() + "]";
				}
				else if(s.charAt(0) == '#')
				{
					s = s.substring(1);
					if(s.length() < 3 && isNum(s))
						return "#" + s.toUpperCase();
				}
				else if(s.length() == 2)
				{
					if("ABCDEFGHI".indexOf(s.charAt(0)) >= 0 && isNum(s.charAt(1) + ""))
						return s.toUpperCase();
				}
				else if(s.length() > 2)
				{
					if(s.charAt(1) == '-')
					{
						if("ABCDEFGHI".indexOf(s.charAt(0)) >= 0 && isNum(s.charAt(2) + ""))
							return s.toUpperCase();
					}
					else if(s.charAt(1) == '/')
					{
						if(isNum(s.charAt(0) + "") && isNum(s.charAt(2) + ""))
							return s.toUpperCase();
					}
					else if(s.length() < 5)
					{
						if(isNum(s.substring(0, s.length() - 2)))
							return s.toUpperCase();
					}
				}
			}
			else if(s.contains(" FROM "))
			{
				String[] temp = s.split(" ");
				int from = -1;
				for(int aa = 0; aa < temp.length; aa++)
				{
					if(temp[aa].equals("FROM"))
					{
						from = aa;
						break;
					}
				}
				for(int aa = 0; aa < from; aa+=2)
				{
					if(isNum(temp[aa]) && isDirection(temp[aa + 1]).length() > 0)
						temp[aa + 1] = isDirection(temp[aa + 1]);
					else
						return "";
				}
				String stuff = "";
				if(temp.length > (from + 1))
					stuff = temp[from + 1];
				for(int aa = from + 2; aa < temp.length; aa++)
					stuff = stuff + " " + temp[aa];
				if(isPosition(stuff).length() > 0)
				{
					String pos = isPosition(stuff);
					stuff = temp[0].toUpperCase();
					for(int aa = 1; aa < from; aa+=2)
						stuff = stuff + " " + temp[aa] + " " + temp[aa + 1];
					return (stuff + " " + pos);
				}
			}
		}
		return "";
	}
	//Returns a valid position
	private int[] getPosition(String i, int[][] grid)
	{
		int row = -1, col = -1;
		switch(i)
		{
			case "TOP LEFT":
			case "NORTH-WEST CORNER":
				row = 0;
				col = 0;
				break;
			case "12 O'CLOCK":
			case "TOP MIDDLE":
			case "NORTH CENTER":
				row = 0;
				col = grid[0].length / 2;
				break;
			case "TOP RIGHT":
			case "NORTH-EAST CORNER":
				row = 0;
				col = grid[0].length - 1;
				break;
			case "9 O'CLOCK":
			case "MIDDLE LEFT":
			case "WEST CENTER":
				row = grid.length / 2;
				col = 0;
				break;
			case "MIDDLE CENTER":
				row = grid.length / 2;
				col = grid[0].length / 2;
				break;
			case "3 O'CLOCK":
			case "MIDDLE RIGHT":
			case "EAST CENTER":
				row = grid.length / 2;
				col = grid.length - 1;
				break;
			case "BOTTOM LEFT":
			case "SOUTH-WEST CORNER":
				row = grid.length - 1;
				col = 0;
				break;
			case "BOTTOM CENTER":
			case "SOUTH CENTER":
				row = grid.length - 1;
				col = grid[0].length / 2;
				break;
			case "BOTTOM RIGHT":
			case "SOUTH-EAST CORNER":
				row = grid.length - 1;
				col = grid[0].length - 1;
				break;
		}
		return new int[] {row, col};
	}
	private String isPosition(String i)
	{
		switch(i)
		{
			case "12 O'CLOCK":
			case "12 OCLOCK":
			case "12 OC":
				return "12 O'CLOCK";
			case "9 O'CLOCK":
			case "9 OCLOCK":
			case "9 OC":
				return "9 O'CLOCK";
			case "6 O'CLOCK":
			case "6 OCLOCK":
			case "6 OC":
				return "6 O'CLOCK";
			case "3 O'CLOCK":
			case "3 OCLOCK":
			case "3 OC":
				return "3 O'CLOCK";
			case "NORTH-WEST CORNER":
			case "NORTHWEST CORNER":
			case "NW C":
				return "NORTH-WEST CORNER";
			case "NORTH CENTER":
			case "N C":
				return "NORTH CENTER";
			case "NORTH-EAST CORNER":
			case "NORTHEAST CORNER":
			case "NE C":
				return "NORTH-EAST CORNER";
			case "WEST CENTER":
			case "W C":
				return "WEST CENTER";
			case "CENTER":
			case "C":
				return "CENTER";
			case "EAST CENTER":
			case "E C":
				return "EAST CENTER";
			case "SOUTH-WEST CORNER":
			case "SOUTHWEST CORNER":
			case "SW C":
				return "SOUTH-WEST CORNER";
			case "SOUTH CENTER":
			case "S C":
				return "SOUTH CENTER";
			case "SOUTH-EAST CORNER":
			case "SOUTHEAST CORNER":
			case "SE C":
				return "SOUTH-EAST CORNER";
			case "TOP LEFT":
			case "T L":
			case "TL":
				return "TOP LEFT";
			case "TOP MIDDLE":
			case "T M":
			case "TM":
				return "TOP MIDDLE";
			case "TOP RIGHT":
			case "T R":
			case "TR":
				return "TOP RIGHT";
			case "MIDDLE LEFT":
			case "M L":
			case "ML":
				return "MIDDLE LEFT";
			case "MIDDLE CENTER":
			case "M C":
			case "MC":
				return "MIDDLE CENTER";
			case "MIDDLE RIGHT":
			case "M R":
			case "MR":
				return "MIDDLE RIGHT";
			case "BOTTOM LEFT":
			case "B L":
			case "BL":
				return "BOTTOM LEFT";
			case "BOTTOM CENTER":
			case "B C":
			case "BC":
				return "BOTTOM CENTER";
			case "BOTTOM RIGHT":
			case "B R":
			case "BR":
				return "BOTTOM RIGHT";
		}
		return "";
	}
	//Returns a valid direction
	private String isDirection(String i)
	{
		switch(i)
		{
			case "UP":
			case "U":
				return "UP";
			case "RIGHT":
			case "R":
				return "RIGHT";
			case "DOWN":
			case "D":
				return "DOWN";
			case "LEFT":
			case "L":
				return "LEFT";
			case "NORTH":
			case "N":
				return "NORTH";
			case "EAST":
			case "E":
				return "EAST";
			case "SOUTH":
			case "S":
				return "SOUTH";
			case "WEST":
			case "W":
				return "WEST";
		}
		return "";
	}
	//Checks if the string is a number
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
	private int mod(int n, int m)
	{
		while(n < 0)
			n += m;
		return (n % m);
	}
}
