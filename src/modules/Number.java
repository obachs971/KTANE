package modules;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.JOptionPane;

import start.BombConfig;
import start.BombEdgework;

public class Number 
{
	private final BombConfig con;
	private final BombEdgework ew;
	private final int timeZone;
	private String[] messages = new String[5];
		
	int[] rules = {-1, -1, -1, -1, -1};
	
	public Number(BombConfig c, BombEdgework e, int TZ)
	{
		con = c;
		ew = e;
		timeZone = TZ;
	}
	public void run()
	{
		long half = (con.getStartingBombMinutes() * 60) + con.getStartingBombSeconds();
		half /= 2;
		long min = half / 60;
		String sec = (half % 60) + "";
		if(sec.length() < 2)
			sec = "0" + sec;
		messages[0] = "Is any of these modules present?\nThe Gamepad\nNumber Pad";
		messages[1] = "Is any of these modules have been solved?\nTimezone\nThe Bulb\nSemaphore";
		messages[2] = "Is any of these modules have not been solved?\nCrytography\nLight Cycle\nPiano Keys";
		messages[3] = "Is there a Forget Me Not on the bomb?";
		messages[4] = "Is the time displayed less than " + min + ":" + sec;
		
		String input = JOptionPane.showInputDialog("Enter the numbers in reading order:");
		boolean v = v1(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the numbers in reading order:").replace(" ", "");
			v = v1(input);
		}
		int[] numbers = new int[10];
		for(int i = 0; i < 10; i++)
			numbers[i] = "0123456789".indexOf(input.charAt(i));
		
		input = JOptionPane.showInputDialog("Enter the number of solved modules:");
		v = v2(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the number of solved modules:");
			v = v2(input);
		}
		while(true)
		{
			int solved = Integer.parseInt(input);
			String[] str = {getDigits(numbers, solved, 0), getDigits(numbers, solved, 1)};
			input = JOptionPane.showInputDialog("0 Strikes: " + str[0] + "\n1+ Strikes: " + str[1] + "\nEnter new solve amount:");
			v = v3(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("0 Strikes: " + str[0] + "\n1+ Strikes: " + str[1] + "\nEnter new solve amount:");
				v = v3(input);
			}
			if(input.length() == 0)
				break;
		}
	}
	private String getDigits(int[] numbers, int numSolved, int strike)
	{
		int[] digits = new int[4];
		
		//Digit #1
		if(ew.numTF() > 0)
			digits[0] = 7;
		else if(ew.BH() >= 3)
			digits[0] = 0;
		else if(ew.numEmpty() > 0)
			digits[0] = 9;
		else if(ew.numDupPorts() > 0)
			digits[0] = 5;
		else if(ew.numCharsInSN("OMZ6L5") > 0)
			digits[0] = 1;
		else if(ew.BAT() < (con.getNumberUnsolved(numSolved)))
			digits[0] = 6;
		else if(ew.numLit() >= 2)
			digits[0] = 8;
		else if (ew.numUnlit() == 1)
			digits[0] = 2;
		else
			digits[0] = 4;
		
		//Digit #2
		if(getNumOcc(numbers, "13579", 0, 5) > getNumOcc(numbers, "02468", 0, 5))
			digits[1] = 2;
		else if(numbers[0] < numbers[1] && numbers[1] < numbers[2] && numbers[2] < numbers[3] && numbers[3] < numbers[4])
			digits[1] = 9;
		else if(numbers[5] < numbers[6] && numbers[6] < numbers[7] && numbers[7] < numbers[8] && numbers[8] < numbers[9])
			digits[1] = 9;
		else if((numbers[5] + numbers[6] + numbers[7] + numbers[8] + numbers[9]) > 16)
			digits[1] = 8;
		else if((numbers[0] + numbers[1] + numbers[2] + numbers[3] + numbers[4]) < 15)
			digits[1] = 3;
		else if((numbers[2] % 2) == (numbers[7] % 2))
			digits[1] = 0;
		else if(getNumOcc(numbers, "237", 0, 5) == 3)
			digits[1] = 5;
		else if(getNumOcc(numbers, "13579", 5, 10) == 2)
			digits[1] = 1;
		else if(getNumOcc(numbers, "01789", 0, 5) == 5 || getNumOcc(numbers, "01789", 5, 10) == 5)
			digits[1] = 6;
		else if(getNumOcc(numbers, "7", 5, 10) == 1)
			digits[1] = 7;
		else
			digits[1] = 4;
		
		//Digit #3
		if(numSolved == 7)
			digits[2] = 7;
		else if(con.getNumberModules() == 9)
			digits[2] = 9;
		else if(getRule(0) == 0)
			digits[2] = 6;
		else if(con.getStartingBombMinutes() < con.getNumberModules())
			digits[2] = 0;
		else if(numSolved > con.getNumberUnsolved(numSolved))
			digits[2] = 1;
		else if(getRule(1) == 0)
			digits[2] = 2;
		else if(getRule(2) == 0)
			digits[2] = 8;
		else if(strike >= 1)
			digits[2] = 3;
		else if(con.getNumberNeedies() > 0)
			digits[2] = 5;
		else
			digits[2] = 4;
		
		//Digit #4
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
        cal.add(Calendar.HOUR_OF_DAY, timeZone);
        long num = cal.getTimeInMillis();
		Date now2 = new Date(num);
	    SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
	    String dayName = simpleDateformat.format(now2).toUpperCase();
	    
	    cal = Calendar.getInstance();
		cal.add(Calendar.HOUR_OF_DAY, timeZone);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String conv = sdf.format(cal.getTime());
        String[] split = conv.split(":");
        int hour = Integer.parseInt(split[0]);
        
        if(dayName.equals("MONDAY") || dayName.equals("WEDNESDAY") || dayName.equals("FRIDAY"))
        	digits[3] = 1;
        else if(hour >= 12 && hour < 17)
        	digits[3] = 0;
        else if((digits[0] % 2) == 1 && (digits[2] % 2) == 1)
        	digits[3] = 8;
        else if(getRule(3) == 0)
        	digits[3] = 9;
        else if(getMostPortType() >= 3)
        	digits[3] = 7;
        else if(digits[0] * digits[1] * digits[2] > 100)
        	digits[3] = 5;
        else if(digits[0] + digits[1] + digits[2] > 19)
        	digits[3] = 3;
        else if(digits[0] == 2 || digits[1] == 2 || digits[2] == 2)
        	digits[3] = 2;
        else if(getRule(4) == 0)
        	digits[3] = 6;
        else
        	digits[3] = 4;
        
		return (digits[0] + "" + digits[1] + "" + digits[2] + "" + digits[3]);
	}
	private int getNumOcc(int[] nums, String check, int start, int end)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < check.length(); i++)
			list.add("0123456789".indexOf(check.charAt(i)));
		int sum = 0;
		for(int i = start; i < end; i++)
		{
			if(list.contains(nums[i]))
				sum++;
		}
		return sum;
	}
	private int getRule(int rule)
	{
		if(rules[rule] == -1)
			rules[rule] = JOptionPane.showConfirmDialog(null, messages[rule]);
		return rules[rule];
	}
	private int getMostPortType()
	{
		ArrayList<ArrayList<String>> portList = ew.getPorts();
		HashMap<String, Integer> portSums = new HashMap<String, Integer>();
		for(ArrayList<String> plate : portList)
		{
			for(String port : plate)
			{
				if(!(portSums.containsKey(port)))
					portSums.put(port, 1);
				else
					portSums.put(port, portSums.get(port) + 1);
			}
		}
		int best = 0;
		for(int val : portSums.values())
		{
			if(val > best)
				best = val;
		}
		return best;
	}
	private boolean v1(String i)
	{
		if(i.length() == 10)
		{
			char[] c = i.toCharArray();
			Arrays.sort(c);
			String temp = new String(c);
			if(temp.equals("0123456789"))
				return true;
		}
		return false;
	}
	private boolean v2(String i)
	{
		if(i.length() > 0)
		{
			for(char c : i.toCharArray())
			{
				if("0123456789".indexOf(c) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
	private boolean v3(String i)
	{
		if(i.length() == 0)
			return true;
		return v2(i);
	}
}
