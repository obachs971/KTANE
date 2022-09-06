package modules;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import start.BombEdgework;

public class GuitarChords 
{
	private int value = -1;
	private final String[] chordPositions =
		{
			"001220","1000--","1-00--",
			"-111--","-011--","2111--","2011--",
			"02220-","01220-","3122--",
			"13331-","12331-","1-101-",
			"-0-122","-0212-","2020--",
			"0102--","-101--","3132--","3131--",
			"121---","021---","101---","001---",
			"1323--","2120--",
			"---11-","-2011-","----1-",
			"001020","000020",
			"112331","111331","-123--","1111--",
			"2---12","000220","0---12"
		};
	private final BombEdgework ew;
	public GuitarChords(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		String[] chordNames = {
			"Ab","Ab7","Abm7",
			"A","Am","A7","Am7",
			"Bb","Bbm","Bbm7",
			"B","Bm","B7",
			"C","C7","Cm7",
			"C#","C#m","C#7","C#m7",
			"D","Dm","D7","Dm7",
			"Ebm","Eb7",
			"Em","E7","Em7",
			"F7","Fm7",
			"F#","F#m","F#7","F#m7",
			"G","Gm","G7"
		};
		JDialog dialog = getDialog(chordNames, 3);
		int[] capos = {level1(), level2(), level3()};
		for(int i = 0; i < 3; i++)
		{
			dialog.setTitle("Select Chord #" + (i + 1) + ":");
			dialog.setVisible(true);
			String positions = chordPositions[value];
			String out = "";
			for(char position : positions.toCharArray())
			{
				if(position == '-')
					out = out + "- ";
				else
					out = out + "" + (position - '0' + capos[i]) + " ";
			}
			JOptionPane.showMessageDialog(null, "- - Skip the String\nIndex Starts at 0\nPlay these positions on\nthe strings, left to right:\n" + out);
		}
	}
	private int level1()
	{
		if(ew.findLit("BOB"))
			return 9;
		else if(ew.findPort("PARALLEL") > 0 && ew.findPort("RJ-45") > 0)
			return 7;
		else if(ew.BAT() < 3)
			return 5;
		else if(ew.getSNDIG(ew.numSNDIGS() - 1) % 2 == 1)
			return 3;
		else
			return 0;
	}
	private int level2()
	{
		if(ew.findPort("PS/2") > 0 || ew.findPort("SERIAL") > 0)
			return 5;
		else if(ew.numCharsInSN("AEIOU") > 0)
			return 0;
		else if(ew.BAT() > 5)
			return 9;
		else if(ew.findUnlit("SIG"))
			return 7;
		else
			return 3;
	}
	private int level3()
	{
		if(ew.BAT() == 0)
			return 3;
		else if(ew.getSNSUM() < 10)
			return 5;
		else if(ew.findInd("FRQ"))
			return 7;
		else if(ew.findPort("RCA") > 0 || ew.findPort("DVI-D") > 0)
			return 0;
		else
			return 9;
	}
	private JDialog getDialog(String[] arr, int div)
	{
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		JButton[] jButton = new JButton[arr.length];
		optionPane.setLayout(new GridLayout(arr.length / div + 1, div));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int aa = 0; aa < arr.length; aa++)
		{
			jButton[aa] = getButton(optionPane, arr[aa], aa);
			optionPane.add(jButton[aa]);
		}
		return optionPane.createDialog(frame, "");
	}
	private JButton getButton(final JOptionPane optionPane, String text, int val) {
	    final JButton button = new JButton();
	    button.setText(text);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(text.toUpperCase());
	        value = val;
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
}
