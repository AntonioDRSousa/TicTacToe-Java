import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.Rectangle;
import java.awt.Polygon;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 
import java.util.*;
import java.util.ArrayList;
import java.lang.Math.*;

public class Paint extends JPanel{
	public static JFrame tela = Tela.tela;//tela do jogo
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);
		
		g2.setColor(Color.CYAN);
		g2.fillRect(0,0,700,700);//define a cor do background do jogo como ciano
		
		Board.createBoard(g2);//cria tabuleiro
		Board.drawBoard(g2);
		
		repaint();
	}
}
