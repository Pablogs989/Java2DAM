package AE2;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Lanzador {
	
	static int cores = Runtime.getRuntime().availableProcessors();
	static int maxCores = 1;
	public void lanzarCalculo(String n1,Double n2, Double n3){
		String clase = "AE2.Calculos";

		try {
	
			String javaHome = System.getProperty("java.home");
			String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
			String classpath = System.getProperty("java.class.path");
			String className = clase;

			List<String> command = new ArrayList<>();
			command.add(javaBin);
			command.add("-cp");
			command.add(classpath);
			command.add(className);
			command.add(n1);
			command.add(n2.toString());
			command.add(n3.toString());
			
			ProcessBuilder builder = new ProcessBuilder(command);
			Process process = builder.inheritIO().start();
			
			if (cores == maxCores) {
				process.waitFor();
				maxCores = 1;


			} else {
				maxCores++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String[] leerNeos(){
		String text = "";
		FileReader fr;
		try {
			fr = new FileReader("NEOs.txt", StandardCharsets.ISO_8859_1);
			int valor = fr.read();
			while (valor != -1) {
			text += (char)valor;
			valor = fr.read();
		}
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] neos = text.split("\n");
		
		return neos;
	}
	
	
	public static void main(String[] args){
		long tInicio, tFin, tiempo;
		tInicio = System.currentTimeMillis();
		Lanzador l = new Lanzador();
		String[] neos =  leerNeos();
		
		for (String neo : neos) {
			if (cores >= maxCores) {
				String[] n = neo.split(",");
				l.lanzarCalculo(n[0] , Double.parseDouble(n[1]), Double.parseDouble(n[2]));
				}
			}
		
		tFin = System.currentTimeMillis();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tiempo = tFin - tInicio;
		System.out.println("Tiempo de ejecucion medio: " + tiempo/neos.length + " milisegundos");
		System.out.println("Tiempo de ejecucion total: " + tiempo + " milisegundos");

	}
}