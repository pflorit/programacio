package flappyBird;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Menu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	public static Menu frame = new Menu();
	public static String dificultad;
	public static String jugador;
	public ButtonGroup grupo = new ButtonGroup();
	/**
	 * Create the frame.
	 */
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Flappy Bird");
		setForeground(Color.WHITE);
		setBackground(SystemColor.inactiveCaption);
		setBounds(100, 100, 289, 276);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("START");
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				Empezar();
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Empezar();
			}
		});
		btnNewButton.setBounds(92, 192, 89, 23);
		contentPane.add(btnNewButton);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Empezar();
				}
			}
		});
		textField.setBounds(20, 42, 132, 23);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombre.setBounds(20, 11, 101, 31);
		contentPane.add(lblNombre);
		
		JLabel lblDificultad = new JLabel("Dificultad:");
		lblDificultad.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDificultad.setBounds(20, 72, 101, 31);
		contentPane.add(lblDificultad);
		
		JRadioButton facil = new JRadioButton("F\u00E1cil");
		facil.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Empezar();
				}
			}
		});
		facil.setSelected(true);
		facil.setBounds(80, 110, 109, 23);
		facil.setActionCommand("facil");
		contentPane.add(facil);
		
		JRadioButton dificil = new JRadioButton("Dif\u00EDcil");
		dificil.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Empezar();
				}
			}
		});
		dificil.setBounds(80, 162, 109, 23);
		dificil.setActionCommand("dificil");
		contentPane.add(dificil);
		
		JRadioButton normal = new JRadioButton("Normal");
		normal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Empezar();
				}
			}
		});
		normal.setBounds(80, 136, 109, 23);
		normal.setActionCommand("normal");
		contentPane.add(normal);
		
		grupo.add(facil);
		grupo.add(normal);
		grupo.add(dificil);

	}
	
	public void Empezar() {
			jugador=textField.getText();
			System.out.println("Jugador: "+jugador);
			dificultad = grupo.getSelection().getActionCommand();
			System.out.println("Dificultad: "+dificultad);
			frame.setVisible(false);
			FlappyBird.flappyBird= new FlappyBird();
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
}