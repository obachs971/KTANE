package modules;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import start.BombEdgework;

public class ShapeShift 
{
	private final BombEdgework ew;
	private final double r;
	public ShapeShift(BombEdgework e, double resizer)
	{
		ew = e;
		r = resizer;
	}
	public String run()
	{
		//Setting up picture
		ImageIcon icon = new ImageIcon("img/ShapeShiftShapes.jpg");
		Image image = icon.getImage();
		image = image.getScaledInstance((int)(icon.getIconWidth() / r), (int)(icon.getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(image);
		JLabel label = new JLabel();
		label.setIcon(icon);
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.add(label, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		//Enter the initial shape
		String input = JOptionPane.showInputDialog("Enter the initial shape:").toUpperCase();
		frame.setVisible(false);
		boolean v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			frame.setVisible(true);
			input = JOptionPane.showInputDialog("Enter the initial shape:").toUpperCase();
			frame.setVisible(false);
			v = valid(input);
		}
		if(input.length() > 2)
		{
			String[] temp = input.split(" ");
			input = temp[0].charAt(0) + "" + temp[1].charAt(0);
		}
		String[] shapes = {"FLAT", "ROUND", "POINT", "TICKET"};
		String souv = "INITIAL SHAPE: ";
		for(int aa = 0; aa < input.length(); aa++)
			souv = souv + "" + shapes["FRPT".indexOf(input.charAt(aa))] + " ";
		input = getSolution(input.toUpperCase());
		String out = "";
		for(int aa = 0; aa < 2; aa++)
			out = out + "" + shapes["FRPT".indexOf(input.charAt(aa))] + " ";
		JOptionPane.showMessageDialog(null, "Enter this shape:\n" + out);
		return souv;
	}
	//Returns the shape that's been visited twice
	private String getSolution(String shape)
	{
		ArrayList<String> shapes = new ArrayList<String>();
		while(true)
		{
			if(shapes.contains(shape))
				return shape.toUpperCase();
			shapes.add(shape.toUpperCase());
			switch(shapes.get(shapes.size() - 1))
			{
				case "FR":
					if(ew.findPort("DVI-D") > 0)
						shape = "RP";
					else
						shape = "TP";
					break;
				case "RP":
					if(ew.findLit("SIG"))
						shape = "TT";
					else
						shape = "FF";
					break;
				case "PT":
					if(ew.findPort("RJ-45") > 0)
						shape = "RP";
					else
						shape = "RR";
					break;
				case "RR":
					if(ew.numCharsInSN("AEIOU") > 0)
						shape = "TT";
					else
						shape = "FR";
					break;
				case "PP":
					if(ew.findLit("IND"))
						shape = "PT";
					else
						shape = "FF";
					break;
				case "TP":
					if(ew.findPort("PS/2") > 0)
						shape = "PT";
					else
						shape = "PR";
					break;
				case "TF":
					if(ew.findUnlit("FRQ"))
						shape = "FR";
					else
						shape = "PP";
					break;
				case "PR":
					if(ew.findPort("PARALLEL") > 0)
						shape = "FP";
					else
						shape = "RF";
					break;
				case "TT":
					if(ew.BAT() >= 3)
						shape = "TF";
					else
						shape = "FT";
					break;
				case "RT":
					if(ew.BA() >= 2)
						shape = "TF";
					else
						shape = "FT";
					break;
				case "FF":
					if(ew.getSNDIG(ew.numSNDIGS() - 1) % 2 == 1)
						shape = "PF";
					else
						shape = "PR";
					break;
				case "FP":
					if(ew.findLit("MSA"))
						shape = "TP";
					else
						shape = "PF";
					break;
				case "FT":
					if(ew.findUnlit("BOB"))
						shape = "TR";
					else
						shape = "PP";
					break;
				case "RF":
					if(ew.findLit("SND"))
						shape = "RR";
					else
						shape = "RT";
					break;
				case "TR":
					if(ew.findPort("RCA") > 0)
						shape = "FP";
					else
						shape = "RF";
					break;
				case "PF":
					if(ew.findUnlit("CAR"))
						shape = "TR";
					else
						shape = "RT";
					break;
			}
		}
	}
	//Validation for the initial shape
	private boolean valid(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 2)
		{
			for(int aa = 0; aa < 2; aa++)
			{
				switch(conv[aa])
				{
					case "FLAT":
					case "F":
					case "ROUND":
					case "R":
					case "TICKET":
					case "T":
					case "POINT":
					case "P":
						break;
					default:
						return false;
				}
			}
			return true;
		}
		else if(i.length() == 2)
		{
			for(int aa = 0; aa < i.length(); aa++)
			{
				if("FRPT".indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
	
}
