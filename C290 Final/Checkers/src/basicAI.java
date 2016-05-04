import java.util.Random;


public class basicAI {
	static Random rand = new Random();
	static Rules myRules = new Rules();
	static int[][] turnHistory = new int[0][6];
	static int turnCount = 0;

	public static int[] makeMove(Board board){
		int currentRow = 100;
		int currentColumn = 100;
		int newX = currentRow+1;
		int newY = currentColumn+1;
		int jump = 0;

		boolean[] valid = Rules.validate(currentRow, currentColumn, newX, newY, board,0);
		boolean[] valid1 = Rules.validate(currentRow, currentColumn, newX, newY-2, board,0);
		boolean[] valid2 = Rules.validate(currentRow, currentColumn, newX-2, newY, board,0);
		boolean[] valid3 = Rules.validate(currentRow, currentColumn, newX-2, newY-2, board,0);

		//king skip move
		for(int x = board.rows-1; x>=0; x--){
			for(int y = board.columns-1; y>=0; y--){
				if(myRules.validate(x, y, x-2, y+2, board,0)[0] == true){
					currentRow = x;
					currentColumn = y;
					newX = x-2;
					newY = y+2;
					jump = 1;
					int[] move = new int[6];
					move[0] = currentRow;
					move[1] = currentColumn;
					move[2] = newX;
					move[3] = newY;
					move[4] = jump;
					move[5] = turnCount;
					add(move);
					System.out.println(turnHistory[turnCount][0]+","+turnHistory[turnCount][1]+" "+turnHistory[turnCount][2]+","+turnHistory[turnCount][3]+" "+turnHistory[turnCount][5]);
					turnCount++;
					return move;
				}
				if(myRules.validate(x, y, x-2, y-2, board,0)[0] == true){
					currentRow = x;
					currentColumn = y;
					newX = x-2;
					newY = y-2;
					jump = 1;
					int[] move = new int[6];
					move[0] = currentRow;
					move[1] = currentColumn;
					move[2] = newX;
					move[3] = newY;
					move[4] = jump;
					move[5] = turnCount;
					add(move);
					System.out.println(turnHistory[turnCount][0]+","+turnHistory[turnCount][1]+" "+turnHistory[turnCount][2]+","+turnHistory[turnCount][3]+" "+turnHistory[turnCount][5]);
					turnCount++;
					return move;
				}
			}
		}

		//skip move
		for(int x = board.rows-1; x>=0; x--){
			for(int y = board.columns-1; y>=0; y--){
				if(myRules.validate(x, y, x+2, y+2, board,0)[0] == true){
					currentRow = x;
					currentColumn = y;
					newX = x+2;
					newY = y+2;
					jump = 1;
					int[] move = new int[6];
					move[0] = currentRow;
					move[1] = currentColumn;
					move[2] = newX;
					move[3] = newY;
					move[4] = jump;
					move[5] = turnCount;
					add(move);
					System.out.println(turnHistory[turnCount][0]+","+turnHistory[turnCount][1]+" "+turnHistory[turnCount][2]+","+turnHistory[turnCount][3]+" "+turnHistory[turnCount][5]);
					turnCount++;
					return move;
				}
				if(myRules.validate(x, y, x+2, y-2, board,0)[0] == true){
					currentRow = x;
					currentColumn = y;
					newX = x+2;
					newY = y-2;
					jump = 1;
					int[] move = new int[6];
					move[0] = currentRow;
					move[1] = currentColumn;
					move[2] = newX;
					move[3] = newY;
					move[4] = jump;
					move[5] = turnCount;
					add(move);
					System.out.println(turnHistory[turnCount][0]+","+turnHistory[turnCount][1]+" "+turnHistory[turnCount][2]+","+turnHistory[turnCount][3]+" "+turnHistory[turnCount][5]);
					turnCount++;
					return move;
				}
			}
		}

		//start move
		while(valid[0]==false && valid1[0]==false && valid2[0]==false && valid3[0]==false){
			currentRow = rand.nextInt(board.rows);
			currentColumn = rand.nextInt(board.columns);
			newX = currentRow+1;
			newY = currentColumn+1;
			valid = Rules.validate(currentRow, currentColumn, newX, newY, board,0);
			valid1 = Rules.validate(currentRow, currentColumn, newX, newY-2, board,0);
			valid2 = Rules.validate(currentRow, currentColumn, newX-2, newY, board,0);
			valid3 = Rules.validate(currentRow, currentColumn, newX-2, newY-2, board,0);

			//reg move left
			if(valid[0] == true){
				int[] move = new int[6];
				move[0] = currentRow;
				move[1] = currentColumn;
				move[2] = newX;
				move[3] = newY;
				move[4] = jump;
				move[5] = turnCount;
				add(move);
				System.out.println(turnHistory[turnCount][0]+","+turnHistory[turnCount][1]+" "+turnHistory[turnCount][2]+","+turnHistory[turnCount][3]+" "+turnHistory[turnCount][5]);
				turnCount++;
				return move;
			}

			//reg move right
			if(valid1[0] == true){
				newY = newY-2;
				int[] move = new int[6];
				move[0] = currentRow;
				move[1] = currentColumn;
				move[2] = newX;
				move[3] = newY;
				move[4] = jump;
				move[5] = turnCount;
				add(move);
				System.out.println(turnHistory[turnCount][0]+","+turnHistory[turnCount][1]+" "+turnHistory[turnCount][2]+","+turnHistory[turnCount][3]+" "+turnHistory[turnCount][5]);
				turnCount++;
				return move;
			}

			//king move to left
			if(valid2[0]==true){
				newX = newX-2;
				int[] move = new int[6];
				move[0] = currentRow;
				move[1] = currentColumn;
				move[2] = newX;
				move[3] = newY;
				move[4] = jump;
				move[5] = turnCount;
				add(move);
				System.out.println(turnHistory[turnCount][0]+","+turnHistory[turnCount][1]+" "+turnHistory[turnCount][2]+","+turnHistory[turnCount][3]+" "+turnHistory[turnCount][5]);
				turnCount++;
				return move;
			}

			//king move to right
			if(valid3[0]==true){
				newX = newX-2;
				newY = newY-2;
				int[] move = new int[6];
				move[0] = currentRow;
				move[1] = currentColumn;
				move[2] = newX;
				move[3] = newY;
				move[4] = jump;
				move[5] = turnCount;
				add(move);
				System.out.println(turnHistory[turnCount][0]+","+turnHistory[turnCount][1]+" "+turnHistory[turnCount][2]+","+turnHistory[turnCount][3]+" "+turnHistory[turnCount][5]);
				turnCount++;
				return move;
			}
		}
		return null;
	}

	public static void add(int[] newMove){
		int[][] newTurnHistory= new int[turnHistory.length+1][6];

		if(turnHistory.length == 0){
			newTurnHistory[0] = newMove;
		}else{
			for(int i=0; i<turnHistory.length; i++){
				newTurnHistory =new int[turnHistory.length+1][6];
				newTurnHistory[i]=turnHistory[i];
			}
		}

		newTurnHistory[turnHistory.length]= newMove;
		turnHistory = newTurnHistory;
	}

}
