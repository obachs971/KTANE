package modules;

import javax.swing.JOptionPane;
import start.BombEdgework;

public class Wires 
{
	private final BombEdgework ew;
	public Wires(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		String colors = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nW - White\nK - Black\nEnter the wire colors:").toUpperCase();
		boolean v = valid(colors);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			colors = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nW - White\nK - Black\nEnter the wire colors:").toUpperCase();
			v = valid(colors);
		}
		int cut;
		switch(colors.length())
		{
			case 3:
				if(!(colors.contains("R")))
					cut = 2;
				else if(colors.charAt(2) == 'W')
					cut = 3;
				else if(colors.chars().filter(ch -> ch == 'B').count() > 1)
				{
					if(colors.charAt(2) == 'B')
						cut = 3;
					else
						cut = 2;
				}
				else
					cut = 3;
				break;
			case 4:
				if(colors.chars().filter(ch -> ch == 'R').count() > 1 && ew.getSNDIG(ew.numSNDIGS()) % 2 == 1)
				{
					if(colors.charAt(3) == 'R')
						cut = 4;
					else if(colors.charAt(2) == 'R')
						cut = 3;
					else
						cut = 2;
				}
				else if(colors.charAt(3) == 'Y' && !(colors.contains("R")))
					cut = 1;
				else if(colors.chars().filter(ch -> ch == 'B').count() == 1)
					cut = 1;
				else if(colors.chars().filter(ch -> ch == 'Y').count() > 1)
					cut = 4;
				else
					cut = 2;
				break;
			case 5:
				if(colors.charAt(4) == 'K' && ew.getSNDIG(ew.numSNDIGS()) % 2 == 1)
					cut = 4;
				else if(colors.chars().filter(ch -> ch == 'R').count() == 1 && colors.chars().filter(ch -> ch == 'Y').count() > 1)
					cut = 1;
				else if(!(colors.contains("K")))
					cut = 2;
				else
					cut = 1;
				break;
			default:
				if(!(colors.contains("Y")) && ew.getSNDIG(ew.numSNDIGS()) % 2 == 1)
					cut = 3;
				else if(colors.chars().filter(ch -> ch == 'Y').count() == 1 && colors.chars().filter(ch -> ch == 'W').count() > 1)
					cut = 4;
				else if(!(colors.contains("R")))
					cut = 6;
				else
					cut = 4;
				break;
		}
		JOptionPane.showMessageDialog(null, "Cut wire #" + cut);
	}
	private boolean valid(String i)
	{
		if(i.length() > 2 && i.length() < 7)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("RBYWK".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
}
