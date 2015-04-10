import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

public class HashCounter
{
	Scanner userIn = new Scanner(System.in);
	int ar[];
	int hashSize;
	int capacity = (int)(Math.floor(hashSize/2));
	String progsToRun;
	int probeIndex = 0;
	int power = 1;
	
	int[] hashedPotatoes = new int[capacity];
	int[] potatoCounter = new int[capacity];
	
	HashCounter(){}
	
	public String getProgsToRun()
	{
		return progsToRun;
	}
	public int getALength()
	{
		return ar.length;
	}
	
	public void setProgsToRun(String progs)
	{
		progsToRun = progs;
	}
	public void setALength(int size)
	{
		ar = new int[size];
	}
	
	public void showMenu()
	{
		System.out.println("\n0. Use debug array");
		System.out.println("1. Enter comma delimited list of ints");
		System.out.println("2. Enter length of array for random numbers");
		System.out.println("3. Enter the string of programs to run\n"
				+ "\t1:Sophomore\n\t2:Junior\n");
		System.out.println("4. Exit program.");
		System.out.println("5. Show array.");
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
				this.runAlgorithms(this.enterMethodsToRun());
				break;
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
			case 5:
				System.out.println(Arrays.toString(ar));
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
			ar[k] = Integer.parseInt(temp[k]);
			
			System.out.println((k+1)+".) " + ar[k]);
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
			ar[k] = (int)((Math.random()*500)%500+1)-250;
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
				timeTaken = System.nanoTime() - timeTaken;
				this.checkTime(timeTaken);
			}
			else if(methodsToRun.charAt(k) == '2')
			{
				System.out.println("Running junior");
				timeTaken = System.nanoTime();
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
	
	public int hash(int val)
	{
		System.out.println("Original value = " + val);
		probeIndex = 0;
		val = (int)(((char)(val))*Math.pow(37,power ));
		power++;
		System.out.println("Hashed value = " + val);
		
		return val;
	}
	
	public int add(int val)
	{
		int index = hash(val)%capacity;
		probeIndex = 0;
		System.out.println("Index in hash table = " + index);
		System.out.println("Index in counting array = " + index);
		
		int nextIndex,newCount;
		
		while(true)
		{
			nextIndex = ((int)(index+Math.pow(probeIndex, 2)))%capacity;
			
			if(potatoCounter[nextIndex] == 0)
			{
				hashedPotatoes[nextIndex] = val;
				potatoCounter[nextIndex] = 1;
				return 1;
			}
			else if (val == hashedPotatoes[nextIndex])
			{
				newCount = potatoCounter[nextIndex]+1;
				potatoCounter[nextIndex] = newCount;
				return newCount;
			}
			probeIndex++;
		}
	}
	
	public void sub(int val)
	{
		int index = hash(val)%capacity;
		probeIndex = 0;
		System.out.println("Index in hash table = " + index);
		System.out.println("Index in counting array = " + index);
		
		int nextIndex,newCount;
		
		while(true)
		{
			nextIndex = ((int)(index+Math.pow(probeIndex, 2)))%capacity;
			
			if(potatoCounter[nextIndex] == 0)
			{
				hashedPotatoes[nextIndex] = val;
				potatoCounter[nextIndex] = 1;
				break;
			}
			else if (val == hashedPotatoes[nextIndex])
			{
				newCount = potatoCounter[nextIndex]-1;
				potatoCounter[nextIndex] = newCount;
				break;
			}
			probeIndex++;
		}
	}
	
	public int count(int val)
	{
		int index = hash(val)%capacity;
		probeIndex = 0;
		System.out.println("Index in hash table = " + index);
		System.out.println("Index in counting array = " + index);
		
		int nextIndex;
		
		while(true)
		{
			nextIndex = ((int)(index+Math.pow(probeIndex, 2)))%capacity;
			if (val == hashedPotatoes[nextIndex])
			{
				return potatoCounter[nextIndex];
			}
			probeIndex++;
		}
	}
	
	public int getModeSlow(int a[],int n)
	{
		int mode = 0;
		int max_count = 0;
		int i,candidate,count,j;
		
		for(i = 0; i < n; i++)
		{
			candidate = a[i];
			count = 1; 
			
			for(j = 0; j < n; j++)
			{
				if(a[j] == candidate)
				{
					count++;
				}
			}
			if(count > max_count)
			{
				max_count = count;
				mode = candidate;
			}
		}
		return mode;
	}
	
	
	
	
	public void quit()
	{
		System.out.println("Good Bye!");
	}
}
