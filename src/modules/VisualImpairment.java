package modules;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class VisualImpairment 
{
	private int index;
	private final String[] grids =
		{
			"RGRWGBWBRRGBRBGBWWGBRBWGR",
			"GBRBRGGGRWWWWWWWRGGGRBRBG",
			"BBBRRRBGRRRGGGBRWWWBRBWBB",
			"BRWWBWRBRBWBRWRGWBWGBGGGB",
			"RGRGWWBRRBBBRWWWGGWBBRGRG",
			"WGWBGBGRBWBGRGWRWBGBRWRRB",
			"GRWRWRWRWBBRWBGWGBGBRWGBG",
			"BBRWWBGWGWRRGWRBGGGRRGWGB",
			"WBRWGBGWGWRWBWRGBGGBWRGRB"
		};
	private final double r;
	public VisualImpairment(double resizer)
	{
		r = resizer;
	}
	public String run()
	{
		String souv = "";
		String[] colorList = {"RED", "GREEN", "BLUE", "WHITE"};
		for(int aa = 0; aa < 3; aa++)
		{
			JFrame inputFrame = new JFrame();
			JOptionPane optionPane = new JOptionPane();
			optionPane.setLayout(new GridLayout(4, 3));
			optionPane.setOptions(new Object[] {});
			optionPane.removeAll();
			JButton[] jButton = new JButton[10];
			ImageIcon[] inputIcon = new ImageIcon[10];
			for(int bb = 0; bb < 10; bb++)
			{
				inputIcon[bb] = new ImageIcon("img/VisualImpairment" + bb + ".png");
				Image image = inputIcon[bb].getImage();
				image = image.getScaledInstance((int)(inputIcon[bb].getIconWidth() / r), (int)(inputIcon[bb].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
				inputIcon[bb] = new ImageIcon(image);
				jButton[bb] = getButton(optionPane, bb, inputIcon[bb]);
				optionPane.add(jButton[bb]);
			}
			JDialog dialog = optionPane.createDialog(inputFrame, "");
			dialog.setTitle("Select the picture:");
			dialog.setVisible(true);
			if(index == 9)
				break;
			String picture = grids[index];
			String color = "";
			while(color.length() == 0)
			{
				JFrame outputFrame = new JFrame();
				JLabel[] outputLabel = new JLabel[25];
				ImageIcon[] outputIcon = new ImageIcon[25];
				outputFrame.setLayout(new GridLayout(5,5));
				for(int bb = 0; bb < picture.length(); bb++)
				{
					outputIcon[bb] = new ImageIcon("img/VisualImpairment" + picture.charAt(bb) + ".png");
					outputLabel[bb] = new JLabel();
					outputLabel[bb].setIcon(outputIcon[bb]);
					outputFrame.add(outputLabel[bb]);
				}
				outputFrame.pack();
				outputFrame.setVisible(true);
				String input = JOptionPane.showInputDialog("CW, CCW, HOR, VER\n(R)ed, (G)reen, (B)lue, (W)hite\nEnter command/color:").toUpperCase();
				boolean v = valid(input);
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog("CW, CCW, HOR, VER\n(R)ed, (G)reen, (B)lue, (W)hite\nEnter command/color:").toUpperCase();
					v = valid(input);
				}
				outputFrame.setVisible(false);
				switch(input)
				{
					case "CW":
						String t1 = "";
						for(int bb = 0; bb < picture.length(); bb++)
							t1 = t1 + "" + picture.charAt((20 - ((bb % 5) * 5)) + (bb / 5));
						picture = t1.toUpperCase();
						break;
					case "CCW":
						String t2 = "";
						for(int bb = 0; bb < picture.length(); bb++)
							t2 = t2 + "" + picture.charAt(((bb % 5) * 5) + ((4 - (bb / 5))));
						picture = t2.toUpperCase();
						break;
					case "HOR":
						String t3 = "";
						for(int bb = 0; bb < picture.length(); bb++)
							t3 = t3 + "" + picture.charAt(((4 - (bb / 5)) * 5) + (bb % 5));
						picture = t3.toUpperCase();
						break;
					case "VER":
						String t4 = "";
						for(int bb = 0; bb < picture.length(); bb++)
							t4 = t4 + "" + picture.charAt((4 - (bb % 5)) + ((bb / 5) * 5));
						picture = t4.toUpperCase();
						break;
					default:
						color = input.toUpperCase();
				}
			}
			String[] cols = {"", "", "", "", ""};
			for(int bb = 0; bb < 25; bb++)
			{
				if(picture.charAt(bb) == color.charAt(0))
					cols[bb % 5] = cols[bb % 5] + "" + ((bb / 5) + 1);
			}
			String out = "";
			for(int bb = 0; bb < 5; bb++)
			{
				if(cols[bb].length() > 0)
					out = out + "\n" + "ABCDE".charAt(bb) + "" + cols[bb];
			}
			JOptionPane.showMessageDialog(null, "Press these squares:" + out);
			souv = souv + "COLOR #" + (aa + 1) + ": " + colorList["RGBW".indexOf(color.charAt(0))] + "\n";
		}
		return souv;
	}
	private JButton getButton(final JOptionPane optionPane, int value, ImageIcon icon ) {
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
	private boolean valid(String i)
	{
		switch(i)
		{
			case "CW":case "CCW":case "HOR":case "VER":
			case "R":case "G":case "B":case "W":
			case "RED":case "GREEN":case "BLUE":case "WHITE":
				return true;
		}
		return false;
	}
}
