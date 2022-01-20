package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class SafetySafe 
{
	private final int[][] table = 
		{
			{8, 10, 2, 11, 0, 4, 7, 8, 0, 2, 5, 1, 1, 9, 5, 3, 4, 8, 9, 7, 11, 11, 6, 4, 10, 3, 7, 9, 2, 10, 6, 6, 1, 0, 5, 3},
			{3, 1, 1, 6, 5, 2, 4, 3, 11, 11, 2, 9, 7, 5, 9, 10, 10, 0, 4, 6, 9, 11, 0, 2, 7, 7, 0, 10, 5, 8, 8, 3, 1, 6, 4, 8},
		    {4, 3, 1, 11, 5, 7, 4, 6, 0, 8, 5, 8, 9, 1, 8, 9, 6, 4, 0, 7, 6, 2, 11, 7, 10, 1, 3, 10, 11, 10, 0, 3, 5, 2, 9, 2},
		    {8, 7, 5, 11, 8, 7, 2, 6, 0, 0, 1, 11, 5, 4, 10, 1, 1, 0, 6, 11, 3, 8, 6, 2, 10, 10, 5, 9, 7, 4, 3, 3, 2, 4, 9, 9},
		    {9, 3, 3, 7, 2, 1, 10, 6, 9, 5, 0, 11, 6, 4, 2, 9, 4, 6, 3, 5, 11, 1, 11, 8, 8, 0, 8, 1, 7, 10, 5, 0, 7, 2, 10, 4},
		    {0, 8, 6, 7, 1, 5, 5, 5, 10, 6, 4, 11, 2, 9, 8, 7, 8, 11, 10, 3, 1, 0, 2, 10, 9, 4, 6, 2, 3, 4, 0, 11, 3, 1, 7, 9}
		};
	private final BombEdgework ew;
	public SafetySafe(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		int offset = ew.numPortTypes() * 7;
		offset += (ew.numLitWithChars(ew.getSN()) * 5);
		offset += ew.numUnlitWithChars(ew.getSN());
		int[] dialPos = {offset, offset, offset, offset, offset, offset};
		for(int aa = 0; aa < 5; aa++)
		{
			dialPos[aa] = (dialPos[aa] + table[aa]["ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".indexOf(ew.getSNCHAR(aa))]) % 12;
			dialPos[5] += table[5]["ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".indexOf(ew.getSNCHAR(aa))];
		}
		dialPos[5] = (dialPos[5] + table[5]["ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".indexOf(ew.getSNCHAR(5))]) % 12;
		JOptionPane.showMessageDialog(null, "Set each dial to the *click*\nThen set each dial to these offsets:\n" + dialPos[0] + " " + dialPos[1] + " " + dialPos[2] + "\n" + dialPos[3] + " " + dialPos[4] + " " + dialPos[5]);
	}
}