package ttt;

import java.io.Console;

public class Katse1 {
	// rida ,veerg
	//player 1 1==X  player 2 -1==O
	static int state[][]=new int[3][3];
	static Console con=System.console();
	
	public static void main(String[] args) {
		//joonistada 3x3 m�ngulaud
		//s�ilitada seis, mis on tekkinud
		//on vaja tuvastada peale igat k�iku, kas on saavutatud v�it
		//v�i on siis kui on koos kolm �hesugust s�mbolit m��da sirget
		//tuleb kontrollida, kas m�ng sai lihtsalt l�bi, k�ik v�ljad t�idetud
		//k�sida m�ngija k�iku

		
		game();
	
	}
	
	static int getCodeInPos(int pos){
		int v=pos % 3;
		int r=(pos-v) / 3;
		return state[r][v];
	}
	
	static void setCodeInPos(int pos,int code){
		int v=pos % 3;
		int r=(pos-v) / 3;
		state[r][v]=code;
	}
	
	static void askMove(int player){
		
		while (true){
			System.out.println(player);
			String move=System.console().readLine("%s move (0-8): ", player);
			try{
				int pos =Integer.parseInt(move);
				if (pos<0 || pos>8)
					System.out.println("Vale positsioon");
				else{
					if (getCodeInPos(pos)==0){
						setCodeInPos(pos, player);
						return;
					} else {
						System.out.println("Positsioon ei ole vaba");
					}
				}
				
			}catch (Exception ex){
				System.out.println("Vigane sisend");
			}
		}
	}
	
	static String codeToString(int code){
		if (code==1) return "X";
		if (code==-1) return "O";
		return " ";
			
	}
	
	static String rowToString(int row){
		String rowtext=codeToString(state[row][0]);
		rowtext=rowtext + "|";
		rowtext=rowtext + codeToString(state[row][1]);
		rowtext=rowtext + "|";
		rowtext=rowtext + codeToString(state[row][2]);
		return rowtext;
	}
	
	static void drawBoard(){
		System.out.println();
		System.out.println(rowToString(0));
		System.out.println("-+-+-");
		System.out.println(rowToString(1));
		System.out.println("-+-+-");
		System.out.println(rowToString(2));
		
	}
	
	static boolean isWin(int player){
		//true or false - boolean
		boolean allSame;
		for(int r=0;r<3;r=r+1){
			allSame=true;
			for(int v=0;v<3;v++){
				if (state[r][v]!=player)
					allSame=false;
			}
			if (allSame)
				return true;
		}
		
		for(int v=0;v<3;v++){
			allSame=true;
			for(int r=0;r<3;r=r+1){
				if (state[r][v]!=player)
					allSame=false;
			}
			if (allSame)
				return true;
		}
		
		allSame=true;
		for(int n=0;n<3;n++){
			if (state[n][n]!=player)
				allSame=false;
		}
		if (allSame)
			return true;
		
		allSame=true;
		for(int n=0;n<3;n++){
			if (state[2-n][n]!=player)
				allSame=false;
		}
		if (allSame)
			return true;
		
		return false;
	}
	
	static boolean isGameOver(){
		for(int r=0;r<3;r=r+1){
			for(int v=0;v<3;v++){
				if (state[r][v]==0)
					return false;
			}
		}
		return true;
	}
	
	static void game(){
		//for(enne ts�klit;tingimus kaua seda teha;iga korra l�pus)
		for(int r=0;r<3;r=r+1){
			for(int v=0;v<3;v++){
				state[r][v]=0;
			}
		}
		int player=1;
		drawBoard();
		while (true){
			askMove(player);
			drawBoard();
			//kas on võit
			if (isWin(player)){
				System.out.println(player + " on võitnud");
				return;
			}
			//kas on lõpp
			if (isGameOver()){
				System.out.println("viik");
				return;
			}
			//teine mängija
			player=player *(-1);
		}


		
	}

}
