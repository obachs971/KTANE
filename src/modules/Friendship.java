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
import javax.swing.JOptionPane;

public class Friendship 
{
	private final String[][] table =
		{
				{"Consideration", "Compassion", "Loyalty", "Courage", "Open-mindedness", "Sympathy", "Fairness", "Authenticity", "Conscientiousness", "Solidarity", "Patience", "Helpfulness", "Flexibility", "Inspiration"},
				{"Support", "Kindness", "Sympathy", "Loyalty", "Generosity", "Consideration", "Thoughtfulness", "Selflessness", "Charitableness", "Helpfulness", "Honesty", "Authenticity", "Inspiration", "Solidarity"},
				{"Honesty", "Inspiration", "Conscientiousness", "Solidarity", "Charitableness", "Support", "Consideration", "Caring", "Sympathy", "Laughter", "Generosity", "Thoughtfulness", "Altruism", "Resoluteness"},
				{"Benevolence", "Sincerity", "Kindness", "Conscientiousness", "Loyalty", "Caring", "Laughter", "Helpfulness", "Open-mindedness", "Consideration", "Fairness", "Altruism", "Solidarity", "Support"},
				{"Altruism", "Charitableness", "Sincerity", "Laughter", "Flexibility", "Helpfulness", "Inspiration", "Patience", "Kindness", "Resoluteness", "Loyalty", "Generosity", "Compassion", "Amicability"},
				{"Open-mindedness", "Courage", "Compassion", "Helpfulness", "Honesty", "Benevolence", "Loyalty", "Fairness", "Sincerity", "Conscientiousness", "Flexibility", "Inspiration", "Caring", "Authenticity"},
				{"Solidarity", "Thoughtfulness", "Laughter", "Charitableness", "Amicability", "Resoluteness", "Benevolence", "Loyalty", "Selflessness", "Sincerity", "Inspiration", "Fairness", "Conscientiousness", "Flexibility"},
				{"Compassion", "Solidarity", "Thoughtfulness", "Consideration", "Sympathy", "Sincerity", "Resoluteness", "Courage", "Altruism", "Selflessness", "Kindness", "Patience", "Support", "Benevolence"},
				{"Courage", "Laughter", "Charitableness", "Amicability", "Consideration", "Honesty", "Sincerity", "Kindness", "Caring", "Authenticity", "Helpfulness", "Loyalty", "Patience", "Fairness"},
				{"Kindness", "Flexibility", "Altruism", "Authenticity", "Support", "Conscientiousness", "Caring", "Amicability", "Compassion", "Charitableness", "Open-mindedness", "Resoluteness", "Fairness", "Generosity"},
				{"Sympathy", "Support", "Open-mindedness", "Fairness", "Thoughtfulness", "Inspiration", "Courage", "Benevolence", "Laughter", "Honesty", "Amicability", "Selflessness", "Resoluteness", "Altruism"},
				{"Patience", "Sympathy", "Solidarity", "Honesty", "Compassion", "Selflessness", "Open-mindedness", "Laughter", "Support", "Generosity", "Sincerity", "Amicability", "Authenticity", "Helpfulness"},
				{"Flexibility", "Altruism", "Patience", "Thoughtfulness", "Conscientiousness", "Courage", "Selflessness", "Consideration", "Generosity", "Benevolence", "Resoluteness", "Caring", "Sympathy", "Charitableness"},
				{"Selflessness", "Generosity", "Amicability", "Compassion", "Patience", "Kindness", "Flexibility", "Honesty", "Courage", "Thoughtfulness", "Authenticity", "Open-mindedness", "Benevolence", "Caring"}
		};
	private String buttonInput;
	private ImageIcon imgInput;
	private final double r;
	public Friendship(double resizer)
	{
		r = resizer;
	}
	
	public void run()
	{
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		ImageIcon[] icon = new ImageIcon[56], imgFriends = new ImageIcon[6];
		JButton[] jButton = new JButton[56];
		optionPane.setLayout(new GridLayout(8, 7));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		String[] friendList = {
				"C0", "C1", "C2", "C3", "C4", "C5", "C6", 
				"C7", "C8", "C9", "C10", "C11", "C12", "C13", 
				"R0", "R1", "R2", "R3", "R4", "R5", "R6", 
				"R7", "R8", "R9", "R10", "R11", "R12", "R13", 
				"C13", "C12", "C11", "C10", "C9", "C8", "C7", 
				"C6", "C5", "C4", "C3", "C2", "C1", "C0", 
				"R13", "R12", "R11", "R10", "R9", "R8", "R7", 
				"R6", "R5", "R4", "R3", "R2", "R1", "R0"
		}, friends = new String[6];
		for(int aa = 0; aa < 56; aa++)
		{
			icon[aa] = new ImageIcon("img/Friendship" + (aa + 1) + ".jpg");
			Image image = icon[aa].getImage();
			image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			icon[aa] = new ImageIcon(image);
			jButton[aa] = getButton(optionPane, friendList[aa], icon[aa]);
			optionPane.add(jButton[aa]);
		}
		int numCol = 0, numRow = 0;
		for(int aa = 0; aa < 6; aa++)
		{
			JDialog dialog = optionPane.createDialog(frame, "");
			dialog.setTitle("Select friend #" + (aa + 1) + ":");
			dialog.setVisible(true);
			boolean v = valid(numCol, numRow);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				dialog.setVisible(true);
				v = valid(numCol, numRow);
			}
			friends[aa] = buttonInput.toUpperCase();
			imgFriends[aa] = imgInput;
			if(friends[aa].charAt(0) == 'C')
				numCol++;
			else
				numRow++;
			if(aa < 5)
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
		ArrayList<Integer> colrow = new ArrayList<Integer>();
		for(int aa = 0; aa < 2; aa++)
		{
			ArrayList<ImageIcon> temp = new ArrayList<ImageIcon>();
			ArrayList<String> temp2 = new ArrayList<String>();
			for(int bb = 0; bb < 6; bb++)
			{
				if(friends[bb].charAt(0) == "CR".charAt(aa))
				{
					temp.add(imgFriends[bb]);
					temp2.add(friends[bb]);
				}
			}
			JButton[] buttons = new JButton[temp.size()];
			JOptionPane pane = new JOptionPane();
			pane.setLayout(new GridLayout(1, 3));
			pane.setOptions(new Object[] {});
			pane.removeAll();
			for(int bb = 0; bb < buttons.length; bb++)
			{
				Image image = temp.get(bb).getImage();
				image = image.getScaledInstance((int)(temp.get(bb).getIconWidth() / r), (int)(temp.get(bb).getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
				temp.set(bb, new ImageIcon(image));
				buttons[bb] = getButton(pane, temp2.get(bb), temp.get(bb));
				pane.add(buttons[bb]);
			}
			JDialog jdialog = pane.createDialog(frame, "");
			if(aa == 0)
				jdialog.setTitle("Select the not tied left most friend:");
			else
				jdialog.setTitle("Select the not tied top most friend:");
			jdialog.setVisible(true);
			for(int bb = 0; bb < temp.size(); bb++)
			{
				if(!(temp2.get(bb).equals(buttonInput)))
					colrow.add(Integer.parseInt(temp2.get(bb).substring(1)));
			}
		}
		System.out.println(colrow.toString());
		JOptionPane.showMessageDialog(null, "Submit one of these words:\n" + table[colrow.get(2)][colrow.get(0)] + "\n" + table[colrow.get(2)][colrow.get(1)] + "\n" + table[colrow.get(3)][colrow.get(0)] + "\n" + table[colrow.get(3)][colrow.get(1)]);
	}
	private JButton getButton(final JOptionPane optionPane, String name, ImageIcon icon ) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	    	optionPane.setValue(button);
	        buttonInput = name.toUpperCase();
	        imgInput = icon;
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
	private boolean valid(int nc, int nr)
	{
		if(nc == 3 && buttonInput.charAt(0) == 'C')
			return false;
		else if(nr == 3 && buttonInput.charAt(0) == 'R')
			return false;
		else
			return true;
	}
}
