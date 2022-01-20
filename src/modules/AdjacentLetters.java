package modules;

import javax.swing.JOptionPane;

public class AdjacentLetters 
{
	private final String[][] table =
		{
				{"GJMOY", "HKPRW"},{"IKLRT", "CDFYZ"},{"BHIJW", "DEMTU"},{"IKOPQ", "CJTUW"},{"ACGIJ", "KSUWZ"},{"CERVY", "AGJPQ"},{"ACFNS", "HOQYZ"},{"LRTUX", "DKMPS"},{"DLOWZ", "EFNUV"},{"BQTUW", "EHIOS"},{"AFPXY", "DIORZ"},{"GKPTZ", "ABRVX"},{"EILQT", "BFPWX"},
				{"PQRSV", "AFGHL"},{"HJLUZ", "IQSTX"},{"DMNOX", "CFHKR"},{"CEOPV", "BDIKN"},{"AEGSU", "BNOXY"},{"ABEKQ", "GMVYZ"},{"GVXYZ", "CJLSU"},{"FMVXZ", "BILNY"},{"DHMNW", "AEJQX"},{"DFHMN", "GLQRT"},{"BDFKW", "AJNOV"},{"BCHSU", "EGMTW"},{"JNRSY", "CLMPV"}
		};
	public void run()
	{
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String[] letters = new String[3], press = {"", "", ""};
		for(int aa = 0; aa < 3; aa++)
		{
			letters[aa] = JOptionPane.showInputDialog("Enter the letters on row #" + (aa + 1) + ":").toUpperCase();
			boolean v = valid(letters[aa], alpha);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				letters[aa] = JOptionPane.showInputDialog("Enter the letters on row #" + (aa + 1) + ":").toUpperCase();
				v = valid(letters[aa], alpha);
			}
		}
		for(int aa = 0; aa < 3; aa++)
		{
			for(int bb = 0; bb < 4; bb++)
			{
				String[] LRUD = {"", ""};
				if(bb > 0)
					LRUD[0] = LRUD[0] + "" + letters[aa].charAt(bb - 1);
				if(bb < 3)
					LRUD[0] = LRUD[0] + "" + letters[aa].charAt(bb + 1);
				if(aa > 0)
					LRUD[1] = LRUD[1] + "" + letters[aa - 1].charAt(bb);
				if(aa < 2)
					LRUD[1] = LRUD[1] + "" + letters[aa + 1].charAt(bb);
				int row = alpha.indexOf(letters[aa].charAt(bb));
				boolean flag = false;
				for(int cc = 0; cc < 2; cc++)
				{
					//System.out.println(table[row][cc]);
					for(int dd = 0; dd < LRUD[cc].length(); dd++)
					{
						if(table[row][cc].indexOf(LRUD[cc].charAt(dd)) >= 0)
						{
							flag = true;
							break;
						}
					}
					if(flag)
					{
						press[aa] = press[aa] + "" + letters[aa].charAt(bb) + " ";
						break;
					}
				}
			}
		}
		String out = "";
		for(int aa = 0; aa < 3; aa++)
		{
			if(press[aa].length() == 0)
				out = out + "--------\n";
			else
				out = out + "" + press[aa] + "\n";
		}
		JOptionPane.showMessageDialog(null, "Press these letters:\n" + out);
	}
	private boolean valid(String i, String alpha)
	{
		if(i.length() == 4)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if(alpha.indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
}
