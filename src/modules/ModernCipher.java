package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class ModernCipher 
{
	private final BombEdgework ew;
	public ModernCipher(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{
		int caseNum = 4, key = ew.getSNSUM(), strikes = 0, solves = 0, length = 0;
		String word, souv = "";
		if(ew.numCharsInSN("AEIOU") > 0)
			caseNum = 1;
		else if(ew.BAT() > 3)
			caseNum = 2;
		else if(ew.findPort("SERIAL") > 0)
			caseNum = 3;
		for(int i = 0; i < 3; i++)
		{
			String input = JOptionPane.showInputDialog("Enter the display:").toUpperCase();
			boolean v = v1(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the display:").toUpperCase();
				v = v1(input);
			}
			switch(caseNum)
			{
				case 1:
					word = decrypt(input, key + strikes, false);
					if(i < 2)
					{
						int[] nums = getInput("Submit " + word + "\nEnter the number of strikes:", 1);
						strikes = nums[0];
					}
					else
						JOptionPane.showMessageDialog(null, "Submit " + word);
					break;
				case 2:
					word = decrypt(input, key + strikes, true);
					if(i < 2)
					{
						int[] nums = getInput("Submit " + word + "\nEnter the number of strikes:", 1);
						strikes = nums[0];
					}
					else
						JOptionPane.showMessageDialog(null, "Submit " + word);
					break;
				case 3:
					word = decrypt(input, key + strikes + length, false);
					if(i < 2)
					{
						int[] nums = getInput("Submit " + word + "\nEnter the number of strikes:", 1);
						strikes = nums[0];
						length = word.length();
					}
					else
						JOptionPane.showMessageDialog(null, "Submit " + word);
					break;
				default:
					word = decrypt(input, key + strikes + solves, true);
					length = word.length();
					if(i < 2)
					{
						int[] nums = getInput("Submit " + word + "\nEnter the number of\nsolves and strikes (spaces):", 2);
						solves = nums[0];
						strikes = nums[1];
					}
					else
						JOptionPane.showMessageDialog(null, "Submit " + word);
					break;
			}
			souv = souv + "WORD #" + (i + 1) + ": " + word.toUpperCase() + "\n";
		}
		return souv;
	}
	private int[] getInput(String out, int len)
	{
		String input = JOptionPane.showInputDialog(out);
		boolean v = v2(input, len);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog(out);
			v = v2(input, len);
		}
		String[] conv = input.split(" ");
		int[] nums = new int[conv.length];
		for(int i = 0; i < conv.length; i++)
			nums[i] = Integer.parseInt(conv[i]);
		return nums;
	}
	private String decrypt(String encrypt, int key, boolean forward)
	{
		String alpha = "QWERTYUIOPASDFGHJKLZXCVBNM", decrypt = "";
		if(forward)
		{
			for(int i = 0; i < encrypt.length(); i++)
				decrypt = decrypt + "" + alpha.charAt(mod(alpha.indexOf(encrypt.charAt(i)) + key, 26));
		}
		else
		{
			for(int i = 0; i < encrypt.length(); i++)
				decrypt = decrypt + "" + alpha.charAt(mod(alpha.indexOf(encrypt.charAt(i)) - key, 26));
		}
		return decrypt;
	}
	private int mod(int n, int m)
	{
		while(n < 0)
			n += m;
		return (n % m);
	}
	private boolean v1(String i)
	{
		for(int aa = 0; aa < i.length(); aa++)
		{
			if("QWERTYUIOPASDFGHJKLZXCVBNM".indexOf(i.charAt(aa)) < 0)
				return false;
		}
		return true;
	}
	private boolean v2(String i, int len)
	{
		String[] conv = i.split(" ");
		if(conv.length == len)
		{
			for(int aa = 0; aa < len; aa++)
			{
				if(!(isNum(conv[aa])))
					return false;
			}
			return true;
		}
		return false;
	}
	private boolean isNum(String i)
	{
		if(i.length() == 0)
			return false;
		for(int aa = 0; aa < i.length(); aa++)
		{
			if("0123456789".indexOf(i.charAt(aa)) < 0)
				return false;
		}
		return true;
	}
}
