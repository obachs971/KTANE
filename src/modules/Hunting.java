package modules;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Hunting 
{
	private final String[][] table =
		{
				{"FDAL", "NFPK", "HIKD", "KJBE"},
				{"ILFG", "LNHI", "DOIM", "EBNC"},
				{"AHGF", "PCMJ", "BMLH", "OGCA"},
				{"GAEO", "MEJN", "JKDP", "CPOB"}
		};
	private int symbolNumber;
	private final double r;
	public Hunting(double resizer) 
	{
		r = resizer;
	}
	public String run()
	{
		int[][] RC = new int[4][2];
		String souv = "";
		for(int aa = 0; aa < 4; aa++)
		{
			int[] cur = new int[2];
			JFrame inputFrame = new JFrame();
			JOptionPane optionPane = new JOptionPane();
			ImageIcon[] inputIcon = new ImageIcon[8];
			JButton[] jButton = new JButton[8];
			optionPane.setLayout(new GridLayout(4, 2));
			optionPane.setOptions(new Object[] {});
			optionPane.removeAll();
			for(int bb = 0; bb < 8; bb++)
			{
				inputIcon[bb] = new ImageIcon("img/Hunting" + bb + ".png");
				Image image = inputIcon[bb].getImage();
				image = image.getScaledInstance((int)(inputIcon[bb].getIconWidth() / r), (int)(inputIcon[bb].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
				inputIcon[bb] = new ImageIcon(image);
				jButton[bb] = getButton(optionPane, bb, inputIcon[bb]);
				optionPane.add(jButton[bb]);
			}
			JDialog dialog = optionPane.createDialog(inputFrame, "");
			dialog.setTitle("Select the left symbol:");
			dialog.setVisible(true);
			cur[0] = symbolNumber + 0;
			String list = "0123";
			if(cur[0] < 4)
				list = "4567";
			optionPane.removeAll();
			optionPane.setLayout(new GridLayout(2, 2));
			inputIcon = new ImageIcon[list.length()];
			jButton = new JButton[list.length()];
			for(int bb = 0; bb < list.length(); bb++)
			{
				inputIcon[bb] = new ImageIcon("img/Hunting" + list.charAt(bb) + ".png");
				Image image = inputIcon[bb].getImage();
				image = image.getScaledInstance((int)(inputIcon[bb].getIconWidth() / r), (int)(inputIcon[bb].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
				inputIcon[bb] = new ImageIcon(image);
				jButton[bb] = getButton(optionPane, "01234567".indexOf(list.charAt(bb)), inputIcon[bb]);
				optionPane.add(jButton[bb]);
			}
			dialog = optionPane.createDialog(inputFrame, "");
			dialog.setTitle("Select the right symbol:");
			dialog.setVisible(true);
			cur[1] = symbolNumber + 0;
			int pos = aa + 0;
			if(cur[0] < cur[1])
			{
				int temp = cur[0] + 0;
				cur[0] = cur[1] + 0;
				cur[1] = temp + 0;
				pos = 3 - aa;
				souv = souv + "STAGE " + (aa + 1) + ": ROW/COL\n";
			}
			else
				souv = souv + "STAGE " + (aa + 1) + ": COL/ROW\n";
			RC[aa][0] = cur[1] + 0;
			RC[aa][1] = cur[0] % 4;
			JFrame outputFrame = new JFrame();
			ImageIcon[] outputIcon = new ImageIcon[aa + 1];
			JLabel[] outputLabel = new JLabel[aa + 1];
			outputFrame.setLayout(new GridLayout(2, 2));
			for(int bb = 0; bb <= aa; bb++)
			{
				outputIcon[bb] = new ImageIcon("img/Hunting" + table[RC[bb][0]][RC[bb][1]].charAt(pos) + ".png");
				Image image = outputIcon[bb].getImage();
				image = image.getScaledInstance((int)(outputIcon[bb].getIconWidth() / r), (int)(outputIcon[bb].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
				outputIcon[bb] = new ImageIcon(image);
				outputLabel[bb] = new JLabel();
				outputLabel[bb].setIcon(outputIcon[bb]);
				outputFrame.add(outputLabel[bb]);
			}
			outputFrame.pack();
			outputFrame.setVisible(true);
			JOptionPane.showMessageDialog(null, "Press any button(s) that are\nNOT any of these symbols");
			outputFrame.setVisible(false);
		}
		return souv;
	}
	private JButton getButton(final JOptionPane optionPane, int value, ImageIcon icon ) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(button);
	        symbolNumber = value + 0;
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
}
