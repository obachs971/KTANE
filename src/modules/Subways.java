package modules;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import start.BombEdgework;

public class Subways 
{
	private int value = -1;
	private final String[][][] tables =
			{
				{
					{"1 8AM", "8 7PM", "4 4AM", "3 11AM", "6 12PM"},
					{"6 7AM", "1 2AM", "2 1PM", "7 BAT", "3 4PM"},
					{"7 BAT", "2 3AM", "5 6PM", "8 9AM", "4 BAT"},
					{"8 8PM", "2 1AM", "1 2PM", "3 BAT", "5 11PM"},
					{"7 6AM", "1 BAT", "4 3PM", "6 5AM", "2 5PM"},
					{"5 12AM", "7 10PM", "3 BAT", "8 10AM", "4 9PM"}
				},
				{
					{"9 1AM", "14 BAT", "13 5PM", "10 5AM", "15 6PM"},
					{"13 BAT", "11 12PM", "10 2AM", "16 4AM", "14 9AM"},
					{"9 8AM", "16 7PM", "12 BAT", "11 9PM", "15 11PM"},
					{"13 11AM", "9 4PM", "10 3AM", "16 1PM", "12 BAT"},
					{"13 7AM", "16 2PM", "11 12AM", "9 BAT", "12 10AM"},
					{"12 BAT", "14 8PM", "9 6AM", "13 3AM", "16 10PM"}
				},
				{
					{"17 BAT", "18 9AM", "21 8PM", "22 2PM", "19 7AM"},
					{"20 3AM", "19 10PM", "23 BAT", "18 10AM", "22 12AM"},
					{"20 5PM", "21 BAT", "23 11AM", "18 8AM", "24 4AM"},
					{"17 12PM", "22 1PM", "24 9PM", "18 6PM", "20 BAT"},
					{"19 5AM", "21 3PM", "23 6AM", "24 BAT", "17 11PM"},
					{"19 2AM", "17 BAT", "20 7PM", "21 1AM", "23 4PM"}
				}
			};
	private final String[] routes =
		{
				"Canal St 1 > Franklin St 1 > Chambers St 1-2-3",
				"Franklin St 1 > Rector St 1 > South Ferry 1",
				"Canal St J-N-Q-R > City Hall R-W > Rector St R-W",
				"South Ferry R-W > Cortlandt St R-W > Canal St J-N-Q-R",
				"Chambers St J-Z > Fulton St > Broad St J-Z",
				"Wall St 2-3 > Park Place 2-3 > Chambers St 1-2-3",
				"World Trade Center E > Canal St A-C-E > Chambers St A-C",
				"Bowling Green 4-5 > Wall St 4-5 > City Hall 4-5-6",
				"Green Park > Piccadilly Circus > Leicester Square",
				"Holborn > Leicester Square > Green Park",
				"Oxford Circus > Tottenham Court Road > Holborn",
				"Warren Street > Tottenham Court Road > Leicester Square",
				"Oxford Circus > Warren Street > King's Cross St. Pancras",
				"Warren Street > Oxford Circus > Green Park",
				"Holborn > Piccadilly Circus > Green Park",
				"King's Cross St. Pancras > Warren Street > Green Park",
				"Richelieu Drouot > Grands Boulevards > Bonne Nouvelle",
				"Réaumur Sébastopol > Sentier > Bourse",
				"St-Michel > Cité > Réaumur Sébastopol",
				"Pont Neuf > Pont Marie > Sully Morland",
				"Bonne Nouvelle > Grands Boulevards > Richelieu Drouot",
				"Bourse > Sentier > Réaumur Sébastopol",
				"Réaumur Sébastopol > Cité > St-Michel",
				"Sully Morland > Pont Marie > Pont Neuf"
		};
	private final String time;
	private final double r;
	public Subways(BombEdgework e, double resizer)
	{
		time = getTime(e.BAT());
		r = resizer;
	}
	public void run()
	{
		
		String[] cities = {"New York", "London", "Paris"};
		String[] names = {"Bryan", "John", "Mike", "Emily", "Mary", "Katie"};
		String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
		JDialog dialog = getDialogImage(cities, 1);
		dialog.setTitle("Select the City:");
		dialog.setVisible(true);
		int table = value;
		dialog = getDialogText(names, 1);
		dialog.setTitle("Select the Commuter:");
		dialog.setVisible(true);
		int row = value;
		dialog = getDialogText(days, 1);
		dialog.setTitle("Select the Day:");
		dialog.setVisible(true);
		int col = value;
		
		String[] spl = tables[table][row][col].split(" ");
		if(spl[1].equals("BAT"))
			spl[1] = time;
		String[] route = routes[Integer.parseInt(spl[0]) - 1].split(" > ");
		String out = "";
		for(int i = 0; i < route.length; i++)
			out = out + "STOP #" + (i + 1) + ": " + route[i] + "\n";
		JOptionPane.showMessageDialog(null, out + "TIME: " + spl[1]);
	}
	private String getTime(int bat)
	{
		int val = bat % 24;
		if(val == 0)
			return "12 AM";
		else if(val < 12)
			return (val + " AM");
		else if(val == 12)
			return "12 PM";
		else
			return ((val - 12) + " PM"); 
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
			icon[aa] = new ImageIcon("img/Subways" + arr[aa].replace(" ", "") + ".png");
			Image image = icon[aa].getImage();
			image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			icon[aa] = new ImageIcon(image);
			jButton[aa] = getButton(optionPane, arr[aa], icon[aa], aa);
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
			jButton[aa] = getButton(optionPane, arr[aa], aa);
			optionPane.add(jButton[aa]);
		}
		return optionPane.createDialog(frame, "");
	}
	private JButton getButton(final JOptionPane optionPane, String text, int val) {
	    final JButton button = new JButton();
	    button.setText(text);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(text.toUpperCase());
	        value = val;
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
	private JButton getButton(final JOptionPane optionPane, String text, ImageIcon icon, int val) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    button.setText(text);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(text.toUpperCase());
	        value = val;
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
}
