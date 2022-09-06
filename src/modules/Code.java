package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class Code 
{
	private final BombEdgework ew;
	public Code(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{
		String input = JOptionPane.showInputDialog("Enter the 4 digit number:");
		boolean v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the 4 digit number:");
			v = valid(input);
		}
		int code = Integer.parseInt(input);
		if(ew.getSNDIG(0) == ew.getSNDIG(ew.numSNDIGS() - 1) && ew.BAT() == 0)
			code = Integer.parseInt(input);
		else if(ew.findInd("CLR"))
			code /= 8;
		else if(ew.numCharsInSN("XYZ") > 0)
			code /= 20;
		else if(ew.numPorts() >= 5)
			code /= 30;
		else if(ew.BAT() == 0)
			code /= 42;
		else if(ew.numLit() > ew.numUnlit())
			code /= 69;
		else
			code /= 3;
		JOptionPane.showMessageDialog(null, "Submit this number: " + code);
		return ("DISPLAYED NUMBER: " + input);
	}
	private boolean valid(String i)
	{
		if(i.length() == 4)
		{
			for(char c : i.toCharArray())
			{
				if("0123456789".indexOf(c) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
}
