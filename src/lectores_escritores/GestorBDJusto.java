package lectores_escritores;

public class GestorBDJusto {

	private int nLectores = 0;
	private boolean hayEscritor = false;
	private int nEscritor = 0;

	public synchronized void openL(int id) throws InterruptedException {
		// Si hay un escritor no puedo entrar, y si hay escritores esperando tampoco voy
		// a entrar. Dandole preferencia a los escritores
		while (hayEscritor || nEscritor > 0) {
			wait();
		}
		nLectores++;
		System.out.println("Lector " + id + " entra en la BD");
	}

	public synchronized void closeL(int id) {
		System.out.println("Lector " + id + " sale de la BD");
		nLectores--;
		if (nLectores == 0) {
			// Una vez que termino dejo a todos entrar
			notifyAll();
		}
	}

	public synchronized void openE(int id) throws InterruptedException {
		// Hay un escritor más
		nEscritor++;
		// Los lectores van a ver que hay un lector esperando y van a parar
		while (hayEscritor || nLectores > 0) {
			wait();
		}
		hayEscritor = true;
		System.out.println("Escritor " + id + " entra en la BD");
	}

	public synchronized void closeE(int id) {
		// Hay un escritor menos
		nEscritor--;
		System.out.println("Escritor " + id + " sale de la BD");
		hayEscritor = false;
		notifyAll();
	}

}
