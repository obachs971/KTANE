package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class BurglarAlarm 
{
	private final BombEdgework ew;
	public BurglarAlarm(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{
		String input = JOptionPane.showInputDialog("Enter the digits:");
		boolean v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the digits:");
			v = valid(input);
		}
		int[] display = new int[8];
		for(int i = 0; i < 8; i++)
			display[i] = "0123456789".indexOf(input.charAt(i));
		int[][] numbers = 
				{
						getDigits(display, true, false),
						getDigits(display, false, false),
						getDigits(display, true, true),
						getDigits(display, false, true)
				};
		String[] out = new String[numbers.length];
		for(int i = 0; i < out.length; i++)
		{
			out[i] = "";
			for(int j = 0; j < 8; j++)
				out[i] = out[i] + "" + ((numbers[i][j] + display[j]) % 10);
		}
		int num = ew.BAT() * ew.numPlates();
		JOptionPane.showMessageDialog(null, "Submit the number under the condition:\nEven Solves, Solves < " + (num + 1) + ": " + out[0] + "\nOdd Solves, Solves < " + (num + 1) + ": " + out[1] + "\nEven Solves, Solves > " + num + ": " + out[2] + "\nOdd Solves, Solves > " + num + ": " + out[3]);
		return ("DIGITS: " + input);
	}
	private int[] getDigits(int[] d, boolean n3, boolean n5)
	{
		return new int[] {
			ew.BAT() > ew.numPorts() ? ew.BH() % 2 == 0 ? 9 : 1 : d[7] % 2 == 0 ? 3 : 4,
			ew.findPort("PS/2") > 0 ? ew.numSNLETS() > ew.numSNDIGS() ? 0 : 6 : ew.findLit("BOB") ? 5 : 2,
			n3 ? d[2] % 2 == 0 ? 8 : 4 : ew.findPort("RJ-45") > 0 ? 9 : 3,
			(d[0] + d[1] + d[2] + d[3] + d[4] + d[5] + d[6] + d[7]) % 2 == 1 ? ew.numPlates() > ew.numInd() ? 7 : 3 : ew.BD() > ew.BA() ? 7 : 2,
			n5 ? ew.numPorts() % 2 == 0 ? 9 : 3 : ew.numPorts() > ew.numInd() ? 7 : 8,
			ew.findPort("PARALLEL") > 0 ? ew.findPort("SERIAL") > 0 ? 1 : 5 : ew.findLit("FRQ") ? 0 : 4,
			ew.BAT() > 4 ? ew.numUnlit() == 0 ? 2 : 6 : ew.numLit() == 0 ? 4 : 9,
			ew.BAT() == ew.numInd() ? ew.numCharsInSN("BURG14R") > 0 ? 1 : 0 : ew.numCharsInSN("AL53M") > 0 ? 0 : 8		
		};
	}
	private boolean valid(String i)
	{
		if(i.length() == 8)
		{
			for(char c : i.toCharArray())
			{
				if("0123456789".indexOf(c) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
}
