package modules;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import start.BombConfig;
import start.BombEdgework;

public class WasteManagement 
{
	private final BombConfig cf;
	private final BombEdgework ew;
	public WasteManagement(BombConfig c, BombEdgework e)
	{
		cf = c;
		ew = e;
	}
	public void run()
	{
		boolean fmn = (JOptionPane.showConfirmDialog(null, "Is there a Forget Me Not?") == 0);
		boolean morse = (JOptionPane.showConfirmDialog(null, "Is there a Simon Sends or a module\nwith 'MORSE' in its name?") == 0);
		int[][] condAmounts = new int[9][];
		condAmounts[0] = getAmounts(morse, fmn, true, true, 2);
		condAmounts[1] = getAmounts(morse, fmn, false, true, 2);
		condAmounts[2] = getAmounts(morse, fmn, false, false, 2);
		condAmounts[3] = getAmounts(morse, fmn, true, true, 1);
		condAmounts[4] = getAmounts(morse, fmn, false, true, 1);
		condAmounts[5] = getAmounts(morse, fmn, false, false, 1);		
		condAmounts[6] = getAmounts(morse, fmn, true, true, 0);
		condAmounts[7] = getAmounts(morse, fmn, false, true, 0);
		condAmounts[8] = getAmounts(morse, fmn, false, false, 0);
		if(compare(condAmounts[0], condAmounts[3]) && compare(condAmounts[0], condAmounts[6]))
			condAmounts = new int[][] {condAmounts[0], condAmounts[1], condAmounts[2]};
		else
		{
			Object[] options = {"1",
            "2", "Other"};
			int strikes = JOptionPane.showOptionDialog(new JFrame(),
					"Select the amount of Strikes",
					"",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,     //do not use a custom Icon
					options,  //the titles of buttons
					options[2]); //default button title
			switch(strikes)
			{
				case 0:
					condAmounts = new int[][] {condAmounts[3], condAmounts[4], condAmounts[5]};
					break;
				case 1:
					condAmounts = new int[][] {condAmounts[0], condAmounts[1], condAmounts[2]};
					break;
				default:
					condAmounts = new int[][] {condAmounts[6], condAmounts[7], condAmounts[8]};
					break;
			}
		}
		if(compare(condAmounts[0], condAmounts[1]) && compare(condAmounts[0], condAmounts[2]))
		{
			int[][] ans = finalRules(condAmounts[0]);
			JOptionPane.showMessageDialog(null, getOutput(ans));
		}
		else if(compare(condAmounts[0], condAmounts[1]))
		{
			int[][] overHalf = finalRules(condAmounts[2]);
			int[][] underHalf = finalRules(condAmounts[0]);
			long half = ((cf.getStartingBombMinutes() * 60) + cf.getStartingBombSeconds()) / 2;
			JOptionPane.showMessageDialog(null, "BOMB TIMER LESS THAN " + (half / 60) + ":" + (half % 60) + "\n" +  getOutput(underHalf) + "\nOTHERWISE\n" + getOutput(overHalf));
		}
		else if(compare(condAmounts[1], condAmounts[2]))
		{
			int[][] overFifth = finalRules(condAmounts[2]);
			int[][] underFifth = finalRules(condAmounts[0]);
			long fifth = ((cf.getStartingBombMinutes() * 60) + cf.getStartingBombSeconds()) / 5;
			JOptionPane.showMessageDialog(null, "BOMB TIMER LESS THAN " + (fifth / 60) + ":" + (fifth % 60) + "\n" +  getOutput(underFifth) + "\nOTHERWISE\n" + getOutput(overFifth));
		}
		else
		{
			int[][] underFifth = finalRules(condAmounts[0]);
			int[][] underHalf = finalRules(condAmounts[1]);
			int[][] otherwise = finalRules(condAmounts[2]);
			long fifth = ((cf.getStartingBombMinutes() * 60) + cf.getStartingBombSeconds()) / 5;
			long half = ((cf.getStartingBombMinutes() * 60) + cf.getStartingBombSeconds()) / 2;
			JOptionPane.showMessageDialog(null, "BOMB TIMER LESS THAN " + (fifth / 60) + ":" + (fifth % 60) + "\n" + getOutput(underFifth) + "\nBOMB TIMER LESS THAN "  + (half / 60) + ":" + (half % 60) + "\n" + getOutput(underHalf) + "\nOTHERWISE\n" + getOutput(otherwise));
		}
	}
	public int[] getAmounts(boolean morse, boolean fmn, boolean fifth, boolean half, int strikes)
	{
		int[] amounts = {0, 0, 0};
		//Paper
		if(ew.findInd("IND") && ew.BAT() < 5)
			amounts[0] += 19;
		if(ew.findInd("SND"))
			amounts[0] += 15;
		if(ew.findPort("PARALLEL") > 0)
			amounts[0] -= 44;
		if(morse && half)
			amounts[0] -= 26;
		if(ew.BAT() == 0 && ew.numInd() < 3)
			amounts[0] += 154;
		if(ew.numCharsInSN("SAVEMYWORLD") > 0 && ew.numCharsInSN("BCDFGHJKLMNPQRSTVWXYZ") < 2)
			amounts[0] += 200;
		//Plastic
		if(ew.findInd("TRN") && strikes != 1)
			amounts[1] += 91;
		if(ew.findInd("FRK") && strikes != 2)
			amounts[1] += 69;
		if(ew.numEmpty() > 0)
			amounts[1] -= 17;
		if(ew.findInd("FRQ") && ew.BD() <= ew.BA())
			amounts[1] += 153;
		//Metal
		if(ew.findInd("BOB"))
			amounts[2] += 199;
		if(ew.findInd("MSA"))
			amounts[2] += 92;
		if(ew.findInd("CAR") && ew.findPort("RJ-45") == 0)
			amounts[2] -= 200;
		if(ew.numDupPorts() > 0 && ew.findPort("DVI-D") == 0)
			amounts[2] += 153;
		if(ew.findInd("SIG") && !(fifth))
			amounts[2] += 99;
		if(fmn)
		{
			if(ew.findInd("BOB") && ew.numPorts() >= 6)
				amounts[2] += 99;
			else
				amounts[2] -= 84;
		}
		for(int i = 0; i < 3; i++)
		{
			if(amounts[i] < 0)
				amounts[i] *= -1;
		}
		return amounts;
	}
	
	private int[][] finalRules(int[] remain)
	{
		int[][] wr = {{0, 0, 0, 0}, {0, 0, 0, 0}};
		if(remain[0] + remain[1] + remain[2] > 695)
		{
			wr[1][0] = remain[0];
			wr[1][1] = remain[1];
			wr[1][2] = remain[2];
			return wr;
		}
		else if(remain[2] > 200)
		{
			wr[1][2] = remain[2] * 3;
			wr[1][2] = (wr[1][2] / 4) + ((wr[1][2] % 4) / 2);
			wr[0][2] = remain[2] - wr[1][2];
			remain[2] = 0;
		}
		else if(remain[2] < remain[0])
		{
			wr[1][0] = remain[0];
			wr[0][2] = (remain[2] / 4) + ((remain[2] % 4) / 2);
			wr[1][3] = remain[1] + remain[2] - wr[0][2];
			wr[1][3] = (wr[1][3] / 2) + (wr[1][3] % 2);
			return wr;
		}
		if(remain[1] < 300 && remain[1] > 100)
		{
			wr[1][1] = (remain[1] / 2) + (remain[1] % 2);
			remain[1] -= wr[1][1];
		}
		else if(remain[1] < 100 && remain[1] > 10)
		{
			wr[0][1] = remain[1];
			remain[1] = 0;
		}
		if(remain[0] < 65)
		{
			if(wr[1][1] > 0)
				wr[1][0] = remain[0];
			else
				wr[0][0] = (remain[0] / 3) + ((remain[0] % 3) / 2);
			remain[0] -= (wr[1][0] + wr[0][0]);
		}
		int leftover = remain[0] + remain[1] + remain[2];
		if(leftover < 300 && leftover > 100)
			wr[1][3] = leftover;
		else
			wr[0][3] = leftover;
		return wr;
	}
	private String getOutput(int[][] wr)
	{
		String[] mat = {"PAPER", "PLASTIC", "METAL"};
		String out = "";
		for(int i = 0; i < 3; i++)
		{
			out += mat[i] + ": ";
			if(wr[0][i] > 0)
				out = out + numToRoman(wr[0][i]) + "W ";
			if(wr[1][i] > 0)
				out = out + numToRoman(wr[1][i]) + "R ";
			out = out + "SUB\n";
		}
		if(wr[0][3] > 0 || wr[1][3] > 0)
		{
			out += "LEFTOVERS: ";
			if(wr[0][3] > 0)
				out = out + numToRoman(wr[0][3]) + "W ";
			if(wr[1][3] > 0)
				out = out + numToRoman(wr[1][3]) + "R ";
			out = out + "SUB\n";
		}
		return out;
	}
	private String numToRoman(int num)
	{
		int[] vals = {50, 10, 5, 1};
		String[] roman = {"L", "X", "V", "I"};
		String r = "";
		for(int i = 0; i < vals.length; i++)
		{
			for(int j = 0; j < (num / vals[i]); j++)
				r += roman[i];
			num = num % vals[i];
		}
		return r;
	}
	private boolean compare(int[] a, int[] b)
	{
		for(int i = 0; i < a.length; i++)
		{
			if(a[i] != b[i])
				return false;
		}
		return true;
	}
}
