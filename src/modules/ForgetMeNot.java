package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class ForgetMeNot 
{
	private final BombEdgework ew;
	public ForgetMeNot(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		String numbers = JOptionPane.showInputDialog("Enter all the numbers:").replace(" ", "").replace(",", "").replace("/", "");
		boolean v = valid(numbers);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			numbers = JOptionPane.showInputDialog("Enter all the numbers:");
			v = valid(numbers);
		}
		int[] result = new int[numbers.length()];
		if(ew.findUnlit("CAR"))
			result[0] = ("0123456789".indexOf(numbers.charAt(0)) + 2) % 10;
		else if(ew.numUnlit() > ew.numLit())
			result[0] = ("0123456789".indexOf(numbers.charAt(0)) + 7) % 10;
		else if(ew.numUnlit() == 0)
			result[0] = ("0123456789".indexOf(numbers.charAt(0)) + ew.numLit()) % 10;
		else
			result[0] = ("0123456789".indexOf(numbers.charAt(0)) + ew.getSNDIG(ew.numSNDIGS() - 1)) % 10;
		String out = result[0] + "";
		if(numbers.length() > 1)
		{
			if(ew.findPort("SERIAL") > 0 && ew.numSNDIGS() >= 3)
				result[1] = ("0123456789".indexOf(numbers.charAt(1)) + 3) % 10;
			else if(result[0] % 2 == 0)
				result[1] = ("0123456789".indexOf(numbers.charAt(1)) + result[0] + 1) % 10;
			else 
				result[1] = ("0123456789".indexOf(numbers.charAt(1)) + result[0] - 1) % 10;
			out = out + "" + result[1];
		}
		int smallestOdd = 9;
		for(int aa = 0; aa < ew.numSNDIGS(); aa++)
		{
			if(ew.getSNDIG(aa) % 2 == 1 && ew.getSNDIG(aa) < smallestOdd)
				smallestOdd = ew.getSNDIG(aa);
		}
		for(int aa = 2; aa < numbers.length(); aa++)
		{
			if(result[aa - 2] == 0 || result[aa - 1] == 0)
				result[aa] = ("0123456789".indexOf(numbers.charAt(aa)) + ew.getLargestSNDIG()) % 10;
			else if(result[aa - 2] % 2 == 0 && result[aa - 1] % 2 == 0)
				result[aa] = ("0123456789".indexOf(numbers.charAt(aa)) + smallestOdd) % 10;
			else if((result[aa - 2] + result[aa - 1]) >= 10)
				result[aa] = ("0123456789".indexOf(numbers.charAt(aa)) + 1) % 10;
			else
				result[aa] = ("0123456789".indexOf(numbers.charAt(aa)) + result[aa - 2] + result[aa - 1]) % 10;
			out = out + "" + result[aa];
			if((aa + 1) % 12 == 0)
				out = out + "\n";
			else if((aa + 1) % 3 == 0)
				out = out + " ";
			
		}
		System.out.println(out);
		ArrayList<String> pages = new ArrayList<String>();
		while(out.length() > 400)
		{
			pages.add(out.substring(0, 400));
			out = out.substring(400);
		}
		pages.add(out);
		for(int aa = 0; aa < pages.size(); aa++)
			JOptionPane.showMessageDialog(null, "Page " + (aa + 1) + " out of " + pages.size() + "\nEnter these numbers:\n" + pages.get(aa));
	}
	private boolean valid(String i)
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
}
