package modules;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class HumanResources 
{
	private final String[][] table =
		{
				{
					"Rebecca", "Damian", "Jean", "Mike", 
					"River", "Samuel", "Yoshi", "Caleb", 
					"Ashley", "Tim", "Eliott", "Ursula", 
					"Silas", "Noah", "Quinn", "Dylan"
				},
				{
					"Intellectual", "Deviser", "Confidant", "Helper", 
					"Auditor", "Innovator", "Defender", "Chameleon", 
					"Director", "Designer", "Educator", "Advocate", 
					"Manager", "Showman", "Contributor", "Entertainer"
				},	
				{
					"INTJ", "INTP", "INFJ", "INFP", 
					"ISTJ", "ISTP", "ISFJ", "ISFP", 
					"ENTJ", "ENTP", "ENFJ", "ENFP", 
					"ESTJ", "ESTP", "ESFJ", "ESFP"
				},	
		};
	public String run()
	{
		ArrayList<String> list = new ArrayList<String>();
		list.addAll(Arrays.asList(table[0]));
		Collections.sort(list);
		ArrayList<String> people = new ArrayList<String>();
		for(int i = 0; i < 10; i++)
		{
			JFrame frame = new JFrame();
			JOptionPane optionPane = new JOptionPane();
			JButton[] jButton = new JButton[list.size()];
			optionPane.setLayout(new GridLayout((list.size() + 1) / 2, 8));
			optionPane.setOptions(new Object[] {});
			optionPane.removeAll();
			for(int aa = 0; aa < list.size(); aa++)
			{
				jButton[aa] = getButton(optionPane, list.get(aa));
				optionPane.add(jButton[aa]);
			}
			JDialog dialog = optionPane.createDialog(frame, "");
			dialog.setTitle("Select the " + new String[] {"GREEN", "RED"}[i / 5] +" Person #" + ((i % 5) + 1));
			dialog.setVisible(true);
			people.add(optionPane.getValue().toString());
			list.remove(people.get(i));
		}
		list.clear();
		list.addAll(Arrays.asList(table[1]));
		Collections.sort(list);
		ArrayList<String> descriptors = new ArrayList<String>();
		for(int i = 0; i < 5; i++)
		{
			JFrame frame = new JFrame();
			JOptionPane optionPane = new JOptionPane();
			JButton[] jButton = new JButton[list.size()];
			optionPane.setLayout(new GridLayout((list.size() + 1) / 2, 8));
			optionPane.setOptions(new Object[] {});
			optionPane.removeAll();
			for(int aa = 0; aa < list.size(); aa++)
			{
				jButton[aa] = getButton(optionPane, list.get(aa));
				optionPane.add(jButton[aa]);
			}
			JDialog dialog = optionPane.createDialog(frame, "");
			dialog.setTitle("Select the " + new String[] {"RED", "GREEN"}[i / 3] +" Personality Type #" + ((i % 3) + 1));
			dialog.setVisible(true);
			descriptors.add(optionPane.getValue().toString());
			list.remove(descriptors.get(i));
		}
		//Find Employee to Fire
		ArrayList<String> MBTI = new ArrayList<String>();
		for(int i = 0; i < 3; i++)
			MBTI.add(table[2][Arrays.asList(table[1]).indexOf(descriptors.get(i))]);
		String[] RP = getTraits(MBTI), FH = new String[2];
		int cur = getBest(RP, people, 0);
		FH[0] = people.get(cur);
		//Find Employee to Hire
		descriptors.add(table[1][Arrays.asList(table[0]).indexOf(people.get(cur))]);
		MBTI.clear();
		for(int i = 3; i < 6; i++)
			MBTI.add(table[2][Arrays.asList(table[1]).indexOf(descriptors.get(i))]);
		RP = getTraits(MBTI);
		FH[1] = people.get(getBest(RP, people, 5));
		JOptionPane.showMessageDialog(null, "FIRE: " + FH[0] + "\nHIRE: " + FH[1]);
		return ("FIRED: " + FH[0] + "\nHIRED: " + FH[1] + 
				"\nGREEN DESCRIPTORS: " + descriptors.get(0) + ", " + descriptors.get(1) + ", " + descriptors.get(2) + 
				"\nRED DESCRIPTORS: " + descriptors.get(3) + ", " +  descriptors.get(4));
	}
	private String[] getTraits(ArrayList<String> MBTI)
	{
		String[] RP = { "", "" };
		for(int i = 0; i < 4; i++)
		{
			if(MBTI.get(0).charAt(i) == MBTI.get(1).charAt(i) && MBTI.get(0).charAt(i) == MBTI.get(2).charAt(i))
				RP[0] = RP[0] + "" + MBTI.get(0).charAt(i);
			else if(MBTI.get(0).charAt(i) == MBTI.get(1).charAt(i) || MBTI.get(0).charAt(i) == MBTI.get(2).charAt(i))
				RP[1] = RP[1] + "" + MBTI.get(0).charAt(i);
			else if(MBTI.get(1).charAt(i) == MBTI.get(2).charAt(i))
				RP[1] = RP[1] + "" + MBTI.get(1).charAt(i);
		}
		return RP;
	}
	private int getBest(String[] RP, ArrayList<String> people, int start)
	{
		int best = 0, cur = 0;
		for(int i = start; i < start + 5; i++)
		{
			String temp = table[2][Arrays.asList(table[0]).indexOf(people.get(i))];
			int score = 0;
			for(int j = 0; j < temp.length(); j++)
			{
				if(RP[0].contains(temp.charAt(j) + ""))
					score += 10;
				else if(RP[1].contains(temp.charAt(j) + ""))
					score++;
			}
			if(score > best)
			{
				best = score;
				cur = i;
			}
		}
		return cur;
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
