package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class Semaphore 
{
	private final String[] chart =
		{
			"SW S", "W S", "NW S", "N S", "S NE", "S E", 
			"S SE", "W SW", "SW NW", "N E", "SW N", "SW NE", 
			"SW E", "SW SE", "W NW", "W N", "W NE", "W E", 
			"W SE", "NW N", "NW NE", "N SE", "NE E", "NE SE", 
			"NW E", "SE E"
		};
	private ArrayList<String> letters;
	private ArrayList<String> numbers;
	public Semaphore(BombEdgework e)
	{
		letters = new ArrayList<String>();
		numbers = new ArrayList<String>();
		for(int aa = 0; aa < 6; aa++)
		{
			if(e.isSNDIG(aa))
				numbers.add(chart["123456789-0".indexOf(e.getSNCHAR(aa))].toUpperCase());
			else
				letters.add(chart["ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(e.getSNCHAR(aa))].toUpperCase());
		}
	}
	public void run()
	{
		boolean flag = true;
		boolean let = false;
		int counter = 1;
		do
		{
			String input = JOptionPane.showInputDialog("L - Letters\nN - Numbers\nNE - North-East\nSW - South-West\nEnter flag signal #" + counter + " (spaces):").toUpperCase();
			boolean v = valid(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("L - Letters\nN - Numbers\nNE - North-East\nSW - South-West\nEnter flag signal #" + counter + " (spaces):").toUpperCase();
				v = valid(input);
			}
			if((input.equals("N E") || input.equals("L") || input.equals("LETTERS")) && !(let))
				let = true;
			else if(input.equals("N NE") || input.equals("N") || input.equals("NUMBERS"))
				let = false;
			else if((let && !(letters.contains(input))) || (!(let) && !(numbers.contains(input))))
				flag = false;
			counter++;
		}while(flag);
		JOptionPane.showMessageDialog(null, "Set it to Flag #" + counter + "\nThen press OK");
	}
	private boolean valid(String i)
	{
		for(int aa = 0; aa < chart.length; aa++)
		{
			if(i.equals(chart[aa]))
				return true;
		}
		switch(i)
		{
			case "N NE":
			case "N E":
			case "L":
			case "LETTERS":
			case "N":
			case "NUMBERS":
				return true;
		}
		return false;
	}
}
