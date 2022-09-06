package modules;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import misc.PlayType;
import start.BombEdgework;

public class JewelVault {
	
	private String sub;
	private final String[] letterList =
		{
			"Alpha","Beta","Gamma","Delta","Epsilon","Zeta",
			"Eta","Theta","Iota","Kappa","Lambda","Mu",
			"Nu","Xi","Omicron","Pi","Rho","Sigma",
			"Tau","Upsilon","Phi","Chi","Psi","Omega"
		};
	private final int[][][] tables =
		{
				{
					{1,9,3,11,6,7},
					{9,5,6,1,9,2},
					{3,6,2,7,10,8},
					{1,11,7,12,4,5},
					{6,9,10,4,10,12},
					{7,2,8,5,12,4}
				},
				{
					{5,8,3,9,7,4},
					{8,2,7,1,5,10},
					{3,7,11,6,12,2},
					{9,1,6,4,3,8},
					{7,5,12,3,11,12},
					{4,10,2,8,12,9}
				},
				{
					{12,5,10,1,5,3},
					{5,2,6,5,11,8},
					{10,6,8,3,12,2},
					{1,5,3,11,1,10},
					{5,11,12,1,4,9},
					{3,8,2,10,9,6}
				},
				{
					{9,4,1,10,6,2},
					{4,3,7,4,12,8},
					{1,7,8,11,9,3},
					{10,4,11,1,10,6},
					{6,12,9,10,5,11},
					{2,8,3,6,11,7}
				}
		};
	private final String[][] priorityTable =
		{
			{"Po","Ru","Sa","Em","On","Am","Sc","Gl"},
			{"Am","On","Em","Sc","Sa","Po","Gl","Ru"},
			{"On","Sa","Ru","Am","Sc","Gl","Em","Po"},
			{"Em","Sc","Po","Sa","Gl","Ru","On","Am"},
			{"Ru","Am","Sc","Gl","Em","Sa","Po","On"},
			{"Sc","Em","Gl","Ru","Po","On","Am","Sa"},
			{"Sa","Gl","On","Po","Am","Em","Ru","Sc"},
			{"Gl","Po","Am","On","Ru","Sc","Sa","Em"},
			{"On","Sc","Em","Sa","Po","Am","Ru","Gl"},
			{"Po","Am","Ru","Gl","On","Sc","Em","Sa"},
			{"Gl","Em","Am","On","Sa","Ru","Sc","Po"},
			{"Sa","Ru","Sc","Po","Gl","Em","Am","On"}
		};
	private final BombEdgework ew;
	private final PlayType pt;
	private final double r;
	public JewelVault(BombEdgework e, PlayType p, double resizer)
	{
		ew = e;
		pt = p;
		r = resizer;
	}
	public String run()
	{
		String souv;
		//Input Greek Letters
		ArrayList<ArrayList<Integer>> greekLetters = getGreekLetters();
		if(pt == PlayType.Team)
		{
			//Get Target Orientation using the Most Jewels
			String orientation = getTargetOrientation();
			//Input which Wheel is A - D
			String[] wheels = getLetteredWheels();
			//Determine Wheel Order
			int[] order = getOrder(wheels);
			//Output the list of each Jewel Priority List
			getTargetJewels(greekLetters, orientation, order);
			souv = "Wheel A: " + order[3] + "\nWheel B: " + order[2] + "\nWheel C: " + order[1] + "\nWheel D: " + order[0]; 
		}
		else
		{
			//Input the Jewels
			String[][] jewels = getJewels();
			//Input which Wheel is A - D
			String[] wheels = getLetteredWheels();
			//Determine Target Orientation
			String orientation = getTargetOrientation(jewels);
			//Determine the Jewel for each wheel
			String[] targetJewels = getTargetJewels(greekLetters, jewels);
			//Determine Wheel Order
			String[] order = getOrder(wheels, targetJewels);
			JOptionPane.showMessageDialog(null, "Orientation: " + orientation + "\n" + order[0] + "\n" + order[1] + "\n" + order[2] + "\n" + order[3] + "\nJewel Order:\n" + targetJewels[0] + "\n" + targetJewels[1] + "\n" + targetJewels[2] + "\n" + targetJewels[3]);
			int[] temp = getOrder(wheels);
			souv = "Wheel A: " + temp[3] + "\nWheel B: " + temp[2] + "\nWheel C: " + temp[1] + "\nWheel D: " + temp[0];
		}
		return souv;
	}
	private ArrayList<ArrayList<Integer>> getGreekLetters()
	{
		ArrayList<String> list = new ArrayList<String>();
		for(String str : letterList)
			list.add(str);
		Collections.sort(list);
		ArrayList<ArrayList<Integer>> numbers = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < 4; i++)
			numbers.add(new ArrayList<Integer>());
		for(int i = 0; i < 8; i ++)
		{
			JDialog dialog = getDialog(list, 3, true);
			dialog.setTitle("Select Letter #" + (i + 1) + ":");
			dialog.setVisible(true);
			int table = getTable(sub);
			numbers.get(table).add(getColRow(sub));
			if(numbers.get(table).size() == 2)
			{
				for(int j = 0; j < 6; j++)
					list.remove(letterList[table * 6 + j]);
			}
		}
		return numbers;
	}
	private String[][] getJewels()
	{
		String[][] jewels = new String[4][4]; 
		for(int i = 0; i < 4; i++)
		{
			String input = JOptionPane.showInputDialog("R/Ru - Red/Ruby\nY/Sc - Yellow/Scapolite\nB/Sa - Blue/Sapphire\nG/Em - Green/Emerald\nP/Am - Purple/Amethyst\nI/Po - Pink/Poudretteite\nW/Gl - White/Glass\nK/On - Black/Onyx\nEnter the Gems\non Wheel #" + (i + 1) + " (spaces):").toUpperCase();
			boolean v = v1(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("R/Ru - Red/Ruby\nY/Sc - Yellow/Scapolite\nB/Sa - Blue/Sapphire\nG/Em - Green/Emerald\nP/Am - Purple/Amethyst\nI/Po - Pink/Poudretteite\nW/Gl - White/Glass\nK/On - Black/Onyx\nEnter the Gems\non Wheel #" + (i + 1) + " (spaces):").toUpperCase();
				v = v1(input);
			}
			String[] temp = input.split(" ");
			for(int j = 0; j < 4; j++)
				jewels[i][j] = colorToGem(temp[j]);
		}
		return jewels;
	}
	private String[] getLetteredWheels()
	{
		boolean flag = true;
		String[] turns = new String[4];
		int wheelA = -1;
		for(int i = 0; i < 3; i++)
		{
			ArrayList<String> choices = new ArrayList<String>();
			for(int j = 0; j < 4; j++)
			{
				boolean check = check(i + 1, j + 1, wheelA, turns);
				if(check)
					choices.add("Wheels " + (i + 1) + " & " + (j + 1));
			}
			if(flag)
				choices.add("Just Wheel " + (i + 1));
			JDialog dialog = getDialog(choices, 1, false);
			dialog.setTitle("Wheel #" + (i + 1) + " turns:");
			dialog.setVisible(true);
			if(sub.startsWith("J"))
			{
				wheelA = sub.charAt(sub.length() - 1) - '0';
				flag = false;
			}
			turns[i] = (sub.charAt(sub.length() - 1) + "");
		}
		//Determine the 4th Wheel
		if(wheelA == -1)
			turns[3] = "4";
		else
		{
			int turn = wheelA;
			flag = true;
			while(flag)
			{
				flag = false;
				for(int i = 0; i < (turns.length - 1); i++)
				{
					boolean check = check(i + 1, wheelA, turn, Integer.parseInt(turns[i]));
					if(check)
					{
						flag = true;
						turn = i + 1;
						break;
					}
				}
			}
			turns[3] = (turn + "");
		}
		return turns;
	}
	private String getTargetOrientation(String[][] jewels)
	{
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i < 8; i++)
			list.add(priorityTable[0][i].toUpperCase());
		int[] sums = {0, 0, 0, 0, 0, 0, 0, 0};
		for(String[] wheel : jewels)
		{
			for(String jewel : wheel)
				sums[list.indexOf(jewel)]++;
		}
		int best = getBest(sums);
		list.clear();
		for(int i = 0; i < sums.length; i++)
		{
			if(sums[i] == best)
				list.add(priorityTable[0][i].toUpperCase());
		}
		if(list.size() > 1)
		{
			int row = mod(ew.getSNDIG(ew.numSNDIGS() - 1) - 1, 10);
			for(String jewel : priorityTable[row])
			{
				if(list.contains(jewel.toUpperCase()))
				{
					list.clear();
					list.add(jewel.toUpperCase());
					break;
				}
			}
		}
		switch(list.get(0))
		{
			case "GL": case "PO": 
				return "N";
			case "AM": case "EM": 
				return "E";
			case "ON": case "SA": 
				return "S";
			default: 
				return "W";
		}
	}
	private String getTargetOrientation()
	{
		String input = JOptionPane.showInputDialog("R/Ru - Red/Ruby\nY/Sc - Yellow/Scapolite\nB/Sa - Blue/Sapphire\nG/Em - Green/Emerald\nP/Am - Purple/Amethyst\nI/Po - Pink/Poudretteite\nW/Gl - White/Glass\nK/On - Black/Onyx\nEnter the Gem(s) with\nthe highest amount (spaces):").toUpperCase();
		boolean v = v2(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("R/Ru - Red/Ruby\nY/Sc - Yellow/Scapolite\nB/Sa - Blue/Sapphire\nG/Em - Green/Emerald\nP/Am - Purple/Amethyst\nI/Po - Pink/Poudretteite\nW/Gl - White/Glass\nK/On - Black/Onyx\nEnter the Gem(s) with\nthe highest amount (spaces):").toUpperCase();
			v = v2(input);
		}
		String[] spl = input.split(" ");
		if(spl.length > 1)
		{
			ArrayList<String> list = new ArrayList<String>();
			for(String str : spl)
				list.add(colorToGem(str));
			int row = mod(ew.getSNDIG(ew.numSNDIGS() - 1) - 1, 10);
			System.out.println(row);
			for(String jewel : priorityTable[row])
			{
				if(list.contains(jewel.toUpperCase()))
				{
					spl[0] = jewel.toUpperCase();
					break;
				}
			}
		}
		
		switch(colorToGem(spl[0]))
		{
			case "GL": case "PO": 
				return "N";
			case "AM": case "EM": 
				return "E";
			case "ON": case "SA": 
				return "S";
			default: 
				return "W";
		}
	}
	private String[] getTargetJewels(ArrayList<ArrayList<Integer>> greekLetters, String[][] jewels)
	{
		String[] targets = new String[4];
		for(int i = 0; i < 4; i++)
		{
			ArrayList<String> jewelList = new ArrayList<String>();
			for(String jewel : jewels[i])
				jewelList.add(jewel);
			int row = tables[i][greekLetters.get(i).get(0)][greekLetters.get(i).get(1)] - 1;
			for(String jewel : priorityTable[row])
			{
				if(jewelList.contains(jewel.toUpperCase()))
				{
					targets[i] = gemToColor(jewel.toUpperCase());
					break;
				}
			}
		}
		return targets;
	}
	private void getTargetJewels(ArrayList<ArrayList<Integer>> greekLetters, String orientation, int[] order)
	{
		for(int i = 0; i < 4; i++)
		{
			String out = "Target Orietnation: " + orientation + "\nSet Wheel #" + order[i] + " to the top most\ngem in the list below:";
			int row = tables[order[i] - 1][greekLetters.get(order[i] - 1).get(0)][greekLetters.get(order[i] - 1).get(1)] - 1;
			for(String jewel : priorityTable[row])
				out = out + "\n" + gemToColor(jewel.toUpperCase());
			JOptionPane.showMessageDialog(null, out);
		}
	}
	private String[] getOrder(String[] wheels, String[] targetJewels)
	{
		ArrayList<String> list = new ArrayList<String>();
		int wheelA = -1;
		for(int i = 0; i < wheels.length; i++)
		{
			if(wheels[i].charAt(0) == "1234".charAt(i))
			{
				wheelA = i + 1;
				break;
			}
		}
		list.add(wheelA + "");
		int turn = wheelA;
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < wheels.length; j++)
			{
				boolean check = check(j + 1, wheelA, turn, Integer.parseInt(wheels[j]));
				if(check)
				{
					list.add((j + 1) + "");
					turn = j + 1;
					break;
				}
			}
		}
		String[] order = new String[4];
		for(int i = 0; i < order.length; i++)
			order[i] = "Wheel " + list.get(3 - i) + ": " + targetJewels[Integer.parseInt(list.get(3 - i)) - 1];
		return order;	
	}
	private int[] getOrder(String[] wheels)
	{
		ArrayList<String> list = new ArrayList<String>();
		int wheelA = -1;
		for(int i = 0; i < wheels.length; i++)
		{
			if(wheels[i].charAt(0) == "1234".charAt(i))
			{
				wheelA = i + 1;
				break;
			}
		}
		list.add(wheelA + "");
		int turn = wheelA;
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < wheels.length; j++)
			{
				boolean check = check(j + 1, wheelA, turn, Integer.parseInt(wheels[j]));
				if(check)
				{
					list.add((j + 1) + "");
					turn = j + 1;
					break;
				}
			}
		}
		int[] order = new int[4];
		for(int i = 0; i < order.length; i++)
			order[i] = Integer.parseInt(list.get(3 - i));
		return order;	
	}
	private int getTable(String letter)
	{
		for(int i = 0; i < letterList.length; i++)
		{
			if(letterList[i].equals(letter))
				return (i / 6);
		}
		return -1;
	}
	private int getColRow(String letter)
	{
		for(int i = 0; i < letterList.length; i++)
		{
			if(letterList[i].equals(letter))
				return (i % 6);
		}
		return -1;
	}
	private String colorToGem(String i)
	{
		switch(i)
		{
			case "R": case "RU": case "RED": case "RUBY":
				return "RU";
			case "B": case "SA": case "BLUE": case "SAPPHIRE":
				return "SA";
			case "Y": case "SC": case "YELLOW": case "SCAPOLITE":
				return "SC";
			case "G": case "EM": case "GREEN": case "EMERALD":
				return "EM";
			case "P": case "AM": case "PURPLE": case "AMETHYST":
				return "AM";
			case "I": case "PO": case "PINK": case "POUDRETTEITE":
				return "PO";
			case "W": case "GL": case "WHITE": case "GLASS":
				return "GL";
			case "K": case "ON": case "BLACK": case "ONYX":
				return "ON";		
		}
		return "";
	}
	private String gemToColor(String i)
	{
		String[][] list =
			{
					{"RU", "SA", "SC", "EM", "AM", "PO", "GL", "ON"},
					{"RED", "BLUE", "YELLOW", "GREEN", "PURPLE", "PINK", "WHITE", "BLACK"}
			};
		for(int aa = 0; aa < list[0].length; aa++)
		{
			if(list[0][aa].equals(i))
				return list[1][aa];
		}
		return "";
	}
	private boolean check(int wheel1, int wheel2, int wheelA, String[] turns)
	{
		if(wheel1 == wheel2)
			return false;
		if(wheel2 == wheelA)
		{
			int sum = 0;
			for(String str : turns)
			{
				if(str != null)
				{
					if(Integer.parseInt(str) == wheel2)
						sum++;
				}
			}
			if(sum >= 2)
				return false;
			else 
				return true;
		}
		for(String str : turns)
		{
			if(str != null)
			{
				if(Integer.parseInt(str) == wheel2)
					return false;
			}
		}
		return check(wheel1, wheel2, turns);
	}
	private boolean check(int wheel1, int wheel2, String[] turns)
	{
		if(turns[wheel2 - 1] == null)
			return true;
		else if(Integer.parseInt(turns[wheel2 - 1]) != wheel1)
		{
			if(Integer.parseInt(turns[wheel2 - 1]) == wheel2)
				return true;
			else
				return check(wheel1, Integer.parseInt(turns[wheel2 - 1]), turns);
		}
		else
			return false;
	}
	private boolean check(int wheel, int wheelA, int turn, int turning)
	{
		if(wheel == wheelA)
			return false;
		return (turning == turn);
	}
	private int mod(int n, int m)
	{
		while(n < 0)
			n += m;
		return (n % m);
	}
	private int getBest(int[] sums)
	{
		int best = 0;
		for(int sum : sums)
		{
			if(sum > best)
				best = sum;
		}
		return best;
	}
	private boolean v1(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 4)
		{
			for(int aa = 0; aa < conv.length; aa++)
			{
				String str = colorToGem(conv[aa]);
				if(str.length() == 0)
					return false;
				for(int bb = aa + 1; bb < conv.length; bb++)
				{
					if(conv[aa].equals(conv[bb]))
						return false;
				}
			}
			return true;
		}
		return false;
	}
	private boolean v2(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length != 0)
		{
			for(int aa = 0; aa < conv.length; aa++)
			{
				String str = colorToGem(conv[aa]);
				if(str.length() == 0)
					return false;
				for(int bb = aa + 1; bb < conv.length; bb++)
				{
					if(conv[aa].equals(conv[bb]))
						return false;
				}
			}
			return true;
		}
		return false;
	}
	private JDialog getDialog(ArrayList<String> arr, int div, boolean isImg)
	{
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		JButton[] jButton = new JButton[arr.size()];
		optionPane.setLayout(new GridLayout(arr.size() / div + (arr.size() % div), div));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		if(isImg)
		{
			ImageIcon[] icon = new ImageIcon[arr.size()];
			for(int aa = 0; aa < arr.size(); aa++)
			{
				icon[aa] = new ImageIcon("img/JewelVault" + arr.get(aa) + ".png");
				Image image = icon[aa].getImage();
				image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
				icon[aa] = new ImageIcon(image);
				jButton[aa] = getButton(optionPane, arr.get(aa), icon[aa]);
				optionPane.add(jButton[aa]);
			}
		}
		else
		{
			for(int aa = 0; aa < arr.size(); aa++)
			{
				jButton[aa] = getButton(optionPane, arr.get(aa));
				optionPane.add(jButton[aa]);
			}
		}
		return optionPane.createDialog(frame, "");
	}
	private JButton getButton(final JOptionPane optionPane, String text) {
	    final JButton button = new JButton();
	    button.setText(text);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(text.toUpperCase());
	        sub = text + "";
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
	private JButton getButton(final JOptionPane optionPane, String text, ImageIcon icon) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    button.setText(text);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(text.toUpperCase());
	        sub = text + "";
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
}
