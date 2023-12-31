import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 
import java.util.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class Tela{
	public static JFrame tela;
	public static Point click=new Point(-1,-1);
	public static Paint p1 = new Paint();
	
	public Tela(){
		tela=new JFrame();

		tela.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				click.setX(e.getX());
				click.setY(e.getY());
		    }

 		});
 		
		editarJanela();	
		new Begin().initTela();	
	}

	public static void initClick(){//para indicar resetar o click e desse modo impedir com que fique guardado o click do turno anterior
		click.setX(-1);
		click.setY(-1);
	}
	
	public static void editarJanela(){
		tela.getContentPane().setBackground(Color.BLACK);
		tela.setTitle("TIC TOC");
		tela.setSize(700,700);
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tela.setLocationRelativeTo(null);
		tela.setResizable(false);
		tela.setVisible(true);
		
	}
}
