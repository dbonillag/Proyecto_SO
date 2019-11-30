package productor_consumidor;

import java.util.concurrent.*;

public class Buffer {

	//arreglo para representar al buffer de datos
	private int[] b;
	//posiones donde tienen que actuar el productor y el consumidor respectivamente
	private int i,j;
	//semaforo que controla que el productor y el  consumidor no utilicen el buffer a la vez
	private Semaphore mutex=new Semaphore(1, true);
	//comprueba que haya productos en el buffr
	private Semaphore hayProductos=new Semaphore(0,true);
	private Semaphore hayEspacio;
	
	public Buffer(int tam) {
		//se le asigna tamaño al buffer
		b=new int[tam];
		//se le asigna el tamaño de hilos que pueden acceder al mismo recurso
		hayEspacio=new Semaphore(b.length,true);
		
	}
	
	public void agregar(int producto) throws InterruptedException {
		//verifico que halla espacio en el buffer
		hayEspacio.acquire();
		//verifico que se pueda usar el buffer
		mutex.acquire();
		//agrego el producto en la posición i
		b[i]=producto;
		//System.out.println("Productor produjo "+producto);
		//paso a la siguiente posición del  buffer
		i=(i+1)%b.length;
		//libero el buffer
		mutex.release();
		//Indico que hay datos en el buffer
		hayProductos.release();
		
	}
	
	public int retirar() throws InterruptedException {
		//comprueba que haya productos
		hayProductos.acquire(); 
		mutex.acquire();
		int aux=j;
		j=(j+1)%b.length;
		//System.out.println("Consumidor usó"+b[aux]);
		mutex.release();
		hayEspacio.release();
		return b[aux];
		
		
		
	}
	
	

}
