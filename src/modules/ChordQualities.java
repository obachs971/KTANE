package modules;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

public class ChordQualities 
{
	private final int[][] chords =
		{
				{4, 7, 10},{3, 7, 10},{4, 7, 11},{3, 7, 11},
				{3, 4, 10},{3, 6, 10},{2, 4, 7},{2, 3, 7},
				{4, 8, 10},{4, 8, 11},{5, 7, 10},{3, 8, 11}
		};
	private int[][] table =
		{
				{11, 9, 1, 5, 7, 2, 4, 10, 6, 0, 3, 8},//This returns the chord
				{10, 11, 1, 8, 0, 4, 6, 7, 9, 3, 5, 2}//This returns the root note
		};
	public String run()
	{
		String input = JOptionPane.showInputDialog("Enter the highlighted notes (spaces)").toUpperCase();
		boolean v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the highlighted notes (spaces)").toUpperCase();
			v = valid(input);
		}
		String[] temp = input.split(" "), noteName = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"}, chordName = {"7", "-7", "MAJ7", "-MAJ7", "7#9", "DIM", "ADD9", "-ADD9", "7#5", "MAJ7#5", "7SUS", "-MAJ7#5"};
		ArrayList<Integer> notes = new ArrayList<Integer>();
		for(int aa = 0; aa < 4; aa++)
			notes.add(noteToNum(temp[aa]));
		Collections.sort(notes);
		int[] chord = getChord(notes);
		String souv = "GIVEN CHORD: " + noteName[chord[0]] + " " + chordName[chord[1]];
		//System.out.println(noteName[chord[0]] + " " + chordName[chord[1]]);
		chord = new int[] {table[1][chord[1]], table[0][chord[0]]};
		String out = noteName[chord[0]];
		for(int aa = 0; aa < 3; aa++)
			out = out + " " + noteName[mod(chord[0] + chords[chord[1]][aa], 12)];
		JOptionPane.showMessageDialog(null, "Submit these notes: " + out);
		souv = souv + "\nNOTES: " + out;
		return souv;
	}
	private int noteToNum(String note)
	{
		switch(note)
		{
			case "A":
				return 0;
			case "A#":
			case "BB":
				return 1;
			case "B":
				return 2;
			case "C":
				return 3;
			case "C#":
			case "DB":
				return 4;
			case "D":
				return 5;
			case "D#":
			case "EB":
				return 6;
			case "E":
				return 7;
			case "F":
				return 8;
			case "F#":
			case "GB":
				return 9;
			case "G":
				return 10;
			case "G#":
			case "AB":
				return 11;
		}
		return -1;
	}
	private int[] getChord(ArrayList<Integer> notes)
	{
		for(int aa = 0; aa < 4; aa++)
		{
			int[] temp = new int[3];
			for(int bb = 0; bb < 3; bb++)
				temp[bb] = mod(notes.get(mod(aa + bb + 1, 4)) - notes.get(aa), 12);
			for(int bb = 0; bb < chords.length; bb++)
			{
				if(temp[0] == chords[bb][0] && temp[1] == chords[bb][1] && temp[2] == chords[bb][2])
					return new int[] {notes.get(aa), bb};
			}
		}
		return null;
	}
	private int mod(int n, int m)
	{
		while(n < 0)
			n += m;
		return (n % m);
	}
	private boolean valid(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 4)
		{
			ArrayList<Integer> notes = new ArrayList<Integer>();
			for(int aa = 0; aa < 4; aa++)
			{
				notes.add(noteToNum(conv[aa]));
				if(notes.get(aa) == -1)
					return false;
			}
			Collections.sort(notes);
			if(getChord(notes) != null)
				return true;
		}
		return false;
	}
}

