package modules;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.*;
public class XRay 
{
	private final int[][] chart =
		{
				{2, 1, 5, 2, 5, 2, 5, 3, 5, 2, 5, 3},
				{4, 5, 4, 3, 4, 3, 4, 2, 1, 4, 3, 2},
				{2, 3, 1, 5, 2, 1, 5, 1, 3, 2, 1, 4},
				{4, 1, 4, 3, 4, 3, 2, 4, 2, 1, 3, 5},
				{3, 2, 5, 2, 1, 2, 5, 1, 5, 4, 5, 2},
				{4, 5, 1, 4, 3, 5, 3, 2, 3, 2, 3, 4},
				{2, 3, 2, 3, 5, 4, 2, 1, 4, 1, 4, 2},
				{4, 5, 1, 4, 2, 3, 5, 4, 3, 5, 2, 1},
				{5, 2, 3, 2, 3, 4, 1, 2, 1, 2, 4, 5},
				{2, 4, 1, 5, 1, 2, 5, 3, 4, 3, 5, 1},
				{5, 3, 5, 3, 2, 3, 1, 4, 2, 5, 2, 4},
				{2, 4, 1, 2, 1, 5, 4, 3, 5, 4, 3, 5}
		};
	private final double r;
	public XRay(double resizer)
	{
		r = resizer;
	}
	public void run()
	{
		ImageIcon i = new ImageIcon("img/X-RayList.jpg");
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
		String input = JOptionPane.showInputDialog("Enter the 3 symbols (spaces):");
		f.setVisible(false);
		boolean v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			f.setVisible(true);
			input = JOptionPane.showInputDialog("Enter the 3 symbols (spaces):");
			f.setVisible(false);
			v = valid(input);
		}
		int[] XY = getXY(input);
		JOptionPane.showMessageDialog(null, "Press this number: " + chart[XY[1]][XY[0]]);
	}
	private boolean valid(String i)
	{
		String[] spl = i.split(" ");
		if(spl.length == 3)
		{
			boolean b1 = false;
			boolean b2 = false;
			boolean b3 = false;
			for(int aa = 0; aa < spl.length; aa++)
			{
				switch(spl[aa])
				{
					case "1":
					case "2":
					case "3":
					case "4":
					case "5":
					case "6":
					case "7":
					case "8":
					case "9":
					case "10":
					case "11":
					case "12":
						b1 = true;
						break;
					case "13":
					case "14":
					case "15":
					case "16":
					case "17":
					case "18":
					case "19":
					case "20":
					case "21":
					case "22":
					case "23":
					case "24":
						b2 = true;
						break;
					case "25":
					case "26":
					case "27":
					case "28":
					case "29":
					case "30":
					case "31":
					case "32":
					case "33":
						b3 = true;
						break;
					default:
						return false;
				}
			}
			if(b1 && b2 && b3)
			{
				int[] XY = getXY(i);
				if(XY[0] >= 0 && XY[1] >= 0)
					return true;
			}
		}
		return false;
	}
	private int[] getXY(String i)
	{
		String[] spl = i.split(" ");
		int[] XY = {0, 0};
		for(int aa = 0; aa < spl.length; aa++)
		{
			int num = Integer.parseInt(spl[aa]);
			if(num < 13)
				XY[1] += (num - 1);
			else if(num < 25)
				XY[0] += (num - 13);
			else
			{
				switch(num)
				{
					case 25:
						XY[1]--;
						XY[0]--;
						break;
					case 26:
						XY[1]--;
						break;
					case 27:
						XY[1]--;
						XY[0]++;
						break;
					case 28:
						XY[0]--;
						break;
					case 30:
						XY[0]++;
						break;
					case 31:
						XY[1]++;
						XY[0]--;
						break;
					case 32:
						XY[1]++;
						break;
					case 33:
						XY[1]++;
						XY[0]++;
						break;
				}
			}
		}
		return XY;
	}
}
