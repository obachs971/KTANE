package modules;

import javax.swing.JOptionPane;

public class Maintenance 
{
	public void run()
	{
		String input = JOptionPane.showInputDialog("Enter the plate number and\nthe number of jobs needed:").toUpperCase().replace(" ", "");
		boolean v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the plate number and\nthe number of jobs needed:").toUpperCase().replace(" ", "");
			v = valid(input);
		}
		int carValue = getMulti(input.substring(0, 2)) * getBaseValue(Integer.parseInt(input.substring(2, 4)));
		//System.out.println(carValue);
		if(carValue < 150)
			JOptionPane.showMessageDialog(null, "The car is a write-off :(");
		else
		{
			int index = 0;
			if("ABCGHJNPRVW".indexOf(input.charAt(input.length() - 2)) >= 0)
				index += 1;
			if("56".indexOf(input.charAt(2)) >= 0)
				index += 2;
			if(input.contains("M"))
				index += 4;
			if(input.substring(0, 2).equals("BN") || input.substring(0, 2).equals("PC") || input.substring(0, 2).equals("FR"))
				index += 8;
			String[] jobOrders =
				{
					"EAIK","AGFK","GKAB","HGEC","CBAF","GCEH",
					"FDJB","ABCD","BLHJ","DJFA","BIKC","KIBE",
					"HDEL","AGCF","LECH","AJKL"
				};
			String jobs = jobOrders[index].substring(0, "01234".indexOf(input.charAt(input.length() - 1)));
			String covered = getCoverage(input.charAt(input.length() - 2));
			int[] jobPrices =
				{
						3, 6, 10, 15, 
						25, 40, 80, 150, 
						160, 320, 500, 750
				};
			int cost = 0;
			for(int aa = 0; aa < jobs.length(); aa++)
			{
				if(covered.indexOf(jobs.charAt(aa)) < 0)
					cost += jobPrices["ABCDEFGHIJKL".indexOf(jobs.charAt(aa))];
			}
			//System.out.println(cost);
			if(cost > carValue)
				JOptionPane.showMessageDialog(null, "The car is a write-off :(");
			else
			{
				String[] jobNames = 
					{
						"Wash", "Headlight Bulb", "Wiper Replacement", "Oil Change", 
						"Brake Fluid Change", "Windscreen Chip", "One Tyre", "Windscreen Replacement", 
						"Two Tyres", "Four Tyres", "Exhaust Welding", "Head Gasket Replacement"
					};
					String jobOrder = "FEBCHKLGIJDA";
					String out = "";
					for(int aa = 0; aa < jobOrder.length(); aa++)
					{
						if(jobs.indexOf(jobOrder.charAt(aa)) >= 0)
							out = out + "\n" + jobNames["ABCDEFGHIJKL".indexOf(jobOrder.charAt(aa))];
					}
					JOptionPane.showMessageDialog(null, "Do these jobs:" + out);
			}
		}
	}
	private int getMulti(String model)
	{
		switch(model)
		{
			case "HN":return 2;
			case "RN":return 3;
			case "FD":return 4;
			case "MA":return 5;
			case "BM":return 6;
			case "AD":return 7;
			case "BN":return 8;
			case "PC":return 9;
			case "FR":return 10;
		}
		return -1;
	}
	private int getBaseValue(int year)
	{
		if(year > 50)
			year -= 50;
		int[] values = 
			{
				-1, 50, 60, 70, 80, 90, 100, 
				125, 150, 175, 200,
				250, 300, 
				400, 500, 600, 700, 800, 900, 1000
			};
		return values[year];
	}
	private String getCoverage(char insurance)
	{
		switch(insurance)
		{
			case 'A':case 'B':case 'C':
				return "E";
			case 'D':case 'E':case 'F':
				return "DG";
			case 'G':case 'H':case 'J':case 'K':case 'L':case 'M':
				return "F";
			case 'N':case 'P':case 'R':
				return "GI";
			case 'S':case 'T':case 'U':
				return "FH";
			case 'V':case 'W':
				return "DEF";
			case 'X':case 'Y':
				return "D";
				
		}
		return "";
	}
	private boolean valid(String i)
	{
		if(i.length() >= 6 && i.length() < 9)
		{
			int check = getMulti(i.substring(0, 2));
			if(check == -1)
				return false;
			//System.out.println("HERE 1");
			if(!(isNum(i.substring(2, 4))))
				return false;
			//System.out.println("HERE 2");
			check = Integer.parseInt(i.substring(2, 4));
			if(!((check >= 2 && check <= 19) || (check >= 51 && check <= 69)))
				return false;
			//System.out.println("HERE 3");
			if("ABCDEFGHJKLMNPRSTUVWXY".indexOf(i.charAt(i.length() - 2)) < 0)
				return false;
			//System.out.println("HERE 4");
			if("234".indexOf(i.charAt(i.length() - 1)) >= 0)
				return true;
		}
		return false;
	}
	private boolean isNum(String i)
	{
		for(int aa = 0; aa < i.length(); aa++)
		{
			if("0123456789".indexOf(i.charAt(aa)) < 0)
				return false;
		}
		return true;
	}
}
