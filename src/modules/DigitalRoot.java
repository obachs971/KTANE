package modules;

import javax.swing.JOptionPane;

public class DigitalRoot 
{
	public void run()
	{
		String input = JOptionPane.showInputDialog("Enter the 4 numbers\nin reading order:").replace(" ", "");
		boolean v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the 4 numbers\nin reading order:").replace(" ", "");
			v = valid(input);
		}
		int check = (input.charAt(3) - '0');
		int sum = 0;
		for(int i = 0; i < 3; i++)
			sum += (input.charAt(i) - '0');
		sum = ((sum - 1) % 9) + 1;
		if(sum == check)
			JOptionPane.showMessageDialog(null, "Press Yes");
		else
			JOptionPane.showMessageDialog(null, "Press No");
	}
	private boolean valid(String i)
	{
		if(i.length() == 4)
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
