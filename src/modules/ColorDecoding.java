package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class ColorDecoding 
{
	private String[][][] table =
		{
				{{"RGBY", "PBYR"}, {"AC", "B", "BE", "BD", "D", "CE"}},
				{{"GRPY", "BYGP"}, {"C", "AD", "AB", "AE", "BD", "AD"}},
				{{"YPRB", "GBPR"}, {"D", "AC", "BE", "CE", "A", "CD"}},
				{{"PGBR", "YRGP"}, {"AE", "BD", "C", "E", "AD", "BC"}}
		};
	private final int[] vennTable =	{3, 8, 2, 6, 5, 2, 4, 5, 7, 3, 6, 1, 1, 4, 8, 7};
	private final String[][] sets =
		{
				{"BGB", "BBY", "No R", "YPG", "YGB"},
				{"PYP", "No G", "YYR", "RPY", "BPR"},
				{"BPY", "PPB", "PRP", "No G", "RBR"},
				{"GGB", "YRG", "No P", "BYB", "RGB"},
				{"GGY", "RGG", "YRP", "PRR", "No B"},
				{"PGG", "YRR", "No B", "YYG", "YGR"},
				{"BBG", "BYG", "PYY", "No R", "YBG"},
				{"PGB", "No Y", "PPG", "BRG", "RGR"}
		};
	private int[] vals;
	public ColorDecoding(BombEdgework e)
	{
		vals = new int[4];
		vals[0] = e.BAT();
		vals[1] = e.numPorts();
		vals[2] = e.numLit();
	}
	public String run()
	{
		String souv = "";
		for(int i = 0; i < 3; i++)
		{
			souv = souv + "STAGE #" + (i + 1) + ":\n";
			vals[3] = (i + 2) % 4;
			String input = JOptionPane.showInputDialog("S - Solid\nV - Vertical\nH - Horizontal\nC - Checkered\nR - Red\nB - Blue\nY - Yellow\nG - Green\nP - Purple\nEnter the pattern/colors:").toUpperCase().replace(" ", "");
			boolean v = valid(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("S - Solid\nV - Vertical\nH - Horizontal\nC - Checkered\nR - Red\nB - Blue\nY - Yellow\nG - Green\nP - Purple\nEnter the pattern/colors:").toUpperCase().replace(" ", "");
				v = valid(input);
			}
			int pattern = -1;
			String colors = "";
			for(char c : input.toCharArray())
			{
				if("CVHS".indexOf(c) >= 0)
					pattern = "CVHS".indexOf(c);
				else
					colors = colors + "" + c;
			}
			String order, skip;
			if(vals[pattern] <= 2)
			{
				order = table[pattern][0][0];
				skip = table[pattern][1][i];
			}
			else
			{
				order = table[pattern][0][1];
				skip = table[pattern][1][i + 3];
			}
			int mult = 1, sum = 0;
			for(char c : order.toCharArray())
			{
				if(colors.indexOf(c) >= 0)
					sum += mult;
				mult *=2;
			}
			int set = vennTable[sum] - 1;
			String out = "Select the row/col that\nsatisfies each constraint:";
			for(int j = 0; j < 5; j++)
			{
				if(skip.indexOf("ABCDE".charAt(j)) < 0)
					out = out + "\n" + sets[set][j];
			}
			JOptionPane.showMessageDialog(null, out);
			souv = souv + "" + new String[] {"CHECKERED", "VERTICAL", "HORIZONTAL", "SOLID"}["CVHS".charAt(pattern)] + "\n";
			souv = souv + "COLORS: " + colors.toUpperCase() + "\n--------------------\n";
		}
		return souv;
	}
	private boolean valid(String i)
	{
		if(i.length() >= 2)
		{
			boolean b1 = false, b2 = false;
			for(int aa = 0; aa < i.length(); aa++)
			{
				switch(i.charAt(aa))
				{
					case 'C':case 'V':case 'H':case 'S':
						if(b1)
							return false;
						b1 = true;
						break;
					case 'R':case 'B':case 'Y':case 'G':case 'P':
						b2 = true;
						break;
					default:
						return false;
				}
			}
			return (b1 && b2);
		}
		return false;
	}
}
