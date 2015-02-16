/**
 * blah
 * blah3
 */

import java.util.*;

public class MaxSubSumCalc
{
	Scanner userIn = new Scanner(System.in);
	int a[];
	String progsToRun;
	
	MaxSubSumCalc(){}
	
	public String getProgsToRun()
	{
		return progsToRun;
	}
	public int getALength()
	{
		return a.length;
	}
	
	public void setProgsToRun(String progs)
	{
		progsToRun = progs;
	}
	public void setALength(int size)
	{
		a = new int[size];
	}
	
	public void showMenu()
	{
		System.out.println("\n1. Enter comma delimited list of ints");
		System.out.println("2. Enter length of array for random numbers");
		System.out.println("3. Enter the string of programs to run\n"
				+ "\t1:Freshmen\n\t2:Sophomore\n\t3:Junior\n\t4:Senior");
		System.out.println("4. Exit program.");
	}
	
	public void getInput()
	{
		int selection = 0;
		boolean again = true;

		while(again)
		{
			this.showMenu();
			System.out.println("\nPlease choose what you would like to do: ");
			selection = Integer.parseInt(userIn.next());

			switch(selection)
			{
			case 1:
				System.out.println("Please enter the ints for the array to find the mss separated by commas: ");
				this.inputArray();
				this.runAlgorithms(this.enterMethodsToRun());
				break;
			case 2:
				System.out.println("Please enter the length for the randomly generated array ");
				this.enterArrayLength();
				this.runAlgorithms(this.enterMethodsToRun());
				break;
			case 3:
				this.runAlgorithms(this.enterMethodsToRun());
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
	
	public void inputArray()
	{
		int k;
		String array;
		
		array = userIn.next();
		
		String[] temp = array.split("\\s*,\\s*");
		this.setALength(temp.length);
		
		for(k = 0; k < this.getALength(); k++)
		{
			a[k] = Integer.parseInt(temp[k]);
			
			System.out.println((k+1)+".) " + a[k]);
		}
	}
	
	public void enterArrayLength()
	{
		int size;
		int k;
		size = userIn.nextInt();
		this.setALength(size);
		
		for(k = 0; k < size; k++)
		{
			a[k] = (int)((Math.random()*20)%20+1)-10;
			System.out.printf("%3d.) %3d\n",k,a[k]);
		}
	}
	
	public String enterMethodsToRun()
	{
		String methodsToRun;
		System.out.println("Please enter the programs you wish to run:");
		methodsToRun = userIn.next();
		return methodsToRun;
	}
	
	public void runAlgorithms(String methodsToRun)
	{
		int k;
		for(k = 0; k < methodsToRun.length(); k++)
		{
		
			if(methodsToRun.charAt(k) == '1')
			{
				System.out.println("Running freshman");
				this.Freshman();
			}
			if(methodsToRun.charAt(k) == '2')
			{
				System.out.println("Running sophomore");
				this.Soph();
			}
			if(methodsToRun.charAt(k) == '3')
			{
				System.out.println("Running junior");
				long timeTaken = System.nanoTime();
				System.out.println("\tMax Sum: "+this.Junior(a, 0, (this.getALength()-1)));
				timeTaken = System.nanoTime() - timeTaken;
				System.out.println("\tTime Taken: " + timeTaken/100 + " milliseconds");
			}
			if(methodsToRun.charAt(k) == '4')
			{
				System.out.println("Running senior");
				this.Senior();
			}
	
		}
		
	}
	
	public void Freshman()
	{
		int max_sum = 0;
		int this_sum = 0;
		long start;
		
		start = System.nanoTime();
		for(int i = 0; i < this.getALength(); i++)
		{
			for(int j = i; j < this.getALength(); j++)
			{
				this_sum = 0;
				
				for(int k = i; k <= j; k++)
				{
					this_sum += a[k];
				}
				
				if(this_sum > max_sum)
				{
					max_sum = this_sum;
				}
			}
		}
		System.out.println("\tMax sum = " + max_sum);
		System.out.println("\tTime Taken: " + (System.nanoTime() - start)/100 + " milliseconds\n");
	}
	
	public void Soph()
	{
		int max_sum = 0; 
		int this_sum;
		long start;
		
		start = System.nanoTime();
		for(int i = 0; i < this.getALength(); i++)
		{
			this_sum = 0;
			for(int j = i; j < this.getALength(); j++)
			{
				this_sum += a[j];
				if(this_sum > max_sum)
				{
					max_sum = this_sum;
				}
			}
		}
		System.out.println("\tMax sum = " + max_sum);
		System.out.println("\tTime Taken: " + (System.nanoTime() - start)/100 + " milliseconds\n");
		
	}
	
	public int Junior(int a[], int left, int right)
	{
		int k,m;
		int mssMax = 0;
		int mssLeft = 0;
		int leftValue = 0;
		int mssRight = 0;
		int rightValue= 0;
		int mid = 0;
		int mssMiddle = 0;
		
		
		if(left == right)
		{
			return jBaseCase(a,left,right);
			
		}
		
		mid = (int)((1.0*(Math.abs(right+left)))/2);
		mssLeft = Junior(a, left, mid);
		mssRight = Junior(a,(mid+1),right);
		mssMiddle = jMSSMiddle(a,left,mid,right);
		
		//Check left mss
		for(k = 0; k < left; k++)
		{
			leftValue += a[k];
			if(leftValue > mssLeft)
			{
				mssLeft = leftValue;
			}
		}
		
		//Check right mss
		for(m = 0; m < right; m++)
		{
			rightValue += a[m];
			if(rightValue > mssRight)
			{
				mssRight = rightValue;
			}
		}
		
		mssMax = mssLeft;
		if(mssMax < mssRight)
		{
			mssMax = mssRight;
			
		}
		
		if(mssMax < mssMiddle)
		{
			mssMax = mssMiddle ; 
		}
		
		return mssMax; 
	}
	
	public int jBaseCase(int a[], int left, int right)
	{
		int MSS;
		if(a[left] > a[right])
		{
			MSS = a[left];
		}
		else if(a[right] > a[left])
		{
			MSS = a[right];
		}
		else if(left == right)
		{
			MSS = a[left];
		}
		else if(a[left] == a[right])
		{
			MSS = a[left];
		}
		else 
		{
			MSS = 0;
		}
		return MSS;
	}
	
	public int jMSSMiddle(int a[], int left, int mid, int right)
	{
		int k;
		int sum = 0;
		
		int sumLeft = 0;
		for(k = mid; k >= left; k--)
		{
			sum += a[k];
			if(sum > sumLeft)
			{
				sumLeft = sum;
			}
		}
		sum = 0;
		int sumRight = 0;
		for(k = mid+1; k <= right; k++)
		{
			sum += a[k];
			if(sum > sumRight)
			{
				sumRight = sum;
			}
		}
		
		return (sumLeft+sumRight);
	}
	
	public void Senior()
	{
		int max_sum = 0;
		int this_sum = 0;
		long start;
		
		start = System.nanoTime();
		for(int i = 0;i < this.getALength(); i++)
		{
			this_sum += a[i];
			
			if(this_sum > max_sum)
			{
				max_sum = this_sum;
			}
			else if(this_sum < 0)
			{
				this_sum = 0;
			}
		}
		System.out.println("\tMax sum = " + max_sum);
		System.out.println("\tTime Taken: " + (System.nanoTime() - start)/100 + " milliseconds\n");
		
	}
	
	
	public void quit()
	{
		System.out.println("Good Bye!");
	}

}
