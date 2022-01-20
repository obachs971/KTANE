package modules;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import start.BombEdgework;

public class RubiksCube 
{
	private final String[][] table =
		{
				{"L'", "F'"},
				{"D'", "U'"},
				{"U", "B'"},
				{"F", "B"},
				{"L", "D"},
				{"R'", "U"},
				{"U'", "F"},
				{"B'", "L'"},
				{"B", "R"},
				{"D", "L"},
				{"R", "D'"},
				{"F'", "R'"}
		};
	private final BombEdgework ew;
	private final double r;
	public RubiksCube(BombEdgework e, double resizer)
	{
		ew = e;
		r = resizer;
	}
	public void run()
	{
		String colorOrder = "YBRGOW", alpha = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ", colors = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nO - Orange\nW - White\nEnter the colors in the order of\nTop, Left, Front, Right, Bottom:").toUpperCase().replace(" ", "");
		boolean v = valid(colors);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			colors = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nO - Orange\nW - White\nEnter the colors in the order of\nTop, Left, Front, Right, Bottom:").toUpperCase().replace(" ", "");
			v = valid(colors);
		}
		String sn = ew.getSN().toUpperCase();
		sn = sn.substring(0, colorOrder.indexOf(colors.charAt(4))) + "" + sn.substring(colorOrder.indexOf(colors.charAt(4)) + 1);
		String[] moves = new String[10];
		if("RGB".indexOf(colors.charAt(3)) >= 0)
		{
			for(int aa = 0; aa < sn.length(); aa++)
			{
				int cursor = alpha.indexOf(sn.charAt(aa));
				cursor = ((cursor / 3) + colorOrder.indexOf(colors.charAt(cursor % 3)) + 1) % 12;
				moves[aa * 2] = table[cursor][0];
				moves[(aa * 2) + 1] = table[cursor][1];
			}
		}
		else
		{
			for(int aa = 0; aa < sn.length(); aa++)
			{
				int cursor = alpha.indexOf(sn.charAt(aa));
				cursor = ((cursor / 3) + (colorOrder.indexOf(colors.charAt(cursor % 3)) + 1)) % 12;
				moves[aa] = table[cursor][0];
				moves[aa + 5] = table[cursor][1];
			}
		}
		if(colors.charAt(3) == 'R' || colors.charAt(3) == 'Y')
		{
			for(int aa = 0; aa < 5; aa++)
			{
				if(moves[aa].length() == 2)
					moves[aa] = moves[aa].substring(0, 1);
				else
					moves[aa] = moves[aa] + "'";
			}
		}
		if(colors.charAt(3) == 'G' || colors.charAt(3) == 'W')
		{
			String[] temp = new String[10];
			for(int aa = 0; aa < 10; aa++)
				temp[aa] = moves[9 - aa];
			moves = temp;
		}
		ImageIcon[] i = new ImageIcon[10];
		JLabel[] l = new JLabel[10];
		JFrame f = new JFrame();
		f.setLayout(new GridLayout(2, 5));
		for(int aa = 0; aa < 10; aa++)
		{
			i[aa] = new ImageIcon("img/RubiksCube" + moves[aa] + ".jpg");
			Image image = i[aa].getImage();
			image = image.getScaledInstance((int)(i[aa].getIconWidth() / r), (int)(i[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			i[aa] = new ImageIcon(image);		
			l[aa] = new JLabel();
			l[aa].setIcon(i[aa]);
			f.add(l[aa]);
		}
		f.pack();
		f.setVisible(true);
		JOptionPane.showMessageDialog(null, "Do these moves");
		f.setVisible(false);
	}
	private boolean valid(String i)
	{
		if(i.length() == 5)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("ROYGBW".indexOf(i.charAt(aa)) < 0)
					return false;
				for(int bb = aa + 1; bb < i.length(); bb++)
				{
					if(i.charAt(aa) == i.charAt(bb))
						return false;
				}
			}
			return true;
		}
		return false;
	}
}
