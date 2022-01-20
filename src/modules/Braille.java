package modules;

import javax.swing.JOptionPane;

import misc.MTK;
import start.BombEdgework;

public class Braille 
{
	private String word;
	private final BombEdgework ew;
	public Braille(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{
		int num = getPress();
		while(num == 0)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			num = getPress();
		}
		JOptionPane.showMessageDialog(null, "Press character #" + num);
		return ("WORD: " + word.toUpperCase());
	}
	private int getPress()
	{
		String input = JOptionPane.showInputDialog("1   4\n2   5\n3   6\n0 - Zero Dots\n\nEnter the dots (spaces):");
		boolean v = v1(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("1   4\n2   5\n3   6\n0 - Zero Dots\n\nEnter the dots (spaces):");
			v = v1(input);
		}
		String[] spl = input.split(" ");
		String braille = "                        ";
		for(int aa = 0; aa < spl.length; aa++)
		{
			if(!(spl[aa].equals("0")))
			{
				for(int bb = 0; bb < spl[aa].length(); bb++)
				{
					int num = (aa * 6) + "123456".indexOf(spl[aa].charAt(bb));
					braille = braille.substring(0, num) + "." + braille.substring(num + 1);
				}
			}
		}
		//System.out.println(braille);
		int cur = 0;
		for(int aa = 0; aa < 6; aa++)
		{
			if(ew.isSNDIG(aa))
				cur += "0123456789".indexOf(ew.getSNCHAR(aa));
			else
				cur += "-ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(ew.getSNCHAR(aa));
			cur = cur % 24;
			if(braille.charAt(cur) == '.')
				braille = braille.substring(0, cur) + " " + braille.substring(cur + 1);
			else
				braille = braille.substring(0, cur) + "." + braille.substring(cur + 1);
			cur++;
		}
		//System.out.println(braille);
		MTK mtk = new MTK();
		word = mtk.brailleToMessage(braille);
		switch(word)
		{
			case "AIMING":case "BEINGS":case "BREAST":case "FASTER":case "FATHER":
			case "FINDING":case "HEADING":case "HEARING":case "LEAVING":case "LINKING":
			case "LISTEN":case "MARKED":case "MARKING":case "NEARER":case "PARKING":
			case "POWERS":case "PUSHED":case "READER":case "READING":case "RUSHING":
			case "SHAKING":case "SOUGHT":case "STAYING":case "TEACHING":case "TENDER":
				return 1;
			case "ARTIST":case "BINDING":case "BREACH":case "CARERS":case "CHARTER":
			case "EATING":case "EIGHTH":case "FARMING":case "FLYING":case "FOSTER":
			case "HEADED":case "HEATING":case "HIGHER":case "LAYING":case "LISTED":
			case "MASTER":case "PUSHING":case "RIDING":case "RUSHED":case "SAYING":
			case "SERVED":case "STRANDS":case "WARNING":case "WEALTH":case "WINNER":
				return 2;
			case "ACTING":case "BEATING":case "BREATH":case "BREATHE":case "BRINGING":
			case "BRINGS":case "CARTER":case "DEALER":case "FINEST":case "FOUGHT":
			case "GAINING":case "INSIST":case "LASTED":case "MAKING":case "MEANING":
			case "RATHER":case "REACHING":case "RESTING":case "TESTING":case "THROWING":
			case "VESTED":case "WARNED":case "WEAKER":case "WINNING":case "WINTER":
				return 3;
			case "ASKING":case "BEARING":case "BOUGHT":case "BOXING":case "CRYING":
			case "DATING":case "FARMER":case "FINISH":case "GATHER":case "GAZING":
			case "GENDER":case "GROWING":case "LEADER":case "LEADING":case "LEANED":
			case "LEANING":case "LIVING":case "MINERS":case "PARISH":case "PARKER":
			case "PAYING":case "SERVER":case "STRINGS":case "TENDED":case "TOWERS":
				return 4;
		}
		return 0;
	}
	private boolean v1(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 4)
		{
			for(int aa = 0; aa < 4; aa++)
			{
				for(int bb = 0; bb < conv[aa].length(); bb++)
				{
					if(!(conv[aa].equals("0")))
					{
						if("123456".indexOf(conv[aa].charAt(bb)) < 0)
							return false;
					}
				}
			}
			return true;
		}
		return false;
	}
}
