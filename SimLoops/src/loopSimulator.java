import java.util.*;

public class loopSimulator
{
	Scanner userIn = new Scanner(System.in);
	int numberOfLoops;
	int iterations;
	int incrementor[];
	boolean display;

	public void showMenu()
	{
		System.out.println("\n1. Enter length of register");
		System.out.println("2. Enter capacity of register");
		System.out.println("3. Simulate filling up register");
		System.out.println("4. Simulate filling up register with display");
		System.out.println("5. Exit program.");
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
				System.out.println("Please enter the length of the register: ");
				this.setLoops(userIn.nextInt());
				incrementor = new int[this.getLoops()];
				break;
			case 2:
				System.out.println("Please enter the capacity: ");
				this.setIterations(userIn.nextInt());
				break;
			case 3:
				this.simulateLoops();
				break;
			case 4:
				this.setDisplay(true);
				this.simulateLoops();
				break;
			case 5:
				again = false;
				this.quit();
				break;
			default:
				System.out.println("");
				break;
			}
		}
	}

	public void setDisplay(boolean set)
	{
		display = set;
	}
	public void setLoops(int loops)
	{
		this.numberOfLoops = loops;
	}
	public void setIterations(int iter)
	{
		this.iterations = iter;
	}

	public boolean getDisplay()
	{
		return this.display;
	}
	public int getLoops()
	{
		return this.numberOfLoops;
	}
	public int getIterations()
	{
		return this.iterations;
	}
	public void print()
	{
		int k;
		
		for(k = 0; k < incrementor.length; k++)
		{
			System.out.printf("%4d.) %s\n",k,Arrays.toString(incrementor));
		}
	}
	public void simulateLoops()
	{		
		int loopIndex = 0;
		int numberOfLoops = this.getLoops();
		int numberOfIterations = this.getIterations();
		boolean isDisplaying = this.getDisplay();
		boolean alreadyExecuted = false;
		
		for(int i = 0; i < this.getLoops(); i++)
		{
			incrementor[i] = 0;

		}

		long time = System.nanoTime();
		System.out.printf("\t%s\n",Arrays.toString(incrementor));

		//While the index is less than the desired loops
		while(loopIndex < numberOfLoops)
		{
			//Ai < n-1
			if(incrementor[loopIndex] < numberOfIterations-1)
			{
				incrementor[loopIndex]++;	
				if(isDisplaying)
				{		
					System.out.printf("\t%s\n",Arrays.toString(incrementor));
				}
				//Stays in this statement until Ai == n-1
				loopIndex = 0;
			}
			//If Ai == n and i is less than the number of loops, move index back one
			else if(incrementor[loopIndex] == numberOfIterations && loopIndex < numberOfLoops)
			{
				loopIndex--;
			}
			//If last Ai == n-1 reset index to zero once and reset first value to 0
			else if(incrementor[numberOfLoops-1] == numberOfIterations-1 && !alreadyExecuted)
			{
				alreadyExecuted = true;
				loopIndex = 0;
				incrementor[loopIndex] = 0;
			}
			//Otherwise, reset Ai to 0 and move the index forward
			else
			{
				incrementor[loopIndex] = 0;
				loopIndex++;		
			}	
		}
		System.out.println("\nProcess took : " + (double)((System.nanoTime()-time)/1000000) + " milliseconds" );
		this.setDisplay(false);
	}
	
	public void quit()
	{
		System.out.println("Good Bye!");
	}
}
