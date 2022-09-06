package modules;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import misc.PlayType;
import start.BombConfig;
import start.BombEdgework;

public class Cube 
{
	private final BombConfig cf;
	private final BombEdgework ew;
	private final double r;
	private final PlayType pt;
	private int result = 0;
	public Cube(BombConfig c, BombEdgework e, double resizer, PlayType p)
	{
		cf = c;
		ew = e;
		r = resizer;
		pt = p;
	}
	public String run()
	{
		//Get Rotation Codes
		String[] souvList = {"BACKWARDS", "FORWARDS", "LEFT", "RIGHT", "CLOCKWISE", "COUNTERCLOCKWISE"};
		String souv = "";
		int[] RC = new int[6];
		for(int i = 0; i < 6; i++)
		{
			JDialog dialog = getDialog(new String[] {"Up", "Down", "Left", "Right", "CW", "CCW"}, 3, 2);
			dialog.setTitle("Select Cube Rotation #" + (i + 1) +  ":");
			dialog.setVisible(true);
			RC[i] = result;
			souv = souv + "" + (i + 1) + ": " + souvList[result] + "\n";
		}
		//Get Cube Faces
		ImageIcon icon = new ImageIcon("img/CubeNumberOrder.png");
		Image image = icon.getImage();
		image = image.getScaledInstance((int)(icon.getIconWidth() / r), (int)(icon.getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(image);
		JLabel label = new JLabel();
		label.setIcon(icon);
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.add(label, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		String input = JOptionPane.showInputDialog("Enter the Cube's digits:").replace(" ", "");
		frame.setVisible(false);
		boolean v = valid(input, 6, "0123456789");
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			frame.setVisible(true);
			input = JOptionPane.showInputDialog("Enter the Cube's digits:").replace(" ", "");
			frame.setVisible(false);
			v = valid(input, 6, "0123456789");
		}
		int[] F = new int[6];
		for(int i = 0; i < input.length(); i++)
			F[i] = (input.charAt(i) - '0');
		//Get Wire Colors
		String wireColors = JOptionPane.showInputDialog("R - Red\nO - Orange\nG - Green\nB - Blue\nP - Purple\nW - White\nEnter the wire colors:").replace(" ", "").toUpperCase();
		v = valid(wireColors, 4, "ROGBPW");
		while(!(v))
		{	
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			wireColors = JOptionPane.showInputDialog("R - Red\nO - Orange\nG - Green\nB - Blue\nP - Purple\nW - White\nEnter the wire colors:").replace(" ", "").toUpperCase();
			v = valid(wireColors, 4, "ROGBPW");
		}
		//Get Symbols
		int[][] ciphers = new int[2][8];
		String[] alpha = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q"};
		String[] pos = {"Left", "Right"};
		for(int i = 0; i < 2; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				JDialog dialog = getDialog(alpha, 6, 3);
				dialog.setTitle("Select Symbol #" + (j + 1) +  " on the " + pos[i] + ":");
				dialog.setVisible(true);
				ciphers[i][j] = (result + 1) % 10;
			}
		}
		if(pt == PlayType.Team)
			Team(RC, F, wireColors, ciphers);
		else
			Solo(RC, F, wireColors, ciphers);
		return souv;
	}
	private void Solo(int[] RC, int[] F, String wireColors, int[][] ciphers)
	{
		String[] alpha = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q"};
		
		//Get Button Colors
		String buttonColors = JOptionPane.showInputDialog("R - Red\nO - Orange\nG - Green\nB - Blue\nP - Purple\nW - White\nEnter the button colors (reading order):").replace(" ", "").toUpperCase();
		boolean v = valid(buttonColors, 8, "ROGBPW");
		while(!(v))
		{	
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			buttonColors = JOptionPane.showInputDialog("R - Red\nO - Orange\nG - Green\nB - Blue\nP - Purple\nW - White\nEnter the button colors (reading order):").replace(" ", "").toUpperCase();
			v = valid(buttonColors, 8, "ROGBPW");
		}
		//Get Button Symbols
		int[] buttonSymbols = new int[8];
		for(int i = 0; i < 8; i++)
		{
			JDialog dialog = getDialog(alpha, 6, 3);
			dialog.setTitle("Select Button Symbol #" + (i + 1) + ":");
			dialog.setVisible(true);
			buttonSymbols[i] = result;
		}
		//Get Submit Color
		String submitColor = JOptionPane.showInputDialog("R - Red\nO - Orange\nG - Green\nB - Blue\nP - Purple\nW - White\nEnter the Submit's color:").replace(" ", "").toUpperCase();
		v = valid(submitColor, 1, "ROGBPW");
		while(!(v))
		{	
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			submitColor = JOptionPane.showInputDialog("R - Red\nO - Orange\nG - Green\nB - Blue\nP - Purple\nW - White\nEnter the Submit's color:").replace(" ", "").toUpperCase();
			v = valid(submitColor, 1, "ROGBPW");
		}
		//Get Submit Symbol
		JDialog dialog = getDialog(alpha, 6, 3);
		dialog.setTitle("Select Submit Symbol:");
		dialog.setVisible(true);
		int submitSymbol = result;
		//Calculate  RC
		int[] vals = {ew.getSNDIG(ew.numSNDIGS() - 1), ew.getSNDIG(0), getNum(wireColors.charAt(2), buttonColors), getNum(wireColors.charAt(0), buttonColors), 4, 7};
		for(int i = 0; i < RC.length; i++)
			RC[i] = vals[RC[i]];
		//Calculate WC
		int[] WC = new int[4];
		for(int i = 0; i < wireColors.length(); i++)
		{
			switch(wireColors.charAt(i))
			{
				case 'B':
					WC[i] = i + 6;
					break;
				case 'G':
					WC[i] = (getNum('B', buttonColors) + 7) % 10;
					break;
				case 'O':
					WC[i] = (getNum('G', buttonColors) + 3) % 10;
					break;
				case 'P':
					WC[i] = (F[0] + F[1] + F[2] + F[3] + F[4] + F[5]) % 10;
					break;
				case 'R':
					WC[i] = (cf.getNumberModules() + 7) % 10;
					break;
				default:
					WC[i] = 6;
			}
		}
		//Calculate Cipher #1
		int[] cipher =
			{
				((RC[0] + F[5] + WC[2]) % 10),
				((RC[1] + F[4] + WC[3]) % 10),
				((RC[2] + F[3] + WC[0]) % 10),
				((RC[3] + F[2] + WC[1]) % 10),
				((RC[4] + F[1]) % 8),
				((RC[5] + F[0]) % 9),
				0, 0
			};
		//Calculate Final Cipher
		int[] FC = new int[8];
		for(int i = 0; i < 8; i++)
			FC[i] = (cipher[i] + ciphers[0][i] + ciphers[1][i]) % 10; 
		//Get Button Presses for each stage
		String[] symList = {"AFIL", "BEKO", "DNQ", "CGP", "HJM", "EJQ", "FLP", "AKM", "CGHO", "BDIN"};
		String out = "";
		for(int i = 0; i < 8; i++)
		{
			//Use the Nth final cipher digit to get a list of symbols
			//Then press the buttons that have the symbols within that list
			int[] find = new int[symList[FC[i]].length()];
			for(int j = 0; j < find.length; j++)
				find[j] = (symList[FC[i]].charAt(j) - 'A');
			boolean[] press = {false, false, false, false, false, false, false, false};
			for(int j = 0; j < buttonSymbols.length; j++)
			{
				for(int k = 0; k < find.length; k++)
				{
					if(find[k] == buttonSymbols[j])
					{
						press[j] = true;
						break;
					}
				}
			}
			//Depending on which stage, there are more rules for button pressing
			switch(i)
			{
				case 1:
					for(int j = 0; j < buttonSymbols.length; j++)
					{
						if(buttonSymbols[j] == submitSymbol)
							press[j] = true;
					}
					break;
				case 3:
					for(int j = 0; j < buttonColors.length(); j++)
					{
						if(buttonColors.charAt(j) == submitColor.charAt(0))
							press[j] = true;
					}
					break;
				case 5:
					for(int j = 0; j < buttonColors.length(); j++)
					{
						if(buttonColors.charAt(j) == wireColors.charAt(0))
							press[j] = true;
					}
					break;
				case 6:
					for(int j = 0; j < buttonColors.length(); j++)
					{
						if(buttonColors.charAt(j) == wireColors.charAt(2))
							press[j] = true;
					}
					break;
				case 7:
					for(int j = 0; j < press.length; j++)
						press[j] = !(press[j]);
					break;
			}
			out = out + "Stage #" + (i + 1) + ": ";
			for(int j = 0; j < press.length; j++)
			{
				if(press[j])
					out = out + "" + (j + 1) + " ";
			}
			out = out + "\n";
		}
		JOptionPane.showMessageDialog(null, "Press these buttons in reading order:\n" + out);
	}
	
	private void Team(int[] RC, int[] F, String wireColors, int[][] ciphers)
	{
		String colorOrder = "BGOPRW";
		String[] colorList = {"BLUE", "GREEN", "ORANGE", "PURPLE", "RED", "WHITE"};
		int[] numButtonColors = {-1, -1, -1, -1, -1, -1};
		boolean v;
		//Calculate  RC
		for(int i = 0; i < RC.length; i++)
		{
			switch(RC[i])
			{
				case 0:
					RC[i] = ew.getSNDIG(ew.numSNDIGS() - 1);
					break;
				case 1:
					RC[i] = ew.getSNDIG(0);
					break;
				case 2:
					int indexA = colorOrder.indexOf(wireColors.charAt(2));
					if(numButtonColors[indexA] == -1)
					{
						String inputA = JOptionPane.showInputDialog("Enter the number of\n" + colorList[indexA] + " buttons:");
						v = valid(inputA, 1, "012345678");
						while(!(v))
						{
							JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
							inputA = JOptionPane.showInputDialog("Enter the number of\n" + colorList[indexA] + " buttons:");
							v = valid(inputA, 1, "012345678");
						}
						numButtonColors[indexA] = Integer.parseInt(inputA);
					}
					RC[i] = numButtonColors[indexA];
					break;
				case 3:
					int indexB = colorOrder.indexOf(wireColors.charAt(0));
					if(numButtonColors[indexB] == -1)
					{
						String inputA = JOptionPane.showInputDialog("Enter the number of\n" + colorList[indexB] + " buttons:");
						v = valid(inputA, 1, "012345678");
						while(!(v))
						{
							JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
							inputA = JOptionPane.showInputDialog("Enter the number of\n" + colorList[indexB] + " buttons:");
							v = valid(inputA, 1, "012345678");
						}
						numButtonColors[indexB] = Integer.parseInt(inputA);
					}
					RC[i] = numButtonColors[indexB];
					break;
				case 4:
					RC[i] = 4;
					break;
				default:
					RC[i] = 7;
					break;
			}
		}
		//Calculate WC
		int[] WC = new int[4];
		for(int i = 0; i < wireColors.length(); i++)
		{
			switch(wireColors.charAt(i))
			{
				case 'B':
					WC[i] = i + 6;
					break;
				case 'G':
					int indexA = colorOrder.indexOf('B');
					if(numButtonColors[indexA] == -1)
					{
						String inputA = JOptionPane.showInputDialog("Enter the number of\n" + colorList[indexA] + " buttons:");
						v = valid(inputA, 1, "012345678");
						while(!(v))
						{
							JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
							inputA = JOptionPane.showInputDialog("Enter the number of\n" + colorList[indexA] + " buttons:");
							v = valid(inputA, 1, "012345678");
						}
						numButtonColors[indexA] = Integer.parseInt(inputA);
					}
					WC[i] = (numButtonColors[indexA] + 7) % 10;
					break;
				case 'O':
					int indexB = colorOrder.indexOf('G');
					if(numButtonColors[indexB] == -1)
					{
						String inputA = JOptionPane.showInputDialog("Enter the number of\n" + colorList[indexB] + " buttons:");
						v = valid(inputA, 1, "012345678");
						while(!(v))
						{
							JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
							inputA = JOptionPane.showInputDialog("Enter the number of\n" + colorList[indexB] + " buttons:");
							v = valid(inputA, 1, "012345678");
						}
						numButtonColors[indexB] = Integer.parseInt(inputA);
					}
					WC[i] = (numButtonColors[indexB] + 3) % 10;
					break;
				case 'P':
					WC[i] = (F[0] + F[1] + F[2] + F[3] + F[4] + F[5]) % 10;
					break;
				case 'R':
					WC[i] = (cf.getNumberModules() + 7) % 10;
					break;
				default:
					WC[i] = 6;
			}
		}
		//Calculate Cipher #1
		int[] cipher =
			{
				((RC[0] + F[5] + WC[2]) % 10),
				((RC[1] + F[4] + WC[3]) % 10),
				((RC[2] + F[3] + WC[0]) % 10),
				((RC[3] + F[2] + WC[1]) % 10),
				((RC[4] + F[1]) % 8),
				((RC[5] + F[0]) % 9),
				0, 0
			};
		
		//System.out.println(RC[0] + " " + RC[1] + " " + RC[2] + " " + RC[3] + " " + RC[4] + " " + RC[5]);
		//System.out.println(F[5] + " " + F[4] + " " + F[3] + " " + F[2] + " " + F[1] + " " + F[0]);
		//System.out.println(WC[2] + " " + WC[3] + " " + WC[0] + " " + WC[1]);
		//System.out.println(cipher[0] + "" + cipher[1] + "" + cipher[2] + "" + cipher[3] + "" + cipher[4] + "" + cipher[5]);
		//for(int i = 0; i < 2; i++)
		//	System.out.println(ciphers[i][0] + "" + ciphers[i][1] + "" + ciphers[i][2] + "" + ciphers[i][3] + "" + ciphers[i][4] + "" + ciphers[i][5] + "" + ciphers[i][6] + "" + ciphers[i][7]);
		//Calculate Final Cipher
		int[] FC = new int[8];
		for(int i = 0; i < 8; i++)
			FC[i] = (cipher[i] + ciphers[0][i] + ciphers[1][i]) % 10; 
		//Get Button Presses for each stage
		String[] symList = {"AFIL", "BEKO", "DNQ", "CGP", "HJM", "EJQ", "FLP", "AKM", "CGHO", "BDIN"};
		for(int i = 0; i < 8; i++)
		{
			ImageIcon[] icon = new ImageIcon[symList[FC[i]].length()];
			JLabel[] label = new JLabel[icon.length];
			JFrame frame = new JFrame();
			frame.setLayout(new GridLayout(icon.length, 1));
			for(int j = 0; j < icon.length; j++)
			{
				icon[j] = new ImageIcon("img/Cube" + symList[FC[i]].charAt(j) + ".png");
				Image image = icon[j].getImage();
				image = image.getScaledInstance((int)(icon[j].getIconWidth() / r), (int)(icon[j].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
				icon[j] = new ImageIcon(image);
				label[j] = new JLabel();
				label[j].setIcon(icon[j]);
				frame.add(label[j]);
			}
			frame.pack();
			frame.setVisible(true);
			switch(i)
			{
				case 1:
					JOptionPane.showMessageDialog(null, "Stage 2:\nPress these symbols\nAnd every button that contains\nthe submit button's symbol");
					break;
				case 3:
					JOptionPane.showMessageDialog(null, "Stage 4:\nPress these symbols\nAnd every button that contains\nthe submit button's color");
					break;
				case 5:
					JOptionPane.showMessageDialog(null, "Stage 6:\nPress these symbols\nAnd every button colored " + colorList[colorOrder.indexOf(wireColors.charAt(0))]);
					break;
				case 6:
					JOptionPane.showMessageDialog(null, "Stage 7:\nPress these symbols\nAnd every button colored " + colorList[colorOrder.indexOf(wireColors.charAt(2))]);
					break;
				case 7:
					JOptionPane.showMessageDialog(null, "Stage 8:\nPress the buttons that does\nNOT have these symbols");
					break;
				default:
					JOptionPane.showMessageDialog(null, "Stage " + (i + 1) + ":\nPress these symbols");
			}
			frame.setVisible(false);
		}
	}
	private JDialog getDialog(String[] list, int row, int col)
	{
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		ImageIcon[] icon = new ImageIcon[list.length];
		JButton[] jButton = new JButton[list.length];
		optionPane.setLayout(new GridLayout(row, col));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int aa = 0; aa < list.length; aa++)
		{
			icon[aa] = new ImageIcon("img/Cube" + list[aa] + ".png");
			Image image = icon[aa].getImage();
			image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			icon[aa] = new ImageIcon(image);
			jButton[aa] = getButton(optionPane, list[aa], icon[aa], aa);
			optionPane.add(jButton[aa]);
		}
		return optionPane.createDialog(frame, "");
	}
	private JButton getButton(final JOptionPane optionPane, String text, ImageIcon icon, int val) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    button.setText(text);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue("");
	        result = val;
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
	private int getNum(char c, String s)
	{
		int sum = 0;
		for(char l : s.toCharArray())
		{
			if(l == c)
				sum++;
		}
		return sum;
	}
	private boolean valid(String i, int len, String op)
	{
		if(i.length() == len)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if(op.indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
}
