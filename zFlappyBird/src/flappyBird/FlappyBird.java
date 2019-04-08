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
import javax.swing.ImageIcon;
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

	public JFrame jframe = new JFrame();

	public boolean cheater = false;

	Menu men = new Menu();

	// Musica del juego
	Audio repro = new Audio();

	// Sonido de paso por columnas 
	Audio reprocol = new Audio();

	// Sonido de muerte
	Audio death = new Audio();

	public void ReproduceAudio() { 
		try {
			if (Menu.jugador.equals("undertale") || Menu.jugador.equals("Undertale")) {
				repro.AbrirFichero("C:\\Temp\\Sonidos\\undertale.wav");
			} else {
				repro.AbrirFichero("C:\\Temp\\Sonidos\\tetris.mp3");
				repro.Play();
			}	
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
	}
	public void cambiarDificultad(){
		if (Menu.dificultad == "facil") {
			speed = 10;
			space = 350;
		}else if (Menu.dificultad == "normal") {
			speed = 10;
			space = 280;
		}else if (Menu.dificultad == "dificil") {
			speed = 15 ;
			space = 300;
		}else if (Menu.dificultad == "Invertido") {
			speed = 10;
			space = 280;
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

		if (Menu.jugador.equals("cide") || Menu.jugador.equals("Cide") || Menu.jugador.equals("CIDE")) {
			bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 75, 75);
		} else {
			bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 50, 50);
		}
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
		if (Menu.jugador.equals("undertale") || Menu.jugador.equals("Undertale")) {
			ImageIcon tub = new ImageIcon(new ImageIcon(getClass().getResource("/flappyBird/hueso.png")).getImage());
			g.drawImage(tub.getImage(), column.x, column.y, column.width, column.height, null);

		} else {
			ImageIcon tub = new ImageIcon(new ImageIcon(getClass().getResource("/flappyBird/Tub.JPG")).getImage());
			g.drawImage(tub.getImage(), column.x, column.y, column.width, column.height, null);

		}

	}

	public void jump()
	{
		if (gameOver)
		{
			if (Menu.jugador.equals("cide") || Menu.jugador.equals("Cide") || Menu.jugador.equals("CIDE")) {
				bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 75, 75);
			} else {
				bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 50, 50);
			}
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

			// Sirve para la puntuacion, por error de sumar 2
			int a;
			int b;

			for (Rectangle column : columns)
			{	
				if (Menu.dificultad == "dificil") {
					a = 5;
					b = 10;
				} else {
					a = 10;
					b = 5;
				}

				if (column.y == 0 && bird.x + bird.width / 2 > column.x + column.width / 2 - a && bird.x + bird.width / 2 < column.x + column.width / 2 + b)
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

	// Pinta el fondo de pantalla + el pajaro
	public void pintaFondo(Graphics g) throws Exception{
		if (Menu.jugador.equals("undertale") || Menu.jugador.equals("Undertale")) {
			// Pinta el fondo de pantalla
			ImageIcon imagen = new ImageIcon(new ImageIcon(getClass().getResource("/flappyBird/fondo-und.png")).getImage());
			g.drawImage(imagen.getImage(), 0,0,WIDTH,HEIGHT - 20, null);

			// Pinta pajaro
			ImageIcon p = new ImageIcon(new ImageIcon(getClass().getResource("/flappyBird/undertale.png")).getImage());
			g.drawImage(p.getImage(), bird.x, bird.y, bird.width, bird.height, null);
			
			// Pinta el suelo
			g.setColor(Color.BLACK);
			g.fillRect(0, HEIGHT - 120, WIDTH, 140);

		} else if (Menu.jugador.equals("cide") || Menu.jugador.equals("Cide") || Menu.jugador.equals("CIDE")) {
			// Pinta el fondo de pantalla
			ImageIcon imagen = new ImageIcon(new ImageIcon(getClass().getResource("/flappyBird/CIDE.jpg")).getImage());
			g.drawImage(imagen.getImage(), 0,0,WIDTH,HEIGHT - 20, null);

			// Pinta pajaro
			ImageIcon p = new ImageIcon(new ImageIcon(getClass().getResource("/flappyBird/super.png")).getImage());
			g.drawImage(p.getImage(), bird.x, bird.y, bird.width, bird.height, null);	
		
			// Pinta el suelo
			g.setColor(Color.GREEN.darker());
			g.fillRect(0, HEIGHT - 120, WIDTH, 140);

		} else {
			// Pinta el fondo de pantalla
			ImageIcon imagen = new ImageIcon(new ImageIcon(getClass().getResource("/flappyBird/fondo.jpg")).getImage());
			g.drawImage(imagen.getImage(), 0,0,WIDTH,HEIGHT - 20, null);

			// Pinta pajaro
			ImageIcon p = new ImageIcon(new ImageIcon(getClass().getResource("/flappyBird/pajaroFly.gif")).getImage());
			g.drawImage(p.getImage(), bird.x, bird.y, bird.width, bird.height, null);	
		}
	}


	public void repaint(Graphics g) throws Exception
	{
		GetBestScore();

		pintaFondo(g);

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

			g.drawString("Best Score: " + BestScore, 46, HEIGHT / 2 - 150);
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
			if (cheater == false) {
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
				}else if (Menu.dificultad == "Invertido") {
					bw=new BufferedWriter(new FileWriter("C:\\Temp\\InvertidoScore.txt"));
					bw.write(String.valueOf(BestScore+nl));
					bw.write(Menu.jugador);
				}else {
					cheater = false;
				}
				bw.close();
			}
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
			}else if (Menu.dificultad == "Invertido") {
				br=new BufferedReader(new FileReader("C:\\Temp\\InvertidoScore.txt"));
				BestScore=Integer.parseInt(br.readLine());
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("File can't open");
		} 
	}

	public void volvreAlMenu() {
		Menu.frame.setVisible(true);
		Menu.frame.setResizable(false);
		jframe.setVisible(false);
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
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			volvreAlMenu();
			try {
				repro.Stop();
				death.Stop();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_ALT) {
			cheater=true;
			score += 10; 
		}

		if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_C) {
			if (space <= 1000) {
				space += 100;
			}
		}

	}

}