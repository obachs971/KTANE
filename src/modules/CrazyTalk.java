package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class CrazyTalk 
{
	private final String[][] chart =
		{
				{"< < > < > >", "54"},
				{"1 3 2 4", "32"},
				{"LEFT ARROW LEFT WORD RIGHT ARROW LEFT WORD RIGHT ARROW RIGHT WORD", "58"},
				{"BLANK", "13"},
				{"LITERALLY BLANK", "15"},
				{"FOR THE LOVE OF ALL THAT IS GOOD AND HOLY PLEASE FULLSTOP FULLSTOP.", "90"},
				{"FOR THE LOVE OF ALL THAT IS GOOD AND HOLY PLEASE FULLSTOP FULLSTOP", "90"},
				{"AN ACTUAL LEFT ARROW LITERAL PHRASE", "53"},
				{"FOR THE LOVE OF - THE DISPLAY JUST CHANGED, I DIDN'T KNOW THIS MOD COULD DO THAT. DOES IT MENTION THAT IN THE MANUAL?", "87"},
				{"FOR THE LOVE OF THE DISPLAY JUST CHANGED I DIDN'T KNOW THIS MOD COULD DO THAT DOES IT MENTION THAT IN THE MANUAL", "87"},
				{"ALL WORDS ONE THREE TO FOR FOR AS IN THIS IS FOR YOU", "40"},
				{"LITERALLY NOTHING", "14"},
				{"NO, LITERALLY NOTHING", "25"},
				{"NO LITERALLY NOTHING", "25"},
				{"THE WORD LEFT", "70"},
				{"HOLD ON IT'S BLANK", "19"},
				{"SEVEN WORDS FIVE WORDS THREE WORDS THE PUNCTUATION FULLSTOP", "05"},
				{"THE PHRASE THE WORD STOP TWICE", "91"},
				{"THE FOLLOWING SENTENCE THE WORD NOTHING", "27"},
				{"ONE THREE TO FOR", "39"},
				{"THREE WORDS THE WORD STOP", "73"},
				{"DISREGARD WHAT I JUST SAID. FOUR WORDS, NO PUNCTUATION. ONE THREE 2 4.", "31"},
				{"DISREGARD WHAT I JUST SAID FOUR WORDS NO PUNCTUATION ONE THREE 2 4", "31"},
				{"1 3 2 FOR", "10"},
				{"DISREGARD WHAT I JUST SAID. TWO WORDS THEN TWO DIGITS. ONE THREE 2 4.", "08"},
				{"DISREGARD WHAT I JUST SAID TWO WORDS THEN TWO DIGITS ONE THREE 2 4", "08"},
				{"WE JUST BLEW UP", "42"},
				
				{"NO REALLY.", "52"},
				{"< LEFT > LEFT > RIGHT", "56"},
				{"ONE AND THEN 3 TO 4", "47"},
				{"STOP TWICE", "76"},
				{"LEFT", "69"},
				{"..", "85"},
				{"PERIOD PERIOD", "82"},
				{"THERE ARE THREE WORDS NO PUNCTUATION READY? STOP DOT PERIOD", "50"},
				{"THERE ARE THREE WORDS NO PUNCTUATION READY STOP DOT PERIOD", "50"},
				{"NOVEBMER OSCAR SPACE, LIMA INDIGO TANGO ECHO ROMEO ALPHA LIMA LIMA YANKEE SPACE NOVEMBER OSCAR TANGO HOTEL INDEGO NOVEMBER GOLF", "29"},
				{"NOVEBMER OSCAR SPACE LIMA INDIGO TANGO ECHO ROMEO ALPHA LIMA LIMA YANKEE SPACE NOVEMBER OSCAR TANGO HOTEL INDEGO NOVEMBER GOLF", "29"},
				{"FIVE WORDS THREE WORDS THE PUNCTUATION FULLSTOP", "19"},
				{"THE PHRASE: THE PUNCTUATION FULLSTOP", "93"},
				{"THE PHRASE THE PUNCTUATION FULLSTOP", "93"},
				{"EMPTY SPACE", "16"},
				{"ONE THREE TWO FOUR", "37"},
				{"IT'S SHOWING NOTHING", "23"},
				{"LIMA ECHO FOXTROT TANGO SPACE ALPHA ROMEO ROMEO OSCAR RISKY SPACE SIERRA YANKEE MIKE BRAVO OSCAR LIMA", "12"},
				{"ONE 3 2 4", "34"},
				{"STOP.", "74"},
				{".PERIOD", "81"},
				{"NO REALLY STOP", "51"},
				{"1 3 TOO 4", "20"},
				{"PERIOD TWICE", "83"},
				
				{"1 3 TOO WITH 2 OHS FOUR", "42"},
				{"1 3 TO 4", "30"},
				{"STOP DOT PERIOD", "50"},
				{"LEFT LEFT RIGHT LEFT RIGHT RIGHT", "67"},
				{"IT LITERALLY SAYS THE WORD ONE AND THEN THE NUMBERS 2 3 4", "45"},
				{"ONE IN LETTERS 3 2 4 IN NUMBERS", "35"},
				{"WAIT FORGET EVERYTHING I JUST SAID, TWO WORDS THEN TWO SYMBOLS THEN TWO WORDS: < < RIGHT LEFT > >", "16"},
				{"WAIT FORGET EVERYTHING I JUST SAID TWO WORDS THEN TWO SYMBOLS THEN TWO WORDS < < RIGHT LEFT > >", "16"},
				{"1 THREE TWO FOUR", "36"},
				{"PERIOD", "79"},
				{".STOP", "78"},
				{"NOVEBMER OSCAR SPACE, LIMA INDIA TANGO ECHO ROMEO ALPHA LIMA LIMA YANKEE SPACE NOVEMBER OSCAR TANGO HOTEL INDIA NOVEMBER GOLF", "07"},
				{"NOVEBMER OSCAR SPACE LIMA INDIA TANGO ECHO ROMEO ALPHA LIMA LIMA YANKEE SPACE NOVEMBER OSCAR TANGO HOTEL INDIA NOVEMBER GOLF", "07"},
				{"LIMA ECHO FOXTROT TANGO SPACE ALPHA ROMEO ROMEO OSCAR WHISKEY SPACE SIERRA YANKEE MIKE BRAVO OSCAR LIMA", "65"},
				{"NOTHING", "12"},
				{"THERE'S NOTHING", "18"},
				{"STOP STOP", "75"},
				{"RIGHT ALL IN WORDS STARTING NOW ONE TWO THREE FOUR", "49"},
				{"THE PHRASE THE WORD LEFT", "71"},
				{"LEFT ARROW SYMBOL TWICE THEN THE WORDS RIGHT LEFT RIGHT THEN A RIGHT ARROW SYMBOL", "59"},
				{"LEFT LEFT RIGHT < RIGHT >", "57"},
				{"NO COMMA LITERALLY NOTHING", "24"},
				{"HOLD ON CRAZY TALK WHILE I DO THIS NEEDY", "21"},
				
				{"THIS ONE IS ALL ARROW SYMBOLS NO WORDS", "28"},
				{"<", "63"},
				{"THE WORD STOP TWICE", "94"},
				{"< < RIGHT LEFT > >", "61"},
				{"THE PUNCTUATION FULLSTOP", "92"},
				{"1 3 TOO WITH TWO OS 4", "41"},
				{"THREE WORDS THE PUNCTUATION FULLSTOP", "99"},
				{"OK WORD FOR WORD LEFT ARROW SYMBOL TWICE THEN THE WORDS RIGHT LEFT RIGHT THEN A RIGHT ARROW SYMBOL", "60"},
				{"DOT DOT", "86"},
				{"LEFT ARROW", "68"},
				{"AFTER I SAY BEEP FIND THIS PHRASE WORD FOR WORD BEEP AN ACTUAL LEFT ARROW", "72"},
				{"ONE THREE 2 WITH TWO OHS 4", "43"},
				{"LEFT ARROW SYMBOL", "64"},
				{"AN ACTUAL LEFT ARROW", "62"},
				{"THAT'S WHAT IT'S SHOWING", "21"},
				{"THE PHRASE THE WORD NOTHING", "26"},
				{"THE WORD ONE AND THEN THE NUMBERS 3 2 4", "48"},
				{"ONE 3 2 FOUR", "38"},
				{"ONE WORD THEN PUNCTUATION. STOP STOP.", "09"},
				{"ONE WORD THEN PUNCTUATION STOP STOP", "09"},
				{"THE WORD BLANK", "01"},
				{"FULLSTOP FULLSTOP", "84"}
		};
	public void run()
	{
		String input = JOptionPane.showInputDialog("Enter the phrase:").toUpperCase();
		boolean v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the phrase:").toUpperCase();
			v = valid(input);
		}
		ArrayList<Integer> poss = new ArrayList<Integer>();
		ArrayList<String> switchFlips = new ArrayList<String>();
		for(int aa = 0; aa < chart.length; aa++)
		{
			if(chart[aa][0].startsWith(input) && !(switchFlips.contains(chart[aa][1])))
			{
				poss.add(aa);
				switchFlips.add(chart[aa][1]);
			}
		}
		while(poss.size() > 1)
		{
			String[] temp = chart[poss.get(0)][0].split(" ");
			String out = "";
			for(int aa = 0; aa < temp.length; aa++)
			{
				out = out + "" + temp[aa] + " ";
				if((aa + 1) % 5 == 0)
					out = out + "\n";
			}
			if(JOptionPane.showConfirmDialog(null, "Is this the phrase?\n" + out) == 0)
			{
				while(poss.size() > 1)
					poss.remove(1);
			}
			else
				poss.remove(0);
		}
		JOptionPane.showMessageDialog(null, "Flip it down when the last digit is a " + chart[poss.get(0)][1].charAt(0) + "\nFlip it up when the last digit is a " + chart[poss.get(0)][1].charAt(1));
	}
	private boolean valid(String i)
	{
		for(int aa = 0; aa < chart.length; aa++)
		{
			if(chart[aa][0].startsWith(i))
				return true;
		}
		return false;
	}
}
