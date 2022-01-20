package modules;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import start.BombEdgework;

public class OnlyConnect 
{
	private final String[][] languages =
		{
				{"ABCDEFGHIJKLMNOPQRSTUVXYZ", "C CEDILLA", "E UMLAUT"},
				{"ABCDEFGHIJKLMNOPQRSTUVWXYZ", "A GRAVE", "C CEDILLA", "E ACUTE", "E GRAVE", "I ACUTE", "I UMLAUT", "O ACUTE", "O GRAVE", "U ACUTE", "U UMLAUT"},
				{"ABCDEFGHIJKLMNOPRSTUVZ", "C ACUTE", "C HACEK", "D SLASH", "S HACEK", "Z HACEK"},
				{"ABCDEFGHIJKLMNOPQRSTUVWXYZ", "A ACUTE", "C HACEK", "D HACEK", "E ACUTE", "E HACEK", "I ACUTE", "N HACEK", "O ACUTE", "R HACEK", "S HACEK", "T HACEK", "U ACUTE", "U RING", "Y ACUTE", "Z HACEK"},
				{"ABCDEFGHIJKLMNOPQRSTUVWXYZ", "A RING", "AE", "O SLASH"},
				{"ABCDEFGHIJKLMNOPRSTUVZ", "C CIRCUMFLEX", "G CIRCUMFLEX", "H CIRCUMFLEX", "J CIRCUMFLEX", "S CIRCUMFLEX", "U BREVE"},
				{"ABCDEFGHIJKLMNOPQRSTUVZ", "A UMLAUT", "O UMLAUT", "O TILDE", "S HACEK", "U UMLAUT", "Z HACEK"},
				{"ADEGHIJKLMNOPRSTUVY", "A UMLAUT", "O UMLAUT"},
				{"ABCDEFGHIJKLMNOPQRSTUVWXYZ", "A GRAVE", "A CIRCUMFLEX", "A UMLAUT", "AE", "C CEDILLA", "E ACUTE", "E GRAVE", "E CIRCUMFLEX", "E UMLAUT", "I CIRCUMFLEX", "I UMLAUT", "O CIRCUMFLEX", "O UMLAUT", "OE", "U GRAVE", "U CIRCUMFLEX", "U UMLAUT", "Y UMLAUT"},
				{"ABCDEFGHIJKLMNOPQRSTUVWXYZ", "A UMLAUT", "O UMLAUT", "ESZETT", "U UMLAUT"},
				{"ABCDEFGHIJKLMNOPQRSTUVWXYZ", "A ACUTE", "E ACUTE", "I ACUTE", "O ACUTE", "O UMLAUT", "O DOUBLE ACUTE", "U ACUTE", "U UMLAUT", "U DOUBLE ACUTE"},
				{"ABDEFGHIJKLMNOPRSTUVXY", "A ACUTE", "AE", "ETH", "E ACUTE", "I ACUTE", "O ACUTE", "O UMLAUT", "THORN", "U ACUTE", "Y ACUTE"},
				{"ABCDEFGHIJKLMNOPRSTUVZ", "A MACRON", "C HACEK", "E MACRON", "G COMMA", "I MACRON", "K COMMA", "L COMMA", "N COMMA", "S HACEK", "U MACRON", "Z HACEK"},
				{"ABCDEFGHIJKLMNOPRSTUVYZ", "A OGONEK", "C HACEK", "E DOT", "E OGONEK", "I OGONEK", "S HACEK", "U MACRON", "U OGONEK", "Z HACEK"},
				{"ABCDEFGHIJKLMNOPRSTUWYZ", "A OGONEK", "C ACUTE", "E OGONEK", "L SLASH", "N ACUTE", "O ACUTE", "S ACUTE", "Z ACUTE", "Z DOT"},
				{"ABCDEFGHIJLMNOPQRSTUVXZ", "A ACUTE", "A GRAVE", "A CIRCUMFLEX", "A TILDE", "C CEDILLA", "E ACUTE", "E CIRCUMFLEX", "I ACUTE", "O ACUTE", "O CIRCUMFLEX", "O TILDE", "U ACUTE", "U UMLAUT"},
				{"ABCDEFGHIJKLMNOPQRSTUVWXYZ", "A CIRCUMFLEX", "A BREVE", "I CIRCUMFLEX", "S COMMA", "T COMMA"},
				{"ABCDEFGHIJKLMNOPQRSTUVWXYZ", "A ACUTE", "E ACUTE", "I ACUTE", "N TILDE", "O ACUTE", "U ACUTE", "U UMLAUT"},
				{"ABCDEFGHIJKLMNOPQRSTUVWXYZ", "A UMLAUT", "A RING", "O UMLAUT"},
				{"ABCDEFGHIJKLMNOPRSTUVYZ", "C CEDILLA", "G BREVE", "DOTLESS I", "O UMLAUT", "S CEDILLA", "U UMLAUT"},
				{"ABCDEFGHIJLMNOPRSTUWY", "W CIRCUMFLEX", "Y CIRCUMFLEX"}
		};
	private final BombEdgework ew;
	private final double r;
	public OnlyConnect(BombEdgework e, double resizer)
	{
		ew = e;
		r = resizer;
	}
	public String run()
	{
		//Round 1
		String input = JOptionPane.showInputDialog("TR - Two Reeds\nL - Lion\nTF - Twisted Flax\nHV - Horned Viper\nW - Water\nEH - Eye of Horus\nEnter the 6 symbols in\nreading order (spaces):").toUpperCase();
		boolean v = v1(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("TR - Two Reeds\nL - Lion\nTF - Twisted Flax\nHV - Horned Viper\nW - Water\nEH - Eye of Horus\nEnter the 6 symbols in\nreading order (spaces):").toUpperCase();
			v = v1(input);
		}
		String[] symbols = input.split(" ");
		input = JOptionPane.showInputDialog("Enter the team name:").toUpperCase();
		input = Round1(symbols, input.toUpperCase());
		while(input.length() == 0)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("TR - Two Reeds\nL - Lion\nTF - Twisted Flax\nHV - Horned Viper\nW - Water\nEH - Eye of Horus\nEnter the 6 symbols in\nreading order (spaces):").toUpperCase();
			v = v1(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("TR - Two Reeds\nL - Lion\nTF - Twisted Flax\nHV - Horned Viper\nW - Water\nEH - Eye of Horus\nEnter the 6 symbols in\nreading order (spaces):").toUpperCase();
				v = v1(input);
			}
			symbols = input.split(" ");
			input = JOptionPane.showInputDialog("Enter the team name:").toUpperCase();
			input = Round1(symbols, input.toUpperCase());
		}
		JOptionPane.showMessageDialog(null, "Press the " + input);
		String[] pos = {"TL", "TM", "TR", "BL", "BM", "BR"};
		String souv = "";
		for(int aa = 0; aa < 6; aa++)
		{
			switch(symbols[aa])
			{
				case "TR":
					souv = souv + "" + pos[aa] + ": TWO REEDS\n";
					break;
				case "L":
					souv = souv + "" + pos[aa] + ": LION\n";
					break;
				case "TF":
					souv = souv + "" + pos[aa] + ": TWISTED FLAX\n";
					break;
				case "HV":
					souv = souv + "" + pos[aa] + ": HORNED VIPER\n";
					break;
				case "W":
					souv = souv + "" + pos[aa] + ": WATER\n";
					break;
				case "EH":
					souv = souv + "" + pos[aa] + ": EYE OF HORUS\n";
					break;
			}
		}
		//Round 2
		ImageIcon i = new ImageIcon("img/OnlyConnectLetters.jpg");
		Image image = i.getImage();
		image = image.getScaledInstance((int)(i.getIconWidth() / r), (int)(i.getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
		i = new ImageIcon(image);
		JLabel l = new JLabel();
		l.setIcon(i);
		JFrame f = new JFrame();
		f.setLayout(new BorderLayout());
		f.add(l, BorderLayout.CENTER);
		f.pack();
		f.setVisible(true);
		String[] characters = new String[9];
		for(int aa = 0; aa < 9; aa++)
		{
			characters[aa] = JOptionPane.showInputDialog("Enter character #" + (aa + 1) + ":").toUpperCase();
			v = v2(characters[aa]);
			while(!(v))
			{
				f.setVisible(false);
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				f.setVisible(true);
				characters[aa] = JOptionPane.showInputDialog("Enter character #" + (aa + 1) + ":").toUpperCase();
				v = v2(characters[aa]);
			}
		}
		characters = Round2(characters);
		while(characters == null)
		{
			f.setVisible(false);
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			f.setVisible(true);
			characters = new String[9];
			for(int aa = 0; aa < 9; aa++)
			{
				characters[aa] = JOptionPane.showInputDialog("Enter character #" + (aa + 1) + ":").toUpperCase();
				v = v2(characters[aa]);
				while(!(v))
				{
					f.setVisible(false);
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					f.setVisible(true);
					characters[aa] = JOptionPane.showInputDialog("Enter character #" + (aa + 1) + ":").toUpperCase();
					v = v2(characters[aa]);
				}
			}
			characters = Round2(characters);
		}
		JOptionPane.showMessageDialog(null, "Select these characters:\n" + characters[0] + ", " + characters[1] + ", " + characters[2] + "\n" + characters[3] + ", " + characters[4] + ", " + characters[5] + "\n" + characters[6] + ", " + characters[7] + ", " + characters[8]);
		f.setVisible(false);
		return souv;
	}
	private String Round1(String[] symbols, String team)
	{
		int[] scores = {0, 0, 0, 0, 0, 0}, totals = {0, 0, 0, 0};
		String[] order = {"TR", "L", "TF", "HV", "W", "EH"}, ports = {"PS/2", "PARALLEL", "RJ-45", "RCA", "SERIAL", "DVI-D"};
		String alpha = "ZABCDEFGHIJKLMNOPQRSTUVWXY";
		for(int aa = 0; aa < 6; aa++)
		{
			if(symbols[aa].equals(order[aa]))
				scores[aa]++;
			char c = ew.getSNCHAR(aa);
			if(alpha.indexOf(c) < 0)
				c = alpha.charAt("0123456789".indexOf(c));
			if(team.indexOf(c) >= 0)
				scores[aa]++;
			if(ew.findPort(ports[aa]) > 0)
				scores[aa]++;
			totals[scores[aa]]++;
		}
		for(int aa = 0; aa < 4; aa++)
		{
			if(totals[aa] == 1)
			{
				for(int bb = 0; bb < 6; bb++)
				{
					if(scores[bb] == aa)
					{
						switch(bb)
						{
							case 0:
								return "Two Reeds";
							case 1:
								return "Lion";
							case 2:
								return "Twisted Flax";
							case 3:
								return "Horned Viper";
							case 4:
								return "Water";
							case 5:
								return "Eye of Horus";
						}
						break;
					}
				}
				break;
			}
		}
		return "";
	}
	private String[] Round2(String[] characters)
	{
		for(int aa = 0; aa < characters.length - 2; aa++)
		{
			for(int bb = aa + 1; bb < characters.length - 1; bb++)
			{
				for(int cc = bb + 1; cc < characters.length; cc++)
				{
					if(isPossibleCombo(new String[] {characters[aa], characters[bb], characters[cc]}))
					{
						if(characters.length == 3)
							return characters;
						else
						{
							String[] temp = new String[characters.length - 3];
							int items = 0;
							for(int dd = 0; dd < characters.length; dd++)
							{
								if(dd != aa && dd != bb && dd != cc)
								{
									temp[items] = characters[dd].toUpperCase();
									items++;
								}
							}
							String[] conv = Round2(temp);
							if(conv != null)
							{
								String[] solution = new String[characters.length];
								solution[0] = characters[aa].toUpperCase();
								solution[1] = characters[bb].toUpperCase();
								solution[2] = characters[cc].toUpperCase();
								for(int dd = 3; dd < characters.length; dd++)
									solution[dd] = conv[dd - 3].toUpperCase();
								return solution;
							}
						}
					}
				}
			}
		}
		return null;
	}
	private boolean isPossibleCombo(String[] characters)
	{
		for(int aa = 0; aa < languages.length; aa++)
		{
			boolean[] b = {false, false, false};
			for(int bb = 0; bb < 3; bb++)
			{
				if(characters[bb].length() == 1)
				{
					if(languages[aa][0].contains(characters[bb]))
						b[bb] = true;
				}
				else
				{
					for(int cc = 1; cc < languages[aa].length; cc++)
					{
						if(languages[aa][cc].equals(characters[bb]))
						{
							b[bb] = true;
							break;
						}
					}
				}
			}
			if(b[0] && b[1] && b[2])
				return true;
		}
		return false;
	}
	private boolean v1(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 6)
		{
			boolean[] b = {false, false, false, false, false, false};
			for(int aa = 0; aa < 6; aa++)
			{
				switch(conv[aa])
				{
					case "TR":
						b[0] = true;
						break;
					case "L":
						b[1] = true;
						break;
					case "TF":
						b[2] = true;
						break;
					case "HV":
						b[3] = true;
						break;
					case "W":
						b[4] = true;
						break;
					case "EH":
						b[5] = true;
						break;
					default:
						return false;
				}
			}
			return (b[0] && b[1] && b[2] && b[3] && b[4] && b[5]);
		}
		return false;
	}
	private boolean v2(String i)
	{
		if(i.length() == 1)
		{
			if("ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(i) >= 0)
				return true;
		}
		else if(i.length() > 1)
		{
			String[] list = {
					"A ACUTE", "C ACUTE", "E ACUTE", "I ACUTE", "N ACUTE", "O ACUTE", "S ACUTE", "U ACUTE", "Y ACUTE", "Z ACUTE",
					"O DOUBLE ACUTE", "U DOUBLE ACUTE",
					"A GRAVE", "E GRAVE", "O GRAVE", "U GRAVE",
					"A CIRCUMFLEX", "C CIRCUMFLEX", "E CIRCUMFLEX", "G CIRCUMFLEX", "H CIRCUMFLEX", "I CIRCUMFLEX", "J CIRCUMFLEX", "O CIRCUMFLEX", "S CIRCUMFLEX", "U CIRCUMFLEX", "W CIRCUMFLEX", "Y CIRCUMFLEX",
					"A UMLAUT", "E UMLAUT", "I UMLAUT", "O UMLAUT", "U UMLAUT", "Y UMLAUT",
					"C HACEK", "D HACEK", "E HACEK", "N HACEK", "R HACEK", "S HACEK", "T HACEK", "Z HACEK",
					"C CEDILLA", "S CEDILLA",
					"G COMMA", "K COMMA", "L COMMA", "N COMMA", "S COMMA", "T COMMA", 
					"A RING", "U RING",
					"A BREVE", "G BREVE", "U BREVE",
					"A TILDE", "N TILDE", "O TILDE",
					"A MACRON", "E MACRON", "I MACRON", "U MACRON",
					"A OGONEK", "E OGONEK", "I OGONEK", "U OGONEK",
					"E DOT", "Z DOT",
					"D SLASH", "L SLASH", "O SLASH",
					"AE", "OE",
					"ETH",
					"DOTLESS I",
					"ESZETT",
					"THORN"
			};
			for(int aa = 0; aa < list.length; aa++)
			{
				if(list[aa].equals(i))
					return true;
			}
		}
		return false;
	}
}

