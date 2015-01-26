package userInputLab;

import java.math.BigDecimal;
import java.util.*;

public class userIn 
{
	private int intInput;
	private float doubInput;
	private int precision;
	private String precisionString = "%.";
	private ArrayList<Float> inNumbers = new ArrayList<Float>();
	private Scanner userIn = new Scanner(System.in);
	userIn(){}
	
	public void displayMenu()
	{
		System.out.println("\n1. Set number of decimal places.");
		System.out.println("2. Enter integers and get mnost frequent.");
		System.out.println("3. Enter real numbers andget std deviation and average.");
		System.out.println("4. Exit program.");
	}
	
	public void getInput()
	{
		int selection = 0;
		boolean again = true;
		this.displayMenu();
		System.out.println("Please choose what you would like to do: ");
		selection =userIn.nextInt();
		
		switch(selection)
		{
		case 1:
			this.setPrecision();
			System.out.println("");
			break;
		case 2:
			this.getNumbers();
			this.getMostFrequent();
			this.getAverage();
			System.out.println("");
			break;
		case 3:
			this.performfloatOps();
			System.out.println("");
			break;
		case 4:
			again = false;
			System.out.println("");
			break;
		default:
			System.out.println("");
				break;
		}
		if(again)
		{
			this.getInput();
		}
		else
		{
			return;
		}
	}
	
	public void getNumbers()
	{
		int k;
		System.out.println("Please enter a comma delimited list of integers");
		
		String temp = userIn.next();
		
		ArrayList<String> tempList = new ArrayList<String>();
		
		tempList.addAll(Arrays.asList(temp.split("\\s*,\\s*")));
		
		userIn.useDelimiter(",");
		
		for(k = 0; k < tempList.size(); k++)
		{
			inNumbers.add(Float.parseFloat(tempList.get(k)));
		}		
	}
	
	public void getfloats()
	{
		
	}
	
	public void quit()
	{
		return;
	}
	
	public void setPrecision()
	{
		System.out.println("Please enter the number of deciaml places you would like to display");
		precision = userIn.nextInt();
		System.out.println("");
	}
	
	public void getMostFrequent()
	{
		int k,m;
		int tempFreq= 0;
		int maxFreq = 0;
		int tempLoc = 0;
		Collections.sort(inNumbers);
		for(k = 0; k < inNumbers.size(); k++)
		{
			System.out.println(""+inNumbers.get(k));
					
			for(m = 0; m < inNumbers.size(); m++)
			{
				tempFreq = Collections.frequency(inNumbers, inNumbers.get(k));
				
			}	
			if(tempFreq > maxFreq)
			{
				maxFreq = tempFreq;
				tempLoc = k;
			}
			tempFreq = 0;
		}
		System.out.println("Max occurances: "+maxFreq);
		System.out.println("At location: " + tempLoc);
		System.out.println("Most frequent: " + (int)(Math.round(inNumbers.get(tempLoc))));
		System.out.println("");
	}
	
	public void getAverage()
	{
		int k;
		float sum = 0;
		
		for(k = 0; k < inNumbers.size(); k++)
		{
			sum += inNumbers.get(k);
		}
		
		BigDecimal dc = new BigDecimal(sum/inNumbers.size());
		dc = dc.setScale(precision,BigDecimal.ROUND_CEILING);
		System.out.format("\nAverage: %s", dc);
		System.out.println("");
	}
	
	public void getStdDeviation()
	{
		
		
		System.out.println("\nStandard Deviation: ");
		System.out.println("");
	}
	
	public void performfloatOps()
	{
		this.getNumbers();
		this.getAverage();
		this.getStdDeviation();		
	}
	

}
