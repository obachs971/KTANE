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

public class FestivePianoKeys 
{
	private ArrayList<String> pianoSymbols;
	private final double r;
	private final BombEdgework ew;
	public FestivePianoKeys(double resize, BombEdgework e)
	{
		r = resize;
		ew = e;
	}
	public void run()
	{
		pianoSymbols = new ArrayList<String>();
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		ImageIcon[] icon = new ImageIcon[13];
		JButton[] jButton = new JButton[13];
		optionPane.setLayout(new GridLayout(7, 2));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		String[] pianoSymbolList = {"WHOLE", "DOUBLE WHOLE", "16 REST", "16 NOTE", "ALTO", "CODA", "MORDENT", "RELEASE", "DOWN BOW", "V", "^", ">", "BREAK"};
		int[] index = {21, 10, 13, 17, 9, 16, 4, 18, 12, 19, 20, 22, 15};
		for(int aa = 0; aa < index.length; aa++)
		{
			icon[aa] = new ImageIcon("img/Piano" + index[aa] + ".jpg");
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
		if(pianoSymbols.contains("BREAK") && ew.numCharsInSN("02468") > ew.numCharsInSN("13579"))
			JOptionPane.showMessageDialog(null, "Rudolph the Red-Nosed Reindeer\nEb F Eb C\nAb F Eb");
		else if((pianoSymbols.contains("CODA") || pianoSymbols.contains("16 NOTE")) && getSNDup())
			JOptionPane.showMessageDialog(null, "We Three Kings\nC# B A F#\nG# A G# F#");
		else if(pianoSymbols.contains("MORDENT") && pianoSymbols.contains("RELEASE"))
			JOptionPane.showMessageDialog(null, "Silent Night\nG A G E\nG A G E");
		else if((pianoSymbols.contains("V") || pianoSymbols.contains("DOWN BOW")) && ew.numPortTypes() <= 2)
			JOptionPane.showMessageDialog(null, "Last Christmas\nEb Eb Db Ab\nEb Eb F Db");
		else if(pianoSymbols.contains("^") && ew.numLitWithChars("AEIOU") > 0)
			JOptionPane.showMessageDialog(null, "All I want for Christmas is You\nB A G Eb D\nA B A G");
		else if((pianoSymbols.contains("16 REST") || pianoSymbols.contains("16 NOTE")) && ew.BA() >= 3)
			JOptionPane.showMessageDialog(null, "It's the Most Wonderful Time of the Year\nF# G A A D\nB A G E D");
		else if(pianoSymbols.contains("WHOLE") && pianoSymbols.contains("DOUBLE WHOLE"))
			JOptionPane.showMessageDialog(null, "Frosty the Snowman\nG E F G C B\nC D C B A G");
		else if((pianoSymbols.contains(">") || pianoSymbols.contains("^") || pianoSymbols.contains("V")) && ew.numCharsInSN("19") > 0)
			JOptionPane.showMessageDialog(null, "Jingle Bells\nG G G G G G\nG Bb Eb F G");
		else if(pianoSymbols.contains("CODA") || pianoSymbols.contains("ALTO") || pianoSymbols.contains("BREAK"))
			JOptionPane.showMessageDialog(null, "Jingle Bell Rock\nD D D C# C#\nC# B C# B F#");
		else
		{
			String out = "Carol of the Bells";
			for(int aa = 0; aa <= ew.getLargestSNDIG(); aa++)
				out = out + "\nBb A Bb G";
			JOptionPane.showMessageDialog(null, out);
		}
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
	private boolean getSNDup()
	{
		for(int aa = 0; aa < 6; aa++)
		{
			for(int bb = aa + 1; bb < 6; bb++)
			{
				if(ew.getSNCHAR(aa) == ew.getSNCHAR(bb))
					return true;
			}
		}
		return false;
	}
}
