package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class ComplicatedButtons 
{
	private final String[] table1 =
		{
			"PRRR",
			"PPDS",
			"DBSR",
			"BBSD"
		};
	private final int[][][] table2 =
		{
				{{0, 1, 2}, {1, 2, 0}, {2, 0, 1}, {0, 1, 2}},
				{{1, 0, 2}, {2, 1, 0}, {0, 2, 1}, {1, 2, 0}},
				{{2, 0, 1}, {0, 1, 2}, {1, 0, 2}, {2, 0, 1}}
		};
	private final BombEdgework ew;
	public ComplicatedButtons(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		String colors = JOptionPane.showInputDialog("W - White\nR - Red\nB - Blue\nP - Purple\nEnter the button colors (top to bottom:").toUpperCase();
		boolean v = valid(colors, "WRBP");
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			colors = JOptionPane.showInputDialog("W - White\nR - Red\nB - Blue\nP - Purple\nEnter the button colors (top to bottom:").toUpperCase();
			v = valid(colors, "WRBP");
		}
		String labels = JOptionPane.showInputDialog("P - Press\nH - Hold\nD - Detonate\nEnter the button labels (top to bottom):").toUpperCase();
		v = valid(labels, "PHD");
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			labels = JOptionPane.showInputDialog("P - Press\nH - Hold\nD - Detonate\nEnter the button labels (top to bottom):").toUpperCase();
			v = valid(labels, "PHD");
		}
		int[] order;
		int col = ew.BAT() / 2;
		if(col > 3)
			col = 3;
		switch(labels.charAt(0))
		{
			case 'P':
				order = table2[0][col];
				break;
			case 'H':
				order = table2[1][col];
				break;
			default:
				order = table2[2][col];
				break;
		}
		String out = "";
		for(int aa = 0; aa < 3; aa++)
		{
			col = "WRBP".indexOf(colors.charAt(order[aa]));
			int row = 0;
			if(labels.charAt(order[aa]) == 'P')
				row += 1;
			if(order[aa] == 1)
				row += 2;
			switch(table1[row].charAt(col))
			{
				case 'P':
					out = out + "" + (order[aa] + 1) + " ";
					break;
				case 'R':
					if(SNDupChars())
						out = out + "" + (order[aa] + 1) + " ";
					break;
				case 'S':
					if(ew.findPort("SERIAL") > 0)
						out = out + "" + (order[aa] + 1) + " ";
					break;
				case 'B':
					if(ew.BH() >= 2)
						out = out + "" + (order[aa] + 1) + " ";
					break;
			}
		}
		//0System.out.println(order[0] + " " + order[1] + " " + order[2]);
		if(out.length() == 0)
			out = (order[1] + 1) + "";
		JOptionPane.showMessageDialog(null, "Press the(se) button(s): " + out);
	}
	private boolean SNDupChars()
	{
		String sn = ew.getSN();
		for(int aa = 0; aa < sn.length(); aa++)
		{
			for(int bb = aa + 1; bb < sn.length(); bb++)
			{
				if(sn.charAt(aa) == sn.charAt(bb))
					return true;
			}
		}
		return false;
	}
	private boolean valid(String i, String choice)
	{
		if(i.length() == 3)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if(choice.indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
}
