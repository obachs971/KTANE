package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class LetterKeys 
{
	private final BombEdgework ew;
	public LetterKeys(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		String input = JOptionPane.showInputDialog("Enter the displayed number:");
		boolean v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the displayed number:");
			v = valid(input);
		}
		int number = Integer.parseInt(input);
		if(number == 69)
			JOptionPane.showMessageDialog(null, "Press D");
		else if(number % 6 == 0)
			JOptionPane.showMessageDialog(null, "Press A");
		else if(ew.BAT() >= 2 && number % 3 == 0)
			JOptionPane.showMessageDialog(null, "Press B");
		else if(ew.numCharsInSN("CE3") > 0 && number >= 22 && number <= 79)
			JOptionPane.showMessageDialog(null, "Press B");
		else if(ew.numCharsInSN("CE3") > 0)
			JOptionPane.showMessageDialog(null, "Press C");
		else if(number < 46)
			JOptionPane.showMessageDialog(null, "Press D");
		else
			JOptionPane.showMessageDialog(null, "Press A");
	}
	private boolean valid(String i)
	{
		if(i.length() > 0)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("0123456789".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
}
