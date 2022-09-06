package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class Radiator 
{
	private final BombEdgework ew;
	public Radiator(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		int[] nums;
		if(ew.findLit("FRK") && ew.findLit("BOB"))
			nums = new int[] {13, 37};
		else
		{
			nums = new int[]{(ew.numCharsInSN("RADI4T07") * 10) + ((ew.BA() / 2) * 5) - (ew.BD() * 5), 0};
			if(nums[0] < 0)
				nums[0] *= -1;
			nums[1] = nums[0] / 3;
			if(ew.findPort("RJ-45") > 0)
				nums[1] += 50;
			if(ew.numLit() > 0)
				nums[1] += 20;
			if(ew.findUnlit("BOB"))
				nums[1] += 40;
			if(ew.findUnlit("NSA"))
				nums[1] -= 10;
			if(ew.findUnlit("FRQ"))
				nums[1] += 2;
			if(ew.findUnlit("MSA"))
				nums[1] += 25;
			if(ew.findUnlit("FRK"))
				nums[1] -= 1;
			if(nums[1] < 0)
				nums[1] *= -1;
		}
		JOptionPane.showMessageDialog(null, "Submit these numbers: " + (nums[0] % 100) + " " + (nums[1] % 100));
	}
}
