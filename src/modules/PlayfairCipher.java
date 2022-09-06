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

public class PlayfairCipher 
{
	private final String[][] screenTable =
		{
				{"SAFE", "EFAS", "MESSAGE", "GROOVE"},
				{"CODE", "EDOC", "QUIET", "ETIUQ"},
				{"GROOVE", "EVOORG", "TEIUQ", "QUITE"},
				{"MESSAGE", "EGASSEM", "SAFE", "EDOC"}
		};
	private final String[][] pressTable =
		{
				{"ABCD","CDAB","BADC","DABC"},
				{"BCDA","DACB","ADCB","ABCD"},
				{"CDAB","ACBD","DCBA","BCDA"},
				{"DABC","CBDA","CBAD","CDAB"},
				{"ABDC","BDAC","BACD","DACB"},
				{"BDCA","DBCA","ACDB","ACBD"},
				{"CABD","BCAD","CDBA","CBDA"},
				{"DCAB","CADB","DBAC","BDAC"}
		};
	private final double r;
	private final BombConfig con;
	private final BombEdgework ew;
	public PlayfairCipher(double resizer, BombConfig cf, BombEdgework e)
	{
		r = resizer;
		con = cf;
		ew = e;
	}
	public void run()
	{
		//Part 1
		String k1;
		switch(con.getStartingDayName())
		{	
			case "MONDAY":
				k1 = "PLAY";
				if(!(ew.findLit("BOB")))
					break;
			case "TUESDAY":
				k1 = "HIDDEN";
				break;
			case "WEDNESDAY":
				k1 = "SECRET";
				if(!(ew.findLit("BOB")))
					break;
			case "THURSDAY":
				k1 = "CIPHER";
				break;
			case "FRIDAY":
				k1 = "FAIL";
				if(!(ew.findLit("BOB")))
					break;
			case "SATURDAY":
				k1 = "PARTYHARD";
				break;
			default:
				k1 = "BECOZY";
		}
		String press = getSolution(k1);
		while(press.length() == 0)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			press = getSolution(k1);
		}
		JOptionPane.showMessageDialog(null, "Press these buttons: " + press);
	}
	private String getSolution(String k1)
	{
		String[] keys = new String[3];
		keys[0] = k1;
		//Part 2
		String[] colors = {"Magenta", "Blue", "Orange", "Yellow"};
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		ImageIcon[] icon = new ImageIcon[colors.length];
		JButton[] jButton = new JButton[colors.length];
		optionPane.setLayout(new GridLayout(4, 1));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int aa = 0; aa < colors.length; aa++)
		{
			icon[aa] = new ImageIcon("img/PlayfairCipher" + colors[aa] + ".png");
			Image image = icon[aa].getImage();
			image = image.getScaledInstance((int)(icon[aa].getIconWidth() / r), (int)(icon[aa].getIconHeight() / r), java.awt.Image.SCALE_SMOOTH);
			icon[aa] = new ImageIcon(image);
			jButton[aa] = getButton(optionPane, colors[aa], icon[aa], (aa + ""));
			optionPane.add(jButton[aa]);
		}
		JDialog dialog = optionPane.createDialog(frame, "");
		dialog.setTitle("Select the color:");
		dialog.setVisible(true);
		int colorIndex = Integer.parseInt(optionPane.getValue().toString());
		if(ew.findPort("SERIAL") > 0 && ew.findPort("PARALLEL") > 0)
			keys[1] = screenTable[0][colorIndex];
		else if(ew.getSNSUM() > 10)
			keys[1] = screenTable[1][colorIndex];
		else if(ew.BD() > ew.BA())
			keys[1] = screenTable[2][colorIndex];
		else
			keys[1] = screenTable[3][colorIndex];
		//Modifying
		String encrypt = JOptionPane.showInputDialog("Enter the text:").toUpperCase().replace("J", "I");
		boolean v = valid(encrypt);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			encrypt = JOptionPane.showInputDialog("Enter the text:").toUpperCase().replace("J", "I");
			v = valid(encrypt);
		}
		if(ew.numCharsInSN("AEIOU") == 0)
		{
			String temp = keys[0].toUpperCase();
			keys[0] = keys[1].toUpperCase();
			keys[1] = temp.toUpperCase();
		}
		//String[] strikes = {"0", "1", "2", "3+"};
		String[] ans = {
				getAnswer(encrypt, colorIndex, keys[0], keys[1], ""), 
				getAnswer(encrypt, colorIndex, keys[0], keys[1], "ONE"), 
				getAnswer(encrypt, colorIndex, keys[0], keys[1], "TWO"), 
				getAnswer(encrypt, colorIndex, keys[0], keys[1], "MANY")};
		//System.out.println(ans[0]);
		//System.out.println(ans[1]);
		//System.out.println(ans[2]);
		//System.out.println(ans[3]);
		for(int i = 0; i < ans.length; i++)
		{
			if(ans[i].length() > 0)
				return ans[i];
		}
		return "";
	}
	private String getAnswer(String encrypt, int colorIndex, String k1, String k2, String k3)
	{
		String key = k1 + k2 + k3, alpha = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
		for(int i = 0; i < key.length(); i++)
		{
			alpha = alpha.replace(key.charAt(i) + "", "");
			for(int j = i + 1; j < key.length(); j++)
			{
				if(key.charAt(i) == key.charAt(j))
				{
					key = key.substring(0, j) + "" + key.substring(j + 1);
					j--;
				}
			}
		}
		key = key + alpha;
		String decrypt = "";
		for(int i = 0; i < encrypt.length(); i+=2)
		{
			int r1 = key.indexOf(encrypt.charAt(i)) / 5, c1 = key.indexOf(encrypt.charAt(i)) % 5;
			int r2 = key.indexOf(encrypt.charAt(i + 1)) / 5, c2 = key.indexOf(encrypt.charAt(i + 1)) % 5;
			if(r1 == r2)
			{
				c1 = mod(c1 - 1, 5);
				c2 = mod(c2 - 1, 5);
			}
			else if(c1 == c2)
			{
				r1 = mod(r1 - 1, 5);
				r2 = mod(r2 - 1, 5);
			}
			else
			{
				int temp = c1;
				c1 = c2;
				c2 = temp;
			}
			decrypt = decrypt + "" + key.charAt(r1 * 5 + c1) + "" + key.charAt(r2 * 5 + c2);
		}
		switch(decrypt.replace("X", ""))
		{
			case "STRIKE": return pressTable[0][colorIndex];
			case "STRIK": return pressTable[1][colorIndex];
			case "STRYKE": return pressTable[2][colorIndex];
			case "STRYK": return pressTable[3][colorIndex];
			case "ZTRIKE": return pressTable[4][colorIndex];
			case "ZTRIK": return pressTable[5][colorIndex];
			case "ZTRYKE": return pressTable[6][colorIndex];
			case "ZTRYK": return pressTable[7][colorIndex];
		}
		return "";
	}
	private boolean valid(String i)
	{
		if(i.length() == 6)
		{
			for(char c : i.toCharArray())
			{
				if("ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(c) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
	private int mod(int n, int m)
	{		while(n < 0)
			n += m;
		return (n % m);
	}
	private JButton getButton(final JOptionPane optionPane, String text, ImageIcon icon, String val) {
	    final JButton button = new JButton();
	    button.setIcon(icon);
	    button.setText(text);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(val);
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
}
