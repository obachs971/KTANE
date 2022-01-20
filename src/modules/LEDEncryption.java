package modules;

import javax.swing.JOptionPane;

public class LEDEncryption 
{
	private final int pt;
	public LEDEncryption(int p)
	{
		pt = p;
	}
	public String run()
	{
		String souv = "";
		if(pt == 1)
		{
			for(int aa = 0; aa < 5; aa++)
			{
				String color = JOptionPane.showInputDialog("Red, Orange, Yellow\nGreen, Blue, Purple\nEnter the color for stage #" + (aa + 1) + ":").toUpperCase();
				boolean v = v1(color);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					color = JOptionPane.showInputDialog("Red, Orange, Yellow\nGreen, Blue, Purple\nEnter the color for stage #" + (aa + 1) + ":").toUpperCase();
					v = v1(color);
				}
				if(color.length() == 0)
					break;
				int mult = "--RGBYPO".indexOf(color.charAt(0));
				String letters = JOptionPane.showInputDialog("Enter the paired letters in diagonal order:").toUpperCase();
				v = v2(letters.charAt(0) + "" + letters.substring(2) + "" + letters.charAt(1), mult);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					letters = JOptionPane.showInputDialog("Enter the paired letters in diagonal order:").toUpperCase();
					v = v2(letters.charAt(0) + "" + letters.substring(2) + "" + letters.charAt(1), mult);
				}
				char press = getAnswer(letters.charAt(0) + "" + letters.substring(2) + "" + letters.charAt(1), mult);
				JOptionPane.showMessageDialog(null, "Press " + press);
				souv = souv + "BUTTON PRESS #" + (aa + 1) + ": " + press + "\n";
			}
		}
		else
		{
			String colors = JOptionPane.showInputDialog("R - Red\nO - Orange\nY - Yellow\nG - Green\nB - Blue\nP - Purple\nEnter the LED colors:").toUpperCase();
			boolean v = v3(colors);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				colors = JOptionPane.showInputDialog("R - Red\nO - Orange\nY - Yellow\nG - Green\nB - Blue\nP - Purple\nEnter the LED colors:").toUpperCase();
				v = v3(colors);
			}
			for(int aa = 0; aa < colors.length(); aa++)
			{
				int mult = "--RGBYPO".indexOf(colors.charAt(aa));
				String letters = JOptionPane.showInputDialog("Enter the letters in reading order:").toUpperCase();
				v = v2(letters, mult);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					letters = JOptionPane.showInputDialog("Enter the letters in reading order:").toUpperCase();
					v = v2(letters, mult);
				}
				char press = getAnswer(letters, mult);
				JOptionPane.showMessageDialog(null, "Press " + getAnswer(letters, mult));
				souv = souv + "BUTTON PRESS #" + (aa + 1) + ": " + press + "\n";
			}
		}
		return souv;
	}
	private char getAnswer(String l, int m)
	{
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for(int aa = 0; aa < 4; aa++)
		{
			int num = (alpha.indexOf(l.charAt(aa)) * m) % 26;
			if(num == alpha.indexOf(l.charAt(3 - aa)))
				return l.charAt(aa);
		}
		return '-';
	}
	private boolean v1(String i)
	{
		switch(i)
		{
			case "":
			case "RED":
			case "R":
			case "GREEN":
			case "G":
			case "BLUE":
			case "B":
			case "YELLOW":
			case "Y":
			case "PURPLE":
			case "P":
			case "ORANGE":
			case "O":
				return true;
			default:
				return false;
		}
	}
	private boolean v2(String i, int m)
	{
		if(i.length() == 4)
		{
			for(int aa = 0; aa < 4; aa++)
			{
				if("ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(i.charAt(aa)) < 0)
					return false;
				for(int bb = aa + 1; bb < 4; bb++)
				{
					if(i.charAt(aa) == i.charAt(bb))
						return false;
				}
			}
			if(getAnswer(i, m) == '-')
				return false;
			return true;
		}
		return false;
	}
	private boolean v3(String i)
	{
		System.out.println(i);
		if(i.length() >= 2 && i.length() <= 5)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("RGBYPO".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
}
