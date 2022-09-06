package modules;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
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
	private String sub;
	private final BombEdgework ew;
	public Murder(BombEdgework e)
	{
		ew = e;
	}
	public String run()
	{
		ArrayList<String> suspects = new ArrayList<String>(), weapons = new ArrayList<String>();
		//Inputting Suspects
		String[] list = {"Miss Scarlett", "Professor Plum", "Mrs Peacock", "Reverend Green", "Colonel Mustard", "Mrs White"};
		for(int aa = 0; aa < 4; aa++)
		{
			JDialog dialog = getDialog(list, 2);
			dialog.setTitle("Select Suspect #" + (aa + 1) + ":");
			dialog.setVisible(true);
			suspects.add(sub.toUpperCase());
			list = remove(list, sub);
		}
		//Inputting weapons
		list = new String[] {"Candlestick", "Dagger", "Lead Pipe", "Revolver", "Rope", "Spanner"};
		for(int aa = 0; aa < 4; aa++)
		{	
			JDialog dialog = getDialog(list, 2);
			dialog.setTitle("Select Weapon #" + (aa + 1) + ":");
			dialog.setVisible(true);
			weapons.add(sub.toUpperCase());
			list = remove(list, sub);
		}
		//Inputting the room
		list = new String[] {"Ballroom", "Billiard Room", "Conservatory", "Dining Room", "Hall", "Kitchen", "Lounge", "Library", "Study"};
		JDialog dialog = getDialog(list, 3);
		dialog.setTitle("Select the Red Room:");
		dialog.setVisible(true);
		String room = sub.toUpperCase();
		
		
		
		
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
		System.out.println(souv);
		return souv;
	}
	
	private int getSusCol(String s)
	{
		String[] sus = {"MISS SCARLETT", "PROFESSOR PLUM", "MRS PEACOCK", "REVEREND GREEN", "COLONEL MUSTARD", "MRS WHITE"};
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
	private JDialog getDialog(String[] arr, int div)
	{
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		JButton[] jButton = new JButton[arr.length];
		optionPane.setLayout(new GridLayout(arr.length / div + (arr.length % div), div));
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
	private String[] remove(String[] arr, String str)
	{
		String[] conv = new String[arr.length - 1];
		int cur = 0;
		for(int i = 0; i < arr.length; i++)
		{
			if(!(arr[i].equalsIgnoreCase(str)))
				conv[cur++] = arr[i];
		}
		return conv;
	}
}
