package modules;

import javax.swing.JOptionPane;

public class Cryptography 
{
	private final String[][] table =
		{
				{"1 6 4 3", "1 9 9 8", "2 3 3 3", "2 3 3 4", "2 4 4 4", "2 4 3 3", "2 4 3 6", "2 6 2 3", "2 6 5 4", "2 7 3 3", "3 2 3 1", "3 4 3 5", "3 4 3 7", "3 4 4 3", "3 4 5 3", "3 4 6 3", "3 5 2 2", "3 5 4 2", "3 7 2 7", "3 8 4 3", "4 3 4 3", "4 3 5 2", "4 3 5 4", "4 4 2 10", "4 4 3 4", "4 5 4 4", "4 7 5 4", "5 2 2 5", "5 2 5 5", "6 3 13 3", "6 4 7 3", "7 3 2 4", "7 3 3 4", "	7 4 2 3", "7 5 7 3", "8 4 3 4", "9 6 3 2"},
				{"AFROSTYIMEWNHDBC", "ASQUEZINGWRCHPLTOVD", "ITWASLHEMO", "ITWASHEVRYNGLKD", "NOWIDTHABLESRFGMUPY", "TOEDGHISWAYLNCRPFUMK", "HEICDSOFNTGAY", "OFCURSEHDI", "NOWARMTHCULDIYE", "HECARIDSOWNLTMPUYB", "BUTHEWASIGFDNROC", "THEFIRMWASKNOCGDLY", "BUTWHADISCROGE", "ANDWHETYSIMCOGULRP", "ANDTHEWOULGIRSYBVKM", "THECOLDWINMFRZSAUPVKG", "HOWCULDITBERS", "ANDITHWOEGRCSM", "THEMNIOFARLYSUBGCKPD", "THEAVISRNDOWLCUBFGMYP", "MADEHISYRTNLPBUOKWGVC", "HARDNSPFLITOMWCEVUKG", "EVNTHBLIDMSOGAPRKW", "THISMUBEDNCLYROGWFA", "WHENILYOUCMTS", "THEYOFNCAMDWSLRGVI", "FOULWEATHRDINKVM", "THERISNODUBAMLYW", "THERISODYAFWBVUCGNML", "SECRTANDLFOIY", "NOBDYEVRSTPHIMAWGLKCU", "SCROGEANDHWPTFIKMY", "SCROGEWAHILXUTDMNYF", "SCROGEKNWHAD", "SCROGENVPAITDULMY", "EXTRNALHDCOIFUSG", "SOMETIPLNWHBUCADRGY"}
		};
	public void run()
	{
		String alpha = JOptionPane.showInputDialog("Enter the lengths of the 1st 4 words (spaces):");
		alpha = getAlpha(alpha);
		while(alpha.length() == 0)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			alpha = JOptionPane.showInputDialog("Enter the lengths of the 1st 4 words (spaces):");
			alpha = getAlpha(alpha);
		}
		String letters = JOptionPane.showInputDialog("Enter the 5 letters on top:").toUpperCase();
		letters = getOrder(letters, alpha);
		while(letters.length() == 0)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			letters = JOptionPane.showInputDialog("Enter the 5 letters on top:").toUpperCase();
			letters = getOrder(letters, alpha);
		}
		JOptionPane.showMessageDialog(null, "Press these letters: " + letters);
	}
	private String getAlpha(String i)
	{
		for(int aa = 0; aa < table[0].length; aa++)
		{
			if(table[0][aa].equals(i))
				return table[1][aa].toUpperCase();
		}
		return "";
	}
	private String getOrder(String i, String alpha)
	{
		if(i.length() != 5)
			return "";
		String order = "";
		for(int aa = 0; aa < alpha.length(); aa++)
		{
			if(i.indexOf(alpha.charAt(aa)) >= 0)
			{
				order = order + "" + alpha.charAt(aa);
				if(order.length() == 5)
					return (order.charAt(0) + " " + order.charAt(1) + " " + order.charAt(2) + " " + order.charAt(3) + " " + order.charAt(4));
			}
		}
		return "";
	}
}
