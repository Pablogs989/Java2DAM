import java.util.ArrayList;

public class App {

	public static void main(String[] args) {

		Mina mina = new Mina();
		ArrayList<Minero> mineros = new ArrayList();

		//Se crean 10 mineros y se añaden a una lista
		for (int i = 0; i < 10; i++) {
			Minero minero = new Minero(mina, Integer.toString(i + 1));
			mineros.add(minero);
		}

		//Se crea un hilo por minero mientras siga quedando oro en la mina y se ejecuta
		Thread t;
		do {
			for (Minero minero : mineros) {
				t = new Thread(minero);
				t.setName("Minero " + minero.getNombre());
				t.start();
				System.out.println(t.getName() + " ha extraido " + minero.getBolsa() + " oro.");

			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("\n");

		} while (mina.getStock() > 0);

		
		//Se suma el total extraido y la cantidad extraida por cada minero y se muestra por pantalla
		int total = 0;
		for (Minero minero : mineros) {
			total = total + minero.bolsa;
		}
		for (Minero minero : mineros) {
			System.out.println("Total minero " + minero.getNombre() + ": " + minero.getBolsa());
		}

		System.out.println("Cantidad total extraida: " + total);
	}
}
