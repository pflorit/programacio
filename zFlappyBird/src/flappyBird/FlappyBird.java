package flappyBird;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;


public class FlappyBird implements ActionListener, MouseListener, KeyListener
{

	public static FlappyBird flappyBird;

	public final int WIDTH = 800, HEIGHT = 800;

	public Renderer renderer;

	public Rectangle bird;

	public ArrayList<Rectangle> columns;

	public int ticks, yMotion, score;

	public boolean gameOver, started;

	public Random rand;

	private BufferedWriter bw;

	private BufferedReader br;

	private static int BestScore;

	private int speed = 10;

	private int space = 300;

	public String nl = System.getProperty("line.separator");

	Menu men = new Menu();

	// Musica del juego
	Audio repro = new Audio();

	// Sonido de paso por columnas 
	Audio reprocol = new Audio();

	// Sonido de muerte
	Audio death = new Audio();

	public void ReproduceAudio() { 
		try {
			repro.AbrirFichero("C:\\Temp\\Sonidos\\tetris.mp3");
			repro.Play();

		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
	}
	public void cambiarDificultad(){
		if (Menu.dificultad == "facil") {
			speed = 10;
			space = 300;
		}else if (Menu.dificultad == "normal") {
			speed = 10;
			space = 250;
		}else if (Menu.dificultad == "dificil") {
			speed = 15 ;
			space = 250;
		}
	}

	public void AudioColumna() {
		try {
			reprocol.AbrirFichero("C:\\Temp\\Sonidos\\Column.wav");
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}

	}

	public void AudioMuerte() {
		try {
			death.AbrirFichero("C:\\Temp\\Sonidos\\golpe.wav");
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

	public FlappyBird()
	{	
		cambiarDificultad();
		ReproduceAudio();
		AudioColumna();
		AudioMuerte();

		JFrame jframe = new JFrame();
		Timer timer = new Timer(20, this);

		renderer = new Renderer();
		rand = new Random();

		jframe.add(renderer);
		jframe.setTitle("Flappy Bird");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(WIDTH, HEIGHT);
		jframe.addMouseListener(this);
		jframe.addKeyListener(this);
		jframe.setResizable(false);
		jframe.setVisible(true);

		bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
		columns = new ArrayList<Rectangle>();

		addColumn(true);
		addColumn(true);
		addColumn(true);
		addColumn(true);

		timer.start();
	}

	public void addColumn(boolean start)
	{
		int width = 100;
		int height = 50 + rand.nextInt(300);

		if (start)
		{
			columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGHT - height - 120, width, height));
			columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
		}
		else
		{
			columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, HEIGHT - height - 120, width, height));
			columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, HEIGHT - height - space));
		}
	}

	public void paintColumn(Graphics g, Rectangle column)
	{
		g.setColor(Color.green.darker());
		g.fillRect(column.x, column.y, column.width, column.height);
	}

	public void jump()
	{
		if (gameOver)
		{
			bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
			columns.clear();
			yMotion = 0;
			score = 0;

			addColumn(true);
			addColumn(true);
			addColumn(true);
			addColumn(true);

			gameOver = false;
		}

		if (!started)
		{
			started = true;
		}
		else if (!gameOver)
		{
			if (yMotion > 0) {
				yMotion = 0;
			}

			yMotion -= 10;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		ticks++;

		if (started)
		{ 

			for (int i = 0; i < columns.size(); i++)
			{
				Rectangle column = columns.get(i);

				column.x -= speed;
			}

			if (ticks % 2 == 0 && yMotion < 15)
			{
				yMotion += 2;
			}

			for (int i = 0; i < columns.size(); i++)
			{
				Rectangle column = columns.get(i);

				if (column.x + column.width < 0)
				{
					columns.remove(column);

					if (column.y == 0)
					{
						addColumn(false);
					}
				}
			}

			bird.y += yMotion;

			for (Rectangle column : columns)
			{	
				if (column.y == 0 && bird.x + bird.width / 2 > column.x + column.width / 2 - 10 && bird.x + bird.width / 2 < column.x + column.width / 2 + 10)
				{
					try {
						reprocol.Play();
					} catch (Exception e1) {
						System.out.println("Error: " + e1.getMessage());
					}
					score++;
				}

				if (column.intersects(bird))
				{
					gameOver = true;

					if (bird.x <= column.x)
					{
						bird.x = column.x - bird.width;

					}
					else
					{
						try {
							reprocol.Stop();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						if (column.y != 0)
						{
							bird.y = column.y - bird.height;
						}
						else if (bird.y < column.height)
						{
							bird.y = column.height;
						}
					}
				}
			}


			if (bird.y > HEIGHT - 120 || bird.y < 0)
			{
				gameOver = true;
			}

			if (bird.y + yMotion >= HEIGHT - 120)
			{
				bird.y = HEIGHT - 120 - bird.height;
				gameOver = true;
			}
		}
		renderer.repaint();
	}

	public void repaint(Graphics g) throws Exception
	{
		GetBestScore();
		g.setColor(Color.cyan);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(Color.orange);
		g.fillRect(0, HEIGHT - 120, WIDTH, 120);

		g.setColor(Color.green);
		g.fillRect(0, HEIGHT - 120, WIDTH, 20);

		g.setColor(Color.red);
		g.fillRect(bird.x, bird.y, bird.width, bird.height);
		for (Rectangle column : columns)
		{
			paintColumn(g, column);
		}

		g.setColor(Color.white);
		g.setFont(new Font("Arial", 1, 100));

		if (!gameOver) {
			repro.Play();
		}

		if (!started)
		{
			g.drawString("Click to start!", 75, HEIGHT / 2 - 50);
		}

		if (gameOver)
		{
			if (score>BestScore) {
				BestScore=score;
				SaveBestScore(BestScore);
			}    
			death.Play();
			g.drawString("Game Over!", 100, HEIGHT / 2 - 50);

			g.drawString("Best Score: " + BestScore, 100, HEIGHT / 2 - 150);
			repro.Stop();

			this.SaveBestScore(BestScore);
		}

		if (!gameOver && started)
		{
			g.drawString(String.valueOf(score), WIDTH / 2 - 25, 100);
		}
		if (!gameOver) {
			if (score>BestScore) {			
				BestScore=score;
			}
		}
	}

	// Guardar Best Score
	public void SaveBestScore(int BestScore) {
		try {
			if (Menu.dificultad == "facil") {
				bw=new BufferedWriter(new FileWriter("C:\\Temp\\EasyScore.txt"));
				bw.write(String.valueOf(BestScore+nl));
				bw.write(Menu.jugador);
			}else if (Menu.dificultad == "normal") {
				bw=new BufferedWriter(new FileWriter("C:\\Temp\\NormalScore.txt"));
				bw.write(String.valueOf(BestScore+nl));
				bw.write(Menu.jugador);
			}else if (Menu.dificultad == "dificil") {
				bw=new BufferedWriter(new FileWriter("C:\\Temp\\HardScore.txt"));
				bw.write(String.valueOf(BestScore+nl));
				bw.write(Menu.jugador);
			}
			bw.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("File can't open");
		} 
	}

	// Obtener best score
	public void GetBestScore() {
		try {
			if (Menu.dificultad == "facil") {
				br=new BufferedReader(new FileReader("C:\\Temp\\EasyScore.txt"));
				BestScore=Integer.parseInt(br.readLine());		
			}else if (Menu.dificultad == "normal") {
				br=new BufferedReader(new FileReader("C:\\Temp\\NormalScore.txt"));
				BestScore=Integer.parseInt(br.readLine());
			}else if (Menu.dificultad == "dificil") {
				br=new BufferedReader(new FileReader("C:\\Temp\\HardScore.txt"));
				BestScore=Integer.parseInt(br.readLine());
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("File can't open");
		} 
	}


	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu.frame.setVisible(true);
					Menu.frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		jump();
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			jump();
		}
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
	}

	@Override
	public void keyTyped(KeyEvent e)
	{

	}

	@Override
	public void keyPressed(KeyEvent e)
	{

	}

}