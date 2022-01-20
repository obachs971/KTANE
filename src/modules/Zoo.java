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

import start.BombEdgework;

public class Zoo 
{
	private final String[][] grid =
		{
				{"Cow", "2TRex", "3Bat", "4Cat"},
				{"Cat", "1Cow", "2Bat", "3Owl", "4Pig"},
				{"TRex", "2Rabbit", "3Ant", "4Bat", "5Cow"},
				{"Pig", "1Cat", "2Owl", "3Goose", "4Spider"},
				{"Bat", "0Cow", "1TRex", "2Ant", "3Rhino", "4Owl", "5Cat"},
				{"Rabbit", "2Horse", "3Fly", "4Ant", "5TRex"},
				{"Spider", "1Pig", "2Goose", "3Dragonfly", "4Bear"},
				{"Owl", "0Cat", "1Bat", "2Rhino", "3Snail", "4Goose", "5Pig"},
				{"Ant", "0TRex", "1Rabbit", "2Fly", "3Tortoise", "4Rhino", "5Bat"},
				{"Horse", "2Flamingo", "3Llama", "4Fly", "5Rabbit"},
				{"Bear", "1Spider", "2Dragonfly", "3Ferret"},
				{"Goose", "0Pig", "1Owl", "2Snail", "3Butterfly", "4Dragonfly", "5Spider"},
				{"Rhino", "0Bat", "1Ant", "2Tortoise", "3Monkey", "4Snail", "5Owl"},
				{"Fly", "0Rabbit", "1Horse", "2Llama", "3SeaHorse", "4Tortoise", "5Ant"},
				{"Flamingo", "3Hyena", "4Llama", "5Horse"},
				{"Dragonfly", "0Spider", "1Goose", "2Butterfly", "3Lion", "4Ferret", "5Bear"},
				{"Snail", "0Owl", "1Rhino", "2Monkey", "3Fox", "4Butterfly", "5Goose"},
				{"Tortoise", "0Ant", "1Fly", "2SeaHorse", "3Wolf", "4Monkey", "5Rhino"},
				{"Llama", "0Horse", "1Flamingo", "2Hyena", "3Camel", "4SeaHorse", "5Fly"},
				{"Ferret", "0Bear", "1Dragonfly", "2Lion", "3Stegosaurus"},
				{"Butterfly", "0Goose", "1Snail", "2Fox", "3Squirrel", "4Lion", "5Dragonfly"},
				{"Monkey", "0Rhino", "1Tortoise", "2Wolf", "3Dolphin", "4Fox", "5Snail"},
				{"SeaHorse", "0Fly", "1Llama", "2Camel", "3Kangaroo", "4Wolf", "5Tortoise"},
				{"Hyena", "0Flamingo", "3Dimetrodon", "4Camel", "5Llama"},
				{"Lion", "0Dragonfly", "1Butterfly", "2Squirrel", "3Pterodactyl", "4Stegosaurus", "5Ferret"},
				{"Fox", "0Snail", "1Monkey", "2Dolphin", "3Giraffe", "4Squirrel", "5Butterfly"},
				{"Wolf", "0Tortoise", "1SeaHorse", "2Kangaroo", "3Eagle", "4Dolphin", "5Monkey"},
				{"Camel", "0Llama", "1Hyena", "2Dimetrodon", "3Lobster", "4Kangaroo", "5SeaHorse"},
				{"Stegosaurus", "0Ferret", "1Lion", "2Pterodactyl", "3Elephant"},
				{"Squirrel", "0Butterfly", "1Fox", "2Giraffe", "3Cobra", "4Pterodactyl", "5Lion"},
				{"Dolphin", "0Monkey", "1Wolf", "2Eagle", "3Koala", "4Giraffe", "5Fox"},
				{"Kangaroo", "0SeaHorse", "1Camel", "2Lobster", "3Porcupine", "4Eagle", "5Wolf"},
				{"Dimetrodon", "0Hyena", "3Dromedary", "4Lobster", "5Camel"},
				{"Pterodactyl", "0Lion", "1Squirrel", "2Cobra", "3Rooster", "4Elephant", "5Stegosaurus"},
				{"Giraffe", "0Fox", "1Dolphin", "2Koala", "3Hippo", "4Cobra", "5Squirrel"},
				{"Eagle", "0Wolf", "1Kangaroo", "2Porcupine", "3Crab", "4Koala", "5Dolphin"},
				{"Lobster", "0Camel", "1Dimetrodon", "2Dromedary", "3Otter", "4Porcupine", "5Kangaroo"},
				{"Elephant", "0Stegosaurus", "1Pterodactyl", "2Rooster", "3Mouse"},
				{"Cobra", "0Squirrel", "1Giraffe", "2Hippo", "3Woodpecker", "4Rooster", "5Pterodactyl"},
				{"Koala", "0Dolphin", "1Eagle", "2Crab", "3Triceratops", "4Hippo", "5Giraffe"},
				{"Porcupine", "0Kangaroo", "1Lobster", "2Otter", "3Frog", "4Crab", "5Eagle"},
				{"Dromedary", "0Dimetrodon", "3Warthog", "4Otter", "5Lobster"},
				{"Rooster", "0Pterodactyl", "1Cobra", "2Woodpecker", "3Seal", "4Mouse", "5Elephant"},
				{"Hippo", "0Giraffe", "1Koala", "2Triceratops", "3Apatosaurus", "4Woodpecker", "5Cobra"},
				{"Crab", "0Eagle", "1Porcupine", "2Frog", "3Duck", "4Triceratops", "5Koala"},
				{"Otter", "0Lobster", "1Dromedary", "2Warthog", "3Swallow", "4Frog", "5Porcupine"},
				{"Mouse", "0Elephant", "1Rooster", "2Seal"},
				{"Woodpecker", "0Cobra", "1Hippo", "2Apatosaurus", "3Skunk", "4Seal", "5Rooster"},
				{"Triceratops", "0Koala", "1Crab", "2Duck", "3Beaver", "4Apatosaurus", "5Hippo"},
				{"Frog", "0Porcupine", "1Otter", "2Swallow", "3Starfish", "4Duck", "5Crab"},
				{"Warthog", "0Dromedary", "4Swallow", "5Otter"},
				{"Seal", "0Rooster", "1Woodpecker", "2Skunk", "5Mouse"},
				{"Apatosaurus", "0Hippo", "1Triceratops", "2Beaver", "3Viper", "4Skunk", "5Woodpecker"},
				{"Duck", "0Crab", "1Frog", "2Starfish", "3Gorilla", "4Beaver", "5Triceratops"},
				{"Swallow", "0Otter", "1Warthog", "4Starfish", "5Frog"},
				{"Skunk", "0Woodpecker", "1Apatosaurus", "2Viper", "5Seal"},
				{"Beaver", "0Triceratops", "1Duck", "2Gorilla", "3Salamander", "4Viper", "5Apatosaurus"},
				{"Starfish", "0Frog", "1Swallow", "4Gorilla", "5Duck"},
				{"Viper", "0Apatosaurus", "1Beaver", "2Salamander", "5Skunk"},
				{"Gorilla", "0Duck", "1Starfish", "4Salamander", "5Beaver"},
				{"Salamander", "0Beaver", "1Gorilla", "5Viper"}	
		};
	private final int[] dirScores;
	private final double r;
	public Zoo(BombEdgework ew, double resizer)
	{
		dirScores = new int[] 
		{
			ew.findPort("DVI-D"), ew.findPort("RCA"), ew.findPort("SERIAL"), 
			ew.findPort("PS/2"), ew.findPort("RJ-45"), ew.findPort("PARALLEL")
		};
		r = resizer;
	}
	public void run()
	{
		//Getting the 1st animal, eliminating any animals that don't have any possible pairs
		String[] animalDoor = 
			{
				"Armadillo", "Baboon", "Caracal", "Caterpillar", "Cheetah", "Coyote", "Crocodile", "Deer", "Gazelle", 
				"Groundhog", "Ocelot", "Orca", "Penguin", "Plesiosaur", "Ram", "Sheep", "Squid", "Whale" 
			};
		ArrayList<String> animalChoices = new ArrayList<String>();
		for(int aa = 0; aa < animalDoor.length; aa++)
		{
			ArrayList<String> poss = getPossibleAnimals(animalDoor[aa]);
			if(poss.size() > 0)
				animalChoices.add(animalDoor[aa]);
		}
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		ImageIcon[] icon = new ImageIcon[animalChoices.size()];
		JButton[] jButton = new JButton[animalChoices.size()];
		if(animalChoices.size() % 2 == 1)
			optionPane.setLayout(new GridLayout((animalChoices.size() / 2) + 1, 2));
		else
			optionPane.setLayout(new GridLayout(animalChoices.size() / 2, 2));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int aa = 0; aa < animalChoices.size(); aa++)
		{
			icon[aa] = new ImageIcon("img/Zoo" + animalChoices.get(aa) + ".png");
			Image image = icon[aa].getImage();
			image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			icon[aa] = new ImageIcon(image);
			jButton[aa] = getButton(optionPane, animalChoices.get(aa), icon[aa]);
			optionPane.add(jButton[aa]);
		}
		JDialog dialog = optionPane.createDialog(frame, "");
		dialog.setTitle("Select animal #1:");
		dialog.setVisible(true);
		String a2, a1 = optionPane.getValue().toString();
		
		//Getting the 2nd animal, eliminating any animals that can't pair with the 1st animal.
		ArrayList<String> poss = getPossibleAnimals(a1);
		poss.sort(null);
		if(poss.size() > 1)
		{
			frame = new JFrame();
			optionPane = new JOptionPane();
			icon = new ImageIcon[poss.size()];
			jButton = new JButton[poss.size()];
			optionPane.setLayout(new GridLayout(poss.size(), 1));
			optionPane.setOptions(new Object[] {});
			optionPane.removeAll();
			for(int aa = 0; aa < poss.size(); aa++)
			{
				icon[aa] = new ImageIcon("img/Zoo" + poss.get(aa) + ".png");
				Image image = icon[aa].getImage();
				image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
				icon[aa] = new ImageIcon(image);
				jButton[aa] = getButton(optionPane, poss.get(aa), icon[aa]);
				optionPane.add(jButton[aa]);
			}
			dialog = optionPane.createDialog(frame, "");
			dialog.setTitle("Select animal #2:");
			dialog.setVisible(true);
			a2 = optionPane.getValue().toString();
		}
		else
			a2 = poss.get(0);
		//Getting the result of the paired animals
		String intersection = getIntersection(a1, a2);
		String dir = getDirection(intersection);
		ArrayList<String> press = new ArrayList<String>();
		press.add(intersection);
		if(dir.length() > 1)
		{
			dir = dir.substring(0, 1);
			String temp = intersection.replace(" ", "");
			for(int aa = 0; aa < 8; aa++)
			{
				int cursor = getCursor(temp);
				for(int bb = 1; bb < grid[cursor].length; bb++)
				{
					if(grid[cursor][bb].startsWith(dir))
					{
						temp = grid[cursor][bb].substring(1);
						if(aa % 2 == 1)
							press.add(grid[cursor][bb].substring(1));
						break;
					}
				}
			}
		}
		else
		{
			String temp = intersection.replace(" ", "");
			for(int aa = 0; aa < 4; aa++)
			{
				int cursor = getCursor(temp);
				for(int bb = 1; bb < grid[cursor].length; bb++)
				{
					if(grid[cursor][bb].startsWith(dir))
					{
						temp = grid[cursor][bb].substring(1);
						press.add(grid[cursor][bb].substring(1));
						break;
					}
				}
			}
		}
		JFrame f = new JFrame();
		f.setLayout(new GridLayout(5, 1));
		ImageIcon[] i = new ImageIcon[5];
		JLabel[] l = new JLabel[5];
		for(int aa = 0; aa < 5; aa++)
		{
			i[aa] = new ImageIcon("img/Zoo" + press.get(aa) + ".png");
			Image image = i[aa].getImage();
			image = image.getScaledInstance((int)(i[aa].getIconWidth() / r), (int)(i[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			i[aa] = new ImageIcon(image);
			l[aa] = new JLabel();
			l[aa].setIcon(i[aa]);
			f.add(l[aa]);
		}
		f.pack();
		f.setVisible(true);
		JOptionPane.showMessageDialog(null, "Press the animals\nin this order");
		f.setVisible(false);
	}
	//Returns an array of animals that can be picked
	private ArrayList<String> getPossibleAnimals(String animal)
	{
		ArrayList<String> poss = new ArrayList<String>();
		String[] others;
		switch(animal)
		{
			case "Gazelle":
			case "Caracal":
			case "Cheetah":
			case "Ocelot":
			case "Sheep":
			case "Caterpillar":
			case "Groundhog":
			case "Armadillo":
			case "Orca":
				others = new String[] {"Plesiosaur", "Penguin", "Baboon", "Whale", "Squid", "Coyote", "Ram", "Deer", "Crocodile"};
				break;
			default:
				others = new String[] {"Gazelle", "Caracal", "Cheetah", "Ocelot", "Sheep", "Caterpillar", "Groundhog", "Armadillo", "Orca"};
				break;
		}
		for(int aa = 0; aa < others.length; aa++)
		{
			String intersection = getIntersection(animal, others[aa]);
			if(intersection.length() > 0)
			{
				String dir = getDirection(intersection);
				if(dir.length() > 0)
					poss.add(others[aa]);
			}
		}
		return poss;
	}
	//Returns the initial space and the direction it's going
	private String[] getInitialSpaceDir(String animal)
	{
		String[][] animals =
			{
					{
						"Gazelle", "Caracal", "Cheetah",
						"Ocelot", "Sheep", "Caterpillar", 
						"Groundhog", "Armadillo", "Orca",
						"Plesiosaur", "Penguin", "Baboon",
						"Whale", "Squid", "Coyote", 
						"Ram", "Deer", "Crocodile" 
					},
					{
						"Bear", "Spider", "Pig",
						"Cat", "Cow", "TRex", 
						"Rabbit", "Horse", "Flamingo",
						"Flamingo", "Hyena", "Dimetrodon", 
						"Dromedary", "Warthog", "Swallow", 
						"Starfish", "Gorilla", "Salamander"
					}
			};
		for(int aa = 0; aa < 18; aa++)
		{
			if(animals[0][aa].equals(animal))
			{
				if(aa < 9)
					return new String[] {animals[1][aa], "3"};
				else
					return new String[] {animals[1][aa], "5"};
			}
		}
		return null;
	}
	//Returns the Intersection of the 2 animals
	//If there is no such intersection, returns an empty string
	private String getIntersection(String a1, String a2)
	{
		String[][] spaces = {getInitialSpaceDir(a1), getInitialSpaceDir(a2)};
		String temp = spaces[1][0].replace(" ", "");
		while(true)
		{
			if(spaces[0][0].equals(temp))
				return spaces[0][0];
			int cursor = getCursor(temp);
			boolean flag = true;
			for(int aa = 1; aa < grid[cursor].length; aa++)
			{
				if(grid[cursor][aa].startsWith(spaces[1][1]))
				{
					flag = false;
					if(spaces[0][0].equals(grid[cursor][aa].substring(1)))
						return spaces[0][0];
					else
						temp = grid[cursor][aa].substring(1);
					break;
				}
			}
			if(flag)
			{
				cursor = getCursor(spaces[0][0]);
				for(int aa = 1; aa < grid[cursor].length; aa++)
				{
					if(grid[cursor][aa].startsWith(spaces[0][1]))
					{
						flag = false;
						spaces[0][0] = grid[cursor][aa].substring(1);
						temp = spaces[1][0].replace(" ", "");
						break;
					}
				}
				if(flag)
					break;
			}
		}
		return "";
	}
	//Returns a valid direction of the intersection
	//If there is no valid direction, it returns an empty string
	private String getDirection(String intersection)
	{
		ArrayList<String> directions = new ArrayList<String>();
		ArrayList<Integer> scores = new ArrayList<Integer>();
		String skipDir = "";
		for(int aa = 0; aa < 6; aa++)
		{
			String dir = (aa) + "";
			String temp = intersection.replace(" ", "");
			int length = 1;
			boolean flag = true;
			while(flag)
			{
				int cursor = getCursor(temp);
				flag = false;
				for(int bb = 1; bb < grid[cursor].length; bb++)
				{
					if(grid[cursor][bb].startsWith(dir))
					{
						flag = true;
						temp = grid[cursor][bb].substring(1);
						length++;
						break;
					}
				}
			}
			if(length >= 9)
				skipDir = (aa + "" + aa);
			if(length >= 5)
			{
				directions.add(dir.toUpperCase());
				scores.add(dirScores[aa]);
			}
		}
		int best = getBestScore(scores);
		ArrayList<String> bestDir = new ArrayList<String>();
		for(int aa = 0; aa < scores.size(); aa++)
		{
			if(best == scores.get(aa))
			{
				bestDir.add(directions.get(aa));
				directions.remove(aa);
				scores.remove(aa);
				aa--;
			}
		}
		while(bestDir.size() > 1 && scores.size() > 0)
		{
			best = getBestScore(scores);
			bestDir.clear();
			for(int aa = 0; aa < scores.size(); aa++)
			{
				if(best == scores.get(aa))
				{
					bestDir.add(directions.get(aa));
					directions.remove(aa);
					scores.remove(aa);
					aa--;
				}
			}
		}
		if(bestDir.size() != 1)
			return skipDir;
		else
			return bestDir.get(0);
	}
	//Returns the best score;
	private int getBestScore(ArrayList<Integer> scores)
	{
		int best = scores.get(0);
		for(int aa = 1; aa < scores.size(); aa++)
		{
			if(scores.get(aa) > best)
				best = scores.get(aa);
		}
		return best;
	}
	//Returns the cursor of the animal
	private int getCursor(String animal)
	{
		for(int aa = 0; aa < grid.length; aa++)
		{
			if(grid[aa][0].equals(animal))
				return aa;
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
	
	
}
