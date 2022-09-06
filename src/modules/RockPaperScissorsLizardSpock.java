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

import start.BombEdgework;

public class RockPaperScissorsLizardSpock 
{
	private final double r;
	private final BombEdgework ew;
	public RockPaperScissorsLizardSpock(double resizer, BombEdgework e)
	{
		r = resizer;
		ew = e;
	}
	public void run()
	{
		String[] names = {"Rock", "Paper", "Scissors", "Lizard", "Spock", "None"};
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		ImageIcon[] icon = new ImageIcon[names.length];
		JButton[] jButton = new JButton[names.length];
		optionPane.setLayout(new GridLayout(3, 2));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int aa = 0; aa < names.length; aa++)
		{
			icon[aa] = new ImageIcon("img/RPS" + names[aa] + ".png");
			Image image = icon[aa].getImage();
			image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			icon[aa] = new ImageIcon(image);
			jButton[aa] = getButton(optionPane, names[aa], icon[aa]);
			optionPane.add(jButton[aa]);
		}
		JDialog dialog = optionPane.createDialog(frame, "");
		dialog.setTitle("Select the middle icon:");
		dialog.setVisible(true);
		int decoy = getDecoy(optionPane.getValue().toString().toUpperCase());
		int sign = row1(decoy);
		String out;
		if(sign == decoy)
		{
			if(decoy == 0)
				icon = new ImageIcon[5];
			else
				icon = new ImageIcon[4];
			int counter = 0;
			sign--;
			out = "";
			String[] signs = {"Rock", "Paper", "Scissors", "Lizard", "Spock"};
			for(int aa = 0; aa < signs.length; aa++)
			{
				if(aa != sign)
				{
					icon[counter] = new ImageIcon("img/RPS" + signs[aa] + ".png");
					Image image = icon[counter].getImage();
					image = image.getScaledInstance((int)(icon[counter].getIconWidth() / r), (int)(icon[counter].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
					icon[counter++] = new ImageIcon(image);
					out = out + "" + signs[aa] + "\n";
				}
					
			};
		}
		else
		{
			switch(sign)
			{
				case 1:
					out = "Paper\nSpock";
					break;
				case 2:
					out = "Scissors\nLizard";
					break;
				case 3:
					out = "Spock\nRock";
					break;
				case 4:
					out = "Rock\nScissors";
					break;
				default:
					out = "Lizard\nPaper";
					break;
			}
			String[] spl = out.split("\n");
			icon = new ImageIcon[2];
			for(int aa = 0; aa < 2; aa++)
			{
				icon[aa] = new ImageIcon("img/RPS" + spl[aa] + ".png");
				Image image = icon[aa].getImage();
				image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
				icon[aa] = new ImageIcon(image);
			}
		}
		JLabel[] label = new JLabel[icon.length];
		JFrame outFrame = new JFrame();
		outFrame.setLayout(new GridLayout(icon.length, 1));
		for(int aa = 0; aa < icon.length; aa++)
		{
			label[aa] = new JLabel();
			label[aa].setIcon(icon[aa]);
			outFrame.add(label[aa]);
		}
		outFrame.pack();
		outFrame.setVisible(true);
		JOptionPane.showMessageDialog(null, "Press these signs:\n" + out);
		outFrame.setVisible(false);
	}
	private int getDecoy(String i)
	{
		String[] list = {"NONE", "ROCK", "PAPER", "SCISSORS", "LIZARD", "SPOCK"};
		for(int aa = 0; aa < list.length; aa++)
		{
			if(i.equals(list[aa]))
				return aa;
		}
		return -1;
	}
	private int row1(int decoy)
	{
		if(ew.numCharsInSN("XY") > 0)
			return row2(decoy);
		int[] scores = {0, ew.numCharsInSN("RO"), ew.numCharsInSN("PA"), ew.numCharsInSN("SI"), ew.numCharsInSN("LZ"), ew.numCharsInSN("CK")};
		int best = getBest(scores, decoy);
		if(best == decoy)
			return row2(decoy);
		else
			return best;
	}
	private int row2(int decoy)
	{
		if(ew.findPort("PS/2") > 0)
			return row3(decoy);
		
		int[] scores = {0, ew.findPort("RJ-45"), ew.findPort("PARALLEL"), ew.findPort("SERIAL"), ew.findPort("DVI-D"), ew.findPort("RCA")};
		int best = getBest(scores, decoy);
		if(best == decoy)
			return row3(decoy);
		else
			return best;
	}
	private int row3(int decoy)
	{
		if(ew.findLit("TRN"))
			return row5(decoy);
		
		int[] scores = {0, 0, 0, 0, 0, 0};
		if(ew.findLit("FRK"))
			scores[1]++;
		if(ew.findLit("FRQ"))
			scores[1]++;
		if(ew.findLit("BOB"))
			scores[2]++;
		if(ew.findLit("IND"))
			scores[2]++;
		if(ew.findLit("CAR"))
			scores[3]++;
		if(ew.findLit("SIG"))
			scores[3]++;
		if(ew.findLit("CLR"))
			scores[4]++;
		if(ew.findLit("NSA"))
			scores[4]++;
		if(ew.findLit("SND"))
			scores[5]++;
		if(ew.findLit("MSA"))
			scores[5]++;
		int best = getBest(scores, decoy);
		if(best == decoy)
			return row4(decoy);
		else
			return best;
	}
	private int row4(int decoy)
	{
		if(ew.findUnlit("TRN"))
			return row5(decoy);
		
		int[] scores = {0, 0, 0, 0, 0, 0};
		if(ew.findUnlit("FRK"))
			scores[1]++;
		if(ew.findUnlit("FRQ"))
			scores[1]++;
		if(ew.findUnlit("BOB"))
			scores[2]++;
		if(ew.findUnlit("IND"))
			scores[2]++;
		if(ew.findUnlit("CAR"))
			scores[3]++;
		if(ew.findUnlit("SIG"))
			scores[3]++;
		if(ew.findUnlit("CLR"))
			scores[4]++;
		if(ew.findUnlit("NSA"))
			scores[4]++;
		if(ew.findUnlit("SND"))
			scores[5]++;
		if(ew.findUnlit("MSA"))
			scores[5]++;
		int best = getBest(scores, decoy);
		if(best == decoy)
			return row5(decoy);
		else
			return best;
	}
	private int row5(int decoy)
	{
		int[] scores = {0, ew.numCharsInSN("05"), ew.numCharsInSN("36"), ew.numCharsInSN("19"), ew.numCharsInSN("28"), ew.numCharsInSN("47")};
		return getBest(scores, decoy);
	}
	private int getBest(int[] scores, int decoy)
	{
		int best = scores[0], numBest = 1, bestCur = 0;
		for(int aa = 1; aa < scores.length; aa++)
		{
			//System.out.print(scores[aa] + " ");
			if(scores[aa] > best)
			{
				best = scores[aa];
				numBest = 1;
				bestCur = aa;
			}
			else if(best == scores[aa])
				numBest++;
		}
		//System.out.println(numBest);
		if(numBest > 1)
			return decoy;
		else
			return bestCur;	
	}
	private JButton getButton(final JOptionPane optionPane, String text, ImageIcon icon ) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    button.setText(text);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(text);
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
}
