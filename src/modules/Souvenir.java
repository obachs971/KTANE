package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Souvenir 
{
	private final ArrayList<String[]> souvList;
	public Souvenir(ArrayList<String[]> sl)
	{
		souvList = sl;
	}
	public void run()
	{
		String module;
		do
		{
			module = JOptionPane.showInputDialog("Enter the module on\nthe display (blank to exit):").toUpperCase();
			ArrayList<String> occur = new ArrayList<String>();
			for(int aa = 0; aa < souvList.size(); aa++)
			{
				if(souvList.get(aa)[0].equals(module))
					occur.add(souvList.get(aa)[1]);
			}
			if(occur.size() == 1)
			{
				JOptionPane.showMessageDialog(null, module + "\n------------------------------\n" + occur.get(0));
			}
			else if(occur.size() > 1)
			{
				String input = JOptionPane.showInputDialog("MAX: " + occur.size() + "\nEnter which solved module:");
				boolean v = valid(input, occur.size());
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog("MAX: " + occur.size() + "\nEnter which solved module:");
					v = valid(input, occur.size());
				}
				JOptionPane.showMessageDialog(null, module + "\n------------------------------\n" + occur.get(Integer.parseInt(input) - 1));
			}
			else if(module.length() > 0)
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
		}while(module.length() > 0);
		
	}
	private boolean valid(String i, int m)
	{
		for(int aa = 0; aa < i.length(); aa++)
		{
			if("0123456789".indexOf(i.charAt(aa)) < 0)
				return false;
		}
		int n = Integer.parseInt(i);
		if(n == 0 || n > m)
			return false;
		return true;
	}
}
