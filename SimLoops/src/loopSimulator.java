import java.util.*;


public class loopSimulator
{
	Scanner userIn = new Scanner(System.in);
	int numberOfLoops;
	int iterations;
	int incrementor[];
	
	public void showMenu()
	{
		System.out.println("\n1. Enter number of loops desired");
		System.out.println("2. Enter number of iterations per loop");
		System.out.println("3. Simulate nested loops");
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
				again = false;
				this.quit();
				break;	
			default:
				System.out.println("");
				break;
			}
		}
	}
	
	public void setLoops(int loops)
	{
		this.numberOfLoops = loops;
	}
	public void setIterations(int iter)
	{
		this.iterations = iter;
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
		int k;
		int loopIndex = 0;
		incrementor = new int[this.getLoops()];
		
		long time = System.nanoTime();
		
		for(k = 0; k < this.getIterations(); k++)
		{
			
			if(incrementor[loopIndex] == this.getIterations())
			{
				incrementor[loopIndex] = 0;
				loopIndex++;
				incrementor[loopIndex]++;
				loopIndex--;
				k = 0;
				
			}
			if(loopIndex == this.getLoops())
			{
				break;
			}
			incrementor[loopIndex] += 1;
		}
		System.out.println("Process took :" + (int)(System.nanoTime()-time) );
		
		
		
		
		
	}
	
	public void quit()
	{
		System.out.println("Good Bye!");
	}

	

}
