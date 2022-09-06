package modules;

import javax.swing.JOptionPane;

import start.BombEdgework;

public class TaxReturns 
{
	private final int[] investments =
		{
			599, 1241, 478, 932, 81, 736, 1647, 0	
		};
	private final BombEdgework ew;
	public TaxReturns(BombEdgework e)
	{
		ew = e;
	}
	public void run()
	{
		//Turnovers
		String input = JOptionPane.showInputDialog("Enter all the Turnovers:");
		while(input.contains("  "))
			input = input.replace("  ", " ");
		boolean v = v1(input, 12);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter all the Turnovers:");
			while(input.contains("  "))
				input = input.replace("  ", " ");
			v = v1(input, 12);
		}
		String[] turnovers = input.split(" ");
		//Expenses
		input = JOptionPane.showInputDialog("Enter all the Expenses:");
		while(input.contains("  "))
			input = input.replace("  ", " ");
		v = v1(input, 36);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter all the Expenses:");
			while(input.contains("  "))
				input = input.replace("  ", " ");
			v = v1(input, 36);
		}
		String[] expenses = input.split(" ");
		//Other Info
		String info = JOptionPane.showInputDialog("Enter the 1st Letter of Surname,\nLast Letter of NI Number\nAnd Last digit of Payroll Number:").toUpperCase().replace(" ", "");
		v = v2(info);
		while(!(v))
		{
			JOptionPane.showMessageDialog(null, "ERROR", "", JOptionPane.ERROR_MESSAGE);
			input = JOptionPane.showInputDialog("Enter the 1st Letter of Surname,\nLast Letter of NI Number\nAnd Last digit of Payroll Number:").toUpperCase().replace(" ", "");
			v = v2(info);
		}
		//Gross Turnover
		int grossTurnover = 0;
		for(String turnover : turnovers)
			grossTurnover += Integer.parseInt(turnover);
		//Gross Expenses
		int grossExpenses = 0;
		for(String expense : expenses)
			grossExpenses += Integer.parseInt(expense);
		//Pension Contributions
		int pension;
		if(ew.numInd() == 0)
			pension = 0;
		else if(ew.numLit() > ew.numUnlit())
			pension = (grossTurnover * 5) / 100;
		else if(ew.numUnlit() > ew.numLit())
			pension = (grossTurnover * 10) / 100;
		else
			pension = (grossTurnover * 15) / 100;
		//Tax-Free Investment
		int investment = 0;
		if("ABCDEFGHIJKLM".indexOf(info.charAt(0)) >= 0)
			investment += 1;
		if("AC".indexOf(info.charAt(1)) >= 0)
			investment += 2;
		if("13579O".indexOf(info.charAt(2)) >= 0)
			investment += 4;
		investment = investments["HABCGEFD".charAt(investment) - 'A'] * ew.numPortTypes();
		//Gross Profit
		int grossProfit = grossTurnover - grossExpenses - pension - investment;
		//Tax-Free Allowance
		int allowance = 11850;
		if(grossProfit > 100000)
			allowance -= ((grossProfit - 100000) / 2);
		if(allowance < 0)
			allowance = 0;
		
		//Income Tax
		int taxableIncome = grossProfit - allowance;
		int[] rates = {0, 0, 0};
		 if (taxableIncome < 34501)
	     {
	         rates[0] = (taxableIncome * 20) / 100;
	         taxableIncome = 0;
	     }
	     else
	     {
	    	 rates[0] = 6900;
	    	 taxableIncome -= 34500;
	     }
	     if (taxableIncome < 103651)
	     {
	         rates[1] = (taxableIncome * 40) /100;
	         taxableIncome = 0;
	     }
	     else
	     {
	         rates[1] = 41460;
	         taxableIncome -= 103650;
	     }
	     rates[2] = (taxableIncome * 45) / 100;
	     int incomeTax = rates[0] + rates[1] + rates[2];
	     //National Insurance
	     int taxableNI = grossTurnover - grossExpenses - 8423;
	     int[] NIrates = {0, 0};
	     if (taxableNI < 37927)
	     {
	         NIrates[0] = (taxableNI * 9) / 100;
	         taxableNI = 0;
	     }
	     else
	     {
	         NIrates[0] = 3413;
	         NIrates[1] = ((taxableNI - 37926) * 2) / 100;
	     }
	     int nationalInsurance = NIrates[0] + NIrates[1];
	     int totalTax = incomeTax + nationalInsurance;
	     /*System.out.println("Gross Turnover: " + grossTurnover);
		 System.out.println("Gross Expenses: " + grossExpenses);
		 System.out.println("Pension: " + pension);
		 System.out.println("Investment: " + investment);
		 System.out.println("Gross Profit: " + grossProfit);
		 System.out.println("Allowance: " + allowance);
		 System.out.println("Taxable Income: " + taxableIncome);
		 System.out.println("Rate #1: " + rates[0]);
	     System.out.println("Rate #2: " + rates[1]);
	     System.out.println("Rate #3: " + rates[2]);
	     System.out.println("Income Tax: " + incomeTax);
	     System.out.println("Taxable NI: " + taxableNI);
	     System.out.println("Rate #1: " + NIrates[0]);
	     System.out.println("Rate #2: " + NIrates[1]);
	     System.out.println("National Insurance: " + nationalInsurance);*/
	     JOptionPane.showMessageDialog(null, "Submit this number: " + totalTax);
	     
	}
	private boolean v1(String i, int len)
	{
		String[] conv = i.split(" ");
		if(conv.length == len)
		{
			for(String str : conv)
			{
				if(str.length() == 0)
					return false;
				for(char c : str.toCharArray())
				{
					if("0123456789".indexOf(c) < 0)
						return false;
				}
			}
			return true;
		}
		return false;
	}
	private boolean v2(String i)
	{
		if(i.length() == 3)
		{
			String[] checks = {"ABCDEFGHIJKLMNOPQRSTUVWXYZ", "ABCD", "0123456789OE"};
			for(int aa = 0; aa < checks.length; aa++)
			{
				if(checks[aa].indexOf(i.charAt(aa)) < 0)
					return false;
			}
			return true;
		}
		return false;
	}
}
