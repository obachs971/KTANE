package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class ColorGenerator 
{
	private final BombEdgework ew;
	public ColorGenerator(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		int[] nums = new int[6];
		for(int aa = 0; aa < 6; aa++)
		{
			if(ew.isSNDIG(aa))
				nums[aa] = "0123456789".indexOf(ew.getSNCHAR(aa));
			else
				nums[aa] = "-ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(ew.getSNCHAR(aa));
			nums[aa] = nums[aa] % 16;
		}
		int[] vals = {((nums[0] * 16) + nums[1]), ((nums[2] * 16) + nums[3]), ((nums[4] * 16) + nums[5])};
		String[] conv = new String[3];
		for(int aa = 0; aa < 3; aa++)
		{
			conv[aa] = vals[aa] + "";
			while(conv[aa].length() < 3)
				conv[aa] = "0" + conv[aa];
		}
		String out = "RED: " + vals[0] + "\nGREEN: " + vals[1] + "\nBLUE: " + vals[2] + "\n";
		for(int aa = 0; aa < 3; aa++)
		{
			out = out + "R" + conv[0].charAt(2 - aa) + ", G" + conv[1].charAt(2 - aa) + ", B" + conv[2].charAt(2 - aa); 
			if(aa != 2)
				out = out + ", SR\n";
			else
				out = out + ", SB";
		}
		JOptionPane.showMessageDialog(null, out);
	}
}
