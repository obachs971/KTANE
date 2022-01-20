package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class RockPaperScissorsLizardSpock 
{
	private final BombEdgework ew;
	public RockPaperScissorsLizardSpock(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		String input = JOptionPane.showInputDialog("Rock, Paper, Scissors,\nLizard, Spock\nEnter the decoy:").toUpperCase();
		int decoy = getDecoy(input);
		while(decoy == -1)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Rock, Paper, Scissors,\nLizard, Spock\nEnter the decoy:").toUpperCase();
			decoy = getDecoy(input);
		}
		int sign = row1(decoy);
		String out;
		if(sign == decoy)
		{
			sign--;
			out = "";
			String[] signs = {"Rock", "Paper", "Scissors", "Lizard", "Spock"};
			for(int aa = 0; aa < signs.length; aa++)
			{
				if(aa != sign)
					out = out + "" + signs[aa] + "\n";
			};
		}
		else
		{
			switch(sign)
			{
				case 1:
					out = "Paper\nSpock";
					break;
				case 2:
					out = "Scissors\nLizard";
					break;
				case 3:
					out = "Spock\nRock";
					break;
				case 4:
					out = "Rock\nScissors";
					break;
				default:
					out = "Lizard\nPaper";
					break;
			}
		}
		JOptionPane.showMessageDialog(null, "Press these signs:\n" + out);
	}
	private int getDecoy(String i)
	{
		String[] list = {"", "ROCK", "PAPER", "SCISSORS", "LIZARD", "SPOCK"};
		for(int aa = 0; aa < list.length; aa++)
		{
			if(i.equals(list[aa]))
				return aa;
		}
		return -1;
	}
	private int row1(int decoy)
	{
		if(ew.numCharsInSN("XY") > 0)
			return row2(decoy);
		int[] scores = {0, ew.numCharsInSN("RO"), ew.numCharsInSN("PA"), ew.numCharsInSN("SI"), ew.numCharsInSN("LZ"), ew.numCharsInSN("CK")};
		int best = getBest(scores, decoy);
		if(best == decoy)
			return row2(decoy);
		else
			return best;
	}
	private int row2(int decoy)
	{
		if(ew.findPort("PS/2") > 0)
			return row3(decoy);
		
		int[] scores = {0, ew.findPort("RJ-45"), ew.findPort("PARALLEL"), ew.findPort("SERIAL"), ew.findPort("DVI-D"), ew.findPort("RCA")};
		int best = getBest(scores, decoy);
		if(best == decoy)
			return row3(decoy);
		else
			return best;
	}
	private int row3(int decoy)
	{
		if(ew.findLit("TRN"))
			return row5(decoy);
		
		int[] scores = {0, 0, 0, 0, 0, 0};
		if(ew.findLit("FRK"))
			scores[1]++;
		if(ew.findLit("FRQ"))
			scores[1]++;
		if(ew.findLit("BOB"))
			scores[2]++;
		if(ew.findLit("IND"))
			scores[2]++;
		if(ew.findLit("CAR"))
			scores[3]++;
		if(ew.findLit("SIG"))
			scores[3]++;
		if(ew.findLit("CLR"))
			scores[4]++;
		if(ew.findLit("NSA"))
			scores[4]++;
		if(ew.findLit("SND"))
			scores[5]++;
		if(ew.findLit("MSA"))
			scores[5]++;
		int best = getBest(scores, decoy);
		if(best == decoy)
			return row4(decoy);
		else
			return best;
	}
	private int row4(int decoy)
	{
		if(ew.findUnlit("TRN"))
			return row5(decoy);
		
		int[] scores = {0, 0, 0, 0, 0, 0};
		if(ew.findUnlit("FRK"))
			scores[1]++;
		if(ew.findUnlit("FRQ"))
			scores[1]++;
		if(ew.findUnlit("BOB"))
			scores[2]++;
		if(ew.findUnlit("IND"))
			scores[2]++;
		if(ew.findUnlit("CAR"))
			scores[3]++;
		if(ew.findUnlit("SIG"))
			scores[3]++;
		if(ew.findUnlit("CLR"))
			scores[4]++;
		if(ew.findUnlit("NSA"))
			scores[4]++;
		if(ew.findUnlit("SND"))
			scores[5]++;
		if(ew.findUnlit("MSA"))
			scores[5]++;
		int best = getBest(scores, decoy);
		if(best == decoy)
			return row5(decoy);
		else
			return best;
	}
	private int row5(int decoy)
	{
		int[] scores = {0, ew.numCharsInSN("05"), ew.numCharsInSN("36"), ew.numCharsInSN("19"), ew.numCharsInSN("28"), ew.numCharsInSN("47")};
		return getBest(scores, decoy);
	}
	private int getBest(int[] scores, int decoy)
	{
		int best = scores[0], numBest = 1, bestCur = 0;
		for(int aa = 1; aa < scores.length; aa++)
		{
			//System.out.print(scores[aa] + " ");
			if(scores[aa] > best)
			{
				best = scores[aa];
				numBest = 1;
				bestCur = aa;
			}
			else if(best == scores[aa])
				numBest++;
		}
		//System.out.println(numBest);
		if(numBest > 1)
			return decoy;
		else
			return bestCur;	
	}
}
