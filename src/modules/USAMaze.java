package modules;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.Image;

import misc.MTK;

public class USAMaze 
{
	private final String cir = "C";
	private final String squ = "Q";
	private final String dia = "D";
	private final String tra = "Z";
	private final String par = "P";
	private final String tri = "T";
	private final String hea = "H";
	private final String sta = "R";
	private String[][] maze =
		{
				{"AK", ""},
				{"AL", cir + "FL", tra + "MS"},
				{"AR", tra + "TN", squ + "MS", cir + "LA", tri + "TX"},
				{"AZ", par + "NV"},
				{"CA", squ + "--", cir + "OR"},
				{"CO", dia + "KS", par + "OK"},
				{"CT", hea + "MA"},
				{"DE", par + "--", dia + "MD"},
				{"FL", cir + "AL", squ + "GA"},
				{"GA", dia + "SC", squ + "FL"},
				{"HI", ""},
				{"IA", par + "MN", sta + "IL", cir + "SD"},
				{"ID", tri + "WY", hea + "UT", dia + "OR", squ + "WA"},
				{"IL", hea + "WI", dia + "IN", squ + "MO", sta + "IA"},
				{"IN", tra + "MI", dia + "IL"},
				{"KS", tra + "OK", dia + "CO"},
				{"KY", tri + "OH", tra + "VA"},
				{"LA", cir + "AR"},
				{"MA", tri + "VT", sta + "RI", hea + "CT", tra + "NY"},
				{"MD", squ + "PA", dia + "DE"},
				{"ME", tri + "--", dia + "NH"},
				{"MI", par + "OH", tra + "IN", sta + "WI"},
				{"MN", tri + "WI", par + "IA"},
				{"MO", squ + "IL", par + "TN", hea + "OK", sta + "NE"},
				{"MS", dia + "TN", tra + "AL", squ + "AR"},
				{"MT", squ + "SD", cir + "WY"},
				{"NC", cir + "VA"},
				{"ND", hea + "--", dia + "SD"},
				{"NE", tra + "SD", sta + "MO"},
				{"NH", dia + "ME", squ + "VT"},
				{"NJ", tra + "PA"},
				{"NM", squ + "OK", cir + "TX"},
				{"NV", tra + "OR", par + "AZ"},
				{"NY", tra + "MA", cir + "PA"},
				{"OH", par + "MI", hea + "WV", tri + "KY"},
				{"OK", tra + "KS", hea + "MO", squ + "NM", par + "CO"},
				{"OR", dia + "ID", tra + "NV", cir + "CA"},
				{"PA", cir + "NY", tra + "NJ", squ + "MD", sta + "WV"},
				{"RI", dia + "--", sta + "MA"},
				{"SC", tra + "--", dia + "GA"},
				{"SD", dia + "ND", cir + "IA", tra + "NE", squ + "MT"},
				{"TN", sta + "VA", dia + "MS", tra + "AR", par + "MO"},
				{"TX", sta + "--", tri + "AR", cir + "NM"},
				{"UT", hea + "ID", sta + "WY"},
				{"VA", cir + "NC", sta + "TN", tra + "KY"},
				{"VT", squ + "NH", tri + "MA"},
				{"WA", cir + "--", squ + "ID"},
				{"WI", sta + "MI", hea + "IL", tri + "MN"},
				{"WV", sta + "PA", hea + "OH"},
				{"WY", cir + "MT", sta + "UT", tri + "ID"},
				{"--"}
		};
	private final int tz;
	private final double r;
	public USAMaze(int t, double resizer)
	{
		tz = t;
		r = resizer;
	}
	public String run()
	{
		
		
		String input = JOptionPane.showInputDialog("Enter the 2 states\n(Big then Small screen):").toUpperCase();
		boolean v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the 2 states\n(Big then Small screen):").toUpperCase();
			v = valid(input);
		}
		String[] states = input.split(" ");
		setMaze();
		String solution = new MTK().getMazeSolution(maze, 1, states[0], states[1]);
		ImageIcon[] icon = new ImageIcon[solution.length()];
		JLabel[] label = new JLabel[solution.length()];
		JFrame frame = new JFrame();
		if(solution.length() % 4 == 0)
			frame.setLayout(new GridLayout(solution.length() / 4, 4));
		else
			frame.setLayout(new GridLayout((solution.length() / 4) + 1, 4));
		String out = "";
		for(int i = 0; i < solution.length(); i++)
		{
			icon[i] = new ImageIcon("img/USAMaze" + getName(solution.charAt(i)) + ".png");
			Image image = icon[i].getImage();
			image = image.getScaledInstance((int)(icon[i].getIconWidth() / r), (int)(icon[i].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			icon[i] = new ImageIcon(image);
			label[i] = new JLabel();
			label[i].setIcon(icon[i]);
			frame.add(label[i]);
			out = out + "" + solution.charAt(i) + " ";
			if((i + 1) % 4 == 0)
				out = out + "\n";
		}
		frame.pack();
		frame.setVisible(true);
		JOptionPane.showMessageDialog(null, "Press these shapes:\n" + out);
		frame.setVisible(false);
		return ("DEPARTURE: " + getName(states[0]));
	}
	private void setMaze()
	{
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
	    cal.add(Calendar.HOUR_OF_DAY, tz);
	    long num = cal.getTimeInMillis();
	    Date now2 = new Date(num);
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
		int AK = findState("AK");
		int HI = findState("HI");
		switch(simpleDateformat.format(now2).toUpperCase())
		{
			case "SUNDAY":
				maze[AK][1] = cir + "WA";
				maze[HI][1] = squ + "CA";
				maze[findState("WA")][1] = cir + "AK";
				maze[findState("CA")][1] = squ + "HI";
				break;
			case "MONDAY":
				maze[AK][1] = squ + "CA";
				maze[HI][1] = cir + "WA";
				maze[findState("CA")][1] = squ + "AK";
				maze[findState("WA")][1] = cir + "HI";
				break;
			case "TUESDAY":
				maze[AK][1] = tra + "SC";
				maze[HI][1] = tri + "ME";
				maze[findState("SC")][1] = tra + "AK";
				maze[findState("ME")][1] = tri + "HI";
				break;
			case "WEDNESDAY":
				maze[AK][1] = par + "DE";
				maze[HI][1] = dia + "RI";
				maze[findState("DE")][1] = par + "AK";
				maze[findState("RI")][1] = dia + "HI";
				break;
			case "THURSDAY":
				maze[AK][1] = dia + "RI";
				maze[HI][1] = par + "DE";
				maze[findState("RI")][1] = dia + "AK";
				maze[findState("DE")][1] = par + "HI";
				break;
			case "FRIDAY":
				maze[AK][1] = tri + "ME";
				maze[HI][1] = tra + "SC";
				maze[findState("ME")][1] = tri + "AK";
				maze[findState("SC")][1] = tra + "HI";
				break;
			case "SATURDAY":
				maze[AK][1] = hea + "ND";
				maze[HI][1] = sta + "TX";
				maze[findState("ND")][1] = hea + "AK";
				maze[findState("TX")][1] = sta + "HI";
				break;
		}
	}
	private String getName(char let)
	{
		String shapes = cir + squ + tra + par + dia + tri + hea + sta;
		String[] names = {"Circle", "Square", "Trapezoid", "Parallelogram", "Diamond", "Triangle", "Heart", "Star"};
		return names[shapes.indexOf(let)];
	}
	private String getName(String abbrev)
	{
		String[] names = {
		"ALASKA", "ALABAMA", "ARKANSAS",
		"ARIZONA", "CALIFORNIA", "COLORADO",
		"CONNECTICUT", "DELAWARE", "FLORIDA",
		"GEORGIA", "HAWAII", "IOWA",
		"IDAHO", "ILLINOIS", "INDIANA",
		"KANSAS", "KENTUCKY", "LOUISIANA",
		"MASSACHUSETTS", "MARYLAND", "MAINE",
		"MICHIGAN", "MINNESOTA", "MISSOURI",
		"MISSISSIPPI", "MONTANA", "NORTH CAROLINA",
		"NORTH DAKOTA", "NEBRASKA", "NEW HAMPSHIRE",
		"NEW JERSEY", "NEW MEXICO", "NEVADA",
		"NEW YORK", "OHIO", "OKLAHOMA",
		"OREGON", "PENNSYLVANIA", "RHODE ISLAND",
		"SOUTH CAROLINA", "SOUTH DAKOTA", "TENNESSEE",
		"TEXAS", "UTAH", "VIRGINIA",
		"VERMONT", "WASHINGTON", "WISCONSIN",
		"WEST VIRGINIA", "WYOMING"
		};
		return names[findState(abbrev)];
	}
	private int findState(String state)
	{
		for(int i = 0; i < maze.length; i++)
		{
			if(state.equals(maze[i][0]))
				return i;
		}
		return -1;
	}
	private boolean valid(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 2)
		{
			boolean[] b = {false, false};
			for(int aa = 0; aa < 2; aa++)
			{
				for(int bb = 0; bb < maze.length - 1; bb++)
				{
					if(conv[aa].equals(maze[bb][0]))
					{
						b[aa] = true;
						break;
					}
				}
			}
			return (b[0] && b[1]);
		}
		return false;
	}
}
