package AE2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class Calculos {

	public double calcularProbabilidad(double posicionNEO, double velocidadNEO) {
		
		double posicionTierra = 1;
		double velocidadTierra = 100;
		for (int i=0;i<(50*365*24*60*60);i++){
		       posicionNEO = posicionNEO + velocidadNEO * i;
		       posicionTierra = posicionTierra + velocidadTierra * i;
		}
		double resultado = 100 * Math.random() *
		Math.pow( ((posicionNEO-posicionTierra)/(posicionNEO+posicionTierra)), 2);
		return resultado;
		}
	
public static void guardarNeo(String nombre, double porcentaje) throws IOException {
		
		BufferedWriter bw = null;
		try {
			File fichero = new File(System.getProperty("user.dir") + File.separator + nombre + ".txt");


			bw = new BufferedWriter(new FileWriter(fichero));
			bw.write("Probabilidad de colisión: " + String.valueOf(porcentaje) + "%");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			bw.close();
		}
		
	}

	
	public static void main(String[] args){
		Calculos c = new Calculos();
		String nombre = args[0];
		try {
		double posicion = Double.parseDouble(args[1]);
		double velocidad = Double.parseDouble(args[2]);
		double resultado = c.calcularProbabilidad(posicion, velocidad);
		DecimalFormat df = new DecimalFormat("#.##");
		guardarNeo(nombre, resultado);
		if (resultado > 10.00) {
			System.err.println("ALERTA. Probabilidad de colisión de " + args[0] + ": " + df.format(resultado) + "%");
		} else System.out.println("Probabilidad de colisión de " + args[0] + ": " + df.format(resultado) + "%");
		} catch (IOException e) {
			e.printStackTrace();
		}
		}

}
