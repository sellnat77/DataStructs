import java.util.Arrays;
import java.util.Scanner;

public class HeapSortOperations 
{
	int[] tree;
	int load;
	int timesPopped = 0;
	Scanner userIn = new Scanner(System.in);
	

	
	void treeInit(int val)
	{
		int k;
		tree = new int[val];
		
		for(k = 0; k < tree.length; k++)
		{
			tree[k] = 0;
		}
	}
	
	void showMenu()
	{
		System.out.println("\n0. Initialize the tree to a particular size");
		System.out.println("1. Enter comma delimited list of ints to place in the tree");
		System.out.println("2. Insert a value into the min-heap");
		System.out.println("3. Pop a value from the min-heap");
		System.out.println("4. Display the sorted heap using heap sort");
		System.out.println("5. Enter a length to generate a random min-heap then sort using arrays.sort and heap sort");
		System.out.println("6. Exit program.");
		System.out.println("7. Show array.");
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
			//init bare
			case 0:
				System.out.println("Please enter the size of the tree: ");
				val = userIn.nextInt();
				this.treeInit(val);
				this.showTree(tree);
				break;
			//input vals
			case 1:
				System.out.println("Please enter the ints for the array: ");
				this.inputArray();
				break;
			//Insert
			case 2:
				System.out.println("Please enter a value to insert");
				val = userIn.nextInt();
				this.insert(val);
				break;
			//Pop
			case 3:
				load = this.getArLoad(tree);
				if(timesPopped > load )
				{
					this.treeInit(load+1);
					timesPopped = 0;
					System.out.println("Tree is empty now!");
				}
				else
				{
					System.out.println("The value popped is : " + this.pop() + "\n\tThe tree is ");
				}
				this.showTree(tree);
				break;
			//Show Sorted
			case 4:
				System.out.println("\t\tThe sorted tree is : ");
				this.heapSort(tree);
				this.showTree(tree);
				break;
			//Init Rand + Sort
			case 5:
				System.out.println("Please enter the length of random numbers to insert into the tree");
				val = userIn.nextInt();
				this.initRand(val);
				this.runTests(tree);
				break;
			//Exit
			case 6:
				again = false;
				this.quit();
				break;
			//Show tree
			case 7:
				this.showTree(tree);
				break;
			default:
				System.out.println("");
				break;
			}
		}
	}
	void runTests(int[] arrayToTest) 
	{
		int[] temp1 = arrayToTest.clone();
		int[] temp2 = arrayToTest.clone();
		long timeTaken;
	
		System.out.println("Running slow");
		timeTaken = System.nanoTime();
		Arrays.sort(temp1);
		this.showTree(temp1);
		timeTaken = System.nanoTime() - timeTaken;
		this.checkTime(timeTaken);
		
		System.out.println("Running fast");
		timeTaken = System.nanoTime();
		this.heapSort(temp2);
		this.showTree(temp2);
		timeTaken = System.nanoTime() - timeTaken;
		this.checkTime(timeTaken);
		tree = temp2.clone();
	}

	void checkTime(long timeTaken) 
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

	void quit() 
	{
		System.out.println("Good Bye!");
	}

	void heapSort(int[] arrayToSort) 
	{
		int k;
		
		build_heap(arrayToSort);
		
		for(k = load; k > 0; k--)
		{
			swap(arrayToSort,0,k);
			load--;
			minHeap(arrayToSort,0);
		}
	}
	void build_heap(int[] arrayToHeap)
	{
		int k;
		
		load = getArLoad(arrayToHeap);
		
		for(k = load/2; k >= 0; k--)
		{
			minHeap(arrayToHeap, k);
		}
	}

	void minHeap(int[] arrayToHeap, int k) 
	{
		int left = 2*k+1;
		int right = 2*k+2;
		int min = k;
		
		if(left <= load && arrayToHeap[left] > arrayToHeap[k])
		{
			min = left;
		}
		if(right <= load && arrayToHeap[right] > arrayToHeap[min])
		{
			min = right;
		}
		if(min != k)
		{
			swap(arrayToHeap,k,min);
			minHeap(arrayToHeap,min);
		}
	}

	void swap(int[] arrayToHeap, int k, int min) 
	{
		int temp = arrayToHeap[k];
		arrayToHeap[k] = arrayToHeap[min];
		arrayToHeap[min] = temp;
	}

	void initRand(int val) 
	{
		int k;
		int randVal = 0;
		tree = new int[val];
		
		for( k = 0; k < tree.length; k++)
		{
			//randVal = (int)((Math.random()*1)%1+1);
			randVal = (int)((Math.random()*1000)%1000+1)-500;
			this.insert(randVal);
		}
	}

	void inputArray() 
	{
		int k;
		String array;
		
		array = userIn.next();
		
		String[] temp = array.split("\\s*,\\s*");
		tree = new int[temp.length];

		for(k = 0; k < tree.length; k++)
		{
			this.insert(Integer.parseInt(temp[k]));
		}
	}

	void showTree(int [] arr)
	{
		int k;
		System.out.println("The tree is : ");
		for( k = 0; k < arr.length; k++)
		{
			if(k < arr.length-1)
			{
				System.out.print(""+arr[k]+",");
			}
			else
			{
				System.out.print(""+arr[k]+"\n\n");
			}
		}
	}
	
	void insert(int x)
	{
		int hole = getArLoad(tree); 
		for(; hole > 1 && tree[hole/2] > x; hole /= 2)
		{
			tree[hole] = tree[hole/2];
		}
		tree[hole] = x;
	}
	
	int pop()
	{
		timesPopped++;
		load = this.getArLoad(tree);
		int top = tree[0];

		tree[0] = tree[load];
		load--;
		percolateDown(0);
		this.heapSort(tree);
		return top;
	}
	
	void percolateDown(int index) 
	{
		int child;
		int temp = tree[index];
		for(; (child = 2*index) <= load; index = child)
		{
			
			if(child != load && tree[child+2] < tree[child+1])
			{
				child++;
			}
			if(tree[child] < temp)
			{
				tree[index] = tree[child];
			}
			else
			{
				break;
			}
		}
	}
	
	int getArLoad(int[] array)
	{
		int k, count;
		count = 0;
		k = 0;
		while(array[k] != 0 && k < array.length-1)
		{
			count++;
			k++;
		}
		return count;
	}
}
