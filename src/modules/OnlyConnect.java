package modules;

import java.awt.BorderLayout;
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

public class OnlyConnect 
{
	private ArrayList<String> symbols;
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
		String[] pos = {"TL", "TM", "TR", "BL", "BM", "BR"};
		//Round 1
		getSymbols();
		String input = JOptionPane.showInputDialog("Enter the team name:").toUpperCase();
		input = Round1(input.toUpperCase());
		while(input.length() == 0)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			getSymbols();
			input = JOptionPane.showInputDialog("Enter the team name:").toUpperCase();
			input = Round1(input.toUpperCase());
		}
		JOptionPane.showMessageDialog(null, "Press the " + input);
		String souv = "";
		for(int aa = 0; aa < 6; aa++)
			souv = souv + pos[aa] + ": " + symbols.get(aa).toUpperCase() + "\n";
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
			boolean v = v2(characters[aa]);
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
				boolean v = v2(characters[aa]);
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
	private void getSymbols()
	{
		symbols = new ArrayList<String>();
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		optionPane.setLayout(new GridLayout(6, 1));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		String[] symbolList = {"Two Reeds", "Lion", "Twisted Flax", "Horned Viper", "Water", "Eye of Horus"};
		String[] pos = {"TL", "TM", "TR", "BL", "BM", "BR"};
		ImageIcon[] icon = new ImageIcon[symbolList.length];
		JButton[] jButton = new JButton[symbolList.length];
		for(int aa = 0; aa < symbolList.length; aa++)
		{
			icon[aa] = new ImageIcon("img/OnlyConnect" + symbolList[aa].replace(" ", "") + ".jpg");
			Image image = icon[aa].getImage();
			image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			icon[aa] = new ImageIcon(image);
			jButton[aa] = getButton(optionPane, symbolList[aa], icon[aa]);
			optionPane.add(jButton[aa]);
		}
		for(int aa = 0; aa < 5; aa++)
		{
			JDialog dialog = optionPane.createDialog(frame, "");
			dialog.setTitle("Select the " + pos[aa] + " symbol:");
			dialog.setVisible(true);
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
			if(jButton.length == 1)
				symbols.add(jButton[0].getText().toUpperCase());
		}
	}
	private String Round1(String team)
	{
		int[] scores = {0, 0, 0, 0, 0, 0}, totals = {0, 0, 0, 0};
		String[] order = {"TWO REEDS", "LION", "TWISTED FLAX", "HORNED VIPER", "WATER", "EYE OF HORUS"}, ports = {"PS/2", "PARALLEL", "RJ-45", "RCA", "SERIAL", "DVI-D"};
		String alpha = "ZABCDEFGHIJKLMNOPQRSTUVWXY";
		for(int aa = 0; aa < 6; aa++)
		{
			if(symbols.get(aa).equals(order[aa]))
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
		//System.out.println(scores[0] + " " + scores[1] + " " + scores[2] + " " + scores[3] + " " + scores[4] + " " + scores[5]);
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
	private JButton getButton(final JOptionPane optionPane, String name, ImageIcon icon ) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    button.setText(name);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(button);
	        symbols.add(name.toUpperCase());
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
}

