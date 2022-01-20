package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class Neutralization 
{
	private final String[][] table =
		{
				{"OFF", "ON", "ON", "OFF"},
				{"ON", "OFF", "ON", "OFF"},
				{"ON", "ON", "OFF", "ON"},
				{"OFF", "OFF", "OFF", "ON"}
		};
	private final BombEdgework ew;
	public Neutralization(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{
		//Determining Acid
		String base, input = JOptionPane.showInputDialog("R - Red\n B - Blue\nY - Yellow\nG - Green\n5, 10, 15, 20\nEnter the color/volume:").toUpperCase().replace(" ", "");
		boolean v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("R - Red\n B - Blue\nY - Yellow\nG - Green\n5, 10, 15, 20\nEnter the color/volume:").toUpperCase().replace(" ", "");
			v = valid(input);
		}
		String acid = "H";
		int acidConc, row, col;
		String souv = "COLOR: ";
		switch(input.charAt(0))
		{
			case 'R':
				acid = acid + "BR";
				acidConc = 35;
				row = 0;
				souv = souv + "RED\n";
				break;
			case 'Y':
				acid = acid + "F";
				acidConc = 9;
				row = 1;
				souv = souv + "YELLOW\n";
				break;
			case 'G':
				acid = acid + "CL";
				acidConc = 17;
				row = 2;
				souv = souv + "GREEN\n";
				break;
			default:
				acid = acid + "I";
				acidConc = 53;
				row = 3;
				souv = souv + "BLUE\n";
				break;
		}
		int volume = Integer.parseInt(input.substring(1));
		souv = souv + "VOLUME: " + volume;
		
		//Determining Base
		if(ew.findInd("NSA") && ew.BAT() == 3)
			base = "NH3";
		else if(ew.findLit("CAR") || ew.findLit("FRQ") || ew.findLit("IND"))
			base = "KOH";
		else if(ew.numPorts() == 0 && ew.numCharsInSN("AEIOU") > 0)
			base = "LiOH";
		else if(ew.numIndWithChars("H" + acid) > 0)
			base = "KOH";
		else if(ew.BD() > ew.BA())
			base = "NH3";
		else if(acidConc < 20)
			base = "NAOH";
		else
			base = "LIOH";
		
		//Determining Acid Concentration
		int baseConc;
		switch(base)
		{
			case "NH3":
				acidConc -= 1;
				baseConc = 5;
				col = 0;
				break;
			case "LIOH":
				acidConc -= 3;
				baseConc = 5;
				col = 2;
				break;
			case "NAOH":
				acidConc -= 11;
				baseConc = 10;
				col = 3;
				break;
			default:
				acidConc -= 19;
				baseConc = 20;
				col = 1;
				break;
		}
		if(acid.equals("HI") || base.equals("LIOH") || base.equals("NAOH"))
			acidConc -= 4;
		if(acid.length() == (base.length() - 1))
			acidConc *= 3;
		if(acidConc < 0)
			acidConc *= -1;
		acidConc %= 10;
		if(acidConc == 0)
			acidConc = (volume * 2) / 5;
		
		//Determining Base Concentration
		if(ew.BH() > ew.numPortTypes() && ew.BH() > ew.numInd())
			baseConc = 5;
		else if(ew.numPortTypes() > ew.BH() && ew.numPortTypes() > ew.numInd())
			baseConc = 10;
		else if(ew.numInd() > ew.BH() && ew.numInd()> ew.numPortTypes())
			baseConc = 20;
		if((acid.equals("HI") && base.equals("KOH")) || (acid.equals("HCL") && base.equals("NH3")))
			baseConc = 20;
		
		//Determining Drop Count
		int drops = ((20 / baseConc) * volume * acidConc) / 10;
		
		//Determining Solubility
		String filter = table[row][col];
		
		//Output Information
		JOptionPane.showMessageDialog(null, "Base: " + base + "\nFilter: " + filter + "\nDrops: " + drops);
		return souv;
	}
	private boolean valid(String i)
	{
		if(i.length() > 1)
		{
			if("RYGB".indexOf(i.charAt(0)) < 0)
				return false;
			switch(i.substring(1))
			{
				case "5":
				case "10":
				case "15":
				case "20":
					return true;
			}
		}
		return false;
	}
}
