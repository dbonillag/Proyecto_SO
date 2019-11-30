package productor_consumidor;

import java.util.Random;

public class Consumidor  extends Thread{
	//crea clase random
	private Random r=new Random();
	//instancia del buffer
	private Buffer buffer;
	//cantDeProductos a consumir
	private int cantProductos;
	
	public Consumidor(Buffer buffer, int cantProductos) {
		
		this.buffer=buffer;
		
		this.cantProductos=cantProductos;
		
		
	}
	
	
	@Override
	public void run() {
		
		for (int i = 0; i < cantProductos; i++) {
			int producto;
			try {
				
				producto = buffer.retirar();
				System.out.println(i+" consumidor usa "+producto);
				sleep(r.nextInt(100));
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
