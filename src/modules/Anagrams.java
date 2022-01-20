package modules;

import javax.swing.JOptionPane;

public class Anagrams 
{
	private final String[][] wordList =
		{
				{"STREAM", "MASTER", "TAMERS"},
				{"LOOPED", "POODLE", "POOLED"},
				{"CELLAR", "CALLER", "RECALL"},
				{"SEATED", "SEDATE", "TEASED"},
				{"RESCUE", "SECURE", "RECUSE"},
				{"RASHES", "SHEARS", "SHARES"},
				{"BARELY", "BARLEY", "BLEARY"},
				{"DUSTER", "RUSTED", "RUDEST"}
		};
	public void run()
	{
		String word = JOptionPane.showInputDialog("Enter the displayed word:").toUpperCase();
		word = getWords(word);
		while(word.length() == 0)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			word = JOptionPane.showInputDialog("Enter the displayed word:").toUpperCase();
			word = getWords(word);
		}
		JOptionPane.showMessageDialog(null, "Submit one of these words:\n" + word);
	}
	private String getWords(String w)
	{
		for(int aa = 0; aa < wordList.length; aa++)
		{
			for(int bb = 0; bb < 3; bb++)
			{
				if(wordList[aa][bb].equals(w))
				{
					switch(bb)
					{
						case 0:
							return (wordList[aa][1] + "\n" + wordList[aa][2]);
						case 1:
							return (wordList[aa][0] + "\n" + wordList[aa][2]);
						default:
							return (wordList[aa][0] + "\n" + wordList[aa][1]);
					}
				}
			}
		}
		return "";
	}
}
