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

import start.BombEdgework;

public class Creation 
{
	private final int[][] table1 =
		{
				{2,	1, 4, 3},
				{1,	2, 3, 4},
				{4,	3, 1, 2},
				{3, 4, 2, 1}
		};
	private final String[][] worm = 
		{
				{"LIFE", "SWAMP"},
				{"BACTERIA", "SWAMP"}
		};
	private final String[][] plankton = 
		{
				{"LIFE", "SWAMP"},
				{"BACTERIA", "WATER"}
		};
	private final String[][] ghost = 
		{
				{"ENERGY", "FIRE"},
				{"LIFE", "PLASMA"}
		};
	private final String[][] mushroom = 
		{
				{"LIFE", "WATER"},
				{"WEEDS", "EARTH"}
		};
	private final String[][] bird = 
		{
				{"LIFE", "EARTH"},
				{"EGG", "AIR"}
		};
	private final String[][] dino = 
		{
				{"LIFE", "EARTH"},
				{"EGG", "EARTH"}
		};
	private final String[][] turtle = 
		{
				{"LIFE", "EARTH"},
				{"EGG", "WATER"}
		};
	private final String[][] lizard = 
		{
				{"LIFE", "EARTH"},
				{"EGG", "SWAMP"}
		};
	private final String[][] seeds = 
		{
				{"LIFE", "EARTH"},
				{"LIFE", "WATER"},
				{"EGG", "WEEDS"}
		};
	private final BombEdgework ew;
	private final double r;
	public Creation(BombEdgework e, double resizer)
	{
		ew = e;
		r = resizer;
	}
	public String run()
	{
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		ImageIcon[] icon = new ImageIcon[5];
		JButton[] jButton = new JButton[5];
		optionPane.setLayout(new GridLayout(5, 1));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int aa = 0; aa < 5; aa++)
		{
			icon[aa] = new ImageIcon("img/Creation" + aa + ".jpg");
			Image image = icon[aa].getImage();
			image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			icon[aa] = new ImageIcon(image);
			jButton[aa] = getButton(optionPane, (aa) + "", icon[aa]);
			optionPane.add(jButton[aa]);
		}
		JDialog dialog = optionPane.createDialog(frame, "");
		dialog.setTitle("Select the weather for day 1:");
		dialog.setVisible(true);
		int weather = Integer.parseInt(optionPane.getValue().toString());
		int permutation = weather + 0;
		String[] elementName = {"WATER", "AIR", "EARTH", "FIRE"}, weatherName = {"CLEAR", "RAIN", "WIND", "METEOR SHOWER", "HEAT WAVE"};
		String souv = "DAY 1: " + weatherName[weather];
		if(permutation > 0)
		{
			JFrame framePos = new JFrame();
			JOptionPane optionPanePos = new JOptionPane();
			JButton[] jButtonPos = new JButton[4];
			optionPanePos.setLayout(new GridLayout(2, 2));
			optionPanePos.setOptions(new Object[] {});
			optionPanePos.removeAll();
			String[] positions = {"Top-Left", "Top-Right", "Bottom-Left", "Bottom-Right"};
			for(int i = 0; i < 4; i++)
			{
				jButtonPos[i] = getButton(optionPanePos, positions[i]);
				optionPanePos.add(jButtonPos[i]);
			}
			JDialog dialogPos = optionPanePos.createDialog(framePos, "");
			dialogPos.setTitle("Select the position of the " + elementName[weather - 1] + " element");
			dialogPos.setVisible(true);
			permutation = table1[permutation - 1][getCol(optionPanePos.getValue().toString())];
		}
		String[][][] order;
		if(ew.BH() >= 3)
		{
			if(ew.numLit() > 0 && ew.BD() == 0)
				order = new String[][][] {bird, dino, turtle, lizard, worm};
			else if(ew.numLit() > 0)
				order = new String[][][] {dino, turtle, lizard, worm, bird};
			else if(ew.numUnlit() > 0 && ew.BA() == 0)
				order = new String[][][]{turtle, lizard, worm, bird, dino};
			else if(ew.numUnlit() > 0)
				order = new String[][][] {lizard, worm, bird, dino, turtle};
			else
				order = new String[][][] {worm, bird, dino, turtle, lizard};
		}
		else
		{
			if(ew.numPlates() > ew.BH())
				order = new String[][][] {ghost, plankton, seeds, mushroom};
			else if(ew.numDupPorts() > 0)
				order = new String[][][] {plankton, seeds, mushroom, ghost};
			else if(ew.numUnlit() > ew.numLit())
				order = new String[][][] {seeds, mushroom, ghost, plankton};
			else
				order = new String[][][] {mushroom, ghost, plankton, seeds};
		}
		ArrayList<String[]> combos = new ArrayList<String[]>();
		combos.add(new String[] {"FIRE", "AIR"});
		combos.add(new String[] {"EARTH", "WATER"});
		combos.add(new String[] {"ENERGY", "SWAMP"});
		permutation = permutation % order.length;
		for(int aa = 0; aa < order[permutation].length; aa++)
			combos.add(order[permutation][aa]);
		JOptionPane.showMessageDialog(null, "Combine " + replace(combos.get(0), weather));
		for(int aa = 1; aa < combos.size(); aa++)
		{
			dialog = optionPane.createDialog(frame, "");
			dialog.setTitle("Select the weather for day " + (aa + 1) + ":");
			dialog.setVisible(true);
			weather = Integer.parseInt(optionPane.getValue().toString());
			JOptionPane.showMessageDialog(null, "Combine " + replace(combos.get(aa), weather));
			souv = souv + "\nDAY " + (aa + 1) + ": " + weatherName[weather];
		}
		return souv;
	}
	private String replace(String[] elements, int weather)
	{
		for(int aa = 0; aa < 2; aa++)
		{
			switch(weather)
			{
				case 1:
					elements[aa] = elements[aa].replace("WATER", "FIRE");
					break;
				case 2:
					elements[aa] = elements[aa].replace("AIR", "EARTH");
					break;
				case 3:
					elements[aa] = elements[aa].replace("EARTH", "AIR");
					break;
				case 4:
					elements[aa] = elements[aa].replace("FIRE", "WATER");
					break;
			}
		}
		return (elements[0] + " and " + elements[1]);
	}
	private int getCol(String i)
	{
		switch(i)
		{
			case "Top-Left":
				return 0;
			case "Top-Right":
				return 1;
			case "Bottom-Left":
				return 2;
			case "Bottom-Right":
				return 3;
		}
		return -1;
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
	private JButton getButton(final JOptionPane optionPane, String value ) {
	    final JButton button = new JButton();
	    button.setText(value);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(value);
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
}
