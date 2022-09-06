package modules;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import start.BombConfig;
import start.BombEdgework;

public class DrDoctor 
{
	private final String[][] diagnoseTable = 
	    {
	        {"Alztimer’s","Fever","Chills","Dizziness","Minecraftazol"},
	        {"Braintenance","Headache","Sleepiness","Thirstiness","Gr-Theta Autazine"},
	        {"Color allergy","Bloating","Cough","Diarrhea","Tears of Tar"},
	        {"Detonession","Dizziness","Fatigue","Fever","Residentevele"},
	        {"Emojilepsy","Headache","Muscle Cramp","Nausea","Vitamin PUBG-12"},
	        {"Foot and Morse","Throat Irritation","Constipation","Foot Swelling","Fortinite"},
	        {"Gout of Life","Hallucination","Cold Hands","Excessive Crying","Scrapmechanol"},
	        {"HRV","Gas","Numbness","Loss of Smell","Freddi-5"},
	        {"Indicitis","Bloating","Fever","Hallucination","Forestamine"},
	        {"Jaundry","Disappearance of the Ears","Fever","Shortness of Breath","λ-3"},
	        {"Keypad stones","Headache","Sleepiness","Fever","Crushed Candy"},
	        {"Legomania","Cough","Excessive Crying","Muscle Cramp","Supermariobromine"},
	        {"Microcontusion","Fever","Chills","Dizziness","Q-Bertamin"},
	        {"Narcolization","Numbness","Constipation","Fatigue","Vitamin Wii"},
	        {"OCd","Sleepiness","Dizziness","Thirstiness","Astrodrosodale"},
	        {"Piekinson’s","Sleepiness","Cold Hands","Thirstiness","Adlez DNA Knil"},
	        {"Quackgrounds","Chills","Loss of Smell","Throat Irritation","Nearwhisper"},
	        {"Royal Flu","Thirstiness","Fever","Headache","Warcraftazol"},
	        {"Seizure Siphor","Constipation","Bloating","Hallucination","Leega Ledgins"},
	        {"Tetrinus","Hallucination","Cold Hands","Dizziness","No-Mercy"},
	        {"Urinary LEDs","Chills","Nausea","Numbness","Assassine Cream"},
	        {"Verticode","Loss of Smell","Cold Hands","Sleepiness","Cupcakes"},
	        {"Widgeting","Thirstiness","Cough","Fatigue","GLa-doze"},
	        {"XMAs","Diarrhea","Sleepiness","Foot Swelling","Ball of Cootie"},
	        {"Yes-no infection","Gas","Throat Irritation","Muscle Cramp","War-Med"},
	        {"Zooties","Muscle Cramp","Constipation","Sleepiness","CS-Go Lotion"},
	        {"Chronic Talk","Throat Irritation","Cough","Foot Swelling","Red Ded"},
	        {"Jukepox","Sleepiness","Headache","Dizziness","Solid Gear Metal"},
	        {"Neurolysis","Foot Swelling","Excessive Crying","Nausea","Vitamin BEAM"},
	        {"Perspective Loss","Sleepiness","Bloating","Dizziness","Waldohol"},
	        {"Orientitis","Gas","Numbness","Loss of Smell","Semtex"},
	        {"Huntington’s disease","Cold Hands","Sleepiness","Throat Irritation","Tetrisine"}
	};
	private final String[] symptomTable =
		{
				"Bloating","Chills","Cold Hands","Constipation","Cough",
				"Diarrhea","Disappearance of the Ears","Dizziness","Excessive Crying","Fatigue","Fever",
				"Foot Swelling","Gas","Hallucination","Headache","Loss of Smell",
				"Muscle Cramp","Nausea","Numbness","Shortness of Breath","Sleepiness",
				"Thirstiness","Throat Irritation"
		};
	private String sub;
	private final BombConfig con;
	private final BombEdgework ew;
	private final int timeZone;
	private final boolean isSouv;
	public DrDoctor(BombConfig c, BombEdgework e, int tz, boolean s)
	{
		con = c;
		ew = e;
		timeZone = tz;
		isSouv = s;
	}
	public String run()
	{
		//Getting Starting Table Position
		int num = 0;
		if(ew.getSNDIG(ew.numSNDIGS() - 1) % 2 == 0)
			num += 1;
		if(ew.BAT() >= 2)
			num += 2;
		if(con.getNumberModules() % 2 == 0)
			num += 4;
		if(ew.numLit() > ew.numUnlit())
			num += 8;
		String order = "ABEHFGORDLMWPQZ1CINSJTY2KVX4U356";
		//Input
		ArrayList<String> symptoms = getSymptoms();
		ArrayList<String> diseases = getDiseases();
		int[] diseaseAnswer = { getDisease(diseases, symptoms, order.charAt(num + 16)), getDisease(diseases, symptoms, order.charAt(num))};
		while(diseaseAnswer[0] == -1)
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			symptoms = getSymptoms();
			diseases = getDiseases();
			diseaseAnswer = new int[]{ getDisease(diseases, symptoms, order.charAt(num + 16)), getDisease(diseases, symptoms, order.charAt(num))};
		}
		//Calculate Treatments/Dosage
		String[] treatments = { diagnoseTable[diseaseAnswer[0]][4], diagnoseTable[diseaseAnswer[1]][4], };
		int dose;
		String metric = "mg";
		boolean solveBased = false;
		if(ew.BAT() == 3 && ew.BH() == 3 && ew.findLit("FRK") && ew.findUnlit("TRN") && JOptionPane.showConfirmDialog(null, "Is there a Forget Me Not present?") == 0)
		{
			dose = 420;
			metric = "g";
			treatments[0] = "Cynaide";
			treatments[1] = "Cynaide";
		}
		else if(ew.findLit("FRQ"))
		{
			if(isPrime(ew.getSNDIG(ew.numSNDIGS() - 1)))
			{
				dose = 2;
				metric = "g";
			}
			else
				dose = ew.numPortTypes() + con.getNumberModules();
		}
		else if(symptoms.contains("Fever"))
		{
			solveBased = true;
			dose = 0;
		}
		else if(JOptionPane.showConfirmDialog(null, "Is there an IPhone present?") == 0)
			dose = ew.getSNDIG(0) * ew.getSNDIG(ew.numSNDIGS() - 1);
		else
		{
			dose = 0;
			for(char c : ew.getIndChars().toCharArray())
				dose += ((c - 'A') + 1);
		}
		//Output Answer
		String out;
		if(diseaseAnswer[0] != diseaseAnswer[1])
		{
			long min = con.getStartingBombMinutes();
			int sec = con.getStartingBombSeconds() / 2;
			if(min % 2 == 1)
				sec += 30;
			min /= 2;
			out = (sec < 10) ? "Over " + min + ":0" + sec + "\n" : "Over " + min + ":" + sec + "\n";
			out = out + "Disease: " + diagnoseTable[diseaseAnswer[0]][0] + "\n"; 
			out = out + "Treament: " + treatments[0] + "\n"; 
			out = out + "------------------------------\nOtherwise\n";
			out = out + "Disease: " + diagnoseTable[diseaseAnswer[1]][0] + "\n"; 
			out = out + "Treament: " + treatments[1] + "\n"; 
			out = out + "------------------------------\n";
		}
		else
		{
			out = "Disease: " + diagnoseTable[diseaseAnswer[0]][0] + "\n"; 
			out = out + "Treament: " + treatments[0] + "\n"; 
		}
		if(solveBased)
		{
			String input = JOptionPane.showInputDialog("Enter the number of solves:");
			boolean v = valid(input, false, con.getNumberSolvable());
			while(!(v))
			{
				JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
				input = JOptionPane.showInputDialog("Enter the number of solves:");
				v = valid(input, false, con.getNumberSolvable());
			}
			while(true)
			{
				dose = Integer.parseInt(input) * con.getNumberUnsolved(Integer.parseInt(input));
				if(dose > 999)
				{
					dose /= 10;
					metric = "g";
				}
				else
					metric = "mg";
				if(dose == 0)
					dose = 1;
				String date = getDate();
				input = JOptionPane.showInputDialog(out + "Dose: " + dose + "" + metric + "\nDate: " + date + "\nEnter the new solve count:");
				v = valid(input, true, con.getNumberSolvable());
				while(!(v))
				{
					JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
					input = JOptionPane.showInputDialog("Enter the number of solves:");
					v = valid(input, true, con.getNumberSolvable());
				}
				if(input.length() == 0)
					break;
			}
		}
		else
		{
			if(dose > 999)
			{
				dose /= 10;
				metric = "g";
			}
			if(dose == 0)
			{
				dose = 1;
				metric = "mg";
			}
			String date = getDate();
			JOptionPane.showMessageDialog(null, out + "Dose: " + dose + "" + metric + "\nDate: " + date);
		}
		if(isSouv)
		{
			if(diseaseAnswer[0] != diseaseAnswer[1])
			{
				long min = con.getStartingBombMinutes();
				int sec = con.getStartingBombSeconds() / 2;
				if(min % 2 == 1)
					sec += 30;
				min /= 2;
				out = (sec < 10) ? "Over " + min + ":0" + sec : "Over " + min + ":" + sec;
				if(JOptionPane.showConfirmDialog(null, "Is the time over " + out + "?") == 0)
					diseases.remove(diagnoseTable[diseaseAnswer[0]][0]);
				else
					diseases.remove(diagnoseTable[diseaseAnswer[1]][0]);
			}
			else
				diseases.remove(diagnoseTable[diseaseAnswer[0]][0]);
			String souv = "";
			for(int i = 0; i < diseases.size(); i++)
				souv = souv + "DISEASE #" + (i + 1) + ": " + diseases.get(i).toUpperCase() + "\n";
			souv = souv + "\n";
			for(int i = 0; i < symptoms.size(); i++)
				souv = souv + "SYMPTOM #" + (i + 1) + ": " + symptoms.get(i).toUpperCase() + "\n"; 
			JOptionPane.showMessageDialog(null, souv);
			return souv;
		}
		return "";
	}
	private ArrayList<String> getSymptoms()
	{
		ArrayList<String> list = new ArrayList<String>();
		for(String str : symptomTable)
			list.add(str);
		Collections.sort(list);
		ArrayList<String> symptoms = new ArrayList<String>();
		for(int aa = 0; aa < 7; aa++)
		{
			JDialog dialog = getDialog(list, 2);
			dialog.setTitle("Select Symptom #" + (aa + 1) + ":");
			dialog.setVisible(true);
			symptoms.add(sub + "");
			list.remove(sub);
		}
		return symptoms;
	}
	private ArrayList<String> getDiseases()
	{
		ArrayList<String> list = new ArrayList<String>();
		for(String[] arr : diagnoseTable)
			list.add(arr[0]);
		Collections.sort(list);
		ArrayList<String> diseases = new ArrayList<String>();
		for(int aa = 0; aa < 3; aa++)
		{
			JDialog dialog = getDialog(list, 2);
			dialog.setTitle("Select Disease #" + (aa + 1) + ":");
			dialog.setVisible(true);
			diseases.add(sub + "");
			list.remove(sub);
		}
		return diseases;
	}
	private int getDisease(ArrayList<String> diseases, ArrayList<String> symptoms, char let)
	{
		int cur = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456".indexOf(let);
		int adder = 1;
		if("AEIOU".indexOf(diagnoseTable[cur][0].charAt(0)) < 0)
			adder = -1;
		for(int i = 0; i < diagnoseTable.length; i++)
		{
			if(diseases.contains(diagnoseTable[cur][0]))
			{
				boolean flag = true;
				for(int aa = 1; aa < 4; aa++)
				{
					if(!(symptoms.contains(diagnoseTable[cur][aa])))
					{
						flag = false;
						break;
					}
				}
				if(flag)
					return cur;
			}
			cur = mod(cur + adder, diagnoseTable.length);
		}
		return -1;
	}
	private String getDate()
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
        LocalDateTime now = LocalDateTime.now();  
        now.plusHours(timeZone);
        String conv = dtf.format(now);  
        String[] split = conv.split("/");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);
        boolean leapYear = ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
        int[] dates = {
        		12,29,
        		44,
        		90,93,101,119,
        		130,132,146,
        		155,175,
        		184,196,207,208,
        		213,231,
        		243,244,246,253,
        		274,288,
        		304,314,323,
        		340,351
        };
        String[] dateStr = 
        	{
        		"13 1","30 1",
        		"14 2",
        		"1 4","4 4","12 4","30 4",
        		"11 5","13 5","27 5",
        		"5 6","25 6",
        		"4 7","16 7","27 7","28 7",
        		"2 8","20 8",
        		"1 9","2 9","4 9","11 9",
        		"2 10","16 10",
        		"1 11","11 11","20 11",
        		"7 12","18 12"
        	};
        int mod = 365;
        int[] vals = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if(leapYear)
        {
        	mod = 366;
        	vals[2]++;
        	for(int i = 3; i < dates.length; i++)
        		dates[i]++;
        }
        int num = day - 1;
        for(int i = 1; i < month; i++)
        	num += vals[i];
        int[] diff = new int[2];
        for(int i = 0; i < dates.length; i++)
        {
        	if(num < dates[i])
        	{
        		diff[0] = mod(num - dates[mod(i - 1, dates.length)], mod);
        		diff[1] = -(num - dates[i]);
        		if(diff[0] < diff[1])
        			return dateStr[mod(i - 1, dates.length)];
        		else
        			return dateStr[i];
        	}
        }
        diff[0] = num - dates[dates.length - 1];
        diff[1] = -(num - (dates[0] + mod));
        if(diff[0] < diff[1])
        	return dateStr[dates.length - 1];
        return dateStr[0];
	}
	private int mod(int n, int m)
	{
		while(n < 0)
			n += m;
		return (n % m);
	}
	private boolean isPrime(int n)
	{
		if(n < 2)
			return false;
		for(int i = 2; i < n; i++)
		{
			if(n % i == 0)
				return false;
		}
		return true;
	}
	private boolean valid(String i, boolean ret, int solvable)
	{
		if(i.length() == 0)
			return ret;
		for(char c : i.toCharArray())
		{
			if("0123456789".indexOf(c) < 0)
				return false;
		}
		if(Integer.parseInt(i) >= solvable)
			return false;
		return true;
	}
	private JDialog getDialog(ArrayList<String> arr, int div)
	{
		JFrame frame = new JFrame();
		JOptionPane optionPane = new JOptionPane();
		JButton[] jButton = new JButton[arr.size()];
		if(arr.size() % div == 0)
			optionPane.setLayout(new GridLayout(arr.size() / div, div));
		else
			optionPane.setLayout(new GridLayout(arr.size() / div + 1, div));
		optionPane.setOptions(new Object[] {});
		optionPane.removeAll();
		for(int aa = 0; aa < arr.size(); aa++)
		{
			jButton[aa] = getButton(optionPane, arr.get(aa));
			optionPane.add(jButton[aa]);
		}
		return optionPane.createDialog(frame, "");
	}
	private JButton getButton(final JOptionPane optionPane, String text) {
	    final JButton button = new JButton();
	    button.setText(text);
	    ActionListener actionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        optionPane.setValue(text.toUpperCase());
	        sub = (text + "");
	      }
	    };
	    button.addActionListener(actionListener);
	    return button;
	  }
}
