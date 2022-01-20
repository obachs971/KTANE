package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class WordSearch 
{
	private final String[][] chart =
		{
				{},{"HOTEL", "DONE"},{"SEARCH", "QUEBEC"},{"ADD", "CHECK"},{"SIERRA", "FIND"},{"FINISH", "EAST"},{},
				{"PORT", "COLOR"},{"BOOM", "SUBMIT"},{"LINE", "BLUE"},{"KABOOM", "ECHO"},{"PANIC", "FALSE"},{"MANUAL", "ALARM"},{"DECOY", "CALL"},
				{"SEE", "TWENTY"},{"INDIA", "NORTH"},{"NUMBER", "LOOK"},{"ZULU", "GREEN"},{"VICTOR", "XRAY"},{"DELTA", "YES"},{"HELP", "LOCATE"},
				{"ROMEO", "BEEP"},{"TRUE", "EXPERT"},{"MIKE", "EDGE"},{"FOUND", "RED"},{"BOMBS", "WORD"},{"WORK", "UNIQUE"},{"TEST", "JINX"},
				{"GOLF", "LETTER"},{"TALK", "SIX"},{"BRAVO", "SERIAL"},{"SEVEN", "TIMER"},{"MODULE", "SPELL"},{"LIST", "TANGO"},{"YANKEE", "SOLVE"},
				{},{"CHART", "OSCAR"},{"MATH", "NEXT"},{"READ", "LISTEN"},{"LIMA", "FOUR"},{"COUNT", "OFFICE"}
		};
	private final BombEdgework ew;
	public WordSearch(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		int[] offsets = {8, 7, 1, 0};
		String out = "", input = JOptionPane.showInputDialog("Enter the letters in the\n4 corners in reading order:").toUpperCase().replace(" ", "");
		boolean v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the letters in the\n4 corners in reading order:").toUpperCase().replace(" ", "");
			v = valid(input);
		}
		for(int aa = 0; aa < 4; aa++)
		{
			out = out + "" + chart[("-VUSZ--PQNXFY-TIMEDA-KBWHJO--RLCG".indexOf(input.charAt(aa))) + offsets[aa]][ew.getSNDIG(ew.numSNDIGS() - 1) % 2] + "\n";
		}
			
		JOptionPane.showMessageDialog(null, "Select one of these words:\n" + out);
	}
	private boolean valid(String i)
	{
		if(i.length() == 4)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
}