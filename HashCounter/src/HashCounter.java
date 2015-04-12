import java.util.ArrayList;
import java.util.Scanner;

public class HashCounter
{
	Scanner userIn = new Scanner(System.in);
	ArrayList<Integer> ar;
	int hashSize;
	int capacity;
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
		return ar.size();
	}
	
	public void setProgsToRun(String progs)
	{
		progsToRun = progs;
	}
	
	public void showMenu()
	{
		System.out.println("\n0. Initialize the tables of a particular size");
		System.out.println("1. Enter comma delimited list of ints");
		System.out.println("2. Enter length of array for random numbers");
		System.out.println("3. Enter the string of programs to run\n"
				+ "\t1:Sophomore\n\t2:Junior\n");
		System.out.println("4. Insert a value into the hash table");
		System.out.println("5. Subtract an element from the hash table");
		System.out.println("6. Retrieve the count of an item in the table");
		System.out.println("7. Exit program.");
		System.out.println("8. Show array.");
	}
	
	public void getInput()
	{
		int val;
		int selection = 0;
		boolean again = true;

		while(again)
		{
			
			this.showMenu();
			System.out.println("\nPlease choose what you would like to do: ");
			selection = Integer.parseInt(userIn.next());

			switch(selection)
			{
			//init
			case 0:
				System.out.println("Please enter the size of the tables: ");
				this.tableInit();
				this.showTables();
				break;
			//input vals
			case 1:
				System.out.println("Please enter the ints for the array to find the mode separated by commas: ");
				this.inputArray();
				this.runAlgorithms(this.enterMethodsToRun());
				break;
			//Random array
			case 2:
				System.out.println("Please enter the length for the randomly generated array ");
				this.enterArrayLength();
				this.runAlgorithms(this.enterMethodsToRun());
				break;
			//Run algos
			case 3:
				this.runAlgorithms(this.enterMethodsToRun());
				break;
			//Add val
			case 4:
				System.out.println("Please enter the number you want to add: ");
				val = userIn.nextInt();
				ar.add(val);
				this.add(val);
				break;
			//Sub val
			case 5:
				System.out.println("Please enter the number you want to take out: ");
				val = userIn.nextInt();
				ar.remove(val);
				this.sub(val);
				break;
			//get count
			case 6:
				System.out.println("Please enter the number you want to find the count for: ");
				this.count(userIn.nextInt());
				break;
			//Exit
			case 7:
				again = false;
				this.quit();
				break;
			//Show
			case 8:
				showTables();
				break;
			default:
				System.out.println("");
				break;
			}
		}
	}
	
	private void showTables() 
	{
		int k;
		
		System.out.println("\n\n\tBase array\t" + ar.toString());
		
		System.out.print("\n\n\tHash Table\t");
		for(k = 0; k < capacity; k++)
		{
			System.out.format(" |%4d| ",hashedPotatoes[k]);
		}
		System.out.print("\n\n\tCounting Table\t");
		for(k = 0; k < capacity; k++)
		{
			System.out.format(" |%4d| ",potatoCounter[k]);
		}
		System.out.print("\n\n\tIndex\t\t");
		for(k = 0; k < capacity; k++)
		{
			System.out.format(" |%4d| ",k);
		}
		
	}

	public void inputArray()
	{
		int k;
		String array;
		
		array = userIn.next();
		
		String[] temp = array.split("\\s*,\\s*");
		hashSize = temp.length;
		capacity = (int)(Math.floor(hashSize/2));
		
		ar = new ArrayList<Integer>();
		
		hashedPotatoes = new int[capacity];
		potatoCounter = new int[capacity];
		
		for(k = 0; k < hashSize; k++)
		{
			ar.add(Integer.parseInt(temp[k]));
			this.add(Integer.parseInt(temp[k]));
		}
	}
	
	public void tableInit()
	{
		int k;
		hashSize = userIn.nextInt();
		capacity = (int)(Math.floor(hashSize/2));
		
		ar = new ArrayList<Integer>();
		hashedPotatoes = new int[capacity];
		potatoCounter = new int[capacity];
		
		for(k = 0; k < capacity; k++)
		{
			//ar.add(2147483647);
			hashedPotatoes[k] = 2147483647;
			potatoCounter[k] = 2147483647;
		}
	}
	
	public void enterArrayLength()
	{
		int value;
		int k;
		hashSize = userIn.nextInt();
		capacity = (int)(Math.floor(hashSize/2));
		
		ar = new ArrayList<Integer>();
		hashedPotatoes = new int[capacity];
		potatoCounter = new int[capacity];
		
		for(k = 0; k < capacity; k++)
		{
			//value = (int)((Math.random()*500)%500+1)-250;
			value = (int)((Math.random()*5)%5+1)-2;
			ar.add(value);
			this.add(value);
			
			//System.out.println(a[k]+"\n");
		}
		this.showTables();
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
				System.out.println("Running slow");
				timeTaken = System.nanoTime();
				System.out.println("\tMode = " + this.getModeSlow(ar, ar.size()));
				timeTaken = System.nanoTime() - timeTaken;
				this.checkTime(timeTaken);
			}
			else if(methodsToRun.charAt(k) == '2')
			{
				System.out.println("Running fast");
				timeTaken = System.nanoTime();
				System.out.println("\tMode = " + this.getModeHash(potatoCounter,capacity));
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
		//System.out.println("Original value = " + val);
		probeIndex = 0;
		val = Math.abs((int)(val*37));
		power++;
		//System.out.println("Hashed value = " + val);
		
		return val;
	}
	
	public int add(int val)
	{
		int index = hash(val)%capacity;
		probeIndex = 0;
		
		int nextIndex,newCount;
		
		while(true)
		{
			nextIndex = ((int)(probeIndex+(index*index)))%capacity;
			
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
		
		int nextIndex,newCount;
		
		while(true)
		{
			nextIndex = ((int)(index+Math.pow(probeIndex, 2)))%capacity;
			
			if (val == hashedPotatoes[nextIndex])
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
		
		int nextIndex;
		
		while(true)
		{
			nextIndex = ((int)(index+Math.pow(probeIndex, 2)))%capacity;
			if (val == hashedPotatoes[nextIndex])
			{
				System.out.println("Found " + potatoCounter[nextIndex] + " instances of " + hashedPotatoes[nextIndex]);
				return potatoCounter[nextIndex];
			}
			probeIndex++;
		}
		//System.out.println("Could not find the value you're looking for.");
	}
	
	public int getModeSlow(ArrayList<Integer> arrayToMode,int n)
	{
		int mode = 0;
		int max_count = 0;
		int i,candidate,count,j;
		
		for(i = 0; i < n; i++)
		{
			candidate = arrayToMode.get(i);
			count = 1; 
			
			for(j = 0; j < n; j++)
			{
				if(arrayToMode.get(j) == candidate)
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
	
	private int getModeHash(int[] a, int capacity)
	{
		int k;
		int mode = 0;
		int modeIndex = 0;
		for(k = 0; k < capacity; k++)
		{
			if(potatoCounter[k] > mode )
			{
				modeIndex = k;
				mode = potatoCounter[k];
			}
		}
	
		return hashedPotatoes[modeIndex];
	}
	
	
	
	public void quit()
	{
		System.out.println("Good Bye!");
	}
}
