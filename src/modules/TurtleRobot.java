package modules;

import javax.swing.JOptionPane;

public class TurtleRobot
{
	private final String[][] chart =
		{
				{//Spade
					"LT 90",
					"FD 1",
					"RT 180 2",
					"LT 90 2",
					"RT 180",
					"LT 90 2",
					"RT 180 2",
					"FD 1",
					"LT 90",
					"LT 90 2",
					"RT 180",
					"FD 6",
					"RT 180",
					"LT 90 2"
				},
				{//Club
					"LT 90",
					"FD 1",
					"RT 180 2",
					"LT 90",
					"RT 180 2",
					"LT 90",
					"RT 180 2",
					"FD 1",
					"LT 90",
					"LT 90 2",
					"RT 180",
					"FD 6",
					"RT 180",
					"LT 90 2"
				},
				{//Crown
					"FD 4",
					"RT 150",
					"FD 3",
					"LT 120",
					"FD 3",
					"RT 120",
					"FD 3",
					"LT 120",
					"FD 3",
					"RT 150",
					"FD 4",
					"RT 90",
					"FD 6",
					"RT 90"
				},
				{//Dog House
					"FD 4",
					"RT 30",
					"FD 4",
					"RT 120",
					"FD 4",
					"RT 30",
					"FD 4",
					"RT 90",
					"FD 1",
					"RT 90",
					"FD 2",
					"LT 180 1",
					"FD 2",
					"RT 90",
					"FD 1",
					"RT 90"
				},
				{//Car
					"RT 90 2",
					"LT 90",
					"RT 180 2",
					"LT 90",
					"RT 90 2",
					"RT 90",
					"FD 1",
					"LT 90",
					"RT 180 1",
					"LT 90",
					"FD 2",
					"LT 90",
					"RT 180 1",
					"LT 90",
					"FD 1",
					"RT 90"
				},
				{//Mushroom
					"FD 1",
					"LT 90",
					"FD 1",
					"RT 90",
					"RT 180 2",
					"RT 90",
					"FD 1",
					"LT 90",
					"FD 1",
					"RT 90",
					"FD 2",
					"RT 90"
				},
				{//Mushroom 2
					"FD 2",
					"LT 90",
					"FD 2",
					"RT 90",
					"RT 180 4",
					"RT 90",
					"FD 2",
					"LT 90",
					"FD 2",
					"RT 90",
					"FD 4",
					"RT 90"
				},
				{//Bottle
					"FD 4",
					"RT 90 1",
					"LT 90",
					"FD 3",
					"RT 90",
					"FD 1",
					"RT 90",
					"FD 3",
					"LT 90",
					"RT 90 1",
					"FD 4",
					"RT 90",
					"FD 3",
					"RT 90"
				},
				{//Shape Shift
					"FD 2",
					"RT 90",
					"LT 90 1",
					"RT 90",
					"FD 4",
					"RT 30",
					"FD 4",
					"RT 120",
					"FD 4",
					"RT 30",
					"FD 4",
					"RT 90",
					"LT 90 1",
					"RT 90"
				},
				{//Tree
					"FD 4",
					"LT 90",
					"FD 1",
					"RT 180 2",
					"LT 90",
					"RT 180 2",
					"LT 90",
					"RT 180 2",
					"FD 1",
					"LT 90",
					"FD 4",
					"RT 90",
					"FD 2",
					"RT 90"
				},
				{//T-Shirt
					"FD 4",
					"LT 90",
					"FD 1",
					"RT 180 1",
					"FD 2",
					"RT 90",
					"LT 180 1",
					"RT 90",
					"FD 2",
					"RT 180 1",
					"FD 1",
					"LT 90",
					"FD 4",
					"RT 90",
					"FD 4",
					"RT 90"
				},
				{//Tulip
					"FD 4",
					"RT 90",
					"RT 90 2",
					"LT 150",
					"FD 2",
					"RT 120",
					"FD 2",
					"LT 150",
					"RT 90 2",
					"RT 90",
					"FD 4",
					"RT 180 3"
				},
				{//Key
					"FD 1",
					"RT 180 2",
					"LT 90",
					"FD 6",
					"RT 90",
					"FD 2",
					"RT 90",
					"FD 2",
					"RT 90",
					"FD 1",
					"LT 90",
					"FD 4",
					"LT 90",
					"RT 180 2"
				}
		};
	public String run()
	{
		String souv = "COMMENTED OUT:\n";
		String[] commands = new String[24];
		String[] souvData = new String[24];
		int lowNum = 90;
		for(int aa = 0; aa < commands.length; aa++)
		{
			commands[aa] = JOptionPane.showInputDialog("Enter command #" + (aa + 1) + ":").toUpperCase();
			boolean v = valid(commands[aa]);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				commands[aa] = JOptionPane.showInputDialog("Enter command #" + (aa + 1) + ":").toUpperCase();
				v = valid(commands[aa]);
			}
			souvData[aa] = commands[aa].toUpperCase();
			String[] split = commands[aa].split(" ");
			int num = Integer.parseInt(split[split.length - 1]);
			if(num < lowNum)
				lowNum = num;
		}
		int trying = -1;
		while(trying == -1)
		{
			trying = lowNum;
			for(int bb = 0; bb < commands.length; bb++)
			{
				String[] split2 = commands[bb].split(" ");
				int num2 = Integer.parseInt(split2[split2.length - 1]);
				boolean b = false;
				if(split2[0].equals("LT") && split2.length == 3)
					b = true;
				else if(split2[0].equals("RT") && split2.length == 3)
					b = true;
				else if(split2[0].equals("FD"))
					b = true;
				if(b)
				{
					if(num2 % lowNum != 0)
					{
						trying = -1;
						break;
					}
				}
			}
			if(trying == -1)
				lowNum--;
		}
		if(lowNum > 1)
		{
			for(int cc = 0; cc < commands.length; cc++)
			{
				String[] split2 = commands[cc].split(" ");
				int num2 = Integer.parseInt(split2[split2.length - 1]);
				boolean b = false;
				if(split2[0].equals("LT") && split2.length == 3)
					b = true;
				else if(split2[0].equals("RT") && split2.length == 3)
					b = true;
				else if(split2[0].equals("FD"))
					b = true;
				if(b)
				{
					num2 = num2 / lowNum;
					split2[split2.length - 1] = num2 + "";
					if(split2.length == 2)
						commands[cc] = split2[0] + " " + split2[1];
					else
						commands[cc] = split2[0] + " " + split2[1] + " " + split2[2];
				}
			}
		}
		for(int dd = 15; dd < commands.length; dd++)
		{
			boolean b = true;
			for(int ee = 0; ee < (commands.length - dd); ee++)
			{
				if(!(commands[ee].equals(commands[ee + dd])))
				{
					b = false;
					break;
				}
			}
			if(b)
			{
				String[] conv2 = new String[dd];
				for(int ff = 0; ff < dd; ff++)
					conv2[ff] = commands[ff];
				commands = conv2;
				break;
			}
		}
		int[] errors = solving(commands);
		if(errors[0] == -1)
		{
			JOptionPane.showMessageDialog(null, "ERROR");
			souv = run();
		}
		else
		{
			String output = "";
			int start = 22;
			for(int ff = 2; ff >= 0; ff--)
			{
				int up = start - errors[ff];
				output = output + "UP " + up + "\n";
				start = errors[ff];
				//System.out.println(errors[ff]);
				souv = souv + "" + souvData[errors[ff]].toUpperCase() + "\n";
			}
			output = output + "--------------------\n";
			for(int i = 0; i < souvData.length; i++)
			{
				if(i == errors[0] || i == errors[1] || i == errors[2])
					output = output + "# ";
				output = output + souvData[i] + "\n";
			}
			JOptionPane.showMessageDialog(null, output);
		}
		return souv;
	}
	private int[] solving(String[] c)
	{
		boolean shape = false;
		int[] errors = {-1, -1, -1};
		for(int aa = 0; aa < c.length; aa++)
		{
			for(int bb = aa + 1; bb < c.length; bb++)
			{
				for(int cc = bb + 1; cc < c.length; cc++)
				{
					String[] conv = remove(c, cc);
					conv = remove(conv, bb);
					conv = remove(conv, aa);
					conv = combine(conv);
					shape = compare(conv);
					if(shape)
					{
						errors[0] = aa;
						errors[1] = bb;
						errors[2] = cc;
						break;
					}
				}
				if(shape)
					break;
			}
			if(shape)
				break;
		}
		return errors;
	}
	private boolean compare(String[] c)
	{
		boolean bool = false;
		for(int aa = 0; aa < chart.length; aa++)
		{
			if(chart[aa].length == c.length)
			{
				
				String[][] shapes = new String[4][];
				shapes[0] = original(chart[aa]);
				shapes[1] = mirror(chart[aa]);
				shapes[2] = reverse(chart[aa]);
				shapes[3] = mirror(chart[aa]);
				shapes[3] = reverse(shapes[3]);
				for(int bb = 0; bb < (c.length + 5); bb++)
				{
					for(int cc = 0; cc < 4; cc++)
					{
						boolean b = true;
						for(int dd = 0; dd < shapes[cc].length; dd++)
						{
							if(!(shapes[cc][dd].equals(c[dd])))
							{
								b = false;
								break;
							}
						}
						if(b)
						{
							bool = true;
							break;
						}
					}
					if(bool)
						break;
					c = newOrder(c);
				}
			}
			if(bool)
				break;
		}
		return bool;
	}
	private String[] newOrder(String[] c)
	{
		String[] conv = new String[c.length];
		conv[0] = c[c.length - 1];
		for(int aa = 1; aa < conv.length; aa++)
		{
			conv[aa] = c[aa - 1];
		}
		return conv;
	}
	private String[] combine(String[] c)
	{
		for(int aa = 0; aa < c.length; aa++)
		{
			String[] s1 = c[aa].split(" ");
			String[] s2 = c[(aa + 1) % c.length].split(" ");
			int n1;
			int n2;
			if(s1[0].equals(s2[0]))
			{
				if(s1.length == s2.length)
				{
					if(s1.length == 2)
					{
						n1 = Integer.parseInt(s1[s1.length - 1]);
						n2 = Integer.parseInt(s2[s2.length - 1]);
						n1 = n1 + n2;
						c[aa] = s1[0] + " " + n1;
						c = remove(c, ((aa + 1) % c.length));
					}
					else
					{
						n1 = Integer.parseInt(s1[s1.length - 2]);
						n2 = Integer.parseInt(s2[s2.length - 2]);
						n1 = n1 + n2;
						c[aa] = s1[0] + " " + n1 + " " + s1[2];
						c = remove(c, ((aa + 1) % c.length));
					}
				}
			}
			else if(s1[0].equals("RT") && s2[0].equals("LT"))
			{
				if(s1.length == 2 && s2.length == 2)
				{
					n1 = Integer.parseInt(s1[s1.length - 1]);
					n2 = Integer.parseInt(s2[s2.length - 1]);
					n1 = n1 - n2;
					if(n1 < 0)
					{
						n1 = n1 * -1;
						c[aa] = "LT " + n1;
					}
					else
						c[aa] = "RT " + n1;
					c = remove(c, ((aa + 1) % c.length));
				}
			}
			else if(s1[0].equals("LT") && s2[0].equals("RT"))
			{
				if(s1.length == 2 && s2.length == 2)
				{
					n1 = Integer.parseInt(s1[s1.length - 1]);
					n2 = Integer.parseInt(s2[s2.length - 1]);
					n1 = n1 - n2;
					if(n1 < 0)
					{
						n1 = n1 * -1;
						c[aa] = "RT " + n1;
					}
					else
						c[aa] = "LT " + n1;
					c = remove(c, ((aa + 1) % c.length));
				}
			}
		}
		return c;
	}
	private String[] remove(String[] shape, int n)
	{
		String[] conv = new String[shape.length - 1];
		int items = 0;
		for(int aa = 0; aa < shape.length; aa++)
		{
			if(aa != n)
			{
				conv[items] = shape[aa];
				items++;
			}
		}
		return conv;
	}
	private String[] original(String[] shape)
	{
		String[] conv = new String[shape.length];
		for(int aa = 0; aa < shape.length; aa++)
			conv[aa] = shape[aa];
		return conv;
	}
	private String[] mirror(String[] shape)
	{
		String[] conv = new String[shape.length];
		for(int aa = 0; aa < conv.length; aa++)
		{
			String[] split = shape[aa].split(" ");
			if(split[0].equals("RT"))
				split[0] = "LT";
			else if(split[0].equals("LT"))
				split[0] = "RT";
			
			if(split.length == 2)
				conv[aa] = split[0] + " " + split[1];
			else
				conv[aa] = split[0] + " " + split[1] + " " + split[2];
		}
		return conv;
	}
	private String[] reverse(String[] shape)
	{
		String[] conv = new String[shape.length];
		for(int aa = 0; aa < conv.length; aa++)
			conv[aa] = shape[shape.length - (aa + 1)];
		return conv;
	}
	private boolean valid(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 2)
		{
			if(conv[0].equals("FD"))
				return isNum(conv[1]);
			else if(conv[0].equals("RT") || conv[0].equals("LT"))
			{
				switch(conv[1])
				{
					case "30": case "90": case "120": case "180": return true;
				}
			}
		}
		else if(conv.length == 3)
		{
			switch(conv[0])
			{
				case "RT": case "LT": break;
				default: return false;
			}
			switch(conv[1])
			{
				case "30": case "90": case "120": case "180": break;
				default: return false;
			}
			return isNum(conv[2]);
		}
		return false;
	}
	private boolean isNum(String i)
	{
		if(i.length() == 0)
			return false;
		for(char c : i.toCharArray())
		{
			if("0123456789".indexOf(c) < 0)
				return false;
		}
		return true;
	}
	/*private void print(String[] shape)
	{
		for(int aa = 0; aa < shape.length; aa++)
		{
			System.out.println(shape[aa]);
		}
		System.out.println("------------------------------");
	}*/
}

