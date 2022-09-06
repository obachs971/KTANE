package modules;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import start.BombConfig;
import start.BombEdgework;

public class Stopwatch 
{
	private final int[][] table1 =
		{
				{260, 66, 164, 152},
				{73, 194, 99, 202},
				{116, 158, 240, 195},
				{269, 204, 121, 1}

		};
	private final int[][] table2 =
		{
				{220, 155},
				{252, 87},
		};
	private final BombConfig con;
	private final BombEdgework ew;
	public Stopwatch(BombConfig cf, BombEdgework e)
	{
		con = cf;
		ew = e;
	}
	public void run()
	{
		int time = getTime();
		if(time > 30)
		{
			long start = (con.getStartingBombMinutes() * 60) + con.getStartingBombSeconds();
			if(start <= 60)
				time /= 20;
			else if(start <= 300)
				time /= 10;
		}
		int min = time / 60;
		int sec = time % 60;
		if(sec < 10)
			JOptionPane.showMessageDialog(null, "Run it for " + min + ":0" + sec);
		else
			JOptionPane.showMessageDialog(null, "Run it for " + min + ":" + sec);
	}
	private int getTime()
	{
		switch(ew.numSNDIGS())
		{
			case 2:
				ArrayList<Integer> list = new ArrayList<Integer>();
				list.add(ew.getSNDIG(0) * 10 + ew.getSNDIG(1));
				list.add(ew.getSNDIG(1) * 10 + ew.getSNDIG(0));
				for(int i = 0; i < list.size(); i++)
					list.set(i, list.get(i) - (ew.getSNDIG(0) * ew.getSNDIG(1)));
				Collections.sort(list);
				return table1[list.get(1) % 4][list.get(0) % 3];
			case 3:
				int[] xyz = {ew.getSNDIG(0), ew.getSNDIG(1), ew.getSNDIG(2)};
				if((xyz[0] * xyz[1]) > ((xyz[2] * xyz[1]) - xyz[0]))
				{
					for(int i = 0; i < 3; i++)
						xyz[i] += ew.BAT();
				}
				if((xyz[0] + xyz[1] + xyz[2]) % 2 == 1 && !(xyz[0] % 2 == 1 && xyz[1] % 2 == 1 && xyz[2] % 2 == 1))
					return table2[0][0];
				else if((xyz[0] + xyz[1] + xyz[2]) % 2 == 0 && !(xyz[0] % 2 == 0 && xyz[1] % 2 == 0 && xyz[2] % 2 == 0))
				{
					int[] n = ignoreLowest(xyz);
					return table2[n[1] % 2][n[0] % 2];
				}
				else if(xyz[0] % 2 == 0 && xyz[1] % 2 == 0 && xyz[2] % 2 == 0)
					return table2[0][0];
				else 
				{
					xyz[1] += 2;
					int[] n = ignoreLowest(xyz);
					return table2[n[1] % 2][n[0] % 2];
				}
			default:
				int[] digs = {ew.getSNDIG(0), ew.getSNDIG(1), ew.getSNDIG(2), ew.getSNDIG(3)};
				if(digs[1] == 0)
					digs[1]++;
				if(digs[3] == 0)
					digs[3]++;
				for(int i = 0; i < 4; i+=2)
				{
					if(digs[i] % digs[i + 1] == 0)
						digs[i] /= digs[i + 1];
					else
						digs[i] += digs[i + 1];
				}
				return table1[digs[2] % 4][digs[0] % 4];
		}
	}
	private int[] ignoreLowest(int[] n)
	{
		if(n[0] <= n[1] && n[0] <= n[2])
			return new int[] {n[1], n[2]};
		else if(n[1] <= n[0] && n[1] <= n[2])
			return new int[] {n[0], n[2]};
		else
			return new int[] {n[0], n[1]};
	}
}
