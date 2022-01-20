package modules;

import javax.swing.JOptionPane;

import misc.MTK;
import start.BombConfig;
import start.BombEdgework;

public class BitwiseOperations 
{
	private final BombConfig cf;
	private final BombEdgework ew;
	public BitwiseOperations(BombConfig c, BombEdgework e)
	{
		cf = c;
		ew = e;
	}
	public void run()
	{
		String out = "", gate = JOptionPane.showInputDialog("AND, OR, XOR, NOT\nEnter the logic gate:").toUpperCase();
		boolean v = valid(gate);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			gate = JOptionPane.showInputDialog("AND, OR, XOR, NOT\nEnter the logic gate:").toUpperCase();
			v = valid(gate);
		}
		boolean[] b1 = new boolean[8];
		b1[0] = ew.BA() == 0;
		b1[1] = ew.findPort("PARALLEL") > 0;
		b1[2] = ew.findLit("NSA");
		b1[3] = cf.getNumberModules() > cf.getStartingBombMinutes();
		b1[4] = ew.numLit() > 1;
		b1[5] = (cf.getNumberModules() % 3) == 0;
		b1[6] = ew.BD() < 2;
		b1[7] = ew.numPorts() < 4;
		if(gate.equals("NOT"))
		{
			for(int aa = 0; aa < 8; aa++)
			{
				if(b1[aa])
					out = out + "0";
				else
					out = out + "1";
			}
		}
		else
		{
			boolean[] b2 = new boolean[8];
			b2[0] = ew.BD() >= 1;
			b2[1] = ew.numPorts() >= 3;
			b2[2] = ew.BH() >= 2;
			b2[3] = ew.findLit("BOB");
			b2[4] = ew.numUnlit() > 1;
			b2[5] = (ew.getSNDIG(ew.numSNDIGS() - 1) % 2) == 1;
			b2[6] = (cf.getNumberModules() % 2) == 0;
			b2[7] = ew.BAT() >= 2;
			MTK mtk = new MTK();
			for(int aa = 0; aa < 8; aa++)
			{
				if(mtk.solveLogicExpression(b1[aa], b2[aa], gate))
					out = out + "1";
				else
					out = out + "0";
			}
		}
		JOptionPane.showMessageDialog(null, "Enter this result: " + out);
	}
	private boolean valid(String i)
	{
		switch(i)
		{
			case "AND":
			case "OR":
			case "XOR":
			case "NOT":
				return true;
			default:
				return false;
		}
	}
}
