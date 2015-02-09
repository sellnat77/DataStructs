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
		System.out.println("\n1. Enter number of loops desired");
		System.out.println("2. Enter number of iterations per loop");
		System.out.println("3. Simulate nested loops");
		System.out.println("4. Simulate nested loops with display");
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
				System.out.println("Please enter the number of loops: ");
				this.setLoops(userIn.nextInt());
				break;
			case 2:
				System.out.println("Please enter the iterations: ");
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

	public void simulateLoops()
	{
		int k = 0;
		int val = 1;
		incrementor = new int[this.getLoops()];
		boolean alreadyExecuted = false;
		for(int i = 0; i < this.getLoops(); i++)
		{
			incrementor[i] = 0;

		}

		long time = System.nanoTime();
		System.out.printf("   0.) %s\n",Arrays.toString(incrementor));

		while(k < this.getLoops())
		{
			incrementor[k]++;
			if(this.getDisplay())
			{
				System.out.printf("%4d.) %s\n",val,Arrays.toString(incrementor));
			}
			val++;

			if(incrementor[k] < this.getIterations())
			{

				k = 0;
			}
			else if(incrementor[k] == this.getIterations()-1 && k < this.getLoops())
			{
				k--;
			}
			else if(incrementor[this.getLoops()-1] == this.getIterations() && !alreadyExecuted)
			{
				System.out.println("CAUGHT");

				alreadyExecuted = true;
				k = 0;
				incrementor[k] = 1;
			}
			else
			{
				incrementor[k] = 1;
				k++;
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
