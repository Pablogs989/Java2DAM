
public class Minero implements Runnable {

	int bolsa;
	int tiempoExtraccion;
	Mina mina;
	String nombre;

	//El constructor pide la mina en la que trabajara el minero y su nombre
	public Minero(Mina mina, String nombre) {
		this.bolsa = 0;
		this.tiempoExtraccion = 1000;
		this.mina = mina;
		this.nombre = nombre;
	}

	//El oro se extrae de la mina y se añade a la bolsa del minero
	synchronized public void extraerRecurso(String nombre, int oro) {

		if (mina.getStock() > 0) {
			mina.setStock(mina.getStock() - oro);
			this.bolsa += oro;
		}
	}

	public void run() {
		extraerRecurso(this.nombre, 100);
	}

	public String getNombre() {
		return nombre;
	}

	public int getBolsa() {
		return bolsa;
	}
}
