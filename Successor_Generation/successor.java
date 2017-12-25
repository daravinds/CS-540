import java.util.*;

public class successor {
    public static class JugState {
			int[] Capacity = new int[]{0,0,0};
			int[] Content = new int[]{0,0,0};

			public JugState(JugState copyFrom)
			{
				this.Capacity[0] = copyFrom.Capacity[0];
				this.Capacity[1] = copyFrom.Capacity[1];
				this.Capacity[2] = copyFrom.Capacity[2];
				this.Content[0] = copyFrom.Content[0];
				this.Content[1] = copyFrom.Content[1];
				this.Content[2] = copyFrom.Content[2];
			}

			public JugState()
			{
			}

			public JugState(int A,int B, int C)
			{
				this.Capacity[0] = A;
				this.Capacity[1] = B;
				this.Capacity[2] = C;
			}

			public JugState(int A,int B, int C, int a, int b, int c)
			{
				this.Capacity[0] = A;
				this.Capacity[1] = B;
				this.Capacity[2] = C;
				this.Content[0] = a;
				this.Content[1] = b;
				this.Content[2] = c;
			}

			public void printContent()
			{
				System.out.println(this.Content[0] + " " + this.Content[1] + " " + this.Content[2]);
			}

			public ArrayList<JugState> getNextStates(){
				ArrayList<JugState> successors = new ArrayList<>();

				// Case 1: Empty any non-empty jugs
				for(int index = 0; index < this.Content.length; index++)
				{
					if(this.Content[index] != 0) {
						int oldContent = this.Content[index];
						this.Content[index] = 0;
						addSuccesorToList(successors, this);
						this.Content[index] = oldContent;
					}				
				}
				
				// Case 2: Fill any non-full jugs
				for(int index = 0; index < this.Content.length; index++)
				{
					if(this.Content[index] < this.Capacity[index]) {
						int oldContent = this.Content[index];
						this.Content[index] = this.Capacity[index];
						addSuccesorToList(successors, this);
						this.Content[index] = oldContent;
					}				
				}
				
				// Case 3: Transfer from one jug to another
				for(int sourceIndex = 0; sourceIndex < this.Content.length; sourceIndex++) {
					if(this.Content[sourceIndex] != 0) { // Source jug must have water 
						for(int targetIndex = 0; targetIndex < this.Content.length; targetIndex++) {
							if((sourceIndex != targetIndex)){ // Source jug must be different from the target jug 
								int targetRemaining = this.Capacity[targetIndex] - this.Content[targetIndex];					
								if(targetRemaining > 0) { // Target must have empty space to get filled
									int contentToTransfer = Math.min(targetRemaining, this.Content[sourceIndex]);
									this.Content[targetIndex] += contentToTransfer;
									this.Content[sourceIndex] -= contentToTransfer;
									addSuccesorToList(successors, this);
									this.Content[targetIndex] -= contentToTransfer;
									this.Content[sourceIndex] += contentToTransfer;
								}
							}
						}
					}
				}
				return successors;
			}
    }
		
		public static void addSuccesorToList(ArrayList<JugState> successors, JugState newState) {
			JugState jugState = new JugState(newState);
			successors.add(jugState);
		}

    public static void main(String[] args) {
			if( args.length != 6 )
			{
					System.out.println("Usage: java successor [A] [B] [C] [a] [b] [c]");
					return;
			}

			// parse command line arguments
			JugState a = new JugState();
			a.Capacity[0] = Integer.parseInt(args[0]);
			a.Capacity[1] = Integer.parseInt(args[1]);
			a.Capacity[2] = Integer.parseInt(args[2]);
			a.Content[0] = Integer.parseInt(args[3]);
			a.Content[1] = Integer.parseInt(args[4]);
			a.Content[2] = Integer.parseInt(args[5]);

			ArrayList<JugState> asist = a.getNextStates();

			// Print out generated successors
			for(int i=0;i< asist.size(); i++)
			{
					asist.get(i).printContent();
			}

			return;
    }
}

