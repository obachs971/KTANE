package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class CaesarCipher 
{
	private final BombEdgework ew;
	public CaesarCipher(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		int offset = ew.BAT();
		if(ew.numCharsInSN("AEIOU") > 0)
			offset--;
		if(ew.getSNDIG(ew.numSNDIGS() - 1) % 2 == 0)
			offset++;
		if(ew.findInd("CAR"))
			offset++;
		if(ew.findPort("PARALLEL") > 0 && ew.findLit("NSA"))
			offset = 0;
		String decrypt = "", alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ", letters = JOptionPane.showInputDialog("Enter the display:").toUpperCase();
		boolean v = valid(letters);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			letters = JOptionPane.showInputDialog("Enter the display:").toUpperCase();
			v = valid(letters);
		}
		for(int aa = 0; aa < letters.length(); aa++)
			decrypt = decrypt + "" + alpha.charAt(mod(alpha.indexOf(letters.charAt(aa)) + offset, 26));
		JOptionPane.showMessageDialog(null, "Offset: " + (offset % 26) + "\nPress these letters: " + decrypt);
	}
	private int mod(int n, int m)
	{
		while(n < m)
			n += m;
		return (n % m);
	}
	private boolean valid(String i)
	{
		if(i.length() == 5)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
}
