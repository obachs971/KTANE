package modules;

import javax.swing.JOptionPane;

import start.BombConfig;

public class CheapCheckout 
{
	private final BombConfig cf;
	public CheapCheckout(BombConfig c)
	{
		cf = c;
	}
	public String run()
	{
		String input = JOptionPane.showInputDialog("Enter the amount paid:");
		boolean v = isNum(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the amount paid:");
			v = isNum(input);
		}
		String souv = "PAID AMOUNT #1: $" + input + ".00";
		int paid = Integer.parseInt(input) * 100;
		String[] items = new String[6];
		int[] prices = new int[6];
		for(int aa = 0; aa < 4; aa++)
		{
			items[aa] = WordFix(JOptionPane.showInputDialog("Enter item #" + (aa + 1) + ":").toUpperCase());
			v = v1(items[aa], aa);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				items[aa] = WordFix(JOptionPane.showInputDialog("Enter item #" + (aa + 1) + ":").toUpperCase());
				v = v1(items[aa], aa);
			}
			prices[aa] = getFixedPrice(items[aa]);
		}
		for(int aa = 4; aa < 6; aa++)
		{
			items[aa] = WordFix(JOptionPane.showInputDialog("Enter item #" + (aa + 1) + ":").toUpperCase());
			v = v1(items[aa], aa);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				items[aa] = WordFix(JOptionPane.showInputDialog("Enter item #" + (aa + 1) + ":").toUpperCase());
				v = v1(items[aa], aa);
			}
			prices[aa] = Round(getNonFixedPrice(items[aa]));
			items[aa] = items[aa].split(" ")[1];
		}
		switch(cf.getStartingDayName())
		{
			case "SUNDAY":
				for(int aa = 0; aa < 6; aa++)
				{
					if(aa < 4 && items[aa].contains("S"))
						prices[aa] += 215;
				}
				break;
			case "MONDAY":
				for(int aa = 0; aa < 6; aa++)
				{
					if(aa == 0 || aa == 2 || aa == 5)
						prices[aa] = Round((prices[aa] * 85) / 10);
				}
				break;
			case "TUESDAY":
				for(int aa = 0; aa < 6; aa++)
				{
					if(aa < 4)
					{
						int DR = prices[aa] + 0;
						while(DR > 9)
						{
							String conv = DR + "";
							DR = 0;
							for(int bb = 0; bb < conv.length(); bb++)
								DR += "0123456789".indexOf(conv.charAt(bb));
						}
						prices[aa] = prices[aa] + (DR * 100);
					}
				}
				break;
			case "WEDNESDAY":
				for(int aa = 0; aa < 6; aa++)
				{
					String s = prices[aa] + "";
					while(s.length() < 3)
						s = "0" + s;
					int high = 0, low = 9;
					for(int bb = 0; bb < s.length(); bb++)
					{
						if("0123456789".indexOf(s.charAt(bb)) > high)
							high = "0123456789".indexOf(s.charAt(bb));
						if("0123456789".indexOf(s.charAt(bb)) < low)
							low = "0123456789".indexOf(s.charAt(bb));
					}
					s = s.replace(high + "", "*").replace(low + "", high + "").replace("*", low + "");
					prices[aa] = Integer.parseInt(s);
				}
				break;
			case "THURSDAY":
				for(int aa = 0; aa < 6; aa++)
				{
					if(aa % 2 == 0)
						prices[aa] = Round(prices[aa] * 5);
				}
				break;
			case "FRIDAY":
				for(int aa = 0; aa < 6; aa++)
				{
					switch(items[aa])
					{
						case "BANANAS":
						case "GRAPEFRUIT":
						case "LEMONS":
						case "ORANGES":
						case "TOMATOES":
							prices[aa] = Round((prices[aa] * 125) / 10);
							break;
					}
				}
				break;
			case "SATURDAY":
				for(int aa = 0; aa < 6; aa++)
				{
					switch(items[aa])
					{	
						case "CANDY CANES":
						case "CHOCOLATE BAR":
						case "COOKIES":
						case "FRUIT PUNCH":
						case "GRAPE JELLY":
						case "GUM":
						case "HONEY":
						case "LOLLIPOPS":
						case "MINTS":
						case "SODA":
						case "SUGAR":
							prices[aa] = Round((prices[aa] * 65) / 10);
							break;
					}
				}
				break;
		}
		int total = 0;
		for(int aa = 0; aa < prices.length; aa++)
		{
			System.out.println(prices[aa]);
			total += prices[aa];
		}
		while(total > paid)
		{
			input = JOptionPane.showInputDialog("Press submit\nEnter the new amount:");
			v = isNum(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Press submit\nEnter the new amount:");
				v = isNum(input);
			}
			souv = souv + "\nPAID AMOUNT #2: $" + input + ".00";
			paid = Integer.parseInt(input) * 100;
		}
		paid = paid - total;
		int dollars = paid / 100, cents = paid % 100;
		if(cents < 10)
			JOptionPane.showMessageDialog(null, "Enter this amount: $" + dollars + ".0" + cents);
		else
			JOptionPane.showMessageDialog(null, "Enter this amount: $" + dollars + "." + cents);
		return souv;
	}
	private int getFixedPrice(String i)
	{
		switch(i)
		{
			case "CANDY CANES":
				return 351;
			case "CANOLA OIL":
				return 228;
			case "CEREAL":
				return 419;
			case "CHEESE":
				return 449;
			case "CHOCOLATE BAR":
				return 210;
			case "CHOCOLATE MILK":
				return 568;
			case "COFFEE BEANS":
				return 785;
			case "COOKIES":
				return 200;
			case "DEODORANT":
				return 397;
			case "FRUIT PUNCH":
				return 208;
			case "GRAPE JELLY":
				return 298;
			case "GUM":
				return 112;
			case "HONEY":
				return 825;
			case "KETCHUP":
				return 359;
			case "LOLLIPOPS":
				return 261;
			case "LOTION":
				return 797;
			case "MAYONNAISE":
				return 399;
			case "MINTS":
				return 639;
			case "MUSTARD":
				return 236;
			case "PAPER TOWELS":
				return 946;
			case "PASTA SAUCE":
				return 230;
			case "PEANUT BUTTER":
				return 500;
			case "POTATO CHIPS":
				return 325;
			case "SHAMPOO":
				return 498;
			case "SOCKS":
				return 697;
			case "SODA":
				return 205;
			case "SPAGHETTI":
				return 292;
			case "SUGAR":
				return 208;
			case "TEA":
				return 235;
			case "TISSUES":
				return 394;
			case "TOOTHPASTE":
				return 250;
			case "WATER BOTTLES":
				return 937;
			case "WHITE BREAD":
				return 243;
			case "WHITE MILK":
				return 362;
		}
		return -1;
	}
	private int getNonFixedPrice(String i)
	{
		String[] conv = i.split(" ");
		conv[0] = conv[0].replace(".", "");
		switch(conv[1])
		{
			case "BANANAS":
				return (87 * Integer.parseInt(conv[0]));
			case "BROCCOLI":
				return (139 * Integer.parseInt(conv[0]));
			case "CHICKEN":
				return (199 * Integer.parseInt(conv[0]));
			case "GRAPEFRUIT":
				return (108 * Integer.parseInt(conv[0]));
			case "LEMONS":
				return (174 * Integer.parseInt(conv[0]));
			case "LETTUCE":
				return (110 * Integer.parseInt(conv[0]));
			case "ORANGES":
				return (80 * Integer.parseInt(conv[0]));
			case "PORK":
				return (414 * Integer.parseInt(conv[0]));
			case "POTATOES":
				return (68 * Integer.parseInt(conv[0]));
			case "STEAK":
				return (497 * Integer.parseInt(conv[0]));
			case "TOMATOES":
				return (180 * Integer.parseInt(conv[0]));
			case "TURKEY":
				return (298 * Integer.parseInt(conv[0]));
		}
		return -1;
	}
	private int Round(int p)
	{
		if(p % 10 >= 5)
			return ((p / 10) + 1);
		else
			return (p / 10);
	}
	private String WordFix(String i)
	{
		switch(i)
		{
			case "MAYO":
				return "MAYONNAISE";
			case "BREAD":
				return "WHITE BREAD";
			case "CHIPS":
				return "POTATO CHIPS";
			case "TOWELS":
				return "PAPER TOWELS";
			case "WATER":
				return "WATER BOTTLES";
			default:
				return i.toUpperCase();
		}
	}
	private boolean v1(String i, int cur)
	{
		String[] conv = i.split(" ");
		if(cur < 4)
		{
			if(getFixedPrice(i) != -1)
				return true;
		}
		else if(conv.length == 2)
		{
			switch(conv[0])
			{
				case "0.5":
				case "1.0":
				case "1.5":
					break;
				default:
					return false;
			}
			conv[0] = conv[0].replace(".", "");
			if(getNonFixedPrice(i) != -1)
				return true;
		}
		return false;
	}
	private boolean isNum(String i)
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
