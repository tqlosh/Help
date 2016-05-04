import java.util.Scanner;


public class Runner extends Board {

	public static void main(String[] args) {

		//create scanner and board objects
		Scanner scanner = new Scanner(System.in);
		Board myBoard = new Board();
		MinMaxAI minMaxAI = new MinMaxAI();

		//populate my board
		Board.populate();
		//		System.out.println(textView(myBoard));

		//		int[][] x = {{0,2}};
		//		int[] y = {1,4};
		//		int[] w = {5,6};
		//		x = Board.add(y, x);
		//		x = Board.add(w, x);
		//		System.out.println(""+x.length);
		//		for(int z = 0; z<x.length; z++){
		//			System.out.println(x[z][0]+" "+x[z][1]);
		//		}

		//testing available moves
		int[][] availableMoves = myBoard.availableMove(myBoard, 0);
		System.out.println(availableMoves.length);
		for(int x = 0; x<availableMoves.length; x++){
			System.out.println(availableMoves[x][0]+","+availableMoves[x][1]+" "+availableMoves[x][2]+","+availableMoves[x][3]);
		}

		//boolean to run the game
		boolean gameOver = false;

		//Ask what player types are playing
		System.out.println("Please type: \n1 for pass and play\n2 for player vs randomAI\n3 for player vs basicAI\n4 for AI vs AI");
		int playerType = scanner.nextInt();		

		if(playerType == 1){
			System.out.println(textView(myBoard));
			while(gameOver == false){
				//variables and while loop to check for double skip
				int kingPosition;
				boolean[] valid = {false, true};
				boolean canSkip = true;
				//Find out and print who's turn it is
				if(myBoard.turnKeeper%2 == 0){
					System.out.println("Reds up!");
					kingPosition = 7;
				}else{
					System.out.println("Blacks up!");
					kingPosition = 0;
				}

				//Get current and new coordinates of the chip moving
				System.out.println("Please type the x coordinates of the piece you want to move.");
				int currentX = scanner.nextInt();

				System.out.println("Please type the y coordinates of the piece you want to move.");
				int currentY = scanner.nextInt();

				System.out.println("Please type the new x coordinates of where you want the piece to go.");
				int newX = scanner.nextInt();

				System.out.println("Please type the new y coordinates of where you want the piece to go.");
				int newY = scanner.nextInt();

				//Test if the input is valid if not have the user reenter the value
				valid = Rules.validate(currentX, currentY, newX, newY, myBoard,myBoard.turnKeeper%2);
				while(valid[0] == false){
					System.out.println("Move not valid please choose a valid Move!");
					System.out.println("Please type the x coordinates of the piece you want to move.");
					currentX = scanner.nextInt();

					System.out.println("Please type the y coordinates of the piece you want to move.");
					currentY = scanner.nextInt();

					System.out.println("Please type the new x coordinates of where you want the piece to go.");
					newX = scanner.nextInt();

					System.out.println("Please type the new y coordinates of where you want the piece to go.");
					newY = scanner.nextInt();				

					valid = Rules.validate(currentX, currentY, newX, newY, myBoard,myBoard.turnKeeper%2);
				}

				int[] move = new int[5];
				if(valid[1]==true){
					int[] a = {currentX, currentY, newX, newY, 1};
					move = a;
				}else{
					int[] a = {currentX, currentY, newX, newY, 0};
					move = a;
				}

				Board.doMove(move, myBoard, kingPosition, 5);

				//Print new changed board
				System.out.println(textView(myBoard));


				//update turnKeeper
				myBoard.turnKeeper++;

				//count chips to check if games over
				int[] check = Rules.gameOver(myBoard);
				if(check[0]==1){
					gameOver = true;
				}
			}

		}else if(playerType == 2){
			System.out.println(textView(myBoard));
			while(gameOver == false){
				//Find out and print who's turn it is
				if(myBoard.turnKeeper%2 == 0){
					//variables to check for double skip
					boolean[] valid = {false, true};
					boolean canSkip = true;
					while(valid[1]==true && canSkip==true){
						System.out.println("Reds up!");
						//Get current and new coordinates of the chip moving
						System.out.println("Please type the x coordinates of the piece you want to move.");
						int currentX = scanner.nextInt();

						System.out.println("Please type the y coordinates of the piece you want to move.");
						int currentY = scanner.nextInt();

						System.out.println("Please type the new x coordinates of where you want the piece to go.");
						int newX = scanner.nextInt();

						System.out.println("Please type the new y coordinates of where you want the piece to go.");
						int newY = scanner.nextInt();

						//Test if the input is valid if not have the user reenter the value
						valid = Rules.validate(currentX, currentY, newX, newY, myBoard,0);
						while(valid[0] == false){
							System.out.println("Move not valid please choose a valid Move!");
							System.out.println("Please type the x coordinates of the piece you want to move.");
							currentX = scanner.nextInt();

							System.out.println("Please type the y coordinates of the piece you want to move.");
							currentY = scanner.nextInt();

							System.out.println("Please type the new x coordinates of where you want the piece to go.");
							newX = scanner.nextInt();

							System.out.println("Please type the new y coordinates of where you want the piece to go.");
							newY = scanner.nextInt();				

							valid = Rules.validate(currentX, currentY, newX, newY, myBoard,0);
						}

						int[] move = new int[5];
						if(valid[1]==true){
							int[] a = {currentX, currentY, newX, newY, 1};
							move = a;
						}else{
							int[] a = {currentX, currentY, newX, newY, 0};
							move = a;
						}

						Board.doMove(move, myBoard, 7, 5);

						//Print new changed board
						System.out.println(textView(myBoard));

					}

				}else{
					//variables to check for double skip
					int[] move = {0,0,0,0,1};
					System.out.println("Blacks up!");
					move = AIRandom.makeMove(myBoard);

					//doMove
					Board.doMove(move, myBoard, 0, 1);

					//Print new changed board
					System.out.println(textView(myBoard));

				}

				//update turnKeeper
				myBoard.turnKeeper++;

				//count chips to check if games over
				int[] check = Rules.gameOver(myBoard);
				if(check[0]==1){
					gameOver = true;
				}
			}
		}else if(playerType == 3){
			System.out.println(textView(myBoard));
			while(gameOver == false){
				//Find out and print who's turn it is
				if(myBoard.turnKeeper%2 == 1){
					//variables to check for double skip
					boolean[] valid = {false, true};
					boolean canSkip = true;
					while(valid[1]==true && canSkip==true){
						System.out.println("Blacks up!");
						//Get current and new coordinates of the chip moving
						System.out.println("Please type the x coordinates of the piece you want to move.");
						int currentX = scanner.nextInt();

						System.out.println("Please type the y coordinates of the piece you want to move.");
						int currentY = scanner.nextInt();

						System.out.println("Please type the new x coordinates of where you want the piece to go.");
						int newX = scanner.nextInt();

						System.out.println("Please type the new y coordinates of where you want the piece to go.");
						int newY = scanner.nextInt();

						//Test if the input is valid if not have the user reenter the value
						valid = Rules.validate(currentX, currentY, newX, newY, myBoard,1);
						while(valid[0] == false){
							System.out.println("Move not valid please choose a valid Move!");
							System.out.println("Please type the x coordinates of the piece you want to move.");
							currentX = scanner.nextInt();

							System.out.println("Please type the y coordinates of the piece you want to move.");
							currentY = scanner.nextInt();

							System.out.println("Please type the new x coordinates of where you want the piece to go.");
							newX = scanner.nextInt();

							System.out.println("Please type the new y coordinates of where you want the piece to go.");
							newY = scanner.nextInt();				

							valid = Rules.validate(currentX, currentY, newX, newY, myBoard,1);
						}

						int[] move = new int[5];
						if(valid[1]==true){
							int[] a = {currentX, currentY, newX, newY, 1};
							move = a;
						}else{
							int[] a = {currentX, currentY, newX, newY, 0};
							move = a;
						}

						Board.doMove(move, myBoard, 0, 5);
						//Print new changed board
						System.out.println(textView(myBoard));

					}

				}else{
					//variables to check for double skip
					int[] move = {0,0,0,0,1};
					System.out.println("Reds up!");
					move = basicAI.makeMove(myBoard);

					//doMove
					Board.doMove(move, myBoard, 7, 2);

					//Print new changed board
					System.out.println(textView(myBoard));

				}

				//update turnKeeper
				myBoard.turnKeeper++;

				//count chips to check if games over
				int[] check = Rules.gameOver(myBoard);
				if(check[0]==1){
					gameOver = true;
				}
			}
		}else if(playerType == 4){
			System.out.println(textView(myBoard));
			while(gameOver == false){
				//Find out and print who's turn it is
				if(myBoard.turnKeeper%2 == 0){
					//variables to check for double skip
					int[] move = {0,0,0,0,1};
					System.out.println("Reds up!");
					move = basicAI.makeMove(myBoard);

					//doMove
					Board.doMove(move, myBoard, 7, 2);

					//Print new changed board
					System.out.println(textView(myBoard));

				}else{
					//variables to check for double skip
					int[] move = {0,0,0,0,1};
					System.out.println("Blacks up!");

					move = AIRandom.makeMove(myBoard);

					//doMove
					Board.doMove(move, myBoard, 0, 1);

					//Print new changed board
					System.out.println(textView(myBoard));

				}

				//update turnKeeper
				myBoard.turnKeeper++;

				//count chips to check if games over
				int[] check = Rules.gameOver(myBoard);
				if(check[0]==1){
					gameOver = true;
				}
			}
		}else if(playerType == 5){
			System.out.println(textView(myBoard));
			while(gameOver == false){
				//Find out and print who's turn it is
				if(myBoard.turnKeeper%2 == 0){
					//variables to check for double skip
					int[] move = {0,0,0,0,1};
					System.out.println("Reds up!");
					move = basicAI.makeMove(myBoard);

					//doMove
					Board.doMove(move, myBoard, 7, 2);

					//Print new changed board
					System.out.println(textView(myBoard));

				}else{
					//variables to check for double skip
					int[] move = {0,0,0,0,1};
					System.out.println("Blacks up!");

					minMaxAI.makeMove(myBoard, 1, 1);
					move = minMaxAI.finalMove;

					//doMove
					Board.doMove(move, myBoard, 0, 3);

					//Print new changed board
					System.out.println(textView(myBoard));

				}

				//update turnKeeper
				myBoard.turnKeeper++;

				//count chips to check if games over
				int[] check = Rules.gameOver(myBoard);
				if(check[0]==1){
					gameOver = true;
				}
			}
		}

	}

	public static String textView(Board board){
		String textBoard = "";
		for(int x = board.rows-1; x>=0; x--){
			for(int y = board.columns-1; y>=0; y--){
				if(board.board[x][y] == null && y == 0){
					textBoard += "|___|"+x;
				}else if(board.board[x][y] == null){
					textBoard += "|___";
				}else if(board.board[x][y].color == 0 && board.board[x][y].isKing == true && y == 0){
					textBoard += "|_R_|"+x;
				}else if(board.board[x][y].color == 0 && y == 0){
					textBoard += "|_r_|"+x;
				}else if(board.board[x][y].color == 0 && board.board[x][y].isKing == true){
					textBoard += "|_R_";
				}else if(board.board[x][y].color == 0){
					textBoard += "|_r_";
				}else if(board.board[x][y].color == 1 && board.board[x][y].isKing == true && y == 0){
					textBoard += "|_B_|"+x;
				}else if(board.board[x][y].color == 1 && y == 0){
					textBoard += "|_b_|"+x;
				}else if( board.board[x][y].color == 1 && board.board[x][y].isKing == true){
					textBoard += "|_B_";
				}else if( board.board[x][y].color == 1){
					textBoard += "|_b_";
				}
			}
			textBoard += "\n";
		}
		textBoard += " 23   22  21  20  19  18  17  16  15  14  13  12  11  10  9   8   7   6   5   4   3   2   1   0";
		return textBoard;
	}


}
