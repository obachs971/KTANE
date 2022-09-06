package modules;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import start.BombConfig;

public class Lasers 
{
	private final BombConfig con;
	public Lasers(BombConfig c)
	{
		con = c;
	}
	public String run()
	{
		String str = JOptionPane.showInputDialog("Enter the numbers in reading order:").replace(" ", "");
		String sol = begin(str);
		while(sol.length() == 0)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			str = JOptionPane.showInputDialog("Enter the numbers\nin reading order:").replace(" ", "");
			sol = begin(str);
		}
		JOptionPane.showMessageDialog(null, "Press these hatches:\n" + sol);
		return ("HATCH PLACEMENT:\n" + str.charAt(0) + " " + str.charAt(1) + " " + str.charAt(2) + "\n" + str.charAt(3) + " " + str.charAt(4) + " " + str.charAt(5) + "\n" + str.charAt(6) + " " + str.charAt(7) + " " + str.charAt(8));
	}
	private String begin(String i)
	{
		if(i.length() == 9)
		{
			char[] arr = i.toCharArray();
			Arrays.sort(arr);
			String conv = new String(arr);
			if(conv.equals("123456789"))
			{
				int[] hatches = new int[9];
				for(int aa = 0; aa < 9; aa++)
					hatches[aa] = (i.charAt(aa) - '0');
				return red(hatches);
			}
		}
		return "";
	}
	private String red(int[] hatches)
	{
		int forbid = 0;
		for(int i = 0; i < 3; i++)
			forbid += hatches[i];
		forbid = getDigitalRoot(forbid + "");
		for(int i = 0; i < hatches.length; i++)
		{
			if(hatches[i] == forbid)
			{
				forbid = i / 3;
				break;
			}
		}
		for(int i = 0; i < hatches.length; i++)
		{
			if((i / 3) != forbid)
			{
				ArrayList<Integer> pressed = new ArrayList<Integer>();
				pressed.add(hatches[i]);
				String sol = orange(hatches, pressed, i);
				if(sol.length() != 0)
					return sol;
			}
		}
		return "";
	}
	private String orange(int[] hatches, ArrayList<Integer> p, int pos)
	{
		ArrayList<Integer> forbid = new ArrayList<Integer>();
		forbid.add(p.get(0));
		switch(pos)
		{
			case 0:
				forbid.add(hatches[1]);
				forbid.add(hatches[3]);
				break;
			case 1:
				forbid.add(hatches[0]);
				forbid.add(hatches[2]);
				forbid.add(hatches[4]);
				break;
			case 2:
				forbid.add(hatches[1]);
				forbid.add(hatches[5]);
				break;
			case 3:
				forbid.add(hatches[0]);
				forbid.add(hatches[4]);
				forbid.add(hatches[6]);
				break;
			case 4:
				forbid.add(hatches[1]);
				forbid.add(hatches[3]);
				forbid.add(hatches[5]);
				forbid.add(hatches[7]);
				break;
			case 5:
				forbid.add(hatches[2]);
				forbid.add(hatches[4]);
				forbid.add(hatches[8]);
				break;
			case 6:
				forbid.add(hatches[3]);
				forbid.add(hatches[7]);
				break;
			case 7:
				forbid.add(hatches[4]);
				forbid.add(hatches[6]);
				forbid.add(hatches[8]);
				break;
			default:
				forbid.add(hatches[5]);
				forbid.add(hatches[7]);
				break;
		}
		for(int i = 0; i < hatches.length; i++)
		{
			if(!(forbid.contains(hatches[i])))
			{
				ArrayList<Integer> pressed = new ArrayList<Integer>();
				for(int press : p)
					pressed.add(press);
				pressed.add(hatches[i]);
				String sol = yellow(hatches, pressed);
				if(sol.length() != 0)
					return sol;
			}
		}
		return "";
	}
	private String yellow(int[] hatches, ArrayList<Integer> p)
	{
		ArrayList<Integer> forbid = new ArrayList<Integer>();
		for(int press : p)
			forbid.add(press);
		int check = 0;
		for(int i = 0; i < 9; i++)
		{
			if(i % 3 > 0)
				check += hatches[i];
		}
		check = getDigitalRoot(check + "");
		for(int i = 0; i < hatches.length; i++)
		{
			if(hatches[i] == check)
			{
				forbid.add(hatches[i % 3]);
				forbid.add(hatches[(i % 3) + 3]);
				forbid.add(hatches[(i % 3) + 6]);
				break;
			}
		}
		for(int i = 0; i < hatches.length; i++)
		{
			if(!(forbid.contains(hatches[i])))
			{
				ArrayList<Integer> pressed = new ArrayList<Integer>();
				for(int press : p)
					pressed.add(press);
				pressed.add(hatches[i]);
				String sol = green(hatches, pressed, i);
				if(sol.length() != 0)
					return sol;
			}
		}
		return "";
	}
	private String green(int[] hatches, ArrayList<Integer> p, int pos)
	{
		ArrayList<Integer> accept = new ArrayList<Integer>();
		switch(pos)
		{
			case 0:
				accept.add(hatches[4]);
				break;
			case 1:
				accept.add(hatches[3]);
				accept.add(hatches[5]);
				break;
			case 2:
				accept.add(hatches[4]);
				break;
			case 3:
				accept.add(hatches[1]);
				accept.add(hatches[7]);
				break;
			case 4:
				accept.add(hatches[1]);
				accept.add(hatches[2]);
				accept.add(hatches[6]);
				accept.add(hatches[8]);
				break;
			case 5:
				accept.add(hatches[1]);
				accept.add(hatches[7]);
				break;
			case 6:
				accept.add(hatches[4]);
				break;
			case 7:
				accept.add(hatches[3]);
				accept.add(hatches[5]);
				break;
			default:
				accept.add(hatches[4]);
				break;
		}
		for(int i = 0; i < hatches.length; i++)
		{
			if(accept.contains(hatches[i]) && !(p.contains(hatches[i])))
			{
				ArrayList<Integer> pressed = new ArrayList<Integer>();
				for(int press : p)
					pressed.add(press);
				pressed.add(hatches[i]);
				String sol = blue(hatches, pressed);
				if(sol.length() != 0)
					return sol;
			}
		}
		return "";
	}
	private String blue(int[] hatches, ArrayList<Integer> p)
	{
		ArrayList<Integer> forbid = new ArrayList<Integer>();
		for(int press : p)
			forbid.add(press);
		int check = getDigitalRoot((con.getStartingBombMinutes() + 1) + "");
		for(int i = 0; i < hatches.length; i++)
		{
			if(hatches[i] == check)
			{
				int row = i / 3, col = i % 3;
				forbid.add(hatches[col]);
				forbid.add(hatches[col + 3]);
				forbid.add(hatches[col + 6]);
				forbid.add(hatches[row * 3]);
				forbid.add(hatches[row * 3 + 1]);
				forbid.add(hatches[row * 3 + 2]);
				break;
			}
		}
		for(int i = 0; i < hatches.length; i++)
		{
			if(!(forbid.contains(hatches[i])))
			{
				ArrayList<Integer> pressed = new ArrayList<Integer>();
				for(int press : p)
					pressed.add(press);
				pressed.add(hatches[i]);
				String sol = purple(hatches, pressed, i);
				if(sol.length() != 0)
					return sol;
			}
		}
		return "";
	}
	private String purple(int[] hatches, ArrayList<Integer> p, int pos)
	{
		ArrayList<Integer> forbid = new ArrayList<Integer>();
		for(int press : p)
			forbid.add(press);
		int check = con.getNumberModules() % 2;
		for(int i = 0; i < 5; i++)
			forbid.add(i * 2 + check);
		for(int i = 0; i < hatches.length; i++)
		{
			if(!(forbid.contains(hatches[i])))
			{
				ArrayList<Integer> pressed = new ArrayList<Integer>();
				for(int press : p)
					pressed.add(press);
				pressed.add(hatches[i]);
				String sol = white(hatches, pressed, pos);
				if(sol.length() != 0)
					return sol;
			}
		}
		return "";
	}
	private String white(int[] hatches, ArrayList<Integer> p, int pos)
	{
		ArrayList<Integer> forbid = new ArrayList<Integer>();
		for(int press : p)
			forbid.add(press);
		forbid.add(hatches[4]);
		switch(pos)
		{
			case 0:
				forbid.add(hatches[1]);
				forbid.add(hatches[3]);
				break;
			case 1:
				forbid.add(hatches[0]);
				forbid.add(hatches[2]);
				forbid.add(hatches[3]);
				forbid.add(hatches[5]);
				break;
			case 2:
				forbid.add(hatches[1]);
				forbid.add(hatches[5]);
				break;
			case 3:
				forbid.add(hatches[0]);
				forbid.add(hatches[1]);
				forbid.add(hatches[6]);
				forbid.add(hatches[7]);
				break;
			case 4:
				return "";
			case 5:
				forbid.add(hatches[1]);
				forbid.add(hatches[2]);
				forbid.add(hatches[7]);
				forbid.add(hatches[8]);
				break;
			case 6:
				forbid.add(hatches[3]);
				forbid.add(hatches[7]);
				break;
			case 7:
				forbid.add(hatches[3]);
				forbid.add(hatches[5]);
				forbid.add(hatches[6]);
				forbid.add(hatches[8]);
				break;
			default:
				forbid.add(hatches[5]);
				forbid.add(hatches[7]);
				break;
		}
		for(int i = 0; i < hatches.length; i++)
		{
			if(!(forbid.contains(hatches[i])))
				return p.get(0) + " " + p.get(1) + " " + p.get(2) + " " + p.get(3) + " " + p.get(4) + " " + p.get(5) + " " + hatches[i]; 
		}
		return "";
	}
	private int getDigitalRoot(String n)
	{
		int sum = 0;
		for(char digit : n.toCharArray())
			sum += (digit - '0');
		while(sum > 9)
		{
			n = sum + "";
			sum = 0;
			for(char digit : n.toCharArray())
				sum += (digit - '0');
		}
		return sum;
	}
}
