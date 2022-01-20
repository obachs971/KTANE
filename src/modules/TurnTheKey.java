package modules;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class TurnTheKey 
{
	public int[] run()
	{
		String input = JOptionPane.showInputDialog("Enter the minutes displayed\nand the minutes remaining (spaces):");
		boolean v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the minutes displayed\nand the minutes remaining (spaces):");
			v = valid(input);
		}
		String[] temp = input.split(" ");
		int diff = (Integer.parseInt(temp[1]) - Integer.parseInt(temp[0]));
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String conv = sdf.format(cal.getTime());
        String[] split = conv.split(":");
        int[] timeStarted = new int[2];
        timeStarted[0] = Integer.parseInt(split[0]);
        timeStarted[1] = Integer.parseInt(split[1]) + diff;
        while(timeStarted[1] >= 60)
        {
        	timeStarted[1] -= 60;
        	timeStarted[0] = (timeStarted[0] + 1) % 24;
        }
        //System.out.println(timeStarted[0] + " " + timeStarted[1]);
        return timeStarted;
	}
	private boolean valid(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 2)
		{
			for(int aa = 0; aa < 2; aa++)
			{
				for(int bb = 0; bb < conv[aa].length(); bb++)
				{
					if("0123456789".indexOf(conv[aa].charAt(bb)) < 0)
						return false;
				}
			}
			if(Integer.parseInt(conv[0]) <= Integer.parseInt(conv[1]))
				return true;
		}
		return false;
	}
}
