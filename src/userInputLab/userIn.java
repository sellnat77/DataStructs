/* Class: Data structures and algorithms
 * Name: Russell Tan
 * Date: 1/28/2015
 * Description: Takes a list of ints or reals numbers
 * 				Displays the most frequent in the int list
 * 				Displays avg and std deviation of real number list
 */

package userInputLab;

import java.math.BigDecimal;
import java.util.*;

public class userIn 
{
	//Private members
	private int precision;
	private ArrayList<Float> inNumbers = new ArrayList<Float>();
	private Scanner userIn = new Scanner(System.in);
	
	//Default constructor
	userIn(){}
	
	//Static menu
	public void displayMenu()
	{
		System.out.println("\n1. Set number of decimal places.");
		System.out.println("2. Enter integers and get most frequent.");
		System.out.println("3. Enter real numbers and get std deviation and average.");
		System.out.println("4. Exit program.");
	}
	
	//Repeats the static menu and calls necessary functions
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
				inNumbers.clear();
				this.getNumbers();
				this.getMostFrequent();
				System.out.println("");
				System.out.println("");
				break;
			case 3:
				inNumbers.clear();
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
	
	//Prompts user for comma delimited list of numbers
	public void getNumbers()
	{
		//Using a temporary arrList to take input as a string
		//Split with regex to account for whitespace and 
		//add all values(Strings) to tempList
		int k;
		System.out.println("Please enter a comma delimited list of numbers");
		
		String temp = userIn.next();
		
		ArrayList<String> tempList = new ArrayList<String>();
		
		tempList.addAll(Arrays.asList(temp.split("\\s*,\\s*")));
		
		//Parse values(Strings) as floats to store in private arrList for processing
		for(k = 0; k < tempList.size(); k++)
		{
			inNumbers.add(Float.parseFloat(tempList.get(k)));
		}		
	}
	
	//Display exit message
	public void quit()
	{
		System.out.println("Goodbye!");
		return;
	}
	
	//Set number of dec. places
	public void setPrecision()
	{
		System.out.println("Please enter the number of deciaml places you would like to display");
		precision = userIn.nextInt();
		System.out.println("");
	}
	
	//Displays most frequently occuring value in list
	//As well as how many times it occurs and what location
	//It first appears
	public void getMostFrequent()
	{
		int k;
		int tempFreq= 0;
		int maxFreq = 0;
		int tempLoc = 0;
		float numbersInArray[] = new float[inNumbers.size()];
		
		
		//Gets frequency by taking first value as most frequent, swaps if another value is more frequent
		//Location is found by setting the index the first time there is a swap
		for(k = 0; k < inNumbers.size(); k++)
		{	
			numbersInArray[k] = inNumbers.get(k);
			tempFreq = Collections.frequency(inNumbers, inNumbers.get(k));	
			
			if(tempFreq > maxFreq)
			{
				maxFreq = tempFreq;
				tempLoc = k;
			}
			tempFreq = 0;
		}
		
		System.out.println("\nMax occurances  : " + maxFreq);
		System.out.println("First spotted at: " + tempLoc);
		System.out.println("Most frequent   : " + (int)(Math.round(inNumbers.get(tempLoc))));
		System.out.println("");
	}
	
	//Returns average of the private arrList
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
	
	//Returns the std deviation of arrList
	public float getStdDeviation()
	{
		int k;
		float std =0;
		float avg = this.getAverage();
				
		//Summation of difference of each value squared
		for(k = 0; k < inNumbers.size(); k++)
		{			
			std += (float)(Math.pow((inNumbers.get(k)-avg), 2));
		}
		
		//Divided by size
		std = std/inNumbers.size();
		
		//Square root of result
		return (float)Math.sqrt(std);
	}
	
	//Retrieves numbers from user, displays average given the precision
	//Displays the std deviation given the precision
	public void performfloatOps()
	{
		this.getNumbers();
		
		//Using Big Decimal to establish a global precision from the private member
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
