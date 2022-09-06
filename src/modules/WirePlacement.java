package modules;

import javax.swing.JOptionPane;

import misc.PlayType;

public class WirePlacement 
{
	private final PlayType pt;
	public WirePlacement(PlayType p)
	{
		pt = p;
	}
	public void run()
	{
		if(pt == PlayType.Team)
		{
			String input = JOptionPane.showInputDialog("Red, Blue, Yellow,\nWhite, Black\nEnter the color at C3:").toUpperCase();
			input = v1(input);
			while(input.length() == 0)
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Red, Blue, Yellow,\nWhite, Black\nEnter the color at C3:").toUpperCase();
				input = v1(input);
			}
			String[] list = getList(input.charAt(0));
			String[] RBYWK = {"", "", "", "", "", ""};
			for(int aa = 0; aa < 4; aa++)
			{
				for(int bb = 0; bb < 4; bb++)
				{
					int cur = "RBYWK-".indexOf(list[bb].charAt(aa));
					RBYWK[cur] = RBYWK[cur] + "" + "ABCD".charAt(aa) + "" + (bb + 1) + " ";
				}
			}
			JOptionPane.showMessageDialog(null, "Red: " + RBYWK[0] + "\nBlue: " + RBYWK[1] + "\nYellow: "  + RBYWK[2] + "\nWhite: " + RBYWK[3] + "\nBlack: "  + RBYWK[4]);
		}
		else
		{
			String[] wires = new String[4];
			for(int aa = 0; aa < 4; aa++)
			{
				wires[aa] = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nW - White\nK - Black\nEnter the colors on row #" + (aa + 1) + ":").toUpperCase();
				boolean v = v2(wires[aa]);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					wires[aa] = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nW - White\nK - Black\nEnter the colors on row #" + (aa + 1) + ":").toUpperCase();
					v = v2(wires[aa]);
				}
			}
			String[] list = getList(wires[2].charAt(2));
			String out = "";
			for(int aa = 0; aa < list.length; aa++)
			{
				for(int bb = 0; bb < list[aa].length(); bb++)
				{
					if(list[aa].charAt(bb) == wires[aa].charAt(bb))
						out = out + "" + "ABCD".charAt(bb) + "" + (aa + 1) + " ";
				}
			}
			JOptionPane.showMessageDialog(null, "Cut these wires:\n" + out);
		}
	}
	private String[] getList(char c)
	{
		switch(c)
		{
			case 'K':
				return new String[] {"RK-Y", "BW-Y", "Y-BW", "--R-"};
			case 'B':
				return new String[] {"Y-WY", "--BW", "-RYR", "--BK"};
			case 'R':
				return new String[] {"BRB-", "YY-Y", "----", "KWRW"};
			case 'W':
				return new String[] {"W-R-", "YR-K", "-W-B", "YYB-"};
			case 'Y':
				return new String[] {"-BWY", "-WR-", "YR--", "YK-B"};	
		}
		return null;
	}
	private String v1(String i)
	{
		switch(i)
		{
			case "RED":
			case "R":
				return "R";
			case "BLUE":
			case "B":
				return "B";
			case "YELLOW":
			case "Y":
				return "Y";
			case "WHITE":
			case "W":
				return "W";
			case "BLACK":
			case "K":
				return "K";
		}
		return "";
	}
	private boolean v2(String i)
	{
		if(i.length() == 4)
		{
			for(int aa = 0; aa < 4; aa++)
			{
				if("RBYWK".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
}
