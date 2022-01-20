package modules;

import javax.swing.JOptionPane;

public class WireSequence 
{
	private final int playType;
	public WireSequence(int pt)
	{
		playType = pt;
	}
	public String run()
	{
		String[][] table =
			{
					{"C", "B", "A", "AC", "B", "AC", "ABC", "AB", "B"},
					{"B", "AC", "B", "A", "B", "BC", "C", "AC", "A"},
					{"ABC", "AC", "B", "AC", "B", "BC", "AB", "C", "C"}
			};
		int[] rbk = {0, 0, 0};
		if(playType == 1)
		{
			int counter = 1;
			while(true)
			{
				String input = JOptionPane.showInputDialog("R - Red\nB - Blue\nK - Black\nEnter wire #" + counter + ":").toUpperCase();
				boolean v = v1(input);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog("R - Red\nB - Blue\nK - Black\nEnter wire #" + counter + ":").toUpperCase();
					v = v1(input);
				}
				if(input.length() == 0)
					break;
				if(input.length() == 1)
				{
					int cursor = "RBK".indexOf(input);
					JOptionPane.showMessageDialog(null, "Cut if connected to " + (table[cursor][rbk[cursor]]));
					rbk[cursor]++;
				}
				else
				{
					int cursor = "RBK".indexOf(input.charAt(0));
					if(table[cursor][rbk[cursor]].contains(input.charAt(1) + ""))
						JOptionPane.showMessageDialog(null, "Cut the wire");
					else
						JOptionPane.showMessageDialog(null, "Do not cut the wire");
					rbk[cursor]++;
				}
				counter++;
			}
		}
		else
		{
			for(int aa = 0; aa < 4; aa++)
			{
				String input = JOptionPane.showInputDialog("R - Red\nB - Blue\nK - Black\nNN - Nothing\nEnter the wires on\n panel #" + (aa + 1) + " (spaces):").toUpperCase();
				boolean v = v2(input);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog("R - Red\nB - Blue\nK - Black\nNN - Nothing\nEnter the wires on\n panel #" + (aa + 1) + " (spaces):").toUpperCase();
					v = v2(input);
				}
				if(input.length() > 0)
				{
					String[] temp = input.split(" ");
					String cut = "";
					for(int bb = 0; bb < temp.length; bb++)
					{
						if(!(temp[bb].equals("NN")))
						{
							int cursor = "RBK".indexOf(temp[bb].charAt(0));
							if(table[cursor][rbk[cursor]].contains(temp[bb].charAt(1) + ""))
								cut = cut + "" + (bb + 1);
							rbk[cursor]++;
						}
					}
					if(cut.length() == 0)
						JOptionPane.showMessageDialog(null, "Don't cut any wire");
					else
						JOptionPane.showMessageDialog(null, "Cut wires " + cut);
				}
			}
		}
		return ("RED: " + rbk[0] + "\nBLUE: " + rbk[1] + "\nBLACK: " + rbk[2]);	
	}
	private boolean v1(String i)
	{
		if(i.length() == 0)
			return true;
		else if(i.length() == 1)
		{
			switch(i)
			{
				case "R":
				case "B":
				case "K":
					return true;
			}
		}
		else if(i.length() == 2)
		{
			switch(i.charAt(0))
			{
				case 'R':
				case 'B':
				case 'K':
					break;
				default:
					return false;
			}
			switch(i.charAt(1))
			{
				case 'A':
				case 'B':
				case 'C':
					return true;
			}
		}
		return false;
	}
	private boolean v2(String i)
	{
		if(i.length() == 0)
			return true;
		String[] temp = i.split(" ");
		if(temp.length < 4)
		{
			for(int aa = 0; aa < temp.length; aa++)
			{
				if(temp[aa].length() == 2)
				{
					if(!(temp[aa].equals("NN")))
					{
						if("RBK".indexOf(temp[aa].charAt(0)) < 0)
							return false;
						if("ABC".indexOf(temp[aa].charAt(1)) < 0)
							return false;
					}
				}
				else
					return false;
			}
			return true;
		}
		return false;
	}
}
