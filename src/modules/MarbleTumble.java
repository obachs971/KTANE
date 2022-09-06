package modules;
import java.awt.BorderLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

public class MarbleTumble 
{
	private final double r;
	public MarbleTumble(double resizer)
	{
		r = resizer;
	}
	public void run()
	{
		char[][] maze =
			{
					{'m', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'},
					{'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'},
					{'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'},
					{'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'},
					{'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'},
					{'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'},
					{'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'},
			};
		String input = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nS - Silver\nEnter the colors\nfrom outer to inner:").replace(" ", "").toLowerCase();
		boolean v = v1(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nS - Silver\nEnter the colors\nfrom outer to inner:").replace(" ", "").toLowerCase();
			v = v1(input);	
		}
		char[] colors = new char[input.length() + 2];
		colors[0] = 'n';
		colors[6] = 'n';
		ImageIcon img = new ImageIcon("img/MarbleTumbleDiagram.jpg");
		Image image = img.getImage();
		image = image.getScaledInstance((int)(img.getIconWidth() / r), (int)(img.getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(image);
		JLabel lab = new JLabel();
		lab.setIcon(img);
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.add(lab, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		for(int aa = 0; aa < input.length(); aa++)
		{
			colors[aa + 1] = input.charAt(aa);
			String input2 = JOptionPane.showInputDialog("Enter the number for opening then\ntrap for ring #" + (aa + 1) + " (spaces):");
			v = v2(input2);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input2 = JOptionPane.showInputDialog("Enter the number for opening then\ntrap for ring #" + (aa + 1) + " (spaces):");
				v = v2(input2);
			}
			String[] conv = input2.split(" ");
			int num = Integer.parseInt(conv[0]) - 1;
			maze[aa + 1][num] = 'o';
			num = Integer.parseInt(conv[1]) - 1;
			maze[aa + 1][num] = 't';
		}
		frame.setVisible(false);
		String solution = solving(colors, maze);
		String out = "";
		for(int i = 0; i < solution.length(); i++)
		{
			out = out + "" + solution.charAt(i);
			if((i + 1) % 12 == 0)
				out = out + "\n";
			else if((i + 1) % 3 == 0)
				out = out + " ";
		}
		JOptionPane.showMessageDialog(null, "Press at these last second digits:\n" + out);
	}
	private String solving(char[] c, char[][] m)
	{
		ArrayList<char[][]> current = new ArrayList<char[][]>();
		current.add(m);
		ArrayList<String> presses = new ArrayList<String>();
		presses.add("");
		while(true)
		{
			ArrayList<char[][]> next = new ArrayList<char[][]>();
			ArrayList<String> nextPress = new ArrayList<String>();
			for(int i = 0; i < current.size(); i++)
			{
				next.add(zero(c, current.get(i)));
				next.add(one(c, current.get(i)));
				next.add(two(c, current.get(i)));
				next.add(three(c, current.get(i)));
				next.add(four(c, current.get(i)));
				next.add(five(c, current.get(i)));
				next.add(six(c, current.get(i)));
				next.add(seven(c, current.get(i)));
				next.add(eight(c, current.get(i)));
				next.add(nine(c, current.get(i)));
				for(int j = 0; j < 10; j++)
					nextPress.add(presses.get(i) + "" + j);
			}
			for(int i = 0; i < next.size(); i++)
				next.set(i, path(next.get(i)));
			current.clear();
			presses.clear();
			int best = 0;
			for(int i = 0; i < next.size(); i++)
			{
				int score = findMarble(next.get(i));
				if(score >= best)
				{
					if(score > best)
					{
						current.clear();
						presses.clear();
						best = score;
					}
					current.add(next.get(i));
					presses.add(nextPress.get(i));
				}
			}
			if(best >= 5)
				return presses.get(0);
		}
	}
	private int findMarble(char[][] m)
	{
		int n = -1;
		for(int aa = 0; aa < m.length; aa++)
		{
			for(int bb = 0; bb < m[aa].length; bb++)
			{
				if(m[aa][bb] == 'm')
				{
					n = aa;
				}
			}
		}
		return n;
	}
	private char[][] path(char[][] m)
	{
		int row = 100;
		int col = 100;
		for(int aa = 0; aa < m.length; aa++)
		{
			for(int bb = 0; bb < m[aa].length; bb++)
			{
				if(m[aa][bb] == 'm')
				{
					row = aa;
					col = bb;
				}
			}
		}
		
		
		if(m[row + 1][col] == 'o')
		{
			m[row][col] = 'o';
			m[row + 1][col] = 'm';
			return (path(m));
		}
		else if(m[row + 1][col] == 't')
		{
			m[row][col] = 'o';
			return m;
		}
		else
		{
			return m;
		}
		
	}
	private char[] cw(char[] s)
	{
		char[] conv = new char[s.length];
		for(int aa = 1; aa < conv.length; aa++)
		{
			conv[aa] = s[aa - 1];
		}
		conv[0] = s[s.length - 1];
		return conv;
	}
	private char[] ccw(char[] s)
	{
		char[] conv = new char[s.length];
		for(int aa = 0; aa < (conv.length - 1); aa++)
		{
			conv[aa] = s[aa + 1];
		}
		conv[s.length - 1] = s[0];
		return conv;
	}
	private char[] none(char[] s)
	{
		char[] conv = new char[s.length];
		for(int aa = 0; aa < conv.length; aa++)
		{
			conv[aa] = s[aa];
		}
		return conv;
	}
	private char[][] zero(char[] c, char[][] m)
	{
		char[][] conv = new char[m.length][];
		for(int aa = 0; aa < m.length; aa++)
		{
			switch(c[aa])
			{
				case 'r':
					conv[aa] = ccw(m[aa]);
					break;
				case 'y':
					conv[aa] = cw(m[aa]);
					break;
				case 'g':
					conv[aa] = ccw(ccw(m[aa]));
					break;
				case 's':
					conv[aa] = cw(cw(m[aa]));
					break;
				default:
					conv[aa] = none(m[aa]);
					break;
			}
		}
		return conv;
	}
	private char[][] one(char[] c, char[][] m)
	{
		char[][] conv = new char[m.length][];
		for(int aa = 0; aa < m.length; aa++)
		{
			switch(c[aa])
			{
				case 'b':
					conv[aa] = ccw(m[aa]);
					break;
				case 'y':
					conv[aa] = cw(m[aa]);
					break;
				case 'r':
					conv[aa] = ccw(ccw(m[aa]));
					break;
				case 'g':
					conv[aa] = cw(cw(m[aa]));
					break;
				default:
					conv[aa] = none(m[aa]);
					break;
			}
		}
		return conv;
	}
	private char[][] two(char[] c, char[][] m)
	{
		char[][] conv = new char[m.length][];
		for(int aa = 0; aa < m.length; aa++)
		{
			switch(c[aa])
			{
				case 's':
					conv[aa] = ccw(m[aa]);
					break;
				case 'r':
					conv[aa] = cw(m[aa]);
					break;
				case 'b':
					conv[aa] = ccw(ccw(m[aa]));
					break;
				case 'g':
					conv[aa] = cw(cw(m[aa]));
					break;
				default:
					conv[aa] = none(m[aa]);
					break;
			}
		}
		return conv;
	}
	private char[][] three(char[] c, char[][] m)
	{
		char[][] conv = new char[m.length][];
		for(int aa = 0; aa < m.length; aa++)
		{
			switch(c[aa])
			{
				case 'y':
					conv[aa] = ccw(m[aa]);
					break;
				case 'b':
					conv[aa] = cw(m[aa]);
					break;
				case 'g':
					conv[aa] = ccw(ccw(m[aa]));
					break;
				case 's':
					conv[aa] = cw(cw(m[aa]));
					break;
				default:
					conv[aa] = none(m[aa]);
					break;
			}
		}
		return conv;
	}
	private char[][] four(char[] c, char[][] m)
	{
		char[][] conv = new char[m.length][];
		for(int aa = 0; aa < m.length; aa++)
		{
			switch(c[aa])
			{
				case 'b':
					conv[aa] = ccw(m[aa]);
					break;
				case 'g':
					conv[aa] = cw(m[aa]);
					break;
				case 's':
					conv[aa] = ccw(ccw(m[aa]));
					break;
				case 'r':
					conv[aa] = cw(cw(m[aa]));
					break;
				default:
					conv[aa] = none(m[aa]);
					break;
			}
		}
		return conv;
	}
	private char[][] five(char[] c, char[][] m)
	{
		char[][] conv = new char[m.length][];
		for(int aa = 0; aa < m.length; aa++)
		{
			switch(c[aa])
			{
				case 'g':
					conv[aa] = ccw(m[aa]);
					break;
				case 'r':
					conv[aa] = cw(m[aa]);
					break;
				case 'y':
					conv[aa] = ccw(ccw(m[aa]));
					break;
				case 'b':
					conv[aa] = cw(cw(m[aa]));
					break;
				default:
					conv[aa] = none(m[aa]);
					break;
			}
		}
		return conv;
	}
	private char[][] six(char[] c, char[][] m)
	{
		char[][] conv = new char[m.length][];
		for(int aa = 0; aa < m.length; aa++)
		{
			switch(c[aa])
			{
				case 's':
					conv[aa] = ccw(m[aa]);
					break;
				case 'b':
					conv[aa] = cw(m[aa]);
					break;
				case 'r':
					conv[aa] = ccw(ccw(m[aa]));
					break;
				case 'y':
					conv[aa] = cw(cw(m[aa]));
					break;
				default:
					conv[aa] = none(m[aa]);
					break;
			}
		}
		return conv;
	}
	private char[][] seven(char[] c, char[][] m)
	{
		char[][] conv = new char[m.length][];
		for(int aa = 0; aa < m.length; aa++)
		{
			switch(c[aa])
			{
				case 'y':
					conv[aa] = ccw(m[aa]);
					break;
				case 'g':
					conv[aa] = cw(m[aa]);
					break;
				case 's':
					conv[aa] = ccw(ccw(m[aa]));
					break;
				case 'b':
					conv[aa] = cw(cw(m[aa]));
					break;
				default:
					conv[aa] = none(m[aa]);
					break;
			}
		}
		return conv;
	}
	private char[][] eight(char[] c, char[][] m)
	{
		char[][] conv = new char[m.length][];
		for(int aa = 0; aa < m.length; aa++)
		{
			switch(c[aa])
			{
				case 'r':
					conv[aa] = ccw(m[aa]);
					break;
				case 's':
					conv[aa] = cw(m[aa]);
					break;
				case 'b':
					conv[aa] = ccw(ccw(m[aa]));
					break;
				case 'y':
					conv[aa] = cw(cw(m[aa]));
					break;
				default:
					conv[aa] = none(m[aa]);
					break;
			}
		}
		return conv;
	}
	private char[][] nine(char[] c, char[][] m)
	{
		char[][] conv = new char[m.length][];
		for(int aa = 0; aa < m.length; aa++)
		{
			switch(c[aa])
			{
				case 'g':
					conv[aa] = ccw(m[aa]);
					break;
				case 's':
					conv[aa] = cw(m[aa]);
					break;
				case 'y':
					conv[aa] = ccw(ccw(m[aa]));
					break;
				case 'r':
					conv[aa] = cw(cw(m[aa]));
					break;
				default:
					conv[aa] = none(m[aa]);
					break;
			}
		}
		return conv;
	}
	private boolean v1(String i)
	{
		if(i.length() == 5)
		{
			String check = "bgrsy";
			char[] temp = i.toCharArray();
			Arrays.sort(temp);
			return (check.equals(new String(temp)));
		}
		return false;
	}
	private boolean v2(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 2)
		{
			for(String str : conv)
			{
				switch(str)
				{
					case "1":case "2":case "3":case "4":case "5":
					case "6":case "7":case "8":case "9":case "10":
						break;
					default:
						return false;
				}
			}
			return !(conv[0].equals(conv[1]));
		}
		return false;
		
	}
}
