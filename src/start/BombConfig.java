package start;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javax.swing.JOptionPane;

public class BombConfig 
{
	private final long minutes;
	private final int seconds;
	private final int modules;
	private final int needies;
	private final int[] timeStarted;
	private final int month;
	private final int day;
	private final String dayName;
	
	public BombConfig(int convertTimeZone)
	{
		//Bomb Date
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR_OF_DAY, convertTimeZone);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String conv = sdf.format(cal.getTime());
        String[] split = conv.split(":");
        timeStarted = new int[2];
        timeStarted[0] = Integer.parseInt(split[0]);
        timeStarted[1] = Integer.parseInt(split[1]);
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
        LocalDateTime now = LocalDateTime.now();  
        now.plusHours(convertTimeZone);
        conv = dtf.format(now);  
        split = conv.split("/");
        month = Integer.parseInt(split[1]);
        day = Integer.parseInt(split[2]);
        
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.add(Calendar.HOUR_OF_DAY, convertTimeZone);
        long num = cal.getTimeInMillis();
		Date now2 = new Date(num);
	    SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
	    dayName = simpleDateformat.format(now2).toUpperCase();
		
	    
	    
		//Bomb Time
		String input = JOptionPane.showInputDialog("Enter the starting time:");
		boolean v = v1(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the starting time:");
			v = v1(input);
		}
		String[] temp = input.split(":");
		minutes = Long.parseLong(temp[0]);
		seconds = Integer.parseInt(temp[1]);
		//Number of modules/needies on bomb
		input = JOptionPane.showInputDialog("Enter the total number of\nmodules/needies (spaces):");
		v = v2(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the total number of\nmodules and needies (spaces):");
			v = v2(input);
		}
		temp = input.split(" ");
		modules = Integer.parseInt(temp[0]);
		needies = Integer.parseInt(temp[1]);
	}
	public long getStartingBombMinutes()
	{
		return minutes;
	}
	public int getStartingBombSeconds()
	{
		return seconds;
	}
	public int getNumberModules()
	{
		return modules;
	}
	public int getNumberNeedies()
	{
		return needies;
	}
	public int getNumberSolvable()
	{
		return (modules - needies);
	}
	public int getNumberUnsolved(int solved)
	{
		return (getNumberSolvable() - solved);
	}
	public int getStartingMonth()
	{
		return month;
	}
	public int getStartingDay()
	{
		return day;
	}
	public String getStartingDayName()
	{
		return dayName;
	}
	private boolean v1(String i)
	{
		String[] conv = i.split(":");
		if(conv.length == 2)
		{
			for(int aa = 0; aa < conv.length; aa++)
			{
				for(int bb = 0; bb < conv[aa].length(); bb++)
				{
					if(!(Character.isDigit(conv[aa].charAt(bb))))
						return false;
				}
			}
			if(Long.parseLong(conv[1]) < 60)
				return true;
		}
		return false;
	}
	private boolean v2(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 2)
		{
			for(int aa = 0; aa < conv.length; aa++)
			{
				for(int bb = 0; bb < conv[aa].length(); bb++)
				{
					if(!(Character.isDigit(conv[aa].charAt(bb))))
						return false;
				}
			}
			if(Integer.parseInt(conv[0]) >= Integer.parseInt(conv[1]))
				return true;
		}
		return false;
	}
}
