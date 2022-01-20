package modules;

import javax.swing.JOptionPane;

public class SeaShells 
{
	private final String[][] table =
		{
				{"BDABDAB", "ACEEAC", "EACEACE", "DAABDAB"},
				{"BEEBBE", "CDCCDB", "EAEAEA", "BEEDA"},
				{"ABABA", "EAAEEA", "DBEAC", "ABDBAA"},
				{"ACACEAC", "DBAEC", "EBDADAB", "CECEC"},
		};
	public String run()
	{
		String souv = "";
		for(int aa = 0; aa < 3; aa++)
		{
			souv = souv + "STAGE #" + (aa + 1) + ": ";
			String phrase = JOptionPane.showInputDialog("Stage #" + (aa + 1) + "\nH - She, Shells\nE - Sea, Sells\nEnter the phrase:").toUpperCase().replace(" ", "");
			boolean v = valid(phrase);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				phrase = JOptionPane.showInputDialog("H - She, Shells\nE - Sea, Sells\nEnter the phrase:").toUpperCase().replace(" ", "");
				v = valid(phrase);
			}
			String[] key = getABCDE(phrase.substring(4));
			//Get the row and col
			int[] rowcol = {3, 3};
			String[][] list = {{"HE", "HH", "EH"}, {"EH", "HH", "EE"}};
			for(int bb = 0; bb < 2; bb++)
			{
				for(int cc = 0; cc < 3; cc++)
				{
					if(phrase.substring(bb * 2, (bb * 2) + 2).equals(list[bb][cc]))
					{
						rowcol[bb] = cc;
						break;
					}
				}
			}
			//Get the button presses
			String out = key["ABCDE".indexOf(table[rowcol[0]][rowcol[1]].charAt(0))];
			for(int bb = 1; bb < table[rowcol[0]][rowcol[1]].length(); bb++)
				out = out + "\n" + key["ABCDE".indexOf(table[rowcol[0]][rowcol[1]].charAt(bb))];
			JOptionPane.showMessageDialog(null, "Press these buttons:\n" + out);
			//Save souvenir data
			for(int bb = 0; bb < 2; bb++)
			{
				switch(phrase.substring(bb * 2, (bb * 2) + 2))
				{
					case "HH":
						souv = souv + "SHE SHELLS ";
						break;
					case "HE":
						souv = souv + "SHE SELLS ";
						break;
					case "EH":
						souv = souv + "SEA SHELLS ";
						break;
					default:
						souv = souv + "SEA SELLS ";
						break;
				}
			}
			switch(key[0])
			{
				case "SHOE":
					souv = souv + "ON THE SEA SHORE\n";
					break;
				case "CAN":
					souv = souv + "ON THE SHE SORE\n";
					break;
				case "WITCH":
					souv = souv + "ON THE SHE SURE\n";
					break;
				default:
					souv = souv + "ON THE SEESAW\n";
					break;
			}
		}
		return souv;
	}
	//Returns the values of A, B, C, D, and E
	private String[] getABCDE(String phrase)
	{
		switch(phrase)
		{
			case "SHORE":
			case "SHIHTZU":
			case "SHIH":
			case "TZU":
			case "SHOE":
			case "SHE":
			case "SIT":
			case "SUSHI":
				return new String[] {"SHOE", "SHIH TZU", "SHE", "SIT", "SUSHI"};
			case "SORE":
			case "TOUCAN":
			case "CAN":
			case "TUTU":
			case "2":
			case "CANCAN":
				return new String[] {"CAN", "TOUCAN", "TUTU", "2", "CANCAN"};
			case "SURE":
			case "SWITCH":
			case "WITCH":
			case "ITCH":
			case "TWITCH":
			case "STITCH":
				return new String[] {"WITCH", "SWITCH", "ITCH", "TWITCH", "STITCH"};
			case "SEESAW":
			case "BURGLARALARM":
			case "BURGLAR":
			case "ALARM":
			case "BULGARIA":
			case "ARMOUR":
			case "BURGER":
			case "LLAMA":
				return new String[] {"BURGLAR ALARM", "BULGARIA", "ARMOUR", "BURGER", "LLAMA"};
			default:
				return null;
		}
	}
	private boolean valid(String i)
	{
		if(i.length() > 4)
		{
			for(int aa = 0; aa < 4; aa++)
			{
				if("HE".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			if(getABCDE(i.substring(4)) == null)
				return false;
			return true;
		}
		return false;
	}
}

