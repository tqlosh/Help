import java.util.Random;


public class AIRandom {
	static Random rand = new Random();
	static Rules myRules = new Rules();
	static int[][] turnHistory = new int[0][6];
	static int turnCount = 0;
	Board myBoard = new Board();

	public static int[] makeMove(Board board){
		int[][] moves = Board.availableMove(board, 1);
		
//		for(int i = 0; i<moves.length; i++){
//			System.out.println("Move: "+moves[i][0]+","+moves[i][1]+" "+moves[i][2]+","+moves[i][3]);
//		}
		
		int x = rand.nextInt(moves.length);
		int[] rawMove = moves[x];
		int[] move = new int[6];
		move[0] = rawMove[0];
		move[1] = rawMove[1];
		move[2] = rawMove[2];
		move[3] = rawMove[3];
		move[4] = rawMove[4];
		move[5] = turnCount;
		add(move);
		System.out.println("Move: "+turnHistory[turnCount][0]+","+turnHistory[turnCount][1]+" "+turnHistory[turnCount][2]+","+turnHistory[turnCount][3]+" "+turnHistory[turnCount][5]);
		turnCount++;
		return move;
	}

	public static void add(int[] newMove){
		int[][] newTurnHistory= new int[turnHistory.length+1][6];

		if(turnHistory.length > 0){
			for(int i=0; i<turnHistory.length; i++){
				newTurnHistory[i]=turnHistory[i];
			}
		}
		
		newTurnHistory[turnHistory.length]= newMove;
		turnHistory = newTurnHistory;
	}
}