package AE5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class principal {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int opcion;
		int idLlibre;

		// Menu con todas las opciones

		do {
			System.out.println("");
			System.out.println("GESTIO BIBLIOTECA");
			System.out.println("_________________________________________________");
			System.out.println("");
			System.out.println("1. Mostrar tots els títols de la biblioteca");
			System.out.println("2. Mostrar informació detallada d’un llibre");
			System.out.println("3. Crear nou llibre");
			System.out.println("4. Actualitzar llibre");
			System.out.println("5. Borrar llibre");
			System.out.println("6. Tanca la biblioteca");
			System.out.println("_________________________________________________");

			System.out.println("Tria una opcio: (1...6) ");
			opcion = scan.nextInt();

			switch (opcion) {
			case 1:
				mostrarLlibres();
				break;
			case 2:
				System.out.println("Id del llibre: ");
				idLlibre = scan.nextInt();
				mostrarLlibre(idLlibre);
				break;
			case 3:
				crearLlibre();
				break;
			case 4:
				System.out.println("Id del llibre: ");
				idLlibre = scan.nextInt();
				modificarLlibre(idLlibre);

				break;
			case 5:
				System.out.println("Id del llibre a eliminar: ");
				idLlibre = scan.nextInt();
				borrarLlibre(idLlibre);

				break;
			default:
				break;
			}
		} while (opcion != 6);
	}

	// Lee todos los datos de la BBDD, crea objetos Llibre con esos datos, los añade
	// a una lista y los muestra.

	public static void mostrarLlibres() {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Llibre.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
				.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		List llistaLlibres = new ArrayList();
		llistaLlibres = session.createQuery("FROM Llibre").list();

		for (int i = 0; i < llistaLlibres.size(); i++) {
			Llibre llibre = (Llibre) llistaLlibres.get(i);
			int identificador = llibre.getIdentificador();
			String titol = llibre.getTitol();
			System.out.println("Titol del llibre " + identificador + ": " + titol);

		}

		session.getTransaction().commit();
		session.close();
	}

	// Lee todos los datos de la BBDD, crea objetos Llibre con esos datos, los añade
	// a una lista y, cuando la id coincide con la introducida los muestra.

	public static void mostrarLlibre(int id) {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Llibre.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
				.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		List llistaLlibres = new ArrayList();
		llistaLlibres = session.createQuery("FROM Llibre").list();

		for (int i = 0; i < llistaLlibres.size(); i++) {
			Llibre llibre = (Llibre) llistaLlibres.get(i);
			int identificador = llibre.getIdentificador();

			if (identificador == id) {
				System.out.println("Informacio del llibre " + identificador);
				String titol = llibre.getTitol();
				System.out.println("Titol: " + titol);
				String autor = llibre.getAutor();
				System.out.println("Autor: " + autor);
				String anyNaixement = llibre.getAny_naixement();
				System.out.println("Any naixement del autor: " + anyNaixement);
				String anyPublicacio = llibre.getAny_publicacio();
				System.out.println("Any de publicacio: " + anyPublicacio);
				String editorial = llibre.getEditorial();
				System.out.println("Editorial: " + editorial);
				String numPagines = llibre.getNum_pagines();
				System.out.println("Numero de pagines: " + numPagines);
			}

		}

		session.getTransaction().commit();
		session.close();
	}

	// Crea un objeto Llibre con los datos introducidos y lo sube a la base de
	// datos.

	public static void crearLlibre() {
		Scanner scan = new Scanner(System.in);
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Llibre.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
				.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		System.out.println("Titol: ");
		String titol = scan.nextLine();

		System.out.println("Autor: ");
		String autor = scan.nextLine();

		System.out.println("Any naixement del autor: ");
		String anyNaixement = scan.nextLine();

		System.out.println("Any de publicacio: ");
		String anyPublicacio = scan.nextLine();

		System.out.println("Editorial: ");
		String editorial = scan.nextLine();

		System.out.println("Numero de pagines: ");
		String numPagines = scan.nextLine();

		Llibre lib = new Llibre(titol, autor, anyNaixement, anyPublicacio, editorial, numPagines);
		Serializable id = session.save(lib);

		session.getTransaction().commit();
		session.close();
	}

	// Lee los datos de un objeto Llibre en la BBDD dada su id y los cambia por los
	// que se introduzcan.

	public static void modificarLlibre(int id) {
		Scanner scan = new Scanner(System.in);
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Llibre.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
				.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		System.out.println("Titol: ");
		String titol = scan.nextLine();

		System.out.println("Autor: ");
		String autor = scan.nextLine();

		System.out.println("Any naixement del autor: ");
		String anyNaixement = scan.nextLine();

		System.out.println("Any de publicacio: ");
		String anyPublicacio = scan.nextLine();

		System.out.println("Editorial: ");
		String editorial = scan.nextLine();

		System.out.println("Numero de pagines: ");
		String numPagines = scan.nextLine();

		Llibre llibre = (Llibre) session.load(Llibre.class, id);
		llibre.setTitol(titol);
		llibre.setAutor(autor);
		llibre.setAny_naixement(anyNaixement);
		llibre.setAny_publicacio(anyPublicacio);
		llibre.setEditorial(editorial);
		llibre.setNum_pagines(numPagines);

		session.update(llibre);

		session.getTransaction().commit();
		session.close();

	}

	// Dada la id de un objeto Llibre lo elimina de la base de datos.

	public static void borrarLlibre(int id) {
		Scanner scan = new Scanner(System.in);
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Llibre.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
				.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Llibre llibre = new Llibre();
		llibre.setIdentificador(id);
		session.delete(llibre);

		session.getTransaction().commit();
		session.close();

	}

}
