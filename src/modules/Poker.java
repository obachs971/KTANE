package modules;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import start.BombEdgework;

public class Poker 
{
	private final BombEdgework ew;
	public Poker(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		String card = JOptionPane.showInputDialog("AS, KH, 5D, 2C\nEnter the card:").toUpperCase();
		boolean v = v1(card);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			card = JOptionPane.showInputDialog("AS, KH, 5D, 2C\nEnter the card:").toUpperCase();
			v = v1(card);
		}
		step1(card);
		String[] responses = {"Terrible Play!", "Awful Play!", "Really?", "Really, really?", "Sure about that?", "Are you sure?"};
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		JButton[] jButton = new JButton[responses.length];
		optionPane.setLayout(new GridLayout(3, 2));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int aa = 0; aa < responses.length; aa++)
		{
			jButton[aa] = getButton(optionPane, (aa + ""), responses[aa]);
			optionPane.add(jButton[aa]);
		}
		JDialog dialog = optionPane.createDialog(frame, "");
		dialog.setTitle("Select the response:");
		dialog.setVisible(true);
		int response = Integer.parseInt(((String)optionPane.getValue()));
		step2(card, response);
		String input = JOptionPane.showInputDialog("S, H, C, D\nEnter the bet and\nthe 4 suits (spaces):").toUpperCase();
		v = v2(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("S, H, C, D\nEnter the bet and\nthe 4 suits (spaces):").toUpperCase();
			v = v2(input);
		}
		step3(card, response, input.split(" "));
	}
	private void step1(String card)
	{
		String[] cards = new String[5];
		cards[0] = card.toUpperCase();
		switch(card)
		{
			case "AS":
				if(ew.BAT() >= 3)
				{
					cards[1] = "3S";
					if(ew.findLit("FRK") || ew.findLit("BOB"))
					{
						cards[2] = "5S";
						if(ew.getSNSUM() % 2 == 0)
						{
							cards[3] = "2S";
							if(ew.findPort("RJ-45") > 0)
								cards[4] = "4S";
							else
								cards[4] = "8S";
						}
						else
						{
							cards[3] = "3D";
							if(ew.findPort("PS/2") > 0)
								cards[4] = "3C";
							else
								cards[4] = "AC";
						}
					}
					else
					{
						cards[2] = "9H";
						if(ew.BD() > ew.BA())
						{
							cards[3] = "QC";
							if(ew.numCharsInSN("AEIOU") > 0)
								cards[4] = "JD";
							else
								cards[4] = "3D";
						}
						else
						{
							cards[3] = "AD";
							if(ew.getSNDIG(ew.numSNDIGS() - 1) % 2 == 0)
								cards[4] = "AC";
							else
								cards[4] = "6H";
						}
					}
				}
				else
				{
					cards[1] = "JD";
					if(ew.numCharsInSN("AEIOU") > 0)
					{
						cards[2] = "JC";
						if(ew.findUnlit("CAR"))
						{
							cards[3] = "JS";
							if(ew.findPort("DVI-D") > 0)
								cards[4] = "JH";
							else
								cards[4] = "AH";
						}
						else
						{
							cards[3] = "AH";
							if(ew.findPort("PARALLEL") > 0)
								cards[4] = "2C";
							else
								cards[4] = "AC";
						}
					}
					else
					{
						cards[2] = "4C";
						if(ew.findPort("SERIAL") > 0)
						{
							cards[3] = "6D";
							if(ew.findUnlit("SND") || ew.findUnlit("TRN"))
								cards[4] = "3C";
							else
								cards[4] = "4S";
						}
						else
						{
							cards[3] = "QD";
							if(ew.findLit("SIG") || ew.findLit("FRQ"))
								cards[4] = "1S";
							else
								cards[4] = "AC";
						}
					}
				}
				break;
			case "KH":
				if(ew.getSNSUM() % 2 == 1)
				{
					cards[1] = "4C";
					if(ew.BAT() > 0)
					{
						cards[2] = "4H";
						if(ew.findLit("IND") || ew.findLit("MSA") || ew.findLit("TRN"))
						{
							cards[3] = "4S";
							if(ew.findPort("RCA") > 0)
								cards[4] = "4D";
							else
								cards[4] = "KC";
						}
						else
						{
							cards[3] = "KS";
							if(ew.findPort("RJ-45") > 0 && ew.findPort("SERIAL") > 0)
								cards[4] = "3C";
							else
								cards[4] = "KC";
						}
					}
					else
					{
						cards[2] = "AH";
						if(ew.findPort("PS/2") > 0 || ew.findPort("DVI-D") > 0)
						{
							cards[3] = "2C";
							if(ew.findLit("SND"))
								cards[4] = "3S";
							else
								cards[4] = "4D";
						}
						else
						{
							cards[3] = "QS";
							if(ew.findUnlit("TRN") || ew.findUnlit("FRK"))
								cards[4] = "3C";
							else
								cards[4] = "5C";
						}
					}
				}
				else
				{
					cards[1] = "3D";
					if(ew.findPort("PARALLEL") > 0)
					{
						cards[2] = "3S";
						if(ew.BA() <= 3)
						{
							cards[3] = "2D";
							if(ew.findUnlit("MSA") || ew.findLit("NSA"))
								cards[4] = "9C";
							else
								cards[4] = "3H";
						}
						else
						{
							cards[3] = "7S";
							if(ew.findLit("FRQ"))
								cards[4] = "2C";
							else
								cards[4] = "7D";
						}
					}
					else
					{
						cards[2] = "4H";
						if(ew.findUnlit("BOB") || ew.findUnlit("FRQ"))
						{
							cards[3] = "1C";
							if(ew.BA() > ew.BD())
								cards[4] = "KC";
							else
								cards[4] = "QS";
						}
						else
						{
							cards[3] = "2H";
							if(ew.BAT() <= 5)
								cards[4] = "AH";
							else
								cards[4] = "9C";
						}
					}
				}
				break;
			case "5D":
				if(ew.BA() > ew.BD())
				{
					cards[1] = "3C";
					if(ew.numCharsInSN("AEIOU") > 0)
					{
						cards[2] = "2S";
						if(ew.numPorts() > 1)
						{
							cards[3] = "AH";
							if(ew.findUnlit("CLR") || ew.findLit("CAR"))
								cards[4] = "4D";
							else
								cards[4] = "6D";
						}
						else
						{
							cards[3] = "6H";
							if(ew.findLit("MSA") || ew.findUnlit("NSA"))
								cards[4] = "4S";
							else
								cards[4] = "3D";
						}
					}
					else
					{
						cards[2] = "9H";
						if(ew.findPort("PS/2") > 0 || ew.findPort("RJ-45") > 0)
						{
							cards[3] = "1S";
							if(ew.numUnlit() > 0)
								cards[4] = "JC";
							else
								cards[4] = "9S";
						}
						else
						{
							cards[3] = "JS";
							if(ew.findUnlit("CLR"))
								cards[4] = "QH";
							else
								cards[4] = "5H";
						}
					}
				}
				else
				{
					cards[1] = "9D";
					if(ew.getSNDIG(ew.numSNDIGS() - 1) % 2 == 1)
					{
						cards[2] = "4D";
						if(ew.findLit("BOB") || ew.findUnlit("FRQ") || ew.findUnlit("SIG"))
						{
							cards[3] = "KD";
							if(ew.numPorts() > 0)
								cards[4] = "KH";
							else
								cards[4] = "QD";
						}
						else
						{
							cards[3] = "AD";
							if(ew.findPort("PARALLEL") > 0)
								cards[4] = "2D";
							else
								cards[4] = "6S";
						}
					}
					else
					{
						cards[2] = "6D";
						if(ew.numLit() > 0)
						{
							cards[3] = "7D";
							if(ew.numPorts() < 3)
								cards[4] = "8D";
							else
								cards[4] = "8H";
						}
						else
						{
							cards[3] = "5C";
							if(ew.findPort("RCA") > 0 && ew.findPort("DVI-D") > 0)
								cards[4] = "5S";
							else
								cards[4] = "6S";
						}
					}
				}
				break;
			case "2C":
				if(ew.findLit("TRN") || ew.findLit("BOB") || ew.findLit("IND"))
				{
					cards[1] = "6C";
					if(ew.BAT() <= 5)
					{
						cards[2] = "1C";
						if(ew.findPort("DVI-D") > 0 || ew.findPort("RCA") > 0)
						{
							cards[3] = "JH";
							if("AEIOU".indexOf(ew.getSNLET(ew.numSNLETS() - 1)) < 0)
								cards[4] = "QC";
							else
								cards[4] = "2D";
						}
						else
						{
							cards[3] = "JC";
							if(ew.getSNDIG(ew.numSNDIGS() - 1) % 2 == 1)
								cards[4] = "4C";
							else
								cards[4] = "KC";
						}
					}
					else
					{
						cards[2] = "AS";
						if(ew.getSNSUM() > 12)
						{
							cards[3] = "2D";
							if(ew.findPort("PS/2") > 0 && ew.findPort("PARALLEL") > 0)
								cards[4] = "6D";
							else
								cards[4] = "2H";
						}
						else
						{
							cards[3] = "6H";
							if(ew.numPorts() <= 3)
								cards[4] = "AH";
							else
								cards[4] = "7S";
						}
					}
				}
				else
				{
					cards[1] = "3H";
					if(ew.numSNLETS() % 2 == 0)
					{
						cards[2] = "4H";
						if(ew.findPort("PARALLEL") > 0 && ew.findPort("SERIAL") > 0)
						{
							cards[3] = "5S";
							if(ew.BA() > ew.BD())
								cards[4] = "6C";
							else
								cards[4] = "5D";
						}
						else
						{
							cards[3] = "6H";
							if(ew.BD() > ew.BA())
								cards[4] = "5H";
							else
								cards[4] = "KH";
						}
					}
					else
					{
						cards[2] = "KH";
						if(ew.findPort("RJ-45") > 0)
						{
							cards[3] = "KC";
							if(ew.BD() > 2)
								cards[4] = "3S";
							else
								cards[4] = "KS";
						}
						else
						{
							cards[3] = "4D";
							if(ew.BAT() > 2)
								cards[4] = "JC";
							else
								cards[4] = "2D";
						}
					}
				}
				break;
		}
		int[] vals = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		for(int aa = 0; aa < cards.length; aa++)
			vals["A234567891JQK".indexOf(cards[aa].charAt(0))]++;
		if(hasNumKind(vals, 4) || (straight(vals) && flush(cards)))
			JOptionPane.showMessageDialog(null, "Press ALL-IN");
		else if(flush(cards) || (hasNumKind(vals, 3) && hasNumKind(vals, 2)))
			JOptionPane.showMessageDialog(null, "Press MAX RAISE");
		else if(hasNumKind(vals, 3) || straight(vals))
			JOptionPane.showMessageDialog(null, "Press MIN RAISE");
		else if(hasNumKind(vals, 2))
			JOptionPane.showMessageDialog(null, "Press CHECK");
		else
			JOptionPane.showMessageDialog(null, "Press FOLD");
	}
	private void step2(String card, int response)
	{
		String[][] table =
			{
					{"Truth", "Truth", "Truth", "Bluff"},
					{"Bluff", "Truth", "Truth", "Bluff"},
					{"Truth", "Bluff", "Bluff", "Bluff"},
					{"Bluff", "Bluff", "Truth", "Bluff"},
					{"Truth", "Bluff", "Bluff", "Truth"},
					{"Bluff", "Truth", "Truth", "Truth"}
			};
		JOptionPane.showMessageDialog(null, "Press " + table[response]["AK52".indexOf(card.charAt(0))]);
	}
	private void step3(String card, int response, String[] info)
	{
		String s = info[1];
		switch(info[0])
		{
			case "25":
				if("HD".indexOf(s.charAt(0)) >= 0 && ew.findLit("BOB"))
					JOptionPane.showMessageDialog(null, "Press the 4th card");
				else if(response == 1 && card.equals("AS"))
					JOptionPane.showMessageDialog(null, "Press the 1st card");
				else if(ew.findUnlit("FRQ") && "SC".indexOf(s.charAt(3)) >= 0)
					JOptionPane.showMessageDialog(null, "Press the 2nd card");
				else if(s.contains("D") && (response == 2 || response == 3))
					JOptionPane.showMessageDialog(null, "Press the 3rd card");
				else if(s.charAt(3) == 'S' && ew.BAT() > 4)
					JOptionPane.showMessageDialog(null, "Press the 3rd card");
				else if(s.charAt(2) == 'D' && s.charAt(1) != 'C')
					JOptionPane.showMessageDialog(null, "Press the 2nd card");
				else if(response == 5 && card.equals("2C"))
					JOptionPane.showMessageDialog(null, "Press the 1st card");
				else if(card.equals("5D"))
					JOptionPane.showMessageDialog(null, "Press the 4th card");
				else if(s.charAt(1) == 'C' && ew.findPort("RJ-45") == 0)
					JOptionPane.showMessageDialog(null, "Press the 2nd card");
				else
					JOptionPane.showMessageDialog(null, "Press the 1st card");
				break;
			case "50":
				if(response == 4 && s.charAt(3) == 'H')
					JOptionPane.showMessageDialog(null, "Press the 1st card");
				else if(s.indexOf("C") < 0 && card.equals("2C"))
					JOptionPane.showMessageDialog(null, "Press the 3rd card");
				else if(s.contains("H") && s.indexOf("D") < 0 && s.substring(s.indexOf("H") + 1).contains("S"))
					JOptionPane.showMessageDialog(null, "Press the 4th card");
				else if(s.charAt(0) == 'H' && !(card.equals("KH")))
					JOptionPane.showMessageDialog(null, "Press the 2nd card");
				else if(response == 3 && s.substring(0, 2).contains("H"))
					JOptionPane.showMessageDialog(null, "Press the 4th card");
				else if(card.equals("5D") && ew.findPort("PARALLEL") > 0)
					JOptionPane.showMessageDialog(null, "Press the 1st card");
				else if(ew.findLit("TRN") && (s.contains("S") || s.contains("C")))
					JOptionPane.showMessageDialog(null, "Press the 2nd card");
				else if(response == 0)
					JOptionPane.showMessageDialog(null, "Press the 3rd card");
				else if(ew.getSNSUM() < 10)
					JOptionPane.showMessageDialog(null, "Press the 1st card");
				else
					JOptionPane.showMessageDialog(null, "Press the 3rd card");
				break;
			case "100":
				if(response == 3)
					JOptionPane.showMessageDialog(null, "Press the 2nd card");
				else if(response == 2)
					JOptionPane.showMessageDialog(null, "Press the 4th card");
				else if(ew.BD() == 0 && card.equals("AS"))
					JOptionPane.showMessageDialog(null, "Press the 1st card");
				else if(prime(ew.getSNSUM()) && s.contains("H"))
					JOptionPane.showMessageDialog(null, "Press the 4th card");
				else if(s.contains("C") && s.contains("S") && response == 4)
					JOptionPane.showMessageDialog(null, "Press the 3rd card");
				else if(s.contains("CS") || s.contains("SC"))
					JOptionPane.showMessageDialog(null, "Press the 2nd card");
				else if(ew.findUnlit("MSA"))
					JOptionPane.showMessageDialog(null, "Press the 1st card");
				else if(s.contains("D"))
					JOptionPane.showMessageDialog(null, "Press the 3rd card");
				else if(response == 1)
					JOptionPane.showMessageDialog(null, "Press the 4th card");
				else
					JOptionPane.showMessageDialog(null, "Press the 2nd card");
				break;
			default:
				if(s.replace("S", "").replace("H", "").replace("D", "").length() > 1)
					JOptionPane.showMessageDialog(null, "Press the 3rd card");
				else if(ew.numCharsInSN("AEIOU") > 0 && s.contains("S"))
					JOptionPane.showMessageDialog(null, "Press the 2nd card");
				else if(ew.numPorts() == 0 && s.contains("H"))
					JOptionPane.showMessageDialog(null, "Press the 1st card");
				else if(s.indexOf("H") < 0 && s.indexOf("D") < 0)
					JOptionPane.showMessageDialog(null, "Press the 4th card");
				else if(response == 5)
					JOptionPane.showMessageDialog(null, "Press the 4th card");
				else if(ew.numLit() == 0 && "HD".indexOf(s.charAt(0)) >= 0)
					JOptionPane.showMessageDialog(null, "Press the 3rd card");
				else if(ew.numUnlit() >= 1 && s.charAt(1) == 'C')
					JOptionPane.showMessageDialog(null, "Press the 2nd card");
				else if(response == 2 && s.indexOf("S") < 0 && s.indexOf("C") < 0)
					JOptionPane.showMessageDialog(null, "Press the 1st card");
				else if(ew.BD() > 1)
					JOptionPane.showMessageDialog(null, "Press the 3rd card");
				else
					JOptionPane.showMessageDialog(null, "Press the 4th card");
				break;
		}
	}
	private boolean straight(int[] vals)
	{
		for(int aa = 4; aa < vals.length; aa++)
		{
			if(vals[aa - 4] > 0 && vals[aa - 3] > 0 && vals[aa - 2] > 0 && vals[aa - 1] > 0 && vals[aa] > 0)
				return true;
		}
		return false;
	}
	private boolean flush(String[] cards)
	{
		return (cards[0].charAt(1) == cards[1].charAt(1) && cards[0].charAt(1) == cards[2].charAt(1) && cards[0].charAt(1) == cards[3].charAt(1) && cards[0].charAt(1) == cards[4].charAt(1));
	}
	private boolean hasNumKind(int[] vals, int dup)
	{
		for(int aa = 0; aa < vals.length; aa++)
		{
			if(vals[aa] == dup)
				return true;
		}
		return false;
	}
	private boolean prime(int n)
	{
		if(n < 2)
			return false;
		for(int aa = 2; aa < n; aa++)
		{
			if(n % aa == 0)
				return false;
		}
		return true;
	}
	private JButton getButton(final JOptionPane optionPane, String value, String text) {
	    final JButton button = new JButton();
	    button.setText(text);
	    button.setSize(50, 50);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(value);
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
	private boolean v1(String i)
	{
		switch(i)
		{
			case "AS":case "KH":case "5D":case "2C":
				return true;
			default:
				return false;
		}
	}
	private boolean v2(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 2)
		{
			switch(conv[0])
			{
				case "25":case "50":case "100":case "500":
					break;
				default:
					return false;
			}
			if(conv[1].length() == 4)
			{
				for(int aa = 0; aa < conv[1].length(); aa++)
				{
					if("SHCD".indexOf(conv[1].charAt(aa)) < 0)
						return false;
				}
				return true;
			}
		}
		return false;
	}
}
