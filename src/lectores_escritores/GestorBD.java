package lectores_escritores;

public class GestorBD {

	//Numero de lectores
	private int nLectores = 0;
	private boolean hayEscritor = false;

	public synchronized void openL(int id) throws InterruptedException {
		//Un lector puede entrar siempre que no haya un escritor
		while (hayEscritor) {
			wait();
		}
		//Despierta el lector y entra
		nLectores++;
		System.out.println("Lector " + id + " entra en la BD");
	}

	public synchronized void closeL(int id) {
		System.out.println("Lector " + id + " sale de la BD");
		//
		nLectores--;
		if (nLectores == 0) {
			//Despierta al escritor que estuviera esperando
			notify();
		}
	}

	public synchronized void openE(int id) throws InterruptedException{
		//Mientras no haya escritores o hallan lectores
		while(hayEscritor || nLectores>0) {
			wait();
		}
		hayEscritor=true;
		System.out.println("Escritor "+ id + " entra en la BD");
	}
	
	public synchronized void closeE(int id) {
		System.out.println("Escritor " + id + " sale de la BD");
		hayEscritor=false;
		//Todo el mundo que esté esperando puede entrar
		notifyAll();
	}
	
}





























