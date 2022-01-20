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

public class SonicTheHedgehog 
{
	private final String[][] tableA =
		{
				{"In","RBt","EL","Rg"},
				{"RBt","In","EL","Rg"},
				{"EL","Rg","RBt","In"},
				{"In","EL","Rg","RBt"},
				{"Rg","RBt","In","EL"}
		};
	private final String[][] tableB =
		{
				{"Rg","RBt","EL","RBt","In","EL","Rg","In"},
				{"In","EL","RBt","Rg","Rg","RBt","EL","In"},
				{"In","Rg","In","EL","RBt","Rg","RBt","EL"},
				{"RBt","EL","Rg","In","EL","Rg","In","RBt"},
				{"EL","Rg","RBt","RBt","EL","In","In","Rg"}
		};
	private final String[][] tableC =
		{
				{"EL","Rg","RBt","RBt","In","Rg","In","EL","EL","RBt","In","Rg"},
				{"RBt","EL","In","RBt","Rg","In","EL","In","RBt","EL","In","RBt"},
				{"Rg","RBt","EL","In","RBt","EL","RBt","Rg","EL","In","RBt","In"},
				{"In","EL","Rg","EL","RBt","In","Rg","RBt","In","Rg","EL","Rg"},
				{"RBt","In","RBt","Rg","EL","RBt","Rg","EL","Rg","In","Rg","EL"}
		};
	private final double r;
	public SonicTheHedgehog(double resizer)
	{
		r = resizer;
	}
	public String run()
	{
		String souv = "";
		ArrayList<String> sounds = new ArrayList<String>();
		String[] soundList = {"Boss", "Breathe", "Bumper", "Continue", "Drown", "Emerald", "Extra Life", "Final Zone", "Invincibility", "Jump", "Lamppost", "Marble Zone", "Skid", "Spikes", "Spin", "Spring"};
		JOptionPane pane = getPane("SonicTheHedgehog", soundList, 8, 2, false);
		String[] buttons = {"RBt", "In", "EL", "Rg"};
		for(int aa = 0; aa < 4; aa++)
		{
			JDialog dialog = pane.createDialog(new JFrame(), "");
			dialog.setTitle("Select the sound played by the " + buttons[aa] + " button:");
			dialog.setVisible(true);
			sounds.add((String)(pane.getValue()));
			souv = souv + "" + buttons[aa].toUpperCase() + ": " + sounds.get(aa).toUpperCase() + "\n";
		}
		//System.out.println(sounds.toString());
		String[] badnikList = {"Ballhog", "Burrobot", "Buzz Bomber", "Crab Meat", "Moto Bug"};
		pane = getPane("SonicTheHedgehog", badnikList, 5, 1, true);
		JDialog dialog = pane.createDialog(new JFrame(), "");
		dialog.setTitle("Select the Badnik:");
		dialog.setVisible(true);
		String badnik = (String)(pane.getValue()), press;
		if(sounds.contains("BOSS") || sounds.contains("FINAL ZONE") || sounds.contains("MARBLE ZONE"))
			press = tableA[getRow(badnik)][0];
		else if(sounds.indexOf(sounds.get(0)) < sounds.lastIndexOf(sounds.get(0)) || sounds.indexOf(sounds.get(1)) < sounds.lastIndexOf(sounds.get(1)) || sounds.get(2).equals(sounds.get(3)))
			press = tableA[getRow(badnik)][1];
		else if(sounds.get(0).equals("EMERALD") || sounds.get(0).equals("SPIKES"))
			press = tableA[getRow(badnik)][2];
		else
			press = tableA[getRow(badnik)][3];
		JOptionPane.showMessageDialog(null, "Press " + press);
		String[] sonicList = {"Annoyed", "Dead", "Drowned", "Falling", "Standing"};
		pane = getPane("SonicTheHedgehog", sonicList, 5, 1, true);
		dialog = pane.createDialog(new JFrame(), "");
		dialog.setTitle("Select the Sonic:");
		dialog.setVisible(true);
		String sonic = (String)(pane.getValue());
		if(sounds.get(2).equals("EXTRA LIFE") || sounds.get(1).equals("INVINCIBILITY"))
			press = tableB[getRow(sonic)][0];
		else if(sounds.contains("LAMPPOST") || sounds.contains("MARBLE ZONE"))
			press = tableB[getRow(sonic)][1];
		else if(sounds.get(0).equals("SPIN") || sounds.get(3).equals("SPRING"))
			press = tableB[getRow(sonic)][2];
		else if(badnik.equals("MOTO BUG"))
			press = tableB[getRow(sonic)][3];
		else if(sounds.contains("SPIKES"))
			press = tableB[getRow(sonic)][4];
		else if(badnik.equals("CRAB MEAT") || sounds.contains("DROWN"))
			press = tableB[getRow(sonic)][5];
		else if(nextTo(sounds, "EMERALD", "BOSS") || nextTo(sounds, "EMERALD", "SKID"))
			press = tableB[getRow(sonic)][6];
		else
			press = tableB[getRow(sonic)][7];
		JOptionPane.showMessageDialog(null, "Press " + press);
		String[] objectList = {"Blue Lamppost", "Red Lamppost", "Red Spring", "Switch", "Yellow Spring"};
		pane = getPane("SonicTheHedgehog", objectList, 5, 1, true);
		dialog = pane.createDialog(new JFrame(), "");
		dialog.setTitle("Select the Object:");
		dialog.setVisible(true);
		String object = (String)(pane.getValue());
		if(sounds.get(2).equals("INVINCIBILITY") || sounds.get(1).equals("EXTRA LIFE"))
			press = tableC[getRow(object)][0];
		else if(badnik.equals("BUZZ BOMBER") && sonic.equals("ANNOYED"))
			press = tableC[getRow(object)][1];
		else if(sonic.equals("DROWNED") || sounds.contains("EMERALD"))
			press = tableC[getRow(object)][2];
		else if(sounds.contains("SPIKES") && (sounds.contains("BOSS") || sounds.contains("FINAL ZONE") || sounds.contains("MARBLE ZONE")))
			press = tableC[getRow(object)][3];
		else if(badnik.equals("BALLHOG") || sounds.get(3).equals("CONTINUE"))
			press = tableC[getRow(object)][4];
		else if(nextTo(sounds, "SKID", "SPIKES") || nextTo(sounds, "SPIN", "SPRING"))
			press = tableC[getRow(object)][5];
		else if(sonic.equals("FALLING") || sounds.contains("FINAL ZONE"))
			press = tableC[getRow(object)][6];
		else if(nextTo(sounds, "DROWN", "BUMPER") || nextTo(sounds, "DROWN", "JUMP"))
			press = tableC[getRow(object)][7];
		else if(sonic.equals("STANDING") && sounds.contains("LAMPPOST"))
			press = tableC[getRow(object)][8];
		else if(sounds.contains("FINAL ZONE") || sounds.contains("SPRING"))
			press = tableC[getRow(object)][9];
		else if(badnik.equals("BURROBOT") && sonic.equals("DEAD"))
			press = tableC[getRow(object)][10];
		else
			press = tableC[getRow(object)][11];
		JOptionPane.showMessageDialog(null, "Press " + press);
		souv = souv + "BADNIK: " + badnik.toUpperCase() + "\nSONIC: " + sonic.toUpperCase() + "\nOBJECT: " + object.toUpperCase();
		return souv;
	}
	private JOptionPane getPane(String title, String[] list, int row, int col, boolean text)
	{
		JOptionPane optionPane = new JOptionPane();
		ImageIcon[] icon = new ImageIcon[list.length];
		JButton[] jButton = new JButton[list.length];
		optionPane.setLayout(new GridLayout(row, col));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int aa = 0; aa < list.length; aa++)
		{
			icon[aa] = new ImageIcon("img/" + title + "" + list[aa].replace(" ", "") + ".png");
			Image image = icon[aa].getImage();
			image = image.getScaledInstance((int)((icon[aa].getIconWidth() / r) * 0.80), (int)((icon[aa].getIconHeight() / r) * 0.80), java.awt.Image.SCALE_SMOOTH);
			icon[aa] = new ImageIcon(image);
			if(text)
				jButton[aa] = getButton(optionPane, list[aa].toUpperCase(), list[aa], icon[aa]);
			else
				jButton[aa] = getButton(optionPane, list[aa].toUpperCase(), icon[aa]);
			optionPane.add(jButton[aa]);
		}
		return optionPane;
	}
	private JButton getButton(final JOptionPane optionPane, String value, ImageIcon icon ) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(value);
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
	private JButton getButton(final JOptionPane optionPane, String value, String text, ImageIcon icon ) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    button.setText(text);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(value);
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
	private int getRow(String i)
	{
		switch(i)
		{
			case "BALLHOG":case "ANNOYED":case "BLUE LAMPPOST":
				return 0;
			case "BURROBOT":case "DEAD":case "RED LAMPPOST":
				return 1;
			case "BUZZ BOMBER":case "DROWNED":case "RED SPRING":
				return 2;
			case "CRAB MEAT":case "FALLING":case "SWITCH":
				return 3;
			default:
				return 4;
		}
	}
	private boolean nextTo(ArrayList<String> l, String s1, String s2)
	{
		if(l.contains(s1) && l.contains(s2))
		{
			for(int aa = l.indexOf(s1); aa < l.size(); aa++)
			{
				if(aa == 0)
				{
					if(l.get(aa).equals(s1) && l.get(aa + 1).equals(s2))
						return true;
				}
				else if(aa == 3)
				{
					if(l.get(aa).equals(s1) && l.get(aa - 1).equals(s2))
						return true;
				}
				else
				{
					if(l.get(aa).equals(s1) && l.get(aa + 1).equals(s2))
						return true;
					if(l.get(aa).equals(s1) && l.get(aa - 1).equals(s2))
						return true;
				}
			}
		}
		return false;
	}
}
