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
		System.out.println("3. Exit program.");
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
		int loopIndex = 1;
		incrementor = new int[this.getLoops()];
		
		for(k = 0; k < this.getIterations(); k++)
		{
			loopIndex--;
			if(incrementor[loopIndex] == this.getIterations())
			{
				incrementor[loopIndex] = 0;
				loopIndex++;
				incrementor[loopIndex]++;
				
			}
			if(k == this.getIterations())
			{
				k = 0;
				loopIndex++;
			}
			incrementor[loopIndex] += 1;
		}
		
		
		
		
		
		
	}
	
	public void quit()
	{
		System.out.println("Good Bye!");
	}

	

}
