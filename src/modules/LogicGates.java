package modules;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import misc.MTK;

public class LogicGates 
{
	private final double r;
	public LogicGates(double resizer)
	{
		r = resizer;
	}
	public String run()
	{
		ArrayList<String> gates = new ArrayList<String>();
		ArrayList<String> gateNames = new ArrayList<String>(Arrays.asList(new String[] {"AND", "OR", "XOR", "NAND", "NOR", "XNOR"}));
		boolean dup = false;
		for(int i = 0; i < 4; i++)
		{
			JFrame frame = new JFrame();
			JOptionPane optionPane = new JOptionPane();
			ImageIcon[] icon = new ImageIcon[gateNames.size()];
			JButton[] jButton = new JButton[gateNames.size()];
			optionPane.setLayout(new GridLayout(2, 3));
			optionPane.setOptions(new Object[] {});
			optionPane.removeAll();
			for(int aa = 0; aa < gateNames.size(); aa++)
			{
				icon[aa] = new ImageIcon("img/LogicGates" + gateNames.get(aa) + ".png");
				Image image = icon[aa].getImage();
				image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
				icon[aa] = new ImageIcon(image);
				jButton[aa] = getButton(optionPane, gateNames.get(aa), icon[aa]);
				optionPane.add(jButton[aa]);
			}
			JDialog dialog = optionPane.createDialog(frame, "");
			dialog.setTitle("Select the gate for " + "ABCD".charAt(i) + ":");
			dialog.setVisible(true);
			String gate = optionPane.getValue().toString();
			if(dup)
				gateNames.remove(gate);
			else if(gates.contains(gate))
			{
				dup = true;
				for(String g : gates)
					gateNames.remove(g);
			}
			gates.add(gate);
		}
		gateNames = new ArrayList<String>(Arrays.asList(new String[] {"AND", "OR", "XOR", "NAND", "NOR", "XNOR"}));
		int[] indexes = {0, 1, 4, 2, 5, 3};
		for(int i = 0; i < indexes.length; i+=2)
		{
			int index = (gateNames.indexOf(gates.get(indexes[i])) + gateNames.indexOf(gates.get(indexes[i + 1])) + 1) % 6;
			if(dup)
			{
				while(gates.contains(gateNames.get(index)))
					index = (index + 1) % 6;
			}
			else if(gates.contains(gateNames.get(index)))
				dup = true;
			gates.add(gateNames.get(index));
		}
		MTK mtk = new MTK();
		String solutions = "";
		for(int i = 0; i < 16; i++)
		{
			String bin = mtk.baseConvert(i + "", 10, 2);
			while(bin.length() < 4)
				bin = "0" + bin;
			boolean e = mtk.solveLogicExpression(bin.charAt(0) == '1', bin.charAt(1) == '1', gates.get(4));
			boolean f = mtk.solveLogicExpression(bin.charAt(2) == '1', bin.charAt(3) == '1', gates.get(5));
			if(mtk.solveLogicExpression(e, f, gates.get(6)))
				solutions = solutions + "" + bin + "\n";
		}
		JOptionPane.showMessageDialog(null, "Gate E: " + gates.get(4) + "\nGate F: " + gates.get(5) + "\nGate G: " + gates.get(6) + "\nSolutions:\n" + solutions);
		return ("GATE A: " + gates.get(0) + "\nGATE B: " + gates.get(1) + "\nGATE C: " + gates.get(2) + "\nGATE D: " + gates.get(3) + "\nGATE E: " + gates.get(4) + "\nGATE F: " + gates.get(5) + "\nGATE G: " + gates.get(6));
	}
	private JButton getButton(final JOptionPane optionPane, String text, ImageIcon icon ) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	    	  optionPane.setValue(text);
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
}
