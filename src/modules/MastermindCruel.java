package modules;

import javax.swing.JOptionPane;

import start.BombConfig;
import start.BombEdgework;

public class MastermindCruel 
{
	private final String[] table = {"AB", "CA", "BC", "BA", "CB", "AC"};
	private final BombConfig cf;
	private final BombEdgework ew;
	public MastermindCruel(BombConfig c, BombEdgework e)
	{
		cf = c;
		ew = e;
	}
	public void run()
	{
		//First, figure out how many colors are in the solution
		String queries = "WMYGRB";
		int[] numColors = {0, 0, 0, 0, 0, 0};
		int sum = 0;
		int cur = 4;
		for(int aa = 0; aa < 5; aa++)
		{
			int A = getA(queries.charAt(aa) + " " + queries.charAt(aa) + " " + queries.charAt(aa) + " " + queries.charAt(aa) + " " + queries.charAt(aa));
			while((sum + A) > 5)
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				A = getA(queries.charAt(aa) + " " + queries.charAt(aa) + " " + queries.charAt(aa) + " " + queries.charAt(aa) + " " + queries.charAt(aa) + " " + queries.charAt(aa));
			}
			numColors[aa] += A;
			sum += A;
			if(sum == 5)
			{
				cur = aa;
				break;
			}
		}
		if(sum < 5)
			numColors[5] = 5 - sum;
		//System.out.println(numColors[0] + " " + numColors[1] + " " + numColors[2] + " " + numColors[3] + " " + numColors[4] + " " + numColors[5]);
		//Next, find a color that isn't in the solution to be used as a control color
		while(numColors[cur] != 0)
			cur = (cur + 1) % 6;
		String solution = getSolution(queries.charAt(cur), numColors);
		JOptionPane.showMessageDialog(null, "Solution: " + solution.charAt(0) + " " + solution.charAt(1) + " " + solution.charAt(2) + " " + solution.charAt(3) + " " + solution.charAt(4) + "\nSet the colors above then\npress the Submit button");
	}
	private String getSolution(char c, int[] NC)
	{
		String solution = c + "" + c + "" + c + "" + c + "" + c;
		int sum = 0;
		String order = "WMYGRB";
		int[] numColors = new int[NC.length];
		for(int aa = 0; aa < NC.length; aa++)
			numColors[aa] = NC[aa] + 0;
		for(int aa = 0; aa < 6; aa++)
		{
			if(numColors[aa] > 0)
			{
				//Fill in the rest of the spaces with the remaining colors
				if(numColors[aa] == (5 - sum))
				{
					solution = solution.replace(c, order.charAt(aa));
					break;
				}
				int colorCount = 5 - sum;
				for(int bb = 0; colorCount > 0 && numColors[aa] > 0; bb++)
				{
					if(colorCount == numColors[aa])
					{
						//System.out.println("HERE");
						int tempCur = 4;
						while(numColors[aa] > 0)
						{
							if(solution.charAt(tempCur) == c)
							{
								solution = solution.substring(0, tempCur) + "" + order.charAt(aa) + "" + solution.substring(tempCur + 1);
								numColors[aa]--;
								sum++;
							}
							tempCur--;
						}
					}
					else if(solution.charAt(bb) == c)
					{
						colorCount--;
						String temp = solution.substring(0, bb) + "" + order.charAt(aa) + "" + solution.substring(bb + 1);
						int A = getA(temp.charAt(0) + " " + temp.charAt(1) + " " + temp.charAt(2) + " " + temp.charAt(3) + " " + temp.charAt(4));
						while(A > (sum + 1))
						{
							JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
							A = getA(temp.charAt(0) + " " + temp.charAt(1) + " " + temp.charAt(2) + " " + temp.charAt(3) + " " + temp.charAt(4));
						}
						if(A > sum)
						{
							numColors[aa]--;
							solution = temp.toUpperCase();
							sum++;
						}
					}
				}
			}
		}
		return solution;
	}
	private int getA(String q)
	{
		String input = JOptionPane.showInputDialog("Query: " + q + "\nEnter the colors/numbers (spaces):").toUpperCase();
		boolean v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Query: " + q + "\nEnter the colors/numbers (spaces):").toUpperCase();
			v = valid(input);
		}
		String[] spl = input.split(" ");
		String c = spl[0].charAt(0) + "" + spl[1].charAt(0);
		int[] n = {Integer.parseInt(spl[0].substring(1)), Integer.parseInt(spl[1].substring(1))};
		String letters = table["WMYGRB".indexOf(c.charAt(0))];
		switch(c.charAt(1))
		{
			case 'W':
				n[0] = n[0] - ew.BAT();
				n[1] = n[1] - getNumberInput("Enter the number of solves");
				break;
			case 'M':
				n[0] = n[0] - ew.numLit();
				n[1] = n[1] - ew.getSNDIG(ew.numSNDIGS() - 1);
				break;
			case 'Y':
				n[0] = n[0] - ew.getSNSUM();
				n[1] = n[1] - ew.numPorts();
				break;
			case 'G':
				n[0] = n[0] - cf.getNumberModules();
				n[1] = n[1] - ew.numUnlit();
				break;
			case 'R':
				n[0] = n[0] - ew.numPortTypes();
				n[1] = n[1] - getNumberInput("Enter the number of strikes:");
				break;
			default:
				n[0] = n[0] - ew.getSNDIG(0);
				n[1] = n[1] - ew.BH();
				break;
		}
		if(n[0] < 0 || n[1] < 0)
			return 6;
		else if(letters.contains("A"))
			return n[letters.indexOf("A")];
		else
			return (5 - (n[0] + n[1]));
	}
	private int getNumberInput(String out)
	{
		String input = JOptionPane.showInputDialog(out);
		boolean v = isNum(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog(out);
			v = isNum(input);
		}
		return (Integer.parseInt(input));
	}
	private boolean isNum(String i)
	{
		if(i.length() > 0)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("0123456789".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
	private boolean valid(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 2)
		{
			for(int aa = 0; aa < 2; aa++)
			{
				if("WMYGRB".indexOf(conv[aa].charAt(0)) < 0)
					return false;
				if(!(isNum(conv[aa].substring(1))))
					return false;
			}
			return true;
		}
		return false;
	}
}
