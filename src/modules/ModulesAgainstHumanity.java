package modules;

import javax.swing.JOptionPane;

import start.BombConfig;
import start.BombEdgework;

public class ModulesAgainstHumanity 
{
	private final BombConfig cf;
	private final BombEdgework ew;
	public ModulesAgainstHumanity(BombConfig c, BombEdgework e)
	{
		cf = c;
		ew = e;
	}
	public void run()
	{
		int[] current = {1, 1}, dir = new int[2];
		if(JOptionPane.showConfirmDialog(null, "Does the BLACK\ncard spell poop?") == 0)
			dir[0] = getDir(current[0], 2);
		else
			dir[0] = getDir(current[0], correction(ew.numUnlit() + ew.numPorts()));
		if(JOptionPane.showConfirmDialog(null, "Does the WHITE\ncard spell poop?") == 0)
			dir[1] = getDir(current[1], 2);
		else
			dir[1] = getDir(current[1], correction(ew.numLit() + ew.BAT()));
		for(int aa = 0; aa < 2; aa++)
			current[aa] = correction(current[aa] + dir[aa]);
		JOptionPane.showMessageDialog(null, "Black: " + getMove(dir[0]) + "\nWhite: " + getMove(dir[1]));
		boolean[] b = {JOptionPane.showConfirmDialog(null, "Does the BLACK card refer\nto a module on the bomb?") == 0, JOptionPane.showConfirmDialog(null, "Does the WHITE card refer\nto a module on the bomb?") == 0};
		if(b[0] & b[1])
		{
			dir[0] = 4;
			dir[1] = 3;
		}
		else if(b[0])
		{
			dir[0] = 0;
			dir[1] = 2;
		}
		else if(b[1])
		{
			dir[0] = 1;
			dir[1] = 0;
		}
		else if(ew.numCharsInSN("MAH") > 0)
		{
			dir[0] = -2;
			dir[1] = -2;
		}
		else if(JOptionPane.showConfirmDialog(null, "Is the BLACK card on the left?") == 0)
		{
			dir[0] = getDir(current[0], correction(ew.numUniPorts()));
			dir[1] = getDir(current[1], correction(ew.numInd()));
		}
		else
		{
			dir[0] = getDir(current[0], correction(cf.getNumberModules()));
			dir[1] = 0;
		}
		JOptionPane.showMessageDialog(null, "Black: " + getMove(dir[0]) + "\nWhite: " + getMove(dir[1]));
	}
	private int getDir(int current, int goal)
	{
		if(current == goal)
			return 0;
		int diff = goal - current;
		if(diff > 5)
			return (diff - 10);
		else if(diff < -5)
			return (diff + 10);
		else
			return diff;
	}
	private int correction(int pos)
	{
		while(pos < 1)
			pos += 10;
		while(pos > 10)
			pos -= 10;
		return pos;
	}
	private String getMove(int dir)
	{
		if(dir < 0)
			return "Left " + (dir * -1);
		else if(dir > 0)
			return "Right " + dir;
		else
			return "Stay";
	}
}
