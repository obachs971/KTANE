package modules;

import javax.swing.JOptionPane;

import start.BombConfig;

public class Clock 
{
	private final BombConfig bombCon;
	public Clock(BombConfig cf)
	{
		bombCon = cf;
	}
	public void run()
	{
		int hour = 11;
		String input = JOptionPane.showInputDialog("Roman, Arabic, None\nEnter the type of numerals:").toUpperCase();
		boolean v = valid(input, new String[] {"ROMAN", "R", "ARABIC", "A", "NONE", "N"});
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Roman, Arabic, None\nEnter the type of numerals:").toUpperCase();
			v = valid(input, new String[] {"ROMAN", "R", "ARABIC", "A", "NONE", "N"});
		}
		hour += ("RNA".indexOf(input.charAt(0)) * 4);
		input = JOptionPane.showInputDialog("Gold, Silver\nEnter the color of the casing:").toUpperCase();
		v = valid(input, new String[] {"GOLD", "G", "SILVER", "S"});
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Gold, Silver\nEnter the color of the casing:").toUpperCase();
			v = valid(input, new String[] {"GOLD", "G", "SILVER", "S"});	
		}
		hour += ("SG".indexOf(input.charAt(0)) * 2);
		if(JOptionPane.showConfirmDialog(null, "Does the color of the\nhands & tickmarks match?") == 1)
			hour++;
		while(hour > 12)
			hour -= 12;
		int minute = 51;
		input = JOptionPane.showInputDialog("Lines, Spades, Arrows\nEnter the shape of the hands:").toUpperCase();
		v = valid(input, new String[] {"LINES", "LINE", "L", "SPADES", "SPADE", "S", "ARROWS", "ARROW", "A"});
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Lines, Spades, Arrows\nEnter the shape of the hands:").toUpperCase();
			v = valid(input, new String[] {"LINES", "LINE", "L", "SPADES", "SPADE", "S", "ARROWS", "ARROW", "A"});
		}
		minute += ("LSA".indexOf(input.charAt(0)) * 20);
		//System.out.println(minute);
		input = JOptionPane.showInputDialog("Red, Blue, Green, Gold, Black\nEnter the color of the tickmarks:").toUpperCase();
		v = valid(input, new String[] {"RED", "BLUE", "GREEN", "GOLD", "BLACK"});
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Red, Blue, Green, Gold, Black\nEnter the color of the tickmarks:").toUpperCase();
			v = valid(input, new String[] {"RED", "BLUE", "GREEN", "GOLD", "BLACK"});
		}
		switch(input)
		{
			case "GREEN":
				minute += 4;
				break;
			case "BLUE":
				minute += 8;
				break;
			case "GOLD":
				minute += 12;
				break;
			case "BLACK":
				minute += 16;
				break;
		}
		//System.out.println(minute);
		input = JOptionPane.showInputDialog("White, Black\nEnter the color of the AM/PM text:").toUpperCase();
		v = valid(input, new String[] {"WHITE", "W", "BLACK", "B"});
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("White, Black\nEnter the color of the AM/PM text:").toUpperCase();
			v = valid(input, new String[] {"WHITE", "W", "BLACK", "B"});
		}
		minute += ("BW".indexOf(input.charAt(0)) * 2);
		//System.out.println(minute);
		if(JOptionPane.showConfirmDialog(null, "Is the seconds hand present?") == 1)
			minute++;
		//System.out.println(minute);
		minute = minute % 60;
		long min = bombCon.getStartingBombMinutes();
		int sec = bombCon.getStartingBombSeconds() / 2;
		if(min % 2 == 1)
			sec += 30;
		min = min / 2;
		String out;
		if(sec < 10)
			out = min + ":0" + sec;
		else
			out = min + ":" + sec;
			
		JOptionPane.showMessageDialog(null, "Forward if the time\ndisplayed is over " + out + "\nOtherwise, Go Backwards\nHour: " + hour + "\nMinutes: " + minute);
	}
	private boolean valid(String i, String[] choices)
	{
		for(int aa = 0; aa < choices.length; aa++)
		{
			if(choices[aa].equals(i))
				return true;
		}
		return false;
	}
}
