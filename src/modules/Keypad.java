package modules;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Keypad 
{
	private int[][] slist = 
		{
				{1, 2, 3, 4, 5, 6, 7},
				{8, 1, 7, 9, 10, 6, 11},
				{12, 13, 9, 14, 15, 3, 10},
				{16, 17, 18, 5, 14, 11, 19},
				{20, 19, 18, 21, 17, 22, 23},
				{16, 8, 24, 25, 20, 26, 27}
		};
	private ArrayList<Integer> symbolNumber;
	private int[] scores;
	private double r;
	public Keypad(double resize)
	{
		r = resize;
		symbolNumber = new ArrayList<Integer>();
		scores = new int[6];
		for(int aa = 0; aa < scores.length; aa++)
			scores[aa] = 0;
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
		for(int aa = 0; aa < 4; aa++)
		{
			JDialog dialog = optionPane.createDialog(frame, "");
			dialog.setTitle("Select symbol #" + (aa + 1) + ":");
			dialog.setVisible(true);
			boolean v = valid(aa);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				dialog.setVisible(true);
				v = valid(aa);
			}
			for(int bb = 0; bb < slist.length; bb++)
			{
				if(Arrays.stream(slist[bb]).anyMatch(i -> i == symbolNumber.get(symbolNumber.size() - 1)))
					scores[bb]++;
			}
			if(aa < 3)
			{
				optionPane.removeAll();
				JButton[] temp = new JButton[jButton.length - 1];
				int cursor = 0;
				for(int bb = 0; bb < jButton.length; bb++)
				{
					if(!(jButton[bb].equals(optionPane.getValue())))
					{
						temp[cursor] = jButton[bb];
						cursor++;
					}
				}
				jButton = temp;
				for(int bb = 0; bb < jButton.length; bb++)
					optionPane.add(jButton[bb]);
			}
		}
		int cursor = -1;
		for(int aa = 0; aa < scores.length; aa++)
		{
			if(scores[aa] == 4)
			{
				cursor = aa;
				break;
			}
		}
		int items = 0;
		ImageIcon[] solution = new ImageIcon[4];
		JLabel[] solutionLabel = new JLabel[4];
		JFrame solutionFrame = new JFrame();
		solutionFrame.setLayout(new GridLayout(4,1));
		for(int aa = 0; aa < slist[cursor].length; aa++)
		{
			if(symbolNumber.contains(slist[cursor][aa]))
			{
				solution[items] = new ImageIcon("img/Keypad" + slist[cursor][aa] + ".jpg");
				Image image = solution[items].getImage();
				image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
				solution[items] = new ImageIcon(image);
				solutionLabel[items] = new JLabel();
				solutionLabel[items].setIcon(solution[items]);
				solutionFrame.add(solutionLabel[items]);
				items++;
			}
		}
		solutionFrame.pack();
		solutionFrame.setVisible(true);
		JOptionPane.showMessageDialog(null, "Press the symbols in this order");
		solutionFrame.setVisible(false);
		
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
	private boolean valid(int aa)
	{
		if(symbolNumber.size() == aa + 1)
		{
			int[] temp = {scores[0] + 0, scores[1] + 0, scores[2] + 0, scores[3] + 0, scores[4] + 0, scores[5] + 0};
			for(int bb = 0; bb < slist.length; bb++)
			{
				for(int cc = 0; cc < slist[bb].length; cc++)
				{
					if(slist[bb][cc] == symbolNumber.get(aa))
					{
						temp[bb]++;
						break;
					}
				}
			}
			if(Arrays.stream(temp).anyMatch(i -> i == (aa + 1)))
				return true;
			else
				symbolNumber.remove(symbolNumber.get(symbolNumber.size() - 1));
		}
		return false;
	}
}
