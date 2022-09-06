package modules;

import javax.swing.JOptionPane;

public class Swan 
{
	private final int resets;
	private final String label;
	public Swan(String label, int resets)
	{
		this.label = label;
		this.resets = resets;
	}
	public void run()
	{
		String[] outputs = {
			"SWAN", "HATCH", "HATCH", "SWN", "SWAN",
			"HATCH", "SWAN", "DARMA", "DHARMA", "SWN",
			"DHARMA", "HATCH", "SWAN", "DARMA", "DHARMA",
			"HTCH", "DHARMA", "SWAN", "HATCH", "HTCH",
			"HATCH", "HTCH", "HTCH", "SWAN", "DHARMA"
		};
		if(resets >= outputs.length)
			JOptionPane.showMessageDialog(null, "Submit this for Swan " + label + ":\n77");
		else
			JOptionPane.showMessageDialog(null, "Submit this for Swan " + label + ":\n" + outputs[resets]);
	}
}
