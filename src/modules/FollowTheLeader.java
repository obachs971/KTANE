package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import misc.PlayType;
import start.BombEdgework;

public class FollowTheLeader 
{
	private final BombEdgework ew;
	private final PlayType playType;
	public FollowTheLeader(BombEdgework e, PlayType pt)
	{
		ew = e;
		playType = pt;
	}
	public void run()
	{
		if(playType == PlayType.Team)//Team play type
		{
			String[] ruleList = {
				"The previous wire is NOT\nYellow, Blue, or Green",
				"The previous wire leads to\nan EVEN numbered plug",
				"The previous wire SHOULD be cut",
				"The previous wire is\nRed, Blue, or Black",
				"TWO of the previous THREE\nwires SHARE a color",
				"EXACTLY ONE of the previous\nTWO wires is the SAME\ncolor as this wire",
				"The previous wire is\nYellow, White, or Green",
				"The previous wire SHOULD NOT be cut",
				"The previous wire SKIPS a plug",
				"The previous wire is NOT\nWhite, Black, or Red",
				"The previous TWO wires\nare DIFFERENT colors",
				"The previous wire does NOT\nlead to a position labeled\n6 OR LESS",
				"EXACTLY ONE OR NONE of the\nprevious TWO wires are\nWhite or Black"
			};
			String out;
			if(ew.findPort("RJ-45") > 0 && JOptionPane.showConfirmDialog(null, "Is there a wire going\nfrom plug #4 to #5?") == 0)
				out = "Cut the wire at plug #4";
			else if(ew.BAT() > 0 && ew.BAT() < 13 && JOptionPane.showConfirmDialog(null, "Is there a wire at plug #" + ew.BAT() + "?") == 0)
				out = "Cut the wire at plug #" + ew.BAT();
			else if(ew.getSNDIG(0) > 0 && ew.getSNDIG(0) != ew.BAT() && JOptionPane.showConfirmDialog(null, "Is there a wire at plug #" + ew.getSNDIG(0) + "?") == 0)
				out = "Cut the wire at plug #" + ew.getSNDIG(0);
			else if(ew.findLit("CLR"))
				out = "UNICORN";
			else
				out = "Cut the wire at lowest plug";
			if(out.equals("UNICORN"))
				JOptionPane.showMessageDialog(null, "Cut all the wires\nin descending order");
			else
			{
				int offset = 1;
				String input = JOptionPane.showInputDialog(out + "\nEnter that wire color:").toUpperCase();
				boolean v = v2(out);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog(out + "\nEnter that wire color:").toUpperCase();
					v = v2(out);
				}
				if(input.equals("RED") || input.equals("GREEN") || input.equals("WHITE"))
					offset = -1;
				int ruleCur = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(ew.getSNLET(0))) % 13;
				for(int aa = 0; aa < 11; aa++)
				{
					input = JOptionPane.showInputDialog("S - STOP\n" + ruleList[ruleCur]).toUpperCase();
					if(input.equals("S") || input.equals("STOP"))
						break;
					ruleCur = mod(ruleCur + offset, 13);
				}
			}
		}
		else//Other playtype
		{
			//Setting up the plugs
			ArrayList<Integer> plugs = new ArrayList<Integer>();
			for(int aa = 1; aa <= 12; aa++)
				plugs.add(aa + 0);
			String out = "", input = JOptionPane.showInputDialog("Enter the plugs that\nare NOT connected (spaces):");
			boolean v = v1(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the plugs that\nare NOT connected (spaces):");
				v = v1(input);
			}
			String[] temp = input.split(" ");
			for(int aa = 0; aa < temp.length; aa++)
				plugs.remove(plugs.indexOf(Integer.parseInt(temp[aa]))); 
			//Entering the color for each plug
			ArrayList<String> colors = new ArrayList<String>();
			ArrayList<Boolean> cut = new ArrayList<Boolean>();
			for(int aa = 0; aa < plugs.size(); aa++)
			{
				input = JOptionPane.showInputDialog("Red, Blue, Yellow\nGreen, White, Black\nEnter the wire color\nat plug #" + (plugs.get(aa)) + ":").toUpperCase();
				v = v2(input);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog("Red, Blue, Yellow\nGreen, White, Black\nEnter the wire color\nat plug #" + (plugs.get(aa)) + ":").toUpperCase();
					v = v2(input);
				}
				colors.add(input.toUpperCase());
				cut.add(false);
			}
			//Find the starting plug
			int wireCur;
			if(ew.findPort("RJ-45") > 0 && plugs.contains(4) && plugs.contains(5))
				wireCur = plugs.indexOf(4);
			else if(plugs.contains(ew.BAT()))
				wireCur = plugs.indexOf(ew.BAT());
			else if(plugs.contains(ew.getSNDIG(0)))
				wireCur = plugs.indexOf(ew.getSNDIG(0));
			else if(ew.findLit("CLR"))
				wireCur = -1;
			else
				wireCur = 0;
			
			//If none of the conditions apply and there is a Lit CLR
			if(wireCur == -1)
			{
				for(int aa = plugs.size() - 1; aa >= 0; aa--)
					out = out + "" + plugs.get(aa) + " ";
			}
			else//Start going through the rules for each wire/plug
			{
				cut.set(wireCur, true);
				out = plugs.get(wireCur) + "";
				int ruleCur = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(ew.getSNLET(0))) % 13;
				int ruleOffset = 1;
				if(colors.get(wireCur).equals("RED") || colors.get(wireCur).equals("GREEN") || colors.get(wireCur).equals("WHITE"))
					ruleOffset = -1;
				wireCur = mod(wireCur + 1, plugs.size());
				for(int aa = 0; aa < plugs.size() - 1; aa++)
				{
					//System.out.println(ruleCur);
					int prev = mod(wireCur - 1, plugs.size());
					switch(ruleCur)
					{
						case 0:
							if(!(colors.get(prev).equals("YELLOW") || colors.get(prev).equals("BLUE") || colors.get(prev).equals("GREEN")))
								cut.set(wireCur, true);
							break;
						case 1:
							if(plugs.get(wireCur) % 2 == 0)
								cut.set(wireCur, true);
							break;
						case 2:
							if(cut.get(prev))
								cut.set(wireCur, true);
							break;
						case 3:
							if(colors.get(prev).equals("RED") || colors.get(prev).equals("BLUE") || colors.get(prev).equals("BLACK"))
								cut.set(wireCur, true);
							break;
						case 4:
							ArrayList<String> prevColors = new ArrayList<String>();
							for(int bb = 1; bb <= 3; bb++)
								prevColors.add(colors.get(mod(wireCur - bb, plugs.size())).toUpperCase());
							if(prevColors.get(0).equals(prevColors.get(1)))
								cut.set(wireCur, true);
							else if(prevColors.get(0).equals(prevColors.get(2)))
								cut.set(wireCur, true);
							else if(prevColors.get(1).equals(prevColors.get(2)))
								cut.set(wireCur, true);
							break;
						case 5:
							int prev2 = mod(prev - 1, plugs.size());
							if(colors.get(wireCur).equals(colors.get(prev)) && !(colors.get(wireCur).equals(colors.get(prev2))))
								cut.set(wireCur, true);
							else if(colors.get(wireCur).equals(colors.get(prev2)) && !(colors.get(wireCur).equals(colors.get(prev))))
								cut.set(wireCur, true);
							break;
						case 6:
							if(colors.get(prev).equals("YELLOW") || colors.get(prev).equals("WHITE") || colors.get(prev).equals("GREEN"))
								cut.set(wireCur, true);
							break;
						case 7:
							if(!(cut.get(prev)))
								cut.set(wireCur, true);
							break;
						case 8:
							if(plugs.get(wireCur) == 1)
							{
								if(plugs.get(prev) != 12)
									cut.set(wireCur, true);
							}
							else if((plugs.get(wireCur) - plugs.get(prev)) != 1)
								cut.set(wireCur, true);
							break;
						case 9:
							if(!(colors.get(prev).equals("WHITE") || colors.get(prev).equals("BLACK") || colors.get(prev).equals("RED")))
								cut.set(wireCur, true);
							break;
						case 10:
							if(!(colors.get(prev).equals(colors.get(mod(prev - 1, plugs.size())))))
								cut.set(wireCur, true);
							break;
						case 11:
							if(plugs.get(wireCur) > 6)
								cut.set(wireCur, true);
							break;
						case 12:
							int sum = 0;
							//System.out.println(prev);
							for(int bb = 0; bb < 2; bb++)
							{
								if(colors.get(mod(prev - bb, plugs.size())).equals("WHITE") || colors.get(mod(prev - bb, plugs.size())).equals("BLACK"))
									sum++;
							}
							if(sum < 2)
								cut.set(wireCur, true);
							break;
					}
					if(cut.get(wireCur))
						out = out + " " + plugs.get(wireCur);
					wireCur = mod(wireCur + 1, plugs.size());
					ruleCur = mod(ruleCur + ruleOffset, 13);
				}
			}
			JOptionPane.showMessageDialog(null, "Cut these wires:\n" + out);
		}
	}
	private int mod(int n, int m)
	{
		while(n < 0)
			n += m;
		return (n % m);
	}
	private boolean v1(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length < 11)
		{
			for(int aa = 0; aa < conv.length; aa++)
			{
				switch(conv[aa])
				{
					case "1":
					case "2":
					case "3":
					case "4":
					case "5":
					case "6":
					case "7":
					case "8":
					case "9":
					case "10":
					case "11":
					case "12":
						break;
					default:
						return false;
				}
			}
			for(int aa = 0; aa < conv.length; aa++)
			{
				for(int bb = aa + 1; bb < conv.length; bb++)
				{
					if(conv[aa].equals(conv[bb]))
						return false;
				}
			}
			return true;
		}
		return false;
	}
	private boolean v2(String i)
	{
		switch(i)
		{
			case "RED":
			case "BLUE":
			case "YELLOW":
			case "GREEN":
			case "WHITE":
			case "BLACK":
				return true;
			default:
				return false;
		}
	}
}
