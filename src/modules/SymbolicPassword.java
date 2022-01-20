package modules;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class SymbolicPassword 
{
	private final int[][] slist = 
		{
				{1, 8, 12, 16, 20, 16, 11},
				{2, 1, 13, 17, 19, 8, 10},
				{3, 7, 9, 18, 18, 24, 1},
				{4, 9, 14, 5, 21, 25, 3},
				{5, 10, 15, 14, 17, 20, 9},
				{6, 6, 3, 11, 22, 26, 8},
				{7, 11, 10, 19, 23, 27, 13}
		};
	private ArrayList<Integer> symbolNumber;
	private final double r;
	public SymbolicPassword(double resize)
	{
		r = resize;
		symbolNumber = new ArrayList<Integer>();
	}
	public void run()
	{
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		ImageIcon[] icon = new ImageIcon[27];
		JButton[] jButton = new JButton[27];
		optionPane.setLayout(new GridLayout(9, 3));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int aa = 0; aa < 27; aa++)
		{
			icon[aa] = new ImageIcon("img/Keypad" + (aa + 1) + ".jpg");
			Image image = icon[aa].getImage();
			image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			icon[aa] = new ImageIcon(image);
			jButton[aa] = getButton(optionPane, (aa + 1), icon[aa]);
			optionPane.add(jButton[aa]);
		}
		int[] score = {
				0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0
				};
		for(int aa = 0; aa < 6; aa++)
		{
			JDialog dialog = optionPane.createDialog(frame, "");
			dialog.setTitle("Select symbol #" + (aa + 1) + ":");
			dialog.setVisible(true);
			score[symbolNumber.get(aa) - 1]++;
		}
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for(int aa = 0; aa < 6; aa++)
		{
			for(int bb = 0; bb < 5; bb++)
			{
				int[] temp = {slist[aa][bb], slist[aa][bb + 1], slist[aa][bb + 2], slist[aa + 1][bb], slist[aa + 1][bb + 1], slist[aa + 1][bb + 2]};
				int[] tempScore = {
						0, 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 0, 0, 0, 0, 0
						};
				for(int cc = 0; cc < 6; cc++)
					tempScore[temp[cc] - 1]++;
				boolean flag = true;
				for(int cc = 0; cc < 27; cc++)
				{
					if(tempScore[cc] != score[cc])
					{
						flag = false;
						break;
					}
				}
				if(flag)
				{
					numbers.add(temp[0] + 0);
					numbers.add(temp[1] + 0);
					numbers.add(temp[2] + 0);
					numbers.add(temp[3] + 0);
					numbers.add(temp[4] + 0);
					numbers.add(temp[5] + 0);
					break;
				}
			}
			if(numbers.size() > 0)
				break;
		}
		if(numbers.size() == 0)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			run();
		}
		else
		{
			String out = "Do these moves:";
			String[][] allMoves = 
				{
						{"", "TL", "TR", "L", "BL L", "BR L"},
						{"", "", "R BL M", "BR M", "M", "BL M"},
						{"", "", "", "BL R", "BR R", "R"},
						{"", "", "", "", "BL", "BR"},
						{"", "", "", "", "", "R BR R BL R"}
				};
			for(int aa = 0; aa < 5; aa++)
			{
				int cur = -1;
				for(int bb = aa; bb < 6; bb++)
				{
					if(numbers.get(aa) == symbolNumber.get(bb))
					{
						cur = bb;
						break;
					}
				}
				if(allMoves[aa][cur].length() > 0)
				{
					out = out + "\n" + allMoves[aa][cur];
					String[] moves = allMoves[aa][cur].split(" ");
					for(int bb = 0; bb < moves.length; bb++)
						symbolNumber = doMove(symbolNumber, moves[bb]);
				}
					
			}
			JFrame f = new JFrame();
			f.setLayout(new GridLayout(2, 3));
			ImageIcon[] i = new ImageIcon[6];
			JLabel[] l = new JLabel[6];
			for(int aa = 0; aa < 6; aa++)
			{
				i[aa] = new ImageIcon("img/Keypad" + numbers.get(aa) + ".jpg");
				Image image = i[aa].getImage();
				image = image.getScaledInstance((int)(i[aa].getIconWidth() / r), (int)(i[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
				i[aa] = new ImageIcon(image);
				l[aa] = new JLabel();
				l[aa].setIcon(i[aa]);
				f.add(l[aa]);
			}
			f.pack();
			f.setVisible(true);
			JOptionPane.showMessageDialog(null, out);
			f.setVisible(false);
		}
	}
	private JButton getButton(final JOptionPane optionPane, int value, ImageIcon icon ) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	    	  optionPane.setValue(button);
	        symbolNumber.add(value);
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
	private ArrayList<Integer> doMove(ArrayList<Integer> SL, String m)
	{
		ArrayList<Integer> conv = new ArrayList<Integer>();
		switch(m)
		{
			case "TL":
				conv.add(SL.get(1));
				conv.add(SL.get(2));
				conv.add(SL.get(0));
				conv.add(SL.get(3));
				conv.add(SL.get(4));
				conv.add(SL.get(5));
				break;
			case "TR":
				conv.add(SL.get(2));
				conv.add(SL.get(0));
				conv.add(SL.get(1));
				conv.add(SL.get(3));
				conv.add(SL.get(4));
				conv.add(SL.get(5));
				break;
			case "BL":
				conv.add(SL.get(0));
				conv.add(SL.get(1));
				conv.add(SL.get(2));
				conv.add(SL.get(4));
				conv.add(SL.get(5));
				conv.add(SL.get(3));
				break;
			case "BR":
				conv.add(SL.get(0));
				conv.add(SL.get(1));
				conv.add(SL.get(2));
				conv.add(SL.get(5));
				conv.add(SL.get(3));
				conv.add(SL.get(4));
				break;
			case "L":
				conv.add(SL.get(3));
				conv.add(SL.get(1));
				conv.add(SL.get(2));
				conv.add(SL.get(0));
				conv.add(SL.get(4));
				conv.add(SL.get(5));
				break;
			case "M":
				conv.add(SL.get(0));
				conv.add(SL.get(4));
				conv.add(SL.get(2));
				conv.add(SL.get(3));
				conv.add(SL.get(1));
				conv.add(SL.get(5));
				break;
			case "R":
				conv.add(SL.get(0));
				conv.add(SL.get(1));
				conv.add(SL.get(5));
				conv.add(SL.get(3));
				conv.add(SL.get(4));
				conv.add(SL.get(2));
				break;
				
		}
		if(conv.size() == 0)
			System.out.println(m);
		return conv;
	}
}
