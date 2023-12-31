import java.io.*;
import java.lang.Math;
import java.util.ArrayList;

public class ComputerAI{
	private char board[][]=new char[3][3];
	private int val = 0;
	
	public ComputerAI(char[][] b){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				board[i][j] = b[i][j];
			}
		}
	}
	
	public int min(int x, int y){
		if(x<=y){
			return x;
		}
		else{
			return y;
		}
	}
	
	/*
		The AI work with values from 1 to 7.
		How great value are a square more bad is that square.
		That is, the AI work with priority moves, calculated by this method,
		that return a movement with minimum value.
		To make play interesting, the AI choose randomly the choosen move 
		in a list of moves with minimum value.
		
		Let's explain basic concepts: 
		
		A fork are a move that in next move you can win in two ways.
		For example, supose board:
		
		 X | X | 
		-----------
		   |   | O 
		-----------
		   | O |    
		   
		This move of player X is a fork.
		
		 X | X | 
		-----------
		 X |   | O 
		-----------
		   | O |    
		
		Note that a fork permit win in two different ways, 
		and because of this there's defense against this move.
		
		Let call the types of square:
		
		 C | B | C
		-----------
		 B | T | B 
		-----------
		 C | B | C   
		 
		 C - corner square
		 T - center square
		 B - border square
		
		VALUES
		
		1 - to square that computer win;
		2 - to square that player win;
		3 - to square that computer  make a fork;
		4 - to square that player make fork;
		5 - to center square
		6 - to corner square
		7 - to border square
		
		
	*/
	public int[] AI(char computer){
		int moves[][] = new int[9][3]; 
		int nmoves = 0;
		
		// represent the absolute values of squares
		int t_square[][] = {{6,7,6},{7,5,7},{6,7,6}};
		
		
		// create array of possible moves
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if (board[i][j]=='s'){
					moves[nmoves][0] = i;
					moves[nmoves][1] = j;
					moves[nmoves][2] = t_square[i][j];
					nmoves++;
				}
			}
		}
		
		char player;
		if(computer=='x'){
			player='o';
		}
		else{
			player='x';
		}
		char[] v = new char[]{computer,player};
		
		
		/*first loop: 
		bl = 0 examine value 1 and 2
		bl = 1 examine value 3 and 4
		that is,
		bl = 0 examine victory of computer and victory of player
		bl = 1 examine conditions of fork for computer and for player
		computer and player are defined by variable k in second loop
		*/
		for(int bl=0;bl<2;bl++){
			// seconde loop: k = 0 indicate computer, k = 1 indicate player
			for(int k=0;k<2;k++){
				// third loop: check all possible moves
				for(int i=0;i<nmoves;i++){

					char chr = v[k];
					int l = 0;
					int c = 0;
					int d1 = 0;
					int d2 = 0;
					
					/* check how many pieces of a certain type 
					are in respective row, column and diagonals of the square of move*/
					for(int j=0;j<3;j++){
						// rows and columns
						if(board[moves[i][0]][j]==chr){
							l++;
						}
						if(board[j][moves[i][1]]==chr){
							c++;
						}
						
						// diagonals
						boolean b = (moves[i][0]==moves[i][1]);
						int calc = (moves[i][0]+moves[i][1]);
						if((board[j][j]==chr)&&b){
							d1++;
						}
						if((board[j][2-j]==chr)&&(calc==2)){
							d2++;
						}
					}
										
					if(bl==0){
						if((l==2)||(c==2)||(d1==2)||(d2==2)){
							moves[i][2]=min(moves[i][2],k+1);
						}
					}
					else{
						int qtd = 0;
						if(l>=1){
							qtd++;
						}
						if(c>=1){
							qtd++;
						}
						if(d1>=1){
							qtd++;
						}
						if(d2>=1){
							qtd++;
						}
						
						if(qtd>=2){
							moves[i][2]=min(moves[i][2],k+3);
						}
					}
				}
			}
		}
		
		
		ArrayList<Integer> good_moves = new ArrayList<Integer>();
		
		// select minimum value moves
		int m = 10;
		for(int i=0;i<nmoves;i++){
			if(moves[i][2]<m){
				m=moves[i][2];
			}
		}
		
		for(int i=0;i<nmoves;i++){
			if(moves[i][2]==m){
				good_moves.add(i);
			}
		}
		
		// choose randomly move
		int r = (int) (Math.random()*good_moves.size());
		int[] pos = {moves[good_moves.get(r)][0],moves[good_moves.get(r)][1]};
		
		return pos;
	}
}