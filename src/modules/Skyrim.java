package modules;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import start.BombEdgework;

public class Skyrim 
{
	private final String[][] imgNames =
		{
				{"Altmer", "Argonian", "Breton", "Dunmer", "Imperial", "Khajiit", "Nord", "Orc", "Redguard"},
				{"Axe of Whiterun", "Blade of Woe", "Bow of the Hunt", "Chillrend", "Dawnbreaker", "Firiniel's End", "Mace of Molag Bal", "Volendrung", "Windshear"},
				{"Alduin", "Blood Dragon", "Cave Bear", "Dragon Priest", "Draugr", "Draugr Overlord", "Frost Troll", "Frostbite Spider", "Mudcrab"},
				{"Dawnstar", "Ivarstead", "Markarth", "Riverwood", "Rorikstead", "Solitude", "Whiterun", "Windhelm", "Winterhold"},
				{"Disarm", "Dismay", "Dragonrend", "Fire Breath", "Ice Form", "Kyne's Peace", "Slow Time", "Unrelenting Force", "Whirlwind Sprint"}
		};
	private final double r;
	private final BombEdgework ew;
	public Skyrim(double resizer, BombEdgework e)
	{
		r = resizer;
		ew = e;
	}
	public String run()
	{
		ArrayList<String> races = getImages("Select Race", imgNames[0]);
		ArrayList<String> order;
		if(ew.BAT() < 3)
			order = new ArrayList<String>(Arrays.asList(new String[] {"Nord", "Khajiit", "Breton", "Argonian", "Dunmer", "Altmer", "Redguard", "Orc", "Imperial"}));
		else if(ew.BAT() < 6)
			order = new ArrayList<String>(Arrays.asList(new String[] {"Imperial", "Orc", "Redguard", "Altmer", "Dunmer", "Argonian", "Breton", "Khajiit", "Nord"}));
		else
			order = new ArrayList<String>(Arrays.asList(new String[] {"Dunmer", "Orc", "Nord", "Altmer", "Khajiit", "Breton", "Redguard", "Imperial", "Argonian"}));
		String race = getFirst(races, order);
		selectImage(race, "Race");
		ArrayList<String> weapons = getImages("Select Weapon", imgNames[1]);
		if(!(race.equals("Breton")) && races.contains("Breton"))
			order = new ArrayList<String>(Arrays.asList(new String[] {"Axe of Whiterun", "Dawnbreaker", "Windshear", "Blade of Woe", "Firiniel's End", "Bow of the Hunt", "Volendrung", "Chillrend", "Mace of Molag Bal"}));
		else if(!(race.equals("Orc")) && races.contains("Orc"))
			order = new ArrayList<String>(Arrays.asList(new String[] {"Blade of Woe", "Volendrung", "Mace of Molag Bal", "Axe of Whiterun", "Bow of the Hunt", "Chillrend", "Dawnbreaker", "Firiniel's End", "Windshear"}));
		else if(race.equals("Redguard"))
			order = new ArrayList<String>(Arrays.asList(new String[] {"Chillrend", "Bow of the Hunt", "Dawnbreaker", "Volendrung", "Windshear", "Firiniel's End", "Axe of Whiterun", "Mace of Molag Bal", "Blade of Woe"}));
		else
			order = new ArrayList<String>(Arrays.asList(new String[] {"Mace of Molag Bal", "Firiniel's End", "Volendrung", "Windshear", "Axe of Whiterun", "Blade of Woe", "Dawnbreaker", "Bow of the Hunt", "Chillrend"}));
		String weapon = getFirst(weapons, order);
		selectImage(weapon, "Weapon");
		ArrayList<String> enemies = getImages("Select Enemy", imgNames[2]);
		if(weapon.equals("Firiniel's End") || weapon.equals("Bow of the Hunt"))
			order = new ArrayList<String>(Arrays.asList(new String[] {"Dragon Priest", "Alduin", "Mudcrab", "Draugr Overlord", "Draugr", "Blood Dragon", "Frostbite Spider", "Frost Troll", "Cave Bear"}));
		else if(weapon.equals("Dawnbreaker") || weapon.equals("Mace of Molag Bal") || weapon.equals("Volendrung"))
			order = new ArrayList<String>(Arrays.asList(new String[] {"Frost Troll", "Mudcrab", "Frostbite Spider", "Draugr Overlord", "Draugr", "Dragon Priest", "Cave Bear", "Blood Dragon", "Alduin"}));
		else if(weapon.equals("Chillrend") || weapon.equals("Windshear"))
			order = new ArrayList<String>(Arrays.asList(new String[] {"Draugr Overlord", "Cave Bear", "Blood Dragon", "Mudcrab", "Draugr", "Dragon Priest", "Alduin", "Frostbite Spider", "Frost Troll"}));
		else if(weapon.equals("Axe of Whiterun"))
			order = new ArrayList<String>(Arrays.asList(new String[] {"Cave Bear", "Frost Troll", "Frostbite Spider", "Blood Dragon", "Draugr", "Draugr Overlord", "Mudcrab", "Alduin", "Dragon Priest"}));
		else
			order = new ArrayList<String>(Arrays.asList(new String[] {"Blood Dragon", "Mudcrab", "Frostbite Spider", "Alduin", "Frost Troll", "Draugr Overlord", "Dragon Priest", "Cave Bear", "Draugr"}));
		String enemy = getFirst(enemies, order);
		selectImage(enemy, "Enemy");
		ArrayList<String> cities = getImages("Select City", imgNames[3]);
		order = new ArrayList<String>(Arrays.asList(new String[] {"Solitude", "Dawnstar", "Winterhold", "Windhelm", "Ivarstead", "Riverwood", "Whiterun", "Rorikstead", "Markarth"}));
		String city;
		switch(race)
		{
			case "Nord":
				city = races.contains("Argonian") ? getFirst(cities, shift(order, order.indexOf("Whiterun"))) : getFirst(cities, shift(order, order.indexOf("Windhelm")));
				break;
			case "Khajiit":
				city = enemies.contains("Frost Troll") ? getFirst(cities, shift(order, order.indexOf("Ivarstead"))) : getFirst(cities, shift(order, order.indexOf("Rorikstead")));
				break;
			case "Breton":
				city = weapon.equals("Blade of Woe") ? getFirst(cities, shift(order, order.indexOf("Dawnstar"))) : getFirst(cities, shift(order, order.indexOf("Riverwood")));
				break;
			case "Argonian":
				city = enemy.equals("Draugr Overlord") ? getFirst(cities, shift(order, order.indexOf("Markarth"))) : getFirst(cities, shift(order, order.indexOf("Dawnstar")));
				break;
			case "Dunmer":
				city = enemies.contains("Mudcrab") ? getFirst(cities, shift(order, order.indexOf("Solitude"))) : getFirst(cities, shift(order, order.indexOf("Rorikstead")));
				break;
			case "Altmer":
				city = weapon.equals("Windshear") ? getFirst(cities, shift(order, order.indexOf("Riverwood"))) : getFirst(cities, shift(order, order.indexOf("Solitude")));
				break;
			case "Redguard":
				city = !(weapons.contains("Dawnbreaker") || weapons.contains("Mace of Molag Bal") || weapons.contains("Volendrung")) ? getFirst(cities, shift(order, order.indexOf("Windhelm"))) : getFirst(cities, shift(order, order.indexOf("Markarth")));
				break;
			case "Orc":
				city = enemy.equals("Cave Bear") ? getFirst(cities, shift(order, order.indexOf("Winterhold"))) : getFirst(cities, shift(order, order.indexOf("Ivarstead")));
				break;
			default:
				city = weapons.contains("Volendrung") ? getFirst(cities, shift(order, order.indexOf("Rorikstead"))) : getFirst(cities, shift(order, order.indexOf("Winterhold")));
				break;
		}
		selectImage(city, "City");
		ArrayList<String> shouts = getImages("Select Shout", imgNames[4]);
		if("ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(ew.getSNCHAR(0)) >= 0)
			order = new ArrayList<String>(Arrays.asList(new String[] {"Draugr Overlord", "Frost Troll", "Blood Dragon", "Frostbite Spider", "Dragon Priest", "Mudcrab", "Cave Bear", "Draugr", "Alduin"}));
		else if("13579".indexOf(ew.getSNCHAR(0)) >= 0)
			order = new ArrayList<String>(Arrays.asList(new String[] {"Draugr", "Dragon Priest", "Mudcrab", "Frost Troll", "Alduin", "Draugr Overlord", "Blood Dragon", "Cave Bear", "Frostbite Spider"}));
		else
			order = new ArrayList<String>(Arrays.asList(new String[] {"Alduin", "Mudcrab", "Cave Bear", "Draugr Overlord", "Blood Dragon", "Draugr", "Frostbite Spider", "Dragon Priest", "Frost Troll"}));
		int index = order.indexOf(enemy);
		order = new ArrayList<String>(Arrays.asList(new String[] {"Unrelenting Force", "Disarm", "Ice Form", "Whirlwind Sprint", "Dragonrend", "Dismay", "Fire Breath", "Kyne's Peace", "Slow Time"}));
		String shout = getFirst(shouts, shift(order, index));
		selectImage(shout, "Shout");
		races.remove(race);
		weapons.remove(weapon);
		cities.remove(city);
		shouts.remove(shout);
		return ("RACES:\n" + races.get(0).toUpperCase() + "\n" + races.get(1).toUpperCase() + 
				"\n--------------------\nWEAPONS:\n" + weapons.get(0).toUpperCase() + "\n" + weapons.get(1).toUpperCase() + 
				"\n--------------------\nENEMIES:\n" + enemies.get(0).toUpperCase() + "\n" + enemies.get(1).toUpperCase() + 
				"\n--------------------\nCITIES:\n" + cities.get(0).toUpperCase() + "\n" + cities.get(1).toUpperCase() + 
				"\n--------------------\nSHOUTS:\n" + shouts.get(0).toUpperCase() + "\n" + shouts.get(1).toUpperCase());
	}
	private ArrayList<String> shift(ArrayList<String> img, int index)
	{
		ArrayList<String> temp = new ArrayList<String>();
		for(int i = 0; i < img.size(); i++)
			temp.add(img.get((i + index) % img.size()));
		return temp;
	}
	private String getFirst(ArrayList<String> img, ArrayList<String> order)
	{
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		for(int i = 0; i < 3; i++)
			indexes.add(order.indexOf(img.get(i)));
		Collections.sort(indexes);
		return order.get(indexes.get(0));
	}
	private ArrayList<String> getImages(String out, String[] img)
	{
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(img));
		ArrayList<String> images = new ArrayList<String>();
		for(int i = 0; i < 3; i++)
		{
			JFrame frame = new JFrame();
			JOptionPane optionPane = new JOptionPane();
			ImageIcon[] icon = new ImageIcon[list.size()];
			JButton[] jButton = new JButton[list.size()];
			optionPane.setLayout(new GridLayout(3, 3));
			optionPane.setOptions(new Object[] {});
			optionPane.removeAll();
			for(int aa = 0; aa < list.size(); aa++)
			{
				icon[aa] = new ImageIcon("img/Skyrim" + list.get(aa).replace(" ", "") + ".png");
				Image image = icon[aa].getImage();
				image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
				icon[aa] = new ImageIcon(image);
				jButton[aa] = getButton(optionPane, list.get(aa), icon[aa]);
				optionPane.add(jButton[aa]);
			}
			JDialog dialog = optionPane.createDialog(frame, "");
			dialog.setTitle(out + " #" + (i + 1));
			dialog.setVisible(true);
			images.add(optionPane.getValue().toString());
			list.remove(optionPane.getValue().toString());
		}
		return images;
	}
	private void selectImage(String img, String type)
	{
		ImageIcon icon = new ImageIcon("img/Skyrim" + img.replace(" ", "") + ".png");
		Image image = icon.getImage();
		image = image.getScaledInstance((int)(icon.getIconWidth() / r), (int)(icon.getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(image);
		JLabel label = new JLabel();
		label.setIcon(icon);
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.add(label, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		JOptionPane.showMessageDialog(null, "Set the " + type + " to\n" + img);
		frame.setVisible(false);
	}
	private JButton getButton(final JOptionPane optionPane, String name, ImageIcon icon ) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    button.setText(name);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(name);
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
}
