package lectores_escritores;

import java.util.Random;

public class Lector extends Thread {

	// Se genera un numero aleatorio
	private static Random r = new Random();
	// El objeto pasivo que contiene la base de datos. da permiso para leer y
	// escribir
	private GestorBDLockCasa gestor;
	//Cada lector tiene un id
	private int id;

	public Lector(GestorBDLockCasa gestor, int id) {
		this.gestor = gestor;
		this.id = id;
	}

	public void run() {
		while (true) {
			try {
				// Llama al gestor y le dice que el lector n quiere leer
				gestor.openL(id);
				// Leyendo BD
				Thread.sleep(r.nextInt(200));
				// Una vez ha terminado de leer
				gestor.closeL(id);
				Thread.sleep(r.nextInt(300));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
