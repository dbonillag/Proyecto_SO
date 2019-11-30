package lectores_escritores;

public class Principal {

	public static void main(String[] args) {

		GestorBDJusto gestor = new GestorBDJusto();
		//Lista de escritores
		Escritor[] esc = new Escritor[2];
		//Lista de lectores
		Lector[] lector = new Lector[10];

		//Bucle para crear escritores
		for (int i = 0; i < esc.length; i++) {
			esc[i] = new Escritor(gestor, i);
		}

		//Bucle para crear lectores
		for (int i = 0; i < lector.length; i++) {
			lector[i] = new Lector(gestor, i);
		}
		
		//Bucle para iniciar los hilos de los escritores
		for (int i = 0; i < esc.length; i++) {
			esc[i].start();
		}

		//Bucle para iniciar los hilos de los lectores
		for (int i = 0; i < lector.length; i++) {
			lector[i].start();
		}
	}

}













