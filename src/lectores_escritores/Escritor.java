package lectores_escritores;
import java.util.Random;

public class Escritor extends Thread {
	
	//Se genera un numero aleatorio
	private static Random r = new Random();
	// El objeto pasivo que contiene la base de datos. da permiso para leer y
	// escribir
	private GestorBDJusto gestor;
	//Cada escritor tiene un id
	private int id;

	public Escritor(GestorBDJusto gestor, int id) {
		this.gestor = gestor;
		this.id = id;
	}

	public void run() {
		while (true) {
			try {
				//Llama al escritor y dice que el escritor n quiere acceder
				gestor.openE(id);
				// Escribir en BD
				Thread.sleep(r.nextInt(200));
				//Termina y sale de la BD
				gestor.closeE(id);
				Thread.sleep(r.nextInt(300));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}












