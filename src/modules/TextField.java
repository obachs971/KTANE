package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class TextField 
{
	private final BombEdgework ew;
	public TextField(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{
		String letter = JOptionPane.showInputDialog("Enter the letter:").toUpperCase();
		String table = getTable(letter);
		while(table.length() == 0)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			letter = JOptionPane.showInputDialog("Enter the letter:").toUpperCase();
			table = getTable(letter);
		}
		JOptionPane.showMessageDialog(null, "Press these positions:\n" + table);
		return ("DISPLAYED LETTER: " + letter.toUpperCase());
	}
	private String getTable(String letter)
	{
		switch(letter)
		{
			case "A":
				if(ew.findLit("CLR"))
					return "B1\n2,1";
				else if(ew.BAT() >= 3)
					return "B1 D3\n2,1 4,3";
				else if(ew.BAT() == 1)
					return "A1 A2 A3 D3\n1,1 1,2 1,3 4,3";
				else if(ew.findLit("FRK"))
					return "A2\n1,2";
				else
					return "D1\n4,1";
			case "B":
				if(ew.BAT() == 0)
					return "B1 B2\n2,1 2,2";
				else if(ew.getSNDIG(ew.numSNDIGS() - 1) % 2 == 1)
					return "A1 C1 D1\n1,1 3,1 4,1";
				else if(ew.findPort("SERIAL") == 0)
					return "A3 B1 C3\n1,3 2,1 3,3";
				else if(ew.findLit("TRN"))
					return "C2 C3\n3,2 3,3";
				else
					return "C2 D1\n3,2 4,1";
			case "C":
				if(ew.findPort("DVI-D") > 0)
					return "B3 D3\n2,3 4,3";
				else if(ew.BAT() == 2)
					return "B1 D3\n2,1 4,3";
				else if(ew.numCharsInSN("AEIOU") == 0)
					return "A1 D2\n1,1 4,2";
				else if(ew.findLit("CAR"))
					return "A2 C3\n1,2 3,3";
				else
					return "B2 C1 D2\n2,2 3,1 4,2";
			case "D":
				if(ew.findPort("PARALLEL") > 0)
					return "A1\n1,1";
				else if(ew.BAT() <= 1)
					return "B2\n2,2";
				else if(ew.findLit("SIG"))
					return "A1 A2\n1,1 1,2";
				else if(ew.findPort("PS/2") == 0)
					return "A3\n1,3";
				else
					return "A3 B2 D2\n1,3 2,2 4,2";
			case "E":
				if(ew.BAT() <= 2)
					return "B3\n2,3";
				else if(ew.findPort("RCA") == 0)
					return "A2 B1 C3\n1,2 2,1 3,3";
				else if(ew.findLit("BOB"))
					return "A1\n1,1";
				else if(ew.findPort("RJ-45") > 0)
					return "B3 D2\n2,3 4,2";
				else
					return "B3 D1\n2,3 4,1";
			case "F":
				if(ew.findPort("SERIAL") == 0)
					return "B2\n2,2";
				else if(ew.numCharsInSN("AEIOU") > 0)
					return "A3 B2 B3 C1\n1,3 2,2 2,3 3,1";
				else if(ew.findLit("IND"))
					return "B3 C2\n2,3 3,2";
				else if(ew.getSNDIG(ew.numSNDIGS() - 1) % 2 == 0)
					return "C1 C2 D2\n3,1 3,2 4,2";
				else
					return "C2\n3,2";
		}
		return "";
	}
}
