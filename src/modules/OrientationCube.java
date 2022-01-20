package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class OrientationCube 
{
	private final BombEdgework ew;
	public OrientationCube(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{
		String eye = JOptionPane.showInputDialog("F - Front\nR - Right\nB - Back\nL - Left\nEnter the eye's position:").toUpperCase();
		boolean v = v1(eye);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			eye = JOptionPane.showInputDialog("F - Front\nR - Right\nB - Back\nL - Left\nEnter the eye's position:").toUpperCase();
			v = v1(eye);
		}
		String souv = "INITIAL POSITION: ";
		switch(eye)
		{
			case "F":
				souv = souv + "FRONT";
				break;
			case "R":
				souv = souv + "RIGHT";
				break;
			case "B":
				souv = souv + "BACK";
				break;
			case "L":
				souv = souv + "LEFT";
				break;
		}
		String[] instructionList;
		if(ew.numCharsInSN("R") > 0)
		{
			String[] temp = {"CW", "L CCW", "CCW", "L CW"};
			instructionList = temp;
		}
		else if(ew.findLit("TRN") || ew.findInd("CAR"))
		{
			String[] temp = {"CCW", "CW R", "CW", "CW L"};
			instructionList = temp;
		}
		else if(ew.findPort("PS/2") > 0 || JOptionPane.showConfirmDialog(null, "Are there any strikes?") == 0)
		{
			String[] temp = {"CCW L", "L CW", "CW L", "L CCW"};
			instructionList = temp;
		}
		else if(ew.numCharsInSN("78") > 0)
		{
			String[] temp = {"CW L L", "R CW R", "CCW L L", "R CCW R"};
			instructionList = temp;
		}
		else if(ew.BAT() >= 3 || eye.contentEquals("L"))
		{
			String[][] temp = 
				{
						{"CW", "R CW", "", "R CCW"},
						{"L CW", "CW", "L CCW", ""},
						{"", "L CW", "CW", "L CCW"},
						{"L CCW", "", "L CW", "CW"}
				};
			instructionList = temp["FRBL".indexOf(eye)];
			eye = JOptionPane.showInputDialog("F - Front\nR - Right\nB - Back\nL - Left\nPress CW then\nEnter the new eye position:").toUpperCase();
			v = v2(eye, instructionList);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				eye = JOptionPane.showInputDialog("F - Front\nR - Right\nB - Back\nL - Left\nPress CW then\nEnter the new eye position:").toUpperCase();
				v = v2(eye, instructionList);
			}
		}
		else
		{
			String[] temp = {"CCW", "CW R", "CW"};
			instructionList = temp;
		}
		JOptionPane.showMessageDialog(null, "Press " + instructionList["FRBL".indexOf(eye)] + " SET");
		return souv;
	}
	private boolean v1(String i)
	{
		switch(i)
		{
			case "F":
			case "R":
			case "B":
			case "L":
				return true;
			default:
				return false;
		}
	}
	private boolean v2(String i, String[] l)
	{
		return (v1(i) && l["FRBL".indexOf(i)].length() > 0);
	}
}
