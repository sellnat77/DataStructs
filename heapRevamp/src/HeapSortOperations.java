import java.util.Arrays;
import java.util.Scanner;

public class HeapSortOperations 
{
	int[] tree;
	int[] buildArray;
	int load;
	Scanner userIn = new Scanner(System.in);
	
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
		System.out.println("8. Sort the heap using heap sort and bubble sort.");
	}

	void getInput()
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
				//this.build_heap(tree);
				break;
			//Pop
			case 3:
				load = this.getArLoad(tree);
				/*
				 * if(tree.length == 1 )
				{
					System.out.println("The value popped is : " + tree[0] + "\n\tThe tree is ");
					System.out.println("Tree is empty now!");
				}
				else
				{
				*/
					System.out.println("The value popped is : " + this.pop() + "\n\tThe tree is ");
				/*}
				this.build_heap(tree);
				*/
				this.showTree(tree);
				break;
			//Show Sorted
			case 4:
				System.out.println("\t\tThe sorted tree is : ");
				this.heapSortShow(tree);
				break;
			//Init Rand + Sort
			case 5:
				System.out.println("Please enter the length of random numbers to insert into the tree");
				val = userIn.nextInt();
				this.initRand(val);
				//this.runTests(tree);
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
			case 8:
				this.runTests(tree);
				break;
			default:
				System.out.println("");
				break;
			}
		}
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

	void treeInit(int val)
	{
		int k;
		tree = new int[val+1];
		
		for(k = 1; k < tree.length; k++)
		{
			tree[k] = 9999;
		}
	}
	
	void initRand(int val) 
	{
		int k;
		int randVal = 0;
		tree = new int[val+1];
		//buildArray = new int[val];
		
		for( k = 1; k < tree.length; k++)
		{
			//randVal = (int)((Math.random()*1)%1+1);
			randVal = (int)((Math.random()*1000)%1000+1)-500;
			tree[k] = randVal;
			//this.insert(randVal);
		}
		//tree = buildArray.clone();
		this.build_heap(tree);
	}

	void inputArray() 
	{
		int k;
		String array;
		
		array = userIn.next();
		
		String[] temp = array.split("\\s*,\\s*");
		tree = new int[temp.length+1];
	
		for(k = 0; k < temp.length; k++)
		{
			tree[k+1] = Integer.parseInt(temp[k]);
		}
		this.build_heap(tree);
	}

	void runTests(int[] arrayToTest) 
	{
		int[] temp1 = arrayToTest.clone();
		int[] temp2 = arrayToTest.clone();
		long timeTaken;
	
		System.out.println("Running slow");
		timeTaken = System.nanoTime();
		//bSort(temp1);
		Arrays.sort(temp1);
		//this.showTree(temp1);
		timeTaken = System.nanoTime() - timeTaken;
		this.checkTime(timeTaken);
		
		System.out.println("Running fast");
		timeTaken = System.nanoTime();
		this.heapSort(temp2);
		//this.popHeap();
		//this.showTree(temp2);
		timeTaken = System.nanoTime() - timeTaken;
		this.checkTime(timeTaken);
		tree = temp2.clone();
	}

	void heapSort(int[] arrayToSort) 
	{
		int k;
		
		//builds the heap
		build_heap(arrayToSort);
		load = getArLoad(arrayToSort);
		for(k = 1; k <= load; k++)
		{
			pop();
			//System.out.print(" " + pop());
		}
	}
	
	void heapSortShow(int[] arrayToSort) 
	{
		int k;
		
		//builds the heap
		build_heap(arrayToSort);
		load = getArLoad(arrayToSort);
		for(k = 1; k <= load; k++)
		{
			System.out.print(" " + pop());
		}
		
	}

	void build_heap(int[] arrayToHeap)
	{
		int k;
		
		load = getArLoad(arrayToHeap);
		
		//Starts at last internal node and swaps the parent with the children if the children are smaller than
		//the parent, maintains a min heap for every iteration
		for(k = load/2; k >= 0; k--)
		{
			minHeap(arrayToHeap, k);
		}
	}
	
	void minHeap(int[] arrayToHeap, int k) 
	{
		int left = 2*k;
		int right = 2*k+1;
		int min = k;
		
		//System.out.println("Left: "+left+ " Parent: " + k + " Right: " + right);
		
		if(left <= load && arrayToHeap[left] < arrayToHeap[k])
		{
			min = left;
		}
		else
		{
			min = k;
		}
		if(right <= load && arrayToHeap[right] < arrayToHeap[min])
		{
			min = right;
		}
		//Continuously swaps the current min to the parent position
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

	void bSort(int[] arToSort)
	{
		int temp;
		boolean swap = true;
		while(swap)
		{	swap = false;
			for(int k = arToSort.length-1; k > 1; k--)
			{
				if(arToSort[k] < arToSort[k-1])
				{
					temp          = arToSort[k];
					arToSort[k]   = arToSort[k-1];
					arToSort[k-1] = temp;

					swap          = true;
				}
			}
		}
	}
	void insert(int x)
	{ 
		int hole = getArLoad(tree)+1;
		System.out.println("Hole is " + hole);
		for(; hole > 1 && tree[hole/2] > x; hole /= 2)
		{
			tree[hole] = tree[hole/2];
		}
		tree[hole] = x;
	}
	

	int pop()
	{
		load = this.getArLoad(tree);
		//Assign top to element @ root
		int top = tree[1];
		
		//Reassign the root to the last element
		tree[1] = tree[load];
		//load--;
		//Percolate the last element to where it belongs
		percolateDown(1);
		return top;
	}
	
	void percolateDown(int index) 
	{
		int child;
		int temp = tree[index];
		for(; (child = 2*index) <= load; index = child)
		{
			
			if(child != load && tree[child+1] < tree[child])
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
		tree[index] = temp;
		}
	
	
	int getArLoad(int[] array)
	{
		int k, count;
		count = 0;
		k = 1;
		while(k < array.length)
		{
			if(array[k] != 9999)
			{
				count++;
			}
			k++;
		}
		return count;
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

	void quit() 
	{
		System.out.println("Good Bye!");
	}
}
