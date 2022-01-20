package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class BigCircle 
{
	private final String[][] table =
		{
				{"Red", "Yellow", "Blue"},
				{"Orange", "Green", "Magenta"},
				{"Blue", "Black", "Red"},
				{"Magenta", "White", "Orange"},
				{"Orange", "Blue", "Black"},
				{"Green", "Red", "White"},
				{"Magenta", "Yellow", "Black"},
				{"Red", "Orange", "Yellow"},
				{"Yellow", "Green", "Blue"},
				{"Blue", "Magenta", "Red"},
				{"Black", "White", "Green"},
				{"White", "Yellow", "Blue"}
		};
	private final BombEdgework ew;
	public BigCircle(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{
		//Start with unicorn
		String[] order;
		if(ew.BAT() == 5 && ew.BH() == 3 && ew.findInd("BOB"))
			order = table[("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(ew.getSNCHAR(0)) / 3)];
		else
		{
			//Indicators
			String[][] inds =
				{
						{"BOB", "CAR", "CLR"},
						{"FRK", "FRQ", "MSA", "NSA"},
						{"SIG", "SND", "TRN"}
				};
			int score = 0, indSum = 0;
			for(int aa = 0; aa < inds.length; aa++)
			{
				for(int bb = 0; bb < inds[aa].length; bb++)
				{
					if(ew.findLit(inds[aa][bb]))
					{
						score += (1 + aa);
						indSum++;
					}
					else if(ew.findUnlit(inds[aa][bb]))
					{
						score -= (1 + aa);
						indSum++;
					}
				}
			}
			if(ew.findInd("IND"))
				indSum++;
			//Solves
			String input = JOptionPane.showInputDialog("Enter the number of solves:");
			boolean v = v1(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the number of solves:");
				v = v1(input);
			}
			score += (Integer.parseInt(input) * 3);
			//Batteries
			if(ew.BAT() % 2 == 1)
				score += 4;
			else
				score -= 4;
			//Parallel/Serial
			int num = ew.findPorts(new String[] {"PARALLEL", "SERIAL"});
			score -= (num * 4);
			num = num - ew.findPort("PARALLEL");
			score += (num * 5);
			//DVI-D/RCA
			num = ew.findPorts(new String[] {"DVI-D", "RCA"});
			score += (num * 4);
			num = num - ew.findPort("DVI-D");
			score -= (num * 5);
			//Special Indicators
			num = ew.numInd() - indSum;
			score += (num * 6);
			//Special Ports
			num = ew.findPort("DVI-D") + ew.findPort("PS/2") + ew.findPort("RCA") + ew.findPort("RJ-45") + ew.findPort("PARALLEL") + ew.findPort("SERIAL");
			num = ew.numPorts() - num;
			score -= (num * 6);
			//Two Factors
			if(ew.numTF() > 0)
			{
				input = JOptionPane.showInputDialog("Enter the least sig fig\nof each two factor code:");
				v = v2(input, ew.numTF());
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog("Enter the least sig fig\nof each two factor code:");
					v = v2(input, ew.numTF());
				}
				for(int aa = 0; aa < input.length(); aa++)
					score += "0123456789".indexOf(input.charAt(aa));
			}
			if(score < 0)
				score *= (-1);
			score = score % 10;
			String tempSN = ew.getSN() + "" + ew.getSNCHAR(4) + "" + ew.getSNCHAR(3) + "" + ew.getSNCHAR(2) + "" + ew.getSNCHAR(1);
			order = table[("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(tempSN.charAt(score)) / 3)];
		}
		String input = JOptionPane.showInputDialog("CW - Clockwise\nCCW - Counter-Clockwise\nEnter the circle's rotation:").toUpperCase(), souv;
		boolean v = v3(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("CW - Clockwise\nCCW - Counter-Clockwise\nEnter the circle's rotation:").toUpperCase();
			v = v3(input);
		} 
		if(input.equals("CW"))
		{
			JOptionPane.showMessageDialog(null, "Press these colors:\n" + order[0] + ", " + order[1] + ", " + order[2]);
			souv = "COLORS: " + order[0].toUpperCase() + " " + order[1].toUpperCase() + " " + order[2].toUpperCase();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Press these colors:\n" + order[2] + ", " + order[1] + ", " + order[0]);
			souv = "COLORS: " + order[2].toUpperCase() + " " + order[1].toUpperCase() + " " + order[0].toUpperCase();
		}
		return souv;
	}
	private boolean v1(String i)
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
	private boolean v2(String i, int l)
	{
		if(i.length() == l)
			return v1(i);
		return false;
	}
	private boolean v3(String i)
	{
		switch(i)
		{
			case "CW":
			case "CCW":
				return true;
			default:
				return false;
		}
	}
}
