package modules;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import start.BombConfig;
import start.BombEdgework;

public class Calendars 
{
	private final int spring = 0, summer = 1, autumn = 2, winter = 3;
	private final String[][] monthTable =
		{
			{"Jan","Nov","Aug","Jun","Mar","Dec","Feb","Jul","Sep","May","Oct","Apr"},
			{"Dec","Jun","Apr","Oct","May","Feb","Aug","Nov","Mar","Jul","Jan","Sep"},
			{"Feb","Jul","Oct","Jan","Sep","Aug","Dec","Apr","Jul","Mar","Nov","Jun"},
			{"May","Mar","Sep","Apr","Jul","Nov","Jun","Oct","Jan","Dec","Aug","Feb"}
		};
	private final String[][] dayTable =
		{
			{"12","2","11","7","18","24","4","14","10","20"},
			{"4","16","21","15","27","6","25","13","2","9"},
			{"22","14","6","11","8","19","31/7","23","28","26"},
			{"8","20","17","16","23","16","1","22","24","5"},
			{"19","5","24","3","29/1","28","18","30/4","13","12"},
			{"10","29/2","12","24","15","20","5","27","25","7"},
			{"15","1","31/7","17","26","30/8","24","9","3","25"},
			{"23","13","25","30/3","4","11","27","15","21","31/5"},
			{"4","27","8","22","10","14","13","28","13","21"},
			{"29/3","19","27","15","9","16","19","14","9","3"},
			{"14","7","23","17","5","31/1","2","25","17","11"},
			{"26","16","3","26","29/7","18","22","25","17","11"},
			{"2","28","18","13","21","12","3","10","20","1"},
			{"17","24","15","20","1","30/9","28","6","7","14"},
			{"21","9","30/6","24","28","6","21","26","31/2","8"},
			{"11","6","22","14","19","27","20","7","16","23"},
			{"1","2","3","4","5","6","7","8","9","10"}
		};
	private String sub;
	private final BombConfig con;
	private final BombEdgework ew;
	private final double r;
	public Calendars(BombConfig c, BombEdgework e, double resizer)
	{
		con = c;
		ew = e;
		r = resizer;
	}
	public String run()
	{
		String[] colors = {"Red", "Blue", "Yellow", "Green"};
		String[] holidays = {
				"Jan 4: World Braille Day", "Jan 6: Epiphany", "Jan 26: Australia Day", 
				"Feb 2: Groundhog Day", "Feb 14: Valentine's Day", "Mar 17: Saint Patrick's Day", 
				"Apr 1: April Fools'", "Apr 22: Earth Day", "Apr 29 - May 6: Golden Week", 
				"May 5: Cinco de Mayo", "Jun 2: Republic Day", "Jul 14: Bastille Day", 
				"Oct 3: Day of German Unity", "Oct 31: Day of the Dead", "Nov 5: Guy Fawkes Night", 
				"Nov 11: Veterans Day", "Dec 24: Christmas Eve", "Dec 26 - Jan 1: Kwanzaa"
		};
		JDialog dialog = getDialogImage(colors, 2);
		dialog.setTitle("Select the LED Color:");
		dialog.setVisible(true);
		String color = sub.toUpperCase();
		int colorNum = colorToNum();
		dialog = getDialogText(holidays, 2);
		dialog.setTitle("Select the Holiday:");
		dialog.setVisible(true);
		int holidayNum = holidayToNum();
		String month = monthTable[colorNum][dayToNum() + (seasonToNum() * 3)];
		if(holidayNum == -1)
			JOptionPane.showMessageDialog(null, "Click any day\nin " + month + " 3 times");
		else
		{
			int col = ew.getSNDIG(ew.numSNDIGS() - 1);
			if(holidayNum == 16)
				col = ew.getSNDIG(0);
			String day = dayTable[holidayNum][col];
			if(day.contains("/"))
			{
				String[] temp = day.split("/");
				if(month.equals("Feb") && temp[0].equals("29"))
					day = day.toUpperCase();
				else
				{
					ArrayList<String> monthList = new ArrayList<String>();
					monthList.add("Feb");
					if(temp[0].equals("31"))
					{
						monthList.add("Apr");
						monthList.add("Jun");
						monthList.add("Sep");
						monthList.add("Nov");
					}
					if(monthList.contains(month))
						day = temp[1];
					else
						day = temp[0];
				}
			}
			JOptionPane.showMessageDialog(null, "Month: " + month + "\nDay: " + day);
		}
		return ("LED COLOR: " + color);
	}
	private int colorToNum()
	{
		switch(sub)
		{
			case "GREEN": return 0;
			case "YELLOW": return 1;
			case "RED": return 2;
			default: return 3;
		}
	}
	private int holidayToNum()
	{
		int index = sub.indexOf(":");
		sub = sub.substring(index + 2);
		switch(sub)
		{
			
			case "CHRISTMAS EVE": return 0;
			case "DAY OF THE DEAD": return 1;
			case "BASTILLE DAY": return 2;
			case "GOLDEN WEEK": return 3;
			case "AUSTRALIA DAY": return 4;
			case "REPUBLIC DAY": return 5;
			case "EPIPHANY": return 6;
			case "EARTH DAY": return 7;
			case "DAY OF GERMAN UNITY": return 8;
			case "CINCO DE MAYO": return 9;
			case "VETERANS DAY": return 10;
			case "GUY FAWKES NIGHT": return 11;
			case "SAINT PATRICK'S DAY": return 12;
			case "WORLD BRAILLE DAY": return 13;
			case "KWANZAA": return 14;
			case "VALENTINE'S DAY": return 15;
			case "APRIL FOOLS'": return 16;
			default: return -1;
		}
	}
	private int seasonToNum()
	{
		int day = con.getStartingDay();
		switch(con.getStartingMonth())
		{
			case 1: case 2: return winter;
			case 3: return (day < 22) ? winter : spring;
			case 4: case 5: return spring;
			case 6: return (day < 22) ? spring : summer;
			case 7: case 8: return summer;
			case 9: return (day < 22) ? summer : autumn;
			case 10: case 11: return autumn;
			default: return (day < 22) ? autumn : winter;
		}
	}
	private int dayToNum()
	{
		int day = con.getStartingDay();
		if(day < 11)
			return 0;
		else if(day < 21)
			return 1;
		else
			return 2;
	}
	private JDialog getDialogImage(String[] arr, int div)
	{
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		JButton[] jButton = new JButton[arr.length];
		ImageIcon[] icon = new ImageIcon[arr.length];
		optionPane.setLayout(new GridLayout(arr.length / div, div));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int aa = 0; aa < arr.length; aa++)
		{
			icon[aa] = new ImageIcon("img/Calendar" + arr[aa] + ".png");
			Image image = icon[aa].getImage();
			image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			icon[aa] = new ImageIcon(image);
			jButton[aa] = getButton(optionPane, arr[aa], icon[aa]);
			optionPane.add(jButton[aa]);
		}
		return optionPane.createDialog(frame, "");
	}
	private JDialog getDialogText(String[] arr, int div)
	{
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		JButton[] jButton = new JButton[arr.length];
		optionPane.setLayout(new GridLayout(arr.length / div, div));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int aa = 0; aa < arr.length; aa++)
		{
			jButton[aa] = getButton(optionPane, arr[aa]);
			optionPane.add(jButton[aa]);
		}
		return optionPane.createDialog(frame, "");
	}
	private JButton getButton(final JOptionPane optionPane, String text) {
	    final JButton button = new JButton();
	    button.setText(text);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(text.toUpperCase());
	        sub = text.toUpperCase();
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
	private JButton getButton(final JOptionPane optionPane, String text, ImageIcon icon) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    button.setText(text);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(text.toUpperCase());
	        sub = text.toUpperCase();
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
}
