package modules;

import javax.swing.JOptionPane;

public class Pie 
{
	private final String pi = "31415926535897932384"
			                + "62643383279502884197"
			                + "16939937510582097494"
			                + "45923078164062862089"
			                + "98628034825342117067"
			                + "98214808651328230664"
			                + "70938446095505822317"
			                + "25359408128481117450"
			                + "28410270193852110555"
			                + "96446229489549303819"
			                + "64428810975665933446"
			                + "12847564823378678316"
			                + "52712019091456485669"
			                + "23460348610454326648"
			                + "21339360726024914127"
			                + "37245870066063155881"
			                + "74881520920962829254"
			                + "09171536436789259036"
			                + "00113305305488204665"
			                + "21384146951941511609"
			                + "43305727036575959195"
			                + "30921861173819326117"
			                + "93105118548074462379"
			                + "96274956735188575272"
			                + "48912279381830119491";
	public String run()
	{
		String input = JOptionPane.showInputDialog("Enter the 5 digits:");
		int index = pi.indexOf(input);
		while(index < 0 || input.length() != 5)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the 5 digits:");
			index = pi.indexOf(input);
		}
		int X = (index + 1 + Integer.parseInt(input)) % 100;
		int Y = 0;
		for(int i = 0; i < 5; i++)
			Y += "0123456789".indexOf(input.charAt(i));
		Y %= 10;
		String left = "54321", press = "";
		if(prime(X))
			press += "1";
		if(X % 2 == Y % 2)
			press += "2";
		if(X % 3 == 0)
			press += "3";
		if(Y != 0 && (X % Y) == 0)
			press += "4";
		for(int i = 0; i < press.length(); i++)
			left = left.replace(press.charAt(i) + "", "");
		JOptionPane.showMessageDialog(null, "Press it in this order: " + press + left);
		return ("DIGITS: " + input);
	}
	private boolean prime(int n)
	{
		if(n < 2)
			return false;
		for(int i = 2; i < n; i++)
		{
			if(n % i == 0)
				return false;
		}
		return true;
	}
}
