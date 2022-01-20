package modules;

import javax.swing.JOptionPane;

public class Probing 
{
	public String run()
	{
		int[] sums = {0, 0, 0, 0}, order = {1, 2, 5, 6}, wireFreq = new int[6], freq = {0, 10, 22, 30, 40, 50, 60};
		String[] displays = new String[5];
		for(int aa = 0; aa < 5; aa++)
		{
			displays[aa] = JOptionPane.showInputDialog("Connect wires 1 and " + (aa + 2) + "\nEnter the display:").replace(" ", "").replace(",", "").replace("/", "").replace("-", "");
			boolean v = valid(displays[aa]);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				displays[aa] = JOptionPane.showInputDialog("Connect wires 1 and " + (aa + 2) + "\nEnter the display:").replace(" ", "").replace(",", "").replace("/", "").replace("-", "");
				v = valid(displays[aa]);
			}
			if(displays[aa].length() == 4)
				displays[aa] = displays[aa].charAt(0) + "" + displays[aa].charAt(2);
			if(displays[aa].length() == 2)
			{
				for(int bb = 0; bb < 2; bb++)
					sums["1256".indexOf(displays[aa].charAt(bb))]++;
			}
		}
		int best = sums[0], bestCur = 0;
		for(int aa = 1; aa < sums.length; aa++)
		{
			if(sums[aa] > best)
			{
				best = sums[aa];
				bestCur = aa;
			}
		}
		wireFreq[0] = order[bestCur];
		String souv = "WIRE #1: " + freq[wireFreq[0]];
		for(int aa = 0; aa < 5; aa++)
		{
			if(displays[aa].length() == 0)
				wireFreq[aa + 1] = order[bestCur];
			else
			{
				displays[aa] = displays[aa].replace((order[bestCur] + ""), "");
				wireFreq[aa + 1] = Integer.parseInt(displays[aa]);
			}
			souv = souv + "\nWIRE #" + (aa + 2) + ": " + freq[wireFreq[aa + 1]];
		}
		String red, blue;
		if(wireFreq[0] != 5)
			red = (getWire(wireFreq, 5) + "");
		else if(wireFreq[4] == 1)
			red = "5";
		else
			red = (getWire(wireFreq, 6) + "");
		if(wireFreq[4] != 1)
			blue = (getWire(wireFreq, 2) + "");
		else
			blue = (getWire(wireFreq, 6) + "");
		JOptionPane.showMessageDialog(null, "Connect red to wire #" + red + "\nConnect blue to wire #" + blue);
		return souv;
	}
	private int getWire(int[] wf, int mf)
	{
		for(int aa = 0; aa < wf.length; aa++)
		{
			if(wf[aa] == mf)
				return (aa + 1);
		}
		return -1;
	}
	private boolean valid(String i)
	{
		if(i.length() == 4)
		{
			String[] temp = {i.substring(0, 2), i.substring(2)};
			for(int aa = 0; aa < temp.length; aa++)
			{
				switch(temp[aa])
				{
					case "10":
					case "22":
					case "50":
					case "60":
						break;
					default:
						return false;
				}
			}
			return true;
		}
		else if(i.length() == 2)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("1256".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		else if(i.length() == 0)
			return true;
		return false;
	}
}
