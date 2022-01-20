package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class PointOfOrder 
{
	private String[][] table =
		{
				{"S/H", "H/C", "C/D", "D/S"},
				{"S/D", "H/S", "C/H", "D/C"},
				{"H/C", "C/D", "D/S", "S/H"},
				{"D/C", "S/D", "H/S", "C/H"}
		};
	private final BombEdgework ew;
	public PointOfOrder(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		String input = JOptionPane.showInputDialog("A, J, Q, K\nS, H, C, D\nEnter the 5 cards (spaces):").toUpperCase();
		int rules = getRules(input);
		while(rules == 0)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("A, J, Q, K\nS, H, C, D\nEnter the 5 cards (spaces):").toUpperCase();
			rules = getRules(input);
		}
		String[] cards = input.split(" "), ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		char suit = cards[4].charAt(cards[4].length() - 1);
		int rank = rankToNumber(cards[4].substring(0, cards[4].length() - 1));
		String order = rules + "", possSuits = "";
		ArrayList<String> possRanks = new ArrayList<String>();
		for(int aa = 0; aa < ranks.length; aa++)
			possRanks.add(ranks[aa].toUpperCase());
		for(int aa = 0; aa < order.length(); aa++)
		{
			switch(order.charAt(aa))
			{
				case '1':
					int row = 0;
					if(!(ew.isSNDIG(0)) && ew.isSNDIG(1))
						row = 1;
					else if(ew.isSNDIG(0) && !(ew.isSNDIG(1)))
						row = 2;
					else if(ew.isSNDIG(0) && ew.isSNDIG(1))
						row = 3;
					possSuits = table[row]["SHCD".indexOf(suit)];
					break;
				case '2':
					int div = ("-ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(ew.getSNCHAR(3)) % 3) + 3;
					ArrayList<String> temp1 = new ArrayList<String>();
					if(rank % div == 0)
					{
						for(int bb = 0; bb < possRanks.size(); bb++)
						{
							int tempRank = rankToNumber(possRanks.get(bb));
							if(tempRank % div != 0)
								temp1.add(possRanks.get(bb));
						}
					}
					else
					{
						for(int bb = 0; bb < possRanks.size(); bb++)
						{
							int tempRank = rankToNumber(possRanks.get(bb));
							if(tempRank % div == 0)
								temp1.add(possRanks.get(bb));
						}
					}
					possRanks = temp1;
					break;
				case '3':
					int x = ("-ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(ew.getSNCHAR(4)) % 3);
					int[] diffs = {(-1) * (x + 3), (-1) * (x + 2), (x + 2), (x + 3)};
					ArrayList<String> temp2 = new ArrayList<String>();
					for(int bb = 0; bb < 4; bb++)
					{
						diffs[bb] = rank + diffs[bb];
						while(diffs[bb] < 1)
							diffs[bb] += 13;
						while(diffs[bb] > 13)
							diffs[bb] -= 13;
					}
					for(int bb = 0; bb < possRanks.size(); bb++)
					{
						int tempRank = rankToNumber(possRanks.get(bb));
						if(tempRank == diffs[0] || tempRank == diffs[1] || tempRank == diffs[2] || tempRank == diffs[3])
							temp2.add(possRanks.get(bb));
					}
					possRanks = temp2;
					break;
			}
		}
		String out = possRanks.get(0).toUpperCase();
		for(int aa = 1; aa < possRanks.size(); aa++)
			out = out + "/" + possRanks.get(aa).toUpperCase();
		if(possSuits.length() > 0)
			out = out + "\n" + possSuits;
		JOptionPane.showMessageDialog(null, "Play one of these cards:\n" + out);
	}
	private int getRules(String i)
	{
		String[] cards = i.split(" ");
		if(cards.length == 5)
		{
			int[] ranks = new int[5];
			String suits = "";
			for(int aa = 0; aa < 5; aa++)
			{
				ranks[aa] = rankToNumber(cards[aa].substring(0, (cards[aa].length() - 1)));
				suits = suits + "" + cards[aa].charAt(cards[aa].length() - 1);
				if(ranks[aa] == 0 || "SHCD".indexOf(suits.charAt(aa)) < 0)
					return 0;
			}
			boolean[] b = {rule1(suits), rule2(ranks), rule3(ranks)};
			if(b[0] && b[1] && !(b[2]))
				return 12;
			else if(b[0] && !(b[1]) && b[2])
				return 13;
			else if(!(b[0]) && b[1] && b[2])
				return 23;
		}
		return 0;
	}
	private boolean rule1(String suits)
	{
		int row = 0;
		if(!(ew.isSNDIG(0)) && ew.isSNDIG(1))
			row = 1;
		else if(ew.isSNDIG(0) && !(ew.isSNDIG(1)))
			row = 2;
		else if(ew.isSNDIG(0) && ew.isSNDIG(1))
			row = 3;
		for(int aa = 1; aa < suits.length(); aa++)
		{
			if(table[row]["SHCD".indexOf(suits.charAt(aa - 1))].indexOf(suits.charAt(aa)) < 0)
				return false;
		}
		return true;
	}
	private boolean rule2(int[] ranks)
	{
		int div = ("-ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(ew.getSNCHAR(3)) % 3) + 3;
		if(ranks[0] % div == 0)
		{
			for(int aa = 1; aa < ranks.length; aa++)
			{
				if(aa % 2 == 1 && ranks[aa] % div == 0)
					return false;
				if(aa % 2 == 0 && ranks[aa] % div != 0)
					return false;
			}
		}
		else
		{
			for(int aa = 1; aa < ranks.length; aa++)
			{
				if(aa % 2 == 0 && ranks[aa] % div == 0)
					return false;
				if(aa % 2 == 1 && ranks[aa] % div != 0)
					return false;
			}
		}
		return true;
	}
	private boolean rule3(int[] ranks)
	{
		int x = ("-ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(ew.getSNCHAR(4)) % 3);
		for(int aa = 1; aa < ranks.length; aa++)
		{
			int diff = ranks[aa] - ranks[aa - 1];
			while(diff > 5)
				diff -= 13;
			while(diff < -5)
				diff += 13;
			if(diff < 0)
				diff *= -1;
			//System.out.println(diff);
			if((x + 2) != diff && (x + 3) != diff)
				return false;
		}
		return true;
	}
	private int rankToNumber(String i)
	{
		switch(i)
		{
			case "A":
				return 1;
			case "J":
				return 11;
			case "Q":
				return 12;
			case "K":
				return 13;
			case "2": case "3": case "4": case "5": case "6": case "7": case "8": case "9": case "10":
				return Integer.parseInt(i);
		}
		return 0;
	}
}
