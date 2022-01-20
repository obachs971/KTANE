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

public class RoundKeypad 
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
	private final Double r;
	public RoundKeypad(Double resize)
	{
		r = resize;
	}
	public void run()
	{
		int[] scores = {0, 0, 0, 0, 0, 0};
		symbolNumber = new ArrayList<Integer>();
		ArrayList<Integer> position = new ArrayList<Integer>();
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
		for(int aa = 0; aa < 8; aa++)
		{
			position.add(aa + 1);
			JDialog dialog = optionPane.createDialog(frame, "");
			dialog.setTitle("Select symbol #" + (aa + 1) + ":");
			dialog.setVisible(true);
			for(int bb = 0; bb < slist.length; bb++)
			{
				for(int cc = 0; cc < slist[bb].length; cc++)
				{
					if(symbolNumber.get(aa) == slist[bb][cc])
					{
						scores[bb]++;
						break;
					}
				}
			}
			if(aa < 7)
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
		int best = scores[0];
		int cursor = 0;
		for(int aa = 1; aa < scores.length; aa++)
		{
			if(best <= scores[aa])
			{
				best = scores[aa];
				cursor = aa;
			}
		}
		for(int aa = 0; aa < slist[cursor].length; aa++)
		{
			if(symbolNumber.contains(slist[cursor][aa]))
			{
				int pos = symbolNumber.indexOf(slist[cursor][aa]);
				symbolNumber.remove(pos);
				position.remove(pos);
			}
				
		}
		ImageIcon[] solution = new ImageIcon[symbolNumber.size()];
		JLabel[] solutionLabel = new JLabel[symbolNumber.size()];
		JFrame solutionFrame = new JFrame();
		solutionFrame.setLayout(new GridLayout(symbolNumber.size(), 1));
		for(int aa = 0; aa < symbolNumber.size(); aa++)
		{
			solution[aa] = new ImageIcon("img/Keypad" + symbolNumber.get(aa) + ".jpg");
			Image image = solution[aa].getImage();
			image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			solution[aa] = new ImageIcon(image);
			solutionLabel[aa] = new JLabel();
			solutionLabel[aa].setIcon(solution[aa]);
			solutionFrame.add(solutionLabel[aa]);
		}
		solutionFrame.pack();
		solutionFrame.setVisible(true);
		JOptionPane.showMessageDialog(null, "Press these symbols: " + position.toString().replace("[", "").replace("]", "").replace(",", ""));
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
}
