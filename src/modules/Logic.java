package modules;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import misc.MTK;
import start.BombEdgework;

public class Logic 
{
	private final BombEdgework ew;
	private final double r;
	public Logic(BombEdgework e, double resizer)
	{
		ew = e;
		r = resizer;
	}
	public void run()
	{
		String out = "";
		String[] pos = {"TOP", "BOTTOM"};
		MTK mtk = new MTK();
		ImageIcon i = new ImageIcon("img/LogicGateList.jpg");
		Image image = i.getImage();
		image = image.getScaledInstance((int)(i.getIconWidth() / r), (int)(i.getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
		i = new ImageIcon(image);
		JLabel l = new JLabel();
		l.setIcon(i);
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.add(l, BorderLayout.CENTER);
		frame.pack();
		for(int aa = 0; aa < 2; aa++)
		{
			frame.setVisible(true);
			String input = JOptionPane.showInputDialog("- - NOT\nEnter the " + pos[aa] + " expression:").toUpperCase();
			frame.setVisible(false);
			boolean v = valid(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				frame.setVisible(true);
				input = JOptionPane.showInputDialog("- - NOT\nEnter the " + pos[aa] + " expression:").toUpperCase();
				frame.setVisible(false);
				v = valid(input);
			}
			String[] expression = input.replace("(", "").replace(")", "").split(" ");
			boolean result;
			if(input.charAt(0) == '(')
			{
				result = mtk.solveLogicExpression(letterToBool(expression[0]), letterToBool(expression[2]), expression[1]);
				result = mtk.solveLogicExpression(result, letterToBool(expression[4]), expression[3]);
			}
			else
			{
				result = mtk.solveLogicExpression(letterToBool(expression[2]), letterToBool(expression[4]), expression[3]);
				result = mtk.solveLogicExpression(letterToBool(expression[0]), result, expression[1]);
			}
			if(result)
				out = out + "T ";
			else
				out = out + "F ";
		}
		JOptionPane.showMessageDialog(null, "Set the buttons\nto these states: " + out);
	}
	private boolean letterToBool(String l)
	{
		boolean b;
		switch(l.charAt(l.length() - 1))
		{
			case 'A':
				b = ew.BAT() == ew.numInd();
				break;
			case 'B':
				b = ew.numSNLETS() > ew.numSNDIGS();
				break;
			case 'C':
				b = ew.findInd("IND");
				break;
			case 'D':
				b = ew.findInd("FRK");
				break;
			case 'E':
				b = ew.numUnlit() == 1;
				break;
			case 'F':
				b = ew.numPortTypes() > 1;
				break;
			case 'G':
				b = ew.BAT() >= 2;
				break;
			case 'H':
				b = ew.BAT() < 2;
				break;
			case 'I':
				b = ew.getSNDIG(ew.numSNDIGS() - 1) % 2 == 1;
				break;
			case 'J':
				b = ew.BAT() > 4;
				break;
			case 'K':
				b = ew.numLit() == 1;
				break;
			case 'L':
				b = ew.numInd() > 2;
				break;
			case 'M':
				b = ew.numDupPorts() == 0;
				break;
			case 'N':
				b = ew.BH() > 2;
				break;
			case 'O':
				b = ew.numLit() > 0 && ew.numUnlit() > 0;
				break;
			case 'P':
				b = ew.findPort("PARALLEL") > 0;
				break;
			case 'Q':
				b = ew.numPorts() == 2;
				break;
			case 'R':
				b = ew.findPort("PS/2") > 0;
				break;
			case 'S':
				b = ew.getSNSUM() > 10;
				break;
			case 'T':
				b = ew.findInd("MSA");
				break;
			case 'U':
				b = ew.BH() == 1;
				break;
			case 'V':
				b = ew.numCharsInSN("AEIOU") > 0;
				break;
			case 'W':
				b = ew.numInd() == 0;
				break;
			case 'X':
				b = ew.numInd() == 1;
				break;
			case 'Y':
				b = ew.numPorts() > 5;
				break;
			default:
				b = ew.numPorts() < 2;
				break;
		}
		if(l.charAt(0) == '-')
			b = !(b);
		return b;
	}
	private boolean valid(String i)
	{
		String[] temp = i.split(" ");
		if(temp.length == 5)
		{
			if(temp[0].charAt(0) == '(' && temp[2].charAt(temp[2].length() - 1) == ')')
			{
				temp[0] = temp[0].substring(1);
				temp[2] = temp[2].substring(0, temp[2].length() - 1);
			}
			else if(temp[2].charAt(0) == '(' && temp[4].charAt(temp[4].length() - 1) == ')')
			{
				temp[2] = temp[2].substring(1);
				temp[4] = temp[4].substring(0, temp[4].length() - 1);
			}
			else
				return false;
			String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			for(int aa = 0; aa < 3; aa++)
			{
				if(temp[aa *2].length() == 2 && temp[aa * 2].charAt(0) == '-' && alpha.indexOf(temp[aa * 2].charAt(1)) >= 0)
					alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
				else if(temp[aa *2].length() == 1 && alpha.indexOf(temp[aa * 2].charAt(0)) >= 0)
					alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
				else
					return false;
			}
			for(int aa = 0; aa < 2; aa++)
			{
				switch(temp[(aa * 2) + 1])
				{
					case "AND":case "OR":case "XOR":case "NAND":case "NOR":case ">":case "<":case "XNOR":
						break;
					default:
						return false;
				}
			}
			return true;
		}
		return false;
	}
}
