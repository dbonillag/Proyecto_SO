package productor_consumidor;

import java.util.Random;

public class Productor extends Thread{
	
	private Random r=new Random();
	private Buffer buffer;
	private int cantProductos;
	

	public Productor(Buffer buffer, int cantProductos) {
		this.buffer=buffer;
		this.cantProductos=cantProductos;
	}
	
	@Override
	public void run() {
		for(int i=0;i<cantProductos;i++) {
			
			
			try {
				int producto=r.nextInt(100);
				System.out.println(i+" productor produce "+producto);
				buffer.agregar(producto);
				
				sleep(r.nextInt(100));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
		
		
	}
	
}


