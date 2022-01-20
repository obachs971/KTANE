package modules;

import java.net.*;
import java.util.Scanner;
import start.BombEdgework;

import javax.swing.JOptionPane;


public class ForeignExchangeRates 
{
	private final String[][] chart =
		{
				{"AUD", "036"},{"CAD", "124"},{"CNY", "156"},{"HRK", "191"},{"DKK", "208"},
				{"HKD", "344"},{"HUF", "348"},{"INR", "356"},{"IDR", "360"},{"ILS", "376"},
				{"JPY", "392"},{"KRW", "410"},{"MYR", "458"},{"MXN", "484"},{"NZD", "554"},
				{"NOK", "578"},{"PHP", "608"},{"RUB", "643"},{"SGD", "702"},{"ZAR", "710"},
				{"SEK", "752"},{"CHF", "756"},{"THB", "764"},{"GBP", "826"},{"USD", "840"},
				{"RON", "946"},{"TRY", "949"},{"BGN", "975"},{"EUR", "978"},{"PLN", "985"},
				{"BRL", "986"}
		};
	private final BombEdgework ew;
	public ForeignExchangeRates(BombEdgework e)
	{
		ew = e;
	}
	public void run() throws Exception
	{
		String color = JOptionPane.showInputDialog("Green/Red\nEnter the color\nof the diodes:").toUpperCase();
		boolean v = v3(color);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			color = JOptionPane.showInputDialog("Green/Red\nEnter the color\nof the diodes:").toUpperCase();
			v = v3(color);
		}
		switch(color)
		{
			case "GREEN":
			case "G":
				green();
				break;
			default:
				red();
				break;
		}
	}
	private void green() throws Exception
	{
		String[] info = new String[3];
		boolean v;
		for(int aa = 0; aa < 3; aa++)
		{
			info[aa] = JOptionPane.showInputDialog("Enter the characters\non row #" + (aa + 1) + ":").toUpperCase();
			if(aa < 2)
				v = v1(info[aa]);
			else
				v = v2(info[aa]);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				info[aa] = JOptionPane.showInputDialog("Enter the characters\non row #" + (aa + 1) + ":").toUpperCase();
				if(aa < 2)
					v = v1(info[aa]);
				else
					v = v2(info[aa]);
			}
		}
		for(int aa = 0; aa < 2; aa++)
		{
			if(v2(info[aa]))
			{
				for(int bb = 0; bb < chart.length; bb++)
				{
					if(info[aa].equals(chart[bb][1]))
					{
						info[aa] = chart[bb][0].toUpperCase();
						break;
					}
				}
			}
		}
		String urlLink;
		if(ew.BAT() >= 2)
			urlLink = "https://api.exchangeratesapi.io/latest?base=" + info[1] +"&symbols=" + info[0];
		else
			urlLink = "https://api.exchangeratesapi.io/latest?base=" + info[0] +"&symbols=" + info[1];
		try
		{
			URL u = new URL(urlLink);
			Scanner s = new Scanner(u.openStream());
			String input = s.nextLine();
			s.close();
			int cursor = 0;
			int counter = 0;
			for(int aa = 0; aa < input.length(); aa++)
			{
				if(input.charAt(aa) == ':')
				{
					cursor = aa + 1;
					counter++;
				}
				if(counter == 2)
					break;
			}
			int start = cursor;
			while(true)
			{
				cursor++;
				if(input.charAt(cursor) == '}')
					break;
			}
			String temp = input.substring(start, cursor);
			double total = Double.parseDouble(temp) * Integer.parseInt(info[2]);
			String number;
			if(total < 10)
				number = "1";
			else
			{
				temp = total + "";
				number = temp.charAt(1) + "";
			}
			JOptionPane.showMessageDialog(null, "Press key #" + number.replace('0', '1'));
		}
		catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(null, "ERROR\nCAN'T OPEN URL\nTERMINATING");
		}
	}
	private void red()
	{
		String position = "MIDDLE";
		if(ew.BAT() >= 2)
			position = "TOP";
		String input = JOptionPane.showInputDialog("Enter the characters\non the " + position + " row:").toUpperCase();
		boolean v = v1(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the characters\non the " + position + " row:").toUpperCase();
			v = v1(input);
		}
		if(!(v2(input)))
		{
			for(int aa = 0; aa < chart.length; aa++)
			{
				if(chart[aa][0].contentEquals(input))
				{
					input = chart[aa][1].toUpperCase();
					break;
				}
			}
		}
		JOptionPane.showMessageDialog(null, "Press key #" + input.replace("0", "1").charAt(1));
	}
	private boolean v1(String i)
	{
		for(int aa = 0; aa < chart.length; aa++)
		{
			for(int bb = 0; bb < chart[aa].length; bb++)
			{
				if(i.equals(chart[aa][bb]))
					return true;
			}
		}
		return false;
	}
	private boolean v2(String i)
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
	private boolean v3(String i)
	{
		switch(i)
		{
			case "GREEN":
			case "G":
			case "RED":
			case "R":
				return true;
			default:
				return false;
		}
	}
}
