package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import misc.MTK;
import start.BombEdgework;

public class TapCode 
{
	private final String[] wordList =
		{
				"CHILD","STYLE","SHAKE","ALIVE","AXION","WRECK","CAUSE","PUPIL","CHEAT","WATCH",
				"JELLY","CLOCK","QUARK","GRASS","LASER","JEANS","YACHT","RUMOR","FAULT","HOVER",
				"SHEET","AWARE","SHELL","JOLLY","GIANT","VAGUE","IMAGE","ACUTE","ARENA","VISIT",
				"TABLE","FORCE","CHAIR","QUICK","SUITE","LARGE","CHORD","POWER","ALOOF","ATTIC",
				"COVER","PRIZE","TRAIL","CYCLE","SIGHT","ZEROS","GLARE","ANGLE","RANCH","UPSET",
				"MIXER","DRIVE","XENON","WATER","VENOM","RIGHT","SWEET","GLOOM","CLASH","ABBEY",
				"LEVEL","QUILT","CLIMB","TEASE","KNOCK","FAIRY","QUEEN","ZEBRA","GUIDE","SOUTH",
				"FUNNY","PROUD","YOUNG","JUMPY","STAFF","QUERY","TRUNK","ZOOMS","SMART","GHOST",
				"JUDGE","YIELD","BRAIN","HELIX","SMALL","NOISE","SEIZE","ROBOT","STAIN","WHERE",
				"WORLD","SHARK","BEARD","DISCO","YUMMY","TITLE","STORY","COLOR","SHORT","FRESH"
		};
	private final BombEdgework ew;
	public TapCode(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{
		String input = JOptionPane.showInputDialog("Enter the taps:").replace(" ", "").toUpperCase();
		int index = getIndex(input);
		while(index == -1)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the taps:").replace(" ", "").toUpperCase();
			index = getIndex(input);
		}
		String souv = "RECEIVED WORD: " + wordList[index];
		int row = index / 10, col = index % 10;
		int offset = ew.getSNDIG(ew.numSNDIGS() - 1);
		if(ew.numCharsInSN("0") > 0)
			offset = ew.getSNSUM();
		if(ew.isSNDIG(0) && ew.isSNDIG(1))
			row = mod(row + offset, 10);
		else if(ew.isSNDIG(0))
			col = mod(col + offset, 10);
		else if(ew.isSNDIG(1))
			col = mod(col - offset, 10);
		else
			row = mod(row - offset, 10);
		String[] tc = new MTK().messageToTapCode(wordList[row * 10 + col]);
		JOptionPane.showMessageDialog(null, "Submit these taps:\n" + tc[0] + " " + tc[1] + " " + tc[2] + " " + tc[3] + " " + tc[4]);
		return souv;
	}
	private int getIndex(String input)
	{
		MTK mtk = new MTK();
		ArrayList<String> arr = new ArrayList<String>();
		for(int i = 0; i < input.length(); i++)
		{
			if("12345".indexOf(input.charAt(i)) >= 0 && i < (input.length() - 1))
			{
				arr.add(input.charAt(i) + "" + input.charAt(i + 1));
				i++;
			}
			else
				arr.add(input.charAt(i) + "");	
		}
		String[] tapCode = new String[arr.size()];
		for(int i = 0; i < tapCode.length; i++)
			tapCode[i] = arr.get(i);
		
		
		String word = mtk.tapCodeToMessage(tapCode);
		for(int i = 0; i < wordList.length; i++)
		{
			String check = wordList[i].replace("K", "C");
			if(word.equals(check))
				return i;
		}
		return -1;
	}
	private int mod(int n, int m)
	{
		while(n < 0)
			n += m;
		return (n % m);
	}
}
