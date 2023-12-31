import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 
import java.util.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class Begin{
	public static JFrame tela;
	public static JPanel initialPanel=new JPanel();
	
	private static String[] types=new String[2];//types of player: human or computer
	private static String[] symbols=new String[2];//symbols: X or O
	private static String[] opFirst=new String[3];//option of first player
	
	private static int nTypes;
	private static int nSymbols;
	private static int nOpFirst;
	
	private	static JLabel typePlayer2=new JLabel("Human");
	private	static JLabel symbolPlayer1=new JLabel("X");
	private	static JLabel first=new JLabel("Player 1");

	private static void initNames(){
		types[0]="Human";
		types[1]="Computer";
		symbols[0]="X";
		symbols[1]="O";
		opFirst[0]="Player 1";
		opFirst[1]="Player 2";
		opFirst[2]="Random";
		nTypes=0;
		nSymbols=0;
		nOpFirst=0;
	}

	private static void changeName(char c,char i){// c='t' -> nTypes  c='s' -> nSymbols c='o' -> nOpFirst i='+' -> increment i='-' -> decrement
		if(c=='t'){
			if(i=='+'){
				nTypes++;
			}
			else if(i=='-'){
				nTypes--;
			}
			
			if(nTypes==2){
				nTypes=0;
			}
			else if(nTypes==(-1)){
				nTypes=1;
			}
				
		}
		else if(c=='s'){
			if(i=='+'){
				nSymbols++;
			}
			else if(i=='-'){
				nSymbols--;
			}
			
			if(nSymbols==2){
				nSymbols=0;
			}
			else if(nSymbols==(-1)){
				nSymbols=1;
			}
		}
		else if(c=='o'){
			if(i=='+'){
				nOpFirst++;
			}
			else if(i=='-'){
				nOpFirst--;
			}
			
			if(nOpFirst==3){
				nOpFirst=0;
			}
			else if(nOpFirst==(-1)){
				nOpFirst=2;
			}
		}
	}
	
	private static void putOptions(){
		typePlayer2.setHorizontalAlignment(SwingConstants.CENTER);
		typePlayer2.setFont(new Font("Courier New",Font.BOLD,30));
		typePlayer2.setOpaque(true);
		typePlayer2.setForeground(Color.BLUE);
		typePlayer2.setBackground(Color.CYAN);
		typePlayer2.setBounds(400,300,250,100);
	
		symbolPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
		symbolPlayer1.setFont(new Font("Courier New",Font.BOLD,30));
		symbolPlayer1.setOpaque(true);
		symbolPlayer1.setForeground(Color.BLUE);
		symbolPlayer1.setBackground(Color.CYAN);
		symbolPlayer1.setBounds(400,400,250,100);

		first.setHorizontalAlignment(SwingConstants.CENTER);
		first.setFont(new Font("Courier New",Font.BOLD,30));
		first.setOpaque(true);
		first.setForeground(Color.BLUE);
		first.setBackground(Color.CYAN);
		first.setBounds(400,500,250,100);

		initialPanel.add(typePlayer2);
		initialPanel.add(symbolPlayer1);
		initialPanel.add(first);
	}

	private static void putDescriptionLabels(){
		JLabel l1=new JLabel("TIC TAC TOE");
		JLabel l2=new JLabel("Player 2");
		JLabel l3=new JLabel("Symbol");
		JLabel l4=new JLabel("First To Play");
		
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setFont(new Font("Courier New",Font.BOLD,100));
		l1.setOpaque(true);
		l1.setForeground(Color.BLUE);
		l1.setBackground(Color.CYAN);
		l1.setBounds(0,0,700,300);
		
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		l2.setFont(new Font("Courier New",Font.BOLD,30));
		l2.setOpaque(true);
		l2.setForeground(Color.BLUE);
		l2.setBackground(Color.CYAN);
		l2.setBounds(0,300,350,100);

		l3.setHorizontalAlignment(SwingConstants.CENTER);
		l3.setFont(new Font("Courier New",Font.BOLD,30));
		l3.setOpaque(true);
		l3.setForeground(Color.BLUE);
		l3.setBackground(Color.CYAN);
		l3.setBounds(0,400,350,100);

		l4.setHorizontalAlignment(SwingConstants.CENTER);
		l4.setFont(new Font("Courier New",Font.BOLD,30));
		l4.setOpaque(true);
		l4.setForeground(Color.BLUE);
		l4.setBackground(Color.CYAN);
		l4.setBounds(0,500,350,100);

		initialPanel.add(l1);
		initialPanel.add(l2);
		initialPanel.add(l3);
		initialPanel.add(l4);
	}	

	private static void putButtonsLR(){
		JButton butTypeLeft=new JButton("<");
		JButton butTypeRight=new JButton(">");
		JButton butSymbolLeft=new JButton("<");
		JButton butSymbolRight=new JButton(">");
		JButton butFirstLeft=new JButton("<");
		JButton butFirstRight=new JButton(">");


		butTypeLeft.setHorizontalAlignment(SwingConstants.CENTER);
		butTypeLeft.setFont(new Font("Courier New",Font.BOLD,20));
		butTypeLeft.setOpaque(true);
		butTypeLeft.setForeground(Color.RED);
		butTypeLeft.setBackground(Color.BLUE);
		butTypeLeft.setBounds(350,300,50,100);
		butTypeLeft.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				changeName('t','-');
				typePlayer2.setText(types[nTypes]);
				Game.setAI((types[nTypes]).equals("Computer"));
			}
		});
		
		butTypeRight.setHorizontalAlignment(SwingConstants.CENTER);
		butTypeRight.setFont(new Font("Courier New",Font.BOLD,20));
		butTypeRight.setOpaque(true);
		butTypeRight.setForeground(Color.RED);
		butTypeRight.setBackground(Color.BLUE);
		butTypeRight.setBounds(650,300,50,100);
		butTypeRight.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				changeName('t','+');
				typePlayer2.setText(types[nTypes]);
				Game.setAI((types[nTypes]).equals("Computer"));
			}
		});

		butSymbolLeft.setHorizontalAlignment(SwingConstants.CENTER);
		butSymbolLeft.setFont(new Font("Courier New",Font.BOLD,20));
		butSymbolLeft.setOpaque(true);
		butSymbolLeft.setForeground(Color.RED);
		butSymbolLeft.setBackground(Color.BLUE);
		butSymbolLeft.setBounds(350,400,50,100);
		butSymbolLeft.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				changeName('s','-');
				symbolPlayer1.setText(symbols[nSymbols]);
				Game.setSymbols(nSymbols);
			}
		});
		
		butSymbolRight.setHorizontalAlignment(SwingConstants.CENTER);
		butSymbolRight.setFont(new Font("Courier New",Font.BOLD,20));
		butSymbolRight.setOpaque(true);
		butSymbolRight.setForeground(Color.RED);
		butSymbolRight.setBackground(Color.BLUE);
		butSymbolRight.setBounds(650,400,50,100);
		butSymbolRight.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				changeName('s','+');
				symbolPlayer1.setText(symbols[nSymbols]);
				Game.setSymbols(nSymbols);
			}
		});

		butFirstLeft.setHorizontalAlignment(SwingConstants.CENTER);
		butFirstLeft.setFont(new Font("Courier New",Font.BOLD,20));
		butFirstLeft.setOpaque(true);
		butFirstLeft.setForeground(Color.RED);
		butFirstLeft.setBackground(Color.BLUE);
		butFirstLeft.setBounds(350,500,50,100);
		butFirstLeft.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				changeName('o','-');
				first.setText(opFirst[nOpFirst]);
				Game.setOrdem(nOpFirst);
			}
		});
		
		butFirstRight.setHorizontalAlignment(SwingConstants.CENTER);
		butFirstRight.setFont(new Font("Courier New",Font.BOLD,20));
		butFirstRight.setOpaque(true);
		butFirstRight.setForeground(Color.RED);
		butFirstRight.setBackground(Color.BLUE);
		butFirstRight.setBounds(650,500,50,100);
		butFirstRight.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				changeName('o','+');
				first.setText(opFirst[nOpFirst]);
				Game.setOrdem(nOpFirst);
			}
		});

		initialPanel.add(butTypeLeft);
		initialPanel.add(butTypeRight);
		initialPanel.add(butSymbolLeft);
		initialPanel.add(butSymbolRight);
		initialPanel.add(butFirstLeft);
		initialPanel.add(butFirstRight);
	}

	private void putPrincipalButtons(){
		JButton playButton=new JButton("PLAY");
		JButton quitButton=new JButton("QUIT");
		
		playButton.setHorizontalAlignment(SwingConstants.CENTER);
		playButton.setFont(new Font("Courier New",Font.BOLD,20));
		playButton.setOpaque(true);
		playButton.setForeground(Color.RED);
		playButton.setBackground(Color.BLUE);
		playButton.setBounds(450,625,150,25);

		quitButton.setHorizontalAlignment(SwingConstants.CENTER);
		quitButton.setFont(new Font("Courier New",Font.BOLD,20));
		quitButton.setOpaque(true);
		quitButton.setForeground(Color.RED);
		quitButton.setBackground(Color.BLUE);
		quitButton.setBounds(150,625,150,25);

		playButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				tela.getContentPane().removeAll();
				tela.revalidate();
				tela.repaint();

				new SW().execute();
			}
		});

		quitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});

		initialPanel.add(playButton);
		initialPanel.add(quitButton);

		initialPanel.revalidate();
		initialPanel.repaint();
	}

	public class SW extends SwingWorker{
		public Object doInBackground()throws Exception{
			Tela.p1.setBounds(100,100,500,500);
			tela.add(Tela.p1);
			
			tela.revalidate();
			tela.repaint();
			Board.initBoard();
			Board.initCoord();
			new Game().jogo();
			
			return null;
		}
	}

	public class components extends SwingWorker{
		public Object doInBackground()throws Exception{
			putDescriptionLabels();
			putOptions();
			putButtonsLR();
			putPrincipalButtons();
			return null;
		}
	}
			

	public void initTela(){
		initialPanel.setVisible(true);
		
		tela=Tela.tela;
		tela.setLayout(null);
		initialPanel.setBounds(0,0,700,700);
		initialPanel.setLayout(null);
		initialPanel.setBackground(Color.CYAN);
		tela.add(initialPanel);

		Game.setOrdem(0);
		Game.setSymbols(0);
		
		initNames();

		new components().execute();

	}
}
