package modules;

import javax.swing.JOptionPane;

import start.BombConfig;
import start.BombEdgework;

public class CombinationLock 
{
	private final BombConfig  cf;
	private final BombEdgework ew;
	public CombinationLock(BombConfig c, BombEdgework e)
	{
		cf = c;
		ew = e;
	}
	public void run()
	{
		int[] code = new int[3];
		code[0] = ew.BAT();
		if(ew.numTF() > 0)
		{
			code[1] = 0;
			String input = JOptionPane.showInputDialog("Enter the last digit\nof each two-factor:");
			boolean v = v1(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the last digit\nof each two-factor:");
				v = v1(input);
			}
			for(int aa = 0; aa < input.length(); aa++)
				code[0] += ("0123456789".indexOf(input.charAt(aa)));
			input = JOptionPane.showInputDialog("Enter the 1st digit\nof each two-factor:");
			v = v1(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the 1st digit\nof each two-factor:");
				v = v1(input);
			}
			for(int aa = 0; aa < input.length(); aa++)
				code[1] += ("0123456789".indexOf(input.charAt(aa)));
			input = JOptionPane.showInputDialog("Enter the number\nof solved modules:");
			v = v2(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the number\nof solved modules:");
				v = v2(input);
			}
			code[1] += Integer.parseInt(input);
		}
		else
		{
			code[1] = cf.getNumberModules();
			String input = JOptionPane.showInputDialog("Enter the number\nof solved modules:");
			boolean v = v2(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the number\nof solved modules:");
				v = v2(input);
			}
			code[0] += (Integer.parseInt(input) + ew.getSNDIG(ew.numSNDIGS() - 1));
			code[1] += Integer.parseInt(input);
		}
		code[0] = code[0] % 20;
		code[1] = code[1] % 20;
		code[2] = (code[0] + code[1]) % 20;
		JOptionPane.showMessageDialog(null, "Turn right to " + code[0] + "\nTurn left to " + code[1] + "\nTurn right to " + code[2]);
	}
	private boolean v1(String i)
	{
		if(i.length() == ew.numTF())
			return v2(i);
		return false;
	}
	private boolean v2(String i)
	{
		for(int aa = 0; aa < i.length(); aa++)
		{
			if("0123456789".indexOf(i.charAt(aa)) < 0)
				return false;
		}
		return true;
	}
}
