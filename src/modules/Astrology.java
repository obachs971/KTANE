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

public class Astrology 
{
	private final int[][][] charts =
	{
			{
				{0, 0, 1, -1, 0, 1, -2, 2, 0, -1},
				{-2, 0, -1, 0, 2, 0, -2, 2, 0, 1},
				{-1, -1, 0, -1, 1, 2, 0, 2, 1, -2},
				{-1, 2, -1, 0, -2, -1, 0, 2, -2, 2}
			},
			{
				{1, 0, -1, 0, 0, 2, 2, 0, 1, 0, 1, 0},
				{2, 2, -1, 2, -1, -1, -2, 1, 2, 0, 0, 2},
				{-2, -1, 0, 0, 1, 0, 1, 2, -1, -2, 1, 1},
				{1, 1, -2, -2, 2, 0, -1, 1, 0, 0, -1, -1}
			},
			{
				{-1, -1, 2, 0, -1, 0, -1, 1, 0, 0, -2, -2},
				{-2, 0, 1, 0, 2, 0, -1, 1, 2, 0, 1, 0},
				{-2, -2, -1, -1, 1, -1, 0, -2, 0, 0, -1, 1},
				{-2, 2, -2, 0, 0, 1, -1, 0, 2, -2, -1, 1},
				{-2, 0, -1, -2, -2, -2, -1, 1, 1, 1, 0, -1},
				{-1, -2, 1, -1, 0, 0, 0, 1, 0, -1, 2, 0},
				{-1, -1, 0, 0, 1, 1, 0, 0, 0, 0, -1, -1},
				{-1, 2, 0, 0, 1, -2, 1, 0, 2, -1, 1, 0},
				{1, 0, 2, 1, -1, 1, 1, 1, 0, -2, 2, 0},
				{-1, 0, 0, -1, -2, 1, 2, 1, 1, 0, 0, -1}
			}
	};
	private final double r;
	private final BombEdgework ew;
	public Astrology(double resizer, BombEdgework e)
	{
		r = resizer;
		ew = e;
	}
	public void run()
	{
		String[][] symbolList =
			{
					{"Fire", "Water", "Earth", "Air"},
					{"Sun", "Jupiter", "Moon", "Saturn", "Mercury", "Uranus", "Venus", "Neptune", "Mars", "Pluto"},
					{"Aries", "Leo", "Sagittarius", "Taurus", "Virgo", "Capricorn", "Gemini", "Libra", "Aquarius", "Cancer", "Scorpio", "Pisces"}
			};
		String[] symbols = {"element", "planet", "zodiac"};
		int omenScore = 0;
		for(int aa = 0; aa < 3; aa++)
		{
			JFrame frame = new JFrame();
			JOptionPane optionPane = new JOptionPane();
			ImageIcon[] icon = new ImageIcon[symbolList[aa].length];
			JButton[] jButton = new JButton[symbolList[aa].length];
			optionPane.setLayout(new GridLayout(4 + (aa % 2), aa + 1));
			optionPane.setOptions(new Object[] {});
			optionPane.removeAll();
			for(int bb = 0; bb < symbolList[aa].length; bb++)
			{
				icon[bb] = new ImageIcon("img/Astrology" + symbolList[aa][bb] + ".png");
				Image image = icon[bb].getImage();
				image = image.getScaledInstance((int)(icon[bb].getIconWidth() / r), (int)(icon[bb].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
				icon[bb] = new ImageIcon(image);
				jButton[bb] = getButton(optionPane, symbolList[aa][bb], icon[bb]);
				optionPane.add(jButton[bb]);
			}
			JDialog dialog = optionPane.createDialog(frame, "");
			dialog.setTitle("Select the " + symbols[aa] + " symbol:");
			dialog.setVisible(true);
			symbols[aa] = (String)optionPane.getValue();
			if(ew.numCharsInSN(symbols[aa]) > 0)
				omenScore++;
			else
				omenScore--;
		}
		omenScore += charts[0][getSymbolIndex(symbols[0])][getSymbolIndex(symbols[1])] + charts[1][getSymbolIndex(symbols[0])][getSymbolIndex(symbols[2])] + charts[2][getSymbolIndex(symbols[1])][getSymbolIndex(symbols[2])];
		if(omenScore > 0)
			JOptionPane.showMessageDialog(null, "Press GOOD OMEN when\nthe timer contains a " + omenScore);
		else if(omenScore < 0)
			JOptionPane.showMessageDialog(null, "Press POOR OMEN when\nthe timer contains a " + (omenScore * -1));
		else
			JOptionPane.showMessageDialog(null, "Press NO OMEN");
	}
	private int getSymbolIndex(String s)
	{
		switch(s)
		{
			case "FIRE":case "SUN":case "ARIES":
				return 0;
			case "WATER":case "MOON":case "TAURUS":
				return 1;
			case "EARTH":case "MERCURY":case "GEMINI":
				return 2;
			case "AIR":case "VENUS":case "CANCER":
				return 3;
			case "MARS":case "LEO":
				return 4;
			case "JUPITER":case "VIRGO":
				return 5;
			case "SATURN":case "LIBRA":
				return 6;
			case "URANUS":case "SCORPIO":
				return 7;
			case "NEPTUNE":case "SAGITTARIUS":
				return 8;
			case "PLUTO":case "CAPRICORN":
				return 9;
			case "AQUARIUS":
				return 10;
			case "PISCES":
				return 11;
		}
		return -1;
	}
	private JButton getButton(final JOptionPane optionPane, String text, ImageIcon icon ) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    button.setText(text);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(text.toUpperCase());
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
}
