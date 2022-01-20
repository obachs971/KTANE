package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class Screw 
{
	private final BombEdgework ew;
	public Screw(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		int[] holes = {(ew.BAT() + ew.numUnlit()), (ew.getSNDIG(ew.numSNDIGS() - 1) + ew.numLit()), (ew.numPorts() + ew.BH())};
		for(int aa = 0; aa < 3; aa++)
		{
			while(holes[aa] >= 7)
				holes[aa] -= 6;
			if(holes[aa] >= 1)
				holes[aa]--;
		}
		if(holes[0] == 0)
			holes[0] = 1;
		for(int aa = 1; aa < 3; aa++)
		{
			if(holes[aa] == holes[aa - 1])
				holes[aa] = (holes[aa] + 1) % 6;
		}
		String colors = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nM - Magenta\nW - White\nEnter the colors:").toUpperCase();
		boolean v = valid(colors);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			colors = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nM - Magenta\nW - White\nEnter the colors:").toUpperCase();
			v = valid(colors);
		}
		String out = "";
		for(int aa = 0; aa < 3; aa++)
		{
			out = out + "Screw hole #" + (holes[aa] + 1) + "\n";
			if("RYG".indexOf(colors.charAt(holes[aa])) >= 0)
			{
				if(holes[aa] < 3)
				{
					if((holes[aa] + 1) == ew.BH())
						out = out + "Press the 4th position\n";
					else
					{
						int num = JOptionPane.showConfirmDialog(null, out + "Is the letter A in\nthe 1st or 3rd position?");
						out = "";
						if(num == 0)
							out = "Press the letter C\n";
						else if(ew.findInd("CLR") || ew.findInd("FRK") || ew.findInd("TRN"))
							out = "Press the 3rd position\n";
						else if(colors.substring(0, 3).contains("B"))
							out = "Press the 1st position\n";
						else
							out = "Press the letter B\n";
					}
				}
				else
				{
					int pos = (holes[aa] % 3) + 1;
					if(pos == ew.numPortTypes())
						out = out + "Press the 2nd position\n";
					else if(pos == ew.BAT())
						out = out + "Press the letter D\n";
					else if(colors.charAt(pos - 1) != 'W')
						out = out + "Press the letter A\n";
					else if((pos == 1 || pos == 3) && colors.charAt(4) == 'M')
						out = out + "Press the letter C\n";
					else if(pos == 2 && colors.substring(3).contains("M"))
						out = out + "Press the letter C\n";
					else
						out = out + "Press the 1st position";
				}
			}
			else
			{
				if(holes[aa] < 3)
				{
					if((holes[aa] + 1) == ew.numPortTypes())
						out = out + "Press the letter D\n";
					else
					{
						int num = JOptionPane.showConfirmDialog(null, out + "Is the letter C in\nthe 2nd or 4th position?");
						out = "";
						if(num == 0)
							out = "Press the letter B\n";
						else if(ew.findInd("CAR") || ew.findInd("FRQ") || ew.findInd("SND"))
							out = "Press the 4th position\n";
						else if(colors.substring(0, 3).contains("R"))
							out = "Press the 2nd position\n";
						else
							out = "Press the letter A\n";
					}
				}
				else
				{
					int pos = (holes[aa] % 3) + 1;
					if(pos == ew.numPlates())
						out = out + "Press the 2nd position\n";
					else if(pos == ew.numInd())
						out = out + "Press the letter A\n";
					else if((pos == 1 || pos == 3) && colors.charAt(4) == 'Y')
						out = out + "Press the letter C\n";
					else if(pos == 2 && colors.substring(3).contains("Y"))
						out = out + "Press the letter C\n";
					else if(colors.charAt(pos - 1) != 'G')
						out = out + "Press the letter D\n";
					else
						out = out + "Press the 4th position";
				}
			}
		}
		JOptionPane.showMessageDialog(null, out);
	}
	private boolean valid(String i)
	{
		if(i.length() == 6)
		{
			for(int aa = 0; aa < 6; aa++)
			{
				if("RBYGMW".indexOf(i.charAt(aa)) < 0)
					return false;
				for(int bb = aa + 1; bb < 6; bb++)
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
