import java.util.Random;


public class MinMaxAI {

	static Random rand = new Random();
	static int[][] turnHistory = new int[0][6];
	static int turnCount = 0;
	static int[] finalMove = new int[6];
	
	public static int makeMove(Board board, int currentPlayerColor, int maxingPlayerColor){

		int score = 0;
		int[] moveIndex={};
		int[] move;
		if (Rules.gameOver(board)[0]==1){
			if(Rules.gameOver(board)[1] == maxingPlayerColor){
				System.out.println("1");
				return score = 1;
			} else if(Rules.gameOver(board)[1] == Math.abs(maxingPlayerColor-1)){
				System.out.println("-1");
				return score = -1;
			} else{
				System.out.println("0");
				return score = 0;
			}
		}else{
			int[][] availableMoves = board.availableMove(board, currentPlayerColor);
			int[] allScores = {};
			
			
			
			
//			System.out.println("loop length: "+ availableMoves.length);
//			for(int x = 0; x<availableMoves.length; x++){
//					System.out.println(availableMoves[x][0]+","+availableMoves[x][1]+" "+availableMoves[x][2]+","+availableMoves[x][3]);
//			}
			
			
			
			
			
			
			for(int x = 0; x<availableMoves.length; x++){
				Board temp = board;
				if(currentPlayerColor == 0){
					Board.doMove(availableMoves[x], temp, 7, 2);
					System.out.println("w");
				}else{
					Board.doMove(availableMoves[x], temp, 0, 3);
					System.out.println("c");
				}
//				System.out.println(""+x);
				allScores = add(makeMove(temp,Math.abs(maxingPlayerColor-1), maxingPlayerColor), allScores);
				System.out.println("allscore len "+ allScores.length);
			}
			if(currentPlayerColor == maxingPlayerColor){
				for(int x = 0; x<allScores.length; x++){
					if(score < allScores[x]){
						allScores = add(score, allScores);
					}
				}
				for(int x = 0; x<allScores.length; x++){
					if(score == allScores[x]){
						moveIndex = add(x,moveIndex);
					}
				}
				System.out.println("max "+moveIndex.length);
				move = availableMoves[rand.nextInt(moveIndex.length)];
			}else{
				for(int x = 0; x<allScores.length; x++){
					if(score > allScores[x]){
						score = allScores[x];
					}
				}
				for(int x = 0; x<allScores.length; x++){
					if(score == allScores[x]){
						moveIndex = add(x,moveIndex);
					}
				}
				System.out.println("min "+moveIndex.length);
				move = availableMoves[rand.nextInt(moveIndex.length)];
			}
		}
		finalMove[0] = move[0];
		finalMove[1] = move[1];
		finalMove[2] = move[2];
		finalMove[3] = move[3];
		finalMove[4] = move[4];
		finalMove[5] = turnCount;
		addMove(move);
		System.out.println("Move: "+turnHistory[turnCount][0]+","+turnHistory[turnCount][1]+" "+turnHistory[turnCount][2]+","+turnHistory[turnCount][3]+" "+turnHistory[turnCount][5]);
		turnCount++;
		System.out.println("Shitty");
		return 0;
	}
	
	public static int[] add(int item, int[] array){
		int[] newArray= new int[array.length+1];

		if(array.length > 0){
			for(int i=0; i<array.length; i++){
				newArray[i]=array[i];
			}
		}

		newArray[array.length]= item;
		return newArray;
	}
	
	public static void addMove(int[] newMove){
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