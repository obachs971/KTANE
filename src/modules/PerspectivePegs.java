package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class PerspectivePegs 
{
	private final String[][][] table =
		{
				{
					{"RYY", "BPY"},
					{"YPG", "PBR"},
					{"RGP", "BGR"},
					{"YBG", "BYY"},
					{"PPR", "RYP"},
					{"BGB", "PYG"},
					{"YGB", "GPY"},
					{"PGG", "GYR"}
				},
				{
					{"BPB", "YBG"},
					{"YYP", "BRP"},
					{"GRB", "YPB"},
					{"RPY", "GBG"},
					{"YGG", "PBR"},
					{"GPB", "YGY"},
					{"PRP", "BBG"},
					{"RYR", "RPB"}
				},
				{
					{"PYB", "RGB"},
					{"YRP", "RYR"},
					{"GYR", "GBP"},
					{"BYG", "PGR"},
					{"RPY", "GYB"},
					{"PPG", "PBR"},
					{"RYY", "BBR"},
					{"YGP", "PYY"}
				}
		};
	private final BombEdgework ew;
	private final int playStyle;
	public PerspectivePegs(BombEdgework e, int ps)
	{
		ew = e;
		playStyle = ps;
	}
	public String run()
	{
		int keyNum = getDiff(ew.getSNLET(0), ew.getSNLET(1));
		if(ew.numSNLETS() == 4)
			keyNum += (getDiff(ew.getSNLET(2), ew.getSNLET(3)));
		char keyColor = "RGPRYBPGBY".charAt(keyNum % 10);
		String souv = "COLOR SEQUENCE: ";
		if(playStyle == 2)//TP style
		{
			//Input the colors of each peg
			String[] pegs = getPegs(keyColor), orders = {"43021", "04132", "34201", "40312", "01423"}, pos = {"T", "TR", "BR", "BL", "TL"};
			//Get the initial key sequence
			String keySeq = pegs[0].charAt(0) + "" + pegs[1].charAt(1) + "" + pegs[2].charAt(2) + "" + pegs[3].charAt(3) + "" + pegs[4].charAt(4);
			int best = 0, bestCur = 0;
			for(int aa = 0; aa < pegs.length; aa++)
			{
				int sum = 0;
				for(int bb = 0; bb < pegs[aa].length(); bb++)
				{
					if(pegs[aa].charAt(bb) == keyColor)
						sum++;
				}
				if(sum > best)
				{
					best = sum;
					bestCur = aa;
				}
			}
			keySeq = keySeq.substring(bestCur) + "" + keySeq.substring(0, bestCur);
			souv = souv + "" + keySeq.toUpperCase();
			//Get the final key sequence
			keySeq = getFinalSeq(keySeq).substring(0, 3);
			//Find final key sequence on the pegs to push.
			String order = "";
			for(int aa = 0; aa < orders.length; aa++)
			{
				String temp = "";
				for(int bb = 0; bb < orders[aa].length(); bb++)
					temp = temp + "" + pegs["01234".indexOf(orders[aa].charAt(bb))].charAt(aa);
				System.out.println(temp);
				if(temp.contains(keySeq))
				{
					int index = temp.indexOf(keySeq);
					order = orders[aa].substring(index, index + 3);
					break;
				}
				else if(reverse(temp).contains(keySeq))
				{
					int index = reverse(temp).indexOf(keySeq);
					order = reverse(orders[aa]).substring(index, index + 3);
					break;
				}
			}
			if(order.length() > 0)
			{
				JOptionPane.showMessageDialog(null, "Press these pegs: " + pos["01234".indexOf(order.charAt(0))] + " " + pos["01234".indexOf(order.charAt(1))] + " " + pos["01234".indexOf(order.charAt(2))]);
				souv = souv + "" + pos["01234".indexOf(order.charAt(0))] + " " + pos["01234".indexOf(order.charAt(1))] + " " + pos["01234".indexOf(order.charAt(2))];
				return souv;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				return run();
			}
		}
		else//Any Other style
		{
			//Enter initial key sequence
			String keySeq = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nP - Purple\nStart at the peg with 3 or more " + keyColor + "\nEnter the color sequence:").toUpperCase();
			boolean v = v2(keySeq);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				keySeq = JOptionPane.showInputDialog("Start at the peg with 3 or more " + keyColor + "\nEnter the color sequence:").toUpperCase();
				v = v2(keySeq);
			}
			souv = souv + "" + keySeq.toUpperCase();
			//Get final key sequence
			keySeq = getFinalSeq(keySeq).substring(0, 3);
			JOptionPane.showMessageDialog(null, "Press the pegs with\nthis color sequence: " + keySeq);
			return souv;
		}
	}
	//Gets the alphabet difference between 2 letters
	private int getDiff(char l1, char l2)
	{
		String alpha = "-ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		if(l1 < l2)
			return (alpha.indexOf(l2) - alpha.indexOf(l1));
		else
			return (alpha.indexOf(l1) - alpha.indexOf(l2));
	}
	//Returns a valid set of pegs
	private String[] getPegs(char keyColor)
	{
		String[] pegs = new String[5];
		
		String left = "RBYGP";
		for(int aa = 0; aa < pegs.length; aa++)
		{
			pegs[aa] = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nP - Purple\nEnter the colors\non peg #" + (aa + 1) + ":").toUpperCase();
			String v = v1(pegs[aa], left);
			while(v.length() == left.length())
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				pegs[aa] = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nP - Purple\nEnter the colors\non peg #" + (aa + 1) + ":").toUpperCase();
				v = v1(pegs[aa], left);
			}
			left = v.toUpperCase();
		}
		return pegs;
	}
	//Returns the final color sequence to be found
	private String getFinalSeq(String ks)
	{
		int cur = 2;
		if(ew.BAT() == 1 || ew.BAT() == 2)
			cur = 0;
		else if(ew.BAT() == 3 || ew.BAT() == 4)
			cur = 1;
		for(int aa = 0; aa < table[cur].length; aa++)
		{
			if(ks.contains(table[cur][aa][0]))
				ks = ks.replaceFirst(table[cur][aa][0], table[cur][aa][1]);
			else if(ks.contains(reverse(table[cur][aa][0])))
				ks = ks.replaceFirst(reverse(table[cur][aa][0]), reverse(table[cur][aa][1]));
		}
		return ks;
	}
	//Reverses string
	private String reverse(String s)
	{
		String temp = "";
		for(int aa = 0; aa < s.length(); aa++)
			temp = s.charAt(aa) + "" + temp;
		return temp;
	}
	//Validation for TP style input
	private String v1(String i, String left)
	{
		String temp = left.toUpperCase();
		if(v2(i))
		{
			int[] sums = new int[temp.length()];
			for(int aa = 0; aa < i.length(); aa++)
			{
				if(temp.indexOf(i.charAt(aa)) >= 0)
					sums[temp.indexOf(i.charAt(aa))]++;
			}
			for(int aa = 0; aa < sums.length; aa++)
			{
				if(sums[aa] >= 3)
				{
					temp = temp.replace(temp.charAt(aa) + "", "");
					break;
				}
			}
		}
		return temp;
	}
	//Validation for other style input
	private boolean v2(String i)
	{
		if(i.length() == 5)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("RBYGP".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
}
