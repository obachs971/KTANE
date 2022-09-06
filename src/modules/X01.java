package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class X01 
{
	private int[][] chart =
		{
				{74, 53, 79},
				{62, 41, 70},
				{42, 47, 86},
				{38, 66, 51},
				{80, 67, 58}
		};
	private final BombEdgework ew;
	public X01(BombEdgework e)
	{
		ew = e;
	}
	
	public void run()
	{
		int row = getPosition(new int[] {3, 5, 6, 8}, ew.BA() + ew.numSNDIGS());
		int col = getPosition(new int[] {3, 6}, ew.numInd() + ew.numPorts());
		//Input Dart Values
		String input = JOptionPane.showInputDialog("Starting at the Top-Left Red/Black\nEnter the 10 numbers in\nclockwise order (spaces):");
		boolean v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Starting at the Top-Left Red/Black\nEnter the 10 numbers in\nclockwise order (spaces):");
			v = valid(input);
		}
		String[] temp = input.split(" ");
		int[] nums = new int[10];
		for(int i = 0; i < 10; i++)
			nums[i] = Integer.parseInt(temp[i]);
		//Calculate Dart Score
		int over10 = 0;
		int redblack = 0;
		int greentan = 0;
		for(int aa = 0; aa < 10; aa++)
		{
			if(aa % 2 == 0)
				redblack += nums[aa];
			else
				greentan += nums[aa];
			if(nums[aa] > 10)
				over10++;
		}
		int score = chart[row][col];
		if(redblack > greentan)
			score += 10;
		else if(greentan > redblack)
			score -= 8;
		else
			score = 69;
		//Get a list of ALL the possible scores
		int[] list = new int[32];
		for(int i = 0; i < 30; i++)
			list[i] = nums[i % 10] * ((i / 10) + 1);
		list[30] = 25;
		list[31] = 50;
		//Determine the Rules
		boolean under6 = false;
		boolean over15 = false;
		boolean odd4 = false;
		boolean even3 = false;
		for(int bb = 0; bb < 10; bb++)
		{
			int n1 = nums[bb];
			int n2 = nums[(bb + 1) % 10];
			int n3 = nums[(bb + 2) % 10];
			int n4 = nums[(bb + 3) % 10];
			if((n1 <= 6) && (n2 <= 6) && (n3 <= 6))
				under6 = true;
			if((n1 >= 15) && (n2 >= 15) && (n3 >= 15))
				over15 = true;
			if((n1 % 2 == 1) && (n2 % 2 == 1) && (n3 % 2 == 1) && (n4 % 2 == 1))
				odd4 = true;
			if((n1 % 2 == 0) && (n2 % 2 == 0) && (n3 % 2 == 0))
				even3 = true;
		}
		//Get Solution
		String solution;
		if(under6)
			solution = dart3(list, "CG*", score);
		else if(over15)
			solution = dart4(list, "DH*", score);
		else if(odd4)
			solution = dart3(list, "AF*", score);
		else if(even3)
			solution = dart4(list, "BD*", score);
		else if(ew.numCharsInSN("MVG") > 0)
			solution = dart4(list, "CEI*", score);
		else if(over10 == 5)
			solution = dart3(list, "GH*", score);
		else if(score <= 45)
			solution = dart2(list, "*", score);
		else
			solution = dart3(list, "BEI*", score);
		JOptionPane.showMessageDialog(null, solution);
	}
	private String dart2(int[] l, String r, int score)
	{
		String solution = "null";
		int[] cur = new int[2];
		for(int aa = 0; aa < l.length; aa++)
		{
			for(int bb = aa + 1; bb < l.length; bb++)
			{
				cur[0] = aa;
				cur[1] = bb;
				int sum = l[cur[0]] + l[cur[1]];
				if(sum == score)
				{
					solution = rules(cur, r, l);
				}
				if(!(solution.equals("null")))
				{
					break;
				}
			}
			if(!(solution.equals("null")))
			{
				break;
			}
		}
		return solution;
	}
	private String dart3(int[] l, String r, int score)
	{
		String solution = "null";
		int[] cur = new int[3];
		for(int aa = 0; aa < l.length; aa++)
		{
			for(int bb = aa + 1; bb < l.length; bb++)
			{
				for(int cc = bb + 1; cc < l.length; cc++)
				{
					
						cur[0] = aa;
						cur[1] = bb;
						cur[2] = cc;
						
						int sum = l[cur[0]] + l[cur[1]] + l[cur[2]];
						if(sum == score)
						{
							solution = rules(cur, r, l);
						}
						if(!(solution.equals("null")))
						{
							//system.out.println(solution);
							break;
						}
				}
				if(!(solution.equals("null")))
				{
					break;
				}
			}
			if(!(solution.equals("null")))
			{
				break;
			}
		}
		return solution;
	}
	private String dart4(int[] l, String r, int score)
	{
		String solution = "null";
		int[] cur = new int[4];
		for(int aa = 0; aa < l.length; aa++)
		{
			for(int bb = aa + 1; bb < l.length; bb++)
			{
				for(int cc = bb + 1; cc < l.length; cc++)
				{
					for(int dd = cc + 1; dd < l.length; dd++)
					{
						cur[0] = aa;
						cur[1] = bb;
						cur[2] = cc;
						cur[3] = dd;
						int sum = l[cur[0]] + l[cur[1]] + l[cur[2]] + l[cur[3]];
						if(sum == score)
							solution = rules(cur, r, l);
						if(!(solution.equals("null")))
							break;
					}
					if(!(solution.equals("null")))
						break;
				}
				if(!(solution.equals("null")))
					break;
			}
			if(!(solution.equals("null")))
				break;
		}
		return solution;
	}
	private String rules(int[] cur, String r, int[] l)
	{
		String solution = "null";
		boolean[] bools = new boolean[r.length()];
		boolean brule = false;
		for(int aa = 0; aa < r.length(); aa++)
		{
			switch(r.charAt(aa))
			{
				case 'A':
					bools[aa] = true;
					for(int bb = 0; bb < cur.length; bb++)
					{
						//system.out.println(cur[bb]);
						if((cur[bb] < 10) && (l[cur[bb]] % 2 == 1))
						{
							bools[aa] = false;
							break;
						}
					}
					//System.out.println("A: " + bools[aa]);
					break;
				case 'B':
					brule = true;
					bools[aa] = false;
					int topDou = -1;
					for(int bb = 0; bb < cur.length; bb++)
					{
						if(cur[bb] >= 10 && cur[bb] <= 14)
						{
							topDou = bb;
							break;
						}
					}
					if(topDou != -1)
					{
						bools[aa] = true;
						cur = switchCur(cur, topDou);
					}
					//system.out.println("B: " + bools[aa]);
					break;
				case 'C':
					bools[aa] = false;
					for(int cc = 0; cc < cur.length; cc++)
					{
						//system.out.println(cur[cc]);
						if(cur[cc] >= 15 && cur[cc] <= 19)
						{
							bools[aa] = true;
							break;
						}
					}
					//system.out.println("C: " + bools[aa]);
					break;
				case 'D':
					bools[aa] = false;
					if(brule)
					{
						int greDou = -1;
						for(int cc = 0; cc < cur.length; cc++)
						{
							//system.out.println(cur[cc]);
							if(cur[cc] == 11 || cur[cc] == 13)
							{
								greDou = cc;
								break;
							}
						}
						if(greDou != -1)
						{
							bools[aa] = true;
							cur = switchCur(cur, greDou);
						}
					}
					else
					{
						brule = true;
						int greDou = -1;
						for(int cc = 0; cc < cur.length; cc++)
						{
							//system.out.println(cur[cc]);
							if(cur[cc] >= 11 && cur[cc] <= 19 && cur[cc] % 2 == 1)
							{
								greDou = cc;
								break;
							}
						}
						if(greDou != -1)
						{
							bools[aa] = true;
							cur = switchCur(cur, greDou);
						}
					}
					//system.out.println("D: " + bools[aa]);
					break;
				case 'E':
					int n1 = 0;
					for(int jj = 0; jj < cur.length; jj++)
					{
						//system.out.println(cur[jj]);
						if(cur[jj] == 30)
							n1++;
					}
					bools[aa] = (n1 == 1);
					//system.out.println("E: " + bools[aa]);
					break;
				case 'F':
					bools[aa] = false;
					for(int cc = 0; cc < cur.length; cc++)
					{
						//system.out.println(cur[cc]);
						if(cur[cc] > 19 && cur[cc] < 30)
						{
							bools[aa] = true;
							break;
						}
					}
					//system.out.println("F: " + bools[aa]);
					break;
				case 'G':
					int[] n3 = {0, 0, 0}; 
					for(int cc = 0; cc < cur.length; cc++)
					{
						////system.out.println(cur[cc]);
						if(cur[cc] >= 0 && cur[cc] < 10)
							n3[0]++;
						if(cur[cc] > 9 && cur[cc] < 20)
							n3[1]++;
						if(cur[cc] > 19 && cur[cc] < 30)
							n3[2]++;
					}
					bools[aa] = (n3[0] > 0) && (n3[1] > 0) && (n3[2] > 0);
					//System.out.println("G: " + bools[aa]);
					break;
				case 'H':
					bools[aa] = false;
					for(int cc = 0; cc < cur.length; cc++)
					{
						//System.out.println(cur[cc]);
						if(cur[cc] > 19 && cur[cc] < 30 && l[cur[cc]] % 6 == 0)
						{
							bools[aa] = true;
							break;
						}
					}
					//System.out.println("H: " + bools[aa]);
					break;
				case 'I':
					bools[aa] = true;
					if(l[cur[0]] == l[cur[1]])
						bools[aa] = false;
					if(l[cur[0]] == l[cur[2]])
						bools[aa] = false;
					if(l[cur[1]] == l[cur[2]])
						bools[aa] = false;
					//System.out.println("I: " + bools[aa]);
					break;
				default:
					if(brule)
						bools[aa] = true;
					else
					{
						bools[aa] = false;
						int dou = -1;
						for(int ff = 0; ff < cur.length; ff++)
						{
							if(cur[ff] >= 10 && cur[ff] <= 19)
							{
								dou = ff;
								break;
							}
						}
						if(dou != -1)
						{
							bools[aa] = true;
							cur = switchCur(cur, dou);
						}
					}
					//System.out.println("*: " + bools[aa]);
			}
		}//EOFL
		boolean bool = true;
		for(int gg = 0; gg < bools.length; gg++)
			bool = bool && bools[gg];
		if(!bool)
			solution = "null";
		else
		{
			solution = "";
			for(int ii = 0; ii < cur.length; ii++)
				solution = solution + "" + getSegment(cur[ii], l) + "\n";
		}
		return solution;
	}
	private int[] switchCur(int[] c, int p)
	{
		int num = c[c.length - 1];
		c[c.length - 1] = c[p];
		c[p] = num;
		return c;
	}
	private String getSegment(int c, int[] l)
	{
		switch(c)
		{
			case 30: return "GREEN BULLSEYE";
			case 31: return "RED BULLSEYE";
			default:
				if(c < 10)
					return "SINGLE " + l[c % 10];
				else if(c < 20)
					return "DOUBLE " + l[c % 10];
				else
					return "TREBLE " + l[c % 10];
		}
	}
	private int getPosition(int[] n, int sum)
	{
		for(int i = 0; i < n.length; i++)
		{
			if(sum < n[i])
				return i;
		}
		return n.length;
	}
	private boolean valid(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 10)
		{
			for(String str : conv)
			{
				if(str.length() == 0)
					return false;
				for(char c : str.toCharArray())
				{
					if("0123456789".indexOf(c) < 0)
						return false;
				}
			}
			for(int aa = 0; aa < conv.length; aa++)
			{
				int n1 = Integer.parseInt(conv[aa]);
				for(int bb = aa + 1; bb < conv.length; bb++)
				{
					int n2 = Integer.parseInt(conv[bb]);
					if(n1 == n2)
						return false;
				}
			}
			return true;
		}
		return false;
	}
}
