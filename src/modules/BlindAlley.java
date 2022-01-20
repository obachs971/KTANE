package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class BlindAlley 
{
	private final BombEdgework ew;
	public BlindAlley(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		int[] scores = {0, 0, 0, 0, 0, 0, 0, 0};
		if(ew.findUnlit("BOB"))
			scores[0]++;
		if(ew.findLit("CAR"))
			scores[0]++;
		if(ew.findLit("IND"))
			scores[0]++;
		if(ew.BH() % 2 == 0)
			scores[0]++;
		
		if(ew.findUnlit("CAR"))
			scores[1]++;
		if(ew.findUnlit("NSA"))
			scores[1]++;
		if(ew.findLit("FRK"))
			scores[1]++;
		if(ew.findPort("RJ-45") > 0)
			scores[1]++;
		
		if(ew.findUnlit("FRQ"))
			scores[2]++;
		if(ew.findUnlit("IND"))
			scores[2]++;
		if(ew.findUnlit("TRN"))
			scores[2]++;
		if(ew.findPort("DVI-D") > 0)
			scores[2]++;
		
		if(ew.findUnlit("SIG"))
			scores[3]++;
		if(ew.findUnlit("SND"))
			scores[3]++;
		if(ew.findLit("NSA"))
			scores[3]++;
		if(ew.BAT() % 2 == 0)
			scores[3]++;
		
		if(ew.findLit("BOB"))
			scores[4]++;
		if(ew.findLit("CLR"))
			scores[4]++;
		if(ew.findPort("PS/2") > 0)
			scores[4]++;
		if(ew.findPort("SERIAL") > 0)
			scores[4]++;
		
		if(ew.findLit("FRQ"))
			scores[5]++;
		if(ew.findLit("SIG"))
			scores[5]++;
		if(ew.findLit("TRN"))
			scores[5]++;
		if(ew.numCharsInSN("02468") > 0)
			scores[5]++;
		
		if(ew.findUnlit("FRK"))
			scores[6]++;
		if(ew.findLit("MSA"))
			scores[6]++;
		if(ew.findPort("PARALLEL") > 0)
			scores[6]++;
		if(ew.numCharsInSN("AEIOU") > 0)
			scores[6]++;
		
		if(ew.findUnlit("CLR"))
			scores[7]++;
		if(ew.findUnlit("MSA"))
			scores[7]++;
		if(ew.findLit("SND"))
			scores[7]++;
		if(ew.findPort("RCA") > 0)
			scores[7]++;
		int best = scores[0];
		String[] pos = {"TL", "TM", "ML", "MM", "MR", "BL", "BM", "BR"};
		String press = pos[0].toUpperCase();
		for(int aa = 1; aa < scores.length; aa++)
		{
			if(scores[aa] > best)
			{
				best = scores[aa];
				press = pos[aa].toUpperCase();
			}
			else if(scores[aa] == best)
				press = press + ", " + pos[aa];
		}
		JOptionPane.showMessageDialog(null, "Press these positions:\n" + press);
	}
}
