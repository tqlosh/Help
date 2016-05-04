
public class Rules {

	public static boolean[] validate(int currentX, int currentY, int newX, int newY, Board board, int color){
		boolean[] ans = new boolean[2];
		//Out of Bounds
		if(currentX < 0 || currentY < 0 || currentX >= board.rows || currentY >= board.columns
				|| newX < 0 || newY < 0 || newX >= board.rows || newY >= board.columns){
			//System.out.println("out of bounds");
			ans[0] = false;
			ans[1] = false;
			return ans;
		}

		//Try to use others chip
		if(board.turnKeeper%2 == 0){
			if(board.board[currentX][currentY] == null || board.board[currentX][currentY].color == 1){
				String x = "not null";
				if(board.board[currentX][currentY] == null){
					x = "null";
				}
				//System.out.println("Try to use others chip1 "+x);
				ans[0] = false;
				ans[1] = false;
				return ans;
			}
		}else if(board.turnKeeper%2 == 1){
			if(board.board[currentX][currentY] == null || board.board[currentX][currentY].color == 0){
				//System.out.println("Try to use others chip");
				ans[0] = false;
				ans[1] = false;
				return ans;
			}
		}

		//checks for red piece whos not king to move across bounds 
		if(color == 0){
			//reg move
			if((currentY == 0 && newY == 23 && currentX == newX-1 && board.board[newX][newY] == null)
					||(currentY == 23 && newY == 0 && currentX == newX-1 && board.board[newX][newY] == null)){
				ans[0] = true;
				ans[1] = false;
				return ans;
			}
			//jump move to the right
			if(currentY == 0 && newY == 22 && currentX == newX-2 
					&& board.board[newX][newY] == null && board.board[currentX+1][23] != null
					&& newX < board.rows && newX > 0 && board.board[currentX+1][23].color == 1){
				ans[0] = true;
				ans[1] = true;
				return ans;
			}
			//jump move to the left
			if(currentY == 23 && newY == 1 && currentX == newX-2 
					&& board.board[newX][newY] == null && board.board[currentX+1][0] != null
					&& newX < board.rows && newX > 0 && board.board[currentX+1][0].color == 1){
				ans[0] = true;
				ans[1] = true;
				return ans;
			}
		}

		//checks for red piece whos a king to move across bounds 
		if(color == 0 && board.board[currentX][currentY].isKing == true){
			//reg move
			if((currentY == 0 && newY == 23 && currentX == newX+1 && board.board[newX][newY] == null)
					||(currentY == 23 && newY == 0 && currentX == newX+1 && board.board[newX][newY] == null)){
				ans[0] = true;
				ans[1] = false;
				return ans;
			}
			//jump move to the right
			if(currentY == 0 && newY == 22 && currentX == newX+2 
					&& board.board[newX][newY] == null && board.board[currentX-1][23] != null
					&& newX < board.rows && newX > 0 && board.board[currentX-1][23].color == 1){
				ans[0] = true;
				ans[1] = true;
				return ans;
			}
			//jump move to the left
			if(currentY == 23 && newY == 1 && currentX == newX+2 
					&& board.board[newX][newY] == null && board.board[currentX-1][0] != null
					&& newX < board.rows && newX > 0 && board.board[currentX-1][0].color == 1){
				ans[0] = true;
				ans[1] = true;
				return ans;
			}
		}

		//checks for black piece whos not king to move across bounds 
		if(color == 1){
			//reg move
			if((currentY == 0 && newY == 23 && currentX == newX+1 && board.board[newX][newY] == null)
					||(currentY == 23 && newY == 0 && currentX == newX+1 && board.board[newX][newY] == null)){
				ans[0] = true;
				ans[1] = false;
				return ans;
			}
			//jump move to the right
			if(currentY == 0 && newY == 22 && currentX == newX+2 
					&& board.board[newX][newY] == null && board.board[currentX-1][23] != null
					&& newX < board.rows && newX > 0 && board.board[currentX-1][23].color == 0){
				ans[0] = true;
				ans[1] = true;
				return ans;
			}
			//jump move to the left
			if(currentY == 23 && newY == 1 && currentX == newX+2 
					&& board.board[newX][newY] == null && board.board[currentX-1][0] != null
					&& newX < board.rows && newX > 0 && board.board[currentX-1][0].color == 0){
				ans[0] = true;
				ans[1] = true;
				return ans;
			}
		}

		//checks for black piece whos a king to move across bounds 
		if(color == 1 && board.board[currentX][currentY].isKing == true){
			//reg move
			if((currentY == 0 && newY == 23 && currentX == newX-1 && board.board[newX][newY] == null)
					||(currentY == 23 && newY == 0 && currentX == newX-1 && board.board[newX][newY] == null)){
				ans[0] = true;
				ans[1] = false;
				return ans;
			}
			//jump move to the right
			if(currentY == 0 && newY == 22 && currentX == newX-2 
					&& board.board[newX][newY] == null && board.board[currentX+1][23] != null
					&& newX < board.rows && newX > 0 && board.board[currentX+1][23].color == 0){
				ans[0] = true;
				ans[1] = true;
				return ans;
			}
			//jump move to the left
			if(currentY == 23 && newY == 1 && currentX == newX-2 
					&& board.board[newX][newY] == null && board.board[currentX+1][0] != null
					&& newX < board.rows && newX > 0 && board.board[currentX+1][0].color == 0){
				ans[0] = true;
				ans[1] = true;
				return ans;
			}
		}

		//red piece and not king
		if(color == 0 && board.board[currentX][currentY].isKing == false){
			//regular move
			if(((newX == currentX+1 && newY == currentY-1) || (newX == currentX+1 && newY == currentY+1))
					&& board.board[newX][newY] == null){
				ans[0] = true;
				ans[1] = false;
				return ans;
			}
			//jump move
			if(((newX == currentX+2 && newY == currentY-2) || (newX == currentX+2 && newY == currentY+2))
					&& board.board[newX][newY] == null && board.board[newX-1][newY + (currentY-newY)/2] != null
					&&board.board[newX-1][newY + (currentY-newY)/2].color == 1){
				ans[0] = true;
				ans[1] = true;
				return ans;
			}
		}
		//red piece and is king
		if(color == 0 && board.board[currentX][currentY].isKing == true){
			//regular move
			if(((newX == currentX+1 && newY == currentY-1) || (newX == currentX+1 && newY == currentY+1)
					|| (newX == currentX-1 && newY == currentY-1) || (newX == currentX-1 && newY == currentY+1))
					&& board.board[newX][newY] == null){
				ans[0] = true;
				ans[1] = false;
				return ans;
			}
			//jump move
			if(((newX == currentX+2 && newY == currentY-2) || (newX == currentX+2 && newY == currentY+2)
					|| (newX == currentX-2 && newY == currentY-2) || (newX == currentX-2 && newY == currentY+2))
					&& board.board[newX][newY] == null && board.board[newX + (currentX-newX)/2][newY + (currentY-newY)/2] != null
					&& board.board[newX + (currentX-newX)/2][newY + (currentY-newY)/2].color == 1){
				ans[0] = true;
				ans[1] = true;
				return ans;
			}
		}

		//black piece and not king
		if(color == 1 && board.board[currentX][currentY].isKing == false){
			//reg move
			if(((newX == currentX-1 && newY == currentY-1) || (newX == currentX-1 && newY == currentY+1))
					&& board.board[newX][newY] == null){
				ans[0] = true;
				ans[1] = false;
				return ans;
			}
			//jump move
			if(((newX == currentX-2 && newY == currentY-2) || (newX == currentX-2 && newY == currentY+2))
					&& board.board[newX][newY] == null && board.board[newX+1][newY + (currentY-newY)/2] != null
					&& board.board[newX+1][newY + (currentY-newY)/2].color == 0){
				ans[0] = true;
				ans[1] = true;
				return ans;
			}
		}
		//black piece and is king
		if(color == 1 && board.board[currentX][currentY].isKing == true){
			if(((newX == currentX+1 && newY == currentY-1) || (newX == currentX+1 && newY == currentY+1)
					|| (newX == currentX-1 && newY == currentY-1) || (newX == currentX-1 && newY == currentY+1))
					&& board.board[newX][newY] == null){
				ans[0] = true;
				ans[1] = false;
				return ans;
			}
			if(((newX == currentX+2 && newY == currentY-2) || (newX == currentX+2 && newY == currentY+2)
					|| (newX == currentX-2 && newY == currentY-2) || (newX == currentX-2 && newY == currentY+2))
					&& board.board[newX][newY] == null && board.board[newX + (currentX-newX)/2][newY + (currentY-newY)/2] != null
					&& board.board[newX + (currentX-newX)/2][newY + (currentY-newY)/2].color == 0){
				ans[0] = true;
				ans[1] = true;
				return ans;
			}
		}	

		//System.out.println("Default");
		ans[0] = false;
		ans[1] = false;
		return ans;
	}

	public static int[] gameOver(Board board){
		int redChipCount = 0;
		int blackChipCount = 0;
		int[] ans = new int[2];
		for(int x = board.rows-1; x>=0; x--){
			for(int y = board.columns-1; y>=0; y--){
				if(board.board[x][y] != null && board.board[x][y].color == 0){
					redChipCount += 1;
				}
				if(board.board[x][y] != null && board.board[x][y].color == 1){
					blackChipCount += 1;
				}
			}
		}
		if(redChipCount == 0){
			ans[0]= 1;
			ans[1]= 0;
			System.out.println("Black Wins!");
		}
		if(blackChipCount == 0){
			ans[0]= 1;
			ans[1]= 1;
			System.out.println("Red Wins");
		}
		return ans;
	}


}
