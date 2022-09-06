package modules;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EuropeanTravel 
{
	private final int[] prices =
		{
			2399, 9554, 5311, 1083, 512, 10233, 7600, 1422, 8890, 12144, 198, 3308	
		};
	private final String[][][] cities =
		{
				{
					{"Zwolle", "Groningen", "Amsterdam CS", "Utrecht CS", "Den Haag CS", "Zutphen", "Maastricht", "Schiphol A'port", "Delft", "Alkmaar", "Lelystad Zuid", "Kampen"},
					{"Gouda", "Leiden CS", "Leeuwarden", "Middelburg", "Rotterdam CS", "Deurne", "Deventer", "Assen", "Eindhoven", "Nijmegen", "Zandvoort aan Zee", "Kerkrade Centrum"}
				},
				{
					{"Swansea", "Coventry", "Peterborough", "Cambridge", "Stoke-on-Trent", "Watford Junction", "Exeter", "Portsmouth H'bour", "Heathrow A'port", "Luton", "Dover", "Brighton"},
					{"Bristol Temple Meads", "Pembroke Dock", "London St. Pancras", "Aylesbury", "Chester", "Bangor", "Stourbridge Town", "Nottingham", "Manchester Victoria", "Sheffield", "Wolverhampton", "Hull"}
				},
				{
					{"Ulm Hbf.", "Emden Hbf.", "Cottbus", "Erfurt Hbf.", "Kiel Hbf.", "Potsdam Hbf.", "Ingolstadt Hbf.", "Berlin Ost.", "Mainz Hbf.", "Frankfurt F'hafen", "Regensburg Hbf.", "Oberstdorf"},
					{"Leipzig Hbf.", "Augsburg Hbf.", "Bonn Hbf.", "Leer (Ostfriesl)", "Bielefeld Hbf.", "Chemnitz Hbf.", "Karlsruhe Hbf.", "Freiburg Hbf.", "Lübeck Hbf.", "Wittenberge", "Dessau Hbf.", "Jena Paradies"}
				},
				{
					{"Clermont-Ferrand", "Bordeaux St-Jean", "Lille", "Montargis", "Grenoble", "Cannes", "Redon", "Biarritz", "Limoges", "Rouen-Rive-Droite", "Le Havre", "Dijon-Ville"},
					{"C. De Gaulle A'port", "St-Dizier", "Boulogne-Ville", "Paris Gare du Nord", "Poitiers", "Angers-Saint-Laud", "Nancy-Ville", "Lisieux", "Marseille St-Charles", "Toul", "Perpignan", "Nîmes"}
				},
				{
					{"Santander", "Ferrol", "Plasencia", "Córdoba", "Almería", "Gandía", "Albacete", "Aranjuez", "Cádiz", "Jaca", "Vitoria", "Murcia del Carmen"},
					{"Girona", "Soria", "Ourense-Empalme", "Zafra", "Málaga", "San Sebastián", "Reus", "Barcelona Sants", "Tarragona", "Guadalajara", "Madrid Atocha", "Linares-Baeza"}
				},
				{
					{"Antwerpen-Zuid", "Lokeren", "Tielen", "Hasselt", "Sint-Joris-Weert", "Waregem", "Oostende", "Enghien", "Lierde", "Brussel-Zuid", "Halle", "Gent-Sint-Pieters"},
					{"Charleroi-Sud", "Aarschot", "Mechelen", "Leuven", "Spa", "Idegem", "Tongeren", "Villers-La-Ville", "De Panne", "Knokke", "Zeebrugge-Strand", "Kortrijk"}
				}
		};
	private final double r;
	public EuropeanTravel(double resizer)
	{
		r = resizer;
	}
	public void run()
	{
		String[] colors = {"Red", "Rose", "Orange", "Yellow", "Green", "Aqua"};
		ArrayList<String> clist = new ArrayList<String>(Arrays.asList(new String[] {"Orange", "Green", "Red", "Aqua", "Yellow", "Rose"}));
		String alpha = "ABCDEFGHIJKLMNPQRSTUVWXYZ0123456789";
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		ImageIcon[] icon = new ImageIcon[colors.length];
		JButton[] jButton = new JButton[colors.length];
		optionPane.setLayout(new GridLayout(colors.length / 2, 2));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int aa = 0; aa < colors.length; aa++)
		{
			icon[aa] = new ImageIcon("img/EuropeanTravel" + colors[aa] + ".png");
			Image image = icon[aa].getImage();
			image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			icon[aa] = new ImageIcon(image);
			jButton[aa] = getButton(optionPane, colors[aa], icon[aa]);
			optionPane.add(jButton[aa]);
		}
		JDialog dialog = optionPane.createDialog(frame, "");
		dialog.setTitle("Select the border color:");
		dialog.setVisible(true);
		String color = optionPane.getValue().toString();
		String serial = JOptionPane.showInputDialog("Enter the ticket's serial:").toUpperCase();
		boolean v = valid(serial);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			serial = JOptionPane.showInputDialog("Enter the ticket's serial:").toUpperCase();
			v = valid(serial);
		}
		String[] out = new String[6];
		out[0] = alpha.indexOf(serial.charAt(4)) < 25 ? "SGL" : "RTN";
		out[1] = alpha.indexOf(serial.charAt(2)) < 25 ? "1st" : "2nd";
		out[2] = cities[clist.indexOf(color)][0][alpha.indexOf(serial.charAt(0)) / 3];
		out[3] = cities[clist.indexOf(color)][1][alpha.indexOf(serial.charAt(1)) / 3];
		if(alpha.indexOf(serial.charAt(5)) < 7)
			out[4] = "1A";
		else if(alpha.indexOf(serial.charAt(5)) < 15)
			out[4] = "1B";
		else if(alpha.indexOf(serial.charAt(5)) < 19)
			out[4] = "2A";
		else if(alpha.indexOf(serial.charAt(5)) < 25)
			out[4] = "2B";
		else if(alpha.indexOf(serial.charAt(5)) < 28)
			out[4] = "3A";
		else if(alpha.indexOf(serial.charAt(5)) < 31)
			out[4] = "3B";
		else if(alpha.indexOf(serial.charAt(5)) < 34)
			out[4] = "4A";
		else
			out[4] = "4B";
		int price = prices[alpha.indexOf(serial.charAt(3)) / 3];
		if(out[1].equals("1st"))
			price *= 2;
		out[5] = (price / 100) + "." + (price % 100 < 10 ? "0" + (price % 100) : (price % 100));
		JOptionPane.showMessageDialog(null, "Type: " + out[0] + "\nClass: " + out[1] + "\nDeparture: " + out[2] + "\nDestination: " + out[3] + "\nSeat: " + out[4] + "\nPrice: " + out[5]);
	}
	private JButton getButton(final JOptionPane optionPane, String text, ImageIcon icon ) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    button.setText(text);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	    	  optionPane.setValue(text);
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
	private boolean valid(String i)
	{
		if(i.length() == 6)
		{
			for(int aa = 0; aa < 6; aa++)
			{
				if("ABCDEFGHIJKLMNPQRSTUVWXYZ0123456789".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
}
