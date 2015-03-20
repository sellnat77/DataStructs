import java.util.*;

public class MinSubSumCalc
{
	Scanner userIn = new Scanner(System.in);
	int a[];
	String progsToRun;
	
	MinSubSumCalc(){}
	
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
	
	public void debugArray()
	{
		this.setALength(8);
		this.a[0] = -34;
		this.a[1] = 49;
		this.a[2] = -58;
		this.a[3] = 76;
		this.a[4] = 29;
		this.a[5] = -71;
		this.a[6] = -54;
		this.a[7] = 63;
	}
	
	public void showMenu()
	{
		System.out.println("\n0. Use debug array");
		System.out.println("1. Enter comma delimited list of ints");
		System.out.println("2. Enter length of array for random numbers");
		System.out.println("3. Enter the string of programs to run\n"
				+ "\t1:Sophomore\n\t2:Junior\n");
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
			case 0:
				System.out.println("Using debug array");
				this.debugArray();
				this.runAlgorithms(this.enterMethodsToRun());
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
			//a[k] = (int)((Math.random()*10)%10+1)-5;
			a[k] = (int)((Math.random()*500)%500+1)-250;
			//System.out.println(a[k]+"\n");
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
		long timeTaken;
		for(k = 0; k < methodsToRun.length(); k++)
		{
			if(methodsToRun.charAt(k) == '1')
			{
				System.out.println("Running sophomore");
				timeTaken = System.nanoTime();
				System.out.println("\tMin Sum: "+this.Soph());
				timeTaken = System.nanoTime() - timeTaken;
				this.checkTime(timeTaken);
			}
			else if(methodsToRun.charAt(k) == '2')
			{
				System.out.println("Running junior");
				timeTaken = System.nanoTime();
				System.out.println("\tMin Sum: "+this.Junior(a, 0, (this.getALength()-1)));
				timeTaken = System.nanoTime() - timeTaken;
				this.checkTime(timeTaken);
			}	
		}
		
	}
	
	public void checkTime(long timeTaken)
	{
		if(timeTaken/10000 > 1000)
		{
			System.out.format("\tTime Taken: %3.2f seconds\n",(float)timeTaken/1000000000);
		}
		else 
		{
			System.out.format("\tTime Taken: %3.2f milliseconds\n",(float)timeTaken/1000000);
		}
	}
	
	
	public int Soph()
	{
		int min_sum = 500; 
		int this_sum;

		for(int i = 0; i < this.getALength(); i++)
		{
			this_sum = 0;
			for(int j = i; j < this.getALength(); j++)
			{
				this_sum += a[j];
				if(this_sum < min_sum && this_sum > 0)
				{
					min_sum = this_sum;
				}
				
			}

		}
		return min_sum;
	}
	
	public int Junior(int a[], int left, int right)
	{
		//System.out.println(Arrays.toString(a));
		int mssMin = 0;	
		
		//If there are 2 or less elements in current array
		if(right-left < 2 )
		{
			return jBaseCase(a,left,right);
		}

		int mid = (int) Math.ceil((right+left)/2);
		int mssLeft = Junior(a, left, mid);
		int mssRight = Junior(a,(mid+1),right);
		
		//Carries out most of the work
		int mssMiddle = jMSSMiddle(a,left,mid,right);
		//mssMin = mssLeft;
		if(mssLeft > 0 )
		{
			mssMin = mssLeft;
			
		}
		else
		{
			mssMin = 500;
		}
		
		if(mssMin > mssRight && mssRight > 0 )
		{
			mssMin = mssRight;
		}
		
		if(mssMin > mssMiddle && mssMiddle > 0 )
		{
			mssMin = mssMiddle ; 
		}
		
		return mssMin; 
	}
	
	public int jBaseCase(int a[], int left, int right)
	{
		int MSS = 500;
		
		if( a[left] > 0 && a[right] > 0 )
		{
			if(a[left] < a[right])
			{
				MSS = a[left];
			}
			else if(a[left] == a[right])
			{
				MSS = a[left];
			}
			else
			{
				MSS = a[right];
			}
		}
		else if(a[left] > 0 && a[right] < 0)
		{
			MSS = a[left];	
		}
		else if(a[right] > 0&& a[left] < 0)
		{
			MSS = a[right];
		}
		
		//if(a[left]+a[right] > 0 && a[left]+a[right] < MSS)
		//{
		//	MSS=a[left]+a[right];
		//}

		return MSS;
	}
	
	public int jMSSMiddle(int a[], int left, int mid, int right)
	{
		int k;
		int leftSums[] = new int[mid+1];
		int rightSums[] = new int[right-mid+1];
		int overallMin=500;
		int sum = 0;
		int count = 0;
		
		//Check left sum
		for(k = mid; k >= 0; k--)
		{
			sum += a[k];
			leftSums[count] = sum;
			count++;
		}
	
		//Check right sum
		sum = 0;
		count = 0;
		for(k = mid+1; k <= right; k++)
		{
			sum += a[k];
			
			rightSums[count] = sum;
			count++;

		}
		Arrays.sort(leftSums);
		Arrays.sort(rightSums);
		
		int i = left;
		int j = rightSums.length-1;
		int check = 0;
		
		while(j >= 0 && i <= leftSums.length-1)
		{
			check = leftSums[i] + rightSums[j];
			if(check < overallMin && check > 0)  
			{
				overallMin = check;
			}
			if(check <= 0 )
			{
				i++;
			}
			else
			{
				j--;
			}
		}
		return overallMin;
	}
	
	
	public void quit()
	{
		System.out.println("Good Bye!");
	}

}
