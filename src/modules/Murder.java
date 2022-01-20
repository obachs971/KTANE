package modules;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class Murder 
{
	private final String[][] table =
		{
				{"DINING ROOM", "LIBRARY", "LOUNGE", "KITCHEN", "STUDY", "CONSERVATORY"},
				{"STUDY", "HALL", "BILLIARD ROOM", "LOUNGE", "KITCHEN", "LIBRARY"},
				{"KITCHEN", "BILLIARD ROOM", "BALLROOM", "LIBRARY", "CONSERVATORY", "DINING ROOM"},
				{"LOUNGE", "BALLROOM", "DINING ROOM", "CONSERVATORY", "HALL", "KITCHEN"},
				{"BILLIARD ROOM", "KITCHEN", "STUDY", "BALLROOM", "DINING ROOM", "HALL"},
				{"CONSERVATORY", "LOUNGE", "LIBRARY", "STUDY", "BILLIARD ROOM", "BALLROOM"},
				{"BALLROOM", "CONSERVATORY", "KITCHEN", "HALL", "LIBRARY", "STUDY"},
				{"HALL", "STUDY", "CONSERVATORY", "DINING ROOM", "LOUNGE", "BILLIARD ROOM"},
				{"LIBRARY", "DINING ROOM", "HALL", "BILLIARD ROOM", "BALLROOM", "LOUNGE"}
		};
	private final BombEdgework ew;
	public Murder(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{
		ArrayList<String> suspects = new ArrayList<String>(), weapons = new ArrayList<String>();
		//Inputting Suspects
		for(int aa = 0; aa < 4; aa++)
		{
			String input = JOptionPane.showInputDialog("Enter suspect #" + (aa + 1) + ":").toUpperCase();
			boolean v = v1(input, suspects);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter suspect #" + (aa + 1) + ":").toUpperCase();
				v = v1(input, suspects);
			}
			suspects.add(getSus(input));
		}
		//Inputting weapons
		for(int aa = 0; aa < 4; aa++)
		{
			String input = JOptionPane.showInputDialog("Enter weapon #" + (aa + 1) + ":").toUpperCase();
			boolean v = v2(input, weapons);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter weapon #" + (aa + 1) + ":").toUpperCase();
				v = v2(input, weapons);
			}
			weapons.add(getWeapon(input));
		}
		//Inputting the room
		String room = JOptionPane.showInputDialog("Enter the red room:").toUpperCase();
		room = getRoom(room);
		while(room.length() == 0)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			room = JOptionPane.showInputDialog("Enter the red room:").toUpperCase();
			room = getRoom(room);
		}
		int susRow, weaRow;
		//Getting the suspect row
		if(ew.findLit("TRN"))
			susRow = 4;
		else if(room.equals("DINING ROOM"))
			susRow = 6;
		else if(ew.findPort("RCA") >= 2)
			susRow = 7;
		else if(ew.BD() == 0)
			susRow = 1;
		else if(room.equals("STUDY"))
			susRow = 3;
		else if(ew.BAT() >= 5)
			susRow = 8;
		else if(ew.findUnlit("FRQ"))
			susRow = 0;
		else if(room.equals("CONSERVATORY"))
			susRow = 2;
		else
			susRow = 5;
		//Getting the weapon row
		if(room.equals("LOUNGE"))
			weaRow = 2;
		else if(ew.BAT() >= 5)
			weaRow = 0;
		else if(ew.findPort("SERIAL") > 0)
			weaRow = 8;
		else if(room.equals("BILLIARD ROOM"))
			weaRow = 3;
		else if(ew.BAT() == 0)
			weaRow = 5;
		else if(ew.numLit() == 0)
			weaRow = 4;
		else if(room.equals("HALL"))
			weaRow = 6;
		else if(ew.findPort("RCA") >= 2)
			weaRow = 1;
		else
			weaRow = 7;
		System.out.println(susRow);
		System.out.println(weaRow);
		//Get Solution
		String[] solution = {"", "", ""};
		for(int aa = 0; aa < suspects.size(); aa++)
		{
			for(int bb = 0; bb < weapons.size(); bb++)
			{
				if(table[susRow][getSusCol(suspects.get(aa))].equals(table[weaRow][getWeaCol(weapons.get(bb))]))
				{
					solution[0] = suspects.get(aa);
					solution[1] = weapons.get(bb);
					solution[2] = table[susRow][getSusCol(suspects.get(aa))];
					break;
				}
			}
			if(solution[0].length() > 0)
				break;
		}
		JOptionPane.showMessageDialog(null, "Murderer: " + solution[0] + "\nWeapon: " + solution[1] + "\nRoom: " + solution[2]);
		suspects.remove(solution[0]);
		weapons.remove(solution[1]);
		String souv = "MURDERER: " + solution[0] + "\nMURDER WEAPON: " + solution[1] + "\nROOM: " + room + "\nSUSPECTS: " + suspects.get(0);
		for(int aa = 1; aa < suspects.size(); aa++)
			souv = souv + ", " + suspects.get(aa);
		souv = souv + "\nWEAPONS: " + weapons.get(0);
		for(int aa = 1; aa < weapons.size(); aa++)
			souv = souv + ", " + weapons.get(aa);
		//System.out.println(souv);
		return souv;
	}
	private String getSus(String s)
	{
		switch(s)
		{
			case "MISS SCARLETT":
			case "SCARLETT":
				return "SCARLETT";
			case "PROFESSOR PLUM":
			case "PLUM":
				return "PLUM";
			case "MRS PEACOCK":
			case "PEACOCK":
				return "PEACOCK";
			case "REVEREND GREEN":
			case "GREEN":
				return "GREEN";
			case "COLONEL MUSTARD":
			case "MUSTARD":
				return "MUSTARD";
			case "MRS WHITE":
			case "WHITE":
				return "WHITE";
		}
		return "";
	}
	private String getWeapon(String w)
	{
		switch(w)
		{
			case "CANDLESTICK":
			case "CANDLE":
				return "CANDLESTICK";
			case "DAGGER":
				return "DAGGER";
			case "LEAD PIPE":
			case "PIPE":
				return "LEAD PIPE";
			case "REVOLVER":
				return "REVOLVER";
			case "ROPE":
				return "ROPE";
			case "SPANNER":
				return "SPANNER";
		}
		return "";
	}
	private String getRoom(String r)
	{
		switch(r)
		{
			case "DINING ROOM":
			case "DINING":	
				return "DINING ROOM";
			case "STUDY":
				return "STUDY";
			case "KITCHEN":
				return "KITCHEN";
			case "LOUNGE":
				return "LOUNGE";
			case "BILLIARD ROOM":
			case "BILLIARD":
				return "BILLIARD ROOM";
			case "CONSERVATORY":
				return "CONSERVATORY";
			case "BALLROOM":
			case "BALL":
				return "BALLROOM";
			case "HALL":
				return "HALL";
			case "LIBRARY":
				return "LIBRARY";
		}
		return "";
	}
	private int getSusCol(String s)
	{
		String[] sus = {"SCARLETT", "PLUM", "PEACOCK", "GREEN", "MUSTARD", "WHITE"};
		for(int aa = 0; aa < sus.length; aa++)
		{
			if(sus[aa].equals(s))
				return aa;
		}
		return -1;
	}
	private int getWeaCol(String w)
	{
		String[] wea = {"CANDLESTICK", "DAGGER", "LEAD PIPE", "REVOLVER", "ROPE", "SPANNER"};
		for(int aa = 0; aa < wea.length; aa++)
		{
			if(wea[aa].equals(w))
				return aa;
		}
		return -1;
	}
	private boolean v1(String s, ArrayList<String> sus)
	{
		String temp = getSus(s);
		return (!(sus.contains(temp)) && getSusCol(temp) != -1);
	}
	private boolean v2(String w, ArrayList<String> wea)
	{
		String temp = getWeapon(w);
		return (!(wea.contains(temp)) && getWeaCol(temp) != -1);
	}
}
