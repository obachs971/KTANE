package modules;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import start.BombEdgework;

public class IPhone 
{
	private final double r;
	private final BombEdgework ew;
	public IPhone(double resizer, BombEdgework e)
	{
		r = resizer;
		ew = e;
	}
	public String run()
	{
		List<String> apps = Arrays.asList("Angry Birds", "Messages", "Photos", "Tinder");
		apps = new ArrayList<String>(apps);
		int[] pin = new int[4];
		for(int i = 0; i < 3; i++)
		{
			JFrame frame = new JFrame();
			JOptionPane optionPane = new JOptionPane();
			ImageIcon[] icon = new ImageIcon[apps.size()];
			JButton[] jButton = new JButton[apps.size()];
			optionPane.setLayout(new GridLayout(apps.size(), 1));
			optionPane.setOptions(new Object[] {});
			optionPane.removeAll();
			for(int aa = 0; aa < apps.size(); aa++)
			{
				icon[aa] = new ImageIcon("img/IPhone" + apps.get(aa).replace(" ", "") + ".png");
				Image image = icon[aa].getImage();
				image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
				icon[aa] = new ImageIcon(image);
				jButton[aa] = getButton(optionPane, apps.get(aa), icon[aa]);
				optionPane.add(jButton[aa]);
			}
			JDialog dialog = optionPane.createDialog(frame, "");
			dialog.setTitle("Select an app:");
			dialog.setVisible(true);
			apps.remove(optionPane.getValue().toString());
			switch(optionPane.getValue().toString())
			{
				case "Angry Birds":
					pin[0] = angryBirds();
					break;
				case "Messages":
					pin[1] = messages();
					break;
				case "Photos":
					pin[2] = photos();
					break;
				case "Tinder":
					pin[3] = tinder();
					break;
			}
		}
		switch(apps.get(0))
		{
			case "Angry Birds":
				pin[0] = angryBirds();
				break;
			case "Messages":
				pin[1] = messages();
				break;
			case "Photos":
				pin[2] = photos();
				break;
			case "Tinder":
				pin[3] = tinder();
				break;
		}
		JOptionPane.showMessageDialog(null, "Enter this pin: " + pin[0] + "" + pin[1] + "" + pin[2] + "" + pin[3]);
		return ("PIN: " + pin[0] + "" + pin[1] + "" + pin[2] + "" + pin[3]);
	}
	private int angryBirds()
	{
		String[] imageNames = {
				"Red Angry Bird",
				"Yellow Angry Bird",
				"Blue Angry Bird",
				"White Angry Bird",
				"Black Angry Bird",
				"Regular Pig",
				"Helmet Pig",
				"Moustached Pig",
				"King Pig",
				"Black Eyed Pig"
		};
		String[] pos = {"Top Left", "Top Right", "Bottom Left", "Bottom Right"};
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		ImageIcon[] icon = new ImageIcon[imageNames.length];
		JButton[] jButton = new JButton[imageNames.length];
		optionPane.setLayout(new GridLayout(5, 2));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int aa = 0; aa < imageNames.length; aa++)
		{
			icon[aa] = new ImageIcon("img/IPhone" + imageNames[aa].replace(" ", "") + ".png");
			Image image = icon[aa].getImage();
			image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			icon[aa] = new ImageIcon(image);
			jButton[aa] = getButton(optionPane, imageNames[aa], icon[aa]);
			optionPane.add(jButton[aa]);
		}
		String[] info = new String[4];
		int numBird = 0;
		for(int i = 0; i < 4; i++)
		{
			JDialog dialog = optionPane.createDialog(frame, "");
			dialog.setTitle("Select the " + pos[i] + " image:");
			dialog.setVisible(true);
			info[i] = optionPane.getValue().toString().toUpperCase();
			if(info[i].contains("BIRD"))
				numBird++;
		}
		pos = new String[3];
		if(numBird > 2)
		{
			if(info[0].contains("YELLOW ") && info[2].contains(" PIG"))
			{
				pos[0] = "Top Right";
				pos[1] = "Bottom Left";
				pos[2] = "Top Left";
			}
			else if(info[1].contains("YELLOW ") && info[3].contains(" PIG"))
			{
				pos[0] = "Top Right";
				pos[1] = "Bottom Left";
				pos[2] = "Top Left";
			}
			else if(info[1].contains("BLACK ") && info[0].contains("RED "))
			{
				pos[0] = "Top Left";
				pos[1] = "Top Right";
				pos[2] = "Bottom Right";
			}
			else if(info[3].contains("BLACK ") && info[2].contains("RED "))
			{
				pos[0] = "Top Left";
				pos[1] = "Top Right";
				pos[2] = "Bottom Right";
			}
			else if(Arrays.binarySearch(info, "WHITE ANGRY BIRD") > 0)
			{
				pos[0] = "Bottom Left";
				pos[1] = "Bottom Right";
				pos[2] = "Top Right";
			}
			else
			{
				pos[0] = "Bottom Right";
				pos[1] = "Top Left";
				pos[2] = "Bottom Left";
			}
		}
		else if(numBird < 2)
		{
			if(!(info[0].equals(info[1]) || info[0].equals(info[2]) || info[0].equals(info[3]) || info[1].equals(info[2]) || info[1].equals(info[3]) || info[2].equals(info[3])))
			{
				pos[0] = "Bottom Left";
				pos[1] = "Bottom Right";
				pos[2] = "Top Right";
			}
			else if(Arrays.binarySearch(info, "KING PIG") < 0)
			{
				pos[0] = "Top Right";
				pos[1] = "Bottom Left";
				pos[2] = "Top Left";
			}
			else if(info[0].contains("HELMET ") && info[1].contains(" BIRD"))
			{
				pos[0] = "Top Left";
				pos[1] = "Top Right";
				pos[2] = "Bottom Right";
			}
			else if(info[2].contains("HELMET ") && info[3].contains(" BIRD"))
			{
				pos[0] = "Top Left";
				pos[1] = "Top Right";
				pos[2] = "Bottom Right";
			}
			else if(info[0].contains("MOUSTACHED ") && info[1].contains(" BIRD"))
			{
				pos[0] = "Top Left";
				pos[1] = "Top Right";
				pos[2] = "Bottom Right";
			}
			else if(info[2].contains("MOUSTACHED ") && info[3].contains(" BIRD"))
			{
				pos[0] = "Top Left";
				pos[1] = "Top Right";
				pos[2] = "Bottom Right";
			}
			else	
			{
				pos[0] = "Bottom Right";
				pos[1] = "Top Left";
				pos[2] = "Bottom Left";
			}
		}
		else
		{
			if(info[0].contains("REGULAR ") && (info[1].contains("WHITE ") || info[1].contains("BLUE ")))
			{
				pos[0] = "Top Left";
				pos[1] = "Bottom Left";
				pos[2] = "Top Right";
			}
			else if(info[1].contains("REGULAR ") && (info[0].contains("WHITE ") || info[0].contains("BLUE ")))
			{
				pos[0] = "Top Left";
				pos[1] = "Bottom Left";
				pos[2] = "Top Right";
			}
			else if(info[0].contains(" PIG") && (info[2].contains("BLACK ") || info[2].contains("RED ")))
			{
				pos[0] = "Bottom Left";
				pos[1] = "Top Right";
				pos[2] = "Bottom Right";
			}
			else if(info[1].contains(" PIG") && (info[3].contains("BLACK ") || info[3].contains("RED ")))
			{
				pos[0] = "Bottom Left";
				pos[1] = "Top Right";
				pos[2] = "Bottom Right";
			}
			else if(!(info[0].equals(info[1]) || info[0].equals(info[2]) || info[0].equals(info[3]) || info[1].equals(info[2]) || info[1].equals(info[3]) || info[2].equals(info[3])))
			{
				pos[0] = "Bottom Right";
				pos[1] = "Top Left";
				pos[2] = "Bottom Left";
			}
			else
			{
				pos[0] = "Top Right";
				pos[1] = "Bottom Right";
				pos[2] = "Top Left";
			}
		}
		String press;
		if(ew.BAT() >= 3)
			press = pos[0];
		else if(ew.numInd() >= 3)
			press = pos[1];
		else
			press = pos[2];
		String input = JOptionPane.showInputDialog("Press the " + press + " image\nEnter the pin number:");
		boolean v = v1(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Press the " + press + " image\nEnter the pin number:");
			v = v1(input);
		}
		return Integer.parseInt(input);
	}
	private int messages()
	{
		ImageIcon icon = new ImageIcon("img/IPhonePhrases.png");
		Image image = icon.getImage();
		image = image.getScaledInstance((int)(icon.getIconWidth() / r), (int)(icon.getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(image);
		JLabel label = new JLabel();
		label.setIcon(icon);
		JFrame f = new JFrame();
		f.setLayout(new BorderLayout());
		f.add(label, BorderLayout.CENTER);
		f.pack();
		f.setVisible(true);
		String input = JOptionPane.showInputDialog("Enter the pin number:");
		f.setVisible(false);
		boolean v = v1(input);
		while(!(v))
		{
			f.setVisible(true);
			input = JOptionPane.showInputDialog("Enter the pin number:");
			f.setVisible(false);
			v = v1(input);
		}
		return Integer.parseInt(input);
	}
	private int photos()
	{
		String[] imageNames = {
				"Beach",
				"Christmas Tree",
				"Computer",
				"Porsche",
				"Composer",
				"Castle",
				"Spaniel",
				"Football Team",
				"Band",
				"Roast Dinner"
		};
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		ImageIcon[] icon = new ImageIcon[imageNames.length];
		JButton[] jButton = new JButton[imageNames.length];
		optionPane.setLayout(new GridLayout(5, 2));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int aa = 0; aa < imageNames.length; aa++)
		{
			icon[aa] = new ImageIcon("img/IPhone" + imageNames[aa].replace(" ", "") + ".jpg");
			Image image = icon[aa].getImage();
			image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			icon[aa] = new ImageIcon(image);
			jButton[aa] = getButton(optionPane, imageNames[aa], icon[aa]);
			optionPane.add(jButton[aa]);
		}
		JDialog dialog = optionPane.createDialog(frame, "");
		dialog.setTitle("Select the photo that is present:");
		dialog.setVisible(true);
		for(int i = 0; i < imageNames.length; i++)
		{
			if(imageNames[i].equals(optionPane.getValue().toString()))
				return i;
		}
		return 0;
	}
	private int tinder()
	{
		int[][] table =
			{
					{3, 2, 1, -1, -2},
					{-2, -1, 3, 2, -1},
					{-1, -1, -2, 3, 2},
					{2, 2, -2, -2, 1, -1},
					{1, 2, -1, -2, 2, -1},
					{1, -1, 1, -1, 1, -1},
					{1, -1, 1, -2, -3, 2},
					{2, 1, -1, 1, -2, -2},
					{-1, 1, -2, 2, 3, -3},
					{3, 2, -1, -2, -2},
					{1, -3, 1, 2, -2},
					{-1, -2, 2, -2, 3},
			};
		for(int i = 0; i < 3; i++)
		{
			String input = JOptionPane.showInputDialog("Enter the number of strikes:");
			boolean v = isNum(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the number of strikes:");
				v = isNum(input);
			}
			int strikes = Integer.parseInt(input);
			if(strikes > 2)
				strikes = 2;
			
			input = JOptionPane.showInputDialog("Virgo, Leo, Scorpio,\nCapricorn, Cancer, Gemini\n\nBadminton, Golf, Cinema,\nTheater, Dancing, Clubbing\nCat, Dog, Goldfish,\nGerbil, Hamster\n\nEnter the age, starsign,\nhobby, and pet:").toUpperCase().replace("THEATRE", "THEATER");
			v = v2(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Virgo, Leo, Scorpio,\nCapricorn, Cancer, Gemini\n\nBadminton, Golf, Cinema,\nTheater, Dancing, Clubbing\nCat, Dog, Goldfish,\nGerbil, Hamster\n\nEnter the age, starsign,\nhobby, and pet:").toUpperCase().replace("THEATRE", "THEATER");
				v = v2(input);
			}
			String[] spl = input.split(" ");
			
			int score = 0;
			for(int j = 0; j < spl.length; j++)
			{
				switch(spl[j])
				{
					case "VIRGO":
						score += table[3 + strikes][0];
						break;
					case "LEO":
						score += table[3 + strikes][1];
						break;
					case "SCORPIO":
						score += table[3 + strikes][2];
						break;
					case "CAPRICORN":
						score += table[3 + strikes][3];
						break;
					case "CANCER":
						score += table[3 + strikes][4];
						break;
					case "GEMINI":
						score += table[3 + strikes][5];
						break;
					case "BADMINTON":
						score += table[6 + strikes][0];
						break;
					case "GOLF":
						score += table[6 + strikes][1];
						break;
					case "CINEMA":
						score += table[6 + strikes][2];
						break;
					case "THEATER":
						score += table[6 + strikes][3];
						break;
					case "DANCING":
						score += table[6 + strikes][4];
						break;
					case "CLUBBING":
						score += table[6 + strikes][5];
						break;
					case "CAT":
						score += table[9 + strikes][0];
						break;
					case "DOG":
						score += table[9 + strikes][1];
						break;
					case "GOLDFISH":
						score += table[9 + strikes][2];
						break;
					case "GERBIL":
						score += table[9 + strikes][3];
						break;
					case "HAMSTER":
						score += table[9 + strikes][4];
						break;
					default:
						int age = Integer.parseInt(spl[j]);
						if(age <= 22)
							score += table[0 + strikes][0];
						else if(age <= 28)
							score += table[0 + strikes][1];
						else if(age <= 35)
							score += table[0 + strikes][2];
						else if(age <= 41)
							score += table[0 + strikes][3];
						else
							score += table[0 + strikes][4];
				}
			}
			if(score == 0)
				JOptionPane.showMessageDialog(null, "Swipe Right if the name\nhas 5 or more letters\nOtherwise, Swipe Left");
			else if(score > 0)
				JOptionPane.showMessageDialog(null, "Swipe Right");
			else
				JOptionPane.showMessageDialog(null, "Swipe Left");
		}
		String input = JOptionPane.showInputDialog("Enter the pin number:");
		boolean v = v1(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the pin number:");
			v = v1(input);
		}
		return Integer.parseInt(input);
	}
	private boolean v1(String i)
	{
		switch(i)
		{
			case "0": case "1": case "2": case "3": case "4": 
			case "5": case "6": case "7": case "8": case "9": 
				return true;
			default:
				return false;
		}
	}
	private boolean v2(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 4)
		{
			boolean[] b = {false, false, false, false};
			for(int aa = 0; aa < conv.length; aa++)
			{
				switch(conv[aa])
				{
					case "VIRGO": case "LEO": case "SCORPIO": 
					case "CAPRICORN": case "CANCER": case "GEMINI":
						b[0] = true;
						break;
					case "BADMINTON": case "GOLF": case "CINEMA": 
					case "THEATER": case "DANCING": case "CLUBBING":
						b[1] = true;
						break;
					case "CAT": case "DOG": case "GOLDFISH": 
					case "GERBIL": case "HAMSTER":
						b[2] = true;
						break;
					default:
						if(!(isNum(conv[aa])))
							return false;
						int temp = Integer.parseInt(conv[aa]);
						b[3] = (temp >= 18 && temp <= 48);
						break;
				}
			}
			return (b[0] && b[1] && b[2] && b[3]);
		}
		return false;
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
}
