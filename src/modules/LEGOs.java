package modules;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
public class LEGOs 
{
	private final double r;
	public LEGOs(double resizer)
	{
		r = resizer;
	}
	public String run()
	{
		String order = "RGBCMY";
		String[] sizes = {"", "", "", "", "", ""};
		int[][] pos = {
				{0, 0, 0},
				{0, 0, 0},
				{0, 0, 0},
				{0, 0, 0},
				{0, 0, 0},
				{0, 0, 0}
		};
		char[][][] board = new char[6][50][50];
		char[][] board2D = new char[50][50];
		for(int a = 0; a < board.length; a++)
		{
			for(int b = 0; b < board[a].length; b++)
			{
				for(int c = 0; c < board[a][b].length; c++)
				{
					board[a][b][c] = 'W';
					board2D[b][c] = 'W';
				}
			}
		}
			
		String input = JOptionPane.showInputDialog("Moves the TOP Piece:\nU - Up\nR - Right\nD - Down\nL - Left\nR - Red\nG - Green\nB - Blue\nC - Cyan\nM - Magenta\nY - Yellow\nLength by Height\nEx: M32 G13 RUU\nEnter page #1 (spaces):").toUpperCase();
		boolean v = v1(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Moves the TOP Piece:\nU - Up\nR - Right\nD - Down\nL - Left\nR - Red\nG - Green\nB - Blue\nC - Cyan\nM - Magenta\nY - Yellow\nLength by Height\nEx: M32 G13 RUU\nEnter page #1 (spaces):").toUpperCase();
			v = v1(input);
		}
		String[] spl = input.split(" ");
		int btm = order.indexOf(spl[0].charAt(0));
		int top = order.indexOf(spl[1].charAt(0));
		sizes[btm] = spl[0].substring(1);
		sizes[top] = spl[1].substring(1);
		board[0] = placePiece(board[0], spl[0], 25, 25);
		pos[btm] = new int[] {0, 25, 25};
		if(spl.length == 3)
			pos[top] = getPos(1, 25, 25, spl[2]);
		else
			pos[top] = new int[] {1, 25, 25};
		board[pos[top][0]] = placePiece(board[pos[top][0]], spl[1], pos[top][1], pos[top][2]);
		int pages = 1;
		do
		{
			input = JOptionPane.showInputDialog("Moves the TOP Piece:\nU - UpnR - RightnD - Down\nL - Left\nR - Red\nG - Green\nB - Blue\nC - Cyan\nM - Magenta\nY - Yellow\nLength by Height: 32\nEx: M32 G13 RUU\nEnter page #" + (pages + 1) + " (spaces):").toUpperCase();
			v = v2(input, sizes, order, pos);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Moves the TOP Piece:\nU - UpnR - RightnD - Down\nL - Left\nR - Red\nG - Green\nB - Blue\nC - Cyan\nM - Magenta\nY - Yellow\nLength by Height: 32\nEx: M32 G13 RUU\nEnter page #" + (pages + 1) + " (spaces):").toUpperCase();
				v = v2(input, sizes, order, pos);
			}
			if(input.length() == 0)
				break;
			pages++;
			spl = input.split(" ");
			btm = order.indexOf(spl[0].charAt(0));
			top = order.indexOf(spl[1].charAt(0));
			if(sizes[btm].length() != sizes[top].length())
			{
				if(sizes[btm].length() > 0)
				{
					pos[top] = getPos(pos[btm][0] + 1, pos[btm][1], pos[btm][2], spl.length == 2 ? "" : spl[2]);
					board[pos[top][0]] = placePiece(board[pos[top][0]], spl[1], pos[top][1], pos[top][2]);
				}
				else
				{
					if(spl.length == 3)
					{
						spl[2] = spl[2].replace("U", "*").replace("D", "U").replace("*", "D");
						spl[2] = spl[2].replace("R", "*").replace("L", "R").replace("*", "L");
					}
					pos[btm] = getPos(pos[top][0] - 1, pos[top][1], pos[top][2], spl.length == 2 ? "" : spl[2]);
					board[pos[btm][0]] = placePiece(board[pos[btm][0]], spl[0], pos[btm][1], pos[btm][2]);
				}
				sizes[btm] = spl[0].substring(1);
				sizes[top] = spl[1].substring(1);
			}
		}while(true);
		int num32 = 0;
		for(int i = 0; i < sizes.length; i++)
		{
			switch(sizes[i])
			{
				case "32":	case "23":
					num32++;
			}
		}
		String orient = "";
		if(num32 >= 3)
			orient += "T";
		else if(sizes[5].equals("13") || sizes[5].equals("31"))
			orient += "B";
		else if(pages >= 7)
			orient += "T";
		else
			orient += "B";
		if(sizes[1].equals(sizes[4]) || (sizes[1].charAt(0) == sizes[4].charAt(1) && sizes[1].charAt(1) == sizes[4].charAt(0)))
			orient += "W";
		else if(pos[0][0] >= 3 || pos[1][0] >= 3 || pos[2][0] >= 3 || pos[3][0] >= 3 || pos[4][0] >= 3 || pos[5][0] >= 3)
			orient += "N";
		else if(pos[2][0] > pos[0][0])
			orient += "E";
		else
			orient += "S";
		//System.out.println(orient);
		if(orient.charAt(0) == 'T')
		{
			for(int i = 5; i >= 0; i--)
			{
				for(int j = 0; j < 50; j++)
				{
					for(int k = 0; k < 50; k++)
					{
						if(board[i][j][k] != 'W' && board2D[j][k] == 'W')
							board2D[j][k] = board[i][j][k];
					}
				}
			}
		}
		else
		{
			if(orient.contains("E"))
				orient = orient.replace("E", "W");
			else if(orient.contains("W"))
				orient = orient.replace("W", "E");
			for(int i = 0; i < 6; i++)
			{
				for(int j = 0; j < 50; j++)
				{
					for(int k = 0; k < 50; k++)
					{
						if(board[i][j][k] != 'W' && board2D[j][49 - k] == 'W')
							board2D[j][49 - k] = board[i][j][k];
					}
				}
			}
		}
		int[] rows = new int[2], cols = new int[2];
		for(int i = 0; i < 50; i++)
		{
			String str = String.copyValueOf(board2D[i]);
			if(str.contains("R") || str.contains("G") || str.contains("B") || str.contains("C") || str.contains("M") || str.contains("Y"))
			{
				rows[0] = i;
				break;
			}
		}
		for(int i = 49; i >= 0; i--)
		{
			String str = String.copyValueOf(board2D[i]);
			if(str.contains("R") || str.contains("G") || str.contains("B") || str.contains("C") || str.contains("M") || str.contains("Y"))
			{
				rows[1] = i + 1;
				break;
			}
		}
		for(int i = 0; i < 50; i++)
		{
			boolean flag = false;
			for(int j = rows[0]; j < rows[1]; j++)
			{
				if("RGBCMY".indexOf(board2D[j][i]) >= 0)
				{
					flag = true;
					break;
				}
			}
			if(flag)
			{
				cols[0] = i;
				break;
			}
		}
		for(int i = 49; i >= 0; i--)
		{
			boolean flag = false;
			for(int j = rows[0]; j < rows[1]; j++)
			{
				if("RGBCMY".indexOf(board2D[j][i]) >= 0)
				{
					flag = true;
					break;
				}
			}
			if(flag)
			{
				cols[1] = i + 1;
				break;
			}
		}
		ArrayList<String> out = new ArrayList<String>();
		//System.out.println(rows[0] + " " + rows[1] + " " + cols[0] + " " + cols[1]);
		switch(orient.charAt(1))
		{
			case 'N':
				for(int i = rows[0]; i < rows[1]; i++)
				{
					String str = String.copyValueOf(board2D[i]);
					out.add(str.substring(cols[0], cols[1]));
				}
				break;
			case 'E':
				for(int i = cols[1] - 1; i >= cols[0]; i--)
				{
					String str = "";
					for(int j = rows[0]; j < rows[1]; j++)
						str = str + "" + board2D[j][i];
					System.out.println(str);
					out.add(str);
				}
				break;
			case 'S':
				for(int i = rows[1] - 1; i >= rows[0]; i--)
				{
					String str = "";
					for(int j = cols[1] - 1; j >= cols[0]; j--)
						str = str + "" + board2D[i][j];
					out.add(str);
				}
				break;
			default:
				for(int i = cols[0]; i < cols[1]; i++)
				{
					String str = "";
					for(int j = rows[1] - 1; j >= rows[0]; j--)
						str = str + "" + board2D[j][i];
					out.add(str);
				}
				break;
		}
		//Output Solution
		JFrame frame = new JFrame();
		frame.setLayout(new GridLayout(out.size(), out.get(0).length()));
		ImageIcon[] icon = new ImageIcon[out.size() * out.get(0).length()];
		JLabel[] label = new JLabel[icon.length];
		int counter = 0;
		for(int i = 0; i < out.size(); i++)
		{
			for(int j = 0; j < out.get(i).length(); j++)
			{
				icon[counter] = new ImageIcon("img/Lego" + out.get(i).charAt(j) + ".png");
				Image image = icon[counter].getImage();
				image = image.getScaledInstance((int)(icon[counter].getIconWidth() / r), (int)(icon[counter].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
				icon[counter] = new ImageIcon(image);
				label[counter] = new JLabel();
				label[counter].setIcon(icon[counter]);
				frame.add(label[counter++]);
			}
		}
		frame.pack();
		frame.setVisible(true);
		JOptionPane.showMessageDialog(null, "Enter the picture");
		frame.setVisible(false);
		return ("RED: " + sizes[0].charAt(0) + "x" + sizes[0].charAt(1) + "\nGREEN: " + sizes[1].charAt(0) + "x" + sizes[1].charAt(1) + "\nBLUE: " + sizes[2].charAt(0) + "x" + sizes[2].charAt(1) + "\nCYAN: " + sizes[3].charAt(0) + "x" + sizes[3].charAt(1) + "\nMAGENTA: " + sizes[4].charAt(0) + "x" + sizes[4].charAt(1) + "\nYELLOW: " + sizes[5].charAt(0) + "x" + sizes[5].charAt(1));
	}
	private int[] getPos(int h, int r, int c, String dir)
	{
		for(char d : dir.toCharArray())
		{
			switch(d)
			{
				case 'U':
					r--;
					break;
				case 'R':
					c++;
					break;
				case 'D':
					r++;
					break;
				case 'L':
					c--;
					break;
			}
		}
		return new int[] {h, r, c};
	}
	private char[][] placePiece(char[][] b, String p, int row, int col)
	{
		for(int i = 0; i < "01234".indexOf(p.charAt(2)); i++)
			for(int j = 0; j < "01234".indexOf(p.charAt(1)); j++)
				b[row + i][col + j] = p.charAt(0);
		return b;
	}
	private boolean v1(String i)
	{
		String conv[] = i.split(" ");
		if(conv.length == 3)
		{
			int[] dir = {0, 0, 0, 0};
			for(int aa = 0; aa < conv[2].length(); aa++)
			{
				if("URDL".indexOf(conv[2].charAt(aa)) < 0)
					return false;
				else
					dir["URDL".indexOf(conv[2].charAt(aa))]++;
			}
			for(int aa = 0; aa < 2; aa++)
			{
				if(dir[aa] > dir[aa + 2])
				{
					dir[aa] -= dir[aa + 2];
					dir[aa + 2] = 0;
				}
				else
				{
					dir[aa + 2] -= dir[aa];
					dir[aa] = 0;
				}
			}	
			if(isPiece(conv[0]) && isPiece(conv[1]))
			{
				if(dir[0] >= (Integer.parseInt(conv[1].substring(1)) % 10))
					return false;
				if(dir[1] >= (Integer.parseInt(conv[0].substring(1)) / 10))
					return false;
				if(dir[2] >= (Integer.parseInt(conv[0].substring(1)) % 10))
					return false;
				if(dir[3] >= (Integer.parseInt(conv[1].substring(1)) / 10))
					return false;
				return true;
			}
		}
		if(conv.length == 2)
			return (isPiece(conv[0]) && isPiece(conv[1]));
		return false;
	}
	private boolean v2(String i, String[] sizes, String order, int[][] pos)
	{
		if(i.length() == 0 && sizes[0].length() > 0 && sizes[1].length() > 0 && sizes[2].length() > 0 && sizes[3].length() > 0 && sizes[4].length() > 0 && sizes[5].length() > 0)
			return true;
		String conv[] = i.split(" ");
		if(conv.length > 1)
		{
			if(isPiece(conv[0]) && isPiece(conv[1]))
			{
				if(sizes[order.indexOf(conv[0].charAt(0))].equals(conv[0].substring(1)))
				{
					if(pos[order.indexOf(conv[0].charAt(0))][0] < 5)
						return v1(i);
				}
				else if(sizes[order.indexOf(conv[1].charAt(0))].equals(conv[1].substring(1)))
				{
					if(pos[order.indexOf(conv[1].charAt(0))][0] > 0)
						return v1(i);
				}
			}
		}
		return false;
	}
	private boolean isPiece(String i)
	{
		if(i.length() == 3)
		{
			if("RGBCMY".indexOf(i.charAt(0)) < 0)
				return false;
			switch(i.substring(1))
			{
				case "13":case "31":case "14":
				case "41":case "22":case "23":
				case "32":case "24":case "42":
					return true;
			}
		}
		return false;
	}
}
