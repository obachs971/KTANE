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

public class CruelPianoKeys 
{
	private final BombEdgework ew;
	private final double r;
	public CruelPianoKeys(BombEdgework e, Double resizer)
	{
		ew = e;
		r = resizer;
	}
	private ArrayList<String> pianoSymbols;
	private String[] note = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
	private String[][] noteTable = 
		{
				{"F", "D", "F#", "G#", "C", "B", "A#", "C#", "G", "E", "D#", "A"},
				{"A#", "A", "C", "E", "C#", "D", "D#", "G", "B", "F#", "G#", "F"},
				{"F#", "B", "A", "G#", "D", "C", "G", "C#", "F", "D#", "E", "A#"},
				{"E", "D#", "D", "F#", "F", "A#", "G#", "C#", "C", "B", "G", "A"},
				{"D", "E", "A", "A#", "C", "B", "C#", "G#", "F", "F#", "D#", "G"},
				{"C", "D#", "F#", "D", "F", "C#", "B", "A", "G", "A#", "E", "G#"},
				{"G#", "C", "A#", "C#", "E", "G", "B", "D#", "A", "D", "F", "F#"},
				{"E", "A", "C#", "B", "G", "G#", "A#", "D#", "F#", "F", "C", "D"},
				{"G#", "D#", "D", "E", "A#", "C#", "F#", "G", "F", "A", "C", "B"},
				{"D#", "G#", "C", "B", "D", "C#", "F#", "A#", "F", "G", "A", "E"}
		};
	public void run()
	{
		int timeRemain = -1;
		int solves = -1;
		int strikes = -1;
		pianoSymbols = new ArrayList<String>();
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		ImageIcon[] icon = new ImageIcon[14];
		JButton[] jButton = new JButton[14];
		optionPane.setLayout(new GridLayout(7, 2));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		String[] pianoSymbolList = {"FLAT", "SHARP", "NATURAL", "MORDENT", "TURN", "FERMATA", "COMMON", "CUT", "ALTO", "WHOLE", "DSHARP", "DOWNBOW", "16THREST", "QUARTERREST"};
		for(int aa = 0; aa < 14; aa++)
		{
			icon[aa] = new ImageIcon("img/Piano" + (aa + 1) + ".jpg");
			Image image = icon[aa].getImage();
			image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			icon[aa] = new ImageIcon(image);
			jButton[aa] = getButton(optionPane, pianoSymbolList[aa], icon[aa]);
			optionPane.add(jButton[aa]);
		}
		for(int aa = 0; aa < 4; aa++)
		{
			JDialog dialog = optionPane.createDialog(frame, "");
			dialog.setTitle("Select symbol #" + (aa + 1) + ":");
			dialog.setVisible(true);
			if(aa < 3)
			{
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
			}
		}
		String[] notes = new String[0];
		System.out.println(ew.numEmpty());
		if(pianoSymbols.contains("WHOLE") && pianoSymbols.contains("TURN") && ew.numInd() >= 2)	
			notes = getNotes(noteTable[ew.getSNDIG(0)], "IR", 0);
		else if((pianoSymbols.contains("SHARP") || pianoSymbols.contains("DSHARP")) && ew.numEmpty() > 0)
		{
			String input = JOptionPane.showInputDialog("12n minutes\nEnter the minutes remaining:");
			boolean v = v1(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("12n minutes\nEnter the minutes remaining:");
				v = v1(input);
			}
			timeRemain = Integer.parseInt(input);
			notes = getNotes(noteTable[ew.BH() % 10], "T", -1 * timeRemain);
		}
		else if((pianoSymbols.contains("FERMATA") || pianoSymbols.contains("DOWNBOW")) && ew.numDupPorts() > 0)
		{
			String input = JOptionPane.showInputDialog("10n solves\nEnter the number\nof solved modules:");
			boolean v = v1(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("10n solves\nEnter the number\nof solved modules:");
				v = v1(input);
			}
			solves = Integer.parseInt(input);
			notes = getNotes(noteTable[solves % 10], "I", 0);
		}
		else if(pianoSymbols.contains("ALTO") && pianoSymbols.contains("16THREST") && ew.numPlates() >= 2)
			notes = getNotes(noteTable[mod(9 - ew.numUnlit(), 10)], "R", 0);
		else if((pianoSymbols.contains("CUT") || pianoSymbols.contains("COMMON")) && ew.numCharsInSN("AEIOU") >= 1)
		{
			String input = JOptionPane.showInputDialog("10n strikes\nEnter the number of strikes:");
			boolean v = v1(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("10n strikes\nEnter the number of strikes:");
				v = v1(input);
			}
			strikes = Integer.parseInt(input);
			notes = getNotes(noteTable[strikes % 10], "RT", -3);
		}
		else if((pianoSymbols.contains("NATURAL") || pianoSymbols.contains("MORDENT")) && ew.BAT() % 2 == 0)
		{
			if(ew.findPort("DVI-D") > 0)
				notes = getNotes(noteTable[7], "T", ew.numPorts());
			else
				notes = getNotes(noteTable[3], "T", ew.numPorts());
		}
		else if((pianoSymbols.contains("FLAT") || pianoSymbols.contains("QUARTERREST")) && ew.numIndWithChars("AEIOU") < ew.numInd())
			notes = getNotes(noteTable[8], "I", 0);
		else if((pianoSymbols.contains("DOWNBOW") || pianoSymbols.contains("16THREST")) && ew.numPorts() < 2)
			notes = getNotes(noteTable[4], "R", 0);
		else if(pianoSymbols.contains("WHOLE") || pianoSymbols.contains("DSHARP"))
			notes = getNotes(noteTable[5], "", 0);
		else
		{
			if(pianoSymbols.contains("FLAT") && ew.getSNDIG(ew.numSNDIGS() - 1) % 2 == 0)
				JOptionPane.showMessageDialog(null, "Final Fantasy\nBb Bb Bb Bb Gb\nAb Bb Ab Bb");
			else if((pianoSymbols.contains("COMMON") || pianoSymbols.contains("SHARP")) && ew.BH() >= 2)
				JOptionPane.showMessageDialog(null, "Guiles Theme\nEb Eb D D Eb Eb D\nEb Eb D D Eb");
			else if(pianoSymbols.contains("NATURAL") && pianoSymbols.contains("FERMATA"))
				JOptionPane.showMessageDialog(null, "James Bond\nE F# F# F#\nF# E E E");
			else if((pianoSymbols.contains("CUT") || pianoSymbols.contains("TURN")) && ew.findPort("RCA") > 0)
				JOptionPane.showMessageDialog(null, "Jurassic Park\nBb A Bb F Eb\nBb A Bb F Eb");
			else if(pianoSymbols.contains("ALTO") && ew.findLit("SND"))
				JOptionPane.showMessageDialog(null, "Super Mario\nE E E C\nE G G");
			else if((pianoSymbols.contains("MORDENT") || pianoSymbols.contains("FERMATA") || pianoSymbols.contains("COMMON")) && ew.BAT() >= 3)
				JOptionPane.showMessageDialog(null, "Pink Panther\nC# D E F C#\nD E F Bb A");
			else if(pianoSymbols.contains("FLAT") && pianoSymbols.contains("SHARP"))
				JOptionPane.showMessageDialog(null, "Superman\nG G C G\nG C G C");
			else if((pianoSymbols.contains("CUT") || pianoSymbols.contains("MORDENT")) && ew.numCharsInSN("378") > 0)
				JOptionPane.showMessageDialog(null, "Tetris Theme A\nA E F G F\nE D D F A");
			else if(pianoSymbols.contains("NATURAL") || pianoSymbols.contains("TURN") || pianoSymbols.contains("ALTO"))
				JOptionPane.showMessageDialog(null, "Empire Strikes Back\nG G G Eb Bb\nG Eb Bb G");
			else
				JOptionPane.showMessageDialog(null, "Zelda's Lullaby\nB D A G\nA B D A");
		}
		if(notes.length > 0)
		{
			String out = notes[0] + " " + notes[1] + " " + notes[2] + " " + notes[3] + " " + notes[4] + " " + notes[5] + "\n" + notes[6] + " " + notes[7] + " " + notes[8] + " " + notes[9] + " " + notes[10] + " " + notes[11];
			if(timeRemain >= 0)
			{
				while(true)
				{
					String input = JOptionPane.showInputDialog("12n minutes\nPlay these notes:\n" + out + "\nEnter minutes remaining when needed:");
					boolean v = v2(input);
					while(!(v))
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						input = JOptionPane.showInputDialog("12n minutes\nPlay these notes:\n" + out + "\nEnter minutes remaining when needed:");
						v = v2(input);
					}
					if(input.length() == 0)
						break;
					timeRemain = Integer.parseInt(input);
					notes = getNotes(noteTable[ew.BH() % 10], "T", -1 * timeRemain);
					out = notes[0] + " " + notes[1] + " " + notes[2] + " " + notes[3] + " " + notes[4] + " " + notes[5] + "\n" + notes[6] + " " + notes[7] + " " + notes[8] + " " + notes[9] + " " + notes[10] + " " + notes[11];
				}
			}
			else if(solves >= 0)
			{
				while(true)
				{
					String input = JOptionPane.showInputDialog("10n solves\nPlay these notes:\n" + out + "\nEnter number of solved modules\nwhen needed:");
					boolean v = v2(input);
					while(!(v))
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						input = JOptionPane.showInputDialog("10n solves\nPlay these notes:\n" + out + "\nEnter number of solved modules\nwhen needed:");
						v = v2(input);
					}
					if(input.length() == 0)
						break;
					solves = Integer.parseInt(input);
					notes = getNotes(noteTable[solves % 10], "I", 0);
					out = notes[0] + " " + notes[1] + " " + notes[2] + " " + notes[3] + " " + notes[4] + " " + notes[5] + "\n" + notes[6] + " " + notes[7] + " " + notes[8] + " " + notes[9] + " " + notes[10] + " " + notes[11];
				}
			}
			else if(strikes >= 0)
			{
				while(true)
				{
					String input = JOptionPane.showInputDialog("10n strikes\nPlay these notes:\n" + out + "\nEnter number of strikes when needed:");
					boolean v = v2(input);
					while(!(v))
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						input = JOptionPane.showInputDialog("10n strikes\nPlay these notes:\n" + out + "\nEnter number of strikes when needed:");
						v = v2(input);
					}
					if(input.length() == 0)
						break;
					strikes = Integer.parseInt(input);
					notes = getNotes(noteTable[strikes % 10], "RT", -3);
					out = notes[0] + " " + notes[1] + " " + notes[2] + " " + notes[3] + " " + notes[4] + " " + notes[5] + "\n" + notes[6] + " " + notes[7] + " " + notes[8] + " " + notes[9] + " " + notes[10] + " " + notes[11];
				}
			}
			else
				JOptionPane.showMessageDialog(null, "Play these notes:\n" + out);
		}
	}
	private String[] getNotes(String[] seq, String transform, int transpose)
	{
		for(int aa = 0; aa < transform.length(); aa++)
		{
			switch(transform.charAt(aa))
			{
				case 'R':
					String[] temp1 = {seq[11], seq[10], seq[9], seq[8], seq[7], seq[6], seq[5], seq[4], seq[3], seq[2], seq[1], seq[0]};
					seq = temp1;
					break;
				case 'I':
					String[] temp2 = new String[seq.length];
					temp2[0] = seq[0];
					for(int bb = 1; bb < seq.length; bb++)
						temp2[bb] = note[mod(noteToNum(temp2[bb - 1]) - (noteToNum(seq[bb]) - noteToNum(seq[bb - 1])), 12)];
					seq = temp2;
					break;
				case 'T':
					String[] temp3 = new String[seq.length];
					for(int bb = 0; bb < seq.length; bb++)
						temp3[bb] = note[mod(noteToNum(seq[bb]) + transpose, 12)];
					seq = temp3;
					break;
			}
		}
		return seq;
	}
	private int mod(int n, int m)
	{
		while(n < 0)
			n += m;
		return (n % m);
	}
	private int noteToNum(String n)
	{
		for(int aa = 0; aa < note.length; aa++)
		{
			if(note[aa].equals(n))
				return aa;
		}
		return -1;
	}
	private JButton getButton(final JOptionPane optionPane, String name, ImageIcon icon ) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(button);
	        pianoSymbols.add(name);
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
	private boolean v2(String i)
	{
		if(i.length() == 0)
			return true;
		else
			return v1(i);
	}
}
