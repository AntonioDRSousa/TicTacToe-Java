import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 
import java.util.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class Game{
	private static int ordem[]=new int[2];//indica a ordem dos jogadores
	private static char jogadores[]=new char[2];//indica o simbolo dos jogadores
	private static boolean ai;
	private static int vez=0;
	
	private static ComputerAI computer;

	public void jogo(){
		Point casa=new Point(-1,-1);

		//jlabel of information
		JLabel information=new JLabel();
		information.setHorizontalAlignment(SwingConstants.CENTER);
		information.setFont(new Font("Courier New",Font.BOLD,30));
		information.setOpaque(true);
		information.setForeground(Color.BLACK);
		information.setBackground(Color.GREEN);
		information.setBounds(100,50,500,50);
		Tela.tela.add(information);

		JButton back=new JButton("Back Menu");
		back.setHorizontalAlignment(SwingConstants.CENTER);
		back.setFont(new Font("Courier New",Font.BOLD,30));
		back.setOpaque(true);
		back.setForeground(Color.BLACK);
		back.setBackground(Color.GREEN);
		back.setBounds(100,600,500,50);
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				vez=0;
				Tela.tela.getContentPane().removeAll();
				Tela.tela.revalidate();
				Tela.tela.repaint();

				new BackMenu().execute();
			}
		});
		Tela.tela.add(back);
		
		while(true){

			information.setText("Player "+(ordem[vez]+1)+" Turn");
		
			Tela.initClick();
			Tela.tela.revalidate();
			Tela.tela.repaint();
			
			while(true){
					Tela.tela.revalidate();
					Tela.tela.repaint();
					casa=Board.getCasa(Tela.click);
					
					if(casa.getX()!=-1 && casa.getY()!=-1){
						if(Board.getPice(casa.getX(),casa.getY())=='s'){
							Board.putPice(jogadores[ordem[vez]],casa.getX(),casa.getY());
							break;
						}
					}
					try{Thread.sleep(100);}catch(Exception erro){}//permite tempo para pintar a tela				
			}
			
			if(checkVictory(information)){
				break;
			}	
			vez = (vez+1)%2;
			
			if(ai){
				computer = new ComputerAI(Board.boardGame);
				int[] pos = computer.AI(jogadores[ordem[vez]]);
				Board.putPice(jogadores[ordem[vez]],pos[0],pos[1]);
				
				if(checkVictory(information)){
					break;
				}
				vez = (vez+1)%2;
			}
			
		}
	}
	
	public boolean checkVictory(JLabel information){
		int vic=victory();
		if(vic!=-1){
			if(vic==0){
				information.setText("Draw");
			}
			else{
				information.setText("Player "+vic+" Win");
			}
			return true;
		}
		return false;
	}

	public class BackMenu extends SwingWorker{
		public Object doInBackground()throws Exception{
			new Begin().initTela();	
			return null;
		}
	}

	public static int victory(){
	
		//check lines
		for(int i=0;i<3;i++){
			if((Board.getPice(i,0)==Board.getPice(i,1))&&(Board.getPice(i,1)==Board.getPice(i,2))){
				if(Board.getPice(i,0)==jogadores[0]){
					return 1;
				}
				else if(Board.getPice(i,0)==jogadores[1]){
					return 2;
				}
			}
		}

		//check coluns
		for(int i=0;i<3;i++){
			if((Board.getPice(0,i)==Board.getPice(1,i))&&(Board.getPice(1,i)==Board.getPice(2,i))){
				if(Board.getPice(0,i)==jogadores[0]){
					return 1;
				}
				else if(Board.getPice(0,i)==jogadores[1]){
					return 2;
				}
			}
		}
		
		//check principal diagonal
		if((Board.getPice(0,0)==Board.getPice(1,1))&&(Board.getPice(1,1)==Board.getPice(2,2))){
			if(Board.getPice(0,0)==jogadores[0]){
				return 1;
			}
			else if(Board.getPice(0,0)==jogadores[1]){
				return 2;
			}
		}
		
		//check secundaire diagonal
		if((Board.getPice(0,2)==Board.getPice(1,1))&&(Board.getPice(1,1)==Board.getPice(2,0))){
			if(Board.getPice(1,1)==jogadores[0]){
				return 1;
			}
			else if(Board.getPice(1,1)==jogadores[1]){
				return 2;
			}
		}
	
		//check draw
		int qtd=0;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(Board.getPice(i,j)!='s'){
					qtd++;
				}
			}
		}
		if(qtd==9){
			return 0;
		}

		//not finish
		return -1;
		
	}

	public static void setOrdem(int i){
		if(i==2){//ordem aleatoria
			if(((int)(Math.random()*2)+1)==1){
				ordem[0]=0;
				ordem[1]=1;
			}
			else{
				ordem[0]=1;
				ordem[1]=0;
			}
		}
		else{//ordem definida
			ordem[0]=i;
			if(i==0){
				ordem[1]=1;
			}
			else{
				ordem[1]=0;
			}
		}
	}

	public static void setSymbols(int c){
		if(c==0){
			jogadores[0]='x';
			jogadores[1]='o';
		}
		else{
			jogadores[0]='o';
			jogadores[1]='x';
		}
	}

	public static void setAI(boolean b){
		ai=b;
	}
}
