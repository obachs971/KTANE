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

public class PianoKeys {
	private final double r;
	private final BombEdgework ew;
	public PianoKeys(double resize, BombEdgework e)
	{
		r = resize;
		ew = e;
	}
	private ArrayList<String> pianoSymbols;
	public void run()
	{
		pianoSymbols = new ArrayList<String>();
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		ImageIcon[] icon = new ImageIcon[9];
		JButton[] jButton = new JButton[9];
		optionPane.setLayout(new GridLayout(5, 2));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		String[] pianoSymbolList = {"FLAT", "SHARP", "NATURAL", "MORDENT", "TURN", "FERMATA", "COMMON", "CUT", "ALTO"};
		for(int aa = 0; aa < 9; aa++)
		{
			icon[aa] = new ImageIcon("img/Piano" + (aa + 1) + ".jpg");
			Image image = icon[aa].getImage();
			image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			icon[aa] = new ImageIcon(image);
			jButton[aa] = getButton(optionPane, pianoSymbolList[aa], icon[aa]);
			optionPane.add(jButton[aa]);
		}
		for(int aa = 0; aa < 3; aa++)
		{
			JDialog dialog = optionPane.createDialog(frame, "");
			dialog.setTitle("Select symbol #" + (aa + 1) + ":");
			dialog.setVisible(true);
			if(aa < 2)
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
}
