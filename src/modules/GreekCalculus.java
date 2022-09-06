package modules;

import java.awt.BorderLayout;
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

public class GreekCalculus 
{
	private String led;
	private ArrayList<String> letters = new ArrayList<String>();
	private ArrayList<Integer> numbers = new ArrayList<Integer>();
	private final BombEdgework ew;
	private final double r;
	public GreekCalculus(BombEdgework e, double resizer)
	{
		ew = e;
		r = resizer;
	}
	public void run()
	{
		//Get LED Color
		JDialog dialog = getDialog(new String[] {"Red", "Orange", "Yellow", "Green", "Blue", "Purple", "Magenta", "White", "Brown"}, 3);
		dialog.setTitle("Select the LED Color:");
		dialog.setVisible(true);
		//Get the 2 Parameters
		String[] parameters = {"Blue", "Yellow"};
		int[] para = new int[2];
		ImageIcon icon = new ImageIcon("img/GreekCalculusList.png");
		Image image = icon.getImage();
		image = image.getScaledInstance((int)(icon.getIconWidth() / r), (int)(icon.getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(image);
		JLabel l = new JLabel();
		l.setIcon(icon);
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.add(l, BorderLayout.CENTER);
		frame.pack();
		for(int i = 0; i < 2; i++)
		{
			frame.setVisible(true);
			String input = JOptionPane.showInputDialog("Enter the " + parameters[i] + " Parameter:").toUpperCase();
			frame.setVisible(false);
			boolean v = v1(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				frame.setVisible(true);
				input = JOptionPane.showInputDialog("Enter the " + parameters[i] + " Parameter:").toUpperCase();
				frame.setVisible(false);
				v = v1(input);
			}
			para[i] = evalExpression(input, 0, 0);
		}
		int result;
		switch(led)
		{
			case "GREEN":
				result = green(para[0], para[1], frame);
				break;
			case "RED":
				result = red(para[0], para[1], frame);
				break;
			case "BLUE":
				result = blue(para[0], para[1], frame);
				break;
			case "YELLOW":
				result = yellow(para[0], para[1], frame);
				break;
			default:
				result = other(para[0], para[1], frame);
				break;
		}
		JOptionPane.showMessageDialog(null, "Submit this number: " + result);
	}
	private int green(int blue, int yellow, JFrame frame)
	{
		int val =  (((blue + yellow) * 10) / 2);
		String out = val + "";
		out = out.substring(0, out.length() - 1) + "." + out.substring(out.length() - 1);
		String input = JOptionPane.showInputDialog("Enter the Least X value\nthat is greater than " + out);
		boolean v = isNum(input) && ((Integer.parseInt(input) * 10) > val);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the Least X value\nthat is greater than " + out);
			v = isNum(input) && ((Integer.parseInt(input) * 10) > val);
		}
		int x1 = Integer.parseInt(input);
		frame.setVisible(true);
		input = JOptionPane.showInputDialog("Enter the Y value at X: " + x1 + ":").toUpperCase();
		frame.setVisible(false);
		v = v1(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			frame.setVisible(true);
			input = JOptionPane.showInputDialog("Enter the Y value at X: " + x1 + ":").toUpperCase();
			frame.setVisible(false);
			v = v1(input);
		}
		int y1 = evalExpression(input, blue, yellow);
		input = JOptionPane.showInputDialog("Enter the Greatest X value\nthat is less than " + out);
		v = isNum(input) && ((Integer.parseInt(input) * 10) < val);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the Greatest X value\nthat is less than " + out);
			v = isNum(input) && ((Integer.parseInt(input) * 10) < val);
		}
		int x2 = Integer.parseInt(input);
		frame.setVisible(true);
		input = JOptionPane.showInputDialog("Enter the Y value at X: " + x2 + ":").toUpperCase();
		frame.setVisible(false);
		v = v1(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			frame.setVisible(true);
			input = JOptionPane.showInputDialog("Enter the Y value at X: " + x2 + ":").toUpperCase();
			frame.setVisible(false);
			v = v1(input);
		}
		int y2 = evalExpression(input, blue, yellow);
		return divide(y1 - y2, x1 - x2);
	}
	private int red(int blue, int yellow, JFrame frame)
	{
		if(blue == yellow)
			return 0;
		ArrayList<Integer> x = getXs(blue, yellow);
		int sum = 0;
		for(int i = 0; i < x.size() - 1; i++)
		{
			frame.setVisible(true);
			String input = JOptionPane.showInputDialog("Enter the Y value at X: " + x.get(i) + ":").toUpperCase();
			frame.setVisible(false);
			boolean v = v1(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				frame.setVisible(true);
				input = JOptionPane.showInputDialog("Enter the Y value at X: " + x.get(i) + ":").toUpperCase();
				frame.setVisible(false);
				v = v1(input);
			}
			sum += (difference(x.get(i), x.get(i + 1)) * evalExpression(input, blue, yellow));
		}
		if(blue >= yellow)
			sum = -sum;
		return sum;
	}
	private int blue(int blue, int yellow, JFrame frame)
	{
		if(blue == yellow)
			return 0;
		ArrayList<Integer> x = getXs(blue, yellow);
		int sum = 0;
		for(int i = 1; i < x.size(); i++)
		{
			frame.setVisible(true);
			String input = JOptionPane.showInputDialog("Enter the Y value at X: " + x.get(i) + ":").toUpperCase();
			frame.setVisible(false);
			boolean v = v1(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				frame.setVisible(true);
				input = JOptionPane.showInputDialog("Enter the Y value at X: " + x.get(i) + ":").toUpperCase();
				frame.setVisible(false);
				v = v1(input);
			}
			sum += (difference(x.get(i - 1), x.get(i)) * evalExpression(input, blue, yellow));
		}
		if(blue >= yellow)
			sum = -sum;
		return sum;
	}
	private int yellow(int blue, int yellow, JFrame frame)
	{
		if(blue == yellow)
			return 0;
		ArrayList<Integer> x = getXs(blue, yellow);
		ArrayList<Integer> y = new ArrayList<Integer>();
		for(int i = 0; i < x.size(); i++)
		{
			frame.setVisible(true);	
			String input = JOptionPane.showInputDialog("Enter the Y value at X: " + x.get(i) + ":").toUpperCase();
			frame.setVisible(false);
			boolean v = v1(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				frame.setVisible(true);
				input = JOptionPane.showInputDialog("Enter the Y value at X: " + x.get(i) + ":").toUpperCase();
				frame.setVisible(false);
				v = v1(input);
			}
			y.add(evalExpression(input, blue, yellow));
		}
		int sum = 0;
		for(int i = 0; i < x.size() - 1; i++)
			sum += (difference(x.get(i), x.get(i + 1)) * (((y.get(i) + y.get(i + 1)) * 10) / 2));
		if(sum % 10 != 0)
			sum += 5;
		sum = sum / 10;
		if(blue >= yellow)
			sum = -sum;
		return sum;
	}
	private int other(int blue, int yellow, JFrame frame)
	{
		ArrayList<Integer> x = getXs(blue, yellow);
		int sum = 0;
		for(int i = 0; i < x.size(); i++)
		{
			frame.setVisible(true);
			String input = JOptionPane.showInputDialog("Enter the Y value at X: " + x.get(i) + ":").toUpperCase();
			frame.setVisible(false);
			boolean v = v1(input);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				frame.setVisible(true);
				input = JOptionPane.showInputDialog("Enter the Y value at X: " + x.get(i) + ":").toUpperCase();
				frame.setVisible(false);
				v = v1(input);
			}
			sum += evalExpression(input, blue, yellow);
		}
		return sum;
	}
	private ArrayList<Integer> getXs(int blue, int yellow)
	{
		ArrayList<Integer> x = new ArrayList<Integer>();
		if(blue == yellow)
			x.add(blue);
		else if(blue == (yellow - 1))
		{
			x.add(blue);
			x.add(yellow);
		}
		else if(blue == (yellow + 1))
		{
			x.add(yellow);
			x.add(blue);
		}
		else
		{
			int low, high;
			if(blue < yellow)
			{
				low = blue;
				high = yellow;
			}
			else
			{
				low = yellow;
				high = blue;
			}
			String input = JOptionPane.showInputDialog("Enter the X values between " + low + " & " + high).toUpperCase();
			boolean v = v2(input, low, high);
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the X values between " + low + " & " + high).toUpperCase();
				v = v2(input, low, high);
			}
			if(input.length() > 0)
			{
				String[] temp = input.split(" ");
				for(String str : temp)
					x.add(Integer.parseInt(str));
			}
			if(!(x.contains(low)))
				x.add(low);
			if(!(x.contains(high)))
				x.add(high);
			Collections.sort(x);
		}
		return x;
	}
	private int evalExpression(String exp, int blue, int yellow)
	{
		String[] conv = exp.split(" ");
		if(conv.length == 1)
			return stringToNumber(exp, blue, yellow);
		else
		{
			if(conv[1].equals("-"))
				return (stringToNumber(conv[0], blue, yellow) - stringToNumber(conv[2], blue, yellow));
			else
				return (stringToNumber(conv[0], blue, yellow) + stringToNumber(conv[2], blue, yellow));
		}
	}
	private int stringToNumber(String i, int blue, int yellow)
	{
		switch(i)
		{
			case "ALPHA":
				return ew.numLit();
			case "BETA":
				return ew.BA();
			case "GAMMA":
				return ew.numPorts();
			case "DELTA":
				return ew.getSNDIG(ew.numSNDIGS() - 1);
			case "EPSILON":
				if(letters.contains(i))
					return numbers.get(letters.indexOf(i));
				else
				{
					String input = JOptionPane.showInputDialog("Enter the MAX Not Encoded Y value:");
					boolean v = isNum(input);
					while(!(v))
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						input = JOptionPane.showInputDialog("Enter the MAX Not Encoded Y value:");
						v = isNum(input);
					}
					letters.add(i.toUpperCase());
					numbers.add(Integer.parseInt(input));
					return (Integer.parseInt(input));
				}
			case "ZETA":
				if(letters.contains(i))
					return numbers.get(letters.indexOf(i));
				else
				{
					String input = JOptionPane.showInputDialog("Enter the Number of Data Points:");
					boolean v = isNum(input);
					while(!(v))
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						input = JOptionPane.showInputDialog("Enter the Number of Data Points:");
						v = isNum(input);
					}
					letters.add(i.toUpperCase());
					numbers.add(Integer.parseInt(input));
					return (Integer.parseInt(input));
				}
			case "ETA":
				if(letters.contains(i))
					return numbers.get(letters.indexOf(i));
				else
				{
					String input = JOptionPane.showInputDialog("Enter the MIN X value:");
					boolean v = isNum(input);
					while(!(v))
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						input = JOptionPane.showInputDialog("Enter the MIN X value:");
						v = isNum(input);
					}
					letters.add(i.toUpperCase());
					numbers.add(Integer.parseInt(input));
					return (Integer.parseInt(input));
				}
			case "THETA":
				if(letters.contains(i))
					return numbers.get(letters.indexOf(i));
				else
				{
					String input = JOptionPane.showInputDialog("Enter the MIN Not Encoded Y value:");
					boolean v = isNum(input);
					while(!(v))
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						input = JOptionPane.showInputDialog("Enter the MIN Not Encoded Y value:");
						v = isNum(input);
					}
					letters.add(i.toUpperCase());
					numbers.add(Integer.parseInt(input));
					return (Integer.parseInt(input));
				}
			case "IOTA":
				return ew.numUnlit();
			case "KAPPA":
				return (ew.numSNDIGS() * ew.numSNLETS());
			case "LAMBDA":
				return difference(blue, yellow);
			case "MU":
				return ew.BH();
			case "NU":
				return ew.BD();
			case "XI":
				if(letters.contains(i))
					return numbers.get(letters.indexOf(i));
				else
				{
					String input = JOptionPane.showInputDialog("Enter the MAX X value:");
					boolean v = isNum(input);
					while(!(v))
					{
						JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
						input = JOptionPane.showInputDialog("Enter the MAX X value:");
						v = isNum(input);
					}
					letters.add(i.toUpperCase());
					numbers.add(Integer.parseInt(input));
					return (Integer.parseInt(input));
				}
			case "OMICRON":
				return ew.numPlates();
			case "PI":
				return 3;
			case "RHO":
				return ew.numPortTypes();
			case "SIGMA":
				return ew.getSNSUM();
			case "TAU":
				return 6;
			case "UPSILON":
				return ew.BAT();
			case "PHI":
				return 2;
			case "CHI":
				return (blue + yellow);
			case "PSI":
				return ew.numInd();
			case "OMEGA":
				return ew.getSNDIG(0);
			default:
				return Integer.parseInt(i);
		}
		
	}
	private int divide(int top, int bottom)
	{
		int result = top / bottom;
		int mod = top % bottom;
		if(mod == 0)
			return result;
		boolean negative = false;
		if((bottom < 0) != (top < 0))
			negative = true;
		if(mod < 0)
			mod = -mod;
		if(bottom < 0)
			bottom = -bottom;
		if(negative)
		{
			int check = bottom / 2;
			if(mod > check)
				result--;
		}
		else
		{
			int check = (bottom / 2) + (bottom % 2);
			if(mod >= check)
				result++;
		}
		return result;
	}
	private int difference(int n1, int n2)
	{
		if(n1 > n2)
			return (n1 - n2);
		else
			return (n2 - n1);
	}
	private boolean v1(String i)
	{
		String[] conv = i.split(" ");
		if(conv.length == 1)
			return isValue(i);
		else if(conv.length == 3)
		{
			switch(conv[1])
			{
				case "+": case "-":
					break;
				default:
					return false;
			}
			return (isValue(conv[0]) && isValue(conv[2]));
		}
		return false;
	}
	private boolean v2(String i, int low, int high)
	{
		if(i.length() == 0)
			return true;
		String[] conv = i.split(" ");
		if(conv.length > 0)
		{
			for(String str : conv)
			{
				if(!(isNum(str)))
					return false;
				int n = Integer.parseInt(str);
				if(n < low || n > high)
					return false;
			}
			System.out.println("Here");
			for(int aa = 0; aa < conv.length; aa++)
			{
				for(int bb = aa + 1; bb < conv.length; bb++)
				{
					if(conv[aa].equals(conv[bb]))
						return false;
				}
			}
			return true;
		}
		return false;
	}
	private boolean isValue(String i)
	{
		switch(i)
		{
			case "ALPHA":case "BETA":case "GAMMA":case "DELTA":
			case "EPSILON":case "ZETA":case "ETA":case "THETA":
			case "IOTA":case "KAPPA":case "LAMBDA":case "MU":
			case "NU":case "XI":case "OMICRON":case "PI":
			case "RHO":case "SIGMA":case "TAU":case "UPSILON":
			case "PHI":case "CHI":case "PSI":case "OMEGA":
				return true;
		}
		return isNum(i);
	}
	private boolean isNum(String i) {
		
	    if(i.isEmpty()) return false;
	    for(int aa = 0; aa < i.length(); aa++) {
	        if(aa == 0 && i.charAt(aa) == '-') {
	            if(i.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(i.charAt(aa),10) < 0) return false;
	    }
	    return true;
	}
	private JDialog getDialog(String[] arr, int div)
	{
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		JButton[] jButton = new JButton[arr.length];
		ImageIcon[] icon = new ImageIcon[arr.length];
		optionPane.setLayout(new GridLayout(arr.length / div, div));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		
		for(int aa = 0; aa < arr.length; aa++)
		{
			icon[aa] = new ImageIcon("img/GreekCalculus" + arr[aa] + ".png");
			Image image = icon[aa].getImage();
			image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			icon[aa] = new ImageIcon(image);
			jButton[aa] = getButton(optionPane, icon[aa], arr[aa]);
			optionPane.add(jButton[aa]);
		}
		return optionPane.createDialog(frame, "");
	}
	private JButton getButton(final JOptionPane optionPane, ImageIcon icon, String text) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    button.setText(text);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(text);
	        led = text.toUpperCase();
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
}
