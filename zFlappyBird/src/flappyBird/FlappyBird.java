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

	/**
	 * Metodo para reproducir el audio
	 */
	public void ReproduceAudio() { 
		try {
			//en caso de que como nombre pongas "undertale" la musica sera diferente.
			if (Menu.jugador.equals("undertale") || Menu.jugador.equals("Undertale")) {
				repro.AbrirFichero("C:\\Temp\\Sonidos\\undertale.wav");
				//emppieza la musica
				repro.Play();
			//musica por defecto
			} else {
				repro.AbrirFichero("C:\\Temp\\Sonidos\\tetris.mp3");
				repro.Play();
			}	
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
	}
	/**
	 * Metodo con las dificultades del juego.
	 * Las dificultades varian cambiando el ancho entre columnas y la velocidad.
	 */
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

	/**
	 * Audio que sonara al pasar por las columnas.
	 */
	public void AudioColumna() {
		try {
			reprocol.AbrirFichero("C:\\Temp\\Sonidos\\Column.wav");
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}

	}
	
	/**
	 * Audio que sonara cuando perdamos.
	 */
	public void AudioMuerte() {
		try {
			death.AbrirFichero("C:\\Temp\\Sonidos\\golpe.wav");
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
	
	/**
	 * Constructor principal
	 */
	public FlappyBird()
	{	
		//al rpincipio de todo cambiamos la dificultad segun la seleccionada por el jugador e iniciamos los diferentes audios.
		cambiarDificultad();
		ReproduceAudio();
		AudioColumna();
		AudioMuerte();
		
		//velocidad a la que se refresca la pantalla, consiguiendo asi el efecto de movimiento de las columnas y el pajaro.
		Timer timer = new Timer(20, this);

		renderer = new Renderer();
		rand = new Random();
		
		//creacion del jframe del juego.
		jframe.add(renderer);
		jframe.setTitle("Flappy Bird");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(WIDTH, HEIGHT);
		jframe.addMouseListener(this);
		jframe.addKeyListener(this);
		jframe.setResizable(false);
		jframe.setVisible(true);

		//variacion del tamaño del pajaro, para adaptarlo a los diferentes iconos segun el codigo con el que juguemos.
		if (Menu.jugador.equals("cide") || Menu.jugador.equals("Cide") || Menu.jugador.equals("CIDE")) {
			bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 75, 75);
		} else {
			bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 50, 50);
		}
		// array para la generacion de columnas.
		columns = new ArrayList<Rectangle>();

		addColumn(true);
		addColumn(true);
		addColumn(true);
		addColumn(true);
		
		timer.start();
	}

	/**
	 *Metodo para crear las columnas
	 * @param start
	 */
	public void addColumn(boolean start)
	{
		//definicion del tamaño
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

	/**
	 * Metodo para dar imagen a las columnas
	 * @param g
	 * @param column
	 */
	public void paintColumn(Graphics g, Rectangle column)
	{
		//Si estamos en el nivel especial del undertale pondremos una imagen de ese juego a las columnas.
		if (Menu.jugador.equals("undertale") || Menu.jugador.equals("Undertale")) {
			ImageIcon tub = new ImageIcon(new ImageIcon(getClass().getResource("/flappyBird/hueso.png")).getImage());
			g.drawImage(tub.getImage(), column.x, column.y, column.width, column.height, null);

		//imagen por defecto de las columnas.		
		}else {
			ImageIcon tub = new ImageIcon(new ImageIcon(getClass().getResource("/flappyBird/Tub.JPG")).getImage());
			g.drawImage(tub.getImage(), column.x, column.y, column.width, column.height, null);

		}

	}
	/**
	 * Metodo del salto del pajaro.
	 */
	public void jump()
	{	
		//que pasa si morimos:
		if (gameOver)
		{	//si morirmos repintamos el pajaro
			if (Menu.jugador.equals("cide") || Menu.jugador.equals("Cide") || Menu.jugador.equals("CIDE")) {
				bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 75, 75);
			} else {
				bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 50, 50);
			}
			//limiamos las columnas que se habian pintado antes
			columns.clear();
			//movimiento a cero
			yMotion = 0;
			//puntuacion a cero
			score = 0;
			//creacion de las nuevas columnas
			addColumn(true);
			addColumn(true);
			addColumn(true);
			addColumn(true);
			//vivimos
			gameOver = false;
		}

		//ymotion es el salto del pajaro
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
	/**
	 * metodo para las acciones dentro del juego
	 */
	public void actionPerformed(ActionEvent e)
	{
		//ticks que se hacen en el juego
		ticks++;
		//que pasa si el juego empieza
		if (started)
		{ 
			//pinta las columnas
			for (int i = 0; i < columns.size(); i++)
			{
				Rectangle column = columns.get(i);
				//aparicion de las columnas
				column.x -= speed;
			}
			//control del salto
			if (ticks % 2 == 0 && yMotion < 15)
			{
				yMotion += 2;
			}
			//limpia las columnas que han pasado y crea de nuevas
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
			//direccion en la que salta el pajaro
			if (Menu.dificultad == "Invertido") {
				//aqui al saltar baja
				bird.y -= yMotion;
			}else {
				//aqui al saltar sube
				bird.y += yMotion;
			}

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

				//cuando toca columna
				if (column.intersects(bird))
				{
					//muere
					gameOver = true;

					if (bird.x <= column.x)
					{
						bird.x = column.x - bird.width;
					}
					else
					{
						try {
							//para el sonido de las columnas
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

		} 
		
		
		
		
		else {
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
		// Nos proporciona el mejor score 
		GetBestScore();
		
		// Pinta el pajaro, el fondo de pantalla y el suelo
		pintaFondo(g);

		// Pinta una columna
		for (Rectangle column : columns)
		{
			paintColumn(g, column);
		}
		
		// Seleccionamos el color de la fuente 
		g.setColor(Color.white);
		// Seleccionamos el tipo de fuente que queremos y el tamaño
		g.setFont(new Font("Arial", 1, 100));

		// Cuando deje de ser game over, reproduce la cancion desde el principio
		if (!gameOver) {
			repro.Play();
		}

		// Cuando started sea false, muesta por pantalla el siguiente mensaje
		if (!started)
		{
			g.drawString("Click to start!", 75, HEIGHT / 2 - 50);
		}

		// Si es Game Over
		if (gameOver)
		{
			// Si el score es mayor al BestScore, guarda el score en el BestScore
			if (score>BestScore) {
				BestScore=score;
				SaveBestScore(BestScore);
			}    
			// Reproduce el sonido de muerte
			death.Play();
			// Escribe por pantalla "Game Over"
			g.drawString("Game Over!", 100, HEIGHT / 2 - 50);
			
			// Escribe por pantalla "Best Score" + la mejor puntuacion actual
			g.drawString("Best Score: " + BestScore, 46, HEIGHT / 2 - 150);
			// Para el sonido de fondo que suena al iniciar el juego
			repro.Stop();

			// Guarda el BestScore
			this.SaveBestScore(BestScore);
		}
		
		// Si game over es falso y started es verdadero
		if (!gameOver && started)
		{
			// Muestra por pantalla la puntuacion actual
			g.drawString(String.valueOf(score), WIDTH / 2 - 25, 100);
		}
		
		// Si game over es falso
		if (!gameOver) {
			// Comprueba que el score es mayor al BestScore
			if (score>BestScore) {	
				// Guarda en el BestScore el mejor socore actual
				BestScore=score;
			}
		}
	}

	/**
	 * Guarda las mejores puntuaciones con el nombre del jugador que realize el jugador el los distintos niveles
	 * @param BestScore
	 */
	public void SaveBestScore(int BestScore) {
		try {
			// Comprobamos el nivel que ha seleccionado el jugador y guardamos el score
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
					// Pasa el cheater a false
					cheater = false;
				}
				// Cierra el Buffered Writer
				bw.close();
			}
		// Captura las diferentes exceptions
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("File can't open");
		} 
	}

	/**
	 * Leemos de un fichero la mejor puntuacion realizada en cada nivel
	 */
	public void GetBestScore() {
		try {
			// Leemos el nivel seleccionado y luego lo pasamos a integer
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
			// Cerramos el Buffered Reader
			br.close();
		// Capturamos las distintas exceptions
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("File can't open");
		} 
	}

	/**
	 *  Una vez dentro del juego, nos deja volber al menu
	 */
	public void volvreAlMenu() {
		// Muestra el menu y no permiye que se cambie el tamaño
		Menu.frame.setVisible(true);
		Menu.frame.setResizable(false);
		// Oculta la ventana del flappyBird
		jframe.setVisible(false);
	}

	public static void main(String[] args){
		// Invocacion del menu
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Haz que el panel del menu sea visible
					Menu.frame.setVisible(true);
					// No se puede modificar el tamaño del menu
					Menu.frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Cuando presionas cualquier boton del raton, el pajaro saltara
	@Override
	public void mouseClicked(MouseEvent e)
	{
		jump();
	}
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		// Cuando dejes de pulsar el espacio, el pajara saltara
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
		// Pulsar escape para ir al menu
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			volvreAlMenu();
			try {
				// Para la musica de fondo + la musica al morir
				repro.Stop();
				death.Stop();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		// Cuando se presiona control + alt, sumara 10 puntos al score
		if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_ALT) {
			// Si es un cheratr, no guardara la puntuacion en el documento
			cheater=true;
			score += 10; 
		}
		
		// Cuando se presione control + c, agrandara el espacio entre columnas
		if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_C) {
			if (space <= 1000) {
				space += 100;
			}
		}

	}

}