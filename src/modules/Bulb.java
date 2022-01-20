package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class Bulb 
{
	private final BombEdgework ew;
	private String souv;
	public Bulb(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{
		souv = "BUTTON PRESSES: ";
		String input = JOptionPane.showInputDialog("Red, Blue, Yellow,\nGreen, Purple, White\nOn, Off\nST - See-through\nOP - Opaque\nEnter the bulb's info (spaces):").toUpperCase();
		boolean v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Red, Blue, Yellow,\nGreen, Purple, White\nOn, Off\nST - See-through\nOP - Opaque\nEnter the bulb's info (spaces):").toUpperCase();
			v = valid(input);
		}
		String[] temp = input.split(" ");
		String color = "", opacity = "", light = "";
		for(int aa = 0; aa < 3; aa++)
		{
			switch(temp[aa])
			{
				case "ON":
				case "OFF":
					light = temp[aa].toUpperCase();
					break;
				case "OP":
				case "ST":
					opacity = temp[aa].toUpperCase();
					break;
				default:
					color = temp[aa].toUpperCase();
					break;
			}
		}
		String out = print(step1(color, opacity, light));
		JOptionPane.showMessageDialog(null, out);
		return souv;
	}
	private ArrayList<String> step1(String color, String opacity, String light)
	{
		ArrayList<String> instruct = new ArrayList<String>();
		if(light.equals("ON") && opacity.contentEquals("ST"))
		{
			instruct.add("Press I");
			souv = souv + "I ";
			return step2(instruct, "Press I", color);
		}
		else if(light.equals("ON") && opacity.equals("OP"))
		{
			instruct.add("Press O");
			souv = souv + "O ";
			return step3(instruct, "Press O", color);
		}
		else
		{
			instruct.add("Unscrew the bulb");
			return step4(instruct, color, opacity);
		}
	}
	private ArrayList<String> step2(ArrayList<String> instruct, String step1, String color)
	{
		if(color.equals("RED"))
		{
			instruct.add("Press I");
			souv = souv + "I ";
			instruct.add("Unscrew the bulb");
			return step5(instruct, "Press I");
		}
		else if(color.equals("WHITE"))
		{
			instruct.add("Press O");
			souv = souv + "O ";
			instruct.add("Unscrew the bulb");
			return step6(instruct, step1, "Press O");
		}
		else
		{
			instruct.add("Unscrew the bulb");
			return step7(instruct, color);
		}
	}
	private ArrayList<String> step3(ArrayList<String> instruct, String step1, String color)
	{
		if(color.equals("GREEN"))
		{
			instruct.add("Press I");
			souv = souv + "I ";
			instruct.add("Unscrew the bulb");
			return step6(instruct, step1, "Press I");
		}
		else if(color.equals("PURPLE"))
		{
			instruct.add("Press O");
			souv = souv + "O ";
			instruct.add("Unscrew the bulb");
			return step5(instruct, "Press O");
		}
		else
		{
			instruct.add("Unscrew the bulb");
			return step8(instruct, color);
		}
	}
	private ArrayList<String> step4(ArrayList<String> instruct, String color, String opacity)
	{
		if(ew.findInd("CAR") || ew.findInd("IND") || ew.findInd("MSA") || ew.findInd("SND"))
		{
			instruct.add("Press I");
			souv = souv + "I ";
			return step9(instruct, color, opacity);
		}
		else
		{
			instruct.add("Press O");
			souv = souv + "O ";
			return step10(instruct, color, opacity);
		}
	}
	private ArrayList<String> step5(ArrayList<String> instruct, String prev)
	{
		String out = instruct.get(0) + "\n";
		instruct.remove(0);
		if(JOptionPane.showConfirmDialog(null, out + "Did it turn off?") == 0)
		{
			instruct.add(prev);
			souv = souv + "" + prev.charAt(prev.length() - 1) + " ";
			instruct.add("Screw the bulb");
		}
		else
		{
			instruct.add("Press " + "IO".replace(prev.charAt(prev.length() - 1) + "", ""));
			souv = souv + "IO".replace(prev.charAt(prev.length() - 1) + "", "") + " ";
			instruct.add("Screw the bulb");
		}
		return instruct;
	}
	private ArrayList<String> step6(ArrayList<String> instruct, String step1, String step23)
	{
		String out = "";
		while(instruct.size() > 0)
		{
			out = out + "" + instruct.get(0) + "\n";
			instruct.remove(0);
			if(out.contains("I"))
				break;
		}
		if(JOptionPane.showConfirmDialog(null, out + "Did it turn off?") == 0)
		{
			instruct.add(step1);
			souv = souv + "" + step1.charAt(step1.length() - 1) + " ";
			instruct.add("Screw the bulb");
		}
		else
		{
			instruct.add(step23);
			souv = souv + "" + step23.charAt(step23.length() - 1) + " ";
			instruct.add("Screw the bulb");
		}
		return instruct;
	}
 	private ArrayList<String> step7(ArrayList<String> instruct, String color)
	{
		if(color.equals("GREEN"))
		{
			instruct.add("Press I");
			souv = souv + "I ";
			return step11(instruct, "SIG");
		}
		else if(color.equals("PURPLE"))
		{
			instruct.add("Press I");
			souv = souv + "I ";
			instruct.add("Screw the bulb");
			return step12(instruct);
		}
		else if(color.equals("BLUE"))
		{
			instruct.add("Press O");
			souv = souv + "O ";
			return step11(instruct, "CLR");
		}
		else
		{
			instruct.add("Press O");
			souv = souv + "O ";
			instruct.add("Screw the bulb");
			return step13(instruct);
		}
	}
	private ArrayList<String> step8(ArrayList<String> instruct, String color)
	{
		if(color.equals("WHITE"))
		{
			instruct.add("Press I");
			souv = souv + "I ";
			return step11(instruct, "FRQ");
		}
		else if(color.equals("RED"))
		{
			instruct.add("Press I");
			souv = souv + "I ";
			instruct.add("Screw the bulb");
			return step13(instruct);
		}
		else if(color.equals("YELLOW"))
		{
			instruct.add("Press O");
			souv = souv + "O ";
			return step11(instruct, "FRK");
		}
		else
		{
			instruct.add("Press O");
			souv = souv + "O ";
			instruct.add("Screw the bulb");
			return step12(instruct);
		}
	}
	private ArrayList<String> step9(ArrayList<String> instruct, String color, String opacity)
	{
		if(color.equals("BLUE"))
		{
			instruct.add("Press I");
			souv = souv + "I ";
			return step14(instruct, opacity);
		}
		else if(color.equals("GREEN"))
		{
			instruct.add("Press I");
			souv = souv + "I ";
			instruct.add("Screw the bulb");
			return step12(instruct);
		}
		else if(color.equals("YELLOW"))
		{
			instruct.add("Press O");
			souv = souv + "O ";
			return step15(instruct, opacity);
		}
		else if(color.equals("WHITE"))
		{
			instruct.add("Press O");
			souv = souv + "O ";
			instruct.add("Screw the bulb");
			return step13(instruct);
		}
		else if(color.equals("PURPLE"))
		{
			instruct.add("Screw the bulb");
			instruct.add("Press I");
			souv = souv + "I ";
			return step12(instruct);
		}
		else
		{
			instruct.add("Screw the bulb");
			instruct.add("Press O");
			souv = souv + "O ";
			return step13(instruct);
		}
	}
	private ArrayList<String> step10(ArrayList<String> instruct, String color, String opacity)
	{
		if(color.equals("PURPLE"))
		{
			instruct.add("Press I");
			souv = souv + "I ";
			return step14(instruct, opacity);
		}
		else if(color.equals("RED"))
		{
			instruct.add("Press I");
			souv = souv + "I ";
			instruct.add("Screw the bulb");
			return step13(instruct);
		}
		else if(color.equals("BLUE"))
		{
			instruct.add("Press O");
			souv = souv + "O ";
			return step15(instruct, opacity);
		}
		else if(color.equals("YELLOW"))
		{
			instruct.add("Press O");
			souv = souv + "O ";
			instruct.add("Screw the bulb");
			return step12(instruct);
		}
		else if(color.equals("GREEN"))
		{
			instruct.add("Screw the bulb");
			instruct.add("Press I");
			souv = souv + "I ";
			return step13(instruct);
		}
		else
		{
			instruct.add("Screw the bulb");
			instruct.add("Press O");
			souv = souv + "O ";
			return step12(instruct);
		}
	}
	private ArrayList<String> step11(ArrayList<String> instruct, String ind)
	{
		if(ew.findInd(ind))
		{
			instruct.add("Press I");
			souv = souv + "I ";
		}
		else
		{
			instruct.add("Press O");
			souv = souv + "O ";
		}
		instruct.add("Screw the bulb");
		return instruct;
	}
	private ArrayList<String> step12(ArrayList<String> instruct)
	{
		String out = print(instruct);
		instruct.clear();
		if(JOptionPane.showConfirmDialog(null, out + "Is the light on?") == 0)
		{
			instruct.add("Press I");
			souv = souv + "I ";
		}
		else
		{
			instruct.add("Press O");
			souv = souv + "O ";
		}
		return instruct;
	}
	private ArrayList<String> step13(ArrayList<String> instruct)
	{
		String out = print(instruct);
		instruct.clear();
		if(JOptionPane.showConfirmDialog(null, out + "Is the light on?") == 0)
		{
			instruct.add("Press O");
			souv = souv + "O ";
		}
		else
		{
			instruct.add("Press I");
			souv = souv + "I ";
		}
		return instruct;
	}
	private ArrayList<String> step14(ArrayList<String> instruct, String opacity)
	{
		if(opacity.equals("OP"))
		{
			instruct.add("Press I");
			souv = souv + "I ";
		}
		else
		{
			instruct.add("Press O");
			souv = souv + "O ";
		}
		instruct.add("Screw the bulb");
		return instruct;
	}
	private ArrayList<String> step15(ArrayList<String> instruct, String opacity)
	{
		if(opacity.equals("ST"))
		{
			instruct.add("Press I");
			souv = souv + "I ";
		}
		else
		{
			instruct.add("Press O");
			souv = souv + "O ";
		}
		instruct.add("Screw the bulb");
		return instruct;
	}
	private String print(ArrayList<String> instruct)
	{
		String out = "";
		for(int aa = 0; aa < instruct.size(); aa++)
			out = out + "" + instruct.get(aa) + "\n";
		return out;
	}
	private boolean valid(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 3)
		{
			boolean[] b = {false, false, false};
			for(int aa = 0; aa < 3; aa++)
			{
				switch(conv[aa])
				{
					case "OP":
					case "ST":
						b[0] = true;
						break;
					case "ON":
					case "OFF":
						b[1] = true;
						break;
					case "RED":
					case "BLUE":
					case "YELLOW":
					case "GREEN":
					case "PURPLE":
					case "WHITE":
						b[2] = true;
						break;
					default:
						return false;
				}
			}
			return (b[0] && b[1] && b[2]);
		}
		return false;
	}
}
