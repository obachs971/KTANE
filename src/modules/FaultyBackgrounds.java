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

public class FaultyBackgrounds 
{
	private final String[] ruleTable =
		{
			"ADCDBFEBCE",
			"CBEDFEBCDA"
		};
	private final int[][] numberTable =
		{
				{3,2,9,1,7,4},
				{7,9,8,8,2,3},
				{5,1,7,4,4,6},
				{6,4,2,6,8,5},
				{5,1,5,3,9,9},
				{1,2,3,6,7,8}
		};
	private final BombEdgework ew;
	public FaultyBackgrounds(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		String[] options = {
				"Left Button is FAKE",
				"Right Button is FAKE",
				"Left Button Doesn't Affect Counter",
				"Right Button Doesn't Affect Counter",
				"Left Button's Color Matches Background",
				"Right Button's Color Matches Background",
				"A Button Says BUSH ME",
				"A Button Says PUSH NE",
				"Left Button Says PUSH HE",
				"Right Button Says PUSH HE",
				"Left Button Says PUSH SHE",
				"Right Button Says PUSH SHE",
				"Even Digits Invisible On Counter",
				"Odd Digits Invisible On Counter",
				"5 Is Invisible and Left Button is Black",
				"5 Is Invisible and Right Button is Black",
				"None Apply"
		};
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		JButton[] jButton = new JButton[options.length];
		optionPane.setLayout(new GridLayout(9, 2));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int aa = 0; aa < options.length; aa++)
		{
			jButton[aa] = getButton(optionPane, options[aa], aa);
			optionPane.add(jButton[aa]);
		}
		JDialog dialog = optionPane.createDialog(frame, "");
		dialog.setTitle("Select the rule that applies:");
		dialog.setVisible(true);
		String real = getRealButton(Integer.parseInt(optionPane.getValue().toString()));
		String input = JOptionPane.showInputDialog("Red, Orange, Yellow,\nGreen, Blue, Purple,\nWhite, Gray, Black\nEnter the colors of\nthe " + real + " button and\nthe background (spaces):").toUpperCase();
		boolean v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Red, Orange, Yellow,\nGreen, Blue, Purple,\nWhite, Gray, Black\nEnter the colors of\nthe " + real + " button and\nthe background (spaces):").toUpperCase();
			v = valid(input);
		}
		String[] colors = input.split(" ");
		ArrayList<String> pri = new ArrayList<String>();
		pri.add("RED");
		pri.add("BLUE");
		pri.add("YELLOW");
		ArrayList<String> sec = new ArrayList<String>();
		sec.add("ORANGE");
		sec.add("GREEN");
		sec.add("PURPLE");
		int cur = 0;
		String letters = "";
		if(colors[0].equals(colors[1]))
			letters = letters + "" + ruleTable[cur++ % 2].charAt(0);
		if((colors[0].equals("WHITE") || colors[0].equals("BLACK")) != (colors[1].equals("WHITE")))
			letters = letters + "" + ruleTable[cur++ % 2].charAt(1);
		if(ew.BD() == 0)
			letters = letters + "" + ruleTable[cur++ % 2].charAt(2);
		if(ew.BA() == 0)
			letters = letters + "" + ruleTable[cur++ % 2].charAt(3);
		if(pri.contains(colors[0]) && pri.contains(colors[1]))
			letters = letters + "" + ruleTable[cur++ % 2].charAt(4);
		if(sec.contains(colors[0]))
			letters = letters + "" + ruleTable[cur++ % 2].charAt(5);	
		if(ew.findUnlit("SND"))
			letters = letters + "" + ruleTable[cur++ % 2].charAt(6);
		if(ew.findPort("SERIAL") > 0)
			letters = letters + "" + ruleTable[cur++ % 2].charAt(7);
		if(colors[0].equals(mixBlue(colors[1])))
			letters = letters + "" + ruleTable[cur++ % 2].charAt(8);
		while(letters.length() < 2)
			letters = letters + "" + ruleTable[cur++ % 2].charAt(9);
		//System.out.println(letters);
		JOptionPane.showMessageDialog(null, "Submit this number: " + numberTable["ABCDEF".indexOf(letters.charAt(0))]["ABCDEF".indexOf(letters.charAt(1))]);
		
	}
	private String getRealButton(int choice)
	{
		switch(choice)
		{
			case 4: return "LEFT";
			case 5: return "RIGHT";
			case 16: return (ew.getSNDIG(ew.numSNDIGS() - 1) % 2 == 0) ? "LEFT" : "RIGHT";
			default: return new String[] {"RIGHT", "LEFT"}[choice % 2];
		}
	}
	private String mixBlue(String c)
	{
		switch(c)
		{
			case "BLUE":
				return "BLUE";
			case "RED":
				return "PURPLE";
			case "YELLOW":
				return "GREEN";
			default:
				return "";
		}
	}
	private boolean valid(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 2)
		{
			switch(conv[0])
			{
				case "RED":
				case "ORANGE":
				case "YELLOW":
				case "GREEN":
				case "BLUE":
				case "PURPLE":
				case "WHITE":
				case "GRAY":
				case "BLACK":
					break;
				default:
					return false;
			}
			switch(conv[1])
			{
				case "RED":
				case "ORANGE":
				case "YELLOW":
				case "GREEN":
				case "BLUE":
				case "PURPLE":
				case "WHITE":
				case "GRAY":
					break;
				default:
					return false;
			}
			return true;
		}
		return false;
	}
	private JButton getButton(final JOptionPane optionPane, String text, int value) {
	    final JButton button = new JButton();
	    button.setText(text);
	    button.setSize(50, 50);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(value);
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
}
