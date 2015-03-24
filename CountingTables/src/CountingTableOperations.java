import java.util.Hashtable;
import java.util.Scanner;

/**
 * Hash table can insert(obj), remove(obj), find(obj)
 * Counting table can add(obj), subtract(obj), count(obj)
 * 
 *  
 *
 */

public class CountingTableOperations 
{
	private Scanner userIn = new Scanner(System.in);
	private int a[];
	private int counts[] = new int[getSize()];
	private int size;
	private int limit = (int) Math.floor(getSize()/2);
	
	private Hashtable hTable = new Hashtable(size);
	
	public int getSize()
	{
		return this.size;
	}
	public void setSize(int a)
	{
		while(!this.isPrime(a))
		{
			a++;
		}
		
		this.size = a;
		
		System.out.println(a);
	}
	
	public void showMenu()
	{
		System.out.println("\n1. Enter comma delimited list of ints");
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
			case 1:
				System.out.println("Please enter the ints for the array to find the mss separated by commas: ");
				this.inputArray();
				break;
			case 2:
				System.out.println("Please enter the length for the randomly generated array ");
				this.enterArrayLength();
				break;
			case 3:
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
	
	public void initialize()
	{
		System.out.println("Please enter the size of the hash table");
		this.setSize(userIn.nextInt());
	}
	public void inputArray()
	{
		int k;
		String array;
		
		array = userIn.next();
		
		String[] temp = array.split("\\s*,\\s*");
		
		for(k = 0; k < this.getSize(); k++)
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
		
		for(k = 0; k < size; k++)
		{
			a[k] = (int)((Math.random()*500)%500+1)-250;
			System.out.println(a[k]+"\n");
		}
	}

	public void quit()
	{
		System.out.println("Good Bye!");
	}
	//checks whether an int is prime or not.
	boolean isPrime(int n) {
	    //check if n is a multiple of 2
	    if (n%2==0) return false;
	    //if not, then just check the odds
	    for(int i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}
	
	int hash(int val)
	{
		val = val % getSize();
		
		return val;
	}

}
