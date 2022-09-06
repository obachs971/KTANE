package modules;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import misc.MTK;

public class Superlogic 
{
	private final double r;
	public Superlogic(double resizer)
	{
		r = resizer;
	}
	public void run()
	{
		ImageIcon img = new ImageIcon("img/LogicGateList.jpg");
		Image image = img.getImage();
		image = image.getScaledInstance((int)(img.getIconWidth() / r), (int)(img.getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
		img = new ImageIcon(image);
		JLabel l = new JLabel();
		l.setIcon(img);
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.add(l, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		String[] exp = new String[3];
		for(int i = 0; i < 3; i++)
		{
			exp[i] = JOptionPane.showInputDialog("- - NOT/¬\nEnter expression " + "ABC".charAt(i) + ":").toUpperCase();
			boolean v = valid(exp[i], "ABC".charAt(i));
			while(!(v))
			{
				frame.setVisible(false);
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				frame.setVisible(true);
				exp[i] = JOptionPane.showInputDialog("- - NOT/¬\nEnter expression " + "ABC".charAt(i) + ":").toUpperCase();
				v = valid(exp[i], "ABC".charAt(i));
			}
		}
		frame.setVisible(false);
		for(int i = 0; i < 8; i++)
		{
			boolean flag = true;
			boolean[] ABC = { (i % 2) == 1, ((i / 2) % 2) == 1, (i / 4) == 1 };
			for(int j = 0; j < 3; j++)
			{
				if(ABC[j] != eval(ABC, exp[j]))
				{
					flag = false;
					break;
				}
			}
			if(flag)
			{
				JOptionPane.showMessageDialog(null, "A: " + (ABC[0] ? "Green/True" : "Red/False") + "\nB: " + (ABC[1] ? "Green/True" : "Red/False") + "\nC: " + (ABC[2] ? "Green/True" : "Red/False"));
				break;
			}
		}
	}
	private boolean eval(boolean[] ABC, String exp)
	{
		MTK mtk = new MTK();
		boolean not = false;
		String temp = exp.toUpperCase();
		if(temp.startsWith("-("))
		{
			temp = temp.replace("-(", "").replace(")", "");
			not = true;
		}
		String[] conv = temp.split(" ");
		boolean result = mtk.solveLogicExpression(evalVar(ABC, conv[0]),  evalVar(ABC, conv[2]), conv[1]);
		if(not)
			result = !(result);
		return result;
	}
	private boolean evalVar(boolean[] ABC, String var)
	{
		switch(var)
		{
			case "A": return ABC[0];
			case "-A": return !(ABC[0]);
			case "B": return ABC[1];
			case "-B": return !(ABC[1]);
			case "C": return ABC[2];
			case "-C": return !(ABC[2]);
		}
		return false;
	}
	private boolean valid(String i, char ex)
	{
		String[] conv = i.split(" ");
		if(conv.length == 3)
		{
			if(i.startsWith("-(") && i.endsWith(")"))
			{
				conv[0] = conv[0].substring(2);
				conv[2] = conv[2].substring(0, conv[2].length() - 1);
			}
			if(conv[0].contains(ex + "") || conv[2].contains(ex + ""))
				return false;
			return (isVar(conv[0]) && isGate(conv[1]) && isVar(conv[2]));
		}
		return false;
	}
	private boolean isVar(String i)
	{
		switch(i)
		{
			case "A": case "-A": 
			case "B": case "-B": 
			case "C": case "-C":
				return true;
		}
		return false;
	}
	private boolean isGate(String i)
	{
		switch(i)
		{
			case "AND": case "NAND":
			case "OR": case "NOR":
			case "XOR": case "XNOR":
			case "<": case ">":
				return true;
		}
		return false;
	}
}
