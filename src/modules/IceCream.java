package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class IceCream 
{
	private final String[][] table =
		{
				{"150", "683", "071", "432", "361"},
				{"083", "214", "435", "267", "143"},
				{"845", "167", "256", "375", "361"},
				{"267", "014", "823", "781", "573"},
				{"341", "362", "021", "247", "856"},
				{"163", "752", "145", "420", "375"},
				{"461", "236", "157", "682", "274"},
				{"625", "417", "082", "126", "367"},
				{"426", "123", "034", "650", "478"},
				{"635", "512", "426", "710", "372"},
				{"035", "164", "548", "207", "736"},
				{"463", "102", "674", "258", "031"},
				{"371", "082", "713", "678", "451"},
				{"241", "780", "346", "103", "652"},
				{"125", "680", "321", "745", "184"},
				{"031", "257", "346", "671", "530"},
				{"812", "648", "043", "164", "325"},
				{"732", "156", "547", "340", "621"},
				{"562", "136", "347", "205", "813"},
				{"568", "210", "482", "425", "051"}
		};
	private final BombEdgework ew;
	public IceCream(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{
		String[] order;
		if(ew.numLit() > ew.numUnlit())
			order = new String[] {"CC", "N", "TF", "TC", "RO", "DC", "MCC", "DS", "RA"};
		else if(ew.numEmpty() > 0)
			order = new String[] {"DC", "MCC", "N", "RO", "TF", "DS", "CC", "RA", "TC"};
		else if(ew.BAT() >= 3)
			order = new String[] {"N", "TF", "CC", "RA", "DS", "MCC", "DC", "TC", "RO"};
		else
			order = new String[] {"DS", "CC", "RO", "TC", "N", "DC", "TF", "RA", "MCC"};
		String souv = "";
		for(int aa = 0; aa < 3; aa++)
		{
			String input = JOptionPane.showInputDialog("Enter the name:").toUpperCase();
			int row = getRow(input);
			while(row == -1)
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the name:").toUpperCase();
				row = getRow(input);
			}
			souv = souv + "NAME: " + input.toUpperCase();
			String allergy = table[row][ew.getSNDIG(ew.numSNDIGS()) / 2];
			input = JOptionPane.showInputDialog("TF - Tutti Frutti\nRO - Rocky Road\nRA - Raspberry Ripple\nDC - Double Chocolate\nDS - Double Strawberry\nCC - Cookies & Cream\nN - Neapolitan\nMCC - Mint Chocolate Chip\nTC - The Classic\nIgnore Vanilla as a flavor\nEnter the 4 flavors (spaces):").toUpperCase();
			boolean v = valid(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("TF - Tutti Frutti\nRO - Rocky Road\nRA - Raspberry Ripple\nDC - Double Chocolate\nDS - Double Strawberry\nCC - Cookies & Cream\nN - Neapolitan\nMCC - Mint Chocolate Chip\nTC - The Classic\nIgnore Vanilla as a flavor\nEnter the 4 flavors (spaces):").toUpperCase();
				v = valid(input);
			}
			String[] options = input.split(" ");
			String sell = "Vanilla";
			String ignore = "";
			for(int bb = 0; bb < order.length; bb++)
			{
				if(order[bb].equals(options[0]) || order[bb].equals(options[1]) || order[bb].equals(options[2]) || order[bb].equals(options[3]))
				{
					String FA = getFlavorAllergy(order[bb]);
					boolean flag = true;
					for(int cc = 0; cc < FA.length(); cc++)
					{
						if(allergy.indexOf(FA.charAt(cc)) >= 0)
						{
							flag = false;
							break;
						}
					}
					if(flag)
					{
						sell = getFlavor(order[bb]);
						ignore = order[bb].toUpperCase();
						break;
					}
				}
			}
			JOptionPane.showMessageDialog(null, "Sell " + sell + " at\neven number of minutes");
			for(int bb = 0; bb < options.length; bb++)
			{
				if(!(options[bb].equals(ignore)))
					souv = souv + "\n" + getFlavor(options[bb]).toUpperCase();
			}
			souv = souv + "\n----------------------------------------\n";
		}
		return souv;
	}
	private String getFlavorAllergy(String f)
	{
		switch(f)
		{
			case "TF":
				return "1267";
			case "RO":
				return "038";
			case "RA":
				return "26";
			case "DC":
				return "0";
			case "DS":
				return "16";
			case "CC":
				return "4";
			case "N":
				return "016";
			case "MCC":
				return "05";
			case "TC":
				return "067";
		}
		return "";
	}
	private String getFlavor(String f)
	{
		switch(f)
		{
			case "TF":
				return "Tutti Frutti";
			case "RO":
				return "Rocky Road";
			case "RA":
				return "Raspberry Ripple";
			case "DC":
				return "Double Chocolate";
			case "DS":
				return "Double Strawberry";
			case "CC":
				return "Cookies & Cream";
			case "N":
				return "Neapolitan";
			case "MCC":
				return "Mint Chocolate Chip";
			case "TC":
				return "The Classic";
		}
		return "";
	}
	private int getRow(String n)
	{
		switch(n)
		{
			case "MIKE":
				return 0;
			case "TIM":
				return 1;
			case "TOM":
				return 2;
			case "DAVE":
				return 3;
			case "ADAM":
				return 4;
			case "CHERYL":
				return 5;
			case "SEAN":
				return 6;
			case "ASHLEY":
				return 7;
			case "JESSICA":
				return 8;
			case "TAYLOR":
				return 9;
			case "SIMON":
				return 10;
			case "SALLY":
				return 11;
			case "JADE":
				return 12;
			case "SAM":
				return 13;
			case "GARY":
				return 14;
			case "VICTOR":
				return 15;
			case "GEORGE":
				return 16;
			case "JACOB":
				return 17;
			case "PAT":
				return 18;
			case "BOB":
				return 19;
		}
		return -1;
	}
	private boolean valid(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 4)
		{
			for(int aa = 0; aa < 4; aa++)
			{
				String temp = getFlavorAllergy(conv[aa]);
				if(temp.length() == 0)
					return false;
			}
			return true;
		}
		return false;
	}
}
