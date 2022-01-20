package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class AdventureGame 
{
	private final BombEdgework ew;
	private final boolean isSouv;
	public AdventureGame(BombEdgework e, boolean s)
	{
		ew = e;
		isSouv = s;
	}
	public String run()
	{
		String souv = "ITEMS USED:\n";
		ArrayList<String> used = new ArrayList<String>();
		if(isSouv)
		{
			if(JOptionPane.showConfirmDialog(null, "Is there a potion present?") == 0)
				souv = souv + "POTION\n";
		}
		JOptionPane.showMessageDialog(null, "Make sure to use\nthe potion first");
		String enemy = JOptionPane.showInputDialog("Enter the enemy name:").toUpperCase();
		int[] enemyStats = getStats(enemy);
		while(enemyStats[0] == -1)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			enemy = JOptionPane.showInputDialog("Enter the enemy name:").toUpperCase();
			enemyStats = getStats(enemy);
		}
		souv = "ENEMY: " + enemy.toUpperCase() + "\n" + souv;
		ArrayList<String> weapons = new ArrayList<String>();
		ArrayList<String> items = new ArrayList<String>();
		int STR = -1;
		int DEX = -1;
		int INT = -1;
		int[] HEIGHT = new int[2];
		int TEMP = -1;
		double GRAVITY = -1.0;
		int PRESSURE = -1;
		String[] statList = {"STR", "DEX", "INT", "HEIGHT (spaces)", "TEMPERATURE", "GRAVITY", "PRESSURE"};
		for(int aa = 0; aa < 7; aa++)
		{
			String input = JOptionPane.showInputDialog("Enter the " + statList[aa] + ":");
			boolean val = v1(input, aa);
			while(!(val))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the " + statList[aa] + ":");
				val = v1(input, aa);
			}
			switch(aa)
			{
				case 0:
					STR = Integer.parseInt(input);
					break;
				case 1:
					DEX = Integer.parseInt(input);
					break;
				case 2:
					INT = Integer.parseInt(input);
					break;
				case 3:
					String[] temp = input.split(" ");
					HEIGHT[0] = Integer.parseInt(temp[0]);
					HEIGHT[1] = Integer.parseInt(temp[1]);
					break;
				case 4:
					TEMP = Integer.parseInt(input);
					break;
				case 5:
					GRAVITY = Double.parseDouble(input);
					break;
				default:
					PRESSURE = Integer.parseInt(input);
					break;
			}
		}
		for(int aa = 0; aa < 8; aa++)
		{
			String input = JOptionPane.showInputDialog("Enter item #" + (aa +1) + ":").toUpperCase();
			int v = valid(input, weapons, items);
			while(v < 0)
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter item #" + (aa +1) + ":").toUpperCase();
				v = valid(input, weapons, items);
			}
			if(v == 2)
			{
				if(weapons.size() == 0)
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					aa--;
				}
			}
			else if(v == 1)
				weapons.add(input.toUpperCase());
			else
				items.add(input.toUpperCase());
		}
		for(int aa = 0; aa < items.size(); aa++)
		{
			switch(items.get(aa))
			{
				case "BALLOON":
					if(GRAVITY < 9.3 || PRESSURE > 110)
						used.add(items.get(aa).toUpperCase());
					break;
				case "BATTERY":
					if(ew.BAT() <= 1)
						used.add(items.get(aa).toUpperCase());
					break;
				case "BELLOWS":
					if((enemy.equals("DRAGON") || enemy.equals("EAGLE")))
					{
						if(PRESSURE > 105)
							used.add(items.get(aa).toUpperCase());
					}
					else if(PRESSURE < 95)
						used.add(items.get(aa).toUpperCase());
					break;
				case "CRYSTAL BALL":
					if(INT > ew.getSNDIG(ew.numSNDIGS() - 1) && !(enemy.equals("WIZARD")))
						used.add(items.get(aa).toUpperCase());
					break;
				case "FEATHER":
					if(DEX > STR || DEX > INT)
						used.add(items.get(aa).toUpperCase());
					break;
				case "HARD DRIVE":
					if(ew.numDupPorts() > 0)
						used.add(items.get(aa).toUpperCase());
					break;
				case "LAMP":
					if(TEMP < 12 && !(enemy.equals("LIZARD")))
						used.add(items.get(aa).toUpperCase());
					break;
				case "MOONSTONE":
					if(ew.numUnlit() >= 2)
						used.add(items.get(aa).toUpperCase());
					break;
				case "SMALL DOG":
					if(!(enemy.equals("DEMON") || enemy.equals("DRAGON") || enemy.equals("TROLL")))
						used.add(items.get(aa).toUpperCase());
					break;
				case "STEPLADDER":
					if(HEIGHT[0] < 4 && !(enemy.equals("GOBLIN") || enemy.equals("LIZARD")))
						used.add(items.get(aa).toUpperCase());
					break;
				case "SUNSTONE":
					if(ew.numLit() >= 2)
						used.add(items.get(aa).toUpperCase());
					break;
				case "SYMBOL":
					if(enemy.equals("DEMON") || enemy.equals("GOLEM") || TEMP > 31)
						used.add(items.get(aa).toUpperCase());
					break;
				case "TICKET":
					if(((HEIGHT[0] == 4 && HEIGHT[1] >= 6) || HEIGHT[0] > 4) && GRAVITY >= 9.2 && GRAVITY <= 10.4)
						used.add(items.get(aa).toUpperCase());
					break;
				case "TROPHY":
					if(enemy.equals("TROLL") || STR > ew.getSNDIG(0))
						used.add(items.get(aa).toUpperCase());
					break;
			}
		}
		int best = getWeaponBonus(weapons.get(0), STR, DEX, INT, enemyStats);
		int bestCur = 0;
		for(int aa = 1; aa < weapons.size(); aa++)
		{
			int temp = getWeaponBonus(weapons.get(aa), STR, DEX, INT, enemyStats);
			if(temp > best)
			{
				best = temp + 0;
				bestCur = aa;
			}
		}
		used.add(weapons.get(bestCur));
		String out = "";
		for(int aa = 0; aa < used.size(); aa++)
			out = out + "" + used.get(aa) + "\n";
		souv = souv + "" + out.toUpperCase();
		JOptionPane.showMessageDialog(null, "Use these items:\n" + out);
		return souv;
	}
	private int[] getStats(String i)
	{
		switch(i)
		{
			case "DEMON":
				return new int[] {50, 50, 50};
			case "DRAGON":
				return new int[] {10, 11, 13};
			case "EAGLE":
				return new int[] {4, 7, 3};
			case "GOBLIN":
				return new int[] {3, 6, 5};
			case "GOLEM":
				return new int[] {9, 4, 7};
			case "TROLL":
				return new int[] {8, 5, 4};
			case "LIZARD":
				return new int[] {4, 6, 3};
			case "WIZARD":
				return new int[] {4, 3, 8};
		}
		return new int[] {-1, -1, -1};
	}
	private int getWeaponBonus(String w, int STR, int DEX, int INT, int[] es)
	{
		switch(w)
		{
			case "BROADSWORD":
				return (STR - es[0]);
			case "CABER":
				return (STR + 2 - es[0]);
			case "NASTY KNIFE":
				return (DEX - es[1]);
			case "LONGBOW":
				return (DEX + 2 - es[1]);
			case "MAGIC ORB":
				return (INT - es[2]);
			default:
				return (INT + 2 - es[2]);
		}
	}
	private int valid(String i, ArrayList<String> weapons, ArrayList<String> items)
	{
		switch(i)
		{
			case "BALLOON":
			case "BATTERY":
			case "BELLOWS":
			case "CHEAT CODE":
			case "CRYSTAL BALL":
			case "FEATHER":
			case "HARD DRIVE":
			case "LAMP":
			case "MOONSTONE":
			case "SMALL DOG":
			case "STEPLADDER":
			case "SUNSTONE":
			case "SYMBOL":
			case "TICKET":
			case "TROPHY":
				if(items.size() < 5)
					return 0;
				break;
			case "BROADSWORD":
			case "CABER":
			case "NASTY KNIFE":
			case "LONGBOW":
			case "MAGIC ORB":
			case "GRIMOIRE":
				if(weapons.size() < 3)
					return 1;
				break;
			case "":
				return 2;
		}
		return -1;
	}
	private boolean v1(String i, int aa)
	{
		switch(aa)
		{
			case 3:
				String[] temp = i.split(" ");
				if(temp.length == 2)
					return (isInt(temp[0]) && isInt(temp[1]));
				return false;
			case 5:
				try
				{
					Double.parseDouble(i);
					return true;
				}
				catch (NumberFormatException e) {return false;}
			default:
				return isInt(i);
		}
	}
	private boolean isInt(String i)
	{
		for(int aa = 0; aa < i.length(); aa++)
		{
			if("0123456789".indexOf(i.charAt(aa)) < 0)
				return false;
		}
		return true;
	}
}
