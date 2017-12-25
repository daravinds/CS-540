import java.util.*;

class Index {
	int x, y;
	public Index(int x, int y) {
		this.x = x;
		this.y = y;
	}	
}

class State {
	char[] board;
	static char player1 = '1';
	static char player2 = '2';

	public State(char[] arr) {
		this.board = Arrays.copyOf(arr, arr.length);
	}

	// Return the game-theoretic value of a terminal state based on the which player's count of tiles is higher.
	public int getScore() {
		int score = 0;
		int player1_count = 0, player2_count = 0;
		for(char position: this.board) {
			if(position == player1)
				player1_count++;
			else if(position == player2)
				player2_count++;
		}
		if(player1_count > player2_count)
			score = 1;
		else if(player1_count == player2_count)
			score = 0;
		else if(player1_count < player2_count)
			score = -1;
		return score;
	}
	
	// Return true if neither player has a valid move at this state.
	public boolean isTerminal() {
		State[] successors1 = this.getSuccessors(State.player1);
		State[] successors2 = this.getSuccessors(State.player2);
		return ((successors1.length == 0) && (successors2.length == 0));
	}

	// Return the indices on the board that are yet to be filled.
	public ArrayList<Index> getEmptyIndices() {
		ArrayList<Index> emptyIndices = new ArrayList<Index>();
		for(int i = 0; i < 16; i++) {
			int x = i / 4;
			int y = i % 4;
			if(this.isEmptyCell(x, y)) {
				Index emptyIndex = new Index(x, y);
				emptyIndices.add(emptyIndex);
			}
		}
		return emptyIndices;
	}

	// Return true if the 2-D representation of the index is within valid bounds.
	public boolean isValidIndex(int x, int y) {
		return ((x >= 0) && (x < 4) && (y >= 0) && (y < 4));
	}
	
	// Return true if the position on the board is empty.
	public boolean isEmptyCell(int x, int y) {
		return this.valueAt(x, y) == '0';
	}
	
	// Return the value at the 2-D representation of the index.
	public char valueAt(int x, int y) {
		if(isValidIndex(x, y))
			return this.board[x * 4 + y];
		else
			return '3';
	}
		
	// Returns true if the player has a valid move from the current state to fill the index.
	public boolean canFillIndex(Index index, char player) {
		return (this.getSuccessorFor(index, player) != null);
	}
	
	// Return the successor for the current state that is generated when the player fills the index.
	public State getSuccessorFor(Index index, char player) {
		int[] moveAlongX = {-1, -1, -1, 0, 0, 1, 1, 1};
		int[] moveAlongY = {-1, 0, 1, -1, 1, -1, 0, 1};
		char otherPlayer = player == State.player1 ? State.player2 : State.player1;
		State successor = null;

		for(int i = 0; i < 8; i++) {
			int newx = index.x + moveAlongX[i];
			int newy = index.y + moveAlongY[i];
			if(this.valueAt(newx, newy) != otherPlayer)
				continue;
			while(this.valueAt(newx, newy) == otherPlayer) {
				newx = newx + moveAlongX[i];
				newy = newy + moveAlongY[i];
			}
			if(this.valueAt(newx, newy) == player) {
				if(successor == null)
					successor = new State(this.board);
				successor.board[index.x * 4 + index.y] = player;
				newx = index.x + moveAlongX[i];
				newy = index.y + moveAlongY[i];
				while(successor.valueAt(newx, newy) == otherPlayer) {
					successor.board[newx * 4 + newy] = player;
					newx = newx + moveAlongX[i];
					newy = newy + moveAlongY[i];
				}
			}
		}
		return successor;
	}

	// Returns the array of successors for the current state when 'player' takes turn.
	public State[] getSuccessors(char player) {
		ArrayList<Index> emptyIndices = this.getEmptyIndices();
		ArrayList<State> successors = new ArrayList<State>();
		char otherPlayer = player == State.player1 ? State.player2 : State.player1;
 		for(Index index: emptyIndices) {
			if(this.canFillIndex(index, player)) {
				State successor = this.getSuccessorFor(index, player);
				successors.add(successor);
			}
		}
		State[] successorsArray = new State[successors.size()];
		for(int i = 0; i < successors.size(); i++)
			successorsArray[i] = successors.get(i);
		return successorsArray;
	}

	public void printState(int option, char player) {
		System.out.println(this.getBoard());
	}

	public String getBoard() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 16; i++) {
			builder.append(this.board[i]);
		}
		return builder.toString().trim();
	}

	public boolean equals(State src) {
		for (int i = 0; i < 16; i++)
			if (this.board[i] != src.board[i])
				return false;
		return true;
	}
}

class Minimax {
	static int counter = 0, pruning_counter = 0;
	private static int max_value(State curr_state) {
		counter++;
		char player = State.player1;
		if(curr_state.isTerminal())
			return curr_state.getScore();
		else {
			int alpha = Integer.MIN_VALUE;
			State[] successors = curr_state.getSuccessors(player);
			
			if(successors.length == 0) {
				successors = new State[1];
				successors[0] = new State(curr_state.board);
			}
			for(State successor: successors) {
				alpha = Integer.max(alpha, min_value(successor));
			}
			return alpha;
		}
	}
	
	private static int min_value(State curr_state) {	
		counter++;
		char player = State.player2;
		if(curr_state.isTerminal())
			return curr_state.getScore();
		else {
			int beta = Integer.MAX_VALUE;
			State[] successors = curr_state.getSuccessors(player);

			if(successors.length == 0) {
				successors = new State[1];
				successors[0] = new State(curr_state.board);
			}
			for(State successor: successors) {
				beta = Integer.min(beta, max_value(successor));
			}
			return beta;
		}
	}

	public static State max_move(State curr_state) {
		char player = State.player1;
		if(curr_state.isTerminal())
			return null;
		else {
			int alpha = Integer.MIN_VALUE;
			State[] successors = curr_state.getSuccessors(player);
			if(successors.length == 0)
				return curr_state;
			for(State successor: successors) {
				alpha = Integer.max(alpha, min_value(successor));
			}
			for(State successor: successors)
				if(alpha == Integer.max(alpha, min_value(successor)))
					return successor;
			return null;
		}
	}
	
	public static State min_move(State curr_state) {
		char player = State.player2;
		if(curr_state.isTerminal())
			return null;
		else {
			int beta = Integer.MAX_VALUE;
			State[] successors = curr_state.getSuccessors(player);
			if(successors.length == 0)
				return curr_state;
			for(State successor: successors) {
				beta = Integer.min(beta, max_value(successor));
			}
			for(State successor: successors)
				if(beta == Integer.min(beta, max_value(successor)))
					return successor;
			return null;
		}
	}

	private static int max_value_with_pruning(State curr_state, int alpha, int beta) {
		pruning_counter++;
		char player = State.player1;
		if(curr_state.isTerminal())
			return curr_state.getScore();
		else {
			State[] successors = curr_state.getSuccessors(player);
			
			if(successors.length == 0) {
				successors = new State[1];
				successors[0] = new State(curr_state.board);
			}
			for(State successor: successors) {
				alpha = Integer.max(alpha, min_value_with_pruning(successor, alpha, beta));
				if(alpha >= beta)
					return beta;
			}
			return alpha;
		}
	}
	
	private static int min_value_with_pruning(State curr_state, int alpha, int beta) {
		pruning_counter++;
		char player = State.player2;
		if(curr_state.isTerminal())
			return curr_state.getScore();
		else {
			State[] successors = curr_state.getSuccessors(player);

			if(successors.length == 0) {
				successors = new State[1];
				successors[0] = new State(curr_state.board);
			}
			for(State successor: successors) {
				beta = Integer.min(beta, max_value_with_pruning(successor, alpha, beta));
				if(alpha >= beta)
					return alpha;
			}
			return beta;
		}
	}
	
	public static State max_move_with_pruning(State curr_state, int alpha, int beta) {
		int oldAlpha = alpha;
		char player = State.player1;
		if(curr_state.isTerminal())
			return null;
		else {
			State[] successors = curr_state.getSuccessors(player);		
			if(successors.length == 0)
				return curr_state;
			for(State successor: successors) {
				alpha = Integer.max(alpha, min_value_with_pruning(successor, alpha, beta));
				if(alpha >= beta)
					return null;
			}
			for(State successor: successors) {
				oldAlpha = Integer.max(oldAlpha, min_value_with_pruning(successor, oldAlpha, beta));			
				if(alpha == oldAlpha)
					return successor;
			}
			return null;
		}
	}

	public static State min_move_with_pruning(State curr_state, int alpha, int beta) {
		int oldBeta = beta;
		char player = State.player2;
		if(curr_state.isTerminal())
			return null;
		else {
			State[] successors = curr_state.getSuccessors(player);		
			if(successors.length == 0)
				return curr_state;
			for(State successor: successors) {
				beta = Integer.min(beta, max_value_with_pruning(successor, alpha, beta));
				if(alpha >= beta)
					return null;
			}
			for(State successor: successors) {
				oldBeta = Integer.min(oldBeta, max_value_with_pruning(successor, alpha, oldBeta));			
				if(beta == oldBeta)
					return successor;
			}
			return null;
		}
	}

	public static int run(State curr_state, char player) {
		if(player == State.player1)
			return Minimax.max_value(curr_state);
		else
			return Minimax.min_value(curr_state);
	}
	
	public static int run_with_pruning(State curr_state, char player) {
		if(player == State.player1)
			return Minimax.max_value_with_pruning(curr_state, Integer.MIN_VALUE, Integer.MAX_VALUE);
		else
			return Minimax.min_value_with_pruning(curr_state, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
}

public class Reversi {
	public static void main(String args[]) {
		if (args.length != 3) {
			System.out.println("Invalid Number of Input Arguments");
			return;
		}
		int flag = Integer.valueOf(args[0]);
		char[] board = new char[16];
		for (int i = 0; i < 16; i++) {
			board[i] = args[2].charAt(i);
		}
		int option = flag / 100;
		char player = args[1].charAt(0);
		if ((player != State.player1 && player != State.player2) || args[1].length() != 1) {
			System.out.println("Invalid Player Input");
			return;
		}
		State init = new State(board);
		
		if(option == 1)
		{
			State[] successors = init.getSuccessors(player);
			if(!init.isTerminal() && successors.length == 0)
				init.printState(option, player);
			else {
				for(State successor: successors)
					successor.printState(option, player);
			}
		}
		else if(option == 2){
			if(init.isTerminal()) {
				System.out.println(init.getScore());
			} else {
				System.out.println("non-terminal");
			}
		} else if(option == 3) {
			Minimax.counter = 0;
			System.out.println(Minimax.run(init, player));
			System.out.println(Minimax.counter);
		} else if(option == 4) {
			State successor;
			if(player == State.player1)
				successor = Minimax.max_move(init);
			else
				successor = Minimax.min_move(init);
			if(successor != null)
				successor.printState(option, player);
		} else if(option == 5) {
			Minimax.pruning_counter = 0;
			System.out.println(Minimax.run_with_pruning(init, player));
			System.out.println(Minimax.pruning_counter);
		} else if(option == 6) {
			State successor;
			if(player == State.player1)
				successor = Minimax.max_move_with_pruning(init, Integer.MIN_VALUE, Integer.MAX_VALUE);
			else
				successor = Minimax.min_move_with_pruning(init, Integer.MIN_VALUE, Integer.MAX_VALUE);
			if(successor != null)
				successor.printState(option, player);
		}
	}
}