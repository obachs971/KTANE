package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class Microcontroller 
{
	private final BombEdgework ew;
	private final int playType;
	private final boolean isSouv;
	public Microcontroller(BombEdgework e, int pt, boolean s)
	{
		ew = e;
		playType = pt;
		isSouv = s;
	}
	public String run()
	{
		//Getting controller's type/size
		String colors, input = JOptionPane.showInputDialog("Enter the controller's\ntype/size (spaces):").toUpperCase();
		boolean v = v1(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the controller's\ntype/size (spaces):").toUpperCase();
			v = v1(input);
		}
		String[] typeSize = input.split(" ");
		//Getting the controller's numbers
		input = JOptionPane.showInputDialog("Enter the 2nd and last digit of\nthe controller's serial number:");
		v = v2(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR");
			input = JOptionPane.showInputDialog("Enter the 2nd and last digit of\nthe controller's serial number:");
			v = v2(input);
		}
		int nums = Integer.parseInt(input);
		//Figuring out what are the component color codes
		if(nums % 10 == 1 || nums % 10 == 4)
			colors = "YMGBRW";
		else if(ew.findLit("SIG") || ew.findPort("RJ-45") > 0)
			colors = "YRMGBW";
		else if(ew.numCharsInSN("CLRX18") > 0)
			colors = "RMGBYW";
		else if((nums / 10) == ew.BAT())
			colors = "RBYGMW";
		else
			colors = "GRYBMW";
		//Get the color order
		if("6810".indexOf(typeSize[0]) >= 0)
			colors = getColors(typeSize[1], typeSize[0], colors);
		else
			colors = getColors(typeSize[0], typeSize[1], colors);
		String souv = "PIN ORDER: ";
		if(isSouv || playType == 1)
		{
			ArrayList<String> remain = new ArrayList<String>();
			for(int aa = 0; aa < colors.length(); aa++)
				remain.add((aa + 1) + "");
			String[] colorOrder = {"RED", "BLUE", "YELLOW", "GREEN", "MAGENTA", "WHITE"};
			input = JOptionPane.showInputDialog("Enter the pin number:");
			while(!(remain.contains(input)))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the pin number:");
			}
			for(int aa = 0; aa < colors.length() - 1; aa++)
			{
				String pos = input.toUpperCase();
				remain.remove(pos);
				if(remain.size() == 1)
					JOptionPane.showMessageDialog(null, "Set pin #" + pos + " to " + colorOrder["RBYGMW".indexOf(colors.charAt(Integer.parseInt(pos) - 1))] + "\nSet pin #" + remain.get(0) + " to " + colorOrder["RBYGMW".indexOf(colors.charAt(Integer.parseInt(remain.get(0)) - 1))]);
				else
				{
					input = JOptionPane.showInputDialog("Set pin #" + pos + " to " + colorOrder["RBYGMW".indexOf(colors.charAt(Integer.parseInt(pos) - 1))] + "\nEnter the new pin number:");
					while(!(remain.contains(input)))
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						input = JOptionPane.showInputDialog("Set pin #" + pos + " to " + colorOrder["RBYGMW".indexOf(colors.charAt(Integer.parseInt(pos) - 1))] + "\nEnter the new pin number:");
					}
				}
				souv = souv + "" + pos + ", ";
			}
			souv = souv + "" + remain.get(0);
		}
		else
		{
			input = JOptionPane.showInputDialog("TL, TR, BL, BR\nEnter the white dot's position").toUpperCase();
			String out = "";
			switch(input)
			{
				case "TL":
					for(int aa = 0; aa < colors.length() / 2; aa++)
						out = out + "" + colors.charAt(aa) + " ";
					out = out + "\n";
					for(int bb = colors.length() - 1; bb >= colors.length() / 2; bb--)
						out= out + "" + colors.charAt(bb) + " "; 
					break;
				case "TR":
					for(int aa = (colors.length() / 2) - 1; aa >= 0; aa--)
						out = out + "" + colors.charAt(aa) + " ";
					out = out + "\n";
					for(int bb = colors.length() / 2; bb < colors.length(); bb++)
						out= out + "" + colors.charAt(bb) + " "; 
					break;
				case "BL":
					for(int aa = colors.length() - 1; aa >= colors.length() / 2; aa--)
						out = out + "" + colors.charAt(aa) + " ";
					out = out + "\n";
					for(int bb = 0; bb < colors.length() / 2; bb++)
						out= out + "" + colors.charAt(bb) + " "; 
					break;
				case "BR":
					for(int aa = colors.length() / 2; aa < colors.length(); aa++)
						out = out + "" + colors.charAt(aa) + " ";
					out = out + "\n";
					for(int bb = (colors.length() / 2) - 1; bb >= 0; bb--)
						out= out + "" + colors.charAt(bb) + " "; 
					break;
			}
			JOptionPane.showMessageDialog(null, "Set the controller\nto these colors:\n\n" + out);
		}
		return souv;
	}
	private String getColors(String type, String size, String colors)
	{
		int VCC = 0, AIN = 1, DIN = 2, PWM = 3, RST = 4, GND = 5;
		switch(type)
		{
			case "STRK":
				switch(size)
				{
					case "6":
						colors = colors.charAt(AIN) + "" + colors.charAt(VCC) + "" + colors.charAt(RST) + "" + colors.charAt(DIN) + "" + colors.charAt(PWM) + "" + colors.charAt(GND);
						break;
					case "8":
						colors = colors.charAt(AIN) + "" + colors.charAt(PWM) + "" + colors.charAt(GND) + "" + colors.charAt(DIN) + "" + colors.charAt(VCC) + "" + colors.charAt(GND) + "" + colors.charAt(RST) + "" + colors.charAt(GND);
						break;
					case "10":
						colors = colors.charAt(GND) + "" + colors.charAt(GND) + "" + colors.charAt(GND) + "" + colors.charAt(GND) + "" + colors.charAt(AIN) + "" + colors.charAt(DIN) + "" + colors.charAt(GND) + "" + colors.charAt(VCC) + "" + colors.charAt(RST) + "" + colors.charAt(PWM);
						break;
					default:
						return "";
				}
				return colors;
			case "LEDS":
				switch(size)
				{
					case "6":
						colors = colors.charAt(PWM) + "" + colors.charAt(RST) + "" + colors.charAt(VCC) + "" + colors.charAt(DIN) + "" + colors.charAt(AIN) + "" + colors.charAt(GND);
						break;
					case "8":
						colors = colors.charAt(PWM) + "" + colors.charAt(DIN) + "" + colors.charAt(VCC) + "" + colors.charAt(GND) + "" + colors.charAt(AIN) + "" + colors.charAt(GND) + "" + colors.charAt(RST) + "" + colors.charAt(GND);
						break;
					case "10":
						colors = colors.charAt(PWM) + "" + colors.charAt(AIN) + "" + colors.charAt(DIN) + "" + colors.charAt(GND) + "" + colors.charAt(GND) + "" + colors.charAt(GND) + "" + colors.charAt(GND) + "" + colors.charAt(RST) + "" + colors.charAt(VCC) + "" + colors.charAt(GND);
						break;
					default:
						return "";
				}
				return colors;
			case "CNTD":
				switch(size)
				{
					case "6":
						colors = colors.charAt(GND) + "" + colors.charAt(AIN) + "" + colors.charAt(PWM) + "" + colors.charAt(VCC) + "" + colors.charAt(DIN) + "" + colors.charAt(RST);
						break;
					case "8":
						colors = colors.charAt(PWM) + "" + colors.charAt(GND) + "" + colors.charAt(GND) + "" + colors.charAt(VCC) + "" + colors.charAt(AIN) + "" + colors.charAt(GND) + "" + colors.charAt(DIN) + "" + colors.charAt(RST);
						break;
					case "10":
						colors = colors.charAt(PWM) + "" + colors.charAt(DIN) + "" + colors.charAt(AIN) + "" + colors.charAt(GND) + "" + colors.charAt(GND) + "" + colors.charAt(VCC) + "" + colors.charAt(GND) + "" + colors.charAt(GND) + "" + colors.charAt(RST) + "" + colors.charAt(GND);
						break;
					default:
						return "";
				}
				return colors;
			case "EXPL":
				switch(size)
				{
					case "6":
						colors = colors.charAt(PWM) + "" + colors.charAt(VCC) + "" + colors.charAt(RST) + "" + colors.charAt(AIN) + "" + colors.charAt(DIN) + "" + colors.charAt(GND);
						break;
					case "8":
						colors = colors.charAt(AIN) + "" + colors.charAt(GND) + "" + colors.charAt(RST) + "" + colors.charAt(GND) + "" + colors.charAt(VCC) + "" + colors.charAt(GND) + "" + colors.charAt(DIN) + "" + colors.charAt(PWM);
						break;
					case "10":
						colors = colors.charAt(RST) + "" + colors.charAt(DIN) + "" + colors.charAt(VCC) + "" + colors.charAt(GND) + "" + colors.charAt(GND) + "" + colors.charAt(GND) + "" + colors.charAt(AIN) + "" + colors.charAt(GND) + "" + colors.charAt(PWM) + "" + colors.charAt(GND);
						break;
					default:
						return "";
				}
				return colors;
		}
		return "";
	}
	//Validation for controller's type/size
	public boolean v1(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 2)
		{
			if(getColors(conv[0], conv[1], "RBYGMW").length() > 0)
				return true;
			else if(getColors(conv[1], conv[0], "RBYGMW").length() > 0)
				return true;
		}
		return false;
	}
	public boolean v2(String i)
	{
		if(i.length() == 2)
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
