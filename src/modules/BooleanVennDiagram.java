package modules;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import misc.MTK;

public class BooleanVennDiagram 
{
	private final double r;
	public BooleanVennDiagram(double resizer)
	{
		r = resizer;
	}
	public void run()
	{
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
		frame.setVisible(true);
		String expression = JOptionPane.showInputDialog("Enter the logic expression:").toUpperCase();
		frame.setVisible(false);
		boolean v = valid(expression);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			frame.setVisible(true);
			expression = JOptionPane.showInputDialog("Enter the logic expression:").toUpperCase();
			frame.setVisible(false);
			v = valid(expression);
		}
		String[] temp = expression.split(" ");
		String out = "";
		if(expression.charAt(0) == '(')
		{
			for(int a = 0; a < 2; a++)
			{
				for(int b = 0; b < 2; b++)
				{
					for(int c = 0; c < 2; c++)
					{
						if(mtk.solveLogicExpression(mtk.solveLogicExpression(a == 0, b == 0, temp[1]), c == 0, temp[3]))
						{
							if(a == 1 && b == 1 && c == 1)
								out = out + "O";
							if(a == 0)
								out = out + "A";
							if(b == 0)
								out = out + "B";
							if(c == 0)
								out = out + "C";
							out = out + "\n";
						}
					}
				}
			}
		}
		else
		{
			for(int a = 0; a < 2; a++)
			{
				for(int b = 0; b < 2; b++)
				{
					for(int c = 0; c < 2; c++)
					{
						if(mtk.solveLogicExpression(a == 0, mtk.solveLogicExpression(b == 0, c == 0, temp[3]), temp[1]))
						{
							if(a == 1 && b == 1 && c == 1)
								out = out + "O";
							if(a == 0)
								out = out + "A";
							if(b == 0)
								out = out + "B";
							if(c == 0)
								out = out + "C";
							out = out + "\n";
						}
					}
				}
			}
		}
		i = new ImageIcon("img/BooleanVennDiagram.jpg");
		image = i.getImage();
		image = image.getScaledInstance((int)(i.getIconWidth() / r), (int)(i.getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
		i = new ImageIcon(image);
		l = new JLabel();
		l.setIcon(i);
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.add(l, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		JOptionPane.showMessageDialog(null, "Press these spaces:\n" + out);
		frame.setVisible(false);
	}
	private boolean valid(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 5)
		{
			if((conv[0].equals("(A") && conv[2].equals("B)") && conv[4].equals("C")) || (conv[0].equals("A") && conv[2].equals("(B") && conv[4].equals("C)")))
			{
				
				for(int aa = 0; aa < 2; aa++)
				{
					switch(conv[(aa * 2) + 1])
					{
						case "AND":case "OR":case "XOR":case "NAND":case "NOR":case ">":case "<":case "XNOR":
							break;
						default:
							return false;
					}
				}
				return true;
			}
		}
		return false;
	}
}
