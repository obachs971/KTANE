package modules;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LondonUnderground 
{
	//bakerloo 0
	//central 1
	//circle 2
	//district 3 
	//hammersmith 4
	//jubilee 5
	//metropolitan 6
	//northern 7
	//piccadilly 8
	//victoria 9
	private final String[][] map =
		{
				{"Acton Town", "38"},{"Aldgate", "26"},{"Aldgate East", "34"},{"Angel", "7"},{"Archway", "7"},{"Arsenal", "8"},
				{"Baker Street", "02456"},{"Barbican", "246"},{"Barons Court", "38"},{"Bayswater", "23"},{"Belsize Park", "7"},{"Bermondsey", "5"},{"Bethnal Green", "1"},{"Blackfriars", "23"},{"Blackhorse Road", "9"},{"Bond Street", "15"},{"Borough", "7"},{"Bounds Green", "8"},{"Bow Road", "34"},{"Brent Cross", "7"},{"Brixton", "9"},{"Bromley-by-bow", "34"},
				{"Caledonian Road", "8"},{"Camden Town", "7"},{"Canada Water", "5"},{"Canary Wharf", "5"},{"Canning Town", "5"},{"Cannon Street", "23"},{"Chalk Farm", "7"},{"Chancery Lane", "1"},{"Charing Cross", "07"},{"Chiswick Park", "3"},{"Clapham Common", "7"},{"Clapham North", "7"},{"Clapham South", "7"},{"Covent Garden", "8"},
				{"Dollis Hill", "5"},			
				{"Ealing Broadway", "13"},{"Ealing Common", "38"},{"Earl's Court", "38"},{"East Acton", "1"},{"East Finchley", "7"},{"East Ham", "34"},{"Edgware Road", "0234"},{"Elephant & Castle", "07"},{"Embankment", "0237"},{"Euston", "79"},{"Euston Square", "246"},
				{"Farringdon", "246"},{"Finchley Road", "56"},{"Finsbury Park", "89"},
				{"Gloucester Road", "238"},{"Golders Green", "7"},{"Goldhawk Road", "24"},{"Goodge Street", "7"},{"Great Portland Street", "246"},{"Green Park", "589"},
				{"Hammersmith", "2348"},{"Hampstead", "7"},{"Hanger Lane", "1"},{"Harlesden", "0"},{"Hendon Central", "7"},{"High Street Kensington", "23"},{"Highbury & Islington", "9"},{"Highgate", "7"},{"Holborn", "18"},{"Holland Park", "1"},{"Holloway Road", "8"},{"Hyde Park Corner", "8"},
				{"Kennington", "7"},{"Kensal Green", "0"},{"Kentish Town", "7"},{"Kilburn", "5"},{"Kilburn Park", "0"},{"King's Cross St. Pancras", "246789"},{"Knightsbridge", "8"},
				{"Ladbroke Grove", "24"},{"Lambeth North", "0"},{"Lancaster Gate", "1"},{"Latimer Road", "24"},{"Leicester Square", "78"},{"Leyton", "1"},{"Leytonstone", "1"},{"Liverpool Street", "1246"},{"London Bridge", "57"},
				{"Maida Vale", "0"},{"Manor House", "8"},{"Mansion House", "23"},{"Marble Arch", "1"},{"Marylebone", "0"},{"Mile End", "134"},{"Monument/Bank", "1237"},{"Moorgate", "2467"},{"Mornington Crescent", "7"},
				{"Neasden", "5"},{"North Acton", "1"},{"North Ealing", "8"},{"North Greenwich", "5"},{"Northfields", "8"},{"Notting Hill Gate", "123"},
				{"Old Street", "7"},{"Oval", "7"},{"Oxford Circus", "019"},
				{"Paddington", "0234"},{"Park Royal", "8"},{"Piccadilly Circus", "08"},{"Pimlico", "9"},{"Plaistow", "34"},
				{"Queen's Park", "0"},{"Queensway", "1"},
				{"Ravenscourt Park", "3"},{"Regent's Park", "0"},{"Royal Oak", "24"},{"Russell Square", "8"},
				{"Seven Sisters", "9"},{"Shepherd's Bush", "1"},{"Shepherd's Bush Market", "24"},{"Sloane Square", "23"},{"South Ealing", "8"},{"South Kensington", "238"},{"Southwark", "5"},{"St. James's Park", "23"},{"St. John's Wood", "5"},{"St. Paul's", "1"},{"Stamford Brook", "3"},{"Stepney Green", "34"},{"Stockwell", "79"},{"Stonebridge Park", "0"},{"Stratford", "15"},{"Swiss Cottage", "5"},
				{"Temple", "23"},{"Tottenham Court Road", "17"},{"Tottenham Hale", "9"},{"Tower Hill", "23"},{"Tufnell Park", "7"},{"Turnham Green", "38"},{"Turnpike Lane", "8"},
				{"Upton Park", "34"},
				{"Vauxhall", "9"},{"Victoria", "239"},
				{"Walthamstow Central", "9"},{"Warren Street", "79"},{"Warwick Avenue", "0"},{"Waterloo", "057"},{"West Acton", "1"},{"West Ham", "345"},{"West Hampstead", "5"},{"West Kensington", "3"},{"Westbourne Park", "24"},{"Westminster", "235"},{"White City/Wood Lane", "124"},{"Whitechapel", "34"},{"Willesden Green", "5"},{"Willesden Junction", "0"},{"Wood Green", "8"}
		};
	
	public String run()
	{
		String souv = "";
		String[] district = {"Bakerloo", "Central", "Circle", "District", "Hammersmith", "Jubilee", "Metropolitan", "Northern", "Piccadilly", "Victoria"};
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		JButton[] jButton = new JButton[map.length];
		optionPane.setLayout(new GridLayout(31, 5));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int aa = 0; aa < map.length; aa++)
		{
			jButton[aa] = getButton(optionPane, map[aa][0]);
			optionPane.add(jButton[aa]);
		}
		JDialog dialog = optionPane.createDialog(frame, "");
		dialog.setTitle("Select the top city:");
		dialog.setVisible(true);
		String top = optionPane.getValue().toString().replace("", "");
		for(int i = 0; i < 3; i++)
		{
			souv = souv + "DEPART #" + (i + 1) + ": " + top.toUpperCase() + "\n";
			dialog = optionPane.createDialog(frame, "");
			dialog.setTitle("Select the bottom city:");
			dialog.setVisible(true);
			String[] solution = solving(top, optionPane.getValue().toString().replace("", ""));
			String out = "";
			for(int j = 0; j < solution.length; j++)
				out = out + "\n" + district["0123456789".indexOf(solution[j].charAt(0))] + ": " + solution[j].substring(1);		
			JOptionPane.showMessageDialog(null, "Submit this route: " + out);
			top = optionPane.getValue().toString().replace("", "");
			souv = souv + "ARRIVE #" + (i + 1) + ": " + top.toUpperCase() + "\n";
		}
		return souv;
	}
	private String[] solving(String curr, String goal)
	{
		String[] cursor = new String[2];
		for(int i = 0; i < map.length; i++)
		{
			if(map[i][0].equals(curr))
			{
				cursor = map[i];
				break;
			}
		}
		if(curr.equals(goal))
			return new String[] {cursor[1].charAt(0) + "" + cursor[0]};
		ArrayList<String> prev = new ArrayList<String>();
		prev.add(cursor[0].replace("", ""));
		ArrayList<String> paths = new ArrayList<String>();
		for(int i = 0; i < map.length; i++)
		{
			if(!(prev.contains(map[i][0])))
			{
				char connection = getConnection(cursor[1], map[i][1]);
				if(connection != '-')
				{
					if(goal.equals(map[i][0]))
						return new String[] {connection + "" + map[i][0]};
					paths.add(connection + "" + map[i][0]);	
					prev.add(map[i][0]);
				}
			}
		}
		for(int i = 0; i < paths.size(); i++)
		{
			for(int j = 0; j < map.length; j++)
			{
				if(!(prev.contains(map[j][0])))
				{
					char connection = getConnection(getConnection(paths.get(i).substring(1)), map[j][1]);
					if(connection != '-')
					{
						if(goal.equals(map[j][0]))
						{
							return new String[] {paths.get(i), connection + "" + map[j][0]};
						}
					}
				}
			}
		}
		return null;
	}
	private String getConnection(String p)
	{
		for(int i = 0; i < map.length; i++)
		{
			if(map[i][0].equals(p))
				return map[i][1];
		}
		return "";
	}
	private char getConnection(String c1, String c2)
	{
		for(char c : c1.toCharArray())
		{
			if(c2.indexOf(c) >= 0)
				return c;
		}
		return '-';
	}
	private JButton getButton(final JOptionPane optionPane, String text) {
	    final JButton button = new JButton();
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
