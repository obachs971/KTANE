package modules;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import start.BombConfig;
import start.BombEdgework;

public class Mafia 
{
	private final String[] order =
		{
				"ROB","TIM","MARY","BRIANE","HUNTER","MACY","JOHN","WILL","LACY","CLAIRE",
				"KENNY","RICK","WALTER","BONNIE","LUKE","BILL","SARAH","LARRY","KATE","STACY",
				"DIANE","MAC","JIM","CLYDE","TOMMY","LENNY","MOLLY","BENNY","PHIL","BOB",
				"GARY","TED","KIM","NATE","CHER","RON","THOMAS","SAM","DUKE","JACK",
				"ED","RONNY","TERRY","CLAIRA","NICK","COB","ASH","DON","JERRY","SIMON"
		};
	private String sub;
	private final BombConfig cf;
	private final BombEdgework ew;
	public Mafia(BombConfig c, BombEdgework e)
	{
		cf = c;
		ew = e;
	}
	public String run()
	{
		ArrayList<String> people = new ArrayList<String>();
		ArrayList<String> peopleList = new ArrayList<String>();
		ArrayList<String> eliminated = new ArrayList<String>();
		ArrayList<String> orderList = new ArrayList<String>();
		for(String p : order)
			orderList.add(p.toUpperCase());
		Collections.sort(orderList);
		String[] pos = {"TL", "TR", "RT", "RB", "BR", "BL", "LB", "LT"};
		for(int aa = 0; aa < 8; aa++)
		{
			JDialog dialog = getDialog(orderList);
			dialog.setTitle("Select the " + pos[aa] + " person:");
			dialog.setVisible(true);
			people.add(sub.toUpperCase());
			peopleList.add(sub.toUpperCase());
			orderList.remove(sub);
		}
		int cur = ew.getSNSUM();
		for(int aa = 0; aa < ew.numSNLETS(); aa++)
			cur += "-ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(ew.getSNLET(aa));
		while(cur > 50)
			cur -= 50;
		cur--;
		while(!(people.contains(order[cur])))
			cur = (cur + 1) % 50;
		cur = people.indexOf(order[cur]);
		int offset = ew.getSNDIG(ew.numSNDIGS() - 1);
		if(ew.numInd() >= 2)
		{
			ArrayList<String> temp = new ArrayList<String>();
			for(int aa = 0; aa < 8; aa++)
				temp.add(people.get(7 - aa));
			people = temp;
			cur = 7 - cur;
		}
		System.out.println(people.toString());
		for(int aa = 0; aa < 7; aa++)
		{
			eliminated.add(people.get(cur).toUpperCase());
			people.remove(cur);
			System.out.println(eliminated.get(aa));
			cur = mod(cur + offset, people.size());
		}
		System.out.println(people.get(0));
		String godfather = getGodfather(people.get(0), peopleList, eliminated, eliminated.get(0), eliminated.get(6));
		JOptionPane.showMessageDialog(null, "Press this person: " + godfather);
		peopleList.remove(godfather);
		Collections.sort(peopleList);
		String souv = "";
		for(int aa = 0; aa < peopleList.size(); aa++)
			souv = souv + "PLAYER #" + (aa + 1) + ": " + peopleList.get(aa).toUpperCase() + "\n";
		return souv;
	}
	private String getGodfather(String person, ArrayList<String> people, ArrayList<String> RO, String FRP, String LRP)
	{
		switch(person)
		{
			case "ROB":
				if(ew.numCharsInSN("AEIOU") > 0)
					return people.get(mod(people.indexOf("ROB") + 1, 8));
				return person;
			case "TIM":
				if(JOptionPane.showConfirmDialog(null, "Are any of these modules present?\nFriendship\nOnly Connect\nBattleship\nMarble Tumble") == 0)
					return FRP;
				return person;
			case "MARY":
				if(people.contains("BOB") || people.contains("WALTER") || people.contains("CHER"))
				{
					if(people.get(0).equals("MARY"))
						return people.get(1);
					return people.get(0);
				}
				return person;
			case "BRIANE":
				if(ew.numTF() > 0 || ew.findLit("CAR"))
					return LRP;
				return person;
			case "HUNTER":
				if(ew.numPorts() > ew.BAT())
				{
					if(people.contains("RICK"))
						return "RICK";
					return RO.get(3);
				}
				return person;
			case "MACY":
				if(people.contains("TOMMY"))
					return "TOMMY";
				return person;
			case "JOHN":
				for(int aa = 0; aa < RO.size(); aa++)
				{
					if(RO.get(aa).startsWith("J"))
						return person;
				}
				return SSN(person, people);
			case "WILL":
				if((ew.findPort("PS/2") > 0 || ew.findPort("DVI-D") > 0) && ew.numCharsInSN("02468") > 0)
					return RO.get(4);
				return person;
			case "LACY":
				if(JOptionPane.showConfirmDialog(null, "Are any of these modules present?\nBoolean Venn Diagram\nBitwise Operations\nAny with Logic in its name") == 0)
					return SSN(person, people);
				return person;
			case "CLAIRE":
				if(cf.getNumberModules() < 20)
					return LRP;
				return person;
			case "KENNY":
				if(ew.numUnlit() == 0)
				{
					int num = mod(people.indexOf(FRP) + 1, 8);
					if(people.get(num).equals(person))
						num = mod(num + 1, 8);
					return people.get(num);
				}
				return person;
			case "RICK":
				if(ew.numEmpty() > 0)
					return people.get(mod(people.indexOf(person) - 1, 8));
				return person;
			case "WALTER":
				if(ew.numCharsInSN(person) > 0)
					return FRP;
				return person;
			case "BONNIE":
				for(int aa = 0; aa < RO.size(); aa++)
				{
					if(RO.get(aa).startsWith("B"))
					{
						int cur1 = mod(people.indexOf(person) + 1, 8);
						while(!(people.get(cur1).startsWith("B")))
							cur1 = mod(cur1 + 1, 8);
						return people.get(cur1);
					}
				}
				return person;
			case "LUKE":
				for(int aa = 0; aa < order.length; aa++)
				{
					if(RO.contains(order[aa]))
						return order[aa];
				}
				return person;
			case "BILL":
				if(ew.getSNDIG(ew.numSNDIGS() - 1) == 0 || ew.getSNDIG(ew.numSNDIGS() - 1) == 2 || ew.getSNDIG(ew.numSNDIGS() - 1) == 3 || ew.getSNDIG(ew.numSNDIGS() - 1) == 5 || ew.getSNDIG(ew.numSNDIGS() - 1) == 7)
				{
					for(int aa = order.length - 1; aa >= 0; aa--)
					{
						if(RO.contains(order[aa]))
							return order[aa];
					}
				}
				return person;
			case "SARAH":
				if(ew.findPort("HDMI") > 0 || ew.numCharsInSN("SH3") > 0)
					return LRP;
				return person;
			case "LARRY":
				if(JOptionPane.showConfirmDialog(null, "Any modules containing\nColor in their name?") != 0)
					return FRP;
				return person;
			case "KATE":
				if(ew.numCharsInSN("LOST") > 0 || JOptionPane.showConfirmDialog(null, "Is the module 'The Swan' present?") == 0)
				{
					if(people.contains("JOHN"))
						return "JOHN";
					return SSN(person, people);
				}
				return person;
			case "STACY":
				if(cf.getNumberModules() < cf.getStartingBombMinutes())
					return FRP;
				return person;
			case "DIANE":
				if(ew.findPort("VGA") > 0 || ew.findPort("USB") > 0 || JOptionPane.showConfirmDialog(null, "Is the module 'The Screw' present?") == 0)
					return LRP;
				return person;
			case "MAC":
				if(ew.findPorts(new String[] {"PARALLEL", "SERIAL"}) > 0)
					return RO.get(5);
				return person;
			case "JIM":
				if(JOptionPane.showConfirmDialog(null, "Are any of these modules present?\nChord Qualities\nRhythms\nAny with 'Piano Keys' in its name\nAny with 'Jukebox' in its name\nAny with 'Guitar Chords' in its name") == 0)
					return SSN(person, people);
				return person;
			case "CLYDE":
				if(people.contains("BONNIE"))
					return "BONNIE";
				return person;
			case "TOMMY":
				if(ew.BAT() == 0 && ew.numPorts() == 0)
					return RO.get(3);
				return person;
			case "LENNY":
				String lennySSN = SSN(person, people);
				if(lennySSN.length() != 3)
					return lennySSN;
				return person;
			case "MOLLY":
				if(JOptionPane.showConfirmDialog(null, "Are there any modules besides\nMafia that starts with 'M'?") == 0)
					return people.get(mod(people.indexOf(person) + 1, 8));
				return person;
			case "BENNY":
				if(!(FRP.equals("HUNTER") || FRP.equals("CHER") || FRP.equals("NICK")))
					return people.get(mod(people.indexOf(person) + 3, 8));
				return person;
			case "PHIL":
				return people.get(4);
			case "BOB":
				if(ew.findInd("BOB") || JOptionPane.showConfirmDialog(null, "Are any of these modules present?\nLaundry\nMorse-A-Maze\nBig Circle\nPainting\nDr. Doctor\nThe Code") == 0)
					return RO.get(2);
				return person;
			case "GARY":
				if(JOptionPane.showConfirmDialog(null, "Are any of these modules present?\nCheap Checkout\nIce Cream\nCooking") == 0)
					return LRP;
				return person;
			case "TED":
				if(JOptionPane.showConfirmDialog(null, "Are any of these modules present?\nBlack Hole\nThe Sun\nThe Moon\nLightspeed\nAstrology") == 0)
					return SSN(person, people);
				return person;
			case "KIM":
				if(getNumber(FRP) <= 25)
					return FRP;
				return person;
			case "NATE":
				if(ew.numLit() > ew.numUnlit())
					return people.get(mod(people.indexOf(person) + 1, 8));
				return person;
			case "CHER":
				if(cf.getNumberNeedies() == 0 && ew.numPorts() > 0)
					return LRP;
				return person;
			case "RON":
				if(ew.numIndWithChars(ew.getSN()) > 0)
					return SSN(person, people);
				return person;
			case "THOMAS":
				if(JOptionPane.showConfirmDialog(null, "Is there any module\nwith 'Maze' in its name?") == 1)
					return people.get(mod(people.indexOf(person) - 2, 8));
				return person;
			case "SAM":
				if(JOptionPane.showConfirmDialog(null, "Are any of these modules present?\nCreation\nThe Gamepad\nMinesweeper\nSkewed Slots") == 0)
					return LRP;
				return person;
			case "DUKE":
				if(getNumber(LRP) > 25)
					return LRP;
				return person;
			case "JACK":
				String JackSSN = SSN(person, people);
				if(JackSSN.length() == 4)
					return JackSSN;
				return person;
			case "ED":
				if(JOptionPane.showConfirmDialog(null, "Is the total number of the\nfollowing modules equal 1?\nGridlock\nHuman Resources\nLasers\ncontaining Double-Oh") == 0)
					return RO.get(1);
				return person;
			case "RONNY":
				if(ew.numPorts() >= 4 || JOptionPane.showConfirmDialog(null, "Are any of these modules present?\nWires\nThe Button\nKeypads\nSimon Says\nWho's on First\nMemory\nMorse Code\nComplicated Wires\nWire Sequence\nMaze\nPassword") == 1)
					return FRP;
				return person;
			case "TERRY":
				if(ew.BAT() >= 3)
					return RO.get(2);
				return person;
			case "CLAIRA":
				int num = 0;
				ArrayList<ArrayList<String>> ports = ew.getPorts();
				for(int aa = 0; aa < ports.size(); aa++)
				{
					if(ports.get(aa).contains("RJ-45") || ports.get(aa).contains("RCA") || ports.get(aa).contains("PS/2"))
						num++;
				}
				if(num >= 2)
					return SSN(person, people);
				return person;
			case "NICK":
				if(JOptionPane.showConfirmDialog(null, "Are any of these modules present?\nZoo\nNonogram\nMurder\nX01") == 1)
					return FRP;
				return person;
			case "COB":
				if(JOptionPane.showConfirmDialog(null, "Are there any duplicate modules?") == 0)
				{
					int cursor = mod(people.indexOf(person) + 1, 8);
					int best = cursor + 0;
					int length = people.get(cursor).length();
					for(int aa = 1; aa < 7; aa++)
					{
						if(people.get((cursor + aa) % 8).length() > length)
						{
							length = people.get((cursor + aa) % 8).length();
							best = (cursor + aa) % 8;
						}
					}
					return people.get(best);
				}
				return person;
			case "ASH":
				if(JOptionPane.showConfirmDialog(null, "Is there any Monsplode modules?") == 0)
					return LRP;
				return person;
			case "DON":
				return person;
			case "JERRY":
				if(JOptionPane.showConfirmDialog(null, "Are any of these modules present?\nThe Clock\nRubik's Clock\nThe Stopwatch\nTimezone\nThe Time Keeper") == 0)
					return people.get(mod(people.indexOf(person) - 1, 8));
				return person;
			default:
				if(JOptionPane.showConfirmDialog(null, "Is there a module with\n'Simon' in its name?") == 1)
					return SSN(person, people);
				return person;
		}
	}
	private String SSN(String p, ArrayList<String> pl)
	{
		int num = pl.indexOf(p);
		if(num % 2 == 0)
			num++;
		else
			num--;
		return pl.get(num);
	}
	private int mod(int n, int m)
	{
		while(n < 0)
			n += m;
		return (n % m);
	}
	private int getNumber(String p)
	{
		for(int aa = 0; aa < order.length; aa++)
		{
			if(order[aa].equals(p))
				return (aa + 1);
		}
		return 0;
	}
	private JDialog getDialog(ArrayList<String> arr)
	{
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		JButton[] jButton = new JButton[arr.size()];
		if(arr.size() % 5 == 0)
			optionPane.setLayout(new GridLayout(arr.size() / 5, 5));
		else
			optionPane.setLayout(new GridLayout((arr.size() / 5 + 1), 5));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int aa = 0; aa < arr.size(); aa++)
		{
			jButton[aa] = getButton(optionPane, arr.get(aa));
			optionPane.add(jButton[aa]);
		}
		return optionPane.createDialog(frame, "");
	}
	private JButton getButton(final JOptionPane optionPane, String text) {
	    final JButton button = new JButton();
	    button.setText(text);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(text.toUpperCase());
	        sub = text.toUpperCase();
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
}
