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

import misc.PlayType;

public class SET 
{
	private int symbolNumber;
	private final PlayType playType;
	private final double r;
	public SET(PlayType pt, double resizer)
	{
		playType = pt;
		r = resizer;
	}
	public void run()
	{
		if(playType == PlayType.Team)
		{
			ImageIcon[] images = new ImageIcon[9];
			JLabel[] labels = new JLabel[9];
			JFrame frame = new JFrame();
			frame.setLayout(new GridLayout(3, 3));
			for(int aa = 0; aa < 9; aa++)
			{
				images[aa] = new ImageIcon("img/SET" + aa + ".jpg");
				Image image = images[aa].getImage();
				image = image.getScaledInstance((int)(images[aa].getIconWidth() / r), (int)(images[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
				images[aa] = new ImageIcon(image);
				labels[aa] = new JLabel();
				labels[aa].setIcon(images[aa]);
				frame.add(labels[aa]);
			}
			frame.pack();
			frame.setVisible(true);
			JOptionPane.showMessageDialog(null, "Same/Different Shading\nSame/Different Quantity of Dots\nSame/Different Rows\nSame/Different Columns");
			frame.setVisible(false);
		}
		else
		{
			String[] solution = getSolution();
			while(solution == null)
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				solution = getSolution();
			}
			ImageIcon[] images = new ImageIcon[3];
			JLabel[] labels = new JLabel[3];
			JFrame frame = new JFrame();
			frame.setLayout(new GridLayout(3, 1));
			for(int aa = 0; aa < solution.length; aa++)
			{
				images[aa] = new ImageIcon("img/SET" + solution[aa] + ".jpg");
				Image image = images[aa].getImage();
				image = image.getScaledInstance((int)(images[aa].getIconWidth() / r), (int)(images[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
				images[aa] = new ImageIcon(image);
				labels[aa] = new JLabel();
				labels[aa].setIcon(images[aa]);
				frame.add(labels[aa]);
			}
			frame.pack();
			frame.setVisible(true);
			JOptionPane.showMessageDialog(null, "Select these buttons");
			frame.setVisible(false);
		}
	}
	private String[] getSolution()
	{
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		ImageIcon[] icon = new ImageIcon[9];
		JButton[] jButton = new JButton[9];
		optionPane.setLayout(new GridLayout(3, 3));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int aa = 0; aa < 9; aa++)
		{
			icon[aa] = new ImageIcon("img/SET" + aa + ".jpg");
			Image image = icon[aa].getImage();
			image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			icon[aa] = new ImageIcon(image);
			jButton[aa] = getButton(optionPane, (aa + 0), icon[aa]);
			optionPane.add(jButton[aa]);
		}
		String[] shapes = new String[9];
		String[] pos = {"TL", "TM", "TR", "ML", "MM", "MR", "BL", "BM", "BR"};
		for(int aa = 0; aa < 9; aa++)
		{
			JDialog dialog = optionPane.createDialog(frame, "");
			dialog.setTitle("Select the " + pos[aa] + " button's shape:");
			dialog.setVisible(true);
			shapes[aa] = JOptionPane.showInputDialog("E - Empty\nF - Filled\nW - Wavy\nEnter the shading and the\nnumber of dots on the " + pos[aa] + " button:").toUpperCase();
			boolean v = valid(shapes[aa]);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				shapes[aa] = JOptionPane.showInputDialog("E - Empty\nF - Filled\nW - Wavy\nEnter the shading and\nthe number of dots\non the " + pos[aa] + " button:").toUpperCase();
				v = valid(shapes[aa]);
			}
			shapes[aa] = symbolNumber + "" + shapes[aa];
			if(aa >= 2)
			{
				for(int bb = 0; bb < aa; bb++)
				{
					for(int cc = bb + 1; cc < aa; cc++)
					{
						if(correct(shapes[aa], shapes[bb], shapes[cc]))
							return new String[] {shapes[bb], shapes[cc], shapes[aa]};
					}
				}
			}
		}
		return null;
	}
	private JButton getButton(final JOptionPane optionPane, int value, ImageIcon icon ) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(button);
	        symbolNumber = value + 0;
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
	private boolean correct(String p1, String p2, String p3)
	{
		if(!(p1.charAt(2) == p2.charAt(2) && p1.charAt(2) == p3.charAt(2)) && !(p1.charAt(2) != p2.charAt(2) && p1.charAt(2) != p3.charAt(2) && p2.charAt(2) != p3.charAt(2)))
			return false;
		if(!(p1.charAt(1) == p2.charAt(1) && p1.charAt(1) == p3.charAt(1)) && !(p1.charAt(1) != p2.charAt(1) && p1.charAt(1) != p3.charAt(1) && p2.charAt(1) != p3.charAt(1)))
			return false;
		int d1 = "012345678".indexOf(p1.charAt(0)) / 3, d2 = "012345678".indexOf(p2.charAt(0)) / 3, d3 = "012345678".indexOf(p3.charAt(0)) / 3;
		if(!(d1 == d2 && d1 == d3) && !(d1 != d2 && d1 != d3 && d2 != d3))
			return false;
		d1 = "012345678".indexOf(p1.charAt(0)) % 3; 
		d2 = "012345678".indexOf(p2.charAt(0)) % 3;
		d3 = "012345678".indexOf(p3.charAt(0)) % 3;
		if(!(d1 == d2 && d1 == d3) && !(d1 != d2 && d1 != d3 && d2 != d3))
			return false;
		return true;
	}
	private boolean valid(String i)
	{
		if(i.length() == 2)
		{
			if("FWE".indexOf(i.charAt(0)) >= 0 && "012".indexOf(i.charAt(1)) >= 0)
				return true;
		}
		return false;
	}
}
