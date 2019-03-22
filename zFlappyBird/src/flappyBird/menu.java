package flappyBird;

import java.awt.Color;
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

public class menu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	public static menu frame = new menu();
	public int dificultad;
	public static String jugador;
	/**
	 * Create the frame.
	 */
	public menu() {
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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jugador=textField.getText();
				frame.setVisible(false);
				FlappyBird.flappyBird = new FlappyBird();
			}
		});
		btnNewButton.setBounds(92, 192, 89, 23);
		contentPane.add(btnNewButton);

		textField = new JTextField();
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
		facil.setSelected(true);
		facil.setBounds(80, 110, 109, 23);
		contentPane.add(facil);
		
		JRadioButton dificil = new JRadioButton("Dif\u00EDcil");
		dificil.setBounds(80, 162, 109, 23);
		contentPane.add(dificil);
		
		JRadioButton normal = new JRadioButton("Normal");
		normal.setBounds(80, 136, 109, 23);
		contentPane.add(normal);
		
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(facil);
		grupo.add(normal);
		grupo.add(dificil);
		
		if (facil.isSelected()){
			dificultad=1;
		}else if (normal.isSelected()) {
			dificultad=2;
		}else if (dificil.isSelected()) {
			dificultad=3;
		}
	}
}