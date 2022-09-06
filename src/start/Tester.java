package start;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Function;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import misc.PlayType;
import modules.*;
import java.util.List;
public class Tester
{
	
	public static void main (String[] args) throws Exception
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = (int)screenSize.getHeight();
		int screenWidth = (int)screenSize.getWidth();
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Dialog", Font.BOLD, (int)screenHeight / 80)));
		double resizer = 1920.0 / screenWidth;
		
		//Random r = new Random();
		//System.out.print((r.nextInt(4) + 1));
		//System.out.println((r.nextInt(3) + 1));
		
		
		//BombConfig con = new BombConfig(0);
		BombEdgework e = new BombEdgework(resizer);
		int timeZone = 0;
		int num = 0;
		while(num == 0)
		{
		    MysticSquare test = new MysticSquare(e, PlayType.EFM, false);
		    test.run();
			
			//num = 1;
		}
		/*
		String[] list = 
			{
					"13/1",
					"30/1",
					"14/2",
					"1/4",
					"4/4",
					"12/4",
					"30/4",
					"11/5",
					"13/5",
					"27/5",
					"5/6",
					"25/6",
					"4/7",
					"16/7",
					"27/7",
					"28/7",
					"2/8",
					"20/8",
					"1/9",
					"2/9",
					"4/9",
					"11/9",
					"2/10",
					"16/10",
					"1/11",
					"11/11",
					"20/11",
					"7/12",
					"18/12"
			};
		int[] vals = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		for(int i = 1; i < vals.length; i++)
		{
			for(int j = 1; j <= vals[i]; j++)
			{
				String check = j + "/" + i;
				if(checking(list, check))
				{
					int num = j - 1;
					for(int k = 1; k < i; k++)
						num += vals[k];
					System.out.println(num);
				}
			}
		}*/
		/*
		File moveList = new File("files/Tester.txt");
		Scanner reader = new Scanner(moveList);
		String finder = reader.nextLine();
		int cur = 0;
		
		for(int i = 0; i < 2000; i++)
		{
			int index = finder.indexOf("\"Symbol\":", cur);
			cur = finder.indexOf(",", index);
			System.out.print("\"" + finder.substring(index + 10, cur - 1).toUpperCase() + "\",");
			if((i + 1) % 10 == 0)
				System.out.println("");
		}*/
		System.exit(0);
	}
	public static boolean checking(String[] arr, String str)
	{
		for(String s : arr)
		{
			if(str.equals(s))
				return true;
		}
		return false;
	}
	public static int getScore(String[] arr)
	{
		int score = 0;
		boolean flag = false;
		for(int i = 0; i <= 65535; i++)
		{
			System.out.println(i);
			String grid = decToBin(i + 0);
			while(grid.length() < 16)
				grid = "0" + grid;
			for(int j = 1; j < 27; j++)
			{
				String bin = decToBin(j + 0);
				while(bin.length() < 5)
					bin = "0" + bin;
				flag = false;
				for(int len = 0; len < arr.length; len++)
				{
					for(int con = 0; con < 16; con++)
					{
						String temp = arr[len].toUpperCase();
						for(int cc = 0; cc < (con / 4); cc++)
							temp = down(temp);
						for(int dd = 0; dd < (con % 4); dd++)
							temp = right(temp.toCharArray());
						String com = "";
						for(int zz = 0; zz < temp.length(); zz++)
						{
							if(temp.charAt(zz) == 'X')
								com = com + "" + grid.charAt(zz);
						}
						if(com.equals(bin))
						{
							flag = true;
							break;
						}
					}
					if(flag)
						break;
				}
				if(!(flag))
					break;
			}
			if(flag)
				score++;
			
		}
		return score;
	}
	private static String decToBin(int n)
	{
		if(n == 0)
			return "0";
		String str = "";
		while(n > 0)
		{
			str = (n % 2) + "" + str;
			n /= 2;
		}
		return str;
	}
	public static String right(char[] s)
	{
		return s[3] + "" + s[0] + "" + s[1] + "" + s[2] + "" + s[7] + "" + s[4] + "" + s[5] + "" + s[6] + "" + s[11] + "" + s[8] + "" + s[9] + "" + s[10] + "" + s[15] + "" + s[12] + "" + s[13] + "" + s[14];
	}
	public static String down(String s)
	{
		return s.substring(12, 16) + s.substring(0, 12);
	}
	public static String right(String s)
	{
		return (s.charAt(s.length() - 1) + "" + s.substring(0, s.length() - 1));
	}
	public static String left(String s)
	{
		return (s.substring(1) + "" + s.charAt(0));
	}
	public static int GCD(int a, int b)
	{
		int r = a % b;
		while(r > 0)
		{
			a = b;
			b = r;
			r = a % b;
		}
		return b;
	}
	public static void Levels()
	{
		String ans = "ANSWER";
		
		String nums = "";
		Random r = new Random();
		for(int aa = 0; aa < ans.length(); aa++)
		{
			switch(ans.charAt(aa))
			{
				case 'A': nums = nums + "" + new String[] {"00", "01", "10"}[r.nextInt(3)];
				case 'B': nums = nums + "11";
				case 'C': nums = nums + "" + new String[] {"12", "21"}[r.nextInt(2)];
				case 'D': nums = nums + "" + new String[] {"13", "31"}[r.nextInt(2)];
				case 'E': nums = nums + "" + new String[] {"02", "03", "20", "30"}[r.nextInt(4)];
				
			}
		}
	}
	public static ArrayList<String> getList(String[][] chart)
	{
		String a1 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random r = new Random();
		ArrayList<String> list = new ArrayList<String>();
		for(int aa = 0; aa < 36; aa++)
		{
			for(int bb = 0; bb < 36; bb++)
			{
				list.add(a1.charAt(aa) + "" + a1.charAt(bb));
			}
		}
		for(int aa = 0; aa < chart.length; aa++)
		{
			for(int bb = 0; bb < chart[aa].length; bb++)
				list.remove(chart[aa][bb]);
		}
		return list;
	}
	public static boolean check(String[][] grid, ArrayList<String> list)
	{
		for(int aa = 0; aa < list.size(); aa++)
		{
			for(int bb = 0; bb < grid.length; bb++)
			{
				for(int cc = 0; cc < 2; cc++)
				{
					if(list.get(aa).charAt(cc) == grid[bb][aa].charAt(cc))
						return false;
				}
			}
		}
		return true;
	}
	public static int[] remove(int[] s, int i)
	{
		int[] conv = new int[s.length - 1];
		int items = 0;
		for(int aa = 0; aa < s.length; aa++)
		{
			if(s[aa] != i)
			{
				conv[items] = s[aa];
				items++;
			}
		}
		return conv;
	}
	public static boolean check(String[] i, String s)
	{
		for(int aa = 0; aa < i.length; aa++)
		{
			for(int bb = 0; bb < 7; bb++)
			{
				if(i[aa].charAt(bb) == s.charAt(bb))
					return true;
			}
		}
		return false;
	}
	public static void mazeMaker(String[][] maze)
	{
		for(int aa = 0; aa < maze.length; aa++)
		{
			for(int bb = 0; bb < maze[aa].length; bb++)
			{
				if(maze[aa][bb].length() > 1)
				{
					System.out.print("{\"" + maze[aa][bb].substring(3, maze[aa][bb].length() - 4) + "\"");
					if(aa >= 2 && maze[aa - 2][bb].length() > 1)
						System.out.print(", \"0" + maze[aa - 2][bb].substring(3, maze[aa - 2][bb].length() - 4) + "\"");
					if(aa >= 1 && bb < maze[aa].length - 1 && maze[aa - 1][bb + 1].length() > 1)
						System.out.print(", \"1" + maze[aa - 1][bb + 1].substring(3, maze[aa - 1][bb + 1].length() - 4) + "\"");
					if(aa < maze.length - 1 && bb < maze[aa].length - 1 && maze[aa + 1][bb + 1].length() > 1)
						System.out.print(", \"2" + maze[aa + 1][bb + 1].substring(3, maze[aa + 1][bb + 1].length() - 4) + "\"");
					if(aa < maze.length - 2 && maze[aa + 2][bb].length() > 1)
						System.out.print(", \"3" + maze[aa + 2][bb].substring(3, maze[aa + 2][bb].length() - 4) + "\"");
					if(aa < maze.length - 1 && bb >= 1 && maze[aa + 1][bb - 1].length() > 1)
						System.out.print(", \"4" + maze[aa + 1][bb - 1].substring(3, maze[aa + 1][bb - 1].length() - 4) + "\"");
					if(aa >= 1 && bb >= 1 && maze[aa - 1][bb - 1].length() > 1)
						System.out.print(", \"5" + maze[aa - 1][bb - 1].substring(3, maze[aa - 1][bb - 1].length() - 4) + "\"");
					System.out.println("},");
				}
			}
		}
	}
	public static boolean mazeSolver(String[] maze, int[] WD, int[] goal)
	{
		//System.out.println("---------------------------------------");
		String dir = "";
		while(true)
		{
			if(maze[WD[1] - 1].charAt(WD[0]) != 'W')//UP
			{
				dir = dir + "U";
				maze[WD[1] - 1] = maze[WD[1] - 1].substring(0, WD[0]) + "W" + maze[WD[1] - 1].substring(WD[0] + 1);
				WD[1] -= 2;
			}
			else if(maze[WD[1]].charAt(WD[0] + 1) != 'W')//RIGHT
			{
				dir = dir + "R";
				maze[WD[1]] = maze[WD[1]].substring(0, WD[0] + 1) + "W" + maze[WD[1]].substring(WD[0] + 2);
				WD[0] += 2;
			}
			else if(maze[WD[1] + 1].charAt(WD[0]) != 'W')//DOWN
			{
				dir = dir + "D";
				maze[WD[1] + 1] = maze[WD[1] + 1].substring(0, WD[0]) + "W" + maze[WD[1] + 1].substring(WD[0] + 1);
				WD[1] += 2;
			}
			else if(maze[WD[1]].charAt(WD[0] - 1) != 'W')//LEFT
			{
				dir = dir + "L";
				maze[WD[1]] = maze[WD[1]].substring(0, WD[0] - 1) + "W" + maze[WD[1]].substring(WD[0]);
				WD[0] -= 2;
			}
			else//DEADEND
			{
				if(dir.length() == 0)
					break;
				switch(dir.charAt(dir.length() - 1))
				{
					case 'U':
						WD[1] += 2;
						break;
					case 'R':
						WD[0] -= 2;
						break;
					case 'D':
						WD[1] -= 2;
						break;
					case 'L':
						WD[0] += 2;
						break;
				}
				dir = dir.substring(0, dir.length() - 1);
			}
			//System.out.println(dir);
			if(WD[0] == goal[0] && WD[1] == goal[1])
				return true;
		}
		return false;
	}
	public static int mod(int n, int m)
	{
		while(n < 0)
			n += m;
		return (n % m);
	}
	public static String getKey(String kw, String alpha)
	{
		for(int aa = 0; aa < kw.length(); aa++)
		{
			for(int bb = aa + 1; bb < kw.length(); bb++)
			{
				if(kw.charAt(aa) == kw.charAt(bb))
				{
					kw = kw.substring(0, bb) + "" + kw.substring(bb + 1);
					bb--;
				}
			}
			alpha = alpha.replace(kw.charAt(aa) + "", "");
		}
		return kw + "" + alpha;
	}
	public static int gcd(int a, int b)
	{
		int gcd = 1;
		for(int aa = 2; aa <= a && aa <= b; aa++)
		{
			if(a % aa == 0 && b % aa == 0)
				gcd = aa;
		}
		return gcd;
	}
	public static boolean check(String i, ArrayList<String> l)
	{
		for(int aa = 0; aa < l.size(); aa++)
		{
			for(int bb = 0; bb < 26; bb++)
			{
				if(l.get(aa).charAt(bb) == i.charAt(bb))
					return true;
			}
		}
		return false;
	}
	public static int EEA(int A, int B)
	{
		int Q = A / B;
		int R = A % B;
		int T1 = 0;
		int T2 = 1;
		int T3 = T1 - (T2 * Q);
		while (R > 0)
		{
			A = B;
			B = R;
			Q = A / B;
			R = A % B;
			T1 = T2;
			T2 = T3;
			T3 = T1 - (T2 * Q);
		}
		return T2;
	}
}
