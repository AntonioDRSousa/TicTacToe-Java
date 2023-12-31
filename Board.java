import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.Rectangle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 
import java.util.*;
import java.util.ArrayList;
import java.lang.Math.*;
import java.io.*;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Board{
	private static int posicaoTab = 100;//indica posicao do tabuleiro inicial
	private static int square = 100;//indica tamanho de um quadrado
	private static int widthShape=10;//indica tamanho da linha do tabuleiro
	public static char boardGame[][]=new char[3][3];//representacao abstrata do tabuleiro
	private static Point coord[][]=new Point[3][3];

	public static void createBoard(Graphics2D g){
		int start;
		g.setColor(Color.BLACK);
		for(int i=0;i<2;i++){
			start=posicaoTab+(square*(i+1))+(widthShape*i);
			g.fillRect(start,posicaoTab,widthShape,(square*3)+(widthShape*2));
			g.fillRect(posicaoTab,start,(square*3)+(widthShape*2),widthShape);
		}
	}

	public static void initBoard(){
		int i,j;
		for(i=0;i<3;i++){
			for(j=0;j<3;j++){
				boardGame[i][j]='s';
			}
		}
	}

	public static void initCoord(){
		int i,j;
		for(i=0;i<3;i++){
			for(j=0;j<3;j++){
				coord[i][j]=new Point(posicaoTab+i*(square+widthShape),posicaoTab+j*(square+widthShape));
			}
		}

	}

	public static void putPice(char c,int x,int y){
		boardGame[x][y]=c;
	}

	public static char getPice(int x,int y){
		return boardGame[x][y];
	}

	public static void drawX(Graphics2D g,int x,int y){
		Polygon p=new Polygon();
		
		p.addPoint(coord[x][y].getX()+widthShape,coord[x][y].getY()+2*widthShape);
		p.addPoint(coord[x][y].getX()+square-2*widthShape,coord[x][y].getY()+square-widthShape);
		p.addPoint(coord[x][y].getX()+square-widthShape,coord[x][y].getY()+square-2*widthShape);
		p.addPoint(coord[x][y].getX()+2*widthShape,coord[x][y].getY()+widthShape);
		
		g.setColor(Color.RED);
		g.fillPolygon(p);
		
		p=new Polygon();
		
		p.addPoint(coord[x][y].getX()+widthShape,coord[x][y].getY()+square-2*widthShape);
		p.addPoint(coord[x][y].getX()+2*widthShape,coord[x][y].getY()+square-widthShape);
		p.addPoint(coord[x][y].getX()+square-widthShape,coord[x][y].getY()+2*widthShape);
		p.addPoint(coord[x][y].getX()+square-2*widthShape,coord[x][y].getY()+widthShape);
		
		g.fillPolygon(p);
	}

	public static void drawO(Graphics2D g,int x,int y){
		int grossura=10;//grossura do anel
		Ellipse2D externo=new Ellipse2D.Double(coord[x][y].getX()+widthShape,coord[x][y].getY()+widthShape,square-2*widthShape,square-2*widthShape);
		Ellipse2D interno=new Ellipse2D.Double(coord[x][y].getX()+widthShape+grossura,coord[x][y].getY()+widthShape+grossura,square-2*widthShape-2*grossura,square-2*widthShape-2*grossura);
		g.setColor(Color.BLUE);
		Area area=new Area(externo);
		area.subtract(new Area(interno));
		Shape anel=area;
		g.fill(anel);
	}
		
	public static void drawBoard(Graphics2D g){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(boardGame[i][j]=='x'){
					drawX(g,i,j);
				}
				else if(boardGame[i][j]=='o'){
					drawO(g,i,j);
				}
			}
		}
	}

	public static Point getCasa(Point p1){
		int x=-1;
		int y=-1;

		p1.setX(p1.getX()-posicaoTab);
		p1.setY(p1.getY()-posicaoTab);
		
		for(int i=0;i<=2;i++){
			if((p1.getX()>=coord[i][0].getX()) && (p1.getX()<=coord[i][0].getX()+square)){
				x=i;
			}
		}
		for(int i=0;i<=2;i++){
			if((p1.getY()>=coord[0][i].getY()) && (p1.getY()<=coord[0][i].getY()+square)){
				y=i;
			}
		}
		Point p2=new Point(x,y);
		return p2;
	}
}
		
	
