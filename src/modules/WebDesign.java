package modules;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class WebDesign 
{
	public void run()
	{
		String input = JOptionPane.showInputDialog("Enter the selector code (spaces):").toUpperCase();
		int[] threshold = getThreshold(input.split(" "));
		while(threshold == null)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the selector code (spaces):").toUpperCase();
			threshold = getThreshold(input.split(" "));
		}
		input = JOptionPane.showInputDialog("Red, Orange, Yellow\nGreen, Blue, Purple\nMagenta, White, Gray\nEnter the first color:").toUpperCase();
		int[] target = getTarget(input);
		while(target == null)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Red, Orange, Yellow\nGreen, Blue, Purple\nMagenta, White, Gray\nEnter the first color:").toUpperCase();
			target = getTarget(input);
		}
		int lines = 0, score = 0, pos = 2;
		String[] names = {"Background", "Background-Color", "Border", "Border-Radius", "Border-Left", "Border-Right", "Border-Bottom", "Box-Shadow", "Color", "Font-Family", "Margin", "Padding", "Position", "Text-Shadow", "Z-Index", "DONE"};
		String[] vals = {"BGC", "BGC", "B", "B", "B", "B", "B", "S", "BGC", "FF", "MAR", "MAR", "POS", "S", "Z", ""};
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		JButton[] jButton = new JButton[names.length];
		optionPane.setLayout(new GridLayout(names.length / 2, 2));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int i = 0; i < names.length; i++)
		{
			jButton[i] = getButton(optionPane, names[i], vals[i]);
			optionPane.add(jButton[i]);
		}
		while(true)
		{
			JDialog dialog = optionPane.createDialog(frame, "");
			dialog.setTitle("Select the code for line #" + (lines + 1));
			dialog.setVisible(true);
			input = optionPane.getValue().toString();
			if(input.length() == 0)
				break;
			lines++;
			switch(input) 
			{
				case "MAR":
					score+=2;
					break;
				case "B":
					if(JOptionPane.showConfirmDialog(null, "Is the border 0px or 50%?") == 1)
						score++;
					break;
				case "Z":
					if(pos == 2)
						pos = JOptionPane.showConfirmDialog(null, "Is there a position?");
					if(pos == 1)
						score--;
					break;
				case "FF":
					if(JOptionPane.showConfirmDialog(null, "Is it in Comic Sans MS?") == 0)
						score-=5;
					else
						score++;
					break;
				case "S":
					if(JOptionPane.showConfirmDialog(null, "Is it set to none?") == 1)
						score+=2;
				case "POS":
					pos = 0;
					break;
			}
		}
		score+=lines;
		if(target[0] < threshold[0])
			score+=3;
		if(target[1] >= threshold[1])
			score+=3;
		if(target[2] > threshold[2])
			score+=3;
		if(JOptionPane.showConfirmDialog(null, "Are the buttons colored?") == 0)
			score*=2;
		else
			score-=3;
		while(score <= 0)
			score+=16;
		while(score > 9)
		{
			String temp = score + "";
			score = 0;
			for(int aa = 0; aa < temp.length(); aa++)
				score += "0123456789".indexOf(temp.charAt(aa));
		}
		switch(score)
		{
			case 6:
			case 8:
				JOptionPane.showMessageDialog(null, "Press Consider");
				break;
			case 1:
			case 4:
			case 9:
				JOptionPane.showMessageDialog(null, "Press Reject");
				break;
			default:
				JOptionPane.showMessageDialog(null, "Press Accept");
				break;
		}
	}
	private int[] getThreshold(String[] i)
	{
		String[][] list =
			{
					{"BODY", "A", "H3", "BLOCKQUOTE", "#HEADER", "#COMMENTS", ".POST", ".TITLE", ".AUTHOR"},
					{"DIV", "SPAN", "IMG", "A", "#MSG", "#COVER", "#CONTENT", "#SIDEBAR", ".POST", ".TITLE", ".SHARE"},
					{"DIV", "IMG", "#MAIN", "#COMMENTS", "#FULLVIEW", ".USERNAME", ".SHARE", ".LARGE"},
					{"UL", "OL", "IMG", "B", "I", "#SIDEBAR", ".AVATAR", ".USERNAME"},
					{"DIV", "IFRAME", "B", "I", "#MAIN", "#RATING", "#COMMENTS", ".USERNAME", ".SHARE", ".CHANNEL"},
					{"BODY", "IFRAME", "#RATING", "#COMMENTS", ".RATING", ".FULLSCREEN"},
					{"DIV", "H3", "IMG", "IFRAME", "#SIDEBAR", "#DOWNLOAD", ".MENU", ".AUTHOR"},
					{"BODY", "DIV", "IMG", "BLOCKQUOTE", "#HEADER", "#CONTENT", "#SIDEBAR", ".AVATAR", ".REPLY"}
			};
		boolean[] b = new boolean[i.length];
		ArrayList<int[]> threshold = new ArrayList<int[]>();
		for(int aa = 0; aa < list.length; aa++)
		{
			for(int z = 0; z < b.length; z++)
				b[z] = false;
			for(int bb = 0; bb < list[aa].length; bb++)
			{
				for(int cc = 0; cc < i.length; cc++)
				{
					if(list[aa][bb].equals(i[cc]))
					{
						b[cc] = true;
						break;
					}
				}
			}
			boolean flag = true;
			for(int z = 0; z < b.length; z++)
				flag = flag && b[z];
			if(flag)
			{
				switch(aa)
				{
					case 0:
						threshold.add(new int[] {0, 255, 0});
						break;
					case 1:
						threshold.add(new int[] {128, 64, 192});
						break;
					case 2:
						threshold.add(new int[] {186, 218, 85});
						break;
					case 3:
						threshold.add(new int[] {3, 230, 30});
						break;
					case 4:
						threshold.add(new int[] {96, 6, 30});
						break;
					case 5:
						threshold.add(new int[] {80, 19, 55});
						break;
					case 6:
						threshold.add(new int[] {176, 32, 229});
						break;
					case 7:
						threshold.add(new int[] {190, 166, 30});
						break;
				}
			}
		}
		if(threshold.size() == 1)
			return threshold.get(0);
		return null;
	}
	private int[] getTarget(String i)
	{
		switch(i)
		{
			case "BLUE":
				return new int[] {0, 0, 255};
			case "YELLOW":
				return new int[] {255, 255, 0};
			case "RED":
				return new int[] {255, 0, 0};
			case "GREEN":
				return new int[] {0, 255, 0};
			case "WHITE":
				return new int[] {255, 255, 255};
			case "ORANGE":
				return new int[] {255, 165, 0};
			case "PURPLE":
				return new int[] {128, 0, 128};
			case "MAGENTA":
				return new int[] {255, 0, 255};
			case "GRAY":
				return new int[] {128, 128, 128};
			case "NONE":
			case "":
				return new int[] {127, 127, 127};
		}
		return null;
	}
	private JButton getButton(final JOptionPane optionPane, String name, String val ) {
	    final JButton button = new JButton();
	    button.setText(name);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(val);
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
}
