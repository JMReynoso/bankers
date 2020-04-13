import java.util.*;

public class bankers {

	public static void main(String[] args) {
		int numCustomers;
		int numResources;

		// set variables
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter customer(row) size: ");
		numCustomers = scan.nextInt();

		System.out.println("Enter resource(col) size: ");
		numResources = scan.nextInt();

		int[][] maximum = new int[numCustomers][numResources];
		int[][] allocation = new int[numCustomers][numResources];
		int[][] need = new int[numCustomers][numResources];

		scan.nextLine();

		System.out.println("Enter available array: ");

		String line = scan.nextLine();
		String[] split = line.split(" ");

		int[] available = stringToInt(split);

		System.out.println("");
		
		System.out.println("Enter ALLOCATION array: ");
		// i must match numCustomers
		int i = 0;
		while (i < numCustomers) {
			line = scan.nextLine();
			split = line.split(" ");
			allocation[i] = stringToInt(split);
			i++;
		}

		System.out.println("");
		
		System.out.println("Enter MAXIMUM array: ");

		// i must match numCustomers
		i = 0;
		while (i < numCustomers) {
			line = scan.nextLine();
			split = line.split(" ");
			maximum[i] = stringToInt(split);
			i++;
		}

		System.out.println("");

		scan.close();

		need = getNeed(allocation, maximum, numCustomers, numResources);
		
		System.out.println("Need array: ");
		printArray(need);

		ArrayList<String> seq = safeSequence(need, allocation, maximum, available, numCustomers, numResources);
		
		if(seq.isEmpty() || (seq.size() < numCustomers)) {
			error();
		}
		
		else {
			System.out.println("Success! Safe sequence is: \n");
			System.out.print("<");
			for (int h = 0; h < seq.size(); h++) {

				if (h == seq.size() - 1) {
					System.out.print(seq.get(h));
				}

				else {
					System.out.print(seq.get(h) + ", ");
				}

			}

			System.out.print(">");
		}

	}

	public static int[] stringToInt(String[] array) {
		int[] num = new int[array.length];

		for (int i = 0; i < num.length; i++) {
			num[i] = Integer.parseInt(array[i]);
		}

		return num;
	}

	public static int[][] getNeed(int[][] alloc, int[][] max, int row, int col) {
		int[][] need = new int[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				need[i][j] = max[i][j] - alloc[i][j];
			}
		}

		return need;
	}

	// GIRL YOU NEED TO WORK ON THIS FJSDKAHFAJKSDHFG
	public static ArrayList<String> safeSequence(int[][] need, int[][] alloc, int[][] max, int[] available, int row,
			int col) {
		ArrayList<String> seq = new ArrayList<String>();
		Queue<Integer> goBack = new LinkedList<Integer>();
		boolean[] visited = new boolean[row];
		int[] work = available;

		int i = 0;
		boolean passNeedWorkTest = false;

		while(true) {
			if(visited[i] == false) {
				//go through each processes
				while (i < row) {
					
					// if need<work is false
					for (int j = 0; j < col; j++) {
						if (need[i][j] > work[j]) {
							break;
						}

						// if last item is passed
						if (j == col - 1) {
							passNeedWorkTest = true;
						}
					}

					if (passNeedWorkTest && visited[i] == false) {

						// place process into safe sequence
						seq.add("P" + i);
						
						// update work
						for (int h = 0; h < col; h++) {
							work[h] += alloc[i][h];
						}
						visited[i]=true;
					}
						
					else {
						goBack.add(i);
					}
						
					passNeedWorkTest = false;
					i++;
				}
				
			}
				
			try{
				i=goBack.remove();
			}catch(Exception e) {
				//go back is empty
				break;
			}
		}
		
		return seq;
	}

	public static void error() {
		System.out.println("No safe sequence exist.");
		System.exit(0);
	}
	
	public static void printArray(int[][] array) {
		for(int i=0; i<array.length; i++) {
			for(int j=0; j<array[i].length; j++) {
				if(j==array[i].length-1) {
					System.out.print(array[i][j]+"\n");
				}
				else {
					System.out.print(array[i][j]+", ");
				}
				
			}
		}
		
		System.out.println("");
	}

}
