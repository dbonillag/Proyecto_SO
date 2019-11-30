package lectores_escritores;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GestorBDLockCasa {

	// Numero de lectores
	private int nLectores = 0;
	private boolean hayEscritor = false;
	// numero de escritores
	private int nEscritor = 0;
	private Lock l = new ReentrantLock(true);
	private Condition okLeer = l.newCondition();
	private Condition okEscribir = l.newCondition();

	public void openL(int id) throws InterruptedException {
		l.lock();
		try {
			// Si hay un escritor no puedo entrar, y si hay escritores esperando tampoco voy
			// a entrar. Dandole preferencia a los escritores
			while (hayEscritor || nEscritor > 0) {
				okLeer.await();
				nLectores++;
				System.out.println("Lector " + id + " entra en la BD");
			}
		} finally {
			l.unlock();
		}
	}

	public void closeL(int id) {
		l.lock();
		try {
			System.out.println("Lector " + id + " sale de la BD");
			nLectores--;
			if (nLectores == 0) {
				okEscribir.signal();
			}

		} finally {
			l.unlock();
		}
	}

	public void openE(int id) throws InterruptedException {
		l.lock();
		try {
			nEscritor++;
			while (hayEscritor || nLectores > 0) {
				okEscribir.await();
			}
			hayEscritor = true;
			System.out.println("Escritor " + id + " entra en la BD");
		} finally {
			l.unlock();
		}
	}

	public void closeE(int id) {
		l.lock();
		try {
			nEscritor--;
			System.out.println("Escritor " + id + " sale de la BD");
			hayEscritor = false;
			if (nEscritor > 0) {
				okEscribir.signal();
			} else {
				okLeer.signalAll();
			}
		} finally {
			l.unlock();
		}
	}

}
