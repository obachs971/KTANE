package modules;

import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import start.BombConfig;
import start.BombEdgework;

public class Laundry 
{
	private final int[][] itemChart =
		{
				{7, 5, 4, 1, 10, 9},
				{7, 3, 5, 0, 8, 11},
				{3, 5, 0, 4, 3, 2},
				{0, 5, 10, 10, 1, 8}
		};
	private final int[][] materialChart =
		{
				{6, 9, 2, 4, 5, 3},
				{4, 2, 8, 6, 6, 9},
				{3, 0, 4, 5, 2, 1},
				{6, 8, 10, 11, 7, 5}
		};
	private final int[][] colorChart =
		{
				{7, 9, 4, 4, 0, 7},
				{3, 7, 0, 10, 1, 2},
				{1, 0, 4, 3, 5, 4},
				{4, 11, 9, 12, 11, 2}
		};
	private final String[] washList ={"Permanent Press","Gentle","Hand Wash","Don't Wash","30c","40c","50c","60c","70c","95c","Don't Wring"};
	private final String[] dryList ={"Tumble Dry","Low Heat","Medium Heat","High Heat","No Heat","Hang Dry","Drip Dry","Dry Flat","Dry In Shade","Don't Dry","Don't Tumble Dry","Dry"};
	private final String[] ironList ={"Iron","Don't Iron","110c","300f","200c","No Steam"};
	private final String[] specialList ={"Bleach","Don't Bleach","No Chlorine","Dry Clean","Any Solvent","No Tetrachlore","Petroleum Only","Wet Cleaning","Don't Dry Clean","Short Cycle","Reduced Moist","Low Heat","No Steam Finish"};
	private final String[] materialList = {"POLYESTER", "COTTON", "WOOL", "NYLON", "CORDUROY", "LEATHER"};
	private final BombConfig cf;
	private final BombEdgework ew;
	private final double resizer;
	public Laundry(BombConfig c, BombEdgework e, double r)
	{
		cf = c;
		ew = e;
		resizer = r;
	}
	public void run()
	{
		String input = JOptionPane.showInputDialog("6n solves\nEnter the number\nof solved modules:");
		boolean v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("6n solves\nEnter the number\nof solved modules:");
			v = valid(input);
		}
		int solved = Integer.parseInt(input);
		int item = mod((cf.getNumberModules() - (solved + cf.getNumberNeedies())) + ew.numInd());
		int material = mod(ew.numPorts() + solved - ew.BH());
		int color = mod(ew.getSNDIG(ew.numSNDIGS() - 1) + ew.BAT());
		ArrayList<Integer> special = new ArrayList<Integer>();
		int wash = materialChart[0][material];
		int dry = colorChart[1][color];
		int iron = itemChart[2][item];
		special.add(itemChart[3][item]);
		if(color == 4)
			special.add(2);
		if(material == 5 || color == 3)
			wash = 4;
		if(special.size() < 2 && (item == 0 || material == 4))
			special.add(materialChart[3][material]);
		if(material == 2 || color == 1)
			dry = 3;
		if(special.size() < 2 && ew.numCharsInSN(materialList[material]) > 0)
			special.add(colorChart[3][color]);
		JFrame f = new JFrame();
		f.setLayout(new GridLayout(1, 2));
		ImageIcon[] i = new ImageIcon[2];
		JLabel[] l = new JLabel[2];
		i[0] = new ImageIcon("img/LaundryWash" + wash + ".png");
		i[1] = new ImageIcon("img/LaundryDry" + dry + ".png");
		for(int aa = 0; aa < 2; aa++)
		{
			Image image = i[aa].getImage();
			image = image.getScaledInstance((int)(i[aa].getIconWidth() / resizer), (int)(i[aa].getIconHeight() / resizer), java.awt.Image.SCALE_SMOOTH);
			i[aa] = new ImageIcon(image);
			l[aa] = new JLabel();
			l[aa].setIcon(i[aa]);
			f.add(l[aa]);
		}
		f.pack();
		f.setVisible(true);
		JOptionPane.showMessageDialog(null, "Wash: " + washList[wash] + "\nDry: " + dryList[dry] + "\nIron: " + ironList[iron] + "\nSpecial: " + specialList[special.get(special.size() - 1)]);
		f.setVisible(false);
	}
	private int mod(int n)
	{
		while(n < 0)
			n += 6;
		return (n % 6);
	}
	private boolean valid(String i)
	{
		if(i.length() > 0)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("0123456789".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
}
