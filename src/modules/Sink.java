package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class Sink 
{
	private final int[][][] table =
		{
				{{2, 1, 4}, {1, 0}},
				{{3, 6, 2}, {0, 1}},
				{{5, 3, 1}, {0, 1}},
				{{5, 6, 4}, {1, 0}},
		};
	private final BombEdgework ew;
	public Sink(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		int[][] row;
		if(ew.BAT() < 6)
			row = table[ew.BAT() / 2];
		else
			row = table[3];
		String out = "";
		for(int rule : row[0])
		{
			int bool;
			switch(rule)
			{
				case 1: bool = ew.findUnlit("NSA") ? 0 : 1; break;
				case 2: bool = ew.numCharsInSN("AEIOU") > 0 ? 0 : 1; break;
				case 3: bool = JOptionPane.showConfirmDialog(null, "Knobs are Gold-Plated?") == 0 ? 0 : 1;  break;
				case 4: bool = JOptionPane.showConfirmDialog(null, "Faucet is Stainless Steel?") == 0 ? 0 : 1;  break;
				case 5: bool = JOptionPane.showConfirmDialog(null, "Drain Pipe is Copper?") == 0 ? 0 : 1;  break;
				default: bool = (ew.findPort("HDMI") > 0 || ew.findPort("RJ-45") > 0) ? 0 : 1;  break;
			}
			out = out + "" + "CH".charAt(row[1][bool]) + " ";
		}
		JOptionPane.showMessageDialog(null, "Press these knobs: " + out);
	}
}
