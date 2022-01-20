package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class SimonScreams 
{
	private final String[][] table1 =
		{
				{"FFC", "CEH", "HAF", "ECD", "DDE", "AHA"},
				{"AHF", "DFC", "ECH", "CDE", "FEA", "HAD"},
				{"DED", "ECF", "FHE", "HAA", "AFH", "CDC"},
				{"HCE", "ADA", "CFD", "DHH", "EAC", "FEF"},
				{"CAH", "FHD", "DDA", "AEC", "HCF", "EFE"},
				{"EDA", "HAE", "AEC", "FFF", "CHD", "DCH"}
		};
	private final String[] table2 =
		{
			"YOGRBP",
			"PYRBOG",
			"OGBPRY",
			"GBOYPR",
			"RPYOGB",
			"BRPGYO"
		};
	private final BombEdgework ew;
	private final int pt;
	private final boolean isSouv;
	public SimonScreams(BombEdgework e, int p, boolean s)
	{
		ew = e;
		pt = p;
		isSouv = s;
	}
	public String run()
	{
		ArrayList<Integer> rows = new ArrayList<Integer>();
		if(ew.numInd() >= 3)
			rows.add(0);
		if(ew.numPorts() >= 3)
			rows.add(1);
		if(ew.numSNDIGS() >= 3)
			rows.add(2);
		if(ew.numSNLETS() >= 3)
			rows.add(3);
		if(ew.BAT() >= 3)
			rows.add(4);
		if(ew.BH() >= 3)
			rows.add(5);
		String souv = "";
		if(pt == 1 && !(isSouv))
		{
			String flash = JOptionPane.showInputDialog("R - Red\nO - Orange\nY - Yellow\nG - Green\nB - Blue\nP - Purple\nEnter the 1st 3 flashing colors:").toUpperCase();
			boolean v = v3(flash);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				flash = JOptionPane.showInputDialog("R - Red\nO - Orange\nY - Yellow\nG - Green\nB - Blue\nP - Purple\nEnter the 1st 3 flashing colors:").toUpperCase();
				v = v3(flash);
			}
			for(int aa = 0; aa < 3; aa++)
			{
				String input = JOptionPane.showInputDialog("1. 3 adjacent colors flashed clockwise\n2. Color flashed, then an adjacent color, then the 1st again\n3. At most 1 color flash out of R, Y, and B\n4. 2 colors opposite each other didn't flash\n5. 2 adjacent colors flashed clockwise\n6. None of the above.");
				v = v4(input);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog("1. 3 adjacent colors flashed clockwise\n2. Color flashed, then an adjacent color, then the 1st again\n3. At most 1 color flash out of R, Y, and B\n4. 2 colors opposite each other didn't flash\n5. 2 adjacent colors flashed clockwise\n6. None of the above.");
					v = v4(input);
				}
				int row = Integer.parseInt(input) - 1;
				int col = "ACDEFH".indexOf(table1[row]["ROYGBP".indexOf(flash.charAt(aa))].charAt(aa));
				String press = "";
				for(int bb = 0; bb < rows.size(); bb++)
					press = press + "" + table2[rows.get(bb)].charAt(col) + " ";
				JOptionPane.showMessageDialog(null, "Press these colors: " + press);
			}
		}
		else
		{
			String flash = "", input;
			String colors = JOptionPane.showInputDialog("R - Red\nO - Orange\nY - Yellow\nG - Green\nB - Blue\nP - Purple\nEnter the colors in clockwise order:").toUpperCase();
			boolean v = v1(colors);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				colors = JOptionPane.showInputDialog("R - Red\nO - Orange\nY - Yellow\nG - Green\nB - Blue\nP - Purple\nEnter the colors in clockwise order:").toUpperCase();
				v = v1(colors);
			}
			for(int aa = 0; aa < 3; aa++)
			{
				if(aa == 0)
				{
					input = JOptionPane.showInputDialog("R - Red\nO - Orange\nY - Yellow\nG - Green\nB - Blue\nP - Purple\nEnter the flashing colors:").toUpperCase();
					v = v2(input, aa);
					while(!(v))
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						input = JOptionPane.showInputDialog("R - Red\nO - Orange\nY - Yellow\nG - Green\nB - Blue\nP - Purple\nEnter the flashing colors:").toUpperCase();
						v = v2(input, aa);
					}
				}
				else
				{
					input = JOptionPane.showInputDialog("R - Red\nO - Orange\nY - Yellow\nG - Green\nB - Blue\nP - Purple\nEnter the flashing colors after flash #" + flash.length() + ":").toUpperCase();
					v = v2(input, aa);
					while(!(v))
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						input = JOptionPane.showInputDialog("R - Red\nO - Orange\nY - Yellow\nG - Green\nB - Blue\nP - Purple\nEnter the flashing colors after flash #" + flash.length() + ":").toUpperCase();
						v = v2(input, aa);
					}
				}
				flash = flash + "" + input.toUpperCase();
				int col;
				if(rule1(colors, flash))
				{
					col = "ACDEFH".indexOf(table1[0]["ROYGBP".indexOf(flash.charAt(aa))].charAt(aa));
					souv = souv + "RULE #" + (aa + 1) + ": THREE ADJACENT COLORS FLASHING IN CLOCKWISE ORDER\n";
				}	
				else if(rule2(colors, flash))
				{	
					col = "ACDEFH".indexOf(table1[1]["ROYGBP".indexOf(flash.charAt(aa))].charAt(aa));
					souv = souv + "RULE #" + (aa + 1) + ": A COLOR FLASHING, THEN AN ADJACENT COLOR, THEN THE FIRST AGAIN\n";
				}
				else if(rule3(colors, flash))
				{
					col = "ACDEFH".indexOf(table1[2]["ROYGBP".indexOf(flash.charAt(aa))].charAt(aa));
					souv = souv + "RULE #" + (aa + 1) + ": AT MOST ONE COLOR FLASHING OUT OF RED, YELLOW, AND BLUE\n";
				}
				else if(rule4(colors, flash))
				{
					col = "ACDEFH".indexOf(table1[3]["ROYGBP".indexOf(flash.charAt(aa))].charAt(aa));
					souv = souv + "RULE #" + (aa + 1) + ": TWO COLORS OPPOSITE EACH OTHER THAT DIDN'T FLASH\n";
				}
				else if(rule5(colors, flash))
				{
					col = "ACDEFH".indexOf(table1[4]["ROYGBP".indexOf(flash.charAt(aa))].charAt(aa));
					souv = souv + "RULE #" + (aa + 1) + ": TWO ADJCENT COLORS FLASHING IN CLOCKWISE ORDER\n";
				}
				else
				{
					col = "ACDEFH".indexOf(table1[5]["ROYGBP".indexOf(flash.charAt(aa))].charAt(aa));
					souv = souv + "RULE #" + (aa + 1) + ": NONE\n";
				}
				String press = "";
				for(int bb = 0; bb < rows.size(); bb++)
					press = press + "" + table2[rows.get(bb)].charAt(col) + " ";
				JOptionPane.showMessageDialog(null, "Press these colors: " + press);
			}
			souv = souv + "COLORS FLASHED: ";
			for(int aa = 0; aa < flash.length(); aa++)
				souv = souv + "" + flash.charAt(aa) + " ";
		}
		return souv;
	}
	private boolean rule1(String colors, String flash)
	{
		for(int aa = 0; aa < flash.length() - 2; aa++)
		{
			int cursor = colors.indexOf(flash.charAt(aa));
			if(flash.charAt(aa + 1) == colors.charAt((cursor + 1) % 6) && flash.charAt(aa + 2) == colors.charAt((cursor + 2) % 6))
				return true;
		}
		return false;
	}
	private boolean rule2(String colors, String flash)
	{
		for(int aa = 0; aa < flash.length() - 2; aa++)
		{
			int cursor = colors.indexOf(flash.charAt(aa)) - 1;
			while(cursor < 0)
				cursor += 6;
			if((flash.charAt(aa + 1) == colors.charAt(cursor) || flash.charAt(aa + 1) == colors.charAt((cursor + 2) % 6)) && flash.charAt(aa) == flash.charAt(aa + 2))
				return true;
		}
		return false;
	}
	private boolean rule3(String colors, String flash)
	{
		boolean[] b = {false, false, false};
		for(int aa = 0; aa < flash.length(); aa++)
		{
			switch(flash.charAt(aa))
			{
				case 'R':
					b[0] = true;
					break;
				case 'B':
					b[1] = true;
					break;
				case 'Y':
					b[2] = true;
					break;
			}
		}
		int sum = 0;
		for(int aa = 0; aa < b.length; aa++)
		{
			if(b[aa])
				sum++;
		}
		return (sum <= 1);
	}
	private boolean rule4(String colors, String flash)
	{
		String temp = colors.toUpperCase();
		for(int aa = 0; aa < flash.length(); aa++)
			temp = temp.replace(flash.charAt(aa), '-');
		for(int aa = 0; aa < 3; aa++)
		{
			if(temp.charAt(aa) != '-' && temp.charAt(aa + 3) != '-')
				return true;
		}
		return false;
	}
	private boolean rule5(String colors, String flash)
	{
		for(int aa = 0; aa < flash.length() - 1; aa++)
		{
			int cursor = colors.indexOf(flash.charAt(aa));
			if(flash.charAt(aa + 1) == colors.charAt((cursor + 1) % 6))
				return true;
		}
		return false;
	}
	private boolean v1(String i)
	{
		if(i.length() == 6)
		{
			boolean[] b = {false, false, false, false, false, false};
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("ROYGBP".indexOf(i.charAt(aa)) < 0)
					return false;
				else
					b["ROYGBP".indexOf(i.charAt(aa))] = true;
			}
			return (b[0] && b[1] && b[2] && b[3] && b[4] && b[5]);
		}
		return false;
	}
	private boolean v2(String i, int aa)
	{
		if((aa == 0 && i.length() >= 3) || (aa > 0 && i.length() > 0))
		{
			for(int bb = 0; bb < i.length(); bb++)
			{
				if("ROYGBP".indexOf(i.charAt(bb)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
	private boolean v3(String i)
	{
		if(i.length() == 3)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("ROYGBP".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
	private boolean v4(String i)
	{
		switch(i)
		{
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
				return true;
			default:
				return false;
		}
	}
}
