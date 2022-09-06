package modules;

import javax.swing.JOptionPane;

import misc.MTK;
import start.BombEdgework;

public class ErrorCodes 
{
	private final BombEdgework ew;
	public ErrorCodes(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		String pos = (ew.numCharsInSN("AEIOU") > 0) ? (ew.BAT() % 2 == 0) ? "1st" : "2nd" : (ew.BAT() % 2 == 0) ? "3rd" : "4th";
		String str = JOptionPane.showInputDialog("Enter the " + pos + " hex code").toUpperCase();
		boolean v = valid(str);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			str = JOptionPane.showInputDialog("Enter the " + pos + " hex code").toUpperCase();
			v = valid(str);
		}
		MTK mtk = new MTK();
		int num = 101 - Integer.parseInt(mtk.baseConvert(str, 16, 10));
		int base = (ew.numCharsInSN("AEIOU") > 0) ? (ew.BAT() % 2 == 0) ? 10 : 8 : (ew.BAT() % 2 == 0) ? 16 : 2;
		str = mtk.baseConvert(num + "", 10, base);
		switch(base)
		{
			case 10:
			case 8:
				num = 3;
				break;
			case 16:
				num = 2;
				break;
			default:
				num = 7;
		}
		while(str.length() < num)
			str = "0" + str;
		JOptionPane.showMessageDialog(null, "Submit this code: " + str);
	}
	private boolean valid(String i)
	{
		if(i.length() == 2)
		{
			for(int aa = 0; aa < 2; aa++)
			{
				if("0123456789ABCDEF".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
}
