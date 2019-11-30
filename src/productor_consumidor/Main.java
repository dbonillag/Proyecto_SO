package productor_consumidor;

public class Main {

	public static void main(String[] args) {
		Buffer buffer = new Buffer(5);
		Productor p =new Productor(buffer, 100);
		Consumidor c = new Consumidor(buffer, 100);
		p.start();
		c.start();

	}

}
