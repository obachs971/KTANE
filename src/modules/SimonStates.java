package modules;

import javax.swing.JOptionPane;

public class SimonStates 
{
	private final String[] table =
		{
			"RBGY",
			"BYRG",
			"GRYB",
			"YGBR"
		};
	public String run()
	{
		String input = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nEnter the top left color:").toUpperCase();
		boolean v = v1(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nEnter the top left color:").toUpperCase();
			v = v1(input);
		}
		String order = table["RYGB".indexOf(input)];
		String pressed = "";
		String[] num = {"1st", "2nd", "3rd", "4th"};
		String souv = "";
		for(int aa = 0; aa < 4; aa++)
		{
			input = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nEnter the " + num[aa] + " flashing color(s):").toUpperCase();
			v = v2(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nEnter the " + num[aa] + " flashing color(s):").toUpperCase();
				v = v2(input);
			}
			souv = souv + "FLASH #" + (aa + 1) + ": " + input.toUpperCase() + "\n";
			switch(aa)
			{
				case 0:
					pressed = stage1(input, order);
					break;
				case 1:
					pressed = pressed + "" + stage2(input, order, pressed);
					break;
				case 2:
					pressed = pressed + "" + stage3(input, order, pressed);
					break;
				case 3:
					pressed = pressed + "" + stage4(input, order, pressed);
					break;
			}
			String out = "";
			for(int bb = 0; bb < pressed.length(); bb++)
				out = out + "" + pressed.charAt(bb) + " ";
			JOptionPane.showMessageDialog(null, "Press these color(s): " + out);
		}
		return souv;
	}
	private String stage1(String flash, String order)
	{
		if(flash.length() == 1)
			return flash.toUpperCase();
		else if(flash.length() == 2 && flash.contains("B"))
			return getPrior(flash, order, true);
		else if(flash.length() == 2)
			return "B";
		else if(flash.length() == 3 && flash.contains("R"))
			return getPrior(flash, order, false);
		else if(flash.length() == 3)
			return "R";
		else
			return order.substring(1, 2);
	}
	private String stage2(String flash, String order, String pressed)
	{
		if(flash.equals("RB") || flash.equals("BR"))
			return order.replace(flash.charAt(0) + "", "").replace(flash.charAt(1) + "", "").substring(0, 1);
		else if(flash.length() == 2)
			return order.replace(flash.charAt(0) + "", "").replace(flash.charAt(1) + "", "").substring(1);
		else if(flash.length() == 1 && !(flash.equals("B")))
			return "B";
		else if(flash.length() == 1)
			return "Y";
		else if(flash.length() == 4)
			return pressed.substring(0, 1);
		else
			return order.replace(flash.charAt(0) + "", "").replace(flash.charAt(1) + "", "").replace(flash.charAt(2) + "", "");
	}
	private String stage3(String flash, String order, String pressed)
	{
		if(flash.length() == 3 && (flash.contains(pressed.charAt(0) + "") || flash.contains(pressed.charAt(1) + "")))
			return getPrior(flash.replace(pressed.charAt(0) + "", "").replace(pressed.charAt(1) + "", ""), order, true);
		else if(flash.length() == 3)
			return getPrior(flash, order, true);
		else if(flash.length() == 2 && pressed.contains(flash.charAt(0) + "") && pressed.contains(flash.charAt(1) + ""))
			return order.replace(flash.charAt(0) + "", "").replace(flash.charAt(1) + "", "").substring(1);
		else if(flash.length() == 2)
			return pressed.substring(0, 1);
		else if(flash.length() == 1)
			return flash;
		else
			return order.substring(2, 3);
	}
	private String stage4(String flash, String order, String pressed)
	{
		if(pressed.charAt(0) != pressed.charAt(1) && pressed.charAt(0) != pressed.charAt(2) && pressed.charAt(1) != pressed.charAt(2))
			return order.replace(pressed.charAt(0) + "", "").replace(pressed.charAt(1) + "", "").replace(pressed.charAt(2) + "", "");
		else if(flash.length() == 3 && flash.replace(pressed.charAt(0) + "", "").replace(pressed.charAt(1) + "", "").replace(pressed.charAt(2) + "", "").length() == 1)
			return flash.replace(pressed.charAt(0) + "", "").replace(pressed.charAt(1) + "", "").replace(pressed.charAt(2) + "", "");
		else if(flash.length() >= 3)
			return order.substring(3);
		else if(flash.length() == 1)
			return flash;
		else
			return "G";
	}
	private String getPrior(String choice, String order, boolean high)
	{
		if(high)
		{
			for(int aa = 0; aa < 4; aa++)
			{
				if(choice.contains(order.charAt(aa) + ""))
					return (order.charAt(aa) + "");
			}
		}
		else
		{
			for(int aa = 3; aa >= 0; aa--)
			{
				if(choice.contains(order.charAt(aa) + ""))
					return (order.charAt(aa) + "");
			}
		}
		return "";
	}
	private boolean v1(String i)
	{
		switch(i)
		{
			case "R":
			case "B":
			case "G":
			case "Y":
				return true;
			default:
				return false;
		}
	}
	private boolean v2(String i)
	{
		if(i.length() > 0)
		{
			String stuff = "RBGY";
			for(int aa = 0; aa < i.length(); aa++)
			{
				if(!(stuff.contains(i.charAt(aa) + "")))
					return false;
				stuff = stuff.replace(i.charAt(aa) + "", "");
			}
			return true;
		}
		return false;
	}
}
