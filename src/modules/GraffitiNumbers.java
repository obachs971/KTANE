package modules;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class GraffitiNumbers 
{
	
	public void run()
	{
		String input = JOptionPane.showInputDialog("Enter the NUMBERS\nin reading order:").replace(" ", "");
		boolean v = v1(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the numbers\nin reading order:").replace(" ", "");
			v = v1(input);
		}
		int[] n = new int[9];
		for(int i = 0; i < n.length; i++)
			n[i] = (input.charAt(i) - '0');
		String c = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nEnter the COLORS\nin reading order:").replace(" ", "").toUpperCase();
		v = v2(c);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			c = JOptionPane.showInputDialog("R - Red\nB - Blue\nY - Yellow\nG - Green\nEnter the COLORS\nin reading order:").replace(" ", "").toUpperCase();
			v = v2(c);
		}
		int[] sums = {0, 0, 0, 0};
		for(char color : c.toCharArray())
			sums["RBYG".indexOf(color)]++;
		int rule, dir;
		if(sums[0] > sums[3])
		{
			rule = 7;
			dir = -1;
		}
		else if(sums[1] > sums[2])
		{
			rule = 5;
			dir = 1;
		}
		else if(sums[3] > sums[1])
		{
			rule = 3;
			dir = -1;
		}
		else
		{
			rule = 0;
			dir = 1;
		}
		boolean[] rules = {rule1(n), rule2(n), rule3(n), rule4(n, c), rule5(n), rule6(n), rule7(n), rule8(c)};
		ArrayList<Integer> spray = new ArrayList<Integer>();
		for(int i = 0; i < 9; i++)
		{
			if(rule == 8)
			{
				if(spray.size() < 3)
					spray.add(9);
			}
			else if(rules[rule])
				spray.add(rule + 1);
			rule = mod(rule + dir, 9);
		}
		String out = spray.get(0) + "";
		for(int i = 1; i < spray.size(); i++)
			out = out + " " + spray.get(i);
		JOptionPane.showMessageDialog(null, "Spray these numbers:\n" + out);
	}
	private boolean rule1(int[] n)
	{
		return (n[0] < n[3] && n[3] < n[6]);
	}
	private boolean rule2(int[] n)
	{
		return (n[0] > n[1] && n[1] > n[2]);
	}
	private boolean rule3(int[] n)
	{
		return ((n[0] + n[2] + n[6] + n[8]) > (n[1] + n[3] + n[4] + n[5] + n[7]));
	}
	private boolean rule4(int[] n, String c)
	{
		for(int i = 0; i < n.length; i++)
		{
			if(n[i] == 1)
				return (c.charAt(i) == 'B');
		}
		return false;
	}
	private boolean rule5(int[] n)
	{
		int sum = 0;
		for(int i = 0; i < 3; i++)
		{
			if(n[i] == 2 || n[i] == 3 || n[i] == 5 || n[i] == 7)
				sum++;
		}
		return (sum > 1);
	}
	private boolean rule6(int[] n)
	{
		for(int i = 0; i < 3; i++)
		{
			if(((n[i] + n[i + 3]) % 10) == n[i + 6])
				return true;
		}
		return false;
	}
	private boolean rule7(int[] n)
	{
		return ((n[0] + n[8]) != (n[2] + n[6]));
	}
	private boolean rule8(String c)
	{
		String temp = c.charAt(0) + "" + c.charAt(2) + "" + c.charAt(6) + "" + c.charAt(8);
		ArrayList<Character> check = new ArrayList<Character>();
		for(char letter : temp.toCharArray())
		{
			if(!(check.contains(letter)))
				check.add(letter);
		}
		return (check.size() <= 2);
	}
	private int mod(int n, int m)
	{
		while(n < 0)
			n += m;
		return (n % m);
	}
	private boolean v1(String i)
	{
		if(i.length() == 9)
		{
			String check = "123456789";
			char[] temp = i.toCharArray();
			Arrays.sort(temp);
			for(int aa = 0; aa < temp.length; aa++)
			{
				if(temp[aa] != check.charAt(aa))
					return false;
			}
			return true;
		}
		return false;
	}
	private boolean v2(String i)
	{
		if(i.length() == 9)
		{
			for(char letter : i.toCharArray())
			{
				if("RBGY".indexOf(letter) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
}

