package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SillySlots 
{
	private final String[][] chart =
		{
				{"BLUE", "RED", "GREEN", "CHERRY", "GRAPE", "BOMB", "COIN"},
				{"BLUE", "GREEN", "RED", "COIN", "BOMB", "GRAPE", "CHERRY"},
				{"GREEN", "BLUE", "RED", "COIN", "CHERRY", "BOMB", "GRAPE"},
				{"RED", "BLUE", "GREEN", "GRAPE", "CHERRY", "BOMB", "COIN"},
				{"RED", "GREEN", "BLUE", "BOMB", "GRAPE", "CHERRY", "COIN"},
				{"RED", "BLUE", "GREEN", "GRAPE", "BOMB", "COIN", "CHERRY"},
				{"GREEN", "RED", "BLUE", "CHERRY", "BOMB", "COIN", "GRAPE"}
		};
	private final String[] order = {"SASSY", "SILLY", "SOGGY", "SALLY", "SIMON", "SAUSAGE", "STEVEN"};
	public String run()
	{
		ArrayList<String[]> colors = new ArrayList<String[]>();
		ArrayList<String[]> items = new ArrayList<String[]>();
		String souv = "";
		for(int aa = 0; aa < 4; aa++)
		{
			souv = souv + "STAGE " + (aa + 1) + ":\n";
			boolean flag = true;
			String input = JOptionPane.showInputDialog("Red, Blue, Green\nEnter the colors (spaces):").toUpperCase();
			boolean v = v1(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Red, Blue, Green\nEnter the colors (spaces):").toUpperCase();
				v = v1(input);
			}
			colors.add(input.split(" "));
			input = JOptionPane.showInputDialog("Cherry, Grap, Bomb, Coin\nEnter the items (spaces):").toUpperCase();
			v = v2(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Cherry, Grap, Bomb, Coin\nEnter the items (spaces):").toUpperCase();
				v = v2(input);
			}
			items.add(input.split(" "));
			for(int bb = 0; bb < 3; bb++)
				souv = souv + "" + colors.get(aa)[bb] + " " + items.get(aa)[bb] + "\n";
			souv = souv + "--------------------\n";
			input = JOptionPane.showInputDialog("Enter the keyword:").toUpperCase();
			int row = wordToNum(input);
			while(row == -1)
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the keyword:").toUpperCase();
				row = wordToNum(input);
			}
			String[] names = {getName(colors.get(aa)[0], items.get(aa)[0], row), getName(colors.get(aa)[1], items.get(aa)[1], row), getName(colors.get(aa)[2], items.get(aa)[2], row)};
			if(getNumOcc(names, "SILLY SAUSAGE") == 1)
			{
				System.out.println("A");
				flag = false;
			}
			else if(getNumOcc(names, "SASSY SALLY") == 1 && names[0].equals("SASSY SALLY") && !(colors.size() > 2 && getName(colors.get(aa - 2)[0], items.get(aa - 2)[0], row).contains("SOGGY")))
			{
				System.out.println("D");
				flag = false;
			}
			else if(getNumOcc(names, "SASSY SALLY") == 1 && names[1].equals("SASSY SALLY") && !(colors.size() > 2 && getName(colors.get(aa - 2)[1], items.get(aa - 2)[1], row).contains("SOGGY")))
			{
				System.out.println("E");
				flag = false;
			}
			else if(getNumOcc(names, "SASSY SALLY") == 1 && names[2].equals("SASSY SALLY") && !(colors.size() > 2 && getName(colors.get(aa - 2)[2], items.get(aa - 2)[2], row).contains("SOGGY")))
			{
				System.out.println("F");
				flag = false;
			}
			else if(getNumOcc(names, "SOGGY STEVEN") >= 2)
			{
				System.out.println("G");
				flag = false;
			}
			else if(getNumOcc(names, "SIMON") == 3 && getNumOcc(names, "SASSY") == 0)
			{
				System.out.println("J");
				flag = false;
			}
			else if(names[0].contains("SAUSAGE") && names[1].contains("SALLY") && !(names[1].contains("SOGGY")))
			{
				System.out.println("K");
				flag = false;
			}
			else if(names[1].contains("SAUSAGE") && names[0].contains("SALLY") && !(names[0].contains("SOGGY")))
			{
				System.out.println("L");
				flag = false;
			}
			else if(names[1].contains("SAUSAGE") && names[2].contains("SALLY") && !(names[2].contains("SOGGY")))
			{
				System.out.println("M");
				flag = false;
			}
			else if(names[2].contains("SAUSAGE") && names[1].contains("SALLY") && !(names[1].contains("SOGGY")))
			{
				System.out.println("N");
				flag = false;
			}
			else if(getNumOcc(names, "SILLY") == 2 && getNumOcc(names, "SILLY STEVEN") < 2)
			{
				System.out.println("O");
				flag = false;
			}
			else if(getNumOcc(names, "SOGGY") == 1 && !(colors.size() > 1 && getNumOcc(colors.get(aa - 1), items.get(aa - 1), row, "SAUSAGE") > 0))
			{
				System.out.println("P");
				flag = false;
			}
			else if(colors.get(aa)[0].equals(colors.get(aa)[1]) && colors.get(aa)[0].equals(colors.get(aa)[2]) && items.get(aa)[0].equals(items.get(aa)[1]) && items.get(aa)[0].equals(items.get(aa)[2]) && !(findPrev(colors, items, row, "SOGGY SAUSAGE")))
			{
				System.out.println("Q");
				flag = false;
			}
			else if(colors.get(aa)[0].equals(colors.get(aa)[1]) && colors.get(aa)[0].equals(colors.get(aa)[2]) && getNumOcc(names, "SALLY") == 0 && !(colors.size() > 1 && getNumOcc(colors.get(aa - 1), items.get(aa - 1), row, "SILLY STEVEN") > 0))
			{
				System.out.println("R");
				flag = false;
			}
			else if(getNumOcc(names, "SILLY SIMON") > 0 && !(findPrev(colors, items, row, "SASSY SAUSAGE")))
			{
				System.out.println("S");
				flag = false;
			}
			
			if(flag)
			{
				JOptionPane.showMessageDialog(null, "Press KEEP");
				break;
			}
			else
				JOptionPane.showMessageDialog(null, "Pull the lever");
		}
		return souv;
	}
	private int getNumOcc(String[] n, String ph)
	{
		int sum = 0;
		for(int aa = 0; aa < n.length; aa++)
		{
			if(n[aa].contains(ph))
				sum++;
		}
		return sum;
	}
	private int getNumOcc(String[] c, String[] i, int row, String name)
	{
		int sum = 0;
		for(int aa = 0; aa < c.length; aa++)
		{
			String temp = getName(c[aa], i[aa], row);
			if(temp.contains(name))
				sum++;
		}
		return sum;
	}
	private boolean findPrev(ArrayList<String[]> c, ArrayList<String[]> i, int row, String name)
	{
		for(int aa = 0; aa < (c.size() - 1); aa++)
		{
			for(int bb = 0; bb < 3; bb++)
			{
				String temp = getName(c.get(aa)[bb], i.get(aa)[bb], row);
				if(temp.equals(name))
					return true;
			}
		}
		return false;
	}
	private String getName(String c, String i, int row)
	{
		String name = "";
		for(int aa = 0; aa < chart[row].length; aa++)
		{
			if(chart[row][aa].equals(c))
			{
				name = order[aa].toUpperCase();
				break;
			}
		}
		for(int aa = 0; aa < chart[row].length; aa++)
		{
			if(chart[row][aa].equals(i))
			{
				name = name + " " + order[aa].toUpperCase();
				break;
			}
		}
		return name;
	}
	private int wordToNum(String kw)
	{
		for(int aa = 0; aa < order.length; aa++)
		{
			if(order[aa].equals(kw))
				return aa;
		}
		return -1;
	}
	private boolean v1(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 3)
		{
			for(int aa = 0; aa < conv.length; aa++)
			{
				switch(conv[aa])
				{
					case "RED":
					case "BLUE":
					case "GREEN":
						break;
					default:
						return false;
				}
			}
			return true;
		}
		return false;
	}
	private boolean v2(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 3)
		{
			for(int aa = 0; aa < conv.length; aa++)
			{
				switch(conv[aa])
				{	
					case "COIN":
					case "BOMB":
					case "CHERRY":
					case "GRAPE":
						break;
					default:
						return false;
				}
			}
			return true;
		}
		return false;
	}
}
