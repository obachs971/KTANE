package modules;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import start.BombConfig;
import start.BombEdgework;

public class Algebra 
{
	private final String[][] equations =
		{
				{"a=3z", "a=5+y", "a=6-x", "a=7x", "a=8y", "a=9+z", "a=x+1", "a=x/2", "a=y-2", "a=y/4", "a=z-7", "a=z/10"},
				{"b=(2x/10)-y", "b=(7x)y", "b=(x+y)-(z/2)", "b=(y/2)-z", "b=(z-y)/2", "b=(zy)-(2x)", "b=2(z+7)", "b=2z+7", "b=xy-(2+x)", "b=xyz"}
		};
	private int index;
	private final BombConfig cf;
	private final BombEdgework ew;
	private final double r;
	public Algebra(BombConfig c, BombEdgework e, double resizer)
	{
		cf = c;
		ew = e;
		r = resizer;
	}
	public String run()
	{
		int[] limits = {12, 10, 8};
		int x = ew.getSNSUM();
		int y = ew.numInd() - ew.numPorts();
		int z = cf.getNumberModules() + (ew.BA() * ew.BD());
		if(ew.BH() >= 3)
			x += 2;
		else
		{
			y -= 2;
			if(ew.BH() == 0)
				z += 3;
		}
		if(ew.findPort("RJ-45") > 0)
			x -= 1;
		if(ew.findPort("SERIAL") > 0)
			y += 3;
		if(ew.findPort("PARALLEL") > 0)
			z -= 6;
		if(ew.findLit("BOB"))
			x += 4;
		if(ew.findUnlit("FRQ"))
			y -= 5;
		if(ew.findLit("MSA"))
			z += 2;
		if(ew.numCharsInSN("AEIOU") > 0)
			x -= 3;
		if(isPrime(ew.getSNSUM()))
			y += 4;
		if(ew.getSNSUM() % 3 == 0)
			z += 1;
		//System.out.println(x);
		//System.out.println(y);
		//System.out.println(z);
		String souv = "";
		for(int aa = 0; aa < 3; aa++)
		{
			JFrame frame = new JFrame();
			JOptionPane optionPane = new JOptionPane();
			ImageIcon[] icon = new ImageIcon[limits[aa]];
			JButton[] jButton = new JButton[limits[aa]];
			optionPane.setLayout(new GridLayout(limits[aa] / 2, 2));
			optionPane.setOptions(new Object[] {});
			optionPane.removeAll();
			for(int bb = 0; bb < limits[aa]; bb++)
			{
				icon[bb] = new ImageIcon("img/Algebra" + "ABC".charAt(aa) + "" + bb + ".png");
				Image image = icon[bb].getImage();
				image = image.getScaledInstance((int)(icon[bb].getIconWidth() / r), (int)(icon[bb].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
				icon[bb] = new ImageIcon(image);
				jButton[bb] = getButton(optionPane, bb, icon[bb]);
				optionPane.add(jButton[bb]);
			}
			JDialog d = optionPane.createDialog(frame, "");
			d.setTitle("Select the equation:");
			d.setVisible(true);
			switch(aa)
			{
				case 0:
					JOptionPane.showMessageDialog(null, "Submit this number: " + getA(x, y, z));
					souv = "EQUATION #1: " + equations[0][index];
					break;
				case 1:
					JOptionPane.showMessageDialog(null, "Submit this number: " + getB(x, y, z));
					souv = souv + "\nEQUATION #2: " + equations[1][index];
					break;
				case 2:
					JOptionPane.showMessageDialog(null, "Submit this number: " + getC(x, y, z));
					break;
			}
		}
		return souv;
	}
	private JButton getButton(final JOptionPane optionPane, int value, ImageIcon icon ) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	    	optionPane.setValue(value);
	        index = value;
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
	private boolean isPrime(int n)
	{
		if(n < 2)
			return false;
		for(int aa = 2; aa < n; aa++)
		{
			if(n % aa == 0)
				return false;
		}
		return true;
	}
	private int getAbs(int n)
	{
		if(n < 0)
			n = n * -1;
		return n;
	}
	private String getA(int x, int y, int z)
	{
		switch(index)
		{
			case 0:
				return ((3 * z) + "");
			case 1:
				return ((5 + y) + "");
			case 2:
				return ((6 - x) + "");
			case 3:
				return ((7 * x) + "");
			case 4:
				return ((8 * y) + "");
			case 5:
				return ((9 + z) + "");
			case 6:
				return ((x + 1) + "");
			case 7:
				return fixOut(x, 2);
			case 8:
				return ((y - 2) + "");
			case 9:
				return fixOut(y, 4);
			case 10:
				return ((z - 7) + "");
			default:
				return fixOut(z, 10);
		}
	}
	private String getB(int x, int y, int z)
	{
		switch(index)
		{
			case 0:
				return fixOut((2 *x) - (10 * y), 10);
			case 1:
				return ((7 * x * y) + "");
			case 2:
				return fixOut((2 * (x + y)) - z, 2);
			case 3:
				return fixOut(y - (2 * z), 2);
			case 4:
				return fixOut(z - y, 2);
			case 5:
				return (((z * y) - (2 * x)) + "");
			case 6:
				return ((2 * (z + 7)) + "");
			case 7:
				return (((2 * z) + 7) + "");
			case 8:
				return (((x * y) - (2 + x)) + "");
			default:
				return ((x * y * z) + "");
		}
	}
	private String getC(int x, int y, int z)
	{
		switch(index)
		{
			case 0:
				return fixOut(y + 14 - (2 * z), 8);
			case 1:
				return fixOut((10 * z) - (120 * x) + (8 + (4 * y)), 40);
			case 2:
				return (((8 * x) - z + y) + "");
			case 3:
				return fixOut((18 * y) + (x * y), 4);
			case 4:
				return (((((x * y) + 22) * y) - 4) + "");
			case 5:
				return ((x - (2 * y) + z) + "");
			case 6:
				return ((((x * y) - z) * 10) + "");
			default:
				return fixOut((2 * z) - x + (4 * z), 16);
		}
	}
	private String fixOut(int r, int d)
	{
		//System.out.println(r);
		String out = getAbs(r / d) + "";
		if(r < 0)
			out = "-" + out;
		int temp = (10000 / d) * getAbs(r % d);	
		if(temp != 0)
		{
			while(temp % 10 == 0)
				temp = temp / 10;
			out = out + "." + temp;
		}
		return out;
	}
}
