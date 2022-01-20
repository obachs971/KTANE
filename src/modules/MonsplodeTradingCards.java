package modules;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import start.BombEdgework;

public class MonsplodeTradingCards 
{
	private final BombEdgework ew;
	private final int col;
	private final double r;
	private int[][] table =
		{
				{2,3,4,2},	{2,5,2,2},
				{2,4,2,5},	{5,2,2,3},
				{2,4,3,2},	{3,2,4,5},
				{2,4,2,4},	{2,4,2,5},
				{2,3,4,2},	{5,2,2,2},
				{2,3,4,3},	{3,3,3,2},
				{4,3,2,3},	{2,4,4,3},
				{2,4,3,3},	{2,2,4,3},
				{3,3,2,4},	{3,3,2,4},
				{2,3,3,3},	{4,2,3,3},
				{4,2,3,2},	{3,4,2,2},
				{3,4,2,3},	{4,2,2,4},
				{6,4,5,3},	{5,6,4,4},
				{6,5,3,4},	{4,4,4,6}
		};
	public MonsplodeTradingCards(BombEdgework e, double resize)
	{
		ew = e;
		if(ew.isSNDIG(0) && ew.isSNDIG(1))
			col = 3;
		else if(ew.isSNDIG(0))
			col = 2;
		else if(ew.isSNDIG(1))
			col = 1;
		else
			col = 0;
		r = resize;
	}
	public String run()
	{
		//Setting up buttons
		String[] imgName = 
			{
				"Aluga", "Asteran", "Bob", "Buhar", "Caadarim",
				"Clondar", "Cutie Pie", "Docsplode", "Flaurim", "Gloorim",
				"Lanaluff", "Lugirit", "Magmy", "Melbor", "Mountoise",
				"Myrchat", "Nibs", "Percy", "Pouse", "Ukkens",
				"Vellarim", "Violan", "Zapra", "Zenlad", 
				"Aluga The Fighter", "Bob The Ancestor", "Buhar The Protector", "Melbor The Web Bug"
			};
		int[] scores = new int[3];
		String[][] info = new String[4][];
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		ImageIcon[] icon = new ImageIcon[imgName.length];
		JButton[] jButton = new JButton[imgName.length];
		optionPane.setLayout(new GridLayout(7, 4));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int bb = 0; bb < imgName.length; bb++)
		{
			icon[bb] = new ImageIcon("img/Monsplode" + imgName[bb].replace(" ", "") + ".jpg");
			Image image = icon[bb].getImage();
			image = image.getScaledInstance((int)(icon[bb].getIconWidth() / r), (int)(icon[bb].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			icon[bb] = new ImageIcon(image);
			jButton[bb] = getButton(optionPane, imgName[bb], bb + "", icon[bb]);
			optionPane.add(jButton[bb]);
		}
		String[] rarities = { "Common", "Uncommon", "Rare", "Ultra Rare" };
		String[] shiny = {"Shiny", "Not Shiny"};
		JFrame f2 = new JFrame();
		JOptionPane op2 = new JOptionPane();
		ImageIcon[] i2 = new ImageIcon[rarities.length];
		JButton[] jb2 = new JButton[rarities.length];
		op2.setLayout(new GridLayout(2, 2));
		op2.setOptions(new Object[] {});
		op2.removeAll();
		for(int bb = 0; bb < rarities.length; bb++)
		{
			i2[bb] = new ImageIcon("img/Monsplode" + rarities[bb].replace(" ", "") + ".jpg");
			Image image = i2[bb].getImage();
			image = image.getScaledInstance((int)(i2[bb].getIconWidth() / r), (int)(i2[bb].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			i2[bb] = new ImageIcon(image);
			jb2[bb] = getButton(op2, rarities[bb], (bb + 4) + "", i2[bb]);
			op2.add(jb2[bb]);
		}
		//Input Player's Hand
		for(int aa = 0; aa < 3; aa++)
		{
			info[aa] = inputCardInfo(optionPane, frame, op2, f2, "Select the Monsplode of card #" + (aa + 1));
			scores[aa] = getScore(info[aa]);
		}
		//Begin Rounds
		for(int aa = 0; aa < 3; aa++)
		{
			info[3] = inputCardInfo(optionPane, frame, op2, f2, "Select the offered Monsplode");
			int score = getScore(info[3]);
			if(scores[0] > score && scores[1] > score && scores[2] > score)
				JOptionPane.showMessageDialog(null, "Press Keep");
			else
			{
				int index = 2;
				if(scores[0] <= scores[1] && scores[0] <= scores[2])
					index = 0;
				else if(scores[1] <= scores[0] && scores[1] <= scores[2])
					index = 1;
				String out = imgName[Integer.parseInt(info[index][0])] + "\n" + info[index][1] + "\n" + rarities[Integer.parseInt(info[index][2]) - 4] + "\n" + shiny[Integer.parseInt(info[index][3])] + "\n" + info[index][4] + " Bent Corner(s)";
				JOptionPane.showMessageDialog(null, "Trade card #" + (index + 1) + "\n" + out);
				if(aa < 2)
				{
					for(int cc = 0; cc < 5; cc++)
						info[index][cc] = info[3][cc].toUpperCase();
					scores[index] = score + 0;
				}
			}
		}
		String souv = "";
		String[] list = {"CARD #1", "CARD #2", "CARD #3", "OFFERED"};
		for(int aa = 0; aa < 4; aa++)
			souv = souv + "" + list[aa] + "\n" + imgName[Integer.parseInt(info[aa][0])].toUpperCase() + "\n" + info[aa][1].toUpperCase() + "\n" + rarities[Integer.parseInt(info[aa][2]) - 4] + "\n------------------------------\n";
		JOptionPane.showMessageDialog(null, souv);
		return souv;
	}
	private String[] inputCardInfo(JOptionPane optionPane, JFrame frame, JOptionPane op2, JFrame f2, String message)
	{
		String[] info = new String[5];
		JDialog dialog = optionPane.createDialog(frame, "");
		dialog.setTitle(message);
		dialog.setVisible(true);
		info[0] = optionPane.getValue().toString();
		dialog.setVisible(false);
		info[1] = JOptionPane.showInputDialog("Enter the print version:").toUpperCase();
		boolean v = v1(info[1]);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			info[1] = JOptionPane.showInputDialog("Enter the print version:").toUpperCase();
			v = v1(info[1]);
		}
		//Need to input card rarity
		JDialog d2 = op2.createDialog(f2, "");
		d2.setTitle("Select the rarity");
		d2.setVisible(true);
		info[2] = op2.getValue().toString();
		d2.setVisible(false);
		info[3] = JOptionPane.showConfirmDialog(null, "Is the card shiny?") + "";
		info[4] = JOptionPane.showInputDialog("Enter the number\nof bent corners");
		v = v2(info[4]);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			info[4] = JOptionPane.showInputDialog("Enter the number\nof bent corners");
			v = v2(info[4]);
		}
		return info;
	}
	private int getScore(String[] info)
	{
		int score = table[Integer.parseInt(info[0])][col];
		score += ew.numLitWithChars(info[1].substring(0, 1));
		score -= ew.numUnlitWithChars(info[1].substring(0, 1));
		if(ew.BAT() > 0)
		{
			int num = "0123456789".indexOf(info[1].charAt(1));
			if(num > ew.BAT())
				score += 1;
			else if(num < ew.BAT())
				score -= 1;
			else
				score += 2;
		}
		if("-ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(info[1].charAt(0)) == "0123456789".indexOf(info[1].charAt(1)))
			score = 0;
		System.out.println("Base Score: " + score);
		int mult = Integer.parseInt(info[2]) - Integer.parseInt(info[4]);
		if(info[3].equals("0"))
			mult += 2;
		score *= mult;
		if(score < 0)
			score = 0;
		System.out.println("Multiplier: " + mult);
		System.out.println("Final Score: " + score);
		String temp = (score * 25) + "";
		if(temp.length() < 3)
			temp = "0" + temp;
		System.out.println("Actual Score: " + temp.substring(0, temp.length() - 2) + "." + temp.substring(temp.length() - 2));
		System.out.println("-------------------------------------");
		return score;
	}
	private boolean v1(String i)
	{
		if(i.length() == 2)
		{
			if("ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(i.charAt(0)) >= 0 && "0123456789".indexOf(i.charAt(1)) >= 0)
				return true;
		}
		return false;
	}
	private boolean v2(String i)
	{
		switch(i)
		{
			case "0":
			case "1":
			case "2":
			case "3":
			case "4":
				return true;
		}
		return false;
	}
	private JButton getButton(final JOptionPane optionPane, String text, String value, ImageIcon icon ) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    button.setText(text);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(value.toUpperCase());
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
}
