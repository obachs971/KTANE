package modules;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import misc.MTK;
import start.BombEdgework;

public class Morsematics 
{
	private final BombEdgework ew;
	public Morsematics(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{
		String souv = "RECEIVED LETTERS: ";
		MTK mtk = new MTK();
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String input = JOptionPane.showInputDialog("Enter the 3 morse\ncode characters (spaces):").toUpperCase();
		boolean v = valid(input, mtk);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the 3 morse\ncode characters (spaces):").toUpperCase();
			v = valid(input, mtk);
		}
		String code = mtk.morseToMessage(input.split(" "));
		souv = souv + "" + code.charAt(0) + " " + code.charAt(1) + " " + code.charAt(2);
		int[] pair = {alpha.indexOf(ew.getSNCHAR(3)), alpha.indexOf(ew.getSNCHAR(4))};
		pair[0] = mod(pair[0] + ew.numLitWithChars(code), 26);
		pair[1] = mod(pair[1] + ew.numUnlitWithChars(code), 26);
		ArrayList<Integer> squares = new ArrayList<Integer>();
		for(int aa = 1; aa < 6; aa++)
			squares.add(aa * aa);
		int check = pair[0] + pair[1] + 2;
		while(check > 26)
			check -= 26;
		if(squares.contains(check))
			pair[0] = mod(pair[0] + 4, 26);
		else
			pair[1] = mod(pair[1] - 4, 26);
		char order[] = code.toCharArray();
		Arrays.sort(order);
		pair[0] = mod(pair[0] + alpha.indexOf(order[2]) + 1, 26);
		for(int aa = 0; aa < 3; aa++)
		{
			if("BCEGKMQSW".indexOf(code.charAt(aa)) >= 0)
				pair[0] = mod(pair[0] - alpha.indexOf(code.charAt(aa)) - 1, 26);
			if("ADIPY".indexOf(code.charAt(aa)) >= 0)
				pair[1] = mod(pair[1] - alpha.indexOf(code.charAt(aa)) - 1, 26);
			if(ew.BAT() > 0 && (alpha.indexOf(code.charAt(aa)) + 1) % ew.BAT() == 0)
			{
				pair[0] = mod(pair[0] - alpha.indexOf(code.charAt(aa)) - 1, 26);
				pair[1] = mod(pair[1] - alpha.indexOf(code.charAt(aa)) - 1, 26);
			}
		}
		if(pair[0] == pair[1])
			JOptionPane.showMessageDialog(null, "Transmit this letter: " + mtk.messageToMorse(alpha.charAt(pair[0]) + "")[0]);
		else if(pair[0] > pair[1])
			JOptionPane.showMessageDialog(null, "Transmit this letter: " + mtk.messageToMorse(alpha.charAt(pair[0] - pair[1] - 1) + "")[0]);
		else
			JOptionPane.showMessageDialog(null, "Transmit this letter: " + mtk.messageToMorse(alpha.charAt(mod(pair[0] + pair[1] + 1, 26)) + "")[0]);
		return souv;
	}
	private int mod(int n, int m)
	{
		while(n < 0)
			n += m;
		return (n % m);
	}
	private boolean valid(String i, MTK mtk)
	{
		String code = mtk.morseToMessage(i.split(" "));
		if(code.length() == 3)
		{
			for(int aa = 0; aa < 3; aa++)
			{
				if("ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(code.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
	
}
