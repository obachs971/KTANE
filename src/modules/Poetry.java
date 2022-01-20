package modules;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Poetry 
{
	private int index;
	private final String[][] wordLists =
		{
				{"BUNNY","COOKIES","RELAX","LOVELY","COMPASSION","CROWD","DANCE","ROMANCE","CREATION","ENERGY","HEART","WEIGHTLESS","FUTURE","PATIENCE","SUNSHINE","WEATHER","MORALITY","FOCUS","CLARITY","OCEAN","WORDS","GAZE","SEARCH","FLOW","REFLECTION","PAST","FAILURE","FATIGUE","IDENTITY","SOLITARY","HOLLOW","BLACK"},
				{"HOLLOW","BLACK","FATIGUE","IDENTITY","SOLITARY","FLOW","REFLECTION","PAST","FAILURE","CLARITY","OCEAN","WORDS","GAZE","SEARCH","SUNSHINE","WEATHER","MORALITY","FOCUS","ENERGY","HEART","WEIGHTLESS","FUTURE","PATIENCE","CROWD","DANCE","ROMANCE","CREATION","RELAX","LOVELY","COMPASSION","BUNNY","COOKIES"},
				{"SEARCH","PATIENCE","FAILURE","FOCUS","CREATION","SOLITARY","GAZE","FUTURE","COMPASSION","BLACK","PAST","MORALITY","ROMANCE","COOKIES","IDENTITY","WORDS","WEIGHTLESS","LOVELY","HOLLOW","REFLECTION","WEATHER","DANCE","BUNNY","FATIGUE","OCEAN","HEART","RELAX","FLOW","SUNSHINE","CROWD","CLARITY","ENERGY"},
				{"CLARITY","ENERGY","FLOW","SUNSHINE","CROWD","FATIGUE","OCEAN","HEART","RELAX","HOLLOW","REFLECTION","WEATHER","DANCE","BUNNY","IDENTITY","WORDS","WEIGHTLESS","LOVELY","BLACK","PAST","MORALITY","ROMANCE","COOKIES","SOLITARY","GAZE","FUTURE","COMPASSION","FAILURE","FOCUS","CREATION","SEARCH","PATIENCE"}
		};
	private final int playType;
	private final double r;
	private final boolean isSouv;
	public Poetry(boolean s, int pt, double resizer)
	{
		isSouv = s;
		playType = pt;
		r = resizer;
	}
	public String run()
	{
		String souv = "";
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		ImageIcon[] icon = new ImageIcon[4];
		JButton[] jButton = new JButton[4];
		optionPane.setLayout(new GridLayout(2, 2));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		String[] people = {"Hana", "Jane", "Lacy", "Melanie"};
		for(int aa = 0; aa < 4; aa++)
		{
			icon[aa] = new ImageIcon("img/Poetry" + people[aa] + ".png");
			Image image = icon[aa].getImage();
			image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			icon[aa] = new ImageIcon(image);
			jButton[aa] = getButton(optionPane, people[aa], aa, icon[aa]);
			optionPane.add(jButton[aa]);
		}
		JDialog dialog = optionPane.createDialog(frame, "");
		dialog.setTitle("Select the poet:");
		dialog.setVisible(true);
		if(playType == 1)
		{
			int[] next = {2, 3, 4, 5, 4, 5, 4};
			int cur = 0;
			String out = "";
			for(int aa = 0; aa < next.length; aa++)
			{
				for(int bb = 0; bb < next[aa]; bb++)
					out = out + "" + wordLists[index][cur + bb].charAt(0) + "" + wordLists[index][cur + bb].substring(1).toLowerCase() + " ";
				out = out + "\n";
				cur += next[aa];
			}
			if(isSouv)
			{
				for(int aa = 0; aa < 3; aa++)
				{
					souv = souv + "WORD #" + (aa + 1) + ": ";
					String input = JOptionPane.showInputDialog("Press the first word that appears:\n" + out + "\nEnter the word:").toUpperCase();
					int check = getIndex(wordLists[index], input);
					while(check == -1)
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						input = JOptionPane.showInputDialog("Press the first word that appears:\n" + out + "\nEnter the word:").toUpperCase();
						check = getIndex(wordLists[index], input);
					}
					souv = souv + "" + input.toUpperCase() + "\n";
				}
			}
			else
				JOptionPane.showMessageDialog(null, "Press the first word that appears:\n" + out);
		}
		else
		{
			for(int aa = 0; aa < 3; aa++)
			{
				ArrayList<Integer> indexes = new ArrayList<Integer>();
				souv = souv + "WORD #" + (aa + 1) + ": ";
				for(int bb = 0; bb < 6; bb++)
				{
					String input = JOptionPane.showInputDialog("Enter word #" + (bb + 1) + ":").toUpperCase();
					int check = getIndex(wordLists[index], input);
					while(check == -1 || indexes.contains(check))
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						input = JOptionPane.showInputDialog("Enter word #" + (bb + 1) + ":").toUpperCase();
						check = getIndex(wordLists[index], input);
					}
					indexes.add(check);
					if(check < 2)
						break;
				}
				Collections.sort(indexes);
				JOptionPane.showMessageDialog(null, "Press " + wordLists[index][indexes.get(0)]);
				souv = souv + "" + wordLists[index][indexes.get(0)] + "\n";
			}
		}
		return souv;
	}
	private JButton getButton(final JOptionPane optionPane, String name, int value, ImageIcon icon ) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(button);
	        index = value;
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
	private int getIndex(String[] arr, String w)
	{
		for(int aa = 0; aa < arr.length; aa++)
		{
			if(arr[aa].equals(w))
				return aa;
		}
		return -1;
	}
}
