package modules;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import start.BombEdgework;

public class Cooking 
{
	private final int[] mealTable =
		{
			250, 160, 200, 180, 180	
		};
	private final String[] ovenTable =
		{
				"Bottom Element Heat",
				"Bottom Element Heat With Grill",
				"Conventional Heating",
				"Fan Oven",
				"Grill",
				"Fan With Grill"
		};
	private final int[][] minTable =
		{
				{10, 15, 20, 5, 30, 50},
				{75, 70, 80, 75, 65, 10},
				{55, 70, 65, 50, 45, 60},
				{95, 90, 75, 85, 70, 35},
				{25, 30, 35, 20, 40, 10}
		};
	private final BombEdgework ew;
	private final double resizer;
	public Cooking(BombEdgework e, double r)
	{
		ew = e;
		resizer = r;
	}
	public void run()
	{
		int meal = mod(ew.BH() - ew.numInd() + (ew.BAT() * ew.numPorts()) - ew.numPlates(), 5) - 1;
		int oven = mod(ew.numLit() - ew.numUnlit() + ew.numSNLETS(), 6) - 1;
		String light = (ew.numCharsInSN("AEIOUY") > 0 || ew.findPort("PS/2") > 0) ? "ON" : "OFF";
		int min;
		if(ew.findLit("FRK") || ew.findPort("SERIAL") > 0)
			min = minTable[meal][4];
		else if(ew.numEmpty() > 0 || ew.findLit("FRQ"))
			min = minTable[meal][0];
		else if(ew.numSNDIGS() > ew.numSNLETS() || ew.findUnlit("SND"))
			min = minTable[meal][5];
		else if(ew.findPort("HDMI") > 0 || ew.findPort("COMPOSITE") > 0 || ew.findPort("USB") > 0)
			min = minTable[meal][3];
		else if(ew.findInd("BOB"))
			min = minTable[meal][1];
		else
			min = minTable[meal][2];
		ImageIcon i = new ImageIcon("img/Cooking" + ovenTable[oven].replace(" ", "") + ".png");
		Image image = i.getImage();
		image = image.getScaledInstance((int)(i.getIconWidth() / resizer), (int)(i.getIconHeight() / resizer), java.awt.Image.SCALE_SMOOTH);
		JLabel l = new JLabel();
		l.setIcon(i);
		JFrame f = new JFrame();
		f.setLayout(new BorderLayout());
		f.add(l, BorderLayout.CENTER);
		f.pack();
		f.setVisible(true);
		JOptionPane.showMessageDialog(null, "Temperature: " + mealTable[meal] + "°C\nTime: " + min + " min\nOven: " + ovenTable[oven] + "\nLight: " + light);
		f.setVisible(false);
	}
	private int mod(int n, int m)
	{
		while(n < 1)
			n += m;
		while(n > m)
			n -= m;
		return n;
	}
}
