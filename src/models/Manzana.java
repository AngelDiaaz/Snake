package models;

import java.awt.Graphics2D;
import java.util.ArrayList;

import ui.GameView;

public class Manzana {

	// Propiedades
	private ArrayList<Cuadrado> manzana;

	public ArrayList<Cuadrado> getManzana() {
		return manzana;
	}

	// Constructor
	public Manzana() {
		manzana = new ArrayList<Cuadrado>();
		
		manzana.add(new Cuadrado(sacarX(), sacarY(), 20, 1));
	}

	/**
	 * Saca un valor que es multiplo de 20 para la coordenada X del juego, asi la
	 * serpiente se puede comer la manzana, porque empieza por ese pixel y asi poder
	 * coincidir en el juego
	 * 
	 * @return Coordenada de X multiplo de 60
	 */
	public int sacarX() {
		int a = (int) (Math.random() * GameView.getX() + 1);
		int b = 20;

		while (multiplo(a, b) == false) {
			a = (int) (Math.random() * GameView.getX() + 1);
		}
		if(Math.abs(GameView.getX() - a) < 50) {
			a -= 100;
		}
		return a;
	}

	/**
	 * Saca un valor que es multiplo de 20 para la coordenada Y del juego, asi la
	 * serpiente se puede comer la manzana, porque empieza por ese pixel y asi poder
	 * coincidir en el juego
	 * 
	 * @return Coordenada de Y multiplo de 60
	 */

	public int sacarY() {
		int a = (int) (Math.random() * GameView.getY() + 1);
		int b = 20;

		while (multiplo(a, b) == false) {
			a = (int) (Math.random() * GameView.getY() + 1);
		}
		if(Math.abs(GameView.getY() - a) < 50) {
			a -= 100;
		}
		return a;
	}
	
	/**
	 * Te dice si un numero es multiplo de otro
	 * @param a Numero que se quiere saber si es multiplo
	 * @param b Numero a comprobar si es multiplo
	 * @return True si es multiplo, false si no lo es
	 */

	private boolean multiplo(int a, int b) {
		if (a % b == 0) {
			return true;
		}

		return false;
	}

	// la manzana también sabe pintarse
	public void pintarse(Graphics2D g) {
		int iCont;

		// pintamos desde el cuadrado 0 hasta el último. Cuidado, aquí con el "<"
		// evitamos
		// tener que poner el "-1" que poniamos en el for del BASIC
		for (iCont = 0; iCont < manzana.size(); iCont++) {
			manzana.get(iCont).pintarse(g);
		}
	}


}
