package ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import enums.Tamanyo;
import models.ControlTeclado;
import models.MyButtonListener;
import models.MySnakeFrame;
import models.TableroJuego;

public class GameView extends Thread {

	private int contador;
	private MySnakeFrame frame;
	private JPanel mainPanel;
	private TableroJuego tablero;
	private JPanel botonera;
	private JLabel puntos;
	private JLabel puntosNum;
	private JButton start;
	private JButton pause;
	private ControlTeclado miControlador;
	private Tamanyo tamanyo;
	private static int x;
	private static int y;

	/**
	 * Create the application.
	 * 
	 * @throws InterruptedException
	 */
	public GameView(Tamanyo tamanyo) throws InterruptedException {
		this.tamanyo = tamanyo;

		// Para seleccionar el tama�o del juego
		if (this.tamanyo.equals(Tamanyo.medium)) {
			x = 900;
			y = 660;
		} else if (this.tamanyo.equals(Tamanyo.big)) {
			x = 1340;
			y = 760;
		} else if (this.tamanyo.equals(Tamanyo.small)) {
			x = 500;
			y = 500;
		}

	}

	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void run() {
		frame = new MySnakeFrame();
		try {
			configureUIComponents();
			configureListeners();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void configureUIComponents() throws InterruptedException {
		// 1. Crear el frame.
		frame = new MySnakeFrame();

		// asignamos el tamaño a nuestra ventana, y hacemos que se cierre cuando nos
		// pulsan
		// la X de cerrar la ventana

		frame.setSize(x, y); // Aqui se modifica el tama�o de la ventana del juego
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 3. Ahora creamos los componentes y los ponemos en la frame (ventana).

		// El panel de fondo. Rellena el frame, y sirve de contenedor del tablero y de
		// la botonera.
		mainPanel = new JPanel(new BorderLayout());

		// Ahora creamos el tablero. Recordamos: no deja de ser un panel un poquito
		// "especial"
		tablero = new TableroJuego();

		// Les damos las propiedades a nuestro tablero. Su color, tamaño y borde
		//tablero.setBorder(BorderFactory.createLineBorder(Color.black));
		tablero.setBackground(new java.awt.Color(172, 219, 249)); // Aqui modifico el color del tablero
		tablero.setSize(x, y);

		// Le damos un enlace al tablero para que sepa quién es su frame (ventana) y
		// así
		// sepa
		// quién contiene la serpiente y quién controla el juego...
		tablero.setSnakeFrame(frame);

		// Ahora el turno de la botonera. Tendrá los dos botones y las etiquetas de
		// texto
		botonera = new JPanel();
		botonera.setBorder(BorderFactory.createLineBorder(Color.black));
		botonera.setBackground(new java.awt.Color(150, 150, 150));

		// Ahora definimos las dos etiquetas para los puntos.
		puntos = new JLabel();
		puntos.setText("Puntos: ");
		puntos.setBackground(new java.awt.Color(190, 190, 190));

		puntosNum = new JLabel();
		puntosNum.setText("0");
		puntosNum.setBackground(new java.awt.Color(190, 190, 190));

		// turno de los botones de empezar y pausar/continuar
		start = new JButton();
		start.setSize(60, 20);
		start.setText("Start");
		start.addActionListener(new MyButtonListener(frame, tablero));

		pause = new JButton();
		pause.setSize(60, 20);
		pause.setText("Pause");
		pause.addActionListener(new MyButtonListener(frame, tablero));

		// Preparamos el control del teclado
		miControlador = new ControlTeclado();
		miControlador.setSnakeFrame(frame); // le damos al controlador de teclado un enlace el frame principal
		tablero.addKeyListener(miControlador); // le decimos al tablero que el teclado es cosa de nuestro controlador
		tablero.setFocusable(true); // permitimos que el tablero pueda coger el foco.

		// Añadimos los componentes uno a uno, cada uno en su contenedor, y al final el
		// panel principal
		// se añade al frame principal.
		botonera.add(start);
		botonera.add(pause);
		botonera.add(puntos);
		botonera.add(puntosNum);

		mainPanel.add(botonera, BorderLayout.PAGE_END);
		mainPanel.add(tablero, BorderLayout.CENTER);
		frame.getContentPane().add(mainPanel);

		frame.setVisible(true); // activamos la ventana principal para que sea "pintable"

	}

	private void configureListeners() throws InterruptedException {

		// Te muestra la pesta�a para indicar la dificultad que quieres en el juego
		Object seleccion = JOptionPane.showInputDialog(null, "Seleccione dificultad", "Dificultad del juego",
				JOptionPane.QUESTION_MESSAGE, null, // null para icono defecto
				new Object[] { "Facil", "Intermedio", "Dificil", "Imposible" }, "Intermedio");

		int dificultad = 0;

		if (seleccion.equals("Facil")) {
			dificultad = 40;
		} else if (seleccion.equals("Intermedio")) {
			dificultad = 20;
		} else if (seleccion.equals("Dificil")) {
			dificultad = 8;
		} else if (seleccion.equals("Imposible")) {
			dificultad = 1;
		}
		contador = 0; // nuestro control de los pasos del tiempo. Cada vez que contador cuenta un

		// paso, pasan 10ms

		while (true) { // por siempre jamás (hasta que nos cierren la ventana) estamos controlando el
			// juego.

			// actualizamos el estado del juego
			if (contador % dificultad == 0) { // cada 200ms nos movemos o crecemos... // Aqui se cambia la velocidad de
												// la
				// serpiente en el juego
				if (contador == 60) { // Cada 600ms crecemos y reseteamos el contador
					contador = 0;
						// hemos crecido... actualizamos puntos.
						puntosNum.setText(Integer.toString(frame.getSerpiente().getPuntos()));
						frame.comprobarEstado(tablero.getHeight() - 20, tablero.getWidth() - 20); // comprobamos si hemos muerto o no.
						frame.tocaMoverse();
				} else { // a los 200 y 400 ms nos movemos...
					contador++;
					frame.tocaMoverse();
				}
				frame.comprobarEstado(tablero.getHeight() - 20, tablero.getWidth() - 20); // comprobamos si hemos muerto o no.

																				// //Quitando esta linea de codigo
																				// hariamos que la
																				// serpiente fuese invencible

			} else { // Cada vez que no hay que moverse o crecer, simplemente contamos...
				contador++;
			}

			// hemos terminado?? mostramos msg
			if (frame.mostrarFin()) {
				JOptionPane.showMessageDialog(frame,
						"Se acabo vaquero, has conseguido " + puntosNum.getText() + " puntos");
			}

			// Repintamos
			tablero.repaint();

			// Esperamos para dar tiempo al thread de repintado a pintar.
			Thread.sleep(5);

		}
	}

	public static int getX() {
		return x;
	}

	public static int getY() {
		return y; //- 20??
	}

}
