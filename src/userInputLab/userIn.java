package userInputLab;

import java.util.*;

public class userIn 
{
	private int intInput;
	private float doubInput;
	private int precision;
	private ArrayList<Float> inNumbers = new ArrayList<Float>();
	private Scanner userIn = new Scanner(System.in);
	userIn(){}
	
	public void displayMenu()
	{
		System.out.println("1. Set number of decimal places.");
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
			break;
		case 2:
			this.getInts();
			this.getMostFrequent();
			break;
		case 3:
			this.performfloatOps();
			break;
		case 4:
			again = false;
			break;
		default:
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
	
	public void getInts()
	{
		System.out.println("Please enter a comma delimited list of integers");
		userIn.useDelimiter(",");
		
		while(userIn.hasNext())
		{
			System.out.println("" + Float.parseFloat(userIn.next().trim()));
			inNumbers.add(Float.parseFloat(userIn.next().trim()));
			System.out.println("DONE");
			
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
	}
	
	public void getMostFrequent()
	{
		int k,m;
		int count = 0;
		Collections.sort(inNumbers);
		for(k = 0; k < inNumbers.size(); k++)
		{
			System.out.println(""+inNumbers.get(k));
		}
		
		
		
		
	}
	
	public void commaDelimited()
	{
		
	}
	
	public void getAverage()
	{
		int k;
		float sum = 0;
		
		for(k = 0; k < inNumbers.size(); k++)
		{
			sum += inNumbers.get(k);
		}
		System.out.println("\nAverage: "+ sum/inNumbers.size());
	}
	
	public void getStdDeviation()
	{
		
		
		System.out.println("\nStandard Deviation: ");
	}
	
	public void performfloatOps()
	{
		//this.getNumbers();
		this.getAverage();
		this.getStdDeviation();		
	}
	

}
