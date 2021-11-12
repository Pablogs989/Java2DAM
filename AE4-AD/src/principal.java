import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class principal {

	public static void main(String[] args) throws SQLException {
		
		String jdbcURL = "jdbc:mysql://localhost:3306/AE04-AD";
		String username = "root";
		String password = "";

		String csvFilePath = "/Users/pablo/eclipse-workspace/AE4-AD/AE04_T1_4_JDBC_Dades.csv";

		int batchSize = 20;

		Connection connection = null;

		

		int opcion = 0;

		do {
			Scanner scan = new Scanner(System.in);
			//Menu
			System.out.println("Elige una opcion: ");
			System.out.println("1. Llibres (títol, autor i any de publicació) dels autors nascuts abans de 1950. ");
			System.out.println("2. Editorials que hagen publicat almenys un llibre en el segle XXI.");
			System.out.println("3. Cargar BBDD.");
			System.out.println("4. Cerrar aplicacion.");

			opcion = Integer.parseInt(scan.nextLine());

			if (opcion == 1) {
				//Se conecta con la base de datos, se crea el select, se ejecuta y se muestran todos los resultados que existan.
				connection = DriverManager.getConnection(jdbcURL, username, password);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(
						"SELECT `Titol`, `Autor`, `Any_publicacio` FROM `Libres` WHERE `Any_naixement` < 1950");

				while (rs.next()) {
					System.out.println(rs.getString(1) + " de " + rs.getString(2) + " publicat en " + rs.getString(3));

				}
			}

			if (opcion == 2) {
				//Se conecta con la base de datos, se crea el select, se ejecuta y se muestran todos los resultados que existan.
				connection = DriverManager.getConnection(jdbcURL, username, password);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT `Editorial` FROM `Libres` WHERE `Any_publicacio` >= 2000");

				
				while (rs.next()) {
					System.out.println(rs.getString(1));

				}
			}
			
			if (opcion == 3) {
				try {
					//Se conecta con la base de datos, se crea el insert sin los valores.
					connection = DriverManager.getConnection(jdbcURL, username, password);
					connection.setAutoCommit(false);

					String sql = "INSERT INTO `Libres`(`Titol`, `Autor`, `Any_naixement`, `Any_publicacio`, `Editorial`, `Num_pagines`) VALUES (?, ?, ?, ?, ?, ?)";
					PreparedStatement statement = connection.prepareStatement(sql);

					//Se leen todas las lineas del csv indicado saltando la primera y se van guardando todos los inserts con los datos.
					BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
					String lineText = null;

					int count = 0;

					lineReader.readLine();

					while ((lineText = lineReader.readLine()) != null) {
						String[] data = lineText.split(";");
						String titol = data[0];
						String autor = data[1];
						String any_naixement = data[2];
						String any_publicacio = data[3];
						String editorial = data[4];
						String num_pagines = data[5];

						statement.setString(1, titol);
						statement.setString(2, autor);
						statement.setString(3, any_naixement);
						statement.setString(4, any_publicacio);
						statement.setString(5, editorial);
						statement.setString(6, num_pagines);

						statement.addBatch();

						if (count % batchSize == 0) {
							statement.executeBatch();
						}
					}

					lineReader.close();
					
					//Se ejecutan todos los inserts guardados y se cierra la conexion.
					statement.executeBatch();

					connection.commit();
					connection.close();

				} catch (IOException ex) {
					System.err.println(ex);
				} catch (SQLException ex) {
					ex.printStackTrace();

					try {
						connection.rollback();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			

		} while (opcion != 4);

	}

}
