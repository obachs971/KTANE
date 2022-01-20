package modules;
import javax.swing.JOptionPane;
import start.BombEdgework;

public class TwoBits 
{
	private final String[][] chart =
		{
				{"KB", "DK", "GV", "TK", "PV", "KP", "BV", "VT", "PZ", "DT"},
				{"EE", "ZK", "KE", "CK", "ZP", "PP", "TP", "TG", "PD", "PT"},
				{"TZ", "EB", "EC", "CC", "CZ", "ZV", "CV", "GC", "BT", "GT"},
				{"BZ", "PK", "KZ", "KG", "VD", "CE", "VB", "KD", "GG", "DG"},
				{"PB", "VV", "GE", "KV", "DZ", "PE", "DB", "CD", "TD", "CB"},
				{"GB", "TV", "KK", "BG", "BP", "VP", "EP", "TT", "ED", "ZG"},
				{"DE", "DD", "EV", "TE", "ZD", "BB", "PC", "BD", "KC", "ZB"},
				{"EG", "BC", "TC", "ZE", "ZC", "GP", "ET", "VC", "TB", "VZ"},
				{"EZ", "EK", "DV", "CG", "VE", "DP", "BK", "PG", "GK", "GZ"},
				{"KT", "CT", "ZZ", "VG", "GD", "CP", "BE", "ZT", "VK", "DC"}
		};
	private final BombEdgework ew;
	public TwoBits(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{
		String souv = "";
		int number = ("-ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(ew.getSNLET(0))) + (ew.getSNDIG(ew.numSNDIGS() - 1) * ew.BAT());
		if(ew.findPort("RCA") > 0 && ew.findPort("RJ-45") == 0)
			number *= 2;
		number = number % 100;
		for(int aa = 0; aa < 3; aa++)
		{
			String input = JOptionPane.showInputDialog("Query " + chart[number / 10][number % 10] + "\nThen enter the new number:");
			boolean v = valid(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Query " + chart[number / 10][number % 10] + "\nThen enter the new number:");
				v = valid(input);
			}
			number = Integer.parseInt(input);
			souv = souv + "RESPONSE #" + (aa + 1) + ": " + (number / 10) + "" + (number % 10) + "\n";
		}
		JOptionPane.showMessageDialog(null, "Submit " + chart[number / 10][number % 10]);
		return souv;
	}
	private boolean valid(String i)
	{
		if(i.length() < 3)
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