package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class Plumbing 
{
	private final BombEdgework ew;
	public Plumbing(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		String[] colors = {"RED", "YELLOW", "GREEN", "BLUE"};
		int[] RYGBin = {0, 0, 0, 0};
		int[] RYGBout = {0, 0, 0, 0};
		if(ew.numCharsInSN("1") > 0)
			RYGBin[0]++;
		if(ew.findPort("RJ-45") == 1)
			RYGBin[0]++;
		if(ew.numDupPorts() > 0)
			RYGBin[0]--;
		for(int aa = 0; aa < 6; aa++)
		{
			boolean flag = false;
			for(int bb = aa + 1; bb < 6; bb++)
			{
				if(ew.getSNCHAR(aa) == ew.getSNCHAR(bb))
				{
					RYGBin[0]--;
					flag = true;
					break;
				}
			}
			if(flag)
				break;
		}
		
		if(ew.numCharsInSN("2") > 0)
			RYGBin[1]++;
		if(ew.findPort("RCA") >= 1)
			RYGBin[1]++;
		if(ew.numDupPorts() == 0)
			RYGBin[1]--;
		if(ew.numCharsInSN("1L") > 0)
			RYGBin[1]--;
		
		if(ew.numSNDIGS() >= 3)
			RYGBin[2]++;
		if(ew.findPort("DVI-D") >= 1)
			RYGBin[2]++;
		if(RYGBin[0] <= 0)
			RYGBin[2]--;
		if(RYGBin[1] <= 0)
			RYGBin[2]--;
		
		if(RYGBin[0] <= 0 && RYGBin[1] <= 0 && RYGBin[2] <= 0)
			RYGBin[3] = 1;
		else
		{
			if(ew.numPortTypes() >= 4)
				RYGBin[3]++;
			if(ew.BAT() >= 4)
				RYGBin[3]++;
			if(ew.numPorts() == 0)
				RYGBin[3]--;
			if(ew.BAT() == 0)
				RYGBin[3]--;
		}
		//System.out.println(RYGBin[0] + " " + RYGBin[1] + " " + RYGBin[2]);
		int numActive = 0;
		String[] out = {"INPUT: ", "OUTPUT: "};
		for(int aa = 0; aa < 4; aa++)
		{
			if(RYGBin[aa] > 0)
			{
				numActive++;
				out[0] = out[0] + "" + colors[aa].toUpperCase() + " ";
			}
		}
		
		if(ew.findPort("SERIAL") >= 1)
			RYGBout[0]++;
		if(ew.BAT() == 1)
			RYGBout[0]++;
		if(ew.numSNDIGS() > 2)
			RYGBout[0]--;
		if(numActive > 2)
			RYGBout[0]--;
		
		if(ew.numDupPorts() > 0)
			RYGBout[1]++;
		if(ew.numCharsInSN("48") > 0)
			RYGBout[1]++;
		if(ew.numCharsInSN("2") == 0)
			RYGBout[1]--;
		if(RYGBin[2] > 0)
			RYGBout[1]--;
		
		if(numActive == 3)
			RYGBout[2]++;
		if(ew.numPorts() == 3)
			RYGBout[2]++;
		if(ew.numPorts() < 3)
			RYGBout[2]--;
		if(ew.numSNDIGS() > 3)
			RYGBout[2]--;
		
		if(RYGBout[0] <= 0 && RYGBout[1] <= 0 && RYGBout[2] <= 0)
			RYGBout[3] = 1;
		else
		{
			if(numActive == 4)
				RYGBout[3]++;
			if(RYGBout[0] <= 0 || RYGBout[1] <= 0 || RYGBout[2] <= 0)
				RYGBout[3]++;
			if(ew.BAT() < 2)
				RYGBout[3]--;
			if(ew.findPort("PARALLEL") == 0)
				RYGBout[3]--;
		}
		for(int aa = 0; aa < 4; aa++)
		{
			if(RYGBout[aa] > 0)
				out[1] = out[1] + "" + colors[aa] + " ";
		}
		JOptionPane.showMessageDialog(null, out[0] + "\n" + out[1]);
	}
}
