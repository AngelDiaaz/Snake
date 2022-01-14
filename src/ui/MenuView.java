package ui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import enums.Tamanyo;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuView {

	private JFrame frmMenu;
	private JLabel lblBienvenida;
	private JLabel lblPregunta;
	private JButton btnPequenyo;
	private JButton btnGrande;
	private JButton btnMediano;

	/**
	 * Create the application.
	 */
	public MenuView() {
		initialize();
		this.frmMenu.setVisible(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenu = new JFrame();

		configureUIComponents();
		configureListeners();
	}

	private void configureUIComponents() {
		frmMenu.setTitle("Menu");
		frmMenu.setBounds(100, 100, 535, 253);
		frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenu.getContentPane().setLayout(null);

		lblBienvenida = new JLabel("Bienvenido al Juego de la serpiente");
		lblBienvenida.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblBienvenida.setBounds(83, 11, 359, 33);
		frmMenu.getContentPane().add(lblBienvenida);

		lblPregunta = new JLabel("\u00BFDe que tama\u00F1o quieres el mapa para jugar?");
		lblPregunta.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPregunta.setBounds(111, 64, 326, 24);
		frmMenu.getContentPane().add(lblPregunta);

		btnPequenyo = new JButton("Peque\u00F1o");
		btnPequenyo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPequenyo.setBounds(42, 131, 142, 50);
		frmMenu.getContentPane().add(btnPequenyo);

		btnGrande = new JButton("Grande");
		btnGrande.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGrande.setBounds(346, 131, 142, 50);
		frmMenu.getContentPane().add(btnGrande);

		btnMediano = new JButton("Mediano");
		btnMediano.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnMediano.setBounds(194, 131, 142, 50);
		frmMenu.getContentPane().add(btnMediano);
	}
	
private void configureListeners() {
		//Para el boton facil
		btnPequenyo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					var juego = new GameView(Tamanyo.small);
					juego.start();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnGrande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					var juego = new GameView(Tamanyo.big);
					juego.start();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnMediano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					var juego = new GameView(Tamanyo.medium);
					juego.start();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		//JOptionPane mirar
	}
}
