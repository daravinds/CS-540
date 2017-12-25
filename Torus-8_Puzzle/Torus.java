import java.util.*;

class State {
	int[] board;
	State parentPt;
	int depth;
	static int totalColumns = 3;
	static int totalRows = 3;
	static int successorsCount = 4;

	// Constructor to initialize a State object from the array configuration
	public State(int[] arr) {
		this.board = Arrays.copyOf(arr, arr.length);
		this.parentPt = null;
		this.depth = 0;
	}

	// Constructor to initialize a State object from another State object
	public State(State state) {
		this.board = Arrays.copyOf(state.board, state.board.length);
		this.parentPt = state.parentPt;
		this.depth = state.depth;
	}
	
	// Constructor to initialize a successor State object from parent State object
	public State(int[] arr, State parent, int toBeIndexOfZeroInChild, int indexOfZeroInParent) {
		this.board = Arrays.copyOf(arr, arr.length);
		int temp = this.board[toBeIndexOfZeroInChild];
		this.board[toBeIndexOfZeroInChild] = this.board[indexOfZeroInParent];
		this.board[indexOfZeroInParent] = temp;
		this.parentPt = parent;
		this.depth = parent.depth + 1;
	}

	// Override equals method to define how to compare two State objects (i.e only boards should be compared)
	@Override
  public boolean equals(Object obj) {
		if(!(obj instanceof State))
			return false;
		State state = (State)obj;
		return Arrays.equals(this.board, state.board);
	}

	// Generate the index of zeroes in the 1-D array for successors.
	public static int[] getIndexOfZeroInSuccessors(int indexOfEmptySpace)	{
		int[] indicesOfSuccessors = new int[4];
		int currentRow = indexOfEmptySpace / totalRows;
		int currentColumn = indexOfEmptySpace % totalColumns;
		indicesOfSuccessors[0] = ((currentRow - 1 + totalRows) % totalRows) * totalRows + currentColumn;
		indicesOfSuccessors[1] = ((currentRow + 1 + totalRows) % totalRows) * totalRows + currentColumn;
		indicesOfSuccessors[2] = currentRow * totalRows + ((currentColumn - 1 + totalColumns) % totalColumns);
		indicesOfSuccessors[3] = currentRow * totalRows + ((currentColumn + 1 + totalColumns) % totalColumns);
		return indicesOfSuccessors;
	}
	
	public int getIndexOfZero() {
		int index;
		for(index = 0; index < this.board.length; index++)
			if(this.board[index] == 0)
				break;
		return index;
	}

	// Generate sucessors of current state sorted in the natural ascending order (where the board configuration is interpreted as a 9-digit number)
 	public State[] getSuccessors() {
		State[] successors = new State[successorsCount];
		int indexOfZero = this.getIndexOfZero();
		int[] successorZeroIndices = State.getIndexOfZeroInSuccessors(indexOfZero);
		for(int index = 0; index < successorsCount; index++)		{
			successors[index] = new State(this.board, this, successorZeroIndices[index], indexOfZero);
		}
		Arrays.sort(successors, new SortByNaturalOrder());
		return successors;
	}

	public void printState(int option) {
		if(option == 3)
		{
			System.out.print(this.getBoard() + " parent ");
			State parent = this.parentPt;
			if(parent != null)
				System.out.println(parentPt.getBoard());
			else
				System.out.println("0 0 0 0 0 0 0 0 0");
		}
		else {
			System.out.println(this.getBoard());
		}		
	}

	public String getBoard() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			builder.append(this.board[i]).append(" ");
		}
		return builder.toString().trim();
	}
	
	// Returns true if the State's board configuration matches the designated (pre-defined) goal state.
	public boolean isGoalState() {
		for (int i = 0; i < 9; i++) {
			if (this.board[i] != (i + 1) % 9)
				return false;
		}
		return true;
	}
}
	
class SortByNaturalOrder implements Comparator<State> {
	
	// Sort states by converting board configuration to 9-digit numbers and sorting the numbers in ascending order.
	public int compare(State a, State b) {
		String strA = a.getBoard().replace(" ", "");
		String strB = b.getBoard().replace(" ", "");
		return Integer.valueOf(strA) - Integer.valueOf(strB);
	}
}

public class Torus {

	public static void main(String args[]) {
		if (args.length < 10) {
			System.out.println("Invalid Input");
			return;
		}
		int flag = Integer.valueOf(args[0]);
		int[] board = new int[9];
		for (int i = 0; i < 9; i++) {
			board[i] = Integer.valueOf(args[i + 1]);
		}
		int option = flag / 100;
		int cutoff = flag % 100;
		State init = new State(board);
			
		if (option == 1) {
			State[] successors = init.getSuccessors();
			for (State successor : successors) {
				successor.printState(option);
			}
		} else {
			State stateBeingGoalChecked = null;
			Stack<State> stack = new Stack<>();
			List<State> prefix = new ArrayList<>();
			Stack<State> path = new Stack<State>();
			long goalChecked = 0;
			int maxStackSize = Integer.MIN_VALUE;
			
			while (true) {				
				stack.push(init);
				prefix.add(init);
				while (!stack.isEmpty()) {
					if((option == 5) && (stack.size() > maxStackSize))
						maxStackSize = stack.size();
					stateBeingGoalChecked = stack.pop();
					if(option == 5){
						goalChecked++;
					}
	
					if(prefix.contains(stateBeingGoalChecked.parentPt)) {
						int parentIndex = prefix.indexOf(stateBeingGoalChecked.parentPt);
						prefix.subList(parentIndex+1, prefix.size()).clear();
						prefix.add(stateBeingGoalChecked);
					}

					if(option == 1 || option == 2 || option == 3)
						stateBeingGoalChecked.printState(option);
					if(stateBeingGoalChecked.isGoalState()) {
						if(option == 5) {
							for(int i = 0; i< prefix.size(); i++)
								prefix.get(i).printState(option);
							System.out.println("Goal-check " + goalChecked);
							System.out.println("Max-stack-size " + maxStackSize);
						}
						return;
					} 
					else {
						if(stateBeingGoalChecked.depth < cutoff) {
							for(State successor: stateBeingGoalChecked.getSuccessors()){
								if(!prefix.contains(successor)) {
									stack.push(successor);
								}
							}
						}
						else if(stateBeingGoalChecked.depth == cutoff && option == 4) {
							for(int index = 0; index < prefix.size(); index++)
								prefix.get(index).printState(option);
							return;
						}
					}			
				}
				
				if (option != 5)
					break;
				cutoff++;
				stack.clear();
				prefix.clear();
			}
		}
	}
}