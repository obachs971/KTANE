package modules;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Listening 
{
	private final String[][] chart =
		{
				{"Arcade", "$ # $ # *"},{"Ballpoint Pen Writing", "$ * $ * *"},{"Beach", "* & * & &"},{"Book Page Turning", "# # # & $"},
				{"Car Engine", "& # * * &"},{"Casino", "* * $ * #"},{"Censorship Bleep", "& & $ & *"},{"Chainsaw", "& # & & #"},
				{"Compressed Air", "$ $ * $ *"},{"Cow", "& $ # $ &"},{"Dial-up Internet", "* # & * &"},{"Door Closing", "# $ # & $"},
				{"Extractor Fan", "$ # $ * &"},{"Firework Exploding", "$ & $ $ *"},{"Glass Shattering", "* $ * $ *"},{"Helicopter", "# & $ & &"},
				{"Marimba", "& * $ * $"},{"Medieval Weapons", "& $ * * &"},{"Oboe", "& # $ $ #"},{"Phone Ringing", "& $ $ & *"},
				{"Police Radio Scanner", "* * # # #"},{"Rattling Iron Chain", "* # $ & &"},{"Reloading Glock 19", "$ & * * #"},{"Saxophone", "$ & & * *"},
				{"Servo Motor", "$ & # $ $"},{"Sewing Machine", "# & & * #"},{"Soccer Match", "# # * $ *"},{"Squeaky Toy", "$ * & # #"},
				{"Supermarket", "# $ $ & *"},{"Table Tennis", "* $ $ & $"},{"Tawny Owl", "$ # * $ &"},{"Taxi Dispatch", "& & & * *"},
				{"Tearing Fabric", "$ & & * &"},{"Throat Singing", "* * $ $ $"},{"Thrush Nightingale", "* * # * *"},{"Tibetan Nuns", "# & & & &"},
				{"Train Station", "# $ $ * *"},{"Tuba", "# & $ # #"},{"Vacuum Cleaner", "# & $ * &"},{"Waterfall", "& * * $ $"},{"Zipper", "& $ & # #"}
		};
	public String run()
	{
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		JButton[] jButton = new JButton[chart.length];
		optionPane.setLayout(new GridLayout(14, 3));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int aa = 0; aa < chart.length; aa++)
		{
			jButton[aa] = getButton(optionPane, chart[aa][0], chart[aa][1]);
			optionPane.add(jButton[aa]);
		}
		JDialog dialog = optionPane.createDialog(frame, "");
		dialog.setTitle("Select the sound:");
		dialog.setVisible(true);
		JOptionPane.showMessageDialog(null, "Press these buttons: " + optionPane.getValue());
		return ("CODE: " + optionPane.getValue());
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
}
