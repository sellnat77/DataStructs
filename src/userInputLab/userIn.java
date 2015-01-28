package userInputLab;

import java.math.BigDecimal;
import java.util.*;

public class userIn 
{

	private int precision;
	private ArrayList<Float> inNumbers = new ArrayList<Float>();
	private Scanner userIn = new Scanner(System.in);
	
	userIn(){}
	
	public void displayMenu()
	{
		System.out.println("\n1. Set number of decimal places.");
		System.out.println("2. Enter integers and get most frequent.");
		System.out.println("3. Enter real numbers and get std deviation and average.");
		System.out.println("4. Exit program.");
	}
	
	public void getInput()
	{
		int selection = 0;
		boolean again = true;
		while(again)
		{
			this.displayMenu();
			System.out.println("\nPlease choose what you would like to do: ");
			selection = Integer.parseInt(userIn.next());
			
			switch(selection)
			{
			case 1:
				this.setPrecision();
				System.out.println("");
				break;
			case 2:
				this.getNumbers();
				this.getMostFrequent();
				System.out.println("");
				System.out.println("");
				break;
			case 3:
				this.performfloatOps();
				System.out.println("");
				break;
			case 4:
				again = false;
				this.quit();
				break;
			default:
				System.out.println("");
					break;
			}
		}
	}
	
	public void getNumbers()
	{
		int k;
		System.out.println("Please enter a comma delimited list of numbers");
		
		String temp = userIn.next();
		
		ArrayList<String> tempList = new ArrayList<String>();
		
		tempList.addAll(Arrays.asList(temp.split("\\s*,\\s*")));
		
		for(k = 0; k < tempList.size(); k++)
		{
			inNumbers.add(Float.parseFloat(tempList.get(k)));
		}		
	}
	
	public void quit()
	{
		System.out.println("Goodbye!");
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
	
	public float getAverage()
	{
		int k;
		float sum = 0;
		
		for(k = 0; k < inNumbers.size(); k++)
		{
			sum += inNumbers.get(k);
		}
		
		return (sum/inNumbers.size());
	}
	
	public float getStdDeviation()
	{
		int k;
		float std =0;
		float numToSquare = 0;
		float avg = this.getAverage();
				
		for(k = 0; k < inNumbers.size(); k++)
		{
			numToSquare = inNumbers.get(k)-avg;
			
			std += (float)(Math.pow(numToSquare, 2));
		}
		
		std = std/inNumbers.size();
		
		
		return (float)Math.sqrt(std);
	}
	
	public void performfloatOps()
	{
		this.getNumbers();
		
		BigDecimal dc = new BigDecimal(this.getAverage());
		dc = dc.setScale(precision,BigDecimal.ROUND_FLOOR);
		System.out.format("\nAverage: %s", dc);
		System.out.println("");
		
		BigDecimal standrdDv = new BigDecimal(this.getStdDeviation());
		standrdDv = standrdDv.setScale(precision, BigDecimal.ROUND_FLOOR); 
		System.out.format("\nStandard Deviation: %s", standrdDv);
		System.out.println("");		
	}
}
