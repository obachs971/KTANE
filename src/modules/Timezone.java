package modules;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Timezone 
{
	private final int[] table =
		{
				13, 7, 8, 1, 10, 21,
				2, 17, 0, 5, 18, 20,
				3, 6, 14, 23, 19, 22,
				11, 12, 4, 9, 15, 16
		};
	public String run()
	{
		String[] list =
			{
				"Alofi", "Bangkok", "Beijing", "Berlin", "Brisbane", "Buenos Aires", 
				"Bujumbura", "Denver", "Edinburgh", "Lahore", "Managua", "Manaus", 
				"Moscow", "Omsk", "Papeete", "Praia", "Quito", "Sao Paulo", 
				"Sydney", "Tarawa", "Tbilisi", "Tokyo", "Unalaska", "Whitehorse"
			};
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		JButton[] jButton = new JButton[list.length];
		optionPane.setLayout(new GridLayout(8, 3));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int aa = 0; aa < list.length; aa++)
		{
			jButton[aa] = getButton(optionPane, list[aa], aa + "");
			optionPane.add(jButton[aa]);
		}
		JDialog dialog = optionPane.createDialog(frame, "");
		dialog.setTitle("Select the top city:");
		dialog.setVisible(true);
		int val1 = table[Integer.parseInt((String)optionPane.getValue())];
		String souv = "DEPARTURE: " + list[Integer.parseInt((String)optionPane.getValue())].toUpperCase();
		dialog = optionPane.createDialog(frame, "");
		dialog.setTitle("Select the bottom city:");
		dialog.setVisible(true);
		int val2 = table[Integer.parseInt((String)optionPane.getValue())];
		souv = souv + "\nDESTINATION: " + list[Integer.parseInt((String)optionPane.getValue())].toUpperCase();
		int result = val2 - val1;
		while(result < 0)
			result += 24;
		//System.out.println(result);
		String input = JOptionPane.showInputDialog("Enter the time:").replace(" ", "").toUpperCase();
		boolean v = valid(input);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the time:").replace(" ", "").toUpperCase();
			v = valid(input);
		}
		int val = Integer.parseInt(input.split(":")[0]) * 100;
		if(val == 1200 && input.contains("AM"))
			val = 0;
		val += Integer.parseInt(input.split(":")[1].substring(0, 2));
		if(input.contains("PM") && val < 1200)
			val += 1200;
		val =  (val + (result * 100)) % 2400;
		frame = new JFrame();
		optionPane = new JOptionPane();
		jButton = new JButton[2];
		optionPane.setLayout(new GridLayout(2, 1));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		jButton[0] = getButton(optionPane, "12 Hour", "12");
		optionPane.add(jButton[0]);
		jButton[1] = getButton(optionPane, "24 Hour", "24");
		optionPane.add(jButton[1]);
		dialog = optionPane.createDialog(frame, "");
		dialog.setTitle("Enter the mode:");
		dialog.setVisible(true);
		input = (String)(optionPane.getValue());
		if(input.equals("12"))
		{
			if(val < 100)
				val = val + 1200;
			else if(val >= 1300)
				val = val - 1200;
		}
		String out = val + "";
		while(out.length() < 4)
			out = "0" + out;
		JOptionPane.showMessageDialog(null, "Enter this number: " + out);
		return souv;
	}
	private JButton getButton(final JOptionPane optionPane, String text, String response) {
	    final JButton button = new JButton();
	    button.setText(text);
	    button.setSize(50, 50);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(response);
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
	private boolean valid(String i)
	{
		String[] conv = i.split(":");
		if(conv.length == 2)
		{
			switch(conv[0])
			{
				case "1":case "2":case "3":case "4":case "5":case "6":
				case "7":case "8":case "9":case "10":case "11":case "12":
					break;
				default:
					return false;
			}
			if(conv[1].length() == 4)
			{
				String[] spl = new String[2];
				spl[0] = conv[1].substring(0, 2);
				spl[1] = conv[1].substring(2);
				switch(spl[0])
				{
					case "00":case "01":case "02":case "03":case "04":case "05":case "06":case "07":case "08":case "09":
					case "10":case "11":case "12":case "13":case "14":case "15":case "16":case "17":case "18":case "19":
					case "20":case "21":case "22":case "23":case "24":case "25":case "26":case "27":case "28":case "29":
					case "30":case "31":case "32":case "33":case "34":case "35":case "36":case "37":case "38":case "39":
					case "40":case "41":case "42":case "43":case "44":case "45":case "46":case "47":case "48":case "49":
					case "50":case "51":case "52":case "53":case "54":case "55":case "56":case "57":case "58":case "59":
						break;
					default:
						return false;
				}
				switch(spl[1])
				{
					case "AM":case "PM":
						break;
					default:
						return false;
				}
				return true;
			}
		}
		return false;
	}
}
