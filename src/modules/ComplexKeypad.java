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
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import start.BombEdgework;

public class ComplexKeypad 
{	
	private final int[][] chart =
		{
				{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
				{11, 1, 7, 14, 15, 16, 17, 18, 19, 20},
				{12, 20, 14, 16, 3, 9, 2, 11, 13, 10},
				{13, 18, 12, 2, 5, 14, 1, 8, 20, 21},
				{16, 14, 5, 15, 21, 19, 6, 1, 17, 9}
		};
	private int symbol = -1;
	private final BombEdgework ew;
	private final double r;
	public ComplexKeypad(BombEdgework e, double resizer)
	{
		ew = e;
		r = resizer;
	}
	public void run()
	{
		if(ew.BAT() > 2 && ew.findPort("PARALLEL") > 0)
			JOptionPane.showMessageDialog(null, "You got a UNICORN\nPress the buttons in Reading Order");
		else
		{
			ArrayList<Integer> possSymbols = new ArrayList<Integer>();
			for(int i = 1; i <= 21; i++)
				possSymbols.add(i);
			ArrayList<Integer> rows = new ArrayList<Integer>();
			ArrayList<Integer> symbols = new ArrayList<Integer>();
			int cur = 1;
			do
			{
				JDialog dialog = getDialog(possSymbols, 3);
				dialog.setTitle("Select Symbol #" + (cur++) + ":");
				dialog.setVisible(true);
				symbols.add(symbol);
				rows = getRows(symbols);
				possSymbols.clear();
				for(int row : rows)
				{
					for(int number : chart[row])
					{
						if(!(possSymbols.contains(number)) && !(symbols.contains(number)))
							possSymbols.add(number);
					}
				}
				Collections.sort(possSymbols);
			}while(rows.size() != 1);
			possSymbols.clear();
			for(int number : chart[rows.get(0)])
				possSymbols.add(number);
			if(ew.findLit("BOB") && ew.findPort("DVI-D") > 0)
				Collections.reverse(possSymbols);
			ImageIcon[] solution = new ImageIcon[possSymbols.size()];
			JLabel[] solutionLabel = new JLabel[possSymbols.size()];
			JFrame solutionFrame = new JFrame();
			solutionFrame.setLayout(new GridLayout(possSymbols.size(),1));
			for(int i = 0; i < possSymbols.size(); i++)
			{
				solution[i] = new ImageIcon("img/ComplexKeypad" + possSymbols.get(i) + ".png");
				Image image = solution[i].getImage();
				image = image.getScaledInstance((int)(solution[i].getIconWidth() / r), (int)(solution[i].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
				solution[i] = new ImageIcon(image);
				solutionLabel[i] = new JLabel();
				solutionLabel[i].setIcon(solution[i]);
				solutionFrame.add(solutionLabel[i]);
			}
			solutionFrame.pack();
			solutionFrame.setVisible(true);
			JOptionPane.showMessageDialog(null, "Press the symbols in this order");
			solutionFrame.setVisible(false);
		}
	}
	private ArrayList<Integer> getRows(ArrayList<Integer> list)
	{
		boolean[][] b = new boolean[chart.length][list.size()];
		ArrayList<Integer> rows = new ArrayList<Integer>();
		for(int i = 0; i < chart.length; i++)
		{
			for(int j = 0; j < b[i].length; j++)
				b[i][j] = false;
			for(int j = 0; j < chart[i].length; j++)
			{
				if(list.contains(chart[i][j]))
					b[i][list.indexOf(chart[i][j])] = true;
			}
			boolean flag = true;
			for(int j = 0; j < b[i].length; j++)
				flag = flag && b[i][j];
			if(flag)
				rows.add(i);
		}
		return rows;
	}
	private JDialog getDialog(ArrayList<Integer> arr, int div)
	{
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		JButton[] jButton = new JButton[arr.size()];
		if(arr.size() % div == 0)
			optionPane.setLayout(new GridLayout(arr.size() / div, div));
		else
			optionPane.setLayout(new GridLayout(arr.size() / div + 1, div));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		ImageIcon[] icon = new ImageIcon[arr.size()];
		for(int aa = 0; aa < arr.size(); aa++)
		{
			icon[aa] = new ImageIcon("img/ComplexKeypad" + arr.get(aa) + ".png");
			Image image = icon[aa].getImage();
			image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			icon[aa] = new ImageIcon(image);
			jButton[aa] = getButton(optionPane, arr.get(aa), icon[aa]);
			optionPane.add(jButton[aa]);
		}
		return optionPane.createDialog(frame, "");
	}
	private JButton getButton(final JOptionPane optionPane, int val, ImageIcon icon) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(val);
	        symbol = val;
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
}
