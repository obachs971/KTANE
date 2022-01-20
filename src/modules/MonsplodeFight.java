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

import start.BombConfig;
import start.BombEdgework;

public class MonsplodeFight 
{
	private final int[][] table =
	{
			{10, 10, 5, 0, 10, 10, 10, 10, 10},
			{10, 5, 5, 5, 10, 10, 20, 10, 10},
			{10, 10, 10, 10, 20, 10, 10, 10, 10},
			{0, 10, 10, 20, 10, 10, 10, 10, 5},
			{10, 10, 5, 10, 5, 5, 20, 10, 10},
			{10, 10, 20, 10, 20, 5, 5, 10, 10},
			{10, 5, 20, 10, 5, 20, 5, 10, 10},
			{10, 10, 10, 10, 10, 20, 5, 5, 10},
			{10, 10, 10, 20, 10, 10, 10, 10, 5}
	};
	private final int NORMAL = 0, POISON = 1, ROCK = 2, GHOST = 3, FIRE = 4, WATER = 5, GRASS = 6, ELECTR = 7, DARK = 8;
	private final BombConfig cf;
	private final BombEdgework ew;
	private final double r;
	public MonsplodeFight(BombConfig c, BombEdgework e, double resize)
	{
		cf = c;
		ew = e;
		r = resize;
	}
	public String run()
	{
		String[] imgName = 
			{
				"Aluga", "Asteran", "Bob", "Buhar", "Caadarim",
				"Clondar", "Cutie Pie", "Docsplode", "Flaurim", "Gloorim",
				"Lanaluff", "Lugirit", "Magmy", "Melbor", "Mountoise",
				"Myrchat", "Nibs", "Percy", "Pouse", "Ukkens",
				"Vellarim", "Violan", "Zapra", "Zenlad", "Glitch"
			};
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		ImageIcon[] icon = new ImageIcon[imgName.length];
		JButton[] jButton = new JButton[imgName.length];
		optionPane.setLayout(new GridLayout(7, 4));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int bb = 0; bb < imgName.length; bb++)
		{
			icon[bb] = new ImageIcon("img/Monsplode" + imgName[bb].replace(" ", "") + ".jpg");
			Image image = icon[bb].getImage();
			image = image.getScaledInstance((int)(icon[bb].getIconWidth() / r), (int)(icon[bb].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			icon[bb] = new ImageIcon(image);
			jButton[bb] = getButton(optionPane, imgName[bb], icon[bb]);
			optionPane.add(jButton[bb]);
		}
		JDialog dialog = optionPane.createDialog(frame, "");
		dialog.setTitle("Select the displayed Monsplode:");
		dialog.setVisible(true);
		String monsplode = optionPane.getValue().toString();
		dialog.setVisible(false);
		String souv = "MONSPLODE: " + monsplode.toUpperCase();
		//1 in 92 chance the module will show a glitched display 
		if(monsplode.equals("GLITCH"))
		{
			JOptionPane.showMessageDialog(null, "Press DEFUSE");
			souv = souv + "\nMOVE: DEFUSE"; 
		}	
		else
		{
			ArrayList<String> moves = new ArrayList<String>();
			for(int aa = 0; aa < 4; aa++)
			{
				String input = JOptionPane.showInputDialog("Enter move #" + (aa + 1) + " in\nreading order:").toUpperCase();
				while(moves.contains(input) || getMoveType(input) == -1)
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog("Enter move #" + (aa + 1) + " in\nreading order:").toUpperCase();
				}
				moves.add(input.toUpperCase());
				souv = souv + "\nMOVE #" + (aa + 1) + ": " + input.toUpperCase();
			}
			if(monsplode.equals("DOCSPLODE") && moves.contains("BOOM"))
				JOptionPane.showMessageDialog(null, "Press BOOM");
			else if(monsplode.equals("PERCY") && moves.contains("SPLASH"))
				JOptionPane.showMessageDialog(null, "Press SPLASH");
			else
				JOptionPane.showMessageDialog(null, "Press " + getBestMove(monsplode, moves));
		}
		return souv;
	}
	private String getBestMove(String monsplode, ArrayList<String> moves)
	{
		int type;
		ArrayList<Integer> dmg = new ArrayList<Integer>();
		switch(monsplode)
		{
			case "BUHAR":
				type = WATER;
				for(int aa = 0; aa < moves.size(); aa++)
				{
					if(getMoveType(moves.get(aa)) == ROCK)
						dmg.add(0);
					else
						dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type]);
				}
				break;
			case "LANALUFF":
				type = NORMAL;
				if(ew.numCharsInSN("LANALUFF") > 0)
				{
					for(int aa = 0; aa < moves.size(); aa++)
					{
						if(getMoveType(moves.get(aa)) == POISON)
							dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type] + 30);
						else
							dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type]);
					}
				}
				else
				{
					for(int aa = 0; aa < moves.size(); aa++)
						dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type]);
				}
				break;
			case "BOB":
				type = NORMAL;
				if(ew.findLit("BOB"))
				{
					for(int aa = 0; aa < moves.size(); aa++)
					{
						if(getMoveType(moves.get(aa)) == NORMAL)
							dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type]);
						else
							dmg.add(0);
					}	
				}
				else
				{
					for(int aa = 0; aa < moves.size(); aa++)
						dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type]);
				}
				break;
			case "MOUNTOISE":
				type = ROCK;
				if(JOptionPane.showConfirmDialog(null, "Is there a strike?") == 0)
					type = NORMAL;
				for(int aa = 0; aa < moves.size(); aa++)
					
						dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type]);
				break;
			case "NIBS":
				type = NORMAL;
				for(int aa = 0; aa < moves.size(); aa++)
				{
					if(getMoveType(moves.get(aa)) == GRASS)
						dmg.add(0);
					else
						dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type]);
				}
				break;
			case "ALUGA":
				type = NORMAL;
				for(int aa = 0; aa < moves.size(); aa++)
				{
					if(getMoveType(moves.get(aa)) == FIRE)
						dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type] + 20);
					else if(getMoveType(moves.get(aa)) == WATER)
						dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type] - 10);
					else
						dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type]);
				}
				break;
			case "LUGIRIT":
				type = GHOST;
				for(int aa = 0; aa < moves.size(); aa++)
				{
					if(getMoveType(moves.get(aa)) == WATER)
						dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type] + 20);
					else if(getMoveType(moves.get(aa)) == FIRE)
						dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type] - 10);
					else
						dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type]);
				}
				break;
			case "CAADARIM":
				type = NORMAL;
				if(ew.numPorts() >= 1)
				{
					for(int aa = 0; aa < moves.size(); aa++)
					{
						if(getMoveType(moves.get(aa)) == NORMAL)
							dmg.add(0);
						else
							dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type]);
					}	
				}
				else
				{
					for(int aa = 0; aa < moves.size(); aa++)
						dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type]);
				}
				break;
			case "VELLARIM":
				type = WATER;
				if(ew.findPort("PARALLEL") > 0)
				{
					for(int aa = 0; aa < moves.size(); aa++)
					{
						if(getMoveType(moves.get(aa)) == NORMAL)
							dmg.add(0);
						else
							dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type]);
					}	
				}
				else
				{
					for(int aa = 0; aa < moves.size(); aa++)
						dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type]);
				}
				break;
			case "FLAURIM":
				type = FIRE;
				if(ew.findPort("SERIAL") > 0)
				{
					for(int aa = 0; aa < moves.size(); aa++)
					{
						if(getMoveType(moves.get(aa)) == NORMAL)
							dmg.add(0);
						else
							dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type]);
					}	
				}
				else
				{
					for(int aa = 0; aa < moves.size(); aa++)
						dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type]);
				}
				break;
			case "GLOORIM":
				type = DARK;
				if(ew.findPort("DVI-D") > 0)
				{
					for(int aa = 0; aa < moves.size(); aa++)
					{
						if(getMoveType(moves.get(aa)) == NORMAL)
							dmg.add(0);
						else
							dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type]);
					}	
				}
				else
				{
					for(int aa = 0; aa < moves.size(); aa++)
						dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type]);
				}
				break;
			case "MELBOR":
				type = DARK;
				for(int aa = 0; aa < moves.size(); aa++)
				{
					dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type]);
					if(dmg.get(aa) == 60 || dmg.get(aa) == 80)
						dmg.set(aa, 0);
				}
				break;
			case "CLONDAR":
				type = ELECTR;
				for(int aa = 0; aa < moves.size(); aa++)
				{
					if(getMoveType(moves.get(aa)) == WATER)
						dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type] + 30);
					else
						dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type]);
				}
				break;
			case "DOCSPLODE":
				type = NORMAL;
				for(int aa = 0; aa < moves.size(); aa++)
					dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type]);
				break;
			case "MAGMY":
				type = FIRE;
				if(ew.BAT() < 3)
					type = ROCK;
				for(int aa = 0; aa < moves.size(); aa++)
					dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type]);
				break;
			case "POUSE":
				type = ELECTR;
				for(int aa = 0; aa < moves.size(); aa++)
				{
					dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type]);
					if(dmg.get(aa) >= 60)
						dmg.set(aa, 0);
				}
				break;
			case "UKKENS":
				type = POISON;
				for(int aa = 0; aa < moves.size(); aa++)
				{
					if(getMoveType(moves.get(aa)) == WATER)
						dmg.add(0);
					else
						dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type]);
				}
				break;
			case "ASTERAN":
				type = GRASS;
				if(ew.findInd("CAR"))
					type = WATER;
				for(int aa = 0; aa < moves.size(); aa++)
					dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type]);
				break;
			case "VIOLAN":
				type = GRASS;
				if(ew.findInd("CLR"))
					type = WATER;
				for(int aa = 0; aa < moves.size(); aa++)
					dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type]);
				break;
			case "ZENLAD":
				type = GRASS;
				for(int aa = 0; aa < moves.size(); aa++)
				{
					if(getMoveType(moves.get(aa)) == ELECTR)
						dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type] + 30);
					else
						dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type]);
				}
				break;
			case "ZAPRA":
				type = ELECTR;
				if(ew.BAT() < 3)
					type = NORMAL;
				for(int aa = 0; aa < moves.size(); aa++)
					dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type]);
				break;
			case "MYRCHAT":
				type = POISON;
				if(ew.numLit() == 0)
					type = DARK;
				for(int aa = 0; aa < moves.size(); aa++)
					dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type]);
				break;
			case "PERCY":
				type = WATER;
				for(int aa = 0; aa < moves.size(); aa++)
					dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type]);
				break;
			case "CUTIE PIE":
				type = NORMAL;
				for(int aa = 0; aa < moves.size(); aa++)
					dmg.add(getDamage(monsplode, type, moves, aa) * table[getMoveType(moves.get(aa))][type] * (-1));
				break;
		}
		if(moves.contains("BOOM"))
		{
			dmg.remove(moves.indexOf("BOOM"));
			moves.remove("BOOM");
		}
		double best = dmg.get(0);
		int bestCur = 0;
		//System.out.println(moves.get(0) + ": " + dmg.get(0));
		for(int aa = 1; aa < dmg.size(); aa++)
		{
			//System.out.println(moves.get(aa) + ": " + dmg.get(aa));
			if(dmg.get(aa) > best)
			{
				best = dmg.get(aa);
				bestCur = aa;
			}
		}
		return moves.get(bestCur);
	}

	private int getDamage(String monsplode, int type, ArrayList<String> moves, int cur)
	{
		switch(moves.get(cur))
		{
			case "APPEARIFY":
				if(type == DARK)
					return 10;
				else
					return 4;
			case "BATTERY POWER":
				return (ew.BAT() * 2);
			case "BEDROCK":
				return (cf.getNumberModules());
			case "BOO":
				return (ew.numCharsInSN("O0") * 3);
			case "BUG SPRAY":
				if(monsplode.equals("MELBOR") || monsplode.equals("ZENLAD"))
					return 10;
				else
					return 2;
			case "COUNTDOWN":
				String min = JOptionPane.showInputDialog("Enter the minutes remaining:");
				boolean v = v1(min);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR");
					min = JOptionPane.showInputDialog("Enter the minutes remaining:");
					v = v1(min);
				}
				return Integer.parseInt(min);
			case "DARK PORTAL":
				return (ew.numPorts());
			case "FIERY SOUL":
				return ((ew.BAT() * ew.BH()));
			case "FINALE":
				if(JOptionPane.showConfirmDialog(null, "Is this the last\nunsolved module?") == 0)
					return 10;
				else
					return 2;
			case "FREAK OUT":
				if(ew.findLit("FRK") || ew.findLit("FRQ"))
					return 10;
				else if(ew.findUnlit("FRK") || ew.findUnlit("FRQ"))
					return 5;
				else
					return 1;
			case "GLYPH":
				return (monsplode.replace(" ", "").length());
			case "LAST WORD":
				return (ew.getSNDIG(ew.numSNDIGS() - 1));
			case "SENDIFY":
				if(type == ROCK || type == GRASS)
					return 10;
				else
					return 2;
			case "SHOCK":
				if(ew.findPort("RJ-45") > 0)
					return 8;
				else
					return 3;
			case "SHRINK":
				return (ew.getSmallestSNDIG());
			case "SIDESTEP":
				switch(cur)
				{
					case 0:
						return (moves.get(1).length());
					case 1:
						return (moves.get(0).length());
					case 2:
						return (moves.get(3).length());
					default:
						return (moves.get(2).length());
				}
			case "STRETCH":
				return (ew.getLargestSNDIG());
			case "VOID":
				if(JOptionPane.showConfirmDialog(null, "Are there any\nsolved modules?") == 1)
					return 10;
				else
					return 2;
			case "CANDLE":
				return 2;
			case "CAVE IN":
				return 3;
			case "DOUBLE ZAP":
				return 4;
			case "EARTHQUAKE":
				return 5;
			case "FLAME SPEAR":
				return 6;
			case "FOUNTAIN":
				return 6;
			case "GRASS BLADE":
				return 4;
			case "HEAVY RAIN":
				return 4;
			case "HIGH VOLTAGE":
				return 6;
			case "HOLLOW GAZE":
				return 4;
			case "IVY SPIKES":
				return 6;
			case "SPECTRE":
				return 5;
			case "SPLASH":
				return 0;
			case "TAC":
				return 5;
			case "TANGLE":
				return 2;
			case "TIC":
				return 3;
			case "TOE":
				return 1;
			case "TORCHLIGHT":
				return 4;
			case "TOXIC WASTE":
				return 5;
			case "VENOM FANG":
				return 3;
			case "ZAP":
				return 2;
		}
		return -1;
	}
	private int getMoveType(String move)
	{
		switch(move)
		{
			case "APPEARIFY":
			case "GLYPH":
			case "SENDIFY":
			case "SHRINK":
			case "SIDESTEP":
			case "STRETCH":
			case "TAC":
			case "TIC":
			case "TOE":
				return NORMAL;
			case "BUG SPRAY":
			case "COUNTDOWN":
			case "TOXIC WASTE":
			case "VENOM FANG":
				return POISON;
			case "BEDROCK":
			case "CAVE IN":
			case "EARTHQUAKE":
				return ROCK;
			case "BOO":
			case "FREAK OUT":
			case "LAST WORD":
			case "SPECTRE":
				return GHOST;
			case "BOOM":
			case "CANDLE":
			case "FIERY SOUL":
			case "FLAME SPEAR":
			case "TORCHLIGHT":
				return FIRE;
			case "FOUNTAIN":
			case "HEAVY RAIN":
			case "SPLASH":
				return WATER;
			case "FINALE":
			case "GRASS BLADE":
			case "IVY SPIKES":
			case "TANGLE":
				return GRASS;
			case "BATTERY POWER":
			case "DOUBLE ZAP":
			case "HIGH VOLTAGE":
			case "SHOCK":
			case "ZAP":
				return ELECTR;
			case "DARK PORTAL":
			case "HOLLOW GAZE":
			case "VOID":
				return DARK;
		}
		return -1;
	}
	private JButton getButton(final JOptionPane optionPane, String text, ImageIcon icon ) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    button.setText(text);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(text.toUpperCase());
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
	private boolean v1(String i)
	{
		if(i.length() > 0)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("0123456789".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
}
