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
		
		Random r = new Random();
		System.out.print((r.nextInt(4) + 1));
		System.out.println((r.nextInt(3) + 1));
		
		//BombConfig con = new BombConfig();
		BombEdgework e = new BombEdgework(resizer);
		
		int num = 0;
		while(num == 0)
		{
		    OnlyConnect test = new OnlyConnect(e, resizer);
			test.run();
			

		}
		
		//System.out.println("\"" + word4.toString().replace("[", "").replace("]", "").replace(", ", "\", \"") + "\"");
		//System.out.println("\"" + word5.toString().replace("[", "").replace("]", "").replace(", ", "\", \"") + "\"");
		//System.out.println("\"" + word6.toString().replace("[", "").replace("]", "").replace(", ", "\", \"") + "\"");
		//System.out.println("\"" + word7.toString().replace("[", "").replace("]", "").replace(", ", "\", \"") + "\"");
		//System.out.println("\"" + word8.toString().replace("[", "").replace("]", "").replace(", ", "\", \"") + "\"");
		//System.out.println((word4.size() + word5.size() + word6.size() + word7.size() + word8.size()));
		//System.out.println(word6.size());
		//System.out.println("\"" + other.toString().replace("[", "").replace("]", "").replace(", ", "\", \"") + "\"");
		/*
		Random r = new Random();
		String c = "0000111122223333444455556666777788889999";
		c = c.replace("0", "");
		String[] grid = {"", "", "", "", "", ""};
		for(int aa = 0; aa < 6; aa++)
		{
			for(int bb = 0; bb < 6; bb++)
			{
				num = r.nextInt(c.length());
				grid[aa] = grid[aa] + "" + c.charAt(num);
				c = c.substring(0, num) + "" + c.substring(num + 1);
			}
		}
		c = "0123456789".replace("0", "");
		for(int aa = 0; aa < c.length(); aa++)
		{
			for(int bb = aa + 1; bb < c.length(); bb++)
			{
				for(int cc = bb + 1; cc < c.length(); cc++)
				{
					String[] maze = new String[8];
					maze[0] = "WWWWWWWW";
					maze[7] = "WWWWWWWW";
					for(int dd = 0; dd < 6; dd++)
						maze[dd + 1] = "W" + grid[dd].replace(c.charAt(aa) + "", "W").replace(c.charAt(bb) + "", "W").replace(c.charAt(cc) + "", "W") + "W";
					for(int dd = 0; dd < maze.length; dd++)
						System.out.println(maze[dd]);
					System.out.println("----------------");
				}
			}
		}*/
		
		
		
		
		
		
		/*int max = 10;
		for(int aa = 0; aa < max; aa++)
		{
			for(int bb = aa + 1; bb < max; bb++)
			{
				String[] maze = {"", "", "", "", "", "", "", "", "", "", "", "", ""};
				for(int cc = 0; cc < 13; cc++)
				{
					for(int dd = 0; dd < 13; dd++)
					{
						if(mazes[aa][cc].charAt(dd) == mazes[bb][cc].charAt(dd))
							maze[cc] = maze[cc] + "" + mazes[aa][cc].charAt(dd);
						else
							maze[cc] = maze[cc] + "O";
					}
						System.out.println(maze[cc]);
				}
					System.out.println("--------------------");
				for(int cc = 0; cc < 6; cc++)
				{
					for(int dd = 0; dd < 6; dd++)
					{
						String[] temp = new String[maze.length];
						for(int ee = 0; ee < temp.length; ee++)
							temp[ee] = maze[ee].toUpperCase();
						boolean b = mazeSolver(temp, new int[] {1 + (dd * 2), 1 + (cc * 2)}, new int[] {11, 11});
						if(!(b))
						{
							System.out.println("MAZES: " + (aa) + ", " + (bb) + "\nROW: " + (cc + 1) + "\nCOL: " + (dd + 1));
							System.exit(0);
						}
					}
				}
			}
		}*/
		System.exit(0);
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
	public static void GCD(int a, int b)
	{
		int t1 = 0;
		int t2 = 1;
		int r = a % b;
		while(r > 0)
		{
			int q = a / b;
			int t3 = t1 - (t2 * q);
			t1 = t2;
			t2 = t3;
			a = b;
			b = r;
			r = a % b;
		}
		while(t2 < 0)
			t2 += 26;
		System.out.println(t2);
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
}
