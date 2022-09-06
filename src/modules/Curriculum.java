package modules;

import javax.swing.JOptionPane;

import misc.PlayType;
import start.BombEdgework;

public class Curriculum 
{
	private final PlayType playType;
	private final BombEdgework ew;
	public Curriculum(PlayType pt, BombEdgework e)
	{
		playType = pt;
		ew = e;
	}
	public void run()
	{
		String effect = getEffect();
		String[] classes = getClasses();
		if(playType == PlayType.TP)
		{
			String[] schedule = getSchedule();
			while(schedule == null)
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				schedule = getSchedule();
			}
			char[] stuff = new char[5];
			for(int aa = 0; aa < 5; aa++)
			{
				String temp = classes[0].charAt(aa) + "" + classes[1].charAt(aa);
				for(int bb = 0; bb < 5; bb++)
				{
					if(temp.indexOf(schedule[0].charAt(bb)) >= 0 && temp.indexOf(schedule[1].charAt(bb)) >= 0)
					{
						stuff[bb] = temp.charAt(0);
						break;
					}
				}
			}
			JOptionPane.showMessageDialog(null, stuff[0] + " " + stuff[1] + " " + stuff[2] + " " + stuff[3] + " " + stuff[4] + "\n\n" + effect + "\n\nCan have 1 conflict if no\nStrikes and at least 1 Solve");
		}
		else
		{
			String out = "";
			for(int aa = 0; aa < 5; aa++)
				out = out + "" + classes[0].charAt(aa) + " over " + classes[1].charAt(aa) + "\n";
			JOptionPane.showMessageDialog(null, out + "\n\n" + effect + "\n\nCan have 1 conflict if no\nStrikes and at least 1 Solve");
		}
	}
	private String getEffect()
	{
		if(ew.getSNDIG(ew.numSNDIGS() - 1) == 0)
			return "Can't take classes on Tuesday";
		else if(ew.numPorts() >= 5)
			return "Can't take classes on the\nlast slot of each day";
		else if(ew.numEmpty() > 0)
			return "Can't take classes on\nThursday or Friday";
		else if(ew.numInd() == 0)
			return "Can't take classes on the\nfirst slot of each day";
		else if(ew.BAT() >= 3)
			return "Can't take classes on the second\nhalves of Monday and Wednesday";
		else
			return "Can't take classes on the\nsecond half of Friday";
	}
	private String[] getClasses()
	{
		String[] classList = {"PPPLL", "MLEME"};
		String[] classes = {"", ""};
		for(int aa = 0; aa < 5; aa++)
		{
			int num;
			if(ew.isSNDIG(aa))
				num = "0123456789".indexOf(ew.getSNCHAR(aa));
			else
				num = "-ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(ew.getSNCHAR(aa));
			classes[0] = classes[0] + "" + classList[num % 2].charAt(aa);
			classes[1] = classes[1] + "" + classList[(num + 1) % 2].charAt(aa);	
		}
		return classes;
	}
	private String[] getSchedule()
	{
		String[] list = {"PM", "PL", "PE", "LM", "LE"};
		boolean[] b = {false, false, false, false, false};
		String[] classes = new String[2];
		String message = "Enter the letters:";
		for(int aa = 0; aa < 2; aa++)
		{
			classes[aa] = JOptionPane.showInputDialog(message).toUpperCase();
			boolean v = v1(classes[aa]);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				classes[aa] = JOptionPane.showInputDialog("Enter the letters:").toUpperCase();
				v = v1(classes[aa]);
			}
			message = "Toggle the letters\nEnter the new letters:";
		}
		for(int aa = 0; aa < 5; aa++)
		{
			for(int bb = 0; bb < 5; bb++)
			{
				if(list[bb].indexOf(classes[0].charAt(aa)) >= 0 && list[bb].indexOf(classes[1].charAt(aa)) >= 0)
				{
					b[bb] = true;
					break;
				}
			}
		}
		if(b[0] && b[1] && b[2] && b[3] && b[4])
			return classes;
		return null;
	}
	private boolean v1(String i)
	{
		if(i.length() == 5)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("PLME".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
}
