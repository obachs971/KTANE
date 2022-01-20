package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class ComplicatedWires 
{
	private final BombEdgework ew;
	public ComplicatedWires(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		String[] diagram = {"CCDB", "SCBB", "SDPP", "SPSD"};
		String input = JOptionPane.showInputDialog("R - Red\nB - Blue\nW - White\nS - Star\nL - Lit LED\nEnter the all\nthe wires (spaces):").toUpperCase();
		boolean v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("R - Red\nB - Blue\nW - White\nS - Star\nL - Lit LED\nEnter the all\nthe wires (spaces):").toUpperCase();
			v = valid(input);
		}
		String[] wires = input.split(" ");
		String cut = "";
		for(int aa = 0; aa < wires.length; aa++)
		{
			int[] rc = {0, 0};
			if(wires[aa].contains("R"))
				rc[0] += 1;
			if(wires[aa].contains("B"))
				rc[0] += 2;
			if(wires[aa].contains("S"))
				rc[1] += 1;
			if(wires[aa].contains("L"))
				rc[1] += 2;
			switch(diagram[rc[0]].charAt(rc[1]))
			{
				case 'C':
					cut = cut + "" + (aa + 1) + " ";
					break;
				case 'S':
					if(ew.getSNDIG(ew.numSNDIGS()) % 2 == 0)
						cut = cut + "" + (aa + 1) + " ";
					break;
				case 'P':
					if(ew.findPort("PARALLEL") > 0)
						cut = cut + "" + (aa + 1) + " ";
					break;
				case 'B':
					if(ew.BAT() > 1)
						cut = cut + "" + (aa + 1) + " ";
					break;
			}
		}
		if(cut.length() > 0)
			JOptionPane.showMessageDialog(null, "Cut these wire(s): " + cut);
		else
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			run();
		}
	}
	private boolean valid(String i)
	{
		if(i.length() == 0)
			return false;
		String[] temp = i.split(" ");
		for(int aa = 0; aa < temp.length; aa++)
		{
			for(int bb = 0; bb < temp[aa].length(); bb++)
			{
				if("WRBSL".indexOf(temp[aa].charAt(bb)) < 0)
					return false;
			}
		}
		return true;
	}
}