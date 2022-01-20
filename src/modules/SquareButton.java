package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class SquareButton 
{
	private final BombEdgework ew;
	private final int pt;
	public SquareButton(BombEdgework e, int p)
	{
		ew = e;
		pt = p;
	}
	public void run()
	{
		String color = JOptionPane.showInputDialog("Blue, Yellow, White, Gray\nEnter the button's color:").toUpperCase();
		boolean v = v1(color);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			color = JOptionPane.showInputDialog("Blue, Yellow, White, Gray\nEnter the button's color:").toUpperCase();
			v = v1(color);
		}
		String label = JOptionPane.showInputDialog("Enter the button's label:").toUpperCase();
		v = v2(label);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			label = JOptionPane.showInputDialog("Enter the button's label:").toUpperCase();
			v = v2(label);
		}
		if(color.equals("BLUE") && ew.BA() > ew.BD())
			hold();
		else if((color.equals("YELLOW") || color.equals("BLUE")) && label.length() >= ew.getLargestSNDIG())
			JOptionPane.showMessageDialog(null, "Tap the button");
		else if((color.equals("YELLOW") || color.equals("BLUE")) && (label.equals("PURPLE") || label.equals("JADE") || label.equals("MAROON") || label.equals("INDIGO")))
			hold();
		else if(label.length() == 0)
			JOptionPane.showMessageDialog(null, "Tap the button when the\ntwo seconds digits match");
		else if(!(color.equals("GRAY")) && label.length() > ew.numLit())
			JOptionPane.showMessageDialog(null, "Tap the button");
		else if(ew.numUnlit() >= 2 && ew.numCharsInSN("AEIOU") > 0)
			JOptionPane.showMessageDialog(null, "Tap the button");
		else
			hold();
	}
	private void hold()
	{
		if(pt == 0)
		{
			String input = JOptionPane.showInputDialog("Enter the remaining minutes:");
			boolean v = v3(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the remaining minutes:");
				v = v3(input);
			}
			int sec = (Integer.parseInt(input) * 60);
			String CS = "52 43 34 25 16 07", OS = "58 49 30 21 12 03", GS = "50 41 32 23 14 05", CF = "", OF = "59 53 47 43 41 37 31 29 23 19 17 13 11 07 05 03 02 00", GF = "59 56 52 47 43 39 38 34 30 25 21 16 12 07 03";
			for(int aa = 59; aa >= 0; aa--)
			{
				if((sec + aa) % 7 == 0)
					CF = CF + "" + (aa) + " ";
			}
			JOptionPane.showMessageDialog(null, "CS: " + CS + "\nOS: " + OS + "\nGS: " + GS + "\nCF: " + CF + "\nOF: " + OF + "\nGF: " + GF);
		}
		else
		{
			String input = JOptionPane.showInputDialog("C - Cyan\nO - Orange\nG - Green\nS - Solid\nF - Flashing\nEnter the color/effect:").toUpperCase();
			boolean v = v4(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("C - Cyan\nO - Orange\nG - Green\nS - Solid\nF - Flashing\nEnter the color/effect:").toUpperCase();
				v = v4(input);
			}
			switch(input)
			{
				case "CS":
					JOptionPane.showMessageDialog(null, "Release when the two\nseconds digits add up to 7:\n07 16 25 34 43 52");
					break;
				case "OS":
					JOptionPane.showMessageDialog(null, "Release when the two seconds\ndigits add up to 3 or 13:\n58 49 30 21 12 03");
					break;
				case "GS":
					JOptionPane.showMessageDialog(null, "Release when the two\nseconds digits add up to 5:\n50 41 32 23 14 05");
					break;
				case "CF":
					input = JOptionPane.showInputDialog("Enter the remaining minutes:");
					
					int sec = (Integer.parseInt(input) * 60);
					String CF = "";
					for(int aa = 59; aa >= 0; aa--)
					{
						if((sec + aa) % 7 == 0)
							CF = CF + "" + (aa) + " ";
					}
					JOptionPane.showMessageDialog(null, "Release when the seconds\nremaining is a multiple of 7:\n" + CF);
					break;
				case "OF":
					JOptionPane.showMessageDialog(null, "Release when the number of\nseconds displayed is either prime or 0:\n59 53 47 43 41 37 31 29 23 19 17 13 11 07 05 03 02 00");
					break;
				case "GF":
					JOptionPane.showMessageDialog(null, "Release one second after the two\nseconds digits add up to a multiple of 4:\n59 56 52 47 43 39 38 34 30 25 21 16 12 07 03");
					break;
			}
		}
	}
	private boolean v1(String i)
	{
		switch(i)
		{
			case "BLUE":
			case "YELLOW":
			case "WHITE":
			case "GRAY":
				return true;
			default:
				return false;
		}
	}
	private boolean v2(String i)
	{
		switch(i)
		{
			case "PURPLE":
			case "JADE":
			case "MAROON":
			case "INDIGO":
			case "ELEVATE":
			case "RUN":
			case "DETONATE":
			case "":
				return true;
			default:
				return false;
		}
	}
	private boolean v3(String i)
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
	private boolean v4(String i)
	{
		switch(i)
		{
			case "CS":
			case "OS":
			case "GS":
			case "CF":
			case "OF":
			case "GF":
				return true;
			default:
				return false;
		}
	}
}
