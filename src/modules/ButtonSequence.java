package modules;

import javax.swing.JOptionPane;

import misc.PlayType;

public class ButtonSequence 
{
	private String[][] table =
		{
				{"ASQU", "DHEX", "HCIR", "ACIR", "PSQU"},
				{"DCIR", "HHEX", "ASQU", "PCIR", "HHEX"},
				{"HCIR", "ASQU", "DHEX", "PSQU", "PHEX"},
				{"HHEX", "DSQU", "PHEX", "ACIR", "DSQU"}
		};
	private final PlayType playType;
	public ButtonSequence(PlayType pt)
	{
		playType = pt;	
	}
	public String run()
	{
		int[] RYBW = {0, 0, 0, 0};
		for(int aa = 0; aa < 12; aa++)
		{
			String input = JOptionPane.showInputDialog("Panel #" + ((aa / 3) + 1) + "\n(R)ed, (B)lue, (Y)ellow, (W)hite\n(Cir)cle, (Squ)are, (Hex)agon\nEnter the color, shape,\nand letter of Button #" + ((aa % 3) + 1) + ":").toUpperCase().replace(" ", "");
			boolean v = v1(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Panel #" + ((aa / 3) + 1) + "\n(R)ed, (B)lue, (Y)ellow, (W)hite\n(Cir)cle, (Squ)are, (Hex)agon\nEnter the color, shape,\nand letter of Button #" + ((aa % 3) + 1) + ":").toUpperCase().replace(" ", "");
				v = v1(input);
			}
			String noShape = input.replace("CIR", "").replace("SQU", "").replace("HEX", "");
			String shape = input.replace(noShape.substring(0, 1), "").replace(noShape.substring(1), "");
			String color = noShape.replace("A", "").replace("D", "").replace("H", "").replace("P", "");
			String letter = noShape.replace("R", "").replace("B", "").replace("Y", "").replace("W", "");
			int index = "RYBW".indexOf(color);
			if(table[index][RYBW[index] % 5].startsWith(letter) && table[index][RYBW[index] % 5].contains(shape))
				JOptionPane.showMessageDialog(null, hold());
			else if(table[index][RYBW[index] % 5].startsWith(letter) || table[index][RYBW[index] % 5].contains(shape))
				JOptionPane.showMessageDialog(null, "TAP the button");
			else
				JOptionPane.showMessageDialog(null, "SKIP the button");
			RYBW[index]++;
		} 
		return ("RED: " + RYBW[0] + "\nYELLOW: " + RYBW[1] + "\nBLUE: " + RYBW[2] + "\nWHITE: " + RYBW[3]);
	}
	private String hold()
	{
		if(playType == PlayType.EFM)
		{
			return "Blue - 2\nYellow - 3\nMagenta - 4\nCyan - 0\nWhite - 7\nHold and release when the\ncountdown timer has that\ndigit associated with the color";
		}
		else
		{
			int[] times = {2, 7, 3, 4, 0};
			String color = JOptionPane.showInputDialog("Y - Yellow\nB - Blue\nM - Magenta\nC - Cyan\nW - White\nHold the button\nEnter the color:").toUpperCase();
			boolean v = v2(color);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				color = JOptionPane.showInputDialog("Y - Yellow\nB - Blue\nM - Magenta\nC - Cyan\nW - White\nHold the button\nEnter the color:").toUpperCase();
				v = v2(color);
			}
			return "Release when the countdown\ntimer has a " + times["BWYMC".indexOf(color.substring(0, 1))] + " in any position";
		}
	}
	public boolean v1(String i)
	{
		if(i.length() == 5)
		{
			String test = "";
			String[] list = {"SQU", "CIR", "HEX"};
			for(int aa = 0; aa < list.length; aa++)
			{
				int index = i.indexOf(list[aa]);
				if(index >= 0)
				{
					test = i.replace(list[aa], "");
					break;
				}
			}
			if(test.length() == 2)
				return (("RBYW".indexOf(test.charAt(0)) >= 0 || "RBYW".indexOf(test.charAt(1)) >= 0) && ("PHAD".indexOf(test.charAt(0)) >= 0 || "PHAD".indexOf(test.charAt(1)) >= 0));
		}
		return false;
	}
	private boolean v2(String i)
	{
		switch(i)
		{
			case "BLUE":case "B":
			case "WHITE":case "W":
			case "YELLOW":case "Y":
			case "MAGENTA":case "M":
			case "CYAN":case "C":
				return true;
		}
		return false;
	}
}
