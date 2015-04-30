import java.util.Arrays;
import java.util.Scanner;


public class BreadthFirstTraveller 
{
	private int adjacencyMatrix[][];
	private Scanner userIn = new Scanner(System.in);
	
	void askForMatrix()
	{
		boolean second = false;
		int counter1 = 0;
		int counter2 = 0;
		System.out.println("Please input the adjacency matrix");
		String tempMatrix = userIn.nextLine();
		
		String tempDoub[] = tempMatrix.split(" ");
		System.out.println(Arrays.toString(tempDoub));
		for(int k = 0; k < tempDoub.length; k++)
		{
			if(second)
			{
				System.out.println("Assigning second");
				adjacencyMatrix[counter1][counter2] = Integer.parseInt(tempDoub[k]);
				second = false;
				counter2++;
			}
			else
			{
				System.out.println("Assigning first");
				adjacencyMatrix[counter1][counter2] = Integer.parseInt(tempDoub[k]);
				second = true;
				counter1++;
			}
		}
		System.out.println(adjacencyMatrix.toString());
		
	}

}
